package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class InfiniteScrollPage {
    private final Page page;
    private final Locator paragraphs;

    public InfiniteScrollPage(Page page) {
        this.page = page;
        this.paragraphs = page.locator(".jscroll-added");
    }

    public void open() {
        page.navigate("https://the-internet.herokuapp.com/infinite_scroll");
    }

    public void scrollDownALot() {
        // duży „skok” kółkiem
        page.mouse().wheel(0, 30000);
    }

    public int countParagraphs() {
        return paragraphs.count();
    }

    /**
     * Scrolluj w pętli aż osiągniesz co najmniej minCount paragrafów
     * albo skończą się próby.
     */
    public void scrollUntilAtLeast(int minCount, int maxScrolls, int pauseMs) {
        int current = countParagraphs();
        int attempts = 0;

        while (current < minCount && attempts < maxScrolls) {
            scrollDownALot();
            // daj czas JS-owi na doładowanie
            page.waitForTimeout(pauseMs);

            int after = countParagraphs();
            if (after > current) {
                // coś się załadowało – resetuj próby i aktualizuj licznik
                current = after;
                attempts = 0;
            } else {
                // nic nie przybyło – ponów próbę
                attempts++;
            }
        }
    }
}
