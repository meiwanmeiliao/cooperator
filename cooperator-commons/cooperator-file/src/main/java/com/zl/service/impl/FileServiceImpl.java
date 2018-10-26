package com.zl.service.impl;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import com.sun.javafx.tk.FileChooserType;
import com.zl.service.api.IFileService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author zhuolin
 * @program: cooperator
 * @date 2018/10/25
 * @description: 文件保存service
 **/
@Service
public class FileServiceImpl implements IFileService {

    private static final String FILE_PATH = "upload" + File.separator;

    private static final String ZIP_FILE_PATH = "zip" + File.separator;

    private static final String PREVIEW_PATH = "preview" + File.separator;


    @Override
    public String save(byte[] bytes) {
        return saveFile(bytes);
    }

    @Override
    public boolean delete(String fileKey) {
        return deleteByFileKey(fileKey);
    }


    @Override
    public byte[] download(String fileKey) {
        return getFileBytes(fileKey);
    }

    @Override
    public byte[] compress(Map<String, String> fileKeyNameMap) {
        return zipFiles(fileKeyNameMap);
    }

    @Override
    public byte[] preview(String fileKey) {
        return new byte[0];
    }

    //======================================================

    /**
     * 文档转pdf
     *
     * @param fileKey
     * @throws IOException
     */
    public static void previewPDF(String fileKey) throws IOException {
        File file = new File(getAbsolutePath(PREVIEW_PATH + File.separator + fileKey + ".pdf"));
        File parentFile = file.getParentFile();
        if (!parentFile.exists() || !parentFile.isDirectory()) {
            parentFile.mkdirs();
        }
        FileOutputStream fileOutputStream =
                new FileOutputStream(file);
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(getAbsolutePath(fileKey)));
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = br.read(buf)) != -1) {
            fileOutputStream.write(buf, 0, len);
        }
        br.close();
        fileOutputStream.close();
    }

    /**
     * 文件压缩
     *
     * @param fileKeyNameMap 文件路径 文件名称
     */
    private static byte[] zipFiles(Map<String, String> fileKeyNameMap) {
        List<File> srcFiles = new ArrayList<>();
        Map<File, String> fileNameMap = new HashMap<>();
        for (String fileKey : fileKeyNameMap.keySet()) {
            File file = new File(getAbsolutePath(fileKey));
            if (!file.exists() || file.isDirectory()) {
                continue;
            }
            srcFiles.add(file);
            fileNameMap.put(file, fileKeyNameMap.get(fileKey));
        }
        File zipFile = new File(getAbsolutePath(ZIP_FILE_PATH + File.separator + "demo1.zip"));
        File parentFile = zipFile.getParentFile();
        if (!parentFile.exists() || !parentFile.isDirectory()) {
            parentFile.mkdirs();
        }
        if (zipFile.exists()) {
            zipFile.delete();
        }
        // 判断压缩后的文件存在不，不存在则创建
        if (!zipFile.exists()) {
            try {
                zipFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 创建 FileOutputStream 对象
        FileOutputStream fileOutputStream = null;
        // 创建 ZipOutputStream
        ZipOutputStream zipOutputStream = null;
        // 创建 FileInputStream 对象
        FileInputStream fileInputStream = null;

        try {
            // 实例化 FileOutputStream 对象
            fileOutputStream = new FileOutputStream(zipFile);
            // 实例化 ZipOutputStream 对象
            zipOutputStream = new ZipOutputStream(fileOutputStream);
            // 创建 ZipEntry 对象
            ZipEntry zipEntry = null;
            // 遍历源文件数组
            for (File file : fileNameMap.keySet()) {
                // 将源文件数组中的当前文件读入 FileInputStream 流中
                fileInputStream = new FileInputStream(file);
                // 实例化 ZipEntry 对象，源文件数组中的当前文件
                zipEntry = new ZipEntry(fileNameMap.get(file));
                zipOutputStream.putNextEntry(zipEntry);
                // 该变量记录每次真正读的字节个数
                int len;
                // 定义每次读取的字节数组
                byte[] buffer = new byte[1024];
                while ((len = fileInputStream.read(buffer)) > 0) {
                    zipOutputStream.write(buffer, 0, len);
                }
            }
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            fileInputStream.close();
            fileOutputStream.close();
            return FileUtils.readFileToByteArray(zipFile);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 写入文件
     *
     * @param bytes
     * @return
     */
    private static String saveFile(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        String fileKey = UUID.randomUUID().toString();
        File file = new File(getAbsolutePath(fileKey));
        File parentFile = file.getParentFile();
        if (!parentFile.exists() && !parentFile.isDirectory()) {
            parentFile.mkdirs();
        }
        try {
            FileUtils.writeByteArrayToFile(file, bytes);
            return fileKey;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 删除文件
     *
     * @param fileKey
     * @return
     */
    private static boolean deleteByFileKey(String fileKey) {
        if (fileKey == null || fileKey.length() == 0 || fileKey.trim().length() == 0) {
            return true;
        }
        File file = new File(getAbsolutePath(fileKey));
        if (!file.exists()) {
            return true;
        }
        return file.delete();
    }

    /**
     * 获取文件二进制
     *
     * @param fileKey
     * @return
     */
    private static byte[] getFileBytes(String fileKey) {
        File file = new File(getAbsolutePath(fileKey));
        if (!file.exists()) {
            return null;
        }
        try {
            return FileUtils.readFileToByteArray(file);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断是否为图片
     *
     * @param fileKey
     * @return
     */
    private static String getFileType(String fileKey) {
        File file = new File(getAbsolutePath(fileKey));
        if (!file.exists() || file.isDirectory()) {
            return null;
        }
        return FileTypeUtil.getType(file);
    }

    /**
     * 获取文件绝对路径
     *
     * @return
     */
    private static String getAbsolutePath(String fileKey) {
        return getUserDir() + File.separator + FILE_PATH + File.separator + fileKey;
    }

    /**
     * 获取工程路径
     *
     * @return
     */
    private static String getUserDir() {
        return System.getProperty("user.dir");
    }

    public static void main(String[] args) {
        System.out.println(getFileType("94788c1c-527f-4dc2-ab96-02c03caffe28"));
    }

}
