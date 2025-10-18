package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import pages.MultipleWindowsPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultipleWindowsTest extends BaseTest {

    @Test
    @Description("Sprawdza, czy po kliknięciu 'Click Here' otwiera się nowe okno z właściwym nagłówkiem")
    public void testNewWindowOpensCorrectly() {
        MultipleWindowsPage pageObject = new MultipleWindowsPage(page);
        pageObject.navigate();
        logStep("Przechodzimy na stronę Multiple Windows");

        logStep("Klikamy w link 'Click Here' i czekamy na nowe okno");
        com.microsoft.playwright.Page newPage = pageObject.clickHereAndWaitForPopup();

        logStep("Przełączamy się do nowego okna");
        String newHeader = newPage.locator("h3").innerText();

        assertEquals("New Window", newHeader);
        logStep("Nagłówek nowego okna to 'New Window'");
    }
}
