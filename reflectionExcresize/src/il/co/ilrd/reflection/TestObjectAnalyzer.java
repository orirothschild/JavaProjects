package il.co.ilrd.reflection;
import java.lang.reflect.Method; 
public class TestObjectAnalyzer {

	public static void main(String[] args) {
		try {
			Object obj = ObjectAnalyzer.givemeinnerclass();
			Method print = obj.getClass().getDeclaredMethods()[0];
			Object i = print.invoke(obj);
			System.out.println(i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
