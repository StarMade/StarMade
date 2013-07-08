/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import org.newdawn.slick.Animation;
/*   4:    */import org.newdawn.slick.AppGameContainer;
/*   5:    */import org.newdawn.slick.BasicGame;
/*   6:    */import org.newdawn.slick.Color;
/*   7:    */import org.newdawn.slick.GameContainer;
/*   8:    */import org.newdawn.slick.Graphics;
/*   9:    */import org.newdawn.slick.Input;
/*  10:    */import org.newdawn.slick.SlickException;
/*  11:    */import org.newdawn.slick.SpriteSheet;
/*  12:    */
/*  22:    */public class AnimationTest
/*  23:    */  extends BasicGame
/*  24:    */{
/*  25:    */  private Animation animation;
/*  26:    */  private Animation limited;
/*  27:    */  private Animation manual;
/*  28:    */  private Animation pingPong;
/*  29:    */  private GameContainer container;
/*  30: 30 */  private int start = 5000;
/*  31:    */  
/*  34:    */  public AnimationTest()
/*  35:    */  {
/*  36: 36 */    super("Animation Test");
/*  37:    */  }
/*  38:    */  
/*  40:    */  public void init(GameContainer container)
/*  41:    */    throws SlickException
/*  42:    */  {
/*  43: 43 */    this.container = container;
/*  44:    */    
/*  45: 45 */    SpriteSheet sheet = new SpriteSheet("testdata/homeranim.png", 36, 65);
/*  46: 46 */    this.animation = new Animation();
/*  47: 47 */    for (int i = 0; i < 8; i++) {
/*  48: 48 */      this.animation.addFrame(sheet.getSprite(i, 0), 150);
/*  49:    */    }
/*  50: 50 */    this.limited = new Animation();
/*  51: 51 */    for (int i = 0; i < 8; i++) {
/*  52: 52 */      this.limited.addFrame(sheet.getSprite(i, 0), 150);
/*  53:    */    }
/*  54: 54 */    this.limited.stopAt(7);
/*  55: 55 */    this.manual = new Animation(false);
/*  56: 56 */    for (int i = 0; i < 8; i++) {
/*  57: 57 */      this.manual.addFrame(sheet.getSprite(i, 0), 150);
/*  58:    */    }
/*  59: 59 */    this.pingPong = new Animation(sheet, 0, 0, 7, 0, true, 150, true);
/*  60: 60 */    this.pingPong.setPingPong(true);
/*  61: 61 */    container.getGraphics().setBackground(new Color(0.4F, 0.6F, 0.6F));
/*  62:    */  }
/*  63:    */  
/*  66:    */  public void render(GameContainer container, Graphics g)
/*  67:    */  {
/*  68: 68 */    g.drawString("Space to restart() animation", 100.0F, 50.0F);
/*  69: 69 */    g.drawString("Til Limited animation: " + this.start, 100.0F, 500.0F);
/*  70: 70 */    g.drawString("Hold 1 to move the manually animated", 100.0F, 70.0F);
/*  71: 71 */    g.drawString("PingPong Frame:" + this.pingPong.getFrame(), 600.0F, 70.0F);
/*  72:    */    
/*  73: 73 */    g.scale(-1.0F, 1.0F);
/*  74: 74 */    this.animation.draw(-100.0F, 100.0F);
/*  75: 75 */    this.animation.draw(-200.0F, 100.0F, 144.0F, 260.0F);
/*  76: 76 */    if (this.start < 0) {
/*  77: 77 */      this.limited.draw(-400.0F, 100.0F, 144.0F, 260.0F);
/*  78:    */    }
/*  79: 79 */    this.manual.draw(-600.0F, 100.0F, 144.0F, 260.0F);
/*  80: 80 */    this.pingPong.draw(-700.0F, 100.0F, 72.0F, 130.0F);
/*  81:    */  }
/*  82:    */  
/*  85:    */  public void update(GameContainer container, int delta)
/*  86:    */  {
/*  87: 87 */    if (container.getInput().isKeyDown(2)) {
/*  88: 88 */      this.manual.update(delta);
/*  89:    */    }
/*  90: 90 */    if (this.start >= 0) {
/*  91: 91 */      this.start -= delta;
/*  92:    */    }
/*  93:    */  }
/*  94:    */  
/*  98:    */  public static void main(String[] argv)
/*  99:    */  {
/* 100:    */    try
/* 101:    */    {
/* 102:102 */      AppGameContainer container = new AppGameContainer(new AnimationTest());
/* 103:103 */      container.setDisplayMode(800, 600, false);
/* 104:104 */      container.start();
/* 105:    */    } catch (SlickException e) {
/* 106:106 */      e.printStackTrace();
/* 107:    */    }
/* 108:    */  }
/* 109:    */  
/* 112:    */  public void keyPressed(int key, char c)
/* 113:    */  {
/* 114:114 */    if (key == 1) {
/* 115:115 */      this.container.exit();
/* 116:    */    }
/* 117:117 */    if (key == 57) {
/* 118:118 */      this.limited.restart();
/* 119:    */    }
/* 120:    */  }
/* 121:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.AnimationTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */