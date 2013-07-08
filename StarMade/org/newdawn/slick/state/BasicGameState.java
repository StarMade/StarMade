/*  1:   */package org.newdawn.slick.state;
/*  2:   */
/*  3:   */import org.newdawn.slick.GameContainer;
/*  4:   */import org.newdawn.slick.Input;
/*  5:   */import org.newdawn.slick.SlickException;
/*  6:   */
/* 18:   */public abstract class BasicGameState
/* 19:   */  implements GameState
/* 20:   */{
/* 21:   */  public void inputStarted() {}
/* 22:   */  
/* 23:   */  public boolean isAcceptingInput()
/* 24:   */  {
/* 25:25 */    return true;
/* 26:   */  }
/* 27:   */  
/* 28:   */  public void setInput(Input input) {}
/* 29:   */  
/* 30:   */  public void inputEnded() {}
/* 31:   */  
/* 32:   */  public abstract int getID();
/* 33:   */  
/* 34:   */  public void controllerButtonPressed(int controller, int button) {}
/* 35:   */  
/* 36:   */  public void controllerButtonReleased(int controller, int button) {}
/* 37:   */  
/* 38:   */  public void controllerDownPressed(int controller) {}
/* 39:   */  
/* 40:   */  public void controllerDownReleased(int controller) {}
/* 41:   */  
/* 42:   */  public void controllerLeftPressed(int controller) {}
/* 43:   */  
/* 44:   */  public void controllerLeftReleased(int controller) {}
/* 45:   */  
/* 46:   */  public void controllerRightPressed(int controller) {}
/* 47:   */  
/* 48:   */  public void controllerRightReleased(int controller) {}
/* 49:   */  
/* 50:   */  public void controllerUpPressed(int controller) {}
/* 51:   */  
/* 52:   */  public void controllerUpReleased(int controller) {}
/* 53:   */  
/* 54:   */  public void keyPressed(int key, char c) {}
/* 55:   */  
/* 56:   */  public void keyReleased(int key, char c) {}
/* 57:   */  
/* 58:   */  public void mouseMoved(int oldx, int oldy, int newx, int newy) {}
/* 59:   */  
/* 60:   */  public void mouseDragged(int oldx, int oldy, int newx, int newy) {}
/* 61:   */  
/* 62:   */  public void mouseClicked(int button, int x, int y, int clickCount) {}
/* 63:   */  
/* 64:   */  public void mousePressed(int button, int x, int y) {}
/* 65:   */  
/* 66:   */  public void mouseReleased(int button, int x, int y) {}
/* 67:   */  
/* 68:   */  public void enter(GameContainer container, StateBasedGame game)
/* 69:   */    throws SlickException
/* 70:   */  {}
/* 71:   */  
/* 72:   */  public void leave(GameContainer container, StateBasedGame game)
/* 73:   */    throws SlickException
/* 74:   */  {}
/* 75:   */  
/* 76:   */  public void mouseWheelMoved(int newValue) {}
/* 77:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.state.BasicGameState
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */