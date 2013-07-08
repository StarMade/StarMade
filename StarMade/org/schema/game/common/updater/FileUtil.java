/*   1:    */package org.schema.game.common.updater;
/*   2:    */
/*   3:    */import java.io.BufferedInputStream;
/*   4:    */import java.io.BufferedOutputStream;
/*   5:    */import java.io.File;
/*   6:    */import java.io.FileInputStream;
/*   7:    */import java.io.FileOutputStream;
/*   8:    */import java.io.IOException;
/*   9:    */import java.io.InputStream;
/*  10:    */import java.io.OutputStream;
/*  11:    */import java.io.PrintStream;
/*  12:    */import java.util.Enumeration;
/*  13:    */import java.util.zip.ZipEntry;
/*  14:    */import java.util.zip.ZipFile;
/*  15:    */
/*  16:    */public class FileUtil
/*  17:    */{
/*  18:    */  public static void a(File paramFile1, File paramFile2)
/*  19:    */  {
/*  20: 20 */    System.err.println("DIR FROM " + paramFile1.getAbsolutePath() + " to " + paramFile2.getAbsolutePath());
/*  21: 21 */    if (paramFile1.isDirectory()) {
/*  22: 22 */      if (!paramFile2.exists()) {
/*  23: 23 */        paramFile2.mkdir();
/*  24:    */      }
/*  25:    */      
/*  26: 26 */      String[] arrayOfString = paramFile1.list();
/*  27: 27 */      for (int i = 0; i < arrayOfString.length; i++)
/*  28:    */      {
/*  29: 29 */        a(new File(paramFile1, arrayOfString[i]), new File(paramFile2, arrayOfString[i]));
/*  30:    */      }
/*  31:    */      
/*  32: 32 */      return; }
/*  33: 33 */    b(paramFile1, paramFile2);
/*  34:    */  }
/*  35:    */  
/*  55:    */  public static void b(File paramFile1, File paramFile2)
/*  56:    */  {
/*  57: 57 */    System.err.println("FILE FROM " + paramFile1.getAbsolutePath() + " to " + paramFile2.getAbsolutePath());
/*  58: 58 */    paramFile1 = new BufferedInputStream(new FileInputStream(paramFile1));
/*  59: 59 */    paramFile2 = new BufferedOutputStream(new FileOutputStream(paramFile2));
/*  60:    */    
/*  62: 62 */    byte[] arrayOfByte = new byte[1024];
/*  63:    */    int i;
/*  64: 64 */    while ((i = paramFile1.read(arrayOfByte)) > 0) {
/*  65: 65 */      paramFile2.write(arrayOfByte, 0, i);
/*  66:    */    }
/*  67: 67 */    paramFile2.flush();
/*  68: 68 */    paramFile1.close();
/*  69: 69 */    paramFile2.close();
/*  70:    */  }
/*  71:    */  
/*  72:    */  public static final void a(InputStream paramInputStream, OutputStream paramOutputStream) {
/*  73: 73 */    byte[] arrayOfByte = new byte[1024];
/*  74:    */    int i;
/*  75: 75 */    while ((i = paramInputStream.read(arrayOfByte)) >= 0)
/*  76: 76 */      paramOutputStream.write(arrayOfByte, 0, i);
/*  77: 77 */    paramInputStream.close();
/*  78: 78 */    paramOutputStream.close();
/*  79:    */  }
/*  80:    */  
/*  81:    */  public static void main(String[] paramArrayOfString) {
/*  82: 82 */    try { a(new File("./sector-export/planet.smsec"), "./sector-export/mm/");
/*  83: 83 */      a(new File("./sector-export/mm/"));
/*  84: 84 */      System.err.println(new File("./sector-export/mm/").delete()); return;
/*  85:    */    } catch (IOException localIOException) {
/*  86: 86 */      
/*  87:    */      
/*  88: 88 */        localIOException;
/*  89:    */    }
/*  90:    */  }
/*  91:    */  
/*  92:    */  public static void a(File paramFile)
/*  93:    */  {
/*  94: 92 */    if (paramFile.isDirectory()) { File[] arrayOfFile;
/*  95: 93 */      int i = (arrayOfFile = paramFile.listFiles()).length; for (int j = 0; j < i; j++) {
/*  96: 94 */        a(arrayOfFile[j]);
/*  97:    */      }
/*  98:    */    }
/*  99:    */    
/* 100: 98 */    if (!paramFile.delete()) {
/* 101: 99 */      System.err.println("Failed to del: " + paramFile.getAbsolutePath());
/* 102:    */    }
/* 103:    */  }
/* 104:    */  
/* 105:    */  public static void a(File paramFile, String paramString)
/* 106:    */  {
/* 107:105 */    if (!paramString.endsWith("/")) {
/* 108:106 */      paramString = paramString + "/";
/* 109:    */    }
/* 110:    */    
/* 113:111 */    (paramFile = new ZipFile(paramFile)).entries();
/* 114:    */    
/* 115:113 */    new File(paramString)
/* 116:114 */      .mkdirs();
/* 117:    */    
/* 123:121 */    Enumeration localEnumeration = paramFile.entries();
/* 124:    */    
/* 125:    */    File localFile;
/* 126:124 */    (localFile = new File(paramString)).mkdirs();
/* 127:    */    
/* 128:126 */    while (localEnumeration.hasMoreElements()) {
/* 129:    */      Object localObject1;
/* 130:128 */      if ((localObject1 = (ZipEntry)localEnumeration.nextElement()).isDirectory())
/* 131:    */      {
/* 136:134 */        new File(paramString + ((ZipEntry)localObject1).getName()).mkdir();
/* 137:    */      }
/* 138:    */      else
/* 139:    */      {
/* 140:    */        int i;
/* 141:    */        
/* 143:141 */        if ((i = (localObject2 = new String(((ZipEntry)localObject1).getName())).lastIndexOf("/")) >= 0) {
/* 144:142 */          localObject2 = ((String)localObject2).substring(0, i);
/* 145:    */          
/* 146:144 */          if (!(localObject3 = new File(paramString + (String)localObject2)).exists())
/* 147:    */          {
/* 148:146 */            ((File)localObject3).mkdirs();
/* 149:    */          }
/* 150:    */        }
/* 151:    */        
/* 153:151 */        Object localObject2 = new File(paramString + ((ZipEntry)localObject1).getName());
/* 154:    */        
/* 155:153 */        System.err.println("Extracting file: " + ((ZipEntry)localObject1).getName() + " exists: " + localFile.exists() + ", is Dir: " + localFile.isDirectory() + ". " + localFile.getAbsolutePath());
/* 156:154 */        Object localObject3 = new BufferedInputStream(paramFile.getInputStream((ZipEntry)localObject1));
/* 157:155 */        localObject1 = new BufferedOutputStream(new FileOutputStream((File)localObject2));
/* 158:156 */        a((InputStream)localObject3, (OutputStream)localObject1);
/* 159:    */      } }
/* 160:158 */    paramFile.close();
/* 161:    */  }
/* 162:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.updater.FileUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */