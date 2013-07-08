package org.schema.game.common.data.world;

import class_1041;
import class_35;
import class_48;
import class_747;
import class_886;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.schema.common.util.ByteUtil;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.physics.octree.ArrayOctree;
import org.schema.schine.network.StateInterface;

public class SegmentData
{
  private Segment segment;
  private byte[] data;
  public static final int lightBlockSize = 39;
  public static final int typeIndexStart = 0;
  public static final int typeIndexEnd = 11;
  public static final int hitpointsIndexStart = 11;
  public static final int hitpointsIndexEnd = 20;
  public static final int activeIndexStart = 20;
  public static final int activeIndexEnd = 21;
  public static final int orientationStart = 21;
  public static final int orientationEnd = 24;
  public static final int blockSize = 3;
  public static final byte ACTIVE_BIT = 16;
  public static final int vis = 12;
  public static final int BLOCK_COUNT = 4096;
  public static final int TOTAL_SIZE = 12288;
  public static final int TOTAL_SIZE_LIGHT = 159744;
  public static final int SEG_TIMES_SEG_TIMES_SEG = 4096;
  public static final int SEG_TIMES_SEG = 256;
  public static final int SEG = 16;
  private static final int SEG_MINUS_ONE = 15;
  public static final int field_1832 = 255;
  public static final byte ANTI_BYTE = -16;
  private int size;
  private final class_35 min = new class_35();
  private final class_35 max = new class_35();
  private final ArrayOctree octree;
  private final class_35 helperPos = new class_35();
  private boolean preserveControl;
  private static final int MASK = 255;
  private boolean needsRevalidate = false;
  private boolean revalidating;
  public static final int PIECE_ADDED = 0;
  public static final int PIECE_REMOVED = 1;
  public static final int PIECE_CHANGED = 2;
  public static final int PIECE_UNCHANGED = 3;
  public static final int PIECE_ACTIVE_CHANGED = 4;
  
  public static float byteArrayToFloat(byte[] paramArrayOfByte)
  {
    int i = 0;
    int j = 0;
    for (int k = 3; k >= 0; k--)
    {
      i |= (paramArrayOfByte[j] & 0xFF) << (k << 3);
      j++;
    }
    return Float.intBitsToFloat(i);
  }
  
  public static byte[] floatToByteArray(float paramFloat)
  {
    return intToByteArray(Float.floatToRawIntBits(paramFloat));
  }
  
  public static byte[] intToByteArray(int paramInt)
  {
    byte[] arrayOfByte = new byte[4];
    for (int i = 0; i < 4; i++)
    {
      int j = arrayOfByte.length - 1 - i << 3;
      arrayOfByte[i] = ((byte)(paramInt >>> j));
    }
    return arrayOfByte;
  }
  
  public static boolean allNeighborsInside(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    return (paramByte1 < 15) && (paramByte2 < 15) && (paramByte3 < 15) && (paramByte1 > 0) && (paramByte2 > 0) && (paramByte3 > 0);
  }
  
  public static int getInfoIndex(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    return 3 * ((paramByte3 << 8) + (paramByte2 << 4) + paramByte1);
  }
  
  public static int getInfoIndex(class_35 paramclass_35)
  {
    return getInfoIndex(paramclass_35.field_453, paramclass_35.field_454, paramclass_35.field_455);
  }
  
  public static int getLightInfoIndexFromIndex(int paramInt)
  {
    return paramInt / 3 * 3 * 24;
  }
  
  public static boolean valid(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    return ((paramByte1 | paramByte2 | paramByte3) & 0xFFFFFFF0) == 0;
  }
  
  public SegmentData(boolean paramBoolean)
  {
    this.octree = new ArrayOctree(!paramBoolean);
    this.data = new byte[12288];
    resetBB();
  }
  
  public int applySegmentData(class_35 paramclass_35, byte[] paramArrayOfByte)
  {
    synchronized (this)
    {
      int i = getInfoIndex(paramclass_35);
      int j = 0;
      boolean bool = isActive(i);
      int k = getType(i);
      byte[] arrayOfByte = getHitpoints(i);
      for (int m = i; m < i + 3; m++) {
        this.data[m] = paramArrayOfByte[(j++)];
      }
      m = getType(i);
      paramArrayOfByte = getHitpoints(i);
      if (m != k)
      {
        if ((k == 0) && (m != 0))
        {
          onAddingElement(i, paramclass_35.field_453, paramclass_35.field_454, paramclass_35.field_455, m, true);
          return 0;
        }
        if ((k != 0) && (m == 0))
        {
          onRemovingElement(i, paramclass_35.field_453, paramclass_35.field_454, paramclass_35.field_455, k, true);
          return 1;
        }
        return 2;
      }
      if (arrayOfByte != paramArrayOfByte) {
        return 2;
      }
      if (bool != isActive(i)) {
        return 4;
      }
      return 3;
    }
  }
  
  public int arraySize()
  {
    return this.data.length;
  }
  
  public void assignData(Segment paramSegment)
  {
    paramSegment.a22(this);
    setSegment(paramSegment);
    resetBB();
  }
  
  public boolean contains(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    if (valid(paramByte1, paramByte2, paramByte3)) {
      return containsUnsave(paramByte1, paramByte2, paramByte3);
    }
    return false;
  }
  
  public boolean contains(int paramInt)
  {
    return getType(paramInt) != 0;
  }
  
  public boolean contains(class_35 paramclass_35)
  {
    return contains(paramclass_35.field_453, paramclass_35.field_454, paramclass_35.field_455);
  }
  
  public boolean containsFast(int paramInt)
  {
    int i = this.data[(paramInt + 2)] & 0xFF;
    paramInt = this.data[(paramInt + 1)] & 0xFF;
    return (i != 0) || ((paramInt & 0x7) != 0);
  }
  
  public boolean containsFast(class_35 paramclass_35)
  {
    return containsFast(getInfoIndex(paramclass_35));
  }
  
  public boolean containsUnblended(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    if (valid(paramByte1, paramByte2, paramByte3)) {
      return ((paramByte1 = getType(paramByte1, paramByte2, paramByte3)) != 0) && (!ElementKeyMap.getInfo(paramByte1).isBlended());
    }
    return false;
  }
  
  public boolean containsUnblended(class_35 paramclass_35)
  {
    return containsUnblended(paramclass_35.field_453, paramclass_35.field_454, paramclass_35.field_455);
  }
  
  public boolean containsUnsave(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    return containsFast(getInfoIndex(paramByte1, paramByte2, paramByte3));
  }
  
  public boolean containsUnsave(int paramInt)
  {
    return getType(paramInt) != 0;
  }
  
  public void createFromByteBuffer(byte[] paramArrayOfByte, StateInterface arg2)
  {
    paramArrayOfByte = ByteBuffer.wrap(paramArrayOfByte);
    synchronized (this)
    {
      if (this.data == null) {
        this.data = new byte[12288];
      }
      for (int i = 0; i < this.data.length; i++) {
        this.data[i] = paramArrayOfByte.get();
      }
      return;
    }
  }
  
  public void deSerialize(DataInputStream paramDataInputStream)
  {
    synchronized (this)
    {
      reset();
      paramDataInputStream.readFully(this.data);
      setNeedsRevalidate(true);
      return;
    }
  }
  
  public byte[] getAsBuffer()
  {
    return this.data;
  }
  
  public short getHitpoints(int paramInt)
  {
    return (short)ByteUtil.a1(ByteUtil.a4(this.data, paramInt), 11, 20);
  }
  
  public class_35 getMax()
  {
    return this.max;
  }
  
  public class_35 getMin()
  {
    return this.min;
  }
  
  public ArrayOctree getOctree()
  {
    return this.octree;
  }
  
  public byte getOrientation(int paramInt)
  {
    return (byte)((this.data[paramInt] & 0xFF) >> 5 & 0x7);
  }
  
  public static void main(String[] paramArrayOfString)
  {
    paramArrayOfString = new byte[3];
    byte[] arrayOfByte = new byte[3];
    for (int i = 0; i < 8; i++)
    {
      new Object();
      ByteUtil.a8(paramArrayOfString, i, 21, 24, 0);
      new Object();
      int j = (byte)ByteUtil.a1(ByteUtil.a4(paramArrayOfString, 0), 21, 24);
      int k = (byte)((paramArrayOfString[0] & 0xFF) >> 5 & 0x7);
      for (int m = 0; m < 3; m++) {
        System.err.println(m + ": " + Integer.toBinaryString(paramArrayOfString[m] & 0xFF));
      }
      System.err.println("---------------");
      for (m = 0; m < 3; m++) {
        System.err.println(m + ": " + Integer.toBinaryString(arrayOfByte[m] & 0xFF));
      }
      System.err.println("---------------");
      System.err.println("---------------");
      System.err.println("REAL: " + j);
      System.err.println("NEW : " + k);
    }
  }
  
  public Segment getSegment()
  {
    return this.segment;
  }
  
  public SegmentController getSegmentController()
  {
    return this.segment.a15();
  }
  
  public byte[] getSegmentPieceData(int paramInt, byte[] paramArrayOfByte)
  {
    int i = 0;
    for (int j = paramInt; j < paramInt + 3; j++) {
      paramArrayOfByte[(i++)] = this.data[j];
    }
    return paramArrayOfByte;
  }
  
  public int getSize()
  {
    return this.size;
  }
  
  public short getType(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    paramByte1 = getInfoIndex(paramByte1, paramByte2, paramByte3);
    return getType(paramByte1);
  }
  
  public short getType(int paramInt)
  {
    int i = this.data[(paramInt + 2)] & 0xFF;
    paramInt = this.data[(paramInt + 1)] & 0xFF;
    return (short)(i + ((paramInt & 0x7) << 8));
  }
  
  public void setType(int paramInt, short paramShort)
  {
    paramShort *= 3;
    this.data[(paramInt + 2)] = ByteUtil.field_2133[(paramShort + 2)];
    int tmp26_25 = (paramInt + 1);
    byte[] tmp26_20 = this.data;
    tmp26_20[tmp26_25] = ((byte)(tmp26_20[tmp26_25] - (this.data[(paramInt + 1)] & 0x1)));
    int tmp48_47 = (paramInt + 1);
    byte[] tmp48_42 = this.data;
    tmp48_42[tmp48_47] = ((byte)(tmp48_42[tmp48_47] | ByteUtil.field_2133[(paramShort + 1)]));
  }
  
  public short getType(class_35 paramclass_35)
  {
    return getType(paramclass_35.field_453, paramclass_35.field_454, paramclass_35.field_455);
  }
  
  public boolean isActive(int paramInt)
  {
    return (this.data[paramInt] & 0x10) == 0;
  }
  
  public boolean isRevalidating()
  {
    return this.revalidating;
  }
  
  public boolean needsRevalidate()
  {
    return this.needsRevalidate;
  }
  
  public boolean neighbors(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    if (!allNeighborsInside(paramByte1, paramByte2, paramByte3))
    {
      if (contains((byte)(paramByte1 - 1), paramByte2, paramByte3)) {
        return true;
      }
      if (contains((byte)(paramByte1 + 1), paramByte2, paramByte3)) {
        return true;
      }
      if (contains(paramByte1, (byte)(paramByte2 - 1), paramByte3)) {
        return true;
      }
      if (contains(paramByte1, (byte)(paramByte2 + 1), paramByte3)) {
        return true;
      }
      if (contains(paramByte1, paramByte2, (byte)(paramByte3 - 1))) {
        return true;
      }
      if (contains(paramByte1, paramByte2, (byte)(paramByte3 + 1))) {
        return true;
      }
    }
    else
    {
      if (containsUnsave((byte)(paramByte1 - 1), paramByte2, paramByte3)) {
        return true;
      }
      if (containsUnsave((byte)(paramByte1 + 1), paramByte2, paramByte3)) {
        return true;
      }
      if (containsUnsave(paramByte1, (byte)(paramByte2 - 1), paramByte3)) {
        return true;
      }
      if (containsUnsave(paramByte1, (byte)(paramByte2 + 1), paramByte3)) {
        return true;
      }
      if (containsUnsave(paramByte1, paramByte2, (byte)(paramByte3 - 1))) {
        return true;
      }
      if (containsUnsave(paramByte1, paramByte2, (byte)(paramByte3 + 1))) {
        return true;
      }
    }
    return false;
  }
  
  public void onAddingElement(int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, boolean paramBoolean)
  {
    synchronized (this)
    {
      onAddingElementUnsynched(paramInt, paramByte1, paramByte2, paramByte3, paramShort, paramBoolean, true);
      return;
    }
  }
  
  public void onAddingElementUnsynched(int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramInt = getSize();
    setSize(getSize() + 1);
    getOctree().insert(paramByte1, paramByte2, paramByte3);
    try
    {
      getSegmentController().onAddedElement(paramShort, paramByte1, paramByte2, paramByte3, paramInt, getSegment(), paramBoolean2);
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException;
    }
    if (!this.revalidating) {
      getSegment().a11(true);
    }
    updateBB(paramByte1, paramByte2, paramByte3, paramBoolean1, true);
  }
  
  public void onRemovingElement(int arg1, byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, boolean paramBoolean)
  {
    synchronized (this)
    {
      int i = getSize();
      setSize(i - 1);
      getOctree().delete(paramByte1, paramByte2, paramByte3);
      try
      {
        getSegmentController().onRemovedElement(paramShort, i, paramByte1, paramByte2, paramByte3, getSegment(), this.preserveControl);
      }
      catch (IOException localIOException)
      {
        localIOException;
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException;
      }
      if (!this.revalidating) {
        getSegment().a11(true);
      }
      updateBB(paramByte1, paramByte2, paramByte3, paramBoolean, false);
      return;
    }
  }
  
  public void removeInfoElement(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    setType(getInfoIndex(paramByte1, paramByte2, paramByte3), (short)0);
  }
  
  public void removeInfoElement(class_35 paramclass_35)
  {
    removeInfoElement(paramclass_35.field_453, paramclass_35.field_454, paramclass_35.field_455);
  }
  
  public void reset()
  {
    synchronized (this)
    {
      this.preserveControl = true;
      if (getSegment() != null)
      {
        for (byte b1 = 0; b1 < 16; b1 = (byte)(b1 + 1)) {
          for (byte b2 = 0; b2 < 16; b2 = (byte)(b2 + 1)) {
            for (byte b3 = 0; b3 < 16; b3 = (byte)(b3 + 1)) {
              setInfoElementUnsynched(b3, b2, b1, (short)0, false);
            }
          }
        }
        getSegment().b2(0);
      }
      this.preserveControl = false;
      setSize(0);
      this.octree.reset();
      resetBB();
      return;
    }
  }
  
  public void resetFast()
  {
    setSize(0);
    Arrays.fill(this.data, (byte)0);
    setSize(0);
    this.octree.reset();
    resetBB();
    setSize(0);
  }
  
  public static class_35 getPositionFromIndex(short paramShort, class_35 paramclass_35)
  {
    int i = (paramShort = (short)(paramShort / 3)) / 256 % 16;
    int j = paramShort % 256 / 16 % 16;
    paramShort = paramShort % 256 % 16;
    paramclass_35.b((byte)paramShort, (byte)j, (byte)i);
    return paramclass_35;
  }
  
