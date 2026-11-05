public class ControladorRecursos {
    private final String[] duenos;
    private final double tamUnidadKiB;
    private int libres;

    public ControladorRecursos(int totalUnidades, double tamUnidadKiB) {
        this.duenos = new String[totalUnidades];
        this.tamUnidadKiB = tamUnidadKiB;
        this.libres = totalUnidades;
    }

    public int unidadesParaGiB(float gib) {
        return (int) Math.max(1, Math.round(gib * 1024.0 * 1024.0 / tamUnidadKiB));
    }

    public synchronized boolean reservar(String dueno, int unidades) {
        if (unidades > libres) return false;
        int asignadas = 0;
        for (int i = 0; i < duenos.length && asignadas < unidades; i++) {
            if (duenos[i] == null) { duenos[i] = dueno; asignadas++; }
        }
        libres -= unidades;
        return true;
    }

    public synchronized void liberar(String dueno) {
        for (int i = 0; i < duenos.length; i++) {
            if (dueno.equals(duenos[i])) { duenos[i] = null; libres++; }
        }
    }

    public synchronized int getLibres() { return libres; }
    public int getTotal() { return duenos.length; }
}