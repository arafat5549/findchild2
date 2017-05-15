package jsoup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

public class CrawlData {

	private static String DATA_DIR = "data/";
	private static String ID_TXT = "id.txt";
	private static char SPLLITER = ',';
	
	
	/**
	 * 爬虫取对应网站信息保存为文本
	 */
	@Test
	public void crawl(){
		String homeurl = "http://www.zgszrkdak.com";
		
		try {
			//_crawlHomePage(homeurl);
			
			/*
			InputStream in = new FileInputStream(new File(ID_TXT));
			String ids = IOUtils.toString(in);
			List<String> lists = Splitter.on(SPLLITER).splitToList(ids);
			System.out.println(lists);
			IOUtils.closeQuietly(in);
			Set<String> emptys = new HashSet<String>();
			final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_45);
			for (String id : lists) {
				String txtName = id+".txt";
				boolean flag = new File(DATA_DIR+txtName).exists();
				if(!flag && !emptys.contains(id)){
					String url = homeurl+"/show.asp?id="+id;
					String ret  = _htmlUnit(webClient,url,txtName,id);
					emptys.add(ret);
				}
				else{
					//System.out.println(txtName);
				}
			}
			//int id = 21921;
			//String txtName = id+".txt";
			//String url = homeurl+"/show.asp?id="+id;
			//_htmlUnit(url,txtName);
			*/
			
			File dir = new File(DATA_DIR);
			File files[] = dir.listFiles();
			for (File file : files) {
				//System.out.println(file.getName());
				_parseText(file.getName());
			}
			//
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	
	/**
	 * 3.将下载下来的文本解析为对象
	 */
	public static void _parseText(String txtName) throws IOException, IllegalAccessException, InvocationTargetException{
		InputStream in = new FileInputStream(new File(DATA_DIR+txtName));
		
		//IO工具类读取
		String demo = IOUtils.toString(in);
		//System.out.println(demo);
		//转成单行带分隔符的字符串
		String newLine = CharMatcher.WHITESPACE.trimAndCollapseFrom(demo, '#');		
		//System.out.println(newLine);
		//分割成List
		List<String> lists = Splitter.on('#').omitEmptyStrings().splitToList(newLine);
		//System.out.println(Joiner.on("-").join(lists));
		Map<String,String> maps = new HashMap<String,String>();
		for(int i=0;i<lists.size();i++)
		{
			String line = lists.get(i);
			if(!"".equals(StringUtils.trim(line)))
			{
				if(line.equals("失踪人姓名")){
					maps.put("logname", lists.get(i+1));
				}
				else if(line.equals("寻找类别") || line.equals("寻找类型")){
					maps.put("logtype", lists.get(i+1));
				}
				else if(line.equals("性别")){
					maps.put("gender", lists.get(i+1));
				}
				else if(line.equals("失踪人籍贯")){
					maps.put("birthplace", lists.get(i+1));
				}
				else if(line.equals("出生日期")){
					maps.put("birthday", lists.get(i+1));
				}
				else if(line.equals("失踪日期") || line.equals("失踪时间")){
					maps.put("losttime", lists.get(i+1));
				}
				else if(line.equals("失踪时身高")){
					maps.put("height", lists.get(i+1));
				}
				else if(line.equals("失踪地点")){
					maps.put("lostplace", lists.get(i+1));
				}
				else if(line.equals("提供线索酬金") || line.startsWith("提供准确信息者")){
					maps.put("reward1", lists.get(i+1));
				}
				else if(line.equals("护送回家酬金") || line.startsWith("护送回家者")){
					maps.put("reward2", lists.get(i+1));
				}
				else if(line.equals("联系电话") || line.equals("联系​电话"))
				{
					maps.put("mobile", lists.get(i+1));
				}
				else if(line.equals("联系人")){
					maps.put("contact", lists.get(i+1));
				}
				else if(line.equals("详细信息")){
					String note = Joiner.on("").join(lists.subList(i+1, lists.size()));
					maps.put("note", note);
				}
				else if(line.equals("综合信息描述")){
					maps.put("note", lists.get(i+1));
				}
			}
		} 		
		Model model = new Model();
		BeanUtils.populate(model, maps);
		//System.out.println(newLine);
		System.out.println(txtName+":"+model.getMobile()); 

	}
	
	/**
	 * 2.爬取对象网站的文本呢信息
	 */
	private static String _htmlUnit(final WebClient webClient,String url,String txtName,String id) throws Exception {
		//String url = "http://v.youku.com/v_show/id_XNDc2MDkzMTIw.html";
		//String xurl = "http://v.youku.com/v_vpofficiallistv5/id_119023280_showid_271942_page_2?__rt=1&__ro=listitem_page2";
		// String a = "<a page=\"2\">178-101</a>";
		// String url="http://www.baidu.com";
		// 模拟一个浏览器
		
		//BrowserVersion.FIREFOX_45
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log","org.apache.commons.logging.impl.NoOpLog");
		java.util.logging.Logger.getLogger("net.sourceforge.htmlunit").setLevel(java.util.logging.Level.OFF);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		// final WebClient webClient=new
		// WebClient(BrowserVersion.FIREFOX_10,"http://myproxyserver",8000);
		// //使用代理
		// final WebClient webClient2=new
		// WebClient(BrowserVersion.INTERNET_EXPLORER_10);
		// 设置webClient的相关参数
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setActiveXNative(false);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.waitForBackgroundJavaScript(60*1000);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		
		webClient.getOptions().setJavaScriptEnabled(true);  
		/*
		webClient.setJavaScriptTimeout(3600*1000);  
		webClient.getOptions().setRedirectEnabled(true);  
		webClient.getOptions().setThrowExceptionOnScriptError(true);  
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(true);  
		webClient.getOptions().setTimeout(3600*1000);  
		webClient.waitForBackgroundJavaScript(600*1000);  
		*/
//		webClient.waitForBackgroundJavaScript(600*1000);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController()); 
		
		// 模拟浏览器打开一个目标网址
		final HtmlPage page = webClient.getPage(url);
//		该方法在getPage()方法之后调用才能生效
		webClient.waitForBackgroundJavaScript(1000*6);
		webClient.setJavaScriptTimeout(0);
//		Thread.sleep(1000 *3L);
		String js = "javascript:checkShowFollow('271942','2');";
		ScriptResult sr = page.executeJavaScript(js);
		HtmlPage newPage = (HtmlPage) sr.getNewPage();
		//String text = newPage.asText();
		//System.out.println("new page.asText=" + text);
		//System.out.println("page.asText=" + page.asText());
		//System.out.println("page.getUrl=" + page.getUrl());
		//String xml =  page.asXml();
		//text.matches("$失踪人.+");
		
		//System.out.println("page.asXml=" + xml );
		//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		//System.out.println("new page.asXml=" + newPage.asXml() );
		String xpath = "//table[@class='cbox']";
		List<?> links = (List<?>) newPage.getByXPath (xpath);
		
		if(null!=links && links.size()>0 ){
			HtmlTable link = (HtmlTable) links.get(0);
			//System.out.println(link.asText());
			
			if (!new File(DATA_DIR).exists()) {//如果上传路径不存在 先创建文件夹
                new File(DATA_DIR).mkdirs();
            } 
			String serverFile = DATA_DIR + "/" + txtName;
            if (!new File(serverFile).exists()) {
                new File(serverFile).createNewFile();
            }
			OutputStream out = new FileOutputStream(new File(serverFile));
			IOUtils.write(link.asText(), out);
			IOUtils.closeQuietly(out);
			System.out.println(txtName);
			return "";
		}
		else{
			System.out.println("out:"+txtName);
			return id;
		}
	}
	/**
	 * 1.爬取主页的所有连接获取id
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws FailingHttpStatusCodeException 
	 */
	private static void _crawlHomePage(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException{
		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_45);
		//日记
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log","org.apache.commons.logging.impl.NoOpLog");
		java.util.logging.Logger.getLogger("net.sourceforge.htmlunit").setLevel(java.util.logging.Level.OFF);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		// 设置webClient的相关参数
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setActiveXNative(false);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.waitForBackgroundJavaScript(60*1000);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
				
		webClient.getOptions().setJavaScriptEnabled(true); 
		//webClient.setAjaxController(new NicelyResynchronizingAjaxController()); 
		
		// 模拟浏览器打开一个目标网址
		final HtmlPage page = webClient.getPage(url);
//		该方法在getPage()方法之后调用才能生效
		webClient.waitForBackgroundJavaScript(1000*6);
		webClient.setJavaScriptTimeout(0);
//		Thread.sleep(1000 *3L);
		String js = "javascript:checkShowFollow('271942','2');";
		ScriptResult sr = page.executeJavaScript(js);
		HtmlPage newPage = (HtmlPage) sr.getNewPage();
		//System.out.println("page.asText=" + newPage.asXml());
		
		String xpath = //"//a[@target='_blank']";
				//"//a[@href='./show.asp?id=']";
				"//a[contains(@href,'./show.asp?id=')]";
		List<HtmlAnchor> links = (List<HtmlAnchor>) newPage.getByXPath (xpath);
		Set<String> set =new HashSet<String>();
		//System.out.println(links.size());
		int i =0;
		for (HtmlAnchor obj : links) {
			//System.out.println(obj.asXml());
			String href = obj.getAttribute("href");
			String id = CharMatcher.DIGIT.retainFrom(href);
		    set.add(id);
		    //System.out.println((i++)+": "+id);
		}
		//System.out.println(set.size());
		OutputStream out = new FileOutputStream(new File(ID_TXT));
		IOUtils.write(Joiner.on(SPLLITER).join(set), out);
	}
	
	
	/** 
     * HttpClient
     * 发送 get请求 
     */  
    public void get(String url) {  
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        try {  
            // 创建httpget.    
            HttpGet httpget = new HttpGet(url);  
            System.out.println("executing request " + httpget.getURI());  
            // 执行get请求.    
            CloseableHttpResponse response = httpclient.execute(httpget);  
            try {  
                // 获取响应实体    
                HttpEntity entity = response.getEntity();  
                System.out.println("--------------------------------------");  
                // 打印响应状态    
                System.out.println(response.getStatusLine());  
                if (entity != null) {  
                    // 打印响应内容长度    
                    System.out.println("Response content length: " + entity.getContentLength());  
                    // 打印响应内容    
                    System.out.println("Response content: " + EntityUtils.toString(entity));  
                }  
                System.out.println("------------------------------------");  
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (ParseException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
}
