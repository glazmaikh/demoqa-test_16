package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.RegistrationPage;

import java.io.IOException;

import static tests.TestData.pictureName;

public class PracticeFormWithPageObjectsTests extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    TestData testData = new TestData();

    public PracticeFormWithPageObjectsTests() throws IOException {
    }

    @Test
    void fillFormTest() {
        registrationPage.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setEmail(testData.email)
                .setGender(testData.gender)
                .setUserNumber(testData.number)
                .setDateOfBirth(testData.day, testData.month, testData.year)
                .setSubjects(testData.subject)
                .setHobby(testData.hobby)
                .setPicture(testData.picture)
                .setCurrentAddress(testData.address)
                .setStateAndCity(testData.state, testData.city)
                .clickSubmitButton();

        registrationPage.verifyResultModalAppears()
                .verifyModal("Student Name", testData.firstName + " " + testData.lastName)
                .verifyModal("Student Email", testData.email)
                .verifyModal("Gender", testData.gender)
                .verifyModal("Mobile", testData.number)
                .verifyModal("Date of Birth", (testData.day + " " + testData.month + "," + testData.year))
                .verifyModal("Subjects", testData.subject)
                .verifyModal("Hobbies", testData.hobby)
                .verifyModal("Picture", pictureName)
                .verifyModal("Address", testData.address)
                .verifyModal("State and City", testData.state + " " + testData.city);
    }

    @ValueSource(strings = {"Arts", "Maths", "English", "History"})
    @ParameterizedTest(name = "check valid subjects data adding")
    void subjectsInputTest(String arg) {
        registrationPage.openPage()
                .setOneSubject(arg)
                .shouldHaveValue(arg);
    }

    @CsvFileSource(
            resources = "/example1.csv"
    )
    @ParameterizedTest(name = "check if selected state {0} able and selected city {1}")
    void stateAndCityTestCsvFileSource(String state, String city) {
        registrationPage.openPage()
                .setStateAndCity(state, city);
    }

    @AfterEach
    void cleanUpFiles() {
        testData.picture.delete();
    }


}
