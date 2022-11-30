package tests;

import com.github.javafaker.Faker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class TestData {

    static Faker faker = new Faker(new Locale("de"));
    static Random random = new Random();
    static String pictureName = faker.file().fileName("", null, null, null)
            .substring(1);
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    String gender = getGender();
    String number = faker.phoneNumber().subscriberNumber(10);
    String day = String.valueOf(faker.number().numberBetween(10, 28));
    String month = getMonth();
    String year = String.valueOf(faker.number().numberBetween(1900, 2100));
    String subject = getSubject();
    String hobby = getHobby();
    File picture = getPicture();
    String address = faker.address().streetAddress();
    String city = getCity();
    String state = getStateByCity(city);

    public TestData() throws IOException {
    }

    public String getGender() {
        String[] genderArray = {"Male", "Female", "Other"};
        return getRandomArrayItem(genderArray);
    }

    public String getRandomArrayItem(String[] values) {
        int index = random.nextInt(values.length);
        return values[index];
    }

    public String getMonth() {
        return new SimpleDateFormat("MMMM").format(faker.date().birthday());
    }

    public String getSubject() {
        String[] subjectsArray = {"Arts", "Maths", "English", "History"};
        return getRandomArrayItem(subjectsArray);
    }

    public String getHobby() {
        String[] hobbiesArray = {"Sports", "Reading", "Music"};
        return getRandomArrayItem(hobbiesArray);
    }

    File getPicture() throws IOException {
        String path = "src/test/resources/";
        Path newFilePath = Paths.get(path + pictureName);
        Files.createFile(newFilePath);
        return new File(path + pictureName);
    }

    public String getCity() {
        String[] states = {"Delhi", "Agra", "Karnal", "Gurgaon", "Lucknow", "Panipat", "Jaipur", "Jaiselmer"};
        return getRandomArrayItem(states);
    }

    public String getStateByCity(String value) {
        Map<String, String> cityAndState = new HashMap<>();
        cityAndState.put("Delhi", "NCR");
        cityAndState.put("Gurgaon", "NCR");
        cityAndState.put("Agra", "Uttar Pradesh");
        cityAndState.put("Lucknow", "Uttar Pradesh");
        cityAndState.put("Karnal", "Haryana");
        cityAndState.put("Panipat", "Haryana");
        cityAndState.put("Jaipur", "Rajasthan");
        cityAndState.put("Jaiselmer", "Rajasthan");
        return cityAndState.get(value);
    }


}
