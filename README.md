# Java With Fun (Functions)

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
        System.out.println(Fun.md5("java-fun"));
    
        System.out.println(Fun.toInt("42"));
    
        System.out.println(Fun.strtotime("2015年04月06日 16时03分03秒"));
    }
}
```


