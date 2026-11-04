import java.util.ArrayList;
public class ControladorAlmacenamiento {
    float almacenamiento;
    ArrayList<Bloque> bloquesLibres;
    ArrayList<Bloque> bloquesAsignados;
    int cantBloques;

    public ControladorAlmacenamiento(float almacenamiento, float tamBloque) {
        this.almacenamiento = almacenamiento;
        this.cantBloques = calcularCantidadBloques(almacenamiento, tamBloque);
        this.bloquesAsignados = new ArrayList<>();
        // Inicializar los bloques libres
        for (int i = 0; i < this.cantBloques; i++) {
            this.bloquesLibres.add(new Bloque((int) tamBloque, i));
        }
    }
    public int calcularCantidadBloques(float almacenamiento, float tamBloque) {
        // Convertimos almacenamiento de GiB a KiB

        //no estoy seguro todavia
        float almacenamientoKiB = almacenamiento * 1024 * 1024;

        // Calculamos la cantidad de bloques
        return (int) (almacenamientoKiB / tamBloque);
    }
}
