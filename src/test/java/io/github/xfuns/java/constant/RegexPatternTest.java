package io.github.xfuns.java.constant;

import org.junit.Assert;
import org.junit.Test;

public class RegexPatternTest {

    @Test
    public void testEmail() {
        Assert.assertTrue(RegexPattern.EMAIL.matcher("aaa@aa.aa").matches());
    }

    @Test
    public void testMobile() {
        Assert.assertTrue(RegexPattern.MOBILE.matcher("+8618600001111").matches());
    }

    @Test
    public void testUrlHttp() {
        Assert.assertEquals(true, RegexPattern.URL.matcher("http://www.baidu.com").matches());
        Assert.assertEquals(true, RegexPattern.URL.matcher("https://www.hutool.cn/docs/#/core/%E6%96%87%E6%9C%AC%E6%93%8D%E4%BD%9C/CSV%E6%96%87%E4%BB%B6%E5%A4%84%E7%90%86%E5%B7%A5%E5%85%B7-CsvUtil").matches());
        Assert.assertEquals(true, RegexPattern.URL.matcher("https://www.zhihu.com/collection/150270125?page=3").matches());
        Assert.assertEquals(true, RegexPattern.URL.matcher("//www.zhihu.com/collection/150270125?page=3").matches());
        Assert.assertEquals(true, RegexPattern.URL.matcher("http://someuser:password@www.website.com/path/to/file.ext?query=something&parameters=passed#with-anchor").matches());
        Assert.assertEquals(true, RegexPattern.URL.matcher("http://someuser:password@www.website.com/path/to/file.ext?query=something&parameters=passed#with-anchor").matches());
    }
}
