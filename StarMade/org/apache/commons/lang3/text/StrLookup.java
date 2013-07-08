/*   1:    */package org.apache.commons.lang3.text;
/*   2:    */
/*   3:    */import java.util.Map;
/*   4:    */
/*  46:    */public abstract class StrLookup<V>
/*  47:    */{
/*  48: 48 */  private static final StrLookup<String> NONE_LOOKUP = new MapStrLookup(null);
/*  49: 49 */  static { StrLookup<String> lookup = null;
/*  50:    */    try {
/*  51: 51 */      Map<?, ?> propMap = System.getProperties();
/*  52:    */      
/*  53: 53 */      Map<String, String> properties = propMap;
/*  54: 54 */      lookup = new MapStrLookup(properties);
/*  55:    */    } catch (SecurityException ex) {
/*  56: 56 */      lookup = NONE_LOOKUP; } }
/*  57:    */  
/*  58: 58 */  private static final StrLookup<String> SYSTEM_PROPERTIES_LOOKUP = lookup;
/*  59:    */  
/*  66:    */  public static StrLookup<?> noneLookup()
/*  67:    */  {
/*  68: 68 */    return NONE_LOOKUP;
/*  69:    */  }
/*  70:    */  
/*  81:    */  public static StrLookup<String> systemPropertiesLookup()
/*  82:    */  {
/*  83: 83 */    return SYSTEM_PROPERTIES_LOOKUP;
/*  84:    */  }
/*  85:    */  
/*  95:    */  public static <V> StrLookup<V> mapLookup(Map<String, V> map)
/*  96:    */  {
/*  97: 97 */    return new MapStrLookup(map);
/*  98:    */  }
/*  99:    */  
/* 113:    */  public abstract String lookup(String paramString);
/* 114:    */  
/* 128:    */  static class MapStrLookup<V>
/* 129:    */    extends StrLookup<V>
/* 130:    */  {
/* 131:    */    private final Map<String, V> map;
/* 132:    */    
/* 146:    */    MapStrLookup(Map<String, V> map)
/* 147:    */    {
/* 148:148 */      this.map = map;
/* 149:    */    }
/* 150:    */    
/* 160:    */    public String lookup(String key)
/* 161:    */    {
/* 162:162 */      if (this.map == null) {
/* 163:163 */        return null;
/* 164:    */      }
/* 165:165 */      Object obj = this.map.get(key);
/* 166:166 */      if (obj == null) {
/* 167:167 */        return null;
/* 168:    */      }
/* 169:169 */      return obj.toString();
/* 170:    */    }
/* 171:    */  }
/* 172:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.StrLookup
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */