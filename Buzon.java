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

    public int getCapacidad() {
        return capacidad;
    }

    public synchronized void disminuirUsuariosActivos(){
        this.usuariosActivos--;
    }

    public int getUsuariosActivos() {
        return usuariosActivos;
    }

    public synchronized boolean generarSolicitud (Solicitud solicitud){
        if (this.capacidad > 0) {
            solicitudes.offer(solicitud);
            this.capacidad--;
            return true;
        }
        else {
            return false;
        }
    }

    public synchronized Solicitud consumirSolicitud (){
        while (solicitudes.isEmpty()) {
            Thread.yield();
        }
        return solicitudes.poll();
    }
}
