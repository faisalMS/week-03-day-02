package com.example.usersmanagementsoftware.Repositroy;

import com.example.usersmanagementsoftware.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    List<Users> findAllById(Integer id);
    Users findByEmail(String email);
    List<Users> getByRole (String role);
    List<Users> findAllByAgeGreaterThanEqual(Integer age);
    Users findUsersByUsernameAndPassword (String username, Integer password);

    @Query("SELECT u from Users  u where u.joiningYear =?1 and u.id =?2")
    Users checkJoinYear(String  joiningYear,Integer id);
    @Query("SELECT  u from Users u where u.joiningYear =?1")
    List<Users> getUsersByJoiningYear(String joiningYear);

    @Query("SELECT u FROM Users u where u.age=?1 and u.joiningYear<=?2")
    List<Users> getByAgeAndYearJoin(String age,String joiningYear);



}
