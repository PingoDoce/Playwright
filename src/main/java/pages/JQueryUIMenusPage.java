package pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class JQueryUIMenusPage {
    private final Page page;

    private FrameLocator demoFrame;
    private boolean useFrame;

    public JQueryUIMenusPage(Page page) {
        this.page = page;
    }

    public void open() {
        page.navigate("https://the-internet.herokuapp.com/jqueryui/menu");

        int frames = page.locator("iframe").count();
        if (frames > 0) {
            useFrame = true;
            demoFrame = page.frameLocator("iframe").first();
            getMenuRoot().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.ATTACHED));
        } else {
            useFrame = false;
            getMenuRoot().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.ATTACHED));
        }
    }

    private Locator scope(String selector) {
        return useFrame ? demoFrame.locator(selector) : page.locator(selector);
    }

    private Locator getMenuRoot() {
        // XPath do menu - unikalny selektor
        return scope("//ul[@id='menu']");
    }

    private Locator enabledItem() {
        // XPath dla elementu Enabled
        return scope("//li[@id='ui-id-3']/a");
    }

    private Locator downloadsItem() {
        // XPath dla elementu Downloads
        return scope("//li[@id='ui-id-4']/a");
    }

    private Locator pdfLink() {
        // XPath dla linku PDF
        return scope("//a[text()='PDF']");
    }

    private Locator csvLink() {
        // XPath dla linku CSV
        return scope("//a[text()='CSV']");
    }

    private Locator excelLink() {
        // XPath dla linku Excel
        return scope("//a[text()='Excel']");
    }

    public void hoverToDownloads() {
        // Hover na odpowiednie elementy w menu
        enabledItem().hover();
        downloadsItem().hover();

        Locator.WaitForOptions visible = new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE);
        pdfLink().waitFor(visible);
        csvLink().waitFor(visible);
        excelLink().waitFor(visible);
    }

    public String getPdfHref() {
        return pdfLink().getAttribute("href");
    }

    public String getCsvHref() {
        csvLink().hover(); // Hover na link CSV
        return csvLink().getAttribute("href");
    }

    public String getExcelHref() {
        excelLink().hover(); // Hover na link Excel
        return excelLink().getAttribute("href");
    }

    public boolean isPdfVisible() {
        return pdfLink().isVisible();
    }

    public boolean isCsvVisible() {
        return csvLink().isVisible();
    }

    public boolean isExcelVisible() {
        return excelLink().isVisible();
    }
}
