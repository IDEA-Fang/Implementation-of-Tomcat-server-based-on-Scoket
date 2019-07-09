package com.fangfor.Tomcat02;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/9/10
 * 17:22
 * #
 * 封装request
 * 　　　　步骤：
 * 　　　　A:接受浏览器发送的请求
 * 　　　　B:解析浏览器发送来的请求
 */
public class Request {
    private static final String Enter = "\r\n";
    //接受请求
    private BufferedReader request;
    //储存信息
    private String requestHeader;
    //通过请求头得到请求方法
    private String method;
    //通过解析请求头获取url
    private String action;
    //通过解析头信息得到传过来的请求参数 ，可能存在一Key多Value的情况所以用list
    private Map<String,List<String> > parameter;
    //获得浏览器发来的消息
    public Request(){
        requestHeader = "";
        method = "";
        action = "";
        parameter = new HashMap<String, List<String>>();
    }

    public Request(InputStream inputStream) throws IOException {
        this();
        request = new BufferedReader(new InputStreamReader(inputStream));
        //接受头部信息
        String temp;
        while ( !(temp  =request.readLine()).equals("")) {
            requestHeader += (temp+Enter);
        }
        System.out.println(requestHeader);
        }
        /**
         *  解析头信息
         */
        public void parseRequestHeader(){
            //声明一个字符串存放请求参数
            String parameterString  = "";
            //读取头部信息第一行
            String fristLine = requestHeader.substring(0,requestHeader.indexOf(Enter) );
            //开始分离第一行
            //split Point分割点1
            int splitPointOne = fristLine.indexOf("/");
            method = fristLine.substring(0,splitPointOne).trim();
            //分割点2
            int splitPointTwo = fristLine.indexOf("HTTP/");
            String actionTemp = fristLine.substring(splitPointOne,splitPointTwo).trim();

            if (method.equalsIgnoreCase("post")) {
                //这里得到post参数的字符串
                this.action = actionTemp;
            }else if (method.equalsIgnoreCase("get")){
                if (actionTemp.contains("?")){
                    parameterString = actionTemp.substring((actionTemp.indexOf("?")+1)).trim();
                    this.action = actionTemp.substring(0,actionTemp.indexOf("?"));
                }else {
                    this.action = actionTemp;
                }
                //将参数封装在Map中
                parseParameterString(parameterString);
            }
        }
    /**
     * 解析参数字符串，将参数封装到Map中
     * @param parameterString
     */
    private void parseParameterString(String parameterString) {}


    }



