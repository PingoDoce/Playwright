package pages;

import com.microsoft.playwright.Page;

public class SecureFileDownloadPage {
    private final Page page;
    private final String url = "https://the-internet.herokuapp.com/download_secure";

    public SecureFileDownloadPage(Page page) {
        this.page = page;
    }

    public void open() {
        page.navigate(url);
    }
}
