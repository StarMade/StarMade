/*   1:    */package org.newdawn.slick.util.pathfinding.navmesh;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import org.newdawn.slick.util.pathfinding.Mover;
/*   5:    */import org.newdawn.slick.util.pathfinding.PathFindingContext;
/*   6:    */import org.newdawn.slick.util.pathfinding.TileBasedMap;
/*   7:    */
/*  16:    */public class NavMeshBuilder
/*  17:    */  implements PathFindingContext
/*  18:    */{
/*  19:    */  private int sx;
/*  20:    */  private int sy;
/*  21: 21 */  private float smallestSpace = 0.2F;
/*  22:    */  
/*  26:    */  private boolean tileBased;
/*  27:    */  
/*  31:    */  public NavMesh build(TileBasedMap map)
/*  32:    */  {
/*  33: 33 */    return build(map, true);
/*  34:    */  }
/*  35:    */  
/*  43:    */  public NavMesh build(TileBasedMap map, boolean tileBased)
/*  44:    */  {
/*  45: 45 */    this.tileBased = tileBased;
/*  46:    */    
/*  47: 47 */    ArrayList spaces = new ArrayList();
/*  48:    */    Space space;
/*  49: 49 */    if (tileBased) {
/*  50: 50 */      for (int x = 0; x < map.getWidthInTiles(); x++) {
/*  51: 51 */        for (int y = 0; y < map.getHeightInTiles(); y++) {
/*  52: 52 */          if (!map.blocked(this, x, y)) {
/*  53: 53 */            spaces.add(new Space(x, y, 1.0F, 1.0F));
/*  54:    */          }
/*  55:    */        }
/*  56:    */      }
/*  57:    */    } else {
/*  58: 58 */      space = new Space(0.0F, 0.0F, map.getWidthInTiles(), map.getHeightInTiles());
/*  59:    */    }
/*  60:    */    
/*  63: 63 */    while (mergeSpaces(spaces)) {}
/*  64: 64 */    linkSpaces(spaces);
/*  65:    */    
/*  66: 66 */    return new NavMesh(spaces);
/*  67:    */  }
/*  68:    */  
/*  76:    */  private boolean mergeSpaces(ArrayList spaces)
/*  77:    */  {
/*  78: 78 */    for (int source = 0; source < spaces.size(); source++) {
/*  79: 79 */      Space a = (Space)spaces.get(source);
/*  80:    */      
/*  81: 81 */      for (int target = source + 1; target < spaces.size(); target++) {
/*  82: 82 */        Space b = (Space)spaces.get(target);
/*  83:    */        
/*  84: 84 */        if (a.canMerge(b)) {
/*  85: 85 */          spaces.remove(a);
/*  86: 86 */          spaces.remove(b);
/*  87: 87 */          spaces.add(a.merge(b));
/*  88: 88 */          return true;
/*  89:    */        }
/*  90:    */      }
/*  91:    */    }
/*  92:    */    
/*  93: 93 */    return false;
/*  94:    */  }
/*  95:    */  
/* 100:    */  private void linkSpaces(ArrayList spaces)
/* 101:    */  {
/* 102:102 */    for (int source = 0; source < spaces.size(); source++) {
/* 103:103 */      Space a = (Space)spaces.get(source);
/* 104:    */      
/* 105:105 */      for (int target = source + 1; target < spaces.size(); target++) {
/* 106:106 */        Space b = (Space)spaces.get(target);
/* 107:    */        
/* 108:108 */        if (a.hasJoinedEdge(b)) {
/* 109:109 */          a.link(b);
/* 110:110 */          b.link(a);
/* 111:    */        }
/* 112:    */      }
/* 113:    */    }
/* 114:    */  }
/* 115:    */  
/* 122:    */  public boolean clear(TileBasedMap map, Space space)
/* 123:    */  {
/* 124:124 */    if (this.tileBased) {
/* 125:125 */      return true;
/* 126:    */    }
/* 127:    */    
/* 128:128 */    float x = 0.0F;
/* 129:129 */    boolean donex = false;
/* 130:    */    
/* 131:131 */    while (x < space.getWidth()) {
/* 132:132 */      float y = 0.0F;
/* 133:133 */      boolean doney = false;
/* 134:    */      
/* 135:135 */      while (y < space.getHeight()) {
/* 136:136 */        this.sx = ((int)(space.getX() + x));
/* 137:137 */        this.sy = ((int)(space.getY() + y));
/* 138:    */        
/* 139:139 */        if (map.blocked(this, this.sx, this.sy)) {
/* 140:140 */          return false;
/* 141:    */        }
/* 142:    */        
/* 143:143 */        y += 0.1F;
/* 144:144 */        if ((y > space.getHeight()) && (!doney)) {
/* 145:145 */          y = space.getHeight();
/* 146:146 */          doney = true;
/* 147:    */        }
/* 148:    */      }
/* 149:    */      
/* 151:151 */      x += 0.1F;
/* 152:152 */      if ((x > space.getWidth()) && (!donex)) {
/* 153:153 */        x = space.getWidth();
/* 154:154 */        donex = true;
/* 155:    */      }
/* 156:    */    }
/* 157:    */    
/* 158:158 */    return true;
/* 159:    */  }
/* 160:    */  
/* 168:    */  private void subsection(TileBasedMap map, Space space, ArrayList spaces)
/* 169:    */  {
/* 170:170 */    if (!clear(map, space)) {
/* 171:171 */      float width2 = space.getWidth() / 2.0F;
/* 172:172 */      float height2 = space.getHeight() / 2.0F;
/* 173:    */      
/* 174:174 */      if ((width2 < this.smallestSpace) && (height2 < this.smallestSpace)) {
/* 175:175 */        return;
/* 176:    */      }
/* 177:    */      
/* 178:178 */      subsection(map, new Space(space.getX(), space.getY(), width2, height2), spaces);
/* 179:179 */      subsection(map, new Space(space.getX(), space.getY() + height2, width2, height2), spaces);
/* 180:180 */      subsection(map, new Space(space.getX() + width2, space.getY(), width2, height2), spaces);
/* 181:181 */      subsection(map, new Space(space.getX() + width2, space.getY() + height2, width2, height2), spaces);
/* 182:    */    } else {
/* 183:183 */      spaces.add(space);
/* 184:    */    }
/* 185:    */  }
/* 186:    */  
/* 191:    */  public Mover getMover()
/* 192:    */  {
/* 193:193 */    return null;
/* 194:    */  }
/* 195:    */  
/* 200:    */  public int getSearchDistance()
/* 201:    */  {
/* 202:202 */    return 0;
/* 203:    */  }
/* 204:    */  
/* 209:    */  public int getSourceX()
/* 210:    */  {
/* 211:211 */    return this.sx;
/* 212:    */  }
/* 213:    */  
/* 218:    */  public int getSourceY()
/* 219:    */  {
/* 220:220 */    return this.sy;
/* 221:    */  }
/* 222:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.pathfinding.navmesh.NavMeshBuilder
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */