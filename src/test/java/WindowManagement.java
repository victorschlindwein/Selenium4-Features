import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class WindowManagement {
    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://practice.automationtesting.in/");
        System.out.println("Title: " + driver.getTitle());
    }

    @Test
    public void testNewWindowTab(){
        WebDriver newWindow = driver.switchTo().newWindow(WindowType.WINDOW);
        newWindow.get("https://practice.automationtesting.in/shop/");
        System.out.println("Title: " + driver.getTitle());
    }

    @Test
    public void testWorkingInBothWindowTabs(){
        //Auto open e switch to new window or tab
        driver.switchTo().newWindow(WindowType.TAB)
                .get("https://practice.automationtesting.in/my-account/");
        System.out.println("Title: " + driver.getTitle());

        //Work in the new window/tab
        driver.findElement(By.id("reg_email")).sendKeys("teste_231rjzdas@email.com");
        driver.findElement(By.id("reg_password")).sendKeys("C@valo123$()12");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(By.name("register")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        //Get window ID handles
        Set<String> allWindowTabs = driver.getWindowHandles();
        Iterator<String> iterate = allWindowTabs.iterator();
        String mainFirstWindow = iterate.next();

        //Switch back to main window/tab
        driver.switchTo().window(mainFirstWindow);
        driver.findElement(By.id("menu-item-50")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        System.out.println("Title: " + driver.getTitle());
    }
}
