/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import org.newdawn.slick.AngelCodeFont;
/*   5:    */import org.newdawn.slick.AppGameContainer;
/*   6:    */import org.newdawn.slick.BasicGame;
/*   7:    */import org.newdawn.slick.Color;
/*   8:    */import org.newdawn.slick.Font;
/*   9:    */import org.newdawn.slick.GameContainer;
/*  10:    */import org.newdawn.slick.Graphics;
/*  11:    */import org.newdawn.slick.Image;
/*  12:    */import org.newdawn.slick.SlickException;
/*  13:    */import org.newdawn.slick.gui.AbstractComponent;
/*  14:    */import org.newdawn.slick.gui.ComponentListener;
/*  15:    */import org.newdawn.slick.gui.MouseOverArea;
/*  16:    */import org.newdawn.slick.gui.TextField;
/*  17:    */import org.newdawn.slick.util.Log;
/*  18:    */
/*  23:    */public class GUITest
/*  24:    */  extends BasicGame
/*  25:    */  implements ComponentListener
/*  26:    */{
/*  27:    */  private Image image;
/*  28: 28 */  private MouseOverArea[] areas = new MouseOverArea[4];
/*  29:    */  
/*  30:    */  private GameContainer container;
/*  31:    */  
/*  32: 32 */  private String message = "Demo Menu System with stock images";
/*  33:    */  
/*  35:    */  private TextField field;
/*  36:    */  
/*  37:    */  private TextField field2;
/*  38:    */  
/*  39:    */  private Image background;
/*  40:    */  
/*  41:    */  private Font font;
/*  42:    */  
/*  43:    */  private AppGameContainer app;
/*  44:    */  
/*  46:    */  public GUITest()
/*  47:    */  {
/*  48: 48 */    super("GUI Test");
/*  49:    */  }
/*  50:    */  
/*  52:    */  public void init(GameContainer container)
/*  53:    */    throws SlickException
/*  54:    */  {
/*  55: 55 */    if ((container instanceof AppGameContainer)) {
/*  56: 56 */      this.app = ((AppGameContainer)container);
/*  57: 57 */      this.app.setIcon("testdata/icon.tga");
/*  58:    */    }
/*  59:    */    
/*  60: 60 */    this.font = new AngelCodeFont("testdata/demo2.fnt", "testdata/demo2_00.tga");
/*  61: 61 */    this.field = (container, this.font, 150, 20, 500, 35, new ComponentListener() {
/*  62:    */      public void componentActivated(AbstractComponent source) {
/*  63: 63 */        GUITest.this.message = ("Entered1: " + GUITest.this.field.getText());
/*  64: 64 */        GUITest.this.field2.setFocus(true);
/*  65:    */      }
/*  66: 66 */    });
/*  67: 67 */    this.field2 = (container, this.font, 150, 70, 500, 35, new ComponentListener() {
/*  68:    */      public void componentActivated(AbstractComponent source) {
/*  69: 69 */        GUITest.this.message = ("Entered2: " + GUITest.this.field2.getText());
/*  70: 70 */        GUITest.this.field.setFocus(true);
/*  71:    */      }
/*  72: 72 */    });
/*  73: 73 */    this.field2.setBorderColor(Color.red);
/*  74:    */    
/*  75: 75 */    this.container = container;
/*  76:    */    
/*  77: 77 */    this.image = new Image("testdata/logo.tga");
/*  78: 78 */    this.background = new Image("testdata/dungeontiles.gif");
/*  79: 79 */    container.setMouseCursor("testdata/cursor.tga", 0, 0);
/*  80:    */    
/*  81: 81 */    for (int i = 0; i < 4; i++) {
/*  82: 82 */      this.areas[i] = new MouseOverArea(container, this.image, 300, 100 + i * 100, 200, 90, this);
/*  83: 83 */      this.areas[i].setNormalColor(new Color(1.0F, 1.0F, 1.0F, 0.8F));
/*  84: 84 */      this.areas[i].setMouseOverColor(new Color(1.0F, 1.0F, 1.0F, 0.9F));
/*  85:    */    }
/*  86:    */  }
/*  87:    */  
/*  90:    */  public void render(GameContainer container, Graphics g)
/*  91:    */  {
/*  92: 92 */    this.background.draw(0.0F, 0.0F, 800.0F, 500.0F);
/*  93:    */    
/*  94: 94 */    for (int i = 0; i < 4; i++) {
/*  95: 95 */      this.areas[i].render(container, g);
/*  96:    */    }
/*  97: 97 */    this.field.render(container, g);
/*  98: 98 */    this.field2.render(container, g);
/*  99:    */    
/* 100:100 */    g.setFont(this.font);
/* 101:101 */    g.drawString(this.message, 200.0F, 550.0F);
/* 102:    */  }
/* 103:    */  
/* 107:    */  public void update(GameContainer container, int delta) {}
/* 108:    */  
/* 112:    */  public void keyPressed(int key, char c)
/* 113:    */  {
/* 114:114 */    if (key == 1) {
/* 115:115 */      System.exit(0);
/* 116:    */    }
/* 117:117 */    if (key == 60) {
/* 118:118 */      this.app.setDefaultMouseCursor();
/* 119:    */    }
/* 120:120 */    if ((key == 59) && 
/* 121:121 */      (this.app != null)) {
/* 122:    */      try {
/* 123:123 */        this.app.setDisplayMode(640, 480, false);
/* 124:    */      } catch (SlickException e) {
/* 125:125 */        Log.error(e);
/* 126:    */      }
/* 127:    */    }
/* 128:    */  }
/* 129:    */  
/* 134:    */  public static void main(String[] argv)
/* 135:    */  {
/* 136:    */    try
/* 137:    */    {
/* 138:138 */      AppGameContainer container = new AppGameContainer(new GUITest());
/* 139:139 */      container.setDisplayMode(800, 600, false);
/* 140:140 */      container.start();
/* 141:    */    } catch (SlickException e) {
/* 142:142 */      e.printStackTrace();
/* 143:    */    }
/* 144:    */  }
/* 145:    */  
/* 148:    */  public void componentActivated(AbstractComponent source)
/* 149:    */  {
/* 150:150 */    System.out.println("ACTIVL : " + source);
/* 151:151 */    for (int i = 0; i < 4; i++) {
/* 152:152 */      if (source == this.areas[i]) {
/* 153:153 */        this.message = ("Option " + (i + 1) + " pressed!");
/* 154:    */      }
/* 155:    */    }
/* 156:156 */    if (source == this.field2) {}
/* 157:    */  }
/* 158:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.GUITest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */