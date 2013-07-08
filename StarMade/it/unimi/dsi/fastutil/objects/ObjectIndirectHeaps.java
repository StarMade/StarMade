package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.ints.IntArrays;
import java.util.Comparator;

public class ObjectIndirectHeaps
{
  public static <K> int downHeap(K[] refArray, int[] heap, int[] inv, int size, int local_i, Comparator<K> local_c)
  {
    if (local_i >= size) {
      throw new IllegalArgumentException("Heap position (" + local_i + ") is larger than or equal to heap size (" + size + ")");
    }
    int local_e = heap[local_i];
    K local_E = refArray[local_e];
    if (local_c == null)
    {
      int child;
      while ((child = 2 * local_i + 1) < size)
      {
        if ((child + 1 < size) && (((Comparable)refArray[heap[(child + 1)]]).compareTo(refArray[heap[child]]) < 0)) {
          child++;
        }
        if (((Comparable)local_E).compareTo(refArray[heap[child]]) <= 0) {
          break;
        }
        heap[local_i] = heap[child];
        inv[heap[local_i]] = local_i;
        local_i = child;
      }
    }
    int child;
    while ((child = 2 * local_i + 1) < size)
    {
      if ((child + 1 < size) && (local_c.compare(refArray[heap[(child + 1)]], refArray[heap[child]]) < 0)) {
        child++;
      }
      if (local_c.compare(local_E, refArray[heap[child]]) <= 0) {
        break;
      }
      heap[local_i] = heap[child];
      inv[heap[local_i]] = local_i;
      local_i = child;
    }
    heap[local_i] = local_e;
    inv[local_e] = local_i;
    return local_i;
  }
  
  public static <K> int upHeap(K[] refArray, int[] heap, int[] inv, int size, int local_i, Comparator<K> local_c)
  {
    if (local_i >= size) {
      throw new IllegalArgumentException("Heap position (" + local_i + ") is larger than or equal to heap size (" + size + ")");
    }
    int local_e = heap[local_i];
    K local_E = refArray[local_e];
    if (local_c == null)
    {
      int parent;
      while ((local_i != 0) && ((parent = (local_i - 1) / 2) >= 0) && (((Comparable)refArray[heap[parent]]).compareTo(local_E) > 0))
      {
        heap[local_i] = heap[parent];
        inv[heap[local_i]] = local_i;
        local_i = parent;
      }
    }
    int parent;
    while ((local_i != 0) && ((parent = (local_i - 1) / 2) >= 0) && (local_c.compare(refArray[heap[parent]], local_E) > 0))
    {
      heap[local_i] = heap[parent];
      inv[heap[local_i]] = local_i;
      local_i = parent;
    }
    heap[local_i] = local_e;
    inv[local_e] = local_i;
    return local_i;
  }
  
  public static <K> void makeHeap(K[] refArray, int offset, int length, int[] heap, int[] inv, Comparator<K> local_c)
  {
    ObjectArrays.ensureOffsetLength(refArray, offset, length);
    if (heap.length < length) {
      throw new IllegalArgumentException("The heap length (" + heap.length + ") is smaller than the number of elements (" + length + ")");
    }
    if (inv.length < refArray.length) {
      throw new IllegalArgumentException("The inversion array length (" + heap.length + ") is smaller than the length of the reference array (" + refArray.length + ")");
    }
    IntArrays.fill(inv, 0, refArray.length, -1);
    int local_i = length;
    while (local_i-- != 0)
    {
      int tmp134_133 = (offset + local_i);
      heap[local_i] = tmp134_133;
      inv[tmp134_133] = local_i;
    }
    local_i = length / 2;
    while (local_i-- != 0) {
      downHeap(refArray, heap, inv, length, local_i, local_c);
    }
  }
  
  public static <K> void makeHeap(K[] refArray, int[] heap, int[] inv, int size, Comparator<K> local_c)
  {
    int local_i = size / 2;
    while (local_i-- != 0) {
      downHeap(refArray, heap, inv, size, local_i, local_c);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectIndirectHeaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */