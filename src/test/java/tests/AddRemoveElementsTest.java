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

    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;
    AddRemoveElementsPage addRemoveElementsPage;

    @BeforeAll
    static void setupAll() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterAll
    static void tearDownAll() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    public void setup() {
        context = browser.newContext();
        page = context.newPage();
        logStep("Przejdź do strony Add/Remove Elements");
        page.navigate("https://the-internet.herokuapp.com/add_remove_elements/");
        addRemoveElementsPage = new AddRemoveElementsPage(page);
    }

    @AfterEach
    public void cleanup() {
        context.close();
    }

    @Test
    @Order(1)
    @Story("Add single element")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Sprawdzenie, czy jedno kliknięcie przycisku 'Add Element' dodaje jeden przycisk 'Delete'")
    public void testAddOneElement() {
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
