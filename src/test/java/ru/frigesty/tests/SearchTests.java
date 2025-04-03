package ru.frigesty.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.frigesty.pages.MainPage;
import ru.frigesty.pages.SearchPage;

@Tag("simple")
@Story("Поиск")
@Feature("Работа поиска")
@DisplayName("Тесты на проверку поиска")
public class SearchTests extends TestBase {

    MainPage mainPage = new MainPage();
    SearchPage searchPage = new SearchPage();

    @DisplayName("Тест на поиск сериалов по названию")
    @Owner("Frigesty")
    @Tag("search")
    @Severity(SeverityLevel.CRITICAL)
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
    @Owner("Frigesty")
    @Tag("search")
    @Severity(SeverityLevel.CRITICAL)
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
    @Owner("Frigesty")
    @Tag("search")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void searchForASeriesThatIsNotOnTheSiteTest() {
        mainPage.openMainPage();
        mainPage.enterTheNameOfTheSeriesInTheSearchField("Повелители бездны");
        mainPage.clickOnTheFindButtonNextToTheSearchBar();
        searchPage.checkThatTheTvSeriesTabIsActiveOnTheSearchPage();
        searchPage.checkThatNothingWasFound();
    }
}