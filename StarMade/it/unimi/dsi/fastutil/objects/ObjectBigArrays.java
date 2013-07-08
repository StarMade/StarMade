package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.BigArrays;
import it.unimi.dsi.fastutil.Hash.Strategy;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class ObjectBigArrays
{
  public static final long ONEOVERPHI = 106039L;
  public static final Object[][] EMPTY_BIG_ARRAY = new Object[0][];
  public static final Hash.Strategy HASH_STRATEGY = new BigArrayHashStrategy(null);
  private static final int SMALL = 7;
  private static final int MEDIUM = 40;
  
  public static <K> K get(K[][] array, long index)
  {
    return array[BigArrays.segment(index)][BigArrays.displacement(index)];
  }
  
  public static <K> void set(K[][] array, long index, K value)
  {
    array[BigArrays.segment(index)][BigArrays.displacement(index)] = value;
  }
  
  public static <K> void swap(K[][] array, long first, long second)
  {
    K local_t = array[BigArrays.segment(first)][BigArrays.displacement(first)];
    array[BigArrays.segment(first)][BigArrays.displacement(first)] = array[BigArrays.segment(second)][BigArrays.displacement(second)];
    array[BigArrays.segment(second)][BigArrays.displacement(second)] = local_t;
  }
  
  public static <K> long length(K[][] array)
  {
    int length = array.length;
    return length == 0 ? 0L : BigArrays.start(length - 1) + array[(length - 1)].length;
  }
  
  public static <K> void copy(K[][] srcArray, long srcPos, K[][] destArray, long destPos, long length)
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
  
  public static <K> void copyFromBig(K[][] srcArray, long srcPos, K[] destArray, int destPos, int length)
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
  
  public static <K> void copyToBig(K[] srcArray, int srcPos, K[][] destArray, long destPos, long length)
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
  
  public static <K> K[][] newBigArray(K[][] prototype, long length)
  {
    return (Object[][])newBigArray(prototype.getClass().getComponentType(), length);
  }
  
  private static Object[][] newBigArray(Class<?> componentType, long length)
  {
    if ((length == 0L) && (componentType == [Ljava.lang.Object.class)) {
      return EMPTY_BIG_ARRAY;
    }
    int baseLength = (int)((length + 134217727L) / 134217728L);
    Object[][] base = (Object[][])Array.newInstance(componentType, baseLength);
    int residual = (int)(length & 0x7FFFFFF);
    if (residual != 0)
    {
      for (int local_i = 0; local_i < baseLength - 1; local_i++) {
        base[local_i] = ((Object[])(Object[])Array.newInstance(componentType.getComponentType(), 134217728));
      }
      base[(baseLength - 1)] = ((Object[])(Object[])Array.newInstance(componentType.getComponentType(), residual));
    }
    else
    {
      for (int local_i = 0; local_i < baseLength; local_i++) {
        base[local_i] = ((Object[])(Object[])Array.newInstance(componentType.getComponentType(), 134217728));
      }
    }
    return base;
  }
  
  public static Object[][] newBigArray(long length)
  {
    if (length == 0L) {
      return EMPTY_BIG_ARRAY;
    }
    int baseLength = (int)((length + 134217727L) / 134217728L);
    Object[][] base = new Object[baseLength][];
    int residual = (int)(length & 0x7FFFFFF);
    if (residual != 0)
    {
      for (int local_i = 0; local_i < baseLength - 1; local_i++) {
        base[local_i] = new Object[134217728];
      }
      base[(baseLength - 1)] = new Object[residual];
    }
    else
    {
      for (int local_i = 0; local_i < baseLength; local_i++) {
        base[local_i] = new Object[134217728];
      }
    }
    return base;
  }
  
  public static <K> K[][] wrap(K[] array)
  {
    if ((array.length == 0) && (array.getClass() == [Ljava.lang.Object.class)) {
      return (Object[][])EMPTY_BIG_ARRAY;
    }
    if (array.length <= 134217728)
    {
      K[][] bigArray = (Object[][])Array.newInstance(array.getClass(), 1);
      bigArray[0] = array;
      return bigArray;
    }
    K[][] bigArray = (Object[][])newBigArray(array.getClass(), array.length);
    for (int local_i = 0; local_i < bigArray.length; local_i++) {
      System.arraycopy(array, (int)BigArrays.start(local_i), bigArray[local_i], 0, bigArray[local_i].length);
    }
    return bigArray;
  }
  
  public static <K> K[][] ensureCapacity(K[][] array, long length)
  {
    return ensureCapacity(array, length, length(array));
  }
  
  public static <K> K[][] ensureCapacity(K[][] array, long length, long preserve)
  {
    long oldLength = length(array);
    if (length > oldLength)
    {
      int valid = array.length - ((array.length == 0) || ((array.length > 0) && (array[(array.length - 1)].length == 134217728)) ? 0 : 1);
      int baseLength = (int)((length + 134217727L) / 134217728L);
      K[][] base = (Object[][])Arrays.copyOf(array, baseLength);
      Class<?> componentType = array.getClass().getComponentType();
      int residual = (int)(length & 0x7FFFFFF);
      if (residual != 0)
      {
        for (int local_i = valid; local_i < baseLength - 1; local_i++) {
          base[local_i] = ((Object[])(Object[])Array.newInstance(componentType.getComponentType(), 134217728));
        }
        base[(baseLength - 1)] = ((Object[])(Object[])Array.newInstance(componentType.getComponentType(), residual));
      }
      else
      {
        for (int local_i = valid; local_i < baseLength; local_i++) {
          base[local_i] = ((Object[])(Object[])Array.newInstance(componentType.getComponentType(), 134217728));
        }
      }
      if (preserve - valid * 134217728L > 0L) {
        copy(array, valid * 134217728L, base, valid * 134217728L, preserve - valid * 134217728L);
      }
      return base;
    }
    return array;
  }
  
  public static <K> K[][] grow(K[][] array, long length)
  {
    long oldLength = length(array);
    return length > oldLength ? grow(array, length, oldLength) : array;
  }
  
  public static <K> K[][] grow(K[][] array, long length, long preserve)
  {
    long oldLength = length(array);
    return length > oldLength ? ensureCapacity(array, Math.max(106039L * oldLength >>> 16, length), preserve) : array;
  }
  
  public static <K> K[][] trim(K[][] array, long length)
  {
    long oldLength = length(array);
    if (length >= oldLength) {
      return array;
    }
    int baseLength = (int)((length + 134217727L) / 134217728L);
    K[][] base = (Object[][])Arrays.copyOf(array, baseLength);
    int residual = (int)(length & 0x7FFFFFF);
    if (residual != 0) {
      base[(baseLength - 1)] = ObjectArrays.trim(base[(baseLength - 1)], residual);
    }
    return base;
  }
  
  public static <K> K[][] setLength(K[][] array, long length)
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
  
  public static <K> K[][] copy(K[][] array, long offset, long length)
  {
    ensureOffsetLength(array, offset, length);
    K[][] local_a = newBigArray(array, length);
    copy(array, offset, local_a, 0L, length);
    return local_a;
  }
  
  public static <K> K[][] copy(K[][] array)
  {
    K[][] base = (Object[][])array.clone();
    int local_i = base.length;
    while (local_i-- != 0) {
      base[local_i] = ((Object[])array[local_i].clone());
    }
    return base;
  }
  
  public static <K> void fill(K[][] array, K value)
  {
    int local_i = array.length;
    while (local_i-- != 0) {
      ObjectArrays.fill(array[local_i], value);
    }
  }
  
  public static <K> void fill(K[][] array, long from, long local_to, K value)
  {
    long length = length(array);
    BigArrays.ensureFromTo(length, from, local_to);
    int fromSegment = BigArrays.segment(from);
    int toSegment = BigArrays.segment(local_to);
    int fromDispl = BigArrays.displacement(from);
    int toDispl = BigArrays.displacement(local_to);
    if (fromSegment == toSegment)
    {
      ObjectArrays.fill(array[fromSegment], fromDispl, toDispl, value);
      return;
    }
    if (toDispl != 0) {
      ObjectArrays.fill(array[toSegment], 0, toDispl, value);
    }
    for (;;)
    {
      toSegment--;
      if (toSegment <= fromSegment) {
        break;
      }
      ObjectArrays.fill(array[toSegment], value);
    }
    ObjectArrays.fill(array[fromSegment], fromDispl, 134217728, value);
  }
  
  public static <K> boolean equals(K[][] local_a1, K[][] local_a2)
  {
    if (length(local_a1) != length(local_a2)) {
      return false;
    }
    K[] local_t;
    K[] local_u;
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
    } while (local_t[local_j] == null ? local_u[local_j] == null : local_t[local_j].equals(local_u[local_j]));
    return false;
    return true;
  }
  
  public static <K> String toString(K[][] local_a)
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
  
  public static <K> void ensureFromTo(K[][] local_a, long from, long local_to)
  {
    BigArrays.ensureFromTo(length(local_a), from, local_to);
  }
  
  public static <K> void ensureOffsetLength(K[][] local_a, long offset, long length)
  {
    BigArrays.ensureOffsetLength(length(local_a), offset, length);
  }
  
  private static <K> void vecSwap(K[][] local_x, long local_a, long local_b, long local_n)
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
  
  private static <K> long med3(K[][] local_x, long local_a, long local_b, long local_c, Comparator<K> comp)
  {
    int local_ab = comp.compare(get(local_x, local_a), get(local_x, local_b));
    int local_ac = comp.compare(get(local_x, local_a), get(local_x, local_c));
    int local_bc = comp.compare(get(local_x, local_b), get(local_x, local_c));
    return local_ac > 0 ? local_c : local_bc > 0 ? local_b : local_ab < 0 ? local_a : local_ac < 0 ? local_c : local_bc < 0 ? local_b : local_a;
  }
  
  private static <K> void selectionSort(K[][] local_a, long from, long local_to, Comparator<K> comp)
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
  
  public static <K> void quickSort(K[][] local_x, long from, long local_to, Comparator<K> comp)
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
    K local_j = get(local_x, local_i);
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
  
  private static <K> long med3(K[][] local_x, long local_a, long local_b, long local_c)
  {
    int local_ab = ((Comparable)get(local_x, local_a)).compareTo(get(local_x, local_b));
    int local_ac = ((Comparable)get(local_x, local_a)).compareTo(get(local_x, local_c));
    int local_bc = ((Comparable)get(local_x, local_b)).compareTo(get(local_x, local_c));
    return local_ac > 0 ? local_c : local_bc > 0 ? local_b : local_ab < 0 ? local_a : local_ac < 0 ? local_c : local_bc < 0 ? local_b : local_a;
  }
  
  private static <K> void selectionSort(K[][] local_a, long from, long local_to)
  {
    for (long local_i = from; local_i < local_to - 1L; local_i += 1L)
    {
      long local_m = local_i;
      for (long local_j = local_i + 1L; local_j < local_to; local_j += 1L) {
        if (((Comparable)get(local_a, local_j)).compareTo(get(local_a, local_m)) < 0) {
          local_m = local_j;
        }
      }
      if (local_m != local_i) {
        swap(local_a, local_i, local_m);
      }
    }
  }
  
  public static <K> void quickSort(K[][] local_x, Comparator<K> comp)
  {
    quickSort(local_x, 0L, length(local_x), comp);
  }
  
  public static <K> void quickSort(K[][] local_x, long from, long local_to)
  {
    long len = local_to - from;
    if (len < 7L)
    {
      for (long local_i = from; local_i < local_to; local_i += 1L) {
        for (long local_j = local_i; (local_j > from) && (((Comparable)get(local_x, local_j - 1L)).compareTo(get(local_x, local_j)) > 0); local_j -= 1L) {
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
    K local_j = get(local_x, local_i);
    long local_a = from;
    long local_b = local_a;
    long local_c = local_to - 1L;
    long local_d = local_c;
    for (;;)
    {
      int comparison;
      if ((local_b <= local_c) && ((comparison = ((Comparable)get(local_x, local_b)).compareTo(local_j)) <= 0))
      {
        if (comparison == 0) {
          swap(local_x, local_a++, local_b);
        }
        local_b += 1L;
      }
      else
      {
        int comparison;
        while ((local_c >= local_b) && ((comparison = ((Comparable)get(local_x, local_c)).compareTo(local_j)) >= 0))
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
      quickSort(local_x, from, from + comparison);
    }
    if ((comparison = local_d - local_c) > 1L) {
      quickSort(local_x, local_n1 - comparison, local_n1);
    }
  }
  
  public static <K> void quickSort(K[][] local_x)
  {
    quickSort(local_x, 0L, length(local_x));
  }
  
  public static <K> long binarySearch(K[][] local_a, long from, long local_to, K key)
  {
    local_to -= 1L;
    while (from <= local_to)
    {
      long mid = from + local_to >>> 1;
      K midVal = get(local_a, mid);
      int cmp = ((Comparable)midVal).compareTo(key);
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
  
  public static <K> long binarySearch(K[][] local_a, Object key)
  {
    return binarySearch(local_a, 0L, length(local_a), key);
  }
  
  public static <K> long binarySearch(K[][] local_a, long from, long local_to, K key, Comparator<K> local_c)
  {
    local_to -= 1L;
    while (from <= local_to)
    {
      long mid = from + local_to >>> 1;
      K midVal = get(local_a, mid);
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
  
  public static <K> long binarySearch(K[][] local_a, K key, Comparator<K> local_c)
  {
    return binarySearch(local_a, 0L, length(local_a), key, local_c);
  }
  
  public static <K> K[][] shuffle(K[][] local_a, long from, long local_to, Random random)
  {
    long local_i = local_to - from;
    while (local_i-- != 0L)
    {
      long local_p = (random.nextLong() & 0xFFFFFFFF) % (local_i + 1L);
      K local_t = get(local_a, from + local_i);
      set(local_a, from + local_i, get(local_a, from + local_p));
      set(local_a, from + local_p, local_t);
    }
    return local_a;
  }
  
  public static <K> K[][] shuffle(K[][] local_a, Random random)
  {
    long local_i = length(local_a);
    while (local_i-- != 0L)
    {
      long local_p = (random.nextLong() & 0xFFFFFFFF) % (local_i + 1L);
      K local_t = get(local_a, local_i);
      set(local_a, local_i, get(local_a, local_p));
      set(local_a, local_p, local_t);
    }
    return local_a;
  }
  
  private static final class BigArrayHashStrategy<K>
    implements Hash.Strategy<K[][]>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public int hashCode(K[][] local_o)
    {
      return Arrays.deepHashCode(local_o);
    }
    
    public boolean equals(K[][] local_a, K[][] local_b)
    {
      return ObjectBigArrays.equals(local_a, local_b);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectBigArrays
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */