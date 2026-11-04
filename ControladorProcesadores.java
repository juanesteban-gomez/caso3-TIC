import java.util.ArrayList;

public class ControladorProcesadores {
    ArrayList<Procesador> procesadoresLibres;
    ArrayList<Procesador> procesadoresAsignados;

    public ControladorProcesadores(int nProcesadores) {
        procesadoresLibres = new ArrayList<>();
        procesadoresAsignados = new ArrayList<>();
        for (int i = 0; i < nProcesadores; i++) {
            procesadoresLibres.add(new Procesador(i));
        }
    }
}
