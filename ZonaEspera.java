public class ZonaEspera {
    float umbral;
    float cantEspera;

    public ZonaEspera(float umbral) {
        this.umbral = umbral;
        this.cantEspera = 0;
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
