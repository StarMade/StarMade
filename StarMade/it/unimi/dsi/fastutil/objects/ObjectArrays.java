package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Hash.Strategy;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Random;

public class ObjectArrays
{
  public static final long ONEOVERPHI = 106039L;
  public static final Object[] EMPTY_ARRAY = new Object[0];
  private static final int SMALL = 7;
  private static final int MEDIUM = 50;
  public static final Hash.Strategy HASH_STRATEGY = new ArrayHashStrategy(null);
  
  private static <K> K[] newArray(K[] prototype, int length)
  {
    Class<?> componentType = prototype.getClass().getComponentType();
    if ((length == 0) && (componentType == Object.class)) {
      return (Object[])EMPTY_ARRAY;
    }
    return (Object[])Array.newInstance(prototype.getClass().getComponentType(), length);
  }
  
  public static <K> K[] ensureCapacity(K[] array, int length)
  {
    if (length > array.length)
    {
      K[] local_t = newArray(array, length);
      System.arraycopy(array, 0, local_t, 0, array.length);
      return local_t;
    }
    return array;
  }
  
  public static <K> K[] ensureCapacity(K[] array, int length, int preserve)
  {
    if (length > array.length)
    {
      K[] local_t = newArray(array, length);
      System.arraycopy(array, 0, local_t, 0, preserve);
      return local_t;
    }
    return array;
  }
  
  public static <K> K[] grow(K[] array, int length)
  {
    if (length > array.length)
    {
      int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
      K[] local_t = newArray(array, newLength);
      System.arraycopy(array, 0, local_t, 0, array.length);
      return local_t;
    }
    return array;
  }
  
  public static <K> K[] grow(K[] array, int length, int preserve)
  {
    if (length > array.length)
    {
      int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
      K[] local_t = newArray(array, newLength);
      System.arraycopy(array, 0, local_t, 0, preserve);
      return local_t;
    }
    return array;
  }
  
  public static <K> K[] trim(K[] array, int length)
  {
    if (length >= array.length) {
      return array;
    }
    K[] local_t = newArray(array, length);
    System.arraycopy(array, 0, local_t, 0, length);
    return local_t;
  }
  
  public static <K> K[] setLength(K[] array, int length)
  {
    if (length == array.length) {
      return array;
    }
    if (length < array.length) {
      return trim(array, length);
    }
    return ensureCapacity(array, length);
  }
  
  public static <K> K[] copy(K[] array, int offset, int length)
  {
    ensureOffsetLength(array, offset, length);
    K[] local_a = newArray(array, length);
    System.arraycopy(array, offset, local_a, 0, length);
    return local_a;
  }
  
  public static <K> K[] copy(K[] array)
  {
    return (Object[])array.clone();
  }
  
  public static <K> void fill(K[] array, K value)
  {
    int local_i = array.length;
    while (local_i-- != 0) {
      array[local_i] = value;
    }
  }
  
  public static <K> void fill(K[] array, int from, int local_to, K value)
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
  
  public static <K> boolean equals(K[] local_a1, K[] local_a2)
  {
    int local_i = local_a1.length;
    if (local_i != local_a2.length) {
      return false;
    }
    while (local_i-- != 0) {
      if (local_a1[local_i] == null ? local_a2[local_i] != null : !local_a1[local_i].equals(local_a2[local_i])) {
        return false;
      }
    }
    return true;
  }
  
  public static <K> void ensureFromTo(K[] local_a, int from, int local_to)
  {
    it.unimi.dsi.fastutil.Arrays.ensureFromTo(local_a.length, from, local_to);
  }
  
  public static <K> void ensureOffsetLength(K[] local_a, int offset, int length)
  {
    it.unimi.dsi.fastutil.Arrays.ensureOffsetLength(local_a.length, offset, length);
  }
  
  private static <K> void swap(K[] local_x, int local_a, int local_b)
  {
    K local_t = local_x[local_a];
    local_x[local_a] = local_x[local_b];
    local_x[local_b] = local_t;
  }
  
  private static <K> void vecSwap(K[] local_x, int local_a, int local_b, int local_n)
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
  
  private static <K> int med3(K[] local_x, int local_a, int local_b, int local_c, Comparator<K> comp)
  {
    int local_ab = comp.compare(local_x[local_a], local_x[local_b]);
    int local_ac = comp.compare(local_x[local_a], local_x[local_c]);
    int local_bc = comp.compare(local_x[local_b], local_x[local_c]);
    return local_ac > 0 ? local_c : local_bc > 0 ? local_b : local_ab < 0 ? local_a : local_ac < 0 ? local_c : local_bc < 0 ? local_b : local_a;
  }
  
  private static <K> void selectionSort(K[] local_a, int from, int local_to, Comparator<K> comp)
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
        K local_j = local_a[local_i];
        local_a[local_i] = local_a[local_m];
        local_a[local_m] = local_j;
      }
    }
  }
  
  private static <K> void insertionSort(K[] local_a, int from, int local_to, Comparator<K> comp)
  {
    int local_i = from;
    for (;;)
    {
      local_i++;
      if (local_i >= local_to) {
        break;
      }
      K local_t = local_a[local_i];
      int local_j = local_i;
      for (K local_u = local_a[(local_j - 1)]; comp.compare(local_t, local_u) < 0; local_u = local_a[(--local_j - 1)])
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
  
  private static <K> void selectionSort(K[] local_a, int from, int local_to)
  {
    for (int local_i = from; local_i < local_to - 1; local_i++)
    {
      int local_m = local_i;
      for (int local_j = local_i + 1; local_j < local_to; local_j++) {
        if (((Comparable)local_a[local_j]).compareTo(local_a[local_m]) < 0) {
          local_m = local_j;
        }
      }
      if (local_m != local_i)
      {
        K local_j = local_a[local_i];
        local_a[local_i] = local_a[local_m];
        local_a[local_m] = local_j;
      }
    }
  }
  
  private static <K> void insertionSort(K[] local_a, int from, int local_to)
  {
    int local_i = from;
    for (;;)
    {
      local_i++;
      if (local_i >= local_to) {
        break;
      }
      K local_t = local_a[local_i];
      int local_j = local_i;
      for (K local_u = local_a[(local_j - 1)]; ((Comparable)local_t).compareTo(local_u) < 0; local_u = local_a[(--local_j - 1)])
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
  
  public static <K> void quickSort(K[] local_x, int from, int local_to, Comparator<K> comp)
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
    K local_l = local_x[local_m];
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
  
  public static <K> void quickSort(K[] local_x, Comparator<K> comp)
  {
    quickSort(local_x, 0, local_x.length, comp);
  }
  
  private static <K> int med3(K[] local_x, int local_a, int local_b, int local_c)
  {
    int local_ab = ((Comparable)local_x[local_a]).compareTo(local_x[local_b]);
    int local_ac = ((Comparable)local_x[local_a]).compareTo(local_x[local_c]);
    int local_bc = ((Comparable)local_x[local_b]).compareTo(local_x[local_c]);
    return local_ac > 0 ? local_c : local_bc > 0 ? local_b : local_ab < 0 ? local_a : local_ac < 0 ? local_c : local_bc < 0 ? local_b : local_a;
  }
  
  public static <K> void quickSort(K[] local_x, int from, int local_to)
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
    K local_l = local_x[local_m];
    int local_n = from;
    int local_s = local_n;
    int local_c = local_to - 1;
    int local_d = local_c;
    for (;;)
    {
      int comparison;
      if ((local_s <= local_c) && ((comparison = ((Comparable)local_x[local_s]).compareTo(local_l)) <= 0))
      {
        if (comparison == 0) {
          swap(local_x, local_n++, local_s);
        }
        local_s++;
      }
      else
      {
        int comparison;
        while ((local_c >= local_s) && ((comparison = ((Comparable)local_x[local_c]).compareTo(local_l)) >= 0))
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
      quickSort(local_x, from, from + comparison);
    }
    if ((comparison = local_d - local_c) > 1) {
      quickSort(local_x, local_n1 - comparison, local_n1);
    }
  }
  
  public static <K> void quickSort(K[] local_x)
  {
    quickSort(local_x, 0, local_x.length);
  }
  
  public static <K> void mergeSort(K[] local_a, int from, int local_to, K[] supp)
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
    if (((Comparable)supp[(mid - 1)]).compareTo(supp[mid]) <= 0)
    {
      System.arraycopy(supp, from, local_a, from, len);
      return;
    }
    int local_i = from;
    int local_p = from;
    int local_q = mid;
    while (local_i < local_to)
    {
      if ((local_q >= local_to) || ((local_p < mid) && (((Comparable)supp[local_p]).compareTo(supp[local_q]) <= 0))) {
        local_a[local_i] = supp[(local_p++)];
      } else {
        local_a[local_i] = supp[(local_q++)];
      }
      local_i++;
    }
  }
  
  public static <K> void mergeSort(K[] local_a, int from, int local_to)
  {
    mergeSort(local_a, from, local_to, (Object[])local_a.clone());
  }
  
  public static <K> void mergeSort(K[] local_a)
  {
    mergeSort(local_a, 0, local_a.length);
  }
  
  public static <K> void mergeSort(K[] local_a, int from, int local_to, Comparator<K> comp, K[] supp)
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
  
  public static <K> void mergeSort(K[] local_a, int from, int local_to, Comparator<K> comp)
  {
    mergeSort(local_a, from, local_to, comp, (Object[])local_a.clone());
  }
  
  public static <K> void mergeSort(K[] local_a, Comparator<K> comp)
  {
    mergeSort(local_a, 0, local_a.length, comp);
  }
  
  public static <K> int binarySearch(K[] local_a, int from, int local_to, K key)
  {
    
    while (from <= local_to)
    {
      int mid = from + local_to >>> 1;
      K midVal = local_a[mid];
      int cmp = ((Comparable)midVal).compareTo(key);
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
  
  public static <K> int binarySearch(K[] local_a, K key)
  {
    return binarySearch(local_a, 0, local_a.length, key);
  }
  
  public static <K> int binarySearch(K[] local_a, int from, int local_to, K key, Comparator<K> local_c)
  {
    
    while (from <= local_to)
    {
      int mid = from + local_to >>> 1;
      K midVal = local_a[mid];
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
  
  public static <K> int binarySearch(K[] local_a, K key, Comparator<K> local_c)
  {
    return binarySearch(local_a, 0, local_a.length, key, local_c);
  }
  
  public static <K> K[] shuffle(K[] local_a, int from, int local_to, Random random)
  {
    int local_i = local_to - from;
    while (local_i-- != 0)
    {
      int local_p = random.nextInt(local_i + 1);
      K local_t = local_a[(from + local_i)];
      local_a[(from + local_i)] = local_a[(from + local_p)];
      local_a[(from + local_p)] = local_t;
    }
    return local_a;
  }
  
  public static <K> K[] shuffle(K[] local_a, Random random)
  {
    int local_i = local_a.length;
    while (local_i-- != 0)
    {
      int local_p = random.nextInt(local_i + 1);
      K local_t = local_a[local_i];
      local_a[local_i] = local_a[local_p];
      local_a[local_p] = local_t;
    }
    return local_a;
  }
  
  public static <K> K[] reverse(K[] local_a)
  {
    int length = local_a.length;
    int local_i = length / 2;
    while (local_i-- != 0)
    {
      K local_t = local_a[(length - local_i - 1)];
      local_a[(length - local_i - 1)] = local_a[local_i];
      local_a[local_i] = local_t;
    }
    return local_a;
  }
  
  private static final class ArrayHashStrategy<K>
    implements Hash.Strategy<K[]>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public int hashCode(K[] local_o)
    {
      return java.util.Arrays.hashCode(local_o);
    }
    
    public boolean equals(K[] local_a, K[] local_b)
    {
      return ObjectArrays.equals(local_a, local_b);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectArrays
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */