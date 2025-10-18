package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.DynamicLoadingPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.*;

@Epic("UI Tests")
@Feature("Dynamic Loading Page")
public class DynamicLoadingTest extends BaseTest {

    @Test
    @Story("Example 1: Element hidden in DOM")
    @Severity(SeverityLevel.NORMAL)
    @Description("Testuje poprawne ładowanie elementu, który był ukryty w DOM (Example 1)")
    public void testDynamicLoadingExample1() {
        page.navigate("https://the-internet.herokuapp.com/dynamic_loading/1");
        DynamicLoadingPage dynamicPage = new DynamicLoadingPage(page);

        logStep("Kliknij przycisk Start");
        dynamicPage.clickStart();

        logStep("Czekaj na koniec ładowania");
        dynamicPage.waitForLoadingToFinish();

        captureScreenshot("Po załadowaniu (Example 1)");
        assertEquals("Hello World!", dynamicPage.getResultText());
    }

    @Test
    @Story("Example 2: Element not in the DOM until loaded")
    @Severity(SeverityLevel.NORMAL)
    @Description("Testuje ładowanie elementu dynamicznie dodawanego do DOM (Example 2)")
    public void testDynamicLoadingExample2() {
        page.navigate("https://the-internet.herokuapp.com/dynamic_loading/2");
        DynamicLoadingPage dynamicPage = new DynamicLoadingPage(page);

        logStep("Kliknij przycisk Start");
        dynamicPage.clickStart();

        logStep("Czekaj na koniec ładowania");
        dynamicPage.waitForLoadingToFinish();

        captureScreenshot("Po załadowaniu (Example 2)");
        assertEquals("Hello World!", dynamicPage.getResultText());
    }
}
