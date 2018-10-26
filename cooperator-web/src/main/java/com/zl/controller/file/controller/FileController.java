package com.zl.controller.file.controller;

import com.zl.service.api.IFileService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuolin
 * @program: cooperator
 * @date 2018/10/26
 * @description: ${description}
 **/
@RestController
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    IFileService fileService;

    /**
     * 保存文件
     *
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(MultipartFile multipartFile) throws IOException {
        return ResponseEntity.ok().body(fileService.save(multipartFile.getBytes()));

    }

    /**
     * 删除文件
     *
     * @param fileKey
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity delete(String fileKey) {
        return ResponseEntity.ok().body(fileService.delete(fileKey));
    }

    /**
     * 下载文件
     *
     * @param fileKey
     * @return
     */
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public void download(String fileKey, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type", "application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" +
                new String("a.txt".getBytes("gb2312"), "ISO8859-1") + "\"");
        // 跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.getOutputStream().write(fileService.download(fileKey));
    }

    /**
     * 压缩文件
     *
     * @param fileKeyNameMap
     * @return 压缩过的文件的路径
     */
    @RequestMapping(value = "/compress", method = RequestMethod.POST)
    public void compress(Map<String, String> fileKeyNameMap, HttpServletResponse response) throws IOException {
        fileKeyNameMap = new HashMap<>();
        fileKeyNameMap.put("6b9d95e5-b62b-4066-9e81-b84a06b6995e", "a.txt");
        fileKeyNameMap.put("7d1094dd-bb6f-4a2c-822e-b82e4da3c15e", "b.txt");
        fileKeyNameMap.put("52d7ad24-9ae1-41d8-8dc5-4441cac6aa6e", "c.txt");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type", "application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" +
                new String("a.txt".getBytes("gb2312"), "ISO8859-1") + "\"");
        // 跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.getOutputStream().write(fileService.compress(fileKeyNameMap));
    }

    /**
     * 预览文件
     *
     * @param fileKey
     * @return
     */
    public ResponseEntity preview(String fileKey) {
        return null;
    }


}
