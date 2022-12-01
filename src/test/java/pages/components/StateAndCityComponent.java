package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class StateAndCityComponent {
    private final SelenideElement stateInput = $("#state");
    private final SelenideElement cityInput = $("#city");

    public StateAndCityComponent setStateAndCity(String state, String city) {
        stateInput.click();
        $(byText(state)).click();
        cityInput.click();
        $(byText(city)).click();
        return this;
    }
}
