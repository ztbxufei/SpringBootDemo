package com.springboot.jingfei.SpringBoot.utils.luoxianzhao;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: jingfei
 * @Date: 2019/1/30 12:42
 * @Version 1.0
 */
public class ReadSheet {
    public static void readSheet(Map<String, List<Map>> tableMap, String filePath) throws IOException {
        File finalXlsxFile = new File(filePath);
        FileInputStream in = new FileInputStream(finalXlsxFile);
        Workbook wb = new XSSFWorkbook(in);
        Sheet sheet = wb.getSheetAt(0);
        Row headRow = sheet.getRow(0);
        // 取出所有的表头
        List<String> headList = new ArrayList<>();
        for (int i = 0; i < headRow.getLastCellNum(); i++) {
            headRow.getCell(i).setCellType(CellType.STRING);
            String columnName = headRow.getCell(i).getStringCellValue();
            headList.add(columnName);
        }
        // 取出所有的正文
        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            String tableName = row.getCell(0).getStringCellValue();
            Map fieldMap = new HashMap();
            for (int j = 0; j < headList.size(); j++) {
                row.getCell(j).setCellType(CellType.STRING);
                fieldMap.put(headList.get(j), row.getCell(j).getStringCellValue());
            }
            if(tableMap.containsKey(tableName)){
                List<Map> list = tableMap.get(tableName);
                list.add(fieldMap);
            } else {
                List<Map> list = new ArrayList<>();
                tableMap.put(tableName, list);
                list.add(fieldMap);
            }
        }
    }

    public static void writeSheet(Map<String, List<Map>> tableMap, String path) throws IOException {
        // 创建一个工作簿
        File finalXlsxFile = new File(path);
        FileOutputStream out = new FileOutputStream(finalXlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("oracle");
        XSSFRow rowm = sheet.createRow(0);
        rowm.createCell(0).setCellValue("Hello");
        int rowIndex = 0;
        int count = 0;
        for(String key : tableMap.keySet()){
            // 表格首行
            XSSFRow row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(key);
            row = sheet.createRow(rowIndex++);
            XSSFCell titleCell = row.createCell(0);
            titleCell.setCellStyle(getHSSFCellStyle(wb));
            titleCell.setCellValue("Name");
            titleCell = row.createCell(1);
            titleCell.setCellValue(key);
            titleCell.setCellStyle(getHSSFCellStyle(wb));
            row = sheet.createRow(rowIndex++);
            titleCell = row.createCell(0);
            titleCell.setCellStyle(getHSSFCellStyle(wb));
            titleCell.setCellValue("Code");
            titleCell = row.createCell(1);
            titleCell.setCellValue(key);
            titleCell.setCellStyle(getHSSFCellStyle(wb));
            sheet.createRow(rowIndex++);

            // 第3,4行 主键
            row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue("主键");
            String keyName = "";
            Object chineseName = "";
            for(Map map : tableMap.get(key)){
                if("P".equals(map.get("主键"))){
                    keyName = map.get("字段名").toString();
                    chineseName = map.get("注释");
                }
            }
            row = sheet.createRow(rowIndex++);
            titleCell = row.createCell(0);
            titleCell.setCellStyle(getHSSFCellStyle(wb));
            titleCell.setCellValue("Name");
            titleCell = row.createCell(1);
            titleCell.setCellValue(chineseName == null ? "" : chineseName.toString());
            titleCell.setCellStyle(getHSSFCellStyle(wb));
            row = sheet.createRow(rowIndex++);
            titleCell = row.createCell(0);
            titleCell.setCellStyle(getHSSFCellStyle(wb));
            titleCell.setCellValue("Code");
            titleCell = row.createCell(1);
            titleCell.setCellValue(keyName);
            titleCell.setCellStyle(getHSSFCellStyle(wb));
            sheet.createRow(rowIndex++);

            List<Map> index = new ArrayList<>();
            List<Map> tableMapList = tableMap.get(key);
            for(Map map : tableMapList){
                if(map.get("索引") != null && !"".equals(map.get("索引").toString())){
                    index.add(map);
                }
            }

            // 第5,6行 索引
            row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue("索引");
            row = sheet.createRow(rowIndex++);
            titleCell = row.createCell(0);
            titleCell.setCellStyle(getHSSFCellStyle(wb));
            titleCell.setCellValue("Name");
            for(int i = 0; i < index.size(); i++){
                titleCell = row.createCell(i+1);
                titleCell.setCellValue(index.get(i).get("中文名") == null ? "" : index.get(i).get("中文名").toString());
                titleCell.setCellStyle(getHSSFCellStyle(wb));
            }
            row = sheet.createRow(rowIndex++);
            titleCell = row.createCell(0);
            titleCell.setCellStyle(getHSSFCellStyle(wb));
            titleCell.setCellValue("Code");
            for(int i = 0; i < index.size(); i++){
                titleCell = row.createCell(i+1);
                titleCell.setCellValue(index.get(i).get("字段名").toString());
                titleCell.setCellStyle(getHSSFCellStyle(wb));
            }
            sheet.createRow(rowIndex++);


            // 表字段
            row = sheet.createRow(rowIndex++);
            titleCell = row.createCell(0);
            titleCell.setCellStyle(getHSSFCellStyle(wb));
            titleCell.setCellValue("Name");
            titleCell = row.createCell(1);
            titleCell.setCellStyle(getHSSFCellStyle(wb));
            titleCell.setCellValue("Code");
            titleCell = row.createCell(2);
            titleCell.setCellStyle(getHSSFCellStyle(wb));
            titleCell.setCellValue("Comment");
            titleCell = row.createCell(3);
            titleCell.setCellStyle(getHSSFCellStyle(wb));
            titleCell.setCellValue("Data Type");
            titleCell = row.createCell(4);
            titleCell.setCellStyle(getHSSFCellStyle(wb));
            titleCell.setCellValue("Primary");
            titleCell = row.createCell(5);
            titleCell.setCellStyle(getHSSFCellStyle(wb));
            titleCell.setCellValue("Mandatory");
            for(Map map : tableMapList){
                row = sheet.createRow(rowIndex++);
                titleCell = row.createCell(0);
                titleCell.setCellStyle(getHSSFCellStyle(wb));
                titleCell.setCellValue(map.get("注释") == null ? "" : map.get("注释").toString());
                titleCell = row.createCell(1);
                titleCell.setCellStyle(getHSSFCellStyle(wb));
                titleCell.setCellValue(map.get("字段名").toString());
                titleCell = row.createCell(2);
                titleCell.setCellStyle(getHSSFCellStyle(wb));
                titleCell.setCellValue(map.get("注释") == null ? "" : map.get("注释").toString());
                titleCell = row.createCell(3);
                titleCell.setCellStyle(getHSSFCellStyle(wb));
                titleCell.setCellValue(map.get("字段类型").toString() + "(" + map.get("长度").toString() + ")");
                titleCell = row.createCell(4);
                titleCell.setCellStyle(getHSSFCellStyle(wb));
                if("P".equals(map.get("主键"))){
                    titleCell.setCellValue("TRUE");
                } else {
                    titleCell.setCellValue("FALSE");
                }
                titleCell = row.createCell(5);
                titleCell.setCellStyle(getHSSFCellStyle(wb));
                if("N".equals(map.get("能否为空"))){
                    titleCell.setCellValue("TRUE");
                } else {
                    titleCell.setCellValue("FALSE");
                }
            }

            sheet.createRow(rowIndex++);
            System.out.println("输出到第" + (++count) + "表");
            for(int i = 0; i< 10;i++){
                sheet.autoSizeColumn(i);
            }
        }
        wb.write(out);
    }

    private static XSSFCellStyle getHSSFCellStyle(XSSFWorkbook wb) {
        // 单元格样式类
        XSSFCellStyle titleStyle = wb.createCellStyle();
        // 设置单元格边框样式
        titleStyle.setBorderTop(BorderStyle.MEDIUM);// 上边框 细边线
        titleStyle.setBorderBottom(BorderStyle.MEDIUM);// 下边框 细边线
        titleStyle.setBorderLeft(BorderStyle.MEDIUM);// 左边框 细边线
        titleStyle.setBorderRight(BorderStyle.MEDIUM);// 右边框 细边线
        // 设置单元格对齐方式
        titleStyle.setAlignment(HorizontalAlignment.CENTER); // 水平居中
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 垂直居中
        // 设置字体样式
        Font titleFont = wb.createFont();
        titleStyle.setFont(titleFont);
        return titleStyle;
    }

//    public static void main(String[] args) throws IOException {
//        Map resultMap = new HashMap();
//        readSheet(resultMap, "D:/Desktop/oracle.xlsx");
//        writeSheet(resultMap, "D:/Desktop/oracleWrite.xlsx");
//    }
}
