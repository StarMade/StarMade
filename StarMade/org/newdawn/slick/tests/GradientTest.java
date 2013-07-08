/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import org.newdawn.slick.AppGameContainer;
/*   4:    */import org.newdawn.slick.BasicGame;
/*   5:    */import org.newdawn.slick.Color;
/*   6:    */import org.newdawn.slick.GameContainer;
/*   7:    */import org.newdawn.slick.Graphics;
/*   8:    */import org.newdawn.slick.SlickException;
/*   9:    */import org.newdawn.slick.fills.GradientFill;
/*  10:    */import org.newdawn.slick.geom.Polygon;
/*  11:    */import org.newdawn.slick.geom.Rectangle;
/*  12:    */import org.newdawn.slick.geom.RoundedRectangle;
/*  13:    */import org.newdawn.slick.opengl.renderer.Renderer;
/*  14:    */
/*  31:    */public class GradientTest
/*  32:    */  extends BasicGame
/*  33:    */{
/*  34:    */  private GameContainer container;
/*  35:    */  private GradientFill gradient;
/*  36:    */  private GradientFill gradient2;
/*  37:    */  private GradientFill gradient4;
/*  38:    */  private Rectangle rect;
/*  39:    */  private Rectangle center;
/*  40:    */  private RoundedRectangle round;
/*  41:    */  private RoundedRectangle round2;
/*  42:    */  private Polygon poly;
/*  43:    */  private float ang;
/*  44:    */  
/*  45:    */  public GradientTest()
/*  46:    */  {
/*  47: 47 */    super("Gradient Test");
/*  48:    */  }
/*  49:    */  
/*  51:    */  public void init(GameContainer container)
/*  52:    */    throws SlickException
/*  53:    */  {
/*  54: 54 */    this.container = container;
/*  55:    */    
/*  56: 56 */    this.rect = new Rectangle(400.0F, 100.0F, 200.0F, 150.0F);
/*  57: 57 */    this.round = new RoundedRectangle(150.0F, 100.0F, 200.0F, 150.0F, 50.0F);
/*  58: 58 */    this.round2 = new RoundedRectangle(150.0F, 300.0F, 200.0F, 150.0F, 50.0F);
/*  59: 59 */    this.center = new Rectangle(350.0F, 250.0F, 100.0F, 100.0F);
/*  60:    */    
/*  61: 61 */    this.poly = new Polygon();
/*  62: 62 */    this.poly.addPoint(400.0F, 350.0F);
/*  63: 63 */    this.poly.addPoint(550.0F, 320.0F);
/*  64: 64 */    this.poly.addPoint(600.0F, 380.0F);
/*  65: 65 */    this.poly.addPoint(620.0F, 450.0F);
/*  66: 66 */    this.poly.addPoint(500.0F, 450.0F);
/*  67:    */    
/*  68: 68 */    this.gradient = new GradientFill(0.0F, -75.0F, Color.red, 0.0F, 75.0F, Color.yellow, true);
/*  69: 69 */    this.gradient2 = new GradientFill(0.0F, -75.0F, Color.blue, 0.0F, 75.0F, Color.white, true);
/*  70: 70 */    this.gradient4 = new GradientFill(-50.0F, -40.0F, Color.green, 50.0F, 40.0F, Color.cyan, true);
/*  71:    */  }
/*  72:    */  
/*  76:    */  public void render(GameContainer container, Graphics g)
/*  77:    */  {
/*  78: 78 */    g.rotate(400.0F, 300.0F, this.ang);
/*  79: 79 */    g.fill(this.rect, this.gradient);
/*  80: 80 */    g.fill(this.round, this.gradient);
/*  81: 81 */    g.fill(this.poly, this.gradient2);
/*  82: 82 */    g.fill(this.center, this.gradient4);
/*  83:    */    
/*  84: 84 */    g.setAntiAlias(true);
/*  85: 85 */    g.setLineWidth(10.0F);
/*  86: 86 */    g.draw(this.round2, this.gradient2);
/*  87: 87 */    g.setLineWidth(2.0F);
/*  88: 88 */    g.draw(this.poly, this.gradient);
/*  89: 89 */    g.setAntiAlias(false);
/*  90:    */    
/*  91: 91 */    g.fill(this.center, this.gradient4);
/*  92: 92 */    g.setAntiAlias(true);
/*  93: 93 */    g.setColor(Color.black);
/*  94: 94 */    g.draw(this.center);
/*  95: 95 */    g.setAntiAlias(false);
/*  96:    */  }
/*  97:    */  
/* 100:    */  public void update(GameContainer container, int delta)
/* 101:    */  {
/* 102:102 */    this.ang += delta * 0.01F;
/* 103:    */  }
/* 104:    */  
/* 108:    */  public static void main(String[] argv)
/* 109:    */  {
/* 110:    */    try
/* 111:    */    {
/* 112:112 */      Renderer.setRenderer(2);
/* 113:    */      
/* 114:114 */      AppGameContainer container = new AppGameContainer(new GradientTest());
/* 115:115 */      container.setDisplayMode(800, 600, false);
/* 116:116 */      container.start();
/* 117:    */    } catch (SlickException e) {
/* 118:118 */      e.printStackTrace();
/* 119:    */    }
/* 120:    */  }
/* 121:    */  
/* 124:    */  public void keyPressed(int key, char c)
/* 125:    */  {
/* 126:126 */    if (key == 1) {
/* 127:127 */      this.container.exit();
/* 128:    */    }
/* 129:    */  }
/* 130:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.GradientTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */