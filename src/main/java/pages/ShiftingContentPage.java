package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ShiftingContentPage {
    private final Page page;
    private final Locator menu;
    private final Locator firstMenuLink;

    private static final String URL = "https://the-internet.herokuapp.com/shifting_content/menu";

    public ShiftingContentPage(Page page) {
        this.page = page;
        // Poprawiamy selektor, upewniając się, że adresujemy właściwy element
        this.menu = page.locator("#content ul").first();  // Pierwsza lista w kontenerze #content
        this.firstMenuLink = page.locator("#content ul li:nth-child(2)").first();  // Pierwszy link w menu
    }

    public void open() {
        page.navigate(URL, new Page.NavigateOptions().setWaitUntil(com.microsoft.playwright.options.WaitUntilState.DOMCONTENTLOADED));

//        // Sprawdzamy, czy menu jest widoczne w DOM
//        if (!menu.isVisible()) {
//            throw new RuntimeException("Menu nie jest widoczne.");
//        }
    }

    public void waitForMenu() {
        // Sprawdzamy, czy pierwszy element w menu jest widoczny
        if (!menu.locator("li").first().isVisible()) {
            throw new RuntimeException("Pierwszy element w menu nie jest widoczny.");
        }
    }

    public void clickFirstMenuLink() {
        // Czekamy aż pierwszy link będzie widoczny
        if (firstMenuLink.isVisible()) {
            firstMenuLink.scrollIntoViewIfNeeded();
            firstMenuLink.click();
        } else {
            throw new RuntimeException("Pierwszy link w menu nie jest widoczny.");
        }
    }

    public boolean waitForUrlContains(String fragment) {
        page.waitForURL("**" + fragment + "**");
        return page.url().contains(fragment);
    }
}
