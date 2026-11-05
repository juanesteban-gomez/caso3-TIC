import java.util.ArrayList;
import java.util.List;

public class GestorSolicitudes extends Thread {
    private final Buzon buzon;
    private final ZonaEspera zonaEspera;
    private final GestorRecursos gestorRecursos;
    private final List<MV> maquinasVirtuales = new ArrayList<>();

    public GestorSolicitudes( Buzon buzon, ZonaEspera zonaEspera, GestorRecursos gestorRecursos) {
        this.buzon = buzon;
        this.zonaEspera = zonaEspera;
        this.gestorRecursos = gestorRecursos;
    }

    @Override
    public void run(){
        while (!buzon.termino() ) {
            zonaEspera.entrar();
            Solicitud solicitud = buzon.consumirSolicitud();
            if (solicitud == null){
                zonaEspera.salir();
                Thread.yield();
            }
            else{
                MV mv = new MV(buzon, zonaEspera, gestorRecursos, solicitud);
                maquinasVirtuales.add(mv);
                mv.start();
            }
        }
    }

    public List<MV> getMaquinasVirtuales() {
        return maquinasVirtuales;
    }
}