package pageobject.base;



import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Base de Selenium, para poder enmascarar
 * la version de Selenium
 * @author eduardo.araya
 */
public class SeleniumBase {

    //atributos
    protected WebDriver driver;

    //Constructor Base
    public SeleniumBase(WebDriver driver){
        this.driver = driver;
    }

    //Envoltorios Wrapper
    public WebElement findElement(By locator){
        return driver.findElement(locator);
    }

    public List<WebElement> findElements (By locator){
        return driver.findElements(locator);
    }

    public String getText (By locator){
        return driver.findElement(locator).getText();
    }

    public void type(String inputText, By locator){
        driver.findElement(locator).sendKeys(inputText);
    }
    public void click(By locator){
        driver.findElement(locator).click();
    }

    public void closeDriver(){
        driver.close();
    }

    public Boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }


    public void goToUrl(String url){
        driver.get(url);
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void switchTo (int n) { driver.switchTo().frame(n); }

    public void clear (By locator){ findElement( locator).clear(); }
    public void sendKeysDerecha(By locator){ findElement(locator).sendKeys(Keys.ARROW_RIGHT); }
    public void sendKeysEnter (By locator){ findElement(locator).sendKeys(Keys.ENTER);}
    public void espera (int sleep) throws InterruptedException {Thread.sleep(sleep);

    }
    public  void switchToPestana(int p1, int p2) throws InterruptedException {
        driver.switchTo().defaultContent();
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles()); //array de cambio de pestana
        driver.switchTo().window(tabs2.get(p1));
        driver.close();
        driver.switchTo().window(tabs2.get(p2));
        espera(8000);
    }

    public void selectCantidad(By locator, String nroCantidad){
        Select cantidad = new Select(findElement(locator));
        cantidad.selectByVisibleText(nroCantidad);
    }
}