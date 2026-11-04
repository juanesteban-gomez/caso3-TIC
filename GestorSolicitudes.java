public class GestorSolicitudes extends Thread {
    Buzon buzon;
    ZonaEspera zonaEspera;
    public GestorSolicitudes( Buzon buzon, ZonaEspera zonaEspera) {
        this.buzon = buzon;
    }

    @Override 
    public void run(){
        while (buzon.getUsuariosActivos() > 0) {
            Solicitud solicitud = buzon.consumirSolicitud();
            if (solicitud!=null) {
                
            }
        }
    }
}