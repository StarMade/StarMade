/*   1:    */import com.bulletphysics.linearmath.AabbUtil2;
/*   2:    */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */
/*  13:    */public abstract class uu
/*  14:    */{
/*  15:    */  protected final q a;
/*  16:    */  protected final q b;
/*  17:    */  public final int a;
/*  18:    */  private uu[] a;
/*  19:    */  private final uu[] jdField_b_of_type_ArrayOfUu;
/*  20:    */  private final int jdField_b_of_type_Int;
/*  21:    */  
/*  22:    */  public uu(uu[] paramArrayOfuu, q paramq1, q paramq2, int paramInt1, int paramInt2)
/*  23:    */  {
/*  24: 24 */    this.jdField_b_of_type_ArrayOfUu = paramArrayOfuu;
/*  25:    */    
/*  26: 26 */    this.jdField_a_of_type_Int = paramInt1;
/*  27:    */    
/*  28: 28 */    this.jdField_b_of_type_Int = paramInt2;
/*  29:    */    
/*  31: 31 */    if (paramInt2 % 2 != 0) {
/*  32: 32 */      paramArrayOfuu = paramq1.jdField_a_of_type_Int;paramq1.jdField_a_of_type_Int = paramq1.c;paramq1.c = paramArrayOfuu;
/*  33:    */      
/*  34: 34 */      paramArrayOfuu = paramq2.jdField_a_of_type_Int;paramq2.jdField_a_of_type_Int = paramq2.c;paramq2.c = paramArrayOfuu;
/*  35:    */    }
/*  36: 36 */    a(paramq1, paramq2);
/*  37:    */    
/*  38: 38 */    this.jdField_a_of_type_Q = paramq1;
/*  39: 39 */    this.jdField_b_of_type_Q = paramq2;
/*  40:    */  }
/*  41:    */  
/*  42:    */  public final void a() {
/*  43: 43 */    ObjectArrayList localObjectArrayList = new ObjectArrayList();
/*  44:    */    
/*  45: 45 */    Vector3f localVector3f1 = new Vector3f(this.jdField_a_of_type_Q.jdField_a_of_type_Int, this.jdField_a_of_type_Q.jdField_b_of_type_Int, this.jdField_a_of_type_Q.c);
/*  46: 46 */    Vector3f localVector3f2 = new Vector3f(this.jdField_b_of_type_Q.jdField_a_of_type_Int, this.jdField_b_of_type_Q.jdField_b_of_type_Int, this.jdField_b_of_type_Q.c);
/*  47:    */    
/*  48: 48 */    Vector3f localVector3f3 = new Vector3f();
/*  49: 49 */    Vector3f localVector3f4 = new Vector3f();
/*  50: 50 */    for (int i = 0; i < this.jdField_b_of_type_ArrayOfUu.length; i++) {
/*  51: 51 */      uu localuu = this.jdField_b_of_type_ArrayOfUu[i];
/*  52: 52 */      localVector3f3.set(localuu.jdField_a_of_type_Q.jdField_a_of_type_Int, localuu.jdField_a_of_type_Q.jdField_b_of_type_Int, localuu.jdField_a_of_type_Q.c);
/*  53: 53 */      localVector3f4.set(localuu.jdField_b_of_type_Q.jdField_a_of_type_Int, localuu.jdField_b_of_type_Q.jdField_b_of_type_Int, localuu.jdField_b_of_type_Q.c);
/*  54:    */      
/*  55: 55 */      if ((localuu != this) && (AabbUtil2.testAabbAgainstAabb2(localVector3f1, localVector3f2, localVector3f3, localVector3f4))) {
/*  56: 56 */        localObjectArrayList.add(localuu);
/*  57:    */      }
/*  58:    */    }
/*  59:    */    
/*  60: 60 */    this.jdField_a_of_type_ArrayOfUu = new uu[localObjectArrayList.size()];
/*  61: 61 */    for (i = 0; i < localObjectArrayList.size(); i++) {
/*  62: 62 */      this.jdField_a_of_type_ArrayOfUu[i] = ((uu)localObjectArrayList.get(i));
/*  63:    */    }
/*  64:    */  }
/*  65:    */  
/*  67:    */  public boolean a(q paramq)
/*  68:    */  {
/*  69: 69 */    return (paramq.jdField_a_of_type_Int < this.jdField_b_of_type_Q.jdField_a_of_type_Int) && (paramq.jdField_a_of_type_Int >= this.jdField_a_of_type_Q.jdField_a_of_type_Int) && (paramq.jdField_b_of_type_Int < this.jdField_b_of_type_Q.jdField_b_of_type_Int) && (paramq.jdField_b_of_type_Int >= this.jdField_a_of_type_Q.jdField_b_of_type_Int) && (paramq.c < this.jdField_b_of_type_Q.c) && (paramq.c >= this.jdField_a_of_type_Q.c);
/*  70:    */  }
/*  71:    */  
/*  72:    */  public final short b(q paramq)
/*  73:    */  {
/*  74: 74 */    for (int i = 0; i < this.jdField_a_of_type_ArrayOfUu.length; i++) {
/*  75: 75 */      if ((this.jdField_a_of_type_ArrayOfUu[i].jdField_a_of_type_Int > this.jdField_a_of_type_Int) && (this.jdField_a_of_type_ArrayOfUu[i].a(paramq)))
/*  76:    */      {
/*  78: 78 */        if ((j = this.jdField_a_of_type_ArrayOfUu[i].b(paramq)) != 32767) {
/*  79: 79 */          return j;
/*  80:    */        }
/*  81:    */      }
/*  82:    */    }
/*  83: 83 */    i = a(paramq);
/*  84: 84 */    for (int j = 0; (j < this.jdField_a_of_type_ArrayOfUu.length) && ((i == 0) || (i == 32767)); j++) {
/*  85: 85 */      if ((this.jdField_a_of_type_ArrayOfUu[j].jdField_a_of_type_Int == this.jdField_a_of_type_Int) && (this.jdField_a_of_type_ArrayOfUu[j].a(paramq))) {
/*  86: 86 */        i = this.jdField_a_of_type_ArrayOfUu[j].a(paramq);
/*  87:    */      }
/*  88:    */    }
/*  89: 89 */    return i;
/*  90:    */  }
/*  91:    */  
/*  95:    */  protected final q a(q paramq1, q paramq2)
/*  96:    */  {
/*  97: 97 */    paramq2.b(paramq1);
/*  98: 98 */    paramq2.c(this.jdField_a_of_type_Q);
/*  99:    */    
/* 100:100 */    switch (this.jdField_b_of_type_Int)
/* 101:    */    {
/* 104:    */    case 0: 
/* 105:105 */      break;
/* 106:    */    case 1: 
/* 107:107 */      paramq1 = paramq2.jdField_a_of_type_Int;paramq2.jdField_a_of_type_Int = paramq2.c;paramq2.c = (paramq1 - 1);break;
/* 108:    */    case 2: 
/* 109:109 */      paramq2.c = (this.jdField_b_of_type_Q.c - this.jdField_a_of_type_Q.c - paramq2.c);break;
/* 110:    */    case 3: 
/* 111:111 */      paramq1 = paramq2.jdField_a_of_type_Int;paramq2.jdField_a_of_type_Int = paramq2.c;paramq2.c = paramq1;paramq2.c = (this.jdField_b_of_type_Q.c - this.jdField_a_of_type_Q.c - paramq2.c);
/* 112:    */    }
/* 113:    */    
/* 114:114 */    return paramq2; }
/* 115:    */  
/* 116:    */  protected abstract short a(q paramq);
/* 117:    */  
/* 118:    */  public static void a(q paramq1, q paramq2) { int i;
/* 119:119 */    if (paramq2.jdField_a_of_type_Int < paramq1.jdField_a_of_type_Int) {
/* 120:120 */      i = paramq2.jdField_a_of_type_Int + 1;
/* 121:121 */      paramq1.jdField_a_of_type_Int += 1;
/* 122:122 */      paramq1.jdField_a_of_type_Int = i;
/* 123:    */    }
/* 124:124 */    if (paramq2.jdField_b_of_type_Int < paramq1.jdField_b_of_type_Int) {
/* 125:125 */      i = paramq2.jdField_b_of_type_Int + 1;
/* 126:126 */      paramq1.jdField_b_of_type_Int += 1;
/* 127:127 */      paramq1.jdField_b_of_type_Int = i;
/* 128:    */    }
/* 129:129 */    if (paramq2.c < paramq1.c) {
/* 130:130 */      i = paramq2.c + 1;
/* 131:131 */      paramq1.c += 1;
/* 132:132 */      paramq1.c = i;
/* 133:    */    }
/* 134:    */  }
/* 135:    */  
/* 142:    */  public byte a(q paramq)
/* 143:    */  {
/* 144:144 */    return -1;
/* 145:    */  }
/* 146:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     uu
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */