package com.diskmanager;



import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        FolderManagerWindows fmw = new FolderManagerWindows("D");
        List<Folder> a= fmw.getAllFolder();
        for(Folder f:a){
           System.out.println(f.getFolderName());
        }


    }
    
}
