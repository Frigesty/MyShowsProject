package ru.frigesty.pages;

import io.qameta.allure.Step;
import ru.frigesty.data.Locale;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ru.frigesty.data.Credentials.*;

public class MainPage {

    @Step("Открываем главную страницу")
    public MainPage openMainPage() {
        open("/");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    @Step("Кликаем на кнопку войти")
    public MainPage clickOnTheEnterButton() {
        $(".HeaderLogin").$(byText("Войти"))
                .shouldBe(visible, clickable)
                .click();
        return this;
    }

    @Step("Вводим логин и пароль")
    public MainPage setLoginAndPassword() {
        $("input.Input__field[autocomplete='username']").setValue(LOGIN);
        $("input.Input__field[autocomplete='current-password']").setValue(PASSWORD);
        return this;
    }

    @Step("Вводим логин и пароль")
    public MainPage setBaLoginAndPassword() {
        $("input.Input__field[autocomplete='username']").setValue("BadLogin");
        $("input.Input__field[autocomplete='current-password']").setValue(PASSWORD);
        return this;
    }

    @Step("Кликаем на кнопку войти в окне Pop-up")
    public MainPage clickOnTheLoginButtonInThePopUpWindow() {
        $(".ModalLoginForm__submit").click();
        return this;
    }

    @Step("Проверяем что мы авторизовались")
    public MainPage letsCheckThatWeAreAuthorized() {
        $(".HomeRookie__title").shouldHave(text("GoodGuy"));
        return this;
    }

    @Step("Проверяем что мы авторизовались")
    public MainPage checkThatWeAreAuthorized() {
        $(".Input__field.error").shouldBe(visible);
        return this;
    }

    @Step("Кликаем на иконку выбора языка в футере")
    public MainPage clickOnTheLanguageSelectionIconInTheFooter() {
        $(".LangSwitcher .LangSwitcher-current .LangSwitcher-option")
                .shouldBe(visible, clickable)
                .scrollTo()
                .click();
        return this;
    }

    @Step("Выбираем в открывшимся меню язык {0}")
    public MainPage selectLanguageFromTheMenuThatOpens(Locale locale) {
        $$(".LangSwitcher-options .LangSwitcher-option")
                .find(text(locale.name()))
                .scrollTo()
                .click();
        return this;
    }

    @Step("Проверяем что элементы меню соответствуют локали {0}")
    public MainPage checkThatMenuItemsMatchLocale(List<String> buttons) {
        $$(".HeaderMenu .HeaderMenu__item")
                .shouldHave(texts(buttons));

        return this;
    }

    @Step("Вводим название сериала в поле поиска")
    public MainPage enterTheNameOfTheSeriesInTheSearchField(String value) {
        $(".Search-input").shouldBe(visible, enabled).setValue(value).shouldHave(value(value));
        return this;
    }

    @Step("Нажимаем на кнопку найти рядом со строкой поиска")
    public MainPage clickOnTheFindButtonNextToTheSearchBar() {
        $(".Search-submit").shouldBe(visible, clickable).click();
        return this;
    }
}