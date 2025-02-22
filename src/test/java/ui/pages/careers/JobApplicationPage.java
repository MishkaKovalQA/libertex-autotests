package ui.pages.careers;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ui.models.JobApplicationFormDataModel;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selenide.$;

public class JobApplicationPage {

    private final SelenideElement clearPersonalInformationButton = $("[data-ui=clear-section-0]"),
                                  firstNameInput = $("#firstname"),
                                  lastNameInput = $("#lastname"),
                                  emailInput = $("#email"),
                                  phoneInput = $("[name=phone]"),
                                  resumeUpload = $("[data-ui=resume]"),
                                  uploadedResumePreview = $("[data-id=filename]");

    @Step("Check that clear button is disabled")
    public JobApplicationPage checkClearButtonDisabled() {
        clearPersonalInformationButton.shouldHave(attribute("aria-disabled", "true"));

        return this;
    }

    @Step("Fill out application form")
    public JobApplicationPage fillPersonalInformationForm() {
        var formData = JobApplicationFormDataModel.builder().build();

        firstNameInput.setValue(formData.getFirstName());
        lastNameInput.setValue(formData.getLastName());
        emailInput.setValue(formData.getEmail());
        phoneInput.setValue(formData.getPhone());

        var resume = formData.getResumeFileName();
        uploadResume(resume);
        verifyUploadedFile(resume);

        return this;
    }

    @Step("Upload resume file: {filePath}")
    public JobApplicationPage uploadResume(String filePath) {
        resumeUpload.uploadFromClasspath(filePath);

        return this;
    }

    @Step("Verify uploaded file is visible: {fileName}")
    public JobApplicationPage verifyUploadedFile(String fileName) {
        uploadedResumePreview.shouldBe(visible).shouldHave(text(fileName));

        return this;
    }

    @Step("Check that clear button is enabled")
    public JobApplicationPage checkClearButtonEnabled() {
        clearPersonalInformationButton.shouldNotHave(attribute("aria-disabled", "true"));

        return this;
    }

    @Step("Click clear button")
    public JobApplicationPage clearFormByClearButton() {
        checkClearButtonEnabled();
        clearPersonalInformationButton.click();

        return this;
    }

    @Step("Verify form fields are cleared")
    public JobApplicationPage verifyFormCleared() {
        firstNameInput.shouldBe(empty);
        lastNameInput.shouldBe(empty);
        emailInput.shouldBe(empty);
        phoneInput.shouldBe(empty);

        return this;
    }
}
