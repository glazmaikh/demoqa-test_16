package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.io.IOException;

public class PracticeFormWithPageObjectsTests extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    RegistrationFormData formData = new RegistrationFormData();

    public PracticeFormWithPageObjectsTests() throws IOException {
    }

    @Test
    void fillFormTest() {
        registrationPage.openPage()
                .setFirstNameInput(formData.firstName)
                .setLastNameInput(formData.lastName)
                .setUserEmailInput(formData.email)
                .setGenderInput(formData.gender)
                .setUserNumberInput(formData.mobile)
                .setBirthDate(formData.day, formData.month, formData.year)
                .setSubjectsInput(formData.subjects)
                .setHobbiesInput(formData.hobbies)
                .setUploadPicture(formData.picture)
                .setCurrentAdress(formData.address)
                .setStateAndCity(formData.state, RegistrationFormData.city)
                .clickSubmitButton();

        registrationPage.verifyResultModalAppears()
                .verifyResult("Student Name", formData.firstName + " " + formData.lastName)
                .verifyResult("Student Email", formData.email)
                .verifyResult("Gender", formData.gender)
                .verifyResult("Mobile", formData.mobile)
                .verifyResult("Date of Birth", formData.day + " " + formData.month + "," + formData.year)
                .verifyResult("Subjects", formData.subjects)
                .verifyResult("Hobbies", formData.hobbies)
                .verifyResult("Picture", formData.pictureName)
                .verifyResult("Address", formData.address)
                .verifyResult("State and City", formData.state + " " + RegistrationFormData.city);
    }

    @AfterEach
    void cleanUpFiles() {
        formData.picture.delete();
    }
}
