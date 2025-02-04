package ui.pages.careers;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import ui.pages.Page;

import java.util.List;
import java.util.NoSuchElementException;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selenide.*;

@Slf4j
public class CareersPage extends Page {

    private final SelenideElement careerPageContent = $("[data-ui=careers-page-content]");
    private final SelenideElement searchJobInput = $("input[data-ui=search-jobs]");
    private final ElementsCollection jobResults = $$("li[data-ui=job-opening]");
    private final ElementsCollection jobName = $$("[data-id=job-item]");
    private final SelenideElement jobLocation = $("[data-id=job-location]");
    private final SelenideElement clearFiltersButton = $("[data-ui=clear-filters]");
    private final SelenideElement locationFilterInput = $("#locations-filter_input");
    private final SelenideElement locationFilterListbox = $("#locations-filter_listbox");

    private final SelenideElement jobTitle = $("[data-ui=job-title]");
    private final List<String> jobOverviewElements = List.of("Description", "Requirements", "Benefits");

    private final SelenideElement applicationFormTab = $("[data-ui=application-form-tab]");
    private final SelenideElement clearButton = $("[data-ui=clear-section-0]");
    private final SelenideElement firstName = $("#firstname");
    private final SelenideElement lastName = $("#lastname");
    private final SelenideElement email = $("#email");
    private final SelenideElement phone = $("[name=phone]");
    private final SelenideElement avatarUpload = $("[data-ui=avatar]");
    private final SelenideElement cropImageButton = $("[data-ui=crop-image]");
    private final SelenideElement uploadedImagePreview = $("[data-role=preview] img");


    @Step("Open Careers page")
    public CareersPage openCareersPage() {
        open("https://apply.workable.com/libertexgroup/");

        return this;
    }

    @Step("Get name from first job")
    public String getNameFromFirstJob() {
        return jobName.first().getOwnText();
    }

    @Step("Opened job title should be the same as in search results")
    public void checkJobTitleHaveSameTitle(String jobName) {
        jobTitle.shouldHave(text(jobName));
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
        checkClearButtonEnabled();
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

    @Step("Verify job overview contains required elements")
    public CareersPage verifyJobOverviewElements() {
        jobOverviewElements.forEach(element ->
                careerPageContent.shouldHave(text(element)));

        return this;
    }

    @Step("Open job application form")
    public CareersPage openApplicationForm() {
        applicationFormTab.click();

        return this;
    }

    @Step("Check that clear button is disabled")
    public CareersPage checkClearButtonDisabled() {
        clearButton.shouldHave(attribute("aria-disabled", "true"));

        return this;
    }

    @Step("Fill out application form")
    public CareersPage fillPersonalInformationForm() {
        firstName.setValue("abc");
        lastName.setValue("def");
        email.setValue("def@gmail.com");
        phone.setValue("9538024156");

        var avatar = "kitten.jpg";
        uploadAvatar(avatar);
        verifyUploadedFile(avatar);

        return this;
    }

    @Step("Upload avatar file: {filePath}")
    public CareersPage uploadAvatar(String filePath) {
        avatarUpload.uploadFromClasspath(filePath);
        cropImageButton.click();

        return this;
    }

    @Step("Verify uploaded file is visible: {fileName}")
    public CareersPage verifyUploadedFile(String fileName) {
        uploadedImagePreview.shouldBe(visible)
                .shouldHave(attribute("alt", fileName));

        return this;
    }

    @Step("Check that clear button is enabled")
    public CareersPage checkClearButtonEnabled() {
        clearButton.shouldNotHave(attribute("aria-disabled", "true"));

        return this;
    }

    @Step("Click clear button")
    public CareersPage clearFormByClearButton() {
        clearButton.click();

        return this;
    }

    @Step("Verify form fields are cleared")
    public CareersPage verifyFormCleared() {
        firstName.shouldBe(empty);
        lastName.shouldBe(empty);
        email.shouldBe(empty);
        phone.shouldBe(empty);

        return this;
    }
}
