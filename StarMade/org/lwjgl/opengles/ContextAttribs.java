/*  1:   */package org.lwjgl.opengles;
/*  2:   */
/*  3:   */import java.nio.IntBuffer;
/*  4:   */import org.lwjgl.BufferUtils;
/*  5:   */
/* 43:   */public final class ContextAttribs
/* 44:   */{
/* 45:   */  private int version;
/* 46:   */  
/* 47:   */  public ContextAttribs()
/* 48:   */  {
/* 49:49 */    this(2);
/* 50:   */  }
/* 51:   */  
/* 52:   */  public ContextAttribs(int version) {
/* 53:53 */    if (3 < version) {
/* 54:54 */      throw new IllegalArgumentException("Invalid OpenGL ES version specified: " + version);
/* 55:   */    }
/* 56:56 */    this.version = version;
/* 57:   */  }
/* 58:   */  
/* 59:   */  private ContextAttribs(ContextAttribs attribs) {
/* 60:60 */    this.version = attribs.version;
/* 61:   */  }
/* 62:   */  
/* 63:   */  public int getVersion() {
/* 64:64 */    return this.version;
/* 65:   */  }
/* 66:   */  
/* 67:   */  public IntBuffer getAttribList() {
/* 68:68 */    int attribCount = 1;
/* 69:   */    
/* 70:70 */    IntBuffer attribs = BufferUtils.createIntBuffer(attribCount * 2 + 1);
/* 71:   */    
/* 72:72 */    attribs.put(12440).put(this.version);
/* 73:   */    
/* 74:74 */    attribs.put(12344);
/* 75:75 */    attribs.rewind();
/* 76:76 */    return attribs;
/* 77:   */  }
/* 78:   */  
/* 79:   */  public String toString() {
/* 80:80 */    StringBuilder sb = new StringBuilder(32);
/* 81:   */    
/* 82:82 */    sb.append("ContextAttribs:");
/* 83:83 */    sb.append(" Version=").append(this.version);
/* 84:   */    
/* 85:85 */    return sb.toString();
/* 86:   */  }
/* 87:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengles.ContextAttribs
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */