package icu.junyao.back.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import icu.junyao.back.constant.OssConstants;
import icu.junyao.back.service.OssService;
import icu.junyao.back.util.ConstantPropertiesUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author wu
 */
@Service
public class OssServiceImpl implements OssService {

    @Override
    @CacheEvict(value = "banner",key = "'selectIndexList'")
    public String uploadPicture(MultipartFile file, String basePath) {
        try {
            // 创建OSS实例。
            OSS ossClient = new OSSClientBuilder()
                    .build(ConstantPropertiesUtils.END_POINT, ConstantPropertiesUtils.ACCESS_KEY_ID
                            , ConstantPropertiesUtils.ACCESS_KEY_SECRET);

            //获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String fileName = file.getOriginalFilename();

            //1 在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            // yuy76t5rew01.jpg
            fileName = uuid + fileName;

            //2 把文件按照日期进行分类
            //获取当前日期
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(OssConstants.DATE_PATTERN);
            String datePath = dateTimeFormatter.format(LocalDate.now());
            //拼接
            //  2019/11/12/ewtqr313401.jpg
            fileName = basePath + datePath + fileName;

            //调用oss方法实现上传
            //第一个参数  Bucket名称
            //第二个参数  上传到oss文件路径和文件名称   aa/bb/1.jpg
            //第三个参数  上传文件输入流
            ossClient.putObject(ConstantPropertiesUtils.BUCKET_NAME, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //把上传之后文件路径返回
            //需要把上传到阿里云oss路径手动拼接出
            return "https://" + ConstantPropertiesUtils.BUCKET_NAME + "."
                    + ConstantPropertiesUtils.END_POINT + "/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
