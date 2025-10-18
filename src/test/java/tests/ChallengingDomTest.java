package tests;

import com.microsoft.playwright.Locator;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.ChallengingDomPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.*;

@Epic("UI Tests")
@Feature("Challenging DOM Page")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ChallengingDomTest extends BaseTest {

    private ChallengingDomPage pageObject;

    @BeforeEach
    public void openPage() {
        pageObject = new ChallengingDomPage(page);
    }

    @Test
    @Order(1)
    @Story("Struktura tabeli")
    @Description("Sprawdza, czy tabela zawiera poprawną liczbę kolumn i co najmniej jeden wiersz")
    public void testTableStructure() {
        logStep("Otwórz stronę Challenging DOM");
        page.navigate("https://the-internet.herokuapp.com/challenging_dom");
        Locator headers = pageObject.getTableHeaders();
        Locator rows = pageObject.getTableRows();

        assertEquals(7, headers.count(), "Tabela powinna mieć 7 kolumn");
        assertTrue(rows.count() > 0, "Tabela powinna mieć co najmniej jeden wiersz");
    }

    @Test
    @Order(2)
    @Story("Dane w komórce tabeli")
    @Description("Sprawdza, czy komórka [0][0] zawiera dane")
    public void testFirstCellHasText() {
        logStep("Otwórz stronę Challenging DOM");
        page.navigate("https://the-internet.herokuapp.com/challenging_dom");
        String text = pageObject.getCellText(0, 0);
        assertNotNull(text);
        assertFalse(text.isEmpty(), "Komórka nie powinna być pusta");
    }

    @Test
    @Order(3)
    @Story("Kliknięcie przycisków u góry")
    @Description("Kliknij wszystkie trzy przyciski u góry strony")
    public void testHeaderButtonsClickable() {
        logStep("Otwórz stronę Challenging DOM");
        page.navigate("https://the-internet.herokuapp.com/challenging_dom");
        Locator buttons = pageObject.getHeaderButtons();
        assertEquals(3, buttons.count(), "Powinny być 3 przyciski");

        for (int i = 0; i < 3; i++) {
            pageObject.clickHeaderButton(i);
        }
    }
}
