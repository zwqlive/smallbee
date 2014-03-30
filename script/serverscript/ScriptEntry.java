package serverscript;

import com.test.script.IScript;

import serverscript.test.ScriptA;
import serverscript.test.ScriptB;

/**
 * 脚本入口
 * 
 * @author will
 *
 */
public class ScriptEntry implements IScript{
	
	@Override
	public void init(){
		ScriptA a = new ScriptA();
		System.out.println(a);
		
		ScriptB b = ScriptB.getMe();
		System.out.println(b);
		System.out.println("ScriptEntry init finish...hah121212");
	}
	
	public static void main(String[] args){
		new ScriptEntry().init();
	}
}
