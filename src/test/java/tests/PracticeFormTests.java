package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.components.CalendarComponent;
import pages.components.ResultModalComponent;

import java.io.IOException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static tests.TestData.pictureName;

@Tag("automation-practice-form")
public class PracticeFormTests extends TestBase {
    TestData testData = new TestData();
    CalendarComponent calendarComponent = new CalendarComponent();
    ResultModalComponent resultModalComponent = new ResultModalComponent();

    public PracticeFormTests() throws IOException {
    }

    @Test
    void fillFormTest() {
        step("Open automation-practice-form", () -> {
            open("/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
            executeJavaScript("$('#fixedban').remove()");
        });
        step("Fill form", () -> {
            $("#firstName").val(testData.firstName);
            $("#lastName").val(testData.lastName);
            $("#userEmail").val(testData.email);
            $("#genterWrapper").$(byText(testData.gender)).click();
            $("#userNumber").val(testData.number);
            $("#dateOfBirthInput").click();
            calendarComponent.setDateOfBirth(testData.day,
                    testData.month,
                    testData.year);
            $("#subjectsInput").val(testData.subject).pressEnter();
            $("#hobbiesWrapper").$(byText(testData.hobby)).click();
            $("#uploadPicture").uploadFile(testData.picture);
            $("#currentAddress").val(testData.address);
            $("#state").click();
            $("#stateCity-wrapper").$(byText(testData.state)).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText(testData.city)).click();
            $("#submit").click();

        });
        step("Check form results", () -> {
            $(".modal-dialog").should(appear);
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

            resultModalComponent.verifyResult("Student Name", testData.firstName + " " + testData.lastName)
                    .verifyResult("Student Email", testData.email)
                    .verifyResult("Gender", testData.gender)
                    .verifyResult("Mobile", testData.number)
                    .verifyResult("Date of Birth", (testData.day + " " + testData.month + "," + testData.year))
                    .verifyResult("Subjects", testData.subject)
                    .verifyResult("Hobbies", testData.hobby)
                    .verifyResult("Picture", pictureName)
                    .verifyResult("Address", testData.address)
                    .verifyResult("State and City", testData.state + " " + testData.city);
            $("#closeLargeModal").shouldHave(appear).click();
        });


    }
}