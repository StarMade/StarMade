/*   1:    */package org.lwjgl.opencl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import org.lwjgl.LWJGLUtil;
/*   5:    */import org.lwjgl.PointerBuffer;
/*   6:    */
/*  49:    */abstract class InfoUtilAbstract<T extends CLObject>
/*  50:    */  implements InfoUtil<T>
/*  51:    */{
/*  52:    */  protected abstract int getInfo(T paramT, int paramInt, ByteBuffer paramByteBuffer, PointerBuffer paramPointerBuffer);
/*  53:    */  
/*  54:    */  protected int getInfoSizeArraySize(T object, int param_name)
/*  55:    */  {
/*  56: 56 */    throw new UnsupportedOperationException();
/*  57:    */  }
/*  58:    */  
/*  59:    */  protected PointerBuffer getSizesBuffer(T object, int param_name) {
/*  60: 60 */    int size = getInfoSizeArraySize(object, param_name);
/*  61:    */    
/*  62: 62 */    PointerBuffer buffer = APIUtil.getBufferPointer(size);
/*  63: 63 */    buffer.limit(size);
/*  64:    */    
/*  65: 65 */    getInfo(object, param_name, buffer.getBuffer(), null);
/*  66:    */    
/*  67: 67 */    return buffer;
/*  68:    */  }
/*  69:    */  
/*  70:    */  public int getInfoInt(T object, int param_name) {
/*  71: 71 */    object.checkValid();
/*  72:    */    
/*  73: 73 */    ByteBuffer buffer = APIUtil.getBufferByte(4);
/*  74: 74 */    getInfo(object, param_name, buffer, null);
/*  75:    */    
/*  76: 76 */    return buffer.getInt(0);
/*  77:    */  }
/*  78:    */  
/*  79:    */  public long getInfoSize(T object, int param_name) {
/*  80: 80 */    object.checkValid();
/*  81:    */    
/*  82: 82 */    PointerBuffer buffer = APIUtil.getBufferPointer();
/*  83: 83 */    getInfo(object, param_name, buffer.getBuffer(), null);
/*  84:    */    
/*  85: 85 */    return buffer.get(0);
/*  86:    */  }
/*  87:    */  
/*  88:    */  public long[] getInfoSizeArray(T object, int param_name) {
/*  89: 89 */    object.checkValid();
/*  90:    */    
/*  91: 91 */    int size = getInfoSizeArraySize(object, param_name);
/*  92: 92 */    PointerBuffer buffer = APIUtil.getBufferPointer(size);
/*  93:    */    
/*  94: 94 */    getInfo(object, param_name, buffer.getBuffer(), null);
/*  95:    */    
/*  96: 96 */    long[] array = new long[size];
/*  97: 97 */    for (int i = 0; i < size; i++) {
/*  98: 98 */      array[i] = buffer.get(i);
/*  99:    */    }
/* 100:100 */    return array;
/* 101:    */  }
/* 102:    */  
/* 103:    */  public long getInfoLong(T object, int param_name) {
/* 104:104 */    object.checkValid();
/* 105:    */    
/* 106:106 */    ByteBuffer buffer = APIUtil.getBufferByte(8);
/* 107:107 */    getInfo(object, param_name, buffer, null);
/* 108:    */    
/* 109:109 */    return buffer.getLong(0);
/* 110:    */  }
/* 111:    */  
/* 112:    */  public String getInfoString(T object, int param_name) {
/* 113:113 */    object.checkValid();
/* 114:    */    
/* 115:115 */    int bytes = getSizeRet(object, param_name);
/* 116:116 */    if (bytes <= 1) {
/* 117:117 */      return null;
/* 118:    */    }
/* 119:119 */    ByteBuffer buffer = APIUtil.getBufferByte(bytes);
/* 120:120 */    getInfo(object, param_name, buffer, null);
/* 121:    */    
/* 122:122 */    buffer.limit(bytes - 1);
/* 123:123 */    return APIUtil.getString(buffer);
/* 124:    */  }
/* 125:    */  
/* 126:    */  protected final int getSizeRet(T object, int param_name) {
/* 127:127 */    PointerBuffer bytes = APIUtil.getBufferPointer();
/* 128:128 */    int errcode = getInfo(object, param_name, null, bytes);
/* 129:129 */    if (errcode != 0) {
/* 130:130 */      throw new IllegalArgumentException("Invalid parameter specified: " + LWJGLUtil.toHexString(param_name));
/* 131:    */    }
/* 132:132 */    return (int)bytes.get(0);
/* 133:    */  }
/* 134:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.InfoUtilAbstract
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */