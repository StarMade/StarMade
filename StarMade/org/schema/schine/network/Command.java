/*     */ package org.schema.schine.network;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import org.schema.schine.network.client.ClientProcessor;
/*     */ import org.schema.schine.network.client.ClientStateInterface;
/*     */ import org.schema.schine.network.server.ServerProcessor;
/*     */ import org.schema.schine.network.server.ServerStateInterface;
/*     */ 
/*     */ public abstract class Command
/*     */ {
/*     */   public static final int MODE_NO_RETURN = 0;
/*     */   public static final int MODE_RETURN = 1;
/*     */   private String[] attribs;
/*  94 */   protected int mode = 0;
/*     */   private byte id;
/*     */   public static long sendingTime;
/*     */ 
/*     */   public static void getHeader()
/*     */   {
/*     */   }
/*     */ 
/*     */   public abstract void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort);
/*     */ 
/*     */   public void createReturnToClient(ServerStateInterface paramServerStateInterface, ServerProcessor paramServerProcessor, short paramShort, Object[] paramArrayOfObject)
/*     */   {
/* 132 */     writeAndCommitParametriziedCommand(paramArrayOfObject, 0, paramServerProcessor.getClient() != null ? paramServerProcessor.getClient().getId() : -4242, paramShort, paramServerProcessor);
/*     */   }
/*     */ 
/*     */   public String[] getAttribs()
/*     */   {
/* 141 */     return this.attribs;
/*     */   }
/*     */ 
/*     */   public byte getId() {
/* 145 */     return this.id;
/*     */   }
/*     */ 
/*     */   public int getMode()
/*     */   {
/* 153 */     return this.mode;
/*     */   }
/*     */ 
/*     */   public Object[] readParameters(Header paramHeader, DataInputStream paramDataInputStream)
/*     */   {
/* 161 */     Object[] arrayOfObject = new Object[paramHeader = paramDataInputStream.readInt()];
/*     */ 
/* 163 */     for (Header localHeader = 0; localHeader < paramHeader; localHeader++)
/*     */     {
/*     */       int i;
/*     */       byte[] arrayOfByte;
/* 164 */       switch (
/* 165 */         i = (byte)paramDataInputStream.read()) {
/*     */       case 2:
/* 166 */         arrayOfObject[localHeader] = Long.valueOf(paramDataInputStream.readLong()); break;
/*     */       case 4:
/* 167 */         arrayOfObject[localHeader] = paramDataInputStream.readUTF(); break;
/*     */       case 3:
/* 168 */         arrayOfObject[localHeader] = Float.valueOf(paramDataInputStream.readFloat()); break;
/*     */       case 1:
/* 169 */         arrayOfObject[localHeader] = Integer.valueOf(paramDataInputStream.readInt()); break;
/*     */       case 5:
/* 170 */         arrayOfObject[localHeader] = Boolean.valueOf(paramDataInputStream.readBoolean()); break;
/*     */       case 6:
/* 171 */         arrayOfObject[localHeader] = Byte.valueOf(paramDataInputStream.readByte()); break;
/*     */       case 7:
/* 172 */         arrayOfObject[localHeader] = Short.valueOf(paramDataInputStream.readShort()); break;
/*     */       case 8:
/* 175 */         arrayOfByte = new byte[paramDataInputStream.readInt()];
/*     */ 
/* 176 */         paramDataInputStream.readFully(arrayOfByte);
/* 177 */         arrayOfObject[localHeader] = arrayOfByte;
/* 178 */         break;
/*     */       default:
/* 179 */         throw new IllegalArgumentException("Type: " + arrayOfByte + " unknown. parameter " + localHeader + " of " + paramHeader + " in command " + this);
/*     */       }
/*     */     }
/*     */ 
/* 183 */     return arrayOfObject;
/*     */   }
/*     */ 
/*     */   public abstract void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort);
/*     */ 
/*     */   public void setId(byte paramByte)
/*     */   {
/* 206 */     this.id = paramByte;
/*     */   }
/*     */ 
/*     */   public byte[] successDoNothing()
/*     */   {
/* 216 */     return NetUtil.COMMAND_SUCCESS_DO_NOTHING;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 224 */     return getClass().getSimpleName() + "[" + (this.mode == 0 ? "void" : "return") + "]";
/*     */   }
/*     */ 
/*     */   public void writeAndCommitParametriziedCommand(Object[] paramArrayOfObject, int paramInt1, int paramInt2, short paramShort, NetworkProcessor paramNetworkProcessor)
/*     */   {
/* 233 */     long l1 = System.currentTimeMillis();
/* 234 */     synchronized (paramNetworkProcessor.getLock()) {
/* 235 */       Header localHeader = new Header(getId(), paramShort, (byte)111);
/* 236 */       if ((paramNetworkProcessor instanceof ClientProcessor)) {
/* 237 */         ((ClientProcessor)paramNetworkProcessor).lastPacketId = paramShort;
/*     */       }
/* 239 */       localHeader.write(paramNetworkProcessor.getOut());
/*     */ 
/* 242 */       writeParametriziedCommand(paramArrayOfObject, paramInt1, paramInt2, paramNetworkProcessor);
/*     */ 
/* 245 */       paramNetworkProcessor.flushDoubleOutBuffer();
/*     */     }
/*     */     long l2;
/* 250 */     if ((
/* 250 */       l2 = System.currentTimeMillis() - l1) > 
/* 250 */       7L)
/*     */     {
/* 252 */       System.err.println("[COMMAND] WARNING: WRITING OUT NT COMMAND " + this + " ON " + paramNetworkProcessor.getState() + " TOOK: " + l2);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void writeParametriziedCommand(Object[] paramArrayOfObject, int paramInt1, int paramInt2, NetworkProcessor paramNetworkProcessor)
/*     */   {
/* 268 */     paramNetworkProcessor.getOut().writeInt(paramArrayOfObject.length);
/*     */ 
/* 270 */     for (paramInt1 = 0; paramInt1 < paramArrayOfObject.length; paramInt1++)
/*     */     {
/* 272 */       if ((paramArrayOfObject[paramInt1] instanceof Long)) {
/* 273 */         paramNetworkProcessor.getOut().write(2);
/* 274 */         paramNetworkProcessor.getOut().writeLong(((Long)paramArrayOfObject[paramInt1]).longValue());
/* 275 */       } else if ((paramArrayOfObject[paramInt1] instanceof String)) {
/* 276 */         paramNetworkProcessor.getOut().write(4);
/* 277 */         paramNetworkProcessor.getOut().writeUTF((String)paramArrayOfObject[paramInt1]);
/* 278 */       } else if ((paramArrayOfObject[paramInt1] instanceof Float)) {
/* 279 */         paramNetworkProcessor.getOut().write(3);
/* 280 */         paramNetworkProcessor.getOut().writeFloat(((Float)paramArrayOfObject[paramInt1]).floatValue());
/* 281 */       } else if ((paramArrayOfObject[paramInt1] instanceof Integer)) {
/* 282 */         paramNetworkProcessor.getOut().write(1);
/* 283 */         paramNetworkProcessor.getOut().writeInt(((Integer)paramArrayOfObject[paramInt1]).intValue());
/* 284 */       } else if ((paramArrayOfObject[paramInt1] instanceof Boolean)) {
/* 285 */         paramNetworkProcessor.getOut().write(5);
/* 286 */         paramNetworkProcessor.getOut().writeBoolean(((Boolean)paramArrayOfObject[paramInt1]).booleanValue());
/* 287 */       } else if ((paramArrayOfObject[paramInt1] instanceof Byte)) {
/* 288 */         paramNetworkProcessor.getOut().write(6);
/* 289 */         paramNetworkProcessor.getOut().writeByte(((Byte)paramArrayOfObject[paramInt1]).byteValue());
/* 290 */       } else if ((paramArrayOfObject[paramInt1] instanceof Short)) {
/* 291 */         paramNetworkProcessor.getOut().write(7);
/* 292 */         paramNetworkProcessor.getOut().writeShort(((Short)paramArrayOfObject[paramInt1]).shortValue());
/* 293 */       } else if ((paramArrayOfObject[paramInt1] instanceof byte[])) {
/* 294 */         paramInt2 = (byte[])paramArrayOfObject[paramInt1];
/* 295 */         paramNetworkProcessor.getOut().write(8);
/* 296 */         paramNetworkProcessor.getOut().writeInt(paramInt2.length);
/* 297 */         paramNetworkProcessor.getOut().write(paramInt2);
/*     */       }
/* 299 */       else if (!$assertionsDisabled) { throw new AssertionError("UNKNOWN OBJECT TYPE: " + paramArrayOfObject[paramInt1] + " : " + paramArrayOfObject[paramInt1].getClass()); }
/*     */ 
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.Command
 * JD-Core Version:    0.6.2
 */