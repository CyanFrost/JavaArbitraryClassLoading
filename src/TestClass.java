
public class TestClass {

	//This is pretty much the runtime point. Consider this the same as public static void main(String args[]), 
	//just not static and without the args[].
	public TestClass(){
		print("Hello, World!");
	}
	
	private void print(String s){
		System.out.println(s);
	}
	
}
