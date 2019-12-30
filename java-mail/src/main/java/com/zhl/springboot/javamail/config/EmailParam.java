package com.zhl.springboot.javamail.config;

/**
 * @author Lenovo
 * @title: EmailParam
 * @projectName spring-boot-emo
 * @description: TODO
 * @date 2019/12/29 14:23
 */
public class EmailParam {
    /**
     * 邮箱服务器地址
     */
    // public static final String emailHost = "smtp.mxhichina.com" ; 阿里云企业邮箱配置（账号+密码）
    // public static final String emailHost = "smtp.aliyun.com" ; 阿里云个人邮箱配置（账号+密码）
    public static final String emailHost = "smtp.163.com" ; // 网易邮箱配置（账号+授权码）
    /**
     * 邮箱协议
     */
    public static final String emailProtocol = "smtp" ;
    /**
     * 邮箱发件人
     */
    public static final String emailSender = "xxxxxx@163.com" ;
    /**
     * 邮箱授权码
     */
    public static final String password = "authCode";
    /**
     * 邮箱授权
     */
    public static final String emailAuth = "true" ;
    /**
     * 邮箱昵称
     */
    public static final String emailNick = "知了一笑" ;
}