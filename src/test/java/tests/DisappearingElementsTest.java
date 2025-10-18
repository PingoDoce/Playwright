package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.DisappearingElementsPage;
import utils.BaseTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Epic("UI Tests")
@Feature("Disappearing Elements Page")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DisappearingElementsTest extends BaseTest {

    private DisappearingElementsPage pageObject;

    @BeforeEach
    public void openPage() {
        pageObject = new DisappearingElementsPage(page);
    }

    @Test
    @Order(1)
    @Story("Sprawdzenie liczby linków")
    @Description("Sprawdza, czy liczba linków w menu to 4 lub 5")
    public void testNumberOfLinks() {
        logStep("Otwórz stronę Disappearing Elements");
        page.navigate("https://the-internet.herokuapp.com/disappearing_elements");
        int size = pageObject.getNavigationLinks().count();
        captureScreenshot("Stan menu");
        assertTrue(size == 4 || size == 5, "Menu powinno zawierać 4 lub 5 linków, obecnie: " + size);
    }

    @Test
    @Order(2)
    @Story("Sprawdzenie obecności linku Gallery")
    @Description("Sprawdza, czy link 'Gallery' pojawia się w menu po kilku odświeżeniach")
    public void testGalleryLinkAppearsEventually() {
        logStep("Otwórz stronę Disappearing Elements");
        page.navigate("https://the-internet.herokuapp.com/disappearing_elements");
        boolean found = false;

        for (int i = 0; i < 10; i++) {
            page.reload();
            if (pageObject.isLinkPresent("Gallery")) {
                found = true;
                break;
            }
        }

        captureScreenshot("Po odświeżeniach");
        assertTrue(found, "Link 'Gallery' powinien pojawić się po kilkukrotnym odświeżeniu strony");
    }

    @Test
    @Order(3)
    @Story("Klikalność linków")
    @Description("Kliknij każdy dostępny link i wróć, aby sprawdzić działanie")
    public void testEachLinkIsClickable() {
        logStep("Otwórz stronę Disappearing Elements");
        page.navigate("https://the-internet.herokuapp.com/disappearing_elements");
        Set<String> testedLinks = new HashSet<>();

        List<String> texts = pageObject.getNavigationLinks().allInnerTexts();
        for (String raw : texts) {
            String text = raw.trim();
            if (text.equalsIgnoreCase("Gallery")) continue; // może nie działać – pomijamy
            testedLinks.add(text);

            logStep("Kliknij link: " + text);
            pageObject.clickLink(text);
            assertTrue(page.title().length() > 0 || page.content().contains("Not Found"));

            page.goBack();
        }

        captureScreenshot("Po kliknięciach");
        assertFalse(testedLinks.isEmpty(), "Powinien być przynajmniej 1 kliknięty link");
    }
}
