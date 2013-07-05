/*    */ import it.unimi.dsi.fastutil.shorts.Short2IntArrayMap;
/*    */ import it.unimi.dsi.fastutil.shorts.Short2IntMap.Entry;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.util.Arrays;
/*    */ import java.util.HashSet;
/*    */ import java.util.Iterator;
/*    */ import org.schema.game.common.data.element.ElementInformation;
/*    */ import org.schema.game.common.data.element.ElementKeyMap;
/*    */ 
/*    */ public class jx
/*    */ {
/*    */   private int[] jdField_a_of_type_ArrayOfInt;
/*    */ 
/*    */   public jx()
/*    */   {
/* 19 */     this.jdField_a_of_type_ArrayOfInt = new int[ElementKeyMap.highestType + 1];
/*    */   }
/*    */ 
/*    */   public final void a(short paramShort)
/*    */   {
/* 28 */     this.jdField_a_of_type_ArrayOfInt[paramShort] -= 1;
/*    */   }
/*    */   public final int a(short paramShort) {
/* 31 */     return this.jdField_a_of_type_ArrayOfInt[paramShort];
/*    */   }
/*    */   public final void b(short paramShort) {
/* 34 */     if ((!jdField_a_of_type_Boolean) && (paramShort >= this.jdField_a_of_type_ArrayOfInt.length)) throw new AssertionError("ERROR: " + paramShort + "/" + this.jdField_a_of_type_ArrayOfInt.length + "  (" + ElementKeyMap.highestType + ")");
/* 35 */     this.jdField_a_of_type_ArrayOfInt[paramShort] += 1;
/*    */   }
/*    */ 
/*    */   public final void a()
/*    */   {
/* 41 */     Arrays.fill(this.jdField_a_of_type_ArrayOfInt, 0);
/*    */   }
/*    */ 
/*    */   public final int a()
/*    */   {
/* 47 */     long l = 0L;
/* 48 */     for (Iterator localIterator = ElementKeyMap.keySet.iterator(); localIterator.hasNext(); ) { short s = ((Short)localIterator.next()).shortValue();
/* 49 */       l += ElementKeyMap.getInfo(s).getPrice() * this.jdField_a_of_type_ArrayOfInt[s];
/*    */     }
/* 51 */     return (int)Math.min(2147483647L, l);
/*    */   }
/*    */   public final void a(DataOutputStream paramDataOutputStream) {
/* 54 */     int i = 0;
/* 55 */     for (int j = 0; j < this.jdField_a_of_type_ArrayOfInt.length; j++) {
/* 56 */       if (this.jdField_a_of_type_ArrayOfInt[j] > 0) {
/* 57 */         i++;
/*    */       }
/*    */     }
/* 60 */     paramDataOutputStream.writeInt(i);
/* 61 */     for (j = 0; j < this.jdField_a_of_type_ArrayOfInt.length; j++)
/* 62 */       if (this.jdField_a_of_type_ArrayOfInt[j] > 0) {
/* 63 */         paramDataOutputStream.writeShort(j);
/* 64 */         paramDataOutputStream.writeInt(this.jdField_a_of_type_ArrayOfInt[j]);
/*    */       }
/*    */   }
/*    */ 
/*    */   public final void a(DataInputStream paramDataInputStream) {
/* 69 */     int i = paramDataInputStream.readInt();
/* 70 */     for (int j = 0; j < i; j++) {
/* 71 */       int k = paramDataInputStream.readShort();
/* 72 */       int m = paramDataInputStream.readInt();
/* 73 */       this.jdField_a_of_type_ArrayOfInt[k] = m;
/*    */     }
/*    */   }
/*    */ 
/*    */   public final void a(Short2IntArrayMap paramShort2IntArrayMap) {
/* 78 */     for (Short2IntMap.Entry localEntry : paramShort2IntArrayMap.short2IntEntrySet())
/* 79 */       this.jdField_a_of_type_ArrayOfInt[localEntry.getShortKey()] = localEntry.getIntValue();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     jx
 * JD-Core Version:    0.6.2
 */