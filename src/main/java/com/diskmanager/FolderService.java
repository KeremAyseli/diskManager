package com.diskmanager;

import java.util.List;

public class FolderService {
    public Folder getBiggerFolderOfDisk(List<Folder> folders){
        for(int i=0;i<folders.size();i++){
             Folder temporary;
             for(int x=i;x<folders.size();x++){
                 if(folders.get(i).getFolderSize()<folders.get(x).getFolderSize()){
                     temporary=folders.get(i);
                     folders.set(i,folders.get(x));
                     folders.set(x,temporary);
                 }
             }
        }
        return folders.get(0);
    }
}
