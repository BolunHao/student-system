package com.management.ftp;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * Upload Assist Class
 */
public final class UploadUtil {


    //private static final Logger logger = LogManager.getLogger();

    /**
     * Upload File Cache Size Limit
     */
    private static int fileSizeThreshold = 1024 * 1024 * 1;

    /**
     * 上传文件临时目录
     */
    private static final String uploadFileDir = "/uploads/";

    /**
     * 获取所有文本域
     *
     * @param request
     * @param saveDir
     */
    public static final List<?> getFileItemList(HttpServletRequest request, File saveDir) throws FileUploadException {
        if (!saveDir.isDirectory()) {
            saveDir.mkdir();
        }
        List<?> fileItems = null;
        RequestContext requestContext = new ServletRequestContext(request);
        if (FileUpload.isMultipartContent(requestContext)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setRepository(saveDir);
            factory.setSizeThreshold(fileSizeThreshold);
            ServletFileUpload upload = new ServletFileUpload(factory);
            fileItems = upload.parseRequest(request);
        }
        return fileItems;
    }

    /**
     * 获取文本域
     *
     * @param request
     * @param saveDir
     * @param fieldName
     */
    public static final FileItem[] getFileItem(HttpServletRequest request, File saveDir, String... fieldName)
            throws FileUploadException {
        if (fieldName == null || saveDir == null) {
            return null;
        }
        List<?> fileItemList = getFileItemList(request, saveDir);
        FileItem fileItem = null;
        FileItem[] fileItems = new FileItem[fieldName.length];
        for (int i = 0; i < fieldName.length; i++) {
            for (Iterator<?> iterator = fileItemList.iterator(); iterator.hasNext(); ) {
                fileItem = (FileItem) iterator.next();
                // 根据名字获得文本域
                if (fieldName[i] != null && fieldName[i].equals(fileItem.getFieldName())) {
                    fileItems[i] = fileItem;
                    break;
                }
            }
        }
        return fileItems;
    }

