package org.hsqldb.lib;

import java.io.PrintStream;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class ValidatingResourceBundle
{
  protected boolean validated = false;
  protected Class<? extends Enum<?>> enumType;
  public static final int THROW_BEHAVIOR = 0;
  public static final int EMPTYSTRING_BEHAVIOR = 1;
  public static final int NOOP_BEHAVIOR = 2;
  protected RefCapablePropertyResourceBundle wrappedRCPRB;
  private int missingPropertyBehavior = 0;
  private int missingPosValueBehavior = 0;
  
  public static String resourceKeyFor(Enum<?> paramEnum)
  {
    return paramEnum.name().replace('_', '.');
  }
  
  public ValidatingResourceBundle(String paramString, Class<? extends Enum<?>> paramClass)
  {
    this.enumType = paramClass;
    try
    {
      this.wrappedRCPRB = RefCapablePropertyResourceBundle.getBundle(paramString, paramClass.getClassLoader());
      validate();
    }
    catch (RuntimeException localRuntimeException)
    {
      System.err.println("Failed to initialize resource bundle: " + localRuntimeException);
      throw localRuntimeException;
    }
  }
  
  public String getString(Enum<?> paramEnum)
  {
    if (!this.enumType.isInstance(paramEnum)) {
      throw new IllegalArgumentException("Key is a " + paramEnum.getClass().getName() + ",not a " + this.enumType.getName() + ":  " + paramEnum);
    }
    return this.wrappedRCPRB.getString(paramEnum.toString());
  }
  
  public String getString(Enum<?> paramEnum, String... paramVarArgs)
  {
    if (!this.enumType.isInstance(paramEnum)) {
      throw new IllegalArgumentException("Key is a " + paramEnum.getClass().getName() + ",not a " + this.enumType.getName() + ":  " + paramEnum);
    }
    return this.wrappedRCPRB.getString(paramEnum.toString(), paramVarArgs, this.missingPosValueBehavior);
  }
  
  public String getExpandedString(Enum<?> paramEnum)
  {
    if (!this.enumType.isInstance(paramEnum)) {
      throw new IllegalArgumentException("Key is a " + paramEnum.getClass().getName() + ",not a " + this.enumType.getName() + ":  " + paramEnum);
    }
    return this.wrappedRCPRB.getExpandedString(paramEnum.toString(), this.missingPropertyBehavior);
  }
  
  public String getExpandedString(Enum<?> paramEnum, String... paramVarArgs)
  {
    if (!this.enumType.isInstance(paramEnum)) {
      throw new IllegalArgumentException("Key is a " + paramEnum.getClass().getName() + ",not a " + this.enumType.getName() + ":  " + paramEnum);
    }
    return this.wrappedRCPRB.getExpandedString(paramEnum.toString(), paramVarArgs, this.missingPropertyBehavior, this.missingPosValueBehavior);
  }
  
  public void setMissingPropertyBehavior(int paramInt)
  {
    this.missingPropertyBehavior = paramInt;
  }
  
  public void setMissingPosValueBehavior(int paramInt)
  {
    this.missingPosValueBehavior = paramInt;
  }
  
  public int getMissingPropertyBehavior()
  {
    return this.missingPropertyBehavior;
  }
  
  public int getMissingPosValueBehavior()
  {
    return this.missingPosValueBehavior;
  }
  
  public void validate()
  {
    if (this.validated) {
      return;
    }
    this.validated = true;
    HashSet localHashSet = new HashSet();
    for (Object localObject2 : (Enum[])this.enumType.getEnumConstants()) {
      localHashSet.add(localObject2.toString());
    }
    ??? = this.wrappedRCPRB.getKeys();
    while (((Enumeration)???).hasMoreElements())
    {
      String str = (String)((Enumeration)???).nextElement();
      this.wrappedRCPRB.getString(str);
      localHashSet.remove(str);
    }
    if (localHashSet.size() > 0) {
      throw new RuntimeException("Resource Bundle pre-validation failed.  Missing property with key:  " + localHashSet);
    }
  }
  
  public String getString(Enum<?> paramEnum, int paramInt)
  {
    if (!this.enumType.isInstance(paramEnum)) {
      throw new IllegalArgumentException("Key is a " + paramEnum.getClass().getName() + ",not a " + this.enumType.getName() + ":  " + paramEnum);
    }
    return getString(paramEnum, new String[] { Integer.toString(paramInt) });
  }
  
  public String getString(Enum<?> paramEnum, int paramInt1, int paramInt2)
  {
    if (!this.enumType.isInstance(paramEnum)) {
      throw new IllegalArgumentException("Key is a " + paramEnum.getClass().getName() + ",not a " + this.enumType.getName() + ":  " + paramEnum);
    }
    return getString(paramEnum, new String[] { Integer.toString(paramInt1), Integer.toString(paramInt2) });
  }
  
  public String getString(Enum<?> paramEnum, int paramInt1, int paramInt2, int paramInt3)
  {
    if (!this.enumType.isInstance(paramEnum)) {
      throw new IllegalArgumentException("Key is a " + paramEnum.getClass().getName() + ",not a " + this.enumType.getName() + ":  " + paramEnum);
    }
    return getString(paramEnum, new String[] { Integer.toString(paramInt1), Integer.toString(paramInt2), Integer.toString(paramInt3) });
  }
  
  public String getString(Enum<?> paramEnum, int paramInt, String paramString)
  {
    if (!this.enumType.isInstance(paramEnum)) {
      throw new IllegalArgumentException("Key is a " + paramEnum.getClass().getName() + ",not a " + this.enumType.getName() + ":  " + paramEnum);
    }
    return getString(paramEnum, new String[] { Integer.toString(paramInt), paramString });
  }
  
  public String getString(Enum<?> paramEnum, String paramString, int paramInt)
  {
    if (!this.enumType.isInstance(paramEnum)) {
      throw new IllegalArgumentException("Key is a " + paramEnum.getClass().getName() + ",not a " + this.enumType.getName() + ":  " + paramEnum);
    }
    return getString(paramEnum, new String[] { paramString, Integer.toString(paramInt) });
  }
  
  public String getString(Enum<?> paramEnum, int paramInt1, int paramInt2, String paramString)
  {
    if (!this.enumType.isInstance(paramEnum)) {
      throw new IllegalArgumentException("Key is a " + paramEnum.getClass().getName() + ",not a " + this.enumType.getName() + ":  " + paramEnum);
    }
    return getString(paramEnum, new String[] { Integer.toString(paramInt1), Integer.toString(paramInt2), paramString });
  }
  
  public String getString(Enum<?> paramEnum, int paramInt1, String paramString, int paramInt2)
  {
    if (!this.enumType.isInstance(paramEnum)) {
      throw new IllegalArgumentException("Key is a " + paramEnum.getClass().getName() + ",not a " + this.enumType.getName() + ":  " + paramEnum);
    }
    return getString(paramEnum, new String[] { Integer.toString(paramInt1), paramString, Integer.toString(paramInt2) });
  }
  
  public String getString(Enum<?> paramEnum, String paramString, int paramInt1, int paramInt2)
  {
    if (!this.enumType.isInstance(paramEnum)) {
      throw new IllegalArgumentException("Key is a " + paramEnum.getClass().getName() + ",not a " + this.enumType.getName() + ":  " + paramEnum);
    }
    return getString(paramEnum, new String[] { paramString, Integer.toString(paramInt1), Integer.toString(paramInt2) });
  }
  
  public String getString(Enum<?> paramEnum, int paramInt, String paramString1, String paramString2)
  {
    if (!this.enumType.isInstance(paramEnum)) {
      throw new IllegalArgumentException("Key is a " + paramEnum.getClass().getName() + ",not a " + this.enumType.getName() + ":  " + paramEnum);
    }
    return getString(paramEnum, new String[] { Integer.toString(paramInt), paramString1, paramString2 });
  }
  
  public String getString(Enum<?> paramEnum, String paramString1, String paramString2, int paramInt)
  {
    if (!this.enumType.isInstance(paramEnum)) {
      throw new IllegalArgumentException("Key is a " + paramEnum.getClass().getName() + ",not a " + this.enumType.getName() + ":  " + paramEnum);
    }
    return getString(paramEnum, new String[] { paramString1, paramString2, Integer.toString(paramInt) });
  }
  
  public String getString(Enum<?> paramEnum, String paramString1, int paramInt, String paramString2)
  {
    if (!this.enumType.isInstance(paramEnum)) {
      throw new IllegalArgumentException("Key is a " + paramEnum.getClass().getName() + ",not a " + this.enumType.getName() + ":  " + paramEnum);
    }
    return getString(paramEnum, new String[] { paramString1, Integer.toString(paramInt), paramString2 });
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.lib.ValidatingResourceBundle
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */