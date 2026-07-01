package it.unisa.seicentools.application.productMGMT.interfaces;

import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

public interface ImgServiceInterface {
    public String saveImage(Part filePart, String basePath) throws IOException;
    public File getImage(String basePath, String fileName);
    public boolean deleteImage(String basePath, String fileName);
    public void imgMGMT(String uploadPath, Part img) throws IOException;
}
