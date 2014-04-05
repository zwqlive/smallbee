package com.smallbee.core.script.compiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import com.smallbee.core.script.loader.ScriptLoader;
//	private ScriptClassLoader scriptClassLoader;

public class CommonCompiler implements ICompiler{
	private String classpath = "";
	private String sourcepath="script";
	private String classFileOutPath="script-bin";
	@Override
	public void compile() {
		JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		StandardJavaFileManager javaFileManager = javaCompiler.getStandardFileManager(diagnostics, null,Charset.forName("utf-8"));
		ArrayList<String> compileOptions = new ArrayList<String>();
		
		compileOptions.add("-encoding");
		compileOptions.add("UTF-8");
		
		compileOptions.add("-classpath");
		StringBuilder localStringBuilder = new StringBuilder();
		
		
		URLClassLoader	urlClassLoader = (URLClassLoader)this.getClass().getClassLoader();
		
		for(URL url : urlClassLoader.getURLs()){
			String str = url.getFile();
		    localStringBuilder.append(str).append(File.pathSeparator);
		}
		this.classpath = localStringBuilder.toString();
		compileOptions.add(this.classpath);
		
		compileOptions.add("-sourcepath");
		compileOptions.add(this.sourcepath);
		
		compileOptions.add("-d");
		String sourceUrl = ScriptLoader.class.getProtectionDomain().getCodeSource().getLocation().toString()+this.classFileOutPath;
		System.out.println(sourceUrl);
		String basePath="";
		try {
			basePath = new File("").getCanonicalFile().getAbsolutePath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(basePath+File.separator+classFileOutPath);
		compileOptions.add(basePath+File.separator+classFileOutPath);
		
		compileOptions.add("-g");
		final String scriptPath = basePath+"\\script\\serverscript\\test\\ScriptB.java";
		System.out.println(scriptPath);
		//javaCompiler.run(null, null, null, scriptPath);
		ArrayList<SimpleJavaFileObject> compilationUnits = new ArrayList<SimpleJavaFileObject>();
		
		
		File entrySourceFile = new File("script/serverscript/ScriptEntry.java");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(entrySourceFile),Charset.forName("utf-8")));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String line = "";
		StringBuilder sbSourceContent = new StringBuilder();
		try {
			while((line = reader.readLine()) != null){
				sbSourceContent.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			compilationUnits.add(new StringJavaFileObject(new File("script/serverscript/ScriptEntry.java").getCanonicalFile().toURI(),sbSourceContent.toString()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JavaCompiler.CompilationTask compilationTask = javaCompiler.getTask(null, javaFileManager, diagnostics, compileOptions, null,compilationUnits );
		boolean result = compilationTask.call();
		try {
			javaFileManager.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		
	}
	/**
	 * 
	 * @author will
	 *
	 */
	static class StringJavaFileObject extends SimpleJavaFileObject {

		String content = "";

		public StringJavaFileObject(URI sourceUri, String sourceContent) {
			super(sourceUri, JavaFileObject.Kind.SOURCE);
			content = sourceContent;
		}

		public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
			return content;
		}

	}

}
