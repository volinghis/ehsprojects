/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.utils 
 * @author: qjj   
 * @date: 2019年9月5日 上午9:25:29 
 */
package com.ehs.common.data.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.aspose.cells.Encoding;
import com.aspose.cells.TxtLoadOptions;
import com.aspose.cells.Workbook;
import com.aspose.slides.Presentation;
import com.aspose.words.Document;
import com.aspose.words.LoadOptions;
import com.aspose.words.SaveFormat;

/**
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: WordToPDF.java
 * @Description: MS office文档转换为PDF
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2019年9月5日 上午9:25:29
 *
 * Modification History: Date Author Version Description
 * ---------------------------------------------------------* 2019年9月5日
 * qjj v1.0.0 修改原因
 */
public class OfficeToPDF {

	public boolean getWordLicense() throws Exception {
		boolean result = false;
		try {
			InputStream is = this.getClass().getResourceAsStream("/templates/license.xml");
			com.aspose.words.License aposeLic = new com.aspose.words.License();
			aposeLic.setLicense(is);
			result = true;
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public boolean getPPtLicense() throws Exception {
		boolean result = false;
		try {
			InputStream is = this.getClass().getResourceAsStream("/templates/license.xml");
			com.aspose.slides.License aposeLic = new com.aspose.slides.License();
			aposeLic.setLicense(is);
			result = true;
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	public boolean getExcelLicense() throws Exception {
		boolean result = false;
		try {
			InputStream is = this.getClass().getResourceAsStream("/templates/license.xml");
			 com.aspose.cells.License aposeLic = new com.aspose.cells.License();
			aposeLic.setLicense(is);
			result = true;
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	/**
	 * 
	 * @Function:excelTopdf
	 * @Description: 该函数的功能描述
	 * @param source
	 * @param os
	 * @throws Exception
	 * @throws：异常描述
	 * @version: v1.0.0
	 * @author: qjj
	 * @date: 2020年3月18日 下午5:38:29
	 *
	 * Modification History: Date Author Version Description
	 * ---------------------------------------------------------* 2020年3月18日
	 * qjj v1.0.0 修改原因
	 */
	public static void excelTopdf(InputStream source, OutputStream os) throws Exception {
		OfficeToPDF d = new OfficeToPDF();
		try {
			// 验证License
			if (!d.getExcelLicense()) {
				return;
			}
			long old = System.currentTimeMillis();
			 com.aspose.cells.TxtLoadOptions to=new TxtLoadOptions();
			 to.setEncoding(Encoding.getUTF8());
			// InPath是将要被转化的文档
			Workbook wb = new Workbook(source);
			wb.save(os, com.aspose.cells.SaveFormat.PDF);
			long now = System.currentTimeMillis();
			System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒"); // 转化用时
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @Function:docTopdf
	 * @Description: word 文档转pdf
	 * @param source
	 * @param os
	 * @throws Exception
	 * @throws：异常描述
	 * @version: v1.0.0
	 * @author: qjj
	 * @date: 2020年3月18日 下午5:38:50
	 *
	 * Modification History: Date Author Version Description
	 * ---------------------------------------------------------* 2020年3月18日
	 * qjj v1.0.0 修改原因
	 */
	public static void docTopdf(InputStream source, OutputStream os) throws Exception {
		OfficeToPDF d = new OfficeToPDF();
		if (!d.getWordLicense()) { // 验证License 若不验证则转化出的pdf文档有水印
			throw new Exception("com.aspose.words lic ERROR!");
		}
		try {
			long old = System.currentTimeMillis();
			LoadOptions lo=new LoadOptions();
			lo.setEncoding(Charset.forName("utf8"));
			Document doc = new Document(source); // word文档
			// 支持RTF HTML,OpenDocument, PDF,EPUB, XPS转换
			doc.save(os, SaveFormat.PDF);
			long now = System.currentTimeMillis();
			System.out.println("convert OK! " + ((now - old) / 1000.0) + "秒");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void pptToPdf(InputStream source, OutputStream os)throws Exception  {
		OfficeToPDF d = new OfficeToPDF();
		if (!d.getPPtLicense()) { // 验证License 若不验证则转化出的pdf文档有水印
			throw new Exception("com.aspose.ppt lic ERROR!");
		}
		try {
			long old = System.currentTimeMillis();
			Presentation pres = new Presentation(source);
			pres.save(os, com.aspose.slides.SaveFormat.Pdf);
			long now = System.currentTimeMillis();
			System.out.println("convert OK! " + ((now - old) / 1000.0) + "秒");
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String filepath = "D:\\test2.xlsx";
		String outpath = "D:\\t4.pdf";

		InputStream source;
		OutputStream target;
		try {
			source = new FileInputStream(filepath);
			target = new FileOutputStream(outpath);
			excelTopdf(source, target);
			//pptToPdf(source, target);
			//docTopdf(source,target);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
