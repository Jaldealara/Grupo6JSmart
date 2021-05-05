package pageobject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pageobject.base.SeleniumBase;
import testcases.atc01_testCase;
import org.openqa.selenium.WebElement;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * URL: https://jetsmart.com/cl/es/
 * HomePage para automatizacion de casos de Pruebas
 */
public class JetSmartHomePage extends SeleniumBase{

    public JetSmartHomePage(WebDriver driver) {
        super(driver);
    }
    //repositorio objetos JetSmartHomePAge
    private By btnPopUpClose = By.xpath("//div[@class='modal-header']//button[@type='button']");
    private By txtNombreSuscribete = By.xpath("//input[@id='name']");
    private By txtApellidoSuscribete = By.xpath("//input[@id='lastname']");
    private By txtEmailSuscribete = By.xpath("//input[@id='email']");
    private By btnSuscribete = By.xpath("//span[contains(text(),'Suscríbete')]");
    private By btnPopUp2Open = By.xpath("//div[@class='menu-close']");
    private By btnPopUp2Close = By.xpath("//div[@class='menu-open']");

    //origen
    private By inputLugarOrigen = By.xpath("//input[@placeholder='Origen']");
    private By listaDestinosOrigen= By.xpath("//*[@class='dg-opening-pane-1']");
    private By lugarLista = By.xpath("//li[@class='dg-typing-results-list-item ']");

    //destino
    private By inputLugarDestino = By.xpath("//input[@placeholder='Destino']");
    private By fechaOrigen = By.xpath("(//div[@class='dayContainer'])[1]");
    private By textoMes = By.xpath("(//*[@class='cur-month'])[1]");

    String fechaIda="Junio 20,2021";
    private By ingresoFecha = By.xpath("(//div[@class='dayContainer'] //span[@aria-label='"+fechaIda+"'])[1]");
    private By arrowNext = By.xpath("(//span[@class='flatpickr-next-month'])[1]");

    String fechaVuelta="Enero 15, 2022";
    private  By fechaDestino = By.xpath("//div[@class='dg-calendar-header']");
    private  By textoMesVuelta = By.xpath("(//span[@class='cur-month'])[3]");
    private  By ingresoFechaDestino= By.xpath("(//div[@class='dayContainer']//span[@aria-label='"+fechaVuelta+"'])[1]");
    private  By arrowNextVuelta= By.xpath("(//span[@class='flatpickr-next-month'])[2]");

    private By pestanaNPasajeros = By.xpath("//input[@ref='paxSummary']");
    private By cantNAdultos = By.xpath("//pax-amount[@ref='adults']/div[contains(text(),'+')]");
    private By cantNInfantes= By.xpath("//pax-amount[@ref='infants']/div[contains(text(),'+')]");
    private By cantNninos=By.xpath("//pax-amount[@ref='children']/div[contains(text(),'+')]");
    private By listaCantidadPasajeros= By.xpath("//div[@class='dg-quantity-dropdown open']");
    private By restaCantidadInfantes= By.xpath("//pax-amount[@ref='infants']/div[contains(text(),'-')]");
    private By mensajeError = By.xpath("//div[@class='dg-pax-quantity-message']");

    //--------------------hoteles-------------------------------------------------------
    private By btnBusquedaFormulario = By.xpath("//*[@class='b-aff__submit--button']");
    private By clickHotelesH =By.xpath("//*[@class='jsh-icon jsh-bed']");
    private By destinoOrigenH =By.xpath("//*[@id='b_destination']");
    private By listaCiudadesH =By.xpath("//*[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content']");
    private By seleccionCiudadH =By.xpath("//*[@class='autocomplete_city autocomplete ui-menu-item']");
    //String fechaIngresoHoteles= "20-junio-2021";
    private By inputFechaIdaH= By.xpath("//*[@id='checkInDate']");
    private By inputFechaVueltaH= By.xpath("//*[@id='checkOutDate']");
    private By mesesHoteles =By.xpath("//*[@class='ui-datepicker-month']");
    private By botonBusqueda = By.xpath("//button[@id='rt_button']");
    private By diasTotalesIdaH=By.xpath("//*[@data-handler='selectDay']");
    private By arrowNextH = By.xpath("//*[@class='ui-icon ui-icon-circle-triangle-e']");
    private By nroHabitaciones = By.xpath("//*[@name='no_rooms']");
    private By nroAdultos = By.xpath("//*[@name='group_adults']");
    private By nroNinos = By.xpath("//*[@name='group_children']");
    //String fechaSalidaHoteles ="3-julio-2021";



    //------------------Traslados---------------------------------------
    private By botonIngresoTraslados= By.xpath("//*[@class='jsh-icon jsh-van']");
    private By origenTraslados= By.xpath("//*[@id='input-pickup']");
    private By destinoTraslados= By.xpath("//*[@id='input-dropoff']");
    private By listaOrigenTraslados= By.xpath("//*[@id='item-1-0']");
    private By listadestinoTraslados= By.xpath("//*[@id='item-0-0']");
    private By clickSoloIda =By.xpath("(//span[contains(text(),'Ida')])[1]");
    private By calendarioTraslados= By.xpath("//input[@id='input-pickup-date']");
    private By horaTraslados = By.xpath("//input[@id='ct-time-picker-pick-up-input']");
    String fechaIdaTraslados= "20-05-2021";




    //keyword Driven
    public void cerrarModuloSuscribete(){
        if(isDisplayed(btnPopUpClose)){
            click(btnPopUpClose);
        }
    }
    public void suscribirseEnPopUp(){
        if (isDisplayed(txtNombreSuscribete)){
            type("nombre",txtNombreSuscribete);
            type("apellido",txtApellidoSuscribete);
            type("email@email.em",txtEmailSuscribete);
            click(btnSuscribete);
        }
    }

    public void popUP2Close() throws InterruptedException {
        isDisplayed(btnPopUp2Open);
        click(btnPopUp2Open);
        isDisplayed(btnPopUp2Close);
        click(btnPopUp2Close);
        espera(2000);
    }
    public void ingresarOrigenVuelos(){
        click(inputLugarOrigen);
        type("Santiago",inputLugarOrigen);
        isDisplayed(listaDestinosOrigen);
        click(lugarLista);

    }
    public void ingresarDestinoVuelos(){
        type("Arica",inputLugarDestino);
        isDisplayed(listaDestinosOrigen);
        click(lugarLista);

    }
    public void ingresarFechaIda(){
        isDisplayed(fechaOrigen);
        findElement(textoMes); //tenemos comp

        String  [] corteFecha =fechaIda.split(" "); //tomamos fecha ida (mes)
        for (int i = 0; i <12 ; i++) {
            //5.- se selecciona fecha de ida con Xpath
            if(corteFecha[0].equals(getText(textoMes))){
                findElement(ingresoFecha).click();
                break;
            }
            else{
                findElement(arrowNext).click();
            }
        }
    }
    public void IngresarFechaVuelta(){
        isDisplayed(fechaDestino);
        findElement(textoMesVuelta);
        String []  corteFechaVuelta=fechaVuelta.split(" "); //tomamos fecha ida (mes)
        for (int i = 0; i <12 ; i++) {
            if (corteFechaVuelta[0].equals(getText(textoMesVuelta))) {
                findElement(ingresoFechaDestino).click();
                break;
            } else {
                findElement(arrowNextVuelta).click();
            }
        }
    }
    public String cantidadPasajeros() throws InterruptedException {


        click(pestanaNPasajeros);
        isDisplayed(listaCantidadPasajeros);
        for (int i = 1; i <8 ; i++) {
            Thread.sleep(200);
            findElement(cantNAdultos).click();
        }
        for (int i = 0; i <8; i++) {
            findElement(cantNInfantes).click();
        }
        findElement(cantNninos).click();

        findElement(restaCantidadInfantes).click();
        try {
            findElement(cantNInfantes).click();
        }
        catch(Exception ignored){}

        String result = findElement(mensajeError).getText();
        return result;
    }
    public void boton_Search(){
        click(botonBusqueda); }

    // hoteles


    public void lugarDeDestinoHoteles (String lugarDestino){
        click(clickHotelesH);
        switchTo(0);
        clear(destinoOrigenH);
        type(lugarDestino,destinoOrigenH);
        isDisplayed(listaCiudadesH);
        click(seleccionCiudadH);

    }
    public void fechaDeIngresoHoteles(String fechaIngresoHoteles) throws InterruptedException {
        String [] fechaingreso = fechaIngresoHoteles.split("-");

        click(inputFechaIdaH);
        for (int i = 0; i <12 ; i++) {
            findElement(mesesHoteles);
            if(fechaingreso[1].equals(getText(mesesHoteles))){
                findElements(diasTotalesIdaH);

                for (WebElement fIngreso:findElements(diasTotalesIdaH)){
                    if (fechaingreso[0].equals(fIngreso.getText())){
                        fIngreso.click();
                        break;
                    }
                }
                break;
            }
            else{
                Thread.sleep(2000);
                click(arrowNextH);
            }
        }
    }
    public void fechaDeSalidaHoteles(String fechaSalidaHoteles) throws InterruptedException {
        String [] fechaSalida = fechaSalidaHoteles.split("-");
        click(inputFechaVueltaH);
        for (int i = 0; i <12 ; i++) {
            findElement(mesesHoteles);
            if(fechaSalida[1].equals(getText(mesesHoteles))){
                findElements(diasTotalesIdaH);
                for (WebElement fSalida:findElements(diasTotalesIdaH)){
                    if (fechaSalida[0].equals(fSalida.getText())){
                        fSalida.click();
                        break;
                    }
                }
                break;
            }
            else{
                espera(2000);
                click(arrowNextH);
            }
        }
    }

    //traslados

    public void lugarOrigenTraslados () {
        click(botonIngresoTraslados);
        switchTo(2);
        click(clickSoloIda);
        click(origenTraslados);
        type("Buenos Aires", origenTraslados);
        isDisplayed(listaOrigenTraslados);
        click(listaOrigenTraslados);
    }
    public void lugarDestinoTraslados (){
//aqui puede ir una espera de invisibilidad

        type("Bariloche", destinoTraslados);
        isDisplayed(listadestinoTraslados);
        click(listadestinoTraslados);

    }
    public void fechaIdaTraslados(){
        String[] fecha = fechaIdaTraslados.split("-"); //
        int dia = Integer.parseInt(fecha[0]);
        int mes = Integer.parseInt(fecha[1])-1;
        int anio = Integer.parseInt(fecha[2]);

        Calendar c = Calendar.getInstance();
        int diaDelAnio = c.get(Calendar.DAY_OF_YEAR); //123

        Calendar c1 = new GregorianCalendar(anio, mes, dia);   //obtenemos dia de la fecha otorgada
        int diaFechaParametro = c1.get(Calendar.DAY_OF_YEAR);

        int diferencia = diaFechaParametro - diaDelAnio;
        int numeroClicks = 1;
        for (int i = 0; i < diferencia; i++) {
            sendKeysDerecha(calendarioTraslados);

            if (numeroClicks == diferencia) {
                sendKeysEnter(calendarioTraslados);
            } else {
                numeroClicks++;
            }
        }
    }

    public void seleccionarNroHabitaciones(String habitaciones){
        click(nroHabitaciones);
        selectCantidad(nroHabitaciones,habitaciones);

    }
    public void seleccionarNroAdultos(String adultos){
        click(nroAdultos);
        selectCantidad(nroAdultos,adultos);
    }
    public void seleccionarNroNinos(String ninos){
        click(nroNinos);
        selectCantidad(nroNinos,ninos);
    }


    public void btnBusquedaHotel(){
        click(btnBusquedaFormulario);
    }
}