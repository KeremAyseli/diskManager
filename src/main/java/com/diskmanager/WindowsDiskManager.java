package com.diskmanager;

import java.util.List;
import java.io.IOException;

/*
| ___ \ |                         | |                              / _|     | |
| |_/ / |     ___  __ _ ___  ___  | |__   ___    ___ __ _ _ __ ___| |_ _   _| |
|  __/| |    / _ \/ _` / __|/ _ \ | '_ \ / _ \  / __/ _` | '__/ _ \  _| | | | |
| |   | |___|  __/ (_| \__ \  __/ | |_) |  __/ | (_| (_| | | |  __/ | | |_| | |
\_|   \_____/\___|\__,_|___/\___| |_.__/ \___|  \___\__,_|_|  \___|_|  \__,_|_|
                                                                               */

/// <summary>
/// This class may will dangerous,I cant test on any disk.PLEASE BE CAREFUL.
/// </summary>   
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
