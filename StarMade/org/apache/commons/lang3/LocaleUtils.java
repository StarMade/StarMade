/*   1:    */package org.apache.commons.lang3;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Arrays;
/*   5:    */import java.util.Collections;
/*   6:    */import java.util.HashSet;
/*   7:    */import java.util.List;
/*   8:    */import java.util.Locale;
/*   9:    */import java.util.Set;
/*  10:    */import java.util.concurrent.ConcurrentHashMap;
/*  11:    */import java.util.concurrent.ConcurrentMap;
/*  12:    */
/*  40:    */public class LocaleUtils
/*  41:    */{
/*  42: 42 */  private static final ConcurrentMap<String, List<Locale>> cLanguagesByCountry = new ConcurrentHashMap();
/*  43:    */  
/*  46: 46 */  private static final ConcurrentMap<String, List<Locale>> cCountriesByLanguage = new ConcurrentHashMap();
/*  47:    */  
/*  87:    */  public static Locale toLocale(String str)
/*  88:    */  {
/*  89: 89 */    if (str == null) {
/*  90: 90 */      return null;
/*  91:    */    }
/*  92: 92 */    int len = str.length();
/*  93: 93 */    if ((len != 2) && (len != 5) && (len < 7)) {
/*  94: 94 */      throw new IllegalArgumentException("Invalid locale format: " + str);
/*  95:    */    }
/*  96: 96 */    char ch0 = str.charAt(0);
/*  97: 97 */    char ch1 = str.charAt(1);
/*  98: 98 */    if ((ch0 < 'a') || (ch0 > 'z') || (ch1 < 'a') || (ch1 > 'z')) {
/*  99: 99 */      throw new IllegalArgumentException("Invalid locale format: " + str);
/* 100:    */    }
/* 101:101 */    if (len == 2) {
/* 102:102 */      return new Locale(str, "");
/* 103:    */    }
/* 104:104 */    if (str.charAt(2) != '_') {
/* 105:105 */      throw new IllegalArgumentException("Invalid locale format: " + str);
/* 106:    */    }
/* 107:107 */    char ch3 = str.charAt(3);
/* 108:108 */    if (ch3 == '_') {
/* 109:109 */      return new Locale(str.substring(0, 2), "", str.substring(4));
/* 110:    */    }
/* 111:111 */    char ch4 = str.charAt(4);
/* 112:112 */    if ((ch3 < 'A') || (ch3 > 'Z') || (ch4 < 'A') || (ch4 > 'Z')) {
/* 113:113 */      throw new IllegalArgumentException("Invalid locale format: " + str);
/* 114:    */    }
/* 115:115 */    if (len == 5) {
/* 116:116 */      return new Locale(str.substring(0, 2), str.substring(3, 5));
/* 117:    */    }
/* 118:118 */    if (str.charAt(5) != '_') {
/* 119:119 */      throw new IllegalArgumentException("Invalid locale format: " + str);
/* 120:    */    }
/* 121:121 */    return new Locale(str.substring(0, 2), str.substring(3, 5), str.substring(6));
/* 122:    */  }
/* 123:    */  
/* 138:    */  public static List<Locale> localeLookupList(Locale locale)
/* 139:    */  {
/* 140:140 */    return localeLookupList(locale, locale);
/* 141:    */  }
/* 142:    */  
/* 160:    */  public static List<Locale> localeLookupList(Locale locale, Locale defaultLocale)
/* 161:    */  {
/* 162:162 */    List<Locale> list = new ArrayList(4);
/* 163:163 */    if (locale != null) {
/* 164:164 */      list.add(locale);
/* 165:165 */      if (locale.getVariant().length() > 0) {
/* 166:166 */        list.add(new Locale(locale.getLanguage(), locale.getCountry()));
/* 167:    */      }
/* 168:168 */      if (locale.getCountry().length() > 0) {
/* 169:169 */        list.add(new Locale(locale.getLanguage(), ""));
/* 170:    */      }
/* 171:171 */      if (!list.contains(defaultLocale)) {
/* 172:172 */        list.add(defaultLocale);
/* 173:    */      }
/* 174:    */    }
/* 175:175 */    return Collections.unmodifiableList(list);
/* 176:    */  }
/* 177:    */  
/* 187:    */  public static List<Locale> availableLocaleList()
/* 188:    */  {
/* 189:189 */    return SyncAvoid.AVAILABLE_LOCALE_LIST;
/* 190:    */  }
/* 191:    */  
/* 201:    */  public static Set<Locale> availableLocaleSet()
/* 202:    */  {
/* 203:203 */    return SyncAvoid.AVAILABLE_LOCALE_SET;
/* 204:    */  }
/* 205:    */  
/* 212:    */  public static boolean isAvailableLocale(Locale locale)
/* 213:    */  {
/* 214:214 */    return availableLocaleList().contains(locale);
/* 215:    */  }
/* 216:    */  
/* 226:    */  public static List<Locale> languagesByCountry(String countryCode)
/* 227:    */  {
/* 228:228 */    if (countryCode == null) {
/* 229:229 */      return Collections.emptyList();
/* 230:    */    }
/* 231:231 */    List<Locale> langs = (List)cLanguagesByCountry.get(countryCode);
/* 232:232 */    if (langs == null) {
/* 233:233 */      langs = new ArrayList();
/* 234:234 */      List<Locale> locales = availableLocaleList();
/* 235:235 */      for (int i = 0; i < locales.size(); i++) {
/* 236:236 */        Locale locale = (Locale)locales.get(i);
/* 237:237 */        if ((countryCode.equals(locale.getCountry())) && (locale.getVariant().length() == 0))
/* 238:    */        {
/* 239:239 */          langs.add(locale);
/* 240:    */        }
/* 241:    */      }
/* 242:242 */      langs = Collections.unmodifiableList(langs);
/* 243:243 */      cLanguagesByCountry.putIfAbsent(countryCode, langs);
/* 244:244 */      langs = (List)cLanguagesByCountry.get(countryCode);
/* 245:    */    }
/* 246:246 */    return langs;
/* 247:    */  }
/* 248:    */  
/* 258:    */  public static List<Locale> countriesByLanguage(String languageCode)
/* 259:    */  {
/* 260:260 */    if (languageCode == null) {
/* 261:261 */      return Collections.emptyList();
/* 262:    */    }
/* 263:263 */    List<Locale> countries = (List)cCountriesByLanguage.get(languageCode);
/* 264:264 */    if (countries == null) {
/* 265:265 */      countries = new ArrayList();
/* 266:266 */      List<Locale> locales = availableLocaleList();
/* 267:267 */      for (int i = 0; i < locales.size(); i++) {
/* 268:268 */        Locale locale = (Locale)locales.get(i);
/* 269:269 */        if ((languageCode.equals(locale.getLanguage())) && (locale.getCountry().length() != 0) && (locale.getVariant().length() == 0))
/* 270:    */        {
/* 272:272 */          countries.add(locale);
/* 273:    */        }
/* 274:    */      }
/* 275:275 */      countries = Collections.unmodifiableList(countries);
/* 276:276 */      cCountriesByLanguage.putIfAbsent(languageCode, countries);
/* 277:277 */      countries = (List)cCountriesByLanguage.get(languageCode);
/* 278:    */    }
/* 279:279 */    return countries;
/* 280:    */  }
/* 281:    */  
/* 289:    */  static class SyncAvoid
/* 290:    */  {
/* 291:291 */    static { List<Locale> list = new ArrayList(Arrays.asList(Locale.getAvailableLocales())); }
/* 292:292 */    private static List<Locale> AVAILABLE_LOCALE_LIST = Collections.unmodifiableList(list);
/* 293:293 */    private static Set<Locale> AVAILABLE_LOCALE_SET = Collections.unmodifiableSet(new HashSet(LocaleUtils.availableLocaleList()));
/* 294:    */  }
/* 295:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.LocaleUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */