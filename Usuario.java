public class Usuario extends Thread{
    int id;
    int nSolicitudes;
    Buzon buzon;
    int maxProc;
    int maxMem;
    int maxDisco;
    public Usuario(int id, int nSolicitudes, Buzon buzon, int maxProc, int maxMem, int maxDisco){
        this.id = id;
        this.nSolicitudes = nSolicitudes;
        this.buzon = buzon;
        this.maxDisco  = maxDisco;
        this.maxMem = maxMem;
        this.maxProc = maxProc;
        
    }

    public Solicitud crearSolicitud(String id){
        int nProcesadores = 1 + (int) (Math.random() * maxProc);
        int memoria = Integer.highestOneBit(1 + (int) (Math.random() * maxMem)); // potencia de 2 <= valor
        int espacioDisco = 1 + (int) (Math.random() * maxDisco);
        if (espacioDisco > 1) espacioDisco -= espacioDisco % 2;                   // par <= valor

        Solicitud solicitud = new Solicitud(id, nProcesadores, memoria, espacioDisco);
        return solicitud;
    }

    @Override 
    public void run(){
        while (nSolicitudes > 0) {
                Solicitud solicitud = crearSolicitud(String.format("%d_Solicitud_%d", id, nSolicitudes));
                buzon.generarSolicitud(solicitud);
                nSolicitudes--;
            }
        buzon.disminuirUsuariosActivos();
    }
}
