package testcases;


import org.junit.Assert;
import org.junit.Test;
import pageobject.pages.*;

public class atc01_testCase extends TestBase {

    protected JetSmartHomePage jsHomePage;
    protected JetSmartHotelesPage jsHotelesPage;
    protected JetSmartVerDisponibilidadHoteles jetSmartVerDisponibilidadHoteles;
    protected JetSmartVuelosPage jsVuelosPage;
    protected JetSmartTrasladosPage jetSmartTrasladosPage;
    @Test
    public void casoVuelo1()throws InterruptedException{

        jsHomePage = new JetSmartHomePage(driver);
        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        jsHomePage.cerrarModuloSuscribete();
        jsHomePage.cantidadPasajeros();

    }
    @Test
    public void casoVuelo2()throws InterruptedException{
        jsHomePage = new JetSmartHomePage(driver);
        jsVuelosPage = new JetSmartVuelosPage(driver);
        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        jsHomePage.cerrarModuloSuscribete();
        jsHomePage.ingresarOrigenVuelos("Santiago");
        jsHomePage.ingresarDestinoVuelos("Arica");
        jsHomePage.ingresarFechaIda();
        jsHomePage.ingresarFechaVuelta();
        jsHomePage.boton_Search();
        jsVuelosPage.validacionBusqueda();
        String resultado=jsVuelosPage.validacionBusqueda();
        System.out.println(resultado);

    }

    @Test
    public void casoVuelo3()throws InterruptedException{
        jsHomePage = new JetSmartHomePage(driver);
        jsVuelosPage = new JetSmartVuelosPage(driver);
        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        jsHomePage.cerrarModuloSuscribete();
        jsHomePage.ingresarOrigenVuelos("Santiago");
        jsHomePage.ingresarDestinoVuelos("Arica");
        jsHomePage.ingresarFechaIda();
        jsHomePage.clickSoloIdaVuelos();
        jsHomePage.sumarPasajerosVuelos();
        jsHomePage.boton_Search();
        jsVuelosPage.clickBotonPrecio();
        jsVuelosPage.seleccionPaquetesFull();
        jsVuelosPage.btnContinuarDatosPasajeros();
        jsVuelosPage.rellenarFormulario("Fernando","Cavenaghi","Marcelo","Gallardo","Pity","Martinezgaal");
        jsVuelosPage.fechaNNinos();
        jsVuelosPage.clickBtnContinuar();
        jsVuelosPage.validacionTitulo();

    }

    //--------------LARGA HOTELES-----------------------------------
    @Test
    public void caso04Hoteles() throws InterruptedException {
        jsHomePage = new JetSmartHomePage(driver);
        jsHotelesPage = new JetSmartHotelesPage(driver);
        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        jsHomePage.cerrarModuloSuscribete();
        jsHomePage.popUP2Close();
        jsHomePage.lugarDeDestinoHoteles("Santiago");
        jsHomePage.fechaDeIngresoHoteles("13-junio-2021");
        jsHomePage.fechaDeSalidaHoteles("3-julio-2021");
        jsHomePage.seleccionarNroHabitaciones("1");
        jsHomePage.seleccionarNroAdultos("2");
        jsHomePage.seleccionarNroNinos("1");
        jsHomePage.btnBusquedaHotel();
        jsHotelesPage.switchToPestana(0,1);
        jsHotelesPage.seleccionFiltroMayorPrecio();
        jsHotelesPage.seleccionarFiltroDesayuno();
        String resultado = jsHotelesPage.validacionPrecioDesayuno();
        System.out.println(resultado);
    }
    @Test
    public void caso05Hoteles() throws InterruptedException {
        jsHomePage = new JetSmartHomePage(driver);
        jsHotelesPage = new JetSmartHotelesPage(driver);
        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        jsHomePage.cerrarModuloSuscribete();
        jsHomePage.lugarDeDestinoHoteles("Arica");
        jsHomePage.fechaDeIngresoHoteles("13-junio-2021");
        jsHomePage.fechaDeSalidaHoteles("3-julio-2021");
        jsHomePage.seleccionarNroHabitaciones("1");
        jsHomePage.seleccionarNroAdultos("2");
        jsHomePage.seleccionarNroNinos("1");
        jsHomePage.btnBusquedaHotel();
        jsHotelesPage.switchToPestana(0,1);
        int longitud = jsHotelesPage.seleccionFiltroMayorPrecio();
        jsHotelesPage.modificacionBusqueda();
        jsHotelesPage.validacionDeBusqueda(longitud,"Arica");
    }
    @Test
    public void caso06Hoteles() throws InterruptedException {
        jsHomePage = new JetSmartHomePage(driver);
        jsHotelesPage = new JetSmartHotelesPage(driver);
        jetSmartVerDisponibilidadHoteles = new JetSmartVerDisponibilidadHoteles(driver);
        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        jsHomePage.cerrarModuloSuscribete();
        jsHomePage.lugarDeDestinoHoteles("Santiago");
        jsHomePage.fechaDeIngresoHoteles("13-junio-2021");
        jsHomePage.fechaDeSalidaHoteles("3-julio-2021");
        jsHomePage.seleccionarNroHabitaciones("1");
        jsHomePage.seleccionarNroAdultos("2");
        jsHomePage.seleccionarNroNinos("1");
        jsHomePage.btnBusquedaHotel();
        jsHotelesPage.switchToPestana(0,1);
        jsHotelesPage.seleccionFiltroMayorPrecio();
        jsHotelesPage.filtroCancelacionGratis();
        jsHotelesPage.clickBtnVerDisponibilidad();
        jsHotelesPage.switchToPestana(0,1);
        jetSmartVerDisponibilidadHoteles.reservar();
        jetSmartVerDisponibilidadHoteles.numeroDeHabitaciones("1");
        jetSmartVerDisponibilidadHoteles.clickReservare();
        jetSmartVerDisponibilidadHoteles.rellenarFormulario("juanito","perez","asdas@gmail.com");
        String resul=jetSmartVerDisponibilidadHoteles.validacionFinal();
        System.out.println(resul);


    }
    //--------------LARGA TRASLADOS----------------------------------------
    @Test
    public void trasladosCaso10 () throws InterruptedException {
        jsHomePage = new JetSmartHomePage(driver);
        jetSmartTrasladosPage = new JetSmartTrasladosPage(driver);

        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        jsHomePage.cerrarModuloSuscribete();
        jsHomePage.lugarOrigenTraslados("Buenos Aires");
        jsHomePage.lugarDestinoTraslados("Bariloche");
        jsHomePage.fechaIdaTraslados();
        jsHomePage.seleccionarHoraTraslados();
        jsHomePage.seleccionarPasajeros();
        jsHomePage.botonBuscarTraslados();
        jsHomePage.switchToPestana(0,1);
        String resul = jetSmartTrasladosPage.validacionResultado();
        System.out.println(resul);

    }
    @Test
    public void trasladosCaso11() throws InterruptedException {
        jsHomePage = new JetSmartHomePage(driver);
        jetSmartTrasladosPage = new JetSmartTrasladosPage(driver);
        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        jsHomePage.cerrarModuloSuscribete();
        jsHomePage.lugarOrigenTraslados("Buenos Aires");
        jsHomePage.lugarDestinoTraslados("Bariloche");
        jsHomePage.fechaIdaTraslados();
        jsHomePage.seleccionarHoraTraslados();
        jsHomePage.seleccionarPasajeros();
        jsHomePage.botonBuscarTraslados();
        jsHomePage.switchToPestana(0,1);
        jetSmartTrasladosPage.cierrePopUp();
        jetSmartTrasladosPage.seleccionMoneda("USD");
        jetSmartTrasladosPage.btnBuscar();
        String resultado=jetSmartTrasladosPage.monedaPrecioTraslado();
        System.out.println(resultado);
    }
    @Test
    public void trasladoCaso12 () throws InterruptedException {
        jsHomePage = new JetSmartHomePage(driver);
        jetSmartTrasladosPage = new JetSmartTrasladosPage(driver);
        jsHomePage.goToUrl("https://jetsmart.com/cl/es/");
        jsHomePage.cerrarModuloSuscribete();
        jsHomePage.lugarOrigenTraslados("Buenos Aires - Ministro Pistarini");
        jsHomePage.lugarDestinoTraslados("Estadio monumental");
        jsHomePage.fechaIdaTraslados();
        jsHomePage.seleccionarHoraTraslados();
        jsHomePage.seleccionarPasajeros();
        jsHomePage.botonBuscarTraslados();
        jsHomePage.switchToPestana(0,1);

        jetSmartTrasladosPage.botonReservaTraslado();
        jetSmartTrasladosPage.rellenarDatos("juan separado","juan12@gmail.com","972447281");
        jetSmartTrasladosPage.clickPage();
        jetSmartTrasladosPage.nroDeVuelo("asddsdqwdqwd");
        String result=jetSmartTrasladosPage.confirmacion();
        System.out.println(result);

    }
}

