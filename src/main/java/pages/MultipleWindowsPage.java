package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class MultipleWindowsPage {
    private final Page page;
    private final Locator clickHereLink;
    private final Locator header;

    public MultipleWindowsPage(Page page) {
        this.page = page;
        this.clickHereLink = page.locator("text=Click Here");
        this.header = page.locator("h3");
    }

    public void navigate() {
        page.navigate("https://the-internet.herokuapp.com/windows");
    }

    public Page clickHereAndWaitForPopup() {
        return page.waitForPopup(() -> {
            clickHereLink.click();
        });
    }

    public String getHeaderText() {
        return header.innerText();
    }
}
