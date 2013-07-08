/*  1:   */package com.google.code.tempusfugit;
/*  2:   */
/*  3:   */import java.util.Arrays;
/*  4:   */import java.util.List;
/*  5:   */
/* 21:   */public class CompositeFactory<T>
/* 22:   */  implements Factory<T>
/* 23:   */{
/* 24:   */  private final List<Factory<T>> factories;
/* 25:   */  
/* 26:   */  public CompositeFactory(Factory<T>... factories)
/* 27:   */  {
/* 28:28 */    this.factories = Arrays.asList(factories);
/* 29:   */  }
/* 30:   */  
/* 31:   */  public T create() throws FactoryException
/* 32:   */  {
/* 33:33 */    for (Factory<T> factory : this.factories) {
/* 34:   */      try {
/* 35:35 */        return factory.create();
/* 36:   */      }
/* 37:   */      catch (FactoryException e) {}
/* 38:   */    }
/* 39:   */    
/* 40:40 */    throw new FactoryException("unable to create any objects from factories");
/* 41:   */  }
/* 42:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.CompositeFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */