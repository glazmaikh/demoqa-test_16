package tests;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static com.codeborne.selenide.Selenide.$$x;

public class RegistrationFormData {
    static Random random = new Random();
    Faker deFaker = new Faker(new Locale("de"));
    String[] gendersArray = {"Male", "Female", "Other"};
    String[] subjectsArray = {"Arts", "Maths", "English", "History"};
    String[] hobbiesArray = {"Sports", "Reading", "Music"};
    String[] stateArray = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
    static List<SelenideElement> cityList = $$x("//div[contains(@id, 'react-select-4')]");
    String firstName = deFaker.name().firstName();
    String lastName = deFaker.name().lastName();
    String email = deFaker.internet().emailAddress();
    String gender = randomListItem(gendersArray);
    String mobile = deFaker.phoneNumber().subscriberNumber(10);
    String day = String.valueOf(deFaker.number().numberBetween(1, 31));
    String month = getMonth();
    String year = getYear();
    String subjects = randomListItem(subjectsArray);
    String hobbies = randomListItem(hobbiesArray);
    String pictureName = deFaker.file().fileName("", null, null, null)
            .substring(1);
    String address = deFaker.address().streetAddress();
    String state = randomListItem(stateArray);
    static String city;
    File picture = getPicture();

    public RegistrationFormData() throws IOException {
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
        StringBuilder sb = new StringBuilder();
        sb.append("src/test/resources/img/")
                .append(pictureName);

        Path newFilePath = Paths.get(sb.toString());
        Files.createFile(newFilePath);
        return new File(sb.toString());
    }

    String randomListItem(String[] values) {
        int index = random.nextInt(values.length);
        return values[index];
    }

    public static String randomElement() {
        city = cityList.get(random.nextInt(cityList.size())).getText();
        return city;
    }
}
