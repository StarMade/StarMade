package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.Hash.Strategy;
import it.unimi.dsi.fastutil.ints.IntArrays;
import java.io.Serializable;
import java.util.Random;

public class ByteArrays
{
  public static final long ONEOVERPHI = 106039L;
  public static final byte[] EMPTY_ARRAY = new byte[0];
  private static final int SMALL = 7;
  private static final int MEDIUM = 50;
  private static final int DIGIT_BITS = 8;
  private static final int DIGIT_MASK = 255;
  private static final int DIGITS_PER_ELEMENT = 1;
  public static final Hash.Strategy<byte[]> HASH_STRATEGY = new ArrayHashStrategy(null);
  
  public static byte[] ensureCapacity(byte[] array, int length)
  {
    if (length > array.length)
    {
      byte[] local_t = new byte[length];
      System.arraycopy(array, 0, local_t, 0, array.length);
      return local_t;
    }
    return array;
  }
  
  public static byte[] ensureCapacity(byte[] array, int length, int preserve)
  {
    if (length > array.length)
    {
      byte[] local_t = new byte[length];
      System.arraycopy(array, 0, local_t, 0, preserve);
      return local_t;
    }
    return array;
  }
  
  public static byte[] grow(byte[] array, int length)
  {
    if (length > array.length)
    {
      int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
      byte[] local_t = new byte[newLength];
      System.arraycopy(array, 0, local_t, 0, array.length);
      return local_t;
    }
    return array;
  }
  
  public static byte[] grow(byte[] array, int length, int preserve)
  {
    if (length > array.length)
    {
      int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
      byte[] local_t = new byte[newLength];
      System.arraycopy(array, 0, local_t, 0, preserve);
      return local_t;
    }
    return array;
  }
  
  public static byte[] trim(byte[] array, int length)
  {
    if (length >= array.length) {
      return array;
    }
    byte[] local_t = length == 0 ? EMPTY_ARRAY : new byte[length];
    System.arraycopy(array, 0, local_t, 0, length);
    return local_t;
  }
  
  public static byte[] setLength(byte[] array, int length)
  {
    if (length == array.length) {
      return array;
    }
    if (length < array.length) {
      return trim(array, length);
    }
    return ensureCapacity(array, length);
  }
  
  public static byte[] copy(byte[] array, int offset, int length)
  {
    ensureOffsetLength(array, offset, length);
    byte[] local_a = length == 0 ? EMPTY_ARRAY : new byte[length];
    System.arraycopy(array, offset, local_a, 0, length);
    return local_a;
  }
  
  public static byte[] copy(byte[] array)
  {
    return (byte[])array.clone();
  }
  
  public static void fill(byte[] array, byte value)
  {
    int local_i = array.length;
    while (local_i-- != 0) {
      array[local_i] = value;
    }
  }
  
  public static void fill(byte[] array, int from, int local_to, byte value)
  {
    ensureFromTo(array, from, local_to);
    if (from == 0) {
      while (local_to-- != 0) {
        array[local_to] = value;
      }
    }
    for (int local_i = from; local_i < local_to; local_i++) {
      array[local_i] = value;
    }
  }
  
  public static boolean equals(byte[] local_a1, byte[] local_a2)
  {
    int local_i = local_a1.length;
    if (local_i != local_a2.length) {
      return false;
    }
    while (local_i-- != 0) {
      if (local_a1[local_i] != local_a2[local_i]) {
        return false;
      }
    }
    return true;
  }
  
  public static void ensureFromTo(byte[] local_a, int from, int local_to)
  {
    it.unimi.dsi.fastutil.Arrays.ensureFromTo(local_a.length, from, local_to);
  }
  
  public static void ensureOffsetLength(byte[] local_a, int offset, int length)
  {
    it.unimi.dsi.fastutil.Arrays.ensureOffsetLength(local_a.length, offset, length);
  }
  
  private static void swap(byte[] local_x, int local_a, int local_b)
  {
    byte local_t = local_x[local_a];
    local_x[local_a] = local_x[local_b];
    local_x[local_b] = local_t;
  }
  
  private static void vecSwap(byte[] local_x, int local_a, int local_b, int local_n)
  {
    int local_i = 0;
    while (local_i < local_n)
    {
      swap(local_x, local_a, local_b);
      local_i++;
      local_a++;
      local_b++;
    }
  }
  
  private static int med3(byte[] local_x, int local_a, int local_b, int local_c, ByteComparator comp)
  {
    int local_ab = comp.compare(local_x[local_a], local_x[local_b]);
    int local_ac = comp.compare(local_x[local_a], local_x[local_c]);
    int local_bc = comp.compare(local_x[local_b], local_x[local_c]);
    return local_ac > 0 ? local_c : local_bc > 0 ? local_b : local_ab < 0 ? local_a : local_ac < 0 ? local_c : local_bc < 0 ? local_b : local_a;
  }
  
  private static void selectionSort(byte[] local_a, int from, int local_to, ByteComparator comp)
  {
    for (int local_i = from; local_i < local_to - 1; local_i++)
    {
      int local_m = local_i;
      for (int local_j = local_i + 1; local_j < local_to; local_j++) {
        if (comp.compare(local_a[local_j], local_a[local_m]) < 0) {
          local_m = local_j;
        }
      }
      if (local_m != local_i)
      {
        byte local_j = local_a[local_i];
        local_a[local_i] = local_a[local_m];
        local_a[local_m] = local_j;
      }
    }
  }
  
  private static void insertionSort(byte[] local_a, int from, int local_to, ByteComparator comp)
  {
    int local_i = from;
    for (;;)
    {
      local_i++;
      if (local_i >= local_to) {
        break;
      }
      byte local_t = local_a[local_i];
      int local_j = local_i;
      for (byte local_u = local_a[(local_j - 1)]; comp.compare(local_t, local_u) < 0; local_u = local_a[(--local_j - 1)])
      {
        local_a[local_j] = local_u;
        if (from == local_j - 1)
        {
          local_j--;
          break;
        }
      }
      local_a[local_j] = local_t;
    }
  }
  
  private static void selectionSort(byte[] local_a, int from, int local_to)
  {
    for (int local_i = from; local_i < local_to - 1; local_i++)
    {
      int local_m = local_i;
      for (int local_j = local_i + 1; local_j < local_to; local_j++) {
        if (local_a[local_j] < local_a[local_m]) {
          local_m = local_j;
        }
      }
      if (local_m != local_i)
      {
        byte local_j = local_a[local_i];
        local_a[local_i] = local_a[local_m];
        local_a[local_m] = local_j;
      }
    }
  }
  
  private static void insertionSort(byte[] local_a, int from, int local_to)
  {
    int local_i = from;
    for (;;)
    {
      local_i++;
      if (local_i >= local_to) {
        break;
      }
      byte local_t = local_a[local_i];
      int local_j = local_i;
      for (byte local_u = local_a[(local_j - 1)]; local_t < local_u; local_u = local_a[(--local_j - 1)])
      {
        local_a[local_j] = local_u;
        if (from == local_j - 1)
        {
          local_j--;
          break;
        }
      }
      local_a[local_j] = local_t;
    }
  }
  
  public static void quickSort(byte[] local_x, int from, int local_to, ByteComparator comp)
  {
    int len = local_to - from;
    if (len < 7)
    {
      selectionSort(local_x, from, local_to, comp);
      return;
    }
    int local_m = from + len / 2;
    if (len > 7)
    {
      int local_l = from;
      int local_n = local_to - 1;
      if (len > 50)
      {
        int local_s = len / 8;
        local_l = med3(local_x, local_l, local_l + local_s, local_l + 2 * local_s, comp);
        local_m = med3(local_x, local_m - local_s, local_m, local_m + local_s, comp);
        local_n = med3(local_x, local_n - 2 * local_s, local_n - local_s, local_n, comp);
      }
      local_m = med3(local_x, local_l, local_m, local_n, comp);
    }
    byte local_l = local_x[local_m];
    int local_n = from;
    int local_s = local_n;
    int local_c = local_to - 1;
    int local_d = local_c;
    for (;;)
    {
      int comparison;
      if ((local_s <= local_c) && ((comparison = comp.compare(local_x[local_s], local_l)) <= 0))
      {
        if (comparison == 0) {
          swap(local_x, local_n++, local_s);
        }
        local_s++;
      }
      else
      {
        int comparison;
        while ((local_c >= local_s) && ((comparison = comp.compare(local_x[local_c], local_l)) >= 0))
        {
          if (comparison == 0) {
            swap(local_x, local_c, local_d--);
          }
          local_c--;
        }
        if (local_s > local_c) {
          break;
        }
        swap(local_x, local_s++, local_c--);
      }
    }
    int local_n1 = local_to;
    int comparison = Math.min(local_n - from, local_s - local_n);
    vecSwap(local_x, from, local_s - comparison, comparison);
    comparison = Math.min(local_d - local_c, local_n1 - local_d - 1);
    vecSwap(local_x, local_s, local_n1 - comparison, comparison);
    if ((comparison = local_s - local_n) > 1) {
      quickSort(local_x, from, from + comparison, comp);
    }
    if ((comparison = local_d - local_c) > 1) {
      quickSort(local_x, local_n1 - comparison, local_n1, comp);
    }
  }
  
  public static void quickSort(byte[] local_x, ByteComparator comp)
  {
    quickSort(local_x, 0, local_x.length, comp);
  }
  
  private static int med3(byte[] local_x, int local_a, int local_b, int local_c)
  {
    int local_ab = local_x[local_a] == local_x[local_b] ? 0 : local_x[local_a] < local_x[local_b] ? -1 : 1;
    int local_ac = local_x[local_a] == local_x[local_c] ? 0 : local_x[local_a] < local_x[local_c] ? -1 : 1;
    int local_bc = local_x[local_b] == local_x[local_c] ? 0 : local_x[local_b] < local_x[local_c] ? -1 : 1;
    return local_ac > 0 ? local_c : local_bc > 0 ? local_b : local_ab < 0 ? local_a : local_ac < 0 ? local_c : local_bc < 0 ? local_b : local_a;
  }
  
  public static void quickSort(byte[] local_x, int from, int local_to)
  {
    int len = local_to - from;
    if (len < 7)
    {
      selectionSort(local_x, from, local_to);
      return;
    }
    int local_m = from + len / 2;
    if (len > 7)
    {
      int local_l = from;
      int local_n = local_to - 1;
      if (len > 50)
      {
        int local_s = len / 8;
        local_l = med3(local_x, local_l, local_l + local_s, local_l + 2 * local_s);
        local_m = med3(local_x, local_m - local_s, local_m, local_m + local_s);
        local_n = med3(local_x, local_n - 2 * local_s, local_n - local_s, local_n);
      }
      local_m = med3(local_x, local_l, local_m, local_n);
    }
    byte local_l = local_x[local_m];
    int local_n = from;
    int local_s = local_n;
    int local_c = local_to - 1;
    int local_d = local_c;
    for (;;)
    {
      if (local_s <= local_c)
      {
        int comparison;
        if ((comparison = local_x[local_s] == local_l ? 0 : local_x[local_s] < local_l ? -1 : 1) <= 0)
        {
          if (comparison == 0) {
            swap(local_x, local_n++, local_s);
          }
          local_s++;
        }
      }
      else
      {
        while (local_c >= local_s)
        {
          int comparison;
          if ((comparison = local_x[local_c] == local_l ? 0 : local_x[local_c] < local_l ? -1 : 1) < 0) {
            break;
          }
          if (comparison == 0) {
            swap(local_x, local_c, local_d--);
          }
          local_c--;
        }
        if (local_s > local_c) {
          break;
        }
        swap(local_x, local_s++, local_c--);
      }
    }
    int local_n1 = local_to;
    int comparison = Math.min(local_n - from, local_s - local_n);
    vecSwap(local_x, from, local_s - comparison, comparison);
    comparison = Math.min(local_d - local_c, local_n1 - local_d - 1);
    vecSwap(local_x, local_s, local_n1 - comparison, comparison);
    if ((comparison = local_s - local_n) > 1) {
      quickSort(local_x, from, from + comparison);
    }
    if ((comparison = local_d - local_c) > 1) {
      quickSort(local_x, local_n1 - comparison, local_n1);
    }
  }
  
  public static void quickSort(byte[] local_x)
  {
    quickSort(local_x, 0, local_x.length);
  }
  
  public static void mergeSort(byte[] local_a, int from, int local_to, byte[] supp)
  {
    int len = local_to - from;
    if (len < 7)
    {
      insertionSort(local_a, from, local_to);
      return;
    }
    int mid = from + local_to >>> 1;
    mergeSort(supp, from, mid, local_a);
    mergeSort(supp, mid, local_to, local_a);
    if (supp[(mid - 1)] <= supp[mid])
    {
      System.arraycopy(supp, from, local_a, from, len);
      return;
    }
    int local_i = from;
    int local_p = from;
    int local_q = mid;
    while (local_i < local_to)
    {
      if ((local_q >= local_to) || ((local_p < mid) && (supp[local_p] <= supp[local_q]))) {
        local_a[local_i] = supp[(local_p++)];
      } else {
        local_a[local_i] = supp[(local_q++)];
      }
      local_i++;
    }
  }
  
  public static void mergeSort(byte[] local_a, int from, int local_to)
  {
    mergeSort(local_a, from, local_to, (byte[])local_a.clone());
  }
  
  public static void mergeSort(byte[] local_a)
  {
    mergeSort(local_a, 0, local_a.length);
  }
  
  public static void mergeSort(byte[] local_a, int from, int local_to, ByteComparator comp, byte[] supp)
  {
    int len = local_to - from;
    if (len < 7)
    {
      insertionSort(local_a, from, local_to, comp);
      return;
    }
    int mid = from + local_to >>> 1;
    mergeSort(supp, from, mid, comp, local_a);
    mergeSort(supp, mid, local_to, comp, local_a);
    if (comp.compare(supp[(mid - 1)], supp[mid]) <= 0)
    {
      System.arraycopy(supp, from, local_a, from, len);
      return;
    }
    int local_i = from;
    int local_p = from;
    int local_q = mid;
    while (local_i < local_to)
    {
      if ((local_q >= local_to) || ((local_p < mid) && (comp.compare(supp[local_p], supp[local_q]) <= 0))) {
        local_a[local_i] = supp[(local_p++)];
      } else {
        local_a[local_i] = supp[(local_q++)];
      }
      local_i++;
    }
  }
  
  public static void mergeSort(byte[] local_a, int from, int local_to, ByteComparator comp)
  {
    mergeSort(local_a, from, local_to, comp, (byte[])local_a.clone());
  }
  
  public static void mergeSort(byte[] local_a, ByteComparator comp)
  {
    mergeSort(local_a, 0, local_a.length, comp);
  }
  
  public static int binarySearch(byte[] local_a, int from, int local_to, byte key)
  {
    
    while (from <= local_to)
    {
      int mid = from + local_to >>> 1;
      byte midVal = local_a[mid];
      if (midVal < key) {
        from = mid + 1;
      } else if (midVal > key) {
        local_to = mid - 1;
      } else {
        return mid;
      }
    }
    return -(from + 1);
  }
  
  public static int binarySearch(byte[] local_a, byte key)
  {
    return binarySearch(local_a, 0, local_a.length, key);
  }
  
  public static int binarySearch(byte[] local_a, int from, int local_to, byte key, ByteComparator local_c)
  {
    
    while (from <= local_to)
    {
      int mid = from + local_to >>> 1;
      byte midVal = local_a[mid];
      int cmp = local_c.compare(midVal, key);
      if (cmp < 0) {
        from = mid + 1;
      } else if (cmp > 0) {
        local_to = mid - 1;
      } else {
        return mid;
      }
    }
    return -(from + 1);
  }
  
  public static int binarySearch(byte[] local_a, byte key, ByteComparator local_c)
  {
    return binarySearch(local_a, 0, local_a.length, key, local_c);
  }
  
  public static void radixSort(byte[] local_a)
  {
    radixSort(local_a, 0, local_a.length);
  }
  
  public static void radixSort(byte[] local_a, int from, int local_to)
  {
    int maxLevel = 0;
    int stackSize = 1;
    int[] offsetStack = new int[1];
    int offsetPos = 0;
    int[] lengthStack = new int[1];
    int lengthPos = 0;
    int[] levelStack = new int[1];
    int levelPos = 0;
    offsetStack[(offsetPos++)] = from;
    lengthStack[(lengthPos++)] = (local_to - from);
    levelStack[(levelPos++)] = 0;
    int[] count = new int[256];
    int[] pos = new int[256];
    byte[] digit = new byte[local_to - from];
    while (offsetPos > 0)
    {
      int first = offsetStack[(--offsetPos)];
      int length = lengthStack[(--lengthPos)];
      int level = levelStack[(--levelPos)];
      int signMask = level % 1 == 0 ? 128 : 0;
      if (length < 50)
      {
        selectionSort(local_a, first, first + length);
      }
      else
      {
        int shift = (0 - level % 1) * 8;
        int local_i = length;
        while (local_i-- != 0) {
          digit[local_i] = ((byte)(local_a[(first + local_i)] >>> shift & 0xFF ^ signMask));
        }
        int local_i = length;
        while (local_i-- != 0) {
          count[(digit[local_i] & 0xFF)] += 1;
        }
        int local_i = -1;
        int local_i1 = 0;
        int local_p = 0;
        while (local_i1 < 256)
        {
          if (count[local_i1] != 0)
          {
            local_i = local_i1;
            if ((level < 0) && (count[local_i1] > 1))
            {
              offsetStack[(offsetPos++)] = (local_p + first);
              lengthStack[(lengthPos++)] = count[local_i1];
              levelStack[(levelPos++)] = (level + 1);
            }
          }
          int tmp324_323 = (local_p + count[local_i1]);
          local_p = tmp324_323;
          pos[local_i1] = tmp324_323;
          local_i1++;
        }
        int local_i1 = length - count[local_i];
        count[local_i] = 0;
        int local_p = 0;
        int local_c = -1;
        while (local_p < local_i1)
        {
          byte local_t = local_a[(local_p + first)];
          local_c = digit[local_p] & 0xFF;
          for (;;)
          {
            int local_d;
            if ((local_d = pos[local_c] -= 1) <= local_p) {
              break;
            }
            byte local_z = local_t;
            int local_zz = local_c;
            local_t = local_a[(local_d + first)];
            local_c = digit[local_d] & 0xFF;
            local_a[(local_d + first)] = local_z;
            digit[local_d] = ((byte)local_zz);
          }
          local_a[(local_p + first)] = local_t;
          local_p += count[local_c];
          count[local_c] = 0;
        }
      }
    }
  }
  
  private static void insertionSortIndirect(int[] perm, byte[] local_a, int from, int local_to)
  {
    int local_i = from;
    for (;;)
    {
      local_i++;
      if (local_i >= local_to) {
        break;
      }
      int local_t = perm[local_i];
      int local_j = local_i;
      for (int local_u = perm[(local_j - 1)]; local_a[local_t] < local_a[local_u]; local_u = perm[(--local_j - 1)])
      {
        perm[local_j] = local_u;
        if (from == local_j - 1)
        {
          local_j--;
          break;
        }
      }
      perm[local_j] = local_t;
    }
  }
  
  public static void radixSortIndirect(int[] perm, byte[] local_a, boolean stable)
  {
    radixSortIndirect(perm, local_a, 0, perm.length, stable);
  }
  
  public static void radixSortIndirect(int[] perm, byte[] local_a, int from, int local_to, boolean stable)
  {
    int maxLevel = 0;
    int stackSize = 1;
    int[] offsetStack = new int[1];
    int offsetPos = 0;
    int[] lengthStack = new int[1];
    int lengthPos = 0;
    int[] levelStack = new int[1];
    int levelPos = 0;
    offsetStack[(offsetPos++)] = from;
    lengthStack[(lengthPos++)] = (local_to - from);
    levelStack[(levelPos++)] = 0;
    int[] count = new int[256];
    int[] pos = stable ? null : new int[256];
    int[] support = stable ? new int[perm.length] : null;
    byte[] digit = new byte[local_to - from];
    while (offsetPos > 0)
    {
      int first = offsetStack[(--offsetPos)];
      int length = lengthStack[(--lengthPos)];
      int level = levelStack[(--levelPos)];
      int signMask = level % 1 == 0 ? 128 : 0;
      if (length < 50)
      {
        insertionSortIndirect(perm, local_a, first, first + length);
      }
      else
      {
        int shift = (0 - level % 1) * 8;
        int local_i = length;
        while (local_i-- != 0) {
          digit[local_i] = ((byte)(local_a[perm[(first + local_i)]] >>> shift & 0xFF ^ signMask));
        }
        int local_i = length;
        while (local_i-- != 0) {
          count[(digit[local_i] & 0xFF)] += 1;
        }
        int local_i = -1;
        int local_i1 = 0;
        int local_p = 0;
        while (local_i1 < 256)
        {
          if (count[local_i1] != 0)
          {
            local_i = local_i1;
            if ((level < 0) && (count[local_i1] > 1))
            {
              offsetStack[(offsetPos++)] = (local_p + first);
              lengthStack[(lengthPos++)] = count[local_i1];
              levelStack[(levelPos++)] = (level + 1);
            }
          }
          if (stable)
          {
            int tmp357_356 = (local_p + count[local_i1]);
            local_p = tmp357_356;
            count[local_i1] = tmp357_356;
          }
          else
          {
            int tmp376_375 = (local_p + count[local_i1]);
            local_p = tmp376_375;
            pos[local_i1] = tmp376_375;
          }
          local_i1++;
        }
        if (stable)
        {
          int local_i1 = length;
          while (local_i1-- != 0)
          {
            byte tmp416_415 = (digit[local_i1] & 0xFF);
            int[] tmp416_405 = count;
            int tmp420_419 = (tmp416_405[tmp416_415] - 1);
            tmp416_405[tmp416_415] = tmp420_419;
            support[tmp420_419] = perm[(first + local_i1)];
          }
          System.arraycopy(support, 0, perm, first, length);
          IntArrays.fill(count, 0);
        }
        else
        {
          int local_i1 = length - count[local_i];
          count[local_i] = 0;
          int local_p = 0;
          int local_c = -1;
          while (local_p < local_i1)
          {
            int local_t = perm[(local_p + first)];
            local_c = digit[local_p] & 0xFF;
            for (;;)
            {
              int local_d;
              if ((local_d = pos[local_c] -= 1) <= local_p) {
                break;
              }
              int local_z = local_t;
              int local_zz = local_c;
              local_t = perm[(local_d + first)];
              local_c = digit[local_d] & 0xFF;
              perm[(local_d + first)] = local_z;
              digit[local_d] = ((byte)local_zz);
            }
            perm[(local_p + first)] = local_t;
            local_p += count[local_c];
            count[local_c] = 0;
          }
        }
      }
    }
  }
  
  private static void selectionSort(byte[] local_a, byte[] local_b, int from, int local_to)
  {
    for (int local_i = from; local_i < local_to - 1; local_i++)
    {
      int local_m = local_i;
      for (int local_j = local_i + 1; local_j < local_to; local_j++) {
        if ((local_a[local_j] < local_a[local_m]) || ((local_a[local_j] == local_a[local_m]) && (local_b[local_j] < local_b[local_m]))) {
          local_m = local_j;
        }
      }
      if (local_m != local_i)
      {
        byte local_j = local_a[local_i];
        local_a[local_i] = local_a[local_m];
        local_a[local_m] = local_j;
        local_j = local_b[local_i];
        local_b[local_i] = local_b[local_m];
        local_b[local_m] = local_j;
      }
    }
  }
  
  public static void radixSort(byte[] local_a, byte[] local_b)
  {
    radixSort(local_a, local_b, 0, local_a.length);
  }
  
  public static void radixSort(byte[] local_a, byte[] local_b, int from, int local_to)
  {
    int layers = 2;
    if (local_a.length != local_b.length) {
      throw new IllegalArgumentException("Array size mismatch.");
    }
    int maxLevel = 1;
    int stackSize = 256;
    int[] offsetStack = new int[256];
    int offsetPos = 0;
    int[] lengthStack = new int[256];
    int lengthPos = 0;
    int[] levelStack = new int[256];
    int levelPos = 0;
    offsetStack[(offsetPos++)] = from;
    lengthStack[(lengthPos++)] = (local_to - from);
    levelStack[(levelPos++)] = 0;
    int[] count = new int[256];
    int[] pos = new int[256];
    byte[] digit = new byte[local_to - from];
    while (offsetPos > 0)
    {
      int first = offsetStack[(--offsetPos)];
      int length = lengthStack[(--lengthPos)];
      int level = levelStack[(--levelPos)];
      int signMask = level % 1 == 0 ? 128 : 0;
      if (length < 50)
      {
        selectionSort(local_a, local_b, first, first + length);
      }
      else
      {
        byte[] local_k = level < 1 ? local_a : local_b;
        int shift = (0 - level % 1) * 8;
        int local_i = length;
        while (local_i-- != 0) {
          digit[local_i] = ((byte)(local_k[(first + local_i)] >>> shift & 0xFF ^ signMask));
        }
        int local_i = length;
        while (local_i-- != 0) {
          count[(digit[local_i] & 0xFF)] += 1;
        }
        int local_i = -1;
        int local_i1 = 0;
        int local_p = 0;
        while (local_i1 < 256)
        {
          if (count[local_i1] != 0)
          {
            local_i = local_i1;
            if ((level < 1) && (count[local_i1] > 1))
            {
              offsetStack[(offsetPos++)] = (local_p + first);
              lengthStack[(lengthPos++)] = count[local_i1];
              levelStack[(levelPos++)] = (level + 1);
            }
          }
          int tmp369_368 = (local_p + count[local_i1]);
          local_p = tmp369_368;
          pos[local_i1] = tmp369_368;
          local_i1++;
        }
        int local_i1 = length - count[local_i];
        count[local_i] = 0;
        int local_p = 0;
        int local_c = -1;
        while (local_p < local_i1)
        {
          byte local_t = local_a[(local_p + first)];
          byte local_u = local_b[(local_p + first)];
          local_c = digit[local_p] & 0xFF;
          for (;;)
          {
            int local_d;
            if ((local_d = pos[local_c] -= 1) <= local_p) {
              break;
            }
            byte local_z = local_t;
            int local_zz = local_c;
            local_t = local_a[(local_d + first)];
            local_a[(local_d + first)] = local_z;
            local_z = local_u;
            local_u = local_b[(local_d + first)];
            local_b[(local_d + first)] = local_z;
            local_c = digit[local_d] & 0xFF;
            digit[local_d] = ((byte)local_zz);
          }
          local_a[(local_p + first)] = local_t;
          local_b[(local_p + first)] = local_u;
          local_p += count[local_c];
          count[local_c] = 0;
        }
      }
    }
  }
  
  private static void insertionSortIndirect(int[] perm, byte[] local_a, byte[] local_b, int from, int local_to)
  {
    int local_i = from;
    for (;;)
    {
      local_i++;
      if (local_i >= local_to) {
        break;
      }
      int local_t = perm[local_i];
      int local_j = local_i;
      for (int local_u = perm[(local_j - 1)]; (local_a[local_t] < local_a[local_u]) || ((local_a[local_t] == local_a[local_u]) && (local_b[local_t] < local_b[local_u])); local_u = perm[(--local_j - 1)])
      {
        perm[local_j] = local_u;
        if (from == local_j - 1)
        {
          local_j--;
          break;
        }
      }
      perm[local_j] = local_t;
    }
  }
  
  public static void radixSortIndirect(int[] perm, byte[] local_a, byte[] local_b, boolean stable)
  {
    radixSortIndirect(perm, local_a, local_b, 0, perm.length, stable);
  }
  
  public static void radixSortIndirect(int[] perm, byte[] local_a, byte[] local_b, int from, int local_to, boolean stable)
  {
    int layers = 2;
    if (local_a.length != local_b.length) {
      throw new IllegalArgumentException("Array size mismatch.");
    }
    int maxLevel = 1;
    int stackSize = 256;
    int[] offsetStack = new int[256];
    int offsetPos = 0;
    int[] lengthStack = new int[256];
    int lengthPos = 0;
    int[] levelStack = new int[256];
    int levelPos = 0;
    offsetStack[(offsetPos++)] = from;
    lengthStack[(lengthPos++)] = (local_to - from);
    levelStack[(levelPos++)] = 0;
    int[] count = new int[256];
    int[] pos = stable ? null : new int[256];
    int[] support = stable ? new int[perm.length] : null;
    byte[] digit = new byte[local_to - from];
    while (offsetPos > 0)
    {
      int first = offsetStack[(--offsetPos)];
      int length = lengthStack[(--lengthPos)];
      int level = levelStack[(--levelPos)];
      int signMask = level % 1 == 0 ? 128 : 0;
      if (length < 50)
      {
        insertionSortIndirect(perm, local_a, local_b, first, first + length);
      }
      else
      {
        byte[] local_k = level < 1 ? local_a : local_b;
        int shift = (0 - level % 1) * 8;
        int local_i = length;
        while (local_i-- != 0) {
          digit[local_i] = ((byte)(local_k[perm[(first + local_i)]] >>> shift & 0xFF ^ signMask));
        }
        int local_i = length;
        while (local_i-- != 0) {
          count[(digit[local_i] & 0xFF)] += 1;
        }
        int local_i = -1;
        int local_i1 = 0;
        int local_p = 0;
        while (local_i1 < 256)
        {
          if (count[local_i1] != 0)
          {
            local_i = local_i1;
            if ((level < 1) && (count[local_i1] > 1))
            {
              offsetStack[(offsetPos++)] = (local_p + first);
              lengthStack[(lengthPos++)] = count[local_i1];
              levelStack[(levelPos++)] = (level + 1);
            }
          }
          if (stable)
          {
            int tmp403_402 = (local_p + count[local_i1]);
            local_p = tmp403_402;
            count[local_i1] = tmp403_402;
          }
          else
          {
            int tmp422_421 = (local_p + count[local_i1]);
            local_p = tmp422_421;
            pos[local_i1] = tmp422_421;
          }
          local_i1++;
        }
        if (stable)
        {
          int local_i1 = length;
          while (local_i1-- != 0)
          {
            byte tmp462_461 = (digit[local_i1] & 0xFF);
            int[] tmp462_451 = count;
            int tmp466_465 = (tmp462_451[tmp462_461] - 1);
            tmp462_451[tmp462_461] = tmp466_465;
            support[tmp466_465] = perm[(first + local_i1)];
          }
          System.arraycopy(support, 0, perm, first, length);
          IntArrays.fill(count, 0);
        }
        else
        {
          int local_i1 = length - count[local_i];
          count[local_i] = 0;
          int local_p = 0;
          int local_c = -1;
          while (local_p < local_i1)
          {
            int local_t = perm[(local_p + first)];
            local_c = digit[local_p] & 0xFF;
            for (;;)
            {
              int local_d;
              if ((local_d = pos[local_c] -= 1) <= local_p) {
                break;
              }
              int local_z = local_t;
              int local_zz = local_c;
              local_t = perm[(local_d + first)];
              local_c = digit[local_d] & 0xFF;
              perm[(local_d + first)] = local_z;
              digit[local_d] = ((byte)local_zz);
            }
            perm[(local_p + first)] = local_t;
            local_p += count[local_c];
            count[local_c] = 0;
          }
        }
      }
    }
  }
  
  private static void selectionSort(byte[][] local_a, int from, int local_to, int level)
  {
    int layers = local_a.length;
    int firstLayer = level / 1;
    for (int local_i = from; local_i < local_to - 1; local_i++)
    {
      int local_m = local_i;
      for (int local_j = local_i + 1; local_j < local_to; local_j++) {
        for (int local_p = firstLayer; local_p < layers; local_p++) {
          if (local_a[local_p][local_j] < local_a[local_p][local_m]) {
            local_m = local_j;
          } else {
            if (local_a[local_p][local_j] > local_a[local_p][local_m]) {
              break;
            }
          }
        }
      }
      if (local_m != local_i)
      {
        int local_j = layers;
        while (local_j-- != 0)
        {
          byte local_p = local_a[local_j][local_i];
          local_a[local_j][local_i] = local_a[local_j][local_m];
          local_a[local_j][local_m] = local_p;
        }
      }
    }
  }
  
  public static void radixSort(byte[][] local_a)
  {
    radixSort(local_a, 0, local_a[0].length);
  }
  
  public static void radixSort(byte[][] local_a, int from, int local_to)
  {
    int layers = local_a.length;
    int maxLevel = 1 * layers - 1;
    int local_p = layers;
    int local_l = local_a[0].length;
    while (local_p-- != 0) {
      if (local_a[local_p].length != local_l) {
        throw new IllegalArgumentException("The array of index " + local_p + " has not the same length of the array of index 0.");
      }
    }
    int local_p = 255 * (layers * 1 - 1) + 1;
    int[] local_l = new int[local_p];
    int offsetPos = 0;
    int[] lengthStack = new int[local_p];
    int lengthPos = 0;
    int[] levelStack = new int[local_p];
    int levelPos = 0;
    local_l[(offsetPos++)] = from;
    lengthStack[(lengthPos++)] = (local_to - from);
    levelStack[(levelPos++)] = 0;
    int[] count = new int[256];
    int[] pos = new int[256];
    byte[] digit = new byte[local_to - from];
    byte[] local_t = new byte[layers];
    while (offsetPos > 0)
    {
      int first = local_l[(--offsetPos)];
      int length = lengthStack[(--lengthPos)];
      int level = levelStack[(--levelPos)];
      int signMask = level % 1 == 0 ? 128 : 0;
      if (length < 50)
      {
        selectionSort(local_a, first, first + length, level);
      }
      else
      {
        byte[] local_k = local_a[(level / 1)];
        int shift = (0 - level % 1) * 8;
        int local_i = length;
        while (local_i-- != 0) {
          digit[local_i] = ((byte)(local_k[(first + local_i)] >>> shift & 0xFF ^ signMask));
        }
        int local_i = length;
        while (local_i-- != 0) {
          count[(digit[local_i] & 0xFF)] += 1;
        }
        int local_i = -1;
        int local_i1 = 0;
        int local_p1 = 0;
        while (local_i1 < 256)
        {
          if (count[local_i1] != 0)
          {
            local_i = local_i1;
            if ((level < maxLevel) && (count[local_i1] > 1))
            {
              local_l[(offsetPos++)] = (local_p1 + first);
              lengthStack[(lengthPos++)] = count[local_i1];
              levelStack[(levelPos++)] = (level + 1);
            }
          }
          int tmp425_424 = (local_p1 + count[local_i1]);
          local_p1 = tmp425_424;
          pos[local_i1] = tmp425_424;
          local_i1++;
        }
        int local_i1 = length - count[local_i];
        count[local_i] = 0;
        int local_p1 = 0;
        int local_c = -1;
        while (local_p1 < local_i1)
        {
          int local_p2 = layers;
          while (local_p2-- != 0) {
            local_t[local_p2] = local_a[local_p2][(local_p1 + first)];
          }
          local_c = digit[local_p1] & 0xFF;
          for (;;)
          {
            int local_d;
            if ((local_d = pos[local_c] -= 1) <= local_p1) {
              break;
            }
            int local_p2 = layers;
            while (local_p2-- != 0)
            {
              byte local_u = local_t[local_p2];
              local_t[local_p2] = local_a[local_p2][(local_d + first)];
              local_a[local_p2][(local_d + first)] = local_u;
            }
            int local_p2 = local_c;
            local_c = digit[local_d] & 0xFF;
            digit[local_d] = ((byte)local_p2);
          }
          int local_p2 = layers;
          while (local_p2-- != 0) {
            local_a[local_p2][(local_p1 + first)] = local_t[local_p2];
          }
          local_p1 += count[local_c];
          count[local_c] = 0;
        }
      }
    }
  }
  
  public static byte[] shuffle(byte[] local_a, int from, int local_to, Random random)
  {
    int local_i = local_to - from;
    while (local_i-- != 0)
    {
      int local_p = random.nextInt(local_i + 1);
      byte local_t = local_a[(from + local_i)];
      local_a[(from + local_i)] = local_a[(from + local_p)];
      local_a[(from + local_p)] = local_t;
    }
    return local_a;
  }
  
  public static byte[] shuffle(byte[] local_a, Random random)
  {
    int local_i = local_a.length;
    while (local_i-- != 0)
    {
      int local_p = random.nextInt(local_i + 1);
      byte local_t = local_a[local_i];
      local_a[local_i] = local_a[local_p];
      local_a[local_p] = local_t;
    }
    return local_a;
  }
  
  public static byte[] reverse(byte[] local_a)
  {
    int length = local_a.length;
    int local_i = length / 2;
    while (local_i-- != 0)
    {
      byte local_t = local_a[(length - local_i - 1)];
      local_a[(length - local_i - 1)] = local_a[local_i];
      local_a[local_i] = local_t;
    }
    return local_a;
  }
  
  private static final class ArrayHashStrategy
    implements Hash.Strategy<byte[]>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public int hashCode(byte[] local_o)
    {
      return java.util.Arrays.hashCode(local_o);
    }
    
    public boolean equals(byte[] local_a, byte[] local_b)
    {
      return ByteArrays.equals(local_a, local_b);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteArrays
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */