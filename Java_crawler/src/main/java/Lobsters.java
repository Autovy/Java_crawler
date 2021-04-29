import us.codecraft.webmagic.Page;
import java.util.Arrays;
import java.util.List;

// 为爬取https://lobste.rs/ 而实现的子类
// ---------------------------------------------
public class Lobsters extends Content {


    // 子类访问父类私有属性，需要放和拿两套方法
    public void process(Page page){

        // 爬取title放入父类私有属性中
        List title = page.getHtml().css("li > div > div.details > span.link.h-cite.u-repost-of > a").regex(">(.+)</a>",1).all();
        set_title(title);

        // 爬取链接放入
        List url = page.getHtml().css("li > div > div.details > span.link.h-cite.u-repost-of > a").links().all();
        set_url(url);

        // 爬取评论数放入
        List comment = page.getHtml().css("span.comments_label > a").regex(">.(\\d)",1).all();
        set_comments(comment);

        // 爬取评论链接放入
        List curl = page.getHtml().css("span.comments_label > a").regex("\\\"(.+)\\\"",1).replace("/s","https://lobste.rs/s").all();
        set_curl(curl);

    }




}
