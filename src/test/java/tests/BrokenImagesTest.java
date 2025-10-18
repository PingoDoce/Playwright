package tests;

import com.microsoft.playwright.Locator;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.BrokenImagesPage;
import utils.BaseTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("UI Tests")
@Feature("Broken Images Page")
public class BrokenImagesTest extends BaseTest {

    private BrokenImagesPage brokenImagesPage;

    @Test
    @Story("Wizualne sprawdzanie obrazów")
    @Severity(SeverityLevel.NORMAL)
    @Description("Sprawdza, które obrazki są niewidoczne (naturalWidth == 0)")
    public void testBrokenImagesByNaturalWidth() {
        logStep("Przejdź do strony z obrazkami");
        page.navigate("https://the-internet.herokuapp.com/broken_images");

        brokenImagesPage = new BrokenImagesPage(page);

        logStep("Wyszukaj wszystkie obrazki, które są niewidoczne");
        List<Locator> brokenImages = brokenImagesPage.getBrokenImagesByNaturalWidth();

        captureScreenshot("Widok strony z uszkodzonymi obrazami");

        logStep("Liczba niewidocznych obrazków: " + brokenImages.size());
        assertTrue(brokenImages.size() > 0, "Powinny być co najmniej 2 uszkodzone obrazki");
    }
}
