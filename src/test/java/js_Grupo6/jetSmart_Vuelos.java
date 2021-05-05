package js_Grupo6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class jetSmart_Vuelos {
     WebDriver driver;  //se inicializa variable driver

    jetSmart_Vuelos(){
        WebDriverManager.chromedriver().setup();
        this.driver= new ChromeDriver();
    }
    public  void PopUp()throws  InterruptedException{

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
    public void LugarOrigenYDestino (String ciudadOrigen,String ciudadDestino){
        //2.- se selecciona lugar de origen

        WebDriverWait Retardo = new WebDriverWait(driver, 5);
        driver.findElement(By.xpath("//input[@placeholder='Origen']")).click();
        Retardo.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='dg-city-selector-list ps ps--active-y']")));
        driver.findElement(By.xpath("//*[@ref='citySelector']/li[contains(text(),'"+ciudadOrigen+"')]")).click();
        Retardo.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='dg-city-selector-list ps ps--active-y']")));

        //3.- se selecciona lugar de destino

        driver.findElement(By.xpath(" //*[@ref='citySelector']/li[contains(text(),'"+ciudadDestino+"')]")).click();

    }
    public void FechasIda(String fechaIda) throws InterruptedException {

        String corteFecha []=fechaIda.split(" "); //tomamos fecha ida (mes)
        WebDriverWait Retardo = new WebDriverWait(driver, 5);

        //4.- se toma mes actual de calendario

        Retardo.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='dg-calendar-header']")));
        WebElement comp = driver.findElement(By.xpath("(//*[@class='cur-month'])[1]"));

        for (int i = 0; i <12 ; i++) {
            //5.- se selecciona fecha de ida con Xpath
            if(corteFecha[0].equals(comp.getText())){
                driver.findElement(By.xpath("(//div[@class='dayContainer'] //span[@aria-label='"+fechaIda+"'])[1]")).click();
                break;
            }
            else{
                //6.- si no coincide la fecha enviada por parametros, se clickea arrow next
                Thread.sleep(2000);  //solo para ver ejecucion
                driver.findElement(By.xpath("(//span[@class='flatpickr-next-month'])[1]")).click();
            }
        }
    }

    public void FechaVuelta(String fechaVuelta) throws InterruptedException {
        //7.- se toma fecha vuelta
        String  [] corteFechaVuelta = fechaVuelta.split(" "); //tomamos fecha vuelta (mes)
        WebDriverWait Retardo = new WebDriverWait(driver, 8);

        Retardo.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='dg-calendar-header']")));

        //8.- se toma el texto del mes actual en fecha de vuelta

        WebElement comp2= driver.findElement(By.xpath("(//span[@class='cur-month'])[3]"));
        for (int i = 0; i <12 ; i++) {
            Thread.sleep(600);

            //9.-si el mes ingresado es igual al mes del calendario actual, busca el dia ingresado

            if (corteFechaVuelta[0].equals(comp2.getText())) {

                driver.findElement(By.xpath("(//div[@class='dayContainer']//span[@aria-label='" + fechaVuelta + "'])[1]")).click();
                break;
            } else {
                //9.1.- si no encuentra el mes, clickea arrow next

                driver.findElement(By.xpath("(//span[@class='flatpickr-next-month'])[2]")).click();
            }
        }
    }

    public String casoVuelos1()throws InterruptedException{

        PopUp();
        driver.findElement(By.xpath("//input[@ref='paxSummary']")).click(); //boton seleccion pasajeros
        WebDriverWait Retardo = new WebDriverWait(driver, 5);

        //10.-se define la ubicacion del boton "+" para cada pasajero

        WebElement infantes = driver.findElement(By.xpath("//pax-amount[@ref='infants']/div[contains(text(),'+')]"));
        WebElement adultos  = driver.findElement(By.xpath("//pax-amount[@ref='adults']/div[contains(text(),'+')]"));
        WebElement ninos    = driver.findElement(By.xpath("//pax-amount[@ref='children']/div[contains(text(),'+')]"));

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        Retardo.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='dg-quantity-dropdown open']")));
        for (int i = 1; i <8 ; i++) {
            Thread.sleep(300);  //solo para visibilizar aumento de pasajeros
            adultos.click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
            //11.1.-sumar infantes

        for (int i = 0; i <8; i++) {
            infantes.click();
            Thread.sleep(300); //solo para visibilizar aumento de pasajeros
        }
            //11.2.-sumar ninos

        ninos.click();
        driver.findElement(By.xpath("//pax-amount[@ref='infants']/div[contains(text(),'-')]")).click();
        try {
            //condicion si existen mas de 10 pasajeros intenta clickear
            infantes.click();
        }
        catch(Exception ignored){}
        //12.- se obtiene texto de cuadro debe llenar formulario

        String result=driver.findElement(By.xpath("//div[@class='dg-pax-quantity-message']")).getText();
        driver.close();
        return result;
    }

    public String CasoVuelo2( String fechaIda,String fechaVuelta,String ciudadOrigen,String ciudadDestino) throws InterruptedException {

        WebDriverWait Retardo = new WebDriverWait(driver, 5);
        //13.-se inician parametros iniciales

        PopUp();
        LugarOrigenYDestino(ciudadOrigen,ciudadDestino);
        FechasIda(fechaIda);
        FechaVuelta(fechaVuelta);

        //14.-se espera que desaparezca calendario para hacer click de busqueda

        Retardo.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//*[@class='flatpickr-days'])[2]")));
        driver.findElement(By.xpath("//button[@id='rt_button']")).click(); //busca
        String textResult=driver.findElement(By.xpath("//h3[contains(text(),'Detalles de tu Compra:')]")).getText();
        String result="";
            if(textResult.equals("Detalles de tu Compra:")){
                result="se encontraron vuelos, Test completo";
            }
            driver.close();
            return result;
        }

        public String CasoVuelo3 (String fechaIda,String ciudadOrigen,String ciudadDestino) throws InterruptedException {

        WebDriverWait Retardo = new WebDriverWait(driver, 7);
        String resultado="";

        PopUp();
        LugarOrigenYDestino(ciudadOrigen,ciudadDestino);
        FechasIda(fechaIda);

        driver.findElement(By.xpath("//label[@for='sbOneWayJourney']")).click();

        //15.-se clickea cantidad de pasajeros y se suman pasajeros

        driver.findElement(By.xpath("//input[@ref='paxSummary']")).click();
        Thread.sleep(500);
        WebElement adultos  = driver.findElement(By.xpath("//pax-amount[@ref='adults']/div[contains(text(),'+')]"));
        WebElement ninos    = driver.findElement(By.xpath("//pax-amount[@ref='children']/div[contains(text(),'+')]"));
        Retardo.until(ExpectedConditions.elementToBeClickable(By.xpath("//pax-amount[@ref='adults']/div[contains(text(),'+')]")));
        adultos.click();
        ninos.click();
        driver.findElement(By.xpath("//button[@class='dg-button dg-pax-btn']")).click();

        //16.- se espera hasta que desaparezca el campo añadir pasajeros

        Retardo.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class='dg-quantity-dropdown open']")));

        //17.-busqueda de vuelo

        driver.findElement(By.xpath("//button[@id='rt_button']")).click();
        Retardo.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='smart-fee nowrap big ']")));

        //18.-seleccion de vuelo y seleccion de paquete de maleta full

        driver.findElement(By.xpath("//*[@class='smart-fee nowrap big ']")).click();
        Retardo.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='bundle-button '])[7]"))).click();

        //19.-se espera pagina resumen y clickea continuar

        Retardo.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//button[@class='bundle-button '])[7]")));
        Retardo.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='rounded-primary-btn booking'])[1]"))).click();


        //20.-espera hasta que aparece titulo detalle de pasajero

        Retardo.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='main-title']")));

        //21.-  completa formulario

        WebElement pasajero1N= driver.findElement(By.xpath("//*[@id='Pfn0']"));
        WebElement pasajero1A= driver.findElement(By.xpath("//input[@id='Pln0']"));
        pasajero1N.sendKeys("frank");
        pasajero1A.sendKeys("luzon Reyes");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        WebElement pasajero2N= driver.findElement(By.xpath("//*[@id='Pfn1']"));
        WebElement pasajero2A= driver.findElement(By.xpath("//input[@id='Pln1']"));

        pasajero2N.sendKeys("Mario");
        pasajero2A.sendKeys("Escudero");

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        WebElement pasajero3N= driver.findElement(By.xpath("//*[@id='Pfn2']"));
        WebElement pasajero3A= driver.findElement(By.xpath("//input[@id='Pln2']"));

        pasajero3N.sendKeys("Yair");
        pasajero3A.sendKeys("Vega");

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Select sD = new Select(driver.findElement(By.id("date_of_birth_day_2")));
        sD.selectByValue("6"); //seleccionamos una opcion por el valor

        Select sM = new Select(driver.findElement(By.id("date_of_birth_month_2")));
        sM.selectByValue("6"); //seleccionamos una opcion por el valor

        Select sA = new Select(driver.findElement(By.id("date_of_birth_year_2")));
        sA.selectByValue("2015"); //seleccionamos una opcion por el valor
        driver.findElement(By.xpath("//*[@class='rounded-primary-btn booking']")).click();
        String textoComprobacion= driver.findElement(By.xpath("(//*[@class='main-title'])[2]")).getText();

        if(textoComprobacion.equals("Equipaje")){
            resultado="comprobacion exitosa, llego hasta equipaje";
        }
        driver.close();
        return resultado;
        }
}
