import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        
        // Atributos de configuración inicializados en 0
        int nu = 0;          // Número de usuarios
        int base = 0;        // Número base de eventos
        int ng = 0;          // Número de gestores
        int tam1 = 0;        // Capacidad del buzón de entrada
        int tam2 = 0;        // Umbral de la zona de espera
        int nProcesadores = 0;// Número de procesadores
        float memoria = 0;     // Capacidad en memoria (GiB)
        float tamPagina = 0;      // Tamaño de página (KiB)
        float almacenamiento = 0; // Capacidad en almacenamiento (GiB)
        float tamBloque = 0;      // Tamaño de bloque (KiB)

        // Ruta del archivo a leer
        String rutaArchivo = "config.txt"; 

        try {
            // Objeto File y Scanner para la lectura
            File archivo = new File(rutaArchivo);
            Scanner scanner = new Scanner(archivo);

            // Leemos secuencialmente los enteros del archivo
            if (scanner.hasNextInt()) nu = scanner.nextInt();
            if (scanner.hasNextInt()) base = scanner.nextInt();
            if (scanner.hasNextInt()) ng = scanner.nextInt();
            if (scanner.hasNextInt()) tam1 = scanner.nextInt();
            if (scanner.hasNextInt()) tam2 = scanner.nextInt();
            if (scanner.hasNextInt()) nProcesadores = scanner.nextInt();
            if (scanner.hasNextFloat()) memoria = scanner.nextFloat();
            if (scanner.hasNextFloat()) tamPagina = scanner.nextFloat();
            if (scanner.hasNextFloat()) almacenamiento = scanner.nextFloat();
            if (scanner.hasNextFloat()) tamBloque = scanner.nextFloat();

            // Cerramos el scanner para liberar recursos
            scanner.close(); 
            System.out.println("Archivo leído exitosamente.");

        } catch (FileNotFoundException e) {
            System.out.println("Error: No se encontró el archivo.");
            e.printStackTrace();
            return; 
        }

        // Aqui pasamos los valores leidos a los constructores de los monitores
        Buzon buzon = new Buzon(tam1, nu);
        ZonaEspera zonaEspera = new ZonaEspera(tam2);
        ControladorProcesadores ctrlProc = new ControladorProcesadores(nProcesadores);
        ControladorMemoria ctrlMem = new ControladorMemoria(memoria, tamPagina);
        ControladorAlmacenamiento ctrlAlm = new ControladorAlmacenamiento(almacenamiento, tamBloque);
        
        // Creamos y lanzamos los diferentes hilos 
        for (int i=0; i<nu;i++){
            Usuario usuario = new Usuario(i, base, buzon);
            usuario.start();
        }
        for (int i=0; i<ng;i++){
            GestorSolicitudes gestor = new GestorSolicitudes(buzon, zonaEspera);
            gestor.start();
        }
    }
}