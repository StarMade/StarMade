package org.jaxen;

import java.util.Iterator;

public abstract interface NamedAccessNavigator
  extends Navigator
{
  public abstract Iterator getChildAxisIterator(Object paramObject, String paramString1, String paramString2, String paramString3)
    throws UnsupportedAxisException;
  
  public abstract Iterator getAttributeAxisIterator(Object paramObject, String paramString1, String paramString2, String paramString3)
    throws UnsupportedAxisException;
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.NamedAccessNavigator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */