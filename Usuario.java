public class Usuario extends Thread{
    int id;
    int nSolicitudes;
    public Usuario(int id, int nSolicitudes){
        this.id = id;
        this.nSolicitudes = nSolicitudes;
    }

    public Solicitud crearSolicitud(int id, int nSolicitudes){
        Solicitud solicitud = new Solicitud();
        solicitud.nProcesadores = (int) (Math.random() * 4 + 1);
        solicitud.memoria = (float) (Math.random() * 8 + 1);
        solicitud.espacioDisco = (float) (Math.random() * 100 + 1);
        return solicitud;
    }
}
