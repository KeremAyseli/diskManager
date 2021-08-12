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
        FolderManager fm = new FolderManager("D");
        for(Folder s:fm.getAllFolder()){
            System.out.println(s.getFolderSize()+"MB "+s.getFolderName()+" "+s.getFolderPath());
        }

    }
    
}
