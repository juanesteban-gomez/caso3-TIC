public class GestorRecursos {
    private final ControladorRecursos cpu, mem, disco;

    public GestorRecursos(ControladorRecursos cpu, ControladorRecursos mem, ControladorRecursos disco) {
        this.cpu = cpu;
        this.mem = mem;
        this.disco = disco;
    }

    private boolean intentarReservar(Solicitud s) {
        if (!cpu.reservar(s.id, s.nProcesadores)) return false;
        if (!mem.reservar(s.id, mem.unidadesParaGiB(s.memoria))) {
            cpu.liberar(s.id);
            return false;
        }
        if (!disco.reservar(s.id, disco.unidadesParaGiB(s.espacioDisco))) {
            mem.liberar(s.id);
            cpu.liberar(s.id);
            return false;
        }
        return true;
    }

    public synchronized void reservarRecursos(Solicitud s) {
        while (!intentarReservar(s)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void liberarRecursos(Solicitud s) {
        cpu.liberar(s.id);
        mem.liberar(s.id);
        disco.liberar(s.id);
        notifyAll();
    }
}