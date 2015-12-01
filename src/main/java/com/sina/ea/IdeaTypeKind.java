package com.sina.ea;

import java.util.ArrayList;
import java.util.List;

/**
 * 创意类型类别
 * 
 * @author biaofei
 *
 */
public class IdeaTypeKind {
	//创意包含图片或flash
	public static List<Long> PIC = new ArrayList<Long>();
	//创意包含图片和文字
	public static List<Long> PIC_TEXT = new ArrayList<Long>();
	//所有类型
	public static List<Long> ALL = new ArrayList<Long>();
	
	static{
		PIC.add(Constants.IDEA_TYPE.PICTURE);
		PIC.add(Constants.IDEA_TYPE.FLASH);
		
		PIC_TEXT.add(Constants.IDEA_TYPE.INFOFLOW);
		PIC_TEXT.add(Constants.IDEA_TYPE.KAINFOFLOW);
		PIC_TEXT.add(Constants.IDEA_TYPE.GRAPHIC);
		PIC_TEXT.add(Constants.IDEA_TYPE.UNIONINFOFLOW);
		
		ALL.addAll(PIC);
		ALL.addAll(PIC_TEXT);
		ALL.add(Constants.IDEA_TYPE.ADBOX);
		ALL.add(Constants.IDEA_TYPE.TEXT);
	}
}
