public class GestorSolicitudes extends Thread {
    Buzon buzon;
    ZonaEspera zonaEspera;
    ControladorProcesadores gestorProcesador;
    ControladorMemoria gestorMemoria;
    ControladorAlmacenamiento gestorAlmacenamiento;

    public GestorSolicitudes( Buzon buzon, ZonaEspera zonaEspera, ControladorProcesadores gestorProcesador, ControladorMemoria gestorMemoria, ControladorAlmacenamiento gestorAlmacenamiento) {
        this.buzon = buzon;
        this.zonaEspera = zonaEspera;
        this.gestorProcesador = gestorProcesador;
        this.gestorMemoria = gestorMemoria;
        this.gestorAlmacenamiento = gestorAlmacenamiento;
    }

    @Override
    public void run(){
        while (buzon.getUsuariosActivos() < 0 || buzon.termino() ) {
                Solicitud solicitud = buzon.consumirSolicitud();
                if (solicitud == null){
                    Thread.yield();
                }
                else{
                    zonaEspera.entrar();
                    MV mv = new MV(buzon, zonaEspera, gestorProcesador, gestorMemoria, gestorAlmacenamiento, solicitud);
                    mv.start();
                }
        }
    }
}