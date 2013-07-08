/*   1:    */package org.lwjgl.opencl;
/*   2:    */
/*   3:    */import java.nio.Buffer;
/*   4:    */import java.nio.ByteBuffer;
/*   5:    */import java.nio.IntBuffer;
/*   6:    */import org.lwjgl.opencl.api.CLBufferRegion;
/*   7:    */import org.lwjgl.opencl.api.CLImageFormat;
/*   8:    */
/*  45:    */public final class CLMem
/*  46:    */  extends CLObjectChild<CLContext>
/*  47:    */{
/*  48: 48 */  private static final CLMemUtil util = (CLMemUtil)CLPlatform.getInfoUtilInstance(CLMem.class, "CL_MEM_UTIL");
/*  49:    */  
/*  50:    */  CLMem(long pointer, CLContext context) {
/*  51: 51 */    super(pointer, context);
/*  52: 52 */    if (isValid()) {
/*  53: 53 */      context.getCLMemRegistry().registerObject(this);
/*  54:    */    }
/*  55:    */  }
/*  56:    */  
/*  73:    */  public static CLMem createImage2D(CLContext context, long flags, CLImageFormat image_format, long image_width, long image_height, long image_row_pitch, Buffer host_ptr, IntBuffer errcode_ret)
/*  74:    */  {
/*  75: 75 */    return util.createImage2D(context, flags, image_format, image_width, image_height, image_row_pitch, host_ptr, errcode_ret);
/*  76:    */  }
/*  77:    */  
/*  95:    */  public static CLMem createImage3D(CLContext context, long flags, CLImageFormat image_format, long image_width, long image_height, long image_depth, long image_row_pitch, long image_slice_pitch, Buffer host_ptr, IntBuffer errcode_ret)
/*  96:    */  {
/*  97: 97 */    return util.createImage3D(context, flags, image_format, image_width, image_height, image_depth, image_row_pitch, image_slice_pitch, host_ptr, errcode_ret);
/*  98:    */  }
/*  99:    */  
/* 100:    */  public CLMem createSubBuffer(long flags, int buffer_create_type, CLBufferRegion buffer_create_info, IntBuffer errcode_ret) {
/* 101:101 */    return util.createSubBuffer(this, flags, buffer_create_type, buffer_create_info, errcode_ret);
/* 102:    */  }
/* 103:    */  
/* 110:    */  public int getInfoInt(int param_name)
/* 111:    */  {
/* 112:112 */    return util.getInfoInt(this, param_name);
/* 113:    */  }
/* 114:    */  
/* 121:    */  public long getInfoSize(int param_name)
/* 122:    */  {
/* 123:123 */    return util.getInfoSize(this, param_name);
/* 124:    */  }
/* 125:    */  
/* 133:    */  public long getInfoLong(int param_name)
/* 134:    */  {
/* 135:135 */    return util.getInfoLong(this, param_name);
/* 136:    */  }
/* 137:    */  
/* 144:    */  public ByteBuffer getInfoHostBuffer()
/* 145:    */  {
/* 146:146 */    return util.getInfoHostBuffer(this);
/* 147:    */  }
/* 148:    */  
/* 157:    */  public long getImageInfoSize(int param_name)
/* 158:    */  {
/* 159:159 */    return util.getImageInfoSize(this, param_name);
/* 160:    */  }
/* 161:    */  
/* 166:    */  public CLImageFormat getImageFormat()
/* 167:    */  {
/* 168:168 */    return util.getImageInfoFormat(this);
/* 169:    */  }
/* 170:    */  
/* 175:    */  public int getImageChannelOrder()
/* 176:    */  {
/* 177:177 */    return util.getImageInfoFormat(this, 0);
/* 178:    */  }
/* 179:    */  
/* 184:    */  public int getImageChannelType()
/* 185:    */  {
/* 186:186 */    return util.getImageInfoFormat(this, 1);
/* 187:    */  }
/* 188:    */  
/* 196:    */  public int getGLObjectType()
/* 197:    */  {
/* 198:198 */    return util.getGLObjectType(this);
/* 199:    */  }
/* 200:    */  
/* 206:    */  public int getGLObjectName()
/* 207:    */  {
/* 208:208 */    return util.getGLObjectName(this);
/* 209:    */  }
/* 210:    */  
/* 220:    */  public int getGLTextureInfoInt(int param_name)
/* 221:    */  {
/* 222:222 */    return util.getGLTextureInfoInt(this, param_name);
/* 223:    */  }
/* 224:    */  
/* 259:    */  static CLMem create(long pointer, CLContext context)
/* 260:    */  {
/* 261:261 */    CLMem clMem = (CLMem)context.getCLMemRegistry().getObject(pointer);
/* 262:262 */    if (clMem == null) {
/* 263:263 */      clMem = new CLMem(pointer, context);
/* 264:    */    } else {
/* 265:265 */      clMem.retain();
/* 266:    */    }
/* 267:267 */    return clMem;
/* 268:    */  }
/* 269:    */  
/* 270:    */  int release() {
/* 271:    */    try {
/* 272:272 */      return super.release();
/* 273:    */    } finally {
/* 274:274 */      if (!isValid()) {
/* 275:275 */        ((CLContext)getParent()).getCLMemRegistry().unregisterObject(this);
/* 276:    */      }
/* 277:    */    }
/* 278:    */  }
/* 279:    */  
/* 280:    */  static abstract interface CLMemUtil
/* 281:    */    extends InfoUtil<CLMem>
/* 282:    */  {
/* 283:    */    public abstract CLMem createImage2D(CLContext paramCLContext, long paramLong1, CLImageFormat paramCLImageFormat, long paramLong2, long paramLong3, long paramLong4, Buffer paramBuffer, IntBuffer paramIntBuffer);
/* 284:    */    
/* 285:    */    public abstract CLMem createImage3D(CLContext paramCLContext, long paramLong1, CLImageFormat paramCLImageFormat, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, Buffer paramBuffer, IntBuffer paramIntBuffer);
/* 286:    */    
/* 287:    */    public abstract CLMem createSubBuffer(CLMem paramCLMem, long paramLong, int paramInt, CLBufferRegion paramCLBufferRegion, IntBuffer paramIntBuffer);
/* 288:    */    
/* 289:    */    public abstract ByteBuffer getInfoHostBuffer(CLMem paramCLMem);
/* 290:    */    
/* 291:    */    public abstract long getImageInfoSize(CLMem paramCLMem, int paramInt);
/* 292:    */    
/* 293:    */    public abstract CLImageFormat getImageInfoFormat(CLMem paramCLMem);
/* 294:    */    
/* 295:    */    public abstract int getImageInfoFormat(CLMem paramCLMem, int paramInt);
/* 296:    */    
/* 297:    */    public abstract int getGLObjectType(CLMem paramCLMem);
/* 298:    */    
/* 299:    */    public abstract int getGLObjectName(CLMem paramCLMem);
/* 300:    */    
/* 301:    */    public abstract int getGLTextureInfoInt(CLMem paramCLMem, int paramInt);
/* 302:    */  }
/* 303:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLMem
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */