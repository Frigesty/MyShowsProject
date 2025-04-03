package ru.frigesty.api;

import io.qameta.allure.Step;
import ru.frigesty.models.body.LoginBodyModel;
import ru.frigesty.models.response.LoginResponseModel;

import static io.restassured.RestAssured.given;
import static ru.frigesty.data.Credentials.LOGIN;
import static ru.frigesty.data.Credentials.PASSWORD;
import static ru.frigesty.data.Paths.LOGIN_PATH;
import static ru.frigesty.specs.ApiSpecs.requestSpecBase;
import static ru.frigesty.specs.ApiSpecs.responseSpecBase;

public class LoginApiSteps {

    @Step("Авторизация через API")
    public LoginResponseModel login() {

        LoginBodyModel credentialsModel = new LoginBodyModel(LOGIN, PASSWORD);

        return
                given(requestSpecBase)
                        .body(credentialsModel)
                        .log().all()
                        .when()
                        .post(LOGIN_PATH)
                        .then()
                        .log().all()
                        .spec(responseSpecBase)
                        .extract().as(LoginResponseModel.class);
    }
}