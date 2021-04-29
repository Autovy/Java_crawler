import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;

import java.util.List;

// 程序运行类，主要在这里实现程序流程
public class App {

    public static void main(String[] args) {

        // 实例化工具类
        Tool tool = new Tool();

        // 抓取lobste数据信息
        // --------------------------------------
        tool.spider(new Lobsters() ,"https://lobste.rs/");

    }




}
