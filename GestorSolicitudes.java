public class GestorSolicitudes extends Thread {
    Buzon buzon;
    ZonaEspera zonaEspera;
    public GestorSolicitudes( Buzon buzon, ZonaEspera zonaEspera) {
        this.buzon = buzon;
        this.zonaEspera = zonaEspera;
    }
}