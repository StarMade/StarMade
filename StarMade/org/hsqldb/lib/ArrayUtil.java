package org.hsqldb.lib;

import java.lang.reflect.Array;

public class ArrayUtil
{
  public static final int CLASS_CODE_BYTE = 66;
  public static final int CLASS_CODE_CHAR = 67;
  public static final int CLASS_CODE_DOUBLE = 68;
  public static final int CLASS_CODE_FLOAT = 70;
  public static final int CLASS_CODE_INT = 73;
  public static final int CLASS_CODE_LONG = 74;
  public static final int CLASS_CODE_OBJECT = 76;
  public static final int CLASS_CODE_SHORT = 83;
  public static final int CLASS_CODE_BOOLEAN = 90;
  private static IntValueHashMap classCodeMap = new IntValueHashMap();
  
  static int getClassCode(Class paramClass)
  {
    if (!paramClass.isPrimitive()) {
      return 76;
    }
    return classCodeMap.get(paramClass, -1);
  }
  
  public static void clearArray(int paramInt1, Object paramObject, int paramInt2, int paramInt3)
  {
    switch (paramInt1)
    {
    case 66: 
      localObject = (byte[])paramObject;
      for (;;)
      {
        paramInt3--;
        if (paramInt3 < paramInt2) {
          break;
        }
        localObject[paramInt3] = 0;
      }
      return;
    case 67: 
      localObject = (byte[])paramObject;
      for (;;)
      {
        paramInt3--;
        if (paramInt3 < paramInt2) {
          break;
        }
        localObject[paramInt3] = 0;
      }
      return;
    case 83: 
      localObject = (short[])paramObject;
      for (;;)
      {
        paramInt3--;
        if (paramInt3 < paramInt2) {
          break;
        }
        localObject[paramInt3] = 0;
      }
      return;
    case 73: 
      localObject = (int[])paramObject;
      for (;;)
      {
        paramInt3--;
        if (paramInt3 < paramInt2) {
          break;
        }
        localObject[paramInt3] = 0;
      }
      return;
    case 74: 
      localObject = (long[])paramObject;
      for (;;)
      {
        paramInt3--;
        if (paramInt3 < paramInt2) {
          break;
        }
        localObject[paramInt3] = 0L;
      }
      return;
    case 70: 
      localObject = (float[])paramObject;
      for (;;)
      {
        paramInt3--;
        if (paramInt3 < paramInt2) {
          break;
        }
        localObject[paramInt3] = 0.0F;
      }
      return;
    case 68: 
      localObject = (double[])paramObject;
      for (;;)
      {
        paramInt3--;
        if (paramInt3 < paramInt2) {
          break;
        }
        localObject[paramInt3] = 0.0D;
      }
      return;
    case 90: 
      localObject = (boolean[])paramObject;
      for (;;)
      {
        paramInt3--;
        if (paramInt3 < paramInt2) {
          break;
        }
        localObject[paramInt3] = 0;
      }
      return;
    }
    Object localObject = (Object[])paramObject;
    for (;;)
    {
      paramInt3--;
      if (paramInt3 < paramInt2) {
        break;
      }
      localObject[paramInt3] = null;
    }
  }
  
  public static void adjustArray(int paramInt1, Object paramObject, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt3 >= paramInt2) {
      return;
    }
    int i = paramInt2 + paramInt4;
    int j;
    int k;
    int m;
    if (paramInt4 >= 0)
    {
      j = paramInt3;
      k = paramInt3 + paramInt4;
      m = paramInt2 - paramInt3;
    }
    else
    {
      j = paramInt3 - paramInt4;
      k = paramInt3;
      m = paramInt2 - paramInt3 + paramInt4;
    }
    if (m > 0) {
      System.arraycopy(paramObject, j, paramObject, k, m);
    }
    if (paramInt4 < 0) {
      clearArray(paramInt1, paramObject, i, paramInt2);
    }
  }
  
  public static void sortArray(int[] paramArrayOfInt)
  {
    int i;
    do
    {
      i = 0;
      for (int j = 0; j < paramArrayOfInt.length - 1; j++) {
        if (paramArrayOfInt[j] > paramArrayOfInt[(j + 1)])
        {
          int k = paramArrayOfInt[(j + 1)];
          paramArrayOfInt[(j + 1)] = paramArrayOfInt[j];
          paramArrayOfInt[j] = k;
          i = 1;
        }
      }
    } while (i != 0);
  }
  
  public static int find(Object[] paramArrayOfObject, Object paramObject)
  {
    for (int i = 0; i < paramArrayOfObject.length; i++)
    {
      if (paramArrayOfObject[i] == paramObject) {
        return i;
      }
      if ((paramObject != null) && (paramObject.equals(paramArrayOfObject[i]))) {
        return i;
      }
    }
    return -1;
  }
  
  public static int find(int[] paramArrayOfInt, int paramInt)
  {
    for (int i = 0; i < paramArrayOfInt.length; i++) {
      if (paramArrayOfInt[i] == paramInt) {
        return i;
      }
    }
    return -1;
  }
  
  public static int find(short[] paramArrayOfShort, int paramInt)
  {
    for (int i = 0; i < paramArrayOfShort.length; i++) {
      if (paramArrayOfShort[i] == paramInt) {
        return i;
      }
    }
    return -1;
  }
  
  public static int find(short[] paramArrayOfShort, int paramInt1, int paramInt2, int paramInt3)
  {
    for (int i = paramInt2; i < paramInt2 + paramInt3; i++) {
      if (paramArrayOfShort[i] == paramInt1) {
        return i;
      }
    }
    return -1;
  }
  
  public static int findNot(int[] paramArrayOfInt, int paramInt)
  {
    for (int i = 0; i < paramArrayOfInt.length; i++) {
      if (paramArrayOfInt[i] != paramInt) {
        return i;
      }
    }
    return -1;
  }
  
  public static boolean areEqualSets(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    return (paramArrayOfInt1.length == paramArrayOfInt2.length) && (haveEqualSets(paramArrayOfInt1, paramArrayOfInt2, paramArrayOfInt1.length));
  }
  
  public static boolean areEqual(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt, boolean paramBoolean)
  {
    if (haveEqualArrays(paramArrayOfInt1, paramArrayOfInt2, paramInt))
    {
      if (paramBoolean) {
        return (paramArrayOfInt1.length == paramArrayOfInt2.length) && (paramInt == paramArrayOfInt1.length);
      }
      return true;
    }
    return false;
  }
  
  public static boolean haveEqualSets(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt)
  {
    if (haveEqualArrays(paramArrayOfInt1, paramArrayOfInt2, paramInt)) {
      return true;
    }
    if ((paramInt > paramArrayOfInt1.length) || (paramInt > paramArrayOfInt2.length)) {
      return false;
    }
    if (paramInt == 1) {
      return paramArrayOfInt1[0] == paramArrayOfInt2[0];
    }
    int[] arrayOfInt1 = (int[])resizeArray(paramArrayOfInt1, paramInt);
    int[] arrayOfInt2 = (int[])resizeArray(paramArrayOfInt2, paramInt);
    sortArray(arrayOfInt1);
    sortArray(arrayOfInt2);
    for (int i = 0; i < paramInt; i++) {
      if (arrayOfInt1[i] != arrayOfInt2[i]) {
        return false;
      }
    }
    return true;
  }
  
  public static boolean haveEqualArrays(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt)
  {
    if ((paramInt > paramArrayOfInt1.length) || (paramInt > paramArrayOfInt2.length)) {
      return false;
    }
    for (int i = 0; i < paramInt; i++) {
      if (paramArrayOfInt1[i] != paramArrayOfInt2[i]) {
        return false;
      }
    }
    return true;
  }
  
  public static boolean haveEqualArrays(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt)
  {
    if ((paramInt > paramArrayOfObject1.length) || (paramInt > paramArrayOfObject2.length)) {
      return false;
    }
    for (int i = 0; i < paramInt; i++) {
      if ((paramArrayOfObject1[i] != paramArrayOfObject2[i]) && ((paramArrayOfObject1[i] == null) || (!paramArrayOfObject1[i].equals(paramArrayOfObject2[i])))) {
        return false;
      }
    }
    return true;
  }
  
  public static boolean haveCommonElement(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    for (int i = 0; i < paramArrayOfInt1.length; i++)
    {
      int j = paramArrayOfInt1[i];
      for (int k = 0; k < paramArrayOfInt2.length; k++) {
        if (j == paramArrayOfInt2[k]) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static int[] commonElements(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    int[] arrayOfInt = null;
    int i = countCommonElements(paramArrayOfInt1, paramArrayOfInt2);
    if (i > 0)
    {
      arrayOfInt = new int[i];
      int j = 0;
      for (int k = 0; k < paramArrayOfInt1.length; k++) {
        for (int m = 0; m < paramArrayOfInt2.length; m++) {
          if (paramArrayOfInt1[k] == paramArrayOfInt2[m]) {
            arrayOfInt[(j++)] = paramArrayOfInt1[k];
          }
        }
      }
    }
    return arrayOfInt;
  }
  
  public static int countCommonElements(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    int i = 0;
    for (int j = 0; j < paramArrayOfInt1.length; j++) {
      for (int k = 0; k < paramArrayOfInt2.length; k++) {
        if (paramArrayOfInt1[j] == paramArrayOfInt2[k])
        {
          i++;
          break;
        }
      }
    }
    return i;
  }
  
  public static int countCommonElements(Object[] paramArrayOfObject1, int paramInt, Object[] paramArrayOfObject2)
  {
    int i = 0;
    for (int j = 0; j < paramInt; j++) {
      for (int k = 0; k < paramArrayOfObject2.length; k++) {
        if (paramArrayOfObject1[j] == paramArrayOfObject2[k])
        {
          i++;
          break;
        }
      }
    }
    return i;
  }
  
  public static int countSameElements(byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2)
  {
    int i = 0;
    int j = paramArrayOfByte1.length - paramInt;
    if (j > paramArrayOfByte2.length) {
      j = paramArrayOfByte2.length;
    }
    for (int k = 0; (k < j) && (paramArrayOfByte1[(k + paramInt)] == paramArrayOfByte2[k]); k++) {
      i++;
    }
    return i;
  }
  
  public static int countSameElements(char[] paramArrayOfChar1, int paramInt, char[] paramArrayOfChar2)
  {
    int i = 0;
    int j = paramArrayOfChar1.length - paramInt;
    if (j > paramArrayOfChar2.length) {
      j = paramArrayOfChar2.length;
    }
    for (int k = 0; (k < j) && (paramArrayOfChar1[(k + paramInt)] == paramArrayOfChar2[k]); k++) {
      i++;
    }
    return i;
  }
  
  public static int[] union(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    int i = paramArrayOfInt1.length + paramArrayOfInt2.length - countCommonElements(paramArrayOfInt1, paramArrayOfInt2);
    if ((i > paramArrayOfInt1.length) && (i > paramArrayOfInt2.length))
    {
      int[] arrayOfInt = (int[])resizeArray(paramArrayOfInt2, i);
      int j = paramArrayOfInt2.length;
      label91:
      for (int k = 0; k < paramArrayOfInt1.length; k++)
      {
        for (int m = 0; m < paramArrayOfInt2.length; m++) {
          if (paramArrayOfInt1[k] == paramArrayOfInt2[m]) {
            break label91;
          }
        }
        arrayOfInt[(j++)] = paramArrayOfInt1[k];
      }
      return arrayOfInt;
    }
    return paramArrayOfInt1.length > paramArrayOfInt2.length ? paramArrayOfInt1 : paramArrayOfInt2;
  }
  
  public static int find(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, byte[] paramArrayOfByte2)
  {
    int i = paramInt1;
    paramInt2 = paramInt2 - paramArrayOfByte2.length + 1;
    int j = paramArrayOfByte2[0];
    while (i < paramInt2)
    {
      if (paramArrayOfByte1[i] == j)
      {
        if (paramArrayOfByte2.length == 1) {
          return i;
        }
        if (containsAt(paramArrayOfByte1, i, paramArrayOfByte2)) {
          return i;
        }
      }
      i++;
    }
    return -1;
  }
  
  public static int findNotIn(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, byte[] paramArrayOfByte2)
  {
    int i = 0;
    if (i < paramInt2)
    {
      for (int j = 0; j < paramArrayOfByte2.length; j++) {
        if (paramArrayOfByte1[i] != paramArrayOfByte2[j]) {}
      }
      return i;
    }
    return -1;
  }
  
  public static int findIn(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, byte[] paramArrayOfByte2)
  {
    for (int i = 0; i < paramInt2; i++) {
      for (int j = 0; j < paramArrayOfByte2.length; j++) {
        if (paramArrayOfByte1[i] == paramArrayOfByte2[j]) {
          return i;
        }
      }
    }
    return -1;
  }
  
  public static int find(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    for (int i = 0; i < paramInt2; i++) {
      if ((paramArrayOfByte[i] == paramInt3) || (paramArrayOfByte[i] == paramInt4)) {
        return i;
      }
    }
    return -1;
  }
  
  public static int[] booleanArrayToIntIndexes(boolean[] paramArrayOfBoolean)
  {
    int i = 0;
    for (int j = 0; j < paramArrayOfBoolean.length; j++) {
      if (paramArrayOfBoolean[j] != 0) {
        i++;
      }
    }
    int[] arrayOfInt = new int[i];
    i = 0;
    for (int k = 0; k < paramArrayOfBoolean.length; k++) {
      if (paramArrayOfBoolean[k] != 0) {
        arrayOfInt[(i++)] = k;
      }
    }
    return arrayOfInt;
  }
  
  public static void intIndexesToBooleanArray(int[] paramArrayOfInt, boolean[] paramArrayOfBoolean)
  {
    for (int i = 0; i < paramArrayOfInt.length; i++) {
      if (paramArrayOfInt[i] < paramArrayOfBoolean.length) {
        paramArrayOfBoolean[paramArrayOfInt[i]] = true;
      }
    }
  }
  
  public static int countStartIntIndexesInBooleanArray(int[] paramArrayOfInt, boolean[] paramArrayOfBoolean)
  {
    int i = 0;
    for (int j = 0; (j < paramArrayOfInt.length) && (paramArrayOfBoolean[paramArrayOfInt[j]] != 0); j++) {
      i++;
    }
    return i;
  }
  
  public static void orBooleanArray(boolean[] paramArrayOfBoolean1, boolean[] paramArrayOfBoolean2)
  {
    for (int i = 0; i < paramArrayOfBoolean2.length; i++) {
      paramArrayOfBoolean2[i] |= paramArrayOfBoolean1[i];
    }
  }
  
  public static boolean areAllIntIndexesInBooleanArray(int[] paramArrayOfInt, boolean[] paramArrayOfBoolean)
  {
    for (int i = 0; i < paramArrayOfInt.length; i++) {
      if (paramArrayOfBoolean[paramArrayOfInt[i]] == 0) {
        return false;
      }
    }
    return true;
  }
  
  public static boolean isAnyIntIndexInBooleanArray(int[] paramArrayOfInt, boolean[] paramArrayOfBoolean)
  {
    for (int i = 0; i < paramArrayOfInt.length; i++) {
      if (paramArrayOfBoolean[paramArrayOfInt[i]] != 0) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean containsAllTrueElements(boolean[] paramArrayOfBoolean1, boolean[] paramArrayOfBoolean2)
  {
    for (int i = 0; i < paramArrayOfBoolean1.length; i++) {
      if ((paramArrayOfBoolean2[i] != 0) && (paramArrayOfBoolean1[i] == 0)) {
        return false;
      }
    }
    return true;
  }
  
  public static int countTrueElements(boolean[] paramArrayOfBoolean)
  {
    int i = 0;
    for (int j = 0; j < paramArrayOfBoolean.length; j++) {
      if (paramArrayOfBoolean[j] != 0) {
        i++;
      }
    }
    return i;
  }
  
  public static boolean hasNull(Object[] paramArrayOfObject, int[] paramArrayOfInt)
  {
    int i = paramArrayOfInt.length;
    for (int j = 0; j < i; j++) {
      if (paramArrayOfObject[paramArrayOfInt[j]] == null) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean hasAllNull(Object[] paramArrayOfObject, int[] paramArrayOfInt)
  {
    int i = paramArrayOfInt.length;
    for (int j = 0; j < i; j++) {
      if (paramArrayOfObject[paramArrayOfInt[j]] != null) {
        return false;
      }
    }
    return true;
  }
  
  public static boolean containsAt(byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2)
  {
    return countSameElements(paramArrayOfByte1, paramInt, paramArrayOfByte2) == paramArrayOfByte2.length;
  }
  
  public static int countStartElementsAt(byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2)
  {
    int i = 0;
    label48:
    for (int j = paramInt; j < paramArrayOfByte1.length; j++)
    {
      for (int k = 0; k < paramArrayOfByte2.length; k++) {
        if (paramArrayOfByte1[j] == paramArrayOfByte2[k])
        {
          i++;
          break label48;
        }
      }
      break;
    }
    return i;
  }
  
  public static boolean containsAt(char[] paramArrayOfChar1, int paramInt, char[] paramArrayOfChar2)
  {
    return countSameElements(paramArrayOfChar1, paramInt, paramArrayOfChar2) == paramArrayOfChar2.length;
  }
  
  public static int countNonStartElementsAt(byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2)
  {
    int i = 0;
    for (int j = paramInt; j < paramArrayOfByte1.length; j++)
    {
      for (int k = 0; k < paramArrayOfByte2.length; k++) {
        if (paramArrayOfByte1[j] == paramArrayOfByte2[k]) {
          return i;
        }
      }
      i++;
    }
    return i;
  }
  
  public static int copyBytes(long paramLong1, byte[] paramArrayOfByte1, int paramInt1, int paramInt2, long paramLong2, byte[] paramArrayOfByte2)
  {
    if ((paramLong1 + paramInt1 >= paramLong2 + paramArrayOfByte2.length) || (paramLong1 + paramInt1 + paramInt2 <= paramLong2)) {
      return 0;
    }
    long l1 = paramLong2 - paramLong1;
    long l2 = 0L;
    int i = paramInt1 + paramInt2;
    if (l1 > 0L)
    {
      if (l1 < paramInt1) {
        l1 = paramInt1;
      }
    }
    else
    {
      l2 = -l1 + paramInt1;
      l1 = paramInt1;
    }
    paramInt2 = i - (int)l1;
    if (paramInt2 > paramArrayOfByte2.length - l2) {
      paramInt2 = paramArrayOfByte2.length - (int)l2;
    }
    System.arraycopy(paramArrayOfByte1, (int)l1, paramArrayOfByte2, (int)l2, paramInt2);
    return paramInt2;
  }
  
  public static byte[] copyBytes(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt)
  {
    if (paramArrayOfByte1.length + paramInt > paramArrayOfByte2.length)
    {
      byte[] arrayOfByte = new byte[paramArrayOfByte1.length + paramInt];
      System.arraycopy(paramArrayOfByte2, 0, arrayOfByte, 0, paramArrayOfByte2.length);
      paramArrayOfByte2 = arrayOfByte;
    }
    System.arraycopy(paramArrayOfByte1, 0, paramArrayOfByte2, paramInt, paramArrayOfByte1.length);
    return paramArrayOfByte2;
  }
  
  public static void copyArray(Object paramObject1, Object paramObject2, int paramInt)
  {
    System.arraycopy(paramObject1, 0, paramObject2, 0, paramInt);
  }
  
  public static void copyMoveSegment(Object paramObject1, Object paramObject2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = paramInt2 < paramInt4 ? 1 : 0;
    int j = i != 0 ? paramInt2 : paramInt4;
    System.arraycopy(paramObject1, 0, paramObject2, 0, j);
    j = i != 0 ? paramInt1 - paramInt4 - paramInt3 : paramInt1 - paramInt2 - paramInt3;
    int k = i != 0 ? paramInt4 + paramInt3 : paramInt2 + paramInt3;
    System.arraycopy(paramObject1, k, paramObject2, k, j);
    System.arraycopy(paramObject1, paramInt2, paramObject2, paramInt4, paramInt3);
    j = Math.abs(paramInt2 - paramInt4);
    k = i != 0 ? paramInt2 + paramInt3 : paramInt4;
    int m = i != 0 ? paramInt2 : paramInt4 + paramInt3;
    System.arraycopy(paramObject1, k, paramObject2, m, j);
  }
  
  public static int[] arraySlice(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    int[] arrayOfInt = new int[paramInt2];
    System.arraycopy(paramArrayOfInt, paramInt1, arrayOfInt, 0, paramInt2);
    return arrayOfInt;
  }
  
  public static void fillArray(char[] paramArrayOfChar, int paramInt, char paramChar)
  {
    int i = paramArrayOfChar.length;
    for (;;)
    {
      i--;
      if (i < paramInt) {
        break;
      }
      paramArrayOfChar[i] = paramChar;
    }
  }
  
  public static void fillArray(byte[] paramArrayOfByte, int paramInt, byte paramByte)
  {
    int i = paramArrayOfByte.length;
    for (;;)
    {
      i--;
      if (i < paramInt) {
        break;
      }
      paramArrayOfByte[i] = paramByte;
    }
  }
  
  public static void fillArray(Object[] paramArrayOfObject, Object paramObject)
  {
    int i = paramArrayOfObject.length;
    for (;;)
    {
      i--;
      if (i < 0) {
        break;
      }
      paramArrayOfObject[i] = paramObject;
    }
  }
  
  public static void fillArray(int[] paramArrayOfInt, int paramInt)
  {
    int i = paramArrayOfInt.length;
    for (;;)
    {
      i--;
      if (i < 0) {
        break;
      }
      paramArrayOfInt[i] = paramInt;
    }
  }
  
  public static void fillArray(double[] paramArrayOfDouble, double paramDouble)
  {
    int i = paramArrayOfDouble.length;
    for (;;)
    {
      i--;
      if (i < 0) {
        break;
      }
      paramArrayOfDouble[i] = paramDouble;
    }
  }
  
  public static void fillArray(boolean[] paramArrayOfBoolean, boolean paramBoolean)
  {
    int i = paramArrayOfBoolean.length;
    for (;;)
    {
      i--;
      if (i < 0) {
        break;
      }
      paramArrayOfBoolean[i] = paramBoolean;
    }
  }
  
  public static Object duplicateArray(Object paramObject)
  {
    int i = Array.getLength(paramObject);
    Object localObject = Array.newInstance(paramObject.getClass().getComponentType(), i);
    System.arraycopy(paramObject, 0, localObject, 0, i);
    return localObject;
  }
  
  public static Object resizeArrayIfDifferent(Object paramObject, int paramInt)
  {
    int i = Array.getLength(paramObject);
    if (i == paramInt) {
      return paramObject;
    }
    Object localObject = Array.newInstance(paramObject.getClass().getComponentType(), paramInt);
    if (i < paramInt) {
      paramInt = i;
    }
    System.arraycopy(paramObject, 0, localObject, 0, paramInt);
    return localObject;
  }
  
  public static Object resizeArray(Object paramObject, int paramInt)
  {
    Object localObject = Array.newInstance(paramObject.getClass().getComponentType(), paramInt);
    int i = Array.getLength(paramObject);
    if (i < paramInt) {
      paramInt = i;
    }
    System.arraycopy(paramObject, 0, localObject, 0, paramInt);
    return localObject;
  }
  
  public static Object toAdjustedArray(Object paramObject1, Object paramObject2, int paramInt1, int paramInt2)
  {
    int i = Array.getLength(paramObject1) + paramInt2;
    Object localObject = Array.newInstance(paramObject1.getClass().getComponentType(), i);
    copyAdjustArray(paramObject1, localObject, paramObject2, paramInt1, paramInt2);
    return localObject;
  }
  
  public static void copyAdjustArray(Object paramObject1, Object paramObject2, Object paramObject3, int paramInt1, int paramInt2)
  {
    int i = Array.getLength(paramObject1);
    if (paramInt1 < 0)
    {
      System.arraycopy(paramObject1, 0, paramObject2, 0, i);
      return;
    }
    System.arraycopy(paramObject1, 0, paramObject2, 0, paramInt1);
    int j;
    if (paramInt2 == 0)
    {
      j = i - paramInt1 - 1;
      Array.set(paramObject2, paramInt1, paramObject3);
      if (j > 0) {
        System.arraycopy(paramObject1, paramInt1 + 1, paramObject2, paramInt1 + 1, j);
      }
    }
    else if (paramInt2 < 0)
    {
      j = i - paramInt1 - 1;
      if (j > 0) {
        System.arraycopy(paramObject1, paramInt1 + 1, paramObject2, paramInt1, j);
      }
    }
    else
    {
      j = i - paramInt1;
      Array.set(paramObject2, paramInt1, paramObject3);
      if (j > 0) {
        System.arraycopy(paramObject1, paramInt1, paramObject2, paramInt1 + 1, j);
      }
    }
  }
  
  public static int[] toAdjustedColumnArray(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    if (paramArrayOfInt == null) {
      return null;
    }
    if (paramInt1 < 0) {
      return paramArrayOfInt;
    }
    int[] arrayOfInt1 = new int[paramArrayOfInt.length];
    int i = 0;
    for (int j = 0; j < paramArrayOfInt.length; j++) {
      if (paramArrayOfInt[j] > paramInt1)
      {
        paramArrayOfInt[j] += paramInt2;
        i++;
      }
      else if (paramArrayOfInt[j] == paramInt1)
      {
        if (paramInt2 >= 0)
        {
          paramArrayOfInt[j] += paramInt2;
          i++;
        }
      }
      else
      {
        arrayOfInt1[i] = paramArrayOfInt[j];
        i++;
      }
    }
    if (paramArrayOfInt.length != i)
    {
      int[] arrayOfInt2 = new int[i];
      copyArray(arrayOfInt1, arrayOfInt2, i);
      return arrayOfInt2;
    }
    return arrayOfInt1;
  }
  
  public static void projectRow(Object[] paramArrayOfObject1, int[] paramArrayOfInt, Object[] paramArrayOfObject2)
  {
    for (int i = 0; i < paramArrayOfInt.length; i++) {
      paramArrayOfObject2[i] = paramArrayOfObject1[paramArrayOfInt[i]];
    }
  }
  
  public static void projectRow(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
  {
    for (int i = 0; i < paramArrayOfInt2.length; i++) {
      paramArrayOfInt3[i] = paramArrayOfInt1[paramArrayOfInt2[i]];
    }
  }
  
  public static void projectRowReverse(Object[] paramArrayOfObject1, int[] paramArrayOfInt, Object[] paramArrayOfObject2)
  {
    for (int i = 0; i < paramArrayOfInt.length; i++) {
      paramArrayOfObject1[paramArrayOfInt[i]] = paramArrayOfObject2[i];
    }
  }
  
  public static void projectMap(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
  {
    for (int i = 0; i < paramArrayOfInt2.length; i++) {
      for (int j = 0; j < paramArrayOfInt1.length; j++) {
        if (paramArrayOfInt2[i] == paramArrayOfInt1[j])
        {
          paramArrayOfInt3[i] = j;
          break;
        }
      }
    }
  }
  
  public static void reorderMaps(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
  {
    for (int i = 0; i < paramArrayOfInt1.length; i++) {
      for (int j = i; j < paramArrayOfInt2.length; j++) {
        if (paramArrayOfInt1[i] == paramArrayOfInt2[j])
        {
          int k = paramArrayOfInt2[i];
          paramArrayOfInt2[i] = paramArrayOfInt2[j];
          paramArrayOfInt2[j] = k;
          k = paramArrayOfInt3[i];
          paramArrayOfInt3[i] = paramArrayOfInt3[j];
          paramArrayOfInt3[j] = k;
          break;
        }
      }
    }
  }
  
  public static void fillSequence(int[] paramArrayOfInt)
  {
    for (int i = 0; i < paramArrayOfInt.length; i++) {
      paramArrayOfInt[i] = i;
    }
  }
  
  public static char[] byteArrayToChars(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar = new char[paramArrayOfByte.length / 2];
    int i = 0;
    for (int j = 0; j < arrayOfChar.length; j++)
    {
      arrayOfChar[j] = ((char)((paramArrayOfByte[i] << 8) + (paramArrayOfByte[(i + 1)] & 0xFF)));
      i += 2;
    }
    return arrayOfChar;
  }
  
  public static byte[] charArrayToBytes(char[] paramArrayOfChar)
  {
    byte[] arrayOfByte = new byte[paramArrayOfChar.length * 2];
    int i = 0;
    for (int j = 0; j < paramArrayOfChar.length; j++)
    {
      int k = paramArrayOfChar[j];
      arrayOfByte[i] = ((byte)(k >> 8));
      arrayOfByte[(i + 1)] = ((byte)k);
      i += 2;
    }
    return arrayOfByte;
  }
  
  public static boolean isInSortedArray(char paramChar, char[] paramArrayOfChar)
  {
    if ((paramArrayOfChar.length == 0) || (paramChar < paramArrayOfChar[0]) || (paramChar > paramArrayOfChar[(paramArrayOfChar.length - 1)])) {
      return false;
    }
    int i = 0;
    int j = paramArrayOfChar.length;
    int k = 0;
    while (i < j)
    {
      k = (i + j) / 2;
      if (paramChar < paramArrayOfChar[k]) {
        j = k;
      } else if (paramChar > paramArrayOfChar[k]) {
        i = k + 1;
      } else {
        return true;
      }
    }
    return false;
  }
  
  public static boolean containsAll(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2)
  {
    label48:
    for (int i = 0; i < paramArrayOfObject2.length; i++)
    {
      for (int j = 0; j < paramArrayOfObject1.length; j++) {
        if ((paramArrayOfObject2[i] == paramArrayOfObject1[j]) || (paramArrayOfObject2[i].equals(paramArrayOfObject1[j]))) {
          break label48;
        }
      }
      return false;
    }
    return true;
  }
  
  public static boolean containsAny(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2)
  {
    for (int i = 0; i < paramArrayOfObject2.length; i++) {
      for (int j = 0; j < paramArrayOfObject1.length; j++) {
        if ((paramArrayOfObject2[i] == paramArrayOfObject1[j]) || (paramArrayOfObject2[i].equals(paramArrayOfObject1[j]))) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static boolean containsAll(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    label36:
    for (int i = 0; i < paramArrayOfInt2.length; i++)
    {
      for (int j = 0; j < paramArrayOfInt1.length; j++) {
        if (paramArrayOfInt2[i] == paramArrayOfInt1[j]) {
          break label36;
        }
      }
      return false;
    }
    return true;
  }
  
  public static boolean containsAllAtStart(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    if (paramArrayOfInt2.length > paramArrayOfInt1.length) {
      return false;
    }
    label53:
    for (int i = 0; i < paramArrayOfInt1.length; i++)
    {
      if (i == paramArrayOfInt2.length) {
        return true;
      }
      for (int j = 0; j < paramArrayOfInt2.length; j++) {
        if (paramArrayOfInt1[i] == paramArrayOfInt2[j]) {
          break label53;
        }
      }
      return false;
    }
    return true;
  }
  
  public static byte[] toByteArray(long paramLong1, long paramLong2)
  {
    byte[] arrayOfByte = new byte[16];
    int i = 0;
    while (i < 16)
    {
      int j;
      if (i == 0) {
        j = (int)paramLong1 >>> 32;
      } else if (i == 4) {
        j = (int)paramLong1;
      } else if (i == 8) {
        j = (int)paramLong2 >>> 32;
      } else {
        j = (int)paramLong2;
      }
      arrayOfByte[(i++)] = ((byte)(j >>> 24));
      arrayOfByte[(i++)] = ((byte)(j >>> 16));
      arrayOfByte[(i++)] = ((byte)(j >>> 8));
      arrayOfByte[(i++)] = ((byte)j);
    }
    return arrayOfByte;
  }
  
  public static int compare(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    int i = paramArrayOfByte1.length;
    if (i > paramArrayOfByte2.length) {
      i = paramArrayOfByte2.length;
    }
    for (int j = 0; j < i; j++) {
      if (paramArrayOfByte1[j] != paramArrayOfByte2[j]) {
        return paramArrayOfByte1[j] < paramArrayOfByte2[j] ? -1 : 1;
      }
    }
    if (paramArrayOfByte1.length == paramArrayOfByte2.length) {
      return 0;
    }
    return paramArrayOfByte1.length < paramArrayOfByte2.length ? -1 : 1;
  }
  
  public static long getBinaryNormalisedCeiling(long paramLong, int paramInt)
  {
    long l1 = -1L << paramInt;
    long l2 = paramLong & l1;
    if (l2 != paramLong) {
      l2 += (1 << paramInt);
    }
    return l2;
  }
  
  public static boolean isTwoPower(int paramInt1, int paramInt2)
  {
    for (int i = 0; i <= paramInt2; i++)
    {
      if ((paramInt1 & 0x1) != 0) {
        return paramInt1 == 1;
      }
      paramInt1 >>= 1;
    }
    return false;
  }
  
  public static int getTwoPowerFloor(int paramInt)
  {
    int i = 0;
    if (paramInt == 0) {
      return 0;
    }
    for (int j = 0; j < 32; j++)
    {
      if ((paramInt & 0x1) != 0) {
        i = j;
      }
      paramInt >>= 1;
    }
    return 1 << i;
  }
  
  static
  {
    classCodeMap.put(Byte.TYPE, 66);
    classCodeMap.put(Character.TYPE, 83);
    classCodeMap.put(Short.TYPE, 83);
    classCodeMap.put(Integer.TYPE, 73);
    classCodeMap.put(Long.TYPE, 74);
    classCodeMap.put(Float.TYPE, 70);
    classCodeMap.put(Double.TYPE, 68);
    classCodeMap.put(Boolean.TYPE, 90);
    classCodeMap.put(Object.class, 76);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.ArrayUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */