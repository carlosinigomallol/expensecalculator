package es.expensecalculator.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import es.expensecalculator.model.configuration.AppKeys;
import es.expensecalculator.model.configuration.Configurador;


/**
 * Utilidades para la gestión de los ficheros de los procesos
 */
public class UtilFile {

    /**
     * Crea una respuesta que se visulaizara en el response de la peticion.
     * 
     * @param filePath the file path
     * @param response the response
     * @return the file
     * @throws Throwable the throwable
     */
    public File createFile(String filePath, HttpServletResponse response) {
        File file = new File(filePath);
        int read = 0;
        byte[] bytes = new byte[1024];

        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(file);

            os = response.getOutputStream();
            while ((read = fis.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

//    /**
//     * Gets the file path.
//     *
//     * @param nameJob the name job
//     * @return the file path
//     */
//    public List<String> getFilePath(String nameJob) {
//        String cadena = Configurador.getProperty(AppKeys.expensecalculator_DIRECTORIO_CONFIGURACION);
//        List<String> pathsDirectory = new ArrayList<String>();
//        ProcessRedirectorEnum[] processRedirectorEnums = ProcessRedirectorEnum.values();
//        for (ProcessRedirectorEnum p : processRedirectorEnums) {
//            String[] processes = p.getProcessArray();
//            for (String name : processes) {
//                if (name.equals(nameJob)) {
//                    pathsDirectory.add(cadena.concat(p.getPath()));
//                }
//            }
//        }
//        return pathsDirectory;
//    }
}
