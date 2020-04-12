package com.fs.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor.RED;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fs.model.Info;
import com.fs.model.User;
import com.fs.model.page;
import com.fs.service.LoginService;
import com.fs.utils.Upload;
import com.fs.utils.pinsert;

import javafx.scene.control.Cell;


@Controller
public class LoginController   {

	
	
	@Autowired 
	private LoginService loginservice;
	@Autowired
	private DataSourceTransactionManager transactionManager;
	 
	@RequestMapping("login")
	public String login(HttpServletRequest request,HttpServletResponse response,User user) {
		
		//获取用户输入的用户信息
		String username=user.getUsername();
		String password=user.getPassword();
		
		
		//通过用户名进行数据库查询
		List<User> list=loginservice.queryUserByUsername(username);
		
		//对查询结果进行判断
		
		//用户不存在
		if(list.isEmpty()) {
			request.setAttribute("info", "用户不存在");
			return"view/LoginUi";
			
		//验证成功	
		}else if (list.get(0).getPassword().equals(password)) {
			
			
			
			return"view/success";
		//密码错误
		}else {
			request.setAttribute("info", "密码错误");
			request.setAttribute("message", username);
			return"view/LoginUi";
		}
		
		
		
	}
	
	@RequestMapping(value = "login2" ,produces = "application/json;charset=utf-8" )
	@ResponseBody
	public String login2(HttpServletRequest request,HttpServletResponse response,User user) {
		
		//获取用户输入的用户信息
		String username=user.getUsername();
		String password=user.getPassword();
		
		//通过用户名进行数据库查询
		List<User> list=loginservice.queryUserByUsername(username);
		
		//对查询结果进行判断
		
		//用户不存在
		if(list.isEmpty()) {
			return"no";
			
		//验证成功	
		}else if (list.get(0).getPassword().equals(password)) {

			
			
			return"success";
		//密码错误
		}else {
			return"erro";
		}
		
		
		
	}
	

	@RequestMapping(value = "query" ,produces = "application/json;charset=utf-8" )
	public String query(HttpServletRequest request,HttpServletResponse response,page pa) {
		//获得前端的当前页数，第一次为空
		String numstr=request.getParameter("num");
		//获得前端的输入的条件，第一次进来为空
		String aa=request.getParameter("aa");
		//判断当前页数
		if (numstr==null||numstr=="") {
			 //查询数据库
			//把条件aa和分页model一起传入后台，在持久层用if判断aa是否为空，做出不同查询
			  List<Info> list=loginservice.queryALLinfo(pa,aa);
			//把条件aa传入后台，在持久层用if判断aa是否为空，做出不同查询
			  int number=loginservice.querynum(aa);
			  //根据查询出来的条数和是定的size计算一共好多页。注意用double在向上取整用int接受， 不然会有误差，
			  double su=(double)number/(double)pa.getSize();
			  int sum=(int) Math.ceil(su);
			  //将一共的页数和当前页数装入page 的model内
			  pa.setSum(sum);
			  //因为第一次进来所以手动设置当前页数
			  pa.setNum(1);
			  //将查询的数据和pa传到前台。
			  request.setAttribute("list", list);
			  request.setAttribute("pa", pa);
			  //将查询条件页传回去，以便跳页面时传递条件。
			  request.setAttribute("message", aa);
			
		}
		else {
			//当当前页面不为空时，调用该方法
			int num =Integer.parseInt(numstr);
			//设置查询开始位置
			pa.setStart((num-1)*pa.getSize());
			//把条件aa和分页model一起传入后台，在持久层用if判断aa是否为空，做出不同查询
			List<Info> list=loginservice.queryALLinfo(pa,aa);
			//把条件aa传入后台，在持久层用if判断aa是否为空，做出不同查询
			int number=loginservice.querynum(aa);
			//根据查询出来的条数和是定的size计算一共好多页。注意用double在向上取整用int接受， 不然会有误差，
			double su=(double)number/(double)pa.getSize();
			int sum=(int) Math.ceil(su);
			//设置一共的页数和当前页
			pa.setSum(sum);
			pa.setNum(num);
			 //将查询的数据和pa传到前台。
			request.setAttribute("list", list);
			request.setAttribute("pa", pa);
			//将查询条件页传回去，以便跳页面时传递条件。
			request.setAttribute("message", aa);
		}
		
		
		return "view/info";
		
		
	}
	//文件上传
	@RequestMapping(value = "upload" ,produces = "application/json;charset=utf-8" )
	public String upload(HttpServletRequest request,HttpServletResponse response ) throws IOException {
		 //调用封装好的Upload类的upload方法,返回上传的文件名
		String message= new Upload().upload(request,response);
			
			if(message!=null && message!="") {
				response.sendRedirect("view/succ.jsp");
				return null;
				
			}else {
				return null;
			}
			
		
	}
	
	
	
//	将数据导出为表格
	@RequestMapping(value = "load" ,produces = "application/json;charset=utf-8" )
	public String load(HttpServletRequest request,HttpServletResponse response,User user) throws IOException {
		// TODO Auto-generated method stub
		
		       String aa=request.getParameter("aa");
		       
		        //标题行;
				List<String> list = new ArrayList<String>();
				list.add("id");
				list.add("编号");
				list.add("名字");
				//将要导出的数据查询好，里面每一个集合就作为一行，每个集合里的元素就作为每一行的字段内容。
				List<Info> data=loginservice.queryallandload(aa);
				
		  
				
				//sheet名称，每一个sheet的名字；
				String sheetName = "产品信息表";
				//创建工作簿对象
				XSSFWorkbook workbook = new XSSFWorkbook();
				//创建sheet对象
				XSSFSheet sheet = workbook.createSheet(sheetName);
				//样式对象。
				XSSFCellStyle style = workbook.createCellStyle();
				//Excel文件的名称
				String excelName  = "massagetable.xls";
				//设置编码
				response.setContentType("application/octet-stream;charset=utf-8");
				//设置浏览器头
				response.addHeader("Content-Disposition", "attachment;filename="+excelName);
				//获得输出流对象;
				OutputStream os = response.getOutputStream();
				
				//创建标题行。行高1300
				XSSFRow row2=sheet.createRow(0);
				row2.setHeight((short) 1300);
				//合并列    前两个0是当前列     第三个0是第几列起    2是合并到第几。。下表是从0开始的。
	            XSSFCell newcell = row2.createCell(0);
	            CellRangeAddress region = new CellRangeAddress(0, 0, 0, 2);	
	            sheet.addMergedRegion(region);
	            newcell.setCellValue("员工信息表");
	            
	            
				//开始创建第一行即标题行;
				XSSFRow row = sheet.createRow(1);
				for(int i =0 ;i<list.size();i++){
					XSSFCell cell = row.createCell(i);
					cell.setCellValue(list.get(i).toString());//标题行内容写入
				}
				
				//除了标题行之外其他的内容行开始插入，表的列索引也是从0开始的。
				int rownum =2;
				for(int i =0;i<data.size();i++){//看有几行
					XSSFRow newRow = sheet.createRow(rownum);
					
					XSSFCell cell = newRow.createCell(0);
					cell.setCellValue(data.get(i).getId());//数据行内容写入
					
					XSSFCell cell2 = newRow.createCell(1);
					cell2.setCellValue(data.get(i).getNum());//数据行内容写入
					
					XSSFCell cell3 = newRow.createCell(2);
					cell3.setCellValue(data.get(i).getName());//数据行内容写入
					
					rownum++;
				}
				
				workbook.write(os);
				
				
				return null;
		
		
		
	}
	//excel表格批量插入数据。
	@RequestMapping(value = "insert" ,produces = "application/json;charset=utf-8" )
	@Transactional
	public String insert(HttpServletRequest request,HttpServletResponse response,User user) throws CloneNotSupportedException, IOException  {
		
		/*
		 * DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		 * def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		 * TransactionStatus status = transactionManager.getTransaction(def);
		 *  // 事务提交
							transactionManager.commit(status);
							//回滚
							//transactionManager.rollback(status);
		 */
		
		         //调用封装好的Upload类的upload方法,返回上传的文件名
		          String message= new Upload().upload(request,response);
		
		
					//根据上传的文件进行解析获得数据
					try {
						//调用封装好的pinsert类的play方法
						List<Info> list=new pinsert().play("D://fileupload2/"+message);
						
								  int a=loginservice.pinsertdata(list);
								  if(a!=0) {
									  System.out.println("批量插入成功");
								  }
						
						System.out.println("解析文件");
					} catch (EncryptedDocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvalidFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		   response.sendRedirect("view/succ.jsp");
				return null;
		
		
		
	}
	
//	批量删除
	@RequestMapping(value = "pdeleteindo" ,produces = "application/json;charset=utf-8" )
	@ResponseBody
	public String pdeleteindo(HttpServletRequest request,HttpServletResponse response) {
		  String idStr=request.getParameter("number");
		  String []idst=idStr.split(",");
		  List<Integer>list=new ArrayList<Integer>();
		
		  for (int i = 1; i < idst.length; i++) {
		  list.add(Integer.parseInt(idst[i])); }
		  
		int b=loginservice.deleteById(list);
		if (b!=0) {
			return "success";
		}else {
			return "flase";
		}
		
		
	
	
	
	}
	
	
	
	
}
