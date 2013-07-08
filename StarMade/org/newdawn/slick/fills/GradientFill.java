/*   1:    */package org.newdawn.slick.fills;
/*   2:    */
/*   3:    */import org.newdawn.slick.Color;
/*   4:    */import org.newdawn.slick.ShapeFill;
/*   5:    */import org.newdawn.slick.geom.Shape;
/*   6:    */import org.newdawn.slick.geom.Vector2f;
/*   7:    */
/*  16:    */public class GradientFill
/*  17:    */  implements ShapeFill
/*  18:    */{
/*  19: 19 */  private Vector2f none = new Vector2f(0.0F, 0.0F);
/*  20:    */  
/*  21:    */  private Vector2f start;
/*  22:    */  
/*  23:    */  private Vector2f end;
/*  24:    */  
/*  25:    */  private Color startCol;
/*  26:    */  
/*  27:    */  private Color endCol;
/*  28:    */  
/*  29: 29 */  private boolean local = false;
/*  30:    */  
/*  41:    */  public GradientFill(float sx, float sy, Color startCol, float ex, float ey, Color endCol)
/*  42:    */  {
/*  43: 43 */    this(sx, sy, startCol, ex, ey, endCol, false);
/*  44:    */  }
/*  45:    */  
/*  57:    */  public GradientFill(float sx, float sy, Color startCol, float ex, float ey, Color endCol, boolean local)
/*  58:    */  {
/*  59: 59 */    this(new Vector2f(sx, sy), startCol, new Vector2f(ex, ey), endCol, local);
/*  60:    */  }
/*  61:    */  
/*  70:    */  public GradientFill(Vector2f start, Color startCol, Vector2f end, Color endCol, boolean local)
/*  71:    */  {
/*  72: 72 */    this.start = new Vector2f(start);
/*  73: 73 */    this.end = new Vector2f(end);
/*  74: 74 */    this.startCol = new Color(startCol);
/*  75: 75 */    this.endCol = new Color(endCol);
/*  76: 76 */    this.local = local;
/*  77:    */  }
/*  78:    */  
/*  83:    */  public GradientFill getInvertedCopy()
/*  84:    */  {
/*  85: 85 */    return new GradientFill(this.start, this.endCol, this.end, this.startCol, this.local);
/*  86:    */  }
/*  87:    */  
/*  92:    */  public void setLocal(boolean local)
/*  93:    */  {
/*  94: 94 */    this.local = local;
/*  95:    */  }
/*  96:    */  
/* 101:    */  public Vector2f getStart()
/* 102:    */  {
/* 103:103 */    return this.start;
/* 104:    */  }
/* 105:    */  
/* 110:    */  public Vector2f getEnd()
/* 111:    */  {
/* 112:112 */    return this.end;
/* 113:    */  }
/* 114:    */  
/* 119:    */  public Color getStartColor()
/* 120:    */  {
/* 121:121 */    return this.startCol;
/* 122:    */  }
/* 123:    */  
/* 128:    */  public Color getEndColor()
/* 129:    */  {
/* 130:130 */    return this.endCol;
/* 131:    */  }
/* 132:    */  
/* 138:    */  public void setStart(float x, float y)
/* 139:    */  {
/* 140:140 */    setStart(new Vector2f(x, y));
/* 141:    */  }
/* 142:    */  
/* 147:    */  public void setStart(Vector2f start)
/* 148:    */  {
/* 149:149 */    this.start = new Vector2f(start);
/* 150:    */  }
/* 151:    */  
/* 157:    */  public void setEnd(float x, float y)
/* 158:    */  {
/* 159:159 */    setEnd(new Vector2f(x, y));
/* 160:    */  }
/* 161:    */  
/* 166:    */  public void setEnd(Vector2f end)
/* 167:    */  {
/* 168:168 */    this.end = new Vector2f(end);
/* 169:    */  }
/* 170:    */  
/* 175:    */  public void setStartColor(Color color)
/* 176:    */  {
/* 177:177 */    this.startCol = new Color(color);
/* 178:    */  }
/* 179:    */  
/* 184:    */  public void setEndColor(Color color)
/* 185:    */  {
/* 186:186 */    this.endCol = new Color(color);
/* 187:    */  }
/* 188:    */  
/* 196:    */  public Color colorAt(Shape shape, float x, float y)
/* 197:    */  {
/* 198:198 */    if (this.local) {
/* 199:199 */      return colorAt(x - shape.getCenterX(), y - shape.getCenterY());
/* 200:    */    }
/* 201:201 */    return colorAt(x, y);
/* 202:    */  }
/* 203:    */  
/* 211:    */  public Color colorAt(float x, float y)
/* 212:    */  {
/* 213:213 */    float dx1 = this.end.getX() - this.start.getX();
/* 214:214 */    float dy1 = this.end.getY() - this.start.getY();
/* 215:    */    
/* 216:216 */    float dx2 = -dy1;
/* 217:217 */    float dy2 = dx1;
/* 218:218 */    float denom = dy2 * dx1 - dx2 * dy1;
/* 219:    */    
/* 220:220 */    if (denom == 0.0F) {
/* 221:221 */      return Color.black;
/* 222:    */    }
/* 223:    */    
/* 224:224 */    float ua = dx2 * (this.start.getY() - y) - dy2 * (this.start.getX() - x);
/* 225:225 */    ua /= denom;
/* 226:226 */    float ub = dx1 * (this.start.getY() - y) - dy1 * (this.start.getX() - x);
/* 227:227 */    ub /= denom;
/* 228:228 */    float u = ua;
/* 229:229 */    if (u < 0.0F) {
/* 230:230 */      u = 0.0F;
/* 231:    */    }
/* 232:232 */    if (u > 1.0F) {
/* 233:233 */      u = 1.0F;
/* 234:    */    }
/* 235:235 */    float v = 1.0F - u;
/* 236:    */    
/* 238:238 */    Color col = new Color(1, 1, 1, 1);
/* 239:239 */    col.r = (u * this.endCol.r + v * this.startCol.r);
/* 240:240 */    col.b = (u * this.endCol.b + v * this.startCol.b);
/* 241:241 */    col.g = (u * this.endCol.g + v * this.startCol.g);
/* 242:242 */    col.a = (u * this.endCol.a + v * this.startCol.a);
/* 243:    */    
/* 244:244 */    return col;
/* 245:    */  }
/* 246:    */  
/* 249:    */  public Vector2f getOffsetAt(Shape shape, float x, float y)
/* 250:    */  {
/* 251:251 */    return this.none;
/* 252:    */  }
/* 253:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.fills.GradientFill
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */