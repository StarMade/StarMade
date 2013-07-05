/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ final class WindowsFileVersion
/*    */ {
/*    */   private final int product_version_ms;
/*    */   private final int product_version_ls;
/*    */ 
/*    */   WindowsFileVersion(int product_version_ms, int product_version_ls)
/*    */   {
/* 43 */     this.product_version_ms = product_version_ms;
/* 44 */     this.product_version_ls = product_version_ls;
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 48 */     int f1 = this.product_version_ms >> 16 & 0xFFFF;
/* 49 */     int f2 = this.product_version_ms & 0xFFFF;
/* 50 */     int f3 = this.product_version_ls >> 16 & 0xFFFF;
/* 51 */     int f4 = this.product_version_ls & 0xFFFF;
/* 52 */     return f1 + "." + f2 + "." + f3 + "." + f4;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.WindowsFileVersion
 * JD-Core Version:    0.6.2
 */