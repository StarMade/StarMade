package org.newdawn.slick.gui;

import org.lwjgl.Sys;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

public class TextField
  extends AbstractComponent
{
  private static final int INITIAL_KEY_REPEAT_INTERVAL = 400;
  private static final int KEY_REPEAT_INTERVAL = 50;
  private int width;
  private int height;
  protected int field_50;
  protected int field_51;
  private int maxCharacter = 10000;
  private String value = "";
  private Font font;
  private Color border = Color.white;
  private Color text = Color.white;
  private Color background = new Color(0.0F, 0.0F, 0.0F, 0.5F);
  private int cursorPos;
  private boolean visibleCursor = true;
  private int lastKey = -1;
  private char lastChar = '\000';
  private long repeatTimer;
  private String oldText;
  private int oldCursorPos;
  private boolean consume = true;
  
  public TextField(GUIContext container, Font font, int local_x, int local_y, int width, int height, ComponentListener listener)
  {
    this(container, font, local_x, local_y, width, height);
    addListener(listener);
  }
  
  public TextField(GUIContext container, Font font, int local_x, int local_y, int width, int height)
  {
    super(container);
    this.font = font;
    setLocation(local_x, local_y);
    this.width = width;
    this.height = height;
  }
  
  public void setConsumeEvents(boolean consume)
  {
    this.consume = consume;
  }
  
  public void deactivate()
  {
    setFocus(false);
  }
  
  public void setLocation(int local_x, int local_y)
  {
    this.field_50 = local_x;
    this.field_51 = local_y;
  }
  
  public int getX()
  {
    return this.field_50;
  }
  
  public int getY()
  {
    return this.field_51;
  }
  
  public int getWidth()
  {
    return this.width;
  }
  
  public int getHeight()
  {
    return this.height;
  }
  
  public void setBackgroundColor(Color color)
  {
    this.background = color;
  }
  
  public void setBorderColor(Color color)
  {
    this.border = color;
  }
  
  public void setTextColor(Color color)
  {
    this.text = color;
  }
  
  public void render(GUIContext container, Graphics local_g)
  {
    if (this.lastKey != -1) {
      if (this.input.isKeyDown(this.lastKey))
      {
        if (this.repeatTimer < System.currentTimeMillis())
        {
          this.repeatTimer = (System.currentTimeMillis() + 50L);
          keyPressed(this.lastKey, this.lastChar);
        }
      }
      else {
        this.lastKey = -1;
      }
    }
    Rectangle oldClip = local_g.getClip();
    local_g.setWorldClip(this.field_50, this.field_51, this.width, this.height);
    Color clr = local_g.getColor();
    if (this.background != null)
    {
      local_g.setColor(this.background.multiply(clr));
      local_g.fillRect(this.field_50, this.field_51, this.width, this.height);
    }
    local_g.setColor(this.text.multiply(clr));
    Font temp = local_g.getFont();
    int cpos = this.font.getWidth(this.value.substring(0, this.cursorPos));
    int local_tx = 0;
    if (cpos > this.width) {
      local_tx = this.width - cpos - this.font.getWidth("_");
    }
    local_g.translate(local_tx + 2, 0.0F);
    local_g.setFont(this.font);
    local_g.drawString(this.value, this.field_50 + 1, this.field_51 + 1);
    if ((hasFocus()) && (this.visibleCursor)) {
      local_g.drawString("_", this.field_50 + 1 + cpos + 2, this.field_51 + 1);
    }
    local_g.translate(-local_tx - 2, 0.0F);
    if (this.border != null)
    {
      local_g.setColor(this.border.multiply(clr));
      local_g.drawRect(this.field_50, this.field_51, this.width, this.height);
    }
    local_g.setColor(clr);
    local_g.setFont(temp);
    local_g.clearWorldClip();
    local_g.setClip(oldClip);
  }
  
  public String getText()
  {
    return this.value;
  }
  
  public void setText(String value)
  {
    this.value = value;
    if (this.cursorPos > value.length()) {
      this.cursorPos = value.length();
    }
  }
  
  public void setCursorPos(int pos)
  {
    this.cursorPos = pos;
    if (this.cursorPos > this.value.length()) {
      this.cursorPos = this.value.length();
    }
  }
  
  public void setCursorVisible(boolean visibleCursor)
  {
    this.visibleCursor = visibleCursor;
  }
  
  public void setMaxLength(int length)
  {
    this.maxCharacter = length;
    if (this.value.length() > this.maxCharacter) {
      this.value = this.value.substring(0, this.maxCharacter);
    }
  }
  
  protected void doPaste(String text)
  {
    recordOldPosition();
    for (int local_i = 0; local_i < text.length(); local_i++) {
      keyPressed(-1, text.charAt(local_i));
    }
  }
  
  protected void recordOldPosition()
  {
    this.oldText = getText();
    this.oldCursorPos = this.cursorPos;
  }
  
  protected void doUndo(int oldCursorPos, String oldText)
  {
    if (oldText != null)
    {
      setText(oldText);
      setCursorPos(oldCursorPos);
    }
  }
  
  public void keyPressed(int key, char local_c)
  {
    if (hasFocus())
    {
      if (key != -1)
      {
        if ((key == 47) && ((this.input.isKeyDown(29)) || (this.input.isKeyDown(157))))
        {
          String text = Sys.getClipboard();
          if (text != null) {
            doPaste(text);
          }
          return;
        }
        if ((key == 44) && ((this.input.isKeyDown(29)) || (this.input.isKeyDown(157))))
        {
          if (this.oldText != null) {
            doUndo(this.oldCursorPos, this.oldText);
          }
          return;
        }
        if ((this.input.isKeyDown(29)) || (this.input.isKeyDown(157))) {
          return;
        }
        if ((this.input.isKeyDown(56)) || (this.input.isKeyDown(184))) {
          return;
        }
      }
      if (this.lastKey != key)
      {
        this.lastKey = key;
        this.repeatTimer = (System.currentTimeMillis() + 400L);
      }
      else
      {
        this.repeatTimer = (System.currentTimeMillis() + 50L);
      }
      this.lastChar = local_c;
      if (key == 203)
      {
        if (this.cursorPos > 0) {
          this.cursorPos -= 1;
        }
        if (this.consume) {
          this.container.getInput().consumeEvent();
        }
      }
      else if (key == 205)
      {
        if (this.cursorPos < this.value.length()) {
          this.cursorPos += 1;
        }
        if (this.consume) {
          this.container.getInput().consumeEvent();
        }
      }
      else if (key == 14)
      {
        if ((this.cursorPos > 0) && (this.value.length() > 0))
        {
          if (this.cursorPos < this.value.length()) {
            this.value = (this.value.substring(0, this.cursorPos - 1) + this.value.substring(this.cursorPos));
          } else {
            this.value = this.value.substring(0, this.cursorPos - 1);
          }
          this.cursorPos -= 1;
        }
        if (this.consume) {
          this.container.getInput().consumeEvent();
        }
      }
      else if (key == 211)
      {
        if (this.value.length() > this.cursorPos) {
          this.value = (this.value.substring(0, this.cursorPos) + this.value.substring(this.cursorPos + 1));
        }
        if (this.consume) {
          this.container.getInput().consumeEvent();
        }
      }
      else if ((local_c < '') && (local_c > '\037') && (this.value.length() < this.maxCharacter))
      {
        if (this.cursorPos < this.value.length()) {
          this.value = (this.value.substring(0, this.cursorPos) + local_c + this.value.substring(this.cursorPos));
        } else {
          this.value = (this.value.substring(0, this.cursorPos) + local_c);
        }
        this.cursorPos += 1;
        if (this.consume) {
          this.container.getInput().consumeEvent();
        }
      }
      else if (key == 28)
      {
        notifyListeners();
        if (this.consume) {
          this.container.getInput().consumeEvent();
        }
      }
    }
  }
  
  public void setFocus(boolean focus)
  {
    this.lastKey = -1;
    super.setFocus(focus);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.gui.TextField
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */