package com.fangfor.Tomcat01;

import java.io.IOException;
import java.io.InputStream;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/9/9
 * 22:15
 * #
 */
public class Request {

    private InputStream inputStream;
    private String url;

    public Request(InputStream input){
        this.inputStream = input;
    }
    //获取信息
    public void parse(){
        //从socket中读取一个2048长度的字符
        StringBuilder stringBuilder = new StringBuilder(Response.BUFFER_SIZE);
        int i ;
        byte[] buffer = new byte[Response.BUFFER_SIZE];
        try {
            i = inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }
        for (int j=0;j<i;j++){
            stringBuilder.append(buffer[j]);
        }
        //打印socket的内容
        System.out.println(stringBuilder.toString());
        url = parseUrl(stringBuilder.toString());
        //打印socket的内容
        System.out.println("url"+url);
    }

    public String parseUrl(String reuestString){
        int index1,index2=0;
        //看socket获取是否有值
        index1 = reuestString.indexOf(' ');
        if (index1 != -1){
            index2 = reuestString.indexOf(' ',index1+1);
            if (index2>index1){
                return reuestString.substring(index1+1,index2);
            }
            return  null;
        }
        System.out.println("----->>>>"+index1);
        System.out.println("----->>>>"+index2);
        return null;
    }

    public String getUrl(){
        return url;
    }
}
