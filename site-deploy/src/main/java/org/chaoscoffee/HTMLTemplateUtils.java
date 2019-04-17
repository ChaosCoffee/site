package org.chaoscoffee;

import com.alibaba.fastjson.JSONObject;
import com.github.jknack.handlebars.internal.Files;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

public class HTMLTemplateUtils {


    public static void main(String[] args) throws IOException {
        String path = "";
        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("user.dir"))
                .append(File.separator)
                .append("src")
                .append(File.separator)
                .append("main")
                .append(File.separator)
                .append("resources")
                .append(File.separator)
                .append("example.json");
        path = sb.toString();
        System.out.println("path = " + path);
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("文件不存在...");
            return;
        }
        String jsonString = Files.read(file, Charset.forName("UTF-8"));
        System.out.println("解析后 = \n" + jsonString);
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        //模板所在目录，相对于当前classloader的classpath。
        resolver.setPrefix("");
        //模板文件后缀
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("UTF-8");

        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);

        //构造上下文(Model)
        Context context = new Context();
        context.setVariable("results", jsonObject);

        //渲染模板
//        FileWriter writer = new FileWriter("result.html");//存在gbk乱码问题
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("result.html"), "UTF-8");
        System.out.println("writer = " + writer.getEncoding());

        //这个example.html 放在resources 下面.
        // 这样会生成一个result.html文件,结果都已经放进去了.
        engine.process("example", context, writer);
        System.out.println("生成完成!");
        writer.close();
        engine.clearTemplateCache();

    }

}
