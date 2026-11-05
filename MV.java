import java.util.Random;

public class MV extends Thread {
    private final Buzon buzon;
    private final ZonaEspera zonaEspera;
    private final Solicitud solicitud;
    private final GestorRecursos gestorRecursos;
    
    public MV(Buzon buzon2, ZonaEspera zonaEspera2, GestorRecursos gestorRecursos, Solicitud solicitud) {
        this.buzon = buzon2;
        this.zonaEspera = zonaEspera2;
        this.gestorRecursos = gestorRecursos;
        this.solicitud = solicitud;
    }

    @Override 
    public void run() {
        gestorRecursos.reservarRecursos(solicitud);
        zonaEspera.salir();
        Random r = new Random();
        long sleepTime = 4000 + (long)(r.nextInt(36001));
        try {
            sleep(sleepTime);
            gestorRecursos.liberarRecursos(solicitud);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Realizado");
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public Buzon getBuzon() {
        return buzon;
    }

    public ZonaEspera getZonaEspera() {
        return zonaEspera;
    } }
