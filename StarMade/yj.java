/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.Vector;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.opengl.GL15;
/*     */ import org.lwjgl.opengl.GL20;
/*     */ 
/*     */ public final class yj
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  24 */   private IntBuffer jdField_a_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*  25 */   private IntBuffer jdField_b_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*     */   private FloatBuffer jdField_a_of_type_JavaNioFloatBuffer;
/*     */   private FloatBuffer jdField_b_of_type_JavaNioFloatBuffer;
/*  45 */   private int jdField_a_of_type_Int = 0;
/*     */ 
/*     */   public yj(int paramInt)
/*     */   {
/*  54 */     this.jdField_a_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(paramInt << 2);
/*     */ 
/*  56 */     this.jdField_b_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(paramInt << 2);
/*     */   }
/*     */ 
/*     */   public final void a(HashMap paramHashMap, ye paramye)
/*     */   {
/*  68 */     this.jdField_a_of_type_JavaNioFloatBuffer.rewind();
/*  69 */     this.jdField_b_of_type_JavaNioFloatBuffer.rewind();
/*  70 */     for (paramHashMap = paramHashMap.entrySet().iterator(); paramHashMap.hasNext(); )
/*     */     {
/*     */       Map.Entry localEntry;
/*     */       Vector localVector;
/*  74 */       if ((
/*  74 */         localVector = (Vector)(
/*  73 */         localEntry = (Map.Entry)paramHashMap.next())
/*  73 */         .getValue())
/*  74 */         .size() > 4)
/*  75 */         for (i = 0; i < localVector.size(); i++) {
/*  76 */           int j = Math.min(localVector.size() - 1, i);
/*  77 */           yi localyi1 = (yi)localVector.get(j);
/*  78 */           System.err.println("[BONE] Exception WARNING: vertex bone weight influence exceeded " + localEntry.getKey() + ": " + paramye.a().get(localyi1.b) + " -> " + localEntry.getKey());
/*     */         }
/*  80 */       for (int i = 0; i < 4; 
/*  82 */         i++) {
/*  83 */         int k = Math.min(localVector.size() - 1, i);
/*  84 */         yi localyi2 = (yi)localVector.get(k);
/*     */ 
/*  87 */         this.jdField_a_of_type_JavaNioFloatBuffer.put((localyi2.jdField_a_of_type_Int << 2) + i, localyi2.b);
/*     */ 
/*  89 */         this.jdField_b_of_type_JavaNioFloatBuffer.put((localyi2.jdField_a_of_type_Int << 2) + i, i < localVector.size() ? localyi2.jdField_a_of_type_Float : 0.0F);
/*     */       }
/*     */     }
/*     */ 
/*  93 */     d();
/*     */ 
/*  95 */     this.jdField_a_of_type_JavaNioFloatBuffer.rewind();
/*  96 */     this.jdField_b_of_type_JavaNioFloatBuffer.rewind();
/*     */   }
/*     */ 
/*     */   public final void a() {
/* 100 */     this.jdField_a_of_type_JavaNioFloatBuffer.rewind();
/* 101 */     this.jdField_b_of_type_JavaNioFloatBuffer.rewind();
/*     */ 
/* 103 */     GL15.glGenBuffers(this.jdField_a_of_type_JavaNioIntBuffer);
/* 104 */     GL15.glBindBuffer(34962, this.jdField_a_of_type_JavaNioIntBuffer.get(0));
/*     */ 
/* 106 */     GL15.glBufferData(34962, this.jdField_a_of_type_JavaNioFloatBuffer, 35044);
/* 107 */     GL15.glBindBuffer(34962, 0);
/*     */ 
/* 109 */     GL15.glGenBuffers(this.jdField_b_of_type_JavaNioIntBuffer);
/* 110 */     GL15.glBindBuffer(34962, this.jdField_b_of_type_JavaNioIntBuffer.get(0));
/*     */ 
/* 112 */     GL15.glBufferData(34962, this.jdField_b_of_type_JavaNioFloatBuffer, 35044);
/* 113 */     GL15.glBindBuffer(34962, 0);
/*     */   }
/*     */ 
/*     */   public final void b() {
/* 117 */     GL20.glEnableVertexAttribArray(3);
/* 118 */     GL20.glEnableVertexAttribArray(4);
/*     */ 
/* 120 */     GL15.glBindBuffer(34962, this.jdField_a_of_type_JavaNioIntBuffer.get(0));
/* 121 */     GL20.glVertexAttribPointer(3, 4, 5126, false, 0, 0L);
/*     */ 
/* 123 */     GL15.glBindBuffer(34962, this.jdField_b_of_type_JavaNioIntBuffer.get(0));
/* 124 */     GL20.glVertexAttribPointer(4, 4, 5126, false, 0, 0L);
/*     */   }
/*     */ 
/*     */   private void d()
/*     */   {
/* 131 */     int i = this.jdField_b_of_type_JavaNioFloatBuffer.capacity() / 4;
/* 132 */     this.jdField_b_of_type_JavaNioFloatBuffer.rewind();
/* 133 */     for (int j = 0; j < i; j++) {
/* 134 */       float f1 = this.jdField_b_of_type_JavaNioFloatBuffer.get();
/* 135 */       float f2 = this.jdField_b_of_type_JavaNioFloatBuffer.get();
/* 136 */       float f3 = this.jdField_b_of_type_JavaNioFloatBuffer.get();
/*     */       float f4;
/* 139 */       if ((
/* 139 */         f4 = this.jdField_b_of_type_JavaNioFloatBuffer.get()) > 
/* 139 */         0.01F)
/* 140 */         this.jdField_a_of_type_Int = Math.max(this.jdField_a_of_type_Int, 4);
/* 141 */       else if (f3 > 0.01F)
/* 142 */         this.jdField_a_of_type_Int = Math.max(this.jdField_a_of_type_Int, 3);
/* 143 */       else if (f2 > 0.01F)
/* 144 */         this.jdField_a_of_type_Int = Math.max(this.jdField_a_of_type_Int, 2);
/* 145 */       else if (f1 > 0.01F)
/* 146 */         this.jdField_a_of_type_Int = Math.max(this.jdField_a_of_type_Int, 1);
/*     */       float f5;
/* 150 */       if ((
/* 150 */         f5 = f1 + f2 + f3 + f4) != 
/* 150 */         1.0F) {
/* 151 */         this.jdField_b_of_type_JavaNioFloatBuffer.position(this.jdField_b_of_type_JavaNioFloatBuffer.position() - 4);
/* 152 */         this.jdField_b_of_type_JavaNioFloatBuffer.put(f1 / f5);
/* 153 */         this.jdField_b_of_type_JavaNioFloatBuffer.put(f2 / f5);
/* 154 */         this.jdField_b_of_type_JavaNioFloatBuffer.put(f3 / f5);
/* 155 */         this.jdField_b_of_type_JavaNioFloatBuffer.put(f4 / f5);
/*     */       }
/*     */     }
/* 158 */     this.jdField_b_of_type_JavaNioFloatBuffer.rewind();
/*     */   }
/*     */ 
/*     */   public static void c() {
/* 162 */     GL15.glBindBuffer(34962, 0);
/* 163 */     GL20.glDisableVertexAttribArray(3);
/* 164 */     GL20.glDisableVertexAttribArray(4);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yj
 * JD-Core Version:    0.6.2
 */