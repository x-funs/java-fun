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
        // whether the string is empty after trim (blank or not)
        System.out.println(Fun.blank("java-fun"));

        // return string md5 hash
        System.out.println(Fun.md5("java-fun"));
    
        // parse string to integer
        System.out.println(Fun.toInt("42"));
    
        // auto parse many datetime string to long timestamp
        System.out.println(Fun.strtotime("2015-04-06 16:03:03"));
    }
}
```


