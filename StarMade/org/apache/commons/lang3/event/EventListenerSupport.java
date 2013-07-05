/*     */ package org.apache.commons.lang3.event;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Array;
/*     */ import java.lang.reflect.InvocationHandler;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import org.apache.commons.lang3.Validate;
/*     */ 
/*     */ public class EventListenerSupport<L>
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3593265990380473632L;
/*  76 */   private List<L> listeners = new CopyOnWriteArrayList();
/*     */   private transient L proxy;
/*     */   private transient L[] prototypeArray;
/*     */ 
/*     */   public static <T> EventListenerSupport<T> create(Class<T> listenerInterface)
/*     */   {
/* 106 */     return new EventListenerSupport(listenerInterface);
/*     */   }
/*     */ 
/*     */   public EventListenerSupport(Class<L> listenerInterface)
/*     */   {
/* 122 */     this(listenerInterface, Thread.currentThread().getContextClassLoader());
/*     */   }
/*     */ 
/*     */   public EventListenerSupport(Class<L> listenerInterface, ClassLoader classLoader)
/*     */   {
/* 139 */     this();
/* 140 */     Validate.notNull(listenerInterface, "Listener interface cannot be null.", new Object[0]);
/* 141 */     Validate.notNull(classLoader, "ClassLoader cannot be null.", new Object[0]);
/* 142 */     Validate.isTrue(listenerInterface.isInterface(), "Class {0} is not an interface", new Object[] { listenerInterface.getName() });
/*     */ 
/* 144 */     initializeTransientFields(listenerInterface, classLoader);
/*     */   }
/*     */ 
/*     */   private EventListenerSupport()
/*     */   {
/*     */   }
/*     */ 
/*     */   public L fire()
/*     */   {
/* 163 */     return this.proxy;
/*     */   }
/*     */ 
/*     */   public void addListener(L listener)
/*     */   {
/* 179 */     Validate.notNull(listener, "Listener object cannot be null.", new Object[0]);
/* 180 */     this.listeners.add(listener);
/*     */   }
/*     */ 
/*     */   int getListenerCount()
/*     */   {
/* 189 */     return this.listeners.size();
/*     */   }
/*     */ 
/*     */   public void removeListener(L listener)
/*     */   {
/* 201 */     Validate.notNull(listener, "Listener object cannot be null.", new Object[0]);
/* 202 */     this.listeners.remove(listener);
/*     */   }
/*     */ 
/*     */   public L[] getListeners()
/*     */   {
/* 212 */     return this.listeners.toArray(this.prototypeArray);
/*     */   }
/*     */ 
/*     */   private void writeObject(ObjectOutputStream objectOutputStream)
/*     */     throws IOException
/*     */   {
/* 221 */     ArrayList serializableListeners = new ArrayList();
/*     */ 
/* 224 */     ObjectOutputStream testObjectOutputStream = new ObjectOutputStream(new ByteArrayOutputStream());
/* 225 */     for (Iterator i$ = this.listeners.iterator(); i$.hasNext(); ) { Object listener = i$.next();
/*     */       try {
/* 227 */         testObjectOutputStream.writeObject(listener);
/* 228 */         serializableListeners.add(listener);
/*     */       }
/*     */       catch (IOException exception) {
/* 231 */         testObjectOutputStream = new ObjectOutputStream(new ByteArrayOutputStream());
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 238 */     objectOutputStream.writeObject(serializableListeners.toArray(this.prototypeArray));
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream objectInputStream)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 249 */     Object[] listeners = (Object[])objectInputStream.readObject();
/*     */ 
/* 251 */     this.listeners = new CopyOnWriteArrayList(listeners);
/*     */ 
/* 254 */     Class listenerInterface = listeners.getClass().getComponentType();
/*     */ 
/* 256 */     initializeTransientFields(listenerInterface, Thread.currentThread().getContextClassLoader());
/*     */   }
/*     */ 
/*     */   private void initializeTransientFields(Class<L> listenerInterface, ClassLoader classLoader)
/*     */   {
/* 266 */     Object[] array = (Object[])Array.newInstance(listenerInterface, 0);
/* 267 */     this.prototypeArray = array;
/* 268 */     createProxy(listenerInterface, classLoader);
/*     */   }
/*     */ 
/*     */   private void createProxy(Class<L> listenerInterface, ClassLoader classLoader)
/*     */   {
/* 277 */     this.proxy = listenerInterface.cast(Proxy.newProxyInstance(classLoader, new Class[] { listenerInterface }, createInvocationHandler()));
/*     */   }
/*     */ 
/*     */   protected InvocationHandler createInvocationHandler()
/*     */   {
/* 287 */     return new ProxyInvocationHandler();
/*     */   }
/*     */ 
/*     */   protected class ProxyInvocationHandler
/*     */     implements InvocationHandler
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */ 
/*     */     protected ProxyInvocationHandler()
/*     */     {
/*     */     }
/*     */ 
/*     */     public Object invoke(Object proxy, Method method, Object[] args)
/*     */       throws Throwable
/*     */     {
/* 310 */       for (Iterator i$ = EventListenerSupport.this.listeners.iterator(); i$.hasNext(); ) { Object listener = i$.next();
/* 311 */         method.invoke(listener, args);
/*     */       }
/* 313 */       return null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.event.EventListenerSupport
 * JD-Core Version:    0.6.2
 */