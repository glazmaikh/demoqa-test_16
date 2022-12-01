package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultModalComponent;
import pages.components.StateAndCityComponent;
import pages.components.SubjectsInputComponent;
import tests.TestBase;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage extends TestBase {
    CalendarComponent calendarComponent = new CalendarComponent();
    ResultModalComponent resultModalComponent = new ResultModalComponent();
    private final SubjectsInputComponent subjectsComponent = new SubjectsInputComponent();
    private final StateAndCityComponent stateAndCityComponent = new StateAndCityComponent();
    private static final String TITLE_TEXT = "Student Registration Form";
    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement genderInput = $("#genterWrapper");
    private final SelenideElement userNumberInput = $("#userNumber");
    private final SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement hobbiesInput = $("#hobbiesWrapper");
    private final SelenideElement uploadPictureInput = $("#uploadPicture");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement stateInput = $("#state");
    private final SelenideElement cityInput = $("#city");


    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(TITLE_TEXT));
        executeJavaScript("$('#fixedban').remove()");
        return this;
    }

    public RegistrationPage setFirstName(String firstName) {
        firstNameInput.val(firstName).click();
        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        lastNameInput.val(lastName).click();
        return this;
    }

    public RegistrationPage setEmail(String userEmail) {
        userEmailInput.val(userEmail).click();
        return this;
    }

    public RegistrationPage setGender(String gender) {
        genderInput.$(byText(gender)).click();
        return this;
    }

    public RegistrationPage setUserNumber(String userNumber) {
        userNumberInput.val(userNumber).click();
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponent.setDateOfBirth(day, month, year);
        return this;
    }

    public RegistrationPage setSubjects(String subject) {
        subjectsInput.val(subject).pressEnter();
        return this;
    }

    public RegistrationPage setHobby(String hobbies) {
        hobbiesInput.$(byText(hobbies)).click();
        return this;
    }

    public RegistrationPage setPicture(File file) {
        uploadPictureInput.uploadFile(file);
        return this;
    }

    public RegistrationPage setCurrentAddress(String address) {
        currentAddressInput.val(address);
        return this;
    }

    public RegistrationPage setStateAndCity(String state, String city) {
        stateInput.click();
        $(byText(state)).click();
        cityInput.click();
        $(byText(city)).click();
        return this;
    }

    public void clickSubmitButton() {
        $("#submit").click();
    }

    public RegistrationPage verifyResultModalAppears() {
        resultModalComponent.verifyResultModalAppears();
        return this;
    }

    public RegistrationPage verifyModal(String key, String value) {
        resultModalComponent.verifyResult(key, value);
        return this;
    }

    //for parameterized tests
    public RegistrationPage setOneSubject(String value) {
        subjectsComponent.setOneSubject(value);
        return this;
    }

    public RegistrationPage shouldHaveValue(String value) {
        subjectsComponent.shouldHaveValue(value);
        return this;
    }
}
