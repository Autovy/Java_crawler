import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;

import java.util.List;

// 程序运行类，主要在这里实现程序流程
public class App {

    public static void main(String[] args) {

// 抓取lobste数据信息
// --------------------------------------
        Lobsters lobsters = new Lobsters();

        Spider.create(lobsters)
                .addUrl("https://lobste.rs/")
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();

        List title = lobsters.show_title();
        System.out.println(title.get(1));

    }



}
