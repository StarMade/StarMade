/*   1:    */package org.newdawn.slick;
/*   2:    */
/*   5:    */public abstract class BasicGame
/*   6:    */  implements Game, InputListener
/*   7:    */{
/*   8:    */  private static final int MAX_CONTROLLERS = 20;
/*   9:    */  
/*  11:    */  private static final int MAX_CONTROLLER_BUTTONS = 100;
/*  12:    */  
/*  14:    */  private String title;
/*  15:    */  
/*  17: 17 */  protected boolean[] controllerLeft = new boolean[20];
/*  18:    */  
/*  19: 19 */  protected boolean[] controllerRight = new boolean[20];
/*  20:    */  
/*  21: 21 */  protected boolean[] controllerUp = new boolean[20];
/*  22:    */  
/*  23: 23 */  protected boolean[] controllerDown = new boolean[20];
/*  24:    */  
/*  25: 25 */  protected boolean[][] controllerButton = new boolean[20][100];
/*  26:    */  
/*  31:    */  public BasicGame(String title)
/*  32:    */  {
/*  33: 33 */    this.title = title;
/*  34:    */  }
/*  35:    */  
/*  39:    */  public void setInput(Input input) {}
/*  40:    */  
/*  44:    */  public boolean closeRequested()
/*  45:    */  {
/*  46: 46 */    return true;
/*  47:    */  }
/*  48:    */  
/*  51:    */  public String getTitle()
/*  52:    */  {
/*  53: 53 */    return this.title;
/*  54:    */  }
/*  55:    */  
/*  60:    */  public abstract void init(GameContainer paramGameContainer)
/*  61:    */    throws SlickException;
/*  62:    */  
/*  67:    */  public void keyPressed(int key, char c) {}
/*  68:    */  
/*  73:    */  public void keyReleased(int key, char c) {}
/*  74:    */  
/*  79:    */  public void mouseMoved(int oldx, int oldy, int newx, int newy) {}
/*  80:    */  
/*  85:    */  public void mouseDragged(int oldx, int oldy, int newx, int newy) {}
/*  86:    */  
/*  90:    */  public void mouseClicked(int button, int x, int y, int clickCount) {}
/*  91:    */  
/*  95:    */  public void mousePressed(int button, int x, int y) {}
/*  96:    */  
/* 100:    */  public void controllerButtonPressed(int controller, int button)
/* 101:    */  {
/* 102:102 */    this.controllerButton[controller][button] = 1;
/* 103:    */  }
/* 104:    */  
/* 107:    */  public void controllerButtonReleased(int controller, int button)
/* 108:    */  {
/* 109:109 */    this.controllerButton[controller][button] = 0;
/* 110:    */  }
/* 111:    */  
/* 114:    */  public void controllerDownPressed(int controller)
/* 115:    */  {
/* 116:116 */    this.controllerDown[controller] = true;
/* 117:    */  }
/* 118:    */  
/* 121:    */  public void controllerDownReleased(int controller)
/* 122:    */  {
/* 123:123 */    this.controllerDown[controller] = false;
/* 124:    */  }
/* 125:    */  
/* 128:    */  public void controllerLeftPressed(int controller)
/* 129:    */  {
/* 130:130 */    this.controllerLeft[controller] = true;
/* 131:    */  }
/* 132:    */  
/* 135:    */  public void controllerLeftReleased(int controller)
/* 136:    */  {
/* 137:137 */    this.controllerLeft[controller] = false;
/* 138:    */  }
/* 139:    */  
/* 142:    */  public void controllerRightPressed(int controller)
/* 143:    */  {
/* 144:144 */    this.controllerRight[controller] = true;
/* 145:    */  }
/* 146:    */  
/* 149:    */  public void controllerRightReleased(int controller)
/* 150:    */  {
/* 151:151 */    this.controllerRight[controller] = false;
/* 152:    */  }
/* 153:    */  
/* 156:    */  public void controllerUpPressed(int controller)
/* 157:    */  {
/* 158:158 */    this.controllerUp[controller] = true;
/* 159:    */  }
/* 160:    */  
/* 163:    */  public void controllerUpReleased(int controller)
/* 164:    */  {
/* 165:165 */    this.controllerUp[controller] = false;
/* 166:    */  }
/* 167:    */  
/* 171:    */  public void mouseReleased(int button, int x, int y) {}
/* 172:    */  
/* 176:    */  public abstract void update(GameContainer paramGameContainer, int paramInt)
/* 177:    */    throws SlickException;
/* 178:    */  
/* 182:    */  public void mouseWheelMoved(int change) {}
/* 183:    */  
/* 187:    */  public boolean isAcceptingInput()
/* 188:    */  {
/* 189:189 */    return true;
/* 190:    */  }
/* 191:    */  
/* 192:    */  public void inputEnded() {}
/* 193:    */  
/* 194:    */  public void inputStarted() {}
/* 195:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.BasicGame
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */