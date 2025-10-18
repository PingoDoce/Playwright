package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.options.Geolocation;
import org.junit.jupiter.api.Test;
import pages.GeolocationPage;
import utils.BaseTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeolocationTest extends BaseTest {

    @Test
    public void testMockedGeolocation() {
        // Utwórz nowy kontekst z mockowaną geolokalizacją i zgodą na jej użycie
        context.close();
        context = browser.newContext(
                new Browser.NewContextOptions()
                        .setGeolocation(new Geolocation(50.06143, 19.93658))
                        .setPermissions(Arrays.asList("geolocation"))
        );
        page = context.newPage();

        page.navigate("https://the-internet.herokuapp.com/geolocation");

        GeolocationPage geoPage = new GeolocationPage(page);
        geoPage.clickWhereAmI();

        // Poczekaj aż współrzędne zostaną wyświetlone
        page.waitForFunction("() => document.querySelector('#lat-value').textContent.trim() !== '' && document.querySelector('#long-value').textContent.trim() !== ''");

        String lat = geoPage.getLatitude();
        String lon = geoPage.getLongitude();

        assertTrue(lat.contains("50"));
        assertTrue(lon.contains("19"));
    }
}
