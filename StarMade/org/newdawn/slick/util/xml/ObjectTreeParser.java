package org.newdawn.slick.util.xml;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import org.newdawn.slick.util.Log;
import org.newdawn.slick.util.ResourceLoader;

public class ObjectTreeParser
{
  private HashMap nameToClass = new HashMap();
  private String defaultPackage;
  private ArrayList ignored = new ArrayList();
  private String addMethod = "add";
  
  public ObjectTreeParser() {}
  
  public ObjectTreeParser(String defaultPackage)
  {
    this.defaultPackage = defaultPackage;
  }
  
  public void addElementMapping(String elementName, Class elementClass)
  {
    this.nameToClass.put(elementName, elementClass);
  }
  
  public void addIgnoredElement(String elementName)
  {
    this.ignored.add(elementName);
  }
  
  public void setAddMethodName(String methodName)
  {
    this.addMethod = methodName;
  }
  
  public void setDefaultPackage(String defaultPackage)
  {
    this.defaultPackage = defaultPackage;
  }
  
  public Object parse(String ref)
    throws SlickXMLException
  {
    return parse(ref, ResourceLoader.getResourceAsStream(ref));
  }
  
  public Object parse(String name, InputStream local_in)
    throws SlickXMLException
  {
    XMLParser parser = new XMLParser();
    XMLElement root = parser.parse(name, local_in);
    return traverse(root);
  }
  
  public Object parseOnto(String ref, Object target)
    throws SlickXMLException
  {
    return parseOnto(ref, ResourceLoader.getResourceAsStream(ref), target);
  }
  
  public Object parseOnto(String name, InputStream local_in, Object target)
    throws SlickXMLException
  {
    XMLParser parser = new XMLParser();
    XMLElement root = parser.parse(name, local_in);
    return traverse(root, target);
  }
  
  private Class getClassForElementName(String name)
  {
    Class clazz = (Class)this.nameToClass.get(name);
    if (clazz != null) {
      return clazz;
    }
    if (this.defaultPackage != null) {
      try
      {
        return Class.forName(this.defaultPackage + "." + name);
      }
      catch (ClassNotFoundException local_e) {}
    }
    return null;
  }
  
  private Object traverse(XMLElement current)
    throws SlickXMLException
  {
    return traverse(current, null);
  }
  
  private Object traverse(XMLElement current, Object instance)
    throws SlickXMLException
  {
    String name = current.getName();
    if (this.ignored.contains(name)) {
      return null;
    }
    Class clazz;
    Class clazz;
    if (instance == null) {
      clazz = getClassForElementName(name);
    } else {
      clazz = instance.getClass();
    }
    if (clazz == null) {
      throw new SlickXMLException("Unable to map element " + name + " to a class, define the mapping");
    }
    try
    {
      if (instance == null)
      {
        instance = clazz.newInstance();
        Method elementNameMethod = getMethod(clazz, "setXMLElementName", new Class[] { String.class });
        if (elementNameMethod != null) {
          invoke(elementNameMethod, instance, new Object[] { name });
        }
        Method contentMethod = getMethod(clazz, "setXMLElementContent", new Class[] { String.class });
        if (contentMethod != null) {
          invoke(contentMethod, instance, new Object[] { current.getContent() });
        }
      }
      String[] elementNameMethod = current.getAttributeNames();
      for (int contentMethod = 0; contentMethod < elementNameMethod.length; contentMethod++)
      {
        String methodName = "set" + elementNameMethod[contentMethod];
        Method method = findMethod(clazz, methodName);
        if (method == null)
        {
          Field field = findField(clazz, elementNameMethod[contentMethod]);
          if (field != null)
          {
            String value = current.getAttribute(elementNameMethod[contentMethod]);
            Object typedValue = typeValue(value, field.getType());
            setField(field, instance, typedValue);
          }
          else
          {
            Log.info("Unable to find property on: " + clazz + " for attribute: " + elementNameMethod[contentMethod]);
          }
        }
        else
        {
          String field = current.getAttribute(elementNameMethod[contentMethod]);
          Object value = typeValue(field, method.getParameterTypes()[0]);
          invoke(method, instance, new Object[] { value });
        }
      }
      XMLElementList contentMethod = current.getChildren();
      for (int methodName = 0; methodName < contentMethod.size(); methodName++)
      {
        XMLElement method = contentMethod.get(methodName);
        Object field = traverse(method);
        if (field != null)
        {
          String value = this.addMethod;
          Method typedValue = findMethod(clazz, value, field.getClass());
          if (typedValue == null) {
            Log.info("Unable to find method to add: " + field + " to " + clazz);
          } else {
            invoke(typedValue, instance, new Object[] { field });
          }
        }
      }
      return instance;
    }
    catch (InstantiationException elementNameMethod)
    {
      throw new SlickXMLException("Unable to instance " + clazz + " for element " + name + ", no zero parameter constructor?", elementNameMethod);
    }
    catch (IllegalAccessException elementNameMethod)
    {
      throw new SlickXMLException("Unable to instance " + clazz + " for element " + name + ", no zero parameter constructor?", elementNameMethod);
    }
  }
  
  private Object typeValue(String value, Class clazz)
    throws SlickXMLException
  {
    if (clazz == String.class) {
      return value;
    }
    try
    {
      clazz = mapPrimitive(clazz);
      return clazz.getConstructor(new Class[] { String.class }).newInstance(new Object[] { value });
    }
    catch (Exception local_e)
    {
      throw new SlickXMLException("Failed to convert: " + value + " to the expected primitive type: " + clazz, local_e);
    }
  }
  
  private Class mapPrimitive(Class clazz)
  {
    if (clazz == Integer.TYPE) {
      return Integer.class;
    }
    if (clazz == Double.TYPE) {
      return Double.class;
    }
    if (clazz == Float.TYPE) {
      return Float.class;
    }
    if (clazz == Boolean.TYPE) {
      return Boolean.class;
    }
    if (clazz == Long.TYPE) {
      return Long.class;
    }
    throw new RuntimeException("Unsupported primitive: " + clazz);
  }
  
  private Field findField(Class clazz, String name)
  {
    Field[] fields = clazz.getDeclaredFields();
    for (int local_i = 0; local_i < fields.length; local_i++) {
      if (fields[local_i].getName().equalsIgnoreCase(name))
      {
        if (fields[local_i].getType().isPrimitive()) {
          return fields[local_i];
        }
        if (fields[local_i].getType() == String.class) {
          return fields[local_i];
        }
      }
    }
    return null;
  }
  
  private Method findMethod(Class clazz, String name)
  {
    Method[] methods = clazz.getDeclaredMethods();
    for (int local_i = 0; local_i < methods.length; local_i++) {
      if (methods[local_i].getName().equalsIgnoreCase(name))
      {
        Method method = methods[local_i];
        Class[] params = method.getParameterTypes();
        if (params.length == 1) {
          return method;
        }
      }
    }
    return null;
  }
  
  private Method findMethod(Class clazz, String name, Class parameter)
  {
    Method[] methods = clazz.getDeclaredMethods();
    for (int local_i = 0; local_i < methods.length; local_i++) {
      if (methods[local_i].getName().equalsIgnoreCase(name))
      {
        Method method = methods[local_i];
        Class[] params = method.getParameterTypes();
        if ((params.length == 1) && (method.getParameterTypes()[0].isAssignableFrom(parameter))) {
          return method;
        }
      }
    }
    return null;
  }
  
  private void setField(Field field, Object instance, Object value)
    throws SlickXMLException
  {
    try
    {
      field.setAccessible(true);
      field.set(instance, value);
    }
    catch (IllegalArgumentException local_e)
    {
      throw new SlickXMLException("Failed to set: " + field + " for an XML attribute, is it valid?", local_e);
    }
    catch (IllegalAccessException local_e)
    {
      throw new SlickXMLException("Failed to set: " + field + " for an XML attribute, is it valid?", local_e);
    }
    finally
    {
      field.setAccessible(false);
    }
  }
  
  private void invoke(Method method, Object instance, Object[] params)
    throws SlickXMLException
  {
    try
    {
      method.setAccessible(true);
      method.invoke(instance, params);
    }
    catch (IllegalArgumentException local_e)
    {
      throw new SlickXMLException("Failed to invoke: " + method + " for an XML attribute, is it valid?", local_e);
    }
    catch (IllegalAccessException local_e)
    {
      throw new SlickXMLException("Failed to invoke: " + method + " for an XML attribute, is it valid?", local_e);
    }
    catch (InvocationTargetException local_e)
    {
      throw new SlickXMLException("Failed to invoke: " + method + " for an XML attribute, is it valid?", local_e);
    }
    finally
    {
      method.setAccessible(false);
    }
  }
  
  private Method getMethod(Class clazz, String name, Class[] params)
  {
    try
    {
      return clazz.getMethod(name, params);
    }
    catch (SecurityException local_e)
    {
      return null;
    }
    catch (NoSuchMethodException local_e) {}
    return null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.xml.ObjectTreeParser
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */