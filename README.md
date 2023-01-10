# Java With Fun (Functions)

Java With Fun (Functions) 是一个短小能干的 Java 工具库。

## 使用

```xml
<dependency>
  <groupId>io.github.x-funs</groupId>
  <artifactId>java-fun</artifactId>
  <version>${java-fun.version}</version>
</dependency>
```

## 示例

```java
import io.github.xfuns.java.Fun;

public class JavaFun {
    public static void main(String[] args) {
        // 判断是否为空
        System.out.println(Fun.empty("  "));
        
        // 判断是否为空白
        System.out.println(Fun.blank("  "));

        // 返回 MD5 字符串
        System.out.println(Fun.md5("java-fun"));
    
        // parse string to integer
        System.out.println(Fun.toInt("42"));
    
        // 自动解析时间格式为秒时间戳
        System.out.println(Fun.strtotime("2015-04-06 16:03:03"));
    }
}
```


