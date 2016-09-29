package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.net.URL;
import java.util.List;
import io.appium.java_client.AppiumDriver;

/**
 * Created by congan on 9/28/16.
 */
public class contactTest {
    private AppiumDriver driver;
    @Before
    public void setUp() throws Exception {
        //设置apk的路径
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "apps");
        File app = new File(appDir, "ContactManager.apk");

        /**
         * DesiredCapabilities 类负责启动服务端时的参数设置,它告诉appium server这样一些事情
         * 本次测试是
         * 启动浏览器还是启动移动设备？
         启动andorid还是启动ios？
         启动android时，app的package是什么？
         启动android时，app的activity是什么？https://testerhome.com/topics/1086
         */

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Emulator");

        //设置安卓系统版本
        capabilities.setCapability("platformVersion", "4.4.4");
        //设置apk路径
        capabilities.setCapability("app", app.getAbsolutePath());

        //设置app的主包名和主类名
        capabilities.setCapability("appPackage", "com.example.android.contactmanager");
        capabilities.setCapability("appActivity", ".ContactManager");

        //初始化
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @Test
    public void addContact(){
        WebElement el = driver.findElement(By.id("com.example.android.contactmanager:id/addContactButton"));
        el.click();
        List<WebElement> textFieldsList = driver.findElementsByClassName("android.widget.EditText");
        textFieldsList.get(0).sendKeys("Some Name");
        textFieldsList.get(2).sendKeys("Some@example.com");
        driver.swipe(100, 500, 100, 100, 2);
        driver.findElement(By.id("com.example.android.contactmanager:id/contactSaveButton")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

}
