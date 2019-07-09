package aop.version5;

import aop.version5.staticProxy.Bird;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import javax.tools.JavaCompiler;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author CreateByQinDaoFang on 2019/05/31 14:25
 * description:我们使用 JavaPoet 这个第三方库帮我们生成TimeProxy的源码
 */
public class Proxy {

    private static final String genePackageName = "aop.version5.code";
    private static final String genePackagePath = "aop\\version5\\code\\";
    private static final String filePath = "D:\\Deve-file\\code\\springboot-every-demo\\FangAOP\\src\\main\\java\\";
    private static final String compileName = "TimeProxy.java";

    public static Object newProxyInstance() throws Exception{

        TypeSpec.Builder typSpecBuilder =
                TypeSpec.classBuilder("TimeProxy")
                        .addSuperinterface(Flyable.class);

        FieldSpec fieldSpec = FieldSpec.builder(Flyable.class,"flyable",Modifier.PRIVATE).build();
        typSpecBuilder.addField(fieldSpec);

        //无参构造函数
        MethodSpec NoConMethodSpec =
                MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PUBLIC)
                        .build();
        typSpecBuilder.addMethod(NoConMethodSpec);
        //构造函数
        MethodSpec cuMethodSpec =
                MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(Flyable.class,"fly")
                        .addStatement("this.flyable = fly")
                        .build();
        typSpecBuilder.addMethod(cuMethodSpec);



        Method[] methods = Flyable.class.getDeclaredMethods();

        for (Method method : methods) {
            MethodSpec methodSpec =
                    MethodSpec.methodBuilder(method.getName())
                            .addModifiers(Modifier.PUBLIC)
                            .addAnnotation(Override.class)
                            .addStatement("long startTime = $T.currentTimeMillis()",System.class)
                            .addCode("\n")
                            .addStatement("this.flyable."+method.getName()+"()")
                            .addCode("\n")
                            .addStatement("long endTime = $T.currentTimeMillis()",System.class)
                            .addCode("\n")
                            .addStatement("$T.out.println(\" proxy newProxyInstance Fly Time =\" + (endTime - startTime))", System.class)
                            .build();
            typSpecBuilder.addMethod(methodSpec);
        }

        JavaFile javaFile = JavaFile.builder(genePackageName,typSpecBuilder.build()).build();
        javaFile.writeTo(new File(filePath));

        FJavaCompiler.compile(new File(filePath+genePackagePath +compileName));

        //加载到内存
        URL[] urls = new URL[]{new URL("file:/"+filePath+genePackageName)};
        URLClassLoader classLoader = new URLClassLoader(urls);
        Class clazz = classLoader.loadClass(genePackageName+".TimeProxy");
        Constructor constructor =clazz.getConstructor(Flyable.class);
        constructor.setAccessible(true);
        Flyable flyable = (Flyable) constructor.newInstance(new Bird());
        flyable.fly();
        return clazz;
    }

    public static Object newProxyInstance(Class inf, InvocationHandler handler) throws Exception {
        TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder("TimeProxyByHandler")
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(inf);

        FieldSpec fieldSpec = FieldSpec.builder(InvocationHandler.class, "handler", Modifier.PRIVATE).build();
        typeSpecBuilder.addField(fieldSpec);

        MethodSpec constructorMethodSpec = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(InvocationHandler.class, "handler")
                .addStatement("this.handler = handler")
                .build();

        typeSpecBuilder.addMethod(constructorMethodSpec);

        Method[] methods = inf.getDeclaredMethods();
        for (Method method : methods) {
            MethodSpec methodSpec = MethodSpec.methodBuilder(method.getName())
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .returns(method.getReturnType())
                    .addCode("try {\n")
                    .addStatement("\t$T method = " + inf.getName() + ".class.getMethod(\"" + method.getName() + "\")", Method.class)
                    // 为了简单起见，这里参数直接写死为空
                    .addStatement("\tthis.handler.invoke(this, method, null)")
                    .addCode("} catch(Exception e) {\n")
                    .addCode("\te.printStackTrace();\n")
                    .addCode("}\n")
                    .build();
            typeSpecBuilder.addMethod(methodSpec);
        }

        JavaFile javaFile = JavaFile.builder(genePackageName, typeSpecBuilder.build()).build();
        // 为了看的更清楚，我将源码文件生成到桌面
        javaFile.writeTo(new File(filePath));

        // 编译
        File f = new File(filePath+genePackagePath + "TimeProxyByHandler.java");
        FJavaCompiler.compile(f);

        // 使用反射load到内存
        URL[] urls = new URL[]{new URL("file:/"+filePath+genePackageName)};
        URLClassLoader classLoader = new URLClassLoader(urls);
        Class clazz = classLoader.loadClass(genePackageName+".TimeProxyByHandler");
        Constructor constructor = clazz.getConstructor(InvocationHandler.class);
        Object obj = constructor.newInstance(handler);

        return obj;
    }



}
