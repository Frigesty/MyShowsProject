package ru.frigesty.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.frigesty.pages.MainPage;
import ru.frigesty.pages.SearchPage;

@Tag("search")
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
        mainPage.openMainPage()
                .enterTheNameOfTheSeriesInTheSearchField(value)
                .clickOnTheFindButtonNextToTheSearchBar();
        searchPage.checkThatTheTvSeriesTabIsActiveOnTheSearchPage()
                  .checkThatTheSearchResultsForTvSeriesContainTheRequiredValue(value);
    }

    @DisplayName("Тест на поиск фильмов по названию")
    @Owner("Frigesty")
    @Tag("search")
    @Severity(SeverityLevel.CRITICAL)
    @ParameterizedTest
    @ValueSource(strings = {"Грань будущего", "Бэтмен: Начало", "Письма к Джульетте"})
    void searchMoviesByTitleTest(String value) {
        mainPage.openMainPage()
                .enterTheNameOfTheSeriesInTheSearchField(value)
                .clickOnTheFindButtonNextToTheSearchBar();
        searchPage.switchToTheMoviesTab()
                  .checkThatTheTabWithMoviesOnTheSearchPageIsActive()
                  .checkThatTheSearchResultsForMoviesContainTheRequiredValue(value);
    }

    @DisplayName("Тест на поиск сериала которого нет на сайте")
    @Owner("Frigesty")
    @Tag("search")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void searchForASeriesThatIsNotOnTheSiteTest() {
        mainPage.openMainPage()
                .enterTheNameOfTheSeriesInTheSearchField("Повелители бездны")
                .clickOnTheFindButtonNextToTheSearchBar();
        searchPage.checkThatTheTvSeriesTabIsActiveOnTheSearchPage()
                  .checkThatNothingWasFound();
    }
}