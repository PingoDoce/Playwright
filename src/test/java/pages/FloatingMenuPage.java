package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class FloatingMenuPage {
    private final Page page;
    private final String url = "https://the-internet.herokuapp.com/floating_menu";
    private final Locator menuLinks;
    private final Locator menu;

    public FloatingMenuPage(Page page) {
        this.page = page;
        this.menuLinks = page.locator("#menu a");
        this.menu = page.locator("#menu");
    }

    public void open() {
        page.navigate(url);
    }

    public Locator getMenuLinks() {
        return menuLinks;
    }

    public void scrollDown() {
        page.evaluate("window.scrollTo(0, document.body.scrollHeight)");
    }

    public boolean isMenuVisible() {
        return menu.isVisible();
    }
}
