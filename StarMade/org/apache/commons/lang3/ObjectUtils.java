package org.apache.commons.lang3;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.lang3.exception.CloneFailedException;
import org.apache.commons.lang3.mutable.MutableInt;

public class ObjectUtils
{
  public static final Null NULL = new Null();
  
  public static <T> T defaultIfNull(T object, T defaultValue)
  {
    return object != null ? object : defaultValue;
  }
  
  public static <T> T firstNonNull(T... values)
  {
    if (values != null) {
      for (T val : values) {
        if (val != null) {
          return val;
        }
      }
    }
    return null;
  }
  
  public static boolean equals(Object object1, Object object2)
  {
    if (object1 == object2) {
      return true;
    }
    if ((object1 == null) || (object2 == null)) {
      return false;
    }
    return object1.equals(object2);
  }
  
  public static boolean notEqual(Object object1, Object object2)
  {
    return !equals(object1, object2);
  }
  
  public static int hashCode(Object obj)
  {
    return obj == null ? 0 : obj.hashCode();
  }
  
  public static int hashCodeMulti(Object... objects)
  {
    int hash = 1;
    if (objects != null) {
      for (Object object : objects) {
        hash = hash * 31 + hashCode(object);
      }
    }
    return hash;
  }
  
  public static String identityToString(Object object)
  {
    if (object == null) {
      return null;
    }
    StringBuffer buffer = new StringBuffer();
    identityToString(buffer, object);
    return buffer.toString();
  }
  
  public static void identityToString(StringBuffer buffer, Object object)
  {
    if (object == null) {
      throw new NullPointerException("Cannot get the toString of a null identity");
    }
    buffer.append(object.getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(object)));
  }
  
  public static String toString(Object obj)
  {
    return obj == null ? "" : obj.toString();
  }
  
  public static String toString(Object obj, String nullStr)
  {
    return obj == null ? nullStr : obj.toString();
  }
  
  public static <T extends Comparable<? super T>> T min(T... values)
  {
    T result = null;
    if (values != null) {
      for (T value : values) {
        if (compare(value, result, true) < 0) {
          result = value;
        }
      }
    }
    return result;
  }
  
  public static <T extends Comparable<? super T>> T max(T... values)
  {
    T result = null;
    if (values != null) {
      for (T value : values) {
        if (compare(value, result, false) > 0) {
          result = value;
        }
      }
    }
    return result;
  }
  
  public static <T extends Comparable<? super T>> int compare(T local_c1, T local_c2)
  {
    return compare(local_c1, local_c2, false);
  }
  
  public static <T extends Comparable<? super T>> int compare(T local_c1, T local_c2, boolean nullGreater)
  {
    if (local_c1 == local_c2) {
      return 0;
    }
    if (local_c1 == null) {
      return nullGreater ? 1 : -1;
    }
    if (local_c2 == null) {
      return nullGreater ? -1 : 1;
    }
    return local_c1.compareTo(local_c2);
  }
  
  public static <T extends Comparable<? super T>> T median(T... items)
  {
    Validate.notEmpty(items);
    Validate.noNullElements(items);
    TreeSet<T> sort = new TreeSet();
    Collections.addAll(sort, items);
    T result = (Comparable)sort.toArray()[((sort.size() - 1) / 2)];
    return result;
  }
  
  public static <T> T median(Comparator<T> comparator, T... items)
  {
    Validate.notEmpty(items, "null/empty items", new Object[0]);
    Validate.noNullElements(items);
    Validate.notNull(comparator, "null comparator", new Object[0]);
    TreeSet<T> sort = new TreeSet(comparator);
    Collections.addAll(sort, items);
    T result = sort.toArray()[((sort.size() - 1) / 2)];
    return result;
  }
  
  public static <T> T mode(T... items)
  {
    if (ArrayUtils.isNotEmpty(items))
    {
      HashMap<T, MutableInt> occurrences = new HashMap(items.length);
      for (T local_t : items)
      {
        MutableInt count = (MutableInt)occurrences.get(local_t);
        if (count == null) {
          occurrences.put(local_t, new MutableInt(1));
        } else {
          count.increment();
        }
      }
      T arr$ = null;
      int len$ = 0;
      Iterator local_i$ = occurrences.entrySet().iterator();
      while (local_i$.hasNext())
      {
        Map.Entry<T, MutableInt> local_t = (Map.Entry)local_i$.next();
        int count = ((MutableInt)local_t.getValue()).intValue();
        if (count == len$)
        {
          arr$ = null;
        }
        else if (count > len$)
        {
          len$ = count;
          arr$ = local_t.getKey();
        }
      }
      return arr$;
    }
    return null;
  }
  
  public static <T> T clone(T obj)
  {
    if ((obj instanceof Cloneable))
    {
      Object result;
      if (obj.getClass().isArray())
      {
        Class<?> componentType = obj.getClass().getComponentType();
        Object result;
        if (!componentType.isPrimitive())
        {
          result = ((Object[])obj).clone();
        }
        else
        {
          int length = Array.getLength(obj);
          Object result = Array.newInstance(componentType, length);
          while (length-- > 0) {
            Array.set(result, length, Array.get(obj, length));
          }
        }
      }
      else
      {
        try
        {
          Method componentType = obj.getClass().getMethod("clone", new Class[0]);
          result = componentType.invoke(obj, new Object[0]);
        }
        catch (NoSuchMethodException componentType)
        {
          throw new CloneFailedException("Cloneable type " + obj.getClass().getName() + " has no clone method", componentType);
        }
        catch (IllegalAccessException componentType)
        {
          throw new CloneFailedException("Cannot clone Cloneable type " + obj.getClass().getName(), componentType);
        }
        catch (InvocationTargetException componentType)
        {
          throw new CloneFailedException("Exception cloning Cloneable type " + obj.getClass().getName(), componentType.getCause());
        }
      }
      T componentType = result;
      return componentType;
    }
    return null;
  }
  
  public static <T> T cloneIfPossible(T obj)
  {
    T clone = clone(obj);
    return clone == null ? obj : clone;
  }
  
  public static class Null
    implements Serializable
  {
    private static final long serialVersionUID = 7092611880189329093L;
    
    private Object readResolve()
    {
      return ObjectUtils.NULL;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.ObjectUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */