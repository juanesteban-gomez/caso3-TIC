import java.util.ArrayList;
public class ControladorAlmacenamiento {
    float almacenamiento;
    ArrayList<Bloque> bloquesLibres = new ArrayList<>();
    ArrayList<Bloque> bloquesAsignados;
    int cantBloques;
    float tamBloque;

    public ControladorAlmacenamiento(float almacenamiento, float tamBloque) {
        this.almacenamiento = almacenamiento;
        this.tamBloque = tamBloque;
        this.cantBloques = calcularCantidadBloques(almacenamiento, tamBloque);
        this.bloquesAsignados = new ArrayList<>();
        // Inicializar los bloques libres
        for (int i = 0; i < this.cantBloques; i++) {
            this.bloquesLibres.add(new Bloque((float) tamBloque, i));
        }
    }
    public int calcularCantidadBloques(float almacenamiento, float tamBloque) {
        // Convertimos almacenamiento de GiB a KiB

        //no estoy seguro todavia
        float almacenamientoKiB = almacenamiento * 1024 * 1024;

        // Calculamos la cantidad de bloques
        return (int) (almacenamientoKiB / tamBloque);
    }
    public int getAlmacenamientoLibre() {
        return bloquesLibres.size();
    }
    public boolean asignarAlmacenamiento (float espacioDisco) {
        int bloquesNecesarios = calcularCantidadBloques(espacioDisco, this.tamBloque);
        if (bloquesNecesarios <= bloquesLibres.size()) {
            for (int i = 0; i < bloquesNecesarios; i++) {
                Bloque bloque = bloquesLibres.remove(0);
                bloquesAsignados.add(bloque);
            }
            return true;
        } else {
            return false;
        }
    }
    public void liberarAlmacenamiento(float espacioDisco) {
        int bloquesNecesarios = calcularCantidadBloques(espacioDisco, this.tamBloque);
        for (int i = 0; i < bloquesNecesarios; i++) {
            if (!bloquesAsignados.isEmpty()) {
                Bloque bloque = bloquesAsignados.remove(0);
                bloquesLibres.add(bloque);
            }
        }
    }
}
