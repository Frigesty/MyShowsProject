package ru.frigesty.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.frigesty.pages.MainPage;

@Tag("simple")
@Story("Авторизация")
@Feature("Проверка авторизации")
@DisplayName("Тесты на авторизацию")
public class AuthorizationTests extends TestBase {

    MainPage mainPage = new MainPage();

    @Tag("auto")
    @DisplayName("Тест на успешную UI авторизацию")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Frigesty")
    @Test
    void ForSuccessfulUiAuthorizationTest() {
        mainPage.openMainPage();
        mainPage.clickOnTheEnterButton();
        mainPage.setLoginAndPassword();
        mainPage.clickOnTheLoginButtonInThePopUpWindow();
        mainPage.letsCheckThatWeAreAuthorized();
    }

    @Tag("auto")
    @DisplayName("Тест на провальную UI авторизацию")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Frigesty")
    @Test
    void forFailedUiAuthorizationTest() {
        mainPage.openMainPage();
        mainPage.clickOnTheEnterButton();
        mainPage.setBaLoginAndPassword();
        mainPage.clickOnTheLoginButtonInThePopUpWindow();
        mainPage.checkThatWeAreAuthorized();
    }
}