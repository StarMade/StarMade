package org.lwjgl.util.glu;

import org.lwjgl.opengl.GL11;

public class Cylinder
  extends Quadric
{
  public void draw(float baseRadius, float topRadius, float height, int slices, int stacks)
  {
    float nsign;
    float nsign;
    if (this.orientation == 100021) {
      nsign = -1.0F;
    } else {
      nsign = 1.0F;
    }
    float local_da = 6.283186F / slices;
    float local_dr = (topRadius - baseRadius) / stacks;
    float local_dz = height / stacks;
    float local_nz = (baseRadius - topRadius) / height;
    if (this.drawStyle == 100010)
    {
      GL11.glBegin(0);
      for (int local_i = 0; local_i < slices; local_i++)
      {
        float local_x = cos(local_i * local_da);
        float local_y = sin(local_i * local_da);
        normal3f(local_x * nsign, local_y * nsign, local_nz * nsign);
        float local_z = 0.0F;
        float local_r = baseRadius;
        for (int local_j = 0; local_j <= stacks; local_j++)
        {
          GL11.glVertex3f(local_x * local_r, local_y * local_r, local_z);
          local_z += local_dz;
          local_r += local_dr;
        }
      }
      GL11.glEnd();
    }
    else if ((this.drawStyle == 100011) || (this.drawStyle == 100013))
    {
      if (this.drawStyle == 100011)
      {
        float local_z = 0.0F;
        float local_r = baseRadius;
        for (int local_j = 0; local_j <= stacks; local_j++)
        {
          GL11.glBegin(2);
          for (int local_i = 0; local_i < slices; local_i++)
          {
            float local_x = cos(local_i * local_da);
            float local_y = sin(local_i * local_da);
            normal3f(local_x * nsign, local_y * nsign, local_nz * nsign);
            GL11.glVertex3f(local_x * local_r, local_y * local_r, local_z);
          }
          GL11.glEnd();
          local_z += local_dz;
          local_r += local_dr;
        }
      }
      if (baseRadius != 0.0D)
      {
        GL11.glBegin(2);
        for (int local_i = 0; local_i < slices; local_i++)
        {
          float local_x = cos(local_i * local_da);
          float local_y = sin(local_i * local_da);
          normal3f(local_x * nsign, local_y * nsign, local_nz * nsign);
          GL11.glVertex3f(local_x * baseRadius, local_y * baseRadius, 0.0F);
        }
        GL11.glEnd();
        GL11.glBegin(2);
        for (local_i = 0; local_i < slices; local_i++)
        {
          float local_x = cos(local_i * local_da);
          float local_y = sin(local_i * local_da);
          normal3f(local_x * nsign, local_y * nsign, local_nz * nsign);
          GL11.glVertex3f(local_x * topRadius, local_y * topRadius, height);
        }
        GL11.glEnd();
      }
      GL11.glBegin(1);
      for (int local_i = 0; local_i < slices; local_i++)
      {
        float local_x = cos(local_i * local_da);
        float local_y = sin(local_i * local_da);
        normal3f(local_x * nsign, local_y * nsign, local_nz * nsign);
        GL11.glVertex3f(local_x * baseRadius, local_y * baseRadius, 0.0F);
        GL11.glVertex3f(local_x * topRadius, local_y * topRadius, height);
      }
      GL11.glEnd();
    }
    else if (this.drawStyle == 100012)
    {
      float local_ds = 1.0F / slices;
      float local_dt = 1.0F / stacks;
      float local_t = 0.0F;
      float local_z = 0.0F;
      float local_r = baseRadius;
      for (int local_j = 0; local_j < stacks; local_j++)
      {
        float local_s = 0.0F;
        GL11.glBegin(8);
        for (int local_i = 0; local_i <= slices; local_i++)
        {
          float local_y;
          float local_x;
          float local_y;
          if (local_i == slices)
          {
            float local_x = sin(0.0F);
            local_y = cos(0.0F);
          }
          else
          {
            local_x = sin(local_i * local_da);
            local_y = cos(local_i * local_da);
          }
          if (nsign == 1.0F)
          {
            normal3f(local_x * nsign, local_y * nsign, local_nz * nsign);
            TXTR_COORD(local_s, local_t);
            GL11.glVertex3f(local_x * local_r, local_y * local_r, local_z);
            normal3f(local_x * nsign, local_y * nsign, local_nz * nsign);
            TXTR_COORD(local_s, local_t + local_dt);
            GL11.glVertex3f(local_x * (local_r + local_dr), local_y * (local_r + local_dr), local_z + local_dz);
          }
          else
          {
            normal3f(local_x * nsign, local_y * nsign, local_nz * nsign);
            TXTR_COORD(local_s, local_t);
            GL11.glVertex3f(local_x * local_r, local_y * local_r, local_z);
            normal3f(local_x * nsign, local_y * nsign, local_nz * nsign);
            TXTR_COORD(local_s, local_t + local_dt);
            GL11.glVertex3f(local_x * (local_r + local_dr), local_y * (local_r + local_dr), local_z + local_dz);
          }
          local_s += local_ds;
        }
        GL11.glEnd();
        local_r += local_dr;
        local_t += local_dt;
        local_z += local_dz;
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.glu.Cylinder
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */