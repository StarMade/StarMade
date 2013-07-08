/*  1:   */package org.newdawn.slick.state.transition;
/*  2:   */
/*  3:   */import java.util.ArrayList;
/*  4:   */import org.newdawn.slick.GameContainer;
/*  5:   */import org.newdawn.slick.Graphics;
/*  6:   */import org.newdawn.slick.SlickException;
/*  7:   */import org.newdawn.slick.state.GameState;
/*  8:   */import org.newdawn.slick.state.StateBasedGame;
/*  9:   */
/* 16:   */public class CombinedTransition
/* 17:   */  implements Transition
/* 18:   */{
/* 19:19 */  private ArrayList transitions = new ArrayList();
/* 20:   */  
/* 32:   */  public void addTransition(Transition t)
/* 33:   */  {
/* 34:34 */    this.transitions.add(t);
/* 35:   */  }
/* 36:   */  
/* 39:   */  public boolean isComplete()
/* 40:   */  {
/* 41:41 */    for (int i = 0; i < this.transitions.size(); i++) {
/* 42:42 */      if (!((Transition)this.transitions.get(i)).isComplete()) {
/* 43:43 */        return false;
/* 44:   */      }
/* 45:   */    }
/* 46:   */    
/* 47:47 */    return true;
/* 48:   */  }
/* 49:   */  
/* 51:   */  public void postRender(StateBasedGame game, GameContainer container, Graphics g)
/* 52:   */    throws SlickException
/* 53:   */  {
/* 54:54 */    for (int i = this.transitions.size() - 1; i >= 0; i--) {
/* 55:55 */      ((Transition)this.transitions.get(i)).postRender(game, container, g);
/* 56:   */    }
/* 57:   */  }
/* 58:   */  
/* 60:   */  public void preRender(StateBasedGame game, GameContainer container, Graphics g)
/* 61:   */    throws SlickException
/* 62:   */  {
/* 63:63 */    for (int i = 0; i < this.transitions.size(); i++) {
/* 64:64 */      ((Transition)this.transitions.get(i)).postRender(game, container, g);
/* 65:   */    }
/* 66:   */  }
/* 67:   */  
/* 69:   */  public void update(StateBasedGame game, GameContainer container, int delta)
/* 70:   */    throws SlickException
/* 71:   */  {
/* 72:72 */    for (int i = 0; i < this.transitions.size(); i++) {
/* 73:73 */      Transition t = (Transition)this.transitions.get(i);
/* 74:   */      
/* 75:75 */      if (!t.isComplete()) {
/* 76:76 */        t.update(game, container, delta);
/* 77:   */      }
/* 78:   */    }
/* 79:   */  }
/* 80:   */  
/* 81:   */  public void init(GameState firstState, GameState secondState) {
/* 82:82 */    for (int i = this.transitions.size() - 1; i >= 0; i--) {
/* 83:83 */      ((Transition)this.transitions.get(i)).init(firstState, secondState);
/* 84:   */    }
/* 85:   */  }
/* 86:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.state.transition.CombinedTransition
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */