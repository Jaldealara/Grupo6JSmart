package js_Grupo6;

import org.junit.Assert;
import org.junit.Test;

public class jetSmart_Test {

    @Test
    public void Caso1JSVuelos() throws InterruptedException {
        jetSmart_Vuelos Vuelos =new jetSmart_Vuelos();
        Assert.assertEquals("Si necesitas hacer una reserva de 10 pasajeros o más, ingresa a nuestro formulario de grupos.",Vuelos.casoVuelos1());
        System.out.println("test 1 exitoso");
    }
    @Test
    public void Caso2JSVuelos() throws InterruptedException {
        jetSmart_Vuelos Vuelos =new jetSmart_Vuelos();
        Assert.assertEquals("se encontraron vuelos, Test completo",Vuelos.CasoVuelo2("Junio 20, 2021",
                                                                                            "Enero 20, 2022",
                                                                                            "Santiago",
                                                                                            "Arica"));
        System.out.println("test 2 exitoso");
    }
    @Test
    public void Caso3JSVuelos() throws  InterruptedException{
        jetSmart_Vuelos Vuelos =new jetSmart_Vuelos();
        Assert.assertEquals("comprobacion exitosa, llego hasta equipaje",Vuelos.CasoVuelo3("Junio 20, 2021",
                                                                                                "Santiago",
                                                                                                "Arica"));
        System.out.println("test 3 exitoso");
    }

    @Test
    public void Caso4JSHoteles() throws InterruptedException{
        jetSmart_Hoteles Hoteles= new jetSmart_Hoteles();
        Assert.assertEquals("Prueba de filtros exitosa",Hoteles.JSH004("Buenos Aires","20-unio-2021","1-julio-2021","1","1","0"));
        System.out.println("test 4 exitoso");
    }
    @Test
    public void Caso5JSHoteles() throws InterruptedException{
        jetSmart_Hoteles Hoteles= new jetSmart_Hoteles();
        Assert.assertEquals("modificacion parametros de busqueda exitosa",Hoteles.JSH005("Buenos Aires","20-junio-2021","1-julio-2021","1","1","0","Arica"));
        System.out.println("test 5 exitoso");
    }
    @Test
    public void Caso6JSHoteles() throws  InterruptedException{
        jetSmart_Hoteles Hoteles= new jetSmart_Hoteles();
        Assert.assertEquals("reserva completa exitosa",Hoteles.JSH006("Buenos Aires","20-junio-2021","1-julio-2021","1","1","0","Mario","Escudero","fjskdlflsd@gmail.com"));
        System.out.println("test 6 exitoso");
    }
    @Test
    public void Caso10JSTraslados()throws InterruptedException{
        jetSmart_Traslados Traslados = new jetSmart_Traslados();
        Assert.assertEquals("Busqueda de traslado exitosa",Traslados.JST010("Buenos aires",
                                                                                    "Ezeiza",
                                                                                    "13-12-2021"));
        System.out.println("test 10 exitoso");
    }
    @Test
    public void Caso11JSTraslados() throws InterruptedException{
        jetSmart_Traslados Traslados = new jetSmart_Traslados();
        Assert.assertEquals("Cambio de moneda exitosa",Traslados.JST011("Buenos aires",
                                                                                "Ezeiza",
                                                                                "15-06-2021"));
        System.out.println("test 11 exitoso");
    }
    @Test
    public void Caso12JSTraslados() throws InterruptedException{
        jetSmart_Traslados Traslados = new jetSmart_Traslados();
        Assert.assertEquals("Introduce una entrada válida. p. ej. AB123, CD1234",Traslados.JST012(
                                                                                "Buenos Aires - Ministro Pistarini",
                                                                                "Estadio Monumental",
                                                                                "20-05-2021",
                                                                                "marito Escudero",
                                                                                "marito.escudero@gmail.com",
                                                                                "1231241233",
                                                                                "aaasssx"));
        System.out.println("test 12 exitoso");

    }
}
