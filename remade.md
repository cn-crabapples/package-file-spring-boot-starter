> # mail-spring-boot-starter(JDK 1.8)

### 快速配置邮件发送模块

- 引入maven坐标
  ```xml
  <dependency>
    <groupId>cn.crabapples</groupId>
    <artifactId>package-file-spring-boot-starter</artifactId>
    <version>1.0.0</version>
  </dependency>
  ```

/**

* 文件打包工具
*
* @param srcFiles 需要打包的文件
* @param outputPath 打包后文件输出路径
* @param isTemp 是否为临时文件，临时文件将在一段时间后自动删除
* @param time 文件保留时间，单位秒
  */
  ``` java
    PackageFileUtils fileUtils = new PackageFileUtils();
    String file = fileUtils.packageFile(files, outputPath, true, 5L);


  ```
