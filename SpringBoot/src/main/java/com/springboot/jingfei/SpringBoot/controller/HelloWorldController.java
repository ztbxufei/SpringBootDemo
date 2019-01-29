package com.springboot.jingfei.SpringBoot.controller;

import com.alibaba.fastjson.JSON;
import com.springboot.jingfei.SpringBoot.annotation.SysLog;
import com.springboot.jingfei.SpringBoot.bean.Select;
import com.springboot.jingfei.SpringBoot.bean.User;
import com.springboot.jingfei.SpringBoot.framework.controller.BaseController;
import com.springboot.jingfei.SpringBoot.service.ReportService;
import com.springboot.jingfei.SpringBoot.service.UserService;
import com.springboot.jingfei.SpringBoot.utils.ExportExcel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * RestController注解返回json字符串
 * RestController注解是被Controller和ResponseBody注解修饰的
 */

@RestController
public class HelloWorldController extends BaseController {

    Logger logger = Logger.getLogger(HelloWorldController.class);

    @Autowired
    private ReportService reportService;

    @Autowired
    private UserService userService;

	@RequestMapping("insert")
    @SysLog(name = "insert方法", value="增加报表指定配置")
	public String insert() {
        try {
            //reportService.updateReportSetting(Constant.UPDATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "OK";
    }

    @RequestMapping("delete")
    @SysLog(name = "delete方法", value="删除报表指定配置")
    public String delete() {
        try {
            //reportService.updateReportSetting(Constant.DELETE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "OK";
    }

    @RequestMapping("insertMenuByParent")
    @SysLog(name = "insertMenuByParent方法", value="增加子菜单")
    public String insertMenuByParent(HttpServletRequest request){
	    String pid = request.getParameter("pid");
	    return pid;
    }

    @RequestMapping("standardData")
    @SysLog(name = "standardData方法", value="跳转standardData页面")
    public ModelAndView standardData(HttpServletRequest request){
        return returnView(request);
    }

    @RequestMapping("bootStrapMenu")
    @SysLog(name = "bootStrapMenu方法", value="跳转bootStrapMenu页面")
    public ModelAndView bootStrapMenu(HttpServletRequest request){
        return returnView(request);
    }

    /**
     * 前台分页
      * @param request
     * @return
     */
    @RequestMapping("pageTable")
    @ResponseBody
    public ModelAndView pageTable(HttpServletRequest request){
        ModelAndView modelAndView = returnView(request);
        Map paramMap = getParameterMap(request);
        try {
            Map userList = userService.getAllUser(paramMap);
            modelAndView.addObject("userList", userList);
        } catch (Exception e){
            logger.error("前台分页失败" + e.getMessage());
        }
        return modelAndView;
    }

    /**
     * 后台分页
     * @param request
     * @return
     */
    @RequestMapping("codeTable")
    @ResponseBody
    public ModelAndView codeTable(HttpServletRequest request){
        ModelAndView modelAndView = returnView(request);
        try {
//            List<Select> selectList = userService.getSelectCodeList();
//            modelAndView.addObject("selectList", JSON.toJSONString(selectList));
        } catch (Exception e){
            logger.error("后台分页失败: " + e.getMessage());
        }
        return modelAndView;
    }


    /**
     * 获取所有用户
     * @param request
     * @return
     */
    @RequestMapping("codeTable1")
    public String codeTable1(HttpServletRequest request){
        Map paramMap = getParameterMap(request);
        try {
            Map resultMap = userService.getAllUser(paramMap);
            return JSON.toJSONString(resultMap);
        } catch (Exception e){
            logger.error("后台分页失败: " + e.getMessage());
        }
        return null;
    }

    /**
     * 导出数据
     * @param request
     * @return
     */
    @RequestMapping("exportCodeTableData")
    public String exportCodeTableData(HttpServletRequest request, HttpServletResponse response){
        Map paramMap = getParameterMap(request);
        try {
            Map resultMap = userService.getAllUser(paramMap);
            //从结果集中获取
            List<User> userList = getEntityList(resultMap);
            //从请求参数中获取
            LinkedHashMap linkedHashMap = getLinkedHashMap(paramMap);
            ExportExcel.export(response, "用户表", linkedHashMap, userList);
            return JSON.toJSONString(resultMap);
        } catch (Exception e){
            logger.error("后台分页失败: " + e.getMessage());
        }
        return null;
    }
}
