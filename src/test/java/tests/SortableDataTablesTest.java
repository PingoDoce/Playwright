package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Test;
import pages.SortableDataTablesPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortableDataTablesTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Playwright: Sprawdzanie sortowania tabeli po kliknięciu w nagłówek kolumny")
    public void testTableSorting() {
        SortableDataTablesPage pageObject = new SortableDataTablesPage(page);
        pageObject.open();
        logStep("Otwieramy stronę z tabelami");

        // Sortuj rosnąco po 1. kolumnie (Last Name)
        pageObject.clickColumnHeaderToSortDesc(1);
        String firstAsc = pageObject.getTableCellText(1, 1);
        logStep("Pierwsza komórka po sortowaniu rosnącym: " + firstAsc);
        assertTrue(firstAsc.toUpperCase().startsWith("B"), "Po sortowaniu rosnącym pierwszy wiersz powinien zaczynać się na 'B'");

        // Sortuj malejąco po 1. kolumnie
        pageObject.clickColumnHeaderToSortAsc(1);
        String firstDesc = pageObject.getTableCellText(1, 1);
        logStep("Pierwsza komórka po sortowaniu malejącym: " + firstDesc);
        assertTrue(firstDesc.toUpperCase().startsWith("S"), "Po sortowaniu malejącym pierwszy wiersz powinien zaczynać się na 'S'");
    }
}
