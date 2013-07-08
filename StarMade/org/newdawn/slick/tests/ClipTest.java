/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import org.newdawn.slick.AppGameContainer;
/*   4:    */import org.newdawn.slick.BasicGame;
/*   5:    */import org.newdawn.slick.Color;
/*   6:    */import org.newdawn.slick.GameContainer;
/*   7:    */import org.newdawn.slick.Graphics;
/*   8:    */import org.newdawn.slick.SlickException;
/*   9:    */
/*  16:    */public class ClipTest
/*  17:    */  extends BasicGame
/*  18:    */{
/*  19: 19 */  private float ang = 0.0F;
/*  20:    */  
/*  22:    */  private boolean world;
/*  23:    */  
/*  24:    */  private boolean clip;
/*  25:    */  
/*  27:    */  public ClipTest()
/*  28:    */  {
/*  29: 29 */    super("Clip Test");
/*  30:    */  }
/*  31:    */  
/*  34:    */  public void init(GameContainer container)
/*  35:    */    throws SlickException
/*  36:    */  {}
/*  37:    */  
/*  40:    */  public void update(GameContainer container, int delta)
/*  41:    */    throws SlickException
/*  42:    */  {
/*  43: 43 */    this.ang += delta * 0.01F;
/*  44:    */  }
/*  45:    */  
/*  48:    */  public void render(GameContainer container, Graphics g)
/*  49:    */    throws SlickException
/*  50:    */  {
/*  51: 51 */    g.setColor(Color.white);
/*  52: 52 */    g.drawString("1 - No Clipping", 100.0F, 10.0F);
/*  53: 53 */    g.drawString("2 - Screen Clipping", 100.0F, 30.0F);
/*  54: 54 */    g.drawString("3 - World Clipping", 100.0F, 50.0F);
/*  55:    */    
/*  56: 56 */    if (this.world) {
/*  57: 57 */      g.drawString("WORLD CLIPPING ENABLED", 200.0F, 80.0F);
/*  58:    */    }
/*  59: 59 */    if (this.clip) {
/*  60: 60 */      g.drawString("SCREEN CLIPPING ENABLED", 200.0F, 80.0F);
/*  61:    */    }
/*  62:    */    
/*  63: 63 */    g.rotate(400.0F, 400.0F, this.ang);
/*  64: 64 */    if (this.world) {
/*  65: 65 */      g.setWorldClip(350.0F, 302.0F, 100.0F, 196.0F);
/*  66:    */    }
/*  67: 67 */    if (this.clip) {
/*  68: 68 */      g.setClip(350, 302, 100, 196);
/*  69:    */    }
/*  70: 70 */    g.setColor(Color.red);
/*  71: 71 */    g.fillOval(300.0F, 300.0F, 200.0F, 200.0F);
/*  72: 72 */    g.setColor(Color.blue);
/*  73: 73 */    g.fillRect(390.0F, 200.0F, 20.0F, 400.0F);
/*  74:    */    
/*  75: 75 */    g.clearClip();
/*  76: 76 */    g.clearWorldClip();
/*  77:    */  }
/*  78:    */  
/*  81:    */  public void keyPressed(int key, char c)
/*  82:    */  {
/*  83: 83 */    if (key == 2) {
/*  84: 84 */      this.world = false;
/*  85: 85 */      this.clip = false;
/*  86:    */    }
/*  87: 87 */    if (key == 3) {
/*  88: 88 */      this.world = false;
/*  89: 89 */      this.clip = true;
/*  90:    */    }
/*  91: 91 */    if (key == 4) {
/*  92: 92 */      this.world = true;
/*  93: 93 */      this.clip = false;
/*  94:    */    }
/*  95:    */  }
/*  96:    */  
/* 100:    */  public static void main(String[] argv)
/* 101:    */  {
/* 102:    */    try
/* 103:    */    {
/* 104:104 */      AppGameContainer container = new AppGameContainer(new ClipTest());
/* 105:105 */      container.setDisplayMode(800, 600, false);
/* 106:106 */      container.start();
/* 107:    */    } catch (SlickException e) {
/* 108:108 */      e.printStackTrace();
/* 109:    */    }
/* 110:    */  }
/* 111:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.ClipTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */