package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class DragAndDropPage {
    private final Page page;
    private final Locator columnA;
    private final Locator columnB;

    public DragAndDropPage(Page page) {
        this.page = page;
        this.columnA = page.locator("#column-a");
        this.columnB = page.locator("#column-b");
    }

    @Step("Pobierz kolumnę A")
    public Locator getColumnA() {
        return columnA;
    }

    @Step("Pobierz kolumnę B")
    public Locator getColumnB() {
        return columnB;
    }

    @Step("Wykonaj drag & drop z A na B")
    public void dragAtoB() {
        columnA.dragTo(columnB);
    }

    @Step("Pobierz nagłówek z kolumny A")
    public String getHeaderOfColumnA() {
        return columnA.locator("header").innerText().trim();
    }

    @Step("Pobierz nagłówek z kolumny B")
    public String getHeaderOfColumnB() {
        return columnB.locator("header").innerText().trim();
    }
}
