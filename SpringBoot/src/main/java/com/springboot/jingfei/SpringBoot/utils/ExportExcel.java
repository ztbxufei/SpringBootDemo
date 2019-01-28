package com.springboot.jingfei.SpringBoot.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class ExportExcel {

    private static final int sheetCount = 100000; // 超过100000条就分页

    public static <T> HSSFWorkbook export(HttpServletResponse response, String fileName, LinkedHashMap<String, String> excelHeader, List<T> dataList) {
        // 创建一个工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        int pageIndex = 0; // sheet页的下标
        int sheetIndex = 0; // 判断当前数据是第几行数据
        HSSFSheet sheet = createHSSFSheet(wb, fileName, excelHeader, pageIndex); // 创建一个新的sheet页
        // 设置正文样式
        HSSFCellStyle titleStyle = getHSSFCellStyle(wb);
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 12); // 字体高度
        titleStyle.setFont(font);
        List<String> fieldList = getFieldList(excelHeader);
        try {
            // 插入数据
            Iterator it = dataList.iterator();
            while (it.hasNext()) {
                if (++sheetIndex > sheetCount * (pageIndex + 1)) {
                    sheet = createHSSFSheet(wb, fileName, excelHeader, ++pageIndex); // 重新新建一个sheet页
                }
                HSSFRow dataRow = sheet.createRow(sheetIndex - sheetCount * pageIndex);
                T t = (T) it.next();
                for (int i = 0; i < fieldList.size(); i++) {
                    HSSFCell dataCell = dataRow.createCell(i);
                    dataCell.setCellStyle(titleStyle);
                    Class clazz = t.getClass();
                    String field = fieldList.get(i);
                    String methodName = "get" + field.substring(0, 1).toUpperCase() + field.substring(1, field.length());
                    Method method = clazz.getMethod(methodName, new Class[]{});
                    Object value = method.invoke(t, new Class[]{});
                    dataCell.setCellValue(value.toString());
                    sheet.autoSizeColumn(i + 1);
                }
            }
            // 设置请求
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1") + ".xls");
            // 打开流
            OutputStream outputStream = response.getOutputStream();
            // HSSFWorkbook写入流
            wb.write(outputStream);
            // 刷新流
            outputStream.flush();
            // 关闭流
            outputStream.close();
            // 关闭工作簿
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wb;
    }

    private static HSSFSheet createHSSFSheet(HSSFWorkbook wb, String fileName, LinkedHashMap<String, String> excelHeader, int pageIndex) {
        HSSFCellStyle titleStyle = getHSSFCellStyle(wb);
        // 创建一个sheet页
        HSSFSheet sheet = wb.createSheet(fileName + "-sheet" + pageIndex);
        // 设置冻结列
        sheet.createFreezePane(excelHeader.size(), 1);
        // 创建标题行
        HSSFRow row = sheet.createRow(0);
        List<String> valueList = new ArrayList<>(); // 表头内容
        for (String key : excelHeader.keySet()) {
            valueList.add(excelHeader.get(key));
        }
        // 创建表头
        for (int i = 0; i < valueList.size(); i++) {
            // 创建单元格
            HSSFCell titleCell = row.createCell(i);
            titleCell.setCellValue(valueList.get(i));
            titleCell.setCellStyle(titleStyle);
            sheet.autoSizeColumn(i + 1);
        }
        return sheet;
    }

    private static HSSFCellStyle getHSSFCellStyle(HSSFWorkbook wb) {
        // 单元格样式类
        HSSFCellStyle titleStyle = wb.createCellStyle();
        // 设置单元格边框样式
        titleStyle.setBorderTop(BorderStyle.THIN);// 上边框 细边线
        titleStyle.setBorderBottom(BorderStyle.THIN);// 下边框 细边线
        titleStyle.setBorderLeft(BorderStyle.THIN);// 左边框 细边线
        titleStyle.setBorderRight(BorderStyle.THIN);// 右边框 细边线
        // 设置单元格对齐方式
        titleStyle.setAlignment(HorizontalAlignment.CENTER); // 水平居中
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 垂直居中
        // 设置字体样式
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) 15); // 字体高度
        titleFont.setFontName("黑体"); // 字体样式
        titleStyle.setFont(titleFont);
        return titleStyle;
    }

    private static List<String> getFieldList(LinkedHashMap<String, String> excelHeader) {
        List<String> fieldList = new ArrayList<>(); // 表头字段
        for (String key : excelHeader.keySet()) {
            fieldList.add(key);
        }
        return fieldList;
    }

//    public static int getChineseNum(String context) {    ///统计context中是汉字的个数
//        int lenOfChinese = 0;
//        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");    //汉字的Unicode编码范围
//        Matcher m = p.matcher(context);
//        while (m.find()) {
//            lenOfChinese++;
//        }
//        return lenOfChinese;
//    }
}
