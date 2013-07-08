package org.newdawn.slick.state.transition;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class FadeInTransition
  implements Transition
{
  private Color color;
  private int fadeTime = 500;
  
  public FadeInTransition()
  {
    this(Color.black, 500);
  }
  
  public FadeInTransition(Color color)
  {
    this(color, 500);
  }
  
  public FadeInTransition(Color color, int fadeTime)
  {
    this.color = new Color(color);
    this.color.field_1779 = 1.0F;
    this.fadeTime = fadeTime;
  }
  
  public boolean isComplete()
  {
    return this.color.field_1779 <= 0.0F;
  }
  
  public void postRender(StateBasedGame game, GameContainer container, Graphics local_g)
  {
    Color old = local_g.getColor();
    local_g.setColor(this.color);
    local_g.fillRect(0.0F, 0.0F, container.getWidth() * 2, container.getHeight() * 2);
    local_g.setColor(old);
  }
  
  public void update(StateBasedGame game, GameContainer container, int delta)
  {
    this.color.field_1779 -= delta * (1.0F / this.fadeTime);
    if (this.color.field_1779 < 0.0F) {
      this.color.field_1779 = 0.0F;
    }
  }
  
  public void preRender(StateBasedGame game, GameContainer container, Graphics local_g) {}
  
  public void init(GameState firstState, GameState secondState) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.state.transition.FadeInTransition
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */