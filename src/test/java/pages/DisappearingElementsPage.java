package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class DisappearingElementsPage {
    private final Page page;
    private final Locator navLinks;

    public DisappearingElementsPage(Page page) {
        this.page = page;
        this.navLinks = page.locator("ul li a");
    }

    @Step("Pobierz wszystkie linki z menu")
    public Locator getNavigationLinks() {
        return navLinks;
    }

    @Step("Sprawdź, czy istnieje link o nazwie: {linkText}")
    public boolean isLinkPresent(String linkText) {
        int count = navLinks.count();
        for (int i = 0; i < count; i++) {
            String text = navLinks.nth(i).innerText().trim();
            if (text.equalsIgnoreCase(linkText)) {
                return true;
            }
        }
        return false;
    }

    @Step("Kliknij link o nazwie: {linkText}")
    public void clickLink(String linkText) {
        int count = navLinks.count();
        for (int i = 0; i < count; i++) {
            Locator link = navLinks.nth(i);
            String text = link.innerText().trim();
            if (text.equalsIgnoreCase(linkText)) {
                link.click();
                return;
            }
        }
        throw new IllegalStateException("Nie znaleziono linku: " + linkText);
    }
}
