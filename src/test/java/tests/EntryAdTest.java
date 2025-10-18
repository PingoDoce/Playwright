package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.EntryAdPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Entry Ad Modal Tests")
public class EntryAdTest extends BaseTest {
    private EntryAdPage entryAdPage;

    @BeforeEach
    public void initPage() {
        context.clearCookies(); // resetuje zachowanie modala
        entryAdPage = new EntryAdPage(page);
    }

    @Test
    @DisplayName("Modal powinien pojawić się po wejściu na stronę")
    public void testModalAppearsOnLoad() {
        entryAdPage.open();
        entryAdPage.waitForModalToAppear();
        assertTrue(entryAdPage.isModalVisible(), "Modal powinien być widoczny po załadowaniu strony.");
    }

    @Test
    @DisplayName("Modal powinien się zamknąć po kliknięciu przycisku")
    public void testModalCanBeClosed() {
        entryAdPage.open();
        entryAdPage.waitForModalToAppear();
        entryAdPage.closeModal();
        assertFalse(entryAdPage.isModalVisible(), "Modal powinien zostać zamknięty.");
    }

    @Test
    @DisplayName("Tytuł modala powinien być poprawny")
    public void testModalContent() {
        entryAdPage.open();
        entryAdPage.waitForModalToAppear();
        String title = entryAdPage.getModalTitle();
        assertTrue(title.equalsIgnoreCase("This is a modal window"), "Tytuł modala powinien być zgodny.");
    }
}
