/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import java.io.IOException;
/*  4:   */import java.util.List;
/*  5:   */import org.newdawn.slick.AppGameContainer;
/*  6:   */import org.newdawn.slick.BasicGame;
/*  7:   */import org.newdawn.slick.GameContainer;
/*  8:   */import org.newdawn.slick.Graphics;
/*  9:   */import org.newdawn.slick.Input;
/* 10:   */import org.newdawn.slick.SlickException;
/* 11:   */import org.newdawn.slick.UnicodeFont;
/* 12:   */import org.newdawn.slick.font.effects.ColorEffect;
/* 13:   */
/* 23:   */public class UnicodeFontTest
/* 24:   */  extends BasicGame
/* 25:   */{
/* 26:   */  private UnicodeFont unicodeFont;
/* 27:   */  
/* 28:   */  public UnicodeFontTest()
/* 29:   */  {
/* 30:30 */    super("Font Test");
/* 31:   */  }
/* 32:   */  
/* 34:   */  public void init(GameContainer container)
/* 35:   */    throws SlickException
/* 36:   */  {
/* 37:37 */    container.setShowFPS(false);
/* 38:   */    
/* 40:40 */    this.unicodeFont = new UnicodeFont("c:/windows/fonts/arial.ttf", 48, false, false);
/* 41:   */    
/* 45:45 */    this.unicodeFont.getEffects().add(new ColorEffect(java.awt.Color.white));
/* 46:   */    
/* 53:53 */    container.getGraphics().setBackground(org.newdawn.slick.Color.darkGray);
/* 54:   */  }
/* 55:   */  
/* 58:   */  public void render(GameContainer container, Graphics g)
/* 59:   */  {
/* 60:60 */    g.setColor(org.newdawn.slick.Color.white);
/* 61:   */    
/* 62:62 */    String text = "This is UnicodeFont!\nIt rockz. Kerning: T,";
/* 63:63 */    this.unicodeFont.drawString(10.0F, 33.0F, text);
/* 64:   */    
/* 65:65 */    g.setColor(org.newdawn.slick.Color.red);
/* 66:66 */    g.drawRect(10.0F, 33.0F, this.unicodeFont.getWidth(text), this.unicodeFont.getLineHeight());
/* 67:67 */    g.setColor(org.newdawn.slick.Color.blue);
/* 68:68 */    int yOffset = this.unicodeFont.getYOffset(text);
/* 69:69 */    g.drawRect(10.0F, 33 + yOffset, this.unicodeFont.getWidth(text), this.unicodeFont.getHeight(text) - yOffset);
/* 70:   */    
/* 73:73 */    this.unicodeFont.addGlyphs("~!@!#!#$%___--");
/* 74:   */  }
/* 75:   */  
/* 79:   */  public void update(GameContainer container, int delta)
/* 80:   */    throws SlickException
/* 81:   */  {
/* 82:82 */    this.unicodeFont.loadGlyphs(1);
/* 83:   */  }
/* 84:   */  
/* 90:   */  public static void main(String[] args)
/* 91:   */    throws SlickException, IOException
/* 92:   */  {
/* 93:93 */    Input.disableControllers();
/* 94:94 */    AppGameContainer container = new AppGameContainer(new UnicodeFontTest());
/* 95:95 */    container.setDisplayMode(512, 600, false);
/* 96:96 */    container.setTargetFrameRate(20);
/* 97:97 */    container.start();
/* 98:   */  }
/* 99:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.UnicodeFontTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */