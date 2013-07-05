/*      */ package org.schema.game.client.view;
/*      */ 
/*      */ import cY;
/*      */ import com.bulletphysics.linearmath.Transform;
/*      */ import ct;
/*      */ import d;
/*      */ import dH;
/*      */ import dI;
/*      */ import dc;
/*      */ import de;
/*      */ import df;
/*      */ import dj;
/*      */ import dm;
/*      */ import do;
/*      */ import eI;
/*      */ import eJ;
/*      */ import eu;
/*      */ import iO;
/*      */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectHeapPriorityQueue;
/*      */ import jL;
/*      */ import java.io.PrintStream;
/*      */ import java.nio.FloatBuffer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import javax.vecmath.Matrix3f;
/*      */ import javax.vecmath.Vector3f;
/*      */ import mF;
/*      */ import mr;
/*      */ import org.lwjgl.BufferUtils;
/*      */ import org.lwjgl.input.Keyboard;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import org.lwjgl.opengl.GL13;
/*      */ import org.lwjgl.opengl.GL15;
/*      */ import org.lwjgl.util.vector.Matrix4f;
/*      */ import org.schema.game.common.controller.SegmentBufferManager;
/*      */ import org.schema.game.common.controller.SegmentController;
/*      */ import org.schema.game.common.data.element.BeamHandler.BeamState;
/*      */ import org.schema.schine.graphicsengine.camera.Camera;
/*      */ import org.schema.schine.graphicsengine.core.GlUtil;
/*      */ import org.schema.schine.network.objects.NetworkEntity;
/*      */ import org.schema.schine.network.objects.remote.RemoteBoolean;
/*      */ import q;
/*      */ import xd;
/*      */ import xe;
/*      */ import xg;
/*      */ import xq;
/*      */ import xu;
/*      */ import zF;
/*      */ import zj;
/*      */ 
/*      */ public class SegmentDrawer
/*      */   implements xg
/*      */ {
/*      */   private HashSet jdField_a_of_type_JavaUtilHashSet;
/*      */   private HashSet jdField_b_of_type_JavaUtilHashSet;
/*  729 */   private HashSet jdField_c_of_type_JavaUtilHashSet = new HashSet();
/*      */   public static int a;
/*      */   public int b;
/*      */   public int c;
/*      */   public static boolean a;
/*      */   private final ArrayList jdField_b_of_type_JavaUtilArrayList;
/*      */   public mr[] a;
/*      */   public mr[] b;
/*      */   public mr[] c;
/*      */   public mr[] d;
/*      */   private int jdField_d_of_type_Int;
/*      */   public ArrayList a;
/*  747 */   private boolean jdField_b_of_type_Boolean = true;
/*      */   public dc a;
/*      */   private ct jdField_a_of_type_Ct;
/*      */   private final de jdField_a_of_type_De;
/*      */   private final df jdField_a_of_type_Df;
/*      */   public static dH a;
/*  778 */   private HashSet jdField_d_of_type_JavaUtilHashSet = new HashSet();
/*      */   private SegmentController jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/*      */   private long jdField_a_of_type_Long;
/*      */   private boolean jdField_c_of_type_Boolean;
/*      */   private Vector3f jdField_a_of_type_JavaxVecmathVector3f;
/*      */   private Vector3f jdField_b_of_type_JavaxVecmathVector3f;
/*      */   private final ArrayList jdField_c_of_type_JavaUtilArrayList;
/*      */   private ArrayList jdField_d_of_type_JavaUtilArrayList;
/*      */   private int jdField_e_of_type_Int;
/*      */   private Matrix4f jdField_a_of_type_OrgLwjglUtilVectorMatrix4f;
/*      */   private FloatBuffer jdField_a_of_type_JavaNioFloatBuffer;
/*      */   private q jdField_a_of_type_Q;
/*      */   private zF jdField_a_of_type_ZF;
/*      */   private boolean jdField_d_of_type_Boolean;
/*      */   private long jdField_b_of_type_Long;
/*      */   private static boolean jdField_e_of_type_Boolean;
/*      */   private final q jdField_b_of_type_Q;
/*      */   private final q jdField_c_of_type_Q;
/*      */   private static Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform;
/*      */   private Matrix4f jdField_b_of_type_OrgLwjglUtilVectorMatrix4f;
/*      */   private Matrix4f jdField_c_of_type_OrgLwjglUtilVectorMatrix4f;
/*      */ 
/*      */   public static void main(String[] paramArrayOfString)
/*      */   {
/*  704 */     for (paramArrayOfString = 0; paramArrayOfString < 99; 
/*  707 */       paramArrayOfString++) {
/*  708 */       long l1 = System.nanoTime();
/*      */ 
/*  710 */       long l2 = System.currentTimeMillis();
/*  711 */       Thread.sleep(12L);
/*  712 */       l2 = System.currentTimeMillis() - l2;
/*  713 */       System.out.println("millis: " + l2);
/*      */ 
/*  715 */       long l3 = (
/*  715 */         l1 = System.nanoTime() - l1) / 
/*  715 */         1000000L;
/*  716 */       System.out.println("millis from nanos: " + l3 + ", nanos: " + l1 + " 1000000");
/*      */     }
/*      */   }
/*      */ 
/*      */   public SegmentDrawer(ct paramct)
/*      */   {
/*  731 */     this.jdField_b_of_type_Int = 1;
/*      */ 
/*  745 */     this.jdField_a_of_type_JavaUtilArrayList = new ArrayList(10);
/*      */ 
/*  748 */     this.jdField_a_of_type_Dc = new dc((byte)0);
/*      */ 
/*  782 */     new Vector3f();
/*      */ 
/*  788 */     this.jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*  789 */     this.jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*      */ 
/*  792 */     new Transform();
/*      */ 
/*  795 */     new Transform();
/*      */ 
/*  797 */     new Vector3f();
/*  798 */     new Vector3f();
/*  799 */     this.jdField_c_of_type_JavaUtilArrayList = new ArrayList();
/*  800 */     this.jdField_d_of_type_JavaUtilArrayList = new ArrayList();
/*      */ 
/*  802 */     this.jdField_e_of_type_Int = 0;
/*      */ 
/*  804 */     this.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f = new Matrix4f();
/*  805 */     this.jdField_a_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
/*      */ 
/*  807 */     this.jdField_a_of_type_Q = new q(-1, 0, 0);
/*  808 */     this.jdField_a_of_type_ZF = new zF(8.0F);
/*      */ 
/*  885 */     this.jdField_b_of_type_Q = new q();
/*      */ 
/*  887 */     this.jdField_c_of_type_Q = new q();
/*      */ 
/* 1383 */     this.jdField_b_of_type_OrgLwjglUtilVectorMatrix4f = new Matrix4f();
/* 1384 */     this.jdField_c_of_type_OrgLwjglUtilVectorMatrix4f = new Matrix4f();
/*      */ 
/*  828 */     this.jdField_a_of_type_Ct = paramct;
/*  829 */     jdField_a_of_type_DH = new dH();
/*  830 */     this.jdField_a_of_type_ArrayOfMr = new mr[jdField_a_of_type_DH.jdField_a_of_type_Int];
/*  831 */     this.jdField_b_of_type_ArrayOfMr = new mr[jdField_a_of_type_DH.jdField_a_of_type_Int];
/*  832 */     this.jdField_c_of_type_ArrayOfMr = new mr[jdField_a_of_type_DH.jdField_a_of_type_Int];
/*  833 */     this.jdField_d_of_type_ArrayOfMr = new mr[jdField_a_of_type_DH.jdField_a_of_type_Int];
/*      */ 
/*  835 */     this.jdField_a_of_type_De = new de(this);
/*  836 */     this.jdField_a_of_type_Df = new df(this);
/*  837 */     this.jdField_b_of_type_JavaUtilArrayList = new ArrayList();
/*      */   }
/*      */ 
/*      */   private void g()
/*      */   {
/*  843 */     synchronized (this.jdField_d_of_type_JavaUtilHashSet) {
/*  844 */       for (int i = 0; i < this.jdField_d_of_type_Int; i++) {
/*  845 */         if ((this.jdField_c_of_type_ArrayOfMr[i] != null) && (this.jdField_c_of_type_ArrayOfMr[i].a() < this.jdField_b_of_type_Int)) {
/*  846 */           this.jdField_c_of_type_ArrayOfMr[i].b(false);
/*  847 */           this.jdField_d_of_type_JavaUtilHashSet.add(this.jdField_c_of_type_ArrayOfMr[i]);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  853 */       Object localObject3 = null; synchronized (this.jdField_c_of_type_JavaUtilArrayList) {
/*  854 */         this.jdField_d_of_type_JavaUtilHashSet.addAll(this.jdField_c_of_type_JavaUtilArrayList);
/*      */ 
/*  856 */         this.jdField_c_of_type_JavaUtilArrayList.clear();
/*      */       }
/*  858 */       for (??? = this.jdField_d_of_type_JavaUtilHashSet.iterator(); ((Iterator)???).hasNext(); )
/*      */       {
/*      */         mr localmr;
/*  859 */         if (!(
/*  859 */           localmr = (mr)((Iterator)???).next())
/*  859 */           .c()) {
/*  860 */           localmr.c();
/*  861 */           localmr.b();
/*  862 */           localmr.b(false);
/*  863 */           this.jdField_d_of_type_JavaUtilArrayList.add(localmr);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  869 */       this.jdField_d_of_type_JavaUtilArrayList.isEmpty();
/*      */ 
/*  872 */       if ((this.jdField_d_of_type_JavaUtilArrayList.isEmpty()) && (!this.jdField_d_of_type_JavaUtilHashSet.isEmpty())) {
/*  873 */         System.err.println("not Disposed LEFT: " + this.jdField_d_of_type_JavaUtilArrayList.size() + "/" + this.jdField_d_of_type_JavaUtilHashSet.size());
/*      */       }
/*  875 */       this.jdField_d_of_type_JavaUtilHashSet.removeAll(this.jdField_d_of_type_JavaUtilArrayList);
/*  876 */       this.jdField_d_of_type_JavaUtilArrayList.clear();
/*      */     }
/*  878 */     synchronized (this.jdField_a_of_type_Df.jdField_a_of_type_JavaLangObject) {
/*  879 */       this.jdField_a_of_type_Df.jdField_a_of_type_JavaLangObject.notify();
/*      */     }
/*  881 */     jdField_a_of_type_DH.a(this.jdField_b_of_type_Int);
/*      */   }
/*      */ 
/*      */   public final void a()
/*      */   {
/*  926 */     jdField_a_of_type_DH.b();
/*      */   }
/*      */ 
/*      */   public final void d()
/*      */   {
/*  934 */     synchronized (this.jdField_b_of_type_JavaUtilArrayList) {
/*  935 */       this.jdField_b_of_type_JavaUtilArrayList.clear();
/*  936 */       return;
/*      */     }
/*      */   }
/*      */ 
/*      */   private void h()
/*      */   {
/*  974 */     d.y.a = dI.jdField_a_of_type_IO;
/*  975 */     d.y.b();
/*  976 */     GL11.glDisable(2896);
/*  977 */     GL13.glActiveTexture(33984);
/*  978 */     GL11.glEnable(2929);
/*  979 */     GL11.glEnable(2884);
/*  980 */     GL11.glEnable(3042);
/*      */ 
/*  982 */     GL11.glBlendFunc(770, 771);
/*      */ 
/*  984 */     this.jdField_a_of_type_Dc.k = 0L;
/*      */ 
/*  987 */     this.jdField_a_of_type_Dc.l = 0L;
/*  988 */     GL11.glEnableClientState(32884);
/*      */   }
/*      */ 
/*      */   public final void b()
/*      */   {
/* 1000 */     jdField_e_of_type_Boolean = Keyboard.isKeyDown(88);
/*      */ 
/* 1002 */     long l1 = System.currentTimeMillis();
/* 1003 */     this.jdField_b_of_type_Long = l1;
/*      */ 
/* 1005 */     if (this.jdField_b_of_type_Boolean)
/* 1006 */       c();
/*      */     Object localObject4;
/* 1008 */     if (jdField_a_of_type_Boolean) {
/* 1009 */       System.err.println("Executing FULL vis update");
/* 1010 */       synchronized (this.jdField_b_of_type_JavaUtilArrayList) {
/* 1011 */         for (Iterator localIterator = this.jdField_b_of_type_JavaUtilArrayList.iterator(); localIterator.hasNext(); 
/* 1012 */           ((SegmentController)localObject4).getSegmentBuffer().a(new cY(localSegmentDrawer), false))
/*      */         {
/* 1011 */           SegmentController localSegmentController1 = (SegmentController)localIterator.next();
/* 1012 */           localObject4 = localSegmentController1; localSegmentDrawer = this;
/*      */         }
/*      */       }
/*      */     }
/* 1016 */     long l2 = System.currentTimeMillis();
/*      */ 
/* 1020 */     if (System.currentTimeMillis() - this.jdField_a_of_type_De.jdField_a_of_type_Long > 1000L) {
/* 1021 */       this.jdField_a_of_type_De.jdField_a_of_type_Long = System.currentTimeMillis();
/* 1022 */       this.jdField_a_of_type_De.jdField_a_of_type_JavaLangObject = Integer.valueOf(this.jdField_a_of_type_De.jdField_a_of_type_Int);
/* 1023 */       this.jdField_a_of_type_De.jdField_a_of_type_Int = 0;
/*      */     }
/* 1025 */     xd.jdField_a_of_type_JavaUtilArrayList.add("CONTEXT UPDATES: " + this.jdField_a_of_type_De.jdField_a_of_type_JavaLangObject + "; enqueued: " + this.jdField_a_of_type_JavaUtilHashSet.size());
/* 1026 */     this.jdField_a_of_type_Long = System.currentTimeMillis();
/* 1027 */     System.currentTimeMillis();
/*      */ 
/* 1030 */     System.currentTimeMillis();
/*      */ 
/* 1033 */     h();
/*      */ 
/* 1035 */     SegmentDrawer localSegmentDrawer = null; this.jdField_a_of_type_Dc.jdField_a_of_type_Long = 0L;
/* 1036 */     this.jdField_a_of_type_Dc.jdField_b_of_type_Long = 0L;
/* 1037 */     this.jdField_a_of_type_Dc.m = 0L;
/* 1038 */     this.jdField_a_of_type_Dc.n = 0L;
/* 1039 */     this.jdField_a_of_type_Dc.c = 0L;
/* 1040 */     this.jdField_a_of_type_Dc.d = 0L;
/* 1041 */     this.jdField_a_of_type_Dc.f = 0L;
/* 1042 */     this.jdField_a_of_type_Dc.h = 0L;
/* 1043 */     this.jdField_a_of_type_Dc.e = 0L;
/* 1044 */     this.jdField_a_of_type_Dc.g = 0L;
/* 1045 */     this.jdField_a_of_type_Dc.o = 0L;
/* 1046 */     this.jdField_a_of_type_Dc.p = 0L;
/*      */ 
/* 1049 */     this.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.load(xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f);
/*      */ 
/* 1051 */     GlUtil.d();
/*      */ 
/* 1053 */     this.jdField_a_of_type_Dc.j = 0L;
/*      */ 
/* 1059 */     this.jdField_e_of_type_Int += 1;
/*      */ 
/* 1061 */     int i = 0;
/*      */     long l3;
/* 1065 */     if ((
/* 1065 */       l3 = System.currentTimeMillis() - l2) > 
/* 1065 */       10L) {
/* 1066 */       System.err.println("PREPARE TIME OF 0 elements: " + l3);
/*      */     }
/* 1068 */     ObjectHeapPriorityQueue localObjectHeapPriorityQueue = this.jdField_a_of_type_Ct.a().a().a();
/* 1069 */     BeamHandler.BeamState localBeamState = null;
/*      */     boolean bool;
/* 1073 */     if (((
/* 1073 */       bool = xu.l.b())) && 
/* 1073 */       (!localObjectHeapPriorityQueue.isEmpty())) {
/* 1074 */       localBeamState = (BeamHandler.BeamState)localObjectHeapPriorityQueue.dequeue();
/*      */     }
/*      */ 
/* 1078 */     synchronized (this.jdField_a_of_type_ArrayOfMr)
/*      */     {
/* 1080 */       long l4 = System.currentTimeMillis();
/* 1081 */       synchronized (this.jdField_a_of_type_Df.jdField_a_of_type_JavaLangObject) {
/* 1082 */         if (this.jdField_c_of_type_Boolean) {
/* 1083 */           g();
/* 1084 */           this.jdField_c_of_type_Boolean = false;
/*      */         }
/*      */       }
/*      */       long l5;
/* 1088 */       if ((
/* 1088 */         l5 = System.currentTimeMillis() - l4) > 
/* 1088 */         10L) {
/* 1089 */         System.err.println("RESORTING TIME OF 0 elements: " + l5);
/*      */       }
/*      */ 
/* 1093 */       i = Math.min(((Integer)xu.ac.a()).intValue(), 
/* 1093 */         this.jdField_d_of_type_Int);
/*      */ 
/* 1095 */       long l6 = System.nanoTime();
/* 1096 */       long l7 = System.currentTimeMillis();
/* 1097 */       this.jdField_a_of_type_Q.b(-1, 0, 0);
/*      */ 
/* 1099 */       for (int j = i - 1; j >= 0; 
/* 1117 */         j--)
/*      */       {
/* 1119 */         mr localmr = this.jdField_a_of_type_ArrayOfMr[j];
/*      */ 
/* 1121 */         if (bool)
/*      */         {
/* 1123 */           int k = 0;
/* 1124 */           while ((localBeamState != null) && (localmr.jdField_a_of_type_Float < localBeamState.camDistStart))
/*      */           {
/* 1126 */             if (k == 0) {
/* 1127 */               GlUtil.c();
/* 1128 */               i();
/*      */             }
/* 1130 */             GlUtil.d();
/* 1131 */             k = 1;
/* 1132 */             this.jdField_a_of_type_Ct.a().a().g();
/* 1133 */             dm.a(localBeamState, jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 1134 */             GlUtil.c();
/* 1135 */             if (!localObjectHeapPriorityQueue.isEmpty())
/* 1136 */               localBeamState = (BeamHandler.BeamState)localObjectHeapPriorityQueue.dequeue();
/*      */             else
/* 1138 */               localBeamState = null; 
/*      */           }
/* 1139 */           if (k != 0)
/*      */           {
/* 1143 */             this.jdField_a_of_type_Ct.a().a(); do.h();
/* 1144 */             h();
/* 1145 */             GlUtil.d();
/*      */           }
/*      */         }
/* 1148 */         localObject4 = localmr; localSegmentDrawer = this; long l9 = System.nanoTime(); Object localObject6 = localObject4; Object localObject3 = localSegmentDrawer;
/*      */         Object localObject7;
/* 1148 */         if ((!localObject6.d()) && (localObject6.jdField_a_of_type_Boolean) && (!localObject6.c())) { localObject7 = (SegmentBufferManager)localObject6.a().getSegmentBuffer(); ((SegmentDrawer)localObject3).jdField_b_of_type_Q.b(localObject6.jdField_a_of_type_Q); ((SegmentDrawer)localObject3).jdField_c_of_type_Q.b(localObject6.jdField_a_of_type_Q); ((SegmentDrawer)localObject3).jdField_b_of_type_Q.c(48, 48, 48); ((SegmentDrawer)localObject3).jdField_c_of_type_Q.a(48, 48, 48);
/*      */           long l12;
/* 1148 */           if ((((l12 = System.currentTimeMillis() - localObject6.jdField_b_of_type_Long) > 100L) && (((SegmentBufferManager)localObject7).a(((SegmentDrawer)localObject3).jdField_b_of_type_Q, ((SegmentDrawer)localObject3).jdField_c_of_type_Q) > localObject6.c)) || (l12 > 10000L)) { localObject6.jdField_a_of_type_Boolean = false; localObject6.e(true); ((SegmentDrawer)localObject3).jdField_a_of_type_Dc.jdField_a_of_type_Int += 1; }  } if (((localObject6.d()) || (localObject6.a() == null)) && (!localObject6.c())) ((SegmentDrawer)localObject3).jdField_a_of_type_De.a(localObject6); if ((((localObject6.a() == null ? 0 : 1) == 0 ? 1 : 0) | (!((mr)localObject4).b() ? 1 : 0)) != 0) { dc.a(localSegmentDrawer.jdField_a_of_type_Dc, System.nanoTime() - l9); dc.b(localSegmentDrawer.jdField_a_of_type_Dc, System.nanoTime() - l9); } else { dc.a(localSegmentDrawer.jdField_a_of_type_Dc, System.nanoTime() - l9); ((mr)localObject4).jdField_a_of_type_Long = localSegmentDrawer.jdField_a_of_type_Long; localObject6 = localObject4; localObject3 = localSegmentDrawer; localObject6.a(((SegmentDrawer)localObject3).jdField_a_of_type_JavaxVecmathVector3f, ((SegmentDrawer)localObject3).jdField_b_of_type_JavaxVecmathVector3f); if (!xe.a().a(((SegmentDrawer)localObject3).jdField_a_of_type_JavaxVecmathVector3f, ((SegmentDrawer)localObject3).jdField_b_of_type_JavaxVecmathVector3f)) { dc.b(localSegmentDrawer.jdField_a_of_type_Dc, System.nanoTime() - l9); } else { long l10 = System.nanoTime() - l9; localSegmentDrawer.jdField_a_of_type_Dc.m += l10; if (localSegmentDrawer.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController != ((mr)localObject4).a()) { if (!jdField_e_of_type_Boolean) { GlUtil.c(); GlUtil.d(); GlUtil.b(((mr)localObject4).a().getWorldTransformClient()); } localSegmentDrawer.jdField_a_of_type_Q.b(-1, 0, 0); localSegmentDrawer.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = ((mr)localObject4).a(); if (localSegmentDrawer.jdField_d_of_type_Boolean) { GlUtil.a(d.y, "selectTime", 0.0F); localSegmentDrawer.jdField_d_of_type_Boolean = false; } if ((dj.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController == localSegmentDrawer.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController) && (System.currentTimeMillis() - dj.jdField_a_of_type_Long < 8000L)) localSegmentDrawer.jdField_d_of_type_Boolean = true;  } if (localSegmentDrawer.jdField_d_of_type_Boolean) GlUtil.a(d.y, "selectTime", localSegmentDrawer.jdField_a_of_type_ZF.a() * 0.34F); l10 = System.nanoTime() - l9; localSegmentDrawer.jdField_a_of_type_Dc.l += l10; localSegmentDrawer.jdField_a_of_type_Dc.jdField_a_of_type_Long += 1L; l10 = System.nanoTime() - l9; localSegmentDrawer.jdField_a_of_type_Dc.k += l10; if (((mr)localObject4).a() != null) { mr.d(); if (!jdField_e_of_type_Boolean) { if (localSegmentDrawer.jdField_a_of_type_Q.jdField_a_of_type_Int == -1) GL11.glTranslatef(((mr)localObject4).jdField_a_of_type_Q.jdField_a_of_type_Int, ((mr)localObject4).jdField_a_of_type_Q.jdField_b_of_type_Int, ((mr)localObject4).jdField_a_of_type_Q.c); else GL11.glTranslatef(-localSegmentDrawer.jdField_a_of_type_Q.jdField_a_of_type_Int + ((mr)localObject4).jdField_a_of_type_Q.jdField_a_of_type_Int, -localSegmentDrawer.jdField_a_of_type_Q.jdField_b_of_type_Int + ((mr)localObject4).jdField_a_of_type_Q.jdField_b_of_type_Int, -localSegmentDrawer.jdField_a_of_type_Q.c + ((mr)localObject4).jdField_a_of_type_Q.c);  } else { localObject7 = ((mr)localObject4).jdField_a_of_type_Q; SegmentController localSegmentController2 = localSegmentDrawer.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController; localObject3 = localSegmentDrawer; Matrix3f localMatrix3f = localSegmentController2.getWorldTransform().basis; Vector3f localVector3f = localSegmentController2.getWorldTransform().origin; ((SegmentDrawer)localObject3).jdField_c_of_type_OrgLwjglUtilVectorMatrix4f.m00 = localMatrix3f.m00; ((SegmentDrawer)localObject3).jdField_c_of_type_OrgLwjglUtilVectorMatrix4f.m01 = localMatrix3f.m10; ((SegmentDrawer)localObject3).jdField_c_of_type_OrgLwjglUtilVectorMatrix4f.m02 = localMatrix3f.m20; ((SegmentDrawer)localObject3).jdField_c_of_type_OrgLwjglUtilVectorMatrix4f.m03 = 0.0F; ((SegmentDrawer)localObject3).jdField_c_of_type_OrgLwjglUtilVectorMatrix4f.m10 = localMatrix3f.m10; ((SegmentDrawer)localObject3).jdField_c_of_type_OrgLwjglUtilVectorMatrix4f.m11 = localMatrix3f.m11; ((SegmentDrawer)localObject3).jdField_c_of_type_OrgLwjglUtilVectorMatrix4f.m12 = localMatrix3f.m21; ((SegmentDrawer)localObject3).jdField_c_of_type_OrgLwjglUtilVectorMatrix4f.m13 = 0.0F; ((SegmentDrawer)localObject3).jdField_c_of_type_OrgLwjglUtilVectorMatrix4f.m20 = localMatrix3f.m02; ((SegmentDrawer)localObject3).jdField_c_of_type_OrgLwjglUtilVectorMatrix4f.m21 = localMatrix3f.m12; ((SegmentDrawer)localObject3).jdField_c_of_type_OrgLwjglUtilVectorMatrix4f.m22 = localMatrix3f.m22; ((SegmentDrawer)localObject3).jdField_c_of_type_OrgLwjglUtilVectorMatrix4f.m23 = 0.0F; ((SegmentDrawer)localObject3).jdField_c_of_type_OrgLwjglUtilVectorMatrix4f.m30 = (localVector3f.x + localMatrix3f.m00 * ((q)localObject7).jdField_a_of_type_Int + localMatrix3f.m01 * ((q)localObject7).jdField_b_of_type_Int + localMatrix3f.m02 * ((q)localObject7).c); ((SegmentDrawer)localObject3).jdField_c_of_type_OrgLwjglUtilVectorMatrix4f.m31 = (localVector3f.y + localMatrix3f.m10 * ((q)localObject7).jdField_a_of_type_Int + localMatrix3f.m11 * ((q)localObject7).jdField_b_of_type_Int + localMatrix3f.m12 * ((q)localObject7).c); ((SegmentDrawer)localObject3).jdField_c_of_type_OrgLwjglUtilVectorMatrix4f.m32 = (localVector3f.z + localMatrix3f.m20 * ((q)localObject7).jdField_a_of_type_Int + localMatrix3f.m21 * ((q)localObject7).jdField_b_of_type_Int + localMatrix3f.m22 * ((q)localObject7).c); ((SegmentDrawer)localObject3).jdField_c_of_type_OrgLwjglUtilVectorMatrix4f.m33 = 1.0F; Matrix4f.mul(((SegmentDrawer)localObject3).jdField_a_of_type_OrgLwjglUtilVectorMatrix4f, ((SegmentDrawer)localObject3).jdField_c_of_type_OrgLwjglUtilVectorMatrix4f, ((SegmentDrawer)localObject3).jdField_b_of_type_OrgLwjglUtilVectorMatrix4f); ((SegmentDrawer)localObject3).jdField_a_of_type_JavaNioFloatBuffer.rewind(); ((SegmentDrawer)localObject3).jdField_b_of_type_OrgLwjglUtilVectorMatrix4f.store(((SegmentDrawer)localObject3).jdField_a_of_type_JavaNioFloatBuffer); ((SegmentDrawer)localObject3).jdField_a_of_type_JavaNioFloatBuffer.rewind(); GL11.glLoadMatrix(((SegmentDrawer)localObject3).jdField_a_of_type_JavaNioFloatBuffer); } ((mr)localObject4).a().a(((mr)localObject4).jdField_a_of_type_Q); ct.i += 1; if ((xu.s.b()) && ((localObject3 = localSegmentDrawer.jdField_a_of_type_Ct.a().a().a(((mr)localObject4).a())) != null) && (((eI)localObject3).a())) { d.y.d(); d.i.a = ((eI)localObject3).a(); d.i.b(); GlUtil.a(d.i, "segPos", ((mr)localObject4).jdField_a_of_type_Q.jdField_a_of_type_Int, ((mr)localObject4).jdField_a_of_type_Q.jdField_b_of_type_Int, ((mr)localObject4).jdField_a_of_type_Q.c); GlUtil.a(d.A, "m_ShieldPercentage", ((eI)localObject3).a()); ((mr)localObject4).a().a(((mr)localObject4).jdField_a_of_type_Q); d.i.d(); d.y.b(); } localSegmentDrawer.jdField_a_of_type_Q.b(((mr)localObject4).jdField_a_of_type_Q); ((mr)localObject4).a();
/*      */               dc tmp2042_2033 = localSegmentDrawer.jdField_a_of_type_Dc; tmp2042_2033.jdField_b_of_type_Long = tmp2042_2033.jdField_b_of_type_Long; l11 = ((mr)localObject4).a().jdField_d_of_type_Int; localSegmentDrawer.jdField_a_of_type_Dc.c += l11; ((mr)localObject4).a();
/*      */               dc tmp2086_2077 = localSegmentDrawer.jdField_a_of_type_Dc; tmp2086_2077.d = tmp2086_2077.d; ((mr)localObject4).a();
/*      */               dc tmp2104_2095 = localSegmentDrawer.jdField_a_of_type_Dc; tmp2104_2095.e = tmp2104_2095.e; ((mr)localObject4).a();
/*      */               dc tmp2122_2113 = localSegmentDrawer.jdField_a_of_type_Dc; tmp2122_2113.f = tmp2122_2113.f; ((mr)localObject4).a();
/*      */               dc tmp2140_2131 = localSegmentDrawer.jdField_a_of_type_Dc; tmp2140_2131.g = tmp2140_2131.g; l11 = ((mr)localObject4).a().jdField_b_of_type_Long; localSegmentDrawer.jdField_a_of_type_Dc.o += l11; } long l11 = ((mr)localObject4).g() ? 0L : ((mr)localObject4).b(); localSegmentDrawer.jdField_a_of_type_Dc.j += l11; l11 = dI.jdField_a_of_type_Long; localSegmentDrawer.jdField_a_of_type_Dc.p += l11; dc.b(localSegmentDrawer.jdField_a_of_type_Dc, System.nanoTime() - l9);
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1155 */       j = (int)(System.currentTimeMillis() - l7);
/*      */       long l8;
/* 1157 */       if ((float)(
/* 1157 */         l8 = System.nanoTime() - l6) / 
/* 1157 */         1000000.0F > 25.0F) {
/* 1158 */         System.err.println("DRAWING TIME OF " + i + " elements: " + j + "(" + (float)l8 / 1000000.0F + "); unifroms: " + (float)this.jdField_a_of_type_Dc.f / 1000000.0F + "; pointer " + (float)this.jdField_a_of_type_Dc.c / 1000000.0F + "; upChk " + (float)this.jdField_a_of_type_Dc.n / 1000000.0F + "; frust " + (float)this.jdField_a_of_type_Dc.m / 1000000.0F + "; update " + (float)this.jdField_a_of_type_Dc.l / 1000000.0F + "; draw: " + (float)this.jdField_a_of_type_Dc.g / 1000000.0F + "; totD: " + (float)this.jdField_a_of_type_Dc.h / 1000000.0F);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1171 */     i();
/* 1172 */     System.currentTimeMillis();
/*      */ 
/* 1174 */     GlUtil.c();
/*      */ 
/* 1176 */     if ((bool) && ((!localObjectHeapPriorityQueue.isEmpty()) || (localBeamState != null))) {
/* 1177 */       this.jdField_a_of_type_Ct.a().a().g();
/*      */ 
/* 1180 */       if (localBeamState != null) {
/* 1181 */         GlUtil.d();
/* 1182 */         dm.a(localBeamState, jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 1183 */         GlUtil.c();
/*      */       }
/* 1185 */       while (!localObjectHeapPriorityQueue.isEmpty()) {
/* 1186 */         GlUtil.d();
/* 1187 */         dm.a((BeamHandler.BeamState)localObjectHeapPriorityQueue.dequeue(), jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 1188 */         GlUtil.c();
/*      */       }
/* 1190 */       this.jdField_a_of_type_Ct.a().a(); do.h();
/*      */     }
/*      */ 
/* 1193 */     System.currentTimeMillis();
/* 1194 */     if (System.currentTimeMillis() - this.jdField_a_of_type_Dc.q > 1000L) {
/* 1195 */       this.jdField_a_of_type_Dc.jdField_b_of_type_Int = this.jdField_a_of_type_Dc.jdField_a_of_type_Int;
/* 1196 */       this.jdField_a_of_type_Dc.jdField_a_of_type_Int = 0;
/* 1197 */       this.jdField_a_of_type_Dc.q = System.currentTimeMillis();
/*      */     }
/* 1199 */     xd.jdField_a_of_type_JavaUtilArrayList.add("LUR: " + this.jdField_a_of_type_Dc.jdField_b_of_type_Int);
/* 1200 */     xd.jdField_a_of_type_JavaUtilArrayList.add("RQU/RSEG/RR: " + ct.jdField_d_of_type_Int + " / " + ct.jdField_e_of_type_Int + " / " + ct.f);
/*      */ 
/* 1202 */     if (Keyboard.isKeyDown(64))
/* 1203 */       GlUtil.f();
/*      */   }
/*      */ 
/*      */   private void i()
/*      */   {
/* 1208 */     GL11.glDisableClientState(32884);
/* 1209 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = null;
/*      */ 
/* 1211 */     GL11.glDisable(3042);
/* 1212 */     GL11.glShadeModel(7425);
/* 1213 */     GL15.glBindBuffer(34962, 0);
/*      */ 
/* 1216 */     d.y.d();
/*      */   }
/*      */ 
/*      */   public final ArrayList a()
/*      */   {
/* 1430 */     return this.jdField_c_of_type_JavaUtilArrayList;
/*      */   }
/*      */ 
/*      */   public final void e() {
/* 1434 */     synchronized (this.jdField_a_of_type_JavaUtilArrayList)
/*      */     {
/* 1438 */       int i = 0;
/* 1439 */       while (!this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) {
/* 1440 */         mr localmr1 = (mr)this.jdField_a_of_type_JavaUtilArrayList.remove(0);
/* 1441 */         if ((!f) && (!localmr1.c())) throw new AssertionError(localmr1.jdField_a_of_type_Q);
/* 1442 */         if ((!f) && (localmr1.b() == null)) throw new AssertionError(localmr1.jdField_a_of_type_Q);
/* 1443 */         synchronized (localmr1.jdField_a_of_type_JavaLangObject)
/*      */         {
/* 1445 */           if (!localmr1.b().a()) {
/* 1446 */             localmr1.b().e();
/*      */           }
/*      */ 
/* 1449 */           int j = i; mr localmr2 = localmr1; SegmentDrawer localSegmentDrawer = this; if ((!f) && (localmr2.b() == null)) throw new AssertionError(); System.currentTimeMillis(); localmr2.b().a(localmr2.b(), j); localSegmentDrawer.jdField_a_of_type_Ct.a().a().a(localmr2); System.currentTimeMillis();
/*      */ 
/* 1451 */           localmr1.c();
/*      */ 
/* 1453 */           localmr1.a();
/*      */ 
/* 1456 */           localmr1.b(true);
/* 1457 */           localmr1.d(false);
/*      */         }
/* 1459 */         localObject1++;
/*      */       }
/* 1461 */       GL15.glBindBuffer(34962, 0);
/* 1462 */       return;
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void c()
/*      */   {
/* 1539 */     f();
/*      */ 
/* 1542 */     this.jdField_a_of_type_De.start();
/* 1543 */     this.jdField_a_of_type_Df.start();
/*      */ 
/* 1546 */     this.jdField_b_of_type_Boolean = false;
/*      */   }
/*      */ 
/*      */   public final void a(xq paramxq)
/*      */   {
/* 1557 */     dH.a();
/* 1558 */     xq localxq = paramxq;
/*      */     iO localiO;
/* 1558 */     (localiO = dI.jdField_a_of_type_IO).jdField_a_of_type_Float += localxq.a(); if (localiO.jdField_a_of_type_Float > 0.5F) { localiO.jdField_a_of_type_Float -= 0.5F; localiO.jdField_a_of_type_Int = ((localiO.jdField_a_of_type_Int + 1) % 4); }
/* 1559 */     this.jdField_a_of_type_De.a();
/* 1560 */     this.jdField_a_of_type_ZF.a(paramxq);
/*      */   }
/*      */ 
/*      */   public final void f()
/*      */   {
/* 1565 */     synchronized (this.jdField_b_of_type_JavaUtilArrayList)
/*      */     {
/*      */       Object localObject2;
/* 1566 */       for (int i = 0; i < this.jdField_b_of_type_JavaUtilArrayList.size(); i++)
/*      */       {
/* 1568 */         if ((((Boolean)(
/* 1568 */           localObject2 = (SegmentController)this.jdField_b_of_type_JavaUtilArrayList.get(i))
/* 1568 */           .getNetworkObject().markedDeleted.get()).booleanValue()) || (!this.jdField_a_of_type_Ct.a().containsKey(((SegmentController)localObject2).getId())))
/*      */         {
/* 1570 */           this.jdField_b_of_type_JavaUtilArrayList.remove(i);
/* 1571 */           i--;
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1577 */       for (Iterator localIterator = this.jdField_a_of_type_Ct.a().values().iterator(); localIterator.hasNext(); )
/*      */       {
/* 1579 */         if (((
/* 1579 */           localObject2 = (mF)localIterator.next()) instanceof SegmentController))
/*      */         {
/* 1581 */           localObject2 = (SegmentController)localObject2;
/*      */ 
/* 1583 */           if ((!this.jdField_b_of_type_JavaUtilArrayList.contains(localObject2)) && 
/* 1583 */             (localObject2 != null)) {
/* 1584 */             this.jdField_b_of_type_JavaUtilArrayList.add(localObject2);
/*      */           }
/*      */         }
/*      */       }
/* 1588 */       return;
/*      */     }
/*      */   }
/*      */ 
/*      */   static
/*      */   {
/*  730 */     jdField_a_of_type_Int = 0;
/*      */ 
/*  735 */     jdField_e_of_type_Boolean = false;
/*      */ 
/*  812 */     (
/*  992 */       SegmentDrawer.jdField_a_of_type_ComBulletphysicsLinearmathTransform = new Transform())
/*  992 */       .setIdentity();
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.client.view.SegmentDrawer
 * JD-Core Version:    0.6.2
 */