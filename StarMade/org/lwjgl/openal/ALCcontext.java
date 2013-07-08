/*   1:    */package org.lwjgl.openal;
/*   2:    */
/*   3:    */import java.nio.IntBuffer;
/*   4:    */import org.lwjgl.BufferUtils;
/*   5:    */
/*  59:    */public final class ALCcontext
/*  60:    */{
/*  61:    */  final long context;
/*  62:    */  private boolean valid;
/*  63:    */  
/*  64:    */  ALCcontext(long context)
/*  65:    */  {
/*  66: 66 */    this.context = context;
/*  67: 67 */    this.valid = true;
/*  68:    */  }
/*  69:    */  
/*  72:    */  public boolean equals(Object context)
/*  73:    */  {
/*  74: 74 */    if ((context instanceof ALCcontext)) {
/*  75: 75 */      return ((ALCcontext)context).context == this.context;
/*  76:    */    }
/*  77: 77 */    return super.equals(context);
/*  78:    */  }
/*  79:    */  
/*  86:    */  static IntBuffer createAttributeList(int contextFrequency, int contextRefresh, int contextSynchronized)
/*  87:    */  {
/*  88: 88 */    IntBuffer attribList = BufferUtils.createIntBuffer(7);
/*  89:    */    
/*  90: 90 */    attribList.put(4103);
/*  91: 91 */    attribList.put(contextFrequency);
/*  92: 92 */    attribList.put(4104);
/*  93: 93 */    attribList.put(contextRefresh);
/*  94: 94 */    attribList.put(4105);
/*  95: 95 */    attribList.put(contextSynchronized);
/*  96: 96 */    attribList.put(0);
/*  97:    */    
/*  98: 98 */    return attribList;
/*  99:    */  }
/* 100:    */  
/* 104:    */  void setInvalid()
/* 105:    */  {
/* 106:106 */    this.valid = false;
/* 107:    */  }
/* 108:    */  
/* 111:    */  public boolean isValid()
/* 112:    */  {
/* 113:113 */    return this.valid;
/* 114:    */  }
/* 115:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.openal.ALCcontext
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */