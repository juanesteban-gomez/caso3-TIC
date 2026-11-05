public class GestorSolicitudes extends Thread {
    Buzon buzon;
    ZonaEspera zonaEspera;
    ControladorProcesadores gestorProcesador;
    ControladorMemoria gestorMemoria;
    ControladorAlmacenamiento gestorAlmacenamiento;

    public GestorSolicitudes( Buzon buzon, ZonaEspera zonaEspera, ControladorProcesadores gestorProcesador, ControladorMemoria gestorMemoria, ControladorAlmacenamiento gestorAlmacenamiento) {
        this.buzon = buzon;
        this.gestorProcesador = gestorProcesador;
        this.gestorMemoria = gestorMemoria;
        this.gestorAlmacenamiento = gestorAlmacenamiento;
    }

    @Override 
    public void run(){
        while (buzon.getUsuariosActivos() > 0) {
            Solicitud solicitud = buzon.consumirSolicitud();
            if (solicitud!=null && zonaEspera.cantEspera < zonaEspera.umbral) {
                MV mv = new MV(buzon, zonaEspera, gestorProcesador, gestorMemoria, gestorAlmacenamiento, solicitud);
                mv.setSolicitud(solicitud);
                mv.setBuzon(this.buzon);
                mv.setZonaEspera(this.zonaEspera);
                mv.start();
            }
        }
        }
}