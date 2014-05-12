package serverscript.test;

import com.test.script.IScript;

public class ScriptA implements IScript{
	
	public void hello(){
		System.out.println("hello~~");
		ScriptB.getMe();
	}

	@Override
	public void init() {
				
	}
}
