package serverscript.test;

public class ScriptB {
	public static ScriptB instance = new ScriptB();
	private ScriptB(){}
	public static ScriptB getMe(){
		return instance;
	}
	public void hello(){
		System.out.println("hello~,B");
	}
}
