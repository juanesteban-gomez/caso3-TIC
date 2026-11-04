import java.util.LinkedList;
import java.util.Queue;

public class ZonaEspera {
    float umbral;
    float cantEspera;
    Queue<MV> mvs = new LinkedList<>();

    public ZonaEspera(float umbral) {
        this.umbral = umbral;
        this.cantEspera = 0;
    }

    public synchronized boolean entrar(MV mv) {
        if (cantEspera < umbral) {
            mvs.offer(mv);
            cantEspera++;
            return true;
        } else {
            return false;
        }
        
    }
    

}
