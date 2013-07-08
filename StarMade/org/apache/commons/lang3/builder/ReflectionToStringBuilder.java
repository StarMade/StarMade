package org.apache.commons.lang3.builder;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;

public class ReflectionToStringBuilder
  extends ToStringBuilder
{
  private boolean appendStatics = false;
  private boolean appendTransients = false;
  protected String[] excludeFieldNames;
  private Class<?> upToClass = null;
  
  public static String toString(Object object)
  {
    return toString(object, null, false, false, null);
  }
  
  public static String toString(Object object, ToStringStyle style)
  {
    return toString(object, style, false, false, null);
  }
  
  public static String toString(Object object, ToStringStyle style, boolean outputTransients)
  {
    return toString(object, style, outputTransients, false, null);
  }
  
  public static String toString(Object object, ToStringStyle style, boolean outputTransients, boolean outputStatics)
  {
    return toString(object, style, outputTransients, outputStatics, null);
  }
  
  public static <T> String toString(T object, ToStringStyle style, boolean outputTransients, boolean outputStatics, Class<? super T> reflectUpToClass)
  {
    return new ReflectionToStringBuilder(object, style, null, reflectUpToClass, outputTransients, outputStatics).toString();
  }
  
  public static String toStringExclude(Object object, Collection<String> excludeFieldNames)
  {
    return toStringExclude(object, toNoNullStringArray(excludeFieldNames));
  }
  
  static String[] toNoNullStringArray(Collection<String> collection)
  {
    if (collection == null) {
      return ArrayUtils.EMPTY_STRING_ARRAY;
    }
    return toNoNullStringArray(collection.toArray());
  }
  
  static String[] toNoNullStringArray(Object[] array)
  {
    List<String> list = new ArrayList(array.length);
    for (Object local_e : array) {
      if (local_e != null) {
        list.add(local_e.toString());
      }
    }
    return (String[])list.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
  }
  
  public static String toStringExclude(Object object, String... excludeFieldNames)
  {
    return new ReflectionToStringBuilder(object).setExcludeFieldNames(excludeFieldNames).toString();
  }
  
  public ReflectionToStringBuilder(Object object)
  {
    super(object);
  }
  
  public ReflectionToStringBuilder(Object object, ToStringStyle style)
  {
    super(object, style);
  }
  
  public ReflectionToStringBuilder(Object object, ToStringStyle style, StringBuffer buffer)
  {
    super(object, style, buffer);
  }
  
  public <T> ReflectionToStringBuilder(T object, ToStringStyle style, StringBuffer buffer, Class<? super T> reflectUpToClass, boolean outputTransients, boolean outputStatics)
  {
    super(object, style, buffer);
    setUpToClass(reflectUpToClass);
    setAppendTransients(outputTransients);
    setAppendStatics(outputStatics);
  }
  
  protected boolean accept(Field field)
  {
    if (field.getName().indexOf('$') != -1) {
      return false;
    }
    if ((Modifier.isTransient(field.getModifiers())) && (!isAppendTransients())) {
      return false;
    }
    if ((Modifier.isStatic(field.getModifiers())) && (!isAppendStatics())) {
      return false;
    }
    return (this.excludeFieldNames == null) || (Arrays.binarySearch(this.excludeFieldNames, field.getName()) < 0);
  }
  
  protected void appendFieldsIn(Class<?> clazz)
  {
    if (clazz.isArray())
    {
      reflectionAppendArray(getObject());
      return;
    }
    Field[] fields = clazz.getDeclaredFields();
    AccessibleObject.setAccessible(fields, true);
    for (Field field : fields)
    {
      String fieldName = field.getName();
      if (accept(field)) {
        try
        {
          Object fieldValue = getValue(field);
          append(fieldName, fieldValue);
        }
        catch (IllegalAccessException fieldValue)
        {
          throw new InternalError("Unexpected IllegalAccessException: " + fieldValue.getMessage());
        }
      }
    }
  }
  
  public String[] getExcludeFieldNames()
  {
    return (String[])this.excludeFieldNames.clone();
  }
  
  public Class<?> getUpToClass()
  {
    return this.upToClass;
  }
  
  protected Object getValue(Field field)
    throws IllegalArgumentException, IllegalAccessException
  {
    return field.get(getObject());
  }
  
  public boolean isAppendStatics()
  {
    return this.appendStatics;
  }
  
  public boolean isAppendTransients()
  {
    return this.appendTransients;
  }
  
  public ReflectionToStringBuilder reflectionAppendArray(Object array)
  {
    getStyle().reflectionAppendArrayDetail(getStringBuffer(), null, array);
    return this;
  }
  
  public void setAppendStatics(boolean appendStatics)
  {
    this.appendStatics = appendStatics;
  }
  
  public void setAppendTransients(boolean appendTransients)
  {
    this.appendTransients = appendTransients;
  }
  
  public ReflectionToStringBuilder setExcludeFieldNames(String... excludeFieldNamesParam)
  {
    if (excludeFieldNamesParam == null)
    {
      this.excludeFieldNames = null;
    }
    else
    {
      this.excludeFieldNames = toNoNullStringArray(excludeFieldNamesParam);
      Arrays.sort(this.excludeFieldNames);
    }
    return this;
  }
  
  public void setUpToClass(Class<?> clazz)
  {
    if (clazz != null)
    {
      Object object = getObject();
      if ((object != null) && (!clazz.isInstance(object))) {
        throw new IllegalArgumentException("Specified class is not a superclass of the object");
      }
    }
    this.upToClass = clazz;
  }
  
  public String toString()
  {
    if (getObject() == null) {
      return getStyle().getNullText();
    }
    Class<?> clazz = getObject().getClass();
    appendFieldsIn(clazz);
    while ((clazz.getSuperclass() != null) && (clazz != getUpToClass()))
    {
      clazz = clazz.getSuperclass();
      appendFieldsIn(clazz);
    }
    return super.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.builder.ReflectionToStringBuilder
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */