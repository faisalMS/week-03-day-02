package com.example.usersmanagementsoftware.Controller;

import com.example.usersmanagementsoftware.Model.Users;
import com.example.usersmanagementsoftware.Service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor

public class UsersController {

    private final UsersService usersService;

    @GetMapping
    public ResponseEntity<List<Users>> getUser(){
        return ResponseEntity.status(HttpStatus.OK).body(usersService.getUser());
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<Users>> getUserByAll(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(usersService.getUserByAll(id));
    }

    @GetMapping("/email")
    public ResponseEntity<Users> getUserByEmail(@RequestParam String email){
        return ResponseEntity.status(HttpStatus.OK).body(usersService.getUserByEmail(email));
    }

    @GetMapping("/age")
    public ResponseEntity<List<Users>> getUserByAge(@RequestParam Integer age){
        return ResponseEntity.status(HttpStatus.OK).body(usersService.getUserByAge(age));
    }

    @GetMapping("/role")
    public ResponseEntity<List<Users>> getUserByRole(@RequestParam String role){
        return ResponseEntity.status(HttpStatus.OK).body(usersService.getUserByRole(role));
    }

    @GetMapping("/checkUser/{username}/{password}")
    public ResponseEntity checkUsers(@PathVariable String username,@PathVariable Integer password){
        if (!usersService.checkUsers(username,password)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong username or password");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Correct username and password");
    }

    @GetMapping("{joiningYear}/{id}")
    public ResponseEntity checkJoinYear(@PathVariable String joiningYear,@PathVariable Integer id){
        if(usersService.checkJoinYear(joiningYear,id)) {
            return ResponseEntity.status(HttpStatus.OK).body("User joined that year");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User didn't join that year");
    }

    @GetMapping("/userYear/{joiningYear}")//
    public ResponseEntity<List<Users>> getUsersByJoiningYear(@PathVariable String joiningYear){
        return ResponseEntity.status(HttpStatus.OK).body(usersService.getUsersByJoiningYear(joiningYear));
    }

    @GetMapping("/ageYear")
    public ResponseEntity<List<Users>> getByAgeAndYearJoin(@RequestParam String age,@RequestParam String year){
        return ResponseEntity.status(200).body(usersService.getByAgeAndYearJoin(age,year));
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody @Valid Users users, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        usersService.addUser(users);
        return ResponseEntity.status(HttpStatus.OK).body("User added!");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUsers(@RequestBody Users users, @PathVariable Integer id){
        usersService.updateUsers(users, id);
        return ResponseEntity.status(HttpStatus.OK).body("User update !");
    }

    @PutMapping("/{id}/{password}")
    public ResponseEntity updatePassword(@PathVariable Integer id, @PathVariable Integer password){
        usersService.updatePassword(id, password);
        return ResponseEntity.status(HttpStatus.OK).body("Password update !");
    }
}
