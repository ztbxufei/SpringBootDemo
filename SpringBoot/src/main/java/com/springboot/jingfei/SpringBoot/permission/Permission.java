package com.springboot.jingfei.SpringBoot.permission;

/**
 * 权限控制
 * @Author: jingfei
 * @Date: 2019/4/1 20:43
 * @Version 1.0
 */
public class Permission {
    private static int ALLOW_INSERT = 1;
    private static int ALLOW_UPDATE = ALLOW_INSERT >> 1;
    private static int ALLOW_SELECT = ALLOW_INSERT >> 2;
    private static int ALLOW_DELETE = ALLOW_INSERT >> 3;

    private int flag;

    public void setFlag(int flag){
        this.flag = flag;
    }

    /**
     * 新增权限
     * @param per
     */
    public void enable(int per){
        flag |= per;
    }

    /**
     * 删除权限
     * @param per
     */
    public void disable(int per){
        flag = flag &~per;
    }

    /**
     * 判断是否有权限
     * @param per
     */
    public boolean isAllow(int per){
        return (flag & per) == per;
    }

    /**
     * 判断是否不存在权限
     * @param per
     */
    public boolean isNotAllow(int per){
        return (flag & per) == 0;
    }

    public static void main(String[] args){
        Permission permission = new Permission();
        permission.setFlag(15);
        permission.disable(ALLOW_DELETE | ALLOW_INSERT);
        System.out.println("SELECT = " + permission.isAllow(ALLOW_SELECT));
        System.out.println("UPDATE = " + permission.isAllow(ALLOW_UPDATE));
        System.out.println("DELETE = " + permission.isNotAllow(ALLOW_DELETE));
        System.out.println("INSERT = " + permission.isNotAllow(ALLOW_INSERT));
    }
}
