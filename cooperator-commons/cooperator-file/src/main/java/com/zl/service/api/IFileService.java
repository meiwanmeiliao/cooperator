package com.zl.service.api;

import java.util.Map;

/**
 * @author zhuolin
 * @program: cooperator
 * @date 2018/10/25
 * @description: ${description}
 **/
public interface IFileService {
    /**
     * 保存文件
     *
     * @param bytes
     * @return
     */
    public String save(byte[] bytes);

    /**
     * 删除文件
     *
     * @param fileKey
     * @return
     */
    public boolean delete(String fileKey);

    /**
     * 下载文件
     *
     * @param fileKey
     * @return
     */
    public byte[] download(String fileKey);

    /**
     * 压缩文件
     *
     * @param fileKeyNameMap
     * @return 压缩过的文件的路径
     */
    public byte[] compress(Map<String,String> fileKeyNameMap);

    /**
     * 预览文件
     *
     * @param fileKey
     * @return
     */
    public byte[] preview(String fileKey);


}
