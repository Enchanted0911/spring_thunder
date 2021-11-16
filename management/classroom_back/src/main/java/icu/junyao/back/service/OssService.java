package icu.junyao.back.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author johnson
 * @date 2021-11-16
 */
public interface OssService {

    /**
     * 上传图片到oss
     *
     * @param file 待上传图片
     * @param basePath 上传的基本路径
     * @return 上传后的url
     */
    String uploadPicture(MultipartFile file, String basePath);
}
