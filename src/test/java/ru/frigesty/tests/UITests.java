package ru.frigesty.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.frigesty.data.Locale;
import ru.frigesty.helpers.WithLogin;
import ru.frigesty.pages.MainPage;
import ru.frigesty.pages.ProfilePage;

import java.util.List;
import java.util.stream.Stream;

@Tag("simple")
@Story("Интерфейс")
@Feature("Проверка отображения элементов интерфейса")
@DisplayName("Тесты на проверку интерфейса")
public class UITests extends TestBase {

    MainPage mainPage = new MainPage();
    ProfilePage profilePage = new ProfilePage();

    static Stream<Arguments> siteShouldContainAllButtonsAfterLanguageSelectionTest() {
        return Stream.of(
                Arguments.of(Locale.UA, List.of("Серіали", "Фільми", "Новини", "Добірки", "Рейтинги")),
                Arguments.of(Locale.RU, List.of("Сериалы", "Фильмы", "Новости", "Подборки", "Рейтинги")),
                Arguments.of(Locale.EN, List.of("Shows", "Movies", "News", "Collections", "Ratings"))
        );
    }

    @Tag("UI")
    @DisplayName("Тест на проверку отображения всех кнопок на сайте после выбора языка")
    @Owner("Frigesty")
    @Severity(SeverityLevel.NORMAL)
    @MethodSource
    @ParameterizedTest(name = "После выбора локали {0} должны отображаться кнопки {1}")
    void siteShouldContainAllButtonsAfterLanguageSelectionTest(Locale locale, List<String> buttons) {
        mainPage.openMainPage();
        mainPage.clickOnTheLanguageSelectionIconInTheFooter();
        mainPage.selectLanguageFromTheMenuThatOpens(locale);
        mainPage.checkThatMenuItemsMatchLocale(buttons);
    }

    @Tag("UI")
    @DisplayName("Тест на проверку того что в профиле есть сериал")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Frigesty")
    @WithLogin
    @Test
    void checkThatByGoingToTheProfileThereWillBeASeriesTest() {
        profilePage.openProfilePage();
        profilePage.checkThatTheSeriesIsInTheBlockWithSeries();
    }
}