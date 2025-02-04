package ui.pages.careers;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class JobOverviewPage {

    private final List<String> jobOverviewElements = List.of("Description", "Requirements", "Benefits");

    private final SelenideElement careerPageContent = $("[data-ui=careers-page-content]"),
                                  jobTitle = $("[data-ui=job-title]"),
                                  applicationFormTab = $("[data-ui=application-form-tab]");

    @Step("Verify job overview contains required elements")
    public JobOverviewPage verifyJobOverviewElements() {
        jobOverviewElements.forEach(element ->
                careerPageContent.shouldHave(text(element)));

        return this;
    }

    @Step("Opened job title should be the same as in search results")
    public void checkJobTitleHaveSameTitle(String jobName) {
        jobTitle.shouldHave(text(jobName));
    }

    @Step("Move to job application form")
    public JobOverviewPage openApplicationForm() {
        applicationFormTab.click();

        return this;
    }

}
