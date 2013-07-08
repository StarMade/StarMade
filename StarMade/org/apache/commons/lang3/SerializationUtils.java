/*   1:    */package org.apache.commons.lang3;
/*   2:    */
/*   3:    */import java.io.ByteArrayInputStream;
/*   4:    */import java.io.ByteArrayOutputStream;
/*   5:    */import java.io.IOException;
/*   6:    */import java.io.InputStream;
/*   7:    */import java.io.ObjectInputStream;
/*   8:    */import java.io.ObjectOutputStream;
/*   9:    */import java.io.ObjectStreamClass;
/*  10:    */import java.io.OutputStream;
/*  11:    */import java.io.Serializable;
/*  12:    */
/*  73:    */public class SerializationUtils
/*  74:    */{
/*  75:    */  public static <T extends Serializable> T clone(T object)
/*  76:    */  {
/*  77: 77 */    if (object == null) {
/*  78: 78 */      return null;
/*  79:    */    }
/*  80: 80 */    byte[] objectData = serialize(object);
/*  81: 81 */    ByteArrayInputStream bais = new ByteArrayInputStream(objectData);
/*  82:    */    
/*  83: 83 */    ClassLoaderAwareObjectInputStream in = null;
/*  84:    */    try
/*  85:    */    {
/*  86: 86 */      in = new ClassLoaderAwareObjectInputStream(bais, object.getClass().getClassLoader());
/*  87:    */      
/*  93: 93 */      T readObject = (Serializable)in.readObject();
/*  94: 94 */      return readObject;
/*  95:    */    }
/*  96:    */    catch (ClassNotFoundException ex) {
/*  97: 97 */      throw new SerializationException("ClassNotFoundException while reading cloned object data", ex);
/*  98:    */    } catch (IOException ex) {
/*  99: 99 */      throw new SerializationException("IOException while reading cloned object data", ex);
/* 100:    */    } finally {
/* 101:    */      try {
/* 102:102 */        if (in != null) {
/* 103:103 */          in.close();
/* 104:    */        }
/* 105:    */      } catch (IOException ex) {
/* 106:106 */        throw new SerializationException("IOException on closing cloned object data InputStream.", ex);
/* 107:    */      }
/* 108:    */    }
/* 109:    */  }
/* 110:    */  
/* 127:    */  public static void serialize(Serializable obj, OutputStream outputStream)
/* 128:    */  {
/* 129:129 */    if (outputStream == null) {
/* 130:130 */      throw new IllegalArgumentException("The OutputStream must not be null");
/* 131:    */    }
/* 132:132 */    ObjectOutputStream out = null;
/* 133:    */    try
/* 134:    */    {
/* 135:135 */      out = new ObjectOutputStream(outputStream);
/* 136:136 */      out.writeObject(obj); return;
/* 137:    */    }
/* 138:    */    catch (IOException ex) {
/* 139:139 */      throw new SerializationException(ex);
/* 140:    */    } finally {
/* 141:    */      try {
/* 142:142 */        if (out != null) {
/* 143:143 */          out.close();
/* 144:    */        }
/* 145:    */      }
/* 146:    */      catch (IOException ex) {}
/* 147:    */    }
/* 148:    */  }
/* 149:    */  
/* 158:    */  public static byte[] serialize(Serializable obj)
/* 159:    */  {
/* 160:160 */    ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
/* 161:161 */    serialize(obj, baos);
/* 162:162 */    return baos.toByteArray();
/* 163:    */  }
/* 164:    */  
/* 181:    */  public static Object deserialize(InputStream inputStream)
/* 182:    */  {
/* 183:183 */    if (inputStream == null) {
/* 184:184 */      throw new IllegalArgumentException("The InputStream must not be null");
/* 185:    */    }
/* 186:186 */    ObjectInputStream in = null;
/* 187:    */    try
/* 188:    */    {
/* 189:189 */      in = new ObjectInputStream(inputStream);
/* 190:190 */      return in.readObject();
/* 191:    */    }
/* 192:    */    catch (ClassNotFoundException ex) {
/* 193:193 */      throw new SerializationException(ex);
/* 194:    */    } catch (IOException ex) {
/* 195:195 */      throw new SerializationException(ex);
/* 196:    */    } finally {
/* 197:    */      try {
/* 198:198 */        if (in != null) {
/* 199:199 */          in.close();
/* 200:    */        }
/* 201:    */      }
/* 202:    */      catch (IOException ex) {}
/* 203:    */    }
/* 204:    */  }
/* 205:    */  
/* 214:    */  public static Object deserialize(byte[] objectData)
/* 215:    */  {
/* 216:216 */    if (objectData == null) {
/* 217:217 */      throw new IllegalArgumentException("The byte[] must not be null");
/* 218:    */    }
/* 219:219 */    ByteArrayInputStream bais = new ByteArrayInputStream(objectData);
/* 220:220 */    return deserialize(bais);
/* 221:    */  }
/* 222:    */  
/* 231:    */  static class ClassLoaderAwareObjectInputStream
/* 232:    */    extends ObjectInputStream
/* 233:    */  {
/* 234:    */    private ClassLoader classLoader;
/* 235:    */    
/* 244:    */    public ClassLoaderAwareObjectInputStream(InputStream in, ClassLoader classLoader)
/* 245:    */      throws IOException
/* 246:    */    {
/* 247:247 */      super();
/* 248:248 */      this.classLoader = classLoader;
/* 249:    */    }
/* 250:    */    
/* 258:    */    protected Class<?> resolveClass(ObjectStreamClass desc)
/* 259:    */      throws IOException, ClassNotFoundException
/* 260:    */    {
/* 261:261 */      String name = desc.getName();
/* 262:    */      try {
/* 263:263 */        return Class.forName(name, false, this.classLoader);
/* 264:    */      } catch (ClassNotFoundException ex) {}
/* 265:265 */      return Class.forName(name, false, Thread.currentThread().getContextClassLoader());
/* 266:    */    }
/* 267:    */  }
/* 268:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.SerializationUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */