package com.diskmanager;

import java.util.List;
import java.io.IOException;

public class WindowsDiskManager implements IDiskManager {

    CommandManager cmManager = new CommandManager();

    public List<String> GetLogicalDrives() throws Exception, IOException {
        return cmManager.RunCommand("powershell.exe get-disk");
    }

    public void FormatLogicalDrive(int driveLetter) throws Exception, IOException {
        cmManager.RunCommand("powershell.exe clear-disk -removedata -number " + driveLetter);
    }

    public void CreateLogicalDrive(int driveLetter, String fileSystem) throws Exception, IOException {
        cmManager.RunCommand("powershell.exe new-disk -number " + driveLetter + " -fileSystem " + fileSystem);
    }
    public void CreateNewPartition(int driveLetter, String fileSystem) throws Exception, IOException {
        cmManager.RunCommand("powershell.exe new-partition -number " + driveLetter + " -fileSystem " + fileSystem);
    }
    public void CreateNewPartition(String driveLetter, String partitionType, String partitionSize) throws Exception{
        cmManager.RunCommand("powershell.exe new-partition -number " + driveLetter + " -partitionType " + partitionType + " -partitionSize " + partitionSize);
    }
    public void DeleteLogicalDrive(int driveLetter) throws Exception, IOException {
        cmManager.RunCommand("powershell.exe remove-disk -number " + driveLetter);
    }
    public void CreateLogicalDrive(int driveLetter, String fileSystem, String driveSize) throws Exception, IOException {
        cmManager.RunCommand("powershell.exe new-disk -number " + driveLetter + " -fileSystem " + fileSystem + " -driveSize " + driveSize);
    }
}
