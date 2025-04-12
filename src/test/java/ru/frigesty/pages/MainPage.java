package ru.frigesty.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.frigesty.data.Locale;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ru.frigesty.data.Credentials.*;

public class MainPage {

    private final SelenideElement mainLoginButton = $(".HeaderLogin").$(byText("Войти"));
    private final SelenideElement usernameField = $("input.Input__field[autocomplete='username']");
    private final SelenideElement passwordField = $("input.Input__field[autocomplete='current-password']");
    private final SelenideElement popupLoginButton = $(".ModalLoginForm__submit");
    private final SelenideElement authorizedUserLabel = $(".HeaderLogin__user");
    private final SelenideElement loginErrorInputField = $(".Input__field.error");
    private final SelenideElement footerLanguageSelectorIcon = $(".LangSwitcher .LangSwitcher-current .LangSwitcher-option");
    private final ElementsCollection languageOptionsList = $$(".LangSwitcher-options .LangSwitcher-option");
    private final ElementsCollection headerMenuItems = $$(".HeaderMenu .HeaderMenu__item");
    private final SelenideElement searchInputField = $(".Search-input");
    private final SelenideElement searchSubmitButton = $(".Search-submit");

    @Step("Открыть главную страницу")
    public MainPage openMainPage() {
        open("/");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    @Step("Нажать на кнопку 'Войти'")
    public MainPage clickOnTheEnterButton() {
        mainLoginButton.shouldBe(visible, clickable).click();
        return this;
    }

    @Step("Ввести логин и пароль")
    public MainPage setLoginAndPassword() {
        usernameField.setValue(LOGIN);
        passwordField.setValue(PASSWORD);
        return this;
    }

    @Step("Ввести некорректный логин и пароль")
    public MainPage setBaLoginAndPassword() {
        usernameField.setValue("BadLogin");
        passwordField.setValue(PASSWORD);
        return this;
    }

    @Step("Нажать на кнопку входа в Pop-up окне")
    public MainPage clickOnTheLoginButtonInThePopUpWindow() {
        popupLoginButton.shouldBe(visible, clickable).click();
        return this;
    }

    @Step("Проверить, что авторизация прошла успешно")
    public MainPage letsCheckThatWeAreAuthorized() {
        authorizedUserLabel.shouldHave(text("GoodGuy"));
        return this;
    }

    @Step("Проверить, что отображается ошибка авторизации")
    public MainPage checkThatWeAreAuthorized() {
        loginErrorInputField.shouldBe(visible);
        return this;
    }

    @Step("Нажать на иконку выбора языка в футере")
    public MainPage clickOnTheLanguageSelectionIconInTheFooter() {
        footerLanguageSelectorIcon.shouldBe(visible, clickable).scrollTo().click();
        return this;
    }

    @Step("Выбрать язык {0} в меню")
    public MainPage selectLanguageFromTheMenuThatOpens(Locale locale) {
        languageOptionsList.find(text(locale.name())).scrollTo().click();
        return this;
    }

    @Step("Проверить, что пункты меню соответствуют локали {0}")
    public MainPage checkThatMenuItemsMatchLocale(List<String> buttons) {
        headerMenuItems.shouldHave(texts(buttons));
        return this;
    }

    @Step("Ввести название сериала в поле поиска")
    public MainPage enterTheNameOfTheSeriesInTheSearchField(String value) {
        searchInputField.shouldBe(visible, enabled).setValue(value).shouldHave(value(value));
        return this;
    }

    @Step("Нажать на кнопку 'Найти'")
    public MainPage clickOnTheFindButtonNextToTheSearchBar() {
        searchSubmitButton.shouldBe(visible, clickable).click();
        return this;
    }
}