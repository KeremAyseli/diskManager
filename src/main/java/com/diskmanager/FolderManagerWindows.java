package com.diskmanager;

import java.io.IOException;
import java.nio.charset.IllegalCharsetNameException;
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

    public FolderManagerWindows(String DiskLabel) throws IOException,IllegalCharsetNameException {
       AllFolder=new ArrayList<Folder>();
       for(String Folder:readAllFolderInDisk(DiskLabel)){
           long folderSize=readFolderSize(DiskLabel,Folder);
           AllFolder.add(new Folder(Folder,Paths.get(DiskLabel+"/"+Folder),folderSize==0?readFolderSize(DiskLabel, Folder):folderSize));
       }
    }

    public Long readFolderSizeFast(String DiskLabel,String FolderName) throws IOException,IllegalCharsetNameException{
     return Long.parseLong(RunCommand("powershell.exe  $totalsize=[long] 0 ;Get-ChildItem -Path "+DiskLabel+":\\'"+FolderName+"' -File -ErrorAction SilentlyContinue | % {$totalsize += $_.Length};echo $totalsize;").get(0))/1024;
    }
    public Long readFolderSize(String DiskLabel,String FolderName) throws IOException,IllegalCharsetNameException{
        return Long.parseLong(RunCommand("powershell.exe  $totalsize=[long] 0 ;Get-ChildItem -Path '"+DiskLabel+":\\"+FolderName+"' -File -Recurse -Force -ErrorAction SilentlyContinue | % {$totalsize += $_.Length};echo $totalsize;").get(0))/1024;
       }

    public List<String> readAllFolderInDisk(String DiskLabel) throws IOException,IllegalCharsetNameException{
        return RunCommand("powershell.exe Get-ChildItem -Path "+DiskLabel+":\\ -Name"); 
    }
   public String getOwner(String DiskLabel,String FolderName) throws IOException,IllegalCharsetNameException{
       return RunCommand("powershell.exe Get-ChildItem -Path "+DiskLabel+":\\"+FolderName+" -File -ErrorAction SilentlyContinue | % {echo $_.LastWriteTime.GetAccessedByOwner().UserName}").get(0);
   }
   public String getGroup(String DiskLabel,String FolderName) throws IOException,IllegalCharsetNameException{
       return RunCommand("powershell.exe Get-ChildItem -Path "+DiskLabel+":\\"+FolderName+" -File -ErrorAction SilentlyContinue | % {echo $_.LastWriteTime.GetAccessedByOwner().GroupName}").get(0);
   } 
   public String getLastAccess(String DiskLabel,String FolderName) throws IOException,IllegalCharsetNameException{
       return RunCommand("powershell.exe Get-ChildItem -Path "+DiskLabel+":\\"+FolderName+" -File -ErrorAction SilentlyContinue | % {echo $_.LastWriteTime.GetAccessedByOwner().LastAccessTime}").get(0);
   }
   public String getLastModified(String DiskLabel,String FolderName) throws IOException,IllegalCharsetNameException{
       return RunCommand("powershell.exe Get-ChildItem -Path "+DiskLabel+":\\"+FolderName+" -File -ErrorAction SilentlyContinue | % {echo $_.LastWriteTime.GetAccessedByOwner().LastWriteTime}").get(0);
   }
   public String getCreationTime(String DiskLabel,String FolderName) throws IOException,IllegalCharsetNameException{
       return RunCommand("powershell.exe Get-ChildItem -Path "+DiskLabel+":\\"+FolderName+" -File -ErrorAction SilentlyContinue | % {echo $_.LastWriteTime.GetAccessedByOwner().CreationTime}").get(0);
   }
}
