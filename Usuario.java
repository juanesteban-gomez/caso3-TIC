public class Usuario extends Thread{
    int id;
    int nSolicitudes;
    Buzon buzon;
    public Usuario(int id, int nSolicitudes, Buzon buzon){
        this.id = id;
        this.nSolicitudes = nSolicitudes;
        this.buzon = buzon;
    }

    public Solicitud crearSolicitud(String id){
        int nProcesadores = (int) (Math.random() * 4 + 1);
        float memoria = (float) (Math.random() * 8 + 1);
        float espacioDisco = (float) (Math.random() * 100 + 1);
        Solicitud solicitud = new Solicitud(id, nProcesadores, memoria, espacioDisco);
        return solicitud;
    }

    @Override 
    public void run(){
        while (nSolicitudes > 0) {
                Solicitud solicitud = crearSolicitud(String.format("%d_Solicitud_%d", id, nSolicitudes));
                buzon.generarSolicitud(solicitud);
            }
        buzon.disminuirUsuariosActivos();
    }
}
