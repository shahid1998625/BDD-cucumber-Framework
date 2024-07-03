package runners;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
    public static WebDriver driver;

    public static void setUp() {
        driver = new ChromeDriver();
//        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
