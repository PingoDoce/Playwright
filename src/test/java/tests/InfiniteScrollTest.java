package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.InfiniteScrollPage;
import utils.BaseTest;

public class InfiniteScrollTest extends BaseTest {

    @Test
    public void testLoadMoreParagraphsOnScroll() {
        // trochę wyższy timeout na akcje w tym teście
        page.setDefaultTimeout(20_000);

        InfiniteScrollPage p = new InfiniteScrollPage(page);
        p.open();

        int initial = p.countParagraphs();

        // Scrolluj aż będzie min. initial + 3 (albo ogólnie 4 jeśli woleisz stałą)
        int target = Math.max(initial + 3, 4);
        p.scrollUntilAtLeast(target, /*maxScrolls*/ 10, /*pauseMs*/ 600);

        int finalCount = p.countParagraphs();

        Assertions.assertTrue(finalCount > 0, "Powinny być widoczne paragrafy po załadowaniu strony");
        Assertions.assertTrue(finalCount >= target,
                "Oczekiwano co najmniej " + target + " paragrafów, jest: " + finalCount);
    }
}
