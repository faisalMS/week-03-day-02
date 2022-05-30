package com.example.usersmanagementsoftware.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.Date;

@AllArgsConstructor @NoArgsConstructor @Data
@Entity
public class Users {

    @Id
    @Column(nullable = false)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private Integer password;
    @Email(message = "It must be an email")
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    @Pattern(regexp = "(Admin|Customer)")
    private String role;
    private Integer joiningYear;
    @Column(nullable = false)
    @Positive(message = "It must be a number")
    private Integer age;
}
