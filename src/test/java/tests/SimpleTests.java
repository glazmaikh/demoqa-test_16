package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class SimpleTests extends TestBase {

    @Test
    void test1() {
        open("/automation-practice-form");
        int i = 1;
        Assertions.assertEquals(1, i);
    }

    @Test
    void test2() {
        int i = 2;
        Assertions.assertEquals(2, i);
    }

    @Test
    void test3() {
        int i = 3;
        Assertions.assertEquals(3, i);
    }
}
