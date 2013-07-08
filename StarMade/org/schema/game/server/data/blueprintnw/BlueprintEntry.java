/*   1:    */package org.schema.game.server.data.blueprintnw;
/*   2:    */
/*   3:    */import Ah;
/*   4:    */import ct;
/*   5:    */import d;
/*   6:    */import java.io.BufferedInputStream;
/*   7:    */import java.io.BufferedOutputStream;
/*   8:    */import java.io.DataInputStream;
/*   9:    */import java.io.DataOutputStream;
/*  10:    */import java.io.File;
/*  11:    */import java.io.FileInputStream;
/*  12:    */import java.io.FileOutputStream;
/*  13:    */import java.io.IOException;
/*  14:    */import java.io.PrintStream;
/*  15:    */import java.nio.ByteBuffer;
/*  16:    */import java.nio.channels.FileChannel;
/*  17:    */import java.util.Arrays;
/*  18:    */import java.util.Iterator;
/*  19:    */import java.util.Locale;
/*  20:    */import java.util.Set;
/*  21:    */import javax.vecmath.Vector3f;
/*  22:    */import jv;
/*  23:    */import jx;
/*  24:    */import kQ;
/*  25:    */import lE;
/*  26:    */import ld;
/*  27:    */import le;
/*  28:    */import org.schema.game.common.controller.SegmentController;
/*  29:    */import org.schema.game.common.controller.elements.ManagerContainer;
/*  30:    */import org.schema.game.common.data.element.ControlElementMap;
/*  31:    */import org.schema.game.common.data.element.ControlElementMapper;
/*  32:    */import org.schema.game.common.data.element.ElementDocking;
/*  33:    */import org.schema.game.common.data.element.ElementKeyMap;
/*  34:    */import org.schema.game.common.data.world.Segment;
/*  35:    */import org.schema.game.common.updater.FileUtil;
/*  36:    */import q;
/*  37:    */import tH;
/*  38:    */import vH;
/*  39:    */import vI;
/*  40:    */import vJ;
/*  41:    */import vK;
/*  42:    */import vc;
/*  43:    */import vg;
/*  44:    */import vn;
/*  45:    */import xO;
/*  46:    */
/*  55:    */public class BlueprintEntry
/*  56:    */  implements vc
/*  57:    */{
/*  58:    */  private xO jdField_a_of_type_XO;
/*  59:    */  private String jdField_a_of_type_JavaLangString;
/*  60:    */  private vK jdField_a_of_type_VK;
/*  61:    */  private int jdField_a_of_type_Int;
/*  62:    */  private jx jdField_a_of_type_Jx;
/*  63:    */  private File jdField_a_of_type_JavaIoFile;
/*  64:    */  private File jdField_b_of_type_JavaIoFile;
/*  65:    */  private File c;
/*  66:    */  private Ah jdField_a_of_type_Ah;
/*  67:    */  private File d;
/*  68:    */  private final tH jdField_a_of_type_TH;
/*  69:    */  private ControlElementMapper jdField_a_of_type_OrgSchemaGameCommonDataElementControlElementMapper;
/*  70:    */  private BlueprintEntry[] jdField_a_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry;
/*  71:    */  private BlueprintEntry[] jdField_b_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry;
/*  72:    */  private BlueprintEntry jdField_a_of_type_OrgSchemaGameServerDataBlueprintnwBlueprintEntry;
/*  73:    */  private SegmentController jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/*  74:    */  private q jdField_a_of_type_Q;
/*  75:    */  private Vector3f jdField_a_of_type_JavaxVecmathVector3f;
/*  76:    */  private short jdField_a_of_type_Short;
/*  77:    */  private byte jdField_a_of_type_Byte;
/*  78:    */  
/*  79:    */  public BlueprintEntry(String paramString)
/*  80:    */  {
/*  81: 81 */    this(paramString, tH.jdField_a_of_type_TH);
/*  82:    */  }
/*  83:    */  
/*  84:    */  private BlueprintEntry(String paramString, tH paramtH) {
/*  85: 85 */    this.jdField_a_of_type_TH = paramtH;
/*  86: 86 */    tH localtH = paramtH;paramtH = paramString;paramString = this;this.jdField_a_of_type_JavaLangString = paramtH;paramString.jdField_a_of_type_JavaIoFile = new File(localtH.jdField_a_of_type_JavaLangString + "/" + paramtH + "/header.smbph");paramString.jdField_b_of_type_JavaIoFile = new File(localtH.jdField_a_of_type_JavaLangString + "/" + paramtH + "/logic.smbpl");paramString.c = new File(localtH.jdField_a_of_type_JavaLangString + "/" + paramtH + "/meta.smbpm");paramString.d = new File(localtH.jdField_a_of_type_JavaLangString + "/" + paramtH + "/DATA/");
/*  87:    */  }
/*  88:    */  
/*  96:    */  public final File[] a()
/*  97:    */  {
/*  98: 98 */    return this.d.listFiles();
/*  99:    */  }
/* 100:    */  
/* 101:    */  public final void a() {
/* 102:102 */    Object localObject = this.jdField_a_of_type_JavaIoFile;BlueprintEntry localBlueprintEntry = this;(localObject = new DataInputStream(new BufferedInputStream(new FileInputStream((File)localObject)))).readInt();localBlueprintEntry.jdField_a_of_type_VK = vK.values()[localObject.readInt()];Vector3f localVector3f1 = new Vector3f(((DataInputStream)localObject).readFloat(), ((DataInputStream)localObject).readFloat(), ((DataInputStream)localObject).readFloat());Vector3f localVector3f2 = new Vector3f(((DataInputStream)localObject).readFloat(), ((DataInputStream)localObject).readFloat(), ((DataInputStream)localObject).readFloat());localBlueprintEntry.jdField_a_of_type_XO = new xO(localVector3f1, localVector3f2);localBlueprintEntry.jdField_a_of_type_Jx = new jx();localBlueprintEntry.jdField_a_of_type_Jx.a((DataInputStream)localObject); BlueprintEntry tmp122_121 = localBlueprintEntry;tmp122_121.jdField_a_of_type_Int = tmp122_121.jdField_a_of_type_Jx.a();((DataInputStream)localObject).close();
/* 103:    */    
/* 104:104 */    a(this.c);
/* 105:    */  }
/* 106:    */  
/* 107:    */  public static void main(String[] paramArrayOfString) {
/* 108:108 */    ElementKeyMap.initializeData();
/* 109:109 */    paramArrayOfString = new BlueprintEntry("llllll");
/* 110:    */    try {
/* 111:111 */      paramArrayOfString.a();
/* 112:112 */      paramArrayOfString.a(true); return;
/* 113:113 */    } catch (IOException localIOException) { 
/* 114:    */      
/* 115:115 */        localIOException;
/* 116:    */    }
/* 117:    */  }
/* 118:    */  
/* 119:    */  public final void a(vn paramvn, String paramString) {
/* 120:118 */    File localFile = new File(this.jdField_a_of_type_TH.jdField_a_of_type_JavaLangString + "/" + this.jdField_a_of_type_JavaLangString + "Tmp/");
/* 121:119 */    this.jdField_a_of_type_JavaIoFile.getParentFile().renameTo(localFile);
/* 122:120 */    if (!this.jdField_a_of_type_JavaIoFile.getParentFile().exists()) {
/* 123:121 */      this.jdField_a_of_type_JavaIoFile.getParentFile().mkdirs();
/* 124:    */    }
/* 125:    */    try {
/* 126:124 */      b(paramvn, paramString);
/* 127:125 */      paramString = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.jdField_a_of_type_JavaIoFile)));
/* 128:126 */      String str = paramString;Object localObject1 = paramvn;str.writeInt(0);str.writeInt(((vn)localObject1).jdField_a_of_type_Int);Object localObject2 = new q(((vn)localObject1).jdField_a_of_type_XO.jdField_a_of_type_JavaxVecmathVector3f);q localq = new q(((vn)localObject1).jdField_a_of_type_XO.b);str.writeFloat(((q)localObject2).jdField_a_of_type_Int);str.writeFloat(((q)localObject2).b);str.writeFloat(((q)localObject2).c);str.writeFloat(localq.jdField_a_of_type_Int);str.writeFloat(localq.b);str.writeFloat(localq.c);(localObject2 = new jx()).a(((vn)localObject1).jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntArrayMap);((jx)localObject2).a(str);
/* 129:127 */      paramString.close();
/* 130:    */      
/* 131:129 */      paramString = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.jdField_b_of_type_JavaIoFile)));
/* 132:130 */      str = paramString;localObject1 = paramvn;str.writeInt(0);ControlElementMap.serialize(str, ((vn)localObject1).a());
/* 133:131 */      paramString.close();
/* 134:    */      
/* 136:134 */      (localObject1 = paramvn = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.c)))).writeInt(0);((DataOutputStream)localObject1).writeByte(1);
/* 137:135 */      paramvn.close();
/* 138:    */      
/* 141:139 */      FileUtil.a(localFile); return;
/* 142:    */    } catch (Exception localException) {
/* 143:141 */      FileUtil.a(this.jdField_a_of_type_JavaIoFile.getParentFile());
/* 144:    */    }
/* 145:    */  }
/* 146:    */  
/* 147:    */  private void b(vn paramvn, String paramString) {
/* 148:146 */    paramString = new File(paramString + "/DATA/");
/* 149:147 */    if ((!jdField_a_of_type_Boolean) && (!paramString.isDirectory())) { throw new AssertionError(paramString.getAbsolutePath());
/* 150:    */    }
/* 151:    */    
/* 156:154 */    int i = (paramString = paramString.listFiles(new vH(paramvn))).length; for (int j = 0; j < i; j++) { Object localObject1;
/* 157:155 */      if ((localObject1 = paramString[j]).getName().startsWith(paramvn.jdField_a_of_type_JavaLangString + "."))
/* 158:    */      {
/* 163:161 */        if (!(localObject2 = new File(this.jdField_a_of_type_TH.jdField_a_of_type_JavaLangString + "/" + this.jdField_a_of_type_JavaLangString + "/DATA/" + ((File)localObject1).getName())).getParentFile().exists()) {
/* 164:162 */          ((File)localObject2).getParentFile().mkdirs();
/* 165:    */        }
/* 166:164 */        localObject1 = new FileInputStream((File)localObject1);
/* 167:165 */        Object localObject2 = new FileOutputStream((File)localObject2);
/* 168:    */        
/* 169:167 */        localObject1 = ((FileInputStream)localObject1).getChannel();
/* 170:168 */        localObject2 = ((FileOutputStream)localObject2).getChannel();
/* 171:    */        
/* 172:170 */        ByteBuffer localByteBuffer = lE.a;
/* 173:    */        for (;;)
/* 174:    */        {
/* 175:173 */          localByteBuffer.clear();
/* 176:    */          
/* 179:177 */          if (((FileChannel)localObject1).read(localByteBuffer) == -1) break;
/* 180:178 */          localByteBuffer.flip();
/* 181:    */          
/* 185:183 */          ((FileChannel)localObject2).write(localByteBuffer);
/* 186:    */        }
/* 187:185 */        ((FileChannel)localObject1).close();
/* 188:186 */        ((FileChannel)localObject2).close();
/* 189:    */      }
/* 190:    */    }
/* 191:    */    
/* 200:198 */    paramString = this.jdField_a_of_type_TH.jdField_a_of_type_JavaLangString + "/" + this.jdField_a_of_type_JavaLangString + "/DATA/";
/* 201:    */    File localFile;
/* 202:200 */    if (((localFile = new File(paramString)).exists()) && (localFile.isDirectory())) {
/* 203:    */      File[] arrayOfFile;
/* 204:202 */      if (((arrayOfFile = localFile.listFiles()).length > 0) && (arrayOfFile[0].getName().endsWith(".smd")) && (!arrayOfFile[0].getName().endsWith(".smd2"))) {
/* 205:203 */        tH.b(paramString);
/* 206:    */      }
/* 207:    */    }
/* 208:    */  }
/* 209:    */  
/* 250:    */  public final void a(SegmentController paramSegmentController, boolean paramBoolean)
/* 251:    */  {
/* 252:250 */    File localFile = new File(this.jdField_a_of_type_TH.jdField_a_of_type_JavaLangString + "/" + this.jdField_a_of_type_JavaLangString + "Tmp/");
/* 253:251 */    this.jdField_a_of_type_JavaIoFile.getParentFile().renameTo(localFile);
/* 254:252 */    if (!this.jdField_a_of_type_JavaIoFile.getParentFile().exists()) {
/* 255:253 */      this.jdField_a_of_type_JavaIoFile.getParentFile().mkdirs();
/* 256:    */    }
/* 257:255 */    DataOutputStream localDataOutputStream1 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.jdField_a_of_type_JavaIoFile)));
/* 258:256 */    DataOutputStream localDataOutputStream2 = localDataOutputStream1;SegmentController localSegmentController = paramSegmentController;localDataOutputStream2.writeInt(0);localDataOutputStream2.writeInt(vK.a(localSegmentController.getClass()).ordinal());q localq1 = new q(localSegmentController.getBoundingBox().jdField_a_of_type_JavaxVecmathVector3f);q localq2 = new q(localSegmentController.getBoundingBox().b);localDataOutputStream2.writeFloat(localq1.jdField_a_of_type_Int);localDataOutputStream2.writeFloat(localq1.b);localDataOutputStream2.writeFloat(localq1.c);localDataOutputStream2.writeFloat(localq2.jdField_a_of_type_Int);localDataOutputStream2.writeFloat(localq2.b);localDataOutputStream2.writeFloat(localq2.c);localSegmentController.getElementClassCountMap().a(localDataOutputStream2);
/* 259:257 */    localDataOutputStream1.close();
/* 260:    */    
/* 261:259 */    localDataOutputStream1 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.jdField_b_of_type_JavaIoFile)));
/* 262:260 */    localDataOutputStream2 = localDataOutputStream1;localSegmentController = paramSegmentController;localDataOutputStream2.writeInt(0);ControlElementMap.serialize(localDataOutputStream2, localSegmentController.getControlElementMap().getControllingMap());
/* 263:261 */    localDataOutputStream1.close();
/* 264:    */    
/* 265:263 */    localDataOutputStream1 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.c)));
/* 266:264 */    a(paramSegmentController, localDataOutputStream1);
/* 267:265 */    localDataOutputStream1.close();
/* 268:    */    
/* 272:270 */    if (paramBoolean) {
/* 273:271 */      b(paramSegmentController);
/* 274:    */    } else {
/* 275:273 */      a(paramSegmentController);
/* 276:    */    }
/* 277:275 */    if (this.jdField_a_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry.length > 0) {
/* 278:276 */      for (paramSegmentController = 0; paramSegmentController < this.jdField_a_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry.length; paramSegmentController++) {
/* 279:277 */        this.jdField_a_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[paramSegmentController].a(this.jdField_a_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[paramSegmentController].jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController, paramBoolean);
/* 280:278 */        this.jdField_a_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[paramSegmentController].jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = null;
/* 281:    */      }
/* 282:    */    }
/* 283:    */    
/* 285:283 */    this.jdField_a_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry = null;
/* 286:284 */    if (this.jdField_a_of_type_OrgSchemaGameServerDataBlueprintnwBlueprintEntry == null) {
/* 287:285 */      FileUtil.a(localFile);
/* 288:    */    }
/* 289:    */  }
/* 290:    */  
/* 312:    */  private void a(SegmentController paramSegmentController)
/* 313:    */  {
/* 314:312 */    for (File localFile : new File(vg.f)
/* 315:305 */      .listFiles(new vI(paramSegmentController)))
/* 316:    */    {
/* 317:    */      Object localObject;
/* 318:    */      
/* 324:314 */      if (this.jdField_a_of_type_OrgSchemaGameServerDataBlueprintnwBlueprintEntry == null) {
/* 325:315 */        localObject = new File(this.jdField_a_of_type_TH.jdField_a_of_type_JavaLangString + "/" + this.jdField_a_of_type_JavaLangString + "/DATA/" + localFile.getName());
/* 326:    */      } else {
/* 327:317 */        localObject = localFile.getName();
/* 328:318 */        localObject = this.jdField_a_of_type_JavaLangString.substring(this.jdField_a_of_type_JavaLangString.indexOf("/")) + ((String)localObject).substring(((String)localObject).indexOf("."));
/* 329:319 */        localObject = new File(this.jdField_a_of_type_TH.jdField_a_of_type_JavaLangString + "/" + this.jdField_a_of_type_JavaLangString + "/DATA/" + (String)localObject);
/* 330:    */      }
/* 331:321 */      if (!((File)localObject).getParentFile().exists()) {
/* 332:322 */        ((File)localObject).getParentFile().mkdirs();
/* 333:    */      }
/* 334:324 */      FileUtil.b(localFile, (File)localObject);
/* 335:    */    }
/* 336:    */  }
/* 337:    */  
/* 338:328 */  private void b(SegmentController paramSegmentController) { File localFile1 = new File(ct.b);
/* 339:329 */    StringBuilder localStringBuilder = new StringBuilder();
/* 340:330 */    kQ.a(paramSegmentController, localStringBuilder);
/* 341:331 */    paramSegmentController = localStringBuilder.toString();
/* 342:    */    
/* 349:339 */    for (File localFile2 : localFile1.listFiles(new vJ(paramSegmentController)))
/* 350:    */    {
/* 351:    */      Object localObject;
/* 352:    */      
/* 358:341 */      if (this.jdField_a_of_type_OrgSchemaGameServerDataBlueprintnwBlueprintEntry == null) {
/* 359:342 */        localObject = new File(this.jdField_a_of_type_TH.jdField_a_of_type_JavaLangString + "/" + this.jdField_a_of_type_JavaLangString + "/DATA/" + localFile2.getName());
/* 360:    */      } else {
/* 361:344 */        localObject = localFile2.getName();
/* 362:345 */        localObject = this.jdField_a_of_type_JavaLangString.substring(this.jdField_a_of_type_JavaLangString.indexOf("/")) + ((String)localObject).substring(((String)localObject).indexOf("."));
/* 363:346 */        localObject = new File(this.jdField_a_of_type_TH.jdField_a_of_type_JavaLangString + "/" + this.jdField_a_of_type_JavaLangString + "/DATA/" + (String)localObject);
/* 364:    */      }
/* 365:    */      
/* 366:349 */      if (!((File)localObject).getParentFile().exists()) {
/* 367:350 */        ((File)localObject).getParentFile().mkdirs();
/* 368:    */      }
/* 369:352 */      FileUtil.b(localFile2, (File)localObject);
/* 370:    */    }
/* 371:    */  }
/* 372:    */  
/* 373:    */  private void a(SegmentController paramSegmentController, DataOutputStream paramDataOutputStream) {
/* 374:357 */    paramDataOutputStream.writeInt(0);
/* 375:    */    
/* 376:359 */    paramDataOutputStream.writeByte(3);
/* 377:    */    
/* 378:361 */    paramDataOutputStream.writeInt(paramSegmentController.getDockingController().a().size());
/* 379:362 */    this.jdField_a_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry = new BlueprintEntry[paramSegmentController.getDockingController().a().size()];
/* 380:363 */    int i = 0;
/* 381:364 */    for (Iterator localIterator = paramSegmentController.getDockingController().a().iterator(); localIterator.hasNext();) { ElementDocking localElementDocking;
/* 382:365 */      SegmentController localSegmentController = (localElementDocking = (ElementDocking)localIterator.next()).from.a().a();
/* 383:366 */      q localq = localElementDocking.to.a(new q());
/* 384:367 */      String str = this.jdField_a_of_type_JavaLangString + "/ATTACHED_" + i;
/* 385:368 */      paramDataOutputStream.writeUTF(str);
/* 386:369 */      paramDataOutputStream.writeInt(localq.jdField_a_of_type_Int);
/* 387:370 */      paramDataOutputStream.writeInt(localq.b);
/* 388:371 */      paramDataOutputStream.writeInt(localq.c);
/* 389:372 */      paramDataOutputStream.writeFloat(localSegmentController.getDockingController().a().x);
/* 390:373 */      paramDataOutputStream.writeFloat(localSegmentController.getDockingController().a().y);
/* 391:374 */      paramDataOutputStream.writeFloat(localSegmentController.getDockingController().a().z);
/* 392:375 */      paramDataOutputStream.writeShort(localElementDocking.to.a());
/* 393:376 */      paramDataOutputStream.writeByte(-1);
/* 394:377 */      this.jdField_a_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[i] = new BlueprintEntry(str, this.jdField_a_of_type_TH);
/* 395:378 */      this.jdField_a_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[i].jdField_a_of_type_OrgSchemaGameServerDataBlueprintnwBlueprintEntry = this;
/* 396:379 */      this.jdField_a_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[i].jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = localSegmentController;
/* 397:380 */      i++;
/* 398:381 */      paramDataOutputStream.flush();
/* 399:    */    }
/* 400:383 */    if ((paramSegmentController instanceof ld)) {
/* 401:384 */      paramDataOutputStream.writeByte(2);
/* 402:    */      
/* 403:386 */      ((ld)paramSegmentController).a().toTagStructure()
/* 404:387 */        .a(paramDataOutputStream, false);
/* 405:    */    }
/* 406:    */    else {
/* 407:390 */      paramDataOutputStream.writeByte(1);
/* 408:    */    }
/* 409:392 */    paramDataOutputStream.flush();
/* 410:    */  }
/* 411:    */  
/* 412:    */  private void a(File paramFile)
/* 413:    */  {
/* 414:397 */    (paramFile = new DataInputStream(new BufferedInputStream(new FileInputStream(paramFile)))).readInt();
/* 415:    */    
/* 416:399 */    int i = 0;
/* 417:400 */    int j = 0;
/* 418:401 */    while ((j == 0) && ((i = paramFile.readByte()) != 1))
/* 419:    */    {
/* 420:403 */      switch (i) {
/* 421:    */      case 2: 
/* 422:405 */        this.jdField_a_of_type_Ah = Ah.a(paramFile, false);
/* 423:    */        
/* 424:407 */        j = 1;
/* 425:408 */        break;
/* 426:    */      
/* 427:    */      case 3: 
/* 428:411 */        i = paramFile.readInt();
/* 429:412 */        this.jdField_b_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry = new BlueprintEntry[i];
/* 430:413 */        for (int k = 0; k < i; k++)
/* 431:    */        {
/* 432:415 */          String str = paramFile.readUTF();
/* 433:416 */          this.jdField_b_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[k] = new BlueprintEntry(str, this.jdField_a_of_type_TH);
/* 434:417 */          this.jdField_b_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[k].jdField_a_of_type_Q = new q(paramFile.readInt(), paramFile.readInt(), paramFile.readInt());
/* 435:418 */          this.jdField_b_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[k].jdField_a_of_type_JavaxVecmathVector3f = new Vector3f(paramFile.readFloat(), paramFile.readFloat(), paramFile.readFloat());
/* 436:419 */          this.jdField_b_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[k].jdField_a_of_type_Short = paramFile.readShort();
/* 437:420 */          this.jdField_b_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[k].jdField_a_of_type_Byte = paramFile.readByte();
/* 438:421 */          this.jdField_b_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[k].jdField_a_of_type_OrgSchemaGameServerDataBlueprintnwBlueprintEntry = this;
/* 439:    */        }
/* 440:    */      }
/* 441:    */      
/* 442:    */    }
/* 443:    */    
/* 444:427 */    paramFile.close();
/* 445:428 */    if (this.jdField_b_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry != null) {
/* 446:429 */      for (i = 0; i < this.jdField_b_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry.length; i++) {
/* 447:430 */        this.jdField_b_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[i].a();
/* 448:    */      }
/* 449:    */    }
/* 450:    */  }
/* 451:    */  
/* 471:    */  private ControlElementMapper a(boolean paramBoolean)
/* 472:    */  {
/* 473:456 */    boolean bool = paramBoolean;Object localObject = this.jdField_b_of_type_JavaIoFile;paramBoolean = this; if ((this.jdField_a_of_type_OrgSchemaGameCommonDataElementControlElementMapper == null) || (bool)) { (localObject = new DataInputStream(new BufferedInputStream(new FileInputStream((File)localObject)))).readInt();ControlElementMapper localControlElementMapper = new ControlElementMapper();ControlElementMap.deserialize((DataInputStream)localObject, localControlElementMapper);paramBoolean.jdField_a_of_type_OrgSchemaGameCommonDataElementControlElementMapper = localControlElementMapper;((DataInputStream)localObject).close(); } return paramBoolean.jdField_a_of_type_OrgSchemaGameCommonDataElementControlElementMapper;
/* 474:    */  }
/* 475:    */  
/* 517:    */  public final Ah a()
/* 518:    */  {
/* 519:502 */    return this.jdField_a_of_type_Ah;
/* 520:    */  }
/* 521:    */  
/* 522:    */  public final ControlElementMapper a()
/* 523:    */  {
/* 524:    */    try {
/* 525:508 */      return a(false);
/* 526:509 */    } catch (IOException localIOException) { localIOException;
/* 527:    */    }
/* 528:511 */    return new ControlElementMapper();
/* 529:    */  }
/* 530:    */  
/* 532:    */  public final int a()
/* 533:    */  {
/* 534:517 */    return this.jdField_a_of_type_Int;
/* 535:    */  }
/* 536:    */  
/* 539:    */  public final BlueprintEntry[] a()
/* 540:    */  {
/* 541:524 */    return this.jdField_b_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry;
/* 542:    */  }
/* 543:    */  
/* 547:    */  public final q a()
/* 548:    */  {
/* 549:532 */    return this.jdField_a_of_type_Q;
/* 550:    */  }
/* 551:    */  
/* 555:    */  public final short a()
/* 556:    */  {
/* 557:540 */    return this.jdField_a_of_type_Short;
/* 558:    */  }
/* 559:    */  
/* 562:    */  public final jx a()
/* 563:    */  {
/* 564:547 */    return this.jdField_a_of_type_Jx;
/* 565:    */  }
/* 566:    */  
/* 570:    */  public final vK a()
/* 571:    */  {
/* 572:555 */    return this.jdField_a_of_type_VK;
/* 573:    */  }
/* 574:    */  
/* 577:    */  public final String a()
/* 578:    */  {
/* 579:562 */    return this.jdField_a_of_type_JavaLangString;
/* 580:    */  }
/* 581:    */  
/* 585:    */  public final xO a()
/* 586:    */  {
/* 587:570 */    return this.jdField_a_of_type_XO;
/* 588:    */  }
/* 589:    */  
/* 592:    */  public final tH a()
/* 593:    */  {
/* 594:577 */    return this.jdField_a_of_type_TH;
/* 595:    */  }
/* 596:    */  
/* 599:    */  public final byte a()
/* 600:    */  {
/* 601:584 */    return this.jdField_a_of_type_Byte;
/* 602:    */  }
/* 603:    */  
/* 606:    */  public final Vector3f a()
/* 607:    */  {
/* 608:591 */    return this.jdField_a_of_type_JavaxVecmathVector3f;
/* 609:    */  }
/* 610:    */  
/* 613:    */  public static String a(File paramFile, tH paramtH)
/* 614:    */  {
/* 615:    */    File localFile1;
/* 616:    */    
/* 618:601 */    if ((localFile1 = new File("./bbtmp/")).exists()) {
/* 619:602 */      FileUtil.a(localFile1);
/* 620:    */    }
/* 621:604 */    if (!localFile1.exists()) {
/* 622:605 */      localFile1.mkdir();
/* 623:    */    }
/* 624:607 */    FileUtil.a(paramFile, "./bbtmp/");
/* 625:608 */    paramFile = 0;
/* 626:    */    Object localObject1;
/* 627:610 */    if ((localObject1 = localFile1.listFiles()).length != 1) {
/* 628:611 */      throw new IOException("wrong file format to import. Must be exctly one dir, but found " + Arrays.toString(localFile1.list()));
/* 629:    */    }
/* 630:    */    
/* 631:    */    File[] arrayOfFile;
/* 632:615 */    if ((arrayOfFile = (localObject1 = localObject1[0]).listFiles()).length > 0) {
/* 633:616 */      for (int j = 0; j < arrayOfFile.length; j++) {
/* 634:617 */        if (arrayOfFile[j].getName().toLowerCase(Locale.ENGLISH).endsWith(".txt")) {
/* 635:618 */          System.err.println("[BLUEPRINT][IMPORT] Found Old Data " + arrayOfFile[j].getName());
/* 636:619 */          paramtH.a("./bbtmp/" + ((File)localObject1).getName() + "/", false, arrayOfFile[j].getName());
/* 637:620 */          paramFile = 1;
/* 638:621 */          break;
/* 639:    */        }
/* 640:    */      }
/* 641:    */    } else
/* 642:625 */      throw new IOException("wrong file format to import. found " + Arrays.toString(((File)localObject1).list()));
/* 643:    */    Object localObject2;
/* 644:627 */    if (paramFile == 0) {
/* 645:628 */      localObject2 = ((File)localObject1).list();
/* 646:629 */      System.err.println("failed to import old method. no Catalog.txt found in " + Arrays.toString((Object[])localObject2) + " trying new!");
/* 647:630 */      for (int i = 0; i < localObject2.length; i++) {
/* 648:631 */        if (!localObject2[i].equals("header.smbph")) {
/* 649:632 */          paramFile = 1;
/* 650:633 */          break;
/* 651:    */        }
/* 652:    */      }
/* 653:636 */      if (paramFile == 0) {
/* 654:637 */        throw new IOException("ERROR: No blueprint data found to import: " + Arrays.toString((Object[])localObject2));
/* 655:    */      }
/* 656:    */    }
/* 657:    */    
/* 660:643 */    if (!(localObject2 = localFile1.listFiles())[0].isDirectory()) {
/* 661:644 */      throw new IOException("not a directory: " + localObject2[0].getAbsolutePath());
/* 662:    */    }
/* 663:646 */    File localFile2 = new File(paramtH.jdField_a_of_type_JavaLangString + "/" + localObject2[0].getName());
/* 664:647 */    System.err.println("IMPORT: " + localObject2[0].getAbsolutePath());
/* 665:648 */    FileUtil.a(localObject2[0], localFile2);
/* 666:    */    
/* 667:650 */    paramFile = paramtH.jdField_a_of_type_JavaLangString + "/" + localObject2[0].getName() + "/DATA";
/* 668:    */    
/* 669:652 */    if (((paramtH = new File(paramFile)).exists()) && (paramtH.isDirectory()))
/* 670:    */    {
/* 671:654 */      if (((paramtH = paramtH.listFiles()).length > 0) && (paramtH[0].getName().endsWith(".smd")) && (!paramtH[0].getName().endsWith(".smd2"))) {
/* 672:655 */        tH.b(paramFile);
/* 673:    */      }
/* 674:    */    }
/* 675:    */    
/* 676:659 */    FileUtil.a(localFile1);
/* 677:660 */    return localFile2.getName();
/* 678:    */  }
/* 679:    */  
/* 680:    */  public final File a()
/* 681:    */  {
/* 682:665 */    String str1 = this.jdField_a_of_type_TH.jdField_a_of_type_JavaLangString + File.separator + this.jdField_a_of_type_JavaLangString + "/";
/* 683:    */    
/* 684:667 */    String str2 = this.jdField_a_of_type_TH.jdField_a_of_type_JavaLangString + File.separator + "exported" + File.separator + this.jdField_a_of_type_JavaLangString + ".sment";
/* 685:    */    File localFile;
/* 686:669 */    if (!(localFile = new File(str2)).getParentFile().exists()) {
/* 687:670 */      localFile.getParentFile().mkdirs();
/* 688:    */    }
/* 689:672 */    d.a(str1, str2, null);
/* 690:    */    
/* 691:674 */    return new File(str2);
/* 692:    */  }
/* 693:    */  
/* 696:    */  public String toString()
/* 697:    */  {
/* 698:681 */    return this.jdField_a_of_type_JavaLangString;
/* 699:    */  }
/* 700:    */  
/* 704:    */  public final boolean a()
/* 705:    */  {
/* 706:689 */    return this.d.exists();
/* 707:    */  }
/* 708:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.server.data.blueprintnw.BlueprintEntry
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */