/*   1:    */import java.util.Random;
/*   2:    */import org.schema.game.common.controller.SegmentController;
/*   3:    */import org.schema.game.common.data.world.Segment;
/*   4:    */
/*  20:    */public final class uh
/*  21:    */  extends uj
/*  22:    */{
/*  23:    */  private uX[] a;
/*  24:    */  
/*  25:    */  public uh(long paramLong)
/*  26:    */  {
/*  27: 27 */    super(paramLong);
/*  28: 28 */    this.jdField_a_of_type_ArrayOfUX = new uX[9];
/*  29: 29 */    this.jdField_a_of_type_ArrayOfUX[0] = new uZ(128, 3, 73);
/*  30: 30 */    this.jdField_a_of_type_ArrayOfUX[1] = new uZ(87, 14, 73);
/*  31: 31 */    this.jdField_a_of_type_ArrayOfUX[2] = new uZ(133, 6, 73);
/*  32: 32 */    this.jdField_a_of_type_ArrayOfUX[3] = new uZ(72, 1, 73);
/*  33: 33 */    this.jdField_a_of_type_ArrayOfUX[4] = new uY(this, 0);
/*  34: 34 */    this.jdField_a_of_type_ArrayOfUX[5] = new uW(95, new short[] { 74, 74, 73 });
/*  35: 35 */    this.jdField_a_of_type_ArrayOfUX[6] = new uW(103, new short[] { 74, 74, 73 });
/*  36: 36 */    this.jdField_a_of_type_ArrayOfUX[7] = new uW(99, new short[] { 74, 74, 73 });
/*  37: 37 */    this.jdField_a_of_type_ArrayOfUX[8] = new uW(107, new short[] { 74, 74, 73 });
/*  38:    */  }
/*  39:    */  
/*  42:    */  public final void a(SegmentController paramSegmentController, Segment paramSegment)
/*  43:    */  {
/*  44: 44 */    if (!this.jdField_a_of_type_Boolean) {
/*  45: 45 */      this.jdField_a_of_type_UV = new uM(((jA)paramSegmentController).getSeed());
/*  46: 46 */      this.jdField_a_of_type_UV.a(this);
/*  47: 47 */      this.jdField_a_of_type_UV.b();
/*  48:    */      
/*  49: 49 */      a();
/*  50:    */      
/*  51: 51 */      this.jdField_a_of_type_Boolean = true;
/*  52:    */    }
/*  53:    */    
/*  54: 54 */    a(paramSegment);
/*  55:    */  }
/*  56:    */  
/*  60:    */  public final short a()
/*  61:    */  {
/*  62: 62 */    return 74;
/*  63:    */  }
/*  64:    */  
/*  67:    */  public final uX[] a()
/*  68:    */  {
/*  69: 69 */    return this.jdField_a_of_type_ArrayOfUX;
/*  70:    */  }
/*  71:    */  
/*  74:    */  public final short b()
/*  75:    */  {
/*  76: 76 */    return 73;
/*  77:    */  }
/*  78:    */  
/*  79:    */  public final short c()
/*  80:    */  {
/*  81: 81 */    return 74;
/*  82:    */  }
/*  83:    */  
/*  85:    */  public final void a(Random paramRandom)
/*  86:    */  {
/*  87:    */    int i;
/*  88: 88 */    if ((i = paramRandom.nextInt(10)) == 0) {
/*  89: 89 */      i = 3;
/*  90: 90 */    } else if (i < 3) {
/*  91: 91 */      i = 2;
/*  92:    */    } else {
/*  93: 93 */      i = 1;
/*  94:    */    }
/*  95: 95 */    uu[] arrayOfuu = new uu[i << 1];
/*  96: 96 */    int j = 0;
/*  97: 97 */    int k = 5;
/*  98: 98 */    for (int m = 0; m < i; m++) {
/*  99: 99 */      int n = paramRandom.nextInt(100) - 50;
/* 100:100 */      int i1 = paramRandom.nextInt(30);
/* 101:101 */      int i2 = paramRandom.nextInt(20) - 10;
/* 102:    */      
/* 103:103 */      ut localut = new ut(paramRandom.nextBoolean(), arrayOfuu, new q(n + -50, i2 + 20, n + -50), new q(n + 50, i1 + 60, n + 50), k--);
/* 104:104 */      arrayOfuu[(j++)] = localut;
/* 105:105 */      Object localObject = new q(n, i2 + 20 + 2, n);
/* 106:106 */      localObject = new uz((q)localObject, arrayOfuu, new q(((q)localObject).a - 1, ((q)localObject).b, ((q)localObject).c - 1), new q(((q)localObject).a + 1, ((q)localObject).b + 1, ((q)localObject).c + 1), 6);
/* 107:107 */      arrayOfuu[(j++)] = localObject;
/* 108:    */    }
/* 109:    */    
/* 110:110 */    for (m = 0; m < arrayOfuu.length; m++) {
/* 111:111 */      arrayOfuu[m].a();
/* 112:    */    }
/* 113:    */    
/* 114:114 */    this.jdField_a_of_type_UV.a(arrayOfuu);
/* 115:    */  }
/* 116:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     uh
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */