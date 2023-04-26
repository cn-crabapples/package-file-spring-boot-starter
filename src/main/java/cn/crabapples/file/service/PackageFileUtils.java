package cn.crabapples.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.UUID;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * TODO 文件打包工具
 *
 * @author Mr.He
 * @description 2023/4/26 17:32
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mshe
 */
@Component
@Configuration
public class PackageFileUtils {
    private final DelFileTask delFileTask;

    public PackageFileUtils() {
        this.delFileTask = new DelFileTask();
    }

    /**
     * 文件打包工具
     *
     * @param srcFiles   需要打包的文件
     * @param outputPath 打包后文件输出路径
     * @param isTemp     是否为临时文件，临时文件将在一段时间后自动删除
     * @param time       文件保留时间，单位秒
     */
    public String packageFile(String[] srcFiles, String outputPath, boolean isTemp, Long time) throws IOException, InterruptedException {
        String fileName = packZip(srcFiles, outputPath);
        if (null == fileName) {
            throw new IOException("文件打包失败: files package fail");
        }
        if (isTemp) {
            delFileTask.addTask(fileName, time);
        }
        return fileName;
    }

    private static String packZip(String[] srcFiles, String destZipFile) {
        String uuid = UUID.randomUUID().toString();
        String finalName = MessageFormat.format("{0}/{1}.zip", destZipFile, uuid);
        byte[] buffer = new byte[1024];
        try {
            FileOutputStream fos = new FileOutputStream(finalName);
            ZipOutputStream zos = new ZipOutputStream(fos);
            zos.setMethod(ZipOutputStream.DEFLATED);
            zos.setLevel(Deflater.DEFAULT_COMPRESSION);
            zos.setMethod(ZipOutputStream.DEFLATED);
            for (String srcFile : srcFiles) {
                FileInputStream fis = new FileInputStream(srcFile);
                ZipEntry entry = new ZipEntry(new File(srcFile).getName());
                zos.putNextEntry(entry);
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                fis.close();
                zos.closeEntry();
            }
            zos.close();
            fos.close();
            return finalName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