  public void resetBB()
  {
    getMax().b((byte)-128, (byte)-128, (byte)-128);
    getMin().b((byte)127, (byte)127, (byte)127);
  }
  
  public void restructBB(boolean paramBoolean)
  {
    int i = getMin().field_453;
    int j = getMin().field_454;
    int k = getMin().field_455;
    int m = getMax().field_453;
    int n = getMax().field_454;
    int i1 = getMax().field_455;
    resetBB();
    int i2 = 0;
    for (byte b1 = 0; b1 < 16; b1 = (byte)(b1 + 1)) {
      for (byte b2 = 0; b2 < 16; b2 = (byte)(b2 + 1)) {
        for (byte b3 = 0; b3 < 16; b3 = (byte)(b3 + 1))
        {
          if (containsFast(i2)) {
            updateBB(b3, b2, b1, false, true);
          }
          i2 += 3;
        }
      }
    }
    if ((paramBoolean) && ((i != getMin().field_453) || (j != getMin().field_454) || (k != getMin().field_455) || (m != getMax().field_453) || (n != getMax().field_454) || (i1 != getMax().field_455))) {
      getSegmentController().getSegmentBuffer().a24(this);
    }
  }
  
  private void revalidate(byte paramByte1, byte paramByte2, byte paramByte3, int paramInt)
  {
    short s;
    if (((s = getType(paramInt)) != 0) && (ElementKeyMap.exists(s)))
    {
      onAddingElement(paramInt, paramByte1, paramByte2, paramByte3, s, false);
      return;
    }
    if (s != 0) {
      setType(paramInt, (short)0);
    }
  }
  
  public void revalidateData()
  {
    synchronized (this)
    {
      this.revalidating = true;
      assert (getSize() == 0) : (" size is " + getSize() + " in " + getSegment().field_34 + " -> " + getSegmentController());
      int i = 0;
      for (byte b1 = 0; b1 < 16; b1 = (byte)(b1 + 1)) {
        for (byte b2 = 0; b2 < 16; b2 = (byte)(b2 + 1)) {
          for (byte b3 = 0; b3 < 16; b3 = (byte)(b3 + 1))
          {
            revalidate(b3, b2, b1, i);
            i += 3;
          }
        }
      }
      getSegmentController().getSegmentBuffer().b6(getSegment());
      setNeedsRevalidate(false);
      this.revalidating = false;
      getSegment().a11(true);
      return;
    }
  }
  
  public void serialize(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.write(this.data);
  }
  
  public void setActive(int paramInt, boolean paramBoolean)
  {
    ByteUtil.a8(this.data, paramBoolean ? 0 : 1, 20, 21, paramInt);
  }
  
  public void setHitpoints(int paramInt, short paramShort)
  {
    assert ((paramShort >= 0) && (paramShort < 512));
    ByteUtil.a8(this.data, paramShort, 11, 20, paramInt);
  }
  
  public void setInfoElement(byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, boolean paramBoolean)
  {
    synchronized (this)
    {
      setInfoElementUnsynched(paramByte1, paramByte2, paramByte3, paramShort, (byte)-1, (byte)-1, paramBoolean);
      return;
    }
  }
  
  public void setInfoElement(byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, byte paramByte4, byte paramByte5, boolean paramBoolean)
  {
    synchronized (this)
    {
      setInfoElementUnsynched(paramByte1, paramByte2, paramByte3, paramShort, paramByte4, paramByte5, paramBoolean);
      return;
    }
  }
  
  public void setInfoElement(class_35 paramclass_35, short paramShort, boolean paramBoolean)
  {
    setInfoElement(paramclass_35.field_453, paramclass_35.field_454, paramclass_35.field_455, paramShort, (byte)-1, (byte)-1, paramBoolean);
  }
  
  public void setInfoElement(class_35 paramclass_35, short paramShort, byte paramByte1, byte paramByte2, boolean paramBoolean)
  {
    setInfoElement(paramclass_35.field_453, paramclass_35.field_454, paramclass_35.field_455, paramShort, paramByte1, paramByte2, paramBoolean);
  }
  
  public void setInfoElementUnsynched(byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, boolean paramBoolean)
  {
    setInfoElementUnsynched(paramByte1, paramByte2, paramByte3, paramShort, (byte)-1, (byte)-1, paramBoolean);
  }
  
  public void setInfoElementForcedAddUnsynched(byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, boolean paramBoolean)
  {
    setInfoElementForcedAddUnsynched(paramByte1, paramByte2, paramByte3, paramShort, (byte)-1, (byte)-1, paramBoolean);
  }
  
  public void setInfoElementForcedAddUnsynched(byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, byte paramByte4, byte paramByte5, boolean paramBoolean)
  {
    if (paramShort == 0) {
      return;
    }
    int i = getInfoIndex(paramByte1, paramByte2, paramByte3);
    setType(i, paramShort);
    if (paramByte4 >= 0) {
      setOrientation(i, paramByte4);
    }
    if (paramByte5 >= 0) {
      setActive(i, paramByte5 != 0);
    }
    onAddingElementUnsynched(i, paramByte1, paramByte2, paramByte3, paramShort, paramBoolean, false);
    setHitpoints(i, ElementKeyMap.getInfo(paramShort).getMaxHitPoints());
  }
  
  public void setInfoElementUnsynched(byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, byte paramByte4, byte paramByte5, boolean paramBoolean)
  {
    int i = getInfoIndex(paramByte1, paramByte2, paramByte3);
    short s = getType(i);
    setType(i, paramShort);
    if (paramByte4 >= 0) {
      setOrientation(i, paramByte4);
    }
    if (paramByte5 >= 0) {
      setActive(i, paramByte5 != 0);
    }
    assert ((getSegment() != null) && (getSegment().a16() == this));
    if (paramShort == 0)
    {
      setActive(i, false);
      setOrientation(i, (byte)0);
      if (s != 0) {
        onRemovingElement(i, paramByte1, paramByte2, paramByte3, s, paramBoolean);
      }
    }
    else if (s == 0)
    {
      onAddingElementUnsynched(i, paramByte1, paramByte2, paramByte3, paramShort, paramBoolean, true);
      setHitpoints(i, ElementKeyMap.getInfo(paramShort).getMaxHitPoints());
    }
  }
  
  public void setInfoElementUnsynched(class_35 paramclass_35, short paramShort, boolean paramBoolean)
  {
    setInfoElementUnsynched(paramclass_35.field_453, paramclass_35.field_454, paramclass_35.field_455, paramShort, paramBoolean);
  }
  
  public void setNeedsRevalidate(boolean paramBoolean)
  {
    this.needsRevalidate = paramBoolean;
  }
  
  public void setOrientation(int paramInt, byte paramByte)
  {
    assert ((paramByte >= 0) && (paramByte < 8)) : "NOT A SIDE INDEX";
    paramByte = org.schema.game.common.data.element.Element.orientationMapping[paramByte];
    ByteUtil.a8(this.data, paramByte, 21, 24, paramInt);
    assert (paramByte == getOrientation(paramInt)) : ("failed orientation coding: " + paramByte + " != result " + getOrientation(paramInt));
  }
  
  public void setSegment(Segment paramSegment)
  {
    this.segment = paramSegment;
  }
  
  public void setSize(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 4096))
    {
      System.err.println("Exception WARNING: SEGMENT SIZE WRONG " + paramInt + " " + (this.segment != null ? this.segment.a15().getState() + ": " + this.segment.a15() + " " + this.segment : ""));
      try
      {
        throw new IllegalArgumentException();
      }
      catch (Exception localException)
      {
        localException;
      }
    }
    paramInt = Math.max(0, Math.min(paramInt, 4096));
    this.size = paramInt;
    if (this.segment != null) {
      this.segment.b2(this.size);
    }
    assert ((paramInt >= 0) && (paramInt <= 4096)) : ("arraySize: " + paramInt + " / 4096");
  }
  
  private void updateBB(byte paramByte1, byte paramByte2, byte paramByte3, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBoolean2)
    {
      if (paramByte1 > getMax().field_453) {
        getMax().field_453 = paramByte1;
      }
      if (paramByte2 > getMax().field_454) {
        getMax().field_454 = paramByte2;
      }
      if (paramByte3 > getMax().field_455) {
        getMax().field_455 = paramByte3;
      }
      if (paramByte1 < getMin().field_453) {
        getMin().field_453 = paramByte1;
      }
      if (paramByte2 < getMin().field_454) {
        getMin().field_454 = paramByte2;
      }
      if (paramByte3 < getMin().field_455) {
        getMin().field_455 = paramByte3;
      }
      if (paramBoolean1) {
        getSegmentController().getSegmentBuffer().b6(getSegment());
      }
    }
    else if (((paramByte1 >= getMax().field_453) || (paramByte2 >= getMax().field_454) || (paramByte3 >= getMax().field_455) || (paramByte1 <= getMin().field_453) || (paramByte2 <= getMin().field_454) || (paramByte3 <= getMin().field_455)) && (paramBoolean1))
    {
      if (class_1041.field_149 == getSegment().a15().getId()) {
        try
        {
          throw new IllegalArgumentException("RESTRCUTCTION DEBUG");
        }
        catch (Exception localException)
        {
          localException;
        }
      }
      restructBB(paramBoolean1);
    }
  }
  
  public void damage(float paramFloat1, class_48 paramclass_48, float paramFloat2)
  {
    synchronized (this)
    {
      int i = 0;
      paramFloat2 *= paramFloat2;
      resetBB();
      int j = (getSegment().field_34.a3(0, 0, 0)) && ((getSegmentController() instanceof class_747)) ? 1 : 0;
      int m;
      for (int k = 0; k < 16; m = (byte)(k + 1))
      {
        int i1;
        for (int n = 0; n < 16; i1 = (byte)(n + 1))
        {
          int i3;
          for (int i2 = 0; i2 < 16; i3 = (byte)(i2 + 1))
          {
            if (getType(i) != 0)
            {
              float f1 = paramclass_48.field_475 - i2;
              float f2 = paramclass_48.field_476 - n;
              float f3 = paramclass_48.field_477 - k;
              if ((f1 = f1 * f1 + f2 * f2 + f3 * f3) < paramFloat2)
              {
                f1 = (1.0F - Math.min(1.0F, f1 / paramFloat2)) * paramFloat1;
                int i4;
                if ((i4 = getHitpoints(i)) - f1 <= 0.0F)
                {
                  setHitpoints(i, (short)0);
                  if ((j != 0) && (i2 == 8) && (n == 8) && (k == 8)) {
                    System.err.println("[HIT-SEGMENTDATA] Core Destroyed");
                  } else {
                    setInfoElementUnsynched(i2, n, k, (short)0, false);
                  }
                }
                else
                {
                  setHitpoints(i, (short)(int)(i4 - f1));
                }
              }
              else
              {
                updateBB(i2, n, k, false, true);
              }
            }
            assert ((getType(i) == 0) || (ElementKeyMap.getInfo(getType(i)) != null));
            i += 3;
          }
        }
      }
      getSegmentController().getSegmentBuffer().b6(getSegment());
      return;
    }
  }
  
  public boolean checkEmpty()
  {
    for (int i = 0; i < this.data.length; i += 3) {
      if (getType(i) != 0) {
        return false;
      }
    }
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.world.SegmentData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */