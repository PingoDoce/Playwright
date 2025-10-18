package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.BasicAuthPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("UI Tests")
@Feature("Basic Auth Page")
public class BasicAuthTest extends BaseTest {

    private BasicAuthPage pageObject;

    @Test
    @Story("Zaloguj się przez Basic Auth")
    @Severity(SeverityLevel.BLOCKER)
    @Description("UI test potwierdzający logowanie do strony chronionej Basic Auth przez adres URL")
    public void testBasicAuthThroughUI() {
        String url = "https://admin:admin@the-internet.herokuapp.com/basic_auth";
        logStep("Przejdź do strony z Basic Auth z danymi logowania w adresie URL");
        page.navigate(url);
        pageObject = new BasicAuthPage(page);
        logStep("Sprawdź, czy pojawił się komunikat powitalny");
        String message = pageObject.getSuccessMessage();
        captureScreenshot("Po zalogowaniu");
        assertTrue(message.contains("Congratulations"), "Powinien pojawić się komunikat 'Congratulations!'");
    }
}
