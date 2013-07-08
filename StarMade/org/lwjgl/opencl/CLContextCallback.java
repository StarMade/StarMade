/*  1:   */package org.lwjgl.opencl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import org.lwjgl.PointerWrapperAbstract;
/*  5:   */
/* 41:   */public abstract class CLContextCallback
/* 42:   */  extends PointerWrapperAbstract
/* 43:   */{
/* 44:   */  private final boolean custom;
/* 45:   */  
/* 46:   */  protected CLContextCallback()
/* 47:   */  {
/* 48:48 */    super(CallbackUtil.getContextCallback());
/* 49:49 */    this.custom = false;
/* 50:   */  }
/* 51:   */  
/* 56:   */  protected CLContextCallback(long pointer)
/* 57:   */  {
/* 58:58 */    super(pointer);
/* 59:   */    
/* 60:60 */    if (pointer == 0L) {
/* 61:61 */      throw new RuntimeException("Invalid callback function pointer specified.");
/* 62:   */    }
/* 63:63 */    this.custom = true;
/* 64:   */  }
/* 65:   */  
/* 66:   */  final boolean isCustom() {
/* 67:67 */    return this.custom;
/* 68:   */  }
/* 69:   */  
/* 70:   */  protected abstract void handleMessage(String paramString, ByteBuffer paramByteBuffer);
/* 71:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLContextCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */