package com.diskmanager;

import java.nio.file.Path;

public class Folder {
    private String FolderName;
    private Path FolderPath;
    private long FolderSize;
    private String FolderCreationTime;
    private String FolderLastModifiedTime;
    private String FolderLastAccessTime;
    private String FolderLastWriteTime;
    private String FolderOwner;
    private String FolderDriver;
    public Folder(String folderName, Path folderPath, long folderSize, String folderCreationTime,
            String folderLastModifiedTime, String folderLastAccessTime, String folderLastWriteTime, String folderOwner,
            String folderDriver) {
        FolderName = folderName;
        FolderPath = folderPath;
        FolderSize = folderSize;
        FolderCreationTime = folderCreationTime;
        FolderLastModifiedTime = folderLastModifiedTime;
        FolderLastAccessTime = folderLastAccessTime;
        FolderLastWriteTime = folderLastWriteTime;
        FolderOwner = folderOwner;
        FolderDriver = folderDriver;
    }
    public Folder() {
    }
    public Folder(String folderName, Path folderPath, long folderSize) {
        FolderName = folderName;
        FolderPath = folderPath;
        FolderSize = folderSize;
    }
    public String getFolderName() {
        return FolderName;
    }
    public void setFolderName(String folderName) {
        FolderName = folderName;
    }
    public Path getFolderPath() {
        return FolderPath;
    }
    public void setFolderPath(Path folderPath) {
        FolderPath = folderPath;
    }
    public long getFolderSize() {
        return FolderSize;
    }
    public void setFolderSize(long folderSize) {
        FolderSize = folderSize;
    }
    public String getFolderCreationTime() {
        return FolderCreationTime;
    }
    public void setFolderCreationTime(String folderCreationTime) {
        FolderCreationTime = folderCreationTime;
    }
    public String getFolderLastModifiedTime() {
        return FolderLastModifiedTime;
    }
    public void setFolderLastModifiedTime(String folderLastModifiedTime) {
        FolderLastModifiedTime = folderLastModifiedTime;
    }
    public String getFolderLastAccessTime() {
        return FolderLastAccessTime;
    }
    public void setFolderLastAccessTime(String folderLastAccessTime) {
        FolderLastAccessTime = folderLastAccessTime;
    }
    public String getFolderLastWriteTime() {
        return FolderLastWriteTime;
    }
    public void setFolderLastWriteTime(String folderLastWriteTime) {
        FolderLastWriteTime = folderLastWriteTime;
    }
    public String getFolderOwner() {
        return FolderOwner;
    }
    public void setFolderOwner(String folderOwner) {
        FolderOwner = folderOwner;
    }
    public String getFolderDriver() {
        return FolderDriver;
    }
    public void setFolderDriver(String folderDriver) {
        FolderDriver = folderDriver;
    }

    
}
