/*     */ package org.newdawn.slick.tests;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.newdawn.slick.AppGameContainer;
/*     */ import org.newdawn.slick.BasicGame;
/*     */ import org.newdawn.slick.GameContainer;
/*     */ import org.newdawn.slick.Graphics;
/*     */ import org.newdawn.slick.SlickException;
/*     */ import org.newdawn.slick.particles.ConfigurableEmitter;
/*     */ import org.newdawn.slick.particles.ParticleIO;
/*     */ import org.newdawn.slick.particles.ParticleSystem;
/*     */ 
/*     */ public class DuplicateEmitterTest extends BasicGame
/*     */ {
/*     */   private GameContainer container;
/*     */   private ParticleSystem explosionSystem;
/*     */   private ConfigurableEmitter explosionEmitter;
/*     */ 
/*     */   public DuplicateEmitterTest()
/*     */   {
/*  33 */     super("DuplicateEmitterTest");
/*     */   }
/*     */ 
/*     */   public void init(GameContainer container)
/*     */     throws SlickException
/*     */   {
/*  42 */     this.container = container;
/*     */     try
/*     */     {
/*  46 */       this.explosionSystem = ParticleIO.loadConfiguredSystem("testdata/endlessexplosion.xml");
/*     */ 
/*  48 */       this.explosionEmitter = ((ConfigurableEmitter)this.explosionSystem.getEmitter(0));
/*     */ 
/*  50 */       this.explosionEmitter.setPosition(400.0F, 100.0F);
/*     */ 
/*  52 */       for (int i = 0; i < 5; i++)
/*     */       {
/*  54 */         ConfigurableEmitter newOne = this.explosionEmitter.duplicate();
/*     */ 
/*  56 */         if (newOne == null) {
/*  57 */           throw new SlickException("Failed to duplicate explosionEmitter");
/*     */         }
/*  59 */         newOne.name = (newOne.name + "_" + i);
/*     */ 
/*  61 */         newOne.setPosition((i + 1) * 133, 400.0F);
/*     */ 
/*  63 */         this.explosionSystem.addEmitter(newOne);
/*     */       }
/*     */     } catch (IOException e) {
/*  66 */       throw new SlickException("Failed to load particle systems", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void update(GameContainer container, int delta)
/*     */     throws SlickException
/*     */   {
/*  74 */     this.explosionSystem.update(delta);
/*     */   }
/*     */ 
/*     */   public void render(GameContainer container, Graphics g)
/*     */     throws SlickException
/*     */   {
/*  81 */     this.explosionSystem.render();
/*     */   }
/*     */ 
/*     */   public void keyPressed(int key, char c)
/*     */   {
/*  88 */     if (key == 1) {
/*  89 */       this.container.exit();
/*     */     }
/*  91 */     if (key == 37)
/*  92 */       this.explosionEmitter.wrapUp();
/*     */   }
/*     */ 
/*     */   public static void main(String[] argv)
/*     */   {
/*     */     try
/*     */     {
/* 103 */       AppGameContainer container = new AppGameContainer(new DuplicateEmitterTest());
/* 104 */       container.setDisplayMode(800, 600, false);
/* 105 */       container.start();
/*     */     } catch (SlickException e) {
/* 107 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.DuplicateEmitterTest
 * JD-Core Version:    0.6.2
 */