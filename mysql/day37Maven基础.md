## 今日内容

+ maven基础
	+ 依赖管理
	+ 项目构建
	
+ idea能够通过maven方式创建web工程【要求】

+ 搭建黑马旅游

+ 实现用户基本注册功能


## 第一章 Maven简介

### 1 Maven是什么？

Maven 翻译为"专家"、"内行"，是 Apache 下的一个纯 Java 开发的开源项目。

**一个对 Maven 比较正式的定义是这么说的：**

Maven 是一个项目管理工具，它包含了一个项目对象模型，一组标准集合，一个项目生命周期，一个依赖管理系统，和用来运行定义在生命周期阶段中插件目标的工具。



### 2 Maven能解决什么问题

可以用更通俗的方式来说明。我们知道，项目开发不仅仅是写写代码而已，期间会伴随着各种必不可少的事情要做，下面列举几个感受一下： 

1. 我们需要引用各种jar包，尤其是比较大的工程，引用的jar包往往有几十个乃至上百个，每用到一种jar包，都需要手动引入工程目录，而且经常遇到各种让人抓狂的jar包冲突，版本冲突。
2. 我们写好的java文件，需要通过javac编译成class文件才能交给JVM运行。这项工作可以由各种集成开发工具帮我们完成 Eclipse、IDEA 等都可以将代码即时编译。 
3. 世界上没有不存在 bug 的代码，因此写完了代码，我们还要写一些单元测试，然后一个个的运行来检验代码质量。 
4. 再优雅的代码也是要出来卖的。我们后面还需要把代码与各种配置文件、资源整合到一起，进行打包。如果是 web 项目，还需要将之发布到服务器 。

试想，如果现在有一种工具，可以把你从上面的繁琐工作中解放出来，能帮你构建工程，管理 jar包，编译代码，还能帮你自动运行单元测试，打包，生成报表，甚至能帮你部署项目，生成 Web 站点，你会心动吗？ Maven 就可以解决上面所提到的这些问题。 



### 3 Maven的两个核心功能【重点】

#### 3.1 依赖管理

```markdown
* 传统的 WEB 工程中，我们必须将工程所依赖的 jar 包复制到工程中，导致了工程的变得很大。 

* maven的 WEB 工程中不直接将jar包导入到工程中，而是通过在 pom.xml 文件中添加所需jar包的坐标。项目运行时，通过读取坐标到一个专门用于“存放jar包仓库"（Maven仓库）找到相应的jar包。
```

#### 3.2 项目构建

 ```markdown
* 我们开发的项目，往往都要经历编译、测试、打包、安装，部署等一系列过程。 

* 什么是构建项目
		指的是项目从编译、测试、打包、安装，部署整个过程都交给maven进行管理，这个过程称为构建。 	
		
* 一键构建
		指的是整个构建过程，使用 maven 一个命令可以轻松完成整个工作。 
 ```

## 第二章 Maven安装和使用

### 1 Maven下载和安装

**下载**

> Maven官网下载地址 ：http://maven.apache.org/download.cgi



> 本次使用：3.5.2版本
>



**安装**

> Maven使用java语言开发的，解压即可运行
>
> 注意：Maven依赖 JAVA_HOME

**目录**

- bin：存放了 maven 的命令，比如我们前面用到的 mvn tomcat7:run
- boot：存放了一些 maven 本身的引导程序，如类加载器等
- conf：存放了 maven 的一些配置文件，如 setting.xml 文件
- lib：存放了 maven 本身运行所需的一些 jar 包

 







**环境变量**

> 新建:
>
> MAVEN_HOME: maven安装目录
>  	  添加:
>
> path: %MAVEN_HOME%\bin
> 
>
> 打开CMD测试
>
> $ mvn --version
> Apache Maven 3.5.2 (138edd61fd100ec658bfa2d307c43b76940a5d7d; 2017-10-18T15:58:13+08:00)
> Maven home: D:\develop\maven\apache-maven-3.5.2
> Java version: 1.8.0_202, vendor: Oracle Corporation
> Java home: D:\develop\Java\jdk1.8.0_202\jre
> Default locale: zh_CN, platform encoding: GBK
> OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"

### 2 Maven仓库

#### 2.1 Maven仓库的分类

```markdown
1. 本地仓库	
		项目通过jar坐标，先从本地仓库找对应jar包，如果找不到会从远程仓库（互联网）去下载 jar 包，保存在本地仓库（在程序员的电脑上），第二次就不需要从远程仓库去下载了。
		
2. 远程仓库
	2.1 中央仓库
		由专业团队（maven团队）统一维护。
		中央仓库的地址：https://repo1.maven.org/maven2/
	2.2 私服
		架设在公司局域网内，提供给内部的人员使用。
	2.3 第三方仓库
		中央仓库只有一个国内使用非常慢，我们可以更换为：阿里云镜像
```

#### 2.2 Maven本地仓库的配置

> 1. 将今天资料中的 repository.zip 解压到非中文及特殊符号目录下

> 2. 修改 maven的安装目录/conf/settings.xml 文件的标签
>
>    <localRepository>本地仓库地址</localRepository>

#### 2.3 Maven仓库国内镜像配置

> 修改 maven 根目录下的 conf 文件夹中的 setting.xml 文件，在 mirrors 节点上，添加内容如下：
>

```xml
<mirror>
    <id>alimaven</id>
    <name>aliyun maven</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
    <mirrorOf>central</mirrorOf>        
</mirror>
```

### 3 Maven坐标和依赖

> 了解以下maven的web工程如何引入本地仓库的jar包

> pom.xml是maven工程的核心配置文件

> 依赖管理



```xml
<!--依赖管理-->
<dependencies>
  <!--mysql驱动-->
  <dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.47</version>
    <scope>runtime</scope>
  </dependency>
</dependencies>
```



### 4 Maven工程的结构【重点】



```markdown
* java项目
	java工程（项目名）
		--| src目录（存放项目的源代码和配置文件）
			--| main目录（存放运行环境代码）
				--| java目录（存放java源代码）
				--| resources目录（存放配置文件）
			--| test目录（存放测试环境代码）
				--| java目录（存放java源代码）
				--| resources目录（存放配置文件）
		--| target目录（存放编译后的代码）
		--| pom.xml（maven的核心配置文件，实现依赖管理）

* web项目
	web工程（项目名）
		--| src目录（存放项目的源代码和配置文件）
			--| main目录（存放运行环境代码）
				--| java目录（存放java源代码）
				--| resources目录（存放配置文件）
				--| webapp目录（存放页面资源）
					--| WEB-INF目录
					--| index.jsp
			--| test目录（存放测试环境代码）
				--| java目录（存放java源代码）
				--| resources目录（存放配置文件）
		--| target目录（存放编译后的代码）
		--| pom.xml（maven的核心配置文件，实现依赖管理）
```



## 第三章 Maven生命周期和插件

### 1 Maven常用命令和插件

我们可以在 cmd 中通过一系列的 maven 命令，来对我们的工程进行编译、测试、打包、安装、部署。 

##### 1）clean

​	maven工程的清理命令，执行clean会删除target目录及内容。

##### 2）compile

​	maven工程的编译命令，作用是将 src/main/java 下的文件编译为class文件输出到target目录下。

##### 3）test

​	maven工程的测试命令，作用是执行 src/test/java 下单元测试类，并编译为class文件。



##### 4）package

​	maven工程的打包命令，对于java工程执行package打成jar包，对于web工程打成war包。 

##### 5）install

​	maven工程的安装命令，执行install将mave工程打成jar包或war包，并发布到本地仓库。 

##### 6）deploy【maven高级课程讲解】

​	maven工程部署命令，将jar或war包部署到私服中。



### 2 生命周期

maven 对项目构建过程分为**“三套相互独立的”**生命周期，这三套生命周期分别是：

```markdown
1. Clean Lifecycle(清理生命周期)
		在进行真正的构建之前进行一些清理工作。
		命令:clean
		
2. Default Lifecycle(默认生命周期)
		构建的核心部分，编译，测试，打包，部署等等。
		命令: compile  test  package  install  deploy
		
3. Site Lifecycle(站点生命周期)
		生成项目报告，站点，发布站点。 
		命令: site
		
* 在同一个生命周期中的命令,执行后面的命令,前面的命令自动执行
```



## 第四章 IDEA创建Maven工程【掌握】

### 1 IDEA配置本地Maven

> 进入configure-->settings-->build-->build tools-->Maven中，设置maven工具和本地仓库  

​	

我们在IDEA创建maven工程默认从互联网在下载一个骨架（但是我们电脑没有联网，就会卡顿一段时间，再使用本地骨架），我们可以直接指定IDEA查找本地骨架，效率提升

```markdown
-DarchetypeCatalog=internal -Dfile.encoding=GB2312
```

> 进入configure-->settings-->build-->build tools-->maven-->Runner中设置  

### 2 IDEA创建工程

> 注意：刚才我们的全局配置，只会影响后面创建的新工程（工作空间）和新模块，之前的不会生效



#### 创建工作空间



#### ① maven的java工程（jar）

> 选择maven的模块创建

> 指定当前工程坐标（GAV）

> 指定maven工程的存放路径

> 完善maven工程结构

> 命令和依赖管理的基本使用

#### ② maven的web工程（war）

> 创建步骤跟刚才java工程完全一致

> 我们需要安装一个插件，将java工程转为web工程(jblJavatoWeb)

> 在pom.xml   ,   统一工程的编码和jdk环境

```xml
    <build>
        <plugins>
            <!-- 设置编译版本为1.8 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
        </plugins>
    </build>
```



### 3 发布web工程

#### ① 传统外置tomcat【掌握】

> 跟之前发布咱们传统web工程的方式完全一致。。

 



#### ② 内置maven（tomcat）插件【了解】

> 在 pom.xml 引入 tomcat7的插件

```xml
    <build>
        <plugins>
            <!-- 设置编译版本为1.8 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.tomcat.maven</groupId>
                <artifactId>tomcat7-maven-plugin</artifactId>
                <version>2.2</version>
                <configuration>
                    <port>8080</port> <!--端口号-->
                    <path>/</path>    <!--项目网络地址（虚拟路径）-->
                    <uriEncoding>utf-8</uriEncoding>  <!--指定get方式的请求编码-->
                </configuration>
            </plugin>
        </plugins>
    </build>
```



> 执行内置插件，运行tomcat



### 4 依赖管理

| 依赖范围         | 编译期有效 | 测试期有效 | 运行期有效 | 例子                    |
| ---------------- | ---------- | ---------- | ---------- | ----------------------- |
| compile（默认）  | Y          | Y          | Y          | mybatis                 |
| test             | -          | Y          | -          | junit                   |
| provided（重点） | Y          | Y          | -          | servlet-api             |
| runtime          | -          | Y          | Y          | JDBC驱动                |
| system（了解）   | Y          | Y          | -          | 本地，maven仓库之外的库 |



```markdown
* compile
		默认依赖范围，作用域在编译、测试、运行时都有效。
		
* test
		作用域在测试时有效。编译和运行时不需要，比如：Junit。
		
* provided
		作用域在编译、测试时有效。运行时不需要，比如： servlet api 被 tomcat 容器提供。 
		
* runtime
		作用域在测试、运行时有效。编译时不需要，比如：jdbc的驱动包。 
		
* system
		system范围依赖与provided类似，jar包在本地磁盘而不是maven仓库
```



```xml
    <!--依赖管理-->
    <dependencies>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.1.0</version>
            <!--作用范围-->
            <scope>provided</scope>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <!--作用范围-->
            <scope>test</scope>
        </dependency>


        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.47</version>
            <!--作用范围-->
            <scope>runtime</scope>
        </dependency>
    </dependencies>
```

