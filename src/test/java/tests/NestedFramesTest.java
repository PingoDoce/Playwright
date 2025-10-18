package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.NestedFramesPage;
import utils.BaseTest;

@Epic("The Internet")
@Feature("NestedFrames")
@Story("NestedFrames page test")
@Severity(SeverityLevel.NORMAL)
public class NestedFramesTest extends BaseTest {

    @Test
    @DisplayName("Open NestedFrames page")
    @Description("Just open the NestedFrames page to ensure it loads.")
    public void testOpenPage() {
        NestedFramesPage pageObject = new NestedFramesPage(page);
        logStep("Open NestedFrames page");
        pageObject.open();
    }
}
