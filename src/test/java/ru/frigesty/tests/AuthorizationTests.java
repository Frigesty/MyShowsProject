package ru.frigesty.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.frigesty.pages.MainPage;

@Tag("authorisation")
@Story("Авторизация")
@Feature("Проверка авторизации")
@DisplayName("Тесты на авторизацию")
public class AuthorizationTests extends TestBase {

    MainPage mainPage = new MainPage();

    @Tag("authorisation")
    @DisplayName("Тест на успешную UI авторизацию")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Frigesty")
    @Test
    void ForSuccessfulUiAuthorizationTest() {
        mainPage.openMainPage()
                .clickOnTheEnterButton()
                .setLoginAndPassword()
                .clickOnTheLoginButtonInThePopUpWindow()
                .letsCheckThatWeAreAuthorized();
    }

    @Tag("authorisation")
    @DisplayName("Тест на провальную UI авторизацию")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Frigesty")
    @Test
    void forFailedUiAuthorizationTest() {
        mainPage.openMainPage()
                .clickOnTheEnterButton()
                .setBaLoginAndPassword()
                .clickOnTheLoginButtonInThePopUpWindow()
                .checkThatWeAreAuthorized();
    }
}