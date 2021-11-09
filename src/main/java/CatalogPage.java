import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CatalogPage {

    private WebDriver driver;
    private WebElement btnComputers;

    CatalogPage(WebDriver driver) {
        this.driver = driver;
        btnComputers = driver.findElement(By.xpath("//span[text()='Компьютеры']"));
    }

    public ComputersPage clickComputersButton() {
        btnComputers.click();
        return new ComputersPage(driver);
    }
}
