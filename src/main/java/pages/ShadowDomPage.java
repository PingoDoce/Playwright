package pages;

import com.microsoft.playwright.Page;

public class ShadowDomPage {
    private final Page page;
    private final String url = "https://the-internet.herokuapp.com/shadowdom";

    public ShadowDomPage(Page page) {
        this.page = page;
    }

    public void open() {
        page.navigate(url);
    }
}
