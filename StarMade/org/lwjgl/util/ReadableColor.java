/*   1:    */package org.lwjgl.util;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */
/* 129:    */public abstract interface ReadableColor
/* 130:    */{
/* 131:131 */  public static final ReadableColor RED = new Color(255, 0, 0);
/* 132:132 */  public static final ReadableColor ORANGE = new Color(255, 128, 0);
/* 133:133 */  public static final ReadableColor YELLOW = new Color(255, 255, 0);
/* 134:134 */  public static final ReadableColor GREEN = new Color(0, 255, 0);
/* 135:135 */  public static final ReadableColor CYAN = new Color(0, 255, 255);
/* 136:136 */  public static final ReadableColor BLUE = new Color(0, 0, 255);
/* 137:137 */  public static final ReadableColor PURPLE = new Color(255, 0, 255);
/* 138:138 */  public static final ReadableColor WHITE = new Color(255, 255, 255);
/* 139:139 */  public static final ReadableColor BLACK = new Color(0, 0, 0);
/* 140:140 */  public static final ReadableColor LTGREY = new Color(192, 192, 192);
/* 141:141 */  public static final ReadableColor DKGREY = new Color(64, 64, 64);
/* 142:142 */  public static final ReadableColor GREY = new Color(128, 128, 128);
/* 143:    */  
/* 144:    */  public abstract int getRed();
/* 145:    */  
/* 146:    */  public abstract int getGreen();
/* 147:    */  
/* 148:    */  public abstract int getBlue();
/* 149:    */  
/* 150:    */  public abstract int getAlpha();
/* 151:    */  
/* 152:    */  public abstract byte getRedByte();
/* 153:    */  
/* 154:    */  public abstract byte getGreenByte();
/* 155:    */  
/* 156:    */  public abstract byte getBlueByte();
/* 157:    */  
/* 158:    */  public abstract byte getAlphaByte();
/* 159:    */  
/* 160:    */  public abstract void writeRGBA(ByteBuffer paramByteBuffer);
/* 161:    */  
/* 162:    */  public abstract void writeRGB(ByteBuffer paramByteBuffer);
/* 163:    */  
/* 164:    */  public abstract void writeABGR(ByteBuffer paramByteBuffer);
/* 165:    */  
/* 166:    */  public abstract void writeBGR(ByteBuffer paramByteBuffer);
/* 167:    */  
/* 168:    */  public abstract void writeBGRA(ByteBuffer paramByteBuffer);
/* 169:    */  
/* 170:    */  public abstract void writeARGB(ByteBuffer paramByteBuffer);
/* 171:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.ReadableColor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */