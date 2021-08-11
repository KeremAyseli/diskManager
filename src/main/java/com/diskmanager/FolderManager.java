package com.diskmanager;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FolderManager extends DiskManger {

        
    private List<Folder> AllFolder;
    public List<Folder> getAllFolder() {
        return AllFolder;
    }

    public void setAllFolder(List<Folder> allFolder) {
        AllFolder = allFolder;
    }

    public FolderManager(String DiskLabel) throws IOException {
       AllFolder=new ArrayList<Folder>();
       for(String Folder:readAllFolderInDisk(DiskLabel)){
           AllFolder.add(new Folder(Folder,Paths.get(DiskLabel+"/"+Folder),ReadFolderSize(Folder)));
       }
    }

    public Long ReadFolderSize(String FolderName) throws IOException{
     return Long.parseLong(RunCommand("powershell.exe  $totalsize=[long] 0 ;Get-ChildItem -Path D:\\'"+FolderName+"' -File -Recurse -Force -ErrorAction SilentlyContinue | % {$totalsize += $_.Length};echo $totalsize;").get(0))/1024;
    }

    public List<String> readAllFolderInDisk(String DiskLabel) throws IOException{
        return RunCommand("powershell.exe Get-ChildItem -Path "+DiskLabel+":\\ -Name"); 
    }

}
