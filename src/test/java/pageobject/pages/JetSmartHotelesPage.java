package pageobject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageobject.base.SeleniumBase;

public class JetSmartHotelesPage extends SeleniumBase {
    public JetSmartHotelesPage(WebDriver driver) {
        super(driver);
    }

    private By filtroMayorPrecio = By.xpath("(//div[@class='bui-checkbox__label filter_item css-checkbox'])[5]");
    private By listaHoteles = By.xpath("(//div[@class='bui-checkbox__label filter_item css-checkbox'])[5]");
    private By filtroDesayuno = By.xpath("(//*[@id='filter_mealplan']//*[@class='bui-checkbox__label filter_item css-checkbox'])[2]");
    private By textoPrecioHotel = By.xpath("(//*[@class='bui-price-display__value prco-inline-block-maker-helper '])[1]");
    private By textoDesayuno = By.xpath("(//sup[@class='sr_room_reinforcement'])[1]");

    private By origenNuevaBusqueda = By.xpath("//input[@id='ss']");
    private By listaDelugares = By.xpath("//li[@data-i='0']");
    private By tituloBusqueda = By.xpath("(//h1)[1]");
    private By filtroCancelacióngratis = By.xpath("//span[contains(text(),'Cancelación gratis')]");
    private By btnVerDisponibilidad = By.xpath("(//a[@class='txp-cta bui-button bui-button--primary sr_cta_button'])[1]");

    public void cambioDePestana() throws InterruptedException {
        switchToPestana(0,1);
    }

    public int seleccionFiltroMayorPrecio() throws InterruptedException {
        click(filtroMayorPrecio);
        int longitud = getText(textoPrecioHotel).length();
        espera(3000);
        return longitud;
    }

    public void seleccionarFiltroDesayuno() throws InterruptedException{
        isDisplayed(listaHoteles);
        click(filtroDesayuno);
        espera(4000);
    }

    public String validacionPrecioDesayuno(){
        String resultado = "";
        String [] arrayTextoPrecio = getText(textoPrecioHotel).split(" ");
        String valorTextoPrecio = arrayTextoPrecio[1];
        String [] arrayTextoprecioP= valorTextoPrecio.split("\\.");
        String valorPrecio= arrayTextoprecioP[0]+arrayTextoprecioP[1];
        int PrecioHotel = Integer.parseInt(valorPrecio);
        if (PrecioHotel >= 171000){
            if (getText(textoDesayuno).equals("Desayuno incluido")){
                resultado = "Prueba de filtros exitosa";
            }
        }
        return resultado;
    }

    public void modificacionBusqueda(){
        clear(origenNuevaBusqueda);
        type("Arica",origenNuevaBusqueda);
        isDisplayed(listaDelugares);
        click(listaDelugares);
        sendKeysEnter(origenNuevaBusqueda);
    }

    public String validacionDeBusqueda(int longitud, String busquedaNueva){
        String resultado= "";
        int longitud2 = getText(textoPrecioHotel).length();
        if(longitud2 < longitud){
            if (!getText(tituloBusqueda).equals(busquedaNueva) ){
                resultado = "modificacion parametros de busqueda exitosa";
            }
        }
        return resultado;
    }





}
