/*      */ package org.schema.schine.graphicsengine.meshimporter;
/*      */ 
/*      */ import Aw;
/*      */ import d;
/*      */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.PrintStream;
/*      */ import java.io.StringReader;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.FloatBuffer;
/*      */ import java.nio.IntBuffer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Vector;
/*      */ import javax.vecmath.AxisAngle4f;
/*      */ import javax.vecmath.Quat4f;
/*      */ import javax.vecmath.Vector3f;
/*      */ import javax.vecmath.Vector4f;
/*      */ import javax.xml.parsers.DocumentBuilder;
/*      */ import javax.xml.parsers.DocumentBuilderFactory;
/*      */ import javax.xml.parsers.ParserConfigurationException;
/*      */ import javax.xml.parsers.SAXParser;
/*      */ import javax.xml.parsers.SAXParserFactory;
/*      */ import k;
/*      */ import org.schema.schine.graphicsengine.core.GlUtil;
/*      */ import org.schema.schine.graphicsengine.forms.AnimationNotFoundException;
/*      */ import org.schema.schine.graphicsengine.forms.Mesh;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.NodeList;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.InputSource;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.helpers.DefaultHandler;
/*      */ import wL;
/*      */ import wP;
/*      */ import wQ;
/*      */ import xM;
/*      */ import xN;
/*      */ import xO;
/*      */ import xS;
/*      */ import xU;
/*      */ import xW;
/*      */ import ya;
/*      */ import ye;
/*      */ import yf;
/*      */ import yi;
/*      */ import yj;
/*      */ import zZ;
/*      */ import zh;
/*      */ import zi;
/*      */ import zx;
/*      */ 
/*      */ public class XMLOgreParser extends DefaultHandler
/*      */ {
/*  137 */   private HashMap jdField_a_of_type_JavaUtilHashMap = new HashMap();
/*      */   private zi jdField_a_of_type_Zi;
/*      */   private zi jdField_b_of_type_Zi;
/*      */   private String jdField_a_of_type_JavaLangString;
/*      */   private String jdField_b_of_type_JavaLangString;
/*      */   private zx[] jdField_a_of_type_ArrayOfZx;
/*      */ 
/*      */   public static void main(String[] paramArrayOfString)
/*      */   {
/*  129 */     paramArrayOfString = new XMLOgreParser();
/*  130 */     String str = "ogretest/temple-machine.scene";
/*      */ 
/*  132 */     paramArrayOfString.a(str, "");
/*      */   }
/*      */ 
/*      */   public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
/*      */   {
/*  174 */     if ((
/*  174 */       paramArrayOfChar = new String(paramArrayOfChar, paramInt1, paramInt2))
/*  174 */       .trim().length() > 0)
/*      */     {
/*  176 */       this.jdField_b_of_type_Zi.jdField_b_of_type_JavaLangString = paramArrayOfChar;
/*      */     }
/*      */   }
/*      */ 
/*      */   public void endDocument()
/*      */   {
/*      */   }
/*      */ 
/*      */   public void endElement(String paramString1, String paramString2, String paramString3)
/*      */   {
/*  200 */     if (("".equals(paramString2) ? paramString3 : paramString2)
/*  200 */       .equals(this.jdField_b_of_type_Zi.jdField_a_of_type_JavaLangString)) {
/*  201 */       this.jdField_b_of_type_Zi.jdField_a_of_type_Boolean = true;
/*  202 */       if (this.jdField_b_of_type_Zi.jdField_a_of_type_Zi != null)
/*  203 */         this.jdField_b_of_type_Zi = this.jdField_b_of_type_Zi.jdField_a_of_type_Zi;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static wL a(zi paramzi)
/*      */   {
/*  233 */     wL localwL = new wL();
/*  234 */     int i = paramzi.jdField_a_of_type_JavaUtilLinkedList.size();
/*      */ 
/*  236 */     for (Iterator localIterator = paramzi.jdField_a_of_type_JavaUtilVector.iterator(); localIterator.hasNext(); )
/*      */     {
/*      */       Aw localAw;
/*  237 */       if ((
/*  237 */         localAw = (Aw)localIterator.next()).jdField_a_of_type_JavaLangString
/*  237 */         .equals("name")) {
/*  238 */         localwL.jdField_a_of_type_JavaLangString = localAw.jdField_b_of_type_JavaLangString;
/*  239 */         System.err.println("... Animation name " + localwL.jdField_a_of_type_JavaLangString);
/*      */       }
/*  241 */       if (localAw.jdField_a_of_type_JavaLangString.equals("loop")) {
/*  242 */         Boolean.parseBoolean(localAw.jdField_b_of_type_JavaLangString);
/*      */       }
/*      */ 
/*  246 */       localAw.jdField_a_of_type_JavaLangString.equals("interpolationMode");
/*      */ 
/*  249 */       localAw.jdField_a_of_type_JavaLangString.equals("rotationInterpolationMode");
/*      */ 
/*  252 */       localAw.jdField_a_of_type_JavaLangString.equals("length");
/*      */     }
/*      */ 
/*  256 */     a(paramzi, i);
/*      */ 
/*  260 */     return localwL;
/*      */   }
/*      */ 
/*      */   private static Vector a(zi paramzi)
/*      */   {
/*  270 */     Vector localVector = new Vector();
/*  271 */     for (paramzi = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext(); )
/*      */     {
/*      */       zi localzi;
/*  272 */       if ((
/*  272 */         localzi = (zi)paramzi.next()).jdField_a_of_type_JavaLangString
/*  272 */         .equals("animation")) {
/*  273 */         localVector.add(a(localzi));
/*      */       }
/*      */     }
/*  276 */     return localVector;
/*      */   }
/*      */ 
/*      */   private static wL b(zi paramzi)
/*      */   {
/*  286 */     Object localObject1 = null;
/*      */ 
/*  288 */     float f = 0.0F;
/*      */ 
/*  291 */     for (Iterator localIterator1 = paramzi.jdField_a_of_type_JavaUtilVector.iterator(); localIterator1.hasNext(); ) {
/*  292 */       if ((
/*  292 */         localObject3 = (Aw)localIterator1.next()).jdField_a_of_type_JavaLangString
/*  292 */         .equals("name")) {
/*  293 */         localObject1 = ((Aw)localObject3).jdField_b_of_type_JavaLangString;
/*      */       }
/*      */ 
/*  296 */       if (((Aw)localObject3).jdField_a_of_type_JavaLangString.equals("loop")) {
/*  297 */         Boolean.parseBoolean(((Aw)localObject3).jdField_b_of_type_JavaLangString);
/*      */       }
/*      */ 
/*  301 */       ((Aw)localObject3).jdField_a_of_type_JavaLangString.equals("interpolationMode");
/*      */ 
/*  304 */       ((Aw)localObject3).jdField_a_of_type_JavaLangString.equals("rotationInterpolationMode");
/*      */ 
/*  307 */       if (((Aw)localObject3).jdField_a_of_type_JavaLangString.equals("length"))
/*  308 */         f = Float.parseFloat(((Aw)localObject3).jdField_b_of_type_JavaLangString);
/*      */     }
/*      */     Object localObject3;
/*      */     wL localwL;
/*  314 */     (
/*  315 */       localwL = new wL()).jdField_a_of_type_JavaLangString = 
/*  315 */       ((String)localObject1);
/*  316 */     localwL.jdField_a_of_type_Float = f;
/*      */ 
/*  318 */     for (localIterator1 = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); localIterator1.hasNext(); )
/*      */     {
/*  320 */       if ((
/*  320 */         localObject3 = (zi)localIterator1.next()).jdField_a_of_type_JavaLangString
/*  320 */         .equals("tracks"))
/*      */       {
/*  322 */         for (paramzi = ((zi)localObject3).jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext(); ) {
/*  323 */           if ((
/*  323 */             localObject1 = (zi)paramzi.next()).jdField_a_of_type_JavaLangString
/*  323 */             .equals("track")) {
/*  324 */             localObject3 = localObject1; Object localObject2 = localwL; localObject1 = null; for (Iterator localIterator2 = ((zi)localObject3).jdField_a_of_type_JavaUtilVector.iterator(); localIterator2.hasNext(); ) if ((localObject4 = (Aw)localIterator2.next()).jdField_a_of_type_JavaLangString.equals("bone")) localObject1 = ((Aw)localObject4).jdField_b_of_type_JavaLangString;
/*  324 */             Object localObject4;
/*  324 */             for (localIterator2 = ((zi)localObject3).jdField_a_of_type_JavaUtilLinkedList.iterator(); localIterator2.hasNext(); localObject2.jdField_a_of_type_JavaUtilHashMap.put(((wQ)localObject3).a(), localObject3)) { localObject4 = (zi)localIterator2.next(); if ((!jdField_a_of_type_Boolean) && (localObject1 == null)) throw new AssertionError(); localObject3 = new wQ((String)localObject1); a((zi)localObject4, (wP)localObject3);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  331 */     return localwL;
/*      */   }
/*      */ 
/*      */   private static List a(zi paramzi)
/*      */   {
/*  341 */     ArrayList localArrayList = new ArrayList();
/*  342 */     for (paramzi = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext(); )
/*      */     {
/*      */       zi localzi;
/*  343 */       if ((
/*  343 */         localzi = (zi)paramzi.next()).jdField_a_of_type_JavaLangString
/*  343 */         .equals("animation")) {
/*  344 */         localArrayList.add(b(localzi));
/*      */       }
/*      */     }
/*  347 */     return localArrayList;
/*      */   }
/*      */ 
/*      */   private static void a(Mesh paramMesh, zi paramzi)
/*      */   {
/*  368 */     yi[] arrayOfyi = new yi[paramzi.jdField_a_of_type_JavaUtilLinkedList.size()];
/*      */ 
/*  370 */     int i = 0;
/*  371 */     for (paramzi = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext(); )
/*      */     {
/*      */       Object localObject;
/*  372 */       if ((
/*  372 */         localObject = (zi)paramzi.next()).jdField_a_of_type_JavaLangString
/*  372 */         .equals("vertexboneassignment"))
/*      */       {
/*  374 */         int j = -1;
/*  375 */         int k = -1;
/*  376 */         float f = -1.0F;
/*  377 */         for (localObject = ((zi)localObject).jdField_a_of_type_JavaUtilVector.iterator(); ((Iterator)localObject).hasNext(); )
/*      */         {
/*      */           Aw localAw;
/*  378 */           String str = (
/*  378 */             localAw = (Aw)((Iterator)localObject).next()).jdField_b_of_type_JavaLangString;
/*      */ 
/*  379 */           if (localAw.jdField_a_of_type_JavaLangString.equals("vertexindex")) {
/*  380 */             j = Integer.parseInt(str);
/*      */           }
/*  382 */           if (localAw.jdField_a_of_type_JavaLangString.equals("boneindex")) {
/*  383 */             k = Integer.parseInt(str);
/*      */           }
/*  385 */           if (localAw.jdField_a_of_type_JavaLangString.equals("weight")) {
/*  386 */             f = Float.parseFloat(str);
/*      */           }
/*      */         }
/*  389 */         localObject = new yi(j, k, f);
/*      */ 
/*  391 */         arrayOfyi[i] = localObject;
/*  392 */         paramMesh.a(arrayOfyi);
/*      */       }
/*  394 */       i++;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static void a(ye paramye, zi paramzi)
/*      */   {
/*  405 */     for (paramzi = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext(); )
/*  406 */       if ((
/*  406 */         localObject1 = (zi)paramzi.next()).jdField_a_of_type_JavaLangString
/*  406 */         .equals("boneparent")) {
/*  407 */         localObject2 = null;
/*  408 */         String str = null;
/*  409 */         for (localObject1 = ((zi)localObject1).jdField_a_of_type_JavaUtilVector.iterator(); ((Iterator)localObject1).hasNext(); ) {
/*  410 */           if ((
/*  410 */             localObject3 = (Aw)((Iterator)localObject1).next()).jdField_a_of_type_JavaLangString
/*  410 */             .equals("bone")) {
/*  411 */             localObject2 = ((Aw)localObject3).jdField_b_of_type_JavaLangString;
/*      */           }
/*  413 */           if (((Aw)localObject3).jdField_a_of_type_JavaLangString.equals("parent"))
/*  414 */             str = ((Aw)localObject3).jdField_b_of_type_JavaLangString;
/*      */         }
/*  417 */         Object localObject3;
/*  417 */         if ((!jdField_a_of_type_Boolean) && (localObject2 == null)) throw new AssertionError();
/*      */ 
/*  419 */         localObject1 = paramye.a().values().iterator();
/*      */         while (true) { if (!((Iterator)localObject1).hasNext()) break label247;
/*  420 */           if ((
/*  420 */             localObject3 = (xN)((Iterator)localObject1).next()).jdField_a_of_type_JavaLangString
/*  420 */             .equals(localObject2)) {
/*  421 */             localObject1 = paramye.a().values().iterator();
/*      */             while (true) { if (!((Iterator)localObject1).hasNext()) break label241;
/*  422 */               if ((
/*  422 */                 localObject2 = (xN)((Iterator)localObject1).next()).jdField_a_of_type_JavaLangString
/*  422 */                 .equals(str)) {
/*  423 */                 ((xN)localObject3).a((xN)localObject2);
/*  424 */                 ((xN)localObject2).a().add(localObject3);
/*  425 */                 break;
/*      */               }
/*      */             }
/*  428 */             break;
/*      */           }
/*      */         }
/*      */       }
/*      */     Object localObject2;
/*  434 */     label241: label247: paramzi = 0;
/*  435 */     for (Object localObject1 = paramye.a().values().iterator(); ((Iterator)localObject1).hasNext(); )
/*  436 */       if ((
/*  436 */         localObject2 = (xN)((Iterator)localObject1).next())
/*  436 */         .a() == null)
/*  437 */         if (paramzi == 0) {
/*  438 */           paramzi = 1;
/*  439 */           paramye.a((xN)localObject2);
/*      */         } else {
/*  441 */           System.err.println("WARNING: more than one skeleton root bone found " + ((xN)localObject2).jdField_a_of_type_JavaLangString);
/*      */         }
/*      */   }
/*      */ 
/*      */   private static void b(ye paramye, zi paramzi)
/*      */   {
/*  456 */     for (paramzi = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext(); )
/*      */     {
/*      */       Object localObject1;
/*  457 */       if ((
/*  457 */         localObject1 = (zi)paramzi.next()).jdField_a_of_type_JavaLangString
/*  457 */         .equals("bone")) {
/*  458 */         Object localObject2 = null;
/*  459 */         int i = 0;
/*      */ 
/*  461 */         for (Object localObject3 = ((zi)localObject1).jdField_a_of_type_JavaUtilVector.iterator(); ((Iterator)localObject3).hasNext(); ) {
/*  462 */           if ((
/*  462 */             localObject4 = (Aw)((Iterator)localObject3).next()).jdField_a_of_type_JavaLangString
/*  462 */             .equals("name")) {
/*  463 */             localObject2 = ((Aw)localObject4).jdField_b_of_type_JavaLangString;
/*      */           }
/*  465 */           if (((Aw)localObject4).jdField_a_of_type_JavaLangString.equals("id")) {
/*  466 */             i = Integer.parseInt(((Aw)localObject4).jdField_b_of_type_JavaLangString);
/*      */           }
/*      */         }
/*      */ 
/*  470 */         localObject3 = new xN(i, (String)localObject2);
/*  470 */         xN.f();
/*  471 */         Object localObject4 = null;
/*  472 */         localObject2 = null;
/*      */ 
/*  474 */         for (localObject1 = ((zi)localObject1).jdField_a_of_type_JavaUtilLinkedList.iterator(); ((Iterator)localObject1).hasNext(); )
/*      */         {
/*      */           zi localzi;
/*  476 */           if ((
/*  476 */             localzi = (zi)((Iterator)localObject1).next()).jdField_a_of_type_JavaLangString
/*  476 */             .equals("position"))
/*      */           {
/*  478 */             localObject4 = a(localzi);
/*      */           }
/*      */           Iterator localIterator;
/*      */           Object localObject5;
/*      */           Object localObject6;
/*  482 */           if (localzi.jdField_a_of_type_JavaLangString.equals("rotation")) {
/*  483 */             localObject2 = new AxisAngle4f();
/*  484 */             for (localIterator = localzi.jdField_a_of_type_JavaUtilVector.iterator(); localIterator.hasNext(); ) {
/*  485 */               localObject6 = (
/*  485 */                 localObject5 = (Aw)localIterator.next()).jdField_b_of_type_JavaLangString;
/*      */ 
/*  486 */               if (((Aw)localObject5).jdField_a_of_type_JavaLangString.equals("angle")) {
/*  487 */                 ((AxisAngle4f)localObject2).angle = Float.parseFloat((String)localObject6);
/*      */               }
/*      */             }
/*  490 */             for (localIterator = localzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); localIterator.hasNext(); ) {
/*  491 */               if ((
/*  491 */                 localObject5 = (zi)localIterator.next()).jdField_a_of_type_JavaLangString
/*  491 */                 .equals("axis")) {
/*  492 */                 (
/*  493 */                   localObject6 = a((zi)localObject5))
/*  493 */                   .normalize();
/*  494 */                 ((AxisAngle4f)localObject2).x = ((Vector3f)localObject6).x;
/*  495 */                 ((AxisAngle4f)localObject2).y = ((Vector3f)localObject6).y;
/*  496 */                 ((AxisAngle4f)localObject2).z = ((Vector3f)localObject6).z;
/*      */               }
/*      */             }
/*      */           }
/*  500 */           if (localzi.jdField_a_of_type_JavaLangString.equals("scale")) {
/*  501 */             a(localzi);
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*  506 */         ((xN)localObject3).b((Vector3f)localObject4, d.a(((AxisAngle4f)localObject2).angle, new Vector3f(((AxisAngle4f)localObject2).x, ((AxisAngle4f)localObject2).y, ((AxisAngle4f)localObject2).z), new Quat4f()));
/*  507 */         if ((!jdField_a_of_type_Boolean) && (((xN)localObject3).jdField_a_of_type_JavaLangString == null)) throw new AssertionError();
/*  508 */         if ((!jdField_a_of_type_Boolean) && (((xN)localObject3).jdField_a_of_type_Int < 0)) throw new AssertionError();
/*  509 */         paramye.a().put(((xN)localObject3).jdField_a_of_type_Int, localObject3);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private xS a(zi paramzi)
/*      */   {
/*  523 */     Object localObject1 = null;
/*  524 */     Object localObject2 = "default";
/*  525 */     for (Iterator localIterator = paramzi.jdField_a_of_type_JavaUtilVector.iterator(); localIterator.hasNext(); ) {
/*  526 */       localObject4 = (
/*  526 */         localObject3 = (Aw)localIterator.next()).jdField_b_of_type_JavaLangString;
/*      */ 
/*  527 */       if (((Aw)localObject3).jdField_a_of_type_JavaLangString.equals("description"))
/*      */       {
/*  529 */         localObject2 = localObject4;
/*      */       }
/*      */ 
/*  532 */       ((Aw)localObject3).jdField_a_of_type_JavaLangString.equals("id");
/*      */ 
/*  536 */       ((Aw)localObject3).jdField_a_of_type_JavaLangString.equals("castShadows");
/*      */ 
/*  540 */       ((Aw)localObject3).jdField_a_of_type_JavaLangString.equals("receiveShadows");
/*      */ 
/*  544 */       if (((Aw)localObject3).jdField_a_of_type_JavaLangString.equals("meshFile"))
/*      */       {
/*  546 */         localObject3 = this.jdField_a_of_type_JavaLangString; localObject5 = new Vector(); (localObject6 = new XMLOgreParser()).a((String)localObject3 + (String)localObject4 + ".xml"); localObject4 = null; for (localObject7 = ((XMLOgreParser)localObject6).jdField_a_of_type_Zi.jdField_a_of_type_JavaUtilLinkedList.iterator(); ((Iterator)localObject7).hasNext(); ) { if ((localObject6 = (zi)((Iterator)localObject7).next()).jdField_a_of_type_JavaLangString.equals("submeshes")) ((Vector)localObject5).addAll(b((zi)localObject6)); if (((zi)localObject6).jdField_a_of_type_JavaLangString.equals("submeshnames")) a((zi)localObject6, (Vector)localObject5); ((zi)localObject6).jdField_a_of_type_JavaLangString.equals("poses"); if (((zi)localObject6).jdField_a_of_type_JavaLangString.equals("skeletonlink")) localObject4 = a((zi)localObject6);  } localObject7 = (xS)((Vector)localObject5).remove(0); for (Object localObject6 = ((Vector)localObject5).iterator(); ((Iterator)localObject6).hasNext(); ((xS)localObject7).a((xM)localObject5)) localObject5 = (xS)((Iterator)localObject6).next(); if (localObject4 != null) a((Mesh)localObject7, (String)localObject3, (String)localObject4); localObject1 = localObject7;
/*      */       }
/*      */     }
/*      */     Object localObject4;
/*      */     Object localObject5;
/*      */     Object localObject7;
/*  551 */     localObject1.c((String)localObject2);
/*  552 */     localIterator = null;
/*  553 */     for (Object localObject3 = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); ((Iterator)localObject3).hasNext(); )
/*  554 */       if ((
/*  554 */         localObject4 = (zi)((Iterator)localObject3).next()).jdField_a_of_type_JavaLangString
/*  554 */         .equals("subentities"))
/*      */       {
/*  556 */         for (paramzi = ((zi)localObject4).jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext(); ) {
/*  557 */           for (localObject5 = ((zi)paramzi.next()).jdField_a_of_type_JavaUtilVector
/*  557 */             .iterator(); ((Iterator)localObject5).hasNext(); ) { (
/*  558 */               localObject2 = (Aw)((Iterator)localObject5).next()).jdField_a_of_type_JavaLangString
/*  558 */               .equals("index");
/*      */ 
/*  561 */             if (((Aw)localObject2).jdField_a_of_type_JavaLangString.equals("materialName")) {
/*  562 */               if ((!this.jdField_a_of_type_JavaUtilHashMap.containsKey(((Aw)localObject2).jdField_b_of_type_JavaLangString)) && 
/*  563 */                 (this.jdField_a_of_type_ArrayOfZx == null))
/*      */               {
/*  568 */                 this.jdField_a_of_type_ArrayOfZx = a(this.jdField_a_of_type_JavaLangString + this.jdField_b_of_type_JavaLangString + ".material");
/*      */ 
/*  570 */                 for (localObject7 : this.jdField_a_of_type_ArrayOfZx)
/*      */                 {
/*  573 */                   this.jdField_a_of_type_JavaUtilHashMap.put(((zx)localObject7).a(), localObject7);
/*      */                 }
/*      */               }
/*  576 */               localzx = (zx)this.jdField_a_of_type_JavaUtilHashMap.get(((Aw)localObject2).jdField_b_of_type_JavaLangString);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*  581 */       else if ((((zi)localObject4).jdField_a_of_type_JavaLangString.equals("userData")) && (((zi)localObject4).jdField_b_of_type_JavaLangString != null))
/*      */       {
/*      */         try
/*      */         {
/*  587 */           localObject2 = DocumentBuilderFactory.newInstance()
/*  587 */             .newDocumentBuilder();
/*  588 */           (
/*  589 */             localObject5 = new InputSource())
/*  589 */             .setCharacterStream(new StringReader(((zi)localObject4).jdField_b_of_type_JavaLangString));
/*  590 */           localObject2 = ((DocumentBuilder)localObject2).parse((InputSource)localObject5);
/*  591 */           localObject1.a((Document)localObject2);
/*      */ 
/*  593 */           if ((
/*  593 */             localObject4 = ((Document)localObject2).getElementsByTagName("Mass").item(0)) != null)
/*      */           {
/*  594 */             float f = Float.parseFloat(((Node)localObject4).getTextContent());
/*  595 */             localObject1.a(f);
/*      */           }
/*      */         } catch (SAXException paramzi) {
/*  598 */           System.err.println("Exception while parsing userdata from " + localObject1.b());
/*  599 */           paramzi.printStackTrace();
/*      */         }
/*      */         catch (IOException localIOException)
/*      */         {
/*  604 */           localIOException.printStackTrace();
/*      */         }
/*      */         catch (ParserConfigurationException localParserConfigurationException)
/*      */         {
/*  604 */           localParserConfigurationException.printStackTrace();
/*      */         }
/*      */       }
/*      */     zx localzx;
/*  608 */     localObject1.a(localzx);
/*      */ 
/*  610 */     return localObject1;
/*      */   }
/*      */ 
/*      */   private static void a(zi paramzi)
/*      */   {
/*  622 */     for (paramzi = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext(); paramzi.next());
/*      */   }
/*      */ 
/*      */   @Deprecated
/*      */   private static zh a(zi paramzi, int paramInt)
/*      */   {
/*  636 */     paramInt = new zh(paramInt);
/*  637 */     int i = 0;
/*  638 */     for (paramzi = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext(); )
/*      */     {
/*      */       Object localObject;
/*  640 */       if ((
/*  640 */         localObject = (zi)paramzi.next()).jdField_a_of_type_JavaLangString
/*  640 */         .equals("keyframe"))
/*      */       {
/*  642 */         for (localObject = ((zi)localObject).jdField_a_of_type_JavaUtilLinkedList.iterator(); ((Iterator)localObject).hasNext(); )
/*      */         {
/*      */           zi localzi;
/*  645 */           if ((
/*  645 */             localzi = (zi)((Iterator)localObject).next()).jdField_a_of_type_JavaLangString
/*  645 */             .equals("translation"))
/*      */           {
/*  647 */             paramInt.b[i] = a(localzi);
/*      */           }
/*  649 */           if (localzi.jdField_a_of_type_JavaLangString.equals("rotation"))
/*      */           {
/*  651 */             paramInt.jdField_a_of_type_ArrayOfJavaxVecmathVector4f[i] = a(localzi);
/*      */           }
/*      */ 
/*  654 */           if (localzi.jdField_a_of_type_JavaLangString.equals("scale"))
/*      */           {
/*  656 */             paramInt.jdField_a_of_type_ArrayOfJavaxVecmathVector3f[i] = a(localzi);
/*      */           }
/*      */         }
/*  659 */         i++;
/*      */       }
/*      */     }
/*  662 */     return paramInt;
/*      */   }
/*      */ 
/*      */   private static zx[] a(String paramString)
/*      */   {
/*  674 */     paramString = new BufferedReader(new InputStreamReader(zZ.a.a(paramString)));
/*      */ 
/*  680 */     StringBuffer localStringBuffer = new StringBuffer();
/*      */     try {
/*  682 */       while (paramString.ready())
/*  683 */         localStringBuffer.append(paramString.readLine() + "\n");
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/*  687 */       localIOException.printStackTrace();
/*      */     }
/*      */     String[] arrayOfString1;
/*  689 */     paramString = new zx[(
/*  689 */       arrayOfString1 = localStringBuffer.toString().trim().split("material")).length];
/*      */ 
/*  690 */     for (int i = 0; i < arrayOfString1.length; i++)
/*      */     {
/*      */       String str1;
/*      */       String[] arrayOfString2;
/*  694 */       if ((
/*  694 */         arrayOfString2 = (
/*  693 */         str1 = arrayOfString1[i])
/*  693 */         .split("\n")).length == 0)
/*      */       {
/*  695 */         System.err.println("Material String: line length 0: " + str1);
/*      */       }
/*  697 */       paramString[i] = new zx();
/*  698 */       paramString[i].a(arrayOfString2[0].trim());
/*      */ 
/*  701 */       for (int j = 0; j < arrayOfString2.length; j++)
/*      */       {
/*      */         String str2;
/*      */         Object localObject;
/*  704 */         if ((
/*  704 */           str2 = arrayOfString2[j])
/*  704 */           .contains("ambient")) {
/*  705 */           localObject = a(str2);
/*  706 */           paramString[i].a((float[])localObject);
/*      */         }
/*      */ 
/*  709 */         if (str2.contains("diffuse")) {
/*  710 */           localObject = a(str2);
/*  711 */           paramString[i].b((float[])localObject);
/*      */         }
/*  713 */         if (str2.contains("specular")) {
/*  714 */           localObject = a(str2);
/*  715 */           paramString[i].c((float[])localObject);
/*      */         }
/*  717 */         if (str2.contains("emissive")) {
/*  718 */           a(str2);
/*      */         }
/*      */ 
/*  721 */         if (str2.contains("texture ")) {
/*  722 */           localObject = str2.split(" ");
/*  723 */           paramString[i].a(true);
/*      */ 
/*  725 */           paramString[i].b(localObject[1].trim());
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  732 */     return paramString;
/*      */   }
/*      */ 
/*      */   private static float[] a(String paramString)
/*      */   {
/*  741 */     paramString = paramString.split(" ");
/*      */     float[] arrayOfFloat;
/*  742 */     (
/*  743 */       arrayOfFloat = new float[4])[
/*  743 */       0] = Float.parseFloat(paramString[1]);
/*  744 */     arrayOfFloat[1] = Float.parseFloat(paramString[2]);
/*  745 */     arrayOfFloat[2] = Float.parseFloat(paramString[3]);
/*  746 */     if (paramString.length > 4)
/*  747 */       arrayOfFloat[3] = Float.parseFloat(paramString[4]);
/*      */     else {
/*  749 */       arrayOfFloat[3] = 1.0F;
/*      */     }
/*  751 */     return arrayOfFloat;
/*      */   }
/*      */ 
/*      */   private void a(zi paramzi, xM paramxM)
/*      */   {
/*  812 */     String str = "default";
/*  813 */     int i = 1;
/*  814 */     for (Object localObject1 = paramzi.jdField_a_of_type_JavaUtilVector.iterator(); ((Iterator)localObject1).hasNext(); ) {
/*  815 */       if ((
/*  815 */         localObject2 = (Aw)((Iterator)localObject1).next()).jdField_a_of_type_JavaLangString
/*  815 */         .equals("description"))
/*      */       {
/*  817 */         str = ((Aw)localObject2).jdField_b_of_type_JavaLangString;
/*      */       }
/*      */ 
/*  820 */       if (((Aw)localObject2).jdField_a_of_type_JavaLangString.equals("visibility")) {
/*  821 */         if (((Aw)localObject2).jdField_b_of_type_JavaLangString.equals("visible")) {
/*  822 */           i = 1;
/*      */         }
/*  824 */         if (((Aw)localObject2).jdField_b_of_type_JavaLangString.equals("hidden"))
/*      */         {
/*  826 */           i = 2;
/*      */         }
/*  828 */         if (((Aw)localObject2).jdField_b_of_type_JavaLangString.equals("tree visible")) {
/*  829 */           i = 4;
/*      */         }
/*  831 */         if (((Aw)localObject2).jdField_b_of_type_JavaLangString.equals("tree hidden")) {
/*  832 */           i = 8;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  837 */     localObject1 = null;
/*  838 */     Object localObject2 = new Vector3f();
/*  839 */     Vector4f localVector4f = new Vector4f();
/*  840 */     Vector3f localVector3f = new Vector3f();
/*  841 */     Vector localVector = null;
/*  842 */     int j = 0;
/*  843 */     for (Iterator localIterator = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); localIterator.hasNext(); )
/*      */     {
/*  845 */       if ((
/*  845 */         localzi = (zi)localIterator.next()).jdField_a_of_type_JavaLangString
/*  845 */         .equals("position"))
/*      */       {
/*  847 */         localObject2 = a(localzi);
/*      */       }
/*  849 */       if (localzi.jdField_a_of_type_JavaLangString.equals("rotation"))
/*      */       {
/*  851 */         localVector4f = a(localzi);
/*      */       }
/*  853 */       if (localzi.jdField_a_of_type_JavaLangString.equals("scale"))
/*      */       {
/*  855 */         localVector3f = a(localzi);
/*      */       }
/*  857 */       if (localzi.jdField_a_of_type_JavaLangString.equals("animations"))
/*      */       {
/*  859 */         localVector = a(localzi);
/*      */       }
/*  861 */       if (localzi.jdField_a_of_type_JavaLangString.equals("entity"))
/*      */       {
/*  863 */         j = 1;
/*  864 */         (
/*  865 */           localObject1 = a(localzi))
/*  865 */           .g(i);
/*      */       }
/*      */     }
/*      */     zi localzi;
/*  876 */     if (j == 0)
/*      */     {
/*  878 */       (
/*  879 */         localObject1 = new ya())
/*  879 */         .c(str);
/*  880 */       ((xM)localObject1).g(i);
/*      */     }
/*      */ 
/*  883 */     for (localIterator = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); localIterator.hasNext(); ) {
/*  884 */       if ((
/*  884 */         localzi = (zi)localIterator.next()).jdField_a_of_type_JavaLangString
/*  884 */         .equals("node")) {
/*  885 */         a(localzi, (xM)localObject1);
/*      */       }
/*      */     }
/*      */ 
/*  889 */     paramxM.a((xM)localObject1);
/*  890 */     if (localVector != null) {
/*  891 */       ((xM)localObject1).a(localVector);
/*      */       try {
/*  893 */         ((xM)localObject1).b(((xM)localObject1).b());
/*      */       }
/*      */       catch (AnimationNotFoundException localAnimationNotFoundException)
/*      */       {
/*  897 */         localAnimationNotFoundException.printStackTrace();
/*      */       }
/*      */ 
/*  898 */       xM.p();
/*      */     }
/*  900 */     ((xM)localObject1).b((Vector3f)localObject2);
/*  901 */     ((xM)localObject1).b(localVector4f);
/*  902 */     ((xM)localObject1).a(localVector4f);
/*  903 */     ((xM)localObject1).e().set(localVector3f);
/*  904 */     ((xM)localObject1).a(new Vector3f(localVector3f));
/*      */   }
/*      */ 
/*      */   private void b(zi paramzi, xM paramxM)
/*      */   {
/*  917 */     for (paramzi = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext(); )
/*      */     {
/*      */       zi localzi;
/*  919 */       if ((
/*  919 */         localzi = (zi)paramzi.next()).jdField_a_of_type_JavaLangString
/*  919 */         .equals("node"))
/*      */       {
/*  921 */         a(localzi, paramxM);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public final xW a(String paramString1, String paramString2)
/*      */   {
/*  938 */     this.jdField_a_of_type_JavaLangString = paramString1;
/*  939 */     this.jdField_b_of_type_JavaLangString = paramString2;
/*  940 */     xW localxW = new xW();
/*  941 */     xM localxM = null; if (this.jdField_a_of_type_Zi == null) {
/*  942 */       paramString1 = paramString1 + paramString2 + ".scene";
/*  943 */       while (paramString1.contains("//")) {
/*  944 */         paramString1 = paramString1.replaceAll("//", "/");
/*      */       }
/*      */ 
/*  947 */       a(paramString1);
/*      */     }
/*      */ 
/*  950 */     for (paramString1 = this.jdField_a_of_type_Zi.jdField_a_of_type_JavaUtilLinkedList.iterator(); paramString1.hasNext(); ) {
/*  951 */       if ((
/*  951 */         paramString2 = (zi)paramString1.next()).jdField_a_of_type_JavaLangString
/*  951 */         .equals("environment")) {
/*  952 */         a(paramString2);
/*      */       }
/*  954 */       if (paramString2.jdField_a_of_type_JavaLangString.equals("nodes")) {
/*  955 */         b(paramString2, localxW);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  961 */     paramString1 = 0;
/*  962 */     for (paramString2 = localxW.a().iterator(); paramString2.hasNext(); ) {
/*  963 */       if (((
/*  963 */         localxM = (xM)paramString2.next()) instanceof xS))
/*      */       {
/*  964 */         paramString1 = (int)(paramString1 + ((xS)localxM).a());
/*      */       }
/*      */     }
/*  967 */     localxW.a(paramString1);
/*      */ 
/*  970 */     localxW.a(localxW.a(new Vector3f(), new Vector3f()));
/*      */ 
/*  974 */     localxW.o();
/*      */ 
/*  976 */     localxW.q();
/*      */ 
/*  978 */     return localxW;
/*      */   }
/*      */ 
/*      */   private static void a(Mesh paramMesh, String paramString1, String paramString2)
/*      */   {
/*  984 */     Object localObject1 = paramString2; paramString2 = paramString1; System.err.println("[PARSER] parsing skeleton " + paramString2 + (String)localObject1); (localObject2 = new XMLOgreParser()).a(paramString2 + (String)localObject1 + ".xml"); paramString2 = new ye(); localObject1 = null; Object localObject3 = null; Object localObject4 = null; for (Object localObject2 = ((XMLOgreParser)localObject2).jdField_a_of_type_Zi.jdField_a_of_type_JavaUtilLinkedList.iterator(); ((Iterator)localObject2).hasNext(); ) { if ((localObject5 = (zi)((Iterator)localObject2).next()).jdField_a_of_type_JavaLangString.equals("bones")) localObject1 = localObject5; if (((zi)localObject5).jdField_a_of_type_JavaLangString.equals("bonehierarchy")) localObject3 = localObject5; if (((zi)localObject5).jdField_a_of_type_JavaLangString.equals("animations")) localObject4 = localObject5;
/*      */     }
/*  984 */     Object localObject5;
/*  984 */     b(paramString2, (zi)localObject1); a(paramString2, localObject3); if (localObject4 != null) { System.out.println("... parsing skeleton animation"); paramString2.a(a(localObject4)); } paramString1 = paramString2;
/*  985 */     paramString2 = new HashMap();
/*      */ 
/*  987 */     for (localObject4 : paramMesh.a())
/*      */     {
/*  990 */       if ((
/*  990 */         localObject5 = (Vector)paramString2.get(Integer.valueOf(localObject4.jdField_a_of_type_Int))) == null)
/*      */       {
/*  991 */         localObject5 = new Vector();
/*  992 */         paramString2.put(Integer.valueOf(localObject4.jdField_a_of_type_Int), localObject5);
/*      */       }
/*      */ 
/*  996 */       ((Vector)localObject5).add(localObject4);
/*      */     }
/*      */ 
/* 1000 */     (
/* 1001 */       localObject1 = new yj(paramMesh.a()))
/* 1001 */       .a(paramString2, paramString1);
/* 1002 */     paramMesh.a(null);
/*      */ 
/* 1004 */     paramMesh.a(new yf(paramString1, (yj)localObject1));
/*      */   }
/*      */ 
/*      */   private static String a(zi paramzi)
/*      */   {
/* 1054 */     for (paramzi = paramzi.jdField_a_of_type_JavaUtilVector.iterator(); paramzi.hasNext(); )
/*      */     {
/*      */       Aw localAw;
/* 1055 */       if ((
/* 1055 */         localAw = (Aw)paramzi.next()).jdField_a_of_type_JavaLangString
/* 1055 */         .equals("name")) {
/* 1056 */         return localAw.jdField_b_of_type_JavaLangString;
/*      */       }
/*      */     }
/* 1059 */     return null;
/*      */   }
/*      */ 
/*      */   private static xS b(zi paramzi)
/*      */   {
/* 1071 */     Object localObject3 = null;
/*      */ 
/* 1077 */     for (Iterator localIterator1 = paramzi.jdField_a_of_type_JavaUtilVector.iterator(); localIterator1.hasNext(); ) {
/* 1078 */       localObject5 = (
/* 1078 */         localObject4 = (Aw)localIterator1.next()).jdField_b_of_type_JavaLangString;
/*      */ 
/* 1079 */       ((Aw)localObject4).jdField_a_of_type_JavaLangString.equals("material");
/* 1080 */       if (((Aw)localObject4).jdField_a_of_type_JavaLangString.equals("usesharedvertices"))
/*      */       {
/* 1083 */         Boolean.parseBoolean((String)localObject5);
/*      */       }
/* 1085 */       if (((Aw)localObject4).jdField_a_of_type_JavaLangString.equals("use32bitindexes")) {
/* 1086 */         Boolean.parseBoolean((String)localObject5);
/*      */       }
/* 1088 */       if (((Aw)localObject4).jdField_a_of_type_JavaLangString.equals("operationtype"))
/* 1089 */         localObject3 = localObject5;
/*      */     }
/*      */     Object localObject4;
/*      */     Object localObject5;
/*      */     Object localObject2;
/* 1093 */     if (((String)localObject3).equals("line_list"))
/* 1094 */       localObject2 = new xU();
/*      */     else {
/* 1096 */       localObject2 = new Mesh();
/*      */     }
/*      */ 
/* 1099 */     for (localIterator1 = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); localIterator1.hasNext(); )
/*      */     {
/*      */       int i;
/* 1104 */       if (((
/* 1104 */         localObject4 = (zi)localIterator1.next()).jdField_a_of_type_JavaLangString
/* 1104 */         .equals("faces")) && 
/* 1105 */         ((localObject2 instanceof Mesh)))
/*      */       {
/* 1108 */         localObject5 = (Mesh)localObject2;
/* 1109 */         i = Integer.parseInt(((Aw)((zi)localObject4).jdField_a_of_type_JavaUtilVector.get(0)).jdField_b_of_type_JavaLangString);
/*      */ 
/* 1112 */         ((Mesh)localObject5).b(i);
/*      */ 
/* 1115 */         ((Mesh)localObject5).a(GlUtil.a(i * 3 << 2, 0).asIntBuffer());
/* 1116 */         ((Mesh)localObject5).b = GlUtil.a(i * 3 << 2, 1).asIntBuffer();
/* 1117 */         ((Mesh)localObject5).jdField_c_of_type_JavaNioIntBuffer = GlUtil.a(i * 3 << 2, 2).asIntBuffer();
/*      */ 
/* 1120 */         for (localObject3 = ((zi)localObject4).jdField_a_of_type_JavaUtilLinkedList.iterator(); ((Iterator)localObject3).hasNext(); ) { paramzi = (zi)((Iterator)localObject3).next();
/*      */ 
/* 1123 */           ((Mesh)localObject5).k();
/*      */ 
/* 1125 */           for (i = 0; i < 3; i++)
/*      */           {
/* 1127 */             ((Mesh)localObject5).a().put(Integer.parseInt(((Aw)paramzi.jdField_a_of_type_JavaUtilVector.get(i)).jdField_b_of_type_JavaLangString));
/*      */ 
/* 1130 */             ((Mesh)localObject5).jdField_c_of_type_JavaNioIntBuffer.put(Integer.parseInt(((Aw)paramzi.jdField_a_of_type_JavaUtilVector.get(i)).jdField_b_of_type_JavaLangString));
/*      */ 
/* 1133 */             ((Mesh)localObject5).b.put(Integer.parseInt(((Aw)paramzi.jdField_a_of_type_JavaUtilVector.get(i)).jdField_b_of_type_JavaLangString));
/*      */           }
/*      */         }
/*      */       }
/*      */       int j;
/*      */       Object localObject1;
/* 1143 */       if (((zi)localObject4).jdField_a_of_type_JavaLangString.equals("geometry")) {
/* 1144 */         j = 0;
/*      */ 
/* 1146 */         for (paramzi = ((zi)localObject4).jdField_a_of_type_JavaUtilVector.iterator(); paramzi.hasNext(); ) {
/* 1147 */           localObject3 = (
/* 1147 */             localObject1 = (Aw)paramzi.next()).jdField_b_of_type_JavaLangString;
/*      */ 
/* 1148 */           if (((Aw)localObject1).jdField_a_of_type_JavaLangString.equals("vertexcount")) {
/* 1149 */             j = Integer.parseInt((String)localObject3);
/* 1150 */             ((xS)localObject2).a(j);
/* 1151 */             if ((localObject2 instanceof Mesh)) {
/* 1152 */               ((Mesh)localObject2).c(j);
/*      */             }
/*      */           }
/*      */         }
/* 1156 */         for (paramzi = ((zi)localObject4).jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext(); )
/* 1157 */           if ((
/* 1157 */             localObject1 = (zi)paramzi.next()).jdField_a_of_type_JavaLangString
/* 1157 */             .equals("vertexbuffer")) {
/* 1158 */             int k = j; Object localObject6 = localObject1; localObject3 = localObject2; boolean bool1 = false; boolean bool2 = false; int m = 0; for (Iterator localIterator2 = ((zi)localObject6).jdField_a_of_type_JavaUtilVector.iterator(); localIterator2.hasNext(); )
/*      */             {
/* 1158 */               Aw localAw;
/* 1158 */               String str = (localAw = (Aw)localIterator2.next()).jdField_b_of_type_JavaLangString; if (localAw.jdField_a_of_type_JavaLangString.equals("positions")) bool1 = Boolean.parseBoolean(str); if (localAw.jdField_a_of_type_JavaLangString.equals("normals")) bool2 = Boolean.parseBoolean(str); if (localAw.jdField_a_of_type_JavaLangString.equals("texture_coords")) m = Integer.parseInt(str); if (localAw.jdField_a_of_type_JavaLangString.equals("texture_coord_dimension_0")) Integer.parseInt(str);  } if (bool1) ((xS)localObject3).b = GlUtil.a(k * 3 << 2, 3).asFloatBuffer(); if (bool2) ((Mesh)localObject3).d = GlUtil.a(k * 3 << 2, 4).asFloatBuffer(); if (m > 0) ((Mesh)localObject3).jdField_c_of_type_JavaNioFloatBuffer = GlUtil.a(k * 3 << 2, 5).asFloatBuffer(); int n = 0; float f2 = -2.147484E+009F; float f1 = 2.147484E+009F; float f3 = -2.147484E+009F; float f4 = 2.147484E+009F; float f5 = -2.147484E+009F; float f6 = 2.147484E+009F; for (localObject6 = ((zi)localObject6).jdField_a_of_type_JavaUtilLinkedList.iterator(); ((Iterator)localObject6).hasNext(); )
/*      */             {
/* 1158 */               Vector3f localVector3f;
/* 1158 */               for (localObject7 = ((zi)((Iterator)localObject6).next()).jdField_a_of_type_JavaUtilLinkedList.iterator(); ((Iterator)localObject7).hasNext(); ((Mesh)localObject3).jdField_c_of_type_JavaNioFloatBuffer.put(localVector3f.y)) { zi localzi = (zi)((Iterator)localObject7).next(); if ((bool1) && (localzi.jdField_a_of_type_JavaLangString.equals("position"))) { localVector3f = a(localzi); ((xS)localObject3).b.put(localVector3f.x); ((xS)localObject3).b.put(localVector3f.y); ((xS)localObject3).b.put(localVector3f.z); f2 = localVector3f.x > f2 ? localVector3f.x : f2; f1 = localVector3f.x < f1 ? localVector3f.x : f1; f3 = localVector3f.y > f3 ? localVector3f.y : f3; f4 = localVector3f.y < f4 ? localVector3f.y : f4; f5 = localVector3f.z > f5 ? localVector3f.z : f5; f6 = localVector3f.z < f6 ? localVector3f.z : f6; n++; } if ((bool2) && (localzi.jdField_a_of_type_JavaLangString.equals("normal"))) { localVector3f = a(localzi); ((Mesh)localObject3).d.put(localVector3f.x); ((Mesh)localObject3).d.put(localVector3f.y); ((Mesh)localObject3).d.put(localVector3f.z); } if ((m > 0) && (localzi.jdField_a_of_type_JavaLangString.equals("texcoord"))) { localVector3f = a(localzi); ((Mesh)localObject3).jdField_c_of_type_JavaNioFloatBuffer.put(localVector3f.x);
/*      */                 }
/*      */               }
/*      */             }
/* 1158 */             Object localObject7;
/* 1158 */             if (bool1) { localObject6 = new Vector3f(f1, f4, f6); localObject7 = new Vector3f(f2, f3, f5); if ((!jdField_a_of_type_Boolean) && (n != k)) throw new AssertionError(); ((xS)localObject3).a(new xO((Vector3f)localObject6, (Vector3f)localObject7)); ((xS)localObject3).o();
/*      */             }
/*      */           }
/*      */       }
/* 1162 */       if (((zi)localObject4).jdField_a_of_type_JavaLangString.equals("boneassignments")) {
/* 1163 */         a((Mesh)localObject2, (zi)localObject4);
/*      */       }
/*      */     }
/* 1166 */     ((xS)localObject2).b.rewind();
/* 1167 */     ((xS)localObject2).a().rewind();
/* 1168 */     if ((localObject2 instanceof Mesh)) {
/* 1169 */       ((Mesh)localObject2).jdField_c_of_type_JavaNioFloatBuffer.rewind();
/* 1170 */       ((Mesh)localObject2).d.rewind();
/* 1171 */       ((Mesh)localObject2).b.rewind();
/* 1172 */       ((Mesh)localObject2).jdField_c_of_type_JavaNioIntBuffer.rewind();
/*      */     }
/* 1174 */     return localObject2;
/*      */   }
/*      */ 
/*      */   private static Vector b(zi paramzi)
/*      */   {
/* 1185 */     Vector localVector = new Vector();
/* 1186 */     for (paramzi = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext(); )
/*      */     {
/* 1188 */       xS localxS = b((zi)paramzi.next());
/*      */ 
/* 1189 */       localVector.add(localxS);
/*      */     }
/* 1191 */     return localVector;
/*      */   }
/*      */ 
/*      */   private static void a(zi paramzi, Vector paramVector)
/*      */   {
/* 1201 */     for (Object localObject : paramzi.jdField_a_of_type_JavaUtilLinkedList) {
/* 1202 */       int i = 0;
/* 1203 */       String str = "";
/* 1204 */       for (localObject = ((zi)localObject).jdField_a_of_type_JavaUtilVector.iterator(); ((Iterator)localObject).hasNext(); )
/*      */       {
/*      */         Aw localAw;
/* 1206 */         if ((
/* 1206 */           localAw = (Aw)((Iterator)localObject).next()).jdField_a_of_type_JavaLangString
/* 1206 */           .equals("name")) {
/* 1207 */           str = localAw.jdField_b_of_type_JavaLangString;
/*      */         }
/*      */ 
/* 1210 */         if (localAw.jdField_a_of_type_JavaLangString.equals("index")) {
/* 1211 */           i = Integer.parseInt(localAw.jdField_b_of_type_JavaLangString);
/*      */         }
/*      */       }
/*      */ 
/* 1215 */       ((xS)paramVector.get(i)).c(str);
/*      */     }
/*      */   }
/*      */ 
/*      */   private static void a(zi paramzi, wP paramwP)
/*      */   {
/* 1443 */     for (paramzi = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext(); )
/*      */     {
/*      */       Object localObject1;
/* 1447 */       if ((
/* 1447 */         localObject1 = (zi)paramzi.next()).jdField_a_of_type_JavaLangString
/* 1447 */         .equals("keyframe")) {
/* 1448 */         float f2 = -1.0F;
/* 1449 */         for (Object localObject3 = ((zi)localObject1).jdField_a_of_type_JavaUtilVector.iterator(); ((Iterator)localObject3).hasNext(); ) {
/* 1450 */           if ((
/* 1450 */             localObject4 = (Aw)((Iterator)localObject3).next()).jdField_a_of_type_JavaLangString
/* 1450 */             .equals("time")) {
/* 1451 */             f2 = Float.parseFloat(((Aw)localObject4).jdField_b_of_type_JavaLangString);
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/* 1456 */         localObject3 = null;
/* 1457 */         Object localObject4 = null;
/* 1458 */         Vector3f localVector3f = null;
/* 1459 */         for (localObject1 = ((zi)localObject1).jdField_a_of_type_JavaUtilLinkedList.iterator(); ((Iterator)localObject1).hasNext(); )
/*      */         {
/* 1462 */           if ((
/* 1462 */             localObject5 = (zi)((Iterator)localObject1).next()).jdField_a_of_type_JavaLangString
/* 1462 */             .equals("translate"))
/*      */           {
/* 1464 */             localObject3 = a((zi)localObject5);
/*      */           }
/*      */           Iterator localIterator;
/*      */           Object localObject6;
/*      */           Object localObject7;
/* 1467 */           if (((zi)localObject5).jdField_a_of_type_JavaLangString.equals("rotate"))
/*      */           {
/* 1469 */             localObject4 = new AxisAngle4f();
/* 1470 */             for (localIterator = ((zi)localObject5).jdField_a_of_type_JavaUtilVector.iterator(); localIterator.hasNext(); ) {
/* 1471 */               localObject7 = (
/* 1471 */                 localObject6 = (Aw)localIterator.next()).jdField_b_of_type_JavaLangString;
/*      */ 
/* 1472 */               if (((Aw)localObject6).jdField_a_of_type_JavaLangString.equals("angle")) {
/* 1473 */                 ((AxisAngle4f)localObject4).angle = Float.parseFloat((String)localObject7);
/*      */               }
/*      */             }
/* 1476 */             for (localIterator = ((zi)localObject5).jdField_a_of_type_JavaUtilLinkedList.iterator(); localIterator.hasNext(); ) {
/* 1477 */               if ((
/* 1477 */                 localObject6 = (zi)localIterator.next()).jdField_a_of_type_JavaLangString
/* 1477 */                 .equals("axis")) {
/* 1478 */                 (
/* 1479 */                   localObject7 = a((zi)localObject6))
/* 1479 */                   .normalize();
/* 1480 */                 ((AxisAngle4f)localObject4).x = ((Vector3f)localObject7).x;
/* 1481 */                 ((AxisAngle4f)localObject4).y = ((Vector3f)localObject7).y;
/* 1482 */                 ((AxisAngle4f)localObject4).z = ((Vector3f)localObject7).z;
/*      */               }
/*      */             }
/*      */           }
/* 1486 */           if (((zi)localObject5).jdField_a_of_type_JavaLangString.equals("scale"))
/*      */           {
/* 1488 */             localVector3f = a((zi)localObject5);
/*      */           }
/*      */         }
/* 1491 */         if ((!jdField_a_of_type_Boolean) && (f2 < 0.0F)) throw new AssertionError();
/*      */ 
/* 1493 */         if ((!jdField_a_of_type_Boolean) && (localObject3 == null)) throw new AssertionError();
/* 1494 */         if ((!jdField_a_of_type_Boolean) && (localObject4 == null)) throw new AssertionError();
/*      */ 
/* 1496 */         Object localObject5 = new Quat4f(); localObject4 = new Vector3f(((AxisAngle4f)localObject4).x, ((AxisAngle4f)localObject4).y, ((AxisAngle4f)localObject4).z); float f1 = ((AxisAngle4f)localObject4).angle; (localObject4 = new Vector3f((Vector3f)localObject4)).normalize(); d.a(f1, (Vector3f)localObject4, (Quat4f)localObject5); Object localObject2 = localObject5;
/* 1497 */         paramwP.a(f2, (Vector3f)localObject3, localObject2, localVector3f);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private static Vector3f a(zi paramzi)
/*      */   {
/* 1509 */     Vector3f localVector3f = new Vector3f();
/* 1510 */     Aw localAw = (Aw)paramzi.jdField_a_of_type_JavaUtilVector.get(0);
/* 1511 */     localVector3f.x = Float.parseFloat(localAw.jdField_b_of_type_JavaLangString);
/* 1512 */     localAw = (Aw)paramzi.jdField_a_of_type_JavaUtilVector.get(1);
/* 1513 */     localVector3f.y = Float.parseFloat(localAw.jdField_b_of_type_JavaLangString);
/* 1514 */     if (paramzi.jdField_a_of_type_JavaUtilVector.size() > 2) {
/* 1515 */       localAw = (Aw)paramzi.jdField_a_of_type_JavaUtilVector.get(2);
/* 1516 */       localVector3f.z = Float.parseFloat(localAw.jdField_b_of_type_JavaLangString);
/*      */     } else {
/* 1518 */       localVector3f.z = 1.0F;
/*      */     }
/* 1520 */     return localVector3f;
/*      */   }
/*      */ 
/*      */   private static Vector4f a(zi paramzi)
/*      */   {
/* 1530 */     Vector4f localVector4f = new Vector4f();
/* 1531 */     Aw localAw = (Aw)paramzi.jdField_a_of_type_JavaUtilVector.get(0);
/* 1532 */     localVector4f.x = Float.parseFloat(localAw.jdField_b_of_type_JavaLangString);
/* 1533 */     localAw = (Aw)paramzi.jdField_a_of_type_JavaUtilVector.get(1);
/* 1534 */     localVector4f.y = Float.parseFloat(localAw.jdField_b_of_type_JavaLangString);
/* 1535 */     localAw = (Aw)paramzi.jdField_a_of_type_JavaUtilVector.get(2);
/* 1536 */     localVector4f.z = Float.parseFloat(localAw.jdField_b_of_type_JavaLangString);
/* 1537 */     localAw = (Aw)paramzi.jdField_a_of_type_JavaUtilVector.get(3);
/* 1538 */     localVector4f.w = Float.parseFloat(localAw.jdField_b_of_type_JavaLangString);
/* 1539 */     return localVector4f;
/*      */   }
/*      */ 
/*      */   private void a(String paramString)
/*      */   {
/*      */     try
/*      */     {
/* 1653 */       SAXParserFactory.newInstance().newSAXParser()
/* 1654 */         .parse(zZ.a.a(paramString), this);
/*      */       return;
/*      */     }
/*      */     catch (ParserConfigurationException localParserConfigurationException)
/*      */     {
/* 1663 */       localParserConfigurationException.printStackTrace();
/*      */       return;
/*      */     }
/*      */     catch (SAXException localSAXException)
/*      */     {
/* 1663 */       localSAXException.printStackTrace();
/*      */       return;
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 1663 */       localIOException.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void startDocument()
/*      */   {
/*      */   }
/*      */ 
/*      */   public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
/*      */   {
/* 1720 */     (
/* 1722 */       paramString1 = "".equals(paramString2) ? paramString3 : paramString2)
/* 1722 */       .contains("userData");
/*      */ 
/* 1726 */     if (this.jdField_a_of_type_Zi == null) {
/* 1727 */       this.jdField_a_of_type_Zi = new zi();
/* 1728 */       this.jdField_a_of_type_Zi.jdField_a_of_type_JavaLangString = paramString1;
/* 1729 */       this.jdField_b_of_type_Zi = this.jdField_a_of_type_Zi; return;
/*      */     }
/*      */ 
/* 1732 */     (
/* 1733 */       paramString2 = new zi()).jdField_a_of_type_JavaLangString = 
/* 1733 */       paramString1;
/*      */ 
/* 1735 */     if (paramAttributes != null) {
/* 1736 */       for (paramString1 = 0; paramString1 < paramAttributes.getLength(); paramString1++) {
/* 1737 */         paramString3 = paramAttributes.getLocalName(paramString1);
/* 1738 */         if ("".equals(paramString3)) {
/* 1739 */           paramString3 = paramAttributes.getQName(paramString1);
/*      */         }
/*      */ 
/* 1742 */         paramString2.jdField_a_of_type_JavaUtilVector.add(new Aw(paramString3, paramAttributes.getValue(paramString1)));
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1747 */     this.jdField_b_of_type_Zi.jdField_a_of_type_JavaUtilLinkedList.add(paramString2);
/*      */ 
/* 1749 */     paramString2.jdField_a_of_type_Zi = this.jdField_b_of_type_Zi;
/* 1750 */     if (!this.jdField_b_of_type_Zi.jdField_a_of_type_Boolean)
/*      */     {
/* 1754 */       this.jdField_b_of_type_Zi = paramString2;
/*      */     }
/*      */   }
/*      */ 
/*      */   static
/*      */   {
/*  113 */     System.getProperty("line.separator");
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.graphicsengine.meshimporter.XMLOgreParser
 * JD-Core Version:    0.6.2
 */