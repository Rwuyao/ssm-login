package com.fs.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.RandomStringUtils;

public class Upload {
	
public String  upload(HttpServletRequest request,HttpServletResponse response ) throws IOException {
	
	//设置编码
			response.setContentType("text/html;charset=utf-8");
			try {
			//创建DiskFileItemFactory工厂对象
			DiskFileItemFactory factory=new DiskFileItemFactory();
			
			//设置文件缓存目录，如果不存在就创建一个
			File file=new File("D:\\fileupload");
			if(!file.exists()) {
				file.mkdirs();
			}
			factory.setRepository(file);
			
			//创建ServletFileUpload对象
	        ServletFileUpload fileUpload=new ServletFileUpload(factory);
			//设置编码
	        fileUpload.setHeaderEncoding("utf-8");
	        //解析request，将form表单的各个字段封装为FileItem对象
	        
			List<FileItem> fileItems=fileUpload.parseRequest(request);
				
			for (FileItem fileItem : fileItems) {
	         //获取上传的文件名
	         String filename=fileItem.getName();
	         //ie浏览器会把绝对路径全上传。所已我们需要得到文件名后缀。用.分割，取最后一个字符
	         String []str=filename.split("[.]");
	         
	         //获得随机字符串，并创建文件名字
	         String name=RandomStringUtils.randomAlphabetic(5)+"."+str[str.length-1];
	         // 创建文件路径
				 //创建File对象
	            File file2=new File("D://fileupload2/"+name);
	            //创建文件夹
	            file2.getParentFile().mkdirs();
	            //创建文件
	            file2.createNewFile();
	            //获取上传文件流
	            InputStream in=fileItem.getInputStream();
	            //使用 FileOutputStream打开服务器端的上传文件
	            FileOutputStream out=new FileOutputStream(file2);
	            //流的对拷
	            byte[] bytes=new byte[1024];//每次读取一个字节
	            int len;
	            //开始读取上传文件的字节，并将其输出到服务器端的上传文件输出流中
	            while ((len=in.read(bytes))>0)
	                out.write(bytes,0,len);
	            in.close();
	            out.close();
	            fileItem.delete();
				System.err.println("上传成功");
				return name;
			 }	
				
				
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
	
	
	
	
	
	
}

}
