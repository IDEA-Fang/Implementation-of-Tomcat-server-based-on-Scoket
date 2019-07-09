package aop.version5;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author CreateByQinDaoFang on 2019/05/31 15:09
 * description:
 */
public class FJavaCompiler {

    public static void compile(File javaFile) throws IOException {
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manager = javaCompiler.getStandardFileManager(null,null,null);
        Iterable iterator = manager.getJavaFileObjects(javaFile);
        JavaCompiler.CompilationTask task = javaCompiler.getTask(null,manager,null,null,null,iterator);
        task.call();
        manager.close();
    }
}
