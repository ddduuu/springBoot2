package com.example.controller;

import com.example.util.WebUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
@Api(tags = "文件上传")
@Slf4j
public class FileUploadController {
    @RequestMapping("fileUpload")
    //@ApiOperation用来描述该接口的作用。可以通过该注解说明接口的职责、返回头信息、方法的请求方式（"GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS" and "PATCH"）、协议（ http, https, ws, wss）、http状态码。
    @ApiOperation(value="单文件上传", notes="文件上传")
    //@ApiImplicitParam：用来给参数增加说明。可以设置参数的名称、是否是必填项、参数的描述信息、是否只读等。
    @ApiImplicitParam(name = "fileName", value = "文件名称", required = true, dataType = "String")
    public String fileUpdload(@RequestParam(value = "fileName") MultipartFile file){
        if (file.isEmpty()){
            return "上传文件为空，请选择文件！";
        }
        String filename = file.getOriginalFilename();
        long size = file.getSize();
        log.info("文件名称:"+filename+",文件大小为："+size);
        String path = WebUtil.getPropertiesValue("dictionaries","download.path");
        File f = new File(path + File.separator + filename);
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
    @ApiOperation(value="多文件上传", notes="多个文件上传")
    public String multifileUpload(HttpServletRequest request){
        MultipartHttpServletRequest m = (MultipartHttpServletRequest)request;
        List<MultipartFile> fileNames = m.getFiles("fileName");
        if (fileNames.isEmpty()){
            return "上传文件为空，请选择文件！";
        }
        String filename;
        for (MultipartFile file : fileNames) {
            filename = file.getOriginalFilename();
            if (StringUtils.isBlank(filename)){
                continue;
            }
            long size = file.getSize();
            log.info("文件名称:"+filename+",文件大小为："+size);
            String path = WebUtil.getPropertiesValue("dictionaries","download.path");
            File f = new File(path + File.separator + filename);
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
