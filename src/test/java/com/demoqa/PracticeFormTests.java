package com.demoqa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {
    private String path = "src/test/resources/1.jpg";
    private final String FIRST_NAME = "Egor";
    private final String LAST_NAME = "Semenov";
    private final String EMAIL = "egorsemenov666@gmail.com";
    private final String GENDER = "Male";
    private final String MOBILE = "1234567891";
    private final String BIRTHDAY = "01 January,1989";
    private final String SUBJECTS = "Maths";
    private final String HOBBIES = "Music";
    private final String PICTURES = "1.jpg";
    private final String ADDRESS = "asdasd";
    private final String STATE_AND_CITY = "Uttar Pradesh Agra";


    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C://webdrivers/chromedriver.exe");
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1932x1160";
        Configuration.browserPosition = "-6x0";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        $("#firstName").val("Egor");
        $("#lastName").val("Semenov");
        $("#userEmail").val("egorsemenov666@gmail.com");
        $$(".custom-radio .custom-control-label").find(text("Male")).click();
        $("#userNumber").val("1234567891");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").selectOption("1989");
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption("January");
        $(byXpath("//div[@class='react-datepicker__day react-datepicker__day--001 react-datepicker__day--weekend']")).click();
        $("#subjectsInput").val("Maths").pressEnter();
        $$(".custom-checkbox .custom-control-label").find(text("Music")).click();
        $("#uploadPicture").uploadFile(new File(path));
        $("#currentAddress").click();
        $("#currentAddress").val("asdasd");
        $("#state").click();
        $("#react-select-3-option-1").click();
        $("#city").click();
        $("#react-select-4-option-0").click();
        $("#submit").click();

        $(".table-responsive").shouldBe(visible);
        $(".table-responsive").shouldHave(text(FIRST_NAME));
        $(".table-responsive").shouldHave(text(LAST_NAME));
        $(".table-responsive").shouldHave(text(EMAIL));
        $(".table-responsive").shouldHave(text(GENDER));
        $(".table-responsive").shouldHave(text(MOBILE));
        $(".table-responsive").shouldHave(text(BIRTHDAY));
        $(".table-responsive").shouldHave(text(SUBJECTS));
        $(".table-responsive").shouldHave(text(HOBBIES));
        $(".table-responsive").shouldHave(text(PICTURES));
        $(".table-responsive").shouldHave(text(ADDRESS));
        $(".table-responsive").shouldHave(text(STATE_AND_CITY));
        $("#closeLargeModal").shouldHave(enabled).click();
    }
}
