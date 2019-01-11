package com.springboot.jingfei.SpringBoot.controller;

import com.springboot.jingfei.SpringBoot.annotation.SysLog;
import com.springboot.jingfei.SpringBoot.bean.User;
import com.springboot.jingfei.SpringBoot.constant.Constant;
import com.springboot.jingfei.SpringBoot.service.ReportService;
import com.springboot.jingfei.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * RestController注解返回json字符串
 * RestController注解是被Controller和ResponseBody注解修饰的
 */

@RestController
public class HelloWorldController {

    @Autowired
    private ReportService reportService;

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
     * 纯粹的返回视图，中间不加任何渲染
     * 前提是路径名称和视图名称一致
     * @param request
     * @return
     */
    private ModelAndView returnView(HttpServletRequest request){
        String filePath = request.getServletPath();
        filePath = filePath.substring(1, filePath.length());
        ModelAndView modelAndView = new ModelAndView(filePath);
        return modelAndView;
    }

}
