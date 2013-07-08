/*   1:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   2:    */import java.io.PrintStream;
/*   3:    */import java.io.Serializable;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */import java.nio.IntBuffer;
/*   6:    */import java.util.HashMap;
/*   7:    */import java.util.Iterator;
/*   8:    */import java.util.Map.Entry;
/*   9:    */import java.util.Set;
/*  10:    */import java.util.Vector;
/*  11:    */import org.lwjgl.BufferUtils;
/*  12:    */import org.lwjgl.opengl.GL15;
/*  13:    */import org.lwjgl.opengl.GL20;
/*  14:    */
/*  20:    */public final class yj
/*  21:    */  implements Serializable
/*  22:    */{
/*  23:    */  private static final long serialVersionUID = 1L;
/*  24: 24 */  private IntBuffer jdField_a_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*  25: 25 */  private IntBuffer jdField_b_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*  26:    */  
/*  32:    */  private FloatBuffer jdField_a_of_type_JavaNioFloatBuffer;
/*  33:    */  
/*  39:    */  private FloatBuffer jdField_b_of_type_JavaNioFloatBuffer;
/*  40:    */  
/*  45: 45 */  private int jdField_a_of_type_Int = 0;
/*  46:    */  
/*  52:    */  public yj(int paramInt)
/*  53:    */  {
/*  54: 54 */    this.jdField_a_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(paramInt << 2);
/*  55:    */    
/*  56: 56 */    this.jdField_b_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(paramInt << 2);
/*  57:    */  }
/*  58:    */  
/*  66:    */  public final void a(HashMap paramHashMap, ye paramye)
/*  67:    */  {
/*  68: 68 */    this.jdField_a_of_type_JavaNioFloatBuffer.rewind();
/*  69: 69 */    this.jdField_b_of_type_JavaNioFloatBuffer.rewind();
/*  70: 70 */    for (paramHashMap = paramHashMap.entrySet().iterator(); paramHashMap.hasNext();)
/*  71:    */    {
/*  72:    */      Map.Entry localEntry;
/*  73:    */      Vector localVector;
/*  74: 74 */      if ((localVector = (Vector)(localEntry = (Map.Entry)paramHashMap.next()).getValue()).size() > 4)
/*  75: 75 */        for (i = 0; i < localVector.size(); i++) {
/*  76: 76 */          int j = Math.min(localVector.size() - 1, i);
/*  77: 77 */          yi localyi1 = (yi)localVector.get(j);
/*  78: 78 */          System.err.println("[BONE] Exception WARNING: vertex bone weight influence exceeded " + localEntry.getKey() + ": " + paramye.a().get(localyi1.b) + " -> " + localEntry.getKey());
/*  79:    */        }
/*  80: 80 */      for (int i = 0; i < 4; 
/*  81:    */          
/*  82: 82 */          i++) {
/*  83: 83 */        int k = Math.min(localVector.size() - 1, i);
/*  84: 84 */        yi localyi2 = (yi)localVector.get(k);
/*  85:    */        
/*  87: 87 */        this.jdField_a_of_type_JavaNioFloatBuffer.put((localyi2.jdField_a_of_type_Int << 2) + i, localyi2.b);
/*  88:    */        
/*  89: 89 */        this.jdField_b_of_type_JavaNioFloatBuffer.put((localyi2.jdField_a_of_type_Int << 2) + i, i < localVector.size() ? localyi2.jdField_a_of_type_Float : 0.0F);
/*  90:    */      }
/*  91:    */    }
/*  92:    */    
/*  93: 93 */    d();
/*  94:    */    
/*  95: 95 */    this.jdField_a_of_type_JavaNioFloatBuffer.rewind();
/*  96: 96 */    this.jdField_b_of_type_JavaNioFloatBuffer.rewind();
/*  97:    */  }
/*  98:    */  
/*  99:    */  public final void a() {
/* 100:100 */    this.jdField_a_of_type_JavaNioFloatBuffer.rewind();
/* 101:101 */    this.jdField_b_of_type_JavaNioFloatBuffer.rewind();
/* 102:    */    
/* 103:103 */    GL15.glGenBuffers(this.jdField_a_of_type_JavaNioIntBuffer);
/* 104:104 */    GL15.glBindBuffer(34962, this.jdField_a_of_type_JavaNioIntBuffer.get(0));
/* 105:    */    
/* 106:106 */    GL15.glBufferData(34962, this.jdField_a_of_type_JavaNioFloatBuffer, 35044);
/* 107:107 */    GL15.glBindBuffer(34962, 0);
/* 108:    */    
/* 109:109 */    GL15.glGenBuffers(this.jdField_b_of_type_JavaNioIntBuffer);
/* 110:110 */    GL15.glBindBuffer(34962, this.jdField_b_of_type_JavaNioIntBuffer.get(0));
/* 111:    */    
/* 112:112 */    GL15.glBufferData(34962, this.jdField_b_of_type_JavaNioFloatBuffer, 35044);
/* 113:113 */    GL15.glBindBuffer(34962, 0);
/* 114:    */  }
/* 115:    */  
/* 116:    */  public final void b() {
/* 117:117 */    GL20.glEnableVertexAttribArray(3);
/* 118:118 */    GL20.glEnableVertexAttribArray(4);
/* 119:    */    
/* 120:120 */    GL15.glBindBuffer(34962, this.jdField_a_of_type_JavaNioIntBuffer.get(0));
/* 121:121 */    GL20.glVertexAttribPointer(3, 4, 5126, false, 0, 0L);
/* 122:    */    
/* 123:123 */    GL15.glBindBuffer(34962, this.jdField_b_of_type_JavaNioIntBuffer.get(0));
/* 124:124 */    GL20.glVertexAttribPointer(4, 4, 5126, false, 0, 0L);
/* 125:    */  }
/* 126:    */  
/* 129:    */  private void d()
/* 130:    */  {
/* 131:131 */    int i = this.jdField_b_of_type_JavaNioFloatBuffer.capacity() / 4;
/* 132:132 */    this.jdField_b_of_type_JavaNioFloatBuffer.rewind();
/* 133:133 */    for (int j = 0; j < i; j++) {
/* 134:134 */      float f1 = this.jdField_b_of_type_JavaNioFloatBuffer.get();
/* 135:135 */      float f2 = this.jdField_b_of_type_JavaNioFloatBuffer.get();
/* 136:136 */      float f3 = this.jdField_b_of_type_JavaNioFloatBuffer.get();
/* 137:    */      
/* 138:    */      float f4;
/* 139:139 */      if ((f4 = this.jdField_b_of_type_JavaNioFloatBuffer.get()) > 0.01F) {
/* 140:140 */        this.jdField_a_of_type_Int = Math.max(this.jdField_a_of_type_Int, 4);
/* 141:141 */      } else if (f3 > 0.01F) {
/* 142:142 */        this.jdField_a_of_type_Int = Math.max(this.jdField_a_of_type_Int, 3);
/* 143:143 */      } else if (f2 > 0.01F) {
/* 144:144 */        this.jdField_a_of_type_Int = Math.max(this.jdField_a_of_type_Int, 2);
/* 145:145 */      } else if (f1 > 0.01F) {
/* 146:146 */        this.jdField_a_of_type_Int = Math.max(this.jdField_a_of_type_Int, 1);
/* 147:    */      }
/* 148:    */      
/* 149:    */      float f5;
/* 150:150 */      if ((f5 = f1 + f2 + f3 + f4) != 1.0F) {
/* 151:151 */        this.jdField_b_of_type_JavaNioFloatBuffer.position(this.jdField_b_of_type_JavaNioFloatBuffer.position() - 4);
/* 152:152 */        this.jdField_b_of_type_JavaNioFloatBuffer.put(f1 / f5);
/* 153:153 */        this.jdField_b_of_type_JavaNioFloatBuffer.put(f2 / f5);
/* 154:154 */        this.jdField_b_of_type_JavaNioFloatBuffer.put(f3 / f5);
/* 155:155 */        this.jdField_b_of_type_JavaNioFloatBuffer.put(f4 / f5);
/* 156:    */      }
/* 157:    */    }
/* 158:158 */    this.jdField_b_of_type_JavaNioFloatBuffer.rewind();
/* 159:    */  }
/* 160:    */  
/* 161:    */  public static void c() {
/* 162:162 */    GL15.glBindBuffer(34962, 0);
/* 163:163 */    GL20.glDisableVertexAttribArray(3);
/* 164:164 */    GL20.glDisableVertexAttribArray(4);
/* 165:    */  }
/* 166:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yj
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */