package com.example.yangdianwen.recycleviewapp_1.HttpUtis;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by yangdianwen on 16-6-19.
 *
 *  这是一个获取网络数据的类，此类中的getData（）方法返回数据
 *
 */
public class HttpOnline  {

    private StringBuffer sb;

    public String getData(){
        URL url=null;
        try {
            //首先获取数据来源地址
             url=new URL("http://gank.io/api/random/data/福利/20");
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            //进行读写操作
            InputStream is=httpURLConnection.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            //使用缓冲区读取，提高效率
            sb = new StringBuffer();
            String line=null;
            while((line=br.readLine())!=null){
                   sb.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


}
