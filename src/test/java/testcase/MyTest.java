package testcase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;

public class MyTest {
    private WebDriver driver;
    String appURL = "http://google.com";
    @BeforeTest
    @Parameters("browser")
    public  void beforeTest(String browser)
    {
        if(browser.equalsIgnoreCase("Firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if(browser.equalsIgnoreCase("Chrome")){
            WebDriverManager.chromedriver().version("2.40").setup();
            driver = new ChromeDriver();
        }
    }
    @AfterTest
    public void afterTest()
    {
        driver.quit();
    }
    @Test
    public void testFirst()
    {
        driver.get(appURL);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
       driver.navigate().to("https://facebook.com");
        System.out.println(driver.getTitle());
        driver.switchTo().window(tabs.get(0));
        System.out.println(driver.getTitle());
        driver.switchTo().window(tabs.get(1));
        driver.close();
        driver.switchTo().window(tabs.get(0));
     WebElement element = driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='1px solid red'", element);
        element.sendKeys("Testing testing");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {


        }

    }
}
