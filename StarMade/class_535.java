import org.schema.game.common.staremote.Staremote;

public final class class_535
  implements Runnable
{
  public class_535(Staremote paramStaremote, class_899 paramclass_899) {}
  
  /* Error */
  public final void run()
  {
    // Byte code:
    //   0: iconst_1
    //   1: putstatic 30	class_52:field_37	Z
    //   4: iconst_1
    //   5: putstatic 35	class_969:field_1259	Z
    //   8: aload_0
    //   9: getfield 14	class_535:jdField_field_839_of_type_OrgSchemaGameCommonStaremoteStaremote	Lorg/schema/game/common/staremote/Staremote;
    //   12: new 37	class_371
    //   15: dup
    //   16: invokespecial 38	class_371:<init>	()V
    //   19: invokestatic 44	org/schema/game/common/staremote/Staremote:a7	(Lorg/schema/game/common/staremote/Staremote;Lclass_371;)Lclass_371;
    //   22: pop
    //   23: new 26	class_52
    //   26: dup
    //   27: aload_0
    //   28: getfield 14	class_535:jdField_field_839_of_type_OrgSchemaGameCommonStaremoteStaremote	Lorg/schema/game/common/staremote/Staremote;
    //   31: invokestatic 48	org/schema/game/common/staremote/Staremote:a8	(Lorg/schema/game/common/staremote/Staremote;)Lclass_371;
    //   34: aconst_null
    //   35: invokespecial 51	class_52:<init>	(Lclass_371;Ljava/util/Observer;)V
    //   38: dup
    //   39: astore_1
    //   40: aload_0
    //   41: getfield 16	class_535:jdField_field_839_of_type_Class_899	Lclass_899;
    //   44: invokevirtual 55	class_52:a1	(Lclass_899;)V
    //   47: aload_1
    //   48: invokevirtual 58	class_52:d	()V
    //   51: aload_0
    //   52: getfield 14	class_535:jdField_field_839_of_type_OrgSchemaGameCommonStaremoteStaremote	Lorg/schema/game/common/staremote/Staremote;
    //   55: invokestatic 48	org/schema/game/common/staremote/Staremote:a8	(Lorg/schema/game/common/staremote/Staremote;)Lclass_371;
    //   58: putstatic 64	class_933:field_1156	Lorg/schema/schine/network/client/ClientState;
    //   61: aload_0
    //   62: getfield 14	class_535:jdField_field_839_of_type_OrgSchemaGameCommonStaremoteStaremote	Lorg/schema/game/common/staremote/Staremote;
    //   65: aload_0
    //   66: getfield 14	class_535:jdField_field_839_of_type_OrgSchemaGameCommonStaremoteStaremote	Lorg/schema/game/common/staremote/Staremote;
    //   69: invokestatic 48	org/schema/game/common/staremote/Staremote:a8	(Lorg/schema/game/common/staremote/Staremote;)Lclass_371;
    //   72: invokestatic 68	org/schema/game/common/staremote/Staremote:a9	(Lorg/schema/game/common/staremote/Staremote;Lclass_371;)V
    //   75: new 70	class_941
    //   78: dup
    //   79: invokespecial 71	class_941:<init>	()V
    //   82: dup
    //   83: astore_2
    //   84: invokevirtual 74	class_941:a2	()V
    //   87: aload_1
    //   88: aload_2
    //   89: invokevirtual 82	class_52:update	(Lclass_941;)V
    //   92: aload_2
    //   93: invokevirtual 85	class_941:b	()V
    //   96: ldc2_w 86
    //   99: invokestatic 93	java/lang/Thread:sleep	(J)V
    //   102: aload_0
    //   103: getfield 14	class_535:jdField_field_839_of_type_OrgSchemaGameCommonStaremoteStaremote	Lorg/schema/game/common/staremote/Staremote;
    //   106: aload_0
    //   107: getfield 14	class_535:jdField_field_839_of_type_OrgSchemaGameCommonStaremoteStaremote	Lorg/schema/game/common/staremote/Staremote;
    //   110: invokestatic 48	org/schema/game/common/staremote/Staremote:a8	(Lorg/schema/game/common/staremote/Staremote;)Lclass_371;
    //   113: invokevirtual 96	org/schema/game/common/staremote/Staremote:a1	(Lclass_371;)V
    //   116: goto -29 -> 87
    //   119: dup
    //   120: astore_1
    //   121: invokevirtual 99	org/schema/schine/network/LoginFailedException:printStackTrace	()V
    //   124: aload_1
    //   125: invokevirtual 103	org/schema/schine/network/LoginFailedException:getErrorCode	()I
    //   128: tableswitch	default:+123 -> 251, -8:+84->212, -7:+60->188, -6:+78->206, -5:+90->218, -4:+72->200, -3:+48->176, -2:+54->182, -1:+66->194
    //   177: lmul
    //   178: astore_2
    //   179: goto +75 -> 254
    //   182: ldc 107
    //   184: astore_2
    //   185: goto +69 -> 254
    //   188: ldc 109
    //   190: astore_2
    //   191: goto +63 -> 254
    //   194: ldc 111
    //   196: astore_2
    //   197: goto +57 -> 254
    //   200: ldc 113
    //   202: astore_2
    //   203: goto +51 -> 254
    //   206: ldc 115
    //   208: astore_2
    //   209: goto +45 -> 254
    //   212: ldc 117
    //   214: astore_2
    //   215: goto +39 -> 254
    //   218: new 119	java/lang/StringBuilder
    //   221: dup
    //   222: ldc 121
    //   224: invokespecial 124	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   227: getstatic 130	class_1266:field_1446	F
    //   230: invokevirtual 134	java/lang/StringBuilder:append	(F)Ljava/lang/StringBuilder;
    //   233: ldc 136
    //   235: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: getstatic 142	class_371:serverVersion	F
    //   241: invokevirtual 134	java/lang/StringBuilder:append	(F)Ljava/lang/StringBuilder;
    //   244: invokevirtual 146	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   247: astore_2
    //   248: goto +6 -> 254
    //   251: ldc 148
    //   253: astore_2
    //   254: new 152	javax/swing/JFrame
    //   257: dup
    //   258: ldc 154
    //   260: invokespecial 155	javax/swing/JFrame:<init>	(Ljava/lang/String;)V
    //   263: dup
    //   264: astore_1
    //   265: iconst_1
    //   266: invokevirtual 159	javax/swing/JFrame:setAlwaysOnTop	(Z)V
    //   269: aload_1
    //   270: aload_2
    //   271: ldc 161
    //   273: iconst_1
    //   274: iconst_0
    //   275: aconst_null
    //   276: iconst_2
    //   277: anewarray 150	java/lang/String
    //   280: dup
    //   281: iconst_0
    //   282: ldc 163
    //   284: aastore
    //   285: dup
    //   286: iconst_1
    //   287: ldc 165
    //   289: aastore
    //   290: ldc 163
    //   292: invokestatic 171	javax/swing/JOptionPane:showOptionDialog	(Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;IILjavax/swing/Icon;[Ljava/lang/Object;Ljava/lang/Object;)I
    //   295: lookupswitch	default:+38->333, 0:+25->320, 1:+26->321
    //   321: getstatic 177	java/lang/System:err	Ljava/io/PrintStream;
    //   324: ldc 179
    //   326: invokevirtual 184	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   329: iconst_0
    //   330: invokestatic 188	java/lang/System:exit	(I)V
    //   333: return
    //   334: astore_1
    //   335: new 119	java/lang/StringBuilder
    //   338: dup
    //   339: invokespecial 191	java/lang/StringBuilder:<init>	()V
    //   342: aload_1
    //   343: invokevirtual 195	java/lang/Object:getClass	()Ljava/lang/Class;
    //   346: invokevirtual 200	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   349: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   352: ldc 202
    //   354: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   357: aload_1
    //   358: invokevirtual 205	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   361: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   364: invokevirtual 146	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   367: astore_2
    //   368: aload_1
    //   369: instanceof 207
    //   372: ifeq +53 -> 425
    //   375: new 119	java/lang/StringBuilder
    //   378: dup
    //   379: invokespecial 191	java/lang/StringBuilder:<init>	()V
    //   382: aload_2
    //   383: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   386: ldc 209
    //   388: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   391: aload_0
    //   392: getfield 16	class_535:jdField_field_839_of_type_Class_899	Lclass_899;
    //   395: getfield 215	class_899:jdField_field_1125_of_type_JavaLangString	Ljava/lang/String;
    //   398: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   401: ldc 217
    //   403: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   406: aload_0
    //   407: getfield 16	class_535:jdField_field_839_of_type_Class_899	Lclass_899;
    //   410: getfield 220	class_899:jdField_field_1125_of_type_Int	I
    //   413: invokevirtual 223	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   416: ldc 225
    //   418: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   421: invokevirtual 146	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   424: astore_2
    //   425: aload_1
    //   426: invokevirtual 226	java/lang/Exception:printStackTrace	()V
    //   429: aconst_null
    //   430: aload_2
    //   431: ldc 228
    //   433: iconst_1
    //   434: iconst_0
    //   435: aconst_null
    //   436: iconst_2
    //   437: anewarray 150	java/lang/String
    //   440: dup
    //   441: iconst_0
    //   442: ldc 230
    //   444: aastore
    //   445: dup
    //   446: iconst_1
    //   447: ldc 232
    //   449: aastore
    //   450: ldc 232
    //   452: invokestatic 171	javax/swing/JOptionPane:showOptionDialog	(Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;IILjavax/swing/Icon;[Ljava/lang/Object;Ljava/lang/Object;)I
    //   455: tableswitch	default:+51 -> 506, 0:+25->480, 1:+26->481, 2:+39->494
    //   481: getstatic 177	java/lang/System:err	Ljava/io/PrintStream;
    //   484: ldc 234
    //   486: invokevirtual 184	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   489: iconst_0
    //   490: invokestatic 188	java/lang/System:exit	(I)V
    //   493: return
    //   494: getstatic 177	java/lang/System:err	Ljava/io/PrintStream;
    //   497: ldc 234
    //   499: invokevirtual 184	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   502: iconst_0
    //   503: invokestatic 188	java/lang/System:exit	(I)V
    //   506: return
    //   507: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	508	0	this	class_535
    //   39	231	1	localObject1	Object
    //   334	92	1	localException	java.lang.Exception
    //   83	348	2	localObject2	Object
    //   119	1	4	localLoginFailedException	org.schema.schine.network.LoginFailedException
    // Exception table:
    //   from	to	target	type
    //   0	119	119	org/schema/schine/network/LoginFailedException
    //   0	119	334	java/lang/Exception
    //   0	333	507	finally
    //   334	506	507	finally
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_535
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */