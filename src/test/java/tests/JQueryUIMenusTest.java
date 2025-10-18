package tests;

import org.junit.jupiter.api.*;
import pages.JQueryUIMenusPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.*;

public class JQueryUIMenusTest extends BaseTest {
    private JQueryUIMenusPage pageObject;

    @BeforeEach
    public void setUp() {
        pageObject = new JQueryUIMenusPage(page);
    }

    @Test
    public void testPdfDownloadLink() {
        pageObject.open();
        pageObject.hoverToDownloads();
        assertTrue(pageObject.isPdfVisible(), "PDF link should be visible");
        assertTrue(pageObject.getPdfHref().endsWith("menu.pdf"));
    }

    @Test
    public void testCsvDownloadLink() {
        pageObject.open();
        pageObject.hoverToDownloads();
        assertTrue(pageObject.isCsvVisible(), "CSV link should be visible");
        assertTrue(pageObject.getCsvHref().endsWith("menu.csv"));
    }

    @Test
    public void testExcelDownloadLink() {
        pageObject.open();
        pageObject.hoverToDownloads();
        assertTrue(pageObject.isExcelVisible(), "Excel link should be visible");
        assertTrue(pageObject.getExcelHref().endsWith("menu.xls"));
    }
}
