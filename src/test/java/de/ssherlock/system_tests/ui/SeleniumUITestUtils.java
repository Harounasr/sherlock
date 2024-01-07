package de.ssherlock.system_tests.ui;

import de.ssherlock.control.notification.Notification;
import de.ssherlock.control.notification.NotificationType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

/**
 * Utility class for selenium ui tests.
 *
 * @author Victor Vollmann
 */
public final class SeleniumUITestUtils {

    /**
     * Username of an administrator in the system.
     */
    public static final String ADMIN_USERNAME = "user";

    /**
     * Password of the administrator of the system.
     */
    public static final String ADMIN_PASSWORD = "lennyistdoof";

    /**
     * This systems' base location.
     * If running a single test and manually starting tomcat you may want
     * to change this to /ssherlock_war_exploded/.
     */
    public static final String BASE_URL = "http://localhost:8080/ssherlock/";

    /**
     * Private constructor for utility class.
     */
    private SeleniumUITestUtils() {

    }

    /**
     * Navigates to the provided location.
     *
     * @param webDriver The web driver.
     * @param location The location.
     */
    public static void navigateTo(WebDriver webDriver, String location) {
        webDriver.get(BASE_URL + location);
    }

    /**
     * Attempts to log in with the given credentials.
     *
     * @param webDriver The web driver.
     * @param username The username.
     * @param password The password.
     */
    public static void tryLogin(WebDriver webDriver, WebDriverWait wait, String username, String password) {
        navigateTo(webDriver, "view/public/login.xhtml");
        webDriver.findElement(By.id("loginForm:username")).sendKeys(username);
        webDriver.findElement(By.id("loginForm:password")).sendKeys(password);
        clickOnElementWithId(wait, "loginForm:login");
    }

    /**
     * Checks the current screen for a certain Notification.
     *
     * @param webDriver The web driver.
     * @param notification The expected notification.
     */
    public static void checkNotification(WebDriver webDriver, Notification notification) {
        String typeClassName = notification.type() == NotificationType.ERROR ? ".notification-error" : ".notification-success";
        WebElement element = webDriver.findElement(By.cssSelector(".popup-notifications, " + typeClassName + " > td"));
        assertTrue(element.isDisplayed());
        assertTrue(element.getText().contains(notification.text()));
    }

    /**
     * Clicks on the element with the specified id.
     *
     * @param wait The web driver wait.
     * @param id The css id.
     */
    public static void clickOnElementWithId(WebDriverWait wait, String id) {
        WebElement element = wait.until(elementToBeClickable(By.id(id)));
        element.click();
    }

}
