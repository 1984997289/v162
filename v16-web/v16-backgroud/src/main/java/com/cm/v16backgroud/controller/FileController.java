package com.cm.v16backgroud.controller;

import com.cm.v16.common.pojo.ResultBean;
import com.cm.v16.common.pojo.ResultBeanPicture;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author CaoMeng
 * @Date 2019-08-07
 */
@RestController
@RequestMapping("file")
public class FileController{

    @Autowired
    private FastFileStorageClient client;

    @Value("${image.url}")
    private String url;


    @RequestMapping("upload")
    public ResultBean upload(MultipartFile file){
        //拿到全名
        String originalFilename=file.getOriginalFilename();
        //定位最后一位"."的位置
        int i=originalFilename.lastIndexOf(".");
        //拿到后缀名
        String extName=originalFilename.substring(i+1);
        try{
            StorePath storePath=client.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), extName, null);
            String fullPath=url+storePath.getFullPath();
            return new ResultBean("200",fullPath);
        }catch(IOException e){
            e.printStackTrace();
            return new ResultBean("500","上传失败");
        }
    }

    @RequestMapping("uploads")
    public ResultBeanPicture resultBeanPicture(MultipartFile[] files){
        String[] data=new String[files.length];
        for(int i=0; i<files.length; i++){
            //拿到全名
            String originalFilename=files[i].getOriginalFilename();
            //定位最后一位"."的位置
//            int k=originalFilename.lastIndexOf(".");
            //拿到后缀名
            String extName=originalFilename.substring(originalFilename.lastIndexOf(".")+1);

            StorePath storePath=null;
            try{
                storePath=client.uploadImageAndCrtThumbImage(files[i].getInputStream(), files[i].getSize(), extName,null);
                String path=url+storePath.getFullPath();
                data[i]=path;
            }catch(IOException e){
                e.printStackTrace();
                return new ResultBeanPicture("-1",null);
            }
        }
        return new ResultBeanPicture("0",data);
    }

}
