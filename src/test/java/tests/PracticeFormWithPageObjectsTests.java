package tests;

import org.junit.jupiter.api.Test;

public class PracticeFormWithPageObjectsTests extends TestBase {
    private final String NAME = "Egor Semenov";
    private final String EMAIL = "egorsemenov666@gmail.com";
    private final String GENDER = "Male";
    private final String MOBILE = "1234567891";
    private final String BIRTHDAY = "30 January,1989";
    private final String SUBJECTS = "Maths";
    private final String HOBBIES = "Music";
    private final String PICTURES = "1.jpg";
    private final String ADDRESS = "asdasd";
    private final String STATE_AND_CITY = "NCR Delhi";

    @Test
    void fillFormTest() {
        registrationPage.openPage()
                .setFirstNameInput("Egor")
                .setLastNameInput("Semenov")
                .setUserEmailInput(EMAIL)
                .setGenderInput(GENDER)
                .setUserNumberInput(MOBILE)
                .setBirthDate("30", "January", "1989")
                .setSubjectsInput(SUBJECTS)
                .setHobbiesInput(HOBBIES)
                .setUploadPicture("img/1.jpg")
                .setCurrentAdress(ADDRESS)
                .setStateAndCity("NCR", "Delhi")
                .getSubmit();

        registrationPage.verifyResultModalAppears()
                .verifyResult("Student Name", NAME)
                .verifyResult("Student Email", EMAIL)
                .verifyResult("Gender", GENDER)
                .verifyResult("Mobile", MOBILE)
                .verifyResult("Date of Birth", BIRTHDAY)
                .verifyResult("Subjects", SUBJECTS)
                .verifyResult("Hobbies", HOBBIES)
                .verifyResult("Picture", PICTURES)
                .verifyResult("Address", ADDRESS)
                .verifyResult("State and City", STATE_AND_CITY);
    }
}
