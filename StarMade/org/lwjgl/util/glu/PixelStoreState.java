/*  1:   */package org.lwjgl.util.glu;
/*  2:   */
/*  3:   */import org.lwjgl.opengl.GL11;
/*  4:   */
/* 45:   */class PixelStoreState
/* 46:   */  extends Util
/* 47:   */{
/* 48:   */  public int unpackRowLength;
/* 49:   */  public int unpackAlignment;
/* 50:   */  public int unpackSkipRows;
/* 51:   */  public int unpackSkipPixels;
/* 52:   */  public int packRowLength;
/* 53:   */  public int packAlignment;
/* 54:   */  public int packSkipRows;
/* 55:   */  public int packSkipPixels;
/* 56:   */  
/* 57:   */  PixelStoreState()
/* 58:   */  {
/* 59:59 */    load();
/* 60:   */  }
/* 61:   */  
/* 62:   */  public void load() {
/* 63:63 */    this.unpackRowLength = glGetIntegerv(3314);
/* 64:64 */    this.unpackAlignment = glGetIntegerv(3317);
/* 65:65 */    this.unpackSkipRows = glGetIntegerv(3315);
/* 66:66 */    this.unpackSkipPixels = glGetIntegerv(3316);
/* 67:67 */    this.packRowLength = glGetIntegerv(3330);
/* 68:68 */    this.packAlignment = glGetIntegerv(3333);
/* 69:69 */    this.packSkipRows = glGetIntegerv(3331);
/* 70:70 */    this.packSkipPixels = glGetIntegerv(3332);
/* 71:   */  }
/* 72:   */  
/* 73:   */  public void save() {
/* 74:74 */    GL11.glPixelStorei(3314, this.unpackRowLength);
/* 75:75 */    GL11.glPixelStorei(3317, this.unpackAlignment);
/* 76:76 */    GL11.glPixelStorei(3315, this.unpackSkipRows);
/* 77:77 */    GL11.glPixelStorei(3316, this.unpackSkipPixels);
/* 78:78 */    GL11.glPixelStorei(3330, this.packRowLength);
/* 79:79 */    GL11.glPixelStorei(3333, this.packAlignment);
/* 80:80 */    GL11.glPixelStorei(3331, this.packSkipRows);
/* 81:81 */    GL11.glPixelStorei(3332, this.packSkipPixels);
/* 82:   */  }
/* 83:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.PixelStoreState
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */