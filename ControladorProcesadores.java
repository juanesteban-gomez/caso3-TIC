import java.util.ArrayList;

public class ControladorProcesadores {
    private ArrayList<Procesador> procesadoresLibres;
    private ArrayList<Procesador> procesadoresAsignados;

    public ControladorProcesadores(int nProcesadores) {
        procesadoresLibres = new ArrayList<>();
        procesadoresAsignados = new ArrayList<>();
        for (int i = 0; i < nProcesadores; i++) {
            procesadoresLibres.add(new Procesador(i));
        }
    }

    public int getProcesadoresLibres() {
        return procesadoresLibres.size();
    }
    public int getProcesadoresAsignados() {
        return procesadoresAsignados.size();
    }

    public void asignarProcesadores(int n) {
        for (int i = 0; i < n; i++) {
            if (!procesadoresLibres.isEmpty()) {
                Procesador p = procesadoresLibres.remove(0);
                procesadoresAsignados.add(p);
            }
        }
    }
    public void liberarProcesadores(int n) {
        for (int i = 0; i < n; i++) {
            if (!procesadoresAsignados.isEmpty()) {
                Procesador p = procesadoresAsignados.remove(0);
                procesadoresLibres.add(p);
            }
        }
    }
}
