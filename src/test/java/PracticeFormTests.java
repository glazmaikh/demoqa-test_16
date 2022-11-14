import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {
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
        $("#firstName").val("Alexandra");
        $("#lastName").val("Petrova");
        $("#userEmail").val("egorsemenov666@gmail.com");
        //$(".custom-radio").$(byText("Male")).click();
        $$(".custom-radio .custom-control-label").findBy(text("Female")).click();
        $("#userNumber").val("1234567891");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").selectOption("1989");
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption("January");
        $(byXpath("//div[@class='react-datepicker__day react-datepicker__day--001 react-datepicker__day--weekend']")).click();
        // todo пропадает значение subjects после выделения любого другого input
        $("#subjectsInput").val("iordan");
        $$(".custom-checkbox .custom-control-label").findBy(text("Music")).click();
//        $(".form-file #uploadPicture").click();
//        $("#uploadPicture").val("C:\\fakepath\\1.png");
        $("#currentAddress").click();
        $("#currentAddress").val("asdasd");
        $("#state").click();
        $("#react-select-3-option-1").click();
        $("#city").click();
        $("#react-select-4-option-0").click();
        $("#submit").click();



//        $(".react-datepicker__month-select").selectOption("January");
//        $(".react-datepicker__day--001:nth-child(2)").click();

//
//
//
//        $(".react-datepicker__week").$(".react-datepicker__day react-datepicker__day--001").click();
//        $(".react-datepicker__month .react-datepicker__week").$(".react-datepicker__day react-datepicker__day--001").click();
//        $(".react-datepicker__day--001:nth-child(2)").click();



        //$(".custom-radio:nth-child(1) > .custom-control-label").click();
//        $("#gender-radio-1").click();
//        //$("#output").shouldBe(visible);
//        $(by("data-testid", "email")).setValue("1");
//        $("[data-testid=email]").setValue("1");
//
//        $("[id=email]").setValue("1");
//        $("#email").setValue("1");
//        $x("//*[@id=email]").setValue("1");
//
//        $("[name=email]").setValue("1");
//        $(byName("email")).setValue("1");
//
//        $("[class=login_form_input_box]").setValue("1");
//        $(".login_form_input_box").setValue("1");
//
//        $(".login_form_input_box .inputtext").setValue("1");
//        $(".login_form_input_box").$(".inputtext").setValue("1");
//
//        $("//div[@text='Hello qa.guru']");
//        $(byText("Hello qa.guru"));
    }
}
