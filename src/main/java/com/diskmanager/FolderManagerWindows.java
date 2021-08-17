package com.diskmanager;

import java.io.IOException;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FolderManagerWindows extends DiskManger implements IFolderCommand {

    private List<Folder> AllFolder;

    public List<Folder> getAllFolder() {
        return AllFolder;
    }

    public FolderManagerWindows() {
    }

    public void setAllFolder(List<Folder> allFolder) {
        AllFolder = allFolder;
    }
   /// <summary>
   /// Constructor method is default scan for 3 properties;Folder name,Folder path and Folder size.
   /// </summary>
    public FolderManagerWindows(String DiskLabel) throws IOException, IllegalCharsetNameException {
        AllFolder = new ArrayList<Folder>();
        for (String Folder : readAllFolderInDisk(DiskLabel)) {
            long folderSize = readFolderSize(DiskLabel, Folder);
            AllFolder.add(new Folder(Folder, Paths.get(DiskLabel + "/" + Folder),
                    folderSize == 0 ? readFolderSize(DiskLabel, Folder) : folderSize));
                }
    }
    /// <summary>
    /// Read all folder and files in disk,but this process is very slow.
    /// </summary>
     public void ScanWithDetails(String DiskLabel) throws IOException, IllegalCharsetNameException {
        AllFolder = new ArrayList<Folder>();
        for (String Folder : readAllFolderInDisk(DiskLabel)) {
            long folderSize = readFolderSize(DiskLabel, Folder);
            AllFolder.add(new Folder(Folder, Paths.get(DiskLabel + "/" + Folder),
                    folderSize == 0 ? readFolderSize(DiskLabel, Folder) : folderSize,
                    getCreationTime(DiskLabel, Folder), getLastModified(DiskLabel, Folder),
                    getLastAccess(DiskLabel, Folder), DiskLabel));
        }
     }
     /// <summary>
     /// Read all folder in disk.Maybe some folder or file can't read it,because this method is not use -Recurse -Force parameters.
     /// </summary>
    public Long readFolderSizeFast(String DiskLabel, String FolderName)
            throws IOException, IllegalCharsetNameException {
        return Long.parseLong(
                RunCommand("powershell.exe  $totalsize=[long] 0 ;Get-ChildItem -Path " + DiskLabel + ":\\'" + FolderName
                        + "' -File -ErrorAction SilentlyContinue | % {$totalsize += $_.Length};echo $totalsize;")
                                .get(0))
                / 1024;
    }
    /// <summary>
    /// Read all folder in disk.But it is may slow.
    /// </summary>
    public Long readFolderSize(String DiskLabel, String FolderName) throws IOException, IllegalCharsetNameException {
        return Long.parseLong(RunCommand("powershell.exe  $totalsize=[long] 0 ;Get-ChildItem -Path '" + DiskLabel
                + ":\\" + FolderName
                + "' -File -Recurse -Force -ErrorAction SilentlyContinue | % {$totalsize += $_.Length};echo $totalsize;")
                        .get(0))
                / 1024;
    }
    /// <summary>
    /// Read all folder or file name in disk.
    /// </summary>
    public List<String> readAllFolderInDisk(String DiskLabel) throws IOException, IllegalCharsetNameException {
        return RunCommand("powershell.exe Get-ChildItem -Path " + DiskLabel + ":\\ -Name");
    }
    /// <summary>
    /// Return Owner of folder or file.
    /// </summary> 
    public String getOwner(String DiskLabel, String FolderName) throws IOException, IllegalCharsetNameException {
        return RunCommand("powershell.exe $owner='';Get-ChildItem -Path " + DiskLabel + ":\\" + FolderName
                + " -File -Recurse -ErrorAction SilentlyContinue | % {$owner= $_.UserName};echo $owner;").get(0);
    }
    /// <summary>
    /// Return LastAccessTime of folder or file.
    /// </summary>
    public String getLastAccess(String DiskLabel, String FolderName) throws IOException, IllegalCharsetNameException {
        return RunCommand("powershell.exe $lastAccessTime='';Get-ChildItem -Path " + DiskLabel + ":\\" + FolderName
                + " -File -Recurse -ErrorAction SilentlyContinue | % {$lastAccessTime=$_.LastAccessTime.Date.ToString()};echo $lastAccessTime;")
                        .get(0);
    }
    /// <summary>
    /// Return LastModifiedTime of folder or file.
    /// </summary>
    public String getLastModified(String DiskLabel, String FolderName) throws IOException, IllegalCharsetNameException {
        return RunCommand("powershell.exe $lastModifiedTime='';Get-ChildItem -Path " + DiskLabel + ":\\" + FolderName
                + " -File -Recurse -ErrorAction SilentlyContinue | % {$lastModifiedTime= $_.LastWriteTime.Day.ToString()}; echo $lastModifiedTime;")
                        .get(0);
    }
    /// <summary>
    /// Return CreationTime of folder or file.
    /// </summary>
    public String getCreationTime(String DiskLabel, String FolderName) throws IOException, IllegalCharsetNameException {
        return RunCommand("powershell.exe  $creationTime='';Get-ChildItem -Path " + DiskLabel + ":\\" + FolderName
                + " -File -Recurse -ErrorAction SilentlyContinue | % {$creationTime= $_.CreationTime.Month.ToString()+' '+$_.CreationTime.Year.ToString()};echo $creationTime;")
                        .get(0);
    }
   /// <summary>
   /// Return lastModifiedTime of folder or file.
   /// </summary>
    public String getLastModifiedTime(String DiskLabel, String FolderName)
            throws IOException, IllegalCharsetNameException {
        return RunCommand("powershell.exe  $lastAccessTime=''; Get-ChildItem -Path " + DiskLabel + ":\\" + FolderName
                + " -File -Recurse -Force -ErrorAction SilentlyContinue | % {$lastAccessTime= $_.LastWriteTime.Date.ToString()};echo $lastAccessTime;")
                        .get(0);
    }
}
