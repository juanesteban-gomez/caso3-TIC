public class ControladorMemoria {
    float memoriaLibre;
    float tamPagina;

    public ControladorMemoria(float memoria, float tamPagina) {
        this.memoriaLibre = memoria;
        this.tamPagina = tamPagina;
    }

    //pendiente de logica de control de memoria

    public float getMemoriaLibre() {
        return this.memoriaLibre;
    }
    public void asignarMemoria(float memoria) {
        if (memoria <= this.memoriaLibre) {
            this.memoriaLibre -= memoria;
        } else {
            throw new IllegalArgumentException("No hay suficiente memoria libre para asignar.");
        }
    }
    public void liberarMemoria(float memoria) {
        if (memoria >= 0) {
            this.memoriaLibre += memoria;
        } else {
            throw new IllegalArgumentException("La cantidad de memoria a liberar no puede ser negativa.");
        }       
    }
}
