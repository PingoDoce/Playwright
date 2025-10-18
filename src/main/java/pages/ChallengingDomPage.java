package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class ChallengingDomPage {
    private final Page page;

    private final Locator headerButtons;
    private final Locator tableHeaders;
    private final Locator tableRows;

    public ChallengingDomPage(Page page) {
        this.page = page;
        this.headerButtons = page.locator(".button");
        this.tableHeaders = page.locator("table thead tr th");
        this.tableRows = page.locator("table tbody tr");
    }

    @Step("Pobierz wszystkie przyciski u góry strony")
    public Locator getHeaderButtons() {
        return headerButtons;
    }

    @Step("Kliknij przycisk o indeksie {index}")
    public void clickHeaderButton(int index) {
        headerButtons.nth(index).click();
    }

    @Step("Pobierz wszystkie nagłówki tabeli")
    public Locator getTableHeaders() {
        return tableHeaders;
    }

    @Step("Pobierz wszystkie wiersze tabeli")
    public Locator getTableRows() {
        return tableRows;
    }

    @Step("Pobierz komórkę z wiersza {row} i kolumny {col}")
    public String getCellText(int row, int col) {
        return tableRows.nth(row).locator("td").nth(col).innerText();
    }

    @Step("Pobierz przyciski Edit z każdego wiersza")
    public Locator getEditButtons() {
        return page.locator("table tbody tr td a[href*='edit']");
    }

    @Step("Pobierz przyciski Delete z każdego wiersza")
    public Locator getDeleteButtons() {
        return page.locator("table tbody tr td a[href*='delete']");
    }
}
