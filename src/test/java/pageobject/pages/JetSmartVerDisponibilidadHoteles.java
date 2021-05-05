package pageobject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageobject.base.SeleniumBase;

public class JetSmartVerDisponibilidadHoteles extends SeleniumBase {

    public JetSmartVerDisponibilidadHoteles(WebDriver driver) {
        super(driver);
    }

    private By reservarAhora =By.xpath("//*[@id='hcta']");
    private By listaNroHabitaciones = By.xpath("(//select[@class='hprt-nos-select js-hprt-nos-select'])[1]");
    private By btnReservare = By.xpath("//*[@class='bui-button__text js-reservation-button__text']");
    private By waitTitle = By.xpath("//h1[@class='bui-f-font-display_two js-property-details__name']");
    private By nombre= By.xpath("//input[@id='firstname']");
    private By apellido=By.xpath("//input[@id='lastname']");
    private By email= By.xpath("//input[@id='email']");
    private By confirmarEmail=By.xpath("//input[@id='email_confirm']");
    private By radioButtonHuespedPrincipal= By.xpath("//span[contains(text(),'Yo soy el huésped principal')]");
    private By enterFinal= By.xpath("(//button[@type='submit'])[1]");
    private By textofinal= By.xpath("(//h2)[1]");


    public void reservar() throws InterruptedException {
        isDisplayed(reservarAhora);
        click(reservarAhora);
         }
    public void numeroDeHabitaciones(String cantidad){
        click(listaNroHabitaciones);
        selectCantidadValue(listaNroHabitaciones,cantidad); }
    public void clickReservare(){ click(btnReservare); }
    public void rellenarFormulario(String nombreP, String apellidoP, String emailP){
        isDisplayed(waitTitle);
        type(nombreP,nombre);
        type(apellidoP,apellido);
        type(emailP,email);
        type(emailP,confirmarEmail);
        click(radioButtonHuespedPrincipal);
        click(enterFinal);
    }
    public String validacionFinal(){
        isDisplayed(textofinal);
        String resultado="";
        if (getText(textofinal).equals("Los datos de tu reserva")){
            resultado = "reserva completa exitosa";
        }

        return resultado;
    }
}
