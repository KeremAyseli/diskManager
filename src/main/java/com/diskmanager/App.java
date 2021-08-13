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
        FolderManagerWindows fm = new FolderManagerWindows("G");
        FolderService fs = new FolderService();
        Folder a =fs.getBiggerFolderOfDisk(fm.getAllFolder());
       System.out.println(a.getFolderSize()+" "+a.getFolderName()+" "+a.getFolderPath());



    }
    
}
