import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import us.codecraft.webmagic.selector.Json;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ToolTest {

    // 测试加密工具方法
    @Test
    void getMd5() {

        System.out.println(new Tool().getMd5("20210427000804714apple1435660288KDZqSWJch5CFLPFa47YH"));
    }

    // 爬取工具类测试
    @Test
    void spider() {
        new Tool().spider(new Lobsters() ,"https://lobste.rs/");

    }

    // 翻译请求测试
    @Test
    void translate() {

        String res= new Tool().translate("Ema: Haskell static site generator with hot reload");
        System.out.println(res);

    }

    // List数组翻译
    @Test
    void translateArray() {
    }
}