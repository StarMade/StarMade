package org.lwjgl.util.glu;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class MipMap
  extends Util
{
  public static int gluBuild2DMipmaps(int target, int components, int width, int height, int format, int type, ByteBuffer data)
  {
    if ((width < 1) || (height < 1)) {
      return 100901;
    }
    int bpp = bytesPerPixel(format, type);
    if (bpp == 0) {
      return 100900;
    }
    int maxSize = glGetIntegerv(3379);
    int local_w = nearestPower(width);
    if (local_w > maxSize) {
      local_w = maxSize;
    }
    int local_h = nearestPower(height);
    if (local_h > maxSize) {
      local_h = maxSize;
    }
    PixelStoreState pss = new PixelStoreState();
    GL11.glPixelStorei(3330, 0);
    GL11.glPixelStorei(3333, 1);
    GL11.glPixelStorei(3331, 0);
    GL11.glPixelStorei(3332, 0);
    int retVal = 0;
    boolean done = false;
    ByteBuffer image;
    if ((local_w != width) || (local_h != height))
    {
      ByteBuffer image = BufferUtils.createByteBuffer((local_w + 4) * local_h * bpp);
      int error = gluScaleImage(format, width, height, type, data, local_w, local_h, type, image);
      if (error != 0)
      {
        retVal = error;
        done = true;
      }
      GL11.glPixelStorei(3314, 0);
      GL11.glPixelStorei(3317, 1);
      GL11.glPixelStorei(3315, 0);
      GL11.glPixelStorei(3316, 0);
    }
    else
    {
      image = data;
    }
    ByteBuffer error = null;
    ByteBuffer bufferB = null;
    for (int level = 0; !done; level++)
    {
      if (image != data)
      {
        GL11.glPixelStorei(3314, 0);
        GL11.glPixelStorei(3317, 1);
        GL11.glPixelStorei(3315, 0);
        GL11.glPixelStorei(3316, 0);
      }
      GL11.glTexImage2D(target, level, components, local_w, local_h, 0, format, type, image);
      if ((local_w == 1) && (local_h == 1)) {
        break;
      }
      int newW = local_w < 2 ? 1 : local_w >> 1;
      int newH = local_h < 2 ? 1 : local_h >> 1;
      ByteBuffer newImage;
      ByteBuffer newImage;
      if (error == null)
      {
        newImage = error = BufferUtils.createByteBuffer((newW + 4) * newH * bpp);
      }
      else
      {
        ByteBuffer newImage;
        if (bufferB == null) {
          newImage = bufferB = BufferUtils.createByteBuffer((newW + 4) * newH * bpp);
        } else {
          newImage = bufferB;
        }
      }
      int error = gluScaleImage(format, local_w, local_h, type, image, newW, newH, type, newImage);
      if (error != 0)
      {
        retVal = error;
        done = true;
      }
      image = newImage;
      if (bufferB != null) {
        bufferB = error;
      }
      local_w = newW;
      local_h = newH;
    }
    pss.save();
    return retVal;
  }
  
  public static int gluScaleImage(int format, int widthIn, int heightIn, int typein, ByteBuffer dataIn, int widthOut, int heightOut, int typeOut, ByteBuffer dataOut)
  {
    int components = compPerPix(format);
    if (components == -1) {
      return 100900;
    }
    float[] tempIn = new float[widthIn * heightIn * components];
    float[] tempOut = new float[widthOut * heightOut * components];
    int sizein;
    switch (typein)
    {
    case 5121: 
      sizein = 1;
      break;
    case 5126: 
      sizein = 4;
      break;
    default: 
      return 1280;
    }
    int sizeout;
    switch (typeOut)
    {
    case 5121: 
      sizeout = 1;
      break;
    case 5126: 
      sizeout = 4;
      break;
    default: 
      return 1280;
    }
    PixelStoreState pss = new PixelStoreState();
    int rowlen;
    int rowlen;
    if (pss.unpackRowLength > 0) {
      rowlen = pss.unpackRowLength;
    } else {
      rowlen = widthIn;
    }
    int rowstride;
    int rowstride;
    if (sizein >= pss.unpackAlignment) {
      rowstride = components * rowlen;
    } else {
      rowstride = pss.unpackAlignment / sizein * ceil(components * rowlen * sizein, pss.unpackAlignment);
    }
    int local_k;
    int local_i;
    switch (typein)
    {
    case 5121: 
      local_k = 0;
      dataIn.rewind();
      local_i = 0;
    }
    int local_j;
    while (local_i < heightIn)
    {
      int ubptr = local_i * rowstride + pss.unpackSkipRows * rowstride + pss.unpackSkipPixels * components;
      for (local_j = 0; local_j < widthIn * components; local_j++) {
        tempIn[(local_k++)] = (dataIn.get(ubptr++) & 0xFF);
      }
      local_i++;
      continue;
      local_k = 0;
      dataIn.rewind();
      local_i = 0;
      while (local_i < heightIn)
      {
        int ubptr = 4 * (local_i * rowstride + pss.unpackSkipRows * rowstride + pss.unpackSkipPixels * components);
        for (local_j = 0; local_j < widthIn * components; local_j++)
        {
          tempIn[(local_k++)] = dataIn.getFloat(ubptr);
          ubptr += 4;
        }
        local_i++;
        continue;
        return 100900;
      }
    }
    float local_sx = widthIn / widthOut;
    float local_sy = heightIn / heightOut;
    float[] ubptr = new float[components];
    for (int local_iy = 0; local_iy < heightOut; local_iy++) {
      for (int local_ix = 0; local_ix < widthOut; local_ix++)
      {
        int local_x0 = (int)(local_ix * local_sx);
        int local_x1 = (int)((local_ix + 1) * local_sx);
        int local_y0 = (int)(local_iy * local_sy);
        int local_y1 = (int)((local_iy + 1) * local_sy);
        int readPix = 0;
        for (int local_ic = 0; local_ic < components; local_ic++) {
          ubptr[local_ic] = 0.0F;
        }
        for (int local_ic = local_x0; local_ic < local_x1; local_ic++) {
          for (int iy0 = local_y0; iy0 < local_y1; iy0++)
          {
            int src = (iy0 * widthIn + local_ic) * components;
            for (int local_ic1 = 0; local_ic1 < components; local_ic1++) {
              ubptr[local_ic1] += tempIn[(src + local_ic1)];
            }
            readPix++;
          }
        }
        int dst = (local_iy * widthOut + local_ix) * components;
        if (readPix == 0)
        {
          int src = (local_y0 * widthIn + local_x0) * components;
          for (int local_ic = 0; local_ic < components; local_ic++) {
            tempOut[(dst++)] = tempIn[(src + local_ic)];
          }
        }
        else
        {
          for (local_k = 0; local_k < components; local_k++) {
            tempOut[(dst++)] = (ubptr[local_k] / readPix);
          }
        }
      }
    }
    if (pss.packRowLength > 0) {
      rowlen = pss.packRowLength;
    } else {
      rowlen = widthOut;
    }
    if (sizeout >= pss.packAlignment) {
      rowstride = components * rowlen;
    } else {
      rowstride = pss.packAlignment / sizeout * ceil(components * rowlen * sizeout, pss.packAlignment);
    }
    switch (typeOut)
    {
    case 5121: 
      local_k = 0;
      local_i = 0;
    }
    while (local_i < heightOut)
    {
      int local_iy = local_i * rowstride + pss.packSkipRows * rowstride + pss.packSkipPixels * components;
      for (local_j = 0; local_j < widthOut * components; local_j++) {
        dataOut.put(local_iy++, (byte)(int)tempOut[(local_k++)]);
      }
      local_i++;
      continue;
      local_k = 0;
      local_i = 0;
      while (local_i < heightOut)
      {
        int local_iy = 4 * (local_i * rowstride + pss.unpackSkipRows * rowstride + pss.unpackSkipPixels * components);
        for (local_j = 0; local_j < widthOut * components; local_j++)
        {
          dataOut.putFloat(local_iy, tempOut[(local_k++)]);
          local_iy += 4;
        }
        local_i++;
        continue;
        return 100900;
      }
    }
    return 0;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.glu.MipMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */