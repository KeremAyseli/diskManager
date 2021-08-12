package com.diskmanager;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FolderManagerWindows extends DiskManger implements IFolderCommand{

        
    private List<Folder> AllFolder;
    public List<Folder> getAllFolder() {
        return AllFolder;
    }

    public void setAllFolder(List<Folder> allFolder) {
        AllFolder = allFolder;
    }

    public FolderManagerWindows(String DiskLabel) throws IOException {
       AllFolder=new ArrayList<Folder>();
       for(String Folder:readAllFolderInDisk(DiskLabel)){
           long folderSize=ReadFolderSizeFast(DiskLabel,Folder);
           AllFolder.add(new Folder(Folder,Paths.get(DiskLabel+"/"+Folder),folderSize==0?ReadFolderSize(DiskLabel, Folder):folderSize));
       }
    }

    public Long ReadFolderSizeFast(String DiskLabel,String FolderName) throws IOException{
     return Long.parseLong(RunCommand("powershell.exe  $totalsize=[long] 0 ;Get-ChildItem -Path "+DiskLabel+":\\'"+FolderName+"' -File -ErrorAction SilentlyContinue | % {$totalsize += $_.Length};echo $totalsize;").get(0))/1024;
    }
    public Long ReadFolderSize(String DiskLabel,String FolderName) throws IOException{
        return Long.parseLong(RunCommand("powershell.exe  $totalsize=[long] 0 ;Get-ChildItem -Path '"+DiskLabel+":\\"+FolderName+"' -File -Recurse -Force -ErrorAction SilentlyContinue | % {$totalsize += $_.Length};echo $totalsize;").get(0))/1024;
       }

    public List<String> readAllFolderInDisk(String DiskLabel) throws IOException{
        return RunCommand("powershell.exe Get-ChildItem -Path "+DiskLabel+":\\ -Name"); 
    }

}
