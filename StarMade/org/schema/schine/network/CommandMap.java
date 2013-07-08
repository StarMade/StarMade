/*   1:    */package org.schema.schine.network;
/*   2:    */
/*   3:    */import Ad;
/*   4:    */import java.io.File;
/*   5:    */import java.io.PrintStream;
/*   6:    */import java.util.Arrays;
/*   7:    */import java.util.Collection;
/*   8:    */import java.util.HashMap;
/*   9:    */import java.util.Iterator;
/*  10:    */import java.util.List;
/*  11:    */import k;
/*  12:    */
/*  64:    */public class CommandMap
/*  65:    */{
/*  66:    */  private HashMap classMap;
/*  67:    */  private HashMap idCommandMap;
/*  68:    */  
/*  69:    */  public static HashMap parseCommands(String paramString, HashMap paramHashMap)
/*  70:    */  {
/*  71: 71 */    HashMap localHashMap = new HashMap();
/*  72:    */    Object localObject;
/*  73:    */    try {
/*  74: 74 */      localHashMap = Ad.a.a(paramString);
/*  75: 75 */      paramHashMap.putAll(localHashMap);
/*  77:    */    }
/*  78:    */    catch (Exception localException)
/*  79:    */    {
/*  80: 80 */      System.err.println("Commmand Map Parsing failed: " + localException.getMessage());
/*  81: 81 */      System.err.println("[BACKUP]: retrieving commands from filesystem failed. trying jar... ");
/*  82:    */      
/*  83: 83 */      localObject = new File("./");
/*  84: 84 */      System.err.println(((File)localObject).getAbsolutePath());
/*  85: 85 */      System.err.println(Arrays.toString(((File)localObject).list()));
/*  86:    */    }
/*  87:    */    
/*  90: 90 */    if (localHashMap.isEmpty()) {
/*  91: 91 */      System.err.println("################# Loading from JAR #####################");
/*  92:    */      
/*  93: 93 */      System.err.println("########################################################");
/*  94:    */      
/*  95: 95 */      localObject = Ad.a.a(paramString);
/*  96:    */      try
/*  97:    */      {
/*  98: 98 */        for (localObject = ((List)localObject).iterator(); ((Iterator)localObject).hasNext();)
/*  99:    */        {
/* 103:103 */          if (((paramString = ((Class)((Iterator)localObject).next()).newInstance()) instanceof Command)) {
/* 104:104 */            paramString = (Command)paramString;
/* 105:    */            
/* 106:106 */            paramHashMap.put(paramString.getClass(), paramString);
/* 107:    */          } }
/* 108:    */      } catch (InstantiationException localInstantiationException) {
/* 109:109 */        
/* 110:    */        
/* 113:113 */          localInstantiationException;
/* 114:    */      } catch (IllegalAccessException localIllegalAccessException) {
/* 115:111 */        
/* 116:    */        
/* 117:113 */          localIllegalAccessException;
/* 118:    */      }
/* 119:    */    }
/* 120:    */    
/* 123:117 */    return paramHashMap;
/* 124:    */  }
/* 125:    */  
/* 136:    */  public CommandMap()
/* 137:    */  {
/* 138:132 */    this.classMap = new HashMap();
/* 139:133 */    this.idCommandMap = new HashMap();
/* 140:    */  }
/* 141:    */  
/* 142:    */  public void add(Command paramCommand)
/* 143:    */  {
/* 144:138 */    this.classMap.put(paramCommand.getClass(), paramCommand);
/* 145:    */  }
/* 146:    */  
/* 147:    */  public void addCommandId(Command paramCommand) {
/* 148:142 */    this.idCommandMap.put(Byte.valueOf(paramCommand.getId()), paramCommand);
/* 149:    */  }
/* 150:    */  
/* 151:    */  public void addCommandPath(String paramString) {
/* 152:146 */    parseCommands(paramString, this.classMap);
/* 153:    */  }
/* 154:    */  
/* 155:149 */  public Command getByClass(Class paramClass) { return (Command)this.classMap.get(paramClass); }
/* 156:    */  
/* 157:    */  public Command getById(byte paramByte) {
/* 158:152 */    assert (this.idCommandMap.containsKey(Byte.valueOf(paramByte))) : ("NOT FOUND " + paramByte + " in " + this.idCommandMap);
/* 159:153 */    return (Command)this.idCommandMap.get(Byte.valueOf(paramByte));
/* 160:    */  }
/* 161:    */  
/* 162:    */  public void getByString() {}
/* 163:    */  
/* 164:    */  public Collection values() {
/* 165:159 */    return this.classMap.values();
/* 166:    */  }
/* 167:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.CommandMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */