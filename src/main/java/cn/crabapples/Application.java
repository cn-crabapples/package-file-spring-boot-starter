//package cn.crabapples;
//
//import cn.crabapples.file.service.PackageFileUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.scheduling.annotation.EnableScheduling;
//
//import javax.annotation.PostConstruct;
//import java.io.IOException;
//
//@SpringBootApplication
//@EnableScheduling
//public class Application {
//    @Autowired
//    PackageFileUtils packageFileUtils;
//
//    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
//    }
//
//    @PostConstruct
//    public void test() throws IOException, InterruptedException {
//        System.out.println("test()");
//        String[] files = new String[]{"e:/1.xml"};
//        String outputPath = "e:/";
//        String file = packageFileUtils.packageFile(files, outputPath, true, 5L);
//        System.out.println("文件打包完成：" + file);
//
//    }
//}
