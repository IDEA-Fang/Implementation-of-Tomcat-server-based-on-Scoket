package com.fangfor.Tomcat01;

import java.io.*;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/9/9
 * 22:18
 * #
 */
public class Response {

    public static final int BUFFER_SIZE = 2048;
    //浏览器访问D盘的文件
    private static final String WEB_ROOT ="D:\\";
    private Request request;
    private OutputStream outputStream;

    public Response(OutputStream output) {
        this.outputStream = output;
    }
    public void setRequest(Request request){
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fileInputStream = null;

        //拼接本地目录和浏览器端口号后面的目录
        File file = new File(WEB_ROOT,request.getUrl());
        System.out.println("----<<<"+file);
        //如果文件存在，而且不是目录
        if (file.exists() && !file.isDirectory()){
            fileInputStream = new FileInputStream(file);
            int ch = fileInputStream.read(bytes,0,BUFFER_SIZE);
            while (ch!=-1){
                outputStream.write(bytes,0,ch);
                ch =fileInputStream.read(bytes, 0,BUFFER_SIZE);
            }
        }else {
            //文件不存在，返回给浏览器响应提示,这里可以拼接HTML任何元素
            String htmlMessage = "<h1>"+file.getName()+" file or directory not exists</h1>";
            String returnMessage ="HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: "+htmlMessage.length()+"\r\n" +
                    "\r\n" +
                    htmlMessage;
            outputStream.write(returnMessage.getBytes());
        }
        fileInputStream.close();
    }

}
