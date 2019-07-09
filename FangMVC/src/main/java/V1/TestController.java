package V1;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@FangController
@FangRequestMapping("/test")
public class TestController {
 

 
  @FangRequestMapping("/doTest")
   public void test1(HttpServletRequest request, HttpServletResponse response,
       @FangRequestParam("param") String param){
    System.out.println(param);
     try {
           response.getWriter().write( "doTest method success! param:"+param);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
  
  
  @FangRequestMapping("/doTest2")
   public void test2(HttpServletRequest request, HttpServletResponse response){
       try {
           response.getWriter().println("doTest2 method success!");
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}