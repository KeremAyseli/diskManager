package com.diskmanager;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

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
