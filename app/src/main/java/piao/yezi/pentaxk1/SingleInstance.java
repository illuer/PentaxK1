package piao.yezi.pentaxk1;

import java.lang.reflect.Constructor;
import java.util.HashMap;

/**
 * 单例基类
 * 
 * @author 张琳
 * 
 */
public class SingleInstance {

	private static HashMap<String, Object> m_registry = new HashMap<String, Object>();

	static {
		SingleInstance x = new SingleInstance();
		m_registry.put(x.getClass().getName(), x);
	}

	/**
	 * 保护的默认构造子
	 */
	protected SingleInstance() {
	}

	/**
	 * 静态工厂方法，返还此类惟一的实例
	 */
	public static SingleInstance getInstance(String name) {

		if (name == null) {
			name = SingleInstance.class.getName();
		}

		if (m_registry.get(name) == null) {

			try {

				Class<?> cls = Class.forName(name);

				Constructor<?> constructor = cls.getDeclaredConstructors()[0];

				constructor.setAccessible(true);

				m_registry.put(name, constructor.newInstance());

			} catch (Exception e) {


			}
		}
		return (SingleInstance) (m_registry.get(name));
	}

}
