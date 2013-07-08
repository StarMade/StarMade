/*   1:    */package org.lwjgl.opencl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import org.lwjgl.LWJGLUtil;
/*   5:    */import org.lwjgl.PointerBuffer;
/*   6:    */
/*  60:    */final class CLChecks
/*  61:    */{
/*  62:    */  static int calculateBufferRectSize(PointerBuffer offset, PointerBuffer region, long row_pitch, long slice_pitch)
/*  63:    */  {
/*  64: 64 */    if (!LWJGLUtil.CHECKS) {
/*  65: 65 */      return 0;
/*  66:    */    }
/*  67: 67 */    long x = offset.get(0);
/*  68: 68 */    long y = offset.get(1);
/*  69: 69 */    long z = offset.get(2);
/*  70:    */    
/*  71: 71 */    if ((LWJGLUtil.DEBUG) && ((x < 0L) || (y < 0L) || (z < 0L))) {
/*  72: 72 */      throw new IllegalArgumentException("Invalid cl_mem host offset: " + x + ", " + y + ", " + z);
/*  73:    */    }
/*  74: 74 */    long w = region.get(0);
/*  75: 75 */    long h = region.get(1);
/*  76: 76 */    long d = region.get(2);
/*  77:    */    
/*  78: 78 */    if ((LWJGLUtil.DEBUG) && ((w < 1L) || (h < 1L) || (d < 1L))) {
/*  79: 79 */      throw new IllegalArgumentException("Invalid cl_mem rectangle region dimensions: " + w + " x " + h + " x " + d);
/*  80:    */    }
/*  81: 81 */    if (row_pitch == 0L) {
/*  82: 82 */      row_pitch = w;
/*  83: 83 */    } else if ((LWJGLUtil.DEBUG) && (row_pitch < w)) {
/*  84: 84 */      throw new IllegalArgumentException("Invalid host row pitch specified: " + row_pitch);
/*  85:    */    }
/*  86: 86 */    if (slice_pitch == 0L) {
/*  87: 87 */      slice_pitch = row_pitch * h;
/*  88: 88 */    } else if ((LWJGLUtil.DEBUG) && (slice_pitch < row_pitch * h)) {
/*  89: 89 */      throw new IllegalArgumentException("Invalid host slice pitch specified: " + slice_pitch);
/*  90:    */    }
/*  91: 91 */    return (int)(z * slice_pitch + y * row_pitch + x + w * h * d);
/*  92:    */  }
/*  93:    */  
/* 104:    */  static int calculateImageSize(PointerBuffer region, long row_pitch, long slice_pitch)
/* 105:    */  {
/* 106:106 */    if (!LWJGLUtil.CHECKS) {
/* 107:107 */      return 0;
/* 108:    */    }
/* 109:109 */    long w = region.get(0);
/* 110:110 */    long h = region.get(1);
/* 111:111 */    long d = region.get(2);
/* 112:    */    
/* 113:113 */    if ((LWJGLUtil.DEBUG) && ((w < 1L) || (h < 1L) || (d < 1L))) {
/* 114:114 */      throw new IllegalArgumentException("Invalid cl_mem image region dimensions: " + w + " x " + h + " x " + d);
/* 115:    */    }
/* 116:116 */    if (row_pitch == 0L) {
/* 117:117 */      row_pitch = w;
/* 118:118 */    } else if ((LWJGLUtil.DEBUG) && (row_pitch < w)) {
/* 119:119 */      throw new IllegalArgumentException("Invalid row pitch specified: " + row_pitch);
/* 120:    */    }
/* 121:121 */    if (slice_pitch == 0L) {
/* 122:122 */      slice_pitch = row_pitch * h;
/* 123:123 */    } else if ((LWJGLUtil.DEBUG) && (slice_pitch < row_pitch * h)) {
/* 124:124 */      throw new IllegalArgumentException("Invalid slice pitch specified: " + slice_pitch);
/* 125:    */    }
/* 126:126 */    return (int)(slice_pitch * d);
/* 127:    */  }
/* 128:    */  
/* 139:    */  static int calculateImage2DSize(ByteBuffer format, long w, long h, long row_pitch)
/* 140:    */  {
/* 141:141 */    if (!LWJGLUtil.CHECKS) {
/* 142:142 */      return 0;
/* 143:    */    }
/* 144:144 */    if ((LWJGLUtil.DEBUG) && ((w < 1L) || (h < 1L))) {
/* 145:145 */      throw new IllegalArgumentException("Invalid 2D image dimensions: " + w + " x " + h);
/* 146:    */    }
/* 147:147 */    int elementSize = getElementSize(format);
/* 148:    */    
/* 149:149 */    if (row_pitch == 0L) {
/* 150:150 */      row_pitch = w * elementSize;
/* 151:151 */    } else if ((LWJGLUtil.DEBUG) && ((row_pitch < w * elementSize) || (row_pitch % elementSize != 0L))) {
/* 152:152 */      throw new IllegalArgumentException("Invalid image_row_pitch specified: " + row_pitch);
/* 153:    */    }
/* 154:154 */    return (int)(row_pitch * h);
/* 155:    */  }
/* 156:    */  
/* 168:    */  static int calculateImage3DSize(ByteBuffer format, long w, long h, long d, long row_pitch, long slice_pitch)
/* 169:    */  {
/* 170:170 */    if (!LWJGLUtil.CHECKS) {
/* 171:171 */      return 0;
/* 172:    */    }
/* 173:173 */    if ((LWJGLUtil.DEBUG) && ((w < 1L) || (h < 1L) || (d < 2L))) {
/* 174:174 */      throw new IllegalArgumentException("Invalid 3D image dimensions: " + w + " x " + h + " x " + d);
/* 175:    */    }
/* 176:176 */    int elementSize = getElementSize(format);
/* 177:    */    
/* 178:178 */    if (row_pitch == 0L) {
/* 179:179 */      row_pitch = w * elementSize;
/* 180:180 */    } else if ((LWJGLUtil.DEBUG) && ((row_pitch < w * elementSize) || (row_pitch % elementSize != 0L))) {
/* 181:181 */      throw new IllegalArgumentException("Invalid image_row_pitch specified: " + row_pitch);
/* 182:    */    }
/* 183:183 */    if (slice_pitch == 0L) {
/* 184:184 */      slice_pitch = row_pitch * h;
/* 185:185 */    } else if ((LWJGLUtil.DEBUG) && ((row_pitch < row_pitch * h) || (slice_pitch % row_pitch != 0L))) {
/* 186:186 */      throw new IllegalArgumentException("Invalid image_slice_pitch specified: " + row_pitch);
/* 187:    */    }
/* 188:188 */    return (int)(slice_pitch * d);
/* 189:    */  }
/* 190:    */  
/* 197:    */  private static int getElementSize(ByteBuffer format)
/* 198:    */  {
/* 199:199 */    int channelOrder = format.getInt(format.position() + 0);
/* 200:200 */    int channelType = format.getInt(format.position() + 4);
/* 201:    */    
/* 202:202 */    return getChannelCount(channelOrder) * getChannelSize(channelType);
/* 203:    */  }
/* 204:    */  
/* 211:    */  private static int getChannelCount(int channelOrder)
/* 212:    */  {
/* 213:213 */    switch (channelOrder) {
/* 214:    */    case 4272: 
/* 215:    */    case 4273: 
/* 216:    */    case 4280: 
/* 217:    */    case 4281: 
/* 218:    */    case 4282: 
/* 219:219 */      return 1;
/* 220:    */    case 4274: 
/* 221:    */    case 4275: 
/* 222:    */    case 4283: 
/* 223:223 */      return 2;
/* 224:    */    case 4276: 
/* 225:    */    case 4284: 
/* 226:226 */      return 3;
/* 227:    */    case 4277: 
/* 228:    */    case 4278: 
/* 229:    */    case 4279: 
/* 230:230 */      return 4;
/* 231:    */    }
/* 232:232 */    throw new IllegalArgumentException("Invalid cl_channel_order specified: " + LWJGLUtil.toHexString(channelOrder));
/* 233:    */  }
/* 234:    */  
/* 242:    */  private static int getChannelSize(int channelType)
/* 243:    */  {
/* 244:244 */    switch (channelType) {
/* 245:    */    case 4304: 
/* 246:    */    case 4306: 
/* 247:    */    case 4311: 
/* 248:    */    case 4314: 
/* 249:249 */      return 1;
/* 250:    */    case 4305: 
/* 251:    */    case 4307: 
/* 252:    */    case 4308: 
/* 253:    */    case 4309: 
/* 254:    */    case 4312: 
/* 255:    */    case 4315: 
/* 256:    */    case 4317: 
/* 257:257 */      return 2;
/* 258:    */    case 4310: 
/* 259:    */    case 4313: 
/* 260:    */    case 4316: 
/* 261:    */    case 4318: 
/* 262:262 */      return 4;
/* 263:    */    }
/* 264:264 */    throw new IllegalArgumentException("Invalid cl_channel_type specified: " + LWJGLUtil.toHexString(channelType));
/* 265:    */  }
/* 266:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLChecks
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */