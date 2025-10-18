package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;

public class StatusCodesPage {
    private final Page page;
    private final String url = "https://the-internet.herokuapp.com/status_codes";

    public StatusCodesPage(Page page) {
        this.page = page;
    }

    public void open() {
        page.navigate(url);
    }

    public int navigateToStatus(int code) {
        // Czekamy na response, który zawiera w adresie "status_codes/{code}"
        Response response = page.waitForResponse(
                r -> r.url().contains("/status_codes/" + code),
                () -> page.click("a[href='status_codes/" + code + "']")
        );
        return response.status();
    }
}
