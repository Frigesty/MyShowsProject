package ru.frigesty.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {

    private final SelenideElement userSeriesBlock = $(".UserShowsBlock__shows");

    @Step("Открыть страницу профиля")
    public ProfilePage openProfilePage() {
        open("/GoodGuy");
        return this;
    }

    @Step("Проверить, что сериал отображается в блоке сериалов")
    public ProfilePage checkThatTheSeriesIsInTheBlockWithSeries() {
        userSeriesBlock.shouldHave(text("Игра престолов"));
        return this;
    }
}