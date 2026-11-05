public class ZonaEspera {
    int umbral;
    int cantEspera;

    public ZonaEspera(int umbral) {
        this.umbral = umbral;
        this.cantEspera = 0;
    }

    public float getCantEspera() {
        return cantEspera;
    }

    public float getUmbral() {
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

    public void salir(){
        cantEspera--;
        if (cantEspera == 0){
            notifyAll();
        }
    }
}
