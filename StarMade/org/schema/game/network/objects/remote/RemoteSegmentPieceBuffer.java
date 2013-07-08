/*   1:    */package org.schema.game.network.objects.remote;
/*   2:    */
/*   3:    */import java.io.DataInputStream;
/*   4:    */import java.io.DataOutputStream;
/*   5:    */import java.lang.reflect.Constructor;
/*   6:    */import le;
/*   7:    */import org.schema.game.common.controller.SegmentController;
/*   8:    */import org.schema.schine.network.objects.NetworkObject;
/*   9:    */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  10:    */
/*  11:    */public class RemoteSegmentPieceBuffer extends RemoteBuffer
/*  12:    */{
/*  13:    */  private static final int SEGMENT_BUFF_MAX_BATCH = 128;
/*  14:    */  private SegmentController segmentController;
/*  15:    */  private static Constructor staticConstructor;
/*  16:    */  
/*  17:    */  static
/*  18:    */  {
/*  19:    */    try
/*  20:    */    {
/*  21: 21 */      if (staticConstructor == null)
/*  22: 22 */        staticConstructor = RemoteSegmentPiece.class.getConstructor(new Class[] { le.class, SegmentController.class, Boolean.TYPE });
/*  23:    */      return;
/*  24: 24 */    } catch (SecurityException localSecurityException) { 
/*  25:    */      
/*  31: 31 */        localSecurityException;
/*  32:    */      
/*  34: 27 */      if (!$assertionsDisabled) throw new AssertionError();
/*  35:    */      return; } catch (NoSuchMethodException localNoSuchMethodException) { 
/*  36:    */      
/*  38: 31 */        localNoSuchMethodException;
/*  39:    */      
/*  40: 30 */      if (!$assertionsDisabled) throw new AssertionError();
/*  41:    */    }
/*  42:    */  }
/*  43:    */  
/*  44:    */  public RemoteSegmentPieceBuffer(SegmentController paramSegmentController, NetworkObject paramNetworkObject)
/*  45:    */  {
/*  46: 36 */    super(RemoteSegmentPiece.class, paramNetworkObject);
/*  47: 37 */    this.segmentController = paramSegmentController;
/*  48:    */  }
/*  49:    */  
/*  50: 40 */  public RemoteSegmentPieceBuffer(SegmentController paramSegmentController, boolean paramBoolean) { super(RemoteSegmentPiece.class, paramBoolean);
/*  51: 41 */    this.segmentController = paramSegmentController;
/*  52:    */  }
/*  53:    */  
/*  58:    */  public void clearReceiveBuffer()
/*  59:    */  {
/*  60: 50 */    getReceiveBuffer().clear();
/*  61:    */  }
/*  62:    */  
/*  63:    */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*  64:    */  {
/*  65: 55 */    int i = paramDataInputStream.readInt();
/*  66:    */    
/*  67: 57 */    for (int j = 0; j < i; j++) {
/*  68:    */      RemoteSegmentPiece localRemoteSegmentPiece;
/*  69: 59 */      (localRemoteSegmentPiece = (RemoteSegmentPiece)staticConstructor.newInstance(new Object[] { null, this.segmentController, Boolean.valueOf(this.onServer) })).fromByteStream(paramDataInputStream, paramInt);
/*  70: 60 */      getReceiveBuffer().add(localRemoteSegmentPiece);
/*  71:    */    }
/*  72:    */  }
/*  73:    */  
/*  81:    */  public int toByteStream2(DataOutputStream paramDataOutputStream)
/*  82:    */  {
/*  83: 73 */    int i = 0;
/*  84: 74 */    synchronized ((it.unimi.dsi.fastutil.objects.ObjectArrayList)get())
/*  85:    */    {
/*  86: 76 */      paramDataOutputStream.writeInt(((it.unimi.dsi.fastutil.objects.ObjectArrayList)get()).size());
/*  87: 77 */      i += 4;
/*  88:    */      
/*  90: 80 */      for (RemoteSegmentPiece localRemoteSegmentPiece : (it.unimi.dsi.fastutil.objects.ObjectArrayList)get()) {
/*  91: 81 */        i += localRemoteSegmentPiece.toByteStream(paramDataOutputStream);
/*  92:    */      }
/*  93:    */      
/*  94: 84 */      ((it.unimi.dsi.fastutil.objects.ObjectArrayList)get()).clear();
/*  95:    */    }
/*  96:    */    
/*  97: 87 */    return i;
/*  98:    */  }
/*  99:    */  
/* 101:    */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 102:    */  {
/* 103: 93 */    int i = Math.min(128, ((it.unimi.dsi.fastutil.objects.ObjectArrayList)get()).size());
/* 104:    */    
/* 105: 95 */    int j = 4;
/* 106:    */    
/* 107: 97 */    paramDataOutputStream.writeInt(i);
/* 108:    */    
/* 109: 99 */    for (int k = 0; k < i; k++) {
/* 110:100 */      RemoteSegmentPiece localRemoteSegmentPiece = (RemoteSegmentPiece)((it.unimi.dsi.fastutil.objects.ObjectArrayList)get()).remove(0);
/* 111:101 */      j += localRemoteSegmentPiece.toByteStream(paramDataOutputStream);
/* 112:102 */      localRemoteSegmentPiece.setChanged(false);
/* 113:    */    }
/* 114:    */    
/* 117:107 */    this.keepChanged = (!((it.unimi.dsi.fastutil.objects.ObjectArrayList)get()).isEmpty());
/* 118:    */    
/* 125:115 */    return j;
/* 126:    */  }
/* 127:    */  
/* 128:    */  public void cacheConstructor() {}
/* 129:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteSegmentPieceBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */