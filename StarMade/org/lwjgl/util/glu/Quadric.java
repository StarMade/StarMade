/*     */ package org.lwjgl.util.glu;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class Quadric
/*     */ {
/*     */   protected int drawStyle;
/*     */   protected int orientation;
/*     */   protected boolean textureFlag;
/*     */   protected int normals;
/*     */ 
/*     */   public Quadric()
/*     */   {
/*  57 */     this.drawStyle = 100012;
/*  58 */     this.orientation = 100020;
/*  59 */     this.textureFlag = false;
/*  60 */     this.normals = 100000;
/*     */   }
/*     */ 
/*     */   protected void normal3f(float x, float y, float z)
/*     */   {
/*  73 */     float mag = (float)Math.sqrt(x * x + y * y + z * z);
/*  74 */     if (mag > 1.0E-005F) {
/*  75 */       x /= mag;
/*  76 */       y /= mag;
/*  77 */       z /= mag;
/*     */     }
/*  79 */     GL11.glNormal3f(x, y, z);
/*     */   }
/*     */ 
/*     */   public void setDrawStyle(int drawStyle)
/*     */   {
/* 101 */     this.drawStyle = drawStyle;
/*     */   }
/*     */ 
/*     */   public void setNormals(int normals)
/*     */   {
/* 118 */     this.normals = normals;
/*     */   }
/*     */ 
/*     */   public void setOrientation(int orientation)
/*     */   {
/* 135 */     this.orientation = orientation;
/*     */   }
/*     */ 
/*     */   public void setTextureFlag(boolean textureFlag)
/*     */   {
/* 150 */     this.textureFlag = textureFlag;
/*     */   }
/*     */ 
/*     */   public int getDrawStyle()
/*     */   {
/* 159 */     return this.drawStyle;
/*     */   }
/*     */ 
/*     */   public int getNormals()
/*     */   {
/* 167 */     return this.normals;
/*     */   }
/*     */ 
/*     */   public int getOrientation()
/*     */   {
/* 175 */     return this.orientation;
/*     */   }
/*     */ 
/*     */   public boolean getTextureFlag()
/*     */   {
/* 183 */     return this.textureFlag;
/*     */   }
/*     */ 
/*     */   protected void TXTR_COORD(float x, float y) {
/* 187 */     if (this.textureFlag) GL11.glTexCoord2f(x, y);
/*     */   }
/*     */ 
/*     */   protected float sin(float r)
/*     */   {
/* 192 */     return (float)Math.sin(r);
/*     */   }
/*     */ 
/*     */   protected float cos(float r) {
/* 196 */     return (float)Math.cos(r);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.Quadric
 * JD-Core Version:    0.6.2
 */