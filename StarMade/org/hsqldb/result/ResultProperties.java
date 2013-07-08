package org.hsqldb.result;

public class ResultProperties
{
  static final int idx_returnable = 0;
  static final int idx_holdable = 1;
  static final int idx_scrollable = 2;
  static final int idx_updatable = 3;
  static final int idx_sensitive = 4;
  static final int idx_isheld = 5;
  public static final int defaultPropsValue = 0;
  public static final int updatablePropsValue = 8;
  
  public static int getProperties(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    int i = paramInt1 << 4 | paramInt2 << 3 | paramInt3 << 2 | paramInt4 << 1 | paramInt5 << 0;
    return i;
  }
  
  public static int getJDBCHoldability(int paramInt)
  {
    return isHoldable(paramInt) ? 1 : 2;
  }
  
  public static int getJDBCConcurrency(int paramInt)
  {
    return isReadOnly(paramInt) ? 1007 : 1008;
  }
  
  public static int getJDBCScrollability(int paramInt)
  {
    return isScrollable(paramInt) ? 1004 : 1003;
  }
  
  public static int getValueForJDBC(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramInt1 == 1003 ? 0 : 1;
    int j = paramInt2 == 1008 ? 1 : 0;
    int k = paramInt3 == 1 ? 1 : 0;
    int m = j << 3 | i << 2 | k << 1;
    return m;
  }
  
  public static boolean isUpdatable(int paramInt)
  {
    return (paramInt & 0x8) != 0;
  }
  
  public static boolean isScrollable(int paramInt)
  {
    return (paramInt & 0x4) != 0;
  }
  
  public static boolean isHoldable(int paramInt)
  {
    return (paramInt & 0x2) != 0;
  }
  
  public static boolean isSensitive(int paramInt)
  {
    return (paramInt & 0x10) != 0;
  }
  
  public static boolean isReadOnly(int paramInt)
  {
    return (paramInt & 0x8) == 0;
  }
  
  public static boolean isHeld(int paramInt)
  {
    return (paramInt & 0x20) != 0;
  }
  
  public static int addUpdatable(int paramInt, boolean paramBoolean)
  {
    return paramBoolean ? paramInt | 0x8 : paramInt & 0xFFFFFFF7;
  }
  
  public static int addHoldable(int paramInt, boolean paramBoolean)
  {
    return paramBoolean ? paramInt | 0x2 : paramInt & 0xFFFFFFFD;
  }
  
  public static int addScrollable(int paramInt, boolean paramBoolean)
  {
    return paramBoolean ? paramInt | 0x4 : paramInt & 0xFFFFFFFB;
  }
  
  public static int addIsHeld(int paramInt, boolean paramBoolean)
  {
    return paramBoolean ? paramInt | 0x20 : paramInt & 0xFFFFFFDF;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.result.ResultProperties
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */