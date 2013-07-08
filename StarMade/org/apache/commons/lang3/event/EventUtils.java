/*   1:    */package org.apache.commons.lang3.event;
/*   2:    */
/*   3:    */import java.lang.reflect.InvocationHandler;
/*   4:    */import java.lang.reflect.InvocationTargetException;
/*   5:    */import java.lang.reflect.Method;
/*   6:    */import java.lang.reflect.Proxy;
/*   7:    */import java.util.Arrays;
/*   8:    */import java.util.HashSet;
/*   9:    */import java.util.Set;
/*  10:    */import org.apache.commons.lang3.reflect.MethodUtils;
/*  11:    */
/*  44:    */public class EventUtils
/*  45:    */{
/*  46:    */  public static <L> void addEventListener(Object eventSource, Class<L> listenerType, L listener)
/*  47:    */  {
/*  48:    */    try
/*  49:    */    {
/*  50: 50 */      MethodUtils.invokeMethod(eventSource, "add" + listenerType.getSimpleName(), new Object[] { listener });
/*  51:    */    } catch (NoSuchMethodException e) {
/*  52: 52 */      throw new IllegalArgumentException("Class " + eventSource.getClass().getName() + " does not have a public add" + listenerType.getSimpleName() + " method which takes a parameter of type " + listenerType.getName() + ".");
/*  53:    */    }
/*  54:    */    catch (IllegalAccessException e)
/*  55:    */    {
/*  56: 56 */      throw new IllegalArgumentException("Class " + eventSource.getClass().getName() + " does not have an accessible add" + listenerType.getSimpleName() + " method which takes a parameter of type " + listenerType.getName() + ".");
/*  57:    */    }
/*  58:    */    catch (InvocationTargetException e)
/*  59:    */    {
/*  60: 60 */      throw new RuntimeException("Unable to add listener.", e.getCause());
/*  61:    */    }
/*  62:    */  }
/*  63:    */  
/*  75:    */  public static <L> void bindEventsToMethod(Object target, String methodName, Object eventSource, Class<L> listenerType, String... eventTypes)
/*  76:    */  {
/*  77: 77 */    L listener = listenerType.cast(Proxy.newProxyInstance(target.getClass().getClassLoader(), new Class[] { listenerType }, new EventBindingInvocationHandler(target, methodName, eventTypes)));
/*  78:    */    
/*  79: 79 */    addEventListener(eventSource, listenerType, listener);
/*  80:    */  }
/*  81:    */  
/*  83:    */  private static class EventBindingInvocationHandler
/*  84:    */    implements InvocationHandler
/*  85:    */  {
/*  86:    */    private final Object target;
/*  87:    */    
/*  88:    */    private final String methodName;
/*  89:    */    
/*  90:    */    private final Set<String> eventTypes;
/*  91:    */    
/*  93:    */    EventBindingInvocationHandler(Object target, String methodName, String[] eventTypes)
/*  94:    */    {
/*  95: 95 */      this.target = target;
/*  96: 96 */      this.methodName = methodName;
/*  97: 97 */      this.eventTypes = new HashSet(Arrays.asList(eventTypes));
/*  98:    */    }
/*  99:    */    
/* 107:    */    public Object invoke(Object proxy, Method method, Object[] parameters)
/* 108:    */      throws Throwable
/* 109:    */    {
/* 110:110 */      if ((this.eventTypes.isEmpty()) || (this.eventTypes.contains(method.getName()))) {
/* 111:111 */        if (hasMatchingParametersMethod(method)) {
/* 112:112 */          return MethodUtils.invokeMethod(this.target, this.methodName, parameters);
/* 113:    */        }
/* 114:114 */        return MethodUtils.invokeMethod(this.target, this.methodName, new Object[0]);
/* 115:    */      }
/* 116:    */      
/* 117:117 */      return null;
/* 118:    */    }
/* 119:    */    
/* 125:    */    private boolean hasMatchingParametersMethod(Method method)
/* 126:    */    {
/* 127:127 */      return MethodUtils.getAccessibleMethod(this.target.getClass(), this.methodName, method.getParameterTypes()) != null;
/* 128:    */    }
/* 129:    */  }
/* 130:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.event.EventUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */