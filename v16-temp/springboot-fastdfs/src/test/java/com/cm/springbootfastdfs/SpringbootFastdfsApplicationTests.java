package com.cm.springbootfastdfs;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootFastdfsApplicationTests {

	@Autowired
	private FastFileStorageClient client;

	@Test
	public void testFdfs() throws FileNotFoundException{
		File file=new File("D:\\第四阶段\\项目\\v16\\v16-temp\\springboot-fastdfs\\src\\main\\resources\\static\\image\\324477.jpg");
		FileInputStream io=new FileInputStream(file);
		String s="jpg";
		//上传图片和缩略图（输入流，文件长度，文件后缀名，空）
		StorePath storePath=client.uploadImageAndCrtThumbImage(io, file.length(), s, null);
		System.out.println(storePath.getFullPath());//[group1/M00/00/00/rBDhxF1KTMSAfY9kAAKf8H0__EY887.jpg]
		System.out.println(storePath); //StorePath [group=group1, path=M00/00/00/rBDhxF1KTMSAfY9kAAKf8H0__EY887.jpg]

	}

}
