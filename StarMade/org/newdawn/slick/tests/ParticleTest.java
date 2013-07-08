/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import org.newdawn.slick.AppGameContainer;
/*  4:   */import org.newdawn.slick.BasicGame;
/*  5:   */import org.newdawn.slick.GameContainer;
/*  6:   */import org.newdawn.slick.Graphics;
/*  7:   */import org.newdawn.slick.Image;
/*  8:   */import org.newdawn.slick.SlickException;
/*  9:   */import org.newdawn.slick.particles.ParticleSystem;
/* 10:   */import org.newdawn.slick.particles.effects.FireEmitter;
/* 11:   */
/* 18:   */public class ParticleTest
/* 19:   */  extends BasicGame
/* 20:   */{
/* 21:   */  private ParticleSystem system;
/* 22:22 */  private int mode = 2;
/* 23:   */  
/* 26:   */  public ParticleTest()
/* 27:   */  {
/* 28:28 */    super("Particle Test");
/* 29:   */  }
/* 30:   */  
/* 32:   */  public void init(GameContainer container)
/* 33:   */    throws SlickException
/* 34:   */  {
/* 35:35 */    Image image = new Image("testdata/particle.tga", true);
/* 36:36 */    this.system = new ParticleSystem(image);
/* 37:   */    
/* 38:38 */    this.system.addEmitter(new FireEmitter(400, 300, 45.0F));
/* 39:39 */    this.system.addEmitter(new FireEmitter(200, 300, 60.0F));
/* 40:40 */    this.system.addEmitter(new FireEmitter(600, 300, 30.0F));
/* 41:   */  }
/* 42:   */  
/* 47:   */  public void render(GameContainer container, Graphics g)
/* 48:   */  {
/* 49:49 */    for (int i = 0; i < 100; i++) {
/* 50:50 */      g.translate(1.0F, 1.0F);
/* 51:51 */      this.system.render();
/* 52:   */    }
/* 53:53 */    g.resetTransform();
/* 54:54 */    g.drawString("Press space to toggle blending mode", 200.0F, 500.0F);
/* 55:55 */    g.drawString("Particle Count: " + this.system.getParticleCount() * 100, 200.0F, 520.0F);
/* 56:   */  }
/* 57:   */  
/* 60:   */  public void update(GameContainer container, int delta)
/* 61:   */  {
/* 62:62 */    this.system.update(delta);
/* 63:   */  }
/* 64:   */  
/* 67:   */  public void keyPressed(int key, char c)
/* 68:   */  {
/* 69:69 */    if (key == 1) {
/* 70:70 */      System.exit(0);
/* 71:   */    }
/* 72:72 */    if (key == 57) {
/* 73:73 */      this.mode = (1 == this.mode ? 2 : 1);
/* 74:74 */      this.system.setBlendingMode(this.mode);
/* 75:   */    }
/* 76:   */  }
/* 77:   */  
/* 81:   */  public static void main(String[] argv)
/* 82:   */  {
/* 83:   */    try
/* 84:   */    {
/* 85:85 */      AppGameContainer container = new AppGameContainer(new ParticleTest());
/* 86:86 */      container.setDisplayMode(800, 600, false);
/* 87:87 */      container.start();
/* 88:   */    } catch (SlickException e) {
/* 89:89 */      e.printStackTrace();
/* 90:   */    }
/* 91:   */  }
/* 92:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.ParticleTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */