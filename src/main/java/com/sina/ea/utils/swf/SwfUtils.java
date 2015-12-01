package com.sina.ea.utils.swf;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.*;

/** 
 * 
 * 
 *  @author Jove 
 *  
 */
public final class SwfUtils {
	/**
	 * 得到一个SWF文件的首选尺寸. 
	 * 算法介绍: <br>
	 * 感谢avie提供swf格式的支持 
	 * 从第9个字节起读5 bits，设值为n； 越过n bits，读n bits，设值为x； 再跳过n
	 * bits，读取n bits，设值为y。 swf的宽度和高度依次为x/20和y/20像素
	 * */
	private static Dimension getPreferredSize(InputStream is) throws IOException {
		is.skip(8);
		byte[] head = new byte[22];
		is.read(head);
		head = toBitArray(head);
		int n = (int) readBit(head, 0, 5);
		long x = readBit(head, 5 + n, n);
		long y = readBit(head, 5 + n * 3, n);
		Dimension d = new Dimension((int) (x / 20), (int) (y / 20));
		return d;
	}
	
	private static Dimension getPreferredSize2(byte[] bytes) throws IOException {
		byte[] head = new byte[30];
		if(bytes.length<30){
			return null;
		}
		for(int i=0;i<22;i++){
			head[i]=bytes[i+8];
		}
		head = toBitArray(head);
		int n = (int) readBit(head, 0, 5);
		long x = readBit(head, 5 + n, n);
		long y = readBit(head, 5 + n * 3, n);
		Dimension d = new Dimension((int) (x / 20), (int) (y / 20));
		return d;
	}

	/**
	 * 从data的from位开始,依次读出length个bit
	 * 
	 * @param data
	 *            数组的每一个元素表示一个bit
	 * @param from
	 *            起始位置
	 * @param length
	 *            读bit的个数
	 * @return 对应的long型值
	 * @see #toBitArray
	 */
	private static long readBit(byte[] data, int from, int length) {
		long rv = 0;
		for (int i = 0; i < length; i++) {
			rv += data[from + i] << (length - i - 1);
		}
		return rv;
	}

	/**
	 * 把byte数组转为bit数组
	 * 
	 * @param data
	 *            数据流
	 * @return 对应的bit数组,长度位data.length*8
	 * 
	 */
	private static byte[] toBitArray(byte[] data) {
		byte[] rv = new byte[data.length * 8];
		for (int i = 0; i < data.length; i++) {
			rv[i * 8] = (byte) ((data[i] & 0x80) > 0 ? 1 : 0);
			rv[i * 8 + 1] = (byte) ((data[i] & 0x40) > 0 ? 1 : 0);
			rv[i * 8 + 2] = (byte) ((data[i] & 0x20) > 0 ? 1 : 0);
			rv[i * 8 + 3] = (byte) ((data[i] & 0x10) > 0 ? 1 : 0);
			rv[i * 8 + 4] = (byte) ((data[i] & 0x08) > 0 ? 1 : 0);
			rv[i * 8 + 5] = (byte) ((data[i] & 0x04) > 0 ? 1 : 0);
			rv[i * 8 + 6] = (byte) ((data[i] & 0x02) > 0 ? 1 : 0);
			rv[i * 8 + 7] = (byte) ((data[i] & 0x01) > 0 ? 1 : 0);
		}
		return rv;
	}
	
	/**
	 * 
	 * #func 获取flash的尺寸<br>
	 * #desc 在此添加实现相关说明
	 *
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	public static String getFlashSize(File flashFile){
		try {
			FileInputStream is=new FileInputStream(flashFile);
			
			byte[] oldBytes=new byte[30];
			is.read(oldBytes);
			is.close();
			
			String type=new String(oldBytes).substring(0,3);
			Dimension d=null;
			if(type.equals("FWS")){
				d=SwfUtils.getPreferredSize2(oldBytes);
			}
			else if(type.equals("CWS")){
				byte [] newBytes=new SWFDecompressor().readFile2(flashFile);
				d=SwfUtils.getPreferredSize2(newBytes);
			}
			else{
				System.out.println("flash type invalid,type:"+type);
			}
			System.out.println("type:"+type+" size:"+(int)d.getWidth()+"*"+(int)d.getHeight());
			return (int)d.getWidth()+"*"+(int)d.getHeight();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * #func 获取图片的尺寸<br>
	 * #desc 在此添加实现相关说明
	 *
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	public static String getImageSize(String fileName){
		try {
			FileInputStream is=new FileInputStream(fileName);
			BufferedImage sourceImg = javax.imageio.ImageIO.read(is); 
			System.out.println("image size:"+sourceImg.getWidth()+"*"+sourceImg.getHeight());
			return sourceImg.getWidth()+"*"+sourceImg.getHeight();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		SwfUtils.getImageSize("d:\\1.gif");
		SwfUtils.getFlashSize(new File("d:\\1.swf"));
		SwfUtils.getFlashSize(new File("D:/TDDOWNLOAD/flash/775x120.swf"));
		SwfUtils.getFlashSize(new File("C:/Users/tkEC/Desktop/58.swf"));
		SwfUtils.getFlashSize(new File("C:/Users/tkEC/Desktop/33.swf"));
	}
}
