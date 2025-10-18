package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.RepeatedTest;
import pages.NotificationMessagePage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotificationMessageTest extends BaseTest {

    @RepeatedTest(5) // uruchom kilka razy, bo wiadomość jest losowa
    @Description("Sprawdza, czy pojawia się komunikat po kliknięciu 'Click here'")
    public void testNotificationAppears() {
        NotificationMessagePage pageObject = new NotificationMessagePage(page);
        pageObject.navigate();
        logStep("Otwieramy stronę z powiadomieniem");

        pageObject.clickHere();
        logStep("Klikamy w 'Click here'");

        assertTrue(pageObject.isNotificationVisible(), "Powiadomienie powinno być widoczne");
        logStep("Powiadomienie: " + pageObject.getNotificationText());
    }
}