    /**
     * Upload file processing (batch support)
     *
     * @param request
     * @param namespace
     */
    public static Map<String, String> uploadImage(HttpServletRequest request, String namespace) {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        Map<String, String> fileNames = new HashMap<String, String>();
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iterator = multiRequest.getFileNames();
            while (iterator.hasNext()) {
                String key = iterator.next();
                MultipartFile multipartFile = multiRequest.getFile(key);
                if (multipartFile != null) {
                    String name = multipartFile.getOriginalFilename();
                    if (!"".equals(name)) {
                        if (name.indexOf(".") == -1 && "blob".equals(name)) {
                            name = name + ".png";
                        }
                        String uuid = UUID.randomUUID().toString();
                        String postFix = name.substring(name.lastIndexOf(".")).toLowerCase();
                        String fileName = uuid + postFix;
                        try {

                            String filePath = remove2Sftp(multipartFile.getBytes(), namespace, fileName);
                            if (filePath != null && !"".equals(filePath)) {
                                fileNames.put(key, filePath);
                            }
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }
        return fileNames;
    }

    public static String[] uploadImage1(HttpServletRequest request, String namespace) {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        String[] fileNames = null;
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> map = multiRequest.getFileMap();
            int i = 0;
            fileNames = new String[map.size()];
            for (String key : map.keySet()) {
                MultipartFile multipartFile = map.get(key);
                System.out.println(multipartFile);
                if (multipartFile != null) {
                    String name = multipartFile.getOriginalFilename();
                    if (!"".equals(name)) {
                        if (name.indexOf(".") == -1 && "blob".equals(name)) {
                            name = name + ".png";
                        }
                        String uuid = UUID.randomUUID().toString();
                        String postFix = name.substring(name.lastIndexOf(".")).toLowerCase();
                        String fileName = uuid + postFix;
                        try {

                            String filePath = remove2Sftp(multipartFile.getBytes(), namespace, fileName);
                            String dir = "";
                            if (filePath != null && !"".equals(filePath)) {
                                fileNames[i] = dir + filePath;
                                i++;
                            }
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }
        return fileNames;
    }

    /**
     * Upload local images to ftp server
     *
     * @param filePath
     * @param namespace
     */
    public static String uploadImage(String filePath, String namespace) {
        String path = "";
        if ((filePath != null && !"".equals(filePath)) && (namespace != null && !"".equals(namespace))) {
            path = remove2Sftp(filePath, namespace);
        }
        return path;
    }

    /**
     * Get temporary directory for uploaded files
     *
     * @param request
     */
    public static String getUploadDir(HttpServletRequest request) {
        return request.getServletPath() + uploadFileDir + File.separator;
    }

    /**
     * Moving files to SFTP and producing random names
     *
     * @param filePath
     * @param namespace
     */
    private static String remove2Sftp(String filePath, String namespace) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("file " + filePath + "non-existent.");
        }
        String dir = "";
        String path = (dir + "/" + namespace).replace("//", "/");

        String name = file.getName(); //Access to the name of the document
        String uuid = UUID.randomUUID().toString(); //Get randomly produced UUIDs
        String postFix = name.substring(name.lastIndexOf(".")).toLowerCase();  //Get Image Suffix
        String fileName = uuid + postFix;

        System.out.println("fileName=" + fileName);
        System.out.println("path=" + path);
        System.out.println("filePath=" + filePath);

        FTPFileTransmit ftp = new FTPFileTransmit();
        ftp.createDirectory(path);
        ftp.saveInFTP(path, fileName, File2byte(filePath));

        String tempPath = "";
        if (dir.contains("home")) {
            tempPath = (namespace + "/" + fileName).replace("//", "/").replace("\\", "/");
        } else {
            tempPath = (path + "/" + fileName).replace("//", "/").replace("\\", "/");
        }
        System.out.println("tempPath=" + tempPath);
        return tempPath;
    }

    /**
     * Moving files to SFTP
     *
     * @param byteFile
     * @param namespace
     * @param fileName
     */
    public static String remove2Sftp(byte[] byteFile, String namespace, String fileName) {
        if (byteFile == null) {
            throw new RuntimeException("file " + fileName + "non-existent.");
        }
        String dir = "";
        String path = (dir + "/" + namespace).replace("//", "/");
        System.out.println("init ftp");
        System.out.println("path=" + path);
        System.out.println("fileName=" + fileName);
        FTPFileTransmit ftp = new FTPFileTransmit();
        ftp.createDirectory(path);
        ftp.saveInFTP(path, fileName, byteFile);
        if (dir.contains("home")) {
            return (namespace + "/" + fileName).replace("//", "/").replace("\\", "/");
        } else {
            return (path + "/" + fileName).replace("//", "/").replace("\\", "/");
        }
    }


    /**
     * Upload files to local server processing (batch support)
     *
     * @param request
     * @param namespace
     * @param isFtp
     */
    public static Map<String, String> uploadImageDir(HttpServletRequest request, String namespace, boolean isFtp) {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        Map<String, String> fileNames = new HashMap<String, String>();
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iterator = multiRequest.getFileNames();
            String tem_path = uploadFileDir + namespace + "/" ;
            String pathDir = request.getSession().getServletContext().getRealPath(tem_path);
            File dirFile = new File(pathDir);
            if (!dirFile.isDirectory()) {
                dirFile.mkdirs();
            }
            while (iterator.hasNext()) {
                String key = iterator.next();
                MultipartFile multipartFile = multiRequest.getFile(key);
                if (multipartFile != null) {
                    String name = multipartFile.getOriginalFilename();
                    if (!"".equals(name)) {
                        if (name.indexOf(".") == -1 && "blob".equals(name)) {
                            name = name + ".png";
                        }
                        String uuid = UUID.randomUUID().toString();
                        String postFix = name.substring(name.lastIndexOf(".")).toLowerCase();
                        String fileName = uuid + postFix;
                        try {
                            FileUtils.writeByteArrayToFile(new File(pathDir, fileName), multipartFile.getBytes());
                            if (isFtp) {
                                //同步到ftp服务器
                                String ftpPath = remove2Sftp(multipartFile.getBytes(), namespace, fileName);
                                System.out.println("Synchronisation to the ftp server was successful, ftpPath= " + ftpPath);
                            }
                            fileNames.put(key, tem_path + fileName);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }
        return fileNames;
    }

    /**
     * Upload files to local server processing (batch support)
     *
     * @param request
     * @param namespace
     */
    public static Map<String, String> uploadImageDir(HttpServletRequest request, String namespace) {
        return uploadImageDir(request, namespace, false);
    }


    /**
     * File to Binary
     *
     * @param filePath
     */
    public static byte[] File2byte(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }
}