package findchild2;

import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;

import com.xjxy.model.Announce;
import com.xjxy.model.User;

import com.xjxy.service.UserService;
/**
 * Velocity模板引擎
 * 
 * @author wyy
 * 2017年4月21日
 *
 */
public class VelocityTest {

	@Test
	public void myTest(){
		UserService userService = new UserService();
		//User user = userService.findByName("admin");
		//System.out.println(user);

		User user2 = userService.findById(1);
		System.out.println(user2);
	}

	//@Test
	public void volocityTest(){
		
		VelocityEngine ve = new VelocityEngine();
		
		Properties p = new Properties();
		p.put("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		ve.init(p);
		Template t = ve.getTemplate("demo.vm"); 
		VelocityContext context = new VelocityContext();
		
		
//		context.put("name", "Liang"); 
//		context.put("date", (new Date()).toString());
//		List temp = new ArrayList();
//		temp.add("1"); 
//		temp.add("2"); 
//		context.put("list", temp); 
		
		List<KeyValue> lists = new ArrayList<KeyValue>();
		
		Field fs[] =Announce.class.getDeclaredFields();
		
		for (Field f : fs) {
			 String enName = f.getName();
			 int i = f.getModifiers();
		     String retval = Modifier.toString(i);
		     if(retval.equals("private")){
		    	 KeyValue kv = new KeyValue();
				 kv.setEnName(enName);
				 lists.add(kv);
		     }
		     //System.out.println("Class Modifier = $" + retval+"$");
		     //System.out.println(f.getName()+","+f.getModifiers());
		}
		
		System.out.println(lists);
		
		StringWriter writer = new StringWriter();
		t.merge(context, writer); 
		System.out.println(writer.toString());
	}
	 
}
