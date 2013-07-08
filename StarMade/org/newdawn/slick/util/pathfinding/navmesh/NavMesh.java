/*   1:    */package org.newdawn.slick.util.pathfinding.navmesh;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */
/*  14:    */public class NavMesh
/*  15:    */{
/*  16: 16 */  private ArrayList spaces = new ArrayList();
/*  17:    */  
/*  23:    */  public NavMesh() {}
/*  24:    */  
/*  29:    */  public NavMesh(ArrayList spaces)
/*  30:    */  {
/*  31: 31 */    this.spaces.addAll(spaces);
/*  32:    */  }
/*  33:    */  
/*  38:    */  public int getSpaceCount()
/*  39:    */  {
/*  40: 40 */    return this.spaces.size();
/*  41:    */  }
/*  42:    */  
/*  48:    */  public Space getSpace(int index)
/*  49:    */  {
/*  50: 50 */    return (Space)this.spaces.get(index);
/*  51:    */  }
/*  52:    */  
/*  57:    */  public void addSpace(Space space)
/*  58:    */  {
/*  59: 59 */    this.spaces.add(space);
/*  60:    */  }
/*  61:    */  
/*  68:    */  public Space findSpace(float x, float y)
/*  69:    */  {
/*  70: 70 */    for (int i = 0; i < this.spaces.size(); i++) {
/*  71: 71 */      Space space = getSpace(i);
/*  72: 72 */      if (space.contains(x, y)) {
/*  73: 73 */        return space;
/*  74:    */      }
/*  75:    */    }
/*  76:    */    
/*  77: 77 */    return null;
/*  78:    */  }
/*  79:    */  
/*  89:    */  public NavPath findPath(float sx, float sy, float tx, float ty, boolean optimize)
/*  90:    */  {
/*  91: 91 */    Space source = findSpace(sx, sy);
/*  92: 92 */    Space target = findSpace(tx, ty);
/*  93:    */    
/*  94: 94 */    if ((source == null) || (target == null)) {
/*  95: 95 */      return null;
/*  96:    */    }
/*  97:    */    
/*  98: 98 */    for (int i = 0; i < this.spaces.size(); i++) {
/*  99: 99 */      ((Space)this.spaces.get(i)).clearCost();
/* 100:    */    }
/* 101:101 */    target.fill(source, tx, ty, 0.0F);
/* 102:102 */    if (target.getCost() == 3.4028235E+38F) {
/* 103:103 */      return null;
/* 104:    */    }
/* 105:105 */    if (source.getCost() == 3.4028235E+38F) {
/* 106:106 */      return null;
/* 107:    */    }
/* 108:    */    
/* 109:109 */    NavPath path = new NavPath();
/* 110:110 */    path.push(new Link(sx, sy, null));
/* 111:111 */    if (source.pickLowestCost(target, path)) {
/* 112:112 */      path.push(new Link(tx, ty, null));
/* 113:113 */      if (optimize) {
/* 114:114 */        optimize(path);
/* 115:    */      }
/* 116:116 */      return path;
/* 117:    */    }
/* 118:    */    
/* 119:119 */    return null;
/* 120:    */  }
/* 121:    */  
/* 131:    */  private boolean isClear(float x1, float y1, float x2, float y2, float step)
/* 132:    */  {
/* 133:133 */    float dx = x2 - x1;
/* 134:134 */    float dy = y2 - y1;
/* 135:135 */    float len = (float)Math.sqrt(dx * dx + dy * dy);
/* 136:136 */    dx *= step;
/* 137:137 */    dx /= len;
/* 138:138 */    dy *= step;
/* 139:139 */    dy /= len;
/* 140:140 */    int steps = (int)(len / step);
/* 141:    */    
/* 142:142 */    for (int i = 0; i < steps; i++) {
/* 143:143 */      float x = x1 + dx * i;
/* 144:144 */      float y = y1 + dy * i;
/* 145:    */      
/* 146:146 */      if (findSpace(x, y) == null) {
/* 147:147 */        return false;
/* 148:    */      }
/* 149:    */    }
/* 150:    */    
/* 151:151 */    return true;
/* 152:    */  }
/* 153:    */  
/* 159:    */  private void optimize(NavPath path)
/* 160:    */  {
/* 161:161 */    int pt = 0;
/* 162:    */    
/* 163:163 */    while (pt < path.length() - 2) {
/* 164:164 */      float sx = path.getX(pt);
/* 165:165 */      float sy = path.getY(pt);
/* 166:166 */      float nx = path.getX(pt + 2);
/* 167:167 */      float ny = path.getY(pt + 2);
/* 168:    */      
/* 169:169 */      if (isClear(sx, sy, nx, ny, 0.1F)) {
/* 170:170 */        path.remove(pt + 1);
/* 171:    */      } else {
/* 172:172 */        pt++;
/* 173:    */      }
/* 174:    */    }
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.pathfinding.navmesh.NavMesh
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */