/*  1:   */import it.unimi.dsi.fastutil.shorts.Short2ObjectArrayMap;
/*  2:   */import java.io.DataInputStream;
/*  3:   */import java.io.DataOutputStream;
/*  4:   */
/* 19:   */public abstract class lw
/* 20:   */{
/* 21:   */  public final short a;
/* 22:   */  public final byte b;
/* 23:   */  
/* 24:   */  public lw(byte paramByte, short paramShort)
/* 25:   */  {
/* 26:26 */    this.b = paramByte;
/* 27:27 */    this.a = paramShort;
/* 28:   */  }
/* 29:   */  
/* 30:   */  public void b(DataOutputStream paramDataOutputStream) {
/* 31:31 */    paramDataOutputStream.writeByte(this.b);
/* 32:32 */    paramDataOutputStream.writeShort(this.a);
/* 33:33 */    a(paramDataOutputStream); }
/* 34:   */  
/* 35:   */  protected abstract void a(DataInputStream paramDataInputStream);
/* 36:   */  
/* 37:   */  protected abstract void a(DataOutputStream paramDataOutputStream);
/* 38:   */  
/* 39:   */  public static lw a(DataInputStream paramDataInputStream) { byte b1;
/* 40:   */    Object localObject;
/* 41:41 */    switch (b1 = paramDataInputStream.readByte())
/* 42:   */    {
/* 43:   */    case 1: 
/* 44:43 */      localObject = new lu(b1, paramDataInputStream.readShort());break;
/* 45:44 */    case 2:  localObject = new ls(localObject, paramDataInputStream.readShort());break;
/* 46:45 */    case 3:  localObject = new lq(localObject, paramDataInputStream.readShort());break;
/* 47:46 */    case 4:  localObject = new lv(localObject, paramDataInputStream.readShort());break;
/* 48:47 */    case 5:  localObject = new lt(localObject, paramDataInputStream.readShort());break;
/* 49:48 */    case 6:  localObject = new lr(localObject, paramDataInputStream.readShort());break;
/* 50:   */    default: 
/* 51:50 */      throw new IllegalArgumentException("Missile Update type not found " + localObject);
/* 52:   */    }
/* 53:52 */    ((lw)localObject).a(paramDataInputStream);
/* 54:   */    
/* 55:54 */    return localObject;
/* 56:   */  }
/* 57:   */  
/* 58:   */  public abstract void a(ct paramct, Short2ObjectArrayMap paramShort2ObjectArrayMap, t paramt);
/* 59:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lw
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */