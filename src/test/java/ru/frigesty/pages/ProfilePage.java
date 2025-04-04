package ru.frigesty.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {


    @Step("Открываем страницу профиля")
    public ProfilePage openProfilePage() {
        open("/GoodGuy");
        return null;
    }

    @Step("Проверяем что сериал есть в блоке с сериалами")
    public ProfilePage checkThatTheSeriesIsInTheBlockWithSeries() {
        $(".UserShowsBlock__shows").shouldHave(text("Игра престолов"));
        return null;
    }


}
