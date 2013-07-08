/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import org.newdawn.slick.AppGameContainer;
/*   5:    */import org.newdawn.slick.BasicGame;
/*   6:    */import org.newdawn.slick.GameContainer;
/*   7:    */import org.newdawn.slick.Graphics;
/*   8:    */import org.newdawn.slick.SlickException;
/*   9:    */import org.newdawn.slick.particles.ConfigurableEmitter;
/*  10:    */import org.newdawn.slick.particles.ParticleIO;
/*  11:    */import org.newdawn.slick.particles.ParticleSystem;
/*  12:    */
/*  24:    */public class DuplicateEmitterTest
/*  25:    */  extends BasicGame
/*  26:    */{
/*  27:    */  private GameContainer container;
/*  28:    */  private ParticleSystem explosionSystem;
/*  29:    */  private ConfigurableEmitter explosionEmitter;
/*  30:    */  
/*  31:    */  public DuplicateEmitterTest()
/*  32:    */  {
/*  33: 33 */    super("DuplicateEmitterTest");
/*  34:    */  }
/*  35:    */  
/*  39:    */  public void init(GameContainer container)
/*  40:    */    throws SlickException
/*  41:    */  {
/*  42: 42 */    this.container = container;
/*  43:    */    
/*  44:    */    try
/*  45:    */    {
/*  46: 46 */      this.explosionSystem = ParticleIO.loadConfiguredSystem("testdata/endlessexplosion.xml");
/*  47:    */      
/*  48: 48 */      this.explosionEmitter = ((ConfigurableEmitter)this.explosionSystem.getEmitter(0));
/*  49:    */      
/*  50: 50 */      this.explosionEmitter.setPosition(400.0F, 100.0F);
/*  51:    */      
/*  52: 52 */      for (int i = 0; i < 5; i++)
/*  53:    */      {
/*  54: 54 */        ConfigurableEmitter newOne = this.explosionEmitter.duplicate();
/*  55:    */        
/*  56: 56 */        if (newOne == null) {
/*  57: 57 */          throw new SlickException("Failed to duplicate explosionEmitter");
/*  58:    */        }
/*  59: 59 */        newOne.name = (newOne.name + "_" + i);
/*  60:    */        
/*  61: 61 */        newOne.setPosition((i + 1) * 133, 400.0F);
/*  62:    */        
/*  63: 63 */        this.explosionSystem.addEmitter(newOne);
/*  64:    */      }
/*  65:    */    } catch (IOException e) {
/*  66: 66 */      throw new SlickException("Failed to load particle systems", e);
/*  67:    */    }
/*  68:    */  }
/*  69:    */  
/*  71:    */  public void update(GameContainer container, int delta)
/*  72:    */    throws SlickException
/*  73:    */  {
/*  74: 74 */    this.explosionSystem.update(delta);
/*  75:    */  }
/*  76:    */  
/*  78:    */  public void render(GameContainer container, Graphics g)
/*  79:    */    throws SlickException
/*  80:    */  {
/*  81: 81 */    this.explosionSystem.render();
/*  82:    */  }
/*  83:    */  
/*  86:    */  public void keyPressed(int key, char c)
/*  87:    */  {
/*  88: 88 */    if (key == 1) {
/*  89: 89 */      this.container.exit();
/*  90:    */    }
/*  91: 91 */    if (key == 37) {
/*  92: 92 */      this.explosionEmitter.wrapUp();
/*  93:    */    }
/*  94:    */  }
/*  95:    */  
/*  99:    */  public static void main(String[] argv)
/* 100:    */  {
/* 101:    */    try
/* 102:    */    {
/* 103:103 */      AppGameContainer container = new AppGameContainer(new DuplicateEmitterTest());
/* 104:104 */      container.setDisplayMode(800, 600, false);
/* 105:105 */      container.start();
/* 106:    */    } catch (SlickException e) {
/* 107:107 */      e.printStackTrace();
/* 108:    */    }
/* 109:    */  }
/* 110:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.DuplicateEmitterTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */