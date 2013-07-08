package org.hsqldb.lib;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RefCapablePropertyResourceBundle
{
  private PropertyResourceBundle wrappedBundle;
  private String baseName;
  private String language;
  private String country;
  private String variant;
  private static Map<ResourceBundle, RefCapablePropertyResourceBundle> allBundles = new HashMap();
  public static String LS = System.getProperty("line.separator");
  private Pattern sysPropVarPattern = Pattern.compile("(?s)\\Q${\\E([^}]+?)(?:\\Q:+\\E([^}]+))?\\Q}");
  private Pattern posPattern = Pattern.compile("(?s)\\Q%{\\E(\\d)(?:\\Q:+\\E([^}]+))?\\Q}");
  private ClassLoader loader;
  public static final int THROW_BEHAVIOR = 0;
  public static final int EMPTYSTRING_BEHAVIOR = 1;
  public static final int NOOP_BEHAVIOR = 2;
  
  public Enumeration<String> getKeys()
  {
    return this.wrappedBundle.getKeys();
  }
  
  private RefCapablePropertyResourceBundle(String paramString, PropertyResourceBundle paramPropertyResourceBundle, ClassLoader paramClassLoader)
  {
    this.baseName = paramString;
    this.wrappedBundle = paramPropertyResourceBundle;
    Locale localLocale = paramPropertyResourceBundle.getLocale();
    this.loader = paramClassLoader;
    this.language = localLocale.getLanguage();
    this.country = localLocale.getCountry();
    this.variant = localLocale.getVariant();
    if (this.language.length() < 1) {
      this.language = null;
    }
    if (this.country.length() < 1) {
      this.country = null;
    }
    if (this.variant.length() < 1) {
      this.variant = null;
    }
  }
  
  public String getExpandedString(String paramString, int paramInt)
  {
    String str1 = getString(paramString);
    Matcher localMatcher = this.sysPropVarPattern.matcher(str1);
    int i = 0;
    StringBuffer localStringBuffer = new StringBuffer();
    while (localMatcher.find())
    {
      String str2 = localMatcher.group(1);
      Object localObject = localMatcher.groupCount() > 1 ? localMatcher.group(2) : null;
      String str3 = System.getProperty(str2);
      if (localObject != null) {
        str3 = str3 == null ? "" : localObject.replaceAll(new StringBuilder().append("\\Q$").append(str2).append("\\E\\b").toString(), Matcher.quoteReplacement(str3));
      }
      if (str3 == null) {
        switch (paramInt)
        {
        case 0: 
          throw new RuntimeException(new StringBuilder().append("No Sys Property set for variable '").append(str2).append("' in property value (").append(str1).append(").").toString());
        case 1: 
          str3 = "";
          break;
        case 2: 
          break;
        default: 
          throw new RuntimeException(new StringBuilder().append("Undefined value for behavior: ").append(paramInt).toString());
        }
      }
      localStringBuffer.append(new StringBuilder().append(str1.substring(i, localMatcher.start())).append(str3 == null ? localMatcher.group() : str3).toString());
      i = localMatcher.end();
    }
    return i < 1 ? str1 : new StringBuilder().append(localStringBuffer.toString()).append(str1.substring(i)).toString();
  }
  
  public String posSubst(String paramString, String[] paramArrayOfString, int paramInt)
  {
    Matcher localMatcher = this.posPattern.matcher(paramString);
    int i = 0;
    StringBuffer localStringBuffer = new StringBuffer();
    while (localMatcher.find())
    {
      int j = Integer.parseInt(localMatcher.group(1)) - 1;
      Object localObject = localMatcher.groupCount() > 1 ? localMatcher.group(2) : null;
      String str = j < paramArrayOfString.length ? paramArrayOfString[j] : null;
      if (localObject != null) {
        str = str == null ? "" : localObject.replaceAll(new StringBuilder().append("\\Q%").append(j + 1).append("\\E\\b").toString(), Matcher.quoteReplacement(str));
      }
      if (str == null) {
        switch (paramInt)
        {
        case 0: 
          throw new RuntimeException(new StringBuilder().append(Integer.toString(paramArrayOfString.length)).append(" positional values given, but property string ").append("contains (").append(localMatcher.group()).append(").").toString());
        case 1: 
          str = "";
          break;
        case 2: 
          break;
        default: 
          throw new RuntimeException(new StringBuilder().append("Undefined value for behavior: ").append(paramInt).toString());
        }
      }
      localStringBuffer.append(new StringBuilder().append(paramString.substring(i, localMatcher.start())).append(str == null ? localMatcher.group() : str).toString());
      i = localMatcher.end();
    }
    return i < 1 ? paramString : new StringBuilder().append(localStringBuffer.toString()).append(paramString.substring(i)).toString();
  }
  
  public String getExpandedString(String paramString, String[] paramArrayOfString, int paramInt1, int paramInt2)
  {
    return posSubst(getExpandedString(paramString, paramInt1), paramArrayOfString, paramInt2);
  }
  
  public String getString(String paramString, String[] paramArrayOfString, int paramInt)
  {
    return posSubst(getString(paramString), paramArrayOfString, paramInt);
  }
  
  public String toString()
  {
    return new StringBuilder().append(this.baseName).append(" for ").append(this.language).append(" / ").append(this.country).append(" / ").append(this.variant).toString();
  }
  
  public String getString(String paramString)
  {
    String str = this.wrappedBundle.getString(paramString);
    if (str.length() < 1)
    {
      str = getStringFromFile(paramString);
      if (str.indexOf(13) > -1) {
        str = str.replaceAll("\\Q\r\n", "\n").replaceAll("\\Q\r", "\n");
      }
      if ((str.length() > 0) && (str.charAt(str.length() - 1) == '\n')) {
        str = str.substring(0, str.length() - 1);
      }
    }
    return toNativeLs(str);
  }
  
  public static String toNativeLs(String paramString)
  {
    return LS.equals("\n") ? paramString : paramString.replaceAll("\\Q\n", LS);
  }
  
  public static RefCapablePropertyResourceBundle getBundle(String paramString, ClassLoader paramClassLoader)
  {
    return getRef(paramString, ResourceBundle.getBundle(paramString, Locale.getDefault(), paramClassLoader), paramClassLoader);
  }
  
  public static RefCapablePropertyResourceBundle getBundle(String paramString, Locale paramLocale, ClassLoader paramClassLoader)
  {
    return getRef(paramString, ResourceBundle.getBundle(paramString, paramLocale, paramClassLoader), paramClassLoader);
  }
  
  private static RefCapablePropertyResourceBundle getRef(String paramString, ResourceBundle paramResourceBundle, ClassLoader paramClassLoader)
  {
    if (!(paramResourceBundle instanceof PropertyResourceBundle)) {
      throw new MissingResourceException(new StringBuilder().append("Found a Resource Bundle, but it is a ").append(paramResourceBundle.getClass().getName()).toString(), PropertyResourceBundle.class.getName(), null);
    }
    if (allBundles.containsKey(paramResourceBundle)) {
      return (RefCapablePropertyResourceBundle)allBundles.get(paramResourceBundle);
    }
    RefCapablePropertyResourceBundle localRefCapablePropertyResourceBundle = new RefCapablePropertyResourceBundle(paramString, (PropertyResourceBundle)paramResourceBundle, paramClassLoader);
    allBundles.put(paramResourceBundle, localRefCapablePropertyResourceBundle);
    return localRefCapablePropertyResourceBundle;
  }
  
  private InputStream getMostSpecificStream(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    final String str = new StringBuilder().append(this.baseName.replace('.', '/')).append('/').append(paramString1).append(paramString2 == null ? "" : new StringBuilder().append("_").append(paramString2).toString()).append(paramString3 == null ? "" : new StringBuilder().append("_").append(paramString3).toString()).append(paramString4 == null ? "" : new StringBuilder().append("_").append(paramString4).toString()).append(".text").toString();
    InputStream localInputStream = (InputStream)AccessController.doPrivileged(new PrivilegedAction()
    {
      public InputStream run()
      {
        return RefCapablePropertyResourceBundle.this.loader.getResourceAsStream(str);
      }
    });
    return (localInputStream == null) && (paramString2 != null) ? getMostSpecificStream(paramString1, paramString3 == null ? null : paramString2, paramString4 == null ? null : paramString3, null) : localInputStream;
  }
  
  private String getStringFromFile(String paramString)
  {
    byte[] arrayOfByte = null;
    int i = 0;
    InputStream localInputStream = getMostSpecificStream(paramString, this.language, this.country, this.variant);
    if (localInputStream == null) {
      throw new MissingResourceException(new StringBuilder().append("Key '").append(paramString).append("' is present in .properties file with no value, yet ").append("text file resource is missing").toString(), RefCapablePropertyResourceBundle.class.getName(), paramString);
    }
    try
    {
      try
      {
        arrayOfByte = new byte[localInputStream.available()];
      }
      catch (RuntimeException localRuntimeException1)
      {
        throw new MissingResourceException(new StringBuilder().append("Resource is too big to read in '").append(paramString).append("' value in one ").append("gulp.\nPlease run the program with more RAM ").append("(try Java -Xm* switches).: ").append(localRuntimeException1).toString(), RefCapablePropertyResourceBundle.class.getName(), paramString);
      }
      int j;
      if (i == arrayOfByte.length) {
        break label409;
      }
    }
    catch (IOException localIOException1)
    {
      throw new MissingResourceException(new StringBuilder().append("Failed to read in value for key '").append(paramString).append("': ").append(localIOException1).toString(), RefCapablePropertyResourceBundle.class.getName(), paramString);
      try
      {
        while ((i < arrayOfByte.length) && ((j = localInputStream.read(arrayOfByte, i, arrayOfByte.length - i)) > 0)) {
          i += j;
        }
      }
      catch (IOException localIOException2)
      {
        throw new MissingResourceException(new StringBuilder().append("Failed to read in value for '").append(paramString).append("': ").append(localIOException2).toString(), RefCapablePropertyResourceBundle.class.getName(), paramString);
      }
    }
    finally
    {
      try
      {
        localInputStream.close();
      }
      catch (IOException localIOException4)
      {
        System.err.println(new StringBuilder().append("Failed to close input stream: ").append(localIOException4).toString());
      }
    }
    throw new MissingResourceException(new StringBuilder().append("Didn't read all bytes.  Read in ").append(i).append(" bytes out of ").append(arrayOfByte.length).append(" bytes for key '").append(paramString).append("'").toString(), RefCapablePropertyResourceBundle.class.getName(), paramString);
    try
    {
      label409:
      return new String(arrayOfByte, "ISO-8859-1");
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new RuntimeException(localUnsupportedEncodingException);
    }
    catch (RuntimeException localRuntimeException2)
    {
      throw new MissingResourceException(new StringBuilder().append("Value for key '").append(paramString).append("' too big to convert to String.  ").append("Please run the program with more RAM ").append("(try Java -Xm* switches).: ").append(localRuntimeException2).toString(), RefCapablePropertyResourceBundle.class.getName(), paramString);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.RefCapablePropertyResourceBundle
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */