/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/* 14:   */final class WindowsFileVersion
/* 15:   */{
/* 16:   */  private final int product_version_ms;
/* 17:   */  
/* 28:   */  private final int product_version_ls;
/* 29:   */  
/* 41:   */  WindowsFileVersion(int product_version_ms, int product_version_ls)
/* 42:   */  {
/* 43:43 */    this.product_version_ms = product_version_ms;
/* 44:44 */    this.product_version_ls = product_version_ls;
/* 45:   */  }
/* 46:   */  
/* 47:   */  public String toString() {
/* 48:48 */    int f1 = this.product_version_ms >> 16 & 0xFFFF;
/* 49:49 */    int f2 = this.product_version_ms & 0xFFFF;
/* 50:50 */    int f3 = this.product_version_ls >> 16 & 0xFFFF;
/* 51:51 */    int f4 = this.product_version_ls & 0xFFFF;
/* 52:52 */    return f1 + "." + f2 + "." + f3 + "." + f4;
/* 53:   */  }
/* 54:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.WindowsFileVersion
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */