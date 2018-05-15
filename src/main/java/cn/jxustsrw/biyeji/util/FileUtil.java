package cn.jxustsrw.biyeji.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileUtil {

    public Map fileUpload(MultipartFile file) throws IOException {
        Map<String, Object> result = new HashMap<>();
        DateFormat simpleFormatter = new SimpleDateFormat("yyyyMMdd");
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String suffix = "null";//后缀
            if (fileName != null) {
                suffix = fileName.substring(fileName.lastIndexOf('.') + 1);
            }
            File path = new File("/FaceIn/" + suffix + "/" + simpleFormatter.format(new Date()) + "/");
            if (!path.exists()) path.mkdirs();
            try (BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(new File(path, file.getOriginalFilename())))) {
                out.write(file.getBytes());
                out.flush();
                result.put("result", true);
                result.put("uri", path + "/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                result.put("result", false);
                result.put("massage", e.getMessage());
            }
        } else {
            result.put("result", false);
            result.put("massage", "file is null");
        }
        return result;
    }

    public Map imageUpload(String image, String fileName) throws IOException {
        Map<String, Object> result = new HashMap<>();
        DateFormat simpleFormatter = new SimpleDateFormat("yyyyMMdd");
        if (!image.isEmpty()) {
            String suffix = fileName.substring(fileName.lastIndexOf('.') + 1);//后缀
            File path = new File("/FaceIn/" + suffix + "/" + simpleFormatter.format(new Date()) + "/");
            if (!path.exists()) path.mkdirs();
            try (BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(new File(path, fileName)))) {
                out.write(image.getBytes(Charset.forName("UTF-8")));
                out.flush();
                result.put("result", true);
                result.put("uri", path + "/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                result.put("result", false);
                result.put("massage", e.getMessage());
            }
        } else {
            result.put("result", false);
            result.put("massage", "file is null");
        }
        return result;
    }
}
