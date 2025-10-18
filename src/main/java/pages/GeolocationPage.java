package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class GeolocationPage {

    private final Page page;

    private final Locator buttonLocator;
    private final Locator latLocator;
    private final Locator lonLocator;

    public GeolocationPage(Page page) {
        this.page = page;
        this.buttonLocator = page.locator("button");
        this.latLocator = page.locator("#lat-value");
        this.lonLocator = page.locator("#long-value");
    }

    public void clickWhereAmI() {
        buttonLocator.click();
    }

    public String getLatitude() {
        return latLocator.innerText().trim();
    }

    public String getLongitude() {
        return lonLocator.innerText().trim();
    }
}
