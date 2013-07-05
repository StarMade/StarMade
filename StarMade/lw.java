/*    */ import it.unimi.dsi.fastutil.shorts.Short2ObjectArrayMap;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ 
/*    */ public abstract class lw
/*    */ {
/*    */   public final short a;
/*    */   public final byte b;
/*    */ 
/*    */   public lw(byte paramByte, short paramShort)
/*    */   {
/* 26 */     this.b = paramByte;
/* 27 */     this.a = paramShort;
/*    */   }
/*    */ 
/*    */   public void b(DataOutputStream paramDataOutputStream) {
/* 31 */     paramDataOutputStream.writeByte(this.b);
/* 32 */     paramDataOutputStream.writeShort(this.a);
/* 33 */     a(paramDataOutputStream);
/*    */   }
/*    */ 
/*    */   protected abstract void a(DataInputStream paramDataInputStream);
/*    */ 
/*    */   protected abstract void a(DataOutputStream paramDataOutputStream);
/*    */ 
/*    */   public static lw a(DataInputStream paramDataInputStream)
/*    */   {
/*    */     byte b1;
/*    */     Object localObject;
/* 40 */     switch (
/* 41 */       b1 = paramDataInputStream.readByte())
/*    */     {
/*    */     case 1:
/* 43 */       localObject = new lu(b1, paramDataInputStream.readShort()); break;
/*    */     case 2:
/* 44 */       localObject = new ls(localObject, paramDataInputStream.readShort()); break;
/*    */     case 3:
/* 45 */       localObject = new lq(localObject, paramDataInputStream.readShort()); break;
/*    */     case 4:
/* 46 */       localObject = new lv(localObject, paramDataInputStream.readShort()); break;
/*    */     case 5:
/* 47 */       localObject = new lt(localObject, paramDataInputStream.readShort()); break;
/*    */     case 6:
/* 48 */       localObject = new lr(localObject, paramDataInputStream.readShort()); break;
/*    */     default:
/* 50 */       throw new IllegalArgumentException("Missile Update type not found " + localObject);
/*    */     }
/* 52 */     ((lw)localObject).a(paramDataInputStream);
/*    */ 
/* 54 */     return localObject;
/*    */   }
/*    */ 
/*    */   public abstract void a(ct paramct, Short2ObjectArrayMap paramShort2ObjectArrayMap, t paramt);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lw
 * JD-Core Version:    0.6.2
 */