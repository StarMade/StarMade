/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import org.newdawn.slick.AppGameContainer;
/*   4:    */import org.newdawn.slick.BasicGame;
/*   5:    */import org.newdawn.slick.Color;
/*   6:    */import org.newdawn.slick.GameContainer;
/*   7:    */import org.newdawn.slick.Graphics;
/*   8:    */import org.newdawn.slick.SlickException;
/*   9:    */import org.newdawn.slick.geom.Circle;
/*  10:    */import org.newdawn.slick.geom.Ellipse;
/*  11:    */import org.newdawn.slick.geom.Rectangle;
/*  12:    */import org.newdawn.slick.geom.RoundedRectangle;
/*  13:    */import org.newdawn.slick.geom.Shape;
/*  14:    */import org.newdawn.slick.geom.Transform;
/*  15:    */import org.newdawn.slick.opengl.renderer.Renderer;
/*  16:    */
/*  22:    */public class GeomTest
/*  23:    */  extends BasicGame
/*  24:    */{
/*  25: 25 */  private Shape rect = new Rectangle(100.0F, 100.0F, 100.0F, 100.0F);
/*  26:    */  
/*  27: 27 */  private Shape circle = new Circle(500.0F, 200.0F, 50.0F);
/*  28:    */  
/*  29: 29 */  private Shape rect1 = new Rectangle(150.0F, 120.0F, 50.0F, 100.0F).transform(Transform.createTranslateTransform(50.0F, 50.0F));
/*  30:    */  
/*  31: 31 */  private Shape rect2 = new Rectangle(310.0F, 210.0F, 50.0F, 100.0F).transform(Transform.createRotateTransform((float)Math.toRadians(45.0D), 335.0F, 260.0F));
/*  32:    */  
/*  34: 34 */  private Shape circle1 = new Circle(150.0F, 90.0F, 30.0F);
/*  35:    */  
/*  36: 36 */  private Shape circle2 = new Circle(310.0F, 110.0F, 70.0F);
/*  37:    */  
/*  38: 38 */  private Shape circle3 = new Ellipse(510.0F, 150.0F, 70.0F, 70.0F);
/*  39:    */  
/*  40: 40 */  private Shape circle4 = new Ellipse(510.0F, 350.0F, 30.0F, 30.0F).transform(Transform.createTranslateTransform(-510.0F, -350.0F)).transform(Transform.createScaleTransform(2.0F, 2.0F)).transform(Transform.createTranslateTransform(510.0F, 350.0F));
/*  41:    */  
/*  45: 45 */  private Shape roundRect = new RoundedRectangle(50.0F, 175.0F, 100.0F, 100.0F, 20.0F);
/*  46:    */  
/*  47: 47 */  private Shape roundRect2 = new RoundedRectangle(50.0F, 280.0F, 50.0F, 50.0F, 20.0F, 20, 5);
/*  48:    */  
/*  51:    */  public GeomTest()
/*  52:    */  {
/*  53: 53 */    super("Geom Test");
/*  54:    */  }
/*  55:    */  
/*  58:    */  public void init(GameContainer container)
/*  59:    */    throws SlickException
/*  60:    */  {}
/*  61:    */  
/*  64:    */  public void render(GameContainer container, Graphics g)
/*  65:    */  {
/*  66: 66 */    g.setColor(Color.white);
/*  67: 67 */    g.drawString("Red indicates a collision, green indicates no collision", 50.0F, 420.0F);
/*  68: 68 */    g.drawString("White are the targets", 50.0F, 435.0F);
/*  69:    */    
/*  70: 70 */    g.pushTransform();
/*  71: 71 */    g.translate(100.0F, 100.0F);
/*  72: 72 */    g.pushTransform();
/*  73: 73 */    g.translate(-50.0F, -50.0F);
/*  74: 74 */    g.scale(10.0F, 10.0F);
/*  75: 75 */    g.setColor(Color.red);
/*  76: 76 */    g.fillRect(0.0F, 0.0F, 5.0F, 5.0F);
/*  77: 77 */    g.setColor(Color.white);
/*  78: 78 */    g.drawRect(0.0F, 0.0F, 5.0F, 5.0F);
/*  79: 79 */    g.popTransform();
/*  80: 80 */    g.setColor(Color.green);
/*  81: 81 */    g.fillRect(20.0F, 20.0F, 50.0F, 50.0F);
/*  82: 82 */    g.popTransform();
/*  83:    */    
/*  84: 84 */    g.setColor(Color.white);
/*  85: 85 */    g.draw(this.rect);
/*  86: 86 */    g.draw(this.circle);
/*  87:    */    
/*  88: 88 */    g.setColor(this.rect1.intersects(this.rect) ? Color.red : Color.green);
/*  89: 89 */    g.draw(this.rect1);
/*  90: 90 */    g.setColor(this.rect2.intersects(this.rect) ? Color.red : Color.green);
/*  91: 91 */    g.draw(this.rect2);
/*  92: 92 */    g.setColor(this.roundRect.intersects(this.rect) ? Color.red : Color.green);
/*  93: 93 */    g.draw(this.roundRect);
/*  94: 94 */    g.setColor(this.circle1.intersects(this.rect) ? Color.red : Color.green);
/*  95: 95 */    g.draw(this.circle1);
/*  96: 96 */    g.setColor(this.circle2.intersects(this.rect) ? Color.red : Color.green);
/*  97: 97 */    g.draw(this.circle2);
/*  98: 98 */    g.setColor(this.circle3.intersects(this.circle) ? Color.red : Color.green);
/*  99: 99 */    g.fill(this.circle3);
/* 100:100 */    g.setColor(this.circle4.intersects(this.circle) ? Color.red : Color.green);
/* 101:101 */    g.draw(this.circle4);
/* 102:    */    
/* 103:103 */    g.fill(this.roundRect2);
/* 104:104 */    g.setColor(Color.blue);
/* 105:105 */    g.draw(this.roundRect2);
/* 106:106 */    g.setColor(Color.blue);
/* 107:107 */    g.draw(new Circle(100.0F, 100.0F, 50.0F));
/* 108:108 */    g.drawRect(50.0F, 50.0F, 100.0F, 100.0F);
/* 109:    */  }
/* 110:    */  
/* 115:    */  public void update(GameContainer container, int delta) {}
/* 116:    */  
/* 120:    */  public void keyPressed(int key, char c)
/* 121:    */  {
/* 122:122 */    if (key == 1) {
/* 123:123 */      System.exit(0);
/* 124:    */    }
/* 125:    */  }
/* 126:    */  
/* 130:    */  public static void main(String[] argv)
/* 131:    */  {
/* 132:    */    try
/* 133:    */    {
/* 134:134 */      Renderer.setRenderer(2);
/* 135:    */      
/* 136:136 */      AppGameContainer container = new AppGameContainer(new GeomTest());
/* 137:137 */      container.setDisplayMode(800, 600, false);
/* 138:138 */      container.start();
/* 139:    */    } catch (SlickException e) {
/* 140:140 */      e.printStackTrace();
/* 141:    */    }
/* 142:    */  }
/* 143:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.GeomTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */