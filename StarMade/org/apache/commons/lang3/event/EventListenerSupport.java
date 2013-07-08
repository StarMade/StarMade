/*   1:    */package org.apache.commons.lang3.event;
/*   2:    */
/*   3:    */import java.io.ByteArrayOutputStream;
/*   4:    */import java.io.IOException;
/*   5:    */import java.io.ObjectInputStream;
/*   6:    */import java.io.ObjectOutputStream;
/*   7:    */import java.io.Serializable;
/*   8:    */import java.lang.reflect.Array;
/*   9:    */import java.lang.reflect.InvocationHandler;
/*  10:    */import java.lang.reflect.Method;
/*  11:    */import java.lang.reflect.Proxy;
/*  12:    */import java.util.ArrayList;
/*  13:    */import java.util.Iterator;
/*  14:    */import java.util.List;
/*  15:    */import java.util.concurrent.CopyOnWriteArrayList;
/*  16:    */import org.apache.commons.lang3.Validate;
/*  17:    */
/*  72:    */public class EventListenerSupport<L>
/*  73:    */  implements Serializable
/*  74:    */{
/*  75:    */  private static final long serialVersionUID = 3593265990380473632L;
/*  76: 76 */  private List<L> listeners = new CopyOnWriteArrayList();
/*  77:    */  
/*  85:    */  private transient L proxy;
/*  86:    */  
/*  94:    */  private transient L[] prototypeArray;
/*  95:    */  
/* 104:    */  public static <T> EventListenerSupport<T> create(Class<T> listenerInterface)
/* 105:    */  {
/* 106:106 */    return new EventListenerSupport(listenerInterface);
/* 107:    */  }
/* 108:    */  
/* 120:    */  public EventListenerSupport(Class<L> listenerInterface)
/* 121:    */  {
/* 122:122 */    this(listenerInterface, Thread.currentThread().getContextClassLoader());
/* 123:    */  }
/* 124:    */  
/* 137:    */  public EventListenerSupport(Class<L> listenerInterface, ClassLoader classLoader)
/* 138:    */  {
/* 139:139 */    this();
/* 140:140 */    Validate.notNull(listenerInterface, "Listener interface cannot be null.", new Object[0]);
/* 141:141 */    Validate.notNull(classLoader, "ClassLoader cannot be null.", new Object[0]);
/* 142:142 */    Validate.isTrue(listenerInterface.isInterface(), "Class {0} is not an interface", new Object[] { listenerInterface.getName() });
/* 143:    */    
/* 144:144 */    initializeTransientFields(listenerInterface, classLoader);
/* 145:    */  }
/* 146:    */  
/* 153:    */  private EventListenerSupport() {}
/* 154:    */  
/* 161:    */  public L fire()
/* 162:    */  {
/* 163:163 */    return this.proxy;
/* 164:    */  }
/* 165:    */  
/* 177:    */  public void addListener(L listener)
/* 178:    */  {
/* 179:179 */    Validate.notNull(listener, "Listener object cannot be null.", new Object[0]);
/* 180:180 */    this.listeners.add(listener);
/* 181:    */  }
/* 182:    */  
/* 187:    */  int getListenerCount()
/* 188:    */  {
/* 189:189 */    return this.listeners.size();
/* 190:    */  }
/* 191:    */  
/* 199:    */  public void removeListener(L listener)
/* 200:    */  {
/* 201:201 */    Validate.notNull(listener, "Listener object cannot be null.", new Object[0]);
/* 202:202 */    this.listeners.remove(listener);
/* 203:    */  }
/* 204:    */  
/* 210:    */  public L[] getListeners()
/* 211:    */  {
/* 212:212 */    return this.listeners.toArray(this.prototypeArray);
/* 213:    */  }
/* 214:    */  
/* 218:    */  private void writeObject(ObjectOutputStream objectOutputStream)
/* 219:    */    throws IOException
/* 220:    */  {
/* 221:221 */    ArrayList<L> serializableListeners = new ArrayList();
/* 222:    */    
/* 224:224 */    ObjectOutputStream testObjectOutputStream = new ObjectOutputStream(new ByteArrayOutputStream());
/* 225:225 */    for (Iterator i$ = this.listeners.iterator(); i$.hasNext();) { L listener = i$.next();
/* 226:    */      try {
/* 227:227 */        testObjectOutputStream.writeObject(listener);
/* 228:228 */        serializableListeners.add(listener);
/* 229:    */      }
/* 230:    */      catch (IOException exception) {
/* 231:231 */        testObjectOutputStream = new ObjectOutputStream(new ByteArrayOutputStream());
/* 232:    */      }
/* 233:    */    }
/* 234:    */    
/* 238:238 */    objectOutputStream.writeObject(serializableListeners.toArray(this.prototypeArray));
/* 239:    */  }
/* 240:    */  
/* 246:    */  private void readObject(ObjectInputStream objectInputStream)
/* 247:    */    throws IOException, ClassNotFoundException
/* 248:    */  {
/* 249:249 */    L[] listeners = (Object[])objectInputStream.readObject();
/* 250:    */    
/* 251:251 */    this.listeners = new CopyOnWriteArrayList(listeners);
/* 252:    */    
/* 254:254 */    Class<L> listenerInterface = listeners.getClass().getComponentType();
/* 255:    */    
/* 256:256 */    initializeTransientFields(listenerInterface, Thread.currentThread().getContextClassLoader());
/* 257:    */  }
/* 258:    */  
/* 264:    */  private void initializeTransientFields(Class<L> listenerInterface, ClassLoader classLoader)
/* 265:    */  {
/* 266:266 */    L[] array = (Object[])Array.newInstance(listenerInterface, 0);
/* 267:267 */    this.prototypeArray = array;
/* 268:268 */    createProxy(listenerInterface, classLoader);
/* 269:    */  }
/* 270:    */  
/* 275:    */  private void createProxy(Class<L> listenerInterface, ClassLoader classLoader)
/* 276:    */  {
/* 277:277 */    this.proxy = listenerInterface.cast(Proxy.newProxyInstance(classLoader, new Class[] { listenerInterface }, createInvocationHandler()));
/* 278:    */  }
/* 279:    */  
/* 285:    */  protected InvocationHandler createInvocationHandler()
/* 286:    */  {
/* 287:287 */    return new ProxyInvocationHandler();
/* 288:    */  }
/* 289:    */  
/* 294:    */  protected class ProxyInvocationHandler
/* 295:    */    implements InvocationHandler
/* 296:    */  {
/* 297:    */    private static final long serialVersionUID = 1L;
/* 298:    */    
/* 302:    */    protected ProxyInvocationHandler() {}
/* 303:    */    
/* 307:    */    public Object invoke(Object proxy, Method method, Object[] args)
/* 308:    */      throws Throwable
/* 309:    */    {
/* 310:310 */      for (Iterator i$ = EventListenerSupport.this.listeners.iterator(); i$.hasNext();) { L listener = i$.next();
/* 311:311 */        method.invoke(listener, args);
/* 312:    */      }
/* 313:313 */      return null;
/* 314:    */    }
/* 315:    */  }
/* 316:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.event.EventListenerSupport
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */