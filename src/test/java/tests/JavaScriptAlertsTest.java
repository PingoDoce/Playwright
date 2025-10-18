package tests;

import org.junit.jupiter.api.*;
import pages.JavaScriptAlertsPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaScriptAlertsTest extends BaseTest {
    private JavaScriptAlertsPage alertsPage;

    @BeforeEach
    public void setUpTest() {
        alertsPage = new JavaScriptAlertsPage(page);
    }

    @Test
    public void testJsAlertAccept() {
        alertsPage.open();
        page.onceDialog(dialog -> dialog.accept());
        alertsPage.triggerJsAlert();
        assertEquals("You successfully clicked an alert", alertsPage.getResultText());
    }

    @Test
    public void testJsConfirmAccept() {
        alertsPage.open();
        page.onceDialog(dialog -> dialog.accept());
        alertsPage.triggerJsConfirm();
        assertEquals("You clicked: Ok", alertsPage.getResultText());
    }

    @Test
    public void testJsConfirmDismiss() {
        alertsPage.open();
        page.onceDialog(dialog -> dialog.dismiss());
        alertsPage.triggerJsConfirm();
        assertEquals("You clicked: Cancel", alertsPage.getResultText());
    }

    @Test
    public void testJsPromptInput() {
        alertsPage.open();
        String input = "Playwright Test";
        page.onceDialog(dialog -> dialog.accept(input));
        alertsPage.triggerJsPrompt();
        assertEquals("You entered: " + input, alertsPage.getResultText());
    }

    @Test
    public void testJsPromptCancel() {
        alertsPage.open();
        page.onceDialog(dialog -> dialog.dismiss());
        alertsPage.triggerJsPrompt();
        assertEquals("You entered: null", alertsPage.getResultText());
    }
}
