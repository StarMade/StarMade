package org.apache.commons.lang3.builder;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;

public class EqualsBuilder
  implements Builder<Boolean>
{
  private static final ThreadLocal<Set<Pair<IDKey, IDKey>>> REGISTRY = new ThreadLocal();
  private boolean isEquals = true;
  
  static Set<Pair<IDKey, IDKey>> getRegistry()
  {
    return (Set)REGISTRY.get();
  }
  
  static Pair<IDKey, IDKey> getRegisterPair(Object lhs, Object rhs)
  {
    IDKey left = new IDKey(lhs);
    IDKey right = new IDKey(rhs);
    return Pair.of2(left, right);
  }
  
  static boolean isRegistered(Object lhs, Object rhs)
  {
    Set<Pair<IDKey, IDKey>> registry = getRegistry();
    Pair<IDKey, IDKey> pair = getRegisterPair(lhs, rhs);
    Pair<IDKey, IDKey> swappedPair = Pair.of2(pair.getLeft(), pair.getRight());
    return (registry != null) && ((registry.contains(pair)) || (registry.contains(swappedPair)));
  }
  
  static void register(Object lhs, Object rhs)
  {
    synchronized (EqualsBuilder.class)
    {
      if (getRegistry() == null) {
        REGISTRY.set(new HashSet());
      }
    }
    Set<Pair<IDKey, IDKey>> registry = getRegistry();
    Object pair = getRegisterPair(lhs, rhs);
    registry.add(pair);
  }
  
  static void unregister(Object lhs, Object rhs)
  {
    Set<Pair<IDKey, IDKey>> registry = getRegistry();
    if (registry != null)
    {
      Pair<IDKey, IDKey> pair = getRegisterPair(lhs, rhs);
      registry.remove(pair);
      synchronized (EqualsBuilder.class)
      {
        registry = getRegistry();
        if ((registry != null) && (registry.isEmpty())) {
          REGISTRY.remove();
        }
      }
    }
  }
  
  public static boolean reflectionEquals(Object lhs, Object rhs, Collection<String> excludeFields)
  {
    return reflectionEquals(lhs, rhs, ReflectionToStringBuilder.toNoNullStringArray(excludeFields));
  }
  
  public static boolean reflectionEquals(Object lhs, Object rhs, String... excludeFields)
  {
    return reflectionEquals(lhs, rhs, false, null, excludeFields);
  }
  
  public static boolean reflectionEquals(Object lhs, Object rhs, boolean testTransients)
  {
    return reflectionEquals(lhs, rhs, testTransients, null, new String[0]);
  }
  
  public static boolean reflectionEquals(Object lhs, Object rhs, boolean testTransients, Class<?> reflectUpToClass, String... excludeFields)
  {
    if (lhs == rhs) {
      return true;
    }
    if ((lhs == null) || (rhs == null)) {
      return false;
    }
    Class<?> lhsClass = lhs.getClass();
    Class<?> rhsClass = rhs.getClass();
    if (lhsClass.isInstance(rhs))
    {
      Class<?> testClass = lhsClass;
      if (!rhsClass.isInstance(lhs)) {
        testClass = rhsClass;
      }
    }
    else if (rhsClass.isInstance(lhs))
    {
      Class<?> testClass = rhsClass;
      if (!lhsClass.isInstance(rhs)) {
        testClass = lhsClass;
      }
    }
    else
    {
      return false;
    }
    Class<?> testClass;
    EqualsBuilder equalsBuilder = new EqualsBuilder();
    try
    {
      reflectionAppend(lhs, rhs, testClass, equalsBuilder, testTransients, excludeFields);
      while ((testClass.getSuperclass() != null) && (testClass != reflectUpToClass))
      {
        testClass = testClass.getSuperclass();
        reflectionAppend(lhs, rhs, testClass, equalsBuilder, testTransients, excludeFields);
      }
    }
    catch (IllegalArgumentException local_e)
    {
      return false;
    }
    return equalsBuilder.isEquals();
  }
  
  private static void reflectionAppend(Object lhs, Object rhs, Class<?> clazz, EqualsBuilder builder, boolean useTransients, String[] excludeFields)
  {
    if (isRegistered(lhs, rhs)) {
      return;
    }
    try
    {
      register(lhs, rhs);
      Field[] fields = clazz.getDeclaredFields();
      AccessibleObject.setAccessible(fields, true);
      for (int local_i = 0; (local_i < fields.length) && (builder.isEquals); local_i++)
      {
        Field local_f = fields[local_i];
        if ((!ArrayUtils.contains(excludeFields, local_f.getName())) && (local_f.getName().indexOf('$') == -1) && ((useTransients) || (!Modifier.isTransient(local_f.getModifiers()))) && (!Modifier.isStatic(local_f.getModifiers()))) {
          try
          {
            builder.append(local_f.get(lhs), local_f.get(rhs));
          }
          catch (IllegalAccessException local_e)
          {
            throw new InternalError("Unexpected IllegalAccessException");
          }
        }
      }
    }
    finally
    {
      unregister(lhs, rhs);
    }
  }
  
  public EqualsBuilder appendSuper(boolean superEquals)
  {
    if (!this.isEquals) {
      return this;
    }
    this.isEquals = superEquals;
    return this;
  }
  
  public EqualsBuilder append(Object lhs, Object rhs)
  {
    if (!this.isEquals) {
      return this;
    }
    if (lhs == rhs) {
      return this;
    }
    if ((lhs == null) || (rhs == null))
    {
      setEquals(false);
      return this;
    }
    Class<?> lhsClass = lhs.getClass();
    if (!lhsClass.isArray()) {
      this.isEquals = lhs.equals(rhs);
    } else if (lhs.getClass() != rhs.getClass()) {
      setEquals(false);
    } else if ((lhs instanceof long[])) {
      append((long[])lhs, (long[])rhs);
    } else if ((lhs instanceof int[])) {
      append((int[])lhs, (int[])rhs);
    } else if ((lhs instanceof short[])) {
      append((short[])lhs, (short[])rhs);
    } else if ((lhs instanceof char[])) {
      append((char[])lhs, (char[])rhs);
    } else if ((lhs instanceof byte[])) {
      append((byte[])lhs, (byte[])rhs);
    } else if ((lhs instanceof double[])) {
      append((double[])lhs, (double[])rhs);
    } else if ((lhs instanceof float[])) {
      append((float[])lhs, (float[])rhs);
    } else if ((lhs instanceof boolean[])) {
      append((boolean[])lhs, (boolean[])rhs);
    } else {
      append((Object[])lhs, (Object[])rhs);
    }
    return this;
  }
  
  public EqualsBuilder append(long lhs, long rhs)
  {
    if (!this.isEquals) {
      return this;
    }
    this.isEquals = (lhs == rhs);
    return this;
  }
  
  public EqualsBuilder append(int lhs, int rhs)
  {
    if (!this.isEquals) {
      return this;
    }
    this.isEquals = (lhs == rhs);
    return this;
  }
  
  public EqualsBuilder append(short lhs, short rhs)
  {
    if (!this.isEquals) {
      return this;
    }
    this.isEquals = (lhs == rhs);
    return this;
  }
  
  public EqualsBuilder append(char lhs, char rhs)
  {
    if (!this.isEquals) {
      return this;
    }
    this.isEquals = (lhs == rhs);
    return this;
  }
  
  public EqualsBuilder append(byte lhs, byte rhs)
  {
    if (!this.isEquals) {
      return this;
    }
    this.isEquals = (lhs == rhs);
    return this;
  }
  
  public EqualsBuilder append(double lhs, double rhs)
  {
    if (!this.isEquals) {
      return this;
    }
    return append(Double.doubleToLongBits(lhs), Double.doubleToLongBits(rhs));
  }
  
  public EqualsBuilder append(float lhs, float rhs)
  {
    if (!this.isEquals) {
      return this;
    }
    return append(Float.floatToIntBits(lhs), Float.floatToIntBits(rhs));
  }
  
  public EqualsBuilder append(boolean lhs, boolean rhs)
  {
    if (!this.isEquals) {
      return this;
    }
    this.isEquals = (lhs == rhs);
    return this;
  }
  
  public EqualsBuilder append(Object[] lhs, Object[] rhs)
  {
    if (!this.isEquals) {
      return this;
    }
    if (lhs == rhs) {
      return this;
    }
    if ((lhs == null) || (rhs == null))
    {
      setEquals(false);
      return this;
    }
    if (lhs.length != rhs.length)
    {
      setEquals(false);
      return this;
    }
    for (int local_i = 0; (local_i < lhs.length) && (this.isEquals); local_i++) {
      append(lhs[local_i], rhs[local_i]);
    }
    return this;
  }
  
  public EqualsBuilder append(long[] lhs, long[] rhs)
  {
    if (!this.isEquals) {
      return this;
    }
    if (lhs == rhs) {
      return this;
    }
    if ((lhs == null) || (rhs == null))
    {
      setEquals(false);
      return this;
    }
    if (lhs.length != rhs.length)
    {
      setEquals(false);
      return this;
    }
    for (int local_i = 0; (local_i < lhs.length) && (this.isEquals); local_i++) {
      append(lhs[local_i], rhs[local_i]);
    }
    return this;
  }
  
  public EqualsBuilder append(int[] lhs, int[] rhs)
  {
    if (!this.isEquals) {
      return this;
    }
    if (lhs == rhs) {
      return this;
    }
    if ((lhs == null) || (rhs == null))
    {
      setEquals(false);
      return this;
    }
    if (lhs.length != rhs.length)
    {
      setEquals(false);
      return this;
    }
    for (int local_i = 0; (local_i < lhs.length) && (this.isEquals); local_i++) {
      append(lhs[local_i], rhs[local_i]);
    }
    return this;
  }
  
  public EqualsBuilder append(short[] lhs, short[] rhs)
  {
    if (!this.isEquals) {
      return this;
    }
    if (lhs == rhs) {
      return this;
    }
    if ((lhs == null) || (rhs == null))
    {
      setEquals(false);
      return this;
    }
    if (lhs.length != rhs.length)
    {
      setEquals(false);
      return this;
    }
    for (int local_i = 0; (local_i < lhs.length) && (this.isEquals); local_i++) {
      append(lhs[local_i], rhs[local_i]);
    }
    return this;
  }
  
  public EqualsBuilder append(char[] lhs, char[] rhs)
  {
    if (!this.isEquals) {
      return this;
    }
    if (lhs == rhs) {
      return this;
    }
    if ((lhs == null) || (rhs == null))
    {
      setEquals(false);
      return this;
    }
    if (lhs.length != rhs.length)
    {
      setEquals(false);
      return this;
    }
    for (int local_i = 0; (local_i < lhs.length) && (this.isEquals); local_i++) {
      append(lhs[local_i], rhs[local_i]);
    }
    return this;
  }
  
  public EqualsBuilder append(byte[] lhs, byte[] rhs)
  {
    if (!this.isEquals) {
      return this;
    }
    if (lhs == rhs) {
      return this;
    }
    if ((lhs == null) || (rhs == null))
    {
      setEquals(false);
      return this;
    }
    if (lhs.length != rhs.length)
    {
      setEquals(false);
      return this;
    }
    for (int local_i = 0; (local_i < lhs.length) && (this.isEquals); local_i++) {
      append(lhs[local_i], rhs[local_i]);
    }
    return this;
  }
  
  public EqualsBuilder append(double[] lhs, double[] rhs)
  {
    if (!this.isEquals) {
      return this;
    }
    if (lhs == rhs) {
      return this;
    }
    if ((lhs == null) || (rhs == null))
    {
      setEquals(false);
      return this;
    }
    if (lhs.length != rhs.length)
    {
      setEquals(false);
      return this;
    }
    for (int local_i = 0; (local_i < lhs.length) && (this.isEquals); local_i++) {
      append(lhs[local_i], rhs[local_i]);
    }
    return this;
  }
  
  public EqualsBuilder append(float[] lhs, float[] rhs)
  {
    if (!this.isEquals) {
      return this;
    }
    if (lhs == rhs) {
      return this;
    }
    if ((lhs == null) || (rhs == null))
    {
      setEquals(false);
      return this;
    }
    if (lhs.length != rhs.length)
    {
      setEquals(false);
      return this;
    }
    for (int local_i = 0; (local_i < lhs.length) && (this.isEquals); local_i++) {
      append(lhs[local_i], rhs[local_i]);
    }
    return this;
  }
  
  public EqualsBuilder append(boolean[] lhs, boolean[] rhs)
  {
    if (!this.isEquals) {
      return this;
    }
    if (lhs == rhs) {
      return this;
    }
    if ((lhs == null) || (rhs == null))
    {
      setEquals(false);
      return this;
    }
    if (lhs.length != rhs.length)
    {
      setEquals(false);
      return this;
    }
    for (int local_i = 0; (local_i < lhs.length) && (this.isEquals); local_i++) {
      append(lhs[local_i], rhs[local_i]);
    }
    return this;
  }
  
  public boolean isEquals()
  {
    return this.isEquals;
  }
  
  public Boolean build()
  {
    return Boolean.valueOf(isEquals());
  }
  
  protected void setEquals(boolean isEquals)
  {
    this.isEquals = isEquals;
  }
  
  public void reset()
  {
    this.isEquals = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.builder.EqualsBuilder
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */