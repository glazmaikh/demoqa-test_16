package tests;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PracticeFormWithPageObjectsTests extends RegistrationFormData {
    public PracticeFormWithPageObjectsTests() throws IOException {
    }

    @Test
    void fillFormTest() {
        registrationPage.openPage()
                .setFirstNameInput(firstName)
                .setLastNameInput(lastName)
                .setUserEmailInput(email)
                .setGenderInput(gender)
                .setUserNumberInput(mobile)
                .setBirthDate(day, month, year)
                .setSubjectsInput(subjects)
                .setHobbiesInput(hobbies)
                .setUploadPicture(picture)
                .setCurrentAdress(address)
                .setStateAndCity(state, city)
                .clickSubmitButton();

        registrationPage.verifyResultModalAppears()
                .verifyResult("Student Name", firstName + " " + lastName)
                .verifyResult("Student Email", email)
                .verifyResult("Gender", gender)
                .verifyResult("Mobile", mobile)
                .verifyResult("Date of Birth", day + " " + month + "," + year)
                .verifyResult("Subjects", subjects)
                .verifyResult("Hobbies", hobbies)
                .verifyResult("Picture", pictureName)
                .verifyResult("Address", address)
                .verifyResult("State and City", state + " " + city);
    }
}
