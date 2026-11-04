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

    public boolean crearVM (){
        if (zonaEspera.cantEspera < zonaEspera.umbral) {
            MV mv = new MV(buzon, zonaEspera, gestorProcesador, gestorMemoria, gestorAlmacenamiento);
            mv.start();
            return true;
        }
        return false;
    }

    @Override 
    public void run(){
        while (buzon.getUsuariosActivos() > 0) {
            Solicitud solicitud = buzon.consumirSolicitud();
            if (solicitud!=null) {
                MV mv = new MV(buzon, zonaEspera, gestorProcesador, gestorMemoria, gestorAlmacenamiento);
                mv.setSolicitud(solicitud);
                mv.setBuzon(this.buzon);
                mv.setZonaEspera(this.zonaEspera);
                mv.start();
            } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        }
    }
}