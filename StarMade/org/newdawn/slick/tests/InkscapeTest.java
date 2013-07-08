/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import org.newdawn.slick.AppGameContainer;
/*   4:    */import org.newdawn.slick.BasicGame;
/*   5:    */import org.newdawn.slick.Color;
/*   6:    */import org.newdawn.slick.GameContainer;
/*   7:    */import org.newdawn.slick.Graphics;
/*   8:    */import org.newdawn.slick.Input;
/*   9:    */import org.newdawn.slick.SlickException;
/*  10:    */import org.newdawn.slick.opengl.renderer.Renderer;
/*  11:    */import org.newdawn.slick.svg.InkscapeLoader;
/*  12:    */import org.newdawn.slick.svg.SimpleDiagramRenderer;
/*  13:    */
/*  18:    */public class InkscapeTest
/*  19:    */  extends BasicGame
/*  20:    */{
/*  21: 21 */  private SimpleDiagramRenderer[] renderer = new SimpleDiagramRenderer[5];
/*  22:    */  
/*  23: 23 */  private float zoom = 1.0F;
/*  24:    */  
/*  26:    */  private float x;
/*  27:    */  
/*  28:    */  private float y;
/*  29:    */  
/*  31:    */  public InkscapeTest()
/*  32:    */  {
/*  33: 33 */    super("Inkscape Test");
/*  34:    */  }
/*  35:    */  
/*  37:    */  public void init(GameContainer container)
/*  38:    */    throws SlickException
/*  39:    */  {
/*  40: 40 */    container.getGraphics().setBackground(Color.white);
/*  41:    */    
/*  42: 42 */    InkscapeLoader.RADIAL_TRIANGULATION_LEVEL = 2;
/*  43:    */    
/*  47: 47 */    this.renderer[3] = new SimpleDiagramRenderer(InkscapeLoader.load("testdata/svg/clonetest.svg"));
/*  48:    */    
/*  50: 50 */    container.getGraphics().setBackground(new Color(0.5F, 0.7F, 1.0F));
/*  51:    */  }
/*  52:    */  
/*  54:    */  public void update(GameContainer container, int delta)
/*  55:    */    throws SlickException
/*  56:    */  {
/*  57: 57 */    if (container.getInput().isKeyDown(16)) {
/*  58: 58 */      this.zoom += delta * 0.01F;
/*  59: 59 */      if (this.zoom > 10.0F) {
/*  60: 60 */        this.zoom = 10.0F;
/*  61:    */      }
/*  62:    */    }
/*  63: 63 */    if (container.getInput().isKeyDown(30)) {
/*  64: 64 */      this.zoom -= delta * 0.01F;
/*  65: 65 */      if (this.zoom < 0.1F) {
/*  66: 66 */        this.zoom = 0.1F;
/*  67:    */      }
/*  68:    */    }
/*  69: 69 */    if (container.getInput().isKeyDown(205)) {
/*  70: 70 */      this.x += delta * 0.1F;
/*  71:    */    }
/*  72: 72 */    if (container.getInput().isKeyDown(203)) {
/*  73: 73 */      this.x -= delta * 0.1F;
/*  74:    */    }
/*  75: 75 */    if (container.getInput().isKeyDown(208)) {
/*  76: 76 */      this.y += delta * 0.1F;
/*  77:    */    }
/*  78: 78 */    if (container.getInput().isKeyDown(200)) {
/*  79: 79 */      this.y -= delta * 0.1F;
/*  80:    */    }
/*  81:    */  }
/*  82:    */  
/*  84:    */  public void render(GameContainer container, Graphics g)
/*  85:    */    throws SlickException
/*  86:    */  {
/*  87: 87 */    g.scale(this.zoom, this.zoom);
/*  88: 88 */    g.translate(this.x, this.y);
/*  89: 89 */    g.scale(0.3F, 0.3F);
/*  90:    */    
/*  91: 91 */    g.scale(3.333333F, 3.333333F);
/*  92: 92 */    g.translate(400.0F, 0.0F);
/*  93:    */    
/*  94: 94 */    g.translate(100.0F, 300.0F);
/*  95: 95 */    g.scale(0.7F, 0.7F);
/*  96:    */    
/*  97: 97 */    g.scale(1.428572F, 1.428572F);
/*  98:    */    
/*  99: 99 */    g.scale(0.5F, 0.5F);
/* 100:100 */    g.translate(-1100.0F, -380.0F);
/* 101:101 */    this.renderer[3].render(g);
/* 102:102 */    g.scale(2.0F, 2.0F);
/* 103:    */    
/* 108:108 */    g.resetTransform();
/* 109:    */  }
/* 110:    */  
/* 114:    */  public static void main(String[] argv)
/* 115:    */  {
/* 116:    */    try
/* 117:    */    {
/* 118:118 */      Renderer.setRenderer(2);
/* 119:119 */      Renderer.setLineStripRenderer(4);
/* 120:    */      
/* 121:121 */      AppGameContainer container = new AppGameContainer(new InkscapeTest());
/* 122:122 */      container.setDisplayMode(800, 600, false);
/* 123:123 */      container.start();
/* 124:    */    } catch (SlickException e) {
/* 125:125 */      e.printStackTrace();
/* 126:    */    }
/* 127:    */  }
/* 128:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.InkscapeTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */