import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class ImportarArchivoTxteing {

    public LinkedList<prestamoseingenieria> leerArchivo(String nombreArchivo) {
        String rutaArchivo = nombreArchivo + ".txt";
        LinkedList<prestamoseingenieria> prestamoseingenieriaes = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String line;
            prestamoseingenieria prestamoseingenieria = null;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("-")) {
                    continue;
                }

                if (line.startsWith("Cedula: ")) {
                    if (prestamoseingenieria != null) {
                        prestamoseingenieriaes.add(prestamoseingenieria);
                    }
                    prestamoseingenieria = new prestamoseingenieria();
                    prestamoseingenieria.setCedula(line.substring(9).trim());
                } else if (line.startsWith("Nombre: ") && line.length() > 9) {
                    if (prestamoseingenieria != null) {
                        prestamoseingenieria.setNombre(line.substring(9).trim());
                    }
                } else if (line.startsWith("Apellido: ") && line.length() > 9) {
                    if (prestamoseingenieria != null) {
                        prestamoseingenieria.setApellido(line.substring(9).trim());
                    }
                } else if (line.startsWith("Telefono: ") && line.length() > 11) {
                    if (prestamoseingenieria != null) {
                        prestamoseingenieria.setTelefono(line.substring(11).trim());
                    }
                } else if (line.startsWith("Numero de semestre: ") && line.length() > 21) {
                    if (prestamoseingenieria != null) {
                        prestamoseingenieria.setNumSemestre(Integer.parseInt(line.substring(20).trim()));
                    }
                } else if (line.startsWith("Promedio: ") && line.length() > 11) {
                    if (prestamoseingenieria != null) {
                        prestamoseingenieria.setPromedio(Float.parseFloat(line.substring(11).trim()));
                    }
                } else if (line.startsWith("Serial: ") && line.length() > 9) {
                    if (prestamoseingenieria != null) {
                        prestamoseingenieria.setSerial(line.substring(9).trim());
                    }
                }
            }

            if (prestamoseingenieria != null) {
                prestamoseingenieriaes.add(prestamoseingenieria);
            }
         
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prestamoseingenieriaes;
    }
}