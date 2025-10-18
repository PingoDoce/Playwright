package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Download;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.SecureFileDownloadPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SecureFileDownloadTest extends BaseTest {

    @Test
    void testSecureFileDownload() throws Exception {
        // nowy kontekst z pobieraniem i (opcjonalnie) HTTP Basic
        context.close();
        context = browser.newContext(new Browser.NewContextOptions()
                .setAcceptDownloads(true)
                .setHttpCredentials(new com.microsoft.playwright.options.HttpCredentials("admin", "admin")) // usuń jeśli nie trzeba
        );
        page = context.newPage();
        page.setDefaultTimeout(20_000);

        page.navigate("https://the-internet.herokuapp.com/download_secure");
        page.waitForSelector("#content a[href]"); // upewnij się, że linki są w DOM

        com.microsoft.playwright.Download download = page.waitForDownload(() ->
                page.locator("#content a[href]").first().click()
        );

        // weryfikacja
        String name = download.suggestedFilename();
        Assertions.assertTrue(name != null && !name.isBlank(), "Powinna być nazwa pliku");

        // (opcjonalnie) trwały zapis
        java.nio.file.Path target = java.nio.file.Paths.get("build/downloads", name);
        java.nio.file.Files.createDirectories(target.getParent());
        java.nio.file.Files.copy(download.path(), target, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
    }

}
