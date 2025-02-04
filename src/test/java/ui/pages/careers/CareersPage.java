package ui.pages.careers;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import ui.components.CookieConsentComponent;

import java.util.List;
import java.util.NoSuchElementException;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Slf4j
public class CareersPage {

    private final SelenideElement searchJobInput = $("input[data-ui=search-jobs]");
    private final ElementsCollection jobResults = $$("li[data-ui=job-opening]");
    private final ElementsCollection jobName = $$("[data-id=job-item]");
    private final SelenideElement clearFiltersButton = $("[data-ui=clear-filters]");
    private final SelenideElement locationFilterInput = $("#locations-filter_input");
    private final SelenideElement locationFilterListbox = $("#locations-filter_listbox");

    @Step("Open Careers page")
    public CareersPage openCareersPage() {
        open("https://apply.workable.com/libertexgroup/");

        return this;
    }

    @Step("Get name from first job")
    public String getNameFromFirstJob() {
        return jobName.first().getOwnText();
    }

    @Step("Get current job count for the first time when career page is loaded")
    public int getStartJobCount() {
        jobResults.shouldBe(sizeGreaterThan(0));

        return getJobCount();
    }

    @Step("Get current job count")
    public int getJobCount() {

        return jobResults.size();
    }

    @Step("Apply location filter: {location}")
    public CareersPage applyLocationFilter(String location) {
        locationFilterInput.click();
        locationFilterListbox.$("[value='" + location + "']").click();

        return this;
    }

    @Step("Open first job listing")
    public CareersPage openFirstJob() {
        jobResults.first().click();

        return this;
    }

    @Step("Wait for job results to change")
    public CareersPage checkJobResultsCountShouldBeLessThan(int previousCount) {
        jobResults.shouldBe(sizeLessThan(previousCount));

        return this;
    }

    @Step("Clear filters")
    public CareersPage clearFilters() {
        clearFiltersButton.shouldBe(visible).click();
        verifyClearFiltersButtonNotVisible();

        return this;
    }

    @Step("Verify 'Clear Filters' button is not visible")
    public CareersPage verifyClearFiltersButtonNotVisible() {
        clearFiltersButton.shouldNotBe(visible);

        return this;
    }

    @Step("Verify job count is {expectedCount}")
    public void verifyJobCountIs(int expectedCount) {
        jobResults.shouldHave(size(expectedCount));
    }

    @Step("Search for any job from list to fetch non-empty results")
    public CareersPage searchAnyJobWithNonEmptyResults() {
        var jobKeywords = List.of("QA", "Analyst", "Advisor", "Manager", "Engineer");

        for (var job : jobKeywords) {
            log.info("Job: {}", job);
            searchJobInput.setValue(job).pressEnter();

            try {
                jobResults.first().should(appear);
                return this;
            } catch (UIAssertionError e) {
                searchJobInput.clear();
            }
        }
        throw new NoSuchElementException("Not found any job!");
    }

    public void acceptCookie() {
        new CookieConsentComponent().acceptCookie();
    }
}
