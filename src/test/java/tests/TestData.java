package tests;

import com.github.javafaker.Faker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

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
    static File picture;

    static {
        try {
            picture = getPicture();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    String address = faker.address().streetAddress();
    String state = getState();
    String city = getCity(state);

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

    static File getPicture() throws IOException {
        String path = "src/test/resources/";
        Path newFilePath = Paths.get(path + pictureName);
        Files.createFile(newFilePath);
        return new File(path + pictureName);
    }

    public String getState() {
        String[] states = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
        return getRandomArrayItem(states);
    }

    public String getCity(String key) {
        Map<String, String[]> cityList = new HashMap<>();
        cityList.put("NCR", new String[] {"Delhi", "Gurgaon", "Noida"});
        cityList.put("Uttar Pradesh", new String[] {"Agra", "Lucknow", "Merrut"});
        cityList.put("Haryana", new String[] {"Karnal", "Panipat"});
        cityList.put("Rajasthan", new String[] {"Jaipur", "Jaiselmer"});
        return getRandomArrayItem(cityList.get(key));
    }


}
