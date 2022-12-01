package pages.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SubjectsInputComponent {

    public SubjectsInputComponent setOneSubject(String value) {
        $("#subjectsInput").setValue(value).pressEnter();
        return this;
    }

    public void shouldHaveValue(String value) {
        $("#subjectsContainer").shouldHave(text(value));
    }
}
