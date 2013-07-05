/*     */ package org.schema.game.common.updater;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Enumeration;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipFile;
/*     */ 
/*     */ public class FileUtil
/*     */ {
/*     */   public static void a(File paramFile1, File paramFile2)
/*     */   {
/*  20 */     System.err.println("DIR FROM " + paramFile1.getAbsolutePath() + " to " + paramFile2.getAbsolutePath());
/*  21 */     if (paramFile1.isDirectory()) {
/*  22 */       if (!paramFile2.exists()) {
/*  23 */         paramFile2.mkdir();
/*     */       }
/*     */ 
/*  26 */       String[] arrayOfString = paramFile1.list();
/*  27 */       for (int i = 0; i < arrayOfString.length; i++)
/*     */       {
/*  29 */         a(new File(paramFile1, arrayOfString[i]), new File(paramFile2, arrayOfString[i]));
/*     */       }
/*     */ 
/*  32 */       return;
/*  33 */     }b(paramFile1, paramFile2);
/*     */   }
/*     */ 
/*     */   public static void b(File paramFile1, File paramFile2)
/*     */   {
/*  57 */     System.err.println("FILE FROM " + paramFile1.getAbsolutePath() + " to " + paramFile2.getAbsolutePath());
/*  58 */     paramFile1 = new BufferedInputStream(new FileInputStream(paramFile1));
/*  59 */     paramFile2 = new BufferedOutputStream(new FileOutputStream(paramFile2));
/*     */ 
/*  62 */     byte[] arrayOfByte = new byte[1024];
/*     */     int i;
/*  64 */     while ((i = paramFile1.read(arrayOfByte)) > 0) {
/*  65 */       paramFile2.write(arrayOfByte, 0, i);
/*     */     }
/*  67 */     paramFile2.flush();
/*  68 */     paramFile1.close();
/*  69 */     paramFile2.close();
/*     */   }
/*     */ 
/*     */   public static final void a(InputStream paramInputStream, OutputStream paramOutputStream) {
/*  73 */     byte[] arrayOfByte = new byte[1024];
/*     */     int i;
/*  75 */     while ((i = paramInputStream.read(arrayOfByte)) >= 0)
/*  76 */       paramOutputStream.write(arrayOfByte, 0, i);
/*  77 */     paramInputStream.close();
/*  78 */     paramOutputStream.close();
/*     */   }
/*     */   public static void main(String[] paramArrayOfString) { try { a(new File("./sector-export/planet.smsec"), "./sector-export/mm/");
/*  83 */       a(new File("./sector-export/mm/"));
/*  84 */       System.err.println(new File("./sector-export/mm/").delete());
/*     */       return;
/*     */     } catch (IOException localIOException) {
/*  88 */       localIOException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void a(File paramFile)
/*     */   {
/*  92 */     if (paramFile.isDirectory())
/*     */     {
/*     */       File[] arrayOfFile;
/*  93 */       int i = (arrayOfFile = paramFile.listFiles()).length; for (int j = 0; j < i; j++) {
/*  94 */         a(arrayOfFile[j]);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  98 */     if (!paramFile.delete())
/*     */     {
/*  99 */       System.err.println("Failed to del: " + paramFile.getAbsolutePath());
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void a(File paramFile, String paramString)
/*     */   {
/* 105 */     if (!paramString.endsWith("/")) {
/* 106 */       paramString = paramString + "/";
/*     */     }
/* 108 */     (
/* 111 */       paramFile = new ZipFile(paramFile))
/* 111 */       .entries();
/*     */ 
/* 113 */     new File(paramString)
/* 114 */       .mkdirs();
/*     */ 
/* 121 */     Enumeration localEnumeration = paramFile.entries();
/*     */     File localFile;
/* 123 */     (
/* 124 */       localFile = new File(paramString))
/* 124 */       .mkdirs();
/*     */ 
/* 126 */     while (localEnumeration.hasMoreElements())
/*     */     {
/*     */       Object localObject1;
/* 128 */       if ((
/* 128 */         localObject1 = (ZipEntry)localEnumeration.nextElement())
/* 128 */         .isDirectory())
/*     */       {
/* 134 */         new File(paramString + ((ZipEntry)localObject1).getName()).mkdir();
/*     */       }
/*     */       else
/*     */       {
/*     */         int i;
/* 141 */         if ((
/* 141 */           i = (
/* 139 */           localObject2 = new String(((ZipEntry)localObject1).getName()))
/* 139 */           .lastIndexOf("/")) >= 0)
/*     */         {
/* 142 */           localObject2 = ((String)localObject2).substring(0, i);
/*     */ 
/* 144 */           if (!(
/* 144 */             localObject3 = new File(paramString + (String)localObject2))
/* 144 */             .exists())
/*     */           {
/* 146 */             ((File)localObject3).mkdirs();
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 151 */         Object localObject2 = new File(paramString + ((ZipEntry)localObject1).getName());
/*     */ 
/* 153 */         System.err.println("Extracting file: " + ((ZipEntry)localObject1).getName() + " exists: " + localFile.exists() + ", is Dir: " + localFile.isDirectory() + ". " + localFile.getAbsolutePath());
/* 154 */         Object localObject3 = new BufferedInputStream(paramFile.getInputStream((ZipEntry)localObject1));
/* 155 */         localObject1 = new BufferedOutputStream(new FileOutputStream((File)localObject2));
/* 156 */         a((InputStream)localObject3, (OutputStream)localObject1);
/*     */       }
/*     */     }
/* 158 */     paramFile.close();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.updater.FileUtil
 * JD-Core Version:    0.6.2
 */