/*   1:    */package org.jaxen.function.ext;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import java.util.Locale;
/*   5:    */import java.util.StringTokenizer;
/*   6:    */import org.jaxen.Function;
/*   7:    */import org.jaxen.Navigator;
/*   8:    */import org.jaxen.function.StringFunction;
/*   9:    */
/*  79:    */public abstract class LocaleFunctionSupport
/*  80:    */  implements Function
/*  81:    */{
/*  82:    */  protected Locale getLocale(Object value, Navigator navigator)
/*  83:    */  {
/*  84: 84 */    if ((value instanceof Locale))
/*  85:    */    {
/*  86: 86 */      return (Locale)value;
/*  87:    */    }
/*  88: 88 */    if ((value instanceof List))
/*  89:    */    {
/*  90: 90 */      List list = (List)value;
/*  91: 91 */      if (!list.isEmpty())
/*  92:    */      {
/*  93: 93 */        return getLocale(list.get(0), navigator);
/*  94:    */      }
/*  95:    */    }
/*  96:    */    else {
/*  97: 97 */      String text = StringFunction.evaluate(value, navigator);
/*  98: 98 */      if ((text != null) && (text.length() > 0))
/*  99:    */      {
/* 100:100 */        return findLocale(text);
/* 101:    */      }
/* 102:    */    }
/* 103:103 */    return null;
/* 104:    */  }
/* 105:    */  
/* 114:    */  protected Locale findLocale(String localeText)
/* 115:    */  {
/* 116:116 */    StringTokenizer tokens = new StringTokenizer(localeText, "-");
/* 117:117 */    if (tokens.hasMoreTokens())
/* 118:    */    {
/* 119:119 */      String language = tokens.nextToken();
/* 120:120 */      if (!tokens.hasMoreTokens())
/* 121:    */      {
/* 122:122 */        return findLocaleForLanguage(language);
/* 123:    */      }
/* 124:    */      
/* 126:126 */      String country = tokens.nextToken();
/* 127:127 */      if (!tokens.hasMoreTokens())
/* 128:    */      {
/* 129:129 */        return new Locale(language, country);
/* 130:    */      }
/* 131:    */      
/* 133:133 */      String variant = tokens.nextToken();
/* 134:134 */      return new Locale(language, country, variant);
/* 135:    */    }
/* 136:    */    
/* 138:138 */    return null;
/* 139:    */  }
/* 140:    */  
/* 148:    */  protected Locale findLocaleForLanguage(String language)
/* 149:    */  {
/* 150:150 */    Locale[] locales = Locale.getAvailableLocales();
/* 151:151 */    int i = 0; for (int size = locales.length; i < size; i++)
/* 152:    */    {
/* 153:153 */      Locale locale = locales[i];
/* 154:154 */      if (language.equals(locale.getLanguage()))
/* 155:    */      {
/* 156:156 */        String country = locale.getCountry();
/* 157:157 */        if ((country == null) || (country.length() == 0))
/* 158:    */        {
/* 159:159 */          String variant = locale.getVariant();
/* 160:160 */          if ((variant == null) || (variant.length() == 0))
/* 161:    */          {
/* 162:162 */            return locale;
/* 163:    */          }
/* 164:    */        }
/* 165:    */      }
/* 166:    */    }
/* 167:167 */    return null;
/* 168:    */  }
/* 169:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.ext.LocaleFunctionSupport
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */