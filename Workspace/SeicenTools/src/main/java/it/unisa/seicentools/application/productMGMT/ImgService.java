package it.unisa.seicentools.application.productMGMT;

import it.unisa.seicentools.application.productMGMT.interfaces.ImgServiceInterface;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ImgService implements ImgServiceInterface {

    private static final String UPLOAD_DIR = "/imgs/products";

    @Override
    public String saveImage(Part filePart, String basePath) throws IOException {

        if (filePart == null || filePart.getSize() == 0) {
            return null;
        }

        // validazione base
        String contentType = filePart.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IOException("File non valido: non è un'immagine");
        }

        // crea directory se non esiste
        File uploadFolder = new File(basePath + UPLOAD_DIR);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        // nome sicuro con un random UUID
        String extension = getExtension(filePart.getSubmittedFileName());
        String fileName = UUID.randomUUID() + extension;

        // salva file
        filePart.write(uploadFolder.getAbsolutePath() + File.separator + fileName);

        return fileName;
    }

    //Recupera l'immagine come file
    @Override
    public File getImage(String basePath, String fileName) {

        if (fileName == null || fileName.isEmpty()) {
            return null;
        }

        return new File(basePath + UPLOAD_DIR, fileName);
    }

    //Elimina immagine
    @Override
    public boolean deleteImage(String basePath, String fileName) {

        File file = getImage(basePath, fileName);

        return file != null && file.exists() && file.delete();
    }

    private String getExtension(String fileName) {

        if (fileName == null) return "";

        int dot = fileName.lastIndexOf('.');
        if (dot == -1) return "";

        return fileName.substring(dot);
    }

    /*
    @Override
    public void imgMGMT(String uploadPath, Part img) throws IOException {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // nome file sicuro
        String fileName = UUID.randomUUID() + "_" + img.getSubmittedFileName();

        // salvataggio fisico
        img.write(uploadPath + File.separator + fileName);
    }
     */
}