package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.RegistrationPage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {
    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C://webdrivers/chromedriver.exe");
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1932x1160";
        Configuration.browserPosition = "-6x0";
        Configuration.holdBrowserOpen = true;
    }

    RegistrationPage registrationPage = new RegistrationPage();
    Faker deFaker = new Faker(new Locale("de"));
    String[] gendersArray = {"Male", "Female", "Other"};
    String[] subjectsArray = {"Arts", "Maths", "English", "History"};
    String[] hobbiesArray = {"Sports", "Reading", "Music"};
    String[] stateArray = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
    static List<SelenideElement> cityList = $$x("//div[contains(@id, 'react-select-4')]");
    String firstName,
            lastName,
            email,
            gender,
            mobile,
            day,
            month,
            year,
            subjects,
            hobbies,
            pictureName,
            address,
            state;
    static String city;
    File picture;

    @BeforeEach
    void beforeEach() throws IOException {
        firstName = deFaker.name().firstName();
        lastName = deFaker.name().lastName();
        email = deFaker.internet().emailAddress();
        gender = randomListItem(gendersArray);
        mobile = deFaker.phoneNumber().subscriberNumber(10);
        year = getYear();
        month = getMonth();
        day = String.valueOf(deFaker.number().numberBetween(1, 31));
        state = randomListItem(stateArray);
        city = null;
        subjects = randomListItem(subjectsArray);
        hobbies = randomListItem(hobbiesArray);
        picture = getPicture();
        address = deFaker.address().streetAddress();
    }

    String getYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return year = sdf.format(deFaker.date().birthday());
    }

    String getMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
        return month = sdf.format(deFaker.date().birthday());
    }

    File getPicture() throws IOException {
        pictureName = deFaker.file().fileName("", null, null, null)
                .substring(1);
        StringBuilder sb = new StringBuilder();
        sb.append("src/test/resources/img/")
                .append(pictureName);

        Path newFilePath = Paths.get(sb.toString());
        Files.createFile(newFilePath);
        return new File(sb.toString());
    }

    static int randomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    static String randomListItem(String[] values) {
        int index = randomInt(0, values.length);
        return values[index];
    }

    public static String randomElement() {
        if (city == null) {
            city = cityList.get(new Random().nextInt(cityList.size())).getText();
        }
        return city;
    }

    @AfterEach
    public void cleanUpFiles() {
        picture.delete();
    }
}
