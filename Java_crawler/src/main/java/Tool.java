import net.sf.json.JSONObject;
import net.sf.json.JSONObject;
import us.codecraft.webmagic.Spider;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tool {


    // 爬取工具方法(并翻译标题)
    // -------------------
    public void spider(Content obj, String url){


        Spider.create(obj)
                .addUrl(url)
                //开启4个线程抓取
                .thread(4)
                //启动爬虫
                .run();

        List title = translateArray(obj.show_title());
        System.out.println(title);

        List urls = obj.show_url();
        System.out.println(urls);

        List comments = obj.show_comments();
        System.out.println(comments);

        List curl = obj.show_curl();
        System.out.println(curl);
        
    }


    // md5加密工具，为了方便直接用别人的轮子
    public String getMd5(String input)
    {
        try {

            // 使用哈希MD5调用静态getInstance方法
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() 方法调用来计算消息摘要
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // 将字节数组转换为符号表示
            BigInteger no = new BigInteger(1, messageDigest);

            // 将消息摘要转换为十六进制值
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // 用于指定错误的消息摘要算法
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    // 实现翻译工具（单个字符串）
    public String translate(String text){

        // 结果存储（输入流读取后存储再res中）
        String res = null;
        BufferedReader in = null;


        // 请求头设置
        //请求链接
        String url = "http://api.fanyi.baidu.com/api/trans/vip/translate";
        // 生成param中的sign字段
        String sign = this.getMd5("20210427000804714"+text+"1435660288"+"KDZqSWJch5CFLPFa47YH");
        // 文本编码（保证url不含空格）
        String txt = text.replace(" ", "%20");
        // 请求内容构造
        String param = "q="+txt+"&from=en&to=zh&appid=20210427000804714&salt=1435660288&sign="+sign;



         //设置URL请求
        try {
            String urlNameString = url + '?' + param;
            URL realUrl = new URL(urlNameString);
            // 打开链接，强转换为httpURLConnection类
            URLConnection connection = realUrl.openConnection();

            // 设置通用请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
           // 建立实际链接
           connection.connect();


           // 请求成功获得输入流
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String jsonstr = in.readLine();

            // 解析json字符串
            JSONObject json_res = JSONObject.fromObject(jsonstr);
            String str_arr = json_res.getString("trans_result");
            res = str_arr;

            // 字符串提取出数组
            String [] arr_res = str_arr.split(",");

            // 数组中提取字符串后，使用正则筛选出中文
            // 正则式规则
            Pattern p = Pattern.compile("dst\\\"\\:\\\"(.+)\\\"",Pattern.MULTILINE);
            // 在字符串查找匹配对象
            Matcher m = p.matcher(arr_res[1]);
            // 提取匹配对象的标记组
            if(m.find()) {
                res = m.group(1);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;

    }

    // 实现整个List数组翻译
    public List<String> translateArray(final List arr){

        // 线程池处理数据请求
        ExecutorService executor = Executors.newFixedThreadPool(arr.size());
        for (int i = 0; i < arr.size(); i++) {
            arr.set(i, translate(arr.get(i).toString()));

        }

        return arr;


    }


}
