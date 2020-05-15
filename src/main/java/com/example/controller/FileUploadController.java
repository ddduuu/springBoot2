package com.example.controller;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author dzh
 * @since 2020-05-14
 */
@RestController
@AllArgsConstructor
public class FileUploadController {
    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    @RequestMapping("fileUpload")
    public String fileUpdload(@RequestParam(value = "fileName") MultipartFile file){
        if (file.isEmpty()){
            return "上传文件为空，请选择文件！";
        }
        String filename = file.getOriginalFilename();
        long size = file.getSize();
        log.info("文件名称:"+filename+",文件大小为："+size);
        String path = "D:\\upload";
        File f = new File(path+File.separator+filename);
        if (!f.getParentFile().exists()){
            f.getParentFile().mkdirs();
        }
        try {
            file.transferTo(f);
            log.info("上传文件成功！");
            return "上传文件成功！";
        } catch (IOException e) {
            e.printStackTrace();
            log.info(e.getMessage());
            return "上传失败！";
        }
    }

    @RequestMapping("multifileUpload")
    public String multifileUpload(HttpServletRequest request){
        MultipartHttpServletRequest m = (MultipartHttpServletRequest)request;
        List<MultipartFile> fileNames = m.getFiles("fileName");
        if (fileNames.isEmpty()){
            return "上传文件为空，请选择文件！";
        }
        String path = "D:\\upload";
        String filename;
        for (MultipartFile file : fileNames) {
            filename = file.getOriginalFilename();
            if (StringUtils.isBlank(filename)){
                continue;
            }
            long size = file.getSize();
            log.info("文件名称:"+filename+",文件大小为："+size);
            File f = new File(path+File.separator+filename);
            if (!f.getParentFile().exists()){
                f.getParentFile().mkdirs();
            }
            try {
                file.transferTo(f);
                return "上传成功！";
            } catch (IOException e) {
                e.printStackTrace();
                log.info(e.getMessage());
                return "上传失败";
            }
        }
        return "上传成功！";
    }



}
