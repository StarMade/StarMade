package it.unimi.dsi.fastutil.booleans;

import it.unimi.dsi.fastutil.Hash.Strategy;
import java.io.Serializable;
import java.util.Random;

public class BooleanArrays
{
  public static final long ONEOVERPHI = 106039L;
  public static final boolean[] EMPTY_ARRAY = new boolean[0];
  private static final int SMALL = 7;
  private static final int MEDIUM = 50;
  public static final Hash.Strategy<boolean[]> HASH_STRATEGY = new ArrayHashStrategy(null);
  
  public static boolean[] ensureCapacity(boolean[] array, int length)
  {
    if (length > array.length)
    {
      boolean[] local_t = new boolean[length];
      System.arraycopy(array, 0, local_t, 0, array.length);
      return local_t;
    }
    return array;
  }
  
  public static boolean[] ensureCapacity(boolean[] array, int length, int preserve)
  {
    if (length > array.length)
    {
      boolean[] local_t = new boolean[length];
      System.arraycopy(array, 0, local_t, 0, preserve);
      return local_t;
    }
    return array;
  }
  
  public static boolean[] grow(boolean[] array, int length)
  {
    if (length > array.length)
    {
      int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
      boolean[] local_t = new boolean[newLength];
      System.arraycopy(array, 0, local_t, 0, array.length);
      return local_t;
    }
    return array;
  }
  
  public static boolean[] grow(boolean[] array, int length, int preserve)
  {
    if (length > array.length)
    {
      int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
      boolean[] local_t = new boolean[newLength];
      System.arraycopy(array, 0, local_t, 0, preserve);
      return local_t;
    }
    return array;
  }
  
  public static boolean[] trim(boolean[] array, int length)
  {
    if (length >= array.length) {
      return array;
    }
    boolean[] local_t = length == 0 ? EMPTY_ARRAY : new boolean[length];
    System.arraycopy(array, 0, local_t, 0, length);
    return local_t;
  }
  
  public static boolean[] setLength(boolean[] array, int length)
  {
    if (length == array.length) {
      return array;
    }
    if (length < array.length) {
      return trim(array, length);
    }
    return ensureCapacity(array, length);
  }
  
  public static boolean[] copy(boolean[] array, int offset, int length)
  {
    ensureOffsetLength(array, offset, length);
    boolean[] local_a = length == 0 ? EMPTY_ARRAY : new boolean[length];
    System.arraycopy(array, offset, local_a, 0, length);
    return local_a;
  }
  
  public static boolean[] copy(boolean[] array)
  {
    return (boolean[])array.clone();
  }
  
  public static void fill(boolean[] array, boolean value)
  {
    int local_i = array.length;
    while (local_i-- != 0) {
      array[local_i] = value;
    }
  }
  
  public static void fill(boolean[] array, int from, int local_to, boolean value)
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
  
  public static boolean equals(boolean[] local_a1, boolean[] local_a2)
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
  
  public static void ensureFromTo(boolean[] local_a, int from, int local_to)
  {
    it.unimi.dsi.fastutil.Arrays.ensureFromTo(local_a.length, from, local_to);
  }
  
  public static void ensureOffsetLength(boolean[] local_a, int offset, int length)
  {
    it.unimi.dsi.fastutil.Arrays.ensureOffsetLength(local_a.length, offset, length);
  }
  
  private static void swap(boolean[] local_x, int local_a, int local_b)
  {
    boolean local_t = local_x[local_a];
    local_x[local_a] = local_x[local_b];
    local_x[local_b] = local_t;
  }
  
  private static void vecSwap(boolean[] local_x, int local_a, int local_b, int local_n)
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
  
  private static int med3(boolean[] local_x, int local_a, int local_b, int local_c, BooleanComparator comp)
  {
    int local_ab = comp.compare(local_x[local_a], local_x[local_b]);
    int local_ac = comp.compare(local_x[local_a], local_x[local_c]);
    int local_bc = comp.compare(local_x[local_b], local_x[local_c]);
    return local_ac > 0 ? local_c : local_bc > 0 ? local_b : local_ab < 0 ? local_a : local_ac < 0 ? local_c : local_bc < 0 ? local_b : local_a;
  }
  
  private static void selectionSort(boolean[] local_a, int from, int local_to, BooleanComparator comp)
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
        boolean local_j = local_a[local_i];
        local_a[local_i] = local_a[local_m];
        local_a[local_m] = local_j;
      }
    }
  }
  
  private static void insertionSort(boolean[] local_a, int from, int local_to, BooleanComparator comp)
  {
    int local_i = from;
    for (;;)
    {
      local_i++;
      if (local_i >= local_to) {
        break;
      }
      boolean local_t = local_a[local_i];
      int local_j = local_i;
      for (boolean local_u = local_a[(local_j - 1)]; comp.compare(local_t, local_u) < 0; local_u = local_a[(--local_j - 1)])
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
  
  private static void selectionSort(boolean[] local_a, int from, int local_to)
  {
    for (int local_i = from; local_i < local_to - 1; local_i++)
    {
      int local_m = local_i;
      for (int local_j = local_i + 1; local_j < local_to; local_j++) {
        if ((local_a[local_j] == 0) && (local_a[local_m] != 0)) {
          local_m = local_j;
        }
      }
      if (local_m != local_i)
      {
        boolean local_j = local_a[local_i];
        local_a[local_i] = local_a[local_m];
        local_a[local_m] = local_j;
      }
    }
  }
  
  private static void insertionSort(boolean[] local_a, int from, int local_to)
  {
    int local_i = from;
    for (;;)
    {
      local_i++;
      if (local_i >= local_to) {
        break;
      }
      boolean local_t = local_a[local_i];
      int local_j = local_i;
      for (boolean local_u = local_a[(local_j - 1)]; (!local_t) && (local_u); local_u = local_a[(--local_j - 1)])
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
  
  public static void quickSort(boolean[] local_x, int from, int local_to, BooleanComparator comp)
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
    boolean local_l = local_x[local_m];
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
  
  public static void quickSort(boolean[] local_x, BooleanComparator comp)
  {
    quickSort(local_x, 0, local_x.length, comp);
  }
  
  private static int med3(boolean[] local_x, int local_a, int local_b, int local_c)
  {
    int local_ab = local_x[local_a] == local_x[local_b] ? 0 : (local_x[local_a] == 0) && (local_x[local_b] != 0) ? -1 : 1;
    int local_ac = local_x[local_a] == local_x[local_c] ? 0 : (local_x[local_a] == 0) && (local_x[local_c] != 0) ? -1 : 1;
    int local_bc = local_x[local_b] == local_x[local_c] ? 0 : (local_x[local_b] == 0) && (local_x[local_c] != 0) ? -1 : 1;
    return local_ac > 0 ? local_c : local_bc > 0 ? local_b : local_ab < 0 ? local_a : local_ac < 0 ? local_c : local_bc < 0 ? local_b : local_a;
  }
  
  public static void quickSort(boolean[] local_x, int from, int local_to)
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
    boolean local_l = local_x[local_m];
    int local_n = from;
    int local_s = local_n;
    int local_c = local_to - 1;
    int local_d = local_c;
    for (;;)
    {
      if (local_s <= local_c)
      {
        int comparison;
        if ((comparison = local_x[local_s] == local_l ? 0 : (local_x[local_s] == 0) && (local_l) ? -1 : 1) <= 0)
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
          if ((comparison = local_x[local_c] == local_l ? 0 : (local_x[local_c] == 0) && (local_l) ? -1 : 1) < 0) {
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
  
  public static void quickSort(boolean[] local_x)
  {
    quickSort(local_x, 0, local_x.length);
  }
  
  public static void mergeSort(boolean[] local_a, int from, int local_to, boolean[] supp)
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
    if ((supp[(mid - 1)] == 0) || (supp[mid] != 0))
    {
      System.arraycopy(supp, from, local_a, from, len);
      return;
    }
    int local_i = from;
    int local_p = from;
    int local_q = mid;
    while (local_i < local_to)
    {
      if ((local_q >= local_to) || ((local_p < mid) && ((supp[local_p] == 0) || (supp[local_q] != 0)))) {
        local_a[local_i] = supp[(local_p++)];
      } else {
        local_a[local_i] = supp[(local_q++)];
      }
      local_i++;
    }
  }
  
  public static void mergeSort(boolean[] local_a, int from, int local_to)
  {
    mergeSort(local_a, from, local_to, (boolean[])local_a.clone());
  }
  
  public static void mergeSort(boolean[] local_a)
  {
    mergeSort(local_a, 0, local_a.length);
  }
  
  public static void mergeSort(boolean[] local_a, int from, int local_to, BooleanComparator comp, boolean[] supp)
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
  
  public static void mergeSort(boolean[] local_a, int from, int local_to, BooleanComparator comp)
  {
    mergeSort(local_a, from, local_to, comp, (boolean[])local_a.clone());
  }
  
  public static void mergeSort(boolean[] local_a, BooleanComparator comp)
  {
    mergeSort(local_a, 0, local_a.length, comp);
  }
  
  public static boolean[] shuffle(boolean[] local_a, int from, int local_to, Random random)
  {
    int local_i = local_to - from;
    while (local_i-- != 0)
    {
      int local_p = random.nextInt(local_i + 1);
      boolean local_t = local_a[(from + local_i)];
      local_a[(from + local_i)] = local_a[(from + local_p)];
      local_a[(from + local_p)] = local_t;
    }
    return local_a;
  }
  
  public static boolean[] shuffle(boolean[] local_a, Random random)
  {
    int local_i = local_a.length;
    while (local_i-- != 0)
    {
      int local_p = random.nextInt(local_i + 1);
      boolean local_t = local_a[local_i];
      local_a[local_i] = local_a[local_p];
      local_a[local_p] = local_t;
    }
    return local_a;
  }
  
  public static boolean[] reverse(boolean[] local_a)
  {
    int length = local_a.length;
    int local_i = length / 2;
    while (local_i-- != 0)
    {
      boolean local_t = local_a[(length - local_i - 1)];
      local_a[(length - local_i - 1)] = local_a[local_i];
      local_a[local_i] = local_t;
    }
    return local_a;
  }
  
  private static final class ArrayHashStrategy
    implements Hash.Strategy<boolean[]>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public int hashCode(boolean[] local_o)
    {
      return java.util.Arrays.hashCode(local_o);
    }
    
    public boolean equals(boolean[] local_a, boolean[] local_b)
    {
      return BooleanArrays.equals(local_a, local_b);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanArrays
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */