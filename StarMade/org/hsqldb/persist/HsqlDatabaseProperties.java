package org.hsqldb.persist;

import java.util.Enumeration;
import java.util.Properties;
import org.hsqldb.Database;
import org.hsqldb.DatabaseURL;
import org.hsqldb.error.Error;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.FileAccess;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.Set;
import org.hsqldb.lib.StringUtil;

public class HsqlDatabaseProperties
  extends HsqlProperties
{
  private static final String hsqldb_method_class_names = "hsqldb.method_class_names";
  private static HashSet accessibleJavaMethodNames;
  private static boolean allowFullPath;
  public static final int SYSTEM_PROPERTY = 0;
  public static final int FILE_PROPERTY = 1;
  public static final int SQL_PROPERTY = 2;
  public static final int FILES_NOT_MODIFIED = 0;
  public static final int FILES_MODIFIED = 1;
  public static final int FILES_MODIFIED_NEW = 2;
  public static final int FILES_NEW = 3;
  private static final String MODIFIED_NO = "no";
  private static final String MODIFIED_YES = "yes";
  private static final String MODIFIED_YES_NEW = "yes-new-files";
  private static final String MODIFIED_NO_NEW = "no-new-files";
  private static final HashMap dbMeta;
  private static final HashMap textMeta;
  public static final String VERSION_STRING_1_8_0 = "1.8.0";
  public static final String THIS_VERSION = "2.2.9";
  public static final String THIS_FULL_VERSION = "2.2.9";
  public static final String THIS_CACHE_VERSION = "2.0.0";
  public static final String PRODUCT_NAME = "HSQL Database Engine";
  public static final int MAJOR = 2;
  public static final int MINOR = 2;
  public static final int REVISION = 9;
  public static final String system_lockfile_poll_retries_property = "hsqldb.lockfile_poll_retries";
  public static final String system_max_char_or_varchar_display_size = "hsqldb.max_char_or_varchar_display_size";
  public static final String hsqldb_inc_backup = "hsqldb.inc_backup";
  public static final String hsqldb_version = "version";
  public static final String hsqldb_readonly = "readonly";
  private static final String hsqldb_modified = "modified";
  public static final String hsqldb_cache_version = "hsqldb.cache_version";
  public static final String runtime_gc_interval = "runtime.gc_interval";
  public static final String url_ifexists = "ifexists";
  public static final String url_create = "create";
  public static final String url_default_schema = "default_schema";
  public static final String url_check_props = "check_props";
  public static final String url_get_column_name = "get_column_name";
  public static final String url_storage_class_name = "storage_class_name";
  public static final String url_fileaccess_class_name = "fileaccess_class_name";
  public static final String url_storage_key = "storage_key";
  public static final String url_shutdown = "shutdown";
  public static final String url_crypt_key = "crypt_key";
  public static final String url_crypt_type = "crypt_type";
  public static final String url_crypt_provider = "crypt_provider";
  public static final String url_crypt_lobs = "crypt_lobs";
  public static final String hsqldb_tx = "hsqldb.tx";
  public static final String hsqldb_tx_level = "hsqldb.tx_level";
  public static final String hsqldb_tx_conflict_rollback = "hsqldb.tx_conflict_rollback";
  public static final String hsqldb_applog = "hsqldb.applog";
  public static final String hsqldb_sqllog = "hsqldb.sqllog";
  public static final String hsqldb_lob_file_scale = "hsqldb.lob_file_scale";
  public static final String hsqldb_cache_file_scale = "hsqldb.cache_file_scale";
  public static final String hsqldb_cache_free_count = "hsqldb.cache_free_count";
  public static final String hsqldb_cache_rows = "hsqldb.cache_rows";
  public static final String hsqldb_cache_size = "hsqldb.cache_size";
  public static final String hsqldb_default_table_type = "hsqldb.default_table_type";
  public static final String hsqldb_defrag_limit = "hsqldb.defrag_limit";
  public static final String hsqldb_files_readonly = "files_readonly";
  public static final String hsqldb_lock_file = "hsqldb.lock_file";
  public static final String hsqldb_log_data = "hsqldb.log_data";
  public static final String hsqldb_log_size = "hsqldb.log_size";
  public static final String hsqldb_nio_data_file = "hsqldb.nio_data_file";
  public static final String hsqldb_nio_max_size = "hsqldb.nio_max_size";
  public static final String hsqldb_script_format = "hsqldb.script_format";
  public static final String hsqldb_temp_directory = "hsqldb.temp_directory";
  public static final String hsqldb_result_max_memory_rows = "hsqldb.result_max_memory_rows";
  public static final String hsqldb_write_delay = "hsqldb.write_delay";
  public static final String hsqldb_write_delay_millis = "hsqldb.write_delay_millis";
  public static final String hsqldb_full_log_replay = "hsqldb.full_log_replay";
  public static final String hsqldb_large_data = "hsqldb.large_data";
  public static final String sql_ref_integrity = "sql.ref_integrity";
  public static final String sql_compare_in_locale = "sql.compare_in_locale";
  public static final String sql_enforce_size = "sql.enforce_size";
  public static final String sql_enforce_strict_size = "sql.enforce_strict_size";
  public static final String sql_enforce_refs = "sql.enforce_refs";
  public static final String sql_enforce_names = "sql.enforce_names";
  public static final String sql_regular_names = "sql.regular_names";
  public static final String sql_enforce_types = "sql.enforce_types";
  public static final String sql_enforce_tdcd = "sql.enforce_tdc_delete";
  public static final String sql_enforce_tdcu = "sql.enforce_tdc_update";
  public static final String sql_concat_nulls = "sql.concat_nulls";
  public static final String sql_nulls_first = "sql.nulls_first";
  public static final String sql_unique_nulls = "sql.unique_nulls";
  public static final String sql_convert_trunc = "sql.convert_trunc";
  public static final String sql_avg_scale = "sql.avg_scale";
  public static final String sql_double_nan = "sql.double_nan";
  public static final String sql_syntax_db2 = "sql.syntax_db2";
  public static final String sql_syntax_mss = "sql.syntax_mss";
  public static final String sql_syntax_mys = "sql.syntax_mys";
  public static final String sql_syntax_ora = "sql.syntax_ora";
  public static final String sql_syntax_pgs = "sql.syntax_pgs";
  public static final String jdbc_translate_tti_types = "jdbc.translate_tti_types";
  public static final String sql_identity_is_pk = "sql.identity_is_pk";
  public static final String sql_longvar_is_lob = "sql.longvar_is_lob";
  public static final String sql_pad_space = "sql.pad_space";
  public static final String textdb_cache_scale = "textdb.cache_scale";
  public static final String textdb_cache_size_scale = "textdb.cache_size_scale";
  public static final String textdb_cache_rows = "textdb.cache_rows";
  public static final String textdb_cache_size = "textdb.cache_size";
  public static final String textdb_all_quoted = "textdb.all_quoted";
  public static final String textdb_allow_full_path = "textdb.allow_full_path";
  public static final String textdb_encoding = "textdb.encoding";
  public static final String textdb_ignore_first = "textdb.ignore_first";
  public static final String textdb_quoted = "textdb.quoted";
  public static final String textdb_fs = "textdb.fs";
  public static final String textdb_vs = "textdb.vs";
  public static final String textdb_lvs = "textdb.lvs";
  private Database database;
  public static final int NO_MESSAGE = 1;
  
  public static boolean supportsJavaMethod(String paramString)
  {
    if (accessibleJavaMethodNames == null) {
      return true;
    }
    if (paramString.startsWith("java.lang.Math.")) {
      return true;
    }
    if (accessibleJavaMethodNames.contains(paramString)) {
      return true;
    }
    Iterator localIterator = accessibleJavaMethodNames.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      int i = str.lastIndexOf(".*");
      if (i >= 1) {
        if (paramString.startsWith(str.substring(0, i + 1))) {
          return true;
        }
      }
    }
    return false;
  }
  
  public HsqlDatabaseProperties(Database paramDatabase)
  {
    super(dbMeta, paramDatabase.getPath(), paramDatabase.logger.getFileAccess(), paramDatabase.isFilesInJar());
    this.database = paramDatabase;
    setNewDatabaseProperties();
  }
  
  void setNewDatabaseProperties()
  {
    setProperty("version", "2.2.9");
    setProperty("modified", "no-new-files");
    if (this.database.logger.isStoredFileAccess())
    {
      setProperty("hsqldb.cache_rows", 25000);
      setProperty("hsqldb.cache_size", 6000);
      setProperty("hsqldb.log_size", 10);
      setProperty("sql.enforce_size", true);
      setProperty("hsqldb.nio_data_file", false);
      setProperty("hsqldb.lock_file", true);
      setProperty("hsqldb.default_table_type", "cached");
      setProperty("jdbc.translate_tti_types", true);
    }
  }
  
  public boolean load()
  {
    if (!DatabaseURL.isFileBasedDatabaseType(this.database.getType())) {
      return true;
    }
    boolean bool;
    try
    {
      bool = super.load();
    }
    catch (Throwable localThrowable)
    {
      throw Error.error(localThrowable, 452, 27, new Object[] { localThrowable.toString(), this.fileName });
    }
    if (!bool) {
      return false;
    }
    filterLoadedProperties();
    String str = getStringProperty("version");
    int i = str.substring(0, 5).compareTo("1.8.0");
    if (i < 0) {
      throw Error.error(453);
    }
    if ((i == 0) && (getIntegerProperty("hsqldb.script_format") != 0)) {
      throw Error.error(453);
    }
    i = str.substring(0, 2).compareTo("2.2.9");
    if (i > 0) {
      throw Error.error(453);
    }
    return true;
  }
  
  public void save()
  {
    if ((!DatabaseURL.isFileBasedDatabaseType(this.database.getType())) || (this.database.isFilesReadOnly()) || (this.database.isFilesInJar())) {
      return;
    }
    try
    {
      HsqlProperties localHsqlProperties = new HsqlProperties(dbMeta, this.database.getPath(), this.database.logger.getFileAccess(), false);
      if (getIntegerProperty("hsqldb.script_format") == 3) {
        localHsqlProperties.setProperty("hsqldb.script_format", 3);
      }
      localHsqlProperties.setProperty("version", "2.2.9");
      if ((this.database.logger.isStoredFileAccess()) && (!this.database.logger.isNewStoredFileAccess())) {}
      localHsqlProperties.setProperty("modified", getProperty("modified"));
      localHsqlProperties.save(this.fileName + ".properties" + ".new");
      this.field_1710.renameElement(this.fileName + ".properties" + ".new", this.fileName + ".properties");
    }
    catch (Throwable localThrowable)
    {
      this.database.logger.logSevereEvent("save failed", localThrowable);
      throw Error.error(localThrowable, 452, 27, new Object[] { localThrowable.toString(), this.fileName });
    }
  }
  
  void filterLoadedProperties()
  {
    String str1 = this.stringProps.getProperty("sql.enforce_strict_size");
    if (str1 != null) {
      this.stringProps.setProperty("sql.enforce_size", str1);
    }
    Enumeration localEnumeration = this.stringProps.propertyNames();
    while (localEnumeration.hasMoreElements())
    {
      String str2 = (String)localEnumeration.nextElement();
      boolean bool = dbMeta.containsKey(str2);
      if (!bool) {
        this.stringProps.remove(str2);
      }
    }
  }
  
  public void setURLProperties(HsqlProperties paramHsqlProperties)
  {
    boolean bool = false;
    if (paramHsqlProperties == null) {
      return;
    }
    String str1 = paramHsqlProperties.getProperty("sql.enforce_strict_size");
    if (str1 != null)
    {
      paramHsqlProperties.setProperty("sql.enforce_size", str1);
      paramHsqlProperties.removeProperty("sql.enforce_strict_size");
    }
    bool = paramHsqlProperties.isPropertyTrue("check_props", false);
    Enumeration localEnumeration = paramHsqlProperties.propertyNames();
    String str2;
    Object localObject;
    while (localEnumeration.hasMoreElements())
    {
      str2 = (String)localEnumeration.nextElement();
      localObject = paramHsqlProperties.getProperty(str2);
      int i = 0;
      int j = 0;
      String str3 = null;
      Object[] arrayOfObject = (Object[])dbMeta.get(str2);
      if ((arrayOfObject != null) && (((Integer)arrayOfObject[1]).intValue() == 2))
      {
        i = 1;
        str3 = HsqlProperties.validateProperty(str2, (String)localObject, arrayOfObject);
        j = str3 == null ? 1 : 0;
      }
      if ((str2.startsWith("sql.")) || (str2.startsWith("hsqldb.")) || (str2.startsWith("textdb.")))
      {
        if ((bool) && (i == 0)) {
          throw Error.error(5555, str2);
        }
        if ((bool) && (j == 0)) {
          throw Error.error(5556, str2);
        }
      }
    }
    localEnumeration = paramHsqlProperties.propertyNames();
    while (localEnumeration.hasMoreElements())
    {
      str2 = (String)localEnumeration.nextElement();
      localObject = (Object[])dbMeta.get(str2);
      if ((localObject != null) && (((Integer)localObject[1]).intValue() == 2)) {
        setDatabaseProperty(str2, paramHsqlProperties.getProperty(str2));
      }
    }
  }
  
  public Set getUserDefinedPropertyData()
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = dbMeta.values().iterator();
    while (localIterator.hasNext())
    {
      Object[] arrayOfObject = (Object[])localIterator.next();
      if (((Integer)arrayOfObject[1]).intValue() == 2) {
        localHashSet.add(arrayOfObject);
      }
    }
    return localHashSet;
  }
  
  public boolean isUserDefinedProperty(String paramString)
  {
    Object[] arrayOfObject = (Object[])dbMeta.get(paramString);
    return (arrayOfObject != null) && (((Integer)arrayOfObject[1]).intValue() == 2);
  }
  
  public boolean isBoolean(String paramString)
  {
    Object[] arrayOfObject = (Object[])dbMeta.get(paramString);
    return (arrayOfObject != null) && (arrayOfObject[2].equals("Boolean")) && (((Integer)arrayOfObject[1]).intValue() == 2);
  }
  
  public boolean isIntegral(String paramString)
  {
    Object[] arrayOfObject = (Object[])dbMeta.get(paramString);
    return (arrayOfObject != null) && (arrayOfObject[2].equals("Integer")) && (((Integer)arrayOfObject[1]).intValue() == 2);
  }
  
  public boolean isString(String paramString)
  {
    Object[] arrayOfObject = (Object[])dbMeta.get(paramString);
    return (arrayOfObject != null) && (arrayOfObject[2].equals("String")) && (((Integer)arrayOfObject[1]).intValue() == 2);
  }
  
  public boolean setDatabaseProperty(String paramString1, String paramString2)
  {
    Object[] arrayOfObject = (Object[])dbMeta.get(paramString1);
    String str = HsqlProperties.validateProperty(paramString1, paramString2, arrayOfObject);
    if (str != null) {
      return false;
    }
    this.stringProps.put(paramString1, paramString2);
    return true;
  }
  
  public int getDefaultWriteDelay()
  {
    if (this.database.logger.isStoredFileAccess()) {
      return 2000;
    }
    return 500;
  }
  
  public int getErrorLevel()
  {
    return 1;
  }
  
  public boolean divisionByZero()
  {
    return false;
  }
  
  public void setDBModified(int paramInt)
  {
    String str;
    switch (paramInt)
    {
    case 0: 
      str = "no";
      break;
    case 1: 
      str = "yes";
      break;
    case 2: 
      str = "yes-new-files";
      break;
    default: 
      throw Error.runtimeError(201, "HsqlDatabaseProperties");
    }
    this.stringProps.put("modified", str);
    save();
  }
  
  public int getDBModified()
  {
    String str = getStringProperty("modified");
    if ("yes".equals(str)) {
      return 1;
    }
    if ("yes-new-files".equals(str)) {
      return 2;
    }
    if ("no-new-files".equals(str)) {
      return 3;
    }
    return 0;
  }
  
  public String getProperty(String paramString)
  {
    Object[] arrayOfObject = (Object[])dbMeta.get(paramString);
    if (arrayOfObject == null) {
      throw Error.error(5555, paramString);
    }
    return this.stringProps.getProperty(paramString);
  }
  
  public String getPropertyString(String paramString)
  {
    Object[] arrayOfObject = (Object[])dbMeta.get(paramString);
    if (arrayOfObject == null) {
      throw Error.error(5555, paramString);
    }
    String str = this.stringProps.getProperty(paramString);
    int i = ((Integer)arrayOfObject[1]).intValue() == 0 ? 1 : 0;
    if ((str == null) && (i != 0)) {
      try
      {
        str = System.getProperty(paramString);
      }
      catch (SecurityException localSecurityException) {}
    }
    if (str == null)
    {
      Object localObject = arrayOfObject[4];
      if (localObject == null) {
        return null;
      }
      return String.valueOf(localObject);
    }
    return str;
  }
  
  public boolean isPropertyTrue(String paramString)
  {
    Object[] arrayOfObject = (Object[])dbMeta.get(paramString);
    if (arrayOfObject == null) {
      throw Error.error(5555, paramString);
    }
    Boolean localBoolean = (Boolean)arrayOfObject[4];
    String str = null;
    int i = ((Integer)arrayOfObject[1]).intValue() == 0 ? 1 : 0;
    if (i != 0) {
      try
      {
        str = System.getProperty(paramString);
      }
      catch (SecurityException localSecurityException) {}
    } else {
      str = this.stringProps.getProperty(paramString);
    }
    if (str != null) {
      localBoolean = Boolean.valueOf(str);
    }
    return localBoolean.booleanValue();
  }
  
  public String getStringProperty(String paramString)
  {
    Object[] arrayOfObject = (Object[])dbMeta.get(paramString);
    if (arrayOfObject == null) {
      throw Error.error(5555, paramString);
    }
    Object localObject = (String)arrayOfObject[4];
    String str = this.stringProps.getProperty(paramString);
    if (str != null) {
      localObject = str;
    }
    return localObject;
  }
  
  public int getIntegerProperty(String paramString)
  {
    Object[] arrayOfObject = (Object[])dbMeta.get(paramString);
    if (arrayOfObject == null) {
      throw Error.error(5555, paramString);
    }
    int i = ((Integer)arrayOfObject[4]).intValue();
    String str = this.stringProps.getProperty(paramString);
    if (str != null) {
      try
      {
        i = Integer.parseInt(str);
      }
      catch (NumberFormatException localNumberFormatException) {}
    }
    return i;
  }
  
  public static Iterator getPropertiesMetaIterator()
  {
    return dbMeta.values().iterator();
  }
  
  public String getClientPropertiesAsString()
  {
    if (isPropertyTrue("jdbc.translate_tti_types"))
    {
      StringBuffer localStringBuffer = new StringBuffer("jdbc.translate_tti_types");
      localStringBuffer.append('=').append(true);
      return localStringBuffer.toString();
    }
    return "";
  }
  
  public boolean isVersion18()
  {
    String str = getProperty("hsqldb.cache_version", "2.2.9");
    return str.substring(0, 4).equals("1.7.");
  }
  
  static
  {
    try
    {
      String str = System.getProperty("hsqldb.method_class_names");
      if (str != null)
      {
        accessibleJavaMethodNames = new HashSet();
        String[] arrayOfString = StringUtil.split(str, ";");
        for (int i = 0; i < arrayOfString.length; i++) {
          accessibleJavaMethodNames.add(arrayOfString[i]);
        }
      }
      str = System.getProperty("hsqldb.method_class_names");
      if ((str != null) && (Boolean.valueOf(str).booleanValue())) {
        allowFullPath = true;
      }
    }
    catch (Exception localException) {}
    dbMeta = new HashMap(67);
    textMeta = new HashMap(17);
    textMeta.put("textdb.allow_full_path", HsqlProperties.getMeta("textdb.allow_full_path", 0, allowFullPath));
    textMeta.put("textdb.quoted", HsqlProperties.getMeta("textdb.quoted", 2, true));
    textMeta.put("textdb.all_quoted", HsqlProperties.getMeta("textdb.all_quoted", 2, false));
    textMeta.put("textdb.ignore_first", HsqlProperties.getMeta("textdb.ignore_first", 2, false));
    textMeta.put("textdb.fs", HsqlProperties.getMeta("textdb.fs", 2, ","));
    textMeta.put("textdb.vs", HsqlProperties.getMeta("textdb.vs", 2, null));
    textMeta.put("textdb.lvs", HsqlProperties.getMeta("textdb.lvs", 2, null));
    textMeta.put("textdb.encoding", HsqlProperties.getMeta("textdb.encoding", 2, "ISO-8859-1"));
    textMeta.put("textdb.cache_scale", HsqlProperties.getMeta("textdb.cache_scale", 2, 10, 8, 16));
    textMeta.put("textdb.cache_size_scale", HsqlProperties.getMeta("textdb.cache_size_scale", 2, 10, 6, 20));
    textMeta.put("textdb.cache_rows", HsqlProperties.getMeta("textdb.cache_rows", 2, 1000, 100, 1000000));
    textMeta.put("textdb.cache_size", HsqlProperties.getMeta("textdb.cache_size", 2, 100, 10, 1000000));
    dbMeta.putAll(textMeta);
    dbMeta.put("version", HsqlProperties.getMeta("version", 1, null));
    dbMeta.put("modified", HsqlProperties.getMeta("modified", 1, null));
    dbMeta.put("hsqldb.cache_version", HsqlProperties.getMeta("hsqldb.cache_version", 1, null));
    dbMeta.put("readonly", HsqlProperties.getMeta("readonly", 1, false));
    dbMeta.put("files_readonly", HsqlProperties.getMeta("files_readonly", 1, false));
    dbMeta.put("hsqldb.tx", HsqlProperties.getMeta("hsqldb.tx", 2, "LOCKS"));
    dbMeta.put("hsqldb.tx_level", HsqlProperties.getMeta("hsqldb.tx_level", 2, "READ_COMMITTED"));
    dbMeta.put("hsqldb.temp_directory", HsqlProperties.getMeta("hsqldb.temp_directory", 2, null));
    dbMeta.put("hsqldb.default_table_type", HsqlProperties.getMeta("hsqldb.default_table_type", 2, "MEMORY"));
    dbMeta.put("hsqldb.tx_conflict_rollback", HsqlProperties.getMeta("hsqldb.tx_conflict_rollback", 2, true));
    dbMeta.put("jdbc.translate_tti_types", HsqlProperties.getMeta("jdbc.translate_tti_types", 2, true));
    dbMeta.put("hsqldb.inc_backup", HsqlProperties.getMeta("hsqldb.inc_backup", 2, true));
    dbMeta.put("hsqldb.lock_file", HsqlProperties.getMeta("hsqldb.lock_file", 2, true));
    dbMeta.put("hsqldb.log_data", HsqlProperties.getMeta("hsqldb.log_data", 2, true));
    dbMeta.put("hsqldb.nio_data_file", HsqlProperties.getMeta("hsqldb.nio_data_file", 2, true));
    dbMeta.put("hsqldb.full_log_replay", HsqlProperties.getMeta("hsqldb.full_log_replay", 2, false));
    dbMeta.put("sql.ref_integrity", HsqlProperties.getMeta("sql.ref_integrity", 2, true));
    dbMeta.put("sql.enforce_names", HsqlProperties.getMeta("sql.enforce_names", 2, false));
    dbMeta.put("sql.regular_names", HsqlProperties.getMeta("sql.regular_names", 2, true));
    dbMeta.put("sql.enforce_refs", HsqlProperties.getMeta("sql.enforce_refs", 2, false));
    dbMeta.put("sql.enforce_size", HsqlProperties.getMeta("sql.enforce_size", 2, true));
    dbMeta.put("sql.enforce_types", HsqlProperties.getMeta("sql.enforce_types", 2, false));
    dbMeta.put("sql.enforce_tdc_delete", HsqlProperties.getMeta("sql.enforce_tdc_delete", 2, true));
    dbMeta.put("sql.enforce_tdc_update", HsqlProperties.getMeta("sql.enforce_tdc_update", 2, true));
    dbMeta.put("sql.concat_nulls", HsqlProperties.getMeta("sql.concat_nulls", 2, true));
    dbMeta.put("sql.nulls_first", HsqlProperties.getMeta("sql.nulls_first", 2, true));
    dbMeta.put("sql.unique_nulls", HsqlProperties.getMeta("sql.unique_nulls", 2, true));
    dbMeta.put("sql.convert_trunc", HsqlProperties.getMeta("sql.convert_trunc", 2, true));
    dbMeta.put("sql.avg_scale", HsqlProperties.getMeta("sql.avg_scale", 2, 0, 0, 10));
    dbMeta.put("sql.double_nan", HsqlProperties.getMeta("sql.double_nan", 2, true));
    dbMeta.put("sql.syntax_db2", HsqlProperties.getMeta("sql.syntax_db2", 2, false));
    dbMeta.put("sql.syntax_mss", HsqlProperties.getMeta("sql.syntax_mss", 2, false));
    dbMeta.put("sql.syntax_mys", HsqlProperties.getMeta("sql.syntax_mys", 2, false));
    dbMeta.put("sql.syntax_ora", HsqlProperties.getMeta("sql.syntax_ora", 2, false));
    dbMeta.put("sql.syntax_pgs", HsqlProperties.getMeta("sql.syntax_pgs", 2, false));
    dbMeta.put("sql.compare_in_locale", HsqlProperties.getMeta("sql.compare_in_locale", 2, false));
    dbMeta.put("sql.identity_is_pk", HsqlProperties.getMeta("sql.identity_is_pk", 2, false));
    dbMeta.put("sql.longvar_is_lob", HsqlProperties.getMeta("sql.longvar_is_lob", 2, false));
    dbMeta.put("hsqldb.write_delay", HsqlProperties.getMeta("hsqldb.write_delay", 2, true));
    dbMeta.put("hsqldb.write_delay_millis", HsqlProperties.getMeta("hsqldb.write_delay_millis", 2, 500, 0, 10000));
    dbMeta.put("hsqldb.applog", HsqlProperties.getMeta("hsqldb.applog", 2, 0, 0, 3));
    dbMeta.put("hsqldb.sqllog", HsqlProperties.getMeta("hsqldb.sqllog", 2, 0, 0, 3));
    dbMeta.put("hsqldb.script_format", HsqlProperties.getMeta("hsqldb.script_format", 2, 0, new int[] { 0, 1, 3 }));
    dbMeta.put("hsqldb.lob_file_scale", HsqlProperties.getMeta("hsqldb.lob_file_scale", 2, 32, new int[] { 1, 2, 4, 8, 16, 32 }));
    dbMeta.put("hsqldb.cache_file_scale", HsqlProperties.getMeta("hsqldb.cache_file_scale", 2, 32, new int[] { 1, 8, 16, 32, 64, 128, 256, 512, 1024 }));
    dbMeta.put("hsqldb.log_size", HsqlProperties.getMeta("hsqldb.log_size", 2, 50, 0, 4096));
    dbMeta.put("hsqldb.defrag_limit", HsqlProperties.getMeta("hsqldb.defrag_limit", 2, 0, 0, 100));
    dbMeta.put("runtime.gc_interval", HsqlProperties.getMeta("runtime.gc_interval", 2, 0, 0, 1000000));
    dbMeta.put("hsqldb.cache_size", HsqlProperties.getMeta("hsqldb.cache_size", 2, 10000, 100, 4194304));
    dbMeta.put("hsqldb.cache_rows", HsqlProperties.getMeta("hsqldb.cache_rows", 2, 50000, 100, 4194304));
    dbMeta.put("hsqldb.cache_free_count", HsqlProperties.getMeta("hsqldb.cache_free_count", 2, 512, 0, 4096));
    dbMeta.put("hsqldb.result_max_memory_rows", HsqlProperties.getMeta("hsqldb.result_max_memory_rows", 2, 0, 0, 4194304));
    dbMeta.put("hsqldb.nio_max_size", HsqlProperties.getMeta("hsqldb.nio_max_size", 2, 256, 64, 262144));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.persist.HsqlDatabaseProperties
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */