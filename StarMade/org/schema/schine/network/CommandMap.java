package org.schema.schine.network;

import class_39;
import class_73;
import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class CommandMap
{
  private HashMap classMap = new HashMap();
  private HashMap idCommandMap = new HashMap();
  
  public static HashMap parseCommands(String paramString, HashMap paramHashMap)
  {
    HashMap localHashMap = new HashMap();
    Object localObject;
    try
    {
      localHashMap = class_73.field_134.a(paramString);
      paramHashMap.putAll(localHashMap);
    }
    catch (Exception localException)
    {
      System.err.println("Commmand Map Parsing failed: " + localException.getMessage());
      System.err.println("[BACKUP]: retrieving commands from filesystem failed. trying jar... ");
      localObject = new File("./");
      System.err.println(((File)localObject).getAbsolutePath());
      System.err.println(Arrays.toString(((File)localObject).list()));
    }
    if (localHashMap.isEmpty())
    {
      System.err.println("################# Loading from JAR #####################");
      System.err.println("########################################################");
      localObject = class_73.field_134.a1(paramString);
      try
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext()) {
          if (((paramString = ((Class)((Iterator)localObject).next()).newInstance()) instanceof Command))
          {
            paramString = (Command)paramString;
            paramHashMap.put(paramString.getClass(), paramString);
          }
        }
      }
      catch (InstantiationException localInstantiationException)
      {
        localInstantiationException;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        localIllegalAccessException;
      }
    }
    return paramHashMap;
  }
  
  public void add(Command paramCommand)
  {
    this.classMap.put(paramCommand.getClass(), paramCommand);
  }
  
  public void addCommandId(Command paramCommand)
  {
    this.idCommandMap.put(Byte.valueOf(paramCommand.getId()), paramCommand);
  }
  
  public void addCommandPath(String paramString)
  {
    parseCommands(paramString, this.classMap);
  }
  
  public Command getByClass(Class paramClass)
  {
    return (Command)this.classMap.get(paramClass);
  }
  
  public Command getById(byte paramByte)
  {
    assert (this.idCommandMap.containsKey(Byte.valueOf(paramByte))) : ("NOT FOUND " + paramByte + " in " + this.idCommandMap);
    return (Command)this.idCommandMap.get(Byte.valueOf(paramByte));
  }
  
  public void getByString() {}
  
  public Collection values()
  {
    return this.classMap.values();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.CommandMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */