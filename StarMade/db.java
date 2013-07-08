/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import java.util.Comparator;
/*   3:    */import javax.vecmath.Matrix3f;
/*   4:    */import javax.vecmath.Vector3f;
/*   5:    */import org.schema.game.common.controller.SegmentController;
/*   6:    */import org.schema.game.common.data.world.Segment;
/*   7:    */
/* 117:    */public final class db
/* 118:    */  implements Comparator
/* 119:    */{
/* 120:    */  private final Vector3f a;
/* 121:121 */  private Vector3f b = new Vector3f();
/* 122:    */  
/* 123:123 */  public db() { this.a = new Vector3f(); }
/* 124:    */  
/* 126:    */  private synchronized int a(mr parammr1, mr parammr2)
/* 127:    */  {
/* 128:128 */    if ((parammr1 == parammr2) || (parammr1.equals(parammr2))) {
/* 129:129 */      return 0;
/* 130:    */    }
/* 131:    */    
/* 132:132 */    return Float.compare(a(parammr1.a, parammr1), a(parammr2.a, parammr2));
/* 133:    */  }
/* 134:    */  
/* 135:    */  private float a(q paramq, Segment paramSegment)
/* 136:    */  {
/* 137:137 */    this.b.set(paramq.a, paramq.b, paramq.c);
/* 138:138 */    paramSegment.a().getWorldTransformClient().basis.transform(this.b);
/* 139:139 */    this.b.add(paramSegment.a().getWorldTransformClient().origin);
/* 140:    */    
/* 141:141 */    this.b.sub(this.a);
/* 142:142 */    return this.b.lengthSquared();
/* 143:    */  }
/* 144:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     db
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */