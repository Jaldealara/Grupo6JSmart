package js_Grupo6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class jetSmart_Autos {
    WebDriver driver;
    jetSmart_Autos() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //System.setProperty("webdriver.opera.driver","C:\\Users\\Jeremy.Aldea\\Desktop\\operadriver_win64\\operadriver_win64\\operadriver.exe");
        //System.setProperty("webdriver.ie.driver","C:\\Users\\Jeremy.Aldea\\Desktop\\IEDriverServer_x64_3.150.1\\IEDriverServer.exe");
/*
        this.driver= new EdgeDriver();
        //FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("disable-gpu");
        options.addArguments("'--headless");
        options.addArguments("--hide-scrollbars");
        options.addArguments("--log-level=0");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("user-agent='+UserAgent(verify_ssl=False).random");
        options.addArguments("++v=90");
        options.addArguments("start-index");
        options.addArguments("window-size=1920,1080");
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("useAutomationExtension","disabled");

 */
        //options.addArguments("user-data-dir=C:\\Users\\Jeremy.Aldea\\Desktop\\tsoft docs\\user data");
        //options.addArguments("--disable-blink-features=AutomationControlled");

//this.driver= new OperaDriver();
      this.driver= new ChromeDriver(options);
//this.driver = new InternetExplorerDriver();

       // this.driver = new FirefoxDriver();
    }

    public void PopUp()throws  InterruptedException{

        // 1.- requerimientos iniciales para eliminar subscripiciones y popUps
        //
        //driver.navigate().to("https://jetsmart.com/cl/es/");
        driver.manage().window().maximize();
        driver.get("https://jetsmart.com/cl/es/");
        WebDriverWait Retardo = new WebDriverWait(driver, 20);
        Retardo.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@ng-model='returnCheckbox']")));
        driver.findElement(By.xpath("//div[@class='modal-header']/button")).click();
        Retardo.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='menu-close']"))).click();
        driver.findElement(By.xpath("//div[@class='menu-open']")).click();
        Retardo.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='menu-open']")));
        //Retardo.until(ExpectedConditions.elementToBeSelected(By.xpath("//input[@placeholder='Origen']")));


    }

    public String pruebasAutos (String LugarRecogida,String FechaRecogida, String horaR,String FechaDevolucion,String horaD) throws InterruptedException {

        PopUp();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//label[@for='sbCars']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[@id='typeahead-00F-6736-option-1']")).sendKeys(LugarRecogida);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //fecha
        driver.findElement(By.xpath("(//input[@class='ng-isolate-scope'])[1]")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.findElement(By.xpath("//*[@class='ui-datepicker-calendar']"));
    WebDriverWait wait = new WebDriverWait(driver,6);
    String [] fechaingreso = FechaRecogida.split("-");
    //driver.switchTo().frame(1);
    driver.findElement(By.xpath("//*[@id='checkInDate']")).click();
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    for (int i = 0; i <12 ; i++) {
        WebElement comp = driver.findElement(By.xpath("//*[@class='pika-select pika-select-month']"));
        //6.- se selecciona fecha de ida con Xpath
        if(fechaingreso[1].equals(comp.getText())){
            List<WebElement> dias = driver.findElements(By.xpath("//*[@class='pika-button pika-day']"));
            for (WebElement ele:dias){
                if (fechaingreso[0].equals(ele.getText())){
                    ele.click();
                    break;
                }
            }
            break;
        }
        else{
            //6.1.- si no coincide la fecha enviada por parametros, se clickea arrow next
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@class='pika-next']")).click();
        }
    }
    return "prueba exitosa";
    }

    public static void main(String[] args) throws InterruptedException {
    jetSmart_Autos A =new jetSmart_Autos();
        System.out.println(A.pruebasAutos("santiago","2-3-2021","15:20","4-3-2021","16:00"));

    }
}
