/*  1:   */package de.jarnbjo.vorbis;
/*  2:   */
/*  3:   */import de.jarnbjo.util.io.BitInputStream;
/*  4:   */import java.io.IOException;
/*  5:   */
/* 26:   */class Mode
/* 27:   */{
/* 28:   */  private boolean blockFlag;
/* 29:   */  private int windowType;
/* 30:   */  private int transformType;
/* 31:   */  private int mapping;
/* 32:   */  
/* 33:   */  protected Mode(BitInputStream source, SetupHeader header)
/* 34:   */    throws VorbisFormatException, IOException
/* 35:   */  {
/* 36:36 */    this.blockFlag = source.getBit();
/* 37:37 */    this.windowType = source.getInt(16);
/* 38:38 */    this.transformType = source.getInt(16);
/* 39:39 */    this.mapping = source.getInt(8);
/* 40:   */    
/* 41:41 */    if (this.windowType != 0) {
/* 42:42 */      throw new VorbisFormatException("Window type = " + this.windowType + ", != 0");
/* 43:   */    }
/* 44:   */    
/* 45:45 */    if (this.transformType != 0) {
/* 46:46 */      throw new VorbisFormatException("Transform type = " + this.transformType + ", != 0");
/* 47:   */    }
/* 48:   */    
/* 49:49 */    if (this.mapping > header.getMappings().length) {
/* 50:50 */      throw new VorbisFormatException("Mode mapping number is higher than total number of mappings.");
/* 51:   */    }
/* 52:   */  }
/* 53:   */  
/* 54:   */  protected boolean getBlockFlag() {
/* 55:55 */    return this.blockFlag;
/* 56:   */  }
/* 57:   */  
/* 58:   */  protected int getWindowType() {
/* 59:59 */    return this.windowType;
/* 60:   */  }
/* 61:   */  
/* 62:   */  protected int getTransformType() {
/* 63:63 */    return this.transformType;
/* 64:   */  }
/* 65:   */  
/* 66:   */  protected int getMapping() {
/* 67:67 */    return this.mapping;
/* 68:   */  }
/* 69:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.Mode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */