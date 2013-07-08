package org.apache.commons.lang3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LocaleUtils
{
  private static final ConcurrentMap<String, List<Locale>> cLanguagesByCountry = new ConcurrentHashMap();
  private static final ConcurrentMap<String, List<Locale>> cCountriesByLanguage = new ConcurrentHashMap();
  
  public static Locale toLocale(String str)
  {
    if (str == null) {
      return null;
    }
    int len = str.length();
    if ((len != 2) && (len != 5) && (len < 7)) {
      throw new IllegalArgumentException("Invalid locale format: " + str);
    }
    char ch0 = str.charAt(0);
    char ch1 = str.charAt(1);
    if ((ch0 < 'a') || (ch0 > 'z') || (ch1 < 'a') || (ch1 > 'z')) {
      throw new IllegalArgumentException("Invalid locale format: " + str);
    }
    if (len == 2) {
      return new Locale(str, "");
    }
    if (str.charAt(2) != '_') {
      throw new IllegalArgumentException("Invalid locale format: " + str);
    }
    char ch3 = str.charAt(3);
    if (ch3 == '_') {
      return new Locale(str.substring(0, 2), "", str.substring(4));
    }
    char ch4 = str.charAt(4);
    if ((ch3 < 'A') || (ch3 > 'Z') || (ch4 < 'A') || (ch4 > 'Z')) {
      throw new IllegalArgumentException("Invalid locale format: " + str);
    }
    if (len == 5) {
      return new Locale(str.substring(0, 2), str.substring(3, 5));
    }
    if (str.charAt(5) != '_') {
      throw new IllegalArgumentException("Invalid locale format: " + str);
    }
    return new Locale(str.substring(0, 2), str.substring(3, 5), str.substring(6));
  }
  
  public static List<Locale> localeLookupList(Locale locale)
  {
    return localeLookupList(locale, locale);
  }
  
  public static List<Locale> localeLookupList(Locale locale, Locale defaultLocale)
  {
    List<Locale> list = new ArrayList(4);
    if (locale != null)
    {
      list.add(locale);
      if (locale.getVariant().length() > 0) {
        list.add(new Locale(locale.getLanguage(), locale.getCountry()));
      }
      if (locale.getCountry().length() > 0) {
        list.add(new Locale(locale.getLanguage(), ""));
      }
      if (!list.contains(defaultLocale)) {
        list.add(defaultLocale);
      }
    }
    return Collections.unmodifiableList(list);
  }
  
  public static List<Locale> availableLocaleList()
  {
    return SyncAvoid.AVAILABLE_LOCALE_LIST;
  }
  
  public static Set<Locale> availableLocaleSet()
  {
    return SyncAvoid.AVAILABLE_LOCALE_SET;
  }
  
  public static boolean isAvailableLocale(Locale locale)
  {
    return availableLocaleList().contains(locale);
  }
  
  public static List<Locale> languagesByCountry(String countryCode)
  {
    if (countryCode == null) {
      return Collections.emptyList();
    }
    List<Locale> langs = (List)cLanguagesByCountry.get(countryCode);
    if (langs == null)
    {
      langs = new ArrayList();
      List<Locale> locales = availableLocaleList();
      for (int local_i = 0; local_i < locales.size(); local_i++)
      {
        Locale locale = (Locale)locales.get(local_i);
        if ((countryCode.equals(locale.getCountry())) && (locale.getVariant().length() == 0)) {
          langs.add(locale);
        }
      }
      langs = Collections.unmodifiableList(langs);
      cLanguagesByCountry.putIfAbsent(countryCode, langs);
      langs = (List)cLanguagesByCountry.get(countryCode);
    }
    return langs;
  }
  
  public static List<Locale> countriesByLanguage(String languageCode)
  {
    if (languageCode == null) {
      return Collections.emptyList();
    }
    List<Locale> countries = (List)cCountriesByLanguage.get(languageCode);
    if (countries == null)
    {
      countries = new ArrayList();
      List<Locale> locales = availableLocaleList();
      for (int local_i = 0; local_i < locales.size(); local_i++)
      {
        Locale locale = (Locale)locales.get(local_i);
        if ((languageCode.equals(locale.getLanguage())) && (locale.getCountry().length() != 0) && (locale.getVariant().length() == 0)) {
          countries.add(locale);
        }
      }
      countries = Collections.unmodifiableList(countries);
      cCountriesByLanguage.putIfAbsent(languageCode, countries);
      countries = (List)cCountriesByLanguage.get(languageCode);
    }
    return countries;
  }
  
  static class SyncAvoid
  {
    private static List<Locale> AVAILABLE_LOCALE_LIST = Collections.unmodifiableList(list);
    private static Set<Locale> AVAILABLE_LOCALE_SET = Collections.unmodifiableSet(new HashSet(LocaleUtils.availableLocaleList()));
    
    static
    {
      List<Locale> list = new ArrayList(Arrays.asList(Locale.getAvailableLocales()));
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.LocaleUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */