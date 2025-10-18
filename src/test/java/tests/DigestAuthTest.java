package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.options.HttpCredentials;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.DigestAuthPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("UI Tests")
@Feature("Digest Authentication Page")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DigestAuthTest extends BaseTest {

    private DigestAuthPage pageObject;

    @Test
    @Order(1)
    @Story("Digest Auth – logowanie przez httpCredentials")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Weryfikuje logowanie do strony chronionej Digest Auth z użyciem httpCredentials w kontekście Playwright")
    public void testDigestAuthWithHttpCredentials() {
        // Zamknij kontekst utworzony w BaseTest i stwórz nowy z danymi logowania
        context.close();
        context = browser.newContext(
                new Browser.NewContextOptions()
                        .setHttpCredentials(new HttpCredentials("admin", "admin"))
        );
        page = context.newPage();

        logStep("Wejdź na stronę Digest Auth");
        page.navigate("https://the-internet.herokuapp.com/digest_auth");

        pageObject = new DigestAuthPage(page);

        logStep("Sprawdź komunikat po zalogowaniu");
        String message = pageObject.getSuccessMessage();
        captureScreenshot("Po zalogowaniu (Digest Auth)");

        assertTrue(message.contains("Congratulations"), "Powinien pojawić się komunikat potwierdzający zalogowanie");
    }
}
