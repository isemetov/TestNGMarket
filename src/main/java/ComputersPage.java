import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ComputersPage {

    private WebDriver driver;
    private WebElement btnNotebooks;

    ComputersPage(WebDriver driver) {
        this.driver = driver;
        btnNotebooks = driver.findElement(By.xpath("//a[text()='Ноутбуки']"));
    }

    public boolean pageLoaded() {
        if (driver.getTitle().equals("Компьютерная техника — купить на Яндекс.Маркете"))
            return true;
        else return false;
    }

    public NotebooksPage clickNotebookButton() {
        btnNotebooks.click();
        return new NotebooksPage(driver);
    }
}
