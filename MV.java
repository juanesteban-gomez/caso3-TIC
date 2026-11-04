import java.util.Random;

public class MV extends Thread {
    private Buzon buzon;
    private ZonaEspera zonaEspera;
    private Solicitud solicitud;
    private GestorRecursos gestorRecursos;
    
    public MV(Buzon buzon2, ZonaEspera zonaEspera2, ControladorProcesadores gestorProcesador, ControladorMemoria gestorMemoria, ControladorAlmacenamiento gestorAlmacenamiento) {
        this.buzon = buzon2;
        this.zonaEspera = zonaEspera2;
        this.gestorRecursos = new GestorRecursos(gestorProcesador, gestorMemoria, gestorAlmacenamiento);
    }

    @Override 
    public void run() {
        while (!gestorRecursos.reservarRecursos(solicitud)) {
        zonaEspera.entrar(this);
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        } 
    Random r = new Random();
    long sleepTime = 4000 + (long)(r.nextInt(36001));
    try {
        sleep(sleepTime);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    }

    public Solicitud getSolicitud() {
        return solicitud;

    }
    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }
    public Buzon getBuzon() {
        return buzon;
    }
    public void setBuzon(Buzon buzon) {
        this.buzon = buzon;
    }
    public void setZonaEspera(ZonaEspera zonaEspera) {
        this.zonaEspera = zonaEspera;
    }
    public ZonaEspera getZonaEspera() {
        return zonaEspera;
    } }
