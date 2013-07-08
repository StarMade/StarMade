/*   1:    */package org.schema.schine.network;
/*   2:    */
/*   3:    */import java.io.DataInputStream;
/*   4:    */import java.io.DataOutputStream;
/*   5:    */import java.io.PrintStream;
/*   6:    */import org.schema.schine.network.client.ClientProcessor;
/*   7:    */import org.schema.schine.network.client.ClientStateInterface;
/*   8:    */import org.schema.schine.network.server.ServerProcessor;
/*   9:    */import org.schema.schine.network.server.ServerStateInterface;
/*  10:    */
/*  89:    */public abstract class Command
/*  90:    */{
/*  91:    */  public static final int MODE_NO_RETURN = 0;
/*  92:    */  public static final int MODE_RETURN = 1;
/*  93:    */  private String[] attribs;
/*  94: 94 */  protected int mode = 0;
/*  95:    */  
/* 101:    */  private byte id;
/* 102:    */  
/* 108:    */  public static long sendingTime;
/* 109:    */  
/* 116:    */  public static void getHeader() {}
/* 117:    */  
/* 123:    */  public abstract void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort);
/* 124:    */  
/* 130:    */  public void createReturnToClient(ServerStateInterface paramServerStateInterface, ServerProcessor paramServerProcessor, short paramShort, Object... paramVarArgs)
/* 131:    */  {
/* 132:132 */    writeAndCommitParametriziedCommand(paramVarArgs, 0, paramServerProcessor.getClient() != null ? paramServerProcessor.getClient().getId() : -4242, paramShort, paramServerProcessor);
/* 133:    */  }
/* 134:    */  
/* 139:    */  public String[] getAttribs()
/* 140:    */  {
/* 141:141 */    return this.attribs;
/* 142:    */  }
/* 143:    */  
/* 144:    */  public byte getId() {
/* 145:145 */    return this.id;
/* 146:    */  }
/* 147:    */  
/* 151:    */  public int getMode()
/* 152:    */  {
/* 153:153 */    return this.mode;
/* 154:    */  }
/* 155:    */  
/* 159:    */  public Object[] readParameters(Header paramHeader, DataInputStream paramDataInputStream)
/* 160:    */  {
/* 161:161 */    Object[] arrayOfObject = new Object[paramHeader = paramDataInputStream.readInt()];
/* 162:    */    
/* 163:163 */    for (Header localHeader = 0; localHeader < paramHeader; localHeader++) { int i;
/* 164:    */      byte[] arrayOfByte;
/* 165:165 */      switch (i = (byte)paramDataInputStream.read()) {
/* 166:    */      case 2: 
/* 167:166 */        arrayOfObject[localHeader] = Long.valueOf(paramDataInputStream.readLong());break;
/* 168:167 */      case 4:  arrayOfObject[localHeader] = paramDataInputStream.readUTF();break;
/* 169:168 */      case 3:  arrayOfObject[localHeader] = Float.valueOf(paramDataInputStream.readFloat());break;
/* 170:169 */      case 1:  arrayOfObject[localHeader] = Integer.valueOf(paramDataInputStream.readInt());break;
/* 171:170 */      case 5:  arrayOfObject[localHeader] = Boolean.valueOf(paramDataInputStream.readBoolean());break;
/* 172:171 */      case 6:  arrayOfObject[localHeader] = Byte.valueOf(paramDataInputStream.readByte());break;
/* 173:172 */      case 7:  arrayOfObject[localHeader] = Short.valueOf(paramDataInputStream.readShort());break;
/* 174:    */      
/* 175:    */      case 8: 
/* 176:175 */        arrayOfByte = new byte[paramDataInputStream.readInt()];
/* 177:176 */        paramDataInputStream.readFully(arrayOfByte);
/* 178:177 */        arrayOfObject[localHeader] = arrayOfByte;
/* 179:178 */        break;
/* 180:179 */      default:  throw new IllegalArgumentException("Type: " + arrayOfByte + " unknown. parameter " + localHeader + " of " + paramHeader + " in command " + this);
/* 181:    */      }
/* 182:    */      
/* 183:    */    }
/* 184:183 */    return arrayOfObject;
/* 185:    */  }
/* 186:    */  
/* 195:    */  public abstract void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort);
/* 196:    */  
/* 205:    */  public void setId(byte paramByte)
/* 206:    */  {
/* 207:206 */    this.id = paramByte;
/* 208:    */  }
/* 209:    */  
/* 215:    */  public byte[] successDoNothing()
/* 216:    */  {
/* 217:216 */    return NetUtil.COMMAND_SUCCESS_DO_NOTHING;
/* 218:    */  }
/* 219:    */  
/* 223:    */  public String toString()
/* 224:    */  {
/* 225:224 */    return getClass().getSimpleName() + "[" + (this.mode == 0 ? "void" : "return") + "]";
/* 226:    */  }
/* 227:    */  
/* 232:    */  public void writeAndCommitParametriziedCommand(Object[] paramArrayOfObject, int paramInt1, int paramInt2, short paramShort, NetworkProcessor paramNetworkProcessor)
/* 233:    */  {
/* 234:233 */    long l1 = System.currentTimeMillis();
/* 235:234 */    synchronized (paramNetworkProcessor.getLock()) {
/* 236:235 */      Header localHeader = new Header(getId(), paramShort, (byte)111);
/* 237:236 */      if ((paramNetworkProcessor instanceof ClientProcessor)) {
/* 238:237 */        ((ClientProcessor)paramNetworkProcessor).lastPacketId = paramShort;
/* 239:    */      }
/* 240:239 */      localHeader.write(paramNetworkProcessor.getOut());
/* 241:    */      
/* 243:242 */      writeParametriziedCommand(paramArrayOfObject, paramInt1, paramInt2, paramNetworkProcessor);
/* 244:    */      
/* 246:245 */      paramNetworkProcessor.flushDoubleOutBuffer();
/* 247:    */    }
/* 248:    */    
/* 249:    */    long l2;
/* 250:    */    
/* 251:250 */    if ((l2 = System.currentTimeMillis() - l1) > 7L)
/* 252:    */    {
/* 253:252 */      System.err.println("[COMMAND] WARNING: WRITING OUT NT COMMAND " + this + " ON " + paramNetworkProcessor.getState() + " TOOK: " + l2);
/* 254:    */    }
/* 255:    */  }
/* 256:    */  
/* 267:    */  public void writeParametriziedCommand(Object[] paramArrayOfObject, int paramInt1, int paramInt2, NetworkProcessor paramNetworkProcessor)
/* 268:    */  {
/* 269:268 */    paramNetworkProcessor.getOut().writeInt(paramArrayOfObject.length);
/* 270:    */    
/* 271:270 */    for (paramInt1 = 0; paramInt1 < paramArrayOfObject.length; paramInt1++)
/* 272:    */    {
/* 273:272 */      if ((paramArrayOfObject[paramInt1] instanceof Long)) {
/* 274:273 */        paramNetworkProcessor.getOut().write(2);
/* 275:274 */        paramNetworkProcessor.getOut().writeLong(((Long)paramArrayOfObject[paramInt1]).longValue());
/* 276:275 */      } else if ((paramArrayOfObject[paramInt1] instanceof String)) {
/* 277:276 */        paramNetworkProcessor.getOut().write(4);
/* 278:277 */        paramNetworkProcessor.getOut().writeUTF((String)paramArrayOfObject[paramInt1]);
/* 279:278 */      } else if ((paramArrayOfObject[paramInt1] instanceof Float)) {
/* 280:279 */        paramNetworkProcessor.getOut().write(3);
/* 281:280 */        paramNetworkProcessor.getOut().writeFloat(((Float)paramArrayOfObject[paramInt1]).floatValue());
/* 282:281 */      } else if ((paramArrayOfObject[paramInt1] instanceof Integer)) {
/* 283:282 */        paramNetworkProcessor.getOut().write(1);
/* 284:283 */        paramNetworkProcessor.getOut().writeInt(((Integer)paramArrayOfObject[paramInt1]).intValue());
/* 285:284 */      } else if ((paramArrayOfObject[paramInt1] instanceof Boolean)) {
/* 286:285 */        paramNetworkProcessor.getOut().write(5);
/* 287:286 */        paramNetworkProcessor.getOut().writeBoolean(((Boolean)paramArrayOfObject[paramInt1]).booleanValue());
/* 288:287 */      } else if ((paramArrayOfObject[paramInt1] instanceof Byte)) {
/* 289:288 */        paramNetworkProcessor.getOut().write(6);
/* 290:289 */        paramNetworkProcessor.getOut().writeByte(((Byte)paramArrayOfObject[paramInt1]).byteValue());
/* 291:290 */      } else if ((paramArrayOfObject[paramInt1] instanceof Short)) {
/* 292:291 */        paramNetworkProcessor.getOut().write(7);
/* 293:292 */        paramNetworkProcessor.getOut().writeShort(((Short)paramArrayOfObject[paramInt1]).shortValue());
/* 294:293 */      } else if ((paramArrayOfObject[paramInt1] instanceof byte[])) {
/* 295:294 */        paramInt2 = (byte[])paramArrayOfObject[paramInt1];
/* 296:295 */        paramNetworkProcessor.getOut().write(8);
/* 297:296 */        paramNetworkProcessor.getOut().writeInt(paramInt2.length);
/* 298:297 */        paramNetworkProcessor.getOut().write(paramInt2);
/* 299:    */      }
/* 300:299 */      else if (!$assertionsDisabled) { throw new AssertionError("UNKNOWN OBJECT TYPE: " + paramArrayOfObject[paramInt1] + " : " + paramArrayOfObject[paramInt1].getClass());
/* 301:    */      }
/* 302:    */    }
/* 303:    */  }
/* 304:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.Command
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */