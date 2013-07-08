/*   1:    */package org.newdawn.slick.font.effects;
/*   2:    */
/*   3:    */import java.awt.AlphaComposite;
/*   4:    */import java.awt.Color;
/*   5:    */import java.awt.Composite;
/*   6:    */import java.awt.Graphics2D;
/*   7:    */import java.awt.RenderingHints;
/*   8:    */import java.awt.image.BufferedImage;
/*   9:    */import java.awt.image.ConvolveOp;
/*  10:    */import java.awt.image.Kernel;
/*  11:    */import java.util.ArrayList;
/*  12:    */import java.util.Iterator;
/*  13:    */import java.util.List;
/*  14:    */import org.newdawn.slick.UnicodeFont;
/*  15:    */import org.newdawn.slick.font.Glyph;
/*  16:    */
/*  24:    */public class ShadowEffect
/*  25:    */  implements ConfigurableEffect
/*  26:    */{
/*  27:    */  public static final int NUM_KERNELS = 16;
/*  28: 28 */  public static final float[][] GAUSSIAN_BLUR_KERNELS = generateGaussianBlurKernels(16);
/*  29:    */  
/*  31: 31 */  private Color color = Color.black;
/*  32:    */  
/*  33: 33 */  private float opacity = 0.6F;
/*  34:    */  
/*  35: 35 */  private float xDistance = 2.0F;
/*  36:    */  
/*  37: 37 */  private float yDistance = 2.0F;
/*  38:    */  
/*  39: 39 */  private int blurKernelSize = 0;
/*  40:    */  
/*  41: 41 */  private int blurPasses = 1;
/*  42:    */  
/*  49:    */  public ShadowEffect() {}
/*  50:    */  
/*  56:    */  public ShadowEffect(Color color, int xDistance, int yDistance, float opacity)
/*  57:    */  {
/*  58: 58 */    this.color = color;
/*  59: 59 */    this.xDistance = xDistance;
/*  60: 60 */    this.yDistance = yDistance;
/*  61: 61 */    this.opacity = opacity;
/*  62:    */  }
/*  63:    */  
/*  66:    */  public void draw(BufferedImage image, Graphics2D g, UnicodeFont unicodeFont, Glyph glyph)
/*  67:    */  {
/*  68: 68 */    g = (Graphics2D)g.create();
/*  69: 69 */    g.translate(this.xDistance, this.yDistance);
/*  70: 70 */    g.setColor(new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), Math.round(this.opacity * 255.0F)));
/*  71: 71 */    g.fill(glyph.getShape());
/*  72:    */    
/*  74: 74 */    for (Iterator iter = unicodeFont.getEffects().iterator(); iter.hasNext();) {
/*  75: 75 */      Effect effect = (Effect)iter.next();
/*  76: 76 */      if ((effect instanceof OutlineEffect)) {
/*  77: 77 */        Composite composite = g.getComposite();
/*  78: 78 */        g.setComposite(AlphaComposite.Src);
/*  79:    */        
/*  80: 80 */        g.setStroke(((OutlineEffect)effect).getStroke());
/*  81: 81 */        g.draw(glyph.getShape());
/*  82:    */        
/*  83: 83 */        g.setComposite(composite);
/*  84: 84 */        break;
/*  85:    */      }
/*  86:    */    }
/*  87:    */    
/*  88: 88 */    g.dispose();
/*  89: 89 */    if ((this.blurKernelSize > 1) && (this.blurKernelSize < 16) && (this.blurPasses > 0)) { blur(image);
/*  90:    */    }
/*  91:    */  }
/*  92:    */  
/*  96:    */  private void blur(BufferedImage image)
/*  97:    */  {
/*  98: 98 */    float[] matrix = GAUSSIAN_BLUR_KERNELS[(this.blurKernelSize - 1)];
/*  99: 99 */    Kernel gaussianBlur1 = new Kernel(matrix.length, 1, matrix);
/* 100:100 */    Kernel gaussianBlur2 = new Kernel(1, matrix.length, matrix);
/* 101:101 */    RenderingHints hints = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
/* 102:102 */    ConvolveOp gaussianOp1 = new ConvolveOp(gaussianBlur1, 1, hints);
/* 103:103 */    ConvolveOp gaussianOp2 = new ConvolveOp(gaussianBlur2, 1, hints);
/* 104:104 */    BufferedImage scratchImage = EffectUtil.getScratchImage();
/* 105:105 */    for (int i = 0; i < this.blurPasses; i++) {
/* 106:106 */      gaussianOp1.filter(image, scratchImage);
/* 107:107 */      gaussianOp2.filter(scratchImage, image);
/* 108:    */    }
/* 109:    */  }
/* 110:    */  
/* 115:    */  public Color getColor()
/* 116:    */  {
/* 117:117 */    return this.color;
/* 118:    */  }
/* 119:    */  
/* 124:    */  public void setColor(Color color)
/* 125:    */  {
/* 126:126 */    this.color = color;
/* 127:    */  }
/* 128:    */  
/* 134:    */  public float getXDistance()
/* 135:    */  {
/* 136:136 */    return this.xDistance;
/* 137:    */  }
/* 138:    */  
/* 144:    */  public void setXDistance(float distance)
/* 145:    */  {
/* 146:146 */    this.xDistance = distance;
/* 147:    */  }
/* 148:    */  
/* 154:    */  public float getYDistance()
/* 155:    */  {
/* 156:156 */    return this.yDistance;
/* 157:    */  }
/* 158:    */  
/* 164:    */  public void setYDistance(float distance)
/* 165:    */  {
/* 166:166 */    this.yDistance = distance;
/* 167:    */  }
/* 168:    */  
/* 173:    */  public int getBlurKernelSize()
/* 174:    */  {
/* 175:175 */    return this.blurKernelSize;
/* 176:    */  }
/* 177:    */  
/* 182:    */  public void setBlurKernelSize(int blurKernelSize)
/* 183:    */  {
/* 184:184 */    this.blurKernelSize = blurKernelSize;
/* 185:    */  }
/* 186:    */  
/* 191:    */  public int getBlurPasses()
/* 192:    */  {
/* 193:193 */    return this.blurPasses;
/* 194:    */  }
/* 195:    */  
/* 200:    */  public void setBlurPasses(int blurPasses)
/* 201:    */  {
/* 202:202 */    this.blurPasses = blurPasses;
/* 203:    */  }
/* 204:    */  
/* 209:    */  public float getOpacity()
/* 210:    */  {
/* 211:211 */    return this.opacity;
/* 212:    */  }
/* 213:    */  
/* 218:    */  public void setOpacity(float opacity)
/* 219:    */  {
/* 220:220 */    this.opacity = opacity;
/* 221:    */  }
/* 222:    */  
/* 225:    */  public String toString()
/* 226:    */  {
/* 227:227 */    return "Shadow";
/* 228:    */  }
/* 229:    */  
/* 232:    */  public List getValues()
/* 233:    */  {
/* 234:234 */    List values = new ArrayList();
/* 235:235 */    values.add(EffectUtil.colorValue("Color", this.color));
/* 236:236 */    values.add(EffectUtil.floatValue("Opacity", this.opacity, 0.0F, 1.0F, "This setting sets the translucency of the shadow."));
/* 237:237 */    values.add(EffectUtil.floatValue("X distance", this.xDistance, 1.4E-45F, 3.4028235E+38F, "This setting is the amount of pixels to offset the shadow on the x axis. The glyphs will need padding so the shadow doesn't get clipped."));
/* 238:    */    
/* 239:239 */    values.add(EffectUtil.floatValue("Y distance", this.yDistance, 1.4E-45F, 3.4028235E+38F, "This setting is the amount of pixels to offset the shadow on the y axis. The glyphs will need padding so the shadow doesn't get clipped."));
/* 240:    */    
/* 242:242 */    List options = new ArrayList();
/* 243:243 */    options.add(new String[] { "None", "0" });
/* 244:244 */    for (int i = 2; i < 16; i++)
/* 245:245 */      options.add(new String[] { String.valueOf(i) });
/* 246:246 */    String[][] optionsArray = (String[][])options.toArray(new String[options.size()][]);
/* 247:247 */    values.add(EffectUtil.optionValue("Blur kernel size", String.valueOf(this.blurKernelSize), optionsArray, "This setting controls how many neighboring pixels are used to blur the shadow. Set to \"None\" for no blur."));
/* 248:    */    
/* 250:250 */    values.add(EffectUtil.intValue("Blur passes", this.blurPasses, "The setting is the number of times to apply a blur to the shadow. Set to \"0\" for no blur."));
/* 251:    */    
/* 252:252 */    return values;
/* 253:    */  }
/* 254:    */  
/* 257:    */  public void setValues(List values)
/* 258:    */  {
/* 259:259 */    for (Iterator iter = values.iterator(); iter.hasNext();) {
/* 260:260 */      ConfigurableEffect.Value value = (ConfigurableEffect.Value)iter.next();
/* 261:261 */      if (value.getName().equals("Color")) {
/* 262:262 */        this.color = ((Color)value.getObject());
/* 263:263 */      } else if (value.getName().equals("Opacity")) {
/* 264:264 */        this.opacity = ((Float)value.getObject()).floatValue();
/* 265:265 */      } else if (value.getName().equals("X distance")) {
/* 266:266 */        this.xDistance = ((Float)value.getObject()).floatValue();
/* 267:267 */      } else if (value.getName().equals("Y distance")) {
/* 268:268 */        this.yDistance = ((Float)value.getObject()).floatValue();
/* 269:269 */      } else if (value.getName().equals("Blur kernel size")) {
/* 270:270 */        this.blurKernelSize = Integer.parseInt((String)value.getObject());
/* 271:271 */      } else if (value.getName().equals("Blur passes")) {
/* 272:272 */        this.blurPasses = ((Integer)value.getObject()).intValue();
/* 273:    */      }
/* 274:    */    }
/* 275:    */  }
/* 276:    */  
/* 282:    */  private static float[][] generateGaussianBlurKernels(int level)
/* 283:    */  {
/* 284:284 */    float[][] pascalsTriangle = generatePascalsTriangle(level);
/* 285:285 */    float[][] gaussianTriangle = new float[pascalsTriangle.length][];
/* 286:286 */    for (int i = 0; i < gaussianTriangle.length; i++) {
/* 287:287 */      float total = 0.0F;
/* 288:288 */      gaussianTriangle[i] = new float[pascalsTriangle[i].length];
/* 289:289 */      for (int j = 0; j < pascalsTriangle[i].length; j++)
/* 290:290 */        total += pascalsTriangle[i][j];
/* 291:291 */      float coefficient = 1.0F / total;
/* 292:292 */      for (int j = 0; j < pascalsTriangle[i].length; j++)
/* 293:293 */        gaussianTriangle[i][j] = (coefficient * pascalsTriangle[i][j]);
/* 294:    */    }
/* 295:295 */    return gaussianTriangle;
/* 296:    */  }
/* 297:    */  
/* 303:    */  private static float[][] generatePascalsTriangle(int level)
/* 304:    */  {
/* 305:305 */    if (level < 2) level = 2;
/* 306:306 */    float[][] triangle = new float[level][];
/* 307:307 */    triangle[0] = new float[1];
/* 308:308 */    triangle[1] = new float[2];
/* 309:309 */    triangle[0][0] = 1.0F;
/* 310:310 */    triangle[1][0] = 1.0F;
/* 311:311 */    triangle[1][1] = 1.0F;
/* 312:312 */    for (int i = 2; i < level; i++) {
/* 313:313 */      triangle[i] = new float[i + 1];
/* 314:314 */      triangle[i][0] = 1.0F;
/* 315:315 */      triangle[i][i] = 1.0F;
/* 316:316 */      for (int j = 1; j < triangle[i].length - 1; j++)
/* 317:317 */        triangle[i][j] = (triangle[(i - 1)][(j - 1)] + triangle[(i - 1)][j]);
/* 318:    */    }
/* 319:319 */    return triangle;
/* 320:    */  }
/* 321:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.font.effects.ShadowEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */