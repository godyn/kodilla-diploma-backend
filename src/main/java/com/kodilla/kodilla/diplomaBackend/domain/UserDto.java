package com.kodilla.kodilla.diplomaBackend.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private long id;

    private String login;

    private String password;

    private String mail;

    private String firstname;

    private String lastname;

    private UserRole role;


}
