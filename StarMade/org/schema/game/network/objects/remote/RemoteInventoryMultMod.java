/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import java.util.Collection;
/*  6:   */import java.util.Iterator;
/*  7:   */import mf;
/*  8:   */import mi;
/*  9:   */import mj;
/* 10:   */import org.schema.schine.network.objects.NetworkObject;
/* 11:   */import org.schema.schine.network.objects.remote.RemoteField;
/* 12:   */import q;
/* 13:   */
/* 16:   */public class RemoteInventoryMultMod
/* 17:   */  extends RemoteField
/* 18:   */{
/* 19:   */  public RemoteInventoryMultMod(mi parammi, NetworkObject paramNetworkObject)
/* 20:   */  {
/* 21:21 */    super(parammi, paramNetworkObject);
/* 22:   */  }
/* 23:   */  
/* 24:24 */  public RemoteInventoryMultMod(mi parammi, boolean paramBoolean) { super(parammi, paramBoolean); }
/* 25:   */  
/* 27:   */  public int byteLength()
/* 28:   */  {
/* 29:29 */    return 4;
/* 30:   */  }
/* 31:   */  
/* 32:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/* 33:   */  {
/* 34:34 */    paramInt = paramDataInputStream.readInt();
/* 35:35 */    int i = paramDataInputStream.readInt();
/* 36:36 */    int j = paramDataInputStream.readInt();
/* 37:   */    
/* 38:38 */    ((mi)get()).jdField_a_of_type_Q = new q(paramInt, i, j);
/* 39:   */    
/* 40:40 */    paramInt = paramDataInputStream.readShort();
/* 41:   */    
/* 42:42 */    ((mi)get()).jdField_a_of_type_ArrayOfMj = new mj[paramInt];
/* 43:   */    
/* 44:44 */    for (i = 0; i < paramInt; i++)
/* 45:   */    {
/* 46:46 */      ((mi)get()).jdField_a_of_type_ArrayOfMj[i] = new mj();
/* 47:   */      
/* 48:48 */      j = paramDataInputStream.readShort();
/* 49:   */      short s;
/* 50:50 */      if ((s = paramDataInputStream.readShort()) != 0) {
/* 51:51 */        int k = paramDataInputStream.readInt();
/* 52:52 */        ((mi)get()).jdField_a_of_type_ArrayOfMj[i].b(k);
/* 53:   */      }
/* 54:   */      
/* 55:55 */      ((mi)get()).jdField_a_of_type_ArrayOfMj[i].jdField_a_of_type_Int = j;
/* 56:56 */      ((mi)get()).jdField_a_of_type_ArrayOfMj[i].jdField_a_of_type_Short = s;
/* 57:   */    }
/* 58:   */  }
/* 59:   */  
/* 61:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 62:   */  {
/* 63:63 */    mf localmf = ((mi)get()).jdField_a_of_type_Mf;
/* 64:64 */    if (((mi)get()).jdField_a_of_type_Q != null) {
/* 65:65 */      paramDataOutputStream.writeInt(((mi)get()).jdField_a_of_type_Q.jdField_a_of_type_Int);
/* 66:66 */      paramDataOutputStream.writeInt(((mi)get()).jdField_a_of_type_Q.b);
/* 67:67 */      paramDataOutputStream.writeInt(((mi)get()).jdField_a_of_type_Q.c);
/* 68:   */    } else {
/* 69:69 */      paramDataOutputStream.writeInt(0);
/* 70:70 */      paramDataOutputStream.writeInt(0);
/* 71:71 */      paramDataOutputStream.writeInt(0);
/* 72:   */    }
/* 73:   */    
/* 74:74 */    paramDataOutputStream.writeShort((short)((mi)get()).jdField_a_of_type_JavaUtilCollection.size());
/* 75:75 */    for (Iterator localIterator = ((mi)get()).jdField_a_of_type_JavaUtilCollection.iterator(); localIterator.hasNext();) { int i = ((Integer)localIterator.next()).intValue();
/* 76:   */      
/* 77:77 */      paramDataOutputStream.writeShort((short)i);
/* 78:78 */      int j = localmf.a(i);
/* 79:79 */      paramDataOutputStream.writeShort(j);
/* 80:80 */      if (j != 0) {
/* 81:81 */        paramDataOutputStream.writeInt(localmf.a(i));
/* 82:   */      }
/* 83:   */    }
/* 84:   */    
/* 89:89 */    return byteLength();
/* 90:   */  }
/* 91:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteInventoryMultMod
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */