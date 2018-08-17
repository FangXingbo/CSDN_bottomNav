package com.example.fxb.realobs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by fxb on 2017/4/27 17:39.
 * Content:
 */
public class WebService {
    private static String IP = "116.62.208.144:8080";
    //通过get方式获取HTTP服务器数据
    public static String executeHttpGet(String username, String password, String url)  {
        HttpURLConnection conn = null;
        InputStream is = null;
        //用户名 密码
        //URL地址
        String path = url;
        path = path + "userName=" + username + "&password=" + password;
        try {
            URLEncoder.encode(path , "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            conn = (HttpURLConnection) new URL(path).openConnection();
            conn.setConnectTimeout(3000);//设置超时时间
            conn.setReadTimeout(3000);//设置读取超时时间
            conn.setDoInput(true);
            conn.setRequestMethod("GET");//设置获取信息方式
            conn.setRequestProperty("Charset", "UTF-8");//设置接受数据编码格式

            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                return parseInfo(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    //通过get方式获取HTTP服务器数据
    public static String executeHttpGet(String username, String password, String level, String url) {
        HttpURLConnection conn = null;
        InputStream is = null;
        //用户名 密码
        //URL地址
        String path = url;
        try {
            path = path + "userName=" + URLEncoder.encode(username, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8")+ "&level=" + URLEncoder.encode(level, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            conn = (HttpURLConnection) new URL(path).openConnection();
            conn.setConnectTimeout(3000);//设置超时时间
            conn.setReadTimeout(3000);//设置读取超时时间
            conn.setDoInput(true);
            conn.setRequestMethod("GET");//设置获取信息方式
            conn.setRequestProperty("Charset", "UTF-8");//设置接受数据编码格式

            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                return parseInfo(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    //将输入流转为String型
    private static String parseInfo(InputStream inStream) throws IOException {

        byte[] data = read(inStream);
        //转为字符串
        return new String(data, "UTF-8");

    }

    //将输入流转为byte型
    public static byte[] read(InputStream inStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        inStream.close();
        return outputStream.toByteArray();
    }
}
