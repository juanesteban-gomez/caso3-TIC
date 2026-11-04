public class GestorSolicitudes extends Thread {
    Buzon buzon;
    ZonaEspera zonaEspera;
    public GestorSolicitudes( Buzon buzon, ZonaEspera zonaEspera) {
        this.buzon = buzon;
    }

    public boolean crearVM (){
        if (zonaEspera.cantEspera < zonaEspera.umbral) {
            MV mv = new MV();
            mv.setBuzon(this.buzon);
            mv.setZonaEspera(this.zonaEspera);
            mv.start();
            return true;
        }
        return false;
    }

    @Override 
    public void run(){
        while (buzon.getUsuariosActivos() > 0) {
            Solicitud solicitud = buzon.consumirSolicitud();
            if (solicitud!=null) {
                
            }
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}