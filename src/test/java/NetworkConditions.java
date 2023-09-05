import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.ConnectionType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

public class NetworkConditions {
    ChromeDriver driver;
    DevTools devTools;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        devTools = driver.getDevTools();
    }

    @Test
    public void enableNetworkThrottling(){
        devTools.createSession();
        devTools.send(Network.enable(
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
        ));
        devTools.send(Network.emulateNetworkConditions(
                false,
                150,
                7500,
                4000,
                Optional.of(ConnectionType.CELLULAR3G)
        ));
        driver.get("https://victorschlindwein.com.br");
        System.out.println("Enable slow Network: " + driver.getTitle());
    }

    @Test
    public void dotNotEnableNetworkThrottling(){
        driver.get("https://victorschlindwein.com.br");
        System.out.println("No slow Network: " + driver.getTitle());
    }
}
