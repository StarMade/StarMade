package org.schema.schine.network.objects;

import com.bulletphysics.util.ObjectArrayList;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ByteOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import org.schema.schine.network.NetUtil;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.client.ClientState;
import org.schema.schine.network.objects.remote.NetworkChangeObserver;
import org.schema.schine.network.objects.remote.RemoteBoolean;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteInteger;
import org.schema.schine.network.objects.remote.Streamable;
import org.schema.schine.network.server.ServerStateInterface;
import org.schema.schine.network.synchronization.SynchronizationReceiver;
import org.schema.schine.network.synchronization.SynchronizationSender;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public abstract class NetworkObject
  implements NetworkChangeObserver
{
  public boolean newObject = true;
  public RemoteInteger field_87 = new RemoteInteger(Integer.valueOf(-123456), this);
  public RemoteInteger clientId = new RemoteInteger(Integer.valueOf(-777777), this);
  public RemoteBoolean markedDeleted = new RemoteBoolean(false, this);
  private Byte2ObjectOpenHashMap fieldMap;
  private Object2ByteOpenHashMap fieldMapKey;
  private ObjectArrayList buffers;
  private final boolean onServer;
  private final StateInterface state;
  private static final ConcurrentHashMap fieldMapCache = new ConcurrentHashMap();
  private static final ConcurrentHashMap fieldMapKeyCache = new ConcurrentHashMap();
  private boolean changed = true;
  private boolean observersInitialized;
  private static final int FIELD_CODE_NOT_CHANGED = 0;
  private static final int FIELD_CODE_CHANGED = 1;
  private static final int FIELD_CODE_CHANGED_KEEP_CHANGED = 2;
  public static long objectDebugIdGen = 777777L;
  public static boolean global_DEBUG;
  
  public static NetworkObject decode(StateInterface paramStateInterface, DataInputStream paramDataInputStream, NetworkObject paramNetworkObject, short arg3, boolean paramBoolean, int paramInt)
  {
    synchronized (paramDataInputStream)
    {
      paramBoolean = paramDataInputStream.readByte();
      if (paramStateInterface.isReadingBigChunk()) {
        System.err.println(paramStateInterface + ": decoding NTObject from big chunk: " + paramNetworkObject.getClass().getSimpleName() + "; fields received: " + paramBoolean);
      }
      try
      {
        for (boolean bool = false; bool < paramBoolean; bool++)
        {
          byte b = paramDataInputStream.readByte();
          synchronized (paramNetworkObject)
          {
            Streamable localStreamable;
            if ((localStreamable = (Streamable)paramNetworkObject.fieldMap.get(b)) == null)
            {
              System.err.println("Exception: FIELD not found " + b + ": in " + paramNetworkObject.getClass().getSimpleName() + paramNetworkObject.fieldMap + " ONE POSSIBLE REASON: RemoteArray sizes on Server and Client differ");
              assert (localStreamable != null) : ("not found " + b + ": in " + paramNetworkObject.getClass().getSimpleName() + "; fromStateID: " + paramInt + "; fields:" + paramNetworkObject.fieldMap + " ONE POSSIBLE REASON: RemoteArray sizes on Server and Client differ");
            }
            if (paramStateInterface.isReadingBigChunk()) {
              System.err.println(paramStateInterface + ": decoding FIELD from big chunk: " + localStreamable);
            }
            if ((SynchronizationReceiver.serverDebug) && ((paramStateInterface instanceof ServerStateInterface))) {
              System.err.println("DEBUG: changed field: " + localStreamable);
            }
            try
            {
              synchronized (localStreamable)
              {
                localStreamable.fromByteStream(paramDataInputStream, paramInt);
              }
            }
            catch (Exception localException2)
            {
              System.err.println("[EXCEPTION] NT ERROR: From senderID: " + paramInt + " for field: " + localStreamable + " in " + paramNetworkObject.getClass().getSimpleName());
              localException2.printStackTrace();
              throw localException2;
            }
          }
        }
      }
      catch (Exception localException3)
      {
        Exception localException1;
        (localException1 = localException3).printStackTrace();
        System.err.println("Exit because of critical error in nt object");
        throw localException1;
      }
    }
    return paramNetworkObject;
  }
  
  public static boolean encode(Sendable paramSendable, NetworkObject paramNetworkObject, boolean paramBoolean1, DataOutputStream paramDataOutputStream, boolean paramBoolean2, boolean paramBoolean3)
  {
    paramBoolean3 = false;
    assert (((Integer)paramNetworkObject.field_87.get()).intValue() >= 0) : (paramNetworkObject.field_87.get() + " id for remote object never set. local it is " + paramSendable.getId() + ", " + paramSendable + ", " + paramSendable.getState());
    try
    {
      assert (paramNetworkObject.observersInitialized) : (paramSendable + ", " + paramNetworkObject + ": " + paramSendable.getState());
      paramDataOutputStream.writeInt(((Integer)paramNetworkObject.field_87.get()).intValue());
      paramDataOutputStream.writeByte(NetUtil.getSendableKey(paramSendable.getClass()));
      synchronized (paramNetworkObject)
      {
        int i = 0;
        Iterator localIterator = paramNetworkObject.fieldMap.values().iterator();
        while (localIterator.hasNext())
        {
          localObject1 = (Streamable)localIterator.next();
          if ((SynchronizationSender.clientDebug) && ((paramSendable.getState() instanceof ClientState))) {
            System.err.println("DEBUG ENCODING ON CLIENT " + localObject1.getClass().getSimpleName());
          }
          if (getFieldCodeLength(paramNetworkObject, (Streamable)localObject1, paramDataOutputStream, paramBoolean1)) {
            i++;
          }
        }
        assert (i <= 127);
        paramDataOutputStream.writeByte(i);
        int j = 0;
        Object localObject1 = paramNetworkObject.fieldMap.values().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          Streamable localStreamable = (Streamable)((Iterator)localObject1).next();
          int k;
          if ((k = getFieldCode(paramNetworkObject, localStreamable, paramDataOutputStream, paramBoolean1, paramBoolean2)) > 0)
          {
            if (k == 2) {
              paramBoolean3 = true;
            }
            j++;
          }
        }
        assert (j == i) : (" ENCODING OF " + paramSendable + " failed; forced " + paramBoolean1 + "; " + i + "/" + j);
        global_DEBUG = false;
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException;
    }
    return paramBoolean3;
  }
  
  private static int getFieldCode(NetworkObject paramNetworkObject, Streamable paramStreamable, DataOutputStream paramDataOutputStream, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramBoolean1) && ((paramStreamable instanceof RemoteBuffer)) && (((RemoteBuffer)paramStreamable).isEmpty()))
    {
      paramStreamable.setChanged(false);
      return 0;
    }
    if ((paramBoolean1) || (paramStreamable.hasChanged()))
    {
      paramNetworkObject = paramNetworkObject.fieldMapKey.get(paramStreamable).byteValue();
      paramDataOutputStream.writeByte(paramNetworkObject);
      paramDataOutputStream.size();
      paramNetworkObject = paramStreamable.toByteStream(paramDataOutputStream);
      paramDataOutputStream.size();
      paramDataOutputStream = 1;
      if (((!paramBoolean2) && (!paramStreamable.keepChanged())) || (paramStreamable.initialSynchUpdateOnly())) {
        paramStreamable.setChanged(false);
      } else {
        paramDataOutputStream = 2;
      }
      assert (paramNetworkObject > 0) : (paramStreamable.getClass() + ": , Field: " + paramStreamable);
      return paramDataOutputStream;
    }
    return 0;
  }
  
  private static boolean getFieldCodeLength(NetworkObject paramNetworkObject, Streamable paramStreamable, OutputStream paramOutputStream, boolean paramBoolean)
  {
    if ((paramBoolean) && ((paramStreamable instanceof RemoteBuffer))) {
      return !((RemoteBuffer)paramStreamable).isEmpty();
    }
    return (paramBoolean) || (paramStreamable.hasChanged());
  }
  
  public NetworkObject(StateInterface paramStateInterface)
  {
    this.onServer = (paramStateInterface instanceof ServerStateInterface);
    this.state = paramStateInterface;
  }
  
  @PostConstruct
  public void init()
  {
    createFieldMap();
  }
  
  public void addObserversForFields()
  {
    try
    {
      Field[] arrayOfField = getClass().getFields();
      for (int i = 0; i < arrayOfField.length; i++)
      {
        Field localField;
        if (((localField = (Field)arrayOfField[i]).getModifiers() == 1) && (Streamable.class.isAssignableFrom(localField.getType()))) {
          ((Streamable)localField.getType().cast(localField.get(this))).setObserver(this);
        }
      }
      this.observersInitialized = true;
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException;
    }
  }
  
  public void appendToDoc(Node paramNode, Document paramDocument)
  {
    Element localElement = paramDocument.createElement("entity");
    (localObject1 = paramDocument.createAttribute("class")).setNodeValue(getClass().getSimpleName());
    localElement.setAttributeNode((Attr)localObject1);
    Object localObject1 = getClass().getFields();
    try
    {
      for (int i = 0; i < localObject1.length; i++)
      {
        Field localField;
        String str2;
        String str1;
        if ((localField = (Field)localObject1[i]).getGenericType().equals(String.class))
        {
          str2 = "string";
          System.err.println("fieldname " + localField.getName());
          str1 = localField.get(this).toString();
        }
        else if (localField.getGenericType().equals(Float.TYPE))
        {
          str2 = "float";
          str1 = localField.get(this).toString();
        }
        else if (localField.getGenericType().equals(Integer.TYPE))
        {
          str2 = "int";
          str1 = localField.get(this).toString();
        }
        else if (localField.getGenericType().equals(Boolean.TYPE))
        {
          str2 = "boolean";
          str1 = localField.get(this).toString();
        }
        else
        {
          int j;
          if (localField.getGenericType().equals([F.class))
          {
            localObject2 = (float[])localField.get(this);
            str2 = "floatArray";
            str1 = "";
            for (j = 0; j < localObject2.length; j++)
            {
              str1 = str1 + localObject2[j];
              if (j < localObject2.length - 1) {
                str1 = str1 + ",";
              }
            }
          }
          else if (localField.getGenericType().equals([Ljava.lang.String.class))
          {
            localObject2 = (String[])localField.get(this);
            str2 = "stringArray";
            str1 = "";
            for (j = 0; j < localObject2.length; j++)
            {
              str1 = str1 + localObject2[j];
              if (j < localObject2.length - 1) {
                str1 = str1 + ",";
              }
            }
          }
          else
          {
            if (!localField.getGenericType().equals([I.class)) {
              break;
            }
            localObject2 = (int[])localField.get(this);
            str2 = "intArray";
            str1 = "";
            for (j = 0; j < localObject2.length; j++)
            {
              str1 = str1 + localObject2[j];
              if (j < localObject2.length - 1) {
                str1 = str1 + ",";
              }
            }
          }
        }
        Object localObject2 = paramDocument.createElement(localField.getName());
        Attr localAttr;
        (localAttr = paramDocument.createAttribute("type")).setNodeValue(str2);
        ((Element)localObject2).setAttributeNode(localAttr);
        ((Element)localObject2).setTextContent(str1);
        localElement.appendChild((Node)localObject2);
      }
    }
    catch (DOMException localDOMException)
    {
      localDOMException;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException;
    }
    System.err.println("appending entity " + getClass().getSimpleName() + ": " + this);
    paramNode.appendChild(localElement);
  }
  
  public void clearReceiveBuffers()
  {
    synchronized (this)
    {
      Iterator localIterator = this.buffers.iterator();
      while (localIterator.hasNext()) {
        ((RemoteBuffer)localIterator.next()).clearReceiveBuffer();
      }
      return;
    }
  }
  
  private void createFieldMap()
  {
    HashMap localHashMap1;
    HashMap localHashMap2;
    Object localObject2;
    if (fieldMapCache.containsKey(getClass()))
    {
      localHashMap1 = (HashMap)fieldMapCache.get(getClass());
      fieldMapKeyCache.get(getClass());
      localHashMap2 = null;
    }
    else
    {
      localHashMap1 = new HashMap();
      localHashMap2 = new HashMap();
      localObject2 = new String[(localObject1 = getClass().getFields()).length];
      for (int i = 0; i < localObject1.length; i++) {
        localObject2[i] = localObject1[i].getName();
      }
      assert (localObject2.length < 127);
      Arrays.sort((Object[])localObject2);
      int j;
      for (i = 0; i < localObject2.length; j = (byte)(i + 1)) {
        try
        {
          localObject1 = getClass().getField(localObject2[i]);
          localHashMap1.put(Byte.valueOf(i), localObject1);
          localHashMap2.put(localObject1, Byte.valueOf(i));
        }
        catch (SecurityException localSecurityException)
        {
          localSecurityException;
        }
        catch (NoSuchFieldException localNoSuchFieldException)
        {
          localNoSuchFieldException;
        }
      }
      fieldMapCache.put(getClass(), localHashMap1);
      fieldMapKeyCache.put(getClass(), localHashMap2);
    }
    this.buffers = new ObjectArrayList();
    this.fieldMap = new Byte2ObjectOpenHashMap();
    this.fieldMapKey = new Object2ByteOpenHashMap();
    Object localObject1 = localHashMap1.entrySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Map.Entry)((Iterator)localObject1).next();
      if (Streamable.class.isAssignableFrom(((Field)((Map.Entry)localObject2).getValue()).getType())) {
        try
        {
          localObject3 = (Streamable)((Field)((Map.Entry)localObject2).getValue()).get(this);
          assert (localObject3 != null) : (getClass().getSimpleName() + " -> " + ((Field)((Map.Entry)localObject2).getValue()).getName() + ": " + ((Field)((Map.Entry)localObject2).getValue()).getType().getSimpleName());
          this.fieldMap.put((Byte)((Map.Entry)localObject2).getKey(), localObject3);
          this.fieldMapKey.put(localObject3, (Byte)((Map.Entry)localObject2).getKey());
          if ((localObject3 instanceof RemoteBuffer)) {
            this.buffers.add((RemoteBuffer)localObject3);
          }
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          (localObject3 = localIllegalArgumentException).printStackTrace();
          throw new RuntimeException(localObject3.getClass() + ": " + ((IllegalArgumentException)localObject3).getMessage());
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          Object localObject3;
          (localObject3 = localIllegalAccessException).printStackTrace();
          throw new RuntimeException(localObject3.getClass() + ": " + ((IllegalAccessException)localObject3).getMessage());
        }
      }
    }
    this.fieldMap.trim();
    this.fieldMapKey.trim();
  }
  
  public void decodeChange(StateInterface paramStateInterface, DataInputStream paramDataInputStream, short paramShort, boolean paramBoolean, int paramInt)
  {
    decode(paramStateInterface, paramDataInputStream, this, paramShort, paramBoolean, paramInt);
  }
  
  public boolean encodeChange(Sendable paramSendable, DataOutputStream paramDataOutputStream, boolean paramBoolean)
  {
    return encode(paramSendable, paramSendable.getNetworkObject(), false, paramDataOutputStream, false, paramBoolean);
  }
  
  public boolean equals(Object paramObject)
  {
    paramObject = (NetworkObject)paramObject;
    return this.field_87.get() == paramObject.field_87.get();
  }
  
  public String getChangedFieldsString()
  {
    return new StringBuffer().toString();
  }
  
  public StateInterface getState()
  {
    return this.state;
  }
  
  public boolean isChanged()
  {
    return this.changed;
  }
  
  public boolean isOnServer()
  {
    return this.onServer;
  }
  
  public abstract void onDelete(StateInterface paramStateInterface);
  
  public abstract void onInit(StateInterface paramStateInterface);
  
  public void setAllFieldsChanged()
  {
    synchronized (this)
    {
      Iterator localIterator = this.fieldMap.values().iterator();
      while (localIterator.hasNext())
      {
        Streamable localStreamable = (Streamable)localIterator.next();
        assert (localStreamable != null);
        localStreamable.setChanged(true);
      }
      setChanged(true);
      return;
    }
  }
  
  public void setChanged(boolean paramBoolean)
  {
    this.changed = paramBoolean;
  }
  
  public String toString()
  {
    return "NetworkObject(" + this.field_87.get() + ")";
  }
  
  public void update(Streamable arg1)
  {
    synchronized (this)
    {
      setChanged(true);
      return;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.NetworkObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */