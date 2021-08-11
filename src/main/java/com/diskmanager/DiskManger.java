package com.diskmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public abstract class DiskManger {
  public List<String> RunCommand(String command) throws IOException {
    Process powerShellProcess = Runtime.getRuntime().exec(command);
    powerShellProcess.getOutputStream().close();
    String line;
    System.out.println("Standard Output:");
    BufferedReader stdout = new BufferedReader(new InputStreamReader(powerShellProcess.getInputStream()));
    List<String>AllLine=new ArrayList<String>();
    while ((line = stdout.readLine()) != null) {
      AllLine.add(line);
    }
    stdout.close();
    System.out.println("Standard Error:");
    BufferedReader stderr = new BufferedReader(new InputStreamReader(powerShellProcess.getErrorStream()));
    while ((line = stderr.readLine()) != null) {
      System.out.println(line);
    }
    stderr.close();
    System.out.println("Done");
    return AllLine;
  }
}
