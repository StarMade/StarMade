/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import org.newdawn.slick.AppGameContainer;
/*   5:    */import org.newdawn.slick.BasicGame;
/*   6:    */import org.newdawn.slick.Color;
/*   7:    */import org.newdawn.slick.GameContainer;
/*   8:    */import org.newdawn.slick.Graphics;
/*   9:    */import org.newdawn.slick.SlickException;
/*  10:    */import org.newdawn.slick.opengl.SlickCallable;
/*  11:    */import org.newdawn.slick.util.Log;
/*  12:    */
/*  20:    */public class TestBox
/*  21:    */  extends BasicGame
/*  22:    */{
/*  23: 23 */  private ArrayList games = new ArrayList();
/*  24:    */  
/*  26:    */  private BasicGame currentGame;
/*  27:    */  
/*  28:    */  private int index;
/*  29:    */  
/*  30:    */  private AppGameContainer container;
/*  31:    */  
/*  33:    */  public TestBox()
/*  34:    */  {
/*  35: 35 */    super("Test Box");
/*  36:    */  }
/*  37:    */  
/*  42:    */  public void addGame(Class game)
/*  43:    */  {
/*  44: 44 */    this.games.add(game);
/*  45:    */  }
/*  46:    */  
/*  49:    */  private void nextGame()
/*  50:    */  {
/*  51: 51 */    if (this.index == -1) {
/*  52: 52 */      return;
/*  53:    */    }
/*  54:    */    
/*  55: 55 */    this.index += 1;
/*  56: 56 */    if (this.index >= this.games.size()) {
/*  57: 57 */      this.index = 0;
/*  58:    */    }
/*  59:    */    
/*  60: 60 */    startGame();
/*  61:    */  }
/*  62:    */  
/*  64:    */  private void startGame()
/*  65:    */  {
/*  66:    */    try
/*  67:    */    {
/*  68: 68 */      this.currentGame = ((BasicGame)((Class)this.games.get(this.index)).newInstance());
/*  69: 69 */      this.container.getGraphics().setBackground(Color.black);
/*  70: 70 */      this.currentGame.init(this.container);
/*  71: 71 */      this.currentGame.render(this.container, this.container.getGraphics());
/*  72:    */    } catch (Exception e) {
/*  73: 73 */      Log.error(e);
/*  74:    */    }
/*  75:    */    
/*  76: 76 */    this.container.setTitle(this.currentGame.getTitle());
/*  77:    */  }
/*  78:    */  
/*  80:    */  public void init(GameContainer c)
/*  81:    */    throws SlickException
/*  82:    */  {
/*  83: 83 */    if (this.games.size() == 0) {
/*  84: 84 */      this.currentGame = new BasicGame("NULL") {
/*  85:    */        public void init(GameContainer container) throws SlickException
/*  86:    */        {}
/*  87:    */        
/*  88:    */        public void update(GameContainer container, int delta) throws SlickException
/*  89:    */        {}
/*  90:    */        
/*  91:    */        public void render(GameContainer container, Graphics g) throws SlickException
/*  92:    */        {}
/*  93: 93 */      };
/*  94: 94 */      this.currentGame.init(c);
/*  95: 95 */      this.index = -1;
/*  96:    */    } else {
/*  97: 97 */      this.index = 0;
/*  98: 98 */      this.container = ((AppGameContainer)c);
/*  99: 99 */      startGame();
/* 100:    */    }
/* 101:    */  }
/* 102:    */  
/* 104:    */  public void update(GameContainer container, int delta)
/* 105:    */    throws SlickException
/* 106:    */  {
/* 107:107 */    this.currentGame.update(container, delta);
/* 108:    */  }
/* 109:    */  
/* 111:    */  public void render(GameContainer container, Graphics g)
/* 112:    */    throws SlickException
/* 113:    */  {
/* 114:114 */    SlickCallable.enterSafeBlock();
/* 115:115 */    this.currentGame.render(container, g);
/* 116:116 */    SlickCallable.leaveSafeBlock();
/* 117:    */  }
/* 118:    */  
/* 121:    */  public void controllerButtonPressed(int controller, int button)
/* 122:    */  {
/* 123:123 */    this.currentGame.controllerButtonPressed(controller, button);
/* 124:    */  }
/* 125:    */  
/* 128:    */  public void controllerButtonReleased(int controller, int button)
/* 129:    */  {
/* 130:130 */    this.currentGame.controllerButtonReleased(controller, button);
/* 131:    */  }
/* 132:    */  
/* 135:    */  public void controllerDownPressed(int controller)
/* 136:    */  {
/* 137:137 */    this.currentGame.controllerDownPressed(controller);
/* 138:    */  }
/* 139:    */  
/* 142:    */  public void controllerDownReleased(int controller)
/* 143:    */  {
/* 144:144 */    this.currentGame.controllerDownReleased(controller);
/* 145:    */  }
/* 146:    */  
/* 149:    */  public void controllerLeftPressed(int controller)
/* 150:    */  {
/* 151:151 */    this.currentGame.controllerLeftPressed(controller);
/* 152:    */  }
/* 153:    */  
/* 156:    */  public void controllerLeftReleased(int controller)
/* 157:    */  {
/* 158:158 */    this.currentGame.controllerLeftReleased(controller);
/* 159:    */  }
/* 160:    */  
/* 163:    */  public void controllerRightPressed(int controller)
/* 164:    */  {
/* 165:165 */    this.currentGame.controllerRightPressed(controller);
/* 166:    */  }
/* 167:    */  
/* 170:    */  public void controllerRightReleased(int controller)
/* 171:    */  {
/* 172:172 */    this.currentGame.controllerRightReleased(controller);
/* 173:    */  }
/* 174:    */  
/* 177:    */  public void controllerUpPressed(int controller)
/* 178:    */  {
/* 179:179 */    this.currentGame.controllerUpPressed(controller);
/* 180:    */  }
/* 181:    */  
/* 184:    */  public void controllerUpReleased(int controller)
/* 185:    */  {
/* 186:186 */    this.currentGame.controllerUpReleased(controller);
/* 187:    */  }
/* 188:    */  
/* 191:    */  public void keyPressed(int key, char c)
/* 192:    */  {
/* 193:193 */    this.currentGame.keyPressed(key, c);
/* 194:    */    
/* 195:195 */    if (key == 28) {
/* 196:196 */      nextGame();
/* 197:    */    }
/* 198:    */  }
/* 199:    */  
/* 202:    */  public void keyReleased(int key, char c)
/* 203:    */  {
/* 204:204 */    this.currentGame.keyReleased(key, c);
/* 205:    */  }
/* 206:    */  
/* 209:    */  public void mouseMoved(int oldx, int oldy, int newx, int newy)
/* 210:    */  {
/* 211:211 */    this.currentGame.mouseMoved(oldx, oldy, newx, newy);
/* 212:    */  }
/* 213:    */  
/* 216:    */  public void mousePressed(int button, int x, int y)
/* 217:    */  {
/* 218:218 */    this.currentGame.mousePressed(button, x, y);
/* 219:    */  }
/* 220:    */  
/* 223:    */  public void mouseReleased(int button, int x, int y)
/* 224:    */  {
/* 225:225 */    this.currentGame.mouseReleased(button, x, y);
/* 226:    */  }
/* 227:    */  
/* 230:    */  public void mouseWheelMoved(int change)
/* 231:    */  {
/* 232:232 */    this.currentGame.mouseWheelMoved(change);
/* 233:    */  }
/* 234:    */  
/* 238:    */  public static void main(String[] argv)
/* 239:    */  {
/* 240:    */    try
/* 241:    */    {
/* 242:242 */      TestBox box = new TestBox();
/* 243:243 */      box.addGame(AnimationTest.class);
/* 244:244 */      box.addGame(AntiAliasTest.class);
/* 245:245 */      box.addGame(BigImageTest.class);
/* 246:246 */      box.addGame(ClipTest.class);
/* 247:247 */      box.addGame(DuplicateEmitterTest.class);
/* 248:248 */      box.addGame(FlashTest.class);
/* 249:249 */      box.addGame(FontPerformanceTest.class);
/* 250:250 */      box.addGame(FontTest.class);
/* 251:251 */      box.addGame(GeomTest.class);
/* 252:252 */      box.addGame(GradientTest.class);
/* 253:253 */      box.addGame(GraphicsTest.class);
/* 254:254 */      box.addGame(ImageBufferTest.class);
/* 255:255 */      box.addGame(ImageReadTest.class);
/* 256:256 */      box.addGame(ImageTest.class);
/* 257:257 */      box.addGame(KeyRepeatTest.class);
/* 258:258 */      box.addGame(MusicListenerTest.class);
/* 259:259 */      box.addGame(PackedSheetTest.class);
/* 260:260 */      box.addGame(PedigreeTest.class);
/* 261:261 */      box.addGame(PureFontTest.class);
/* 262:262 */      box.addGame(ShapeTest.class);
/* 263:263 */      box.addGame(SoundTest.class);
/* 264:264 */      box.addGame(SpriteSheetFontTest.class);
/* 265:265 */      box.addGame(TransparentColorTest.class);
/* 266:    */      
/* 267:267 */      AppGameContainer container = new AppGameContainer(box);
/* 268:268 */      container.setDisplayMode(800, 600, false);
/* 269:269 */      container.start();
/* 270:    */    } catch (SlickException e) {
/* 271:271 */      e.printStackTrace();
/* 272:    */    }
/* 273:    */  }
/* 274:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.TestBox
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */