package ui.models;

import com.github.javafaker.Faker;
import lombok.Builder;
import lombok.Data;

import java.util.Locale;

@Data
@Builder
public class JobApplicationFormDataModel {

    private static Faker faker() {
        return Faker.instance(Locale.ENGLISH);
    }

    @Builder.Default
    private String firstName = faker().name().firstName();

    @Builder.Default
    private String lastName = faker().name().lastName();

    @Builder.Default
    private String email = faker().internet().emailAddress();

    @Builder.Default
    private String phone = faker().phoneNumber().subscriberNumber(10);

    @Builder.Default
    private String resumeFileName = "test_resume.pdf";
}
