package pageobject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageobject.base.SeleniumBase;

public class JetSmartTrasladosPage extends SeleniumBase {

    public JetSmartTrasladosPage(WebDriver driver){
        super(driver);
    }

    private By textoTitulo =By.xpath("//h3[@class='gtg-padding gtg-strong']");


    public String validacionResultado(){
        String resultado= "";
        String paginaResultado = getText(textoTitulo);
        if (paginaResultado.equals("Opciones de traslado disponibles para este viaje")){
            resultado = "Busqueda de traslado exitosa";
        }
        return resultado;
    }

}
