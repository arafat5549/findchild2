# findchild2

- .运行xunqin.sql安装数据库 (在src/main/resources/jdbc.properties修改数据库配置)

- mvn install

- mvn --debug tomcat7:run 整合了Tomcat7插件(不需要独立安装tomcat)

- 然后在http://localhost:9999/find  可以在pom.xml中配置

  ```xml
            <plugin>
                  <groupId>org.apache.tomcat.maven</groupId>
                  <artifactId>tomcat7-maven-plugin</artifactId>
                  <version>2.1</version>
                  <configuration>
                      <port>9999</port>
                      <path>/find</path>
                      <uriEncoding>UTF-8</uriEncoding>
                      <finalName>find</finalName>
                      <server>tomcat7</server>
                  </configuration>
              </plugin>
  ```

  ​