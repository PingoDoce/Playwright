package pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

public class BrokenImagesPage {
    private final Page page;
    private final Locator imageElements;

    public BrokenImagesPage(Page page) {
        this.page = page;
        this.imageElements = page.locator("img");
    }

    @Step("Pobierz wszystkie obrazki z DOM")
    public Locator getAllImages() {
        return imageElements;
    }

    @Step("Sprawdź obrazki, które mają naturalWidth == 0 (niewidoczne)")
    public List<Locator> getBrokenImagesByNaturalWidth() {
        List<Locator> broken = new ArrayList<>();
        int count = imageElements.count();

        for (int i = 0; i < count; i++) {
            Locator img = imageElements.nth(i);
            ElementHandle handle = img.elementHandle();
            if (handle == null) continue;

            Number width = (Number) handle.evaluate("el => el.naturalWidth");
            if (width.intValue() == 0) {
                broken.add(img);
            }
            handle.dispose(); // sprzątanie
        }
        return broken;
    }
}
