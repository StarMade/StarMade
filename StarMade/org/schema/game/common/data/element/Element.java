package org.schema.game.common.data.element;

import class_35;
import class_48;
import javax.vecmath.Vector3f;

public abstract class Element
{
  public static byte[] orientationMapping;
  public static byte[] orientationBackMapping;
  public static final float HALF_SIZE = 1.0F;
  public static final short TYPE_BLENDED_START = -1;
  public static final short TYPE_NONE = 0;
  public static final short TYPE_ALL = 32767;
  public static final int RIGHT = 0;
  public static final int LEFT = 1;
  public static final int TOP = 2;
  public static final int BOTTOM = 3;
  public static final int FRONT = 4;
  public static final int BACK = 5;
  public static final int TOP_FRONT = 0;
  public static final int TOP_RIGHT = 1;
  public static final int TOP_BACK = 2;
  public static final int TOP_LEFT = 3;
  public static final int BACK_FRONT = 4;
  public static final int BACK_RIGHT = 5;
  public static final int BACK_BACK = 6;
  public static final int BACK_LEFT = 7;
  public static final int TOP_FRONT_B = 8;
  public static final int TOP_RIGHT_B = 9;
  public static final int TOP_BACK_B = 10;
  public static final int TOP_LEFT_B = 11;
  public static final int BACK_FRONT_B = 12;
  public static final int BACK_RIGHT_B = 13;
  public static final int BACK_BACK_B = 14;
  public static final int BACK_LEFT_B = 15;
  public static final int[] OPPOSITE_SIDE = { 1, 0, 3, 2, 5, 4 };
  public static final int FLAG_RIGHT = 1;
  public static final int FLAG_LEFT = 2;
  public static final int FLAG_TOP = 4;
  public static final int FLAG_BOTTOM = 8;
  public static final int FLAG_FRONT = 16;
  public static final int FLAG_BACK = 32;
  public static final int[] SIDE_FLAG = { 1, 2, 4, 8, 16, 32 };
  public static final class_35[] DIRECTIONSb = { new class_35(1.0F, 0.0F, 0.0F), new class_35(-1.0F, 0.0F, 0.0F), new class_35(0.0F, 1.0F, 0.0F), new class_35(0.0F, -1.0F, 0.0F), new class_35(0.0F, 0.0F, 1.0F), new class_35(0.0F, 0.0F, -1.0F) };
  public static final class_48[] DIRECTIONSi = { new class_48(1, 0, 0), new class_48(-1, 0, 0), new class_48(0, 1, 0), new class_48(0, -1, 0), new class_48(0, 0, 1), new class_48(0, 0, -1) };
  public static final Vector3f[] DIRECTIONSf = { new Vector3f(1.0F, 0.0F, 0.0F), new Vector3f(-1.0F, 0.0F, 0.0F), new Vector3f(0.0F, 1.0F, 0.0F), new Vector3f(0.0F, -1.0F, 0.0F), new Vector3f(0.0F, 0.0F, 1.0F), new Vector3f(0.0F, 0.0F, -1.0F) };
  public static final int SIDE_INDEX_COUNT = 4;
  public static final int INDEX_BOTTOM = 0;
  public static final int INDEX_TOP = 4;
  public static final int INDEX_FRONT = 8;
  public static final int INDEX_BACK = 16;
  public static final int INDEX_LEFT = 12;
  public static final int INDEX_RIGHT = 20;
  public static byte FULLVIS = 63;
  private static final float margin = 0.001F;
  
  public static int getRelativeOrientation(Vector3f paramVector3f)
  {
    int i = 0;
    if ((Math.abs(paramVector3f.field_615) >= Math.abs(paramVector3f.field_616)) && (Math.abs(paramVector3f.field_615) >= Math.abs(paramVector3f.field_617)))
    {
      if (paramVector3f.field_615 >= 0.0F) {
        i = 0;
      } else {
        i = 1;
      }
    }
    else if ((Math.abs(paramVector3f.field_616) >= Math.abs(paramVector3f.field_615)) && (Math.abs(paramVector3f.field_616) >= Math.abs(paramVector3f.field_617)))
    {
      if (paramVector3f.field_616 >= 0.0F) {
        i = 2;
      } else {
        i = 3;
      }
    }
    else if ((Math.abs(paramVector3f.field_617) >= Math.abs(paramVector3f.field_616)) && (Math.abs(paramVector3f.field_617) >= Math.abs(paramVector3f.field_615))) {
      if (paramVector3f.field_617 >= 0.0F) {
        i = 4;
      } else {
        i = 5;
      }
    }
    return i;
  }
  
  public static int countBits(int paramInt)
  {
    return ((paramInt = ((paramInt = ((paramInt = ((paramInt = (paramInt >>> 1 & 0x55555555) + (paramInt & 0x55555555)) >>> 2 & 0x33333333) + (paramInt & 0x33333333)) >>> 4 & 0xF0F0F0F) + (paramInt & 0xF0F0F0F)) >>> 8 & 0xFF00FF) + (paramInt & 0xFF00FF)) >>> 16) + (paramInt & 0xFFFF);
  }
  
  private static void createOrientationMapping()
  {
    (Element.orientationMapping = new byte[16])[4] = 0;
    orientationMapping[5] = 1;
    orientationMapping[2] = 2;
    orientationMapping[3] = 3;
    orientationMapping[1] = 4;
    orientationMapping[0] = 5;
    orientationMapping[6] = 6;
    orientationMapping[7] = 7;
    orientationMapping[12] = 8;
    orientationMapping[13] = 9;
    orientationMapping[10] = 10;
    orientationMapping[11] = 11;
    orientationMapping[9] = 12;
    orientationMapping[8] = 13;
    orientationMapping[14] = 14;
    orientationMapping[15] = 15;
    (Element.orientationBackMapping = new byte[16])[0] = 4;
    orientationBackMapping[1] = 5;
    orientationBackMapping[2] = 2;
    orientationBackMapping[3] = 3;
    orientationBackMapping[4] = 1;
    orientationBackMapping[5] = 0;
    orientationBackMapping[6] = 6;
    orientationBackMapping[7] = 7;
    orientationBackMapping[8] = 12;
    orientationBackMapping[9] = 13;
    orientationBackMapping[10] = 10;
    orientationBackMapping[11] = 11;
    orientationBackMapping[12] = 9;
    orientationBackMapping[13] = 8;
    orientationBackMapping[14] = 14;
    orientationBackMapping[15] = 15;
  }
  
  public static int getSide(Vector3f paramVector3f, class_48 paramclass_48)
  {
    return getSide(paramVector3f, paramclass_48, 0.001F);
  }
  
  public static int getSide(Vector3f paramVector3f, class_48 paramclass_48, float paramFloat)
  {
    for (;;)
    {
      float f1 = paramclass_48.field_475 - 0.5F;
      float f2 = paramclass_48.field_476 - 0.5F;
      float f3 = paramclass_48.field_477 - 0.5F;
      float f4 = paramclass_48.field_475 + 0.5F;
      float f5 = paramclass_48.field_476 + 0.5F;
      float f6 = paramclass_48.field_477 + 0.5F;
      if (paramVector3f.field_615 >= f4 - paramFloat) {
        return 0;
      }
      if (paramVector3f.field_616 >= f5 - paramFloat) {
        return 2;
      }
      if (paramVector3f.field_617 >= f6 - paramFloat) {
        return 4;
      }
      if (paramVector3f.field_615 <= f1 + paramFloat) {
        return 1;
      }
      if (paramVector3f.field_616 <= f2 + paramFloat) {
        return 3;
      }
      if (paramVector3f.field_617 <= f3 + paramFloat) {
        return 5;
      }
      if (paramFloat >= 0.5F) {
        break;
      }
      paramFloat *= 2.0F;
    }
    return -1;
  }
  
  public static String getSideString(int paramInt)
  {
    switch (paramInt)
    {
    case 1: 
      return "LEFT";
    case 0: 
      return "RIGHT";
    case 2: 
      return "TOP";
    case 3: 
      return "BOTTOM";
    case 4: 
      return "FRONT";
    case 5: 
      return "BACK";
    }
    return "[WARNING] UNKNOWN SIDE";
  }
  
  public String toString()
  {
    return "ELEMENT";
  }
  
  public static int getOpposite(int paramInt)
  {
    switch (paramInt)
    {
    case 1: 
      return 0;
    case 0: 
      return 1;
    case 2: 
      return 3;
    case 3: 
      return 2;
    case 4: 
      return 5;
    case 5: 
      return 4;
    }
    throw new RuntimeException("SIDE NOT FOUND: " + paramInt);
  }
  
  static {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.Element
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */