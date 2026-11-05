public class ZonaEspera {
    int umbral;
    int cantEspera;

    public ZonaEspera(int umbral) {
        this.umbral = umbral;
        this.cantEspera = 0;
    }

    public synchronized int getCantEspera() {
        return cantEspera;
    }

    public int getUmbral() {
        return umbral;
    }

    public synchronized void entrar() {
        while (cantEspera >= umbral) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cantEspera++;
    }

    public synchronized void salir(){
        cantEspera--;
        if (cantEspera == 0){
            notifyAll();
        }
    }
}
