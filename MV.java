import java.util.Random;

public class MV extends Thread {
    private Buzon buzon;
    private ZonaEspera zonaEspera;
    private Solicitud solicitud;
    private static GestorRecursos gestorRecursos = new GestorRecursos();

    @Override 
    public void run() {
        while (!gestorRecursos.reservarRecursos(solicitud)) {
        zonaEspera.entrar(this);
        wait();
        } 
    Random r = new Random();
    long sleepTime = 4000 + (long)(r.nextDouble() * (400000 - 4000));
    sleep(r.nextInt());
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
   



