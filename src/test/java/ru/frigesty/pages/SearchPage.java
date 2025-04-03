package ru.frigesty.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class SearchPage {

    @Step("Проверяем что активна вкладка с сериалами на странице поиска")
    public SearchPage checkThatTheTvSeriesTabIsActiveOnTheSearchPage() {
        $(".SlidingTabs__tab.active").shouldHave(text("Сериалы"));
        return this;
    }

    @Step("Проверяем что активна вкладка с фильмами на странице поиска")
    public SearchPage checkThatTheTabWithMoviesOnTheSearchPageIsActive() {
        $(".SlidingTabs__tab.active").shouldHave(text("Фильмы"));
        return this;
    }

    @Step("Проверяем что в результатах поиска сериалов есть искомое значение")
    public SearchPage checkThatTheSearchResultsForTvSeriesContainTheRequiredValue(String value) {
        $(".ShowsTable").shouldHave(text(value));
        return this;
    }

    @Step("Проверяем что в результатах поиска фильмов есть искомое значение")
    public SearchPage checkThatTheSearchResultsForMoviesContainTheRequiredValue(String value) {
        $(".MoviesTable").shouldHave(text(value));
        return this;
    }

    @Step("Переключаемся на вкладку фильмы")
    public SearchPage switchToTheMoviesTab() {
        $("[class='SlidingTabs__tab']").click();
        return this;
    }

    @Step("Проверяем что нечего не нашлось")
    public SearchPage checkThatNothingWasFound() {
        $(".SlidingTabs__showscontent").shouldHave(text("Ничего не нашлось :-(\n" +
                "Попробуйте поискать по оригинальному (англ.) названию."));
        return this;
    }
}