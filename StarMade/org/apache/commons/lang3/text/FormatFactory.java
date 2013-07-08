package org.apache.commons.lang3.text;

import java.text.Format;
import java.util.Locale;

public abstract interface FormatFactory
{
  public abstract Format getFormat(String paramString1, String paramString2, Locale paramLocale);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.FormatFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */