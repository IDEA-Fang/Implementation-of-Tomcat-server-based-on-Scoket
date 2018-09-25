package com.fangfor.Tomcat02;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/9/10
 * 15:43
 * #
 * 1-服务器端监听请求
 * 2-根据上面的服务器相应内容来写一个针对浏览器客户端的响应：
 */
public class Server {

    private static  final String ENTER = "\r\n";
    private static  final String SPACE = " ";
    private static  final Integer PORT = 8080 ;


    public static  void main(String[] args) throws IOException {
        //1创建一个服务并开启
        Server server = new Server();
        server.start();
    }

    private void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        //2接受请求
        this.receive(serverSocket);
    }

    private void receive(ServerSocket socket) throws IOException {
        BufferedReader bufferedReader = null;
        Socket client = socket.accept();
        //3打印信息
        Reader reader = new InputStreamReader(client.getInputStream());
        bufferedReader = new BufferedReader(reader);
        StringBuilder httpRequest = new StringBuilder();
        String msg = null;


        //4循环监听
        while ( !( msg = bufferedReader.readLine().trim()).equals("") ){
            httpRequest.append(msg);
            httpRequest.append("\r\n");
        }
        System.out.println(httpRequest.toString());
        this.httpResponse(client);
    }

    private void httpResponse(Socket client) throws IOException {
        BufferedWriter bufferedWriter = null;
        Writer out = new OutputStreamWriter(client.getOutputStream());
        bufferedWriter = new BufferedWriter(out);

        StringBuilder contextText = new StringBuilder();
        contextText.append("<html><head></head><body>This is my page</body></html>");

        StringBuilder sb = new StringBuilder();
        /*通用头域begin*/
        sb.append("HTTP/1.1").append(SPACE).append("200").append(SPACE).append("OK").append(ENTER);
        sb.append("Server:myServer").append(SPACE).append("0.0.1v").append(ENTER);
        sb.append("Date:Sat," + SPACE).append(new Date()).append(ENTER);
        sb.append("Content-Type:text/html;charset=UTF-8").append(ENTER);
        sb.append("Content-Length:").append(contextText.toString().getBytes().length).append(ENTER);
        /*通用头域end*/
        sb.append(ENTER);//空一行
        sb.append(contextText);//正文部分

        System.out.println(sb.toString());
        bufferedWriter.write(sb.toString());//写会
        bufferedWriter.flush();

        bufferedWriter.close();
    }

}
