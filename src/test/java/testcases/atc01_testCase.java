package testcases;


import org.junit.Test;
import pageobject.pages.JetSmartHomePage;

public class atc01_testCase extends TestBase {

    protected JetSmartHomePage jsHomePage;


    @Test
    public void atc01_test(){
        jsHomePage = new JetSmartHomePage(driver);
        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        jsHomePage.cerrarModuloSuscribete();
        jsHomePage.ingresarOrigenVuelos();
        jsHomePage.ingresarDestinoVuelos();
        jsHomePage.ingresarFechaIda();
        jsHomePage.IngresarFechaVuelta();
        System.out.println("kdjfldksfj");
    }
    @Test
    public void casoVuelo1()throws InterruptedException{

        jsHomePage = new JetSmartHomePage(driver);
        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        jsHomePage.cerrarModuloSuscribete();
        jsHomePage.cantidadPasajeros();

    }
    @Test
    public void hotelesPrueba () throws InterruptedException {
        jsHomePage = new JetSmartHomePage(driver);
        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        jsHomePage.cerrarModuloSuscribete();
        jsHomePage.lugarDeDestinoHoteles();
        jsHomePage.fechaDeIngresoHoteles("13-Junio-2021");
        jsHomePage.fechaDeSalidaHoteles("3-julio-2021");
    }
    @Test
    public void trasladosPrueba (){
        jsHomePage = new JetSmartHomePage(driver);
        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        jsHomePage.cerrarModuloSuscribete();
        jsHomePage.lugarOrigenTraslados();
        jsHomePage.lugarDestinoTraslados();
        jsHomePage.fechaIdaTraslados();
    }
}

