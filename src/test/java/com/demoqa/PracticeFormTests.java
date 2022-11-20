package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {
    private final String FIRST_NAME = "Egor";
    private final String LAST_NAME = "Semenov";
    private final String EMAIL = "egorsemenov666@gmail.com";
    private final String GENDER = "Male";
    private final String MOBILE = "1234567891";
    private final String BIRTHDAY = "30 January,1989";
    private final String SUBJECTS = "Maths";
    private final String HOBBIES = "Music";
    private final String PICTURES = "1.jpg";
    private final String ADDRESS = "asdasd";
    private final String STATE_AND_CITY = "NCR Delhi";


    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C://webdrivers/chromedriver.exe");
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1932x1160";
        Configuration.browserPosition = "-6x0";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('#fixedban').remove()");

        $("#firstName").val(FIRST_NAME);
        $("#lastName").val(LAST_NAME);
        $("#userEmail").val(EMAIL);
        // переделать под клик по айди элемента
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").val(MOBILE);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1989");
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").val(SUBJECTS).pressEnter();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("img/1.jpg");
        $("#currentAddress").click();
        $("#currentAddress").val(ADDRESS);
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();

        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(FIRST_NAME), text(LAST_NAME), text(EMAIL),
                text(GENDER), text(MOBILE), text(BIRTHDAY), text(SUBJECTS), text(HOBBIES),
                text(PICTURES), text(ADDRESS), text(STATE_AND_CITY));
        $("#closeLargeModal").shouldHave(appear).click();
    }
}
