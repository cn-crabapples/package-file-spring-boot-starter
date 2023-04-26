package cn.crabapples.file.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TODO 配置类
 *
 * @author Mr.He
 * 2023/4/26 09:44
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mshe
 */
@ConfigurationProperties(prefix = "crabapples.package")
public class ConfigProperties {
    private String exportPath; //打包后文件存放路径
    private Long time; //打包后文件有效期
    private boolean isDebug;//是否开启debug日志

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public String getExportPath() {
        return exportPath;
    }

    public void setExportPath(String exportPath) {
        this.exportPath = exportPath;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
