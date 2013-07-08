package org.dom4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.dom4j.tree.QNameCache;
import org.dom4j.util.SingletonStrategy;

public class QName
  implements Serializable
{
  private static SingletonStrategy singleton = null;
  private String name;
  private String qualifiedName;
  private transient Namespace namespace;
  private int hashCode;
  private DocumentFactory documentFactory;
  
  public QName(String name)
  {
    this(name, Namespace.NO_NAMESPACE);
  }
  
  public QName(String name, Namespace namespace)
  {
    this.name = (name == null ? "" : name);
    this.namespace = (namespace == null ? Namespace.NO_NAMESPACE : namespace);
  }
  
  public QName(String name, Namespace namespace, String qualifiedName)
  {
    this.name = (name == null ? "" : name);
    this.qualifiedName = qualifiedName;
    this.namespace = (namespace == null ? Namespace.NO_NAMESPACE : namespace);
  }
  
  public static QName get(String name)
  {
    return getCache().get(name);
  }
  
  public static QName get(String name, Namespace namespace)
  {
    return getCache().get(name, namespace);
  }
  
  public static QName get(String name, String prefix, String uri)
  {
    if (((prefix == null) || (prefix.length() == 0)) && (uri == null)) {
      return get(name);
    }
    if ((prefix == null) || (prefix.length() == 0)) {
      return getCache().get(name, Namespace.get(uri));
    }
    if (uri == null) {
      return get(name);
    }
    return getCache().get(name, Namespace.get(prefix, uri));
  }
  
  public static QName get(String qualifiedName, String uri)
  {
    if (uri == null) {
      return getCache().get(qualifiedName);
    }
    return getCache().get(qualifiedName, uri);
  }
  
  public static QName get(String localName, Namespace namespace, String qualifiedName)
  {
    return getCache().get(localName, namespace, qualifiedName);
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getQualifiedName()
  {
    if (this.qualifiedName == null)
    {
      String prefix = getNamespacePrefix();
      if ((prefix != null) && (prefix.length() > 0)) {
        this.qualifiedName = (prefix + ":" + this.name);
      } else {
        this.qualifiedName = this.name;
      }
    }
    return this.qualifiedName;
  }
  
  public Namespace getNamespace()
  {
    return this.namespace;
  }
  
  public String getNamespacePrefix()
  {
    if (this.namespace == null) {
      return "";
    }
    return this.namespace.getPrefix();
  }
  
  public String getNamespaceURI()
  {
    if (this.namespace == null) {
      return "";
    }
    return this.namespace.getURI();
  }
  
  public int hashCode()
  {
    if (this.hashCode == 0)
    {
      this.hashCode = (getName().hashCode() ^ getNamespaceURI().hashCode());
      if (this.hashCode == 0) {
        this.hashCode = 47806;
      }
    }
    return this.hashCode;
  }
  
  public boolean equals(Object object)
  {
    if (this == object) {
      return true;
    }
    if ((object instanceof QName))
    {
      QName that = (QName)object;
      if (hashCode() == that.hashCode()) {
        return (getName().equals(that.getName())) && (getNamespaceURI().equals(that.getNamespaceURI()));
      }
    }
    return false;
  }
  
  public String toString()
  {
    return super.toString() + " [name: " + getName() + " namespace: \"" + getNamespace() + "\"]";
  }
  
  public DocumentFactory getDocumentFactory()
  {
    return this.documentFactory;
  }
  
  public void setDocumentFactory(DocumentFactory documentFactory)
  {
    this.documentFactory = documentFactory;
  }
  
  private void writeObject(ObjectOutputStream out)
    throws IOException
  {
    out.writeObject(this.namespace.getPrefix());
    out.writeObject(this.namespace.getURI());
    out.defaultWriteObject();
  }
  
  private void readObject(ObjectInputStream local_in)
    throws IOException, ClassNotFoundException
  {
    String prefix = (String)local_in.readObject();
    String uri = (String)local_in.readObject();
    local_in.defaultReadObject();
    this.namespace = Namespace.get(prefix, uri);
  }
  
  private static QNameCache getCache()
  {
    QNameCache cache = (QNameCache)singleton.instance();
    return cache;
  }
  
  static
  {
    try
    {
      String defaultSingletonClass = "org.dom4j.util.SimpleSingleton";
      Class clazz = null;
      try
      {
        String singletonClass = defaultSingletonClass;
        singletonClass = System.getProperty("org.dom4j.QName.singleton.strategy", singletonClass);
        clazz = Class.forName(singletonClass);
      }
      catch (Exception singletonClass)
      {
        try
        {
          String singletonClass = defaultSingletonClass;
          clazz = Class.forName(singletonClass);
        }
        catch (Exception singletonClass) {}
      }
      singleton = (SingletonStrategy)clazz.newInstance();
      singleton.setSingletonClassName(QNameCache.class.getName());
    }
    catch (Exception defaultSingletonClass) {}
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.QName
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */