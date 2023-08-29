import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class RelativeLocators {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @AfterMethod
    public void tearDown(){
       driver.quit();
    }

    @Test
    public void testRelativeLocator() {
        WebElement loginPanel = driver.findElement(By.className("orangehrm-demo-credentials"));
        List<WebElement> credentials = driver.findElements(with(
                        By.tagName("p"))
                .near(loginPanel));
        for (WebElement credential : credentials){
            System.out.println(credential.getText());
        }
    }

    @Test
    public void testListOfElements() {
        WebElement allSocialMedias = driver.findElement(By.className("orangehrm-login-footer-sm"));
        List<WebElement> socialMedias = driver.findElements(with(
                By.tagName("a"))
                .near(allSocialMedias));
        for (WebElement socialMedia : socialMedias){
            System.out.println(socialMedia.getAttribute("href"));
        }
    }
}