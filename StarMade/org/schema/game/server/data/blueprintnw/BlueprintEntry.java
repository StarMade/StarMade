/*     */ package org.schema.game.server.data.blueprintnw;
/*     */ 
/*     */ import Ad;
/*     */ import ct;
/*     */ import d;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.Locale;
/*     */ import java.util.Set;
/*     */ import javax.vecmath.Vector3f;
/*     */ import jv;
/*     */ import jx;
/*     */ import kQ;
/*     */ import lE;
/*     */ import ld;
/*     */ import le;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.ManagerContainer;
/*     */ import org.schema.game.common.data.element.ControlElementMap;
/*     */ import org.schema.game.common.data.element.ControlElementMapper;
/*     */ import org.schema.game.common.data.element.ElementDocking;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.common.updater.FileUtil;
/*     */ import q;
/*     */ import tH;
/*     */ import vH;
/*     */ import vI;
/*     */ import vJ;
/*     */ import vK;
/*     */ import vc;
/*     */ import vg;
/*     */ import vn;
/*     */ import xO;
/*     */ 
/*     */ public class BlueprintEntry
/*     */   implements vc
/*     */ {
/*     */   private xO jdField_a_of_type_XO;
/*     */   private String jdField_a_of_type_JavaLangString;
/*     */   private vK jdField_a_of_type_VK;
/*     */   private int jdField_a_of_type_Int;
/*     */   private jx jdField_a_of_type_Jx;
/*     */   private File jdField_a_of_type_JavaIoFile;
/*     */   private File jdField_b_of_type_JavaIoFile;
/*     */   private File c;
/*     */   private Ad jdField_a_of_type_Ad;
/*     */   private File d;
/*     */   private final tH jdField_a_of_type_TH;
/*     */   private ControlElementMapper jdField_a_of_type_OrgSchemaGameCommonDataElementControlElementMapper;
/*     */   private BlueprintEntry[] jdField_a_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry;
/*     */   private BlueprintEntry[] jdField_b_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry;
/*     */   private BlueprintEntry jdField_a_of_type_OrgSchemaGameServerDataBlueprintnwBlueprintEntry;
/*     */   private SegmentController jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/*     */   private q jdField_a_of_type_Q;
/*     */   private Vector3f jdField_a_of_type_JavaxVecmathVector3f;
/*     */   private short jdField_a_of_type_Short;
/*     */   private byte jdField_a_of_type_Byte;
/*     */ 
/*     */   public BlueprintEntry(String paramString)
/*     */   {
/*  81 */     this(paramString, tH.jdField_a_of_type_TH);
/*     */   }
/*     */ 
/*     */   private BlueprintEntry(String paramString, tH paramtH) {
/*  85 */     this.jdField_a_of_type_TH = paramtH;
/*  86 */     tH localtH = paramtH; paramtH = paramString; paramString = this; this.jdField_a_of_type_JavaLangString = paramtH; paramString.jdField_a_of_type_JavaIoFile = new File(localtH.jdField_a_of_type_JavaLangString + "/" + paramtH + "/header.smbph"); paramString.jdField_b_of_type_JavaIoFile = new File(localtH.jdField_a_of_type_JavaLangString + "/" + paramtH + "/logic.smbpl"); paramString.c = new File(localtH.jdField_a_of_type_JavaLangString + "/" + paramtH + "/meta.smbpm"); paramString.d = new File(localtH.jdField_a_of_type_JavaLangString + "/" + paramtH + "/DATA/");
/*     */   }
/*     */ 
/*     */   public final File[] a()
/*     */   {
/*  98 */     return this.d.listFiles();
/*     */   }
/*     */ 
/*     */   public final void a() {
/* 102 */     Object localObject = this.jdField_a_of_type_JavaIoFile; BlueprintEntry localBlueprintEntry = this; (localObject = new DataInputStream(new BufferedInputStream(new FileInputStream((File)localObject)))).readInt(); localBlueprintEntry.jdField_a_of_type_VK = vK.values()[localObject.readInt()]; Vector3f localVector3f1 = new Vector3f(((DataInputStream)localObject).readFloat(), ((DataInputStream)localObject).readFloat(), ((DataInputStream)localObject).readFloat()); Vector3f localVector3f2 = new Vector3f(((DataInputStream)localObject).readFloat(), ((DataInputStream)localObject).readFloat(), ((DataInputStream)localObject).readFloat()); localBlueprintEntry.jdField_a_of_type_XO = new xO(localVector3f1, localVector3f2); localBlueprintEntry.jdField_a_of_type_Jx = new jx(); localBlueprintEntry.jdField_a_of_type_Jx.a((DataInputStream)localObject);
/*     */     BlueprintEntry tmp122_121 = localBlueprintEntry; tmp122_121.jdField_a_of_type_Int = tmp122_121.jdField_a_of_type_Jx.a(); ((DataInputStream)localObject).close();
/*     */ 
/* 104 */     a(this.c);
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString) {
/* 108 */     ElementKeyMap.initializeData();
/* 109 */     paramArrayOfString = new BlueprintEntry("llllll");
/*     */     try { paramArrayOfString.a();
/* 112 */       paramArrayOfString.a(true);
/*     */       return; } catch (IOException localIOException) {
/* 115 */       localIOException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(vn paramvn, String paramString) {
/* 118 */     File localFile = new File(this.jdField_a_of_type_TH.jdField_a_of_type_JavaLangString + "/" + this.jdField_a_of_type_JavaLangString + "Tmp/");
/* 119 */     this.jdField_a_of_type_JavaIoFile.getParentFile().renameTo(localFile);
/* 120 */     if (!this.jdField_a_of_type_JavaIoFile.getParentFile().exists())
/* 121 */       this.jdField_a_of_type_JavaIoFile.getParentFile().mkdirs(); try { b(paramvn, paramString);
/* 125 */       paramString = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.jdField_a_of_type_JavaIoFile)));
/* 126 */       String str = paramString; Object localObject1 = paramvn; str.writeInt(0); str.writeInt(((vn)localObject1).jdField_a_of_type_Int); Object localObject2 = new q(((vn)localObject1).jdField_a_of_type_XO.jdField_a_of_type_JavaxVecmathVector3f); q localq = new q(((vn)localObject1).jdField_a_of_type_XO.b); str.writeFloat(((q)localObject2).jdField_a_of_type_Int); str.writeFloat(((q)localObject2).b); str.writeFloat(((q)localObject2).c); str.writeFloat(localq.jdField_a_of_type_Int); str.writeFloat(localq.b); str.writeFloat(localq.c); (localObject2 = new jx()).a(((vn)localObject1).jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntArrayMap); ((jx)localObject2).a(str);
/* 127 */       paramString.close();
/*     */ 
/* 129 */       paramString = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.jdField_b_of_type_JavaIoFile)));
/* 130 */       str = paramString; localObject1 = paramvn; str.writeInt(0); ControlElementMap.serialize(str, ((vn)localObject1).a());
/* 131 */       paramString.close();
/*     */ 
/* 134 */       (localObject1 = paramvn = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.c))))
/* 134 */         .writeInt(0); ((DataOutputStream)localObject1).writeByte(1);
/* 135 */       paramvn.close();
/*     */ 
/* 139 */       FileUtil.a(localFile);
/*     */       return; } catch (Exception localException) { FileUtil.a(this.jdField_a_of_type_JavaIoFile.getParentFile()); }
/*     */   }
/*     */ 
/*     */   private void b(vn paramvn, String paramString)
/*     */   {
/* 146 */     paramString = new File(paramString + "/DATA/");
/* 147 */     if ((!jdField_a_of_type_Boolean) && (!paramString.isDirectory())) throw new AssertionError(paramString.getAbsolutePath());
/*     */ 
/* 154 */     int i = (paramString = paramString.listFiles(new vH(paramvn))).length;
/*     */ 
/* 154 */     for (int j = 0; j < i; j++)
/*     */     {
/*     */       Object localObject1;
/* 155 */       if ((
/* 155 */         localObject1 = paramString[j])
/* 155 */         .getName().startsWith(paramvn.jdField_a_of_type_JavaLangString + "."))
/*     */       {
/* 161 */         if (!(
/* 161 */           localObject2 = new File(this.jdField_a_of_type_TH.jdField_a_of_type_JavaLangString + "/" + this.jdField_a_of_type_JavaLangString + "/DATA/" + ((File)localObject1).getName()))
/* 161 */           .getParentFile().exists()) {
/* 162 */           ((File)localObject2).getParentFile().mkdirs();
/*     */         }
/* 164 */         localObject1 = new FileInputStream((File)localObject1);
/* 165 */         Object localObject2 = new FileOutputStream((File)localObject2);
/*     */ 
/* 167 */         localObject1 = ((FileInputStream)localObject1).getChannel();
/* 168 */         localObject2 = ((FileOutputStream)localObject2).getChannel();
/*     */ 
/* 170 */         ByteBuffer localByteBuffer = lE.a;
/*     */         while (true)
/*     */         {
/* 173 */           localByteBuffer.clear();
/*     */ 
/* 177 */           if (((FileChannel)localObject1).read(localByteBuffer) == 
/* 177 */             -1) break;
/* 178 */           localByteBuffer.flip();
/*     */ 
/* 183 */           ((FileChannel)localObject2).write(localByteBuffer);
/*     */         }
/* 185 */         ((FileChannel)localObject1).close();
/* 186 */         ((FileChannel)localObject2).close();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 198 */     paramString = this.jdField_a_of_type_TH.jdField_a_of_type_JavaLangString + "/" + this.jdField_a_of_type_JavaLangString + "/DATA/";
/*     */     File localFile;
/* 200 */     if (((
/* 200 */       localFile = new File(paramString))
/* 200 */       .exists()) && (localFile.isDirectory()))
/*     */     {
/*     */       File[] arrayOfFile;
/* 202 */       if (((
/* 202 */         arrayOfFile = localFile.listFiles()).length > 0) && 
/* 202 */         (arrayOfFile[0].getName().endsWith(".smd")) && (!arrayOfFile[0].getName().endsWith(".smd2")))
/* 203 */         tH.b(paramString);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(SegmentController paramSegmentController, boolean paramBoolean)
/*     */   {
/* 250 */     File localFile = new File(this.jdField_a_of_type_TH.jdField_a_of_type_JavaLangString + "/" + this.jdField_a_of_type_JavaLangString + "Tmp/");
/* 251 */     this.jdField_a_of_type_JavaIoFile.getParentFile().renameTo(localFile);
/* 252 */     if (!this.jdField_a_of_type_JavaIoFile.getParentFile().exists()) {
/* 253 */       this.jdField_a_of_type_JavaIoFile.getParentFile().mkdirs();
/*     */     }
/* 255 */     DataOutputStream localDataOutputStream1 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.jdField_a_of_type_JavaIoFile)));
/* 256 */     DataOutputStream localDataOutputStream2 = localDataOutputStream1; SegmentController localSegmentController = paramSegmentController; localDataOutputStream2.writeInt(0); localDataOutputStream2.writeInt(vK.a(localSegmentController.getClass()).ordinal()); q localq1 = new q(localSegmentController.getBoundingBox().jdField_a_of_type_JavaxVecmathVector3f); q localq2 = new q(localSegmentController.getBoundingBox().b); localDataOutputStream2.writeFloat(localq1.jdField_a_of_type_Int); localDataOutputStream2.writeFloat(localq1.b); localDataOutputStream2.writeFloat(localq1.c); localDataOutputStream2.writeFloat(localq2.jdField_a_of_type_Int); localDataOutputStream2.writeFloat(localq2.b); localDataOutputStream2.writeFloat(localq2.c); localSegmentController.getElementClassCountMap().a(localDataOutputStream2);
/* 257 */     localDataOutputStream1.close();
/*     */ 
/* 259 */     localDataOutputStream1 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.jdField_b_of_type_JavaIoFile)));
/* 260 */     localDataOutputStream2 = localDataOutputStream1; localSegmentController = paramSegmentController; localDataOutputStream2.writeInt(0); ControlElementMap.serialize(localDataOutputStream2, localSegmentController.getControlElementMap().getControllingMap());
/* 261 */     localDataOutputStream1.close();
/*     */ 
/* 263 */     localDataOutputStream1 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.c)));
/* 264 */     a(paramSegmentController, localDataOutputStream1);
/* 265 */     localDataOutputStream1.close();
/*     */ 
/* 270 */     if (paramBoolean)
/* 271 */       b(paramSegmentController);
/*     */     else {
/* 273 */       a(paramSegmentController);
/*     */     }
/* 275 */     if (this.jdField_a_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry.length > 0) {
/* 276 */       for (paramSegmentController = 0; paramSegmentController < this.jdField_a_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry.length; paramSegmentController++) {
/* 277 */         this.jdField_a_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[paramSegmentController].a(this.jdField_a_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[paramSegmentController].jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController, paramBoolean);
/* 278 */         this.jdField_a_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[paramSegmentController].jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = null;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 283 */     this.jdField_a_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry = null;
/* 284 */     if (this.jdField_a_of_type_OrgSchemaGameServerDataBlueprintnwBlueprintEntry == null)
/* 285 */       FileUtil.a(localFile);
/*     */   }
/*     */ 
/*     */   private void a(SegmentController paramSegmentController)
/*     */   {
/* 312 */     for (File localFile : new File(vg.f)
/* 305 */       .listFiles(new vI(paramSegmentController)))
/*     */     {
/*     */       Object localObject;
/* 314 */       if (this.jdField_a_of_type_OrgSchemaGameServerDataBlueprintnwBlueprintEntry == null) {
/* 315 */         localObject = new File(this.jdField_a_of_type_TH.jdField_a_of_type_JavaLangString + "/" + this.jdField_a_of_type_JavaLangString + "/DATA/" + localFile.getName());
/*     */       } else {
/* 317 */         localObject = localFile.getName();
/* 318 */         localObject = this.jdField_a_of_type_JavaLangString.substring(this.jdField_a_of_type_JavaLangString.indexOf("/")) + ((String)localObject).substring(((String)localObject).indexOf("."));
/* 319 */         localObject = new File(this.jdField_a_of_type_TH.jdField_a_of_type_JavaLangString + "/" + this.jdField_a_of_type_JavaLangString + "/DATA/" + (String)localObject);
/*     */       }
/* 321 */       if (!((File)localObject).getParentFile().exists()) {
/* 322 */         ((File)localObject).getParentFile().mkdirs();
/*     */       }
/* 324 */       FileUtil.b(localFile, (File)localObject);
/*     */     }
/*     */   }
/*     */ 
/* 328 */   private void b(SegmentController paramSegmentController) { File localFile1 = new File(ct.b);
/* 329 */     StringBuilder localStringBuilder = new StringBuilder();
/* 330 */     kQ.a(paramSegmentController, localStringBuilder);
/* 331 */     paramSegmentController = localStringBuilder.toString();
/*     */ 
/* 339 */     for (File localFile2 : localFile1.listFiles(new vJ(paramSegmentController)))
/*     */     {
/*     */       Object localObject;
/* 341 */       if (this.jdField_a_of_type_OrgSchemaGameServerDataBlueprintnwBlueprintEntry == null) {
/* 342 */         localObject = new File(this.jdField_a_of_type_TH.jdField_a_of_type_JavaLangString + "/" + this.jdField_a_of_type_JavaLangString + "/DATA/" + localFile2.getName());
/*     */       } else {
/* 344 */         localObject = localFile2.getName();
/* 345 */         localObject = this.jdField_a_of_type_JavaLangString.substring(this.jdField_a_of_type_JavaLangString.indexOf("/")) + ((String)localObject).substring(((String)localObject).indexOf("."));
/* 346 */         localObject = new File(this.jdField_a_of_type_TH.jdField_a_of_type_JavaLangString + "/" + this.jdField_a_of_type_JavaLangString + "/DATA/" + (String)localObject);
/*     */       }
/*     */ 
/* 349 */       if (!((File)localObject).getParentFile().exists()) {
/* 350 */         ((File)localObject).getParentFile().mkdirs();
/*     */       }
/* 352 */       FileUtil.b(localFile2, (File)localObject);
/*     */     } }
/*     */ 
/*     */   private void a(SegmentController paramSegmentController, DataOutputStream paramDataOutputStream)
/*     */   {
/* 357 */     paramDataOutputStream.writeInt(0);
/*     */ 
/* 359 */     paramDataOutputStream.writeByte(3);
/*     */ 
/* 361 */     paramDataOutputStream.writeInt(paramSegmentController.getDockingController().a().size());
/* 362 */     this.jdField_a_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry = new BlueprintEntry[paramSegmentController.getDockingController().a().size()];
/* 363 */     int i = 0;
/* 364 */     for (Iterator localIterator = paramSegmentController.getDockingController().a().iterator(); localIterator.hasNext(); )
/*     */     {
/*     */       ElementDocking localElementDocking;
/* 365 */       SegmentController localSegmentController = (
/* 365 */         localElementDocking = (ElementDocking)localIterator.next()).from
/* 365 */         .a().a();
/* 366 */       q localq = localElementDocking.to.a(new q());
/* 367 */       String str = this.jdField_a_of_type_JavaLangString + "/ATTACHED_" + i;
/* 368 */       paramDataOutputStream.writeUTF(str);
/* 369 */       paramDataOutputStream.writeInt(localq.jdField_a_of_type_Int);
/* 370 */       paramDataOutputStream.writeInt(localq.b);
/* 371 */       paramDataOutputStream.writeInt(localq.c);
/* 372 */       paramDataOutputStream.writeFloat(localSegmentController.getDockingController().a().x);
/* 373 */       paramDataOutputStream.writeFloat(localSegmentController.getDockingController().a().y);
/* 374 */       paramDataOutputStream.writeFloat(localSegmentController.getDockingController().a().z);
/* 375 */       paramDataOutputStream.writeShort(localElementDocking.to.a());
/* 376 */       paramDataOutputStream.writeByte(-1);
/* 377 */       this.jdField_a_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[i] = new BlueprintEntry(str, this.jdField_a_of_type_TH);
/* 378 */       this.jdField_a_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[i].jdField_a_of_type_OrgSchemaGameServerDataBlueprintnwBlueprintEntry = this;
/* 379 */       this.jdField_a_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[i].jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = localSegmentController;
/* 380 */       i++;
/* 381 */       paramDataOutputStream.flush();
/*     */     }
/* 383 */     if ((paramSegmentController instanceof ld)) {
/* 384 */       paramDataOutputStream.writeByte(2);
/*     */ 
/* 386 */       ((ld)paramSegmentController).a().toTagStructure()
/* 387 */         .a(paramDataOutputStream, false);
/*     */     }
/*     */     else {
/* 390 */       paramDataOutputStream.writeByte(1);
/*     */     }
/* 392 */     paramDataOutputStream.flush();
/*     */   }
/*     */   private void a(File paramFile) {
/* 395 */     (
/* 397 */       paramFile = new DataInputStream(new BufferedInputStream(new FileInputStream(paramFile))))
/* 397 */       .readInt();
/*     */ 
/* 399 */     int i = 0;
/* 400 */     int j = 0;
/* 401 */     while ((j == 0) && ((i = paramFile.readByte()) != 1))
/*     */     {
/* 403 */       switch (i) {
/*     */       case 2:
/* 405 */         this.jdField_a_of_type_Ad = Ad.a(paramFile, false);
/*     */ 
/* 407 */         j = 1;
/* 408 */         break;
/*     */       case 3:
/* 411 */         i = paramFile.readInt();
/* 412 */         this.jdField_b_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry = new BlueprintEntry[i];
/* 413 */         for (int k = 0; k < i; k++)
/*     */         {
/* 415 */           String str = paramFile.readUTF();
/* 416 */           this.jdField_b_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[k] = new BlueprintEntry(str, this.jdField_a_of_type_TH);
/* 417 */           this.jdField_b_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[k].jdField_a_of_type_Q = new q(paramFile.readInt(), paramFile.readInt(), paramFile.readInt());
/* 418 */           this.jdField_b_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[k].jdField_a_of_type_JavaxVecmathVector3f = new Vector3f(paramFile.readFloat(), paramFile.readFloat(), paramFile.readFloat());
/* 419 */           this.jdField_b_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[k].jdField_a_of_type_Short = paramFile.readShort();
/* 420 */           this.jdField_b_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[k].jdField_a_of_type_Byte = paramFile.readByte();
/* 421 */           this.jdField_b_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[k].jdField_a_of_type_OrgSchemaGameServerDataBlueprintnwBlueprintEntry = this;
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 427 */     paramFile.close();
/* 428 */     if (this.jdField_b_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry != null)
/* 429 */       for (i = 0; i < this.jdField_b_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry.length; i++)
/* 430 */         this.jdField_b_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[i].a();
/*     */   }
/*     */ 
/*     */   private ControlElementMapper a(boolean paramBoolean)
/*     */   {
/* 456 */     boolean bool = paramBoolean; Object localObject = this.jdField_b_of_type_JavaIoFile; paramBoolean = this; if ((this.jdField_a_of_type_OrgSchemaGameCommonDataElementControlElementMapper == null) || (bool)) { (localObject = new DataInputStream(new BufferedInputStream(new FileInputStream((File)localObject)))).readInt(); ControlElementMapper localControlElementMapper = new ControlElementMapper(); ControlElementMap.deserialize((DataInputStream)localObject, localControlElementMapper); paramBoolean.jdField_a_of_type_OrgSchemaGameCommonDataElementControlElementMapper = localControlElementMapper; ((DataInputStream)localObject).close(); } return paramBoolean.jdField_a_of_type_OrgSchemaGameCommonDataElementControlElementMapper;
/*     */   }
/*     */ 
/*     */   public final Ad a()
/*     */   {
/* 502 */     return this.jdField_a_of_type_Ad;
/*     */   }
/*     */ 
/*     */   public final ControlElementMapper a()
/*     */   {
/*     */     try {
/* 508 */       return a(false); } catch (IOException localIOException) { localIOException
/* 509 */         .printStackTrace();
/*     */     }
/* 511 */     return new ControlElementMapper();
/*     */   }
/*     */ 
/*     */   public final int a()
/*     */   {
/* 517 */     return this.jdField_a_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final BlueprintEntry[] a()
/*     */   {
/* 524 */     return this.jdField_b_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry;
/*     */   }
/*     */ 
/*     */   public final q a()
/*     */   {
/* 532 */     return this.jdField_a_of_type_Q;
/*     */   }
/*     */ 
/*     */   public final short a()
/*     */   {
/* 540 */     return this.jdField_a_of_type_Short;
/*     */   }
/*     */ 
/*     */   public final vK a()
/*     */   {
/* 555 */     return this.jdField_a_of_type_VK;
/*     */   }
/*     */ 
/*     */   public final String a()
/*     */   {
/* 562 */     return this.jdField_a_of_type_JavaLangString;
/*     */   }
/*     */ 
/*     */   public final xO a()
/*     */   {
/* 570 */     return this.jdField_a_of_type_XO;
/*     */   }
/*     */ 
/*     */   public final tH a()
/*     */   {
/* 577 */     return this.jdField_a_of_type_TH;
/*     */   }
/*     */ 
/*     */   public final byte a()
/*     */   {
/* 584 */     return this.jdField_a_of_type_Byte;
/*     */   }
/*     */ 
/*     */   public final Vector3f a()
/*     */   {
/* 591 */     return this.jdField_a_of_type_JavaxVecmathVector3f;
/*     */   }
/*     */ 
/*     */   public static String a(File paramFile, tH paramtH)
/*     */   {
/*     */     File localFile1;
/* 601 */     if ((
/* 601 */       localFile1 = new File("./bbtmp/"))
/* 601 */       .exists()) {
/* 602 */       FileUtil.a(localFile1);
/*     */     }
/* 604 */     if (!localFile1.exists()) {
/* 605 */       localFile1.mkdir();
/*     */     }
/* 607 */     FileUtil.a(paramFile, "./bbtmp/");
/* 608 */     paramFile = 0;
/*     */     Object localObject1;
/* 610 */     if ((
/* 610 */       localObject1 = localFile1.listFiles()).length != 
/* 610 */       1)
/* 611 */       throw new IOException("wrong file format to import. Must be exctly one dir, but found " + Arrays.toString(localFile1.list()));
/*     */     File[] arrayOfFile;
/* 615 */     if ((
/* 615 */       arrayOfFile = (
/* 614 */       localObject1 = localObject1[0])
/* 614 */       .listFiles()).length > 0)
/*     */     {
/* 616 */       for (int j = 0; j < arrayOfFile.length; j++)
/* 617 */         if (arrayOfFile[j].getName().toLowerCase(Locale.ENGLISH).endsWith(".txt")) {
/* 618 */           System.err.println("[BLUEPRINT][IMPORT] Found Old Data " + arrayOfFile[j].getName());
/* 619 */           paramtH.a("./bbtmp/" + ((File)localObject1).getName() + "/", false, arrayOfFile[j].getName());
/* 620 */           paramFile = 1;
/* 621 */           break;
/*     */         }
/*     */     }
/*     */     else
/* 625 */       throw new IOException("wrong file format to import. found " + Arrays.toString(((File)localObject1).list()));
/*     */     Object localObject2;
/* 627 */     if (paramFile == 0) {
/* 628 */       localObject2 = ((File)localObject1).list();
/* 629 */       System.err.println("failed to import old method. no Catalog.txt found in " + Arrays.toString((Object[])localObject2) + " trying new!");
/* 630 */       for (int i = 0; i < localObject2.length; i++) {
/* 631 */         if (!localObject2[i].equals("header.smbph")) {
/* 632 */           paramFile = 1;
/* 633 */           break;
/*     */         }
/*     */       }
/* 636 */       if (paramFile == 0) {
/* 637 */         throw new IOException("ERROR: No blueprint data found to import: " + Arrays.toString((Object[])localObject2));
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 643 */     if (!(
/* 643 */       localObject2 = localFile1.listFiles())[
/* 643 */       0].isDirectory()) {
/* 644 */       throw new IOException("not a directory: " + localObject2[0].getAbsolutePath());
/*     */     }
/* 646 */     File localFile2 = new File(paramtH.jdField_a_of_type_JavaLangString + "/" + localObject2[0].getName());
/* 647 */     System.err.println("IMPORT: " + localObject2[0].getAbsolutePath());
/* 648 */     FileUtil.a(localObject2[0], localFile2);
/*     */ 
/* 650 */     paramFile = paramtH.jdField_a_of_type_JavaLangString + "/" + localObject2[0].getName() + "/DATA";
/*     */ 
/* 652 */     if (((
/* 652 */       paramtH = new File(paramFile))
/* 652 */       .exists()) && (paramtH.isDirectory()))
/*     */     {
/* 654 */       if (((
/* 654 */         paramtH = paramtH.listFiles()).length > 0) && 
/* 654 */         (paramtH[0].getName().endsWith(".smd")) && (!paramtH[0].getName().endsWith(".smd2"))) {
/* 655 */         tH.b(paramFile);
/*     */       }
/*     */     }
/*     */ 
/* 659 */     FileUtil.a(localFile1);
/* 660 */     return localFile2.getName();
/*     */   }
/*     */ 
/*     */   public final File a()
/*     */   {
/* 665 */     String str1 = this.jdField_a_of_type_TH.jdField_a_of_type_JavaLangString + File.separator + this.jdField_a_of_type_JavaLangString + "/";
/*     */ 
/* 667 */     String str2 = this.jdField_a_of_type_TH.jdField_a_of_type_JavaLangString + File.separator + "exported" + File.separator + this.jdField_a_of_type_JavaLangString + ".sment";
/*     */     File localFile;
/* 669 */     if (!(
/* 669 */       localFile = new File(str2))
/* 669 */       .getParentFile().exists()) {
/* 670 */       localFile.getParentFile().mkdirs();
/*     */     }
/* 672 */     d.a(str1, str2, null);
/*     */ 
/* 674 */     return new File(str2);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 681 */     return this.jdField_a_of_type_JavaLangString;
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 689 */     return this.d.exists();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.server.data.blueprintnw.BlueprintEntry
 * JD-Core Version:    0.6.2
 */