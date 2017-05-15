package test;

import java.text.ParseException;

import com.xjxy.model.Xunqin;
import com.xjxy.service.XunqinService;

/**
 * 这个package下都是业务无关的测试类
 * @author wyy
 * 2017年4月19日
 *
 */

public class MyTest {
	
	public static void main(String[] args) throws ParseException {
//		String time = "2017/04/18-14:43:28";
//		SimpleDateFormat format = new SimpleDateFormat("YYYY/MM/DD-hh:mm:ss");
//		Date date = format.parse(time);
//		System.out.println(date);
		XunqinService xunqinService = new XunqinService();
		Xunqin xunqin = new Xunqin();
		xunqinService.save(xunqin);
	}
}


