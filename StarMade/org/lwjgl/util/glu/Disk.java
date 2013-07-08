package org.lwjgl.util.glu;

import org.lwjgl.opengl.GL11;

public class Disk
  extends Quadric
{
  public void draw(float innerRadius, float outerRadius, int slices, int loops)
  {
    if (this.normals != 100002) {
      if (this.orientation == 100020) {
        GL11.glNormal3f(0.0F, 0.0F, 1.0F);
      } else {
        GL11.glNormal3f(0.0F, 0.0F, -1.0F);
      }
    }
    float local_da = 6.283186F / slices;
    float local_dr = (outerRadius - innerRadius) / loops;
    float dtc;
    float local_r1;
    int local_l;
    switch (this.drawStyle)
    {
    case 100012: 
      dtc = 2.0F * outerRadius;
      local_r1 = innerRadius;
      local_l = 0;
    }
    while (local_l < loops)
    {
      float local_r2 = local_r1 + local_dr;
      if (this.orientation == 100020)
      {
        GL11.glBegin(8);
        for (int local_s = 0; local_s <= slices; local_s++)
        {
          float local_a;
          float local_a;
          if (local_s == slices) {
            local_a = 0.0F;
          } else {
            local_a = local_s * local_da;
          }
          float local_sa = sin(local_a);
          float local_ca = cos(local_a);
          TXTR_COORD(0.5F + local_sa * local_r2 / dtc, 0.5F + local_ca * local_r2 / dtc);
          GL11.glVertex2f(local_r2 * local_sa, local_r2 * local_ca);
          TXTR_COORD(0.5F + local_sa * local_r1 / dtc, 0.5F + local_ca * local_r1 / dtc);
          GL11.glVertex2f(local_r1 * local_sa, local_r1 * local_ca);
        }
        GL11.glEnd();
      }
      else
      {
        GL11.glBegin(8);
        for (int local_s = slices; local_s >= 0; local_s--)
        {
          float local_a;
          float local_a;
          if (local_s == slices) {
            local_a = 0.0F;
          } else {
            local_a = local_s * local_da;
          }
          float local_sa = sin(local_a);
          float local_ca = cos(local_a);
          TXTR_COORD(0.5F - local_sa * local_r2 / dtc, 0.5F + local_ca * local_r2 / dtc);
          GL11.glVertex2f(local_r2 * local_sa, local_r2 * local_ca);
          TXTR_COORD(0.5F - local_sa * local_r1 / dtc, 0.5F + local_ca * local_r1 / dtc);
          GL11.glVertex2f(local_r1 * local_sa, local_r1 * local_ca);
        }
        GL11.glEnd();
      }
      local_r1 = local_r2;
      local_l++;
      continue;
      for (int dtc = 0; dtc <= loops; dtc++)
      {
        float local_ca = innerRadius + dtc * local_dr;
        GL11.glBegin(2);
        for (int local_sa = 0; local_sa < slices; local_sa++)
        {
          float local_r1 = local_sa * local_da;
          GL11.glVertex2f(local_ca * sin(local_r1), local_ca * cos(local_r1));
        }
        GL11.glEnd();
      }
      int local_sa = 0;
      while (local_sa < slices)
      {
        float local_ca = local_sa * local_da;
        float local_r1 = sin(local_ca);
        float local_l = cos(local_ca);
        GL11.glBegin(3);
        for (dtc = 0; dtc <= loops; dtc++)
        {
          float local_r2 = innerRadius + dtc * local_dr;
          GL11.glVertex2f(local_r2 * local_r1, local_r2 * local_l);
        }
        GL11.glEnd();
        local_sa++;
        continue;
        GL11.glBegin(0);
        for (int dtc = 0; dtc < slices; dtc++)
        {
          float local_sa = dtc * local_da;
          float local_ca = sin(local_sa);
          float local_r1 = cos(local_sa);
          for (int local_l = 0; local_l <= loops; local_l++)
          {
            float local_r2 = innerRadius * local_l * local_dr;
            GL11.glVertex2f(local_r2 * local_ca, local_r2 * local_r1);
          }
        }
        GL11.glEnd();
        break;
        if (innerRadius != 0.0D)
        {
          GL11.glBegin(2);
          for (float dtc = 0.0F; dtc < 6.283185482025147D; dtc += local_da)
          {
            float local_sa = innerRadius * sin(dtc);
            float local_ca = innerRadius * cos(dtc);
            GL11.glVertex2f(local_sa, local_ca);
          }
          GL11.glEnd();
        }
        GL11.glBegin(2);
        for (float dtc = 0.0F; dtc < 6.283186F; dtc += local_da)
        {
          float local_sa = outerRadius * sin(dtc);
          float local_ca = outerRadius * cos(dtc);
          GL11.glVertex2f(local_sa, local_ca);
        }
        GL11.glEnd();
        break;
        return;
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.glu.Disk
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */