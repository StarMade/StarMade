/*   1:    */package org.newdawn.slick.geom;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */
/*   9:    */public class MorphShape
/*  10:    */  extends Shape
/*  11:    */{
/*  12: 12 */  private ArrayList shapes = new ArrayList();
/*  13:    */  
/*  15:    */  private float offset;
/*  16:    */  
/*  18:    */  private Shape current;
/*  19:    */  
/*  21:    */  private Shape next;
/*  22:    */  
/*  25:    */  public MorphShape(Shape base)
/*  26:    */  {
/*  27: 27 */    this.shapes.add(base);
/*  28: 28 */    float[] copy = base.points;
/*  29: 29 */    this.points = new float[copy.length];
/*  30:    */    
/*  31: 31 */    this.current = base;
/*  32: 32 */    this.next = base;
/*  33:    */  }
/*  34:    */  
/*  39:    */  public void addShape(Shape shape)
/*  40:    */  {
/*  41: 41 */    if (shape.points.length != this.points.length) {
/*  42: 42 */      throw new RuntimeException("Attempt to morph between two shapes with different vertex counts");
/*  43:    */    }
/*  44:    */    
/*  45: 45 */    Shape prev = (Shape)this.shapes.get(this.shapes.size() - 1);
/*  46: 46 */    if (equalShapes(prev, shape)) {
/*  47: 47 */      this.shapes.add(prev);
/*  48:    */    } else {
/*  49: 49 */      this.shapes.add(shape);
/*  50:    */    }
/*  51:    */    
/*  52: 52 */    if (this.shapes.size() == 2) {
/*  53: 53 */      this.next = ((Shape)this.shapes.get(1));
/*  54:    */    }
/*  55:    */  }
/*  56:    */  
/*  63:    */  private boolean equalShapes(Shape a, Shape b)
/*  64:    */  {
/*  65: 65 */    a.checkPoints();
/*  66: 66 */    b.checkPoints();
/*  67:    */    
/*  68: 68 */    for (int i = 0; i < a.points.length; i++) {
/*  69: 69 */      if (a.points[i] != b.points[i]) {
/*  70: 70 */        return false;
/*  71:    */      }
/*  72:    */    }
/*  73:    */    
/*  74: 74 */    return true;
/*  75:    */  }
/*  76:    */  
/*  82:    */  public void setMorphTime(float time)
/*  83:    */  {
/*  84: 84 */    int p = (int)time;
/*  85: 85 */    int n = p + 1;
/*  86: 86 */    float offset = time - p;
/*  87:    */    
/*  88: 88 */    p = rational(p);
/*  89: 89 */    n = rational(n);
/*  90:    */    
/*  91: 91 */    setFrame(p, n, offset);
/*  92:    */  }
/*  93:    */  
/*  98:    */  public void updateMorphTime(float delta)
/*  99:    */  {
/* 100:100 */    this.offset += delta;
/* 101:101 */    if (this.offset < 0.0F) {
/* 102:102 */      int index = this.shapes.indexOf(this.current);
/* 103:103 */      if (index < 0) {
/* 104:104 */        index = this.shapes.size() - 1;
/* 105:    */      }
/* 106:    */      
/* 107:107 */      int nframe = rational(index + 1);
/* 108:108 */      setFrame(index, nframe, this.offset);
/* 109:109 */      this.offset += 1.0F;
/* 110:110 */    } else if (this.offset > 1.0F) {
/* 111:111 */      int index = this.shapes.indexOf(this.next);
/* 112:112 */      if (index < 1) {
/* 113:113 */        index = 0;
/* 114:    */      }
/* 115:    */      
/* 116:116 */      int nframe = rational(index + 1);
/* 117:117 */      setFrame(index, nframe, this.offset);
/* 118:118 */      this.offset -= 1.0F;
/* 119:    */    } else {
/* 120:120 */      this.pointsDirty = true;
/* 121:    */    }
/* 122:    */  }
/* 123:    */  
/* 128:    */  public void setExternalFrame(Shape current)
/* 129:    */  {
/* 130:130 */    this.current = current;
/* 131:131 */    this.next = ((Shape)this.shapes.get(0));
/* 132:132 */    this.offset = 0.0F;
/* 133:    */  }
/* 134:    */  
/* 140:    */  private int rational(int n)
/* 141:    */  {
/* 142:142 */    while (n >= this.shapes.size()) {
/* 143:143 */      n -= this.shapes.size();
/* 144:    */    }
/* 145:145 */    while (n < 0) {
/* 146:146 */      n += this.shapes.size();
/* 147:    */    }
/* 148:    */    
/* 149:149 */    return n;
/* 150:    */  }
/* 151:    */  
/* 158:    */  private void setFrame(int a, int b, float offset)
/* 159:    */  {
/* 160:160 */    this.current = ((Shape)this.shapes.get(a));
/* 161:161 */    this.next = ((Shape)this.shapes.get(b));
/* 162:162 */    this.offset = offset;
/* 163:163 */    this.pointsDirty = true;
/* 164:    */  }
/* 165:    */  
/* 168:    */  protected void createPoints()
/* 169:    */  {
/* 170:170 */    if (this.current == this.next) {
/* 171:171 */      System.arraycopy(this.current.points, 0, this.points, 0, this.points.length);
/* 172:172 */      return;
/* 173:    */    }
/* 174:    */    
/* 175:175 */    float[] apoints = this.current.points;
/* 176:176 */    float[] bpoints = this.next.points;
/* 177:    */    
/* 178:178 */    for (int i = 0; i < this.points.length; i++) {
/* 179:179 */      this.points[i] = (apoints[i] * (1.0F - this.offset));
/* 180:180 */      this.points[i] += bpoints[i] * this.offset;
/* 181:    */    }
/* 182:    */  }
/* 183:    */  
/* 186:    */  public Shape transform(Transform transform)
/* 187:    */  {
/* 188:188 */    createPoints();
/* 189:189 */    Polygon poly = new Polygon(this.points);
/* 190:    */    
/* 191:191 */    return poly;
/* 192:    */  }
/* 193:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.geom.MorphShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */