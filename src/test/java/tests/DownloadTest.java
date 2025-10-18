package tests;

import com.microsoft.playwright.Download;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.DownloadPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.*;

public class DownloadTest extends BaseTest {

    @Test
    @DisplayName("Lista plików powinna być dostępna do pobrania")
    public void testFilesAreListed() {
        DownloadPage pageObject = new DownloadPage(page);
        pageObject.open();

        assertTrue(pageObject.getNumberOfFiles() > 0, "Powinny być dostępne pliki do pobrania.");
        pageObject.getFileNames().forEach(name -> assertFalse(name.isBlank(), "Nazwa pliku nie powinna być pusta"));
    }

    @Test
    @DisplayName("Kliknięcie w pierwszy plik powinno zainicjować pobieranie")
    public void testDownloadFileLink() {
        DownloadPage pageObject = new DownloadPage(page);
        pageObject.open();

        Download download = pageObject.downloadFirstFile();
        assertNotNull(download);
        assertFalse(download.suggestedFilename().isBlank(), "Sugerowana nazwa pliku nie powinna być pusta");

        logStep("Kliknięto w pierwszy plik – pobieranie zainicjowane");
    }
}
