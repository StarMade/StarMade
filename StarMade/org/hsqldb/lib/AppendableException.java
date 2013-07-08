package org.hsqldb.lib;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppendableException
  extends Exception
{
  static final long serialVersionUID = -1002629580611098803L;
  public static String field_1903 = System.getProperty("line.separator");
  public List<String> appendages = null;
  
  public String getMessage()
  {
    String str1 = super.getMessage();
    if (this.appendages == null) {
      return str1;
    }
    StringBuffer localStringBuffer = new StringBuffer();
    if (str1 != null) {
      localStringBuffer.append(str1);
    }
    Iterator localIterator = this.appendages.iterator();
    while (localIterator.hasNext())
    {
      String str2 = (String)localIterator.next();
      if (localStringBuffer.length() > 0) {
        localStringBuffer.append(field_1903);
      }
      localStringBuffer.append(str2);
    }
    return localStringBuffer.toString();
  }
  
  public void appendMessage(String paramString)
  {
    if (this.appendages == null) {
      this.appendages = new ArrayList();
    }
    this.appendages.add(paramString);
  }
  
  public AppendableException() {}
  
  public AppendableException(String paramString)
  {
    super(paramString);
  }
  
  public AppendableException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
  
  public AppendableException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.lib.AppendableException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */