package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.DropdownPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("UI Tests")
@Feature("Dropdown Page")
public class DropdownTest extends BaseTest {

    private DropdownPage pageObject;

    @Test
    @Story("Wybór opcji z rozwijanego menu")
    @Severity(SeverityLevel.NORMAL)
    @Description("Testuje wybór dostępnych opcji z dropdowna")
    public void testDropdownSelection() {
        logStep("Przejdź do strony Dropdown");
        page.navigate("https://the-internet.herokuapp.com/dropdown");
        pageObject = new DropdownPage(page);

        logStep("Wybierz Option 1");
        pageObject.selectByVisibleText("Option 1");
        captureScreenshot("Option 1 selected");
        assertEquals("Option 1", pageObject.getSelectedOptionText());

        logStep("Wybierz Option 2");
        pageObject.selectByVisibleText("Option 2");
        captureScreenshot("Option 2 selected");
        assertEquals("Option 2", pageObject.getSelectedOptionText());
    }
}
