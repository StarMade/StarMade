/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import java.util.ArrayList;
/*   3:    */import java.util.Iterator;
/*   4:    */import javax.vecmath.Vector3f;
/*   5:    */
/*  27:    */public final class ac
/*  28:    */  extends U
/*  29:    */{
/*  30:    */  private wT a;
/*  31:    */  
/*  32:    */  public ac(ct paramct)
/*  33:    */  {
/*  34: 34 */    super(paramct);
/*  35:    */  }
/*  36:    */  
/*  44:    */  public final void b(boolean paramBoolean)
/*  45:    */  {
/*  46: 46 */    wV.a = !paramBoolean;
/*  47:    */    
/*  48: 48 */    if (paramBoolean)
/*  49:    */    {
/*  56: 56 */      a().b(false);
/*  57: 57 */      int i = 0;
/*  58: 58 */      Iterator localIterator; synchronized (a().b()) {
/*  59: 59 */        for (localIterator = a().b().iterator(); localIterator.hasNext();) {
/*  60: 60 */          if (((H)localIterator.next() instanceof D)) {
/*  61: 61 */            i = 1;
/*  62:    */          }
/*  63:    */        }
/*  64:    */      }
/*  65: 65 */      if (i == 0) {
/*  66: 66 */        ??? = new D(a());
/*  67: 67 */        a().b().add(???);
/*  68:    */      }
/*  69:    */    }
/*  70:    */    
/*  71: 71 */    super.b(paramBoolean);
/*  72:    */  }
/*  73:    */  
/*  96:    */  public final void a(xq paramxq)
/*  97:    */  {
/*  98: 98 */    super.a(paramxq);
/*  99: 99 */    wV.a = false;
/* 100:100 */    if (this.a == null) {
/* 101:101 */      ac localac = this;(localObject = new Transform()).setIdentity();Object localObject = new xb(new ad((Transform)localObject));localac.a().a(); Vector3f localVector3f; (localVector3f = new Vector3f(cW.a().a())).negate();localVector3f.normalize();localac.a = new wT((xb)localObject, new Vector3f(125.0F, 70.0F, 223.0F), localVector3f);
/* 102:    */    }
/* 103:103 */    if (xe.a() != this.a) {
/* 104:104 */      xe.a(this.a);
/* 105:105 */      this.a.a(paramxq);
/* 106:    */    }
/* 107:    */  }
/* 108:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ac
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */