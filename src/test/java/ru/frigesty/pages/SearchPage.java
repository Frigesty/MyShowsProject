package ru.frigesty.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class SearchPage {

    private final SelenideElement activeTab = $(".SlidingTabs__tab.active");
    private final SelenideElement tvSeriesResultsTable = $(".ShowsTable");
    private final SelenideElement moviesResultsTable = $(".MoviesTable");
    private final SelenideElement moviesTab = $("[class='SlidingTabs__tab']");
    private final SelenideElement noResultsMessage = $(".SlidingTabs__showscontent");

    @Step("Проверить, что активна вкладка 'Сериалы'")
    public SearchPage checkThatTheTvSeriesTabIsActiveOnTheSearchPage() {
        activeTab.shouldHave(text("Сериалы"));
        return this;
    }

    @Step("Проверить, что активна вкладка 'Фильмы'")
    public SearchPage checkThatTheTabWithMoviesOnTheSearchPageIsActive() {
        activeTab.shouldHave(text("Фильмы"));
        return this;
    }

    @Step("Проверить, что в результатах поиска сериалов есть значение {0}")
    public SearchPage checkThatTheSearchResultsForTvSeriesContainTheRequiredValue(String value) {
        tvSeriesResultsTable.shouldHave(text(value));
        return this;
    }

    @Step("Проверить, что в результатах поиска фильмов есть значение {0}")
    public SearchPage checkThatTheSearchResultsForMoviesContainTheRequiredValue(String value) {
        moviesResultsTable.shouldHave(text(value));
        return this;
    }

    @Step("Переключиться на вкладку 'Фильмы'")
    public SearchPage switchToTheMoviesTab() {
        moviesTab.click();
        return this;
    }

    @Step("Проверить, что ничего не найдено")
    public SearchPage checkThatNothingWasFound() {
        noResultsMessage.shouldHave(text("Ничего не нашлось :-(\n" +
                "Попробуйте поискать по оригинальному (англ.) названию."));
        return this;
    }
}