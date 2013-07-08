/*   1:    */import java.io.Externalizable;
/*   2:    */import java.io.ObjectInput;
/*   3:    */import java.io.ObjectOutput;
/*   4:    */
/*  52:    */public final class l
/*  53:    */  implements Externalizable, Cloneable
/*  54:    */{
/*  55:    */  private static final long serialVersionUID = 1L;
/*  56:    */  private float a;
/*  57:    */  private float b;
/*  58:    */  private float c;
/*  59:    */  private float d;
/*  60:    */  
/*  61:    */  static
/*  62:    */  {
/*  63: 63 */    new l(0.0F, 0.0F, 0.0F);
/*  64:    */    
/*  67: 67 */    new l(1.0F, 1.0F, 1.0F);
/*  68:    */    
/*  71: 71 */    new l(0.2F, 0.2F, 0.2F);
/*  72:    */    
/*  75: 75 */    new l(0.5F, 0.5F, 0.5F);
/*  76:    */    
/*  79: 79 */    new l(0.8F, 0.8F, 0.8F);
/*  80:    */    
/*  83: 83 */    new l(1.0F, 0.0F, 0.0F);
/*  84:    */    
/*  87: 87 */    new l(0.0F, 1.0F, 0.0F);
/*  88:    */    
/*  91: 91 */    new l(0.0F, 0.0F, 1.0F);
/*  92:    */    
/*  95: 95 */    new l(1.0F, 1.0F, 0.0F);
/*  96:    */    
/*  99: 99 */    new l(1.0F, 0.0F, 1.0F);
/* 100:    */    
/* 103:103 */    new l(0.0F, 1.0F, 1.0F);
/* 104:    */    
/* 107:107 */    new l(0.9843137F, 0.509804F, 0.0F);
/* 108:    */    
/* 111:111 */    new l(0.254902F, 0.1568628F, 0.09803922F);
/* 112:    */    
/* 115:115 */    new l(1.0F, 0.68F, 0.68F);
/* 116:    */  }
/* 117:    */  
/* 155:    */  public l()
/* 156:    */  {
/* 157:157 */    this.a = (this.b = this.c = this.d = 1.0F);
/* 158:    */  }
/* 159:    */  
/* 181:    */  private l(float paramFloat1, float paramFloat2, float paramFloat3)
/* 182:    */  {
/* 183:183 */    this.a = paramFloat1;
/* 184:184 */    this.b = paramFloat2;
/* 185:185 */    this.c = paramFloat3;
/* 186:186 */    this.d = 1.0F;
/* 187:    */  }
/* 188:    */  
/* 258:    */  private l a()
/* 259:    */  {
/* 260:    */    try
/* 261:    */    {
/* 262:262 */      return (l)super.clone();
/* 263:    */    } catch (CloneNotSupportedException localCloneNotSupportedException) {
/* 264:264 */      throw new AssertionError();
/* 265:    */    }
/* 266:    */  }
/* 267:    */  
/* 275:    */  public final boolean equals(Object paramObject)
/* 276:    */  {
/* 277:277 */    if (!(paramObject instanceof l)) {
/* 278:278 */      return false;
/* 279:    */    }
/* 280:    */    
/* 281:281 */    if (this == paramObject) {
/* 282:282 */      return true;
/* 283:    */    }
/* 284:    */    
/* 285:285 */    paramObject = (l)paramObject;
/* 286:286 */    if (Float.compare(this.a, paramObject.a) != 0) {
/* 287:287 */      return false;
/* 288:    */    }
/* 289:289 */    if (Float.compare(this.b, paramObject.b) != 0) {
/* 290:290 */      return false;
/* 291:    */    }
/* 292:292 */    if (Float.compare(this.c, paramObject.c) != 0) {
/* 293:293 */      return false;
/* 294:    */    }
/* 295:295 */    if (Float.compare(this.d, paramObject.d) != 0) {
/* 296:296 */      return false;
/* 297:    */    }
/* 298:298 */    return true;
/* 299:    */  }
/* 300:    */  
/* 349:    */  public final int hashCode()
/* 350:    */  {
/* 351:351 */    int tmp14_13 = (37 + (1369 + Float.floatToIntBits(this.a))); int 
/* 352:    */    
/* 353:353 */      tmp27_26 = (tmp14_13 + (tmp14_13 * 37 + Float.floatToIntBits(this.b))); int 
/* 354:354 */      tmp40_39 = (tmp27_26 + (tmp27_26 * 37 + Float.floatToIntBits(this.c)));
/* 355:    */    
/* 356:356 */    return tmp40_39 + (tmp40_39 * 37 + Float.floatToIntBits(this.d));
/* 357:    */  }
/* 358:    */  
/* 419:    */  public final void readExternal(ObjectInput paramObjectInput)
/* 420:    */  {
/* 421:421 */    this.a = paramObjectInput.readFloat();
/* 422:422 */    this.b = paramObjectInput.readFloat();
/* 423:423 */    this.c = paramObjectInput.readFloat();
/* 424:424 */    this.d = paramObjectInput.readFloat();
/* 425:    */  }
/* 426:    */  
/* 508:    */  public final String toString()
/* 509:    */  {
/* 510:510 */    return "com.jme.renderer.ColorRGBA: [R=" + this.a + ", G=" + this.b + ", B=" + this.c + ", A=" + this.d + "]";
/* 511:    */  }
/* 512:    */  
/* 519:    */  public final void writeExternal(ObjectOutput paramObjectOutput)
/* 520:    */  {
/* 521:521 */    paramObjectOutput.writeFloat(this.a);
/* 522:522 */    paramObjectOutput.writeFloat(this.b);
/* 523:523 */    paramObjectOutput.writeFloat(this.c);
/* 524:524 */    paramObjectOutput.writeFloat(this.d);
/* 525:    */  }
/* 526:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     l
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */