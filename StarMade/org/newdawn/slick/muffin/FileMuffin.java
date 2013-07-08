/*  1:   */package org.newdawn.slick.muffin;
/*  2:   */
/*  3:   */import java.io.EOFException;
/*  4:   */import java.io.File;
/*  5:   */import java.io.FileInputStream;
/*  6:   */import java.io.FileOutputStream;
/*  7:   */import java.io.IOException;
/*  8:   */import java.io.ObjectInputStream;
/*  9:   */import java.io.ObjectOutputStream;
/* 10:   */import java.util.HashMap;
/* 11:   */import org.newdawn.slick.util.Log;
/* 12:   */
/* 21:   */public class FileMuffin
/* 22:   */  implements Muffin
/* 23:   */{
/* 24:   */  public void saveFile(HashMap scoreMap, String fileName)
/* 25:   */    throws IOException
/* 26:   */  {
/* 27:27 */    String userHome = System.getProperty("user.home");
/* 28:28 */    File file = new File(userHome);
/* 29:29 */    file = new File(file, ".java");
/* 30:30 */    if (!file.exists()) {
/* 31:31 */      file.mkdir();
/* 32:   */    }
/* 33:   */    
/* 34:34 */    file = new File(file, fileName);
/* 35:35 */    FileOutputStream fos = new FileOutputStream(file);
/* 36:36 */    ObjectOutputStream oos = new ObjectOutputStream(fos);
/* 37:   */    
/* 39:39 */    oos.writeObject(scoreMap);
/* 40:   */    
/* 41:41 */    oos.close();
/* 42:   */  }
/* 43:   */  
/* 45:   */  public HashMap loadFile(String fileName)
/* 46:   */    throws IOException
/* 47:   */  {
/* 48:48 */    HashMap hashMap = new HashMap();
/* 49:49 */    String userHome = System.getProperty("user.home");
/* 50:   */    
/* 51:51 */    File file = new File(userHome);
/* 52:52 */    file = new File(file, ".java");
/* 53:53 */    file = new File(file, fileName);
/* 54:   */    
/* 55:55 */    if (file.exists()) {
/* 56:   */      try {
/* 57:57 */        FileInputStream fis = new FileInputStream(file);
/* 58:58 */        ObjectInputStream ois = new ObjectInputStream(fis);
/* 59:   */        
/* 60:60 */        hashMap = (HashMap)ois.readObject();
/* 61:   */        
/* 62:62 */        ois.close();
/* 64:   */      }
/* 65:   */      catch (EOFException e) {}catch (ClassNotFoundException e)
/* 66:   */      {
/* 67:67 */        Log.error(e);
/* 68:68 */        throw new IOException("Failed to pull state from store - class not found");
/* 69:   */      }
/* 70:   */    }
/* 71:   */    
/* 72:72 */    return hashMap;
/* 73:   */  }
/* 74:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.muffin.FileMuffin
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */