/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.broadphase.AxisSweep3Internal.EdgeArray;
/*   4:    */import java.io.PrintStream;
/*   5:    */
/* 119:    */public class AxisSweep3Ext$EdgeArrayImplExt
/* 120:    */  extends AxisSweep3Internal.EdgeArray
/* 121:    */{
/* 122:    */  private short[] pos;
/* 123:    */  private short[] handle;
/* 124:    */  
/* 125:    */  public AxisSweep3Ext$EdgeArrayImplExt(int paramInt)
/* 126:    */  {
/* 127:127 */    this.pos = new short[paramInt];
/* 128:128 */    this.handle = new short[paramInt];
/* 129:    */  }
/* 130:    */  
/* 131:131 */  public void insert(EdgeArrayImplExt paramEdgeArrayImplExt) { System.err.println("[Physics][AXIS-SWEEP] EDGE ARRAY INSERTING grow: " + paramEdgeArrayImplExt.pos.length + " -> " + this.pos.length);
/* 132:132 */    for (int i = 0; i < paramEdgeArrayImplExt.pos.length; i++) {
/* 133:133 */      this.pos[i] = paramEdgeArrayImplExt.pos[i];
/* 134:134 */      this.handle[i] = paramEdgeArrayImplExt.handle[i];
/* 135:    */    }
/* 136:    */  }
/* 137:    */  
/* 138:    */  public void swap(int paramInt1, int paramInt2)
/* 139:    */  {
/* 140:140 */    int i = this.pos[paramInt1];
/* 141:141 */    int j = this.handle[paramInt1];
/* 142:    */    
/* 143:143 */    this.pos[paramInt1] = this.pos[paramInt2];
/* 144:144 */    this.handle[paramInt1] = this.handle[paramInt2];
/* 145:    */    
/* 146:146 */    this.pos[paramInt2] = i;
/* 147:147 */    this.handle[paramInt2] = j;
/* 148:    */  }
/* 149:    */  
/* 150:    */  public void set(int paramInt1, int paramInt2) {
/* 151:151 */    this.pos[paramInt1] = this.pos[paramInt2];
/* 152:152 */    this.handle[paramInt1] = this.handle[paramInt2];
/* 153:    */  }
/* 154:    */  
/* 155:    */  public int getPos(int paramInt)
/* 156:    */  {
/* 157:157 */    return this.pos[paramInt] & 0xFFFF;
/* 158:    */  }
/* 159:    */  
/* 160:    */  public void setPos(int paramInt1, int paramInt2)
/* 161:    */  {
/* 162:162 */    this.pos[paramInt1] = ((short)paramInt2);
/* 163:    */  }
/* 164:    */  
/* 165:    */  public int getHandle(int paramInt)
/* 166:    */  {
/* 167:167 */    return this.handle[paramInt] & 0xFFFF;
/* 168:    */  }
/* 169:    */  
/* 170:    */  public void setHandle(int paramInt1, int paramInt2)
/* 171:    */  {
/* 172:172 */    this.handle[paramInt1] = ((short)paramInt2);
/* 173:    */  }
/* 174:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.AxisSweep3Ext.EdgeArrayImplExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */