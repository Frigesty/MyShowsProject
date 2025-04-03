package ru.frigesty.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.frigesty.data.Locale;
import ru.frigesty.pages.MainPage;
import ru.frigesty.pages.SearchPage;

import java.util.List;
import java.util.stream.Stream;

public class WebTests extends TestBase {

    MainPage mainPage = new MainPage();
    SearchPage searchPage = new SearchPage();

    @Tag("UI")
    @DisplayName("Тест на успешную UI авторизацию")
    @Test
    void ForSuccessfulUiAuthorizationTest() {
        mainPage.openMainPage();
        mainPage.clickOnTheEnterButton();
        mainPage.setLoginAndPassword();
        mainPage.clickOnTheLoginButtonInThePopUpWindow();
        mainPage.letsCheckThatWeAreAuthorized();
    }

    @Tag("UI")
    @DisplayName("Тест на провальную UI авторизацию")
    @Test
    void forFailedUiAuthorizationTest() {
        mainPage.openMainPage();
        mainPage.clickOnTheEnterButton();
        mainPage.setBaLoginAndPassword();
        mainPage.clickOnTheLoginButtonInThePopUpWindow();
        mainPage.checkThatWeAreAuthorized();
    }

    static Stream<Arguments> siteShouldContainAllButtonsAfterLanguageSelectionTest() {
        return Stream.of(
                Arguments.of(Locale.UA, List.of("Серіали", "Фільми", "Новини", "Добірки", "Рейтинги")),
                Arguments.of(Locale.RU, List.of("Сериалы", "Фильмы", "Новости", "Подборки", "Рейтинги")),
                Arguments.of(Locale.EN, List.of("Shows", "Movies", "News", "Collections", "Ratings"))
        );
    }

    @Tag("UI")
    @DisplayName("Тест на проверку отображения всех кнопок на сайте после выбора языка")
    @MethodSource
    @ParameterizedTest(name = "После выбора локали {0} должны отображаться кнопки {1}")
    void siteShouldContainAllButtonsAfterLanguageSelectionTest(Locale locale, List<String> buttons) {
        mainPage.openMainPage();
        mainPage.clickOnTheLanguageSelectionIconInTheFooter();
        mainPage.selectLanguageFromTheMenuThatOpens(locale);
        mainPage.checkThatMenuItemsMatchLocale(buttons);
    }

    @DisplayName("Тест на поиск сериалов по названию")
    @ParameterizedTest
    @ValueSource(strings = {"Во все тяжкие", "Монолог фармацевта", "Охотник х Охотник"})
    void searchSeriesByTitleTest(String value) {
        mainPage.openMainPage();
        mainPage.enterTheNameOfTheSeriesInTheSearchField(value);
        mainPage.clickOnTheFindButtonNextToTheSearchBar();
        searchPage.checkThatTheTvSeriesTabIsActiveOnTheSearchPage();
        searchPage.checkThatTheSearchResultsForTvSeriesContainTheRequiredValue(value);
    }

    @DisplayName("Тест на поиск фильмов по названию")
    @ParameterizedTest
    @ValueSource(strings = {"Грань будущего", "Бэтмен: Начало", "Письма к Джульетте"})
    void searchMoviesByTitleTest(String value) {
        mainPage.openMainPage();
        mainPage.enterTheNameOfTheSeriesInTheSearchField(value);
        mainPage.clickOnTheFindButtonNextToTheSearchBar();
        searchPage.switchToTheMoviesTab();
        searchPage.checkThatTheTabWithMoviesOnTheSearchPageIsActive();
        searchPage.checkThatTheSearchResultsForMoviesContainTheRequiredValue(value);
    }

    @DisplayName("Тест на поиск сериала которого нет на сайте")
    @Test
    void searchForASeriesThatIsNotOnTheSiteTest() {
        mainPage.openMainPage();
        mainPage.enterTheNameOfTheSeriesInTheSearchField("Повелители бездны");
        mainPage.clickOnTheFindButtonNextToTheSearchBar();
        searchPage.checkThatTheTvSeriesTabIsActiveOnTheSearchPage();
        searchPage.checkThatNothingWasFound();
    }
}