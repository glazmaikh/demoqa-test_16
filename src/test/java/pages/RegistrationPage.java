package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultModalComponent;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static tests.TestBase.randomElement;

public class RegistrationPage {
    private final String TITLE_TEXT = "Student Registration Form";
    private final CalendarComponent calendarComponent = new CalendarComponent();
    private final ResultModalComponent resultModalComponent = new ResultModalComponent();
    private SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderInput = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesInput = $("#hobbiesWrapper"),
            uploadPictureInput = $("#uploadPicture"),
            currentAdressInput = $("#currentAddress"),
            stateInput = $("#state"),
            cityInput = $("#city"),
            submitButton = $("#submit");

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(TITLE_TEXT));
        executeJavaScript("$('#fixedban').remove()");
        return this;
    }

    public RegistrationPage setFirstNameInput(String firstName) {
        firstNameInput.val(firstName);
        return this;
    }

    public RegistrationPage setLastNameInput(String lastName) {
        lastNameInput.val(lastName);
        return this;
    }

    public RegistrationPage setUserEmailInput(String userEmail) {
        userEmailInput.val(userEmail);
        return this;
    }

    public RegistrationPage setGenderInput(String gender) {
        genderInput.$(byText(gender)).click();
        return this;
    }

    public RegistrationPage setUserNumberInput(String userNumber) {
        userNumberInput.val(userNumber).click();
        return this;
    }

    public RegistrationPage setBirthDate(String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationPage setSubjectsInput(String subjects) {
        subjectsInput.val(subjects).pressEnter();
        return this;
    }

    public RegistrationPage setHobbiesInput(String hobbies) {
        hobbiesInput.$(byText(hobbies)).click();
        return this;
    }

    public RegistrationPage setUploadPicture(File file) {
        uploadPictureInput.uploadFile(file);
        return this;
    }

    public RegistrationPage setCurrentAdress(String adress) {
        currentAdressInput.click();
        currentAdressInput.val(adress);
        return this;
    }

    public RegistrationPage setStateAndCity(String state, String city) {
        stateInput.click();
        $("#stateCity-wrapper").$(byText(state)).click();
        cityInput.click();
        city = randomElement();
        $("#stateCity-wrapper").$(byText(city)).click();
        return this;
    }

    public RegistrationPage getSubmit() {
        $("#submit").click();
        return this;
    }

    public RegistrationPage verifyResultModalAppears() {
        resultModalComponent.verifyModalAppears();
        return this;
    }

    public RegistrationPage verifyResult(String key, String value) {
        resultModalComponent.verifyResult(key, value);
        return this;
    }
}
