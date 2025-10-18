package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SortableDataTablesPage {

    private final Page page;
    private final Locator table;

    public SortableDataTablesPage(Page page) {
        this.page = page;
        this.table = page.locator("#table1");
    }

    public void open() {
        page.navigate("https://the-internet.herokuapp.com/tables");
    }

    // Pobierz tekst komórki (1-based index jak w wersji Selenium)
    public String getTableCellText(int row, int col) {
        return table.locator("tbody tr:nth-child(" + row + ") td:nth-child(" + col + ")")
                .innerText()
                .trim();
    }

    // Kliknij nagłówek kolumny, aby posortować (1-based index)
    public void clickColumnHeaderToSortDesc(int col) {
        table.locator("thead th:nth-child(" + col + ")").click();

        // Czekaj, aż zawartość tabeli się zmieni (np. pierwszy wiersz zaczyna się na 'B' po sortowaniu rosnącym)
        page.waitForFunction(
                "document.querySelector('#table1 tbody tr:nth-child(1) td:nth-child(1)').innerText[0] === 'B'"
        );
    }

    // Kliknij nagłówek kolumny, aby posortować (1-based index)
    public void clickColumnHeaderToSortAsc(int col) {
        table.locator("thead th:nth-child(" + col + ")").click();

        // Czekaj, aż zawartość tabeli się zmieni (np. pierwszy wiersz zaczyna się na 'B' po sortowaniu rosnącym)
        page.waitForFunction(
                "document.querySelector('#table1 tbody tr:nth-child(1) td:nth-child(1)').innerText[0] === 'S'"
        );
    }

    // Pobierz tekst nagłówka (1-based index)
    public String getTableHeaderText(int col) {
        return table.locator("thead th:nth-child(" + col + ")").innerText().trim();
    }
}
