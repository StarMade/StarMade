package org.schema.game.common.data.element;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

public class ElementCategory
{
  private ElementCategory parent;
  private final Class category;
  private final ArrayList children = new ArrayList();
  private final ArrayList infoElements = new ArrayList();
  
  public ElementCategory(Class paramClass, ElementCategory paramElementCategory)
  {
    this.category = paramClass;
    this.parent = paramElementCategory;
  }
  
  public Class getCategory()
  {
    return this.category;
  }
  
  public ArrayList getChildren()
  {
    return this.children;
  }
  
  public ArrayList getInfoElements()
  {
    return this.infoElements;
  }
  
  public boolean hasChildren()
  {
    return !getChildren().isEmpty();
  }
  
  public boolean isRoot()
  {
    return this.parent == null;
  }
  
  public void print()
  {
    printRec(1);
  }
  
  private void printItems(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; i < paramInt; i++) {
      localStringBuilder.append("-");
    }
    for (i = 0; i < getInfoElements().size(); i++)
    {
      paramInt = (ElementInformation)getInfoElements().get(i);
      System.err.println(localStringBuilder.toString() + " " + paramInt.getName());
    }
  }
  
  private void printRec(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; i < paramInt; i++) {
      localStringBuilder.append("#");
    }
    System.err.println(localStringBuilder.toString() + " " + this.category.getSimpleName());
    printItems(paramInt);
    for (i = 0; i < this.children.size(); i++) {
      ((ElementCategory)this.children.get(i)).printRec(paramInt + 1);
    }
  }
  
  public String toString()
  {
    return this.category.getSimpleName();
  }
  
  public void clear()
  {
    this.children.clear();
    this.infoElements.clear();
  }
  
  public void insertRecusrive(ElementInformation paramElementInformation)
  {
    if (this.category == paramElementInformation.getType())
    {
      this.infoElements.add(paramElementInformation);
      return;
    }
    Iterator localIterator = getChildren().iterator();
    while (localIterator.hasNext()) {
      ((ElementCategory)localIterator.next()).insertRecusrive(paramElementInformation);
    }
  }
  
  public void removeRecursive(ElementInformation paramElementInformation)
  {
    if (this.infoElements.contains(paramElementInformation))
    {
      this.infoElements.remove(paramElementInformation);
      System.err.println("REMOVED FROM CATEGORY: " + paramElementInformation.getName());
      return;
    }
    Iterator localIterator = getChildren().iterator();
    while (localIterator.hasNext()) {
      ((ElementCategory)localIterator.next()).removeRecursive(paramElementInformation);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.ElementCategory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */