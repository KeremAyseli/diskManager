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

    public FolderManagerWindows(String DiskLabel) throws IOException, IllegalCharsetNameException {
        AllFolder = new ArrayList<Folder>();
        for (String Folder : readAllFolderInDisk(DiskLabel)) {
            long folderSize = readFolderSize(DiskLabel, Folder);
            AllFolder.add(new Folder(Folder, Paths.get(DiskLabel + "/" + Folder),
                    folderSize == 0 ? readFolderSize(DiskLabel, Folder) : folderSize,
                    getCreationTime(DiskLabel, Folder), getLastModified(DiskLabel, Folder),
                    getLastAccess(DiskLabel, Folder), DiskLabel));
        }
    }

    public Long readFolderSizeFast(String DiskLabel, String FolderName)
            throws IOException, IllegalCharsetNameException {
        return Long.parseLong(
                RunCommand("powershell.exe  $totalsize=[long] 0 ;Get-ChildItem -Path " + DiskLabel + ":\\'" + FolderName
                        + "' -File -ErrorAction SilentlyContinue | % {$totalsize += $_.Length};echo $totalsize;")
                                .get(0))
                / 1024;
    }

    public Long readFolderSize(String DiskLabel, String FolderName) throws IOException, IllegalCharsetNameException {
        return Long.parseLong(RunCommand("powershell.exe  $totalsize=[long] 0 ;Get-ChildItem -Path '" + DiskLabel
                + ":\\" + FolderName
                + "' -File -Recurse -Force -ErrorAction SilentlyContinue | % {$totalsize += $_.Length};echo $totalsize;")
                        .get(0))
                / 1024;
    }

    public List<String> readAllFolderInDisk(String DiskLabel) throws IOException, IllegalCharsetNameException {
        return RunCommand("powershell.exe Get-ChildItem -Path " + DiskLabel + ":\\ -Name");
    }

    public String getOwner(String DiskLabel, String FolderName) throws IOException, IllegalCharsetNameException {
        return RunCommand("powershell.exe $owner='';Get-ChildItem -Path " + DiskLabel + ":\\" + FolderName
                + " -File -Recurse -ErrorAction SilentlyContinue | % {$owner= $_.UserName};echo $owner;").get(0);
    }

    public String getLastAccess(String DiskLabel, String FolderName) throws IOException, IllegalCharsetNameException {
        return RunCommand("powershell.exe $lastAccessTime='';Get-ChildItem -Path " + DiskLabel + ":\\" + FolderName
                + " -File -Recurse -ErrorAction SilentlyContinue | % {$lastAccessTime=$_.LastAccessTime.Date.ToString()};echo $lastAccessTime;")
                        .get(0);
    }

    public String getLastModified(String DiskLabel, String FolderName) throws IOException, IllegalCharsetNameException {
        return RunCommand("powershell.exe $lastModifiedTime='';Get-ChildItem -Path " + DiskLabel + ":\\" + FolderName
                + " -File -Recurse -ErrorAction SilentlyContinue | % {$lastModifiedTime= $_.LastWriteTime.Day.ToString()}; echo $lastModifiedTime;")
                        .get(0);
    }

    public String getCreationTime(String DiskLabel, String FolderName) throws IOException, IllegalCharsetNameException {
        return RunCommand("powershell.exe  $creationTime='';Get-ChildItem -Path " + DiskLabel + ":\\" + FolderName
                + " -File -Recurse -ErrorAction SilentlyContinue | % {$creationTime= $_.CreationTime.Month.ToString()+' '+$_.CreationTime.Year.ToString()};echo $creationTime;")
                        .get(0);
    }

    public String getLastModifiedTime(String DiskLabel, String FolderName)
            throws IOException, IllegalCharsetNameException {
        return RunCommand("powershell.exe  $lastAccessTime=''; Get-ChildItem -Path " + DiskLabel + ":\\" + FolderName
                + " -File -Recurse -Force -ErrorAction SilentlyContinue | % {$lastAccessTime= $_.LastWriteTime.Date.ToString()};echo $lastAccessTime;")
                        .get(0);
    }
}
