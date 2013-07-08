package org.lwjgl.util.glu;

import java.io.PrintStream;
import org.lwjgl.opengl.GL11;

public class PartialDisk
  extends Quadric
{
  private static final int CACHE_SIZE = 240;
  
  public void draw(float innerRadius, float outerRadius, int slices, int loops, float startAngle, float sweepAngle)
  {
    float[] sinCache = new float['ð'];
    float[] cosCache = new float['ð'];
    float texLow = 0.0F;
    float texHigh = 0.0F;
    if (slices >= 240) {
      slices = 239;
    }
    if ((slices < 2) || (loops < 1) || (outerRadius <= 0.0F) || (innerRadius < 0.0F) || (innerRadius > outerRadius))
    {
      System.err.println("PartialDisk: GLU_INVALID_VALUE");
      return;
    }
    if (sweepAngle < -360.0F) {
      sweepAngle = 360.0F;
    }
    if (sweepAngle > 360.0F) {
      sweepAngle = 360.0F;
    }
    if (sweepAngle < 0.0F)
    {
      startAngle += sweepAngle;
      sweepAngle = -sweepAngle;
    }
    int slices2;
    int slices2;
    if (sweepAngle == 360.0F) {
      slices2 = slices;
    } else {
      slices2 = slices + 1;
    }
    float deltaRadius = outerRadius - innerRadius;
    float angleOffset = startAngle / 180.0F * 3.141593F;
    for (int local_i = 0; local_i <= slices; local_i++)
    {
      float angle = angleOffset + 3.141593F * sweepAngle / 180.0F * local_i / slices;
      sinCache[local_i] = sin(angle);
      cosCache[local_i] = cos(angle);
    }
    if (sweepAngle == 360.0F)
    {
      sinCache[slices] = sinCache[0];
      cosCache[slices] = cosCache[0];
    }
    switch (this.normals)
    {
    case 100000: 
    case 100001: 
      if (this.orientation == 100020) {
        GL11.glNormal3f(0.0F, 0.0F, 1.0F);
      } else {
        GL11.glNormal3f(0.0F, 0.0F, -1.0F);
      }
      break;
    }
    int finish;
    int local_j;
    switch (this.drawStyle)
    {
    case 100012: 
      if (innerRadius == 0.0F)
      {
        int finish = loops - 1;
        GL11.glBegin(6);
        if (this.textureFlag) {
          GL11.glTexCoord2f(0.5F, 0.5F);
        }
        GL11.glVertex3f(0.0F, 0.0F, 0.0F);
        float radiusLow = outerRadius - deltaRadius * ((loops - 1) / loops);
        if (this.textureFlag) {
          texLow = radiusLow / outerRadius / 2.0F;
        }
        if (this.orientation == 100020) {
          for (local_i = slices; local_i >= 0; local_i--)
          {
            if (this.textureFlag) {
              GL11.glTexCoord2f(texLow * sinCache[local_i] + 0.5F, texLow * cosCache[local_i] + 0.5F);
            }
            GL11.glVertex3f(radiusLow * sinCache[local_i], radiusLow * cosCache[local_i], 0.0F);
          }
        }
        for (local_i = 0; local_i <= slices; local_i++)
        {
          if (this.textureFlag) {
            GL11.glTexCoord2f(texLow * sinCache[local_i] + 0.5F, texLow * cosCache[local_i] + 0.5F);
          }
          GL11.glVertex3f(radiusLow * sinCache[local_i], radiusLow * cosCache[local_i], 0.0F);
        }
        GL11.glEnd();
      }
      else
      {
        finish = loops;
      }
      local_j = 0;
    case 100010: 
    case 100011: 
    case 100013: 
      while (local_j < finish)
      {
        float radiusLow = outerRadius - deltaRadius * (local_j / loops);
        float radiusHigh = outerRadius - deltaRadius * ((local_j + 1) / loops);
        if (this.textureFlag)
        {
          texLow = radiusLow / outerRadius / 2.0F;
          texHigh = radiusHigh / outerRadius / 2.0F;
        }
        GL11.glBegin(8);
        for (local_i = 0; local_i <= slices; local_i++) {
          if (this.orientation == 100020)
          {
            if (this.textureFlag) {
              GL11.glTexCoord2f(texLow * sinCache[local_i] + 0.5F, texLow * cosCache[local_i] + 0.5F);
            }
            GL11.glVertex3f(radiusLow * sinCache[local_i], radiusLow * cosCache[local_i], 0.0F);
            if (this.textureFlag) {
              GL11.glTexCoord2f(texHigh * sinCache[local_i] + 0.5F, texHigh * cosCache[local_i] + 0.5F);
            }
            GL11.glVertex3f(radiusHigh * sinCache[local_i], radiusHigh * cosCache[local_i], 0.0F);
          }
          else
          {
            if (this.textureFlag) {
              GL11.glTexCoord2f(texHigh * sinCache[local_i] + 0.5F, texHigh * cosCache[local_i] + 0.5F);
            }
            GL11.glVertex3f(radiusHigh * sinCache[local_i], radiusHigh * cosCache[local_i], 0.0F);
            if (this.textureFlag) {
              GL11.glTexCoord2f(texLow * sinCache[local_i] + 0.5F, texLow * cosCache[local_i] + 0.5F);
            }
            GL11.glVertex3f(radiusLow * sinCache[local_i], radiusLow * cosCache[local_i], 0.0F);
          }
        }
        GL11.glEnd();
        local_j++;
        continue;
        GL11.glBegin(0);
        for (local_i = 0; local_i < slices2; local_i++)
        {
          float sintemp = sinCache[local_i];
          float costemp = cosCache[local_i];
          for (local_j = 0; local_j <= loops; local_j++)
          {
            radiusLow = outerRadius - deltaRadius * (local_j / loops);
            if (this.textureFlag)
            {
              texLow = radiusLow / outerRadius / 2.0F;
              GL11.glTexCoord2f(texLow * sinCache[local_i] + 0.5F, texLow * cosCache[local_i] + 0.5F);
            }
            GL11.glVertex3f(radiusLow * sintemp, radiusLow * costemp, 0.0F);
          }
        }
        GL11.glEnd();
        break;
        if (innerRadius == outerRadius)
        {
          GL11.glBegin(3);
          for (local_i = 0; local_i <= slices; local_i++)
          {
            if (this.textureFlag) {
              GL11.glTexCoord2f(sinCache[local_i] / 2.0F + 0.5F, cosCache[local_i] / 2.0F + 0.5F);
            }
            GL11.glVertex3f(innerRadius * sinCache[local_i], innerRadius * cosCache[local_i], 0.0F);
          }
          GL11.glEnd();
        }
        else
        {
          for (int local_j = 0; local_j <= loops; local_j++)
          {
            float radiusLow = outerRadius - deltaRadius * (local_j / loops);
            if (this.textureFlag) {
              texLow = radiusLow / outerRadius / 2.0F;
            }
            GL11.glBegin(3);
            for (local_i = 0; local_i <= slices; local_i++)
            {
              if (this.textureFlag) {
                GL11.glTexCoord2f(texLow * sinCache[local_i] + 0.5F, texLow * cosCache[local_i] + 0.5F);
              }
              GL11.glVertex3f(radiusLow * sinCache[local_i], radiusLow * cosCache[local_i], 0.0F);
            }
            GL11.glEnd();
          }
          local_i = 0;
          while (local_i < slices2)
          {
            float sintemp = sinCache[local_i];
            float costemp = cosCache[local_i];
            GL11.glBegin(3);
            for (local_j = 0; local_j <= loops; local_j++)
            {
              float radiusLow = outerRadius - deltaRadius * (local_j / loops);
              if (this.textureFlag) {
                texLow = radiusLow / outerRadius / 2.0F;
              }
              if (this.textureFlag) {
                GL11.glTexCoord2f(texLow * sinCache[local_i] + 0.5F, texLow * cosCache[local_i] + 0.5F);
              }
              GL11.glVertex3f(radiusLow * sintemp, radiusLow * costemp, 0.0F);
            }
            GL11.glEnd();
            local_i++;
            continue;
            if (sweepAngle < 360.0F)
            {
              local_i = 0;
              while (local_i <= slices)
              {
                sintemp = sinCache[local_i];
                costemp = cosCache[local_i];
                GL11.glBegin(3);
                for (local_j = 0; local_j <= loops; local_j++)
                {
                  float radiusLow = outerRadius - deltaRadius * (local_j / loops);
                  if (this.textureFlag)
                  {
                    texLow = radiusLow / outerRadius / 2.0F;
                    GL11.glTexCoord2f(texLow * sinCache[local_i] + 0.5F, texLow * cosCache[local_i] + 0.5F);
                  }
                  GL11.glVertex3f(radiusLow * sintemp, radiusLow * costemp, 0.0F);
                }
                GL11.glEnd();
                local_i += slices;
              }
            }
            int local_j = 0;
            while (local_j <= loops)
            {
              float radiusLow = outerRadius - deltaRadius * (local_j / loops);
              if (this.textureFlag) {
                texLow = radiusLow / outerRadius / 2.0F;
              }
              GL11.glBegin(3);
              for (local_i = 0; local_i <= slices; local_i++)
              {
                if (this.textureFlag) {
                  GL11.glTexCoord2f(texLow * sinCache[local_i] + 0.5F, texLow * cosCache[local_i] + 0.5F);
                }
                GL11.glVertex3f(radiusLow * sinCache[local_i], radiusLow * cosCache[local_i], 0.0F);
              }
              GL11.glEnd();
              if (innerRadius == outerRadius) {
                break;
              }
              local_j += loops;
            }
          }
        }
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.glu.PartialDisk
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */