import us.codecraft.webmagic.Page;
import java.util.Arrays;
import java.util.List;

// 为爬取https://lobste.rs/ 而实现的子类
// ---------------------------------------------
public class Lobsters extends Content {


    // 子类访问父类私有属性，需要放和拿两套方法
    public void process(Page page){

        // 将title放入父类私有属性中
        List title = page.getHtml().css("li > div > div.details > span.link.h-cite.u-repost-of > a").all();
        set_title(title);

    }




}
