package by.gsu.epamlab.model.beans;

public interface ClassA {

	void test() throws FileNotFoundException {;
	
}

public class ClassB implements ClassA {
	
	public void test() {
		
	}
}

public class ClassC {
	public void testC() {
		ClassA a = new ClassB();
		
		a.test();
	}
}