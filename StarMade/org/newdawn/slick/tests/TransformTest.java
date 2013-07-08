/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import org.newdawn.slick.AppGameContainer;
/*   4:    */import org.newdawn.slick.BasicGame;
/*   5:    */import org.newdawn.slick.Color;
/*   6:    */import org.newdawn.slick.GameContainer;
/*   7:    */import org.newdawn.slick.Graphics;
/*   8:    */import org.newdawn.slick.SlickException;
/*   9:    */
/*  15:    */public class TransformTest
/*  16:    */  extends BasicGame
/*  17:    */{
/*  18: 18 */  private float scale = 1.0F;
/*  19:    */  
/*  21:    */  private boolean scaleUp;
/*  22:    */  
/*  23:    */  private boolean scaleDown;
/*  24:    */  
/*  26:    */  public TransformTest()
/*  27:    */  {
/*  28: 28 */    super("Transform Test");
/*  29:    */  }
/*  30:    */  
/*  32:    */  public void init(GameContainer container)
/*  33:    */    throws SlickException
/*  34:    */  {
/*  35: 35 */    container.setTargetFrameRate(100);
/*  36:    */  }
/*  37:    */  
/*  40:    */  public void render(GameContainer contiainer, Graphics g)
/*  41:    */  {
/*  42: 42 */    g.translate(320.0F, 240.0F);
/*  43: 43 */    g.scale(this.scale, this.scale);
/*  44:    */    
/*  45: 45 */    g.setColor(Color.red);
/*  46: 46 */    for (int x = 0; x < 10; x++) {
/*  47: 47 */      for (int y = 0; y < 10; y++) {
/*  48: 48 */        g.fillRect(-500 + x * 100, -500 + y * 100, 80.0F, 80.0F);
/*  49:    */      }
/*  50:    */    }
/*  51:    */    
/*  52: 52 */    g.setColor(new Color(1.0F, 1.0F, 1.0F, 0.5F));
/*  53: 53 */    g.fillRect(-320.0F, -240.0F, 640.0F, 480.0F);
/*  54: 54 */    g.setColor(Color.white);
/*  55: 55 */    g.drawRect(-320.0F, -240.0F, 640.0F, 480.0F);
/*  56:    */  }
/*  57:    */  
/*  60:    */  public void update(GameContainer container, int delta)
/*  61:    */  {
/*  62: 62 */    if (this.scaleUp) {
/*  63: 63 */      this.scale += delta * 0.001F;
/*  64:    */    }
/*  65: 65 */    if (this.scaleDown) {
/*  66: 66 */      this.scale -= delta * 0.001F;
/*  67:    */    }
/*  68:    */  }
/*  69:    */  
/*  72:    */  public void keyPressed(int key, char c)
/*  73:    */  {
/*  74: 74 */    if (key == 1) {
/*  75: 75 */      System.exit(0);
/*  76:    */    }
/*  77: 77 */    if (key == 16) {
/*  78: 78 */      this.scaleUp = true;
/*  79:    */    }
/*  80: 80 */    if (key == 30) {
/*  81: 81 */      this.scaleDown = true;
/*  82:    */    }
/*  83:    */  }
/*  84:    */  
/*  87:    */  public void keyReleased(int key, char c)
/*  88:    */  {
/*  89: 89 */    if (key == 16) {
/*  90: 90 */      this.scaleUp = false;
/*  91:    */    }
/*  92: 92 */    if (key == 30) {
/*  93: 93 */      this.scaleDown = false;
/*  94:    */    }
/*  95:    */  }
/*  96:    */  
/* 100:    */  public static void main(String[] argv)
/* 101:    */  {
/* 102:    */    try
/* 103:    */    {
/* 104:104 */      AppGameContainer container = new AppGameContainer(new TransformTest());
/* 105:105 */      container.setDisplayMode(640, 480, false);
/* 106:106 */      container.start();
/* 107:    */    } catch (SlickException e) {
/* 108:108 */      e.printStackTrace();
/* 109:    */    }
/* 110:    */  }
/* 111:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.TransformTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */