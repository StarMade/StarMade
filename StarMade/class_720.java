import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import org.schema.common.util.ByteUtil;
import org.schema.game.common.controller.SegmentController;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.client.ClientToServerConnection;

public class class_720
{
  private SegmentController jdField_field_991_of_type_OrgSchemaGameCommonControllerSegmentController;
  private static final class_48 jdField_field_991_of_type_Class_48;
  private byte[] jdField_field_991_of_type_ArrayOfByte = new byte[8];
  private byte[] jdField_field_992_of_type_ArrayOfByte = new byte[8];
  private boolean jdField_field_991_of_type_Boolean;
  private final String jdField_field_991_of_type_JavaLangString;
  private long jdField_field_991_of_type_Long;
  private static final byte[] jdField_field_993_of_type_ArrayOfByte;
  private class_724 jdField_field_991_of_type_Class_724;
  private static final int jdField_field_991_of_type_Int;
  private static final int jdField_field_992_of_type_Int;
  private static final int jdField_field_993_of_type_Int;
  private static byte[] field_994;
  private static byte[] field_995;
  private int[] jdField_field_991_of_type_ArrayOfInt = new int[2];
  
  private static int a(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = Math.abs(paramInt1 + 128) >> 4 & 0xF;
    int j = Math.abs(paramInt2 + 128) >> 4 & 0xF;
    int k;
    int m = (k = Math.abs(paramInt3 + 128) >> 4 & 0xF) * jdField_field_991_of_type_Class_48.field_476 * jdField_field_991_of_type_Class_48.field_475 + j * jdField_field_991_of_type_Class_48.field_475 + i;
    if ((!jdField_field_992_of_type_Boolean) && (m >= jdField_field_991_of_type_Int)) {
      throw new AssertionError(m + "/" + jdField_field_991_of_type_Int + ": " + paramInt1 + ", " + paramInt2 + ", " + paramInt3 + " ---> " + i + ", " + j + ", " + k + " ");
    }
    return m;
  }
  
  public class_720(SegmentController paramSegmentController, boolean paramBoolean)
  {
    this.jdField_field_991_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
    this.jdField_field_991_of_type_Boolean = paramBoolean;
    this.jdField_field_991_of_type_Class_724 = new class_724(paramSegmentController.getState(), paramSegmentController);
    if (paramBoolean)
    {
      this.jdField_field_991_of_type_JavaLangString = class_1041.field_149;
      return;
    }
    this.jdField_field_991_of_type_JavaLangString = class_371.field_145;
  }
  
  private void a1(int paramInt1, int paramInt2, int paramInt3, File paramFile)
  {
    long l1 = System.currentTimeMillis();
    FileOutputStream localFileOutputStream = new FileOutputStream(paramFile);
    BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(localFileOutputStream, field_994.length);
    long l2 = System.currentTimeMillis() - l1;
    localBufferedOutputStream.write(field_994);
    long l3 = System.currentTimeMillis();
    localFileOutputStream.close();
    localBufferedOutputStream.close();
    localFileOutputStream.close();
    long l4 = System.currentTimeMillis() - l3;
    if ((!this.jdField_field_991_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) && (System.currentTimeMillis() - l1 > 5L)) {
      System.err.println("[CLIENT] Wrote Empty Header " + field_994.length + ": " + paramFile.getName() + " - " + this.jdField_field_991_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + " for " + paramInt1 + ", " + paramInt2 + ", " + paramInt3 + "; TIME: " + (System.currentTimeMillis() - l1) + "ms; open " + l2 + "; close: " + l4);
    }
  }
  
  private void a2(int paramInt, int[] paramArrayOfInt, FileChannel paramFileChannel)
  {
    paramInt = jdField_field_993_of_type_ArrayOfByte.length + paramInt * this.jdField_field_991_of_type_ArrayOfByte.length;
    paramInt = paramFileChannel.map(FileChannel.MapMode.READ_ONLY, paramInt, 8L);
    paramArrayOfInt[0] = paramInt.getInt();
    paramArrayOfInt[1] = paramInt.getInt();
  }
  
  public static void a3(SegmentController paramSegmentController, StringBuilder paramStringBuilder)
  {
    StringBuilder localStringBuilder;
    (localStringBuilder = new StringBuilder()).append(paramSegmentController.getUniqueIdentifier());
    String str = ((class_371)paramSegmentController.getState()).a4().getConnection().getHost();
    long l = Math.abs(paramSegmentController.getUniqueIdentifier().hashCode() + str.hashCode()) % 128;
    for (paramSegmentController = 0; paramSegmentController < localStringBuilder.length(); paramSegmentController++) {
      paramStringBuilder.append("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_.-".charAt((int)(localStringBuilder.charAt(paramSegmentController) * (paramSegmentController + l) % 65L)));
    }
  }
  
  private String a4(int paramInt1, int paramInt2, int paramInt3, SegmentController paramSegmentController)
  {
    paramInt1 = ByteUtil.a(paramInt1 + 128) / jdField_field_991_of_type_Class_48.field_475 - (paramInt1 + 128 < 0 ? 1 : 0);
    paramInt2 = ByteUtil.a(paramInt2 + 128) / jdField_field_991_of_type_Class_48.field_476 - (paramInt2 + 128 < 0 ? 1 : 0);
    paramInt3 = ByteUtil.a(paramInt3 + 128) / jdField_field_991_of_type_Class_48.field_477 - (paramInt3 + 128 < 0 ? 1 : 0);
    StringBuilder localStringBuilder;
    (localStringBuilder = new StringBuilder()).append(this.jdField_field_991_of_type_JavaLangString);
    if (!paramSegmentController.isOnServer())
    {
      a3(paramSegmentController, localStringBuilder);
      localStringBuilder.append(".");
      localStringBuilder.append(paramInt1);
      localStringBuilder.append(".");
      localStringBuilder.append(paramInt2);
      localStringBuilder.append(".");
      localStringBuilder.append(paramInt3);
    }
    else
    {
      localStringBuilder.append(paramSegmentController.getUniqueIdentifier());
      localStringBuilder.append(".");
      localStringBuilder.append(paramInt1);
      localStringBuilder.append(".");
      localStringBuilder.append(paramInt2);
      localStringBuilder.append(".");
      localStringBuilder.append(paramInt3);
    }
    localStringBuilder.append(".smd2");
    return localStringBuilder.toString();
  }
  
  public final long a5(int paramInt1, int paramInt2, int paramInt3)
  {
    synchronized (this)
    {
      Object localObject1 = a4(paramInt1, paramInt2, paramInt3, this.jdField_field_991_of_type_OrgSchemaGameCommonControllerSegmentController);
      if (!new File((String)localObject1).exists()) {
        return -1L;
      }
      synchronized (localObject1 = this.jdField_field_991_of_type_Class_724.a2((String)localObject1))
      {
        paramInt1 = a(paramInt1, paramInt2, paramInt3);
        ((RandomAccessFile)localObject1).seek(jdField_field_993_of_type_ArrayOfByte.length + jdField_field_992_of_type_Int + paramInt1 * this.jdField_field_992_of_type_ArrayOfByte.length);
        try
        {
          return ((RandomAccessFile)localObject1).readLong();
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          return -1L;
        }
      }
    }
  }
  
  public final long a6()
  {
    return this.jdField_field_991_of_type_Long;
  }
  
  public final void a7()
  {
    synchronized (this)
    {
      this.jdField_field_991_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a18(new class_722(this), true);
      return;
    }
  }
  
  public final void b()
  {
    long l1 = System.currentTimeMillis();
    this.jdField_field_991_of_type_Class_724.a1();
    long l2;
    if ((l2 = System.currentTimeMillis() - l1) > 10L) {
      System.err.println("[SEGMENT-IO] WARNING: File Handle Release for " + this.jdField_field_991_of_type_OrgSchemaGameCommonControllerSegmentController + " on " + this.jdField_field_991_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + " took " + l2 + " ms");
    }
  }
  
  public final int a8(int arg1, int paramInt2, int paramInt3, class_672 paramclass_672)
  {
    synchronized (this)
    {
      Object localObject1 = a4(???, paramInt2, paramInt3, this.jdField_field_991_of_type_OrgSchemaGameCommonControllerSegmentController);
      File localFile;
      if (!(localFile = new File((String)localObject1)).exists())
      {
        if ((!(paramclass_672.a15() instanceof class_784)) || (((class_784)paramclass_672.a15()).a1())) {
          a1(???, paramInt2, paramInt3, localFile);
        }
        return 2;
      }
      FileChannel localFileChannel = (localObject1 = this.jdField_field_991_of_type_Class_724.a2((String)localObject1)).getChannel();
      synchronized (localObject1)
      {
        int i = a(???, paramInt2, paramInt3);
        a2(i, this.jdField_field_991_of_type_ArrayOfInt, localFileChannel);
        int j = this.jdField_field_991_of_type_ArrayOfInt[0];
        int k = this.jdField_field_991_of_type_ArrayOfInt[1];
        if (j < 0)
        {
          if (k < 0)
          {
            try
            {
              int m = jdField_field_993_of_type_ArrayOfByte.length + jdField_field_992_of_type_Int + i * this.jdField_field_992_of_type_ArrayOfByte.length;
              long l2 = localFileChannel.map(FileChannel.MapMode.READ_ONLY, m, 8L).getLong();
              paramclass_672.a46(l2);
            }
            catch (IOException localIOException)
            {
              System.err.println("COULD NOT READ TIMESTAMP FOR " + paramclass_672 + " ... " + localIOException.getMessage());
              paramclass_672.a46(System.currentTimeMillis());
            }
            return 1;
          }
          return 2;
        }
        long l1 = jdField_field_993_of_type_ArrayOfByte.length + jdField_field_992_of_type_Int + i * this.jdField_field_992_of_type_ArrayOfByte.length;
        if ((!jdField_field_992_of_type_Boolean) && (l1 >= ((RandomAccessFile)localObject1).length())) {
          throw new AssertionError(" " + l1 + " / " + ((RandomAccessFile)localObject1).length() + " on  (" + ??? + ", " + paramInt2 + ", " + paramInt3 + ") on " + localFile.getName() + " " + paramclass_672.a15() + " offest(" + j + "); offsetIndex(" + i + ")");
        }
        this.jdField_field_991_of_type_Long = l1;
        long l3 = localFileChannel.map(FileChannel.MapMode.READ_ONLY, l1, 8L).getLong();
        if ((!jdField_field_992_of_type_Boolean) && ((k <= 0) || (k >= 5120))) {
          throw new AssertionError(" len: " + k + " / 5120 ON " + localFile.getName() + " (" + ??? + ", " + paramInt2 + ", " + paramInt3 + ")");
        }
        long l4 = jdField_field_993_of_type_ArrayOfByte.length + jdField_field_992_of_type_Int + jdField_field_993_of_type_Int + j * 5120;
        synchronized (this.jdField_field_991_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getDataByteBuffer())
        {
          this.jdField_field_991_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getDataByteBuffer().rewind();
          this.jdField_field_991_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getDataByteBuffer().limit(k);
          localFileChannel.read(this.jdField_field_991_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getDataByteBuffer(), l4);
          this.jdField_field_991_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getDataByteBuffer().rewind();
          paramInt2 = new class_33(this.jdField_field_991_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getDataByteBuffer());
          paramInt3 = new DataInputStream(paramInt2);
          paramclass_672.a43(paramInt3, k, false);
          paramclass_672.a46(l3);
          paramInt2.close();
          paramInt3.close();
        }
        return 0;
      }
    }
  }
  
