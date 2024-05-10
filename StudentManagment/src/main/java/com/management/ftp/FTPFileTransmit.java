package com.management.ftp;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.net.SocketException;

/**
 *  ftp file tools
 */
public class FTPFileTransmit {

    private String ftpName;
    private String ftpPassword;
    private String ftpServerIP;
    private int ftpPort;

    public FTPFileTransmit() {

        this.ftpName = "Administrator";
        this.ftpPassword = "qiuxinfa";
        this.ftpServerIP = "192.168.43.152";
        this.ftpPort = 21;

    }

    /**
     * Method name: saveInFTP <BR>
     * Description: Storing files on FTP <BR>
     * Remark: <BR>
     * @param FolderName            示例"/20161118_ReTransmit/"
     * @param FileName                示例"2011080912345678.text"
     * @param data                    byte[]数组
     * @return boolean<BR>
     */
    public boolean saveInFTP(String FolderName, String FileName, byte[] data) {
        System.out.println("Ready to upload.");
        boolean flag = false;
        // Creating an FTP client
        FTPClient ftpClient = new FTPClient();
        // Input stream for reading files
        ByteArrayInputStream bis = null;
        try {

            // If neither the FolderName nor the FileName meets the basic requirements, then there is no need for ftp.
            if (FolderName != null && FolderName.compareTo("") != 0 && FileName != null && FileName.compareTo("") != 0) {
                System.out.println("Go to Upload：");
                // Establishing an FTP connection
                ftpClient.connect(ftpServerIP, ftpPort);
                // If the login is successful, the input stream will be created.
                if (ftpClient.login(ftpName, ftpPassword)) {
                    System.out.println("ftp Login Successful");

                    ftpClient.changeWorkingDirectory(FolderName);
                    System.out.println("FolderName=" + FolderName);
                    ftpClient.enterLocalPassiveMode();
                    // Write byte[] to input stream, instantiated
                    bis = new ByteArrayInputStream(data);
                    // Setting the buffer
                    ftpClient.setBufferSize(1024);
                    //Setting the file name in Chinese
                    ftpClient.setControlEncoding("utf-8");
                    // Set file type (binary type)
                    if (ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE)) {
                        flag = ftpClient.storeFile(FileName, bis);
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
            flag = false;
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            try {
                // input close
                IOUtils.closeQuietly(bis);
                // connection close
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return flag;
    }

    /**
     * Method name: getFromFTP <BR>
     * Description: Reading files from FTP <BR>
     * filePath=/20161118_ReTransmit/111.jpg <BR>
     * outPutFilePath = C:/ftpload.jpg
     * @return boolean<BR>
     */
    public boolean getFromFTP(String filePath, String outPutFilePath) {
        boolean flag = false;
        // Creating an FTP client
        FTPClient ftpClient = new FTPClient();
        // Output streams are used to output files
        FileOutputStream fos = null;
        try {
            // Establishing an FTP connection
            ftpClient.connect(ftpServerIP, ftpPort);
            // If the login is successful, then the output stream is created.
            if (ftpClient.login(ftpName, ftpPassword)) {
                // FTP file
                String distinationFile = filePath;
                // Instantiating an output stream
                fos = new FileOutputStream(outPutFilePath);
                // Setting the buffer
                ftpClient.setBufferSize(1024);
                // Set file type (binary type)
                if (ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE)) {
                    ftpClient.retrieveFile(distinationFile, fos);
                    flag = true;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
            flag = false;
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            try {
                // Closing the output stream
                IOUtils.closeQuietly(fos);
                // Close connection
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return flag;
    }

    /**
     * Automatically create folder directories in the ftp root directory.
     */
    public String createDirectory(String path) {
        //Create a catalogue
        String FolderName = path;
        boolean flag = false;
        // 创建FTP客户端
        FTPClient ftpClient = new FTPClient();
        try {
            // Creating an FTP client
            ftpClient.connect(ftpServerIP, ftpPort);
            // If you are logged in successfully, then you can proceed.
            String[] fi = path.split("/");

            if (ftpClient.login(ftpName, ftpPassword)) {
                for (String string : fi) {
                    if (StringUtils.isNotEmpty(string)) {
                        System.out.println("ftp Login Successful");
                        ftpClient.makeDirectory(string);
                        boolean sdf = ftpClient.changeWorkingDirectory(string);
                        flag = true;
                        System.out.println("FTP directory created successfully：" + FolderName);
                    }
                }
            } else {
                System.out.println("ftp login failed, please check login user, password..............");
            }

        } catch (SocketException e) {
            e.printStackTrace();
            flag = false;
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            try {
                // 关闭连接
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return FolderName;
    }

    //Get a byte array of the specified file
    public static byte[] getBytes(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
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
    public static void main(String[] args) {
        FTPFileTransmit ftpFileTransmit = new FTPFileTransmit();
        //=================================Creating Folders===========================================//
        String filepath = ftpFileTransmit.createDirectory("/li/ll");
        //=================================Uploading files===========================================//
        String disk_path = "C:\\Users\\sa\\Pictures\\u=2109630271,3850214520&fm=27&gp=0.jpg";
        String taget_path = "abc1.jpg";

        //Converting files to byte arrays
        byte[] data = ftpFileTransmit.getBytes(disk_path);
        //Upload local files to ftp
        boolean flag = ftpFileTransmit.saveInFTP("/data/wwwroot/ftpdata", taget_path, data);
        if (flag) {
            System.out.println("****** FTP upload file successfully******");
        } else {
            System.out.println("****** FTP upload file failure******");
        }
        //=================================Download file===========================================//
        String fromfilepath = "ftp.txt";
        String outPutFilePath = "C:\\Users\\sa\\Desktop\\ftp.txt";
        flag = ftpFileTransmit.getFromFTP(fromfilepath, outPutFilePath);
        if (flag) {
            System.out.println("****** FTP Download Successful******");
        } else {
            System.out.println("****** FTP download failure******");
        }
    }
}
