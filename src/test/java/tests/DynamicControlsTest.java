package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.DynamicControlsPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.*;

@Epic("UI Tests")
@Feature("Dynamic Controls Page")
public class DynamicControlsTest extends BaseTest {

    private DynamicControlsPage pageObject;

    @Test
    @Story("Ukrywanie i pokazywanie checkboxa")
    @Severity(SeverityLevel.NORMAL)
    @Description("Testuje usuwanie i dodawanie checkboxa oraz wyświetlanie komunikatów")
    public void testCheckboxAppearsAndDisappears() {
        logStep("Otwórz stronę Dynamic Controls");
        page.navigate("https://the-internet.herokuapp.com/dynamic_controls");

        pageObject = new DynamicControlsPage(page);

        logStep("Kliknij Remove");
        pageObject.clickRemoveAddButton();
        pageObject.waitForCheckboxToDisappear();
        captureScreenshot("Po usunięciu checkboxa");
        assertFalse(pageObject.isCheckboxVisible());
        assertEquals("It's gone!", pageObject.getMessage());

        logStep("Kliknij Add");
        pageObject.clickRemoveAddButton();
        pageObject.waitForCheckboxToAppear();
        captureScreenshot("Po dodaniu checkboxa");
        assertTrue(pageObject.isCheckboxVisible());
        assertEquals("It's back!", pageObject.getMessage());
    }

    @Test
    @Story("Włączanie i wyłączanie pola input")
    @Severity(SeverityLevel.NORMAL)
    @Description("Testuje przełączanie aktywności pola tekstowego")
    public void testEnableDisableInputField() {
        page.navigate("https://the-internet.herokuapp.com/dynamic_controls");
        pageObject = new DynamicControlsPage(page);

        logStep("Kliknij Enable");
        pageObject.clickEnableDisableButton();
        pageObject.waitForInputToBeEnabled();
        captureScreenshot("Input odblokowany");
        assertTrue(pageObject.isInputEnabled());
        assertEquals("It's enabled!", pageObject.getMessage());

        logStep("Kliknij Disable");
        pageObject.clickEnableDisableButton();
        pageObject.waitForInputToBeDisabled();
        captureScreenshot("Input zablokowany");
        assertFalse(pageObject.isInputEnabled());
        assertEquals("It's disabled!", pageObject.getMessage());
    }
}
