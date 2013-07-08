/*  1:   */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*  2:   */import java.io.PrintStream;
/*  3:   */import org.schema.game.common.data.element.Element;
/*  4:   */import org.schema.schine.network.NetworkStateContainer;
/*  5:   */
/*  6:   */public final class cs
/*  7:   */{
/*  8:   */  private final ct a;
/*  9:   */  public q a;
/* 10:   */  public q b;
/* 11:   */  
/* 12:   */  public cs(ct paramct)
/* 13:   */  {
/* 14:14 */    this.jdField_a_of_type_Ct = paramct;
/* 15:   */  }
/* 16:   */  
/* 20:20 */  private q c = new q();
/* 21:21 */  private q d = new q();
/* 22:   */  
/* 23:   */  public final void a(int paramInt)
/* 24:   */  {
/* 25:25 */    if (this.jdField_a_of_type_Q != null)
/* 26:   */    {
/* 27:27 */      if ((paramInt = (mv)this.jdField_a_of_type_Ct.getLocalAndRemoteObjectContainer().getLocalObjects().get(paramInt)) == null) {
/* 28:28 */        this.jdField_a_of_type_Ct.a().b = true;
/* 29:29 */        return;
/* 30:   */      }
/* 31:31 */      paramInt = paramInt.a();
/* 32:32 */      System.err.println("UPDATE WAYPOINT: " + this.jdField_a_of_type_Q);
/* 33:   */      
/* 34:34 */      if (this.b == null) {
/* 35:35 */        this.b = new q();
/* 36:   */      }
/* 37:   */      
/* 38:38 */      if (paramInt.equals(this.jdField_a_of_type_Q)) {
/* 39:39 */        a(null);
/* 40:40 */        return;
/* 41:   */      }
/* 42:42 */      this.d.b(0, 0, 0);
/* 43:43 */      for (int i = 0; i < Element.DIRECTIONSi.length; i++)
/* 44:   */      {
/* 45:45 */        this.c.b(paramInt, Element.DIRECTIONSi[i]);
/* 46:   */        
/* 47:47 */        if (this.c.equals(this.jdField_a_of_type_Q)) {
/* 48:48 */          this.b.b(paramInt, Element.DIRECTIONSi[i]);
/* 49:49 */          break;
/* 50:   */        }
/* 51:   */        
/* 53:53 */        System.err.println("CHECKING WAYPOINT: " + paramInt + ": " + this.c);
/* 54:54 */        this.c.c(this.jdField_a_of_type_Q);
/* 55:55 */        if ((this.d.a() == 0.0F) || (this.c.a() < this.d.a())) {
/* 56:56 */          this.b.b(paramInt, Element.DIRECTIONSi[i]);
/* 57:57 */          this.d.b(this.c);
/* 58:   */        } else {
/* 59:59 */          System.err.println("NOT TAKING: " + this.c.a() + " / " + this.d.a());
/* 60:   */        }
/* 61:   */      }
/* 62:62 */      System.err.println("NEAREST WAYPOINT " + this.b);
/* 63:   */    }
/* 64:   */  }
/* 65:   */  
/* 80:   */  public final void a(q paramq)
/* 81:   */  {
/* 82:82 */    System.err.println("SETTING WAYPOINT: " + paramq);
/* 83:83 */    this.jdField_a_of_type_Q = paramq;
/* 84:84 */    this.b = null;
/* 85:85 */    a(this.jdField_a_of_type_Ct.a());
/* 86:   */  }
/* 87:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     cs
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */