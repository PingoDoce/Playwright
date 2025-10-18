package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HorizontalSliderPage {
    private final Page page;
    private final Locator slider;
    private final Locator value;

    public HorizontalSliderPage(Page page) {
        this.page = page;
        this.slider = page.locator("input[type='range']");
        this.value = page.locator("#range");
    }

    public void setSliderTo(String targetValue) {
        double target = Double.parseDouble(targetValue);
        slider.click(); // ustaw fokus na suwaku

        // Bezpiecznik przed pętlą nieskończoną
        int guard = 100;

        double current = Double.parseDouble(value.innerText().trim());
        while (current < target && guard-- > 0) {
            slider.press("ArrowRight");
            current = Double.parseDouble(value.innerText().trim());
        }
        while (current > target && guard-- > 0) {
            slider.press("ArrowLeft");
            current = Double.parseDouble(value.innerText().trim());
        }
    }

    public String getSliderValue() {
        return value.innerText().trim();
    }
}
