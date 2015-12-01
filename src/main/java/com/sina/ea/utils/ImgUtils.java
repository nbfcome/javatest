package com.sina.ea.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImgUtils {
	
	private static Logger logger = LoggerFactory.getLogger(ImgUtils.class);
	
	public final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();

	private ImgUtils() {
	}
	
	public static int MAX_HEAD_LENGTH;

	static {
		getAllFileType(); // 初始化文件类型信息
	}

	/**
	 * * Created on 2010-7-1 *
	 * <p>
	 * Discription:[getAllFileType,常见文件头信息]
	 * </p>
	 * 
	 * @author:[shixing_11@sina.com]
	 */
	private static void getAllFileType() {
		FILE_TYPE_MAP.put("jpg", "FFD8FF"); // JPEG (jpg)
		FILE_TYPE_MAP.put("png", "89504E47"); // PNG (png)
		FILE_TYPE_MAP.put("gif", "47494638"); // GIF (gif)
		FILE_TYPE_MAP.put("tif", "49492A00"); // TIFF (tif)
		FILE_TYPE_MAP.put("bmp", "424D"); // Windows Bitmap (bmp)
		FILE_TYPE_MAP.put("dwg", "41433130"); // CAD (dwg)
		FILE_TYPE_MAP.put("html", "68746D6C3E"); // HTML (html)
		FILE_TYPE_MAP.put("rtf", "7B5C727466"); // Rich Text Format (rtf)
		FILE_TYPE_MAP.put("xml", "3C3F786D6C");
		FILE_TYPE_MAP.put("zip", "504B0304");
		FILE_TYPE_MAP.put("rar", "52617221");
		FILE_TYPE_MAP.put("psd", "38425053"); // Photoshop (psd)
		FILE_TYPE_MAP.put("eml", "44656C69766572792D646174653A"); // Email
																	// [thorough
																	// only]
																	// (eml)
		FILE_TYPE_MAP.put("dbx", "CFAD12FEC5FD746F"); // Outlook Express (dbx)
		FILE_TYPE_MAP.put("pst", "2142444E"); // Outlook (pst)
		FILE_TYPE_MAP.put("xls", "D0CF11E0"); // MS Word
		FILE_TYPE_MAP.put("doc", "D0CF11E0"); // MS Excel 注意： word 和 excel 的文件
												// 头一样
		FILE_TYPE_MAP.put("mdb", "5374616E64617264204A"); // MS Access (mdb)
		FILE_TYPE_MAP.put("wpd", "FF575043"); // WordPerfect (wpd)
		FILE_TYPE_MAP.put("eps", "252150532D41646F6265");
		FILE_TYPE_MAP.put("ps", "252150532D41646F6265");
		FILE_TYPE_MAP.put("pdf", "255044462D312E"); // Adobe Acrobat (pdf)
		FILE_TYPE_MAP.put("qdf", "AC9EBD8F"); // Quicken (qdf)
		FILE_TYPE_MAP.put("pwl", "E3828596"); // Windows Password (pwl)
		FILE_TYPE_MAP.put("wav", "57415645"); // Wave (wav)
		FILE_TYPE_MAP.put("avi", "41564920");
		FILE_TYPE_MAP.put("ram", "2E7261FD"); // Real Audio (ram)
		FILE_TYPE_MAP.put("rm", "2E524D46"); // Real Media (rm)
		FILE_TYPE_MAP.put("mpg", "000001BA"); // 
		FILE_TYPE_MAP.put("mov", "6D6F6F76"); // Quicktime (mov)
		FILE_TYPE_MAP.put("asf", "3026B2758E66CF11"); // Windows Media (asf)
		FILE_TYPE_MAP.put("mid", "4D546864"); // MIDI (mid)
		FILE_TYPE_MAP.put("swf", "465753"); //swf
		FILE_TYPE_MAP.put("swf", "435753"); //swf
		FILE_TYPE_MAP.put("flv", "464C56"); //flv
		
		MAX_HEAD_LENGTH = 0;
		for(String s : FILE_TYPE_MAP.values()){
			if(s.length()>MAX_HEAD_LENGTH){
				MAX_HEAD_LENGTH = s.length();
			}
		}
		MAX_HEAD_LENGTH = MAX_HEAD_LENGTH + 10;
	}

	/**
	 * * Created on 2010-7-1 *
	 * <p>
	 * Discription:[getImageFileType,获取图片文件实际类型,若不是图片则返回 null]
	 * </p>
	 * * @param File * @return fileType * @author:[shixing_11@sina.com]
	 * */
	public final static String getImageFileType(File f) {
		if (isImage(f)) {
			try {
				ImageInputStream iis = ImageIO.createImageInputStream(f);
				Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
				if (!iter.hasNext()) {
					return null;
				}
				ImageReader reader = iter.next();
				iis.close();
				return reader.getFormatName();
			} catch (IOException e) {
				return null;
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

	/**
	 * * Created on 2010-7-1 *
	 * <p>
	 * Discription:[getFileByFile,获取文件类型,包括图片,若格式不是已配置的,则返回 null]
	 * </p>
	 * * @param file * @return fileType * @author:[shixing_11@sina.com]
	 */
	public final static String getFileTypeByFile(File file) {
		String filetype = null;
		byte[] b = new byte[MAX_HEAD_LENGTH];
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			//System.out.println("MAX_HEAD_LENGTH:"+MAX_HEAD_LENGTH);
			is.read(b,0,MAX_HEAD_LENGTH);
			filetype = getFileTypeByStream(b);
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try{
				is.close();
			} catch(Exception e){
				
			}
		}
		return filetype;
	}

	/**
	 * * Created on 2010-7-1 *
	 * <p>
	 * Discription:[getFileTypeByStream]
	 * </p>
	 * * @param b * @return fileType * @author:[shixing_11@sina.com]
	 */
	public final static String getFileTypeByStream(byte[] b) {
		String filetypeHex = String.valueOf(getFileHexString(b));
		logger.info("file hex:"+filetypeHex);
		Iterator<Entry<String, String>> entryiterator = FILE_TYPE_MAP.entrySet().iterator();
		while (entryiterator.hasNext()) {
			Entry<String, String> entry = entryiterator.next();
			String fileTypeHexValue = entry.getValue();
			if (filetypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * * Created on 2010-7-2 *
	 * <p>
	 * Discription:[isImage,判断文件是否为图片]
	 * </p>
	 * * @param file * @return true 是 | false 否 * @author:[shixing_11@sina.com]
	 */
	public static final boolean isImage(File file) {
		boolean flag = false;
		try {
			BufferedImage bufreader = ImageIO.read(file);
			int width = bufreader.getWidth();
			int height = bufreader.getHeight();

			if (width == 0 || height == 0) {
				flag = false;
			} else {
				flag = true;
			}
		} catch (IOException e) {
			flag = false;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * * Created on 2010-7-1 *
	 * <p>
	 * Discription:[getFileHexString]
	 * </p>
	 * * @param b * @return fileTypeHex * @author:[shixing_11@sina.com]
	 */
	public final static String getFileHexString(byte[] b) {
		StringBuilder stringBuilder = new StringBuilder();
		if (b == null || b.length <= 0) {
			return null;
		}
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
	
	/**
	 * 
	 * #func 判断文件是否是flash<br>
	 * #desc 在此添加实现相关说明
	 *
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	public static final boolean isFlash(File file) {
		byte[] b = new byte[MAX_HEAD_LENGTH];
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			is.read(b,0,MAX_HEAD_LENGTH);
			String filetypeHex = String.valueOf(getFileHexString(b));
			logger.info("file hex:"+filetypeHex);
			if (filetypeHex.toUpperCase().startsWith("465753")
					|| filetypeHex.toUpperCase().startsWith("435753")) {
				return true;
			}
		} catch (FileNotFoundException e) {
			return false;
		}catch (IOException e) {
			return false;
		} finally {
			try{
				is.close();
			} catch(Exception e){
				
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 * #func <br>
	 * #desc 在此添加实现相关说明
	 *
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	public final static Long getFlashVersion(File flashFile) {
		byte[] b = new byte[MAX_HEAD_LENGTH];
		InputStream is = null;
		try {
			is = new FileInputStream(flashFile);
			is.read(b,0,MAX_HEAD_LENGTH);
			if(isFlash(flashFile)){
				String filetypeHex = String.valueOf(getFileHexString(b));
				
				String flashVersionHex = filetypeHex.substring(6, 8);
				//将16进制字符串转为int
				int version  = Integer.parseInt(flashVersionHex, 16);
				logger.info("flash file hex : " + filetypeHex + ", version hex :"+flashVersionHex + ", version:"+version);
				return Long.valueOf(version);
			}else{
				return null;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(is!=null){
					is.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 
	 * #func 获取图片的宽高，结果为字符串：宽*高<br>
	 * #desc 在此添加实现相关说明
	 *
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	public final static String getImgWdht(File imgFile) {
		BufferedImage sourceImg = null;
		try {
			sourceImg = ImageIO.read(new FileInputStream(imgFile));
			int width = sourceImg.getWidth();
			int height = sourceImg.getHeight();
			System.out.println(width);
			System.out.println(height);
			return width + "*" + height;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws Exception {
//		File f = new File("d:\\1.flv");
//		if (f.exists()) {
//			String filetype1 = getImageFileType(f);
//			System.out.println(filetype1);
//			String filetype2 = getFileTypeByFile(f);
//			System.out.println(filetype2);
//		}
//		String[] imageFormats = ImageIO.getReaderFormatNames(); 
//        // [jpg, BMP, bmp, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif] 
//        System.out.println(Arrays.asList(imageFormats)); 
// 
//        String[] imageFormats1 = ImageIO.getWriterFormatNames(); 
//        // [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif] 
//        System.out.println(Arrays.asList(imageFormats1)); 
        
//        URL url = new URL("http://img.adbox.sina.com.cn/pic/3970.swf");
//        URLConnection connect = url.openConnection();
//        connect.connect();
//        FileInputStream flash = (FileInputStream)connect.getInputStream();
//        File flashFile = new File(flash);
		
		File img = new File("d:/1.gif");
		String wdht = ImgUtils.getImgWdht(img);
		System.out.println(wdht);
	}
}
