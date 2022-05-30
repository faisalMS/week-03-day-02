package com.example.usersmanagementsoftware.Service;

import com.example.usersmanagementsoftware.Model.Users;
import com.example.usersmanagementsoftware.Repositroy.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {


    private final UsersRepository usersRepository;

    public List<Users> getUser(){
        return usersRepository.findAll();
    }

    public void addUser(Users users) {
        usersRepository.save(users);
    }

    public List<Users> getUserByAll(Integer id) {
        return usersRepository.findAllById(id);
    }


    public Users getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    public List<Users> getUserByAge(Integer age) {
        return usersRepository.findAllByAgeGreaterThanEqual(age);
    }

    public List<Users> getUserByRole(String role) {
        return usersRepository.getByRole(role);
    }

    public boolean checkUsers(String username, Integer password) {
        if(usersRepository.findUsersByUsernameAndPassword(username,password) != null)
            return true;
        return false;
    }

    public void updateUsers(Users newUsers, Integer id){
        Users oldUser = usersRepository.findById(id).get();
        oldUser.setUsername(newUsers.getUsername());
        oldUser.setPassword(newUsers.getPassword());
        oldUser.setEmail(newUsers.getEmail());
        oldUser.setRole(String.valueOf(newUsers.getRole().equals("Admin")));
        oldUser.setJoiningYear(newUsers.getJoiningYear());
        oldUser.setAge(newUsers.getAge());
        usersRepository.save(oldUser);
    }

    public void updatePassword(Integer id, Integer password){
        Users newUsers = usersRepository.findById(id).get();
        newUsers.setPassword((password));
        usersRepository.save(newUsers);
    }

    public boolean checkJoinYear(String joiningYear, Integer id)  {
        Users checkUser = usersRepository.checkJoinYear(joiningYear,id);
        if(checkUser != null){
            if(checkUser.getJoiningYear().equals(joiningYear))
                return true;
        }
        return false;
    }
    public List<Users> getUsersByJoiningYear(String joiningYear){
        return usersRepository.getUsersByJoiningYear(joiningYear);
    }

    public List<Users> getByAgeAndYearJoin(String age, String year) {
        return usersRepository.getByAgeAndYearJoin(age,year);
    }
}
