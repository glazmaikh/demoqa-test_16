package tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class WikiMotorsSearchTests {
    @BeforeAll
    static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "C://webdrivers/chromedriver.exe");
        Configuration.browserSize = "1932x1160";
        Configuration.holdBrowserOpen = true;
    }

    @BeforeEach
    void setUp() {
        open("http://wikimotors.ru/");
    }

    @ValueSource(strings = {"m50b25", "m52b28", "m54b30"})
    @ParameterizedTest(name = "check searching engines {arguments}")
    @Tag("Blocker")
    void enginesSearchTestValueSource(String arg) {
        $("#search-3 input").shouldBe(visible)
                .val(arg).pressEnter();
        $x("//h2[@class='post-title entry-title']").shouldHave(text(arg));
    }

    @CsvSource({
            "m50b25, Двигатель BMW M50B25 / M50B25TU Характеристики Производство  Munich Plant Steyr Plant Марка двигателя М50 Годы выпуска 1990-1996 Материал блока цилиндров...",
            "m52b28, Двигатель BMW M52B28 / M52TUB28 Характеристики двигателя М52В28 Производство  Munich Plant Марка двигателя М52 Годы выпуска 1995-2001 Материал блока цилиндров...",
            "m54b30, Двигатель BMW M54B30 Характеристики двигателя М54В30 Производство  Munich Plant Марка двигателя М54 Годы выпуска 2000-2006 Материал блока цилиндров алюминий Система..."
    })
    @ParameterizedTest(name = "check searching engines {arguments}")
    @Tag("Blocker")
    void enginesSearchTestCsvSource(String actualRequest, String expectedResult) {
        $("#search-3 input").shouldBe(visible)
                .val(actualRequest).pressEnter();
        $(".post-inner .entry").shouldBe(visible)
                .shouldHave(text(expectedResult));
    }

    @CsvFileSource(
            resources = "/example.csv"
    )
    @ParameterizedTest(name = "check searching engines {arguments}")
    @Tag("Blocker")
    void enginesSearchTestCsvFileSource(String actualRequest, String expectedResult) {
        $("#search-3 input").shouldBe(visible)
                .val(actualRequest).pressEnter();
        $(".post-inner .entry").shouldBe(visible)
                .shouldHave(text(expectedResult));
    }

    static Stream<Arguments> enginesSearchTestMethodSource() {
        return Stream.of(
                Arguments.of("m50b25", List.of("M50B25", "M30B30", "M30B35", "S50B32", "S50B30",
                "M42B18", "M40B16", "M52B20", "M52B25", "M52B28")),
                Arguments.of("m52B28", List.of("M52B28", "S52B32", "M54B30", "M54B25", "M52B20",
                        "M52B25", "M50B20", "M20B20", "BMW 7-Series", "BMW 5-Series")),
                Arguments.of("m54b30", List.of("M54B30", "M30B30", "S54b32", "N54B30", "N52B30",
                        "M54B25", "M54B22", "M52B25", "M52B28", "M50B20"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "check list of headers from list {1} for request {0}")
    @Tag("Blocker")
    void enginesSearchTestMethodSource(String actualRequest, List<String> expectedHeaders) {
        $("#search-3 input").shouldBe(visible)
                .val(actualRequest).pressEnter();
        $$("#grid-wrapper .post-title a ").filter(visible)
                .shouldHave(CollectionCondition.texts(expectedHeaders));
    }
}
