/*   1:    */package org.newdawn.slick.svg;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import org.newdawn.slick.Color;
/*   5:    */import org.newdawn.slick.Image;
/*   6:    */import org.newdawn.slick.ImageBuffer;
/*   7:    */import org.newdawn.slick.geom.Transform;
/*   8:    */
/*  16:    */public class Gradient
/*  17:    */{
/*  18:    */  private String name;
/*  19: 19 */  private ArrayList steps = new ArrayList();
/*  20:    */  
/*  22:    */  private float x1;
/*  23:    */  
/*  25:    */  private float x2;
/*  26:    */  
/*  28:    */  private float y1;
/*  29:    */  
/*  31:    */  private float y2;
/*  32:    */  
/*  33:    */  private float r;
/*  34:    */  
/*  35:    */  private Image image;
/*  36:    */  
/*  37:    */  private boolean radial;
/*  38:    */  
/*  39:    */  private Transform transform;
/*  40:    */  
/*  41:    */  private String ref;
/*  42:    */  
/*  44:    */  public Gradient(String name, boolean radial)
/*  45:    */  {
/*  46: 46 */    this.name = name;
/*  47: 47 */    this.radial = radial;
/*  48:    */  }
/*  49:    */  
/*  54:    */  public boolean isRadial()
/*  55:    */  {
/*  56: 56 */    return this.radial;
/*  57:    */  }
/*  58:    */  
/*  63:    */  public void setTransform(Transform trans)
/*  64:    */  {
/*  65: 65 */    this.transform = trans;
/*  66:    */  }
/*  67:    */  
/*  72:    */  public Transform getTransform()
/*  73:    */  {
/*  74: 74 */    return this.transform;
/*  75:    */  }
/*  76:    */  
/*  81:    */  public void reference(String ref)
/*  82:    */  {
/*  83: 83 */    this.ref = ref;
/*  84:    */  }
/*  85:    */  
/*  90:    */  public void resolve(Diagram diagram)
/*  91:    */  {
/*  92: 92 */    if (this.ref == null) {
/*  93: 93 */      return;
/*  94:    */    }
/*  95:    */    
/*  96: 96 */    Gradient other = diagram.getGradient(this.ref);
/*  97:    */    
/*  98: 98 */    for (int i = 0; i < other.steps.size(); i++) {
/*  99: 99 */      this.steps.add(other.steps.get(i));
/* 100:    */    }
/* 101:    */  }
/* 102:    */  
/* 105:    */  public void genImage()
/* 106:    */  {
/* 107:107 */    if (this.image == null) {
/* 108:108 */      ImageBuffer buffer = new ImageBuffer(128, 16);
/* 109:109 */      for (int i = 0; i < 128; i++) {
/* 110:110 */        Color col = getColorAt(i / 128.0F);
/* 111:111 */        for (int j = 0; j < 16; j++) {
/* 112:112 */          buffer.setRGBA(i, j, col.getRedByte(), col.getGreenByte(), col.getBlueByte(), col.getAlphaByte());
/* 113:    */        }
/* 114:    */      }
/* 115:115 */      this.image = buffer.getImage();
/* 116:    */    }
/* 117:    */  }
/* 118:    */  
/* 123:    */  public Image getImage()
/* 124:    */  {
/* 125:125 */    genImage();
/* 126:    */    
/* 127:127 */    return this.image;
/* 128:    */  }
/* 129:    */  
/* 134:    */  public void setR(float r)
/* 135:    */  {
/* 136:136 */    this.r = r;
/* 137:    */  }
/* 138:    */  
/* 143:    */  public void setX1(float x1)
/* 144:    */  {
/* 145:145 */    this.x1 = x1;
/* 146:    */  }
/* 147:    */  
/* 152:    */  public void setX2(float x2)
/* 153:    */  {
/* 154:154 */    this.x2 = x2;
/* 155:    */  }
/* 156:    */  
/* 161:    */  public void setY1(float y1)
/* 162:    */  {
/* 163:163 */    this.y1 = y1;
/* 164:    */  }
/* 165:    */  
/* 170:    */  public void setY2(float y2)
/* 171:    */  {
/* 172:172 */    this.y2 = y2;
/* 173:    */  }
/* 174:    */  
/* 179:    */  public float getR()
/* 180:    */  {
/* 181:181 */    return this.r;
/* 182:    */  }
/* 183:    */  
/* 188:    */  public float getX1()
/* 189:    */  {
/* 190:190 */    return this.x1;
/* 191:    */  }
/* 192:    */  
/* 197:    */  public float getX2()
/* 198:    */  {
/* 199:199 */    return this.x2;
/* 200:    */  }
/* 201:    */  
/* 206:    */  public float getY1()
/* 207:    */  {
/* 208:208 */    return this.y1;
/* 209:    */  }
/* 210:    */  
/* 215:    */  public float getY2()
/* 216:    */  {
/* 217:217 */    return this.y2;
/* 218:    */  }
/* 219:    */  
/* 225:    */  public void addStep(float location, Color c)
/* 226:    */  {
/* 227:227 */    this.steps.add(new Step(location, c));
/* 228:    */  }
/* 229:    */  
/* 235:    */  public Color getColorAt(float p)
/* 236:    */  {
/* 237:237 */    if (p <= 0.0F) {
/* 238:238 */      return ((Step)this.steps.get(0)).col;
/* 239:    */    }
/* 240:240 */    if (p > 1.0F) {
/* 241:241 */      return ((Step)this.steps.get(this.steps.size() - 1)).col;
/* 242:    */    }
/* 243:    */    
/* 244:244 */    for (int i = 1; i < this.steps.size(); i++) {
/* 245:245 */      Step prev = (Step)this.steps.get(i - 1);
/* 246:246 */      Step current = (Step)this.steps.get(i);
/* 247:    */      
/* 248:248 */      if (p <= current.location) {
/* 249:249 */        float dis = current.location - prev.location;
/* 250:250 */        p -= prev.location;
/* 251:251 */        float v = p / dis;
/* 252:    */        
/* 253:253 */        Color c = new Color(1, 1, 1, 1);
/* 254:254 */        c.a = (prev.col.a * (1.0F - v) + current.col.a * v);
/* 255:255 */        c.r = (prev.col.r * (1.0F - v) + current.col.r * v);
/* 256:256 */        c.g = (prev.col.g * (1.0F - v) + current.col.g * v);
/* 257:257 */        c.b = (prev.col.b * (1.0F - v) + current.col.b * v);
/* 258:    */        
/* 259:259 */        return c;
/* 260:    */      }
/* 261:    */    }
/* 262:    */    
/* 264:264 */    return Color.black;
/* 265:    */  }
/* 266:    */  
/* 271:    */  private class Step
/* 272:    */  {
/* 273:    */    float location;
/* 274:    */    
/* 278:    */    Color col;
/* 279:    */    
/* 283:    */    public Step(float location, Color c)
/* 284:    */    {
/* 285:285 */      this.location = location;
/* 286:286 */      this.col = c;
/* 287:    */    }
/* 288:    */  }
/* 289:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.svg.Gradient
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */