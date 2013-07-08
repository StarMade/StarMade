package org.hsqldb.lib;

import java.util.Comparator;

public class ArraySort
{
  public static int searchFirst(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Object paramObject, Comparator paramComparator)
  {
    int i = paramInt1;
    int j = paramInt2;
    int k = paramInt1;
    int m = 0;
    int n = paramInt2;
    while (i < j)
    {
      k = (i + j) / 2;
      m = paramComparator.compare(paramObject, paramArrayOfObject[k]);
      if (m < 0)
      {
        j = k;
      }
      else if (m > 0)
      {
        i = k + 1;
      }
      else
      {
        j = k;
        n = k;
      }
    }
    return n == paramInt2 ? -i - 1 : n;
  }
  
  public static int deDuplicate(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Comparator paramComparator)
  {
    int i = paramInt1;
    int j = paramInt1 + 1;
    if (paramArrayOfObject.length == 0) {
      return 0;
    }
    while (j < paramInt2)
    {
      int k = paramComparator.compare(paramArrayOfObject[i], paramArrayOfObject[j]);
      if (k != 0)
      {
        i++;
        paramArrayOfObject[i] = paramArrayOfObject[j];
      }
      j++;
    }
    return i + 1;
  }
  
  public static void sort(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Comparator paramComparator)
  {
    if (paramInt1 + 1 >= paramInt2) {
      return;
    }
    quickSort(paramArrayOfObject, paramComparator, paramInt1, paramInt2 - 1);
    insertionSort(paramArrayOfObject, paramComparator, paramInt1, paramInt2 - 1);
  }
  
  static void quickSort(Object[] paramArrayOfObject, Comparator paramComparator, int paramInt1, int paramInt2)
  {
    int i = 16;
    if (paramInt2 - paramInt1 > i)
    {
      int j = (paramInt2 + paramInt1) / 2;
      if (paramComparator.compare(paramArrayOfObject[j], paramArrayOfObject[paramInt1]) < 0) {
        swap(paramArrayOfObject, paramInt1, j);
      }
      if (paramComparator.compare(paramArrayOfObject[paramInt2], paramArrayOfObject[paramInt1]) < 0) {
        swap(paramArrayOfObject, paramInt1, paramInt2);
      }
      if (paramComparator.compare(paramArrayOfObject[paramInt2], paramArrayOfObject[j]) < 0) {
        swap(paramArrayOfObject, j, paramInt2);
      }
      int k = paramInt2 - 1;
      swap(paramArrayOfObject, j, k);
      j = paramInt1;
      int m = k;
      for (;;)
      {
        if (paramComparator.compare(paramArrayOfObject[(++j)], paramArrayOfObject[m]) >= 0)
        {
          while (paramComparator.compare(paramArrayOfObject[m], paramArrayOfObject[(--k)]) < 0) {}
          if (k < j) {
            break;
          }
          swap(paramArrayOfObject, j, k);
        }
      }
      swap(paramArrayOfObject, j, paramInt2 - 1);
      quickSort(paramArrayOfObject, paramComparator, paramInt1, k);
      quickSort(paramArrayOfObject, paramComparator, j + 1, paramInt2);
    }
  }
  
  public static void insertionSort(Object[] paramArrayOfObject, Comparator paramComparator, int paramInt1, int paramInt2)
  {
    for (int i = paramInt1 + 1; i <= paramInt2; i++)
    {
      for (int j = i; (j > paramInt1) && (paramComparator.compare(paramArrayOfObject[i], paramArrayOfObject[(j - 1)]) < 0); j--) {}
      if (i != j) {
        moveAndInsertRow(paramArrayOfObject, i, j);
      }
    }
  }
  
  private static void swap(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
  {
    Object localObject = paramArrayOfObject[paramInt1];
    paramArrayOfObject[paramInt1] = paramArrayOfObject[paramInt2];
    paramArrayOfObject[paramInt2] = localObject;
  }
  
  private static void moveAndInsertRow(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
  {
    Object localObject = paramArrayOfObject[paramInt1];
    moveRows(paramArrayOfObject, paramInt2, paramInt2 + 1, paramInt1 - paramInt2);
    paramArrayOfObject[paramInt2] = localObject;
  }
  
  private static void moveRows(Object[] paramArrayOfObject, int paramInt1, int paramInt2, int paramInt3)
  {
    System.arraycopy(paramArrayOfObject, paramInt1, paramArrayOfObject, paramInt2, paramInt3);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.lib.ArraySort
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */