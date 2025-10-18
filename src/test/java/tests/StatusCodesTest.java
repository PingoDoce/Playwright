package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.StatusCodesPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("The Internet")
@Feature("StatusCodes")
@Story("StatusCodes page test")
@Severity(SeverityLevel.NORMAL)
public class StatusCodesTest extends BaseTest {

    @Test
    @DisplayName("Sprawdź kod 200")
    public void testStatus200() {
        StatusCodesPage statusPage = new StatusCodesPage(page);
        statusPage.open();
        logStep("Przechodzimy do linku 200");

        int status = statusPage.navigateToStatus(200);
        assertEquals(200, status, "Oczekiwano kodu HTTP 200");
    }

    @Test
    @DisplayName("Sprawdź kod 404")
    public void testStatus404() {
        StatusCodesPage statusPage = new StatusCodesPage(page);
        statusPage.open();
        logStep("Przechodzimy do linku 404");

        int status = statusPage.navigateToStatus(404);
        assertEquals(404, status, "Oczekiwano kodu HTTP 404");
    }

    @Test
    @DisplayName("Sprawdź kod 500")
    public void testStatus500() {
        StatusCodesPage statusPage = new StatusCodesPage(page);
        statusPage.open();
        logStep("Przechodzimy do linku 500");

        int status = statusPage.navigateToStatus(500);

    }
}
