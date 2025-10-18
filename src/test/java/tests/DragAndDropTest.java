package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.DragAndDropPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("UI Tests")
@Feature("Drag and Drop Page")
public class DragAndDropTest extends BaseTest {

    private DragAndDropPage pageObject;

    @Test
    @Story("Drag & Drop A → B")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Sprawdza, czy po przeciągnięciu kolumny A na B zawartość zostaje zamieniona")
    public void testDragAtoB() {
        logStep("Otwórz stronę Drag and Drop");
        page.navigate("https://the-internet.herokuapp.com/drag_and_drop");

        pageObject = new DragAndDropPage(page);

        logStep("Sprawdź początkowy stan nagłówków");
        String beforeA = pageObject.getHeaderOfColumnA();
        String beforeB = pageObject.getHeaderOfColumnB();

        assertEquals("A", beforeA);
        assertEquals("B", beforeB);

        logStep("Wykonaj przeciągnięcie A → B");
        pageObject.dragAtoB();
        captureScreenshot("Po przeciągnięciu");

        logStep("Sprawdź nowy stan nagłówków");
        String afterA = pageObject.getHeaderOfColumnA();
        String afterB = pageObject.getHeaderOfColumnB();

        assertEquals("B", afterA, "Po przeciągnięciu kolumna A powinna mieć nagłówek B");
        assertEquals("A", afterB, "Po przeciągnięciu kolumna B powinna mieć nagłówek A");
    }
}
