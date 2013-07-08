/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import java.io.PrintStream;
/*   3:    */import java.util.ArrayList;
/*   4:    */import javax.vecmath.Vector3f;
/*   5:    */import org.schema.schine.graphicsengine.camera.Camera;
/*   6:    */
/*  26:    */public final class ez
/*  27:    */  implements xg
/*  28:    */{
/*  29:    */  private ey[] jdField_a_of_type_ArrayOfEy;
/*  30:    */  private ze jdField_a_of_type_Ze;
/*  31:    */  private eE jdField_a_of_type_EE;
/*  32:    */  private zd jdField_a_of_type_Zd;
/*  33: 33 */  private boolean jdField_a_of_type_Boolean = true;
/*  34:    */  
/*  35:    */  private ct jdField_a_of_type_Ct;
/*  36:    */  
/*  37:    */  public final ArrayList a;
/*  38:    */  
/*  39: 39 */  private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*  40:    */  
/*  41:    */  public ez(ct paramct)
/*  42:    */  {
/*  43: 37 */    this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*  44:    */    
/*  49: 43 */    this.jdField_a_of_type_Ct = paramct;
/*  50: 44 */    this.jdField_a_of_type_ArrayOfEy = new ey[32];
/*  51: 45 */    for (paramct = 0; paramct < 32; paramct++) {
/*  52: 46 */      this.jdField_a_of_type_ArrayOfEy[paramct] = new ey(xe.a().getWorldTransform());
/*  53:    */    }
/*  54:    */    
/*  55: 49 */    this.jdField_a_of_type_Ze = new ze();
/*  56:    */    
/*  58: 52 */    this.jdField_a_of_type_EE = new eE(this.jdField_a_of_type_ArrayOfEy);
/*  59: 53 */    this.jdField_a_of_type_Zd = new zd(this.jdField_a_of_type_EE, 16.0F);
/*  60:    */  }
/*  61:    */  
/*  70:    */  public final void a() {}
/*  71:    */  
/*  79:    */  public final void b()
/*  80:    */  {
/*  81: 75 */    if (this.jdField_a_of_type_Boolean) {
/*  82: 76 */      c();
/*  83:    */    }
/*  84: 78 */    this.jdField_a_of_type_Zd.b();
/*  85:    */    
/*  87: 81 */    for (int i = 0; i < 32; i++) {
/*  88: 82 */      if (this.jdField_a_of_type_ArrayOfEy[i].jdField_a_of_type_Boolean) {
/*  89: 83 */        this.jdField_a_of_type_Ze.a(this.jdField_a_of_type_ArrayOfEy[i]);
/*  90: 84 */        this.jdField_a_of_type_Ze.b();
/*  91:    */      }
/*  92:    */    }
/*  93:    */  }
/*  94:    */  
/* 115:    */  public final void c()
/* 116:    */  {
/* 117:111 */    this.jdField_a_of_type_Ze.c();
/* 118:112 */    this.jdField_a_of_type_Boolean = false;
/* 119:    */  }
/* 120:    */  
/* 121:115 */  private void a(Transform paramTransform) { for (int i = 0; i < 32; i++) {
/* 122:116 */      if (this.jdField_a_of_type_ArrayOfEy[i].jdField_a_of_type_ComBulletphysicsLinearmathTransform == paramTransform) {
/* 123:117 */        this.jdField_a_of_type_ArrayOfEy[i].jdField_b_of_type_Boolean = true;
/* 124:118 */        this.jdField_a_of_type_Ct.a().a().a(this.jdField_a_of_type_ArrayOfEy[i].jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin, 50.0F);
/* 125:119 */        (paramTransform = this.jdField_a_of_type_EE).jdField_a_of_type_ArrayOfEy[i].jdField_a_of_type_Int = ((paramTransform.jdField_a_of_type_ArrayOfEy[i].jdField_a_of_type_Int + 1) % 100000);
/* 126:120 */        return;
/* 127:    */      }
/* 128:    */    }
/* 129:    */  }
/* 130:    */  
/* 156:    */  public final void a(xq paramxq)
/* 157:    */  {
/* 158:152 */    synchronized (this.jdField_a_of_type_JavaUtilArrayList) {
/* 159:153 */      while (!this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) {
/* 160:    */        Object localObject1;
/* 161:155 */        if ((localObject1 = (eA)this.jdField_a_of_type_JavaUtilArrayList.remove(0)).jdField_a_of_type_Boolean) {
/* 162:156 */          Object localObject2 = ((eA)localObject1).jdField_a_of_type_ComBulletphysicsLinearmathTransform;localObject1 = this; for (Object localObject3 = 0;; localObject3++) { if (localObject3 >= 32) break label204; if (!localObject1.jdField_a_of_type_ArrayOfEy[localObject3].jdField_a_of_type_Boolean) { System.err.println("[MISSILE] STARTING NEW TRAIL " + ((Transform)localObject2).origin);Object localObject4 = localObject2;(localObject2 = localObject1.jdField_a_of_type_ArrayOfEy[localObject3]).b();((ey)localObject2).jdField_a_of_type_ComBulletphysicsLinearmathTransform = localObject4;((ey)localObject2).jdField_b_of_type_ComBulletphysicsLinearmathTransform = null;((ey)localObject2).jdField_b_of_type_Boolean = false;((ey)localObject2).jdField_a_of_type_Boolean = true;localObject4 = localObject3; eE tmp143_136 = ((ez)localObject1).jdField_a_of_type_EE;i = tmp143_136.a((localObject2 = tmp143_136).jdField_a_of_type_ArrayOfEy[localObject4].jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin, ((eE)localObject2).jdField_a_of_type_JavaxVecmathVector3f);localObject2.jdField_a_of_type_ArrayOfEy[localObject4].jdField_a_of_type_Int = i;((yW)localObject2).a.c(i, localObject4, i, 0.0F);break;
/* 163:    */            } } } else { label204:
/* 164:158 */          a(i.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 165:    */        }
/* 166:    */      }
/* 167:    */    }
/* 168:    */    
/* 172:166 */    for (int i = 0; i < 32;)
/* 173:    */    {
/* 174:168 */      if (this.jdField_a_of_type_ArrayOfEy[i].jdField_a_of_type_Boolean)
/* 175:169 */        this.jdField_a_of_type_ArrayOfEy[i].a(paramxq);
/* 176:170 */      i++;
/* 177:    */    }
/* 178:    */    
/* 180:174 */    this.jdField_a_of_type_JavaxVecmathVector3f.set(xe.a().a());
/* 181:    */    
/* 183:177 */    if (this.jdField_a_of_type_Ze != null) {
/* 184:178 */      this.jdField_a_of_type_Ze.a(paramxq);
/* 185:    */    }
/* 186:    */    
/* 187:181 */    this.jdField_a_of_type_EE.a(paramxq);
/* 188:    */  }
/* 189:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ez
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */