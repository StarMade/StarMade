package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.BigArrays;
import it.unimi.dsi.fastutil.Hash.Strategy;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class ByteBigArrays
{
  public static final long ONEOVERPHI = 106039L;
  public static final byte[][] EMPTY_BIG_ARRAY = new byte[0][];
  public static final Hash.Strategy HASH_STRATEGY = new BigArrayHashStrategy(null);
  private static final int SMALL = 7;
  private static final int MEDIUM = 40;
  private static final int DIGIT_BITS = 8;
  private static final int DIGIT_MASK = 255;
  private static final int DIGITS_PER_ELEMENT = 1;
  
  public static byte get(byte[][] array, long index)
  {
    return array[BigArrays.segment(index)][BigArrays.displacement(index)];
  }
  
  public static void set(byte[][] array, long index, byte value)
  {
    array[BigArrays.segment(index)][BigArrays.displacement(index)] = value;
  }
  
  public static void swap(byte[][] array, long first, long second)
  {
    byte local_t = array[BigArrays.segment(first)][BigArrays.displacement(first)];
    array[BigArrays.segment(first)][BigArrays.displacement(first)] = array[BigArrays.segment(second)][BigArrays.displacement(second)];
    array[BigArrays.segment(second)][BigArrays.displacement(second)] = local_t;
  }
  
  public static void add(byte[][] array, long index, byte incr)
  {
    int tmp10_7 = BigArrays.displacement(index);
    byte[] tmp10_5 = array[BigArrays.segment(index)];
    tmp10_5[tmp10_7] = ((byte)(tmp10_5[tmp10_7] + tmp10_7));
  }
  
  public static void mul(byte[][] array, long index, byte factor)
  {
    int tmp10_7 = BigArrays.displacement(index);
    byte[] tmp10_5 = array[BigArrays.segment(index)];
    tmp10_5[tmp10_7] = ((byte)(tmp10_5[tmp10_7] * tmp10_7));
  }
  
  public static void incr(byte[][] array, long index)
  {
    int tmp10_7 = BigArrays.displacement(index);
    byte[] tmp10_5 = array[BigArrays.segment(index)];
    tmp10_5[tmp10_7] = ((byte)(tmp10_5[tmp10_7] + 1));
  }
  
  public static void decr(byte[][] array, long index)
  {
    int tmp10_7 = BigArrays.displacement(index);
    byte[] tmp10_5 = array[BigArrays.segment(index)];
    tmp10_5[tmp10_7] = ((byte)(tmp10_5[tmp10_7] - 1));
  }
  
  public static long length(byte[][] array)
  {
    int length = array.length;
    return length == 0 ? 0L : BigArrays.start(length - 1) + array[(length - 1)].length;
  }
  
  public static void copy(byte[][] srcArray, long srcPos, byte[][] destArray, long destPos, long length)
  {
    if (destPos <= srcPos)
    {
      int srcSegment = BigArrays.segment(srcPos);
      int destSegment = BigArrays.segment(destPos);
      int srcDispl = BigArrays.displacement(srcPos);
      int destDispl = BigArrays.displacement(destPos);
      while (length > 0L)
      {
        int local_l = (int)Math.min(length, Math.min(srcArray[srcSegment].length - srcDispl, destArray[destSegment].length - destDispl));
        System.arraycopy(srcArray[srcSegment], srcDispl, destArray[destSegment], destDispl, local_l);
        if (srcDispl += local_l == 134217728)
        {
          srcDispl = 0;
          srcSegment++;
        }
        if (destDispl += local_l == 134217728)
        {
          destDispl = 0;
          destSegment++;
        }
        length -= local_l;
      }
    }
    else
    {
      int srcSegment = BigArrays.segment(srcPos + length);
      int destSegment = BigArrays.segment(destPos + length);
      int srcDispl = BigArrays.displacement(srcPos + length);
      int destDispl = BigArrays.displacement(destPos + length);
      while (length > 0L)
      {
        if (srcDispl == 0)
        {
          srcDispl = 134217728;
          srcSegment--;
        }
        if (destDispl == 0)
        {
          destDispl = 134217728;
          destSegment--;
        }
        int local_l = (int)Math.min(length, Math.min(srcDispl, destDispl));
        System.arraycopy(srcArray[srcSegment], srcDispl - local_l, destArray[destSegment], destDispl - local_l, local_l);
        srcDispl -= local_l;
        destDispl -= local_l;
        length -= local_l;
      }
    }
  }
  
  public static void copyFromBig(byte[][] srcArray, long srcPos, byte[] destArray, int destPos, int length)
  {
    int srcSegment = BigArrays.segment(srcPos);
    int srcDispl = BigArrays.displacement(srcPos);
    while (length > 0)
    {
      int local_l = Math.min(srcArray[srcSegment].length - srcDispl, length);
      System.arraycopy(srcArray[srcSegment], srcDispl, destArray, destPos, local_l);
      if (srcDispl += local_l == 134217728)
      {
        srcDispl = 0;
        srcSegment++;
      }
      destPos += local_l;
      length -= local_l;
    }
  }
  
  public static void copyToBig(byte[] srcArray, int srcPos, byte[][] destArray, long destPos, long length)
  {
    int destSegment = BigArrays.segment(destPos);
    int destDispl = BigArrays.displacement(destPos);
    while (length > 0L)
    {
      int local_l = (int)Math.min(destArray[destSegment].length - destDispl, length);
      System.arraycopy(srcArray, srcPos, destArray[destSegment], destDispl, local_l);
      if (destDispl += local_l == 134217728)
      {
        destDispl = 0;
        destSegment++;
      }
      srcPos += local_l;
      length -= local_l;
    }
  }
  
  public static byte[][] newBigArray(long length)
  {
    if (length == 0L) {
      return EMPTY_BIG_ARRAY;
    }
    int baseLength = (int)((length + 134217727L) / 134217728L);
    byte[][] base = new byte[baseLength][];
    int residual = (int)(length & 0x7FFFFFF);
    if (residual != 0)
    {
      for (int local_i = 0; local_i < baseLength - 1; local_i++) {
        base[local_i] = new byte[134217728];
      }
      base[(baseLength - 1)] = new byte[residual];
    }
    else
    {
      for (int local_i = 0; local_i < baseLength; local_i++) {
        base[local_i] = new byte[134217728];
      }
    }
    return base;
  }
  
  public static byte[][] wrap(byte[] array)
  {
    if (array.length == 0) {
      return EMPTY_BIG_ARRAY;
    }
    if (array.length <= 134217728) {
      return new byte[][] { array };
    }
    byte[][] bigArray = newBigArray(array.length);
    for (int local_i = 0; local_i < bigArray.length; local_i++) {
      System.arraycopy(array, (int)BigArrays.start(local_i), bigArray[local_i], 0, bigArray[local_i].length);
    }
    return bigArray;
  }
  
  public static byte[][] ensureCapacity(byte[][] array, long length)
  {
    return ensureCapacity(array, length, length(array));
  }
  
  public static byte[][] ensureCapacity(byte[][] array, long length, long preserve)
  {
    long oldLength = length(array);
    if (length > oldLength)
    {
      int valid = array.length - ((array.length == 0) || ((array.length > 0) && (array[(array.length - 1)].length == 134217728)) ? 0 : 1);
      int baseLength = (int)((length + 134217727L) / 134217728L);
      byte[][] base = (byte[][])Arrays.copyOf(array, baseLength);
      int residual = (int)(length & 0x7FFFFFF);
      if (residual != 0)
      {
        for (int local_i = valid; local_i < baseLength - 1; local_i++) {
          base[local_i] = new byte[134217728];
        }
        base[(baseLength - 1)] = new byte[residual];
      }
      else
      {
        for (int local_i = valid; local_i < baseLength; local_i++) {
          base[local_i] = new byte[134217728];
        }
      }
      if (preserve - valid * 134217728L > 0L) {
        copy(array, valid * 134217728L, base, valid * 134217728L, preserve - valid * 134217728L);
      }
      return base;
    }
    return array;
  }
  
  public static byte[][] grow(byte[][] array, long length)
  {
    long oldLength = length(array);
    return length > oldLength ? grow(array, length, oldLength) : array;
  }
  
  public static byte[][] grow(byte[][] array, long length, long preserve)
  {
    long oldLength = length(array);
    return length > oldLength ? ensureCapacity(array, Math.max(106039L * oldLength >>> 16, length), preserve) : array;
  }
  
  public static byte[][] trim(byte[][] array, long length)
  {
    long oldLength = length(array);
    if (length >= oldLength) {
      return array;
    }
    int baseLength = (int)((length + 134217727L) / 134217728L);
    byte[][] base = (byte[][])Arrays.copyOf(array, baseLength);
    int residual = (int)(length & 0x7FFFFFF);
    if (residual != 0) {
      base[(baseLength - 1)] = ByteArrays.trim(base[(baseLength - 1)], residual);
    }
    return base;
  }
  
  public static byte[][] setLength(byte[][] array, long length)
  {
    long oldLength = length(array);
    if (length == oldLength) {
      return array;
    }
    if (length < oldLength) {
      return trim(array, length);
    }
    return ensureCapacity(array, length);
  }
  
  public static byte[][] copy(byte[][] array, long offset, long length)
  {
    ensureOffsetLength(array, offset, length);
    byte[][] local_a = newBigArray(length);
    copy(array, offset, local_a, 0L, length);
    return local_a;
  }
  
  public static byte[][] copy(byte[][] array)
  {
    byte[][] base = (byte[][])array.clone();
    int local_i = base.length;
    while (local_i-- != 0) {
      base[local_i] = ((byte[])array[local_i].clone());
    }
    return base;
  }
  
  public static void fill(byte[][] array, byte value)
  {
    int local_i = array.length;
    while (local_i-- != 0) {
      ByteArrays.fill(array[local_i], value);
    }
  }
  
  public static void fill(byte[][] array, long from, long local_to, byte value)
  {
    long length = length(array);
    BigArrays.ensureFromTo(length, from, local_to);
    int fromSegment = BigArrays.segment(from);
    int toSegment = BigArrays.segment(local_to);
    int fromDispl = BigArrays.displacement(from);
    int toDispl = BigArrays.displacement(local_to);
    if (fromSegment == toSegment)
    {
      ByteArrays.fill(array[fromSegment], fromDispl, toDispl, value);
      return;
    }
    if (toDispl != 0) {
      ByteArrays.fill(array[toSegment], 0, toDispl, value);
    }
    for (;;)
    {
      toSegment--;
      if (toSegment <= fromSegment) {
        break;
      }
      ByteArrays.fill(array[toSegment], value);
    }
    ByteArrays.fill(array[fromSegment], fromDispl, 134217728, value);
  }
  
  public static boolean equals(byte[][] local_a1, byte[][] local_a2)
  {
    if (length(local_a1) != length(local_a2)) {
      return false;
    }
    byte[] local_t;
    byte[] local_u;
    int local_j;
    do
    {
      int local_i = local_a1.length;
      while (local_j-- == 0)
      {
        if (local_i-- == 0) {
          break;
        }
        local_t = local_a1[local_i];
        local_u = local_a2[local_i];
        local_j = local_t.length;
      }
    } while (local_t[local_j] == local_u[local_j]);
    return false;
    return true;
  }
  
  public static String toString(byte[][] local_a)
  {
    if (local_a == null) {
      return "null";
    }
    long last = length(local_a) - 1L;
    if (last == -1L) {
      return "[]";
    }
    StringBuilder local_b = new StringBuilder();
    local_b.append('[');
    for (long local_i = 0L;; local_i += 1L)
    {
      local_b.append(String.valueOf(get(local_a, local_i)));
      if (local_i == last) {
        return ']';
      }
      local_b.append(", ");
    }
  }
  
  public static void ensureFromTo(byte[][] local_a, long from, long local_to)
  {
    BigArrays.ensureFromTo(length(local_a), from, local_to);
  }
  
  public static void ensureOffsetLength(byte[][] local_a, long offset, long length)
  {
    BigArrays.ensureOffsetLength(length(local_a), offset, length);
  }
  
  private static void vecSwap(byte[][] local_x, long local_a, long local_b, long local_n)
  {
    int local_i = 0;
    while (local_i < local_n)
    {
      swap(local_x, local_a, local_b);
      local_i++;
      local_a += 1L;
      local_b += 1L;
    }
  }
  
  private static long med3(byte[][] local_x, long local_a, long local_b, long local_c, ByteComparator comp)
  {
    int local_ab = comp.compare(get(local_x, local_a), get(local_x, local_b));
    int local_ac = comp.compare(get(local_x, local_a), get(local_x, local_c));
    int local_bc = comp.compare(get(local_x, local_b), get(local_x, local_c));
    return local_ac > 0 ? local_c : local_bc > 0 ? local_b : local_ab < 0 ? local_a : local_ac < 0 ? local_c : local_bc < 0 ? local_b : local_a;
  }
  
  private static void selectionSort(byte[][] local_a, long from, long local_to, ByteComparator comp)
  {
    for (long local_i = from; local_i < local_to - 1L; local_i += 1L)
    {
      long local_m = local_i;
      for (long local_j = local_i + 1L; local_j < local_to; local_j += 1L) {
        if (comp.compare(get(local_a, local_j), get(local_a, local_m)) < 0) {
          local_m = local_j;
        }
      }
      if (local_m != local_i) {
        swap(local_a, local_i, local_m);
      }
    }
  }
  
  public static void quickSort(byte[][] local_x, long from, long local_to, ByteComparator comp)
  {
    long len = local_to - from;
    if (len < 7L)
    {
      for (long local_i = from; local_i < local_to; local_i += 1L) {
        for (long local_j = local_i; (local_j > from) && (comp.compare(get(local_x, local_j - 1L), get(local_x, local_j)) > 0); local_j -= 1L) {
          swap(local_x, local_j, local_j - 1L);
        }
      }
      return;
    }
    long local_i = from + len / 2L;
    if (len > 7L)
    {
      long local_j = from;
      long local_n = local_to - 1L;
      if (len > 40L)
      {
        long local_s = len / 8L;
        local_j = med3(local_x, local_j, local_j + local_s, local_j + 2L * local_s, comp);
        local_i = med3(local_x, local_i - local_s, local_i, local_i + local_s, comp);
        local_n = med3(local_x, local_n - 2L * local_s, local_n - local_s, local_n, comp);
      }
      local_i = med3(local_x, local_j, local_i, local_n, comp);
    }
    byte local_j = get(local_x, local_i);
    long local_a = from;
    long local_b = local_a;
    long local_c = local_to - 1L;
    long local_d = local_c;
    for (;;)
    {
      int comparison;
      if ((local_b <= local_c) && ((comparison = comp.compare(get(local_x, local_b), local_j)) <= 0))
      {
        if (comparison == 0) {
          swap(local_x, local_a++, local_b);
        }
        local_b += 1L;
      }
      else
      {
        int comparison;
        while ((local_c >= local_b) && ((comparison = comp.compare(get(local_x, local_c), local_j)) >= 0))
        {
          if (comparison == 0) {
            swap(local_x, local_c, local_d--);
          }
          local_c -= 1L;
        }
        if (local_b > local_c) {
          break;
        }
        swap(local_x, local_b++, local_c--);
      }
    }
    long local_n1 = local_to;
    long comparison = Math.min(local_a - from, local_b - local_a);
    vecSwap(local_x, from, local_b - comparison, comparison);
    comparison = Math.min(local_d - local_c, local_n1 - local_d - 1L);
    vecSwap(local_x, local_b, local_n1 - comparison, comparison);
    if ((comparison = local_b - local_a) > 1L) {
      quickSort(local_x, from, from + comparison, comp);
    }
    if ((comparison = local_d - local_c) > 1L) {
      quickSort(local_x, local_n1 - comparison, local_n1, comp);
    }
  }
  
  private static long med3(byte[][] local_x, long local_a, long local_b, long local_c)
  {
    int local_ab = get(local_x, local_a) == get(local_x, local_b) ? 0 : get(local_x, local_a) < get(local_x, local_b) ? -1 : 1;
    int local_ac = get(local_x, local_a) == get(local_x, local_c) ? 0 : get(local_x, local_a) < get(local_x, local_c) ? -1 : 1;
    int local_bc = get(local_x, local_b) == get(local_x, local_c) ? 0 : get(local_x, local_b) < get(local_x, local_c) ? -1 : 1;
    return local_ac > 0 ? local_c : local_bc > 0 ? local_b : local_ab < 0 ? local_a : local_ac < 0 ? local_c : local_bc < 0 ? local_b : local_a;
  }
  
  private static void selectionSort(byte[][] local_a, long from, long local_to)
  {
    for (long local_i = from; local_i < local_to - 1L; local_i += 1L)
    {
      long local_m = local_i;
      for (long local_j = local_i + 1L; local_j < local_to; local_j += 1L) {
        if (get(local_a, local_j) < get(local_a, local_m)) {
          local_m = local_j;
        }
      }
      if (local_m != local_i) {
        swap(local_a, local_i, local_m);
      }
    }
  }
  
  public static void quickSort(byte[][] local_x, ByteComparator comp)
  {
    quickSort(local_x, 0L, length(local_x), comp);
  }
  
  public static void quickSort(byte[][] local_x, long from, long local_to)
  {
    long len = local_to - from;
    if (len < 7L)
    {
      for (long local_i = from; local_i < local_to; local_i += 1L) {
        for (long local_j = local_i; local_j > from; local_j -= 1L)
        {
          if ((get(local_x, local_j - 1L) == get(local_x, local_j) ? 0 : get(local_x, local_j - 1L) < get(local_x, local_j) ? -1 : 1) <= 0) {
            break;
          }
          swap(local_x, local_j, local_j - 1L);
        }
      }
      return;
    }
    long local_i = from + len / 2L;
    if (len > 7L)
    {
      long local_j = from;
      long local_n = local_to - 1L;
      if (len > 40L)
      {
        long local_s = len / 8L;
        local_j = med3(local_x, local_j, local_j + local_s, local_j + 2L * local_s);
        local_i = med3(local_x, local_i - local_s, local_i, local_i + local_s);
        local_n = med3(local_x, local_n - 2L * local_s, local_n - local_s, local_n);
      }
      local_i = med3(local_x, local_j, local_i, local_n);
    }
    byte local_j = get(local_x, local_i);
    long local_a = from;
    long local_b = local_a;
    long local_c = local_to - 1L;
    long local_d = local_c;
    for (;;)
    {
      if (local_b <= local_c)
      {
        int comparison;
        if ((comparison = get(local_x, local_b) == local_j ? 0 : get(local_x, local_b) < local_j ? -1 : 1) <= 0)
        {
          if (comparison == 0) {
            swap(local_x, local_a++, local_b);
          }
          local_b += 1L;
        }
      }
      else
      {
        while (local_c >= local_b)
        {
          int comparison;
          if ((comparison = get(local_x, local_c) == local_j ? 0 : get(local_x, local_c) < local_j ? -1 : 1) < 0) {
            break;
          }
          if (comparison == 0) {
            swap(local_x, local_c, local_d--);
          }
          local_c -= 1L;
        }
        if (local_b > local_c) {
          break;
        }
        swap(local_x, local_b++, local_c--);
      }
    }
    long local_n1 = local_to;
    long comparison = Math.min(local_a - from, local_b - local_a);
    vecSwap(local_x, from, local_b - comparison, comparison);
    comparison = Math.min(local_d - local_c, local_n1 - local_d - 1L);
    vecSwap(local_x, local_b, local_n1 - comparison, comparison);
    if ((comparison = local_b - local_a) > 1L) {
      quickSort(local_x, from, from + comparison);
    }
    if ((comparison = local_d - local_c) > 1L) {
      quickSort(local_x, local_n1 - comparison, local_n1);
    }
  }
  
  public static void quickSort(byte[][] local_x)
  {
    quickSort(local_x, 0L, length(local_x));
  }
  
  public static long binarySearch(byte[][] local_a, long from, long local_to, byte key)
  {
    local_to -= 1L;
    while (from <= local_to)
    {
      long mid = from + local_to >>> 1;
      byte midVal = get(local_a, mid);
      if (midVal < key) {
        from = mid + 1L;
      } else if (midVal > key) {
        local_to = mid - 1L;
      } else {
        return mid;
      }
    }
    return -(from + 1L);
  }
  
  public static long binarySearch(byte[][] local_a, byte key)
  {
    return binarySearch(local_a, 0L, length(local_a), key);
  }
  
  public static long binarySearch(byte[][] local_a, long from, long local_to, byte key, ByteComparator local_c)
  {
    local_to -= 1L;
    while (from <= local_to)
    {
      long mid = from + local_to >>> 1;
      byte midVal = get(local_a, mid);
      int cmp = local_c.compare(midVal, key);
      if (cmp < 0) {
        from = mid + 1L;
      } else if (cmp > 0) {
        local_to = mid - 1L;
      } else {
        return mid;
      }
    }
    return -(from + 1L);
  }
  
  public static long binarySearch(byte[][] local_a, byte key, ByteComparator local_c)
  {
    return binarySearch(local_a, 0L, length(local_a), key, local_c);
  }
  
  public static void radixSort(byte[][] local_a)
  {
    radixSort(local_a, 0L, length(local_a));
  }
  
  public static void radixSort(byte[][] local_a, long from, long local_to)
  {
    int maxLevel = 0;
    int stackSize = 1;
    long[] offsetStack = new long[1];
    int offsetPos = 0;
    long[] lengthStack = new long[1];
    int lengthPos = 0;
    int[] levelStack = new int[1];
    int levelPos = 0;
    offsetStack[(offsetPos++)] = from;
    lengthStack[(lengthPos++)] = (local_to - from);
    levelStack[(levelPos++)] = 0;
    long[] count = new long[256];
    long[] pos = new long[256];
    byte[][] digit = newBigArray(local_to - from);
    while (offsetPos > 0)
    {
      long first = offsetStack[(--offsetPos)];
      long length = lengthStack[(--lengthPos)];
      int level = levelStack[(--levelPos)];
      int signMask = level % 1 == 0 ? 128 : 0;
      if (length < 40L)
      {
        selectionSort(local_a, first, first + length);
      }
      else
      {
        int shift = (0 - level % 1) * 8;
        long local_i = length;
        while (local_i-- != 0L) {
          set(digit, local_i, (byte)(get(local_a, first + local_i) >>> shift & 0xFF ^ signMask));
        }
        long local_i = length;
        while (local_i-- != 0L) {
          count[(get(digit, local_i) & 0xFF)] += 1L;
        }
        int local_i = -1;
        long local_p = 0L;
        for (int local_i1 = 0; local_i1 < 256; local_i1++)
        {
          if (count[local_i1] != 0L)
          {
            local_i = local_i1;
            if ((level < 0) && (count[local_i1] > 1L))
            {
              offsetStack[(offsetPos++)] = (local_p + first);
              lengthStack[(lengthPos++)] = count[local_i1];
              levelStack[(levelPos++)] = (level + 1);
            }
          }
          long tmp345_344 = (local_p + count[local_i1]);
          local_p = tmp345_344;
          pos[local_i1] = tmp345_344;
        }
        long local_i1 = length - count[local_i];
        count[local_i] = 0L;
        int local_c = -1;
        long local_i2 = 0L;
        while (local_i2 < local_i1)
        {
          byte local_t = get(local_a, local_i2 + first);
          local_c = get(digit, local_i2) & 0xFF;
          for (;;)
          {
            long local_d;
            if ((local_d = pos[local_c] -= 1L) <= local_i2) {
              break;
            }
            byte local_z = local_t;
            int local_zz = local_c;
            local_t = get(local_a, local_d + first);
            local_c = get(digit, local_d) & 0xFF;
            set(local_a, local_d + first, local_z);
            set(digit, local_d, (byte)local_zz);
          }
          set(local_a, local_i2 + first, local_t);
          local_i2 += count[local_c];
          count[local_c] = 0L;
        }
      }
    }
  }
  
  private static void selectionSort(byte[][] local_a, byte[][] local_b, long from, long local_to)
  {
    for (long local_i = from; local_i < local_to - 1L; local_i += 1L)
    {
      long local_m = local_i;
      for (long local_j = local_i + 1L; local_j < local_to; local_j += 1L) {
        if ((get(local_a, local_j) < get(local_a, local_m)) || ((get(local_a, local_j) == get(local_a, local_m)) && (get(local_b, local_j) < get(local_b, local_m)))) {
          local_m = local_j;
        }
      }
      if (local_m != local_i)
      {
        byte local_j = get(local_a, local_i);
        set(local_a, local_i, get(local_a, local_m));
        set(local_a, local_m, local_j);
        local_j = get(local_b, local_i);
        set(local_b, local_i, get(local_b, local_m));
        set(local_b, local_m, local_j);
      }
    }
  }
  
  public static void radixSort(byte[][] local_a, byte[][] local_b)
  {
    radixSort(local_a, local_b, 0L, length(local_a));
  }
  
  public static void radixSort(byte[][] local_a, byte[][] local_b, long from, long local_to)
  {
    int layers = 2;
    if (length(local_a) != length(local_b)) {
      throw new IllegalArgumentException("Array size mismatch.");
    }
    int maxLevel = 1;
    int stackSize = 256;
    long[] offsetStack = new long[256];
    int offsetPos = 0;
    long[] lengthStack = new long[256];
    int lengthPos = 0;
    int[] levelStack = new int[256];
    int levelPos = 0;
    offsetStack[(offsetPos++)] = from;
    lengthStack[(lengthPos++)] = (local_to - from);
    levelStack[(levelPos++)] = 0;
    long[] count = new long[256];
    long[] pos = new long[256];
    byte[][] digit = newBigArray(local_to - from);
    while (offsetPos > 0)
    {
      long first = offsetStack[(--offsetPos)];
      long length = lengthStack[(--lengthPos)];
      int level = levelStack[(--levelPos)];
      int signMask = level % 1 == 0 ? 128 : 0;
      if (length < 40L)
      {
        selectionSort(local_a, local_b, first, first + length);
      }
      else
      {
        byte[][] local_k = level < 1 ? local_a : local_b;
        int shift = (0 - level % 1) * 8;
        long local_i = length;
        while (local_i-- != 0L) {
          set(digit, local_i, (byte)(get(local_k, first + local_i) >>> shift & 0xFF ^ signMask));
        }
        long local_i = length;
        while (local_i-- != 0L) {
          count[(get(digit, local_i) & 0xFF)] += 1L;
        }
        int local_i = -1;
        long local_p = 0L;
        for (int local_i1 = 0; local_i1 < 256; local_i1++)
        {
          if (count[local_i1] != 0L)
          {
            local_i = local_i1;
            if ((level < 1) && (count[local_i1] > 1L))
            {
              offsetStack[(offsetPos++)] = (local_p + first);
              lengthStack[(lengthPos++)] = count[local_i1];
              levelStack[(levelPos++)] = (level + 1);
            }
          }
          long tmp397_396 = (local_p + count[local_i1]);
          local_p = tmp397_396;
          pos[local_i1] = tmp397_396;
        }
        long local_i1 = length - count[local_i];
        count[local_i] = 0L;
        int local_c = -1;
        long local_i2 = 0L;
        while (local_i2 < local_i1)
        {
          byte local_t = get(local_a, local_i2 + first);
          byte local_u = get(local_b, local_i2 + first);
          local_c = get(digit, local_i2) & 0xFF;
          for (;;)
          {
            long local_d;
            if ((local_d = pos[local_c] -= 1L) <= local_i2) {
              break;
            }
            byte local_z = local_t;
            int local_zz = local_c;
            local_t = get(local_a, local_d + first);
            set(local_a, local_d + first, local_z);
            local_z = local_u;
            local_u = get(local_b, local_d + first);
            set(local_b, local_d + first, local_z);
            local_c = get(digit, local_d) & 0xFF;
            set(digit, local_d, (byte)local_zz);
          }
          set(local_a, local_i2 + first, local_t);
          set(local_b, local_i2 + first, local_u);
          local_i2 += count[local_c];
          count[local_c] = 0L;
        }
      }
    }
  }
  
  public static byte[][] shuffle(byte[][] local_a, long from, long local_to, Random random)
  {
    long local_i = local_to - from;
    while (local_i-- != 0L)
    {
      long local_p = (random.nextLong() & 0xFFFFFFFF) % (local_i + 1L);
      byte local_t = get(local_a, from + local_i);
      set(local_a, from + local_i, get(local_a, from + local_p));
      set(local_a, from + local_p, local_t);
    }
    return local_a;
  }
  
  public static byte[][] shuffle(byte[][] local_a, Random random)
  {
    long local_i = length(local_a);
    while (local_i-- != 0L)
    {
      long local_p = (random.nextLong() & 0xFFFFFFFF) % (local_i + 1L);
      byte local_t = get(local_a, local_i);
      set(local_a, local_i, get(local_a, local_p));
      set(local_a, local_p, local_t);
    }
    return local_a;
  }
  
  private static final class BigArrayHashStrategy
    implements Hash.Strategy<byte[][]>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public int hashCode(byte[][] local_o)
    {
      return Arrays.deepHashCode(local_o);
    }
    
    public boolean equals(byte[][] local_a, byte[][] local_b)
    {
      return ByteBigArrays.equals(local_a, local_b);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteBigArrays
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */