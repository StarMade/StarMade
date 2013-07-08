package org.hsqldb.types;

import java.text.Collator;
import java.util.Locale;
import org.hsqldb.HsqlException;
import org.hsqldb.HsqlNameManager;
import org.hsqldb.HsqlNameManager.HsqlName;
import org.hsqldb.SchemaObject;
import org.hsqldb.Session;
import org.hsqldb.TypeInvariants;
import org.hsqldb.error.Error;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.Set;
import org.hsqldb.lib.StringUtil;
import org.hsqldb.lib.java.JavaSystem;
import org.hsqldb.rights.Grantee;

public class Collation
  implements SchemaObject
{
  static String defaultCollationName = "SQL_TEXT";
  public static final HashMap nameToJavaName = new HashMap(101);
  public static final HashMap dbNameToJavaName = new HashMap(101);
  public static final HashMap dbNameToCollation = new HashMap(11);
  static final Collation defaultCollation;
  final HsqlNameManager.HsqlName name;
  private Collator collator;
  private Locale locale;
  private boolean equalIsIdentical = true;
  private boolean isFinal;
  private boolean padSpace = true;
  private Charset charset;
  private HsqlNameManager.HsqlName sourceName;
  
  private Collation()
  {
    this.locale = Locale.ENGLISH;
    this.name = HsqlNameManager.newInfoSchemaObjectName(defaultCollationName, false, 15);
    this.isFinal = true;
  }
  
  private Collation(String paramString1, String paramString2, String paramString3)
  {
    this.locale = new Locale(paramString2, paramString3);
    this.collator = Collator.getInstance(this.locale);
    this.equalIsIdentical = false;
    this.name = HsqlNameManager.newInfoSchemaObjectName(paramString1, true, 15);
    this.charset = TypeInvariants.SQL_TEXT;
    this.isFinal = true;
  }
  
  public Collation(HsqlNameManager.HsqlName paramHsqlName, Collation paramCollation, Charset paramCharset, Boolean paramBoolean)
  {
    this.name = paramHsqlName;
    this.locale = paramCollation.locale;
    this.collator = paramCollation.collator;
    this.equalIsIdentical = paramCollation.equalIsIdentical;
    this.isFinal = true;
    this.charset = paramCharset;
    this.sourceName = paramCollation.name;
    this.padSpace = paramBoolean.booleanValue();
  }
  
  public static Collation getDefaultInstance()
  {
    return defaultCollation;
  }
  
  public static Collation newDatabaseInstance()
  {
    Collation localCollation = new Collation();
    localCollation.isFinal = false;
    return localCollation;
  }
  
  public static Iterator getCollationsIterator()
  {
    return nameToJavaName.keySet().iterator();
  }
  
  public static Iterator getLocalesIterator()
  {
    return nameToJavaName.values().iterator();
  }
  
  public static synchronized Collation getCollation(String paramString)
  {
    Collation localCollation = (Collation)dbNameToCollation.get(paramString);
    if (localCollation != null) {
      return localCollation;
    }
    if (defaultCollationName.equals(paramString))
    {
      dbNameToCollation.put(defaultCollationName, defaultCollation);
      return defaultCollation;
    }
    String str1 = (String)dbNameToJavaName.get(paramString);
    if (str1 == null)
    {
      str1 = (String)nameToJavaName.get(paramString);
      if (str1 == null) {
        throw Error.error(5501, str1);
      }
    }
    String[] arrayOfString = StringUtil.split(str1, "-");
    String str2 = arrayOfString[0];
    String str3 = arrayOfString.length == 2 ? arrayOfString[1] : "";
    localCollation = new Collation(paramString, str2, str3);
    dbNameToCollation.put(paramString, localCollation);
    return localCollation;
  }
  
  public void setPadding(boolean paramBoolean)
  {
    if (this.isFinal) {
      throw Error.error(5503);
    }
    this.padSpace = paramBoolean;
  }
  
  public void setCollationAsLocale()
  {
    Locale localLocale = Locale.getDefault();
    String str = localLocale.getDisplayLanguage(Locale.ENGLISH);
    try
    {
      setCollation(str, Boolean.valueOf(false));
    }
    catch (HsqlException localHsqlException) {}
  }
  
  public void setCollation(String paramString, Boolean paramBoolean)
  {
    if (this.isFinal) {
      throw Error.error(5503, paramString);
    }
    if (defaultCollationName.equals(paramString))
    {
      this.locale = Locale.ENGLISH;
      this.name.rename(defaultCollationName, false);
      this.collator = null;
      this.equalIsIdentical = true;
    }
    else
    {
      String str1 = (String)nameToJavaName.get(paramString);
      if (str1 == null) {
        str1 = (String)dbNameToJavaName.get(paramString);
      }
      if (str1 == null) {
        throw Error.error(5501, paramString);
      }
      this.name.rename(paramString, true);
      String[] arrayOfString = StringUtil.split(str1, "-");
      String str2 = arrayOfString[0];
      String str3 = arrayOfString.length == 2 ? arrayOfString[1] : "";
      this.locale = new Locale(str2, str3);
      this.collator = Collator.getInstance(this.locale);
      this.equalIsIdentical = false;
    }
    this.padSpace = paramBoolean.booleanValue();
  }
  
  public boolean isPadSpace()
  {
    return this.padSpace;
  }
  
  public boolean isEqualAlwaysIdentical()
  {
    return this.collator == null;
  }
  
  public int compare(String paramString1, String paramString2)
  {
    int i;
    if (this.collator == null) {
      i = paramString1.compareTo(paramString2);
    } else {
      i = this.collator.compare(paramString1, paramString2);
    }
    return i < 0 ? -1 : i == 0 ? 0 : 1;
  }
  
  public int compareIgnoreCase(String paramString1, String paramString2)
  {
    int i;
    if (this.collator == null) {
      i = JavaSystem.compareIngnoreCase(paramString1, paramString2);
    } else {
      i = this.collator.compare(toUpperCase(paramString1), toUpperCase(paramString2));
    }
    return i < 0 ? -1 : i == 0 ? 0 : 1;
  }
  
  public String toUpperCase(String paramString)
  {
    return paramString.toUpperCase(this.locale);
  }
  
  public String toLowerCase(String paramString)
  {
    return paramString.toLowerCase(this.locale);
  }
  
  public boolean isDefaultCollation()
  {
    return (this.collator == null) && (this.padSpace);
  }
  
  public boolean isObjectCollation()
  {
    return (this.isFinal) && (this.collator != null);
  }
  
  public HsqlNameManager.HsqlName getName()
  {
    return this.name;
  }
  
  public int getType()
  {
    return 15;
  }
  
  public HsqlNameManager.HsqlName getSchemaName()
  {
    return this.name.schema;
  }
  
  public HsqlNameManager.HsqlName getCatalogName()
  {
    return this.name.schema.schema;
  }
  
  public Grantee getOwner()
  {
    return this.name.schema.owner;
  }
  
  public OrderedHashSet getReferences()
  {
    return new OrderedHashSet();
  }
  
  public OrderedHashSet getComponents()
  {
    return null;
  }
  
  public void compile(Session paramSession, SchemaObject paramSchemaObject) {}
  
  public String getSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("CREATE").append(' ');
    localStringBuffer.append("COLLATION").append(' ');
    localStringBuffer.append(this.name.getSchemaQualifiedStatementName()).append(' ');
    localStringBuffer.append("FOR").append(' ');
    localStringBuffer.append(this.charset.name.getSchemaQualifiedStatementName()).append(' ');
    localStringBuffer.append("FROM").append(' ');
    localStringBuffer.append(this.sourceName.statementName);
    localStringBuffer.append(' ');
    if (this.padSpace) {
      localStringBuffer.append("PAD").append(' ').append("SPACE");
    } else {
      localStringBuffer.append("NO").append(' ').append("PAD");
    }
    return localStringBuffer.toString();
  }
  
  public long getChangeTimestamp()
  {
    return 0L;
  }
  
  public String getDatabaseCollationSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("SET").append(' ');
    localStringBuffer.append("DATABASE").append(' ');
    localStringBuffer.append("COLLATION").append(' ');
    localStringBuffer.append(getName().statementName);
    localStringBuffer.append(' ');
    if (this.padSpace) {
      localStringBuffer.append("PAD").append(' ').append("SPACE");
    } else {
      localStringBuffer.append("NO").append(' ').append("PAD");
    }
    return localStringBuffer.toString();
  }
  
  static
  {
    nameToJavaName.put("Afrikaans", "af-ZA");
    nameToJavaName.put("Amharic", "am-ET");
    nameToJavaName.put("Arabic", "ar");
    nameToJavaName.put("Assamese", "as-IN");
    nameToJavaName.put("Azerbaijani_Latin", "az-AZ");
    nameToJavaName.put("Azerbaijani_Cyrillic", "az-cyrillic");
    nameToJavaName.put("Belarusian", "be-BY");
    nameToJavaName.put("Bulgarian", "bg-BG");
    nameToJavaName.put("Bengali", "bn-IN");
    nameToJavaName.put("Tibetan", "bo-CN");
    nameToJavaName.put("Bosnian", "bs-BA");
    nameToJavaName.put("Catalan", "ca-ES");
    nameToJavaName.put("Czech", "cs-CZ");
    nameToJavaName.put("Welsh", "cy-GB");
    nameToJavaName.put("Danish", "da-DK");
    nameToJavaName.put("German", "de-DE");
    nameToJavaName.put("Greek", "el-GR");
    nameToJavaName.put("Latin1_General", "en-US");
    nameToJavaName.put("English", "en-US");
    nameToJavaName.put("Spanish", "es-ES");
    nameToJavaName.put("Estonian", "et-EE");
    nameToJavaName.put("Basque", "eu");
    nameToJavaName.put("Finnish", "fi-FI");
    nameToJavaName.put("French", "fr-FR");
    nameToJavaName.put("Guarani", "gn-PY");
    nameToJavaName.put("Gujarati", "gu-IN");
    nameToJavaName.put("Hausa", "ha-NG");
    nameToJavaName.put("Hebrew", "he-IL");
    nameToJavaName.put("Hindi", "hi-IN");
    nameToJavaName.put("Croatian", "hr-HR");
    nameToJavaName.put("Hungarian", "hu-HU");
    nameToJavaName.put("Armenian", "hy-AM");
    nameToJavaName.put("Indonesian", "id-ID");
    nameToJavaName.put("Igbo", "ig-NG");
    nameToJavaName.put("Icelandic", "is-IS");
    nameToJavaName.put("Italian", "it-IT");
    nameToJavaName.put("Inuktitut", "iu-CA");
    nameToJavaName.put("Japanese", "ja-JP");
    nameToJavaName.put("Georgian", "ka-GE");
    nameToJavaName.put("Kazakh", "kk-KZ");
    nameToJavaName.put("Khmer", "km-KH");
    nameToJavaName.put("Kannada", "kn-IN");
    nameToJavaName.put("Korean", "ko-KR");
    nameToJavaName.put("Konkani", "kok-IN");
    nameToJavaName.put("Kashmiri", "ks");
    nameToJavaName.put("Kirghiz", "ky-KG");
    nameToJavaName.put("Lao", "lo-LA");
    nameToJavaName.put("Lithuanian", "lt-LT");
    nameToJavaName.put("Latvian", "lv-LV");
    nameToJavaName.put("Maori", "mi-NZ");
    nameToJavaName.put("Macedonian", "mk-MK");
    nameToJavaName.put("Malayalam", "ml-IN");
    nameToJavaName.put("Mongolian", "mn-MN");
    nameToJavaName.put("Manipuri", "mni-IN");
    nameToJavaName.put("Marathi", "mr-IN");
    nameToJavaName.put("Malay", "ms-MY");
    nameToJavaName.put("Maltese", "mt-MT");
    nameToJavaName.put("Burmese", "my-MM");
    nameToJavaName.put("Danish_Norwegian", "nb-NO");
    nameToJavaName.put("Nepali", "ne-NP");
    nameToJavaName.put("Dutch", "nl-NL");
    nameToJavaName.put("Norwegian", "nn-NO");
    nameToJavaName.put("Oriya", "or-IN");
    nameToJavaName.put("Punjabi", "pa-IN");
    nameToJavaName.put("Polish", "pl-PL");
    nameToJavaName.put("Pashto", "ps-AF");
    nameToJavaName.put("Portuguese", "pt-PT");
    nameToJavaName.put("Romanian", "ro-RO");
    nameToJavaName.put("Russian", "ru-RU");
    nameToJavaName.put("Sanskrit", "sa-IN");
    nameToJavaName.put("Sindhi", "sd-IN");
    nameToJavaName.put("Slovak", "sk-SK");
    nameToJavaName.put("Slovenian", "sl-SI");
    nameToJavaName.put("Somali", "so-SO");
    nameToJavaName.put("Albanian", "sq-AL");
    nameToJavaName.put("Serbian_Cyrillic", "sr-YU");
    nameToJavaName.put("Serbian_Latin", "sh-BA");
    nameToJavaName.put("Swedish", "sv-SE");
    nameToJavaName.put("Swahili", "sw-KE");
    nameToJavaName.put("Tamil", "ta-IN");
    nameToJavaName.put("Telugu", "te-IN");
    nameToJavaName.put("Tajik", "tg-TJ");
    nameToJavaName.put("Thai", "th-TH");
    nameToJavaName.put("Turkmen", "tk-TM");
    nameToJavaName.put("Tswana", "tn-BW");
    nameToJavaName.put("Turkish", "tr-TR");
    nameToJavaName.put("Tatar", "tt-RU");
    nameToJavaName.put("Ukrainian", "uk-UA");
    nameToJavaName.put("Urdu", "ur-PK");
    nameToJavaName.put("Uzbek_Latin", "uz-UZ");
    nameToJavaName.put("Venda", "ven-ZA");
    nameToJavaName.put("Vietnamese", "vi-VN");
    nameToJavaName.put("Yoruba", "yo-NG");
    nameToJavaName.put("Chinese", "zh-CN");
    nameToJavaName.put("Zulu", "zu-ZA");
    Iterator localIterator = nameToJavaName.values().iterator();
    while (localIterator.hasNext())
    {
      String str1 = (String)localIterator.next();
      String str2 = str1.replace('-', '_').toUpperCase(Locale.ENGLISH);
      dbNameToJavaName.put(str2, str1);
    }
    defaultCollation = new Collation();
    defaultCollation.charset = TypeInvariants.SQL_TEXT;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.types.Collation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */