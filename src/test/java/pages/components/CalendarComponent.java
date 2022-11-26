package pages.components;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CalendarComponent {
    private final List<SelenideElement> dayElements = $$(".react-datepicker__day" +
            ":not(.react-datepicker__day--outside-month)");
    public void setDate(String day, String month, String year) {
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__month-select").selectOption(month);
        for (SelenideElement se : dayElements) {
            if (se.getText().equals(day)) {
                se.click();
                break;
            }
        }
    }
}
