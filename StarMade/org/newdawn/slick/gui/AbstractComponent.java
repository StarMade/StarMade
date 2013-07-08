/*   1:    */package org.newdawn.slick.gui;
/*   2:    */
/*   3:    */import java.util.HashSet;
/*   4:    */import java.util.Iterator;
/*   5:    */import java.util.Set;
/*   6:    */import org.newdawn.slick.Graphics;
/*   7:    */import org.newdawn.slick.Input;
/*   8:    */import org.newdawn.slick.SlickException;
/*   9:    */import org.newdawn.slick.geom.Rectangle;
/*  10:    */import org.newdawn.slick.util.InputAdapter;
/*  11:    */
/*  18:    */public abstract class AbstractComponent
/*  19:    */  extends InputAdapter
/*  20:    */{
/*  21: 21 */  private static AbstractComponent currentFocus = null;
/*  22:    */  
/*  24:    */  protected GUIContext container;
/*  25:    */  
/*  27:    */  protected Set listeners;
/*  28:    */  
/*  30: 30 */  private boolean focus = false;
/*  31:    */  
/*  35:    */  protected Input input;
/*  36:    */  
/*  40:    */  public AbstractComponent(GUIContext container)
/*  41:    */  {
/*  42: 42 */    this.container = container;
/*  43:    */    
/*  44: 44 */    this.listeners = new HashSet();
/*  45:    */    
/*  46: 46 */    this.input = container.getInput();
/*  47: 47 */    this.input.addPrimaryListener(this);
/*  48:    */    
/*  49: 49 */    setLocation(0, 0);
/*  50:    */  }
/*  51:    */  
/*  59:    */  public void addListener(ComponentListener listener)
/*  60:    */  {
/*  61: 61 */    this.listeners.add(listener);
/*  62:    */  }
/*  63:    */  
/*  71:    */  public void removeListener(ComponentListener listener)
/*  72:    */  {
/*  73: 73 */    this.listeners.remove(listener);
/*  74:    */  }
/*  75:    */  
/*  78:    */  protected void notifyListeners()
/*  79:    */  {
/*  80: 80 */    Iterator it = this.listeners.iterator();
/*  81: 81 */    while (it.hasNext()) {
/*  82: 82 */      ((ComponentListener)it.next()).componentActivated(this);
/*  83:    */    }
/*  84:    */  }
/*  85:    */  
/*  93:    */  public abstract void render(GUIContext paramGUIContext, Graphics paramGraphics)
/*  94:    */    throws SlickException;
/*  95:    */  
/* 102:    */  public abstract void setLocation(int paramInt1, int paramInt2);
/* 103:    */  
/* 110:    */  public abstract int getX();
/* 111:    */  
/* 118:    */  public abstract int getY();
/* 119:    */  
/* 126:    */  public abstract int getWidth();
/* 127:    */  
/* 134:    */  public abstract int getHeight();
/* 135:    */  
/* 142:    */  public void setFocus(boolean focus)
/* 143:    */  {
/* 144:144 */    if (focus) {
/* 145:145 */      if (currentFocus != null) {
/* 146:146 */        currentFocus.setFocus(false);
/* 147:    */      }
/* 148:148 */      currentFocus = this;
/* 149:    */    }
/* 150:150 */    else if (currentFocus == this) {
/* 151:151 */      currentFocus = null;
/* 152:    */    }
/* 153:    */    
/* 154:154 */    this.focus = focus;
/* 155:    */  }
/* 156:    */  
/* 161:    */  public boolean hasFocus()
/* 162:    */  {
/* 163:163 */    return this.focus;
/* 164:    */  }
/* 165:    */  
/* 168:    */  protected void consumeEvent()
/* 169:    */  {
/* 170:170 */    this.input.consumeEvent();
/* 171:    */  }
/* 172:    */  
/* 177:    */  public void mouseReleased(int button, int x, int y)
/* 178:    */  {
/* 179:179 */    setFocus(Rectangle.contains(x, y, getX(), getY(), getWidth(), getHeight()));
/* 180:    */  }
/* 181:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.gui.AbstractComponent
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */