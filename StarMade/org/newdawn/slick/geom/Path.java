/*   1:    */package org.newdawn.slick.geom;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */
/*  10:    */public class Path
/*  11:    */  extends Shape
/*  12:    */{
/*  13: 13 */  private ArrayList localPoints = new ArrayList();
/*  14:    */  
/*  15:    */  private float cx;
/*  16:    */  
/*  17:    */  private float cy;
/*  18:    */  
/*  19:    */  private boolean closed;
/*  20:    */  
/*  21: 21 */  private ArrayList holes = new ArrayList();
/*  22:    */  
/*  25:    */  private ArrayList hole;
/*  26:    */  
/*  30:    */  public Path(float sx, float sy)
/*  31:    */  {
/*  32: 32 */    this.localPoints.add(new float[] { sx, sy });
/*  33: 33 */    this.cx = sx;
/*  34: 34 */    this.cy = sy;
/*  35: 35 */    this.pointsDirty = true;
/*  36:    */  }
/*  37:    */  
/*  43:    */  public void startHole(float sx, float sy)
/*  44:    */  {
/*  45: 45 */    this.hole = new ArrayList();
/*  46: 46 */    this.holes.add(this.hole);
/*  47:    */  }
/*  48:    */  
/*  55:    */  public void lineTo(float x, float y)
/*  56:    */  {
/*  57: 57 */    if (this.hole != null) {
/*  58: 58 */      this.hole.add(new float[] { x, y });
/*  59:    */    } else {
/*  60: 60 */      this.localPoints.add(new float[] { x, y });
/*  61:    */    }
/*  62: 62 */    this.cx = x;
/*  63: 63 */    this.cy = y;
/*  64: 64 */    this.pointsDirty = true;
/*  65:    */  }
/*  66:    */  
/*  69:    */  public void close()
/*  70:    */  {
/*  71: 71 */    this.closed = true;
/*  72:    */  }
/*  73:    */  
/*  83:    */  public void curveTo(float x, float y, float cx1, float cy1, float cx2, float cy2)
/*  84:    */  {
/*  85: 85 */    curveTo(x, y, cx1, cy1, cx2, cy2, 10);
/*  86:    */  }
/*  87:    */  
/*  99:    */  public void curveTo(float x, float y, float cx1, float cy1, float cx2, float cy2, int segments)
/* 100:    */  {
/* 101:101 */    if ((this.cx == x) && (this.cy == y)) {
/* 102:102 */      return;
/* 103:    */    }
/* 104:    */    
/* 105:105 */    Curve curve = new Curve(new Vector2f(this.cx, this.cy), new Vector2f(cx1, cy1), new Vector2f(cx2, cy2), new Vector2f(x, y));
/* 106:106 */    float step = 1.0F / segments;
/* 107:    */    
/* 108:108 */    for (int i = 1; i < segments + 1; i++) {
/* 109:109 */      float t = i * step;
/* 110:110 */      Vector2f p = curve.pointAt(t);
/* 111:111 */      if (this.hole != null) {
/* 112:112 */        this.hole.add(new float[] { p.x, p.y });
/* 113:    */      } else {
/* 114:114 */        this.localPoints.add(new float[] { p.x, p.y });
/* 115:    */      }
/* 116:116 */      this.cx = p.x;
/* 117:117 */      this.cy = p.y;
/* 118:    */    }
/* 119:119 */    this.pointsDirty = true;
/* 120:    */  }
/* 121:    */  
/* 124:    */  protected void createPoints()
/* 125:    */  {
/* 126:126 */    this.points = new float[this.localPoints.size() * 2];
/* 127:127 */    for (int i = 0; i < this.localPoints.size(); i++) {
/* 128:128 */      float[] p = (float[])this.localPoints.get(i);
/* 129:129 */      this.points[(i * 2)] = p[0];
/* 130:130 */      this.points[(i * 2 + 1)] = p[1];
/* 131:    */    }
/* 132:    */  }
/* 133:    */  
/* 136:    */  public Shape transform(Transform transform)
/* 137:    */  {
/* 138:138 */    Path p = new Path(this.cx, this.cy);
/* 139:139 */    p.localPoints = transform(this.localPoints, transform);
/* 140:140 */    for (int i = 0; i < this.holes.size(); i++) {
/* 141:141 */      p.holes.add(transform((ArrayList)this.holes.get(i), transform));
/* 142:    */    }
/* 143:143 */    p.closed = this.closed;
/* 144:    */    
/* 145:145 */    return p;
/* 146:    */  }
/* 147:    */  
/* 154:    */  private ArrayList transform(ArrayList pts, Transform t)
/* 155:    */  {
/* 156:156 */    float[] in = new float[pts.size() * 2];
/* 157:157 */    float[] out = new float[pts.size() * 2];
/* 158:    */    
/* 159:159 */    for (int i = 0; i < pts.size(); i++) {
/* 160:160 */      in[(i * 2)] = ((float[])(float[])pts.get(i))[0];
/* 161:161 */      in[(i * 2 + 1)] = ((float[])(float[])pts.get(i))[1];
/* 162:    */    }
/* 163:163 */    t.transform(in, 0, out, 0, pts.size());
/* 164:    */    
/* 165:165 */    ArrayList outList = new ArrayList();
/* 166:166 */    for (int i = 0; i < pts.size(); i++) {
/* 167:167 */      outList.add(new float[] { out[(i * 2)], out[(i * 2 + 1)] });
/* 168:    */    }
/* 169:    */    
/* 170:170 */    return outList;
/* 171:    */  }
/* 172:    */  
/* 237:    */  public boolean closed()
/* 238:    */  {
/* 239:239 */    return this.closed;
/* 240:    */  }
/* 241:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.geom.Path
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */