### 组件信息
The following guides illustrate how to use some features concretely:

* [MybatisPlus](https://baomidou.com/guide/crud-interface.html#service-crud-%E6%8E%A5%E5%8F%A3)
* [Hutool](https://www.hutool.cn/docs/#/)
* [knife4j](https://xiaoym.gitee.io/knife4j/documentation/)
* [EasyExcel](https://www.yuque.com/easyexcel/doc/easyexcel)

### 访问地址 dev环境
* [api手册](http://localhost:8081/doc.html)
* [检查服务健康](http://localhost:8081/actuator/health)

### 开发使用指南
- 代码生成器 使用说明
  修改 generator／generator.properties 文件
  执行 com.leon.config.mybatis.CodeGenerator main方法即可

- /src/main/java/com/leon/config/mybatis/MybatisPlusConfig.java
  修改此类中@MapperScan("com.leon.biz.*.mapper")
  
- /src/main/resources/application-dev.properties 修改
  swagger.scan.base.package="com.leon.biz"  