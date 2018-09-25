# Pom.xml

参考：https://www.cnblogs.com/wkrbky/p/6353285.html

### 什么是POM？

POM是项目对象模型(Project Object Model)的简称,它是Maven项目中的文件，使用XML表示，名称叫做pom.xml。作用类似ant的build.xml文件，功能更强大。该文件用于管理：源代码、配置文件、开发者的信息和角色、问题追踪系统、组织信息、项目授权、项目的url、项目的依赖关系等等。事实上，在Maven世界中，project可以什么都没有，甚至没有代码，但是必须包含pom.xml文件。

下面是一个POM项目中的pom.xml文件中包含的元素。注意，其中的modelVersion是4.0.0，这是当前仅有的可以被Maven2&3同时支持的POM版本，它是必须的。

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
            http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <!-- 基本设置 The Basics -->
    <groupId>...</groupId>
    <artifactId>...</artifactId>
    <version>...</version>
    <packaging>...</packaging>
    <dependencies>...</dependencies>
    <parent>...</parent>
    <dependencyManagement>...</dependencyManagement>
    <modules>...</modules>
    <properties>...</properties>
    
    <!-- 构建过程的设置 Build Settings -->
    <build>...</build>
    <reporting>...</reporting>
    
    <!-- 项目信息设置 More Project Information -->
    <name>...</name>
    <description>...</description>
    <url>...</url>
    <inceptionYear>...</inceptionYear>
    <licenses>...</licenses>
    <organization>...</organization>
    <developers>...</developers>
    <contributors>...</contributors>
    
    <!-- 环境设置 Environment Settings -->
    <issueManagement>...</issueManagement>
    <ciManagement>...</ciManagement>
    <mailingLists>...</mailingLists>
    <scm>...</scm>
    <prerequisites>...</prerequisites>
    <repositories>...</repositories>
    <pluginRepositories>...</pluginRepositories>
    <distributionManagement>...</distributionManagement>
    <profiles>...</profiles>
    
</project>

```

### POM详解

#### 1 基本配置

##### 1.1 Maven的协作相关属性

一个最简单的pom.xml的定义必须包含modelVersion、groupId、artifactId和version这四个元素，当然这其中的元素也是可以从它的父项目中继承的。在Maven中，使用groupId、artifactId和version组成groupdId:artifactId:version的形式来唯一确定一个项目：

```xml

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"  
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0  
                      http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <!-- 
        含义：组织标识，定义了项目属于哪个组，风向标，坐标，或者说若把本项目打包
        用途：此名称则是本地仓库中的路径，列如：xxx.user.dao，在M2_REPO目录下，将是: xxx/user/dao目录
        命名规范:项目名称，模块，子模块
    -->
    <groupId>xxx.user.dao</groupId>
    <!-- 
        含义：项目名称也可以说你所模块名称，定义当面Maven项目在组中唯一的ID
        用途：例如：user-dao，在M2_REPO目录下，将是：xxx/user/dao/user-dao目录
        命名规范:唯一就好
    -->
    <artifactId>user-dao</artifactId>
    <!-- 
        含义：项目当前的版本号
        用途：例如：0.0.1-SNAPSHOT，在M2_REPO目录下，将是：xxx/user/dao/user-dao/0.0.1-SNAPSHOT目录
    -->
    <version>0.0.1-SNAPSHOT</version>
    <!-- 打包的格式，可以为：pom , jar , maven-plugin , ejb , war , ear , rar , par -->
    <packaging>war</packaging>
    <!-- 元素声明了一个对用户更为友好的项目名称 -->
    <name>maven</name>
</project>

```

##### 1.2 POM之间的关系

我们知道Maven在建立项目的时候是基于Maven项目下的pom.xml进行的，我们项目依赖的信息和一些基本信息都是在这个文件里面定义的。那如果当我们有多个项目要进行，这多个项目有些配置内容是相同的，有些是要彼此关联的，那如果按照传统的做法的话我们就需要在多个项目中都定义这些重复的内容。这无疑是非常耗费时间和不易维护的。好在Maven给我们提供了一个pom的继承和聚合的功能。

对于使用java的人而言，继承这个词大家应该都不陌生。要继承pom就需要有一个父pom，在Maven中定义了超级pom.xml，任何没有申明自己父pom.xml的pom.xml都将默认继承自这个超级pom.xml。

>位置：
>
>在Maven 2.xxx版本中，比如maven-2.0.9-uber.jar，打开此Jar文件后，在包包org.apache.maven.project下会有pom-4.0.0.xml的文件。
>
>3.xxx版本之后在： 
Maven安装目录下的：/lib/maven-model-builder-version.jar中 \org\apache\maven\mode目录中的pom-4.0.0.xml。


对于一个pom.xml来说有几个元素是必须定义的，一个是project根元素，然后就是它里面的modelVersion、groupId、artifactId和version。由上面的超级pom.xml的内容我们可以看到pom.xml中没有groupId、artifactId和version的定义，所以我们在建立自己的pom.xml的时候就需要定义这三个元素。和java里面的继承类似，子pom.xml会完全继承父pom.xml中所有的元素，而且对于相同的元素，一般子pom.xml中的会覆盖父pom.xml中的元素，但是有几个特殊的元素它们会进行合并而不是覆盖。这些特殊的元素是：

* dependencies
* developers
* contributors
* plugin列表，包括plugin下面的reports
* resources

##### 1.3 依赖关系：依赖关系列表（dependency list）是POM的重要部分

项目之间的依赖是通过pom.xml文件里面的dependencies元素下面的dependency元素进行的。一个dependency元素定义一个依赖关系。在dependency元素中我们主要通过依赖项目的groupId、artifactId和version来定义所依赖的项目。

先来看一个简单的项目依赖的示例吧，假设我现在有一个项目projectA，然后它里面有对junit的依赖，那么它的pom.xml就类似以下这个样子：

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">  
  <modelVersion>4.0.0</modelVersion>  
  <groupId>com.tiantian.mavenTest</groupId>  
  <artifactId>projectB</artifactId>  
  <version>1.0-SNAPSHOT</version>  
  <packaging>jar</packaging>  
   
  <dependencies>  
    <dependency>  
      <groupId>junit</groupId>  
      <artifactId>junit</artifactId>  
      <version>3.8.1</version>  
      <scope>test</scope>  
      <optional>true</optional>  
    </dependency>  
  </dependencies>  
</project>  

```

groupId, artifactId, version:描述了依赖的项目唯一标志。

type：对应于依赖项目的packaging类型，默认是jar。

scope：用于限制相应的依赖范围、传播范围。scope的主要取值范围如下：

    test:在测试范围有效，它在执行命令test的时候才执行，并且它不会传播给其他模块进行引入，比如 junit,dbunit 等测试框架。
    
    compile(default 默认):这是它的默认值，这种类型很容易让人产生误解，以为只有在编译的时候才是需要的，其实这种类型表示所有的情况都是有用的，包括编译和运行时。而且这种类型的依赖性是可以传递的。
    
    runtime:在程序运行的时候依赖，在编译的时候不依赖。
    
    provided:这个跟compile很类似，但是它表示你期望这个依赖项目在运行时由JDK或者容器来提供。这种类型表示该依赖只有在测试和编译的情况下才有效，在运行时将由JDK或者容器提供。这种类型的依赖性是不可传递的。
    
    system：这种类型跟provided类似，唯一不同的就是这种类型的依赖我们要自己提供jar包，这需要与另一个元素systemPath来结合使用。systemPath将指向我们系统上的jar包的路径，而且必须是给定的绝对路径。


#### 2 属性

在pom.xml文件中我们可以使用${propertyName}的形式引用属性。是值的占位符，类似EL，类似ant的属性，比如${X}，可用于pom文件任何赋值的位置。有以下分类：

    env.propertyName：这种形式表示引用的是环境变量，比如我们需要引用当前系统的环境变量PATH的时候，就可以使用${env.PATH}。
    
    project.propertyName：这种形式表示引用的是当前这个pom.xml中project根元素下面的子元素的值。比如我们需要引用当前project下面的version的时候，就可以使用${project.version}。
    
    settings.propertyName：这种形式引用的是Maven本地配置文件settings.xml或本地Maven安装目录下的settings.xml文件根元素settings下的元素。比如我们需要引用settings下的本地仓库localRepository元素的值时，我们可以用${settings.localRepository}
    
    Java System Properties：java的系统属性，所有在java中使用java.lang.System.getProperties()能够获取到的属性都可以在pom.xml中引用，比如${java.home}。
    
    自定义：pom.xml中properties元素下面的子元素作为属性。假如在pom.xml中有如下一段代码<properties><hello.world>helloWorld</hello.world></properties>，那么我们就可以使用${hello.world}引用到对应的helloWorld。


