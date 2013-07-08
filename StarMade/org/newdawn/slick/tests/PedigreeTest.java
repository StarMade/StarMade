/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import org.newdawn.slick.AppGameContainer;
/*   5:    */import org.newdawn.slick.BasicGame;
/*   6:    */import org.newdawn.slick.GameContainer;
/*   7:    */import org.newdawn.slick.Graphics;
/*   8:    */import org.newdawn.slick.Image;
/*   9:    */import org.newdawn.slick.SlickException;
/*  10:    */import org.newdawn.slick.particles.ConfigurableEmitter;
/*  11:    */import org.newdawn.slick.particles.ParticleIO;
/*  12:    */import org.newdawn.slick.particles.ParticleSystem;
/*  13:    */
/*  25:    */public class PedigreeTest
/*  26:    */  extends BasicGame
/*  27:    */{
/*  28:    */  private Image image;
/*  29:    */  private GameContainer container;
/*  30:    */  private ParticleSystem trail;
/*  31:    */  private ParticleSystem fire;
/*  32:    */  private float rx;
/*  33: 33 */  private float ry = 900.0F;
/*  34:    */  
/*  37:    */  public PedigreeTest()
/*  38:    */  {
/*  39: 39 */    super("Pedigree Test");
/*  40:    */  }
/*  41:    */  
/*  43:    */  public void init(GameContainer container)
/*  44:    */    throws SlickException
/*  45:    */  {
/*  46: 46 */    this.container = container;
/*  47:    */    try
/*  48:    */    {
/*  49: 49 */      this.fire = ParticleIO.loadConfiguredSystem("testdata/system.xml");
/*  50: 50 */      this.trail = ParticleIO.loadConfiguredSystem("testdata/smoketrail.xml");
/*  51:    */    }
/*  52:    */    catch (IOException e) {
/*  53: 53 */      throw new SlickException("Failed to load particle systems", e);
/*  54:    */    }
/*  55: 55 */    this.image = new Image("testdata/rocket.png");
/*  56:    */    
/*  57: 57 */    spawnRocket();
/*  58:    */  }
/*  59:    */  
/*  62:    */  private void spawnRocket()
/*  63:    */  {
/*  64: 64 */    this.ry = 700.0F;
/*  65: 65 */    this.rx = ((float)(Math.random() * 600.0D + 100.0D));
/*  66:    */  }
/*  67:    */  
/*  70:    */  public void render(GameContainer container, Graphics g)
/*  71:    */  {
/*  72: 72 */    ((ConfigurableEmitter)this.trail.getEmitter(0)).setPosition(this.rx + 14.0F, this.ry + 35.0F);
/*  73: 73 */    this.trail.render();
/*  74: 74 */    this.image.draw((int)this.rx, (int)this.ry);
/*  75:    */    
/*  76: 76 */    g.translate(400.0F, 300.0F);
/*  77: 77 */    this.fire.render();
/*  78:    */  }
/*  79:    */  
/*  82:    */  public void update(GameContainer container, int delta)
/*  83:    */  {
/*  84: 84 */    this.fire.update(delta);
/*  85: 85 */    this.trail.update(delta);
/*  86:    */    
/*  87: 87 */    this.ry -= delta * 0.25F;
/*  88: 88 */    if (this.ry < -100.0F) {
/*  89: 89 */      spawnRocket();
/*  90:    */    }
/*  91:    */  }
/*  92:    */  
/*  93:    */  public void mousePressed(int button, int x, int y) {
/*  94: 94 */    super.mousePressed(button, x, y);
/*  95:    */    
/*  96: 96 */    for (int i = 0; i < this.fire.getEmitterCount(); i++) {
/*  97: 97 */      ((ConfigurableEmitter)this.fire.getEmitter(i)).setPosition(x - 400, y - 300, true);
/*  98:    */    }
/*  99:    */  }
/* 100:    */  
/* 104:    */  public static void main(String[] argv)
/* 105:    */  {
/* 106:    */    try
/* 107:    */    {
/* 108:108 */      AppGameContainer container = new AppGameContainer(new PedigreeTest());
/* 109:109 */      container.setDisplayMode(800, 600, false);
/* 110:110 */      container.start();
/* 111:    */    } catch (SlickException e) {
/* 112:112 */      e.printStackTrace();
/* 113:    */    }
/* 114:    */  }
/* 115:    */  
/* 118:    */  public void keyPressed(int key, char c)
/* 119:    */  {
/* 120:120 */    if (key == 1) {
/* 121:121 */      this.container.exit();
/* 122:    */    }
/* 123:    */  }
/* 124:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.PedigreeTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */