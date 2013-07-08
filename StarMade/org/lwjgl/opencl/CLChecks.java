package org.lwjgl.opencl;

import java.nio.ByteBuffer;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.PointerBuffer;

final class CLChecks
{
  static int calculateBufferRectSize(PointerBuffer offset, PointerBuffer region, long row_pitch, long slice_pitch)
  {
    if (!LWJGLUtil.CHECKS) {
      return 0;
    }
    long local_x = offset.get(0);
    long local_y = offset.get(1);
    long local_z = offset.get(2);
    if ((LWJGLUtil.DEBUG) && ((local_x < 0L) || (local_y < 0L) || (local_z < 0L))) {
      throw new IllegalArgumentException("Invalid cl_mem host offset: " + local_x + ", " + local_y + ", " + local_z);
    }
    long local_w = region.get(0);
    long local_h = region.get(1);
    long local_d = region.get(2);
    if ((LWJGLUtil.DEBUG) && ((local_w < 1L) || (local_h < 1L) || (local_d < 1L))) {
      throw new IllegalArgumentException("Invalid cl_mem rectangle region dimensions: " + local_w + " x " + local_h + " x " + local_d);
    }
    if (row_pitch == 0L) {
      row_pitch = local_w;
    } else if ((LWJGLUtil.DEBUG) && (row_pitch < local_w)) {
      throw new IllegalArgumentException("Invalid host row pitch specified: " + row_pitch);
    }
    if (slice_pitch == 0L) {
      slice_pitch = row_pitch * local_h;
    } else if ((LWJGLUtil.DEBUG) && (slice_pitch < row_pitch * local_h)) {
      throw new IllegalArgumentException("Invalid host slice pitch specified: " + slice_pitch);
    }
    return (int)(local_z * slice_pitch + local_y * row_pitch + local_x + local_w * local_h * local_d);
  }
  
  static int calculateImageSize(PointerBuffer region, long row_pitch, long slice_pitch)
  {
    if (!LWJGLUtil.CHECKS) {
      return 0;
    }
    long local_w = region.get(0);
    long local_h = region.get(1);
    long local_d = region.get(2);
    if ((LWJGLUtil.DEBUG) && ((local_w < 1L) || (local_h < 1L) || (local_d < 1L))) {
      throw new IllegalArgumentException("Invalid cl_mem image region dimensions: " + local_w + " x " + local_h + " x " + local_d);
    }
    if (row_pitch == 0L) {
      row_pitch = local_w;
    } else if ((LWJGLUtil.DEBUG) && (row_pitch < local_w)) {
      throw new IllegalArgumentException("Invalid row pitch specified: " + row_pitch);
    }
    if (slice_pitch == 0L) {
      slice_pitch = row_pitch * local_h;
    } else if ((LWJGLUtil.DEBUG) && (slice_pitch < row_pitch * local_h)) {
      throw new IllegalArgumentException("Invalid slice pitch specified: " + slice_pitch);
    }
    return (int)(slice_pitch * local_d);
  }
  
  static int calculateImage2DSize(ByteBuffer format, long local_w, long local_h, long row_pitch)
  {
    if (!LWJGLUtil.CHECKS) {
      return 0;
    }
    if ((LWJGLUtil.DEBUG) && ((local_w < 1L) || (local_h < 1L))) {
      throw new IllegalArgumentException("Invalid 2D image dimensions: " + local_w + " x " + local_h);
    }
    int elementSize = getElementSize(format);
    if (row_pitch == 0L) {
      row_pitch = local_w * elementSize;
    } else if ((LWJGLUtil.DEBUG) && ((row_pitch < local_w * elementSize) || (row_pitch % elementSize != 0L))) {
      throw new IllegalArgumentException("Invalid image_row_pitch specified: " + row_pitch);
    }
    return (int)(row_pitch * local_h);
  }
  
  static int calculateImage3DSize(ByteBuffer format, long local_w, long local_h, long local_d, long row_pitch, long slice_pitch)
  {
    if (!LWJGLUtil.CHECKS) {
      return 0;
    }
    if ((LWJGLUtil.DEBUG) && ((local_w < 1L) || (local_h < 1L) || (local_d < 2L))) {
      throw new IllegalArgumentException("Invalid 3D image dimensions: " + local_w + " x " + local_h + " x " + local_d);
    }
    int elementSize = getElementSize(format);
    if (row_pitch == 0L) {
      row_pitch = local_w * elementSize;
    } else if ((LWJGLUtil.DEBUG) && ((row_pitch < local_w * elementSize) || (row_pitch % elementSize != 0L))) {
      throw new IllegalArgumentException("Invalid image_row_pitch specified: " + row_pitch);
    }
    if (slice_pitch == 0L) {
      slice_pitch = row_pitch * local_h;
    } else if ((LWJGLUtil.DEBUG) && ((row_pitch < row_pitch * local_h) || (slice_pitch % row_pitch != 0L))) {
      throw new IllegalArgumentException("Invalid image_slice_pitch specified: " + row_pitch);
    }
    return (int)(slice_pitch * local_d);
  }
  
  private static int getElementSize(ByteBuffer format)
  {
    int channelOrder = format.getInt(format.position() + 0);
    int channelType = format.getInt(format.position() + 4);
    return getChannelCount(channelOrder) * getChannelSize(channelType);
  }
  
  private static int getChannelCount(int channelOrder)
  {
    switch (channelOrder)
    {
    case 4272: 
    case 4273: 
    case 4280: 
    case 4281: 
    case 4282: 
      return 1;
    case 4274: 
    case 4275: 
    case 4283: 
      return 2;
    case 4276: 
    case 4284: 
      return 3;
    case 4277: 
    case 4278: 
    case 4279: 
      return 4;
    }
    throw new IllegalArgumentException("Invalid cl_channel_order specified: " + LWJGLUtil.toHexString(channelOrder));
  }
  
  private static int getChannelSize(int channelType)
  {
    switch (channelType)
    {
    case 4304: 
    case 4306: 
    case 4311: 
    case 4314: 
      return 1;
    case 4305: 
    case 4307: 
    case 4308: 
    case 4309: 
    case 4312: 
    case 4315: 
    case 4317: 
      return 2;
    case 4310: 
    case 4313: 
    case 4316: 
    case 4318: 
      return 4;
    }
    throw new IllegalArgumentException("Invalid cl_channel_type specified: " + LWJGLUtil.toHexString(channelType));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opencl.CLChecks
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */