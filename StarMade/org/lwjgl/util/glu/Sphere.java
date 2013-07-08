package org.lwjgl.util.glu;

import org.lwjgl.opengl.GL11;

public class Sphere
  extends Quadric
{
  public void draw(float radius, int slices, int stacks)
  {
    boolean normals = this.normals != 100002;
    float nsign;
    float nsign;
    if (this.orientation == 100021) {
      nsign = -1.0F;
    } else {
      nsign = 1.0F;
    }
    float drho = 3.141593F / stacks;
    float dtheta = 6.283186F / slices;
    if (this.drawStyle == 100012)
    {
      if (!this.textureFlag)
      {
        GL11.glBegin(6);
        GL11.glNormal3f(0.0F, 0.0F, 1.0F);
        GL11.glVertex3f(0.0F, 0.0F, nsign * radius);
        for (int local_j = 0; local_j <= slices; local_j++)
        {
          float theta = local_j == slices ? 0.0F : local_j * dtheta;
          float local_x = -sin(theta) * sin(drho);
          float local_y = cos(theta) * sin(drho);
          float local_z = nsign * cos(drho);
          if (normals) {
            GL11.glNormal3f(local_x * nsign, local_y * nsign, local_z * nsign);
          }
          GL11.glVertex3f(local_x * radius, local_y * radius, local_z * radius);
        }
        GL11.glEnd();
      }
      float local_ds = 1.0F / slices;
      float local_dt = 1.0F / stacks;
      float local_t = 1.0F;
      int imax;
      int imin;
      int imax;
      if (this.textureFlag)
      {
        int imin = 0;
        imax = stacks;
      }
      else
      {
        imin = 1;
        imax = stacks - 1;
      }
      for (int local_i = imin; local_i < imax; local_i++)
      {
        float rho = local_i * drho;
        GL11.glBegin(8);
        float local_s = 0.0F;
        for (int local_j = 0; local_j <= slices; local_j++)
        {
          float theta = local_j == slices ? 0.0F : local_j * dtheta;
          float local_x = -sin(theta) * sin(rho);
          float local_y = cos(theta) * sin(rho);
          float local_z = nsign * cos(rho);
          if (normals) {
            GL11.glNormal3f(local_x * nsign, local_y * nsign, local_z * nsign);
          }
          TXTR_COORD(local_s, local_t);
          GL11.glVertex3f(local_x * radius, local_y * radius, local_z * radius);
          local_x = -sin(theta) * sin(rho + drho);
          local_y = cos(theta) * sin(rho + drho);
          local_z = nsign * cos(rho + drho);
          if (normals) {
            GL11.glNormal3f(local_x * nsign, local_y * nsign, local_z * nsign);
          }
          TXTR_COORD(local_s, local_t - local_dt);
          local_s += local_ds;
          GL11.glVertex3f(local_x * radius, local_y * radius, local_z * radius);
        }
        GL11.glEnd();
        local_t -= local_dt;
      }
      if (!this.textureFlag)
      {
        GL11.glBegin(6);
        GL11.glNormal3f(0.0F, 0.0F, -1.0F);
        GL11.glVertex3f(0.0F, 0.0F, -radius * nsign);
        float rho = 3.141593F - drho;
        float local_s = 1.0F;
        for (int local_j = slices; local_j >= 0; local_j--)
        {
          float theta = local_j == slices ? 0.0F : local_j * dtheta;
          float local_x = -sin(theta) * sin(rho);
          float local_y = cos(theta) * sin(rho);
          float local_z = nsign * cos(rho);
          if (normals) {
            GL11.glNormal3f(local_x * nsign, local_y * nsign, local_z * nsign);
          }
          local_s -= local_ds;
          GL11.glVertex3f(local_x * radius, local_y * radius, local_z * radius);
        }
        GL11.glEnd();
      }
    }
    else
    {
      int local_i;
      int local_j;
      if ((this.drawStyle == 100011) || (this.drawStyle == 100013))
      {
        for (local_i = 1; local_i < stacks; local_i++)
        {
          float rho = local_i * drho;
          GL11.glBegin(2);
          for (int local_j = 0; local_j < slices; local_j++)
          {
            float theta = local_j * dtheta;
            float local_x = cos(theta) * sin(rho);
            float local_y = sin(theta) * sin(rho);
            float local_z = cos(rho);
            if (normals) {
              GL11.glNormal3f(local_x * nsign, local_y * nsign, local_z * nsign);
            }
            GL11.glVertex3f(local_x * radius, local_y * radius, local_z * radius);
          }
          GL11.glEnd();
        }
        local_j = 0;
      }
      while (local_j < slices)
      {
        float theta = local_j * dtheta;
        GL11.glBegin(3);
        for (local_i = 0; local_i <= stacks; local_i++)
        {
          float rho = local_i * drho;
          float local_x = cos(theta) * sin(rho);
          float local_y = sin(theta) * sin(rho);
          float local_z = cos(rho);
          if (normals) {
            GL11.glNormal3f(local_x * nsign, local_y * nsign, local_z * nsign);
          }
          GL11.glVertex3f(local_x * radius, local_y * radius, local_z * radius);
        }
        GL11.glEnd();
        local_j++;
        continue;
        if (this.drawStyle == 100010)
        {
          GL11.glBegin(0);
          if (normals) {
            GL11.glNormal3f(0.0F, 0.0F, nsign);
          }
          GL11.glVertex3f(0.0F, 0.0F, radius);
          if (normals) {
            GL11.glNormal3f(0.0F, 0.0F, -nsign);
          }
          GL11.glVertex3f(0.0F, 0.0F, -radius);
          for (int local_i = 1; local_i < stacks - 1; local_i++)
          {
            float rho = local_i * drho;
            for (int local_j = 0; local_j < slices; local_j++)
            {
              float theta = local_j * dtheta;
              float local_x = cos(theta) * sin(rho);
              float local_y = sin(theta) * sin(rho);
              float local_z = cos(rho);
              if (normals) {
                GL11.glNormal3f(local_x * nsign, local_y * nsign, local_z * nsign);
              }
              GL11.glVertex3f(local_x * radius, local_y * radius, local_z * radius);
            }
          }
          GL11.glEnd();
        }
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.glu.Sphere
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */