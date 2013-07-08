/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import org.newdawn.slick.AppGameContainer;
/*   4:    */import org.newdawn.slick.BasicGame;
/*   5:    */import org.newdawn.slick.Color;
/*   6:    */import org.newdawn.slick.GameContainer;
/*   7:    */import org.newdawn.slick.Graphics;
/*   8:    */import org.newdawn.slick.Image;
/*   9:    */import org.newdawn.slick.SlickException;
/*  10:    */import org.newdawn.slick.fills.GradientFill;
/*  11:    */import org.newdawn.slick.geom.Polygon;
/*  12:    */import org.newdawn.slick.geom.Rectangle;
/*  13:    */import org.newdawn.slick.geom.Shape;
/*  14:    */
/*  27:    */public class GradientImageTest
/*  28:    */  extends BasicGame
/*  29:    */{
/*  30:    */  private Image image1;
/*  31:    */  private Image image2;
/*  32:    */  private GradientFill fill;
/*  33:    */  private Shape shape;
/*  34:    */  private Polygon poly;
/*  35:    */  private GameContainer container;
/*  36:    */  private float ang;
/*  37: 37 */  private boolean rotating = false;
/*  38:    */  
/*  41:    */  public GradientImageTest()
/*  42:    */  {
/*  43: 43 */    super("Gradient Image Test");
/*  44:    */  }
/*  45:    */  
/*  47:    */  public void init(GameContainer container)
/*  48:    */    throws SlickException
/*  49:    */  {
/*  50: 50 */    this.container = container;
/*  51:    */    
/*  52: 52 */    this.image1 = new Image("testdata/grass.png");
/*  53: 53 */    this.image2 = new Image("testdata/rocks.png");
/*  54:    */    
/*  55: 55 */    this.fill = new GradientFill(-64.0F, 0.0F, new Color(1.0F, 1.0F, 1.0F, 1.0F), 64.0F, 0.0F, new Color(0, 0, 0, 0));
/*  56: 56 */    this.shape = new Rectangle(336.0F, 236.0F, 128.0F, 128.0F);
/*  57: 57 */    this.poly = new Polygon();
/*  58: 58 */    this.poly.addPoint(320.0F, 220.0F);
/*  59: 59 */    this.poly.addPoint(350.0F, 200.0F);
/*  60: 60 */    this.poly.addPoint(450.0F, 200.0F);
/*  61: 61 */    this.poly.addPoint(480.0F, 220.0F);
/*  62: 62 */    this.poly.addPoint(420.0F, 400.0F);
/*  63: 63 */    this.poly.addPoint(400.0F, 390.0F);
/*  64:    */  }
/*  65:    */  
/*  68:    */  public void render(GameContainer container, Graphics g)
/*  69:    */  {
/*  70: 70 */    g.drawString("R - Toggle Rotationg", 10.0F, 50.0F);
/*  71: 71 */    g.drawImage(this.image1, 100.0F, 236.0F);
/*  72: 72 */    g.drawImage(this.image2, 600.0F, 236.0F);
/*  73:    */    
/*  74: 74 */    g.translate(0.0F, -150.0F);
/*  75: 75 */    g.rotate(400.0F, 300.0F, this.ang);
/*  76: 76 */    g.texture(this.shape, this.image2);
/*  77: 77 */    g.texture(this.shape, this.image1, this.fill);
/*  78: 78 */    g.resetTransform();
/*  79:    */    
/*  80: 80 */    g.translate(0.0F, 150.0F);
/*  81: 81 */    g.rotate(400.0F, 300.0F, this.ang);
/*  82: 82 */    g.texture(this.poly, this.image2);
/*  83: 83 */    g.texture(this.poly, this.image1, this.fill);
/*  84: 84 */    g.resetTransform();
/*  85:    */  }
/*  86:    */  
/*  89:    */  public void update(GameContainer container, int delta)
/*  90:    */  {
/*  91: 91 */    if (this.rotating) {
/*  92: 92 */      this.ang += delta * 0.1F;
/*  93:    */    }
/*  94:    */  }
/*  95:    */  
/*  99:    */  public static void main(String[] argv)
/* 100:    */  {
/* 101:    */    try
/* 102:    */    {
/* 103:103 */      AppGameContainer container = new AppGameContainer(new GradientImageTest());
/* 104:104 */      container.setDisplayMode(800, 600, false);
/* 105:105 */      container.start();
/* 106:    */    } catch (SlickException e) {
/* 107:107 */      e.printStackTrace();
/* 108:    */    }
/* 109:    */  }
/* 110:    */  
/* 113:    */  public void keyPressed(int key, char c)
/* 114:    */  {
/* 115:115 */    if (key == 19) {
/* 116:116 */      this.rotating = (!this.rotating);
/* 117:    */    }
/* 118:118 */    if (key == 1) {
/* 119:119 */      this.container.exit();
/* 120:    */    }
/* 121:    */  }
/* 122:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.GradientImageTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */