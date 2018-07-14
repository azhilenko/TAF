import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.FindBy;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Sasha on 7/14/2018.
 */
public class MavenPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public MavenPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);

    }

    @FindBy(css = ".textfield")
    private WebElement textField;

    @FindBy(className = "button")
    private WebElement search;

    public void inputSearchValue(String value) {
        wait.until(ExpectedConditions.visibilityOf(textField));
        textField.sendKeys(value);
        wait.until(ExpectedConditions.elementToBeClickable(search));
        search.click();
    }
}
