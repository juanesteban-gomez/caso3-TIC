import java.util.Random;

public class MV extends Thread {
    private Buzon buzon;
    private ZonaEspera zonaEspera;
    private Solicitud solicitud;
    private GestorRecursos gestorRecursos;
    
    public MV(Buzon buzon2, ZonaEspera zonaEspera2, ControladorProcesadores gestorProcesador, ControladorMemoria gestorMemoria, ControladorAlmacenamiento gestorAlmacenamiento, Solicitud solicitud) {
        this.buzon = buzon2;
        this.zonaEspera = zonaEspera2;
        this.gestorRecursos = new GestorRecursos(gestorProcesador, gestorMemoria, gestorAlmacenamiento);
        this.solicitud = solicitud;
    }

    @Override 
    public void run() {
        while (!gestorRecursos.reservarRecursos(solicitud)) {
            Thread.yield();
        }
        Random r = new Random();
        long sleepTime = 4000 + (long)(r.nextInt(36001));
        try {
            sleep(0); //Cambiar por sleepTime cuando funcione
            System.out.println("Realizado");
            gestorRecursos.liberarRecursos(solicitud);
            zonaEspera.salir();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
