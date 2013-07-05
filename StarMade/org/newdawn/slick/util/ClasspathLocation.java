/*    */ package org.newdawn.slick.util;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import java.net.URL;
/*    */ 
/*    */ public class ClasspathLocation
/*    */   implements ResourceLocation
/*    */ {
/*    */   public URL getResource(String ref)
/*    */   {
/* 16 */     String cpRef = ref.replace('\\', '/');
/* 17 */     return ResourceLoader.class.getClassLoader().getResource(cpRef);
/*    */   }
/*    */ 
/*    */   public InputStream getResourceAsStream(String ref)
/*    */   {
/* 24 */     String cpRef = ref.replace('\\', '/');
/* 25 */     return ResourceLoader.class.getClassLoader().getResourceAsStream(cpRef);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.ClasspathLocation
 * JD-Core Version:    0.6.2
 */