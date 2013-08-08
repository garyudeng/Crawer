package com.bmk.crawler;

import java.io.BufferedReader;

import java.io.ByteArrayOutputStream;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.util.ArrayList;

import java.util.Collection;

import java.util.Date;

import java.util.zip.GZIPInputStream;

import javax.net.ssl.SSLHandshakeException;

import org.apache.commons.lang.StringUtils;

import org.apache.http.Header;

import org.apache.http.HttpEntity;

import org.apache.http.HttpEntityEnclosingRequest;

import org.apache.http.HttpHost;

import org.apache.http.HttpRequest;

import org.apache.http.HttpResponse;

import org.apache.http.HttpVersion;

import org.apache.http.NoHttpResponseException;

import org.apache.http.ParseException;

import org.apache.http.StatusLine;

import org.apache.http.client.ClientProtocolException;

import org.apache.http.client.HttpRequestRetryHandler;

import org.apache.http.client.entity.GzipDecompressingEntity;

import org.apache.http.client.methods.HttpGet;

import org.apache.http.client.params.ClientPNames;

import org.apache.http.client.params.CookiePolicy;

import org.apache.http.conn.params.ConnManagerParams;

import org.apache.http.conn.params.ConnRoutePNames;

import org.apache.http.conn.routing.HttpRoute;

import org.apache.http.conn.scheme.PlainSocketFactory;

import org.apache.http.conn.scheme.Scheme;

import org.apache.http.conn.scheme.SchemeRegistry;

import org.apache.http.conn.ssl.SSLSocketFactory;

import org.apache.http.entity.ContentType;

import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.impl.conn.PoolingClientConnectionManager;

import org.apache.http.message.BasicHeader;

import org.apache.http.params.BasicHttpParams;

import org.apache.http.params.CoreConnectionPNames;

import org.apache.http.params.CoreProtocolPNames;

import org.apache.http.params.HttpParams;

import org.apache.http.protocol.ExecutionContext;

import org.apache.http.protocol.HttpContext;

import org.apache.http.util.EntityUtils;

/**
 * @Intro descrption here
 * @author Lee
 * @Date 2013-8-8
 */
public class HttpConnnectionManager {

	/**
	 * 
	 * 连接池里的最大连接数
	 */

	public static final int MAX_TOTAL_CONNECTIONS = 100;

	/**
	 * 
	 * 每个路由的默认最大连接数
	 */

	public static final int MAX_ROUTE_CONNECTIONS = 50;

	/**
	 * 
	 * 连接超时时间
	 */

	public static final int CONNECT_TIMEOUT = 50000;

	/**
	 * 
	 * 套接字超时时间
	 */

	public static final int SOCKET_TIMEOUT = 50000;

	/**
	 * 
	 * 连接池中 连接请求执行被阻塞的超时时间
	 */

	public static final long CONN_MANAGER_TIMEOUT = 60000;

	/**
	 * 
	 * http连接相关参数
	 */

	private static HttpParams parentParams;

	/**
	 * 
	 * http线程池管理器
	 */

	private static PoolingClientConnectionManager cm;

	/**
	 * 
	 * http客户端
	 */

	private static DefaultHttpClient httpClient;

	/**
	 * 
	 * 默认目标主机
	 */

	private static final HttpHost DEFAULT_TARGETHOST = new HttpHost(
			"http://category.dangdang.com/all/?category_path=01.00.00.00.00.00",
			80);

	/**
	 * 
	 * 初始化http连接池，设置参数、http头等等信息
	 */

	static {

		SchemeRegistry schemeRegistry = new SchemeRegistry();

		schemeRegistry.register(

		new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));

		schemeRegistry.register(

		new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));

		cm = new PoolingClientConnectionManager(schemeRegistry);

		cm.setMaxTotal(MAX_TOTAL_CONNECTIONS);

		cm.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);

		cm.setMaxPerRoute(new HttpRoute(DEFAULT_TARGETHOST), 20); // 设置对目标主机的最大连接数

		parentParams = new BasicHttpParams();

		parentParams.setParameter(CoreProtocolPNames.PROTOCOL_VERSION,
				HttpVersion.HTTP_1_1);

		parentParams
				.setParameter(ClientPNames.DEFAULT_HOST, DEFAULT_TARGETHOST); // 设置默认targetHost

		parentParams.setParameter(ClientPNames.COOKIE_POLICY,
				CookiePolicy.BROWSER_COMPATIBILITY);

		parentParams.setParameter(ClientPNames.CONN_MANAGER_TIMEOUT,
				CONN_MANAGER_TIMEOUT);

		parentParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
				CONNECT_TIMEOUT);

		parentParams.setParameter(CoreConnectionPNames.SO_TIMEOUT,
				SOCKET_TIMEOUT);

		parentParams.setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);

		parentParams.setParameter(ClientPNames.HANDLE_REDIRECTS, true);

		// 设置头信息,模拟浏览器

		Collection collection = new ArrayList();

		collection
				.add(new BasicHeader("User-Agent",
						"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0)"));

		collection
				.add(new BasicHeader("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"));

		collection.add(new BasicHeader("Accept-Language",
				"zh-cn,zh,en-US,en;q=0.5"));

		collection.add(new BasicHeader("Accept-Charset",
				"ISO-8859-1,utf-8,gbk,gb2312;q=0.7,*;q=0.7"));

		collection.add(new BasicHeader("Accept-Encoding", "gzip, deflate"));

		parentParams.setParameter(ClientPNames.DEFAULT_HEADERS, collection);

		// 请求重试处理

		HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {

			public boolean retryRequest(IOException exception,
					int executionCount, HttpContext context) {

				if (executionCount >= 5) {

					// 如果超过最大重试次数，那么就不要继续了

					return false;

				}

				if (exception instanceof NoHttpResponseException) {

					// 如果服务器丢掉了连接，那么就重试

					return true;

				}

				if (exception instanceof SSLHandshakeException) {

					// 不要重试SSL握手异常

					return false;

				}

				HttpRequest request = (HttpRequest) context
						.getAttribute(ExecutionContext.HTTP_REQUEST);

				boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);

				if (idempotent) {

					// 如果请求被认为是幂等的，那么就重试

					return true;

				}

				return false;

			}

		};

		httpClient = new DefaultHttpClient(cm, parentParams);

		httpClient.setHttpRequestRetryHandler(httpRequestRetryHandler);

	}

	/**
	 * 
	 * 抓取页面代码
	 * 
	 * @param url
	 *            目标页面的url
	 * 
	 * @return 页面代码
	 */

	public static String getHtml(String url) {

		HttpHost proxyHost = new HttpHost("211.142.236.137", 8080);// 代理

		String html = getHtml(url, proxyHost);

		int count = 0;

		while (StringUtils.isEmpty(html)) {

			proxyHost = new HttpHost("211.142.236.137", 80);// 更换代理

			html = getHtml(url, proxyHost);

			count++;

			if (count > 3) {

				System.out.println("抓取失败");

				break;

			}

		}

		//System.out.println(html.length());

		return html;

	}

	/**
	 * 
	 * 抓取url所指的页面代码
	 * 
	 * @param url
	 *            目标页面的url
	 * 
	 * @return 页面代码
	 */

	private static String getHtml(String url, HttpHost proxyHost) {

		String html = "";

		HttpGet httpGet = new HttpGet(url);

		httpGet.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
				proxyHost);// 设置代理

		HttpResponse httpResponse;

		HttpEntity httpEntity;

		try {

			httpResponse = httpClient.execute(httpGet);

			StatusLine statusLine = httpResponse.getStatusLine();

			int statusCode = statusLine.getStatusCode();

//			System.out.println(statusCode);

			if (200 != statusCode) {

				return html;

			}

			httpEntity = httpResponse.getEntity();

			if (httpEntity != null) {

				html = readHtmlContentFromEntity(httpEntity);

			}

		} catch (ClientProtocolException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			if (httpGet != null) {

				httpGet.releaseConnection();

			}

		}

		return html;

	}

	/**
	 * 
	 * 从response返回的实体中读取页面代码
	 * 
	 * @param httpEntity
	 *            Http实体
	 * 
	 * @return 页面代码
	 * 
	 * @throws ParseException
	 * 
	 * @throws IOException
	 */

	private static String readHtmlContentFromEntity(HttpEntity httpEntity)
			throws ParseException, IOException {

		String html = "";

		Header header = httpEntity.getContentEncoding();

		if (httpEntity.getContentLength() < 2147483647L) { // EntityUtils无法处理ContentLength超过2147483647L的Entity

			if (header != null && "gzip".equals(header.getValue())) {

				html = EntityUtils.toString(new GzipDecompressingEntity(
						httpEntity));

			} else {

				html = EntityUtils.toString(httpEntity);

			}

		} else {

			InputStream in = httpEntity.getContent();

			if (header != null && "gzip".equals(header.getValue())) {

				html = unZip(in, ContentType.getOrDefault(httpEntity)
						.getCharset().toString());

			} else {

				html = readInStreamToString(in,
						ContentType.getOrDefault(httpEntity).getCharset()
								.toString());

			}

			if (in != null) {

				in.close();

			}

		}

		return html;

	}

	/**
	 * 
	 * 测试代理是否可用（其实和getHtml(String url, HttpHost proxyHost)的代码差不多，为了从功能上区别，暂时这样）
	 * 
	 * @param httpHost
	 *            封装了代理的ip地址和端口
	 * 
	 * @param url
	 *            用来测试的页面
	 * 
	 * @return true 可用 false 不可用
	 */

	public boolean isProxyUsable(HttpHost proxyHost, String url) {

		HttpGet httpGet = new HttpGet(url);

		httpGet.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
				proxyHost);

		try {

			HttpResponse httpResponse = httpClient.execute(httpGet);

			StatusLine statusLine = httpResponse.getStatusLine();

			int statusCode = statusLine.getStatusCode();

			System.out.println(statusCode);

			if (200 != statusCode) {

				return false;

			}

			HttpEntity httpEntity = httpResponse.getEntity();

			if (httpEntity != null) {

				String html = readHtmlContentFromEntity(httpEntity);

				if (StringUtils.isEmpty(html)) {

					return false;

				}

			} else {

				return false;

			}

		} catch (ClientProtocolException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

			return false;

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

			return false;

		}

		return true;

	}

	/**
	 * 
	 * 解压服务器返回的gzip流
	 * 
	 * @param in
	 *            抓取返回的InputStream流
	 * 
	 * @param charSet
	 *            页面内容编码
	 * 
	 * @return 页面内容的String格式
	 * 
	 * @throws IOException
	 */

	private static String unZip(InputStream in, String charSet)
			throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		GZIPInputStream gis = null;

		try {

			gis = new GZIPInputStream(in);

			byte[] _byte = new byte[1024];

			int len = 0;

			while ((len = gis.read(_byte)) != -1) {

				baos.write(_byte, 0, len);

			}

			String unzipString = new String(baos.toByteArray(), charSet);

			return unzipString;

		} finally {

			if (gis != null) {

				gis.close();

			}

			if (baos != null) {

				baos.close();

			}

		}

	}

	/**
	 * 
	 * 读取InputStream流
	 * 
	 * @param in
	 *            InputStream流
	 * 
	 * @return 从流中读取的String
	 * 
	 * @throws IOException
	 */

	private static String readInStreamToString(InputStream in, String charSet)
			throws IOException {

		StringBuilder str = new StringBuilder();

		String line;

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(in, charSet));

		while ((line = bufferedReader.readLine()) != null) {

			str.append(line);

			str.append("\n");

		}

		if (bufferedReader != null) {

			bufferedReader.close();

		}

		return str.toString();

	}
}