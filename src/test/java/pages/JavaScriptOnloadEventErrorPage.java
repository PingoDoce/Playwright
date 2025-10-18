package pages;

import com.microsoft.playwright.Page;

public class JavaScriptOnloadEventErrorPage {
    private final Page page;
    private final String url = "https://the-internet.herokuapp.com/javascript_error";

    public JavaScriptOnloadEventErrorPage(Page page) {
        this.page = page;
    }

    public void open() {
        page.navigate(url);
    }
}
