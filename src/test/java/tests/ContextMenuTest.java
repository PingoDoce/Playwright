package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.ContextMenuPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("UI Tests")
@Feature("Context Menu Page")
public class ContextMenuTest extends BaseTest {

    private ContextMenuPage contextMenuPage;

    @Test
    @Story("Kliknięcie prawym przyciskiem i obsługa alertu")
    @Severity(SeverityLevel.NORMAL)
    @Description("Wykonaj kliknięcie prawym przyciskiem na pole i sprawdź alert")
    public void testContextClickShowsAlert() {
        logStep("Otwórz stronę Context Menu");
        page.navigate("https://the-internet.herokuapp.com/context_menu");

        contextMenuPage = new ContextMenuPage(page);

        logStep("Dodaj listener do obsługi alertu");
        page.onceDialog(dialog -> {
            String alertText = dialog.message();
            assertEquals("You selected a context menu", alertText, "Alert powinien zawierać odpowiedni tekst");
            dialog.accept();
        });

        logStep("Wykonaj kliknięcie prawym przyciskiem myszy");
        contextMenuPage.rightClickHotSpot();

        captureScreenshot("Po kliknięciu prawym przyciskiem");
    }
}
