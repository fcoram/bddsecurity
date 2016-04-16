package main;
 
import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
 
public class ExecuteGroovyScript {
  
 public static void main(String args[])throws Exception{
  String[] scripts = new String[] { "src/main" };
  GroovyScriptEngine gse = new GroovyScriptEngine(scripts);
  Binding binding = new Binding();
  gse.run("hello.groovy", binding);
 }
 
}
