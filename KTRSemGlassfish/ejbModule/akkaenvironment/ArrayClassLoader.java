package akkaenvironment;

public class ArrayClassLoader extends ClassLoader {

	public ArrayClassLoader(ClassLoader parent) {
		super(parent);
	}

	public Class<?> defineClassFromArray(byte[] clazz) {
		Class<?> tmp = defineClass(null, clazz, 0, clazz.length);
		resolveClass(tmp);
		return tmp;
	}

}
