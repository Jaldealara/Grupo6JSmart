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
import java.util.List;
import java.util.concurrent.TimeUnit;

public class jetSmart_Hoteles {
    WebDriver driver;

    jetSmart_Hoteles(){
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

    public void LugarDeDestino(String lugar_destino) throws InterruptedException {
        //2.-se carga lugar de destino
        WebDriverWait wait = new WebDriverWait(driver,8);
        driver.switchTo().frame(0);
        WebElement entradaOrigen = driver.findElement(By.xpath("//*[@id='b_destination']"));
        entradaOrigen.clear();
        entradaOrigen.sendKeys(lugar_destino);
        Thread.sleep(2000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content']")));
        driver.findElement(By.xpath("//*[@class='autocomplete_city autocomplete ui-menu-item']")).click();
        Thread.sleep(1000);
    }

    public void FechaDeIngreso(String fecha_ingreso) throws InterruptedException {
        //3.- se carga fecha de ingreso
        String [] fechaingreso = fecha_ingreso.split("-");
        driver.findElement(By.xpath("//*[@id='checkInDate']")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        //4.-recorre los meses y compara mes ingresado con mes de calendario
        for (int i = 0; i <12 ; i++) {
            WebElement comp = driver.findElement(By.xpath("//*[@class='ui-datepicker-month']"));
            //5.- se selecciona fecha de ida con Xpath
            if(fechaingreso[1].equals(comp.getText())){
                List<WebElement> dias = driver.findElements(By.xpath("//*[@data-handler='selectDay']"));
                for (WebElement fIngreso:dias){
                    if (fechaingreso[0].equals(fIngreso.getText())){
                        fIngreso.click();
                        break;
                    }
                }
                break;
            }
            else{
                //5.1.- si no coincide la fecha enviada por parametros, se clickea arrow next
                Thread.sleep(2000);
                driver.findElement(By.xpath("//*[@class='ui-icon ui-icon-circle-triangle-e']")).click();
            }
        }
    }

    public void FechaDeSalida(String fecha_salida) throws InterruptedException {
        //6.- se carga fecha de salida
        String [] fechaSalida = fecha_salida.split("-");
        driver.findElement(By.xpath("//*[@id='checkOutDate']")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        for (int i = 0; i <12 ; i++) {
            WebElement comp = driver.findElement(By.xpath("//*[@class='ui-datepicker-month']"));
            //8.- se selecciona fecha de ida con Xpath
            if(fechaSalida[1].equals(comp.getText())){
                List<WebElement> dias = driver.findElements(By.xpath("//*[@data-handler='selectDay']"));
                for (WebElement fIngreso:dias){
                    if (fechaSalida[0].equals(fIngreso.getText())){
                        fIngreso.click();
                        break;
                    }
                }
                break;
            }
            else{
                //8.1.- si no coincide la fecha enviada por parametros, se clickea arrow next
                Thread.sleep(2000);
                driver.findElement(By.xpath("//*[@class='ui-icon ui-icon-circle-triangle-e']")).click();

            }
        }
    }

    public void NroHabitaciones(String nro_habitacion){
        //9.- se selecciona nro de habitaciones
        driver.findElement(By.xpath("//*[@name='no_rooms']")).click();
        Select habitacion = new Select(driver.findElement(By.xpath("//*[@name='no_rooms']")));
        habitacion.selectByVisibleText(nro_habitacion); //seleccionamos una opcion por el valor

    }
    public void NroAdultos(String nro_adultos){
        //10.- se selecciona nro de adultos
        driver.findElement(By.xpath("//*[@name='group_adults']")).click();
        Select adultos = new Select(driver.findElement(By.xpath("//*[@name='group_adults']")));
        adultos.selectByVisibleText(nro_adultos);
    }

    public void NroNinos(String nro_ninos){
        //11.- se selecciona nro de ninos
        driver.findElement(By.xpath("//*[@name='group_children']")).click();
        Select ninos = new Select(driver.findElement(By.xpath("//*[@name='group_children']")));
        ninos.selectByVisibleText(nro_ninos);
    }

    public String JSH004 (String lugar_destino,
                          String fecha_ingreso,
                          String fecha_salida,
                          String nro_habitacion,
                          String nro_adultos,
                          String nro_ninos) throws InterruptedException {
        String resultado = "";
        WebDriverWait Retardo = new WebDriverWait(driver, 10);
        //12.- se clickea hoteles y se parametriza datos iniciales
        PopUp();
        driver.findElement(By.xpath("//*[@class='jsh-icon jsh-bed']")).click();
        LugarDeDestino(lugar_destino);
        FechaDeIngreso(fecha_ingreso);
        FechaDeSalida(fecha_salida);
        NroHabitaciones(nro_habitacion);
        NroAdultos(nro_adultos);
        NroNinos(nro_ninos);
        driver.findElement(By.xpath("//*[@class='b-aff__submit--button']")).click();
        //13.- se cambia de pestaña
        driver.switchTo().defaultContent();
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles()); //array de cambio de pestana
        driver.switchTo().window(tabs2.get(0));
        driver.close();
        driver.switchTo().window(tabs2.get(1));
        Thread.sleep(8000);
        //14.-Seleccionar filtro de mayor precio

        driver.findElement(By.xpath("(//div[@class='bui-checkbox__label filter_item css-checkbox'])[5]")).click();
        Retardo.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='hotellist_inner']")));
        //15.-Seleccionar filtro desayuno incluido
        driver.findElement(By.xpath("(//*[@id='filter_mealplan']//*[@class='bui-checkbox__label filter_item css-checkbox'])[2]")).click();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS) ;

        //16.- se toma texto de precio y se transforma
        String textoPrecio = driver.findElement(By.xpath("(//*[@class='bui-price-display__value prco-inline-block-maker-helper '])[1]")).getText();
        String [] arrayTextoPrecio = textoPrecio.split(" ");
        String valorTextoPrecio = arrayTextoPrecio[1];
        String [] arrayTextoprecioP= valorTextoPrecio.split("\\.");
        String valorPrecio= arrayTextoprecioP[0]+arrayTextoprecioP[1]+arrayTextoprecioP[2];
        System.out.println(valorPrecio);
        int PrecioHotel = Integer.parseInt(valorPrecio);
        //17.-se toma texto de desayuno y se compara al precio de filtro minimo
        String textoDesayuno = driver.findElement(By.xpath("(//sup[@class='sr_room_reinforcement'])[1]")).getText();

        if (PrecioHotel >= 171000){
            if (textoDesayuno.equals("Desayuno incluido")){
                resultado = "Prueba de filtros exitosa";
            }
        }
        driver.close();
        return resultado;
    }

    public String JSH005 (String lugar_destino,
                          String fecha_ingreso,
                          String fecha_salida,
                          String nro_habitacion,
                          String nro_adultos,
                          String nro_ninos,
                          String lugar_NuevaBusqueda) throws InterruptedException {

        String resultado = "";
        //12.- se clickea hoteles y se parametriza datos iniciales
        PopUp();
        driver.findElement(By.xpath("//*[@class='jsh-icon jsh-bed']")).click();
        LugarDeDestino(lugar_destino);
        FechaDeIngreso(fecha_ingreso);
        FechaDeSalida(fecha_salida);
        NroHabitaciones(nro_habitacion);
        NroAdultos(nro_adultos);
        NroNinos(nro_ninos);
        driver.findElement(By.xpath("//*[@class='b-aff__submit--button']")).click();
        driver.switchTo().defaultContent(); //frame de vuelta
        //13.- se cambia de pestaña
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(0));
        driver.close();
        driver.switchTo().window(tabs2.get(1));

        //14.-Seleccionar filtro de mayor precio
        Thread.sleep(8000);
        driver.findElement(By.xpath("(//div[@class='bui-checkbox__label filter_item css-checkbox'])[5]")).click();
        String valor= driver.findElement(By.xpath("(//*[@class='bui-price-display__value prco-inline-block-maker-helper '])[1]")).getText();
        int longitud= valor.length();
        Thread.sleep(2000);

        //15.-se ingresan nuevos parametros para lugar de salida
        WebElement entradaNueva = driver.findElement(By.xpath("//input[@id='ss']"));
        entradaNueva.clear();
        entradaNueva.sendKeys(lugar_NuevaBusqueda);
        Thread.sleep(1000);
        entradaNueva.sendKeys(Keys.ENTER);
        //16.- se comprueba nueva busqueda y ausencia de filtro tras nueva busqueda
        String valor2= driver.findElement(By.xpath("(//*[@class='bui-price-display__value prco-inline-block-maker-helper '])[1]")).getText();
        String textoBusqueda = driver.findElement(By.xpath("(//h1)[1]")).getText();
        int longitud2= valor2.length();
        if(longitud2 < longitud){
            if (!textoBusqueda.equals("Buenos Aires") ){
                resultado = "modificacion parametros de busqueda exitosa";
            }
        }
        driver.close();
        return resultado;
    }

    public String JSH006 (String lugar_destino,
                          String fecha_ingreso,
                          String fecha_salida,
                          String nro_habitacion,
                          String nro_adultos,
                          String nro_ninos,
                          String nombre_Reserva,
                          String apellido_Reserva,
                          String mail) throws InterruptedException {
        //17.- se clickea hoteles y se parametriza datos iniciales
        String resultado = "";
        WebDriverWait Retardo = new WebDriverWait(driver, 10);
        PopUp();
        driver.findElement(By.xpath("//*[@class='jsh-icon jsh-bed']")).click();
        LugarDeDestino(lugar_destino);
        FechaDeIngreso(fecha_ingreso);
        FechaDeSalida(fecha_salida);
        NroHabitaciones(nro_habitacion);
        NroAdultos(nro_adultos);
        NroNinos(nro_ninos);
        driver.findElement(By.xpath("//*[@class='b-aff__submit--button']")).click();
        //18.-cambio de pestaña
        driver.switchTo().defaultContent();
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(0));
        driver.close();
        driver.switchTo().window(tabs2.get(1));

        //19.-Seleccionar filtro de mayor precio
        Thread.sleep(8000);
        driver.findElement(By.xpath("(//div[@class='bui-checkbox__label filter_item css-checkbox'])[5]")).click();
        Thread.sleep(3000);

        //20.-Seleccionar filtro de cancelacion gratis
        driver.findElement(By.xpath("//span[contains(text(),'Cancelación gratis')]")).click();
        Thread.sleep(3000);

        //21.-Click al boton de ver disponibilidad del primer resultado y cambio de pestaña
        driver.findElement(By.xpath("(//a[@class='txp-cta bui-button bui-button--primary sr_cta_button'])[1]")).click();
        driver.switchTo().defaultContent();
        ArrayList<String> tabs3 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs3.get(0));
        driver.close();
        driver.switchTo().window(tabs3.get(1));

        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        driver.findElement(By.xpath("(//*[@id='hcta'])[1]")).click();
        //22.-Seleccionar nro de habitaciones
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//select[@class='hprt-nos-select js-hprt-nos-select'])[1]")).click();
        Select habitaciones = new Select(driver.findElement(By.xpath("(//select[@class='hprt-nos-select js-hprt-nos-select'])[1]")));
        habitaciones.selectByValue("1");

        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@class='bui-button__text js-reservation-button__text']")).click();
        Retardo.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='bui-f-font-display_two js-property-details__name']")));

        //23.-Rellenar datos
        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(nombre_Reserva);
        driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(apellido_Reserva);
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(mail);
        driver.findElement(By.xpath("//input[@id='email_confirm']")).sendKeys(mail);
        driver.findElement(By.xpath("//span[contains(text(),'Yo soy el huésped principal')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click();
        Thread.sleep(2000);
        String textoFinal = driver.findElement(By.xpath("(//h2)[1]")).getText();
        if (textoFinal.equals("Los datos de tu reserva")){
            resultado = "reserva completa exitosa";
        }
        driver.close();
        return resultado;
    }
}