package com.sina.ea.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

import com.sina.ea.ExportSetInfo;

/**
 * excel导入 导出（目前报表只用到导出，导入功能暂时不实现）
 * @author songjuan
 *
 */
public class ExcelExportUtil {
	
	private static HSSFWorkbook wb;

	private static CellStyle titleStyle;		// 标题行样式
	private static Font titleFont;				// 标题行字体		
	private static CellStyle dateStyle;			// 日期行样式
	private static Font dateFont;				// 日期行字体
	private static CellStyle headStyle;			// 表头行样式
	private static Font headFont;				// 表头行字体
	private static CellStyle contentStyle ;		// 内容行样式
	private static Font contentFont;			// 内容行字体

	/**
	 * 导出到Excel
	 * @param setInfo
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("rawtypes")
	public static void exportToExcel(ExportSetInfo setInfo) throws 
			IOException, IllegalArgumentException, IllegalAccessException{
		init();	
		Set<Entry<String, List>> set = setInfo.getObjsMap().entrySet();
		String[] sheetNames = new String[setInfo.getObjsMap().size()];
		int sheetNameNum = 0;
		for (Entry<String, List> entry : set)
		{
			sheetNames[sheetNameNum] = entry.getKey();
			sheetNameNum++;
		}
		HSSFSheet[] sheets = getSheets(setInfo.getObjsMap().size(), sheetNames);
		int sheetNum = 0;
		for (Entry<String, List> entry : set)
		{
			// Sheet
			List objs = entry.getValue();
			// 标题行
			createTableTitleRow(setInfo, sheets, sheetNum);
			// 日期行
			createTableDateRow(setInfo, sheets, sheetNum);
			// 表头
			creatTableHeadRow(setInfo, sheets, sheetNum);
			// 表体
			String[] fieldNames = setInfo.getFieldNames().get(sheetNum);
			int rowNum = 3;
			for (Object obj : objs)
			{
				HSSFRow contentRow = sheets[sheetNum].createRow(rowNum);
				contentRow.setHeight((short) 300);
				HSSFCell[] cells = getCells(contentRow, setInfo.getFieldNames().get(sheetNum).length,true);
				int cellNum = 1;// 去掉一列序号，因此从1开始
				if(fieldNames != null)
				{
					for (int num = 0; num < fieldNames.length; num++)
					{
						Object value = ReflectionUtils.invokeGetterMethod(obj, fieldNames[num]);
						if(value == null || (value != null && "-1.0".equals(value.toString())) || (value != null && "-1".equals(value.toString()))){
							cells[cellNum].setCellValue("-");
						}else{
							if("ctr".equals(fieldNames[num])){
								cells[cellNum].setCellValue(value.toString()+"‰");
							}else{
								cells[cellNum].setCellValue(value.toString());
							}
						}
						
						cellNum++;
					}
				}
				rowNum++;
			}
			//设置平均值
			if(setInfo.getAvgValues() != null && setInfo.getAvgValues().size() != 0){
				String[] avgValues = setInfo.getAvgValues().get(sheetNum);
				if(avgValues != null && avgValues.length != 0){
					HSSFRow contentRow = sheets[sheetNum].createRow(rowNum);
					contentRow.setHeight((short) 300);
					int cellNum = 2;// 去掉一列序号，因此从1开始
					HSSFCell[] cells = getCells(contentRow, avgValues.length + 1,false);
					cells[1].setCellValue("平均值：");
					for(int i = 0; i < avgValues.length; i++){
						cells[cellNum].setCellValue(avgValues[i]);
						cellNum++;
					}
					rowNum++;
				}
			}
			//设置合计值
			if(setInfo.getSumValues() != null && setInfo.getSumValues().size() != 0){
				String[] sumValues = setInfo.getSumValues().get(sheetNum);
				if(sumValues != null && sumValues.length != 0){
					HSSFRow contentRow = sheets[sheetNum].createRow(rowNum);
					contentRow.setHeight((short) 300);
					int cellNum = 2;// 去掉一列序号，因此从1开始
					HSSFCell[] cells = getCells(contentRow, sumValues.length + 1,false);
					cells[1].setCellValue("合计值：");
					for(int i = 0; i < sumValues.length; i++){
						cells[cellNum].setCellValue(sumValues[i]);
						cellNum++;
					}
					rowNum++;
				}
			}
			
			//设置列宽
			int[] colWidths = setInfo.getColWidths().get(sheetNum);
			if(colWidths == null || colWidths.length == 0){
				adjustColumnSize(sheets, sheetNum, fieldNames);	// 自动调整列宽
			}else{
				setColumnWidth(sheets, sheetNum,colWidths);
			}
			
			sheetNum++;
		}
		wb.write(setInfo.getOut());
	}
	


	/**
	 * 设置列宽
	 * @param sheets
	 * @param sheetNum
	 * @param colWidths
	 */
	private static void setColumnWidth(HSSFSheet[] sheets, int sheetNum,
			int[] colWidths) {
		for(int i = 0; i < colWidths.length; i++)
		{
			sheets[sheetNum].setColumnWidth(i, colWidths[i]);
		}
		
	}

	/**
	 * @Description: 自动调整列宽
	 */
	private static void adjustColumnSize(HSSFSheet[] sheets, int sheetNum,
			String[] fieldNames)
	{
		for(int i = 0; i < fieldNames.length + 1; i++)
		{
			sheets[sheetNum].autoSizeColumn(i, true);
		}
	}
	
	/**
	 * @Description: 创建标题行(需合并单元格)
	 */
	private static void createTableTitleRow(ExportSetInfo setInfo,
			HSSFSheet[] sheets, int sheetNum)
	{
		CellRangeAddress titleRange = new CellRangeAddress(0, 0, 0, 
				setInfo.getFieldNames().get(sheetNum).length);
		sheets[sheetNum].addMergedRegion(titleRange);
		HSSFRow titleRow = sheets[sheetNum].createRow(0);
		titleRow.setHeight((short) 800);
		HSSFCell titleCell = titleRow.createCell(0);
		titleCell.setCellStyle(titleStyle);
		titleCell.setCellValue(setInfo.getTitles()[sheetNum]);
	}
	
	/**
	 * @Description: 创建日期行(需合并单元格)
	 */
	private static void createTableDateRow(ExportSetInfo setInfo,
			HSSFSheet[] sheets, int sheetNum)
	{
		CellRangeAddress dateRange = new CellRangeAddress(1, 1, 0, 
				setInfo.getFieldNames().get(sheetNum).length);
		sheets[sheetNum].addMergedRegion(dateRange);
		HSSFRow dateRow = sheets[sheetNum].createRow(1);
		dateRow.setHeight((short) 350);
		HSSFCell dateCell = dateRow.createCell(0);
		dateCell.setCellStyle(dateStyle);
		dateCell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	}
	
	/**
	 * @Description: 创建表头行(需合并单元格)
	 */
	private static void creatTableHeadRow(ExportSetInfo setInfo,
			HSSFSheet[] sheets, int sheetNum)
	{
		// 表头
		HSSFRow headRow = sheets[sheetNum].createRow(2);
		headRow.setHeight((short) 350);
		// 序号列
		HSSFCell snCell = headRow.createCell(0);
		snCell.setCellStyle(headStyle);
		snCell.setCellValue("序号");
		// 列头名称
		for(int num = 1, len = setInfo.getHeadNames().get(sheetNum).length; num <= len; num++)
		{
			HSSFCell headCell = headRow.createCell(num);
			headCell.setCellStyle(headStyle);
			headCell.setCellValue(setInfo.getHeadNames().get(sheetNum)[num - 1]);
		}
	}
	
	/**
	 * @Description: 创建内容行的每一列(附加一列序号)
	 */
	private static HSSFCell[] getCells(HSSFRow contentRow, int num,boolean hasNo)
	{
		HSSFCell[] cells = new HSSFCell[num + 1];

		for (int i = 0,len = cells.length; i < len; i++)
		{
			cells[i] = contentRow.createCell(i);
			cells[i].setCellStyle(contentStyle);
		}
		if(hasNo){
			// 设置序号列值，因为出去标题行和日期行，所有-2
			cells[0].setCellValue(contentRow.getRowNum() - 2);
		}
		
		return cells;
	}
	
	/**
	 * @Description: 创建所有的Sheet
	 */
	private static HSSFSheet[] getSheets(int num, String[] names)
	{
		HSSFSheet[] sheets = new HSSFSheet[num];
		for (int i = 0; i < num; i++)
		{
			sheets[i] = wb.createSheet(names[i]);
		}
		return sheets;
	}
	
	/**
	 * @Description: 初始化
	 */
	private static void init()
	{
		wb = new HSSFWorkbook();
		
		titleFont = wb.createFont();
		titleStyle = wb.createCellStyle();
		dateStyle = wb.createCellStyle();
		dateFont = wb.createFont();
		headStyle = wb.createCellStyle();
		headFont = wb.createFont();
		contentStyle = wb.createCellStyle();
		contentFont = wb.createFont();
		
		initTitleCellStyle();
		initTitleFont();
		initDateCellStyle();
		initDateFont();
		initHeadCellStyle();
		initHeadFont();
		initContentCellStyle();
		initContentFont();
	}
	
	/**
	 * @Description: 初始化标题行样式
	 */
	private static void initTitleCellStyle()
	{
		titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
		titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		titleStyle.setFont(titleFont);
		titleStyle.setFillBackgroundColor(IndexedColors.SKY_BLUE.index);
	}
	
	/**
	 * @Description: 初始化标题行字体
	 */
	private static void initTitleFont()
	{
		titleFont.setFontName("宋体");
		titleFont.setFontHeightInPoints((short) 20);//设置字体大小
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);//粗体
		titleFont.setCharSet(Font.DEFAULT_CHARSET);
		titleFont.setColor(IndexedColors.BLUE_GREY.index);
	}

	/**
	 * @Description: 初始化日期行样式
	 */
	private static void initDateCellStyle()
	{
		dateStyle.setAlignment(CellStyle.ALIGN_CENTER_SELECTION);
		dateStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		dateStyle.setFont(dateFont);
		dateStyle.setFillBackgroundColor(IndexedColors.SKY_BLUE.index);
	}
	
	/**
	 * @Description: 初始化日期行字体
	 */
	private static void initDateFont()
	{
		dateFont.setFontName("宋体");
		dateFont.setFontHeightInPoints((short) 10);
		dateFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		dateFont.setCharSet(Font.DEFAULT_CHARSET);
		dateFont.setColor(IndexedColors.BLUE_GREY.index);
	}

	
	/**
	 * @Description: 初始化表头行样式
	 */
	private static void initHeadCellStyle()
	{
		headStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headStyle.setFont(headFont);
		headStyle.setFillBackgroundColor(IndexedColors.YELLOW.index);
		headStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
		headStyle.setBorderBottom(CellStyle.BORDER_THIN);
		headStyle.setBorderLeft(CellStyle.BORDER_THIN);
		headStyle.setBorderRight(CellStyle.BORDER_THIN);
		headStyle.setTopBorderColor(IndexedColors.BLUE.index);
		headStyle.setBottomBorderColor(IndexedColors.BLUE.index);
		headStyle.setLeftBorderColor(IndexedColors.BLUE.index);
		headStyle.setRightBorderColor(IndexedColors.BLUE.index);
	}
	
	/**
	 * @Description: 初始化表头行字体
	 */
	private static void initHeadFont()
	{
		headFont.setFontName("宋体");
		headFont.setFontHeightInPoints((short) 10);
		headFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		headFont.setCharSet(Font.DEFAULT_CHARSET);
		headFont.setColor(IndexedColors.BLUE_GREY.index);
	}
	
	/**
	 * @Description: 初始化内容行样式
	 */
	private static void initContentCellStyle()
	{
		contentStyle.setAlignment(CellStyle.ALIGN_CENTER);
		contentStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		contentStyle.setFont(contentFont);
		contentStyle.setBorderTop(CellStyle.BORDER_THIN);
		contentStyle.setBorderBottom(CellStyle.BORDER_THIN);
		contentStyle.setBorderLeft(CellStyle.BORDER_THIN);
		contentStyle.setBorderRight(CellStyle.BORDER_THIN);
		contentStyle.setTopBorderColor(IndexedColors.BLUE.index);
		contentStyle.setBottomBorderColor(IndexedColors.BLUE.index);
		contentStyle.setLeftBorderColor(IndexedColors.BLUE.index);
		contentStyle.setRightBorderColor(IndexedColors.BLUE.index);
		contentStyle.setWrapText(true);	// 字段换行
	}
	
	/**
	 * @Description: 初始化内容行字体
	 */
	private static void initContentFont()
	{
		contentFont.setFontName("宋体");
		contentFont.setFontHeightInPoints((short) 10);
		contentFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		contentFont.setCharSet(Font.DEFAULT_CHARSET);
		contentFont.setColor(IndexedColors.BLUE_GREY.index);
	}

}

	