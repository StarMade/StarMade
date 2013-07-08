/*   1:    */package org.newdawn.slick.opengl;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.InputStream;
/*   5:    */import org.newdawn.slick.loading.DeferredResource;
/*   6:    */import org.newdawn.slick.loading.LoadingList;
/*   7:    */
/*  27:    */public class DeferredTexture
/*  28:    */  extends TextureImpl
/*  29:    */  implements DeferredResource
/*  30:    */{
/*  31:    */  private InputStream in;
/*  32:    */  private String resourceName;
/*  33:    */  private boolean flipped;
/*  34:    */  private int filter;
/*  35:    */  private TextureImpl target;
/*  36:    */  private int[] trans;
/*  37:    */  
/*  38:    */  public DeferredTexture(InputStream in, String resourceName, boolean flipped, int filter, int[] trans)
/*  39:    */  {
/*  40: 40 */    this.in = in;
/*  41: 41 */    this.resourceName = resourceName;
/*  42: 42 */    this.flipped = flipped;
/*  43: 43 */    this.filter = filter;
/*  44: 44 */    this.trans = trans;
/*  45:    */    
/*  46: 46 */    LoadingList.get().add(this);
/*  47:    */  }
/*  48:    */  
/*  50:    */  public void load()
/*  51:    */    throws IOException
/*  52:    */  {
/*  53: 53 */    boolean before = InternalTextureLoader.get().isDeferredLoading();
/*  54: 54 */    InternalTextureLoader.get().setDeferredLoading(false);
/*  55: 55 */    this.target = InternalTextureLoader.get().getTexture(this.in, this.resourceName, this.flipped, this.filter, this.trans);
/*  56: 56 */    InternalTextureLoader.get().setDeferredLoading(before);
/*  57:    */  }
/*  58:    */  
/*  61:    */  private void checkTarget()
/*  62:    */  {
/*  63: 63 */    if (this.target == null) {
/*  64:    */      try {
/*  65: 65 */        load();
/*  66: 66 */        LoadingList.get().remove(this);
/*  67: 67 */        return;
/*  68:    */      } catch (IOException e) {
/*  69: 69 */        throw new RuntimeException("Attempt to use deferred texture before loading and resource not found: " + this.resourceName);
/*  70:    */      }
/*  71:    */    }
/*  72:    */  }
/*  73:    */  
/*  76:    */  public void bind()
/*  77:    */  {
/*  78: 78 */    checkTarget();
/*  79:    */    
/*  80: 80 */    this.target.bind();
/*  81:    */  }
/*  82:    */  
/*  85:    */  public float getHeight()
/*  86:    */  {
/*  87: 87 */    checkTarget();
/*  88:    */    
/*  89: 89 */    return this.target.getHeight();
/*  90:    */  }
/*  91:    */  
/*  94:    */  public int getImageHeight()
/*  95:    */  {
/*  96: 96 */    checkTarget();
/*  97: 97 */    return this.target.getImageHeight();
/*  98:    */  }
/*  99:    */  
/* 102:    */  public int getImageWidth()
/* 103:    */  {
/* 104:104 */    checkTarget();
/* 105:105 */    return this.target.getImageWidth();
/* 106:    */  }
/* 107:    */  
/* 110:    */  public int getTextureHeight()
/* 111:    */  {
/* 112:112 */    checkTarget();
/* 113:113 */    return this.target.getTextureHeight();
/* 114:    */  }
/* 115:    */  
/* 118:    */  public int getTextureID()
/* 119:    */  {
/* 120:120 */    checkTarget();
/* 121:121 */    return this.target.getTextureID();
/* 122:    */  }
/* 123:    */  
/* 126:    */  public String getTextureRef()
/* 127:    */  {
/* 128:128 */    checkTarget();
/* 129:129 */    return this.target.getTextureRef();
/* 130:    */  }
/* 131:    */  
/* 134:    */  public int getTextureWidth()
/* 135:    */  {
/* 136:136 */    checkTarget();
/* 137:137 */    return this.target.getTextureWidth();
/* 138:    */  }
/* 139:    */  
/* 142:    */  public float getWidth()
/* 143:    */  {
/* 144:144 */    checkTarget();
/* 145:145 */    return this.target.getWidth();
/* 146:    */  }
/* 147:    */  
/* 150:    */  public void release()
/* 151:    */  {
/* 152:152 */    checkTarget();
/* 153:153 */    this.target.release();
/* 154:    */  }
/* 155:    */  
/* 158:    */  public void setAlpha(boolean alpha)
/* 159:    */  {
/* 160:160 */    checkTarget();
/* 161:161 */    this.target.setAlpha(alpha);
/* 162:    */  }
/* 163:    */  
/* 166:    */  public void setHeight(int height)
/* 167:    */  {
/* 168:168 */    checkTarget();
/* 169:169 */    this.target.setHeight(height);
/* 170:    */  }
/* 171:    */  
/* 174:    */  public void setTextureHeight(int texHeight)
/* 175:    */  {
/* 176:176 */    checkTarget();
/* 177:177 */    this.target.setTextureHeight(texHeight);
/* 178:    */  }
/* 179:    */  
/* 182:    */  public void setTextureID(int textureID)
/* 183:    */  {
/* 184:184 */    checkTarget();
/* 185:185 */    this.target.setTextureID(textureID);
/* 186:    */  }
/* 187:    */  
/* 190:    */  public void setTextureWidth(int texWidth)
/* 191:    */  {
/* 192:192 */    checkTarget();
/* 193:193 */    this.target.setTextureWidth(texWidth);
/* 194:    */  }
/* 195:    */  
/* 198:    */  public void setWidth(int width)
/* 199:    */  {
/* 200:200 */    checkTarget();
/* 201:201 */    this.target.setWidth(width);
/* 202:    */  }
/* 203:    */  
/* 206:    */  public byte[] getTextureData()
/* 207:    */  {
/* 208:208 */    checkTarget();
/* 209:209 */    return this.target.getTextureData();
/* 210:    */  }
/* 211:    */  
/* 214:    */  public String getDescription()
/* 215:    */  {
/* 216:216 */    return this.resourceName;
/* 217:    */  }
/* 218:    */  
/* 221:    */  public boolean hasAlpha()
/* 222:    */  {
/* 223:223 */    checkTarget();
/* 224:224 */    return this.target.hasAlpha();
/* 225:    */  }
/* 226:    */  
/* 229:    */  public void setTextureFilter(int textureFilter)
/* 230:    */  {
/* 231:231 */    checkTarget();
/* 232:232 */    this.target.setTextureFilter(textureFilter);
/* 233:    */  }
/* 234:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.DeferredTexture
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */