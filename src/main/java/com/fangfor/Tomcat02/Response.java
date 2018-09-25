package com.fangfor.Tomcat02;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/9/10
 * 16:52
 * #
 * 封装response
 * 　　　　步骤：
 * 　　　　A:构建报文头
 * 　　　　B:构建响应的HTML正文内容
 * 　　　　C：将报文头和HTML正文内容发送给客户端（浏览器）
 */
public class Response {
    private static final String ENTER = "\r\n";
    private static final String SPACE = " ";

    //储存头部
    private StringBuilder headerInfo ;
    //储存正文信息
    private StringBuilder textContext;
    //记录正文信息长度
    private int contextLength;
    //构造输出流
    private BufferedWriter bwriter;


    public Response(){
        headerInfo = new StringBuilder();
        textContext  = new StringBuilder();
        contextLength = 0;
    }
    public Response(OutputStream output){
        this();
        bwriter = new BufferedWriter(new OutputStreamWriter(output));
    }
    /**
     * 创建头部信息 html报文
     * @param code
     */

    private void createHeader(int code){
        headerInfo.append("HTTP/1.1").append(SPACE).append(code).append(SPACE);
        switch (code) {
            case 200:
                headerInfo.append("OK").append(ENTER);
                break;
            case 404:
                headerInfo.append("NOT FOUND").append(ENTER);
                break;
            case 500:
                headerInfo.append("SERVER ERROR").append(ENTER);
                break;
            default:
                break;
        }
        headerInfo.append("Server:myServer").append(SPACE).append("0.0.1v").append(ENTER);
        headerInfo.append("Date:Sat,"+SPACE).append(new Date()).append(ENTER);
        headerInfo.append("Content-Type:text/html;charset=UTF-8").append(ENTER);
        headerInfo.append("Content-Length:").append(contextLength).append(ENTER);
        headerInfo.append(ENTER);
    }

    /**
     * 响应给浏览器解析的内容（html正文）
     * @param content
     * @return
     */
    public Response htmlContent(String content){
        textContext.append(content).append(ENTER);
        contextLength += (content+ENTER).toString().getBytes().length;
        return this;
    }
    /**
     * 发送给浏览器端
     * @param code
     */
    public void pushClient(int code) throws IOException {
        createHeader(code);
        bwriter.append(headerInfo.toString());
        System.out.println(headerInfo.toString());
        bwriter.append(textContext.toString());
        System.out.println(bwriter);
    }

}
