# readme
## 基于Scoket的Tomcat的实现 

项目设计
##### 功能设计
![实现原理](https://pan.baidu.com/s/11hlUDEwZshXcaPBaKR6PmA)

### 角色划分：

### 功能分析：
实现以上效果整体思路如下：
      1. ServerSocket占用8080端口，用while（true）循环等待用户发请求。
      2. 拿到浏览器的请求，解析并返回URL地址，用I/O输入流读取本地磁盘上相应文件。 
      3. 读取文件，不存在构建响应报文头、HTML正文内容，存在则写到浏览器端。
实现：    
      1. HttpServer核心处理类，用于接受用户请求，传递HTTP请求头信息，关闭容器：
      2. 创建Request类，获取HTTP的请求头所有信息并截取URL地址返回：
      3. 创建Response类，响应请求读取文件并写回到浏览器
      4. 封装request
           　　　　步骤：
           　　　　A:接受浏览器发送的请求
           　　　　B:解析浏览器发送来的请求
      5. 封装response
           　　　　步骤：
           　　　　A:构建报文头
           　　　　B:构建响应的HTML正文内容
           　　　　C：将报文头和HTML正文内容发送给客户端（浏览器）
### 技术框架

### 技术点

![项目效果1](https://pan.baidu.com/s/1dj7wJ3SgY3fFBmHG3vg19g)
![项目效果2](https://pan.baidu.com/s/1JqCO7riyMkr528ppJMBynw)

后端开发，按照dao->service->controller的api的顺序开发

### 开发进度：

### 后续开发及完善
 >> * xml文件的加载或者其他格式文件加载
 >> * 抽离Request和Response
 >> * 编写servlet
 >> * 实现多个servlet的访问
 >> * 监听器实现

### 代码解释

2018.9.25整理
---------------

