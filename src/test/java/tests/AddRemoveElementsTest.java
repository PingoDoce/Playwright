package tests;

import com.microsoft.playwright.*;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.AddRemoveElementsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("UI Tests")
@Feature("Add/Remove Elements Page")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddRemoveElementsTest {

    Page page;
    AddRemoveElementsPage addRemoveElementsPage;


    @BeforeEach
    public void setup() {
        addRemoveElementsPage = new AddRemoveElementsPage(page);
    }

    @Test
    @Order(1)
    @Story("Add single element")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Sprawdzenie, czy jedno kliknięcie przycisku 'Add Element' dodaje jeden przycisk 'Delete'")
    public void testAddOneElement() {
        logStep("Przejdź do strony Add/Remove Elements");
        page.navigate("https://the-internet.herokuapp.com/add_remove_elements/");
        logStep("Kliknij 'Add Element'");
        addRemoveElementsPage.clickAddElement();

        logStep("Sprawdź liczbę przycisków 'Delete'");
        int count = addRemoveElementsPage.getDeleteButtonsCount();

        assertEquals(1, count, "Powinien być jeden przycisk 'Delete'");
    }

    @Test
    @Order(2)
    @Story("Add multiple elements")
    @Severity(SeverityLevel.NORMAL)
    @Description("Sprawdzenie dodawania wielu przycisków 'Delete'")
    public void testAddMultipleElements() {
        logStep("Przejdź do strony Add/Remove Elements");
        page.navigate("https://the-internet.herokuapp.com/add_remove_elements/");
        int clicks = 5;
        logStep("Kliknij 'Add Element' " + clicks + " razy");
        for (int i = 0; i < clicks; i++) {
            addRemoveElementsPage.clickAddElement();
        }

        logStep("Sprawdź liczbę przycisków 'Delete'");
        int count = addRemoveElementsPage.getDeleteButtonsCount();

        assertEquals(clicks, count, "Powinno być " + clicks + " przycisków 'Delete'");
    }

    @Test
    @Order(3)
    @Story("Delete one element")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Sprawdzenie, czy usunięcie elementu działa prawidłowo")
    public void testDeleteElement() {
        logStep("Przejdź do strony Add/Remove Elements");
        page.navigate("https://the-internet.herokuapp.com/add_remove_elements/");
        logStep("Dodaj 3 przyciski 'Delete'");
        for (int i = 0; i < 3; i++) {
            addRemoveElementsPage.clickAddElement();
        }

        logStep("Usuń drugi przycisk 'Delete'");
        addRemoveElementsPage.clickDeleteButton(1);

        logStep("Sprawdź liczbę pozostałych przycisków");
        int count = addRemoveElementsPage.getDeleteButtonsCount();

        assertEquals(2, count, "Powinny zostać 2 przyciski 'Delete'");
    }

    @Step("{message}")
    public void logStep(String message) {
        // Pusta metoda z @Step – używana dla czytelnych logów w Allure
    }
}
