package org.apache.commons.lang3.math;

public class IEEE754rUtils
{
  public static double min(double[] array)
  {
    if (array == null) {
      throw new IllegalArgumentException("The Array must not be null");
    }
    if (array.length == 0) {
      throw new IllegalArgumentException("Array cannot be empty.");
    }
    double min = array[0];
    for (int local_i = 1; local_i < array.length; local_i++) {
      min = min(array[local_i], min);
    }
    return min;
  }
  
  public static float min(float[] array)
  {
    if (array == null) {
      throw new IllegalArgumentException("The Array must not be null");
    }
    if (array.length == 0) {
      throw new IllegalArgumentException("Array cannot be empty.");
    }
    float min = array[0];
    for (int local_i = 1; local_i < array.length; local_i++) {
      min = min(array[local_i], min);
    }
    return min;
  }
  
  public static double min(double local_a, double local_b, double local_c)
  {
    return min(min(local_a, local_b), local_c);
  }
  
  public static double min(double local_a, double local_b)
  {
    if (Double.isNaN(local_a)) {
      return local_b;
    }
    if (Double.isNaN(local_b)) {
      return local_a;
    }
    return Math.min(local_a, local_b);
  }
  
  public static float min(float local_a, float local_b, float local_c)
  {
    return min(min(local_a, local_b), local_c);
  }
  
  public static float min(float local_a, float local_b)
  {
    if (Float.isNaN(local_a)) {
      return local_b;
    }
    if (Float.isNaN(local_b)) {
      return local_a;
    }
    return Math.min(local_a, local_b);
  }
  
  public static double max(double[] array)
  {
    if (array == null) {
      throw new IllegalArgumentException("The Array must not be null");
    }
    if (array.length == 0) {
      throw new IllegalArgumentException("Array cannot be empty.");
    }
    double max = array[0];
    for (int local_j = 1; local_j < array.length; local_j++) {
      max = max(array[local_j], max);
    }
    return max;
  }
  
  public static float max(float[] array)
  {
    if (array == null) {
      throw new IllegalArgumentException("The Array must not be null");
    }
    if (array.length == 0) {
      throw new IllegalArgumentException("Array cannot be empty.");
    }
    float max = array[0];
    for (int local_j = 1; local_j < array.length; local_j++) {
      max = max(array[local_j], max);
    }
    return max;
  }
  
  public static double max(double local_a, double local_b, double local_c)
  {
    return max(max(local_a, local_b), local_c);
  }
  
  public static double max(double local_a, double local_b)
  {
    if (Double.isNaN(local_a)) {
      return local_b;
    }
    if (Double.isNaN(local_b)) {
      return local_a;
    }
    return Math.max(local_a, local_b);
  }
  
  public static float max(float local_a, float local_b, float local_c)
  {
    return max(max(local_a, local_b), local_c);
  }
  
  public static float max(float local_a, float local_b)
  {
    if (Float.isNaN(local_a)) {
      return local_b;
    }
    if (Float.isNaN(local_b)) {
      return local_a;
    }
    return Math.max(local_a, local_b);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.math.IEEE754rUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */