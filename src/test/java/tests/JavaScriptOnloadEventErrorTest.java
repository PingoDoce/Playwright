package tests;

import com.microsoft.playwright.ConsoleMessage;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.JavaScriptOnloadEventErrorPage;
import utils.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class JavaScriptOnloadEventErrorTest extends BaseTest {

    @Test
    @Description("Powinien zostać zarejestrowany co najmniej jeden błąd JS na załadowaniu strony")
    public void testJavaScriptOnloadError() {
        List<String> consoleErrors = new ArrayList<>();
        List<String> pageErrors = new ArrayList<>();

        // Zbieraj ERRORY z konsoli
        page.onConsoleMessage((ConsoleMessage msg) -> {
            if ("error".equalsIgnoreCase(msg.type())) {
                consoleErrors.add(msg.text());
            }
        });

        // Zbieraj błędy 'uncaught' (pageerror)
        page.onPageError(pageErrors::add);

        JavaScriptOnloadEventErrorPage errorPage = new JavaScriptOnloadEventErrorPage(page);
        errorPage.open();

        // Daj chwilę na wyemitowanie zdarzeń
        page.waitForLoadState();        // domcontentloaded -> load
        page.waitForTimeout(500);       // krótka pauza, żeby eventy doszły

        boolean gotAny = !pageErrors.isEmpty() || !consoleErrors.isEmpty();

        if (!gotAny) {
            // Pomocny log przy diagnostyce
            System.out.println("No pageerror/console error captured.");
        } else {
            System.out.println("pageErrors: " + pageErrors);
            System.out.println("consoleErrors: " + consoleErrors);
        }

        Assertions.assertTrue(
                gotAny,
                "Expected at least one JavaScript error on page load, but none were captured."
        );
    }
}
