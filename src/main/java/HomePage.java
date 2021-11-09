import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebElement btnCatalog;

    HomePage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://market.yandex.ru");
        btnCatalog = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='_2AMPZ _3CFK9 _2VvV8 _3WgR5']")));
    }

    public boolean pageLoaded() {
        if (driver.getTitle().equals("Яндекс.Маркет — покупки с быстрой доставкой"))
            return true;
        else return false;
    }

    public CatalogPage clickCatalogButton() {
        btnCatalog.click();
        return new CatalogPage(driver);
    }
}
