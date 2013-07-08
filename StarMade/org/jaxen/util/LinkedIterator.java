/*   1:    */package org.jaxen.util;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Iterator;
/*   5:    */import java.util.List;
/*   6:    */import java.util.NoSuchElementException;
/*   7:    */
/*  56:    *//**
/*  57:    */ * @deprecated
/*  58:    */ */
/*  59:    */public class LinkedIterator
/*  60:    */  implements Iterator
/*  61:    */{
/*  62:    */  private List iterators;
/*  63:    */  private int cur;
/*  64:    */  
/*  65:    */  public LinkedIterator()
/*  66:    */  {
/*  67: 67 */    this.iterators = new ArrayList();
/*  68: 68 */    this.cur = 0;
/*  69:    */  }
/*  70:    */  
/*  71:    */  public void addIterator(Iterator i)
/*  72:    */  {
/*  73: 73 */    this.iterators.add(i);
/*  74:    */  }
/*  75:    */  
/*  76:    */  public boolean hasNext()
/*  77:    */  {
/*  78: 78 */    boolean has = false;
/*  79:    */    
/*  80: 80 */    if (this.cur < this.iterators.size())
/*  81:    */    {
/*  82: 82 */      has = ((Iterator)this.iterators.get(this.cur)).hasNext();
/*  83:    */      
/*  84: 84 */      if ((!has) && (this.cur < this.iterators.size()))
/*  85:    */      {
/*  88: 88 */        this.cur += 1;
/*  89: 89 */        has = hasNext();
/*  90:    */      }
/*  91:    */    }
/*  92:    */    else
/*  93:    */    {
/*  94: 94 */      has = false;
/*  95:    */    }
/*  96:    */    
/*  97: 97 */    return has;
/*  98:    */  }
/*  99:    */  
/* 100:    */  public Object next()
/* 101:    */  {
/* 102:102 */    if (!hasNext())
/* 103:    */    {
/* 104:104 */      throw new NoSuchElementException();
/* 105:    */    }
/* 106:    */    
/* 107:107 */    return ((Iterator)this.iterators.get(this.cur)).next();
/* 108:    */  }
/* 109:    */  
/* 115:    */  public void remove()
/* 116:    */  {
/* 117:117 */    throw new UnsupportedOperationException();
/* 118:    */  }
/* 119:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.util.LinkedIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */