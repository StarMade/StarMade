/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.InputStream;
/*   5:    */import java.io.PrintStream;
/*   6:    */import org.newdawn.slick.BasicGame;
/*   7:    */import org.newdawn.slick.Color;
/*   8:    */import org.newdawn.slick.GameContainer;
/*   9:    */import org.newdawn.slick.Graphics;
/*  10:    */import org.newdawn.slick.Input;
/*  11:    */import org.newdawn.slick.SlickException;
/*  12:    */import org.newdawn.slick.util.Bootstrap;
/*  13:    */import org.newdawn.slick.util.ResourceLoader;
/*  14:    */import org.newdawn.slick.util.pathfinding.Mover;
/*  15:    */import org.newdawn.slick.util.pathfinding.PathFindingContext;
/*  16:    */import org.newdawn.slick.util.pathfinding.TileBasedMap;
/*  17:    */import org.newdawn.slick.util.pathfinding.navmesh.Link;
/*  18:    */import org.newdawn.slick.util.pathfinding.navmesh.NavMesh;
/*  19:    */import org.newdawn.slick.util.pathfinding.navmesh.NavMeshBuilder;
/*  20:    */import org.newdawn.slick.util.pathfinding.navmesh.NavPath;
/*  21:    */import org.newdawn.slick.util.pathfinding.navmesh.Space;
/*  22:    */
/*  27:    */public class NavMeshTest
/*  28:    */  extends BasicGame
/*  29:    */  implements PathFindingContext
/*  30:    */{
/*  31:    */  private NavMesh navMesh;
/*  32:    */  private NavMeshBuilder builder;
/*  33: 33 */  private boolean showSpaces = true;
/*  34:    */  
/*  35: 35 */  private boolean showLinks = true;
/*  36:    */  
/*  38:    */  private NavPath path;
/*  39:    */  
/*  41:    */  private float sx;
/*  42:    */  
/*  43:    */  private float sy;
/*  44:    */  
/*  45:    */  private float ex;
/*  46:    */  
/*  47:    */  private float ey;
/*  48:    */  
/*  49:    */  private DataMap dataMap;
/*  50:    */  
/*  52:    */  public NavMeshTest()
/*  53:    */  {
/*  54: 54 */    super("Nav-mesh Test");
/*  55:    */  }
/*  56:    */  
/*  60:    */  public void init(GameContainer container)
/*  61:    */    throws SlickException
/*  62:    */  {
/*  63: 63 */    container.setShowFPS(false);
/*  64:    */    try
/*  65:    */    {
/*  66: 66 */      this.dataMap = new DataMap("testdata/map.dat");
/*  67:    */    } catch (IOException e) {
/*  68: 68 */      throw new SlickException("Failed to load map data", e);
/*  69:    */    }
/*  70: 70 */    this.builder = new NavMeshBuilder();
/*  71: 71 */    this.navMesh = this.builder.build(this.dataMap);
/*  72:    */    
/*  73: 73 */    System.out.println("Navmesh shapes: " + this.navMesh.getSpaceCount());
/*  74:    */  }
/*  75:    */  
/*  78:    */  public void update(GameContainer container, int delta)
/*  79:    */    throws SlickException
/*  80:    */  {
/*  81: 81 */    if (container.getInput().isKeyPressed(2)) {
/*  82: 82 */      this.showLinks = (!this.showLinks);
/*  83:    */    }
/*  84: 84 */    if (container.getInput().isKeyPressed(3)) {
/*  85: 85 */      this.showSpaces = (!this.showSpaces);
/*  86:    */    }
/*  87:    */  }
/*  88:    */  
/*  94:    */  public void render(GameContainer container, Graphics g)
/*  95:    */    throws SlickException
/*  96:    */  {
/*  97: 97 */    g.translate(50.0F, 50.0F);
/*  98: 98 */    for (int x = 0; x < 50; x++) {
/*  99: 99 */      for (int y = 0; y < 50; y++) {
/* 100:100 */        if (this.dataMap.blocked(this, x, y)) {
/* 101:101 */          g.setColor(Color.gray);
/* 102:102 */          g.fillRect(x * 10 + 1, y * 10 + 1, 8.0F, 8.0F);
/* 103:    */        }
/* 104:    */      }
/* 105:    */    }
/* 106:    */    
/* 107:107 */    if (this.showSpaces) {
/* 108:108 */      for (int i = 0; i < this.navMesh.getSpaceCount(); i++) {
/* 109:109 */        Space space = this.navMesh.getSpace(i);
/* 110:110 */        if (this.builder.clear(this.dataMap, space)) {
/* 111:111 */          g.setColor(new Color(1.0F, 1.0F, 0.0F, 0.5F));
/* 112:112 */          g.fillRect(space.getX() * 10.0F, space.getY() * 10.0F, space.getWidth() * 10.0F, space.getHeight() * 10.0F);
/* 113:    */        }
/* 114:114 */        g.setColor(Color.yellow);
/* 115:115 */        g.drawRect(space.getX() * 10.0F, space.getY() * 10.0F, space.getWidth() * 10.0F, space.getHeight() * 10.0F);
/* 116:    */        
/* 117:117 */        if (this.showLinks) {
/* 118:118 */          int links = space.getLinkCount();
/* 119:119 */          for (int j = 0; j < links; j++) {
/* 120:120 */            Link link = space.getLink(j);
/* 121:121 */            g.setColor(Color.red);
/* 122:122 */            g.fillRect(link.getX() * 10.0F - 2.0F, link.getY() * 10.0F - 2.0F, 5.0F, 5.0F);
/* 123:    */          }
/* 124:    */        }
/* 125:    */      }
/* 126:    */    }
/* 127:    */    
/* 128:128 */    if (this.path != null) {
/* 129:129 */      g.setColor(Color.white);
/* 130:130 */      for (int i = 0; i < this.path.length() - 1; i++) {
/* 131:131 */        g.drawLine(this.path.getX(i) * 10.0F, this.path.getY(i) * 10.0F, this.path.getX(i + 1) * 10.0F, this.path.getY(i + 1) * 10.0F);
/* 132:    */      }
/* 133:    */    }
/* 134:    */  }
/* 135:    */  
/* 139:    */  public Mover getMover()
/* 140:    */  {
/* 141:141 */    return null;
/* 142:    */  }
/* 143:    */  
/* 147:    */  public int getSearchDistance()
/* 148:    */  {
/* 149:149 */    return 0;
/* 150:    */  }
/* 151:    */  
/* 155:    */  public int getSourceX()
/* 156:    */  {
/* 157:157 */    return 0;
/* 158:    */  }
/* 159:    */  
/* 163:    */  public int getSourceY()
/* 164:    */  {
/* 165:165 */    return 0;
/* 166:    */  }
/* 167:    */  
/* 171:    */  public void mousePressed(int button, int x, int y)
/* 172:    */  {
/* 173:173 */    float mx = (x - 50) / 10.0F;
/* 174:174 */    float my = (y - 50) / 10.0F;
/* 175:    */    
/* 176:176 */    if (button == 0) {
/* 177:177 */      this.sx = mx;
/* 178:178 */      this.sy = my;
/* 179:    */    } else {
/* 180:180 */      this.ex = mx;
/* 181:181 */      this.ey = my;
/* 182:    */    }
/* 183:    */    
/* 184:184 */    this.path = this.navMesh.findPath(this.sx, this.sy, this.ex, this.ey, true);
/* 185:    */  }
/* 186:    */  
/* 191:    */  private class DataMap
/* 192:    */    implements TileBasedMap
/* 193:    */  {
/* 194:194 */    private byte[] map = new byte[2500];
/* 195:    */    
/* 200:    */    public DataMap(String ref)
/* 201:    */      throws IOException
/* 202:    */    {
/* 203:203 */      ResourceLoader.getResourceAsStream(ref).read(this.map);
/* 204:    */    }
/* 205:    */    
/* 209:    */    public boolean blocked(PathFindingContext context, int tx, int ty)
/* 210:    */    {
/* 211:211 */      if ((tx < 0) || (ty < 0) || (tx >= 50) || (ty >= 50)) {
/* 212:212 */        return false;
/* 213:    */      }
/* 214:    */      
/* 215:215 */      return this.map[(tx + ty * 50)] != 0;
/* 216:    */    }
/* 217:    */    
/* 221:    */    public float getCost(PathFindingContext context, int tx, int ty)
/* 222:    */    {
/* 223:223 */      return 1.0F;
/* 224:    */    }
/* 225:    */    
/* 229:    */    public int getHeightInTiles()
/* 230:    */    {
/* 231:231 */      return 50;
/* 232:    */    }
/* 233:    */    
/* 237:    */    public int getWidthInTiles()
/* 238:    */    {
/* 239:239 */      return 50;
/* 240:    */    }
/* 241:    */    
/* 247:    */    public void pathFinderVisited(int x, int y) {}
/* 248:    */  }
/* 249:    */  
/* 254:    */  public static void main(String[] argv)
/* 255:    */  {
/* 256:256 */    Bootstrap.runAsApplication(new NavMeshTest(), 600, 600, false);
/* 257:    */  }
/* 258:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.NavMeshTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */