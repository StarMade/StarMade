/*   1:    */package org.newdawn.slick.opengl.renderer;
/*   2:    */
/*   7:    */public class QuadBasedLineStripRenderer
/*   8:    */  implements LineStripRenderer
/*   9:    */{
/*  10: 10 */  private SGL GL = Renderer.get();
/*  11:    */  
/*  13: 13 */  public static int MAX_POINTS = 10000;
/*  14:    */  
/*  15:    */  private boolean antialias;
/*  16:    */  
/*  17: 17 */  private float width = 1.0F;
/*  18:    */  
/*  20:    */  private float[] points;
/*  21:    */  
/*  22:    */  private float[] colours;
/*  23:    */  
/*  24:    */  private int pts;
/*  25:    */  
/*  26:    */  private int cpt;
/*  27:    */  
/*  28: 28 */  private DefaultLineStripRenderer def = new DefaultLineStripRenderer();
/*  29:    */  
/*  31:    */  private boolean renderHalf;
/*  32:    */  
/*  33: 33 */  private boolean lineCaps = false;
/*  34:    */  
/*  37:    */  public QuadBasedLineStripRenderer()
/*  38:    */  {
/*  39: 39 */    this.points = new float[MAX_POINTS * 2];
/*  40: 40 */    this.colours = new float[MAX_POINTS * 4];
/*  41:    */  }
/*  42:    */  
/*  47:    */  public void setLineCaps(boolean caps)
/*  48:    */  {
/*  49: 49 */    this.lineCaps = caps;
/*  50:    */  }
/*  51:    */  
/*  54:    */  public void start()
/*  55:    */  {
/*  56: 56 */    if (this.width == 1.0F) {
/*  57: 57 */      this.def.start();
/*  58: 58 */      return;
/*  59:    */    }
/*  60:    */    
/*  61: 61 */    this.pts = 0;
/*  62: 62 */    this.cpt = 0;
/*  63: 63 */    this.GL.flush();
/*  64:    */    
/*  65: 65 */    float[] col = this.GL.getCurrentColor();
/*  66: 66 */    color(col[0], col[1], col[2], col[3]);
/*  67:    */  }
/*  68:    */  
/*  71:    */  public void end()
/*  72:    */  {
/*  73: 73 */    if (this.width == 1.0F) {
/*  74: 74 */      this.def.end();
/*  75: 75 */      return;
/*  76:    */    }
/*  77:    */    
/*  78: 78 */    renderLines(this.points, this.pts);
/*  79:    */  }
/*  80:    */  
/*  83:    */  public void vertex(float x, float y)
/*  84:    */  {
/*  85: 85 */    if (this.width == 1.0F) {
/*  86: 86 */      this.def.vertex(x, y);
/*  87: 87 */      return;
/*  88:    */    }
/*  89:    */    
/*  90: 90 */    this.points[(this.pts * 2)] = x;
/*  91: 91 */    this.points[(this.pts * 2 + 1)] = y;
/*  92: 92 */    this.pts += 1;
/*  93:    */    
/*  94: 94 */    int index = this.pts - 1;
/*  95: 95 */    color(this.colours[(index * 4)], this.colours[(index * 4 + 1)], this.colours[(index * 4 + 2)], this.colours[(index * 4 + 3)]);
/*  96:    */  }
/*  97:    */  
/* 100:    */  public void setWidth(float width)
/* 101:    */  {
/* 102:102 */    this.width = width;
/* 103:    */  }
/* 104:    */  
/* 107:    */  public void setAntiAlias(boolean antialias)
/* 108:    */  {
/* 109:109 */    this.def.setAntiAlias(antialias);
/* 110:110 */    this.antialias = antialias;
/* 111:    */  }
/* 112:    */  
/* 118:    */  public void renderLines(float[] points, int count)
/* 119:    */  {
/* 120:120 */    if (this.antialias) {
/* 121:121 */      this.GL.glEnable(2881);
/* 122:122 */      renderLinesImpl(points, count, this.width + 1.0F);
/* 123:    */    }
/* 124:    */    
/* 125:125 */    this.GL.glDisable(2881);
/* 126:126 */    renderLinesImpl(points, count, this.width);
/* 127:    */    
/* 128:128 */    if (this.antialias) {
/* 129:129 */      this.GL.glEnable(2881);
/* 130:    */    }
/* 131:    */  }
/* 132:    */  
/* 139:    */  public void renderLinesImpl(float[] points, int count, float w)
/* 140:    */  {
/* 141:141 */    float width = w / 2.0F;
/* 142:    */    
/* 143:143 */    float lastx1 = 0.0F;
/* 144:144 */    float lasty1 = 0.0F;
/* 145:145 */    float lastx2 = 0.0F;
/* 146:146 */    float lasty2 = 0.0F;
/* 147:    */    
/* 148:148 */    this.GL.glBegin(7);
/* 149:149 */    for (int i = 0; i < count + 1; i++) {
/* 150:150 */      int current = i;
/* 151:151 */      int next = i + 1;
/* 152:152 */      int prev = i - 1;
/* 153:153 */      if (prev < 0) {
/* 154:154 */        prev += count;
/* 155:    */      }
/* 156:156 */      if (next >= count) {
/* 157:157 */        next -= count;
/* 158:    */      }
/* 159:159 */      if (current >= count) {
/* 160:160 */        current -= count;
/* 161:    */      }
/* 162:    */      
/* 163:163 */      float x1 = points[(current * 2)];
/* 164:164 */      float y1 = points[(current * 2 + 1)];
/* 165:165 */      float x2 = points[(next * 2)];
/* 166:166 */      float y2 = points[(next * 2 + 1)];
/* 167:    */      
/* 169:169 */      float dx = x2 - x1;
/* 170:170 */      float dy = y2 - y1;
/* 171:    */      
/* 172:172 */      if ((dx != 0.0F) || (dy != 0.0F))
/* 173:    */      {
/* 176:176 */        float d2 = dx * dx + dy * dy;
/* 177:177 */        float d = (float)Math.sqrt(d2);
/* 178:178 */        dx *= width;
/* 179:179 */        dy *= width;
/* 180:180 */        dx /= d;
/* 181:181 */        dy /= d;
/* 182:    */        
/* 183:183 */        float tx = dy;
/* 184:184 */        float ty = -dx;
/* 185:    */        
/* 186:186 */        if (i != 0) {
/* 187:187 */          bindColor(prev);
/* 188:188 */          this.GL.glVertex3f(lastx1, lasty1, 0.0F);
/* 189:189 */          this.GL.glVertex3f(lastx2, lasty2, 0.0F);
/* 190:190 */          bindColor(current);
/* 191:191 */          this.GL.glVertex3f(x1 + tx, y1 + ty, 0.0F);
/* 192:192 */          this.GL.glVertex3f(x1 - tx, y1 - ty, 0.0F);
/* 193:    */        }
/* 194:    */        
/* 195:195 */        lastx1 = x2 - tx;
/* 196:196 */        lasty1 = y2 - ty;
/* 197:197 */        lastx2 = x2 + tx;
/* 198:198 */        lasty2 = y2 + ty;
/* 199:    */        
/* 200:200 */        if (i < count - 1) {
/* 201:201 */          bindColor(current);
/* 202:202 */          this.GL.glVertex3f(x1 + tx, y1 + ty, 0.0F);
/* 203:203 */          this.GL.glVertex3f(x1 - tx, y1 - ty, 0.0F);
/* 204:204 */          bindColor(next);
/* 205:205 */          this.GL.glVertex3f(x2 - tx, y2 - ty, 0.0F);
/* 206:206 */          this.GL.glVertex3f(x2 + tx, y2 + ty, 0.0F);
/* 207:    */        }
/* 208:    */      }
/* 209:    */    }
/* 210:210 */    this.GL.glEnd();
/* 211:    */    
/* 212:212 */    float step = width <= 12.5F ? 5.0F : 180.0F / (float)Math.ceil(width / 2.5D);
/* 213:    */    
/* 215:215 */    if (this.lineCaps) {
/* 216:216 */      float dx = points[2] - points[0];
/* 217:217 */      float dy = points[3] - points[1];
/* 218:218 */      float fang = (float)Math.toDegrees(Math.atan2(dy, dx)) + 90.0F;
/* 219:    */      
/* 220:220 */      if ((dx != 0.0F) || (dy != 0.0F)) {
/* 221:221 */        this.GL.glBegin(6);
/* 222:222 */        bindColor(0);
/* 223:223 */        this.GL.glVertex2f(points[0], points[1]);
/* 224:224 */        for (int i = 0; i < 180.0F + step; i = (int)(i + step)) {
/* 225:225 */          float ang = (float)Math.toRadians(fang + i);
/* 226:226 */          this.GL.glVertex2f(points[0] + (float)(Math.cos(ang) * width), points[1] + (float)(Math.sin(ang) * width));
/* 227:    */        }
/* 228:    */        
/* 229:229 */        this.GL.glEnd();
/* 230:    */      }
/* 231:    */    }
/* 232:    */    
/* 234:234 */    if (this.lineCaps) {
/* 235:235 */      float dx = points[(count * 2 - 2)] - points[(count * 2 - 4)];
/* 236:236 */      float dy = points[(count * 2 - 1)] - points[(count * 2 - 3)];
/* 237:237 */      float fang = (float)Math.toDegrees(Math.atan2(dy, dx)) - 90.0F;
/* 238:    */      
/* 239:239 */      if ((dx != 0.0F) || (dy != 0.0F)) {
/* 240:240 */        this.GL.glBegin(6);
/* 241:241 */        bindColor(count - 1);
/* 242:242 */        this.GL.glVertex2f(points[(count * 2 - 2)], points[(count * 2 - 1)]);
/* 243:243 */        for (int i = 0; i < 180.0F + step; i = (int)(i + step)) {
/* 244:244 */          float ang = (float)Math.toRadians(fang + i);
/* 245:245 */          this.GL.glVertex2f(points[(count * 2 - 2)] + (float)(Math.cos(ang) * width), points[(count * 2 - 1)] + (float)(Math.sin(ang) * width));
/* 246:    */        }
/* 247:    */        
/* 248:248 */        this.GL.glEnd();
/* 249:    */      }
/* 250:    */    }
/* 251:    */  }
/* 252:    */  
/* 257:    */  private void bindColor(int index)
/* 258:    */  {
/* 259:259 */    if (index < this.cpt) {
/* 260:260 */      if (this.renderHalf) {
/* 261:261 */        this.GL.glColor4f(this.colours[(index * 4)] * 0.5F, this.colours[(index * 4 + 1)] * 0.5F, this.colours[(index * 4 + 2)] * 0.5F, this.colours[(index * 4 + 3)] * 0.5F);
/* 262:    */      }
/* 263:    */      else {
/* 264:264 */        this.GL.glColor4f(this.colours[(index * 4)], this.colours[(index * 4 + 1)], this.colours[(index * 4 + 2)], this.colours[(index * 4 + 3)]);
/* 265:    */      }
/* 266:    */    }
/* 267:    */  }
/* 268:    */  
/* 272:    */  public void color(float r, float g, float b, float a)
/* 273:    */  {
/* 274:274 */    if (this.width == 1.0F) {
/* 275:275 */      this.def.color(r, g, b, a);
/* 276:276 */      return;
/* 277:    */    }
/* 278:    */    
/* 279:279 */    this.colours[(this.pts * 4)] = r;
/* 280:280 */    this.colours[(this.pts * 4 + 1)] = g;
/* 281:281 */    this.colours[(this.pts * 4 + 2)] = b;
/* 282:282 */    this.colours[(this.pts * 4 + 3)] = a;
/* 283:283 */    this.cpt += 1;
/* 284:    */  }
/* 285:    */  
/* 286:    */  public boolean applyGLLineFixes() {
/* 287:287 */    if (this.width == 1.0F) {
/* 288:288 */      return this.def.applyGLLineFixes();
/* 289:    */    }
/* 290:    */    
/* 291:291 */    return this.def.applyGLLineFixes();
/* 292:    */  }
/* 293:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.renderer.QuadBasedLineStripRenderer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */