import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;


/**
 * @author Sasha on 7/14/2018.
 */
public class TestInitial {
    private WebDriver driver;

    @Test
    public void firstTest1() throws InterruptedException {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.navigate().to("https://mvnrepository.com");
        WebElement textField = driver.findElement(By.cssSelector(".textfield"));
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(textField));
        textField.isDisplayed();
        textField.sendKeys("junit 4");

        WebElement search = driver.findElement(By.className("button"));
        wait.until(ExpectedConditions.elementToBeClickable(search));
        search.click();

        List<WebElement> results = driver.findElements(By.className("im"));
        wait.until(ExpectedConditions.visibilityOfAllElements(results));
//        for (WebElement elem : results) {
//            System.out.println(elem.findElement(By.cssSelector(".im .im-title a:nth-child(2)")).getText());
////            assertThat(String.format("For element [%s] text is not matched to 'junit'", elem.findElement(By.cssSelector(".im .im-title a:nth-child(2)")).getText()), elem.findElement(By.cssSelector(".im .im-title a:nth-child(2)")).getText().equalsIgnoreCase("junit"));
//        }
        WebElement compare = results.get(1).findElement(By.cssSelector(".im .im-title a:nth-child(2)"));
        wait.until(ExpectedConditions.visibilityOf(compare));

        assertThat(String.format("First element title should be [junit] but actual result is [%s]"
                , compare.getText())
                , compare.getText().equalsIgnoreCase("junit"));

        Thread.sleep(3000);
    }

    @Test
    public void Test2() throws InterruptedException {
        FirefoxDriverManager.getInstance().setup();
        driver = new FirefoxDriver();
        driver.navigate().to("https://mvnrepository.com");
        MavenPage page = PageFactory.initElements(driver,MavenPage.class);
        page.inputSearchValue("hamcrest");
        Thread.sleep(3000);
    }

    @Test
    public void test3(){
        //TODO search for junit
//        FirefoxDriverManager.getInstance().setup();
//        driver = new FirefoxDriver();
//        driver.navigate().to("https://mvnrepository.com");
//        MavenPage page = PageFactory.initElements(driver,MavenPage.class);
//        page.inputSearchValue("junit");/


    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
