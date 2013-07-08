/*   1:    */package com.bulletphysics.linearmath;
/*   2:    */
/*   3:    */import com.bulletphysics.util.FloatArrayList;
/*   4:    */import com.bulletphysics.util.IntArrayList;
/*   5:    */import com.bulletphysics.util.ObjectArrayList;
/*   6:    */import java.util.Comparator;
/*   7:    */
/*  35:    */public class MiscUtil
/*  36:    */{
/*  37:    */  public static int getListCapacityForHash(ObjectArrayList<?> list)
/*  38:    */  {
/*  39: 39 */    return getListCapacityForHash(list.size());
/*  40:    */  }
/*  41:    */  
/*  42:    */  public static int getListCapacityForHash(int size) {
/*  43: 43 */    int n = 2;
/*  44: 44 */    while (n < size) {
/*  45: 45 */      n <<= 1;
/*  46:    */    }
/*  47: 47 */    return n;
/*  48:    */  }
/*  49:    */  
/*  53:    */  public static <T> void ensureIndex(ObjectArrayList<T> list, int index, T value)
/*  54:    */  {
/*  55: 55 */    while (list.size() <= index) {
/*  56: 56 */      list.add(value);
/*  57:    */    }
/*  58:    */  }
/*  59:    */  
/*  62:    */  public static void resize(IntArrayList list, int size, int value)
/*  63:    */  {
/*  64: 64 */    while (list.size() < size) {
/*  65: 65 */      list.add(value);
/*  66:    */    }
/*  67:    */    
/*  68: 68 */    while (list.size() > size) {
/*  69: 69 */      list.remove(list.size() - 1);
/*  70:    */    }
/*  71:    */  }
/*  72:    */  
/*  75:    */  public static void resize(FloatArrayList list, int size, float value)
/*  76:    */  {
/*  77: 77 */    while (list.size() < size) {
/*  78: 78 */      list.add(value);
/*  79:    */    }
/*  80:    */    
/*  81: 81 */    while (list.size() > size) {
/*  82: 82 */      list.remove(list.size() - 1);
/*  83:    */    }
/*  84:    */  }
/*  85:    */  
/*  88:    */  public static <T> void resize(ObjectArrayList<T> list, int size, Class<T> valueCls)
/*  89:    */  {
/*  90:    */    try
/*  91:    */    {
/*  92: 92 */      while (list.size() < size) {
/*  93: 93 */        list.add(valueCls != null ? valueCls.newInstance() : null);
/*  94:    */      }
/*  95:    */      
/*  96: 96 */      while (list.size() > size) {
/*  97: 97 */        list.removeQuick(list.size() - 1);
/*  98:    */      }
/*  99:    */    }
/* 100:    */    catch (IllegalAccessException e) {
/* 101:101 */      throw new IllegalStateException(e);
/* 102:    */    }
/* 103:    */    catch (InstantiationException e) {
/* 104:104 */      throw new IllegalStateException(e);
/* 105:    */    }
/* 106:    */  }
/* 107:    */  
/* 112:    */  public static <T> int indexOf(T[] array, T obj)
/* 113:    */  {
/* 114:114 */    for (int i = 0; i < array.length; i++) {
/* 115:115 */      if (array[i] == obj) return i;
/* 116:    */    }
/* 117:117 */    return -1;
/* 118:    */  }
/* 119:    */  
/* 120:    */  public static float GEN_clamped(float a, float lb, float ub) {
/* 121:121 */    return ub < a ? ub : a < lb ? lb : a;
/* 122:    */  }
/* 123:    */  
/* 126:    */  private static <T> void downHeap(ObjectArrayList<T> pArr, int k, int n, Comparator<T> comparator)
/* 127:    */  {
/* 128:128 */    T temp = pArr.getQuick(k - 1);
/* 129:    */    
/* 130:130 */    while (k <= n / 2) {
/* 131:131 */      int child = 2 * k;
/* 132:    */      
/* 133:133 */      if ((child < n) && (comparator.compare(pArr.getQuick(child - 1), pArr.getQuick(child)) < 0)) {
/* 134:134 */        child++;
/* 135:    */      }
/* 136:    */      
/* 137:137 */      if (comparator.compare(temp, pArr.getQuick(child - 1)) >= 0)
/* 138:    */        break;
/* 139:139 */      pArr.setQuick(k - 1, pArr.getQuick(child - 1));
/* 140:140 */      k = child;
/* 141:    */    }
/* 142:    */    
/* 146:146 */    pArr.setQuick(k - 1, temp);
/* 147:    */  }
/* 148:    */  
/* 155:    */  public static <T> void heapSort(ObjectArrayList<T> list, Comparator<T> comparator)
/* 156:    */  {
/* 157:157 */    int n = list.size();
/* 158:158 */    for (int k = n / 2; k > 0; k--) {
/* 159:159 */      downHeap(list, k, n, comparator);
/* 160:    */    }
/* 161:    */    
/* 163:163 */    while (n >= 1) {
/* 164:164 */      swap(list, 0, n - 1);
/* 165:    */      
/* 166:166 */      n -= 1;
/* 167:    */      
/* 168:168 */      downHeap(list, 1, n, comparator);
/* 169:    */    }
/* 170:    */  }
/* 171:    */  
/* 172:    */  private static <T> void swap(ObjectArrayList<T> list, int index0, int index1) {
/* 173:173 */    T temp = list.getQuick(index0);
/* 174:174 */    list.setQuick(index0, list.getQuick(index1));
/* 175:175 */    list.setQuick(index1, temp);
/* 176:    */  }
/* 177:    */  
/* 181:    */  public static <T> void quickSort(ObjectArrayList<T> list, Comparator<T> comparator)
/* 182:    */  {
/* 183:183 */    if (list.size() > 1) {
/* 184:184 */      quickSortInternal(list, comparator, 0, list.size() - 1);
/* 185:    */    }
/* 186:    */  }
/* 187:    */  
/* 189:    */  private static <T> void quickSortInternal(ObjectArrayList<T> list, Comparator<T> comparator, int lo, int hi)
/* 190:    */  {
/* 191:191 */    int i = lo;int j = hi;
/* 192:192 */    T x = list.getQuick((lo + hi) / 2);
/* 193:    */    
/* 194:    */    do
/* 195:    */    {
/* 196:196 */      while (comparator.compare(list.getQuick(i), x) < 0) i++;
/* 197:197 */      while (comparator.compare(x, list.getQuick(j)) < 0) { j--;
/* 198:    */      }
/* 199:199 */      if (i <= j) {
/* 200:200 */        swap(list, i, j);
/* 201:201 */        i++;
/* 202:202 */        j--;
/* 203:    */      }
/* 204:    */      
/* 205:205 */    } while (i <= j);
/* 206:    */    
/* 208:208 */    if (lo < j) {
/* 209:209 */      quickSortInternal(list, comparator, lo, j);
/* 210:    */    }
/* 211:211 */    if (i < hi) {
/* 212:212 */      quickSortInternal(list, comparator, i, hi);
/* 213:    */    }
/* 214:    */  }
/* 215:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.MiscUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */