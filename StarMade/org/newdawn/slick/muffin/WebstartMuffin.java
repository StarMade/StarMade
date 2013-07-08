package org.newdawn.slick.muffin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.jnlp.BasicService;
import javax.jnlp.FileContents;
import javax.jnlp.PersistenceService;
import javax.jnlp.ServiceManager;
import org.newdawn.slick.util.Log;

public class WebstartMuffin
  implements Muffin
{
  public void saveFile(HashMap scoreMap, String fileName)
    throws IOException
  {
    PersistenceService local_ps;
    URL configURL;
    try
    {
      local_ps = (PersistenceService)ServiceManager.lookup("javax.jnlp.PersistenceService");
      BasicService local_bs = (BasicService)ServiceManager.lookup("javax.jnlp.BasicService");
      URL baseURL = local_bs.getCodeBase();
      configURL = new URL(baseURL, fileName);
    }
    catch (Exception baseURL)
    {
      Log.error(baseURL);
      throw new IOException("Failed to save state: ");
    }
    try
    {
      local_ps.delete(configURL);
    }
    catch (Exception baseURL)
    {
      Log.info("No exisiting Muffin Found - First Save");
    }
    try
    {
      local_ps.create(configURL, 1024L);
      FileContents baseURL = local_ps.get(configURL);
      DataOutputStream oos = new DataOutputStream(baseURL.getOutputStream(false));
      Set keys = scoreMap.keySet();
      Iterator local_i = keys.iterator();
      while (local_i.hasNext())
      {
        String key = (String)local_i.next();
        oos.writeUTF(key);
        if (fileName.endsWith("Number"))
        {
          double value = ((Double)scoreMap.get(key)).doubleValue();
          oos.writeDouble(value);
        }
        else
        {
          String value = (String)scoreMap.get(key);
          oos.writeUTF(value);
        }
      }
      oos.flush();
      oos.close();
    }
    catch (Exception baseURL)
    {
      Log.error(baseURL);
      throw new IOException("Failed to store map of state data");
    }
  }
  
  public HashMap loadFile(String fileName)
    throws IOException
  {
    HashMap hashMap = new HashMap();
    try
    {
      PersistenceService local_ps = (PersistenceService)ServiceManager.lookup("javax.jnlp.PersistenceService");
      BasicService local_bs = (BasicService)ServiceManager.lookup("javax.jnlp.BasicService");
      URL baseURL = local_bs.getCodeBase();
      URL configURL = new URL(baseURL, fileName);
      FileContents local_fc = local_ps.get(configURL);
      DataInputStream ois = new DataInputStream(local_fc.getInputStream());
      if (fileName.endsWith("Number"))
      {
        String key;
        while ((key = ois.readUTF()) != null)
        {
          double value = ois.readDouble();
          hashMap.put(key, new Double(value));
        }
      }
      else
      {
        String key;
        while ((key = ois.readUTF()) != null)
        {
          String value = ois.readUTF();
          hashMap.put(key, value);
        }
      }
      ois.close();
    }
    catch (EOFException local_ps) {}catch (IOException local_ps) {}catch (Exception local_ps)
    {
      Log.error(local_ps);
      throw new IOException("Failed to load state from webstart muffin");
    }
    return hashMap;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.muffin.WebstartMuffin
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */