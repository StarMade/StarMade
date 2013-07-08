/*   1:    */package org.newdawn.slick.util.pathfinding.navmesh;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.HashMap;
/*   5:    */
/*  16:    */public class Space
/*  17:    */{
/*  18:    */  private float x;
/*  19:    */  private float y;
/*  20:    */  private float width;
/*  21:    */  private float height;
/*  22: 22 */  private HashMap links = new HashMap();
/*  23:    */  
/*  24: 24 */  private ArrayList linksList = new ArrayList();
/*  25:    */  
/*  29:    */  private float cost;
/*  30:    */  
/*  35:    */  public Space(float x, float y, float width, float height)
/*  36:    */  {
/*  37: 37 */    this.x = x;
/*  38: 38 */    this.y = y;
/*  39: 39 */    this.width = width;
/*  40: 40 */    this.height = height;
/*  41:    */  }
/*  42:    */  
/*  47:    */  public float getWidth()
/*  48:    */  {
/*  49: 49 */    return this.width;
/*  50:    */  }
/*  51:    */  
/*  56:    */  public float getHeight()
/*  57:    */  {
/*  58: 58 */    return this.height;
/*  59:    */  }
/*  60:    */  
/*  65:    */  public float getX()
/*  66:    */  {
/*  67: 67 */    return this.x;
/*  68:    */  }
/*  69:    */  
/*  74:    */  public float getY()
/*  75:    */  {
/*  76: 76 */    return this.y;
/*  77:    */  }
/*  78:    */  
/*  85:    */  public void link(Space other)
/*  86:    */  {
/*  87: 87 */    if ((inTolerance(this.x, other.x + other.width)) || (inTolerance(this.x + this.width, other.x))) {
/*  88: 88 */      float linkx = this.x;
/*  89: 89 */      if (this.x + this.width == other.x) {
/*  90: 90 */        linkx = this.x + this.width;
/*  91:    */      }
/*  92:    */      
/*  93: 93 */      float top = Math.max(this.y, other.y);
/*  94: 94 */      float bottom = Math.min(this.y + this.height, other.y + other.height);
/*  95: 95 */      float linky = top + (bottom - top) / 2.0F;
/*  96:    */      
/*  97: 97 */      Link link = new Link(linkx, linky, other);
/*  98: 98 */      this.links.put(other, link);
/*  99: 99 */      this.linksList.add(link);
/* 100:    */    }
/* 101:    */    
/* 102:102 */    if ((inTolerance(this.y, other.y + other.height)) || (inTolerance(this.y + this.height, other.y))) {
/* 103:103 */      float linky = this.y;
/* 104:104 */      if (this.y + this.height == other.y) {
/* 105:105 */        linky = this.y + this.height;
/* 106:    */      }
/* 107:    */      
/* 108:108 */      float left = Math.max(this.x, other.x);
/* 109:109 */      float right = Math.min(this.x + this.width, other.x + other.width);
/* 110:110 */      float linkx = left + (right - left) / 2.0F;
/* 111:    */      
/* 112:112 */      Link link = new Link(linkx, linky, other);
/* 113:113 */      this.links.put(other, link);
/* 114:114 */      this.linksList.add(link);
/* 115:    */    }
/* 116:    */  }
/* 117:    */  
/* 125:    */  private boolean inTolerance(float a, float b)
/* 126:    */  {
/* 127:127 */    return a == b;
/* 128:    */  }
/* 129:    */  
/* 136:    */  public boolean hasJoinedEdge(Space other)
/* 137:    */  {
/* 138:138 */    if ((inTolerance(this.x, other.x + other.width)) || (inTolerance(this.x + this.width, other.x))) {
/* 139:139 */      if ((this.y >= other.y) && (this.y <= other.y + other.height)) {
/* 140:140 */        return true;
/* 141:    */      }
/* 142:142 */      if ((this.y + this.height >= other.y) && (this.y + this.height <= other.y + other.height)) {
/* 143:143 */        return true;
/* 144:    */      }
/* 145:145 */      if ((other.y >= this.y) && (other.y <= this.y + this.height)) {
/* 146:146 */        return true;
/* 147:    */      }
/* 148:148 */      if ((other.y + other.height >= this.y) && (other.y + other.height <= this.y + this.height)) {
/* 149:149 */        return true;
/* 150:    */      }
/* 151:    */    }
/* 152:    */    
/* 153:153 */    if ((inTolerance(this.y, other.y + other.height)) || (inTolerance(this.y + this.height, other.y))) {
/* 154:154 */      if ((this.x >= other.x) && (this.x <= other.x + other.width)) {
/* 155:155 */        return true;
/* 156:    */      }
/* 157:157 */      if ((this.x + this.width >= other.x) && (this.x + this.width <= other.x + other.width)) {
/* 158:158 */        return true;
/* 159:    */      }
/* 160:160 */      if ((other.x >= this.x) && (other.x <= this.x + this.width)) {
/* 161:161 */        return true;
/* 162:    */      }
/* 163:163 */      if ((other.x + other.width >= this.x) && (other.x + other.width <= this.x + this.width)) {
/* 164:164 */        return true;
/* 165:    */      }
/* 166:    */    }
/* 167:    */    
/* 168:168 */    return false;
/* 169:    */  }
/* 170:    */  
/* 176:    */  public Space merge(Space other)
/* 177:    */  {
/* 178:178 */    float minx = Math.min(this.x, other.x);
/* 179:179 */    float miny = Math.min(this.y, other.y);
/* 180:    */    
/* 181:181 */    float newwidth = this.width + other.width;
/* 182:182 */    float newheight = this.height + other.height;
/* 183:183 */    if (this.x == other.x) {
/* 184:184 */      newwidth = this.width;
/* 185:    */    } else {
/* 186:186 */      newheight = this.height;
/* 187:    */    }
/* 188:188 */    return new Space(minx, miny, newwidth, newheight);
/* 189:    */  }
/* 190:    */  
/* 197:    */  public boolean canMerge(Space other)
/* 198:    */  {
/* 199:199 */    if (!hasJoinedEdge(other)) {
/* 200:200 */      return false;
/* 201:    */    }
/* 202:    */    
/* 203:203 */    if ((this.x == other.x) && (this.width == other.width)) {
/* 204:204 */      return true;
/* 205:    */    }
/* 206:206 */    if ((this.y == other.y) && (this.height == other.height)) {
/* 207:207 */      return true;
/* 208:    */    }
/* 209:    */    
/* 210:210 */    return false;
/* 211:    */  }
/* 212:    */  
/* 217:    */  public int getLinkCount()
/* 218:    */  {
/* 219:219 */    return this.linksList.size();
/* 220:    */  }
/* 221:    */  
/* 227:    */  public Link getLink(int index)
/* 228:    */  {
/* 229:229 */    return (Link)this.linksList.get(index);
/* 230:    */  }
/* 231:    */  
/* 238:    */  public boolean contains(float xp, float yp)
/* 239:    */  {
/* 240:240 */    return (xp >= this.x) && (xp < this.x + this.width) && (yp >= this.y) && (yp < this.y + this.height);
/* 241:    */  }
/* 242:    */  
/* 250:    */  public void fill(Space target, float sx, float sy, float cost)
/* 251:    */  {
/* 252:252 */    if (cost >= this.cost) {
/* 253:253 */      return;
/* 254:    */    }
/* 255:255 */    this.cost = cost;
/* 256:256 */    if (target == this) {
/* 257:257 */      return;
/* 258:    */    }
/* 259:    */    
/* 260:260 */    for (int i = 0; i < getLinkCount(); i++) {
/* 261:261 */      Link link = getLink(i);
/* 262:262 */      float extraCost = link.distance2(sx, sy);
/* 263:263 */      float nextCost = cost + extraCost;
/* 264:264 */      link.getTarget().fill(target, link.getX(), link.getY(), nextCost);
/* 265:    */    }
/* 266:    */  }
/* 267:    */  
/* 270:    */  public void clearCost()
/* 271:    */  {
/* 272:272 */    this.cost = 3.4028235E+38F;
/* 273:    */  }
/* 274:    */  
/* 279:    */  public float getCost()
/* 280:    */  {
/* 281:281 */    return this.cost;
/* 282:    */  }
/* 283:    */  
/* 290:    */  public boolean pickLowestCost(Space target, NavPath path)
/* 291:    */  {
/* 292:292 */    if (target == this) {
/* 293:293 */      return true;
/* 294:    */    }
/* 295:295 */    if (this.links.size() == 0) {
/* 296:296 */      return false;
/* 297:    */    }
/* 298:    */    
/* 299:299 */    Link bestLink = null;
/* 300:300 */    for (int i = 0; i < getLinkCount(); i++) {
/* 301:301 */      Link link = getLink(i);
/* 302:302 */      if ((bestLink == null) || (link.getTarget().getCost() < bestLink.getTarget().getCost())) {
/* 303:303 */        bestLink = link;
/* 304:    */      }
/* 305:    */    }
/* 306:    */    
/* 307:307 */    path.push(bestLink);
/* 308:308 */    return bestLink.getTarget().pickLowestCost(target, path);
/* 309:    */  }
/* 310:    */  
/* 315:    */  public String toString()
/* 316:    */  {
/* 317:317 */    return "[Space " + this.x + "," + this.y + " " + this.width + "," + this.height + "]";
/* 318:    */  }
/* 319:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.pathfinding.navmesh.Space
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */