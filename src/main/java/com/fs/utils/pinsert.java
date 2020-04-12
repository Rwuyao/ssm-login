package com.fs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;

import com.fs.model.Info;



public class pinsert  {
	
	

	public List<Info> play( String filepath) throws EncryptedDocumentException, InvalidFormatException, IOException, CloneNotSupportedException {
		List<Info> list=new ArrayList<Info>();
		Info info2=new Info();
		//文件历经
		File file=new File(filepath);
		//创建输入流和workbook对象
		FileInputStream fileInputStream=new FileInputStream(file);
		Workbook wb=WorkbookFactory.create(fileInputStream);
		Sheet sheet=wb.getSheetAt(0);
		
		//将数据一行一行的添加到集合中
		Row row=null;
		for (int i = 1; i <=sheet.getLastRowNum(); i++) {
			Info info=(Info) info2.clone();//用克隆的效率比new()的效率高。
			System.err.println("一共行数"+sheet.getLastRowNum());//获得一共多少行
			System.err.println("当前行数"+i);
		     row=sheet.getRow(i);
		
           int num= (int)row.getCell(0).getNumericCellValue();//获得int类型的参数
           String name =row.getCell(1).getStringCellValue();//获得String类型的参数。
           info.setName(name);
           info.setNum(num);
           list.add(info);
           
		}
		return list;
		
	}

	}
