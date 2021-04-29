import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Arrays;
import java.util.List;
import java.util.ListResourceBundle;

//实现配置爬取规则的父类
// ---------------------------------------------------------------------

// 实现PageProcessor接口
// PageProcessor接口中返回了Site类，然后我们可以调用Site类中的方法
public class Content implements PageProcessor {

    // 抓取网站的相关配置
    // 创建Site对象，实现其中方法：抓取间隔、重试次数等
    private Site site = Site.me()
            .setCharset("UTF-8")
            .setRetryTimes(3)
            .setSleepTime(100);

    // 爬取信息的主要对象
    private List title;
    private List url;
    private List comments;
    private List curl;

    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    // 重写接口中的process方法，形参实例化一个Page对象,子类的返回值要与父类的返回值相同
    public void process(Page page){

    }

    // 重写接口中的getSite方法，重写完实现PageProcessor接口的Content才不是抽象类
    public Site getSite(){
        return site;
    }

    // 供子类放数据到私有属性的方法
    public void set_title(List title){
        this.title = title;
    }
    public void set_url(List url){
        this.url = url;
    }
    public void set_comments(List comments){
        this.comments = comments;
    }
    public void set_curl(List curl){
        this.curl = curl;
    }

    // 返回爬取数据
    public List show_title(){
        return this.title;
    }
    public List show_url(){
        return this.url;
    }
    public List show_comments(){
        return this.comments;
    }
    public List show_curl(){
        return this.curl;
    }


}