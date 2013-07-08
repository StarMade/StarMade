package org.newdawn.slick.font.effects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.Glyph;

public class ShadowEffect
  implements ConfigurableEffect
{
  public static final int NUM_KERNELS = 16;
  public static final float[][] GAUSSIAN_BLUR_KERNELS = generateGaussianBlurKernels(16);
  private Color color = Color.black;
  private float opacity = 0.6F;
  private float xDistance = 2.0F;
  private float yDistance = 2.0F;
  private int blurKernelSize = 0;
  private int blurPasses = 1;
  
  public ShadowEffect() {}
  
  public ShadowEffect(Color color, int xDistance, int yDistance, float opacity)
  {
    this.color = color;
    this.xDistance = xDistance;
    this.yDistance = yDistance;
    this.opacity = opacity;
  }
  
  public void draw(BufferedImage image, Graphics2D local_g, UnicodeFont unicodeFont, Glyph glyph)
  {
    local_g = (Graphics2D)local_g.create();
    local_g.translate(this.xDistance, this.yDistance);
    local_g.setColor(new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), Math.round(this.opacity * 255.0F)));
    local_g.fill(glyph.getShape());
    Iterator iter = unicodeFont.getEffects().iterator();
    while (iter.hasNext())
    {
      Effect effect = (Effect)iter.next();
      if ((effect instanceof OutlineEffect))
      {
        Composite composite = local_g.getComposite();
        local_g.setComposite(AlphaComposite.Src);
        local_g.setStroke(((OutlineEffect)effect).getStroke());
        local_g.draw(glyph.getShape());
        local_g.setComposite(composite);
        break;
      }
    }
    local_g.dispose();
    if ((this.blurKernelSize > 1) && (this.blurKernelSize < 16) && (this.blurPasses > 0)) {
      blur(image);
    }
  }
  
  private void blur(BufferedImage image)
  {
    float[] matrix = GAUSSIAN_BLUR_KERNELS[(this.blurKernelSize - 1)];
    Kernel gaussianBlur1 = new Kernel(matrix.length, 1, matrix);
    Kernel gaussianBlur2 = new Kernel(1, matrix.length, matrix);
    RenderingHints hints = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
    ConvolveOp gaussianOp1 = new ConvolveOp(gaussianBlur1, 1, hints);
    ConvolveOp gaussianOp2 = new ConvolveOp(gaussianBlur2, 1, hints);
    BufferedImage scratchImage = EffectUtil.getScratchImage();
    for (int local_i = 0; local_i < this.blurPasses; local_i++)
    {
      gaussianOp1.filter(image, scratchImage);
      gaussianOp2.filter(scratchImage, image);
    }
  }
  
  public Color getColor()
  {
    return this.color;
  }
  
  public void setColor(Color color)
  {
    this.color = color;
  }
  
  public float getXDistance()
  {
    return this.xDistance;
  }
  
  public void setXDistance(float distance)
  {
    this.xDistance = distance;
  }
  
  public float getYDistance()
  {
    return this.yDistance;
  }
  
  public void setYDistance(float distance)
  {
    this.yDistance = distance;
  }
  
  public int getBlurKernelSize()
  {
    return this.blurKernelSize;
  }
  
  public void setBlurKernelSize(int blurKernelSize)
  {
    this.blurKernelSize = blurKernelSize;
  }
  
  public int getBlurPasses()
  {
    return this.blurPasses;
  }
  
  public void setBlurPasses(int blurPasses)
  {
    this.blurPasses = blurPasses;
  }
  
  public float getOpacity()
  {
    return this.opacity;
  }
  
  public void setOpacity(float opacity)
  {
    this.opacity = opacity;
  }
  
  public String toString()
  {
    return "Shadow";
  }
  
  public List getValues()
  {
    List values = new ArrayList();
    values.add(EffectUtil.colorValue("Color", this.color));
    values.add(EffectUtil.floatValue("Opacity", this.opacity, 0.0F, 1.0F, "This setting sets the translucency of the shadow."));
    values.add(EffectUtil.floatValue("X distance", this.xDistance, 1.4E-45F, 3.4028235E+38F, "This setting is the amount of pixels to offset the shadow on the x axis. The glyphs will need padding so the shadow doesn't get clipped."));
    values.add(EffectUtil.floatValue("Y distance", this.yDistance, 1.4E-45F, 3.4028235E+38F, "This setting is the amount of pixels to offset the shadow on the y axis. The glyphs will need padding so the shadow doesn't get clipped."));
    List options = new ArrayList();
    options.add(new String[] { "None", "0" });
    for (int local_i = 2; local_i < 16; local_i++) {
      options.add(new String[] { String.valueOf(local_i) });
    }
    String[][] local_i = (String[][])options.toArray(new String[options.size()][]);
    values.add(EffectUtil.optionValue("Blur kernel size", String.valueOf(this.blurKernelSize), local_i, "This setting controls how many neighboring pixels are used to blur the shadow. Set to \"None\" for no blur."));
    values.add(EffectUtil.intValue("Blur passes", this.blurPasses, "The setting is the number of times to apply a blur to the shadow. Set to \"0\" for no blur."));
    return values;
  }
  
  public void setValues(List values)
  {
    Iterator iter = values.iterator();
    while (iter.hasNext())
    {
      ConfigurableEffect.Value value = (ConfigurableEffect.Value)iter.next();
      if (value.getName().equals("Color")) {
        this.color = ((Color)value.getObject());
      } else if (value.getName().equals("Opacity")) {
        this.opacity = ((Float)value.getObject()).floatValue();
      } else if (value.getName().equals("X distance")) {
        this.xDistance = ((Float)value.getObject()).floatValue();
      } else if (value.getName().equals("Y distance")) {
        this.yDistance = ((Float)value.getObject()).floatValue();
      } else if (value.getName().equals("Blur kernel size")) {
        this.blurKernelSize = Integer.parseInt((String)value.getObject());
      } else if (value.getName().equals("Blur passes")) {
        this.blurPasses = ((Integer)value.getObject()).intValue();
      }
    }
  }
  
  private static float[][] generateGaussianBlurKernels(int level)
  {
    float[][] pascalsTriangle = generatePascalsTriangle(level);
    float[][] gaussianTriangle = new float[pascalsTriangle.length][];
    for (int local_i = 0; local_i < gaussianTriangle.length; local_i++)
    {
      float total = 0.0F;
      gaussianTriangle[local_i] = new float[pascalsTriangle[local_i].length];
      for (int local_j = 0; local_j < pascalsTriangle[local_i].length; local_j++) {
        total += pascalsTriangle[local_i][local_j];
      }
      float local_j = 1.0F / total;
      for (int local_j1 = 0; local_j1 < pascalsTriangle[local_i].length; local_j1++) {
        gaussianTriangle[local_i][local_j1] = (local_j * pascalsTriangle[local_i][local_j1]);
      }
    }
    return gaussianTriangle;
  }
  
  private static float[][] generatePascalsTriangle(int level)
  {
    if (level < 2) {
      level = 2;
    }
    float[][] triangle = new float[level][];
    triangle[0] = new float[1];
    triangle[1] = new float[2];
    triangle[0][0] = 1.0F;
    triangle[1][0] = 1.0F;
    triangle[1][1] = 1.0F;
    for (int local_i = 2; local_i < level; local_i++)
    {
      triangle[local_i] = new float[local_i + 1];
      triangle[local_i][0] = 1.0F;
      triangle[local_i][local_i] = 1.0F;
      for (int local_j = 1; local_j < triangle[local_i].length - 1; local_j++) {
        triangle[local_i][local_j] = (triangle[(local_i - 1)][(local_j - 1)] + triangle[(local_i - 1)][local_j]);
      }
    }
    return triangle;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.font.effects.ShadowEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */