package pageobject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageobject.base.SeleniumBase;

public class JetSmartVuelosPage extends SeleniumBase {


    public JetSmartVuelosPage(WebDriver driver){
        super(driver);
    }

    private By textoResultado = By.xpath("//h3[contains(text(),'Detalles de tu Compra:')]");
    private By precioVuelo = By.xpath("//*[@class='smart-fee nowrap big ']");
    private By btnPaqueteFull = By.xpath("(//button[@class='bundle-button '])[7]");
    private By btnContiunarResumen=By.xpath("(//*[@class='rounded-primary-btn booking'])[1]");

    private By esperaTitulo = By.xpath("//*[@class='main-title']");

    private By nombrePasajero1 = By.xpath("//*[@id='Pfn0']");
    private By apellidoPasajero1 = By.xpath("//input[@id='Pln0']");

    private By nombrePasajero2 = By.xpath("//*[@id='Pfn1']");
    private By apellidoPasajero2 = By.xpath("//input[@id='Pln1']");

    private By nombrePasajero3 = By.xpath("//*[@id='Pfn2']");
    private By apellidoPasajero3 = By.xpath("//input[@id='Pln2']");

    private By diaDeNacimiento = By.id("date_of_birth_day_2");
    private By mesDeNacimiento = By.id("date_of_birth_month_2");
    private By anioDeNacimiento = By.id("date_of_birth_year_2");

    private By botonContinuar = By.xpath("//*[@class='rounded-primary-btn booking']");
    private By tituloEquipaje = By.xpath("(//*[@class='main-title'])[2]");


    public String validacionBusqueda(){
        String texto=getText(textoResultado);
        String result="";
        if(texto.equals("Detalles de tu Compra:")){
            result="se encontraron vuelos, Test completo";
        }
        return result;
    }

    public void clickBotonPrecio(){
        isDisplayed(precioVuelo);
        click(precioVuelo);
    }

    public void seleccionPaquetesFull(){
        isDisplayed(btnPaqueteFull);
        click(btnPaqueteFull);
    }

    public void btnContinuarDatosPasajeros(){
        click(btnContiunarResumen);
        isDisplayed(esperaTitulo);
    }
    public void rellenarFormulario(String nomP1,String nomE1,String nomP2,String nomE2,String nomP3,String nomE3){
        type(nomP1,nombrePasajero1);
        type(nomE1,apellidoPasajero1);
        type(nomP2,nombrePasajero2);
        type(nomE2,apellidoPasajero2);
        type(nomP3,nombrePasajero3);
        type(nomE3,apellidoPasajero3);
    }

    public void fechaNNinos(){
        selectCantidadValue(diaDeNacimiento,"6");
        selectCantidadValue(mesDeNacimiento,"6");
        selectCantidadValue(anioDeNacimiento,"2015");
    }

    public void clickBtnContinuar(){
        click(botonContinuar);
    }

    public String validacionTitulo(){
        String resultado ="";
        String textoComprobacion = getText(tituloEquipaje);
        if(textoComprobacion.equals("Equipaje")){
            resultado="comprobacion exitosa, llego hasta equipaje";
        }
        click(tituloEquipaje);
        return resultado;
    }

}
