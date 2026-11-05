import java.util.LinkedList;
import java.util.Queue;

public class Buzon {
    int capacidad;
    int usuariosActivos;
    Queue<Solicitud> solicitudes = new LinkedList<>();
    public Buzon(int capacidad, int nUsuarios) {
        this.capacidad = capacidad;
        this.usuariosActivos = nUsuarios;
    }

    public synchronized boolean termino(){
        return usuariosActivos == 0 && solicitudes.isEmpty();
    }

    public synchronized int getCapacidad() {
        return capacidad;
    }

    public synchronized void disminuirUsuariosActivos(){
        this.usuariosActivos--;
    }

    public synchronized int getUsuariosActivos() {
        return usuariosActivos;
    }

    public synchronized void generarSolicitud (Solicitud solicitud){
        while (this.capacidad == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        solicitudes.offer(solicitud);
        this.capacidad--;
    }

    public synchronized Solicitud consumirSolicitud (){
        Solicitud solicitud = solicitudes.poll();
        if (solicitud != null){
            capacidad++;
            notify();
        }
        return solicitud;
    }
}
