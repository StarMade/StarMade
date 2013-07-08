package it.unimi.dsi.fastutil;

import it.unimi.dsi.fastutil.ints.IntBigArrays;
import it.unimi.dsi.fastutil.longs.LongComparator;
import java.io.PrintStream;

public class BigArrays
{
  public static final int SEGMENT_SHIFT = 27;
  public static final int SEGMENT_SIZE = 134217728;
  public static final int SEGMENT_MASK = 134217727;
  private static final int SMALL = 7;
  private static final int MEDIUM = 40;
  
  public static int segment(long index)
  {
    return (int)(index >>> 27);
  }
  
  public static int displacement(long index)
  {
    return (int)(index & 0x7FFFFFF);
  }
  
  public static long start(int segment)
  {
    return segment << 27;
  }
  
  public static long index(int segment, int displacement)
  {
    return start(segment) + displacement;
  }
  
  public static void ensureFromTo(long bigArrayLength, long from, long local_to)
  {
    if (from < 0L) {
      throw new ArrayIndexOutOfBoundsException("Start index (" + from + ") is negative");
    }
    if (from > local_to) {
      throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    if (local_to > bigArrayLength) {
      throw new ArrayIndexOutOfBoundsException("End index (" + local_to + ") is greater than big-array length (" + bigArrayLength + ")");
    }
  }
  
  public static void ensureOffsetLength(long bigArrayLength, long offset, long length)
  {
    if (offset < 0L) {
      throw new ArrayIndexOutOfBoundsException("Offset (" + offset + ") is negative");
    }
    if (length < 0L) {
      throw new IllegalArgumentException("Length (" + length + ") is negative");
    }
    if (offset + length > bigArrayLength) {
      throw new ArrayIndexOutOfBoundsException("Last index (" + (offset + length) + ") is greater than big-array length (" + bigArrayLength + ")");
    }
  }
  
  private static void inPlaceMerge(long from, long mid, long local_to, LongComparator comp, BigSwapper swapper)
  {
    if ((from >= mid) || (mid >= local_to)) {
      return;
    }
    if (local_to - from == 2L)
    {
      if (comp.compare(mid, from) < 0) {
        swapper.swap(from, mid);
      }
      return;
    }
    long secondCut;
    long secondCut;
    long firstCut;
    if (mid - from > local_to - mid)
    {
      long firstCut = from + (mid - from) / 2L;
      secondCut = lowerBound(mid, local_to, firstCut, comp);
    }
    else
    {
      secondCut = mid + (local_to - mid) / 2L;
      firstCut = upperBound(from, mid, secondCut, comp);
    }
    long first2 = firstCut;
    long middle2 = mid;
    long last2 = secondCut;
    if ((middle2 != first2) && (middle2 != last2))
    {
      long first1 = first2;
      long last1 = middle2;
      while (first1 < --last1) {
        swapper.swap(first1++, last1);
      }
      first1 = middle2;
      last1 = last2;
      while (first1 < --last1) {
        swapper.swap(first1++, last1);
      }
      first1 = first2;
      last1 = last2;
      while (first1 < --last1) {
        swapper.swap(first1++, last1);
      }
    }
    mid = firstCut + (secondCut - mid);
    inPlaceMerge(from, firstCut, mid, comp, swapper);
    inPlaceMerge(mid, secondCut, local_to, comp, swapper);
  }
  
  private static long lowerBound(long mid, long local_to, long firstCut, LongComparator comp)
  {
    long len = local_to - mid;
    while (len > 0L)
    {
      long half = len / 2L;
      long middle = mid + half;
      if (comp.compare(middle, firstCut) < 0)
      {
        mid = middle + 1L;
        len -= half + 1L;
      }
      else
      {
        len = half;
      }
    }
    return mid;
  }
  
  private static long med3(long local_a, long local_b, long local_c, LongComparator comp)
  {
    int local_ab = comp.compare(local_a, local_b);
    int local_ac = comp.compare(local_a, local_c);
    int local_bc = comp.compare(local_b, local_c);
    return local_ac > 0 ? local_c : local_bc > 0 ? local_b : local_ab < 0 ? local_a : local_ac < 0 ? local_c : local_bc < 0 ? local_b : local_a;
  }
  
  public static void mergeSort(long from, long local_to, LongComparator comp, BigSwapper swapper)
  {
    long length = local_to - from;
    if (length < 7L)
    {
      for (long local_i = from; local_i < local_to; local_i += 1L) {
        for (long local_j = local_i; (local_j > from) && (comp.compare(local_j - 1L, local_j) > 0); local_j -= 1L) {
          swapper.swap(local_j, local_j - 1L);
        }
      }
      return;
    }
    long local_i = from + local_to >>> 1;
    mergeSort(from, local_i, comp, swapper);
    mergeSort(local_i, local_to, comp, swapper);
    if (comp.compare(local_i - 1L, local_i) <= 0) {
      return;
    }
    inPlaceMerge(from, local_i, local_to, comp, swapper);
  }
  
  public static void quickSort(long from, long local_to, LongComparator comp, BigSwapper swapper)
  {
    long len = local_to - from;
    if (len < 7L)
    {
      for (long local_i = from; local_i < local_to; local_i += 1L) {
        for (long local_j = local_i; (local_j > from) && (comp.compare(local_j - 1L, local_j) > 0); local_j -= 1L) {
          swapper.swap(local_j, local_j - 1L);
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
        local_j = med3(local_j, local_j + local_s, local_j + 2L * local_s, comp);
        local_i = med3(local_i - local_s, local_i, local_i + local_s, comp);
        local_n = med3(local_n - 2L * local_s, local_n - local_s, local_n, comp);
      }
      local_i = med3(local_j, local_i, local_n, comp);
    }
    long local_j = from;
    long local_n = local_j;
    long local_s = local_to - 1L;
    long local_d = local_s;
    for (;;)
    {
      int comparison;
      if ((local_n <= local_s) && ((comparison = comp.compare(local_n, local_i)) <= 0))
      {
        if (comparison == 0)
        {
          if (local_j == local_i) {
            local_i = local_n;
          } else if (local_n == local_i) {
            local_i = local_j;
          }
          swapper.swap(local_j++, local_n);
        }
        local_n += 1L;
      }
      else
      {
        int comparison;
        while ((local_s >= local_n) && ((comparison = comp.compare(local_s, local_i)) >= 0))
        {
          if (comparison == 0)
          {
            if (local_s == local_i) {
              local_i = local_d;
            } else if (local_d == local_i) {
              local_i = local_s;
            }
            swapper.swap(local_s, local_d--);
          }
          local_s -= 1L;
        }
        if (local_n > local_s) {
          break;
        }
        if (local_n == local_i) {
          local_i = local_d;
        } else if (local_s == local_i) {
          local_i = local_s;
        }
        swapper.swap(local_n++, local_s--);
      }
    }
    long local_n1 = from + len;
    long comparison = Math.min(local_j - from, local_n - local_j);
    vecSwap(swapper, from, local_n - comparison, comparison);
    comparison = Math.min(local_d - local_s, local_n1 - local_d - 1L);
    vecSwap(swapper, local_n, local_n1 - comparison, comparison);
    if ((comparison = local_n - local_j) > 1L) {
      quickSort(from, from + comparison, comp, swapper);
    }
    if ((comparison = local_d - local_s) > 1L) {
      quickSort(local_n1 - comparison, local_n1, comp, swapper);
    }
  }
  
  private static long upperBound(long from, long mid, long secondCut, LongComparator comp)
  {
    long len = mid - from;
    while (len > 0L)
    {
      long half = len / 2L;
      long middle = from + half;
      if (comp.compare(secondCut, middle) < 0)
      {
        len = half;
      }
      else
      {
        from = middle + 1L;
        len -= half + 1L;
      }
    }
    return from;
  }
  
  private static void vecSwap(BigSwapper swapper, long from, long local_l, long local_s)
  {
    int local_i = 0;
    while (local_i < local_s)
    {
      swapper.swap(from, local_l);
      local_i++;
      from += 1L;
      local_l += 1L;
    }
  }
  
  public static void main(String[] arg)
  {
    int[][] local_a = IntBigArrays.newBigArray(1L << Integer.parseInt(arg[0]));
    int local_k = 10;
    while (local_k-- != 0)
    {
      long start = -System.currentTimeMillis();
      long local_x = 0L;
      long local_i = IntBigArrays.length(local_a);
      while (local_i-- != 0L) {
        local_x ^= local_i ^ IntBigArrays.get(local_a, local_i);
      }
      if (local_x == 0L) {
        System.err.println();
      }
      System.out.println("Single loop: " + (start + System.currentTimeMillis()) + "ms");
      start = -System.currentTimeMillis();
      long local_y = 0L;
      int local_i = local_a.length;
      while (local_i-- != 0)
      {
        int[] local_t = local_a[local_i];
        int local_d = local_t.length;
        while (local_d-- != 0) {
          local_y ^= local_t[local_d] ^ index(local_i, local_d);
        }
      }
      if (local_y == 0L) {
        System.err.println();
      }
      if (local_x != local_y) {
        throw new AssertionError();
      }
      System.out.println("Double loop: " + (start + System.currentTimeMillis()) + "ms");
      long local_z = 0L;
      long local_i = IntBigArrays.length(local_a);
      int local_d = local_a.length;
      while (local_d-- != 0)
      {
        int[] local_t1 = local_a[local_d];
        int local_d1 = local_t1.length;
        while (local_d1-- != 0) {
          local_y ^= local_t1[local_d1] ^ --local_i;
        }
      }
      if (local_z == 0L) {
        System.err.println();
      }
      if (local_x != local_z) {
        throw new AssertionError();
      }
      System.out.println("Double loop (with additional index): " + (start + System.currentTimeMillis()) + "ms");
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.BigArrays
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */