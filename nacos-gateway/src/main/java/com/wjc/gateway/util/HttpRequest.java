package com.wjc.gateway.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author: wjc
 * @createDate: 2020/9/11 20:43
 * @description:
 */
public class HttpRequest {
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        //PrintWriter out = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "application/json, text/javascript, */*; q=0.01");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("content-type", "application/json");
            connection.setRequestProperty("cookie", "NTKF_T2D_CLIENTID=guest1F0867F2-7CE0-1A2C-7E11-4E4221596781; gr_user_id=bd347bdd-5af5-4a8d-9bbc-bca3d9b962e4; grwng_uid=51d2c422-3244-4f04-adb1-9c791fad0220; citycode=%7B%22provinceName%22%3A%22%E5%8C%97%E4%BA%AC%E5%B8%82%22%2C%22cityName%22%3A%22%E5%8C%97%E4%BA%AC%E5%B8%82%22%2C%22provinceCode%22%3A110000%2C%22cityCode%22%3A110100%7D; UM_distinctid=16c5046eda55cf-0c6e40c2cafac3-c343162-1fa400-16c5046eda6718; pro_utmSource=2019-08-05; nTalk_CACHE_DATA={uid:kf_10297_ISME9754_guest1F0867F2-7CE0-1A,tid:1565019004624346}; CART_SESSION=87127042; hadskfhjayuasdhjk=%4067d0897870-jnjkghyg-990767!hjkh()%2F.kdj; Hm_lvt_877617efccb857b835eeeba62fdfd8c1=1564714921,1565019012; area_ok=true; tt=0; searchOriginKey=ekhukgx4s2o0000; 8ccf9443d38f1ead_gr_session_id=6b0f2786-2048-4b21-87b2-ee0513e65d9c; 8ccf9443d38f1ead_gr_session_id_6b0f2786-2048-4b21-87b2-ee0513e65d9c=true; CNZZDATA1000386340=42653692-1564709314-https%253A%252F%252Fwww.zkh360.com%252F%7C1565017086; Hm_lpvt_877617efccb857b835eeeba62fdfd8c1=1565022155");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段

            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
//				System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String sendGet(String url, String param, Map<String, String> property) {
        String result = "";
        BufferedReader in = null;
        //PrintWriter out = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            if(property!=null){
                Iterator<String> it = property.keySet().iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    connection.setRequestProperty(key, property.get(key));
                }
            }
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段

            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//			conn.setRequestProperty("Charset", "utf-8");

            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("contentType", "UTF-8");
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(60000);
            conn.setReadTimeout(60000);
            // 获取URLConnection对象对应的输出流
//			out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
//			// 发送请求参数
//			out.print(param);
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String sendPost(String url, String param, Map<String, String> property) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
//			conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1");
//			conn.setRequestProperty("Charset", "utf-8");

//			conn.setRequestProperty("Accept-Charset", "UTF-8");
//		    conn.setRequestProperty("contentType", "UTF-8");
//		    conn.setRequestProperty("Content-type", "application/json;charset=UTF-8");
            if(property!=null){
                Iterator<String> it = property.keySet().iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    conn.addRequestProperty(key, property.get(key));
                }
            }
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(60000);
            conn.setReadTimeout(60000);
            // 获取URLConnection对象对应的输出流
//			out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
//			// 发送请求参数
//			out.print(param);
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String sendFilePost(String url, String param, File file) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            String BOUNDARY = "----------" + System.currentTimeMillis();
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data;boundary="+BOUNDARY);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    public static void sendPostJson2(String url, String json) throws ClientProtocolException, IOException {
        String result = null;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8");
        httpPost.addHeader("Authorization", "Basic "+ Base64.encodeBase64String("3bb10ed84fad07adb839e315:fa4400f0145bda41cf1ea480".getBytes("UTF-8")));

        StringEntity se = new StringEntity(json, "UTF-8");
        se.setContentType("application/json;charset=UTF-8");
        se.setContentEncoding(new BasicHeader("Authorization", "Basic "+Base64.encodeBase64String("3bb10ed84fad07adb839e315:fa4400f0145bda41cf1ea480".getBytes("UTF-8"))));
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "UTF-8"));
        httpPost.setEntity(se);
        // httpClient.execute(httpPost);
        HttpResponse response = httpClient.execute(httpPost);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(resEntity.getContent(), "UTF-8")); // 实例化输入流，并获取网页代码
                String s; // 依次循环，至到读的值为空
                StringBuilder sb = new StringBuilder();
                while ((s = reader.readLine()) != null) {
                    sb.append(s);
                }
                reader.close();
                String str = sb.toString();
                System.out.println(str);
            }
        }
        httpPost.abort();
//		return result;
    }

}
