package org.newdawn.slick.tests.states;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class TestState2
  extends BasicGameState
{
  public static final int field_265 = 2;
  private Font font;
  private Image image;
  private float ang;
  private StateBasedGame game;
  
  public int getID()
  {
    return 2;
  }
  
  public void init(GameContainer container, StateBasedGame game)
    throws SlickException
  {
    this.game = game;
    this.font = new AngelCodeFont("testdata/demo2.fnt", "testdata/demo2_00.tga");
    this.image = new Image("testdata/logo.tga");
  }
  
  public void render(GameContainer container, StateBasedGame game, Graphics local_g)
  {
    local_g.setFont(this.font);
    local_g.setColor(Color.green);
    local_g.drawString("This is State 2", 200.0F, 50.0F);
    local_g.rotate(400.0F, 300.0F, this.ang);
    local_g.drawImage(this.image, 400 - this.image.getWidth() / 2, 300 - this.image.getHeight() / 2);
  }
  
  public void update(GameContainer container, StateBasedGame game, int delta)
  {
    this.ang += delta * 0.1F;
  }
  
  public void keyReleased(int key, char local_c)
  {
    if (key == 2) {
      this.game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
    }
    if (key == 4) {
      this.game.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.states.TestState2
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */