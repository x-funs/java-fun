# Java With Fun (Functions)

Java with functions is a small java tools and utils library.

## Install

```xml
<dependency>
  <groupId>io.github.x-funs</groupId>
  <artifactId>java-fun</artifactId>
  <version>${java-fun.version}</version>
</dependency>
```

## Usage

```java
import io.github.xfuns.java.Fun;

public class JavaFun {
    public static void main(String[] args) {
        // 字符串 MD5
        System.out.println(Fun.md5("java-fun"));
    
        // 字符串转Int
        System.out.println(Fun.toInt("42"));
    
        // 字符串转时间戳
        System.out.println(Fun.strtotime("2015年04月06日 16时03分03秒"));
    }
}
```


