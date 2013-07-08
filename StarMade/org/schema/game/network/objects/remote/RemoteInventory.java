/*   1:    */package org.schema.game.network.objects.remote;
/*   2:    */
/*   3:    */import java.io.DataInputStream;
/*   4:    */import java.io.DataOutputStream;
/*   5:    */import mf;
/*   6:    */import mh;
/*   7:    */import mk;
/*   8:    */import mo;
/*   9:    */import org.schema.schine.network.objects.remote.RemoteField;
/*  10:    */import q;
/*  11:    */
/*  17:    */public class RemoteInventory
/*  18:    */  extends RemoteField
/*  19:    */{
/*  20:    */  private boolean add;
/*  21:    */  private mh holder;
/*  22:    */  
/*  23:    */  public RemoteInventory(mf parammf, mh parammh, boolean paramBoolean1, boolean paramBoolean2)
/*  24:    */  {
/*  25: 25 */    super(parammf, paramBoolean2);
/*  26: 26 */    setAdd(paramBoolean1);
/*  27: 27 */    this.holder = parammh;
/*  28:    */  }
/*  29:    */  
/*  30:    */  public int byteLength()
/*  31:    */  {
/*  32: 32 */    return 22;
/*  33:    */  }
/*  34:    */  
/*  40:    */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*  41:    */  {
/*  42: 42 */    paramInt = paramDataInputStream.readInt();
/*  43: 43 */    int i = paramDataInputStream.readInt();
/*  44: 44 */    int j = paramDataInputStream.readInt();
/*  45: 45 */    int k = paramDataInputStream.readInt();
/*  46: 46 */    setAdd(paramDataInputStream.readBoolean());
/*  47:    */    
/*  50: 50 */    if (isAdd())
/*  51:    */    {
/*  52: 52 */      switch (paramInt) {
/*  53: 53 */      case 1:  paramInt = new mk(this.holder, new q(i, j, k));break;
/*  54: 54 */      default:  paramInt = new mo(this.holder, new q(i, j, k));
/*  55:    */      }
/*  56: 56 */      paramInt.a(paramDataInputStream);
/*  57:    */    }
/*  58:    */    else {
/*  59: 59 */      switch (paramInt) {
/*  60: 60 */      case 1:  paramInt = new mk(this.holder, new q(i, j, k));break;
/*  61: 61 */      default:  paramInt = new mo(this.holder, new q(i, j, k));
/*  62:    */      }
/*  63:    */    }
/*  64: 64 */    set(paramInt);
/*  65:    */  }
/*  66:    */  
/*  71:    */  public int toByteStream(DataOutputStream paramDataOutputStream)
/*  72:    */  {
/*  73: 73 */    int i = 17;
/*  74:    */    
/*  76: 76 */    paramDataOutputStream.writeInt(((mf)get()).c());
/*  77: 77 */    paramDataOutputStream.writeInt(((mf)get()).a().a);
/*  78: 78 */    paramDataOutputStream.writeInt(((mf)get()).a().b);
/*  79: 79 */    paramDataOutputStream.writeInt(((mf)get()).a().c);
/*  80: 80 */    paramDataOutputStream.writeBoolean(isAdd());
/*  81:    */    
/*  82: 82 */    if (isAdd())
/*  83:    */    {
/*  84: 84 */      i = 17 + ((mf)get()).a(paramDataOutputStream);
/*  85:    */    }
/*  86:    */    
/*  90: 90 */    return i;
/*  91:    */  }
/*  92:    */  
/*  95:    */  public boolean isAdd()
/*  96:    */  {
/*  97: 97 */    return this.add;
/*  98:    */  }
/*  99:    */  
/* 102:    */  public void setAdd(boolean paramBoolean)
/* 103:    */  {
/* 104:104 */    this.add = paramBoolean;
/* 105:    */  }
/* 106:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteInventory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */