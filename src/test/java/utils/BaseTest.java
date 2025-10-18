package utils;

import com.microsoft.playwright.*;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeAll
    void beforeAll() {
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        String[] wh = System.getProperty("viewport", "1920,1080").split(",");
        int width = Integer.parseInt(wh[0]);
        int height = Integer.parseInt(wh[1]);

        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(headless)
        );

        // baseline bez trace/video (włączaj tylko do debugowania)
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(width, height)
                .setScreenSize(width, height)
        );
    }


    @AfterAll
    void afterAll() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    @BeforeEach
    void beforeEach() {
        page = context.newPage();
    }

    @AfterEach
    void afterEach() {
        if (page != null) {
            page.close();
        }
    }

    @Attachment(value = "{screenshotName}", type = "image/png")
    public byte[] captureScreenshot(String screenshotName) {
        return page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
    }

    @Step("{stepDescription}")
    public void logStep(String stepDescription) {
        Allure.step(stepDescription);
    }
}
