public class Solicitud {
    String id;
    int nProcesadores;
    float memoria;
    float espacioDisco;

    public Solicitud(String id, int nProcesadores2, float memoria2, float espacioDisco2) {
        this.id = id;
        this.nProcesadores = nProcesadores2;
        this.memoria = memoria2;
        this.espacioDisco = espacioDisco2;
    }
}