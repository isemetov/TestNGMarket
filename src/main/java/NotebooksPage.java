import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class NotebooksPage {

    private WebDriver driver;
    private WebElement btnCompany;
    private WebElement priceFrom;
    private WebElement priceTo;

    NotebooksPage(WebDriver driver) {
        this.driver = driver;
        priceFrom = driver.findElement(By.name("Цена от"));
        priceTo = driver.findElement(By.name("Цена до"));
    }

    public boolean pageLoaded() {
        if (driver.getTitle().equals("Ноутбуки — купить на Яндекс.Маркете"))
            return true;
        else return false;
    }

    public void selectCompany(String company) {
        btnCompany = driver.findElement(By.xpath("//span[text()='" + company + "']"));
        btnCompany.click();
    }

    public void setPrice() {
        priceFrom.sendKeys("25000");
        priceTo.sendKeys("30000");
    }

    public ArrayList<Integer> getResultPrices() {
        List<WebElement> prices = driver.findElements(By.xpath("//div[@class='_3NaXx _33ZFz _2m5MZ']/span/span[1]"));
        ArrayList<Integer> resultPrices = new ArrayList<Integer>();
        for(WebElement el:prices) {
            resultPrices.add(Integer.parseInt(el.getText().replaceAll("\\s+","")));
        }
        return  resultPrices;
    }

    public ArrayList<String> getResultDescriptions() {
        List<WebElement> descriptions = driver.findElements(By.xpath("//h3[@class='_2UHry _2vVOc']/a/span"));
        ArrayList<String> resultDescriptions = new ArrayList<String>();
        for(WebElement el:descriptions) {
            //resultCompanies.add(el.getText().split(" ")[2]); как оказалось не всегда работает
            resultDescriptions.add(el.getText());
        }
        return resultDescriptions;
    }



}
