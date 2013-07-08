package it.unimi.dsi.fastutil.doubles;

public class DoubleHeaps
{
  public static int downHeap(double[] heap, int size, int local_i, DoubleComparator local_c)
  {
    if (local_i >= size) {
      throw new IllegalArgumentException("Heap position (" + local_i + ") is larger than or equal to heap size (" + size + ")");
    }
    double local_e = heap[local_i];
    if (local_c == null)
    {
      int child;
      while ((child = 2 * local_i + 1) < size)
      {
        if ((child + 1 < size) && (heap[(child + 1)] < heap[child])) {
          child++;
        }
        if (local_e <= heap[child]) {
          break;
        }
        heap[local_i] = heap[child];
        local_i = child;
      }
    }
    int child;
    while ((child = 2 * local_i + 1) < size)
    {
      if ((child + 1 < size) && (local_c.compare(heap[(child + 1)], heap[child]) < 0)) {
        child++;
      }
      if (local_c.compare(local_e, heap[child]) <= 0) {
        break;
      }
      heap[local_i] = heap[child];
      local_i = child;
    }
    heap[local_i] = local_e;
    return local_i;
  }
  
  public static int upHeap(double[] heap, int size, int local_i, DoubleComparator local_c)
  {
    if (local_i >= size) {
      throw new IllegalArgumentException("Heap position (" + local_i + ") is larger than or equal to heap size (" + size + ")");
    }
    double local_e = heap[local_i];
    if (local_c == null)
    {
      int parent;
      while ((local_i != 0) && ((parent = (local_i - 1) / 2) >= 0) && (heap[parent] > local_e))
      {
        heap[local_i] = heap[parent];
        local_i = parent;
      }
    }
    int parent;
    while ((local_i != 0) && ((parent = (local_i - 1) / 2) >= 0) && (local_c.compare(heap[parent], local_e) > 0))
    {
      heap[local_i] = heap[parent];
      local_i = parent;
    }
    heap[local_i] = local_e;
    return local_i;
  }
  
  public static void makeHeap(double[] heap, int size, DoubleComparator local_c)
  {
    int local_i = size / 2;
    while (local_i-- != 0) {
      downHeap(heap, size, local_i, local_c);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleHeaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */