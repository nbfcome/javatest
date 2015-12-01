package com.sina.ea.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.DecimalFormat;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {
	protected static Logger log = LoggerFactory.getLogger(FileUtils.class);
	/**
	 * 
	 * #func 获取文件大小<br>
	 * #desc 在此添加实现相关说明
	 *
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	public static long getFileSizes(File f){
        long s=0;
        if (f!=null && f.exists()) {
            FileInputStream fis = null;
            try {
				fis = new FileInputStream(f);
				s= fis.available();
			} catch (Exception e) {
				s = 0;
			}
        } 
        return s;
    }
	
	/**
	 * 
	 * #func 转换文件大小<br>
	 * #desc 在此添加实现相关说明
	 *
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	public static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }
	
	/**
	 * #func 拷贝文件<br>
	 * #desc is是原文件输入流，destine是新文件路径
	 */
	public static long copyFile(InputStream is, String destine) {
		if (is == null || destine == null) {
			log.info("source inputstream or destine is null");
			return -1L;
		}

		File newFile = new File(destine);
		OutputStream os = null;
		try {
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			os = new BufferedOutputStream(new FileOutputStream(newFile));
			int b = -1;
			while ((b = is.read()) != -1) {
				os.write(b);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return -1L;
		} finally {
			if (is != null) {
				try {
					is.close();
					
				} catch (IOException e1) {
					return -1L;
				}
			}
			if (os != null) {
				try {
					os.close();
				} catch (IOException e1) {
					return -1L;
				}
			}
		}
		
		return newFile.length();
	}

	/**
	 * #func 拷贝文件<br>
	 * #desc file是原文件，destine是新文件路径
	 */
	public static long copyFile(File file, String destine) {
		if (file == null || destine == null) {
			log.info("source file or destine is null");
			return -1L;
		}

		if (!file.exists()) {
			log.info("source file does not exist");
			return -1L;
		}

		try {
			InputStream is = new BufferedInputStream(new FileInputStream(file));
			return copyFile(is, destine);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return -1L;
		}
	}
	
	/**
	 * #func 从url拷贝文件<br>
	 * #desc url是原文件所在地址，destine是新文件路径
	 */
	public static void copyURLFile(URL url, String destine) throws Exception{
		if (url == null || destine == null) {
			throw new IllegalArgumentException("params is null");
		}
		File destFile = new File(destine);
		if (!destFile.exists()) {
			destFile.createNewFile();
		}
		org.apache.commons.io.FileUtils.copyURLToFile(url, destFile);
	}
	
	/**
	 * 
	 * #func 判断文件名后缀是否符合<br>
	 * #desc 在此添加实现相关说明
	 *
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	public static boolean isFileSuffixMatch(String suffix, String allowSuffix){
		if(StringUtils.isBlank(suffix) || StringUtils.isBlank(allowSuffix)){
			return false;
		}
		String[] allowArr = allowSuffix.split(";");
		for(String unit : allowArr){
			if(unit.toLowerCase().equals(suffix)){
				return true;
			}
		}
		return false;
	}
	
	public static void main(String args[]){
		try {
			String url = "http://sucai.flashline.cn/flash/sucai/ad/33.swf";
			URL uri = new URL(url);
			String dest = "d:/33.swf";
			FileUtils.copyURLFile(uri,dest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}