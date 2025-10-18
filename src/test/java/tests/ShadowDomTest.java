package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ShadowDomPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("The Internet")
@Feature("ShadowDom")
@Story("ShadowDom page test")
@Severity(SeverityLevel.NORMAL)
public class ShadowDomTest extends BaseTest {

    @Test
    @DisplayName("Weryfikacja elementów Shadow DOM (Playwright)")
    @Description("Playwright automatycznie obsługuje Shadow DOM, więc możemy używać standardowych lokatorów")
    public void testShadowDomContent() {
        ShadowDomPage pageObject = new ShadowDomPage(page);
        pageObject.open();

        // np. sprawdzamy tekst w shadow root
        String text = page.locator("my-paragraph").first().innerText();
        assertTrue(text.contains("Let's have some different text!"),
                "Shadow DOM powinien zawierać oczekiwany tekst");
    }
}
