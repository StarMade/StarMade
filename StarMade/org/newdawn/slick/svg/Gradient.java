package org.newdawn.slick.svg;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;
import org.newdawn.slick.geom.Transform;

public class Gradient
{
  private String name;
  private ArrayList steps = new ArrayList();
  private float field_1655;
  private float field_1656;
  private float field_1657;
  private float field_1658;
  private float field_1659;
  private Image image;
  private boolean radial;
  private Transform transform;
  private String ref;
  
  public Gradient(String name, boolean radial)
  {
    this.name = name;
    this.radial = radial;
  }
  
  public boolean isRadial()
  {
    return this.radial;
  }
  
  public void setTransform(Transform trans)
  {
    this.transform = trans;
  }
  
  public Transform getTransform()
  {
    return this.transform;
  }
  
  public void reference(String ref)
  {
    this.ref = ref;
  }
  
  public void resolve(Diagram diagram)
  {
    if (this.ref == null) {
      return;
    }
    Gradient other = diagram.getGradient(this.ref);
    for (int local_i = 0; local_i < other.steps.size(); local_i++) {
      this.steps.add(other.steps.get(local_i));
    }
  }
  
  public void genImage()
  {
    if (this.image == null)
    {
      ImageBuffer buffer = new ImageBuffer(128, 16);
      for (int local_i = 0; local_i < 128; local_i++)
      {
        Color col = getColorAt(local_i / 128.0F);
        for (int local_j = 0; local_j < 16; local_j++) {
          buffer.setRGBA(local_i, local_j, col.getRedByte(), col.getGreenByte(), col.getBlueByte(), col.getAlphaByte());
        }
      }
      this.image = buffer.getImage();
    }
  }
  
  public Image getImage()
  {
    genImage();
    return this.image;
  }
  
  public void setR(float local_r)
  {
    this.field_1659 = local_r;
  }
  
  public void setX1(float local_x1)
  {
    this.field_1655 = local_x1;
  }
  
  public void setX2(float local_x2)
  {
    this.field_1656 = local_x2;
  }
  
  public void setY1(float local_y1)
  {
    this.field_1657 = local_y1;
  }
  
  public void setY2(float local_y2)
  {
    this.field_1658 = local_y2;
  }
  
  public float getR()
  {
    return this.field_1659;
  }
  
  public float getX1()
  {
    return this.field_1655;
  }
  
  public float getX2()
  {
    return this.field_1656;
  }
  
  public float getY1()
  {
    return this.field_1657;
  }
  
  public float getY2()
  {
    return this.field_1658;
  }
  
  public void addStep(float location, Color local_c)
  {
    this.steps.add(new Step(location, local_c));
  }
  
  public Color getColorAt(float local_p)
  {
    if (local_p <= 0.0F) {
      return ((Step)this.steps.get(0)).col;
    }
    if (local_p > 1.0F) {
      return ((Step)this.steps.get(this.steps.size() - 1)).col;
    }
    for (int local_i = 1; local_i < this.steps.size(); local_i++)
    {
      Step prev = (Step)this.steps.get(local_i - 1);
      Step current = (Step)this.steps.get(local_i);
      if (local_p <= current.location)
      {
        float dis = current.location - prev.location;
        local_p -= prev.location;
        float local_v = local_p / dis;
        Color local_c = new Color(1, 1, 1, 1);
        local_c.field_1779 = (prev.col.field_1779 * (1.0F - local_v) + current.col.field_1779 * local_v);
        local_c.field_1776 = (prev.col.field_1776 * (1.0F - local_v) + current.col.field_1776 * local_v);
        local_c.field_1777 = (prev.col.field_1777 * (1.0F - local_v) + current.col.field_1777 * local_v);
        local_c.field_1778 = (prev.col.field_1778 * (1.0F - local_v) + current.col.field_1778 * local_v);
        return local_c;
      }
    }
    return Color.black;
  }
  
  private class Step
  {
    float location;
    Color col;
    
    public Step(float location, Color local_c)
    {
      this.location = location;
      this.col = local_c;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.svg.Gradient
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */