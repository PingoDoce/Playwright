package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import pages.ShiftingContentPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("The Internet")
@Feature("Shifting Content")
public class ShiftingContentTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Kliknięcie pierwszego linku w menu i weryfikacja przejścia na inną stronę")
    public void testShiftingMenu() {
        ShiftingContentPage pageObject = new ShiftingContentPage(page);

        logStep("Otwórz stronę menu");
        pageObject.open();

        logStep("Czekaj na obecność menu w DOM");
        pageObject.waitForMenu();

        logStep("Kliknij pierwszy link w menu (About)");
        pageObject.clickFirstMenuLink();

        logStep("Poczekaj aż URL się zmieni (nawigacja)");
        boolean navigated = pageObject.waitForUrlContains("/about/");
        captureScreenshot("Po kliknięciu linku z menu");

        assertTrue(navigated, "Powinniśmy przejść do strony zawierającej '/about/' w URL");
    }
}