  /* Error */
  public final void a9(class_672 paramclass_672)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 334	class_672:a15	()Lorg/schema/game/common/controller/SegmentController;
    //   4: instanceof 336
    //   7: ifeq +19 -> 26
    //   10: aload_1
    //   11: invokevirtual 334	class_672:a15	()Lorg/schema/game/common/controller/SegmentController;
    //   14: checkcast 336	class_784
    //   17: invokeinterface 338 1 0
    //   22: ifne +4 -> 26
    //   25: return
    //   26: aload_1
    //   27: invokevirtual 334	class_672:a15	()Lorg/schema/game/common/controller/SegmentController;
    //   30: invokevirtual 144	org/schema/game/common/controller/SegmentController:isOnServer	()Z
    //   33: ifeq +18 -> 51
    //   36: aload_1
    //   37: invokevirtual 424	class_672:e1	()Z
    //   40: ifne +10 -> 50
    //   43: aload_1
    //   44: invokevirtual 427	class_672:f	()Z
    //   47: ifeq +4 -> 51
    //   50: return
    //   51: aload_0
    //   52: dup
    //   53: astore_2
    //   54: monitorenter
    //   55: aload_0
    //   56: aload_1
    //   57: getfield 430	class_672:field_34	Lclass_48;
    //   60: getfield 37	class_48:field_475	I
    //   63: aload_1
    //   64: getfield 430	class_672:field_34	Lclass_48;
    //   67: getfield 34	class_48:field_476	I
    //   70: aload_1
    //   71: getfield 430	class_672:field_34	Lclass_48;
    //   74: getfield 253	class_48:field_477	I
    //   77: aload_0
    //   78: getfield 83	class_720:jdField_field_991_of_type_OrgSchemaGameCommonControllerSegmentController	Lorg/schema/game/common/controller/SegmentController;
    //   81: invokespecial 265	class_720:a4	(IIILorg/schema/game/common/controller/SegmentController;)Ljava/lang/String;
    //   84: astore_3
    //   85: new 157	java/io/File
    //   88: dup
    //   89: aload_3
    //   90: invokespecial 266	java/io/File:<init>	(Ljava/lang/String;)V
    //   93: dup
    //   94: astore_3
    //   95: invokevirtual 269	java/io/File:exists	()Z
    //   98: ifne +29 -> 127
    //   101: aload_0
    //   102: aload_1
    //   103: getfield 430	class_672:field_34	Lclass_48;
    //   106: getfield 37	class_48:field_475	I
    //   109: aload_1
    //   110: getfield 430	class_672:field_34	Lclass_48;
    //   113: getfield 34	class_48:field_476	I
    //   116: aload_1
    //   117: getfield 430	class_672:field_34	Lclass_48;
    //   120: getfield 253	class_48:field_477	I
    //   123: aload_3
    //   124: invokespecial 340	class_720:a1	(IIILjava/io/File;)V
    //   127: aload_1
    //   128: invokevirtual 433	class_672:g	()Z
    //   131: ifeq +188 -> 319
    //   134: aload_0
    //   135: aload_1
    //   136: astore 5
    //   138: astore 4
    //   140: aload 5
    //   142: getfield 430	class_672:field_34	Lclass_48;
    //   145: getfield 37	class_48:field_475	I
    //   148: aload 5
    //   150: getfield 430	class_672:field_34	Lclass_48;
    //   153: getfield 34	class_48:field_476	I
    //   156: aload 5
    //   158: getfield 430	class_672:field_34	Lclass_48;
    //   161: getfield 253	class_48:field_477	I
    //   164: invokestatic 276	class_720:a	(III)I
    //   167: istore 6
    //   169: aload 4
    //   171: getfield 98	class_720:jdField_field_991_of_type_Class_724	Lclass_724;
    //   174: aload 4
    //   176: aload 5
    //   178: getfield 430	class_672:field_34	Lclass_48;
    //   181: getfield 37	class_48:field_475	I
    //   184: aload 5
    //   186: getfield 430	class_672:field_34	Lclass_48;
    //   189: getfield 34	class_48:field_476	I
    //   192: aload 5
    //   194: getfield 430	class_672:field_34	Lclass_48;
    //   197: getfield 253	class_48:field_477	I
    //   200: aload 4
    //   202: getfield 83	class_720:jdField_field_991_of_type_OrgSchemaGameCommonControllerSegmentController	Lorg/schema/game/common/controller/SegmentController;
    //   205: invokespecial 265	class_720:a4	(IIILorg/schema/game/common/controller/SegmentController;)Ljava/lang/String;
    //   208: invokevirtual 274	class_724:a2	(Ljava/lang/String;)Ljava/io/RandomAccessFile;
    //   211: dup
    //   212: astore 7
    //   214: dup
    //   215: astore 8
    //   217: monitorenter
    //   218: aload 7
    //   220: invokevirtual 346	java/io/RandomAccessFile:getChannel	()Ljava/nio/channels/FileChannel;
    //   223: getstatic 436	java/nio/channels/FileChannel$MapMode:READ_WRITE	Ljava/nio/channels/FileChannel$MapMode;
    //   226: getstatic 185	class_720:jdField_field_993_of_type_ArrayOfByte	[B
    //   229: arraylength
    //   230: iload 6
    //   232: aload 4
    //   234: getfield 77	class_720:jdField_field_991_of_type_ArrayOfByte	[B
    //   237: arraylength
    //   238: imul
    //   239: iadd
    //   240: i2l
    //   241: ldc2_w 192
    //   244: invokevirtual 199	java/nio/channels/FileChannel:map	(Ljava/nio/channels/FileChannel$MapMode;JJ)Ljava/nio/MappedByteBuffer;
    //   247: dup
    //   248: astore_1
    //   249: iconst_m1
    //   250: invokevirtual 440	java/nio/MappedByteBuffer:putInt	(I)Ljava/nio/ByteBuffer;
    //   253: pop
    //   254: aload_1
    //   255: iconst_m1
    //   256: invokevirtual 440	java/nio/MappedByteBuffer:putInt	(I)Ljava/nio/ByteBuffer;
    //   259: pop
    //   260: aload 7
    //   262: invokevirtual 346	java/io/RandomAccessFile:getChannel	()Ljava/nio/channels/FileChannel;
    //   265: getstatic 436	java/nio/channels/FileChannel$MapMode:READ_WRITE	Ljava/nio/channels/FileChannel$MapMode;
    //   268: getstatic 185	class_720:jdField_field_993_of_type_ArrayOfByte	[B
    //   271: arraylength
    //   272: getstatic 278	class_720:jdField_field_992_of_type_Int	I
    //   275: iadd
    //   276: iload 6
    //   278: aload 4
    //   280: getfield 79	class_720:jdField_field_992_of_type_ArrayOfByte	[B
    //   283: arraylength
    //   284: imul
    //   285: iadd
    //   286: i2l
    //   287: ldc2_w 192
    //   290: invokevirtual 199	java/nio/channels/FileChannel:map	(Ljava/nio/channels/FileChannel$MapMode;JJ)Ljava/nio/MappedByteBuffer;
    //   293: aload 5
    //   295: invokevirtual 443	class_672:a44	()J
    //   298: invokevirtual 447	java/nio/MappedByteBuffer:putLong	(J)Ljava/nio/ByteBuffer;
    //   301: pop
    //   302: aload 8
    //   304: monitorexit
    //   305: goto +11 -> 316
    //   308: astore 9
    //   310: aload 8
    //   312: monitorexit
    //   313: aload 9
    //   315: athrow
    //   316: aload_2
    //   317: monitorexit
    //   318: return
    //   319: invokestatic 120	java/lang/System:currentTimeMillis	()J
    //   322: pop2
    //   323: aload_0
    //   324: aload_1
    //   325: getfield 430	class_672:field_34	Lclass_48;
    //   328: getfield 37	class_48:field_475	I
    //   331: aload_1
    //   332: getfield 430	class_672:field_34	Lclass_48;
    //   335: getfield 34	class_48:field_476	I
    //   338: aload_1
    //   339: getfield 430	class_672:field_34	Lclass_48;
    //   342: getfield 253	class_48:field_477	I
    //   345: invokevirtual 449	class_720:a5	(III)J
    //   348: lstore 5
    //   350: aload_1
    //   351: invokevirtual 443	class_672:a44	()J
    //   354: lconst_0
    //   355: lcmp
    //   356: ifgt +10 -> 366
    //   359: aload_1
    //   360: invokestatic 120	java/lang/System:currentTimeMillis	()J
    //   363: invokevirtual 354	class_672:a46	(J)V
    //   366: lload 5
    //   368: aload_1
    //   369: invokevirtual 443	class_672:a44	()J
    //   372: lcmp
    //   373: iflt +11 -> 384
    //   376: aload_0
    //   377: getfield 85	class_720:jdField_field_991_of_type_Boolean	Z
    //   380: pop
    //   381: aload_2
    //   382: monitorexit
    //   383: return
    //   384: invokestatic 120	java/lang/System:currentTimeMillis	()J
    //   387: pop2
    //   388: invokestatic 120	java/lang/System:currentTimeMillis	()J
    //   391: pop2
    //   392: aload_0
    //   393: aload_1
    //   394: getfield 430	class_672:field_34	Lclass_48;
    //   397: getfield 37	class_48:field_475	I
    //   400: aload_1
    //   401: getfield 430	class_672:field_34	Lclass_48;
    //   404: getfield 34	class_48:field_476	I
    //   407: aload_1
    //   408: getfield 430	class_672:field_34	Lclass_48;
    //   411: getfield 253	class_48:field_477	I
    //   414: aload_0
    //   415: getfield 81	class_720:jdField_field_991_of_type_ArrayOfInt	[I
    //   418: astore 8
    //   420: istore 7
    //   422: istore 6
    //   424: istore 5
    //   426: dup
    //   427: astore 4
    //   429: iload 5
    //   431: iload 6
    //   433: iload 7
    //   435: aload 4
    //   437: getfield 83	class_720:jdField_field_991_of_type_OrgSchemaGameCommonControllerSegmentController	Lorg/schema/game/common/controller/SegmentController;
    //   440: invokespecial 265	class_720:a4	(IIILorg/schema/game/common/controller/SegmentController;)Ljava/lang/String;
    //   443: astore 9
    //   445: aload 4
    //   447: getfield 98	class_720:jdField_field_991_of_type_Class_724	Lclass_724;
    //   450: aload 9
    //   452: invokevirtual 274	class_724:a2	(Ljava/lang/String;)Ljava/io/RandomAccessFile;
    //   455: dup
    //   456: astore 9
    //   458: dup
    //   459: astore 10
    //   461: monitorenter
    //   462: aload 4
    //   464: iload 5
    //   466: iload 6
    //   468: iload 7
    //   470: aload 8
    //   472: aload 9
    //   474: invokevirtual 346	java/io/RandomAccessFile:getChannel	()Ljava/nio/channels/FileChannel;
    //   477: astore 9
    //   479: astore 8
    //   481: istore 7
    //   483: istore 6
    //   485: istore 5
    //   487: astore 4
    //   489: iload 5
    //   491: iload 6
    //   493: iload 7
    //   495: invokestatic 276	class_720:a	(III)I
    //   498: istore 5
    //   500: aload 4
    //   502: iload 5
    //   504: aload 8
    //   506: aload 9
    //   508: invokespecial 348	class_720:a2	(I[ILjava/nio/channels/FileChannel;)V
    //   511: aload 10
    //   513: monitorexit
    //   514: goto +9 -> 523
    //   517: astore_1
    //   518: aload 10
    //   520: monitorexit
    //   521: aload_1
    //   522: athrow
    //   523: invokestatic 120	java/lang/System:currentTimeMillis	()J
    //   526: pop2
    //   527: invokestatic 120	java/lang/System:currentTimeMillis	()J
    //   530: pop2
    //   531: new 451	class_726
    //   534: dup
    //   535: aload_3
    //   536: invokespecial 452	class_726:<init>	(Ljava/io/File;)V
    //   539: astore_3
    //   540: new 127	java/io/BufferedOutputStream
    //   543: dup
    //   544: aload_3
    //   545: sipush 8192
    //   548: invokespecial 132	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;I)V
    //   551: astore 4
    //   553: new 454	java/io/DataOutputStream
    //   556: dup
    //   557: aload 4
    //   559: invokespecial 457	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   562: astore 5
    //   564: aload_0
    //   565: getfield 81	class_720:jdField_field_991_of_type_ArrayOfInt	[I
    //   568: iconst_0
    //   569: iaload
    //   570: dup
    //   571: istore 6
    //   573: ifge +59 -> 632
    //   576: aload_3
    //   577: invokevirtual 459	class_726:b	()J
    //   580: lstore 11
    //   582: iconst_0
    //   583: lload 11
    //   585: getstatic 278	class_720:jdField_field_992_of_type_Int	I
    //   588: i2l
    //   589: lsub
    //   590: getstatic 383	class_720:jdField_field_993_of_type_Int	I
    //   593: i2l
    //   594: lsub
    //   595: getstatic 185	class_720:jdField_field_993_of_type_ArrayOfByte	[B
    //   598: arraylength
    //   599: i2l
    //   600: lsub
    //   601: ldc2_w 460
    //   604: ldiv
    //   605: l2i
    //   606: invokestatic 465	java/lang/Math:max	(II)I
    //   609: istore 7
    //   611: aload_3
    //   612: aload_3
    //   613: invokevirtual 459	class_726:b	()J
    //   616: ldc2_w 460
    //   619: ladd
    //   620: invokevirtual 468	class_726:b1	(J)V
    //   623: aload_3
    //   624: lload 11
    //   626: invokevirtual 470	class_726:a1	(J)V
    //   629: goto +87 -> 716
    //   632: aload_3
    //   633: getstatic 185	class_720:jdField_field_993_of_type_ArrayOfByte	[B
    //   636: arraylength
    //   637: getstatic 278	class_720:jdField_field_992_of_type_Int	I
    //   640: iadd
    //   641: getstatic 383	class_720:jdField_field_993_of_type_Int	I
    //   644: iadd
    //   645: iload 6
    //   647: sipush 5120
    //   650: imul
    //   651: iadd
    //   652: i2l
    //   653: invokevirtual 470	class_726:a1	(J)V
    //   656: iload 6
    //   658: istore 7
    //   660: aload_0
    //   661: getfield 83	class_720:jdField_field_991_of_type_OrgSchemaGameCommonControllerSegmentController	Lorg/schema/game/common/controller/SegmentController;
    //   664: instanceof 474
    //   667: ifeq +49 -> 716
    //   670: aload_0
    //   671: getfield 83	class_720:jdField_field_991_of_type_OrgSchemaGameCommonControllerSegmentController	Lorg/schema/game/common/controller/SegmentController;
    //   674: invokevirtual 144	org/schema/game/common/controller/SegmentController:isOnServer	()Z
    //   677: ifeq +39 -> 716
    //   680: getstatic 150	java/lang/System:err	Ljava/io/PrintStream;
    //   683: new 45	java/lang/StringBuilder
    //   686: dup
    //   687: ldc_w 476
    //   690: invokespecial 155	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   693: iload 6
    //   695: invokevirtual 53	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   698: ldc 66
    //   700: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   703: aload_1
    //   704: getfield 430	class_672:field_34	Lclass_48;
    //   707: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   710: invokevirtual 70	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   713: invokevirtual 181	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   716: aload_3
    //   717: invokevirtual 478	class_726:a	()J
    //   720: pop2
    //   721: aload_1
    //   722: aload 5
    //   724: invokevirtual 482	class_672:a45	(Ljava/io/DataOutputStream;)I
    //   727: istore 6
    //   729: aload 4
    //   731: invokevirtual 485	java/io/BufferedOutputStream:flush	()V
    //   734: getstatic 39	class_720:jdField_field_992_of_type_Boolean	Z
    //   737: ifne +40 -> 777
    //   740: iload 6
    //   742: sipush 5120
    //   745: if_icmplt +32 -> 777
    //   748: new 43	java/lang/AssertionError
    //   751: dup
    //   752: new 45	java/lang/StringBuilder
    //   755: dup
    //   756: invokespecial 49	java/lang/StringBuilder:<init>	()V
    //   759: iload 6
    //   761: invokevirtual 53	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   764: ldc_w 487
    //   767: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   770: invokevirtual 70	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   773: invokespecial 73	java/lang/AssertionError:<init>	(Ljava/lang/Object;)V
    //   776: athrow
    //   777: invokestatic 120	java/lang/System:currentTimeMillis	()J
    //   780: pop2
    //   781: aload_3
    //   782: invokevirtual 459	class_726:b	()J
    //   785: pop2
    //   786: invokestatic 120	java/lang/System:currentTimeMillis	()J
    //   789: pop2
    //   790: aload_1
    //   791: getfield 430	class_672:field_34	Lclass_48;
    //   794: getfield 37	class_48:field_475	I
    //   797: aload_1
    //   798: getfield 430	class_672:field_34	Lclass_48;
    //   801: getfield 34	class_48:field_476	I
    //   804: aload_1
    //   805: getfield 430	class_672:field_34	Lclass_48;
    //   808: getfield 253	class_48:field_477	I
    //   811: invokestatic 276	class_720:a	(III)I
    //   814: istore 8
    //   816: invokestatic 120	java/lang/System:currentTimeMillis	()J
    //   819: pop2
    //   820: invokestatic 120	java/lang/System:currentTimeMillis	()J
    //   823: pop2
    //   824: aload_3
    //   825: getstatic 185	class_720:jdField_field_993_of_type_ArrayOfByte	[B
    //   828: arraylength
    //   829: iload 8
    //   831: aload_0
    //   832: getfield 77	class_720:jdField_field_991_of_type_ArrayOfByte	[B
    //   835: arraylength
    //   836: imul
    //   837: iadd
    //   838: i2l
    //   839: invokevirtual 470	class_726:a1	(J)V
    //   842: getstatic 39	class_720:jdField_field_992_of_type_Boolean	Z
    //   845: ifne +19 -> 864
    //   848: iload 6
    //   850: sipush 5120
    //   853: if_icmplt +11 -> 864
    //   856: new 43	java/lang/AssertionError
    //   859: dup
    //   860: invokespecial 488	java/lang/AssertionError:<init>	()V
    //   863: athrow
    //   864: aload 5
    //   866: iload 7
    //   868: invokevirtual 492	java/io/DataOutputStream:writeInt	(I)V
    //   871: aload 5
    //   873: iload 6
    //   875: invokevirtual 492	java/io/DataOutputStream:writeInt	(I)V
    //   878: aload 4
    //   880: invokevirtual 485	java/io/BufferedOutputStream:flush	()V
    //   883: aload_3
    //   884: getstatic 185	class_720:jdField_field_993_of_type_ArrayOfByte	[B
    //   887: arraylength
    //   888: getstatic 278	class_720:jdField_field_992_of_type_Int	I
    //   891: iadd
    //   892: iload 8
    //   894: aload_0
    //   895: getfield 79	class_720:jdField_field_992_of_type_ArrayOfByte	[B
    //   898: arraylength
    //   899: imul
    //   900: iadd
    //   901: i2l
    //   902: invokevirtual 470	class_726:a1	(J)V
    //   905: aload 5
    //   907: aload_1
    //   908: invokevirtual 443	class_672:a44	()J
    //   911: invokevirtual 495	java/io/DataOutputStream:writeLong	(J)V
    //   914: aload 4
    //   916: invokevirtual 485	java/io/BufferedOutputStream:flush	()V
    //   919: invokestatic 120	java/lang/System:currentTimeMillis	()J
    //   922: pop2
    //   923: invokestatic 120	java/lang/System:currentTimeMillis	()J
    //   926: pop2
    //   927: invokestatic 120	java/lang/System:currentTimeMillis	()J
    //   930: pop2
    //   931: invokestatic 120	java/lang/System:currentTimeMillis	()J
    //   934: pop2
    //   935: aload 5
    //   937: invokevirtual 496	java/io/DataOutputStream:flush	()V
    //   940: aload_3
    //   941: invokevirtual 497	class_726:flush	()V
    //   944: aload 5
    //   946: invokevirtual 498	java/io/DataOutputStream:close	()V
    //   949: aload_3
    //   950: invokevirtual 499	class_726:close	()V
    //   953: invokestatic 120	java/lang/System:currentTimeMillis	()J
    //   956: pop2
    //   957: aload_2
    //   958: monitorexit
    //   959: return
    //   960: astore_1
    //   961: aload_2
    //   962: monitorexit
    //   963: aload_1
    //   964: athrow
    //   965: astore_2
    //   966: aload_0
    //   967: getfield 83	class_720:jdField_field_991_of_type_OrgSchemaGameCommonControllerSegmentController	Lorg/schema/game/common/controller/SegmentController;
    //   970: invokevirtual 144	org/schema/game/common/controller/SegmentController:isOnServer	()Z
    //   973: ifeq +39 -> 1012
    //   976: aload_0
    //   977: getfield 83	class_720:jdField_field_991_of_type_OrgSchemaGameCommonControllerSegmentController	Lorg/schema/game/common/controller/SegmentController;
    //   980: invokevirtual 93	org/schema/game/common/controller/SegmentController:getState	()Lorg/schema/schine/network/StateInterface;
    //   983: checkcast 100	class_1041
    //   986: invokevirtual 503	class_1041:a59	()Lorg/schema/game/server/controller/GameServerController;
    //   989: ldc_w 505
    //   992: iconst_3
    //   993: invokevirtual 511	org/schema/game/server/controller/GameServerController:broadcastMessage	(Ljava/lang/String;I)V
    //   996: aload_0
    //   997: getfield 83	class_720:jdField_field_991_of_type_OrgSchemaGameCommonControllerSegmentController	Lorg/schema/game/common/controller/SegmentController;
    //   1000: invokevirtual 93	org/schema/game/common/controller/SegmentController:getState	()Lorg/schema/schine/network/StateInterface;
    //   1003: checkcast 100	class_1041
    //   1006: sipush 600
    //   1009: invokevirtual 514	class_1041:a34	(I)V
    //   1012: aload_2
    //   1013: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1014	0	this	class_720
    //   0	1014	1	paramclass_672	class_672
    //   965	48	2	localIOException	IOException
    //   84	866	3	localObject1	Object
    //   138	777	4	localObject2	Object
    //   136	158	5	localclass_672	class_672
    //   348	19	5	l1	long
    //   424	79	5	i	int
    //   562	383	5	localDataOutputStream	DataOutputStream
    //   167	707	6	j	int
    //   212	49	7	localRandomAccessFile	RandomAccessFile
    //   420	447	7	k	int
    //   814	86	8	m	int
    //   308	6	9	localObject3	Object
    //   443	64	9	localObject4	Object
    //   580	45	11	l2	long
    // Exception table:
    //   from	to	target	type
    //   218	305	308	finally
    //   462	514	517	finally
    //   55	318	960	finally
    //   319	383	960	finally
    //   384	959	960	finally
    //   0	25	965	java/io/IOException
    //   26	50	965	java/io/IOException
    //   51	318	965	java/io/IOException
    //   319	383	965	java/io/IOException
    //   384	965	965	java/io/IOException
  }
  
  public static void a10(class_672 paramclass_672, int[] paramArrayOfInt, File paramFile)
  {
    Object localObject1 = paramFile.getName().substring(0, paramFile.getName().indexOf("."));
    int i = ByteUtil.a(paramclass_672.field_34.field_475 + 128) / jdField_field_991_of_type_Class_48.field_475 - (paramclass_672.field_34.field_475 + 128 < 0 ? 1 : 0);
    int j = ByteUtil.a(paramclass_672.field_34.field_476 + 128) / jdField_field_991_of_type_Class_48.field_476 - (paramclass_672.field_34.field_476 + 128 < 0 ? 1 : 0);
    int k = ByteUtil.a(paramclass_672.field_34.field_477 + 128) / jdField_field_991_of_type_Class_48.field_477 - (paramclass_672.field_34.field_477 + 128 < 0 ? 1 : 0);
    if (!(paramFile = new File(paramFile.getParentFile().getAbsolutePath() + "/" + (String)localObject1 + "." + i + "." + j + "." + k + ".smd2")).exists())
    {
      paramFile.createNewFile();
      long l1 = System.currentTimeMillis();
      localObject1 = new FileOutputStream(paramFile);
      localObject2 = new BufferedOutputStream((OutputStream)localObject1);
      DataOutputStream localDataOutputStream;
      (localDataOutputStream = new DataOutputStream((OutputStream)localObject2)).write(field_994);
      localDataOutputStream.close();
      ((FileOutputStream)localObject1).close();
      ((BufferedOutputStream)localObject2).close();
      ((FileOutputStream)localObject1).close();
      if ((paramclass_672.a15() != null) && (!paramclass_672.a15().isOnServer())) {
        System.err.println("Wrote Empty Header " + field_994.length + ": " + paramFile.getName() + "; " + (System.currentTimeMillis() - l1) + "ms; for " + paramclass_672.field_34);
      }
    }
    RandomAccessFile localRandomAccessFile = new RandomAccessFile(paramFile, "r");
    int m = a(paramclass_672.field_34.field_475, paramclass_672.field_34.field_476, paramclass_672.field_34.field_477);
    localRandomAccessFile.seek(jdField_field_993_of_type_ArrayOfByte.length + (m << 3));
    paramArrayOfInt[0] = localRandomAccessFile.readInt();
    paramArrayOfInt[1] = localRandomAccessFile.readInt();
    localRandomAccessFile.close();
    localObject1 = new class_726(paramFile);
    Object localObject2 = new DataOutputStream((OutputStream)localObject1);
    int[] arrayOfInt;
    if ((arrayOfInt = paramArrayOfInt[0]) < 0)
    {
      long l2 = ((class_726)localObject1).b();
      paramArrayOfInt = Math.max(0, (int)((l2 - jdField_field_992_of_type_Int - jdField_field_993_of_type_Int - jdField_field_993_of_type_ArrayOfByte.length) / 5120L));
      ((class_726)localObject1).b1(((class_726)localObject1).b() + 5120L);
      ((class_726)localObject1).a1(l2);
    }
    else
    {
      ((class_726)localObject1).a1(jdField_field_993_of_type_ArrayOfByte.length + jdField_field_992_of_type_Int + jdField_field_993_of_type_Int + arrayOfInt * 5120);
      paramArrayOfInt = arrayOfInt;
    }
    ((class_726)localObject1).a();
    paramFile = paramclass_672.a45((DataOutputStream)localObject2);
    if ((!jdField_field_992_of_type_Boolean) && (paramFile >= 5120)) {
      throw new AssertionError(paramFile + "/5120");
    }
    ((class_726)localObject1).b();
    paramclass_672 = a(paramclass_672.field_34.field_475, paramclass_672.field_34.field_476, paramclass_672.field_34.field_477);
    ((class_726)localObject1).a1(jdField_field_993_of_type_ArrayOfByte.length + (paramclass_672 << 3));
    if ((!jdField_field_992_of_type_Boolean) && (paramFile >= 5120)) {
      throw new AssertionError();
    }
    ((DataOutputStream)localObject2).writeInt(paramArrayOfInt);
    ((DataOutputStream)localObject2).writeInt(paramFile);
    ((class_726)localObject1).a1(jdField_field_993_of_type_ArrayOfByte.length + jdField_field_992_of_type_Int + (paramclass_672 << 3));
    ((DataOutputStream)localObject2).writeLong(System.currentTimeMillis());
    ((class_726)localObject1).flush();
    ((DataOutputStream)localObject2).flush();
    ((DataOutputStream)localObject2).close();
    ((class_726)localObject1).close();
  }
  
  static
  {
    jdField_field_991_of_type_Class_48 = new class_48(16, 16, 16);
    jdField_field_993_of_type_ArrayOfByte = ByteUtil.a5(0);
    jdField_field_992_of_type_Int = (class_720.jdField_field_991_of_type_Int = jdField_field_991_of_type_Class_48.field_475 * jdField_field_991_of_type_Class_48.field_476 * jdField_field_991_of_type_Class_48.field_477) << 3;
    jdField_field_993_of_type_Int = jdField_field_991_of_type_Int << 3;
    field_994 = new byte[jdField_field_993_of_type_ArrayOfByte.length + jdField_field_992_of_type_Int + jdField_field_993_of_type_Int];
    field_995 = ByteUtil.a5(-1);
    int i = 0;
    for (int j = 0; j < jdField_field_993_of_type_ArrayOfByte.length; j++) {
      field_994[(i++)] = jdField_field_993_of_type_ArrayOfByte[j];
    }
    for (j = 0; j < jdField_field_991_of_type_Class_48.field_477; j++) {
      for (int k = 0; k < jdField_field_991_of_type_Class_48.field_476; k++) {
        for (int m = 0; m < jdField_field_991_of_type_Class_48.field_475; m++)
        {
          for (int n = 0; n < field_995.length; n++) {
            field_994[(i++)] = field_995[n];
          }
          i += 4;
        }
      }
    }
    if ((!jdField_field_992_of_type_Boolean) && (i != (field_994.length - 4) / 2 + 4)) {
      throw new AssertionError(i + "/" + ((field_994.length - 4) / 2 + 4));
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_720
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */