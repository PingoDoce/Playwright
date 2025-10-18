package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.InputsPage;
import utils.BaseTest;

public class InputsTest extends BaseTest {

    @Test
    public void testNumberInputField() {
        InputsPage pageObject = new InputsPage(page);
        pageObject.open();

        pageObject.setInputValue("1234");
        Assertions.assertEquals("1234", pageObject.getInputValue());

        pageObject.setInputValue("-567");
        Assertions.assertEquals("-567", pageObject.getInputValue());
    }

    @Test
    public void testInvalidInputIgnored() {
        InputsPage pageObject = new InputsPage(page);
        pageObject.open();

        pageObject.setInputValue("abc");
        String value = pageObject.getInputValue();

        Assertions.assertTrue(value.isEmpty(), "Expected field to remain empty on invalid input");
    }
}
