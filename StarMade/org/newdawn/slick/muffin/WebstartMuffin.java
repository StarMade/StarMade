/*   1:    */package org.newdawn.slick.muffin;
/*   2:    */
/*   3:    */import java.io.DataInputStream;
/*   4:    */import java.io.DataOutputStream;
/*   5:    */import java.io.EOFException;
/*   6:    */import java.io.IOException;
/*   7:    */import java.net.URL;
/*   8:    */import java.util.HashMap;
/*   9:    */import java.util.Iterator;
/*  10:    */import java.util.Set;
/*  11:    */import javax.jnlp.BasicService;
/*  12:    */import javax.jnlp.FileContents;
/*  13:    */import javax.jnlp.PersistenceService;
/*  14:    */import javax.jnlp.ServiceManager;
/*  15:    */import org.newdawn.slick.util.Log;
/*  16:    */
/*  27:    */public class WebstartMuffin
/*  28:    */  implements Muffin
/*  29:    */{
/*  30:    */  public void saveFile(HashMap scoreMap, String fileName)
/*  31:    */    throws IOException
/*  32:    */  {
/*  33:    */    PersistenceService ps;
/*  34:    */    URL configURL;
/*  35:    */    try
/*  36:    */    {
/*  37: 37 */      ps = (PersistenceService)ServiceManager.lookup("javax.jnlp.PersistenceService");
/*  38:    */      
/*  39: 39 */      BasicService bs = (BasicService)ServiceManager.lookup("javax.jnlp.BasicService");
/*  40:    */      
/*  41: 41 */      URL baseURL = bs.getCodeBase();
/*  42:    */      
/*  43: 43 */      configURL = new URL(baseURL, fileName);
/*  44:    */    } catch (Exception e) {
/*  45: 45 */      Log.error(e);
/*  46: 46 */      throw new IOException("Failed to save state: ");
/*  47:    */    }
/*  48:    */    try
/*  49:    */    {
/*  50: 50 */      ps.delete(configURL);
/*  51:    */    } catch (Exception e) {
/*  52: 52 */      Log.info("No exisiting Muffin Found - First Save");
/*  53:    */    }
/*  54:    */    try
/*  55:    */    {
/*  56: 56 */      ps.create(configURL, 1024L);
/*  57:    */      
/*  58: 58 */      FileContents fc = ps.get(configURL);
/*  59: 59 */      DataOutputStream oos = new DataOutputStream(fc.getOutputStream(false));
/*  60:    */      
/*  63: 63 */      Set keys = scoreMap.keySet();
/*  64:    */      
/*  66: 66 */      for (Iterator i = keys.iterator(); i.hasNext();) {
/*  67: 67 */        String key = (String)i.next();
/*  68:    */        
/*  69: 69 */        oos.writeUTF(key);
/*  70:    */        
/*  71: 71 */        if (fileName.endsWith("Number")) {
/*  72: 72 */          double value = ((Double)scoreMap.get(key)).doubleValue();
/*  73: 73 */          oos.writeDouble(value);
/*  74:    */        } else {
/*  75: 75 */          String value = (String)scoreMap.get(key);
/*  76: 76 */          oos.writeUTF(value);
/*  77:    */        }
/*  78:    */      }
/*  79:    */      
/*  80: 80 */      oos.flush();
/*  81: 81 */      oos.close();
/*  82:    */    } catch (Exception e) {
/*  83: 83 */      Log.error(e);
/*  84: 84 */      throw new IOException("Failed to store map of state data");
/*  85:    */    }
/*  86:    */  }
/*  87:    */  
/*  89:    */  public HashMap loadFile(String fileName)
/*  90:    */    throws IOException
/*  91:    */  {
/*  92: 92 */    HashMap hashMap = new HashMap();
/*  93:    */    try
/*  94:    */    {
/*  95: 95 */      PersistenceService ps = (PersistenceService)ServiceManager.lookup("javax.jnlp.PersistenceService");
/*  96:    */      
/*  97: 97 */      BasicService bs = (BasicService)ServiceManager.lookup("javax.jnlp.BasicService");
/*  98:    */      
/*  99: 99 */      URL baseURL = bs.getCodeBase();
/* 100:100 */      URL configURL = new URL(baseURL, fileName);
/* 101:101 */      FileContents fc = ps.get(configURL);
/* 102:102 */      DataInputStream ois = new DataInputStream(fc.getInputStream());
/* 103:    */      
/* 108:108 */      if (fileName.endsWith("Number"))
/* 109:    */      {
/* 110:    */        String key;
/* 111:111 */        while ((key = ois.readUTF()) != null) {
/* 112:112 */          double value = ois.readDouble();
/* 113:    */          
/* 114:114 */          hashMap.put(key, new Double(value));
/* 115:    */        }
/* 116:    */      }
/* 117:    */      else {
/* 118:    */        String key;
/* 119:119 */        while ((key = ois.readUTF()) != null) {
/* 120:120 */          String value = ois.readUTF();
/* 121:    */          
/* 122:122 */          hashMap.put(key, value);
/* 123:    */        }
/* 124:    */      }
/* 125:    */      
/* 126:126 */      ois.close();
/* 128:    */    }
/* 129:    */    catch (EOFException e) {}catch (IOException e) {}catch (Exception e)
/* 130:    */    {
/* 132:132 */      Log.error(e);
/* 133:133 */      throw new IOException("Failed to load state from webstart muffin");
/* 134:    */    }
/* 135:    */    
/* 136:136 */    return hashMap;
/* 137:    */  }
/* 138:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.muffin.WebstartMuffin
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */