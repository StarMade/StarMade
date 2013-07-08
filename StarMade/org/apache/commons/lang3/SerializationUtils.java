package org.apache.commons.lang3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.io.Serializable;

public class SerializationUtils
{
  public static <T extends Serializable> T clone(T object)
  {
    if (object == null) {
      return null;
    }
    byte[] objectData = serialize(object);
    ByteArrayInputStream bais = new ByteArrayInputStream(objectData);
    ClassLoaderAwareObjectInputStream local_in = null;
    try
    {
      local_in = new ClassLoaderAwareObjectInputStream(bais, object.getClass().getClassLoader());
      T readObject = (Serializable)local_in.readObject();
      T ? = readObject;
      return ?;
    }
    catch (ClassNotFoundException readObject)
    {
      throw new SerializationException("ClassNotFoundException while reading cloned object data", readObject);
    }
    catch (IOException readObject)
    {
      throw new SerializationException("IOException while reading cloned object data", readObject);
    }
    finally
    {
      try
      {
        if (local_in != null) {
          local_in.close();
        }
      }
      catch (IOException local_ex1)
      {
        throw new SerializationException("IOException on closing cloned object data InputStream.", local_ex1);
      }
    }
  }
  
  public static void serialize(Serializable obj, OutputStream outputStream)
  {
    if (outputStream == null) {
      throw new IllegalArgumentException("The OutputStream must not be null");
    }
    ObjectOutputStream out = null;
    try
    {
      out = new ObjectOutputStream(outputStream);
      out.writeObject(obj);
      return;
    }
    catch (IOException local_ex)
    {
      throw new SerializationException(local_ex);
    }
    finally
    {
      try
      {
        if (out != null) {
          out.close();
        }
      }
      catch (IOException local_ex1) {}
    }
  }
  
  public static byte[] serialize(Serializable obj)
  {
    ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
    serialize(obj, baos);
    return baos.toByteArray();
  }
  
  public static Object deserialize(InputStream inputStream)
  {
    if (inputStream == null) {
      throw new IllegalArgumentException("The InputStream must not be null");
    }
    ObjectInputStream local_in = null;
    try
    {
      local_in = new ObjectInputStream(inputStream);
      Object localObject1 = local_in.readObject();
      return localObject1;
    }
    catch (ClassNotFoundException local_ex1)
    {
      throw new SerializationException(local_ex1);
    }
    catch (IOException local_ex1)
    {
      throw new SerializationException(local_ex1);
    }
    finally
    {
      try
      {
        if (local_in != null) {
          local_in.close();
        }
      }
      catch (IOException local_ex2) {}
    }
  }
  
  public static Object deserialize(byte[] objectData)
  {
    if (objectData == null) {
      throw new IllegalArgumentException("The byte[] must not be null");
    }
    ByteArrayInputStream bais = new ByteArrayInputStream(objectData);
    return deserialize(bais);
  }
  
  static class ClassLoaderAwareObjectInputStream
    extends ObjectInputStream
  {
    private ClassLoader classLoader;
    
    public ClassLoaderAwareObjectInputStream(InputStream local_in, ClassLoader classLoader)
      throws IOException
    {
      super();
      this.classLoader = classLoader;
    }
    
    protected Class<?> resolveClass(ObjectStreamClass desc)
      throws IOException, ClassNotFoundException
    {
      String name = desc.getName();
      try
      {
        return Class.forName(name, false, this.classLoader);
      }
      catch (ClassNotFoundException local_ex) {}
      return Class.forName(name, false, Thread.currentThread().getContextClassLoader());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.SerializationUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */