/*   1:    */package org.newdawn.slick.opengl;
/*   2:    */
/*   3:    */import java.io.BufferedInputStream;
/*   4:    */import java.io.IOException;
/*   5:    */import java.io.InputStream;
/*   6:    */import java.nio.ByteBuffer;
/*   7:    */import java.util.ArrayList;
/*   8:    */import org.newdawn.slick.util.Log;
/*   9:    */
/*  16:    */public class CompositeImageData
/*  17:    */  implements LoadableImageData
/*  18:    */{
/*  19: 19 */  private ArrayList sources = new ArrayList();
/*  20:    */  
/*  23:    */  private LoadableImageData picked;
/*  24:    */  
/*  27:    */  public void add(LoadableImageData data)
/*  28:    */  {
/*  29: 29 */    this.sources.add(data);
/*  30:    */  }
/*  31:    */  
/*  33:    */  public ByteBuffer loadImage(InputStream fis)
/*  34:    */    throws IOException
/*  35:    */  {
/*  36: 36 */    return loadImage(fis, false, null);
/*  37:    */  }
/*  38:    */  
/*  40:    */  public ByteBuffer loadImage(InputStream fis, boolean flipped, int[] transparent)
/*  41:    */    throws IOException
/*  42:    */  {
/*  43: 43 */    return loadImage(fis, flipped, false, transparent);
/*  44:    */  }
/*  45:    */  
/*  47:    */  public ByteBuffer loadImage(InputStream is, boolean flipped, boolean forceAlpha, int[] transparent)
/*  48:    */    throws IOException
/*  49:    */  {
/*  50: 50 */    CompositeIOException exception = new CompositeIOException();
/*  51: 51 */    ByteBuffer buffer = null;
/*  52:    */    
/*  53: 53 */    BufferedInputStream in = new BufferedInputStream(is, is.available());
/*  54: 54 */    in.mark(is.available());
/*  55:    */    
/*  57: 57 */    for (int i = 0; i < this.sources.size(); i++) {
/*  58: 58 */      in.reset();
/*  59:    */      try {
/*  60: 60 */        LoadableImageData data = (LoadableImageData)this.sources.get(i);
/*  61:    */        
/*  62: 62 */        buffer = data.loadImage(in, flipped, forceAlpha, transparent);
/*  63: 63 */        this.picked = data;
/*  64:    */      }
/*  65:    */      catch (Exception e) {
/*  66: 66 */        Log.warn(this.sources.get(i).getClass() + " failed to read the data", e);
/*  67: 67 */        exception.addException(e);
/*  68:    */      }
/*  69:    */    }
/*  70:    */    
/*  71: 71 */    if (this.picked == null) {
/*  72: 72 */      throw exception;
/*  73:    */    }
/*  74:    */    
/*  75: 75 */    return buffer;
/*  76:    */  }
/*  77:    */  
/*  81:    */  private void checkPicked()
/*  82:    */  {
/*  83: 83 */    if (this.picked == null) {
/*  84: 84 */      throw new RuntimeException("Attempt to make use of uninitialised or invalid composite image data");
/*  85:    */    }
/*  86:    */  }
/*  87:    */  
/*  90:    */  public int getDepth()
/*  91:    */  {
/*  92: 92 */    checkPicked();
/*  93:    */    
/*  94: 94 */    return this.picked.getDepth();
/*  95:    */  }
/*  96:    */  
/*  99:    */  public int getHeight()
/* 100:    */  {
/* 101:101 */    checkPicked();
/* 102:    */    
/* 103:103 */    return this.picked.getHeight();
/* 104:    */  }
/* 105:    */  
/* 108:    */  public ByteBuffer getImageBufferData()
/* 109:    */  {
/* 110:110 */    checkPicked();
/* 111:    */    
/* 112:112 */    return this.picked.getImageBufferData();
/* 113:    */  }
/* 114:    */  
/* 117:    */  public int getTexHeight()
/* 118:    */  {
/* 119:119 */    checkPicked();
/* 120:    */    
/* 121:121 */    return this.picked.getTexHeight();
/* 122:    */  }
/* 123:    */  
/* 126:    */  public int getTexWidth()
/* 127:    */  {
/* 128:128 */    checkPicked();
/* 129:    */    
/* 130:130 */    return this.picked.getTexWidth();
/* 131:    */  }
/* 132:    */  
/* 135:    */  public int getWidth()
/* 136:    */  {
/* 137:137 */    checkPicked();
/* 138:    */    
/* 139:139 */    return this.picked.getWidth();
/* 140:    */  }
/* 141:    */  
/* 144:    */  public void configureEdging(boolean edging)
/* 145:    */  {
/* 146:146 */    for (int i = 0; i < this.sources.size(); i++) {
/* 147:147 */      ((LoadableImageData)this.sources.get(i)).configureEdging(edging);
/* 148:    */    }
/* 149:    */  }
/* 150:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.CompositeImageData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */