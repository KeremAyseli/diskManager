package com.diskmanager;

import java.util.List;

public interface IDiskManager {

    public List<String> GetLogicalDrives() throws Exception;

    public void CreateLogicalDrive(int driveLetter, String driveName, String driveType) throws Exception;

    public void CreateLogicalDrive(int driveLetter, String driveName) throws Exception;

    public void DeleteLogicalDrive(int driveLetter) throws Exception;

    public void FormatLogicalDrive(int driveLetter) throws Exception;

    public void CreateNewPartition(String driveLetter, String partitionType, String partitionSize) throws Exception;
}
