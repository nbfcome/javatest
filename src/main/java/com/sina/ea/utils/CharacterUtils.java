package com.sina.ea.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharacterUtils {
	protected static Logger log = LoggerFactory.getLogger(CharacterUtils.class);

	// 根据Unicode编码完美的判断中文汉字和符号
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }
 
    // 完整的判断中文汉字和符号
    public static boolean isChinese(String str) {
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }
    
    // 获取含中文字符或符号的字符串长度，中文或中文符号算2个长度
    public static int getChineseLength(String str) {
        char[] ch = str.toCharArray();
        int len = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                len += 2;
            }else{
            	len += 1;
            }
        }
        return len;
    }
    
    public static void main(String args []){
    	String[] strArr = new String[] { "www.micmiu.com", "!@#$%^&*()_+{}[]|\"'?/:;<>,.", "！￥……（）——：；“”‘’《》，。？、", "不要啊", "やめて", "韩佳人", "???" };
        for (String str : strArr) {
            System.out.println("===========> 测试字符串：" + str);
            System.out.println("Unicode判断结果 ：" + isChinese(str));
            System.out.println("长度结果 ：" + str.length());
            System.out.println("中文长度结果 ：" + getChineseLength(str));
            System.out.println("详细判断列表：");
            char[] ch = str.toCharArray();
            for (int i = 0; i < ch.length; i++) {
                char c = ch[i];
                System.out.println(c + " --> " + (isChinese(c) ? "是" : "否"));
            }
        }
    }
}