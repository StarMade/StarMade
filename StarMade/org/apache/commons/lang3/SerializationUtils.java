/*     */ package org.apache.commons.lang3;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.ObjectStreamClass;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class SerializationUtils
/*     */ {
/*     */   public static <T extends Serializable> T clone(T object)
/*     */   {
/*  77 */     if (object == null) {
/*  78 */       return null;
/*     */     }
/*  80 */     byte[] objectData = serialize(object);
/*  81 */     ByteArrayInputStream bais = new ByteArrayInputStream(objectData);
/*     */ 
/*  83 */     ClassLoaderAwareObjectInputStream in = null;
/*     */     try
/*     */     {
/*  86 */       in = new ClassLoaderAwareObjectInputStream(bais, object.getClass().getClassLoader());
/*     */ 
/*  93 */       Serializable readObject = (Serializable)in.readObject();
/*  94 */       return readObject;
/*     */     }
/*     */     catch (ClassNotFoundException ex) {
/*  97 */       throw new SerializationException("ClassNotFoundException while reading cloned object data", ex);
/*     */     } catch (IOException ex) {
/*  99 */       throw new SerializationException("IOException while reading cloned object data", ex);
/*     */     } finally {
/*     */       try {
/* 102 */         if (in != null)
/* 103 */           in.close();
/*     */       }
/*     */       catch (IOException ex) {
/* 106 */         throw new SerializationException("IOException on closing cloned object data InputStream.", ex);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void serialize(Serializable obj, OutputStream outputStream)
/*     */   {
/* 129 */     if (outputStream == null) {
/* 130 */       throw new IllegalArgumentException("The OutputStream must not be null");
/*     */     }
/* 132 */     ObjectOutputStream out = null;
/*     */     try
/*     */     {
/* 135 */       out = new ObjectOutputStream(outputStream);
/* 136 */       out.writeObject(obj);
/*     */     }
/*     */     catch (IOException ex) {
/* 139 */       throw new SerializationException(ex);
/*     */     } finally {
/*     */       try {
/* 142 */         if (out != null)
/* 143 */           out.close();
/*     */       }
/*     */       catch (IOException ex)
/*     */       {
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static byte[] serialize(Serializable obj)
/*     */   {
/* 160 */     ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
/* 161 */     serialize(obj, baos);
/* 162 */     return baos.toByteArray();
/*     */   }
/*     */ 
/*     */   public static Object deserialize(InputStream inputStream)
/*     */   {
/* 183 */     if (inputStream == null) {
/* 184 */       throw new IllegalArgumentException("The InputStream must not be null");
/*     */     }
/* 186 */     ObjectInputStream in = null;
/*     */     try
/*     */     {
/* 189 */       in = new ObjectInputStream(inputStream);
/* 190 */       return in.readObject();
/*     */     }
/*     */     catch (ClassNotFoundException ex) {
/* 193 */       throw new SerializationException(ex);
/*     */     } catch (IOException ex) {
/* 195 */       throw new SerializationException(ex);
/*     */     } finally {
/*     */       try {
/* 198 */         if (in != null)
/* 199 */           in.close();
/*     */       }
/*     */       catch (IOException ex)
/*     */       {
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Object deserialize(byte[] objectData)
/*     */   {
/* 216 */     if (objectData == null) {
/* 217 */       throw new IllegalArgumentException("The byte[] must not be null");
/*     */     }
/* 219 */     ByteArrayInputStream bais = new ByteArrayInputStream(objectData);
/* 220 */     return deserialize(bais);
/*     */   }
/*     */ 
/*     */   static class ClassLoaderAwareObjectInputStream extends ObjectInputStream
/*     */   {
/*     */     private ClassLoader classLoader;
/*     */ 
/*     */     public ClassLoaderAwareObjectInputStream(InputStream in, ClassLoader classLoader)
/*     */       throws IOException
/*     */     {
/* 247 */       super();
/* 248 */       this.classLoader = classLoader;
/*     */     }
/*     */ 
/*     */     protected Class<?> resolveClass(ObjectStreamClass desc)
/*     */       throws IOException, ClassNotFoundException
/*     */     {
/* 261 */       String name = desc.getName();
/*     */       try {
/* 263 */         return Class.forName(name, false, this.classLoader); } catch (ClassNotFoundException ex) {
/*     */       }
/* 265 */       return Class.forName(name, false, Thread.currentThread().getContextClassLoader());
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.SerializationUtils
 * JD-Core Version:    0.6.2
 */