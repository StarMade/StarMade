package org.jaxen.function.ext;

import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import org.jaxen.Function;
import org.jaxen.Navigator;
import org.jaxen.function.StringFunction;

public abstract class LocaleFunctionSupport
  implements Function
{
  protected Locale getLocale(Object value, Navigator navigator)
  {
    if ((value instanceof Locale)) {
      return (Locale)value;
    }
    if ((value instanceof List))
    {
      List list = (List)value;
      if (!list.isEmpty()) {
        return getLocale(list.get(0), navigator);
      }
    }
    else
    {
      String list = StringFunction.evaluate(value, navigator);
      if ((list != null) && (list.length() > 0)) {
        return findLocale(list);
      }
    }
    return null;
  }
  
  protected Locale findLocale(String localeText)
  {
    StringTokenizer tokens = new StringTokenizer(localeText, "-");
    if (tokens.hasMoreTokens())
    {
      String language = tokens.nextToken();
      if (!tokens.hasMoreTokens()) {
        return findLocaleForLanguage(language);
      }
      String country = tokens.nextToken();
      if (!tokens.hasMoreTokens()) {
        return new Locale(language, country);
      }
      String variant = tokens.nextToken();
      return new Locale(language, country, variant);
    }
    return null;
  }
  
  protected Locale findLocaleForLanguage(String language)
  {
    Locale[] locales = Locale.getAvailableLocales();
    int local_i = 0;
    int size = locales.length;
    while (local_i < size)
    {
      Locale locale = locales[local_i];
      if (language.equals(locale.getLanguage()))
      {
        String country = locale.getCountry();
        if ((country == null) || (country.length() == 0))
        {
          String variant = locale.getVariant();
          if ((variant == null) || (variant.length() == 0)) {
            return locale;
          }
        }
      }
      local_i++;
    }
    return null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.function.ext.LocaleFunctionSupport
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */