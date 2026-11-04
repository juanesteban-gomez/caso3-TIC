public class GestorRecursos {


    //deberian ir static????


private ControladorProcesadores controladorProcesadores;
private ControladorMemoria controladorMemoria;
private ControladorAlmacenamiento controladorDisco;

public GestorRecursos(ControladorProcesadores controladorProcesadores, ControladorMemoria controladorMemoria, ControladorAlmacenamiento controladorDisco) {
    this.controladorProcesadores = controladorProcesadores;
    this.controladorMemoria = controladorMemoria;
    this.controladorDisco = controladorDisco;
}

    public synchronized boolean reservarRecursos (Solicitud solicitud) {
        if (this.controladorProcesadores.getProcesadoresLibres() >= solicitud.nProcesadores) {
            this.controladorProcesadores.asignarProcesadores(solicitud.nProcesadores);

            if ( this.controladorMemoria.getMemoriaLibre() >= solicitud.memoria){
                this.controladorMemoria.asignarMemoria(solicitud.memoria);

                if (this.controladorDisco.getAlmacenamientoLibre() >= solicitud.espacioDisco) {
                    this.controladorDisco.asignarAlmacenamiento(solicitud.espacioDisco);
                    return true;
                } else {
                    this.controladorProcesadores.liberarProcesadores(solicitud.nProcesadores);
                    this.controladorMemoria.liberarMemoria(solicitud.memoria);
                    return false;
                }

            } else {
                this.controladorProcesadores.liberarProcesadores(solicitud.nProcesadores);
                return false;
            }
        } else {
            return false;
        }
    }

}
