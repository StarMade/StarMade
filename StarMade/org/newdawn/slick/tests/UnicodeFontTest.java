package org.newdawn.slick.tests;

import java.io.IOException;
import java.util.List;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class UnicodeFontTest
  extends BasicGame
{
  private UnicodeFont unicodeFont;
  
  public UnicodeFontTest()
  {
    super("Font Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    container.setShowFPS(false);
    this.unicodeFont = new UnicodeFont("c:/windows/fonts/arial.ttf", 48, false, false);
    this.unicodeFont.getEffects().add(new ColorEffect(java.awt.Color.white));
    container.getGraphics().setBackground(org.newdawn.slick.Color.darkGray);
  }
  
  public void render(GameContainer container, Graphics local_g)
  {
    local_g.setColor(org.newdawn.slick.Color.white);
    String text = "This is UnicodeFont!\nIt rockz. Kerning: T,";
    this.unicodeFont.drawString(10.0F, 33.0F, text);
    local_g.setColor(org.newdawn.slick.Color.red);
    local_g.drawRect(10.0F, 33.0F, this.unicodeFont.getWidth(text), this.unicodeFont.getLineHeight());
    local_g.setColor(org.newdawn.slick.Color.blue);
    int yOffset = this.unicodeFont.getYOffset(text);
    local_g.drawRect(10.0F, 33 + yOffset, this.unicodeFont.getWidth(text), this.unicodeFont.getHeight(text) - yOffset);
    this.unicodeFont.addGlyphs("~!@!#!#$%___--");
  }
  
  public void update(GameContainer container, int delta)
    throws SlickException
  {
    this.unicodeFont.loadGlyphs(1);
  }
  
  public static void main(String[] args)
    throws SlickException, IOException
  {
    Input.disableControllers();
    AppGameContainer container = new AppGameContainer(new UnicodeFontTest());
    container.setDisplayMode(512, 600, false);
    container.setTargetFrameRate(20);
    container.start();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.UnicodeFontTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */