/*   1:    */import java.util.Comparator;
/*   2:    */import javax.vecmath.Vector3f;
/*   3:    */
/*  88:    */public final class da
/*  89:    */  implements Comparator
/*  90:    */{
/*  91:    */  private final Vector3f a;
/*  92:    */  
/*  93:    */  public da()
/*  94:    */  {
/*  95: 95 */    new Vector3f();
/*  96:    */    
/*  97: 97 */    this.a = new Vector3f();
/*  98:    */  }
/*  99:    */  
/* 100:    */  public final synchronized int a(mr parammr1, mr parammr2)
/* 101:    */  {
/* 102:102 */    if ((parammr1 == parammr2) || (parammr1.equals(parammr2))) {
/* 103:103 */      return 0;
/* 104:    */    }
/* 105:    */    
/* 106:106 */    return Float.compare(parammr1.a, parammr2.a);
/* 107:    */  }
/* 108:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     da
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */