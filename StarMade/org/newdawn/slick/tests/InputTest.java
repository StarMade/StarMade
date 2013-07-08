/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.util.ArrayList;
/*   5:    */import org.newdawn.slick.AppGameContainer;
/*   6:    */import org.newdawn.slick.BasicGame;
/*   7:    */import org.newdawn.slick.Color;
/*   8:    */import org.newdawn.slick.GameContainer;
/*   9:    */import org.newdawn.slick.Graphics;
/*  10:    */import org.newdawn.slick.Input;
/*  11:    */import org.newdawn.slick.SlickException;
/*  12:    */import org.newdawn.slick.util.Log;
/*  13:    */
/*  18:    */public class InputTest
/*  19:    */  extends BasicGame
/*  20:    */{
/*  21: 21 */  private String message = "Press any key, mouse button, or drag the mouse";
/*  22:    */  
/*  23: 23 */  private ArrayList lines = new ArrayList();
/*  24:    */  
/*  25:    */  private boolean buttonDown;
/*  26:    */  
/*  27:    */  private float x;
/*  28:    */  
/*  29:    */  private float y;
/*  30:    */  
/*  31: 31 */  private Color[] cols = { Color.red, Color.green, Color.blue, Color.white, Color.magenta, Color.cyan };
/*  32:    */  
/*  34:    */  private int index;
/*  35:    */  
/*  37:    */  private Input input;
/*  38:    */  
/*  39:    */  private int ypos;
/*  40:    */  
/*  41:    */  private AppGameContainer app;
/*  42:    */  
/*  43:    */  private boolean space;
/*  44:    */  
/*  45:    */  private boolean lshift;
/*  46:    */  
/*  47:    */  private boolean rshift;
/*  48:    */  
/*  50:    */  public InputTest()
/*  51:    */  {
/*  52: 52 */    super("Input Test");
/*  53:    */  }
/*  54:    */  
/*  56:    */  public void init(GameContainer container)
/*  57:    */    throws SlickException
/*  58:    */  {
/*  59: 59 */    if ((container instanceof AppGameContainer)) {
/*  60: 60 */      this.app = ((AppGameContainer)container);
/*  61:    */    }
/*  62:    */    
/*  63: 63 */    this.input = container.getInput();
/*  64: 64 */    this.x = 300.0F;
/*  65: 65 */    this.y = 300.0F;
/*  66:    */  }
/*  67:    */  
/*  70:    */  public void render(GameContainer container, Graphics g)
/*  71:    */  {
/*  72: 72 */    g.drawString("left shift down: " + this.lshift, 100.0F, 240.0F);
/*  73: 73 */    g.drawString("right shift down: " + this.rshift, 100.0F, 260.0F);
/*  74: 74 */    g.drawString("space down: " + this.space, 100.0F, 280.0F);
/*  75:    */    
/*  76: 76 */    g.setColor(Color.white);
/*  77: 77 */    g.drawString(this.message, 10.0F, 50.0F);
/*  78: 78 */    g.drawString("" + container.getInput().getMouseY(), 10.0F, 400.0F);
/*  79: 79 */    g.drawString("Use the primary gamepad to control the blob, and hit a gamepad button to change the color", 10.0F, 90.0F);
/*  80:    */    
/*  81: 81 */    for (int i = 0; i < this.lines.size(); i++) {
/*  82: 82 */      Line line = (Line)this.lines.get(i);
/*  83: 83 */      line.draw(g);
/*  84:    */    }
/*  85:    */    
/*  86: 86 */    g.setColor(this.cols[this.index]);
/*  87: 87 */    g.fillOval((int)this.x, (int)this.y, 50.0F, 50.0F);
/*  88: 88 */    g.setColor(Color.yellow);
/*  89: 89 */    g.fillRect(50.0F, 200 + this.ypos, 40.0F, 40.0F);
/*  90:    */  }
/*  91:    */  
/*  94:    */  public void update(GameContainer container, int delta)
/*  95:    */  {
/*  96: 96 */    this.lshift = container.getInput().isKeyDown(42);
/*  97: 97 */    this.rshift = container.getInput().isKeyDown(54);
/*  98: 98 */    this.space = container.getInput().isKeyDown(57);
/*  99:    */    
/* 100:100 */    if (this.controllerLeft[0] != 0) {
/* 101:101 */      this.x -= delta * 0.1F;
/* 102:    */    }
/* 103:103 */    if (this.controllerRight[0] != 0) {
/* 104:104 */      this.x += delta * 0.1F;
/* 105:    */    }
/* 106:106 */    if (this.controllerUp[0] != 0) {
/* 107:107 */      this.y -= delta * 0.1F;
/* 108:    */    }
/* 109:109 */    if (this.controllerDown[0] != 0) {
/* 110:110 */      this.y += delta * 0.1F;
/* 111:    */    }
/* 112:    */  }
/* 113:    */  
/* 116:    */  public void keyPressed(int key, char c)
/* 117:    */  {
/* 118:118 */    if (key == 1) {
/* 119:119 */      System.exit(0);
/* 120:    */    }
/* 121:121 */    if ((key == 59) && 
/* 122:122 */      (this.app != null)) {
/* 123:    */      try {
/* 124:124 */        this.app.setDisplayMode(600, 600, false);
/* 125:125 */        this.app.reinit();
/* 126:126 */      } catch (Exception e) { Log.error(e);
/* 127:    */      }
/* 128:    */    }
/* 129:    */  }
/* 130:    */  
/* 133:    */  public void keyReleased(int key, char c)
/* 134:    */  {
/* 135:135 */    this.message = ("You pressed key code " + key + " (character = " + c + ")");
/* 136:    */  }
/* 137:    */  
/* 140:    */  public void mousePressed(int button, int x, int y)
/* 141:    */  {
/* 142:142 */    if (button == 0) {
/* 143:143 */      this.buttonDown = true;
/* 144:    */    }
/* 145:    */    
/* 146:146 */    this.message = ("Mouse pressed " + button + " " + x + "," + y);
/* 147:    */  }
/* 148:    */  
/* 151:    */  public void mouseReleased(int button, int x, int y)
/* 152:    */  {
/* 153:153 */    if (button == 0) {
/* 154:154 */      this.buttonDown = false;
/* 155:    */    }
/* 156:    */    
/* 157:157 */    this.message = ("Mouse released " + button + " " + x + "," + y);
/* 158:    */  }
/* 159:    */  
/* 162:    */  public void mouseClicked(int button, int x, int y, int clickCount)
/* 163:    */  {
/* 164:164 */    System.out.println("CLICKED:" + x + "," + y + " " + clickCount);
/* 165:    */  }
/* 166:    */  
/* 169:    */  public void mouseWheelMoved(int change)
/* 170:    */  {
/* 171:171 */    this.message = ("Mouse wheel moved: " + change);
/* 172:    */    
/* 173:173 */    if (change < 0) {
/* 174:174 */      this.ypos -= 10;
/* 175:    */    }
/* 176:176 */    if (change > 0) {
/* 177:177 */      this.ypos += 10;
/* 178:    */    }
/* 179:    */  }
/* 180:    */  
/* 183:    */  public void mouseMoved(int oldx, int oldy, int newx, int newy)
/* 184:    */  {
/* 185:185 */    if (this.buttonDown) {
/* 186:186 */      this.lines.add(new Line(oldx, oldy, newx, newy));
/* 187:    */    }
/* 188:    */  }
/* 189:    */  
/* 193:    */  private class Line
/* 194:    */  {
/* 195:    */    private int oldx;
/* 196:    */    
/* 199:    */    private int oldy;
/* 200:    */    
/* 203:    */    private int newx;
/* 204:    */    
/* 207:    */    private int newy;
/* 208:    */    
/* 212:    */    public Line(int oldx, int oldy, int newx, int newy)
/* 213:    */    {
/* 214:214 */      this.oldx = oldx;
/* 215:215 */      this.oldy = oldy;
/* 216:216 */      this.newx = newx;
/* 217:217 */      this.newy = newy;
/* 218:    */    }
/* 219:    */    
/* 224:    */    public void draw(Graphics g)
/* 225:    */    {
/* 226:226 */      g.drawLine(this.oldx, this.oldy, this.newx, this.newy);
/* 227:    */    }
/* 228:    */  }
/* 229:    */  
/* 232:    */  public void controllerButtonPressed(int controller, int button)
/* 233:    */  {
/* 234:234 */    super.controllerButtonPressed(controller, button);
/* 235:    */    
/* 236:236 */    this.index += 1;
/* 237:237 */    this.index %= this.cols.length;
/* 238:    */  }
/* 239:    */  
/* 243:    */  public static void main(String[] argv)
/* 244:    */  {
/* 245:    */    try
/* 246:    */    {
/* 247:247 */      AppGameContainer container = new AppGameContainer(new InputTest());
/* 248:248 */      container.setDisplayMode(800, 600, false);
/* 249:249 */      container.start();
/* 250:    */    } catch (SlickException e) {
/* 251:251 */      e.printStackTrace();
/* 252:    */    }
/* 253:    */  }
/* 254:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.InputTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */