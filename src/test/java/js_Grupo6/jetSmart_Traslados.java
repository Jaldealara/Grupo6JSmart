package js_Grupo6;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class jetSmart_Traslados {
    WebDriver driver;

    jetSmart_Traslados(){
        WebDriverManager.chromedriver().setup();
        this.driver= new ChromeDriver();
    }

    public void PopUp()throws  InterruptedException{

        // 1.- requerimientos iniciales para eliminar subscripiciones y popUps
        driver.manage().window().maximize();
        driver.get("https://jetsmart.com/cl/es/");
        WebDriverWait Retardo = new WebDriverWait(driver, 8);
        Thread.sleep(2000);
        Retardo.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='close modal-close']/span[@aria-hidden='true']")));
        driver.findElement(By.xpath("//div[@class='modal-header']/button")).click();
        Retardo.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='menu-close']"))).click();
        driver.findElement(By.xpath("//div[@class='menu-open']")).click();
        Retardo.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='menu-open']")));
        Thread.sleep(2000);

    }
    public void LugarOrigen (String Origen) throws InterruptedException {
        //2.- se selecciona lugar de origen
        driver.switchTo().frame(2);
        WebDriverWait Retardo = new WebDriverWait(driver, 5);
        driver.findElement(By.xpath("//*[@id='input-pickup']")).sendKeys(Origen);
        Retardo.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='item-1-0']")));
        driver.findElement(By.xpath("//*[@id='item-1-0']")).click();



    }
    public void LugarDestino (String Destino) throws InterruptedException{
        //3.- se selecciona lugar de destino
        WebDriverWait Retardo = new WebDriverWait(driver, 5);
        Retardo.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@ng-if='key === 'external_loc'']")));
        driver.findElement(By.xpath("//*[@id='input-dropoff']")).sendKeys(Destino);
        Retardo.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='item-0-0']"))); //
        driver.findElement(By.xpath("//*[@id='item-0-0']")).click();
    }

    public void FormularioInicial(String Origen,String Destino,String fechaIda) throws InterruptedException {

        driver.findElement(By.xpath("//*[@class='jsh-icon jsh-van']")).click();//
        WebDriverWait Retardo = new WebDriverWait(driver, 12);//
        LugarOrigen(Origen);
        LugarDestino(Destino);
        //4.-Seleccionar solo ida

        driver.findElement(By.xpath("(//span[contains(text(),'Ida')])[1]")).click();//
        //5.- se toman fechas//
        WebElement calendarioIda = driver.findElement(By.xpath("//input[@id='input-pickup-date']"));//
        String[] fecha = fechaIda.split("-");
        int dia = Integer.parseInt(fecha[0]);
        int mes = Integer.parseInt(fecha[1])-1;
        int anio = Integer.parseInt(fecha[2]);
        //6.-dia del anio actual
        Calendar c = Calendar.getInstance();
        int diaDelAnio = c.get(Calendar.DAY_OF_YEAR); //123
        //7.-dia de la fecha pasada por parametros
        Calendar c1 = new GregorianCalendar(anio, mes, dia);   //obtenemos dia de la fecha otorgada
        int diaFechaParametro = c1.get(Calendar.DAY_OF_YEAR);

        int diferencia = diaFechaParametro - diaDelAnio;
        int numeroClicks = 1;
        //8.- cantidad de desplazamiento de calendario
        for (int i = 0; i < diferencia; i++) {
            calendarioIda.sendKeys(Keys.ARROW_RIGHT);
            if (numeroClicks == diferencia) {
                calendarioIda.sendKeys(Keys.ENTER);
            } else {
                numeroClicks++;
            }
        }

        //9.-hora de lugar ida
        WebElement hora = driver.findElement(By.xpath("//input[@id='ct-time-picker-pick-up-input']"));

        for (int i = 0; i < 3; i++) {
            hora.sendKeys(Keys.ARROW_DOWN);
        }
        hora.sendKeys(Keys.ENTER);

        driver.findElement(By.xpath("//*[@id='passenger-number-input']")).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);//desplegable pasajero
        driver.findElement(By.xpath("//*[@class='ct-btn ct-btn-p gt-is-valid']")).click();  //click boton buscar

        //10.-cambio de pestaña
        driver.switchTo().defaultContent();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        driver.close();
        driver.switchTo().window(tabs.get(1));
        Retardo.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='ct-btn ct-btn-p '])[1]")));
    }



    public String JST010 (String Origen,
                          String Destino,
                          String fechaIda) throws InterruptedException {
        String resultado = "";
        PopUp();
        FormularioInicial(Origen,Destino,fechaIda);
        //11.-busqueda de traslado muestra resultados
        String paginaResultado = driver.findElement(By.xpath("//h3[@class='gtg-padding gtg-strong']")).getText();
        if (paginaResultado.equals("Opciones de traslado disponibles para este viaje")){
            resultado = "Busqueda de traslado exitosa";
        }
        driver.close();
        return resultado;
    }


    public String JST011 (String Origen, String Destino, String fechaIda) throws InterruptedException {

        String resultado = "";
        WebDriverWait Retardo = new WebDriverWait(driver, 8);
        PopUp();
        FormularioInicial(Origen,Destino,fechaIda);
        //12.-clickeamos popUp de acuerdo
        Retardo.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@aria-label='De acuerdo.'])[1]")));
        driver.findElement(By.xpath("(//*[@aria-label='De acuerdo.'])[1]")).click();

        //13.-clickeamos cambio de moneda
        Retardo.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='currency-code']")));
        driver.findElement(By.xpath("//*[@id='currency-code']")).click();

        //14.-se selecciona moneda USD
        Select moneda = new Select(driver.findElement(By.xpath("//*[@id='currency-code']")));
        moneda.selectByValue("USD");
        //15.-click boton busqueda
        driver.findElement(By.xpath("//*[@id='ct-button-search']/button")).click();

        //16.-verificamos precios con nueva moneda
        Retardo.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='ct-clearfix ct-hide-mobile'])[1]")));
        String Precio = driver.findElement(By.xpath("(//*[@class='ct-clearfix ct-hide-mobile'])[1]")).getText();
        if (Precio.contains("USD")){
            resultado="Cambio de moneda exitosa";
        }
        driver.close();
        return resultado;
    }

    public String JST012 (String Origen,
                          String Destino,
                          String fechaIda,
                          String nombre,
                          String mail,
                          String nroTel,
                          String nroVuelo) throws InterruptedException {
        String resultado = "";
        PopUp();
        FormularioInicial(Origen,Destino,fechaIda);

        //17.-se setea parametrso de formulario

        driver.findElement(By.xpath("(//*[@class='ct-btn ct-btn-p '])[1]")).click();
        driver.findElement(By.xpath("//*[@id='gt-customer-name']")).sendKeys(nombre);
        driver.findElement(By.xpath("//*[@id='gt-customer-email']")).sendKeys(mail);
        driver.findElement(By.xpath("//*[@id='gt-customer-confirm-email']")).sendKeys(mail);
        driver.findElement(By.xpath("//*[@id='gt-customer-mobile-number']")).sendKeys(nroTel);
        driver.findElement(By.xpath("(//*[@class='ctc-switch__label'])[1]")).click();
        driver.findElement(By.xpath("//*[@id='gt-pickup-flight-number']")).sendKeys(nroVuelo);
        driver.findElement(By.xpath("//*[@class='ctc-button ctc-button--full ctc-button--primary']")).click();

        //18.-se toma dato de error de codigo de vuelo
        String mensajeError=driver.findElement(By.xpath("(//*[@class='ctc-form__hint'])[7]")).getText();
        if (mensajeError .equals("Introduce una entrada válida. p. ej. AB123, CD1234")){
            resultado=mensajeError;
        }
        driver.close();
        return resultado;
    }
}