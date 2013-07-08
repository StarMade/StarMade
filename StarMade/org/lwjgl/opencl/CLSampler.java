/*  1:   */package org.lwjgl.opencl;
/*  2:   */
/* 38:   */public final class CLSampler
/* 39:   */  extends CLObjectChild<CLContext>
/* 40:   */{
/* 41:41 */  private static final InfoUtil<CLSampler> util = CLPlatform.getInfoUtilInstance(CLSampler.class, "CL_SAMPLER_UTIL");
/* 42:   */  
/* 43:   */  CLSampler(long pointer, CLContext context) {
/* 44:44 */    super(pointer, context);
/* 45:45 */    if (isValid()) {
/* 46:46 */      context.getCLSamplerRegistry().registerObject(this);
/* 47:   */    }
/* 48:   */  }
/* 49:   */  
/* 57:   */  public int getInfoInt(int param_name)
/* 58:   */  {
/* 59:59 */    return util.getInfoInt(this, param_name);
/* 60:   */  }
/* 61:   */  
/* 69:   */  public long getInfoLong(int param_name)
/* 70:   */  {
/* 71:71 */    return util.getInfoLong(this, param_name);
/* 72:   */  }
/* 73:   */  
/* 74:   */  int release()
/* 75:   */  {
/* 76:   */    try
/* 77:   */    {
/* 78:78 */      return super.release();
/* 79:   */    } finally {
/* 80:80 */      if (!isValid()) {
/* 81:81 */        ((CLContext)getParent()).getCLSamplerRegistry().unregisterObject(this);
/* 82:   */      }
/* 83:   */    }
/* 84:   */  }
/* 85:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLSampler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */