package org.hsqldb.resources;

import java.lang.reflect.Method;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.HsqlArrayList;

public final class BundleHandler
{
  private static final Object mutex = new Object();
  private static Locale locale = Locale.getDefault();
  private static HashMap bundleHandleMap = new HashMap();
  private static HsqlArrayList bundleList = new HsqlArrayList();
  private static final String prefix = "org/hsqldb/resources/";
  private static final Method newGetBundleMethod = getNewGetBundleMethod();

  public static Locale getLocale()
  {
    synchronized (mutex)
    {
      return locale;
    }
  }

  public static void setLocale(Locale paramLocale)
    throws IllegalArgumentException
  {
    synchronized (mutex)
    {
      if (paramLocale == null)
        throw new IllegalArgumentException("null locale");
      locale = paramLocale;
    }
  }

  public static int getBundleHandle(String paramString, ClassLoader paramClassLoader)
  {
    String str1 = "org/hsqldb/resources/" + paramString;
    Integer localInteger;
    synchronized (mutex)
    {
      String str2 = locale.toString() + str1;
      localInteger = (Integer)bundleHandleMap.get(str2);
      if (localInteger == null)
      {
        ResourceBundle localResourceBundle = getBundle(str1, locale, paramClassLoader);
        bundleList.add(localResourceBundle);
        localInteger = new Integer(bundleList.size() - 1);
        bundleHandleMap.put(str2, localInteger);
      }
    }
    return localInteger == null ? -1 : localInteger.intValue();
  }

  public static String getString(int paramInt, String paramString)
  {
    ResourceBundle localResourceBundle;
    synchronized (mutex)
    {
      if ((paramInt < 0) || (paramInt >= bundleList.size()) || (paramString == null))
        localResourceBundle = null;
      else
        localResourceBundle = (ResourceBundle)bundleList.get(paramInt);
    }
    String str;
    if (localResourceBundle == null)
      str = null;
    else
      try
      {
        str = localResourceBundle.getString(paramString);
      }
      catch (Exception localException)
      {
        str = null;
      }
    return str;
  }

  private static Method getNewGetBundleMethod()
  {
    ResourceBundle localResourceBundle = ResourceBundle.class;
    Class[] arrayOfClass = { String.class, Locale.class, ClassLoader.class };
    try
    {
      return localResourceBundle.getMethod("getBundle", arrayOfClass);
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public static ResourceBundle getBundle(String paramString, Locale paramLocale, ClassLoader paramClassLoader)
    throws NullPointerException, MissingResourceException
  {
    if (paramClassLoader == null)
      return ResourceBundle.getBundle(paramString, paramLocale);
    if (newGetBundleMethod == null)
      return ResourceBundle.getBundle(paramString, paramLocale);
    try
    {
      return (ResourceBundle)newGetBundleMethod.invoke(null, new Object[] { paramString, paramLocale, paramClassLoader });
    }
    catch (Exception localException)
    {
    }
    return ResourceBundle.getBundle(paramString, paramLocale);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.resources.BundleHandler
 * JD-Core Version:    0.6.2
 */