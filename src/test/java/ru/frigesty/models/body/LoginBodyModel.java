package ru.frigesty.models.body;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginBodyModel {
    String login, password;
}