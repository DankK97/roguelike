/* 
               SUPER SIMPLE EXTREME EASILY FILES EDITING !!!!!!!!!!!!!!!
                        Alistair Carscadden 2015
                                                                                         */

import java.io.*;
import java.util.*;

public class SimpleFiles {
   /* readArray
      requires filename as a String ("ex.txt").
      In a file with n lines: returns a String[] of length n.
      String[n] is the file at line n.
      */
   public static String[] readArray(String fileName) throws IOException {
      FileReader filereader = new FileReader(fileName);
      BufferedReader bufferedreader = new BufferedReader(filereader);
      List<String> lines = new ArrayList<String>();
      
      String s = bufferedreader.readLine();
      while (s != null)
      {
         lines.add(s);
         s = bufferedreader.readLine();
      }
      
      String[] fileArray = new String[lines.size()];
      fileArray = lines.toArray(fileArray);
            
      bufferedreader.close();
      return fileArray;
   }
 
   /* write
      requires filename as a String ("ex.txt").
      requires content as a String ("this is content").
      Overwrites the entire file to content.
      */
      /*
   public static void write(String fileName, String content) throws IOException {
      Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
   }
   
   /* writeArray
      requires filename as a String ("ex.txt");
      requires a String[] of content.
      Overwrites the entire file to content.
      The file at line n will be content[n].
      *//*
   public static void writeArray(String fileName, String[] content) throws IOException {
      String finalWrite = new String();
      for (int i = 0; i < content.length; i++)
      {
         finalWrite += content[i] + "\r\n";
      }
        
      Files.write(Paths.get(fileName), finalWrite.getBytes(), StandardOpenOption.CREATE);
   }
   
   /* add
      requires filename as a String ("ex.txt");
      Appends the content to a new line in the file
      *//*
   public static void add(String fileName, String content) throws IOException {
      String[] fileContent = SimpleFiles.readArray(fileName);
      String[] newContent = new String[fileContent.length + 1];
      System.arraycopy(fileContent, 0, newContent, 0, fileContent.length);
      newContent[newContent.length - 1] = content;
      SimpleFiles.writeArray(fileName, newContent);
   }
   */
   
}
