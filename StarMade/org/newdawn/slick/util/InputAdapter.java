/*  1:   */package org.newdawn.slick.util;
/*  2:   */
/*  3:   */import org.newdawn.slick.Input;
/*  4:   */import org.newdawn.slick.InputListener;
/*  5:   */
/* 10:   */public class InputAdapter
/* 11:   */  implements InputListener
/* 12:   */{
/* 13:13 */  private boolean acceptingInput = true;
/* 14:   */  
/* 19:   */  public void controllerButtonPressed(int controller, int button) {}
/* 20:   */  
/* 25:   */  public void controllerButtonReleased(int controller, int button) {}
/* 26:   */  
/* 31:   */  public void controllerDownPressed(int controller) {}
/* 32:   */  
/* 37:   */  public void controllerDownReleased(int controller) {}
/* 38:   */  
/* 43:   */  public void controllerLeftPressed(int controller) {}
/* 44:   */  
/* 49:   */  public void controllerLeftReleased(int controller) {}
/* 50:   */  
/* 55:   */  public void controllerRightPressed(int controller) {}
/* 56:   */  
/* 61:   */  public void controllerRightReleased(int controller) {}
/* 62:   */  
/* 67:   */  public void controllerUpPressed(int controller) {}
/* 68:   */  
/* 73:   */  public void controllerUpReleased(int controller) {}
/* 74:   */  
/* 78:   */  public void inputEnded() {}
/* 79:   */  
/* 83:   */  public boolean isAcceptingInput()
/* 84:   */  {
/* 85:85 */    return this.acceptingInput;
/* 86:   */  }
/* 87:   */  
/* 92:   */  public void setAcceptingInput(boolean acceptingInput)
/* 93:   */  {
/* 94:94 */    this.acceptingInput = acceptingInput;
/* 95:   */  }
/* 96:   */  
/* 97:   */  public void keyPressed(int key, char c) {}
/* 98:   */  
/* 99:   */  public void keyReleased(int key, char c) {}
/* 100:   */  
/* 101:   */  public void mouseMoved(int oldx, int oldy, int newx, int newy) {}
/* 102:   */  
/* 103:   */  public void mousePressed(int button, int x, int y) {}
/* 104:   */  
/* 105:   */  public void mouseReleased(int button, int x, int y) {}
/* 106:   */  
/* 107:   */  public void mouseWheelMoved(int change) {}
/* 108:   */  
/* 109:   */  public void setInput(Input input) {}
/* 110:   */  
/* 111:   */  public void mouseClicked(int button, int x, int y, int clickCount) {}
/* 112:   */  
/* 113:   */  public void mouseDragged(int oldx, int oldy, int newx, int newy) {}
/* 114:   */  
/* 115:   */  public void inputStarted() {}
/* 116:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.InputAdapter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */