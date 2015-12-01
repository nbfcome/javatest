package com.sina.ea;

import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 封装Excel导出的设置信息
 * @author songjuan
 *
 */
@SuppressWarnings("rawtypes")
public class ExportSetInfo {

	private LinkedHashMap<String, List> objsMap;
	
	private String[] titles;
	
	private List<String[]> headNames;
	
	private List<String[]> fieldNames;
	
	private List<int[]> colWidths;
	
	private List<String[]> avgValues;
	
	private List<String[]> sumValues;
	
	private OutputStream out;

	
	public LinkedHashMap<String, List> getObjsMap()
	{
		return objsMap;
	}

	public List<int[]> getColWidths() {
		return colWidths;
	}

	/**
	 * 设置列宽，此参数若不设置
	 * @param colWidths
	 */
	public void setColWidths(List<int[]> colWidths) {
		this.colWidths = colWidths;
	}

	public List<String[]> getAvgValues() {
		return avgValues;
	}

	/**
	 * 设置计算好的平均值
	 * @param avgValues
	 */
	public void setAvgValues(List<String[]> avgValues) {
		this.avgValues = avgValues;
	}

	public List<String[]> getSumValues() {
		return sumValues;
	}

	/**
	 * 设置计算好的合计值
	 * @param sumValues
	 */
	public void setSumValues(List<String[]> sumValues) {
		this.sumValues = sumValues;
	}

	/**
	 * @param objMap 导出数据
	 * 
	 * String : 代表sheet名称
	 * List : 代表单个sheet里的所有行数据   泛型
	 */

	public void setObjsMap(LinkedHashMap<String, List> objsMap)
	{
		this.objsMap = objsMap;
	}

	public List<String[]> getFieldNames()
	{
		return fieldNames;
	}

	/**
	 * @param clazz 对应每个sheet里的每行数据的对象的属性名称
	 */
	public void setFieldNames(List<String[]> fieldNames)
	{
		this.fieldNames = fieldNames;
	}

	public String[] getTitles()
	{
		return titles;
	}

	/**
	 * @param titles 对应每个sheet里的标题，即顶部大字
	 */
	public void setTitles(String[] titles)
	{
		this.titles = titles;
	}

	public List<String[]> getHeadNames()
	{
		return headNames;
	}

	/**
	 * @param headNames 对应每个页签的表头的每一列的名称
	 */
	public void setHeadNames(List<String[]> headNames)
	{
		this.headNames = headNames;
	}

	public OutputStream getOut()
	{
		return out;
	}

	/**
	 * @param out Excel数据将输出到该输出流
	 */
	public void setOut(OutputStream out)
	{
		this.out = out;
	}
}
