package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.CheckboxesPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.*;

@Epic("UI Tests")
@Feature("Checkboxes Page")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CheckboxesTest extends BaseTest {

    private CheckboxesPage checkboxesPage;

    @BeforeEach
    public void openPage() {
        checkboxesPage = new CheckboxesPage(page);
    }

    @Test
    @Order(1)
    @Story("Domyślny stan checkboxów")
    @Description("Sprawdza, że pierwszy checkbox jest odznaczony, a drugi zaznaczony")
    public void testDefaultState() {
        logStep("Otwórz stronę Checkboxes");
        page.navigate("https://the-internet.herokuapp.com/checkboxes");
        assertFalse(checkboxesPage.isCheckboxSelected(0), "Checkbox 1 powinien być odznaczony");
        assertTrue(checkboxesPage.isCheckboxSelected(1), "Checkbox 2 powinien być zaznaczony");
    }

    @Test
    @Order(2)
    @Story("Zaznaczenie checkboxa")
    @Description("Zaznacz pierwszy checkbox i sprawdź stan")
    public void testCheckFirstCheckbox() {
        logStep("Otwórz stronę Checkboxes");
        page.navigate("https://the-internet.herokuapp.com/checkboxes");
        checkboxesPage.checkCheckbox(0);
        assertTrue(checkboxesPage.isCheckboxSelected(0), "Checkbox 1 powinien być zaznaczony");
    }

    @Test
    @Order(3)
    @Story("Odznaczenie checkboxa")
    @Description("Odznacz drugi checkbox i sprawdź stan")
    public void testUncheckSecondCheckbox() {
        logStep("Otwórz stronę Checkboxes");
        page.navigate("https://the-internet.herokuapp.com/checkboxes");
        checkboxesPage.uncheckCheckbox(1);
        assertFalse(checkboxesPage.isCheckboxSelected(1), "Checkbox 2 powinien być odznaczony");
    }

    @Test
    @Order(4)
    @Story("Zaznaczenie obu checkboxów")
    @Description("Zaznacz oba checkboxy i sprawdź ich stan")
    public void testCheckBothCheckboxes() {
        logStep("Otwórz stronę Checkboxes");
        page.navigate("https://the-internet.herokuapp.com/checkboxes");
        checkboxesPage.checkCheckbox(0);
        checkboxesPage.checkCheckbox(1);

        assertTrue(checkboxesPage.isCheckboxSelected(0), "Checkbox 1 powinien być zaznaczony");
        assertTrue(checkboxesPage.isCheckboxSelected(1), "Checkbox 2 powinien być zaznaczony");
    }

    @Test
    @Order(5)
    @Story("Odznaczenie obu checkboxów")
    @Description("Odznacz oba checkboxy i sprawdź ich stan")
    public void testUncheckBothCheckboxes() {
        logStep("Otwórz stronę Checkboxes");
        page.navigate("https://the-internet.herokuapp.com/checkboxes");
        checkboxesPage.uncheckCheckbox(0);
        checkboxesPage.uncheckCheckbox(1);

        assertFalse(checkboxesPage.isCheckboxSelected(0), "Checkbox 1 powinien być odznaczony");
        assertFalse(checkboxesPage.isCheckboxSelected(1), "Checkbox 2 powinien być odznaczony");
    }
}
