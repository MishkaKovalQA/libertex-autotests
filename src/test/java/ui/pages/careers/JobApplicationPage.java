package ui.pages.careers;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selenide.$;

public class JobApplicationPage {

    private final SelenideElement clearPersonalInformationButton = $("[data-ui=clear-section-0]");
    private final SelenideElement firstNameInput = $("#firstname");
    private final SelenideElement lastNameInput = $("#lastname");
    private final SelenideElement emailInput = $("#email");
    private final SelenideElement phoneInput = $("[name=phone]");
    private final SelenideElement avatarUpload = $("[data-ui=avatar]");
    private final SelenideElement uploadAvatarButton = $("[data-ui=crop-image]");
    private final SelenideElement uploadedImagePreview = $("[data-role=preview] img");

    @Step("Check that clear button is disabled")
    public JobApplicationPage checkClearButtonDisabled() {
        clearPersonalInformationButton.shouldHave(attribute("aria-disabled", "true"));

        return this;
    }

    @Step("Fill out application form")
    public JobApplicationPage fillPersonalInformationForm() {
        firstNameInput.setValue("abc");
        lastNameInput.setValue("def");
        emailInput.setValue("def@gmail.com");
        phoneInput.setValue("9538024156");

        var avatar = "kitten.jpg";
        uploadAvatar(avatar);
        verifyUploadedFile(avatar);

        return this;
    }

    @Step("Upload avatar file: {filePath}")
    public JobApplicationPage uploadAvatar(String filePath) {
        avatarUpload.uploadFromClasspath(filePath);
        uploadAvatarButton.click();

        return this;
    }

    @Step("Verify uploaded file is visible: {fileName}")
    public JobApplicationPage verifyUploadedFile(String fileName) {
        uploadedImagePreview.shouldBe(visible)
                .shouldHave(attribute("alt", fileName));

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
