package org.newdawn.slick.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class MouseOverArea
  extends AbstractComponent
{
  private static final int NORMAL = 1;
  private static final int MOUSE_DOWN = 2;
  private static final int MOUSE_OVER = 3;
  private Image normalImage;
  private Image mouseOverImage;
  private Image mouseDownImage;
  private Color normalColor = Color.white;
  private Color mouseOverColor = Color.white;
  private Color mouseDownColor = Color.white;
  private Sound mouseOverSound;
  private Sound mouseDownSound;
  private Shape area;
  private Image currentImage;
  private Color currentColor;
  private boolean over;
  private boolean mouseDown;
  private int state = 1;
  private boolean mouseUp;
  
  public MouseOverArea(GUIContext container, Image image, int local_x, int local_y, ComponentListener listener)
  {
    this(container, image, local_x, local_y, image.getWidth(), image.getHeight());
    addListener(listener);
  }
  
  public MouseOverArea(GUIContext container, Image image, int local_x, int local_y)
  {
    this(container, image, local_x, local_y, image.getWidth(), image.getHeight());
  }
  
  public MouseOverArea(GUIContext container, Image image, int local_x, int local_y, int width, int height, ComponentListener listener)
  {
    this(container, image, local_x, local_y, width, height);
    addListener(listener);
  }
  
  public MouseOverArea(GUIContext container, Image image, int local_x, int local_y, int width, int height)
  {
    this(container, image, new Rectangle(local_x, local_y, width, height));
  }
  
  public MouseOverArea(GUIContext container, Image image, Shape shape)
  {
    super(container);
    this.area = shape;
    this.normalImage = image;
    this.currentImage = image;
    this.mouseOverImage = image;
    this.mouseDownImage = image;
    this.currentColor = this.normalColor;
    this.state = 1;
    Input input = container.getInput();
    this.over = this.area.contains(input.getMouseX(), input.getMouseY());
    this.mouseDown = input.isMouseButtonDown(0);
    updateImage();
  }
  
  public void setLocation(float local_x, float local_y)
  {
    if (this.area != null)
    {
      this.area.setX(local_x);
      this.area.setY(local_y);
    }
  }
  
  public void setX(float local_x)
  {
    this.area.setX(local_x);
  }
  
  public void setY(float local_y)
  {
    this.area.setY(local_y);
  }
  
  public int getX()
  {
    return (int)this.area.getX();
  }
  
  public int getY()
  {
    return (int)this.area.getY();
  }
  
  public void setNormalColor(Color color)
  {
    this.normalColor = color;
  }
  
  public void setMouseOverColor(Color color)
  {
    this.mouseOverColor = color;
  }
  
  public void setMouseDownColor(Color color)
  {
    this.mouseDownColor = color;
  }
  
  public void setNormalImage(Image image)
  {
    this.normalImage = image;
  }
  
  public void setMouseOverImage(Image image)
  {
    this.mouseOverImage = image;
  }
  
  public void setMouseDownImage(Image image)
  {
    this.mouseDownImage = image;
  }
  
  public void render(GUIContext container, Graphics local_g)
  {
    if (this.currentImage != null)
    {
      int local_xp = (int)(this.area.getX() + (getWidth() - this.currentImage.getWidth()) / 2);
      int local_yp = (int)(this.area.getY() + (getHeight() - this.currentImage.getHeight()) / 2);
      this.currentImage.draw(local_xp, local_yp, this.currentColor);
    }
    else
    {
      local_g.setColor(this.currentColor);
      local_g.fill(this.area);
    }
    updateImage();
  }
  
  private void updateImage()
  {
    if (!this.over)
    {
      this.currentImage = this.normalImage;
      this.currentColor = this.normalColor;
      this.state = 1;
      this.mouseUp = false;
    }
    else
    {
      if (this.mouseDown)
      {
        if ((this.state != 2) && (this.mouseUp))
        {
          if (this.mouseDownSound != null) {
            this.mouseDownSound.play();
          }
          this.currentImage = this.mouseDownImage;
          this.currentColor = this.mouseDownColor;
          this.state = 2;
          notifyListeners();
          this.mouseUp = false;
        }
        return;
      }
      this.mouseUp = true;
      if (this.state != 3)
      {
        if (this.mouseOverSound != null) {
          this.mouseOverSound.play();
        }
        this.currentImage = this.mouseOverImage;
        this.currentColor = this.mouseOverColor;
        this.state = 3;
      }
    }
    this.mouseDown = false;
    this.state = 1;
  }
  
  public void setMouseOverSound(Sound sound)
  {
    this.mouseOverSound = sound;
  }
  
  public void setMouseDownSound(Sound sound)
  {
    this.mouseDownSound = sound;
  }
  
  public void mouseMoved(int oldx, int oldy, int newx, int newy)
  {
    this.over = this.area.contains(newx, newy);
  }
  
  public void mouseDragged(int oldx, int oldy, int newx, int newy)
  {
    mouseMoved(oldx, oldy, newx, newy);
  }
  
  public void mousePressed(int button, int local_mx, int local_my)
  {
    this.over = this.area.contains(local_mx, local_my);
    if (button == 0) {
      this.mouseDown = true;
    }
  }
  
  public void mouseReleased(int button, int local_mx, int local_my)
  {
    this.over = this.area.contains(local_mx, local_my);
    if (button == 0) {
      this.mouseDown = false;
    }
  }
  
  public int getHeight()
  {
    return (int)(this.area.getMaxY() - this.area.getY());
  }
  
  public int getWidth()
  {
    return (int)(this.area.getMaxX() - this.area.getX());
  }
  
  public boolean isMouseOver()
  {
    return this.over;
  }
  
  public void setLocation(int local_x, int local_y)
  {
    setLocation(local_x, local_y);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.gui.MouseOverArea
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */