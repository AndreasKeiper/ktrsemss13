package akkaenvironment;

public class ArrayClassLoader extends ClassLoader {

	public ArrayClassLoader(ClassLoader parent) {
		super(parent);
	}

	public Class defineClassFromArray(byte[] clazz) {
		return defineClass(null, clazz, 0, clazz.length);
	}

}
