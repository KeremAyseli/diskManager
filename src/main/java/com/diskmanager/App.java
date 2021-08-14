package com.diskmanager;



import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        FolderManagerWindows fm = new FolderManagerWindows();

     System.out.println(fm.getCreationTime("D", "Java")+" "+fm.getLastAccess("D", "Java")); 


    }
    
}
