public class GestorRecursos {
private final ControladorProcesadores controladorProcesadores;
private final ControladorMemoria controladorMemoria;
private final ControladorAlmacenamiento controladorDisco;

public GestorRecursos(ControladorProcesadores controladorProcesadores, ControladorMemoria controladorMemoria, ControladorAlmacenamiento controladorDisco) {
    this.controladorProcesadores = controladorProcesadores;
    this.controladorMemoria = controladorMemoria;
    this.controladorDisco = controladorDisco;
}

    public synchronized void reservarRecursos (Solicitud solicitud) {
        Boolean asigno = false;
        while (!asigno) {
            if (this.controladorProcesadores.getProcesadoresLibres() >= solicitud.nProcesadores) {
                this.controladorProcesadores.asignarProcesadores(solicitud.nProcesadores);
                if (this.controladorMemoria.getMemoriaLibre() >= solicitud.memoria){
                    this.controladorMemoria.asignarMemoria(solicitud.memoria);
                    if (this.controladorDisco.getAlmacenamientoLibre() >= solicitud.espacioDisco) {
                        this.controladorDisco.asignarAlmacenamiento(solicitud.espacioDisco);
                        asigno = true;
                    } else {
                        this.controladorProcesadores.liberarProcesadores(solicitud.nProcesadores);
                        this.controladorMemoria.liberarMemoria(solicitud.memoria);
                    }
                } else {
                    this.controladorProcesadores.liberarProcesadores(solicitud.nProcesadores);
                }
            }
            if (!asigno) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void liberarRecursos (Solicitud solicitud) {
        this.controladorProcesadores.liberarProcesadores(solicitud.nProcesadores);
        this.controladorMemoria.liberarMemoria(solicitud.memoria);
        this.controladorDisco.liberarAlmacenamiento(solicitud.espacioDisco);
        notifyAll();
    }

    public synchronized void liberarProcesadores(int n) {
        this.controladorProcesadores.liberarProcesadores(n);
    }
    public synchronized void liberarMemoria(float memoria) {
        this.controladorMemoria.liberarMemoria(memoria);
    }
    public synchronized void liberarAlmacenamiento(float espacioDisco) {
        this.controladorDisco.liberarAlmacenamiento(espacioDisco);
    }

}
