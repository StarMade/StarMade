/*    */ package org.lwjgl.opengles;
/*    */ 
/*    */ import java.nio.IntBuffer;
/*    */ import org.lwjgl.BufferUtils;
/*    */ 
/*    */ public final class ContextAttribs
/*    */ {
/*    */   private int version;
/*    */ 
/*    */   public ContextAttribs()
/*    */   {
/* 49 */     this(2);
/*    */   }
/*    */ 
/*    */   public ContextAttribs(int version) {
/* 53 */     if (3 < version) {
/* 54 */       throw new IllegalArgumentException("Invalid OpenGL ES version specified: " + version);
/*    */     }
/* 56 */     this.version = version;
/*    */   }
/*    */ 
/*    */   private ContextAttribs(ContextAttribs attribs) {
/* 60 */     this.version = attribs.version;
/*    */   }
/*    */ 
/*    */   public int getVersion() {
/* 64 */     return this.version;
/*    */   }
/*    */ 
/*    */   public IntBuffer getAttribList() {
/* 68 */     int attribCount = 1;
/*    */ 
/* 70 */     IntBuffer attribs = BufferUtils.createIntBuffer(attribCount * 2 + 1);
/*    */ 
/* 72 */     attribs.put(12440).put(this.version);
/*    */ 
/* 74 */     attribs.put(12344);
/* 75 */     attribs.rewind();
/* 76 */     return attribs;
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 80 */     StringBuilder sb = new StringBuilder(32);
/*    */ 
/* 82 */     sb.append("ContextAttribs:");
/* 83 */     sb.append(" Version=").append(this.version);
/*    */ 
/* 85 */     return sb.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengles.ContextAttribs
 * JD-Core Version:    0.6.2
 */