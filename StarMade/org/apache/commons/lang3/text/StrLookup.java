package org.apache.commons.lang3.text;

import java.util.Map;

public abstract class StrLookup<V>
{
  private static final StrLookup<String> NONE_LOOKUP = new MapStrLookup(null);
  private static final StrLookup<String> SYSTEM_PROPERTIES_LOOKUP = lookup;
  
  public static StrLookup<?> noneLookup()
  {
    return NONE_LOOKUP;
  }
  
  public static StrLookup<String> systemPropertiesLookup()
  {
    return SYSTEM_PROPERTIES_LOOKUP;
  }
  
  public static <V> StrLookup<V> mapLookup(Map<String, V> map)
  {
    return new MapStrLookup(map);
  }
  
  public abstract String lookup(String paramString);
  
  static
  {
    StrLookup<String> lookup = null;
    try
    {
      Map<?, ?> propMap = System.getProperties();
      Map<String, String> properties = propMap;
      lookup = new MapStrLookup(properties);
    }
    catch (SecurityException propMap)
    {
      lookup = NONE_LOOKUP;
    }
  }
  
  static class MapStrLookup<V>
    extends StrLookup<V>
  {
    private final Map<String, V> map;
    
    MapStrLookup(Map<String, V> map)
    {
      this.map = map;
    }
    
    public String lookup(String key)
    {
      if (this.map == null) {
        return null;
      }
      Object obj = this.map.get(key);
      if (obj == null) {
        return null;
      }
      return obj.toString();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.text.StrLookup
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */