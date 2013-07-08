package com.bulletphysics.linearmath;

import com.bulletphysics.util.FloatArrayList;
import com.bulletphysics.util.IntArrayList;
import com.bulletphysics.util.ObjectArrayList;
import java.util.Comparator;

public class MiscUtil
{
  public static int getListCapacityForHash(ObjectArrayList<?> list)
  {
    return getListCapacityForHash(list.size());
  }
  
  public static int getListCapacityForHash(int size)
  {
    int local_n = 2;
    while (local_n < size) {
      local_n <<= 1;
    }
    return local_n;
  }
  
  public static <T> void ensureIndex(ObjectArrayList<T> list, int index, T value)
  {
    while (list.size() <= index) {
      list.add(value);
    }
  }
  
  public static void resize(IntArrayList list, int size, int value)
  {
    while (list.size() < size) {
      list.add(value);
    }
    while (list.size() > size) {
      list.remove(list.size() - 1);
    }
  }
  
  public static void resize(FloatArrayList list, int size, float value)
  {
    while (list.size() < size) {
      list.add(value);
    }
    while (list.size() > size) {
      list.remove(list.size() - 1);
    }
  }
  
  public static <T> void resize(ObjectArrayList<T> list, int size, Class<T> valueCls)
  {
    try
    {
      while (list.size() < size) {
        list.add(valueCls != null ? valueCls.newInstance() : null);
      }
      while (list.size() > size) {
        list.removeQuick(list.size() - 1);
      }
    }
    catch (IllegalAccessException local_e)
    {
      throw new IllegalStateException(local_e);
    }
    catch (InstantiationException local_e)
    {
      throw new IllegalStateException(local_e);
    }
  }
  
  public static <T> int indexOf(T[] array, T obj)
  {
    for (int local_i = 0; local_i < array.length; local_i++) {
      if (array[local_i] == obj) {
        return local_i;
      }
    }
    return -1;
  }
  
  public static float GEN_clamped(float local_a, float local_lb, float local_ub)
  {
    return local_ub < local_a ? local_ub : local_a < local_lb ? local_lb : local_a;
  }
  
  private static <T> void downHeap(ObjectArrayList<T> pArr, int local_k, int local_n, Comparator<T> comparator)
  {
    T temp = pArr.getQuick(local_k - 1);
    while (local_k <= local_n / 2)
    {
      int child = 2 * local_k;
      if ((child < local_n) && (comparator.compare(pArr.getQuick(child - 1), pArr.getQuick(child)) < 0)) {
        child++;
      }
      if (comparator.compare(temp, pArr.getQuick(child - 1)) >= 0) {
        break;
      }
      pArr.setQuick(local_k - 1, pArr.getQuick(child - 1));
      local_k = child;
    }
    pArr.setQuick(local_k - 1, temp);
  }
  
  public static <T> void heapSort(ObjectArrayList<T> list, Comparator<T> comparator)
  {
    int local_n = list.size();
    for (int local_k = local_n / 2; local_k > 0; local_k--) {
      downHeap(list, local_k, local_n, comparator);
    }
    while (local_n >= 1)
    {
      swap(list, 0, local_n - 1);
      local_n -= 1;
      downHeap(list, 1, local_n, comparator);
    }
  }
  
  private static <T> void swap(ObjectArrayList<T> list, int index0, int index1)
  {
    T temp = list.getQuick(index0);
    list.setQuick(index0, list.getQuick(index1));
    list.setQuick(index1, temp);
  }
  
  public static <T> void quickSort(ObjectArrayList<T> list, Comparator<T> comparator)
  {
    if (list.size() > 1) {
      quickSortInternal(list, comparator, 0, list.size() - 1);
    }
  }
  
  private static <T> void quickSortInternal(ObjectArrayList<T> list, Comparator<T> comparator, int local_lo, int local_hi)
  {
    int local_i = local_lo;
    int local_j = local_hi;
    T local_x = list.getQuick((local_lo + local_hi) / 2);
    do
    {
      while (comparator.compare(list.getQuick(local_i), local_x) < 0) {
        local_i++;
      }
      while (comparator.compare(local_x, list.getQuick(local_j)) < 0) {
        local_j--;
      }
      if (local_i <= local_j)
      {
        swap(list, local_i, local_j);
        local_i++;
        local_j--;
      }
    } while (local_i <= local_j);
    if (local_lo < local_j) {
      quickSortInternal(list, comparator, local_lo, local_j);
    }
    if (local_i < local_hi) {
      quickSortInternal(list, comparator, local_i, local_hi);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.linearmath.MiscUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */