import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.schema.schine.graphicsengine.core.ResourceException;
import org.schema.schine.network.Command;
import org.schema.schine.network.CommandMap;

public final class class_39
{
  public final HashMap a(String paramString)
  {
    HashMap localHashMap = new HashMap();
    if (!(localObject = new String(paramString)).startsWith("/")) {
      localObject = "/" + (String)localObject;
    }
    Object localObject = ((String)localObject).replace('.', '/');
    try
    {
      localObject = a3((String)localObject);
      if (!(localObject = new File(((URL)localObject).getFile())).exists()) {
        throw new ResourceException("ERROR: no content in Resource: " + ((File)localObject).getAbsolutePath());
      }
      File[] arrayOfFile;
      int i = (arrayOfFile = ((File)localObject).listFiles()).length;
      for (int j = 0; j < i; j++) {
        if ((localObject = arrayOfFile[j].getName()).endsWith(".class"))
        {
          localObject = ((String)localObject).substring(0, ((String)localObject).length() - 6);
          try
          {
            if (((localObject = Class.forName(paramString + "." + (String)localObject).newInstance()) instanceof Command))
            {
              localObject = (Command)localObject;
              localHashMap.put(localObject.getClass(), localObject);
            }
          }
          catch (ClassNotFoundException localClassNotFoundException)
          {
            System.err.println(localClassNotFoundException);
          }
          catch (InstantiationException localInstantiationException) {}catch (IllegalAccessException localIllegalAccessException) {}
        }
      }
    }
    catch (ResourceException localResourceException)
    {
      System.err.println(localResourceException.getMessage());
    }
    return localHashMap;
  }
  
  public final List a1(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    paramString = paramString.replaceAll("\\.", "/");
    try
    {
      localObject1 = a3(paramString);
      JarFile localJarFile = null;
      Object localObject2;
      if (((localObject1 = ((URL)localObject1).openConnection()) instanceof JarURLConnection))
      {
        localObject2 = null;
        localJarFile = ((JarURLConnection)localObject1).getJarFile();
      }
      else
      {
        System.setSecurityManager(null);
        localObject2 = Class.forName("com.sun.jnlp.JNLPCachedJarURLConnection");
        try
        {
          for (Object localObject5 : ((Class)localObject2).getFields()) {
            System.err.println(localObject5);
          }
          for (??? : ((Class)localObject2).getMethods()) {
            System.err.println(???);
          }
          (??? = ((Class)localObject2).getDeclaredMethod("getJarFile", new Class[0])).setAccessible(true);
          localJarFile = (JarFile)((Method)???).invoke(((Class)localObject2).cast(localObject1), new Object[0]);
        }
        catch (Throwable localThrowable)
        {
          localThrowable;
        }
      }
      ??? = localJarFile.entries();
      while ((((Enumeration)???).hasMoreElements()) && ((localObject2 = (JarEntry)((Enumeration)???).nextElement()) != null)) {
        if ((((JarEntry)localObject2).getName().startsWith(paramString)) && (((JarEntry)localObject2).getName().endsWith(".class")))
        {
          ??? = Thread.currentThread().getContextClassLoader().loadClass(((JarEntry)localObject2).getName().replaceAll("/", "\\.").replaceAll("\\.class", ""));
          localArrayList.add(???);
        }
      }
    }
    catch (Exception localException)
    {
      Object localObject1 = null;
      localException.printStackTrace();
    }
    return localArrayList;
  }
  
  public final List b(String paramString)
  {
    localArrayList = new ArrayList();
    paramString = paramString.replaceAll("\\.", "/");
    try
    {
      Object localObject1 = null;
      Object localObject2;
      if (((localObject1 = a3(paramString).openConnection()) instanceof JarURLConnection))
      {
        localObject2 = ((JarURLConnection)localObject1).getJarFile().entries();
        while ((((Enumeration)localObject2).hasMoreElements()) && ((localObject1 = (JarEntry)((Enumeration)localObject2).nextElement()) != null)) {
          if (((JarEntry)localObject1).getName().startsWith(paramString)) {
            localArrayList.add(((JarEntry)localObject1).getName());
          }
        }
      }
      else
      {
        localObject2 = Class.forName("com.sun.jnlp.JNLPCachedJarURLConnection");
        try
        {
          localObject1 = (JarFile)((Class)localObject2).getDeclaredMethod("getJarFile", new Class[0]).invoke(localObject1, new Object[0]);
          System.err.println(localObject1);
        }
        catch (Throwable localThrowable) {}
      }
      return localArrayList;
    }
    catch (Exception localException)
    {
      localObject1 = null;
      localException.printStackTrace();
    }
  }
  
  public final InputStream a2(String paramString)
  {
    for (paramString = paramString.replaceAll("\\\\", "/").replaceAll("\\./", ""); paramString.contains("//"); paramString = paramString.replaceAll("//", "/")) {}
    Object localObject;
    if ((localObject = getClass().getClassLoader().getResourceAsStream(paramString)) == null) {
      localObject = CommandMap.class.getResourceAsStream(paramString);
    }
    if (localObject == null) {
      localObject = ClassLoader.getSystemResourceAsStream(paramString);
    }
    File localFile;
    if ((localObject == null) && ((localFile = new File(paramString)).exists())) {
      try
      {
        localObject = new FileInputStream(localFile);
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        localFileNotFoundException;
      }
    }
    if (localObject == null) {
      throw new ResourceException("[WARNING][ResourceLoader]Resource not found " + paramString);
    }
    return localObject;
  }
  
  private URL a3(String paramString)
  {
    for (paramString = paramString.replaceAll("\\./", "").replaceAll("\\\\", ""); paramString.contains("//"); paramString = paramString.replaceAll("//", "/")) {}
    URL localURL;
    if ((localURL = getClass().getClassLoader().getResource(paramString)) == null) {
      localURL = CommandMap.class.getResource(paramString);
    }
    if (localURL == null) {
      localURL = ClassLoader.getSystemResource(paramString);
    }
    File localFile;
    if ((localURL == null) && ((localFile = new File(paramString)).exists())) {
      try
      {
        localURL = new URL("file:" + localFile.getAbsolutePath());
      }
      catch (MalformedURLException localMalformedURLException)
      {
        localMalformedURLException;
      }
    }
    if (localURL == null) {
      throw new ResourceException("[WARNING][ResourceLoader] Resource not found: " + paramString);
    }
    return localURL;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_39
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */