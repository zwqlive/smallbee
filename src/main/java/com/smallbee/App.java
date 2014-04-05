package com.smallbee;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        App app = new App();
        System.out.println(app.getClass().getProtectionDomain().getCodeSource().getLocation());
    }
}
