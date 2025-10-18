package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.FloatingMenuPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.*;

public class FloatingMenuTest extends BaseTest {

    @Test
    @DisplayName("Menu powinno być widoczne nawet po przewinięciu strony")
    public void testFloatingMenuVisibilityOnScroll() {
        FloatingMenuPage pageObject = new FloatingMenuPage(page);
        pageObject.open();

        assertTrue(pageObject.isMenuVisible(), "Menu powinno być widoczne po załadowaniu strony");

        pageObject.scrollDown();

        // Po przewinięciu – nadal powinno być widoczne
        assertTrue(pageObject.isMenuVisible(), "Menu powinno być widoczne po przewinięciu strony w dół");
    }

    @Test
    @DisplayName("Linki w menu powinny być poprawne")
    public void testMenuLinksAreValid() {
        FloatingMenuPage pageObject = new FloatingMenuPage(page);
        pageObject.open();

        int count = pageObject.getMenuLinks().count();
        for (int i = 0; i < count; i++) {
            String href = pageObject.getMenuLinks().nth(i).getAttribute("href");
            assertNotNull(href, "Każdy link powinien mieć atrybut href");
            assertTrue(href.contains("#"), "Każdy link powinien być lokalnym odnośnikiem (zawierać #)");
        }
    }
}
