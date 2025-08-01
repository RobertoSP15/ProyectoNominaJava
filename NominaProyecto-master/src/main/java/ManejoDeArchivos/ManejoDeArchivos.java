package ManejoDeArchivos;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ManejoDeArchivos {

    private final Path rutaArchivo;

    public ManejoDeArchivos(String nombreArchivo) {
        String projectRoot = System.getProperty("user.dir");
        this.rutaArchivo = Paths.get(projectRoot, "Data", nombreArchivo); 

        System.out.println("Ruta de archivo definitiva en uso: " + rutaArchivo.toAbsolutePath().toString());
        crearArchivoSiNoExiste();
    }

    private void crearArchivoSiNoExiste() {
        try {
            if (Files.notExists(rutaArchivo.getParent())) {
                Files.createDirectories(rutaArchivo.getParent());
            }
            if (Files.notExists(rutaArchivo)) {
                Files.createFile(rutaArchivo);
            }
        } catch (IOException e) {
            System.err.println("Error al crear el archivo o directorio: " + e.getMessage());
        }
    } 
    
    public List<String> leerTodasLasLineas() throws IOException {
        return Files.readAllLines(rutaArchivo);
    }

    public void agregarLinea(String nuevaLinea) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo.toFile(), true))) {
            writer.write(nuevaLinea);
            writer.newLine();
        }
    }

    private void escribirTodasLasLineas(List<String> lineas) throws IOException {
        Files.write(rutaArchivo, lineas);
    }

    public boolean modificarLinea(String idBusqueda, String nuevaLinea, int columna) throws IOException {
        List<String> lineas = leerTodasLasLineas();
        boolean modificado = false;
        for (int i = 0; i < lineas.size(); i++) {
            String[] partes = lineas.get(i).split(",");
            if (partes.length > columna && partes[columna].trim().equals(idBusqueda)) {
                lineas.set(i, nuevaLinea);
                modificado = true;
                break; 
            }
        }
        if (modificado) {
            escribirTodasLasLineas(lineas);
        }
        return modificado;
    }

    // MÉTODO RESTAURADO
    public boolean eliminarLinea(String idBusqueda, int columna) throws IOException {
        List<String> lineasActuales = leerTodasLasLineas();
        List<String> lineasNuevas = new ArrayList<>();
        boolean eliminado = false;
        for (String linea : lineasActuales) {
            String[] partes = linea.split(",");
            if (partes.length > columna && partes[columna].trim().equals(idBusqueda)) {
                eliminado = true; 
            } else {
                lineasNuevas.add(linea);
            }
        }
        if (eliminado) {
            escribirTodasLasLineas(lineasNuevas);
        }
        return eliminado;
    }
    
    // MÉTODO RESTAURADO
    public boolean lineaExiste(String idBusqueda, int columna) throws IOException {
        List<String> lineas = leerTodasLasLineas();
        for (String linea : lineas) {
            String[] partes = linea.split(",");
            if (partes.length > columna && partes[columna].trim().equals(idBusqueda)) {
                return true;
            }
        }
        return false;
    }
}