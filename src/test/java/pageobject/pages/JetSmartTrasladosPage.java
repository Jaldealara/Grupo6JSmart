package pageobject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageobject.base.SeleniumBase;

public class JetSmartTrasladosPage extends SeleniumBase {

    public JetSmartTrasladosPage(WebDriver driver) {
        super(driver);
    }

    private By textoTitulo = By.xpath("//h3[@class='gtg-padding gtg-strong']");
    private By popUpDeAcuerdo = By.xpath("(//*[@aria-label='De acuerdo.'])[1]");
    private By tipoMoneda = By.xpath("//*[@id='currency-code']");
    private By btnBusqueda = By.xpath("//*[@id='ct-button-search']/button");
    private By monedaPTraslado = By.xpath("(//*[@class='ct-clearfix ct-hide-mobile'])[1]");
    private By btnReservarAhora = By.xpath("(//*[@class='ct-btn ct-btn-p '])[1]");
    private By nombre = By.xpath("//*[@id='gt-customer-name']");
    private By email = By.xpath("//*[@id='gt-customer-email']");
    private By confirmEmail = By.xpath("//*[@id='gt-customer-confirm-email']");
    private By nroTelefono = By.xpath("//*[@id='gt-customer-mobile-number']");

    private By click = By.xpath("(//*[@class='ctc-switch__label'])[1]");
    private By nroVuelo = By.xpath("//*[@id='gt-pickup-flight-number']");
    private By clickEnvio = By.xpath("//*[@class='ctc-button ctc-button--full ctc-button--primary']");
    private By msgError = By.xpath("(//*[@class='ctc-form__hint'])[7]");

/*

        driver.findElement(By.xpath("(//*[@class='ctc-switch__label'])[1]")).click();
        driver.findElement(By.xpath("//*[@id='gt-pickup-flight-number']")).sendKeys(nroVuelo);
        driver.findElement(By.xpath("//*[@class='ctc-button ctc-button--full ctc-button--primary']")).click();

 */

    public String validacionResultado() {
        String resultado = "";
        String paginaResultado = getText(textoTitulo);
        if (paginaResultado.equals("Opciones de traslado disponibles para este viaje")) {
            resultado = "Busqueda de traslado exitosa";
        }
        return resultado;
    }

    public void cierrePopUp() {
        isDisplayed(popUpDeAcuerdo);
        click(popUpDeAcuerdo);
    }

    public void seleccionMoneda(String tipoMoned) {
        isDisplayed(tipoMoneda);
        click(tipoMoneda);
        selectCantidadValue(tipoMoneda, tipoMoned);
    }

    public void btnBuscar() {
        click(btnBusqueda);
    }


    public String monedaPrecioTraslado() {
        String result = "";
        isDisplayed(monedaPTraslado);
        String precio = getText(monedaPTraslado);
        if (precio.contains("USD")) {
            result = "Cambio de moneda exitosa";
        }
        return result;
    }

    public void botonReservaTraslado() {
        isDisplayed(btnReservarAhora);
        click(btnReservarAhora);
    }

    public void rellenarDatos(String nombreP, String emailp, String nroTel) {
        type(nombreP, nombre);
        type(emailp, email);
        type(emailp, confirmEmail);
        type(nroTel, nroTelefono);
    }

    public void clickPage() {
        click(click);
    }

    public void nroDeVuelo(String nroVuelos) throws InterruptedException {
        isDisplayed(nroVuelo);
        type(nroVuelos, nroVuelo);
        click(clickEnvio);
    }

    public String confirmacion() {
        String mensajeError = getText(msgError);
        String resultado = "";
        if (mensajeError.equals("Introduce una entrada válida. p. ej. AB123, CD1234")) {
            resultado = mensajeError;
        }
        return resultado;
    }
}