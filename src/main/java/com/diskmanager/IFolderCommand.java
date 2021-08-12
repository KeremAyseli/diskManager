package com.diskmanager;

import java.io.IOException;
import java.util.List;

public interface IFolderCommand {
    public Long ReadFolderSize(String DiskLabel,String FolderName)throws IOException;
    public List<String> readAllFolderInDisk(String DiskLabel)throws IOException;
}
