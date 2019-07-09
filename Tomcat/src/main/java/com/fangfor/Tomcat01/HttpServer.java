package com.fangfor.Tomcat01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/9/9
 * 21:43
 * #.HttpServer核心处理类，用于接受用户请求，传递HTTP请求头信息，关闭容器：
 */
public class HttpServer {

    public boolean shutdown = false;

    public void acceptWait(){

        ServerSocket serverSocket = null;
        try {
            InetAddress address = InetAddress.getByName("127.0.0.1");
             serverSocket = new ServerSocket(8080,1,address);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        //等待用户发来请求
        //死循环监听端口
        while (!shutdown){

            try {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream =socket.getOutputStream();
                //接受请求参数
                Request request = new Request(inputStream);
                request.parse();
                // 创建用于返回浏览器的对象
                Response response = new Response(outputStream);
                response.setRequest(request);
                response.sendStaticResource();
                //关闭一次请求的socket,因为http请求就是采用短连接的方式
                socket.close();
                //如果请求地址是/shutdown  则关闭容器
                if(null != request){
                    shutdown = request.getUrl().equals("/shutdown");
                }
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }


    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.acceptWait();
    }
}
