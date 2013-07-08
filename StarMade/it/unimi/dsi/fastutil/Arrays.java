package it.unimi.dsi.fastutil;

import it.unimi.dsi.fastutil.ints.IntComparator;

public class Arrays
{
  private static final int SMALL = 7;
  private static final int MEDIUM = 40;
  
  public static void ensureFromTo(int arrayLength, int from, int local_to)
  {
    if (from < 0) {
      throw new ArrayIndexOutOfBoundsException("Start index (" + from + ") is negative");
    }
    if (from > local_to) {
      throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    if (local_to > arrayLength) {
      throw new ArrayIndexOutOfBoundsException("End index (" + local_to + ") is greater than array length (" + arrayLength + ")");
    }
  }
  
  public static void ensureOffsetLength(int arrayLength, int offset, int length)
  {
    if (offset < 0) {
      throw new ArrayIndexOutOfBoundsException("Offset (" + offset + ") is negative");
    }
    if (length < 0) {
      throw new IllegalArgumentException("Length (" + length + ") is negative");
    }
    if (offset + length > arrayLength) {
      throw new ArrayIndexOutOfBoundsException("Last index (" + (offset + length) + ") is greater than array length (" + arrayLength + ")");
    }
  }
  
  private static void inPlaceMerge(int from, int mid, int local_to, IntComparator comp, Swapper swapper)
  {
    if ((from >= mid) || (mid >= local_to)) {
      return;
    }
    if (local_to - from == 2)
    {
      if (comp.compare(mid, from) < 0) {
        swapper.swap(from, mid);
      }
      return;
    }
    int secondCut;
    int secondCut;
    int firstCut;
    if (mid - from > local_to - mid)
    {
      int firstCut = from + (mid - from) / 2;
      secondCut = lowerBound(mid, local_to, firstCut, comp);
    }
    else
    {
      secondCut = mid + (local_to - mid) / 2;
      firstCut = upperBound(from, mid, secondCut, comp);
    }
    int first2 = firstCut;
    int middle2 = mid;
    int last2 = secondCut;
    if ((middle2 != first2) && (middle2 != last2))
    {
      int first1 = first2;
      int last1 = middle2;
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
  
  private static int lowerBound(int from, int local_to, int pos, IntComparator comp)
  {
    int len = local_to - from;
    while (len > 0)
    {
      int half = len / 2;
      int middle = from + half;
      if (comp.compare(middle, pos) < 0)
      {
        from = middle + 1;
        len -= half + 1;
      }
      else
      {
        len = half;
      }
    }
    return from;
  }
  
  private static int upperBound(int from, int mid, int pos, IntComparator comp)
  {
    int len = mid - from;
    while (len > 0)
    {
      int half = len / 2;
      int middle = from + half;
      if (comp.compare(pos, middle) < 0)
      {
        len = half;
      }
      else
      {
        from = middle + 1;
        len -= half + 1;
      }
    }
    return from;
  }
  
  private static int med3(int local_a, int local_b, int local_c, IntComparator comp)
  {
    int local_ab = comp.compare(local_a, local_b);
    int local_ac = comp.compare(local_a, local_c);
    int local_bc = comp.compare(local_b, local_c);
    return local_ac > 0 ? local_c : local_bc > 0 ? local_b : local_ab < 0 ? local_a : local_ac < 0 ? local_c : local_bc < 0 ? local_b : local_a;
  }
  
  public static void mergeSort(int from, int local_to, IntComparator local_c, Swapper swapper)
  {
    int length = local_to - from;
    if (length < 7)
    {
      for (int local_i = from; local_i < local_to; local_i++) {
        for (int local_j = local_i; (local_j > from) && (local_c.compare(local_j - 1, local_j) > 0); local_j--) {
          swapper.swap(local_j, local_j - 1);
        }
      }
      return;
    }
    int local_i = from + local_to >>> 1;
    mergeSort(from, local_i, local_c, swapper);
    mergeSort(local_i, local_to, local_c, swapper);
    if (local_c.compare(local_i - 1, local_i) <= 0) {
      return;
    }
    inPlaceMerge(from, local_i, local_to, local_c, swapper);
  }
  
  public static void quickSort(int from, int local_to, IntComparator comp, Swapper swapper)
  {
    int len = local_to - from;
    if (len < 7)
    {
      for (int local_i = from; local_i < local_to; local_i++) {
        for (int local_j = local_i; (local_j > from) && (comp.compare(local_j - 1, local_j) > 0); local_j--) {
          swapper.swap(local_j, local_j - 1);
        }
      }
      return;
    }
    int local_i = from + len / 2;
    if (len > 7)
    {
      int local_j = from;
      int local_n = local_to - 1;
      if (len > 40)
      {
        int local_s = len / 8;
        local_j = med3(local_j, local_j + local_s, local_j + 2 * local_s, comp);
        local_i = med3(local_i - local_s, local_i, local_i + local_s, comp);
        local_n = med3(local_n - 2 * local_s, local_n - local_s, local_n, comp);
      }
      local_i = med3(local_j, local_i, local_n, comp);
    }
    int local_j = from;
    int local_n = local_j;
    int local_s = local_to - 1;
    int local_d = local_s;
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
        local_n++;
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
          local_s--;
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
    int local_n1 = local_to;
    int comparison = Math.min(local_j - from, local_n - local_j);
    vecSwap(swapper, from, local_n - comparison, comparison);
    comparison = Math.min(local_d - local_s, local_n1 - local_d - 1);
    vecSwap(swapper, local_n, local_n1 - comparison, comparison);
    if ((comparison = local_n - local_j) > 1) {
      quickSort(from, from + comparison, comp, swapper);
    }
    if ((comparison = local_d - local_s) > 1) {
      quickSort(local_n1 - comparison, local_n1, comp, swapper);
    }
  }
  
  private static void vecSwap(Swapper swapper, int from, int local_l, int local_s)
  {
    int local_i = 0;
    while (local_i < local_s)
    {
      swapper.swap(from, local_l);
      local_i++;
      from++;
      local_l++;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.Arrays
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */