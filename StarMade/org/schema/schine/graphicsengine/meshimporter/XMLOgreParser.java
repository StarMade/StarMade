/*    1:     */package org.schema.schine.graphicsengine.meshimporter;
/*    2:     */
/*    3:     */import AA;
/*    4:     */import Ad;
/*    5:     */import d;
/*    6:     */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    7:     */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*    8:     */import java.io.BufferedReader;
/*    9:     */import java.io.IOException;
/*   10:     */import java.io.InputStreamReader;
/*   11:     */import java.io.PrintStream;
/*   12:     */import java.io.StringReader;
/*   13:     */import java.nio.ByteBuffer;
/*   14:     */import java.nio.FloatBuffer;
/*   15:     */import java.nio.IntBuffer;
/*   16:     */import java.util.ArrayList;
/*   17:     */import java.util.HashMap;
/*   18:     */import java.util.Iterator;
/*   19:     */import java.util.LinkedList;
/*   20:     */import java.util.List;
/*   21:     */import java.util.Vector;
/*   22:     */import javax.vecmath.AxisAngle4f;
/*   23:     */import javax.vecmath.Quat4f;
/*   24:     */import javax.vecmath.Vector3f;
/*   25:     */import javax.vecmath.Vector4f;
/*   26:     */import javax.xml.parsers.DocumentBuilder;
/*   27:     */import javax.xml.parsers.DocumentBuilderFactory;
/*   28:     */import javax.xml.parsers.ParserConfigurationException;
/*   29:     */import javax.xml.parsers.SAXParser;
/*   30:     */import javax.xml.parsers.SAXParserFactory;
/*   31:     */import k;
/*   32:     */import org.schema.schine.graphicsengine.core.GlUtil;
/*   33:     */import org.schema.schine.graphicsengine.forms.AnimationNotFoundException;
/*   34:     */import org.schema.schine.graphicsengine.forms.Mesh;
/*   35:     */import org.w3c.dom.Document;
/*   36:     */import org.w3c.dom.Node;
/*   37:     */import org.w3c.dom.NodeList;
/*   38:     */import org.xml.sax.Attributes;
/*   39:     */import org.xml.sax.InputSource;
/*   40:     */import org.xml.sax.SAXException;
/*   41:     */import org.xml.sax.helpers.DefaultHandler;
/*   42:     */import wL;
/*   43:     */import wP;
/*   44:     */import wQ;
/*   45:     */import xM;
/*   46:     */import xN;
/*   47:     */import xO;
/*   48:     */import xS;
/*   49:     */import xU;
/*   50:     */import xW;
/*   51:     */import ya;
/*   52:     */import ye;
/*   53:     */import yf;
/*   54:     */import yi;
/*   55:     */import yj;
/*   56:     */import zB;
/*   57:     */import zh;
/*   58:     */import zi;
/*   59:     */
/*  108:     */public class XMLOgreParser
/*  109:     */  extends DefaultHandler
/*  110:     */{
/*  111:     */  static
/*  112:     */  {
/*  113: 113 */    System.getProperty("line.separator");
/*  114:     */  }
/*  115:     */  
/*  127:     */  public static void main(String[] paramArrayOfString)
/*  128:     */  {
/*  129: 129 */    paramArrayOfString = new XMLOgreParser();
/*  130: 130 */    String str = "ogretest/temple-machine.scene";
/*  131:     */    
/*  132: 132 */    paramArrayOfString.a(str, "");
/*  133:     */  }
/*  134:     */  
/*  137: 137 */  private HashMap jdField_a_of_type_JavaUtilHashMap = new HashMap();
/*  138:     */  
/*  143:     */  private zi jdField_a_of_type_Zi;
/*  144:     */  
/*  149:     */  private zi jdField_b_of_type_Zi;
/*  150:     */  
/*  155:     */  private String jdField_a_of_type_JavaLangString;
/*  156:     */  
/*  161:     */  private String jdField_b_of_type_JavaLangString;
/*  162:     */  
/*  166:     */  private zB[] jdField_a_of_type_ArrayOfZB;
/*  167:     */  
/*  172:     */  public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
/*  173:     */  {
/*  174: 174 */    if ((paramArrayOfChar = new String(paramArrayOfChar, paramInt1, paramInt2)).trim().length() > 0)
/*  175:     */    {
/*  176: 176 */      this.jdField_b_of_type_Zi.jdField_b_of_type_JavaLangString = paramArrayOfChar;
/*  177:     */    }
/*  178:     */  }
/*  179:     */  
/*  198:     */  public void endElement(String paramString1, String paramString2, String paramString3)
/*  199:     */  {
/*  200: 200 */    if (("".equals(paramString2) ? paramString3 : paramString2).equals(this.jdField_b_of_type_Zi.jdField_a_of_type_JavaLangString)) {
/*  201: 201 */      this.jdField_b_of_type_Zi.jdField_a_of_type_Boolean = true;
/*  202: 202 */      if (this.jdField_b_of_type_Zi.jdField_a_of_type_Zi != null) {
/*  203: 203 */        this.jdField_b_of_type_Zi = this.jdField_b_of_type_Zi.jdField_a_of_type_Zi;
/*  204:     */      }
/*  205:     */    }
/*  206:     */  }
/*  207:     */  
/*  231:     */  private static wL a(zi paramzi)
/*  232:     */  {
/*  233: 233 */    wL localwL = new wL();
/*  234: 234 */    int i = paramzi.jdField_a_of_type_JavaUtilLinkedList.size();
/*  235:     */    
/*  236: 236 */    for (Iterator localIterator = paramzi.jdField_a_of_type_JavaUtilVector.iterator(); localIterator.hasNext();) { AA localAA;
/*  237: 237 */      if ((localAA = (AA)localIterator.next()).jdField_a_of_type_JavaLangString.equals("name")) {
/*  238: 238 */        localwL.jdField_a_of_type_JavaLangString = localAA.jdField_b_of_type_JavaLangString;
/*  239: 239 */        System.err.println("... Animation name " + localwL.jdField_a_of_type_JavaLangString);
/*  240:     */      }
/*  241: 241 */      if (localAA.jdField_a_of_type_JavaLangString.equals("loop")) {
/*  242: 242 */        Boolean.parseBoolean(localAA.jdField_b_of_type_JavaLangString);
/*  243:     */      }
/*  244:     */      
/*  246: 246 */      localAA.jdField_a_of_type_JavaLangString.equals("interpolationMode");
/*  247:     */      
/*  249: 249 */      localAA.jdField_a_of_type_JavaLangString.equals("rotationInterpolationMode");
/*  250:     */      
/*  252: 252 */      localAA.jdField_a_of_type_JavaLangString.equals("length");
/*  253:     */    }
/*  254:     */    
/*  256: 256 */    a(paramzi, i);
/*  257:     */    
/*  260: 260 */    return localwL;
/*  261:     */  }
/*  262:     */  
/*  268:     */  private static Vector a(zi paramzi)
/*  269:     */  {
/*  270: 270 */    Vector localVector = new Vector();
/*  271: 271 */    for (paramzi = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext();) { zi localzi;
/*  272: 272 */      if ((localzi = (zi)paramzi.next()).jdField_a_of_type_JavaLangString.equals("animation")) {
/*  273: 273 */        localVector.add(a(localzi));
/*  274:     */      }
/*  275:     */    }
/*  276: 276 */    return localVector;
/*  277:     */  }
/*  278:     */  
/*  284:     */  private static wL b(zi paramzi)
/*  285:     */  {
/*  286: 286 */    Object localObject1 = null;
/*  287:     */    
/*  288: 288 */    float f = 0.0F;
/*  289:     */    
/*  291: 291 */    for (Iterator localIterator1 = paramzi.jdField_a_of_type_JavaUtilVector.iterator(); localIterator1.hasNext();) {
/*  292: 292 */      if ((localObject3 = (AA)localIterator1.next()).jdField_a_of_type_JavaLangString.equals("name")) {
/*  293: 293 */        localObject1 = ((AA)localObject3).jdField_b_of_type_JavaLangString;
/*  294:     */      }
/*  295:     */      
/*  296: 296 */      if (((AA)localObject3).jdField_a_of_type_JavaLangString.equals("loop")) {
/*  297: 297 */        Boolean.parseBoolean(((AA)localObject3).jdField_b_of_type_JavaLangString);
/*  298:     */      }
/*  299:     */      
/*  301: 301 */      ((AA)localObject3).jdField_a_of_type_JavaLangString.equals("interpolationMode");
/*  302:     */      
/*  304: 304 */      ((AA)localObject3).jdField_a_of_type_JavaLangString.equals("rotationInterpolationMode");
/*  305:     */      
/*  307: 307 */      if (((AA)localObject3).jdField_a_of_type_JavaLangString.equals("length")) {
/*  308: 308 */        f = Float.parseFloat(((AA)localObject3).jdField_b_of_type_JavaLangString);
/*  309:     */      }
/*  310:     */    }
/*  311:     */    
/*  312:     */    Object localObject3;
/*  313:     */    
/*  314:     */    wL localwL;
/*  315: 315 */    (localwL = new wL()).jdField_a_of_type_JavaLangString = ((String)localObject1);
/*  316: 316 */    localwL.jdField_a_of_type_Float = f;
/*  317:     */    
/*  318: 318 */    for (localIterator1 = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); localIterator1.hasNext();)
/*  319:     */    {
/*  320: 320 */      if ((localObject3 = (zi)localIterator1.next()).jdField_a_of_type_JavaLangString.equals("tracks"))
/*  321:     */      {
/*  322: 322 */        for (paramzi = ((zi)localObject3).jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext();) {
/*  323: 323 */          if ((localObject1 = (zi)paramzi.next()).jdField_a_of_type_JavaLangString.equals("track")) {
/*  324: 324 */            localObject3 = localObject1;Object localObject2 = localwL;localObject1 = null; for (Iterator localIterator2 = ((zi)localObject3).jdField_a_of_type_JavaUtilVector.iterator(); localIterator2.hasNext();) if ((localObject4 = (AA)localIterator2.next()).jdField_a_of_type_JavaLangString.equals("bone")) localObject1 = ((AA)localObject4).jdField_b_of_type_JavaLangString; Object localObject4; for (localIterator2 = ((zi)localObject3).jdField_a_of_type_JavaUtilLinkedList.iterator(); localIterator2.hasNext(); localObject2.jdField_a_of_type_JavaUtilHashMap.put(((wQ)localObject3).a(), localObject3)) { localObject4 = (zi)localIterator2.next(); if ((!jdField_a_of_type_Boolean) && (localObject1 == null)) throw new AssertionError(); localObject3 = new wQ((String)localObject1);a((zi)localObject4, (wP)localObject3);
/*  325:     */            }
/*  326:     */          }
/*  327:     */        }
/*  328:     */      }
/*  329:     */    }
/*  330:     */    
/*  331: 331 */    return localwL;
/*  332:     */  }
/*  333:     */  
/*  339:     */  private static List a(zi paramzi)
/*  340:     */  {
/*  341: 341 */    ArrayList localArrayList = new ArrayList();
/*  342: 342 */    for (paramzi = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext();) { zi localzi;
/*  343: 343 */      if ((localzi = (zi)paramzi.next()).jdField_a_of_type_JavaLangString.equals("animation")) {
/*  344: 344 */        localArrayList.add(b(localzi));
/*  345:     */      }
/*  346:     */    }
/*  347: 347 */    return localArrayList;
/*  348:     */  }
/*  349:     */  
/*  366:     */  private static void a(Mesh paramMesh, zi paramzi)
/*  367:     */  {
/*  368: 368 */    yi[] arrayOfyi = new yi[paramzi.jdField_a_of_type_JavaUtilLinkedList.size()];
/*  369:     */    
/*  370: 370 */    int i = 0;
/*  371: 371 */    for (paramzi = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext();) { Object localObject;
/*  372: 372 */      if ((localObject = (zi)paramzi.next()).jdField_a_of_type_JavaLangString.equals("vertexboneassignment"))
/*  373:     */      {
/*  374: 374 */        int j = -1;
/*  375: 375 */        int k = -1;
/*  376: 376 */        float f = -1.0F;
/*  377: 377 */        for (localObject = ((zi)localObject).jdField_a_of_type_JavaUtilVector.iterator(); ((Iterator)localObject).hasNext();) { AA localAA;
/*  378: 378 */          String str = (localAA = (AA)((Iterator)localObject).next()).jdField_b_of_type_JavaLangString;
/*  379: 379 */          if (localAA.jdField_a_of_type_JavaLangString.equals("vertexindex")) {
/*  380: 380 */            j = Integer.parseInt(str);
/*  381:     */          }
/*  382: 382 */          if (localAA.jdField_a_of_type_JavaLangString.equals("boneindex")) {
/*  383: 383 */            k = Integer.parseInt(str);
/*  384:     */          }
/*  385: 385 */          if (localAA.jdField_a_of_type_JavaLangString.equals("weight")) {
/*  386: 386 */            f = Float.parseFloat(str);
/*  387:     */          }
/*  388:     */        }
/*  389: 389 */        localObject = new yi(j, k, f);
/*  390:     */        
/*  391: 391 */        arrayOfyi[i] = localObject;
/*  392: 392 */        paramMesh.a(arrayOfyi);
/*  393:     */      }
/*  394: 394 */      i++;
/*  395:     */    }
/*  396:     */  }
/*  397:     */  
/*  403:     */  private static void a(ye paramye, zi paramzi)
/*  404:     */  {
/*  405: 405 */    for (paramzi = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext();)
/*  406: 406 */      if ((localObject1 = (zi)paramzi.next()).jdField_a_of_type_JavaLangString.equals("boneparent")) {
/*  407: 407 */        localObject2 = null;
/*  408: 408 */        String str = null;
/*  409: 409 */        for (localObject1 = ((zi)localObject1).jdField_a_of_type_JavaUtilVector.iterator(); ((Iterator)localObject1).hasNext();) {
/*  410: 410 */          if ((localObject3 = (AA)((Iterator)localObject1).next()).jdField_a_of_type_JavaLangString.equals("bone")) {
/*  411: 411 */            localObject2 = ((AA)localObject3).jdField_b_of_type_JavaLangString;
/*  412:     */          }
/*  413: 413 */          if (((AA)localObject3).jdField_a_of_type_JavaLangString.equals("parent"))
/*  414: 414 */            str = ((AA)localObject3).jdField_b_of_type_JavaLangString;
/*  415:     */        }
/*  416:     */        Object localObject3;
/*  417: 417 */        if ((!jdField_a_of_type_Boolean) && (localObject2 == null)) { throw new AssertionError();
/*  418:     */        }
/*  419: 419 */        localObject1 = paramye.a().values().iterator(); for (;;) { if (!((Iterator)localObject1).hasNext()) break label247;
/*  420: 420 */          if ((localObject3 = (xN)((Iterator)localObject1).next()).jdField_a_of_type_JavaLangString.equals(localObject2)) {
/*  421: 421 */            localObject1 = paramye.a().values().iterator(); for (;;) { if (!((Iterator)localObject1).hasNext()) break label241;
/*  422: 422 */              if ((localObject2 = (xN)((Iterator)localObject1).next()).jdField_a_of_type_JavaLangString.equals(str)) {
/*  423: 423 */                ((xN)localObject3).a((xN)localObject2);
/*  424: 424 */                ((xN)localObject2).a().add(localObject3);
/*  425: 425 */                break;
/*  426:     */              }
/*  427:     */            }
/*  428: 428 */            break;
/*  429:     */          }
/*  430:     */        } }
/*  431:     */    Object localObject2;
/*  432:     */    label241:
/*  433:     */    label247:
/*  434: 434 */    paramzi = 0;
/*  435: 435 */    for (Object localObject1 = paramye.a().values().iterator(); ((Iterator)localObject1).hasNext();) {
/*  436: 436 */      if ((localObject2 = (xN)((Iterator)localObject1).next()).a() == null) {
/*  437: 437 */        if (paramzi == 0) {
/*  438: 438 */          paramzi = 1;
/*  439: 439 */          paramye.a((xN)localObject2);
/*  440:     */        } else {
/*  441: 441 */          System.err.println("WARNING: more than one skeleton root bone found " + ((xN)localObject2).jdField_a_of_type_JavaLangString);
/*  442:     */        }
/*  443:     */      }
/*  444:     */    }
/*  445:     */  }
/*  446:     */  
/*  454:     */  private static void b(ye paramye, zi paramzi)
/*  455:     */  {
/*  456: 456 */    for (paramzi = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext();) { Object localObject1;
/*  457: 457 */      if ((localObject1 = (zi)paramzi.next()).jdField_a_of_type_JavaLangString.equals("bone")) {
/*  458: 458 */        Object localObject2 = null;
/*  459: 459 */        int i = 0;
/*  460:     */        
/*  461: 461 */        for (Object localObject3 = ((zi)localObject1).jdField_a_of_type_JavaUtilVector.iterator(); ((Iterator)localObject3).hasNext();) {
/*  462: 462 */          if ((localObject4 = (AA)((Iterator)localObject3).next()).jdField_a_of_type_JavaLangString.equals("name")) {
/*  463: 463 */            localObject2 = ((AA)localObject4).jdField_b_of_type_JavaLangString;
/*  464:     */          }
/*  465: 465 */          if (((AA)localObject4).jdField_a_of_type_JavaLangString.equals("id")) {
/*  466: 466 */            i = Integer.parseInt(((AA)localObject4).jdField_b_of_type_JavaLangString);
/*  467:     */          }
/*  468:     */        }
/*  469:     */        
/*  470: 470 */        localObject3 = new xN(i, (String)localObject2);xN.f();
/*  471: 471 */        Object localObject4 = null;
/*  472: 472 */        localObject2 = null;
/*  473:     */        
/*  474: 474 */        for (localObject1 = ((zi)localObject1).jdField_a_of_type_JavaUtilLinkedList.iterator(); ((Iterator)localObject1).hasNext();) {
/*  475:     */          zi localzi;
/*  476: 476 */          if ((localzi = (zi)((Iterator)localObject1).next()).jdField_a_of_type_JavaLangString.equals("position"))
/*  477:     */          {
/*  478: 478 */            localObject4 = a(localzi); }
/*  479:     */          Iterator localIterator;
/*  480:     */          Object localObject5;
/*  481:     */          Object localObject6;
/*  482: 482 */          if (localzi.jdField_a_of_type_JavaLangString.equals("rotation")) {
/*  483: 483 */            localObject2 = new AxisAngle4f();
/*  484: 484 */            for (localIterator = localzi.jdField_a_of_type_JavaUtilVector.iterator(); localIterator.hasNext();) {
/*  485: 485 */              localObject6 = (localObject5 = (AA)localIterator.next()).jdField_b_of_type_JavaLangString;
/*  486: 486 */              if (((AA)localObject5).jdField_a_of_type_JavaLangString.equals("angle")) {
/*  487: 487 */                ((AxisAngle4f)localObject2).angle = Float.parseFloat((String)localObject6);
/*  488:     */              }
/*  489:     */            }
/*  490: 490 */            for (localIterator = localzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); localIterator.hasNext();) {
/*  491: 491 */              if ((localObject5 = (zi)localIterator.next()).jdField_a_of_type_JavaLangString.equals("axis"))
/*  492:     */              {
/*  493: 493 */                (localObject6 = a((zi)localObject5)).normalize();
/*  494: 494 */                ((AxisAngle4f)localObject2).x = ((Vector3f)localObject6).x;
/*  495: 495 */                ((AxisAngle4f)localObject2).y = ((Vector3f)localObject6).y;
/*  496: 496 */                ((AxisAngle4f)localObject2).z = ((Vector3f)localObject6).z;
/*  497:     */              }
/*  498:     */            }
/*  499:     */          }
/*  500: 500 */          if (localzi.jdField_a_of_type_JavaLangString.equals("scale")) {
/*  501: 501 */            a(localzi);
/*  502:     */          }
/*  503:     */        }
/*  504:     */        
/*  506: 506 */        ((xN)localObject3).b((Vector3f)localObject4, d.a(((AxisAngle4f)localObject2).angle, new Vector3f(((AxisAngle4f)localObject2).x, ((AxisAngle4f)localObject2).y, ((AxisAngle4f)localObject2).z), new Quat4f()));
/*  507: 507 */        if ((!jdField_a_of_type_Boolean) && (((xN)localObject3).jdField_a_of_type_JavaLangString == null)) throw new AssertionError();
/*  508: 508 */        if ((!jdField_a_of_type_Boolean) && (((xN)localObject3).jdField_a_of_type_Int < 0)) throw new AssertionError();
/*  509: 509 */        paramye.a().put(((xN)localObject3).jdField_a_of_type_Int, localObject3);
/*  510:     */      }
/*  511:     */    }
/*  512:     */  }
/*  513:     */  
/*  521:     */  private xS a(zi paramzi)
/*  522:     */  {
/*  523: 523 */    Object localObject1 = null;
/*  524: 524 */    Object localObject2 = "default";
/*  525: 525 */    for (Iterator localIterator = paramzi.jdField_a_of_type_JavaUtilVector.iterator(); localIterator.hasNext();) {
/*  526: 526 */      localObject4 = (localObject3 = (AA)localIterator.next()).jdField_b_of_type_JavaLangString;
/*  527: 527 */      if (((AA)localObject3).jdField_a_of_type_JavaLangString.equals("description"))
/*  528:     */      {
/*  529: 529 */        localObject2 = localObject4;
/*  530:     */      }
/*  531:     */      
/*  532: 532 */      ((AA)localObject3).jdField_a_of_type_JavaLangString.equals("id");
/*  533:     */      
/*  536: 536 */      ((AA)localObject3).jdField_a_of_type_JavaLangString.equals("castShadows");
/*  537:     */      
/*  540: 540 */      ((AA)localObject3).jdField_a_of_type_JavaLangString.equals("receiveShadows");
/*  541:     */      
/*  544: 544 */      if (((AA)localObject3).jdField_a_of_type_JavaLangString.equals("meshFile"))
/*  545:     */      {
/*  546: 546 */        localObject3 = this.jdField_a_of_type_JavaLangString;localObject5 = new Vector();(localObject6 = new XMLOgreParser()).a((String)localObject3 + (String)localObject4 + ".xml");localObject4 = null; for (localObject7 = ((XMLOgreParser)localObject6).jdField_a_of_type_Zi.jdField_a_of_type_JavaUtilLinkedList.iterator(); ((Iterator)localObject7).hasNext();) { if ((localObject6 = (zi)((Iterator)localObject7).next()).jdField_a_of_type_JavaLangString.equals("submeshes")) ((Vector)localObject5).addAll(b((zi)localObject6)); if (((zi)localObject6).jdField_a_of_type_JavaLangString.equals("submeshnames")) a((zi)localObject6, (Vector)localObject5); ((zi)localObject6).jdField_a_of_type_JavaLangString.equals("poses"); if (((zi)localObject6).jdField_a_of_type_JavaLangString.equals("skeletonlink")) localObject4 = a((zi)localObject6); } localObject7 = (xS)((Vector)localObject5).remove(0); for (Object localObject6 = ((Vector)localObject5).iterator(); ((Iterator)localObject6).hasNext(); ((xS)localObject7).a((xM)localObject5)) localObject5 = (xS)((Iterator)localObject6).next(); if (localObject4 != null) a((Mesh)localObject7, (String)localObject3, (String)localObject4); localObject1 = localObject7;
/*  547:     */      } }
/*  548:     */    Object localObject4;
/*  549:     */    Object localObject5;
/*  550:     */    Object localObject7;
/*  551: 551 */    localObject1.c((String)localObject2);
/*  552: 552 */    localIterator = null;
/*  553: 553 */    for (Object localObject3 = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); ((Iterator)localObject3).hasNext();) {
/*  554: 554 */      if ((localObject4 = (zi)((Iterator)localObject3).next()).jdField_a_of_type_JavaLangString.equals("subentities"))
/*  555:     */      {
/*  556: 556 */        for (paramzi = ((zi)localObject4).jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext();) {
/*  557: 557 */          for (localObject5 = ((zi)paramzi.next()).jdField_a_of_type_JavaUtilVector.iterator(); ((Iterator)localObject5).hasNext();) {
/*  558: 558 */            (localObject2 = (AA)((Iterator)localObject5).next()).jdField_a_of_type_JavaLangString.equals("index");
/*  559:     */            
/*  561: 561 */            if (((AA)localObject2).jdField_a_of_type_JavaLangString.equals("materialName")) {
/*  562: 562 */              if ((!this.jdField_a_of_type_JavaUtilHashMap.containsKey(((AA)localObject2).jdField_b_of_type_JavaLangString)) && 
/*  563: 563 */                (this.jdField_a_of_type_ArrayOfZB == null))
/*  564:     */              {
/*  568: 568 */                this.jdField_a_of_type_ArrayOfZB = a(this.jdField_a_of_type_JavaLangString + this.jdField_b_of_type_JavaLangString + ".material");
/*  569:     */                
/*  570: 570 */                for (localObject7 : this.jdField_a_of_type_ArrayOfZB)
/*  571:     */                {
/*  573: 573 */                  this.jdField_a_of_type_JavaUtilHashMap.put(((zB)localObject7).a(), localObject7);
/*  574:     */                }
/*  575:     */              }
/*  576: 576 */              localzB = (zB)this.jdField_a_of_type_JavaUtilHashMap.get(((AA)localObject2).jdField_b_of_type_JavaLangString);
/*  577:     */            }
/*  578:     */            
/*  579:     */          }
/*  580:     */        }
/*  581: 581 */      } else if ((((zi)localObject4).jdField_a_of_type_JavaLangString.equals("userData")) && (((zi)localObject4).jdField_b_of_type_JavaLangString != null))
/*  582:     */      {
/*  584:     */        try
/*  585:     */        {
/*  587: 587 */          localObject2 = DocumentBuilderFactory.newInstance().newDocumentBuilder();
/*  588: 588 */          (
/*  589: 589 */            localObject5 = new InputSource()).setCharacterStream(new StringReader(((zi)localObject4).jdField_b_of_type_JavaLangString));
/*  590: 590 */          localObject2 = ((DocumentBuilder)localObject2).parse((InputSource)localObject5);
/*  591: 591 */          localObject1.a((Document)localObject2);
/*  592:     */          
/*  593: 593 */          if ((localObject4 = ((Document)localObject2).getElementsByTagName("Mass").item(0)) != null) {
/*  594: 594 */            float f = Float.parseFloat(((Node)localObject4).getTextContent());
/*  595: 595 */            localObject1.a(f);
/*  596:     */          }
/*  597:     */        } catch (SAXException paramzi) {
/*  598: 598 */          System.err.println("Exception while parsing userdata from " + localObject1.b());
/*  599: 599 */          paramzi.printStackTrace();
/*  600: 600 */        } catch (IOException localIOException) { 
/*  601:     */          
/*  604: 604 */            localIOException;
/*  605:     */        } catch (ParserConfigurationException localParserConfigurationException) {
/*  606: 602 */          
/*  607:     */          
/*  608: 604 */            localParserConfigurationException;
/*  609:     */        }
/*  610:     */      }
/*  611:     */    }
/*  612:     */    
/*  613:     */    zB localzB;
/*  614: 608 */    localObject1.a(localzB);
/*  615:     */    
/*  616: 610 */    return localObject1;
/*  617:     */  }
/*  618:     */  
/*  626:     */  private static void a(zi paramzi)
/*  627:     */  {
/*  628: 622 */    for (paramzi = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext(); paramzi.next()) {}
/*  629:     */  }
/*  630:     */  
/*  639:     */  @Deprecated
/*  640:     */  private static zh a(zi paramzi, int paramInt)
/*  641:     */  {
/*  642: 636 */    paramInt = new zh(paramInt);
/*  643: 637 */    int i = 0;
/*  644: 638 */    for (paramzi = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext();) {
/*  645:     */      Object localObject;
/*  646: 640 */      if ((localObject = (zi)paramzi.next()).jdField_a_of_type_JavaLangString.equals("keyframe"))
/*  647:     */      {
/*  648: 642 */        for (localObject = ((zi)localObject).jdField_a_of_type_JavaUtilLinkedList.iterator(); ((Iterator)localObject).hasNext();)
/*  649:     */        {
/*  650:     */          zi localzi;
/*  651: 645 */          if ((localzi = (zi)((Iterator)localObject).next()).jdField_a_of_type_JavaLangString.equals("translation"))
/*  652:     */          {
/*  653: 647 */            paramInt.b[i] = a(localzi);
/*  654:     */          }
/*  655: 649 */          if (localzi.jdField_a_of_type_JavaLangString.equals("rotation"))
/*  656:     */          {
/*  657: 651 */            paramInt.jdField_a_of_type_ArrayOfJavaxVecmathVector4f[i] = a(localzi);
/*  658:     */          }
/*  659:     */          
/*  660: 654 */          if (localzi.jdField_a_of_type_JavaLangString.equals("scale"))
/*  661:     */          {
/*  662: 656 */            paramInt.jdField_a_of_type_ArrayOfJavaxVecmathVector3f[i] = a(localzi);
/*  663:     */          }
/*  664:     */        }
/*  665: 659 */        i++;
/*  666:     */      }
/*  667:     */    }
/*  668: 662 */    return paramInt;
/*  669:     */  }
/*  670:     */  
/*  678:     */  private static zB[] a(String paramString)
/*  679:     */  {
/*  680: 674 */    paramString = new BufferedReader(new InputStreamReader(Ad.a.a(paramString)));
/*  681:     */    
/*  686: 680 */    StringBuffer localStringBuffer = new StringBuffer();
/*  687:     */    try {
/*  688: 682 */      while (paramString.ready())
/*  689: 683 */        localStringBuffer.append(paramString.readLine() + "\n");
/*  690:     */    } catch (IOException localIOException) {
/*  691: 685 */      
/*  692:     */      
/*  693: 687 */        localIOException;
/*  694:     */    }
/*  695:     */    
/*  696:     */    String[] arrayOfString1;
/*  697: 689 */    paramString = new zB[(arrayOfString1 = localStringBuffer.toString().trim().split("material")).length];
/*  698: 690 */    for (int i = 0; i < arrayOfString1.length; i++)
/*  699:     */    {
/*  700:     */      String str1;
/*  701:     */      String[] arrayOfString2;
/*  702: 694 */      if ((arrayOfString2 = (str1 = arrayOfString1[i]).split("\n")).length == 0) {
/*  703: 695 */        System.err.println("Material String: line length 0: " + str1);
/*  704:     */      }
/*  705: 697 */      paramString[i] = new zB();
/*  706: 698 */      paramString[i].a(arrayOfString2[0].trim());
/*  707:     */      
/*  709: 701 */      for (int j = 0; j < arrayOfString2.length; j++) {
/*  710:     */        String str2;
/*  711:     */        Object localObject;
/*  712: 704 */        if ((str2 = arrayOfString2[j]).contains("ambient")) {
/*  713: 705 */          localObject = a(str2);
/*  714: 706 */          paramString[i].a((float[])localObject);
/*  715:     */        }
/*  716:     */        
/*  717: 709 */        if (str2.contains("diffuse")) {
/*  718: 710 */          localObject = a(str2);
/*  719: 711 */          paramString[i].b((float[])localObject);
/*  720:     */        }
/*  721: 713 */        if (str2.contains("specular")) {
/*  722: 714 */          localObject = a(str2);
/*  723: 715 */          paramString[i].c((float[])localObject);
/*  724:     */        }
/*  725: 717 */        if (str2.contains("emissive")) {
/*  726: 718 */          a(str2);
/*  727:     */        }
/*  728:     */        
/*  729: 721 */        if (str2.contains("texture ")) {
/*  730: 722 */          localObject = str2.split(" ");
/*  731: 723 */          paramString[i].a(true);
/*  732:     */          
/*  733: 725 */          paramString[i].b(localObject[1].trim());
/*  734:     */        }
/*  735:     */      }
/*  736:     */    }
/*  737:     */    
/*  740: 732 */    return paramString;
/*  741:     */  }
/*  742:     */  
/*  747:     */  private static float[] a(String paramString)
/*  748:     */  {
/*  749: 741 */    paramString = paramString.split(" ");
/*  750:     */    float[] arrayOfFloat;
/*  751: 743 */    (arrayOfFloat = new float[4])[0] = Float.parseFloat(paramString[1]);
/*  752: 744 */    arrayOfFloat[1] = Float.parseFloat(paramString[2]);
/*  753: 745 */    arrayOfFloat[2] = Float.parseFloat(paramString[3]);
/*  754: 746 */    if (paramString.length > 4) {
/*  755: 747 */      arrayOfFloat[3] = Float.parseFloat(paramString[4]);
/*  756:     */    } else {
/*  757: 749 */      arrayOfFloat[3] = 1.0F;
/*  758:     */    }
/*  759: 751 */    return arrayOfFloat;
/*  760:     */  }
/*  761:     */  
/*  818:     */  private void a(zi paramzi, xM paramxM)
/*  819:     */  {
/*  820: 812 */    String str = "default";
/*  821: 813 */    int i = 1;
/*  822: 814 */    for (Object localObject1 = paramzi.jdField_a_of_type_JavaUtilVector.iterator(); ((Iterator)localObject1).hasNext();) {
/*  823: 815 */      if ((localObject2 = (AA)((Iterator)localObject1).next()).jdField_a_of_type_JavaLangString.equals("description"))
/*  824:     */      {
/*  825: 817 */        str = ((AA)localObject2).jdField_b_of_type_JavaLangString;
/*  826:     */      }
/*  827:     */      
/*  828: 820 */      if (((AA)localObject2).jdField_a_of_type_JavaLangString.equals("visibility")) {
/*  829: 821 */        if (((AA)localObject2).jdField_b_of_type_JavaLangString.equals("visible")) {
/*  830: 822 */          i = 1;
/*  831:     */        }
/*  832: 824 */        if (((AA)localObject2).jdField_b_of_type_JavaLangString.equals("hidden"))
/*  833:     */        {
/*  834: 826 */          i = 2;
/*  835:     */        }
/*  836: 828 */        if (((AA)localObject2).jdField_b_of_type_JavaLangString.equals("tree visible")) {
/*  837: 829 */          i = 4;
/*  838:     */        }
/*  839: 831 */        if (((AA)localObject2).jdField_b_of_type_JavaLangString.equals("tree hidden")) {
/*  840: 832 */          i = 8;
/*  841:     */        }
/*  842:     */      }
/*  843:     */    }
/*  844:     */    
/*  845: 837 */    localObject1 = null;
/*  846: 838 */    Object localObject2 = new Vector3f();
/*  847: 839 */    Vector4f localVector4f = new Vector4f();
/*  848: 840 */    Vector3f localVector3f = new Vector3f();
/*  849: 841 */    Vector localVector = null;
/*  850: 842 */    int j = 0;
/*  851: 843 */    for (Iterator localIterator = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); localIterator.hasNext();)
/*  852:     */    {
/*  853: 845 */      if ((localzi = (zi)localIterator.next()).jdField_a_of_type_JavaLangString.equals("position"))
/*  854:     */      {
/*  855: 847 */        localObject2 = a(localzi);
/*  856:     */      }
/*  857: 849 */      if (localzi.jdField_a_of_type_JavaLangString.equals("rotation"))
/*  858:     */      {
/*  859: 851 */        localVector4f = a(localzi);
/*  860:     */      }
/*  861: 853 */      if (localzi.jdField_a_of_type_JavaLangString.equals("scale"))
/*  862:     */      {
/*  863: 855 */        localVector3f = a(localzi);
/*  864:     */      }
/*  865: 857 */      if (localzi.jdField_a_of_type_JavaLangString.equals("animations"))
/*  866:     */      {
/*  867: 859 */        localVector = a(localzi);
/*  868:     */      }
/*  869: 861 */      if (localzi.jdField_a_of_type_JavaLangString.equals("entity"))
/*  870:     */      {
/*  871: 863 */        j = 1;
/*  872: 864 */        (
/*  873: 865 */          localObject1 = a(localzi)).g(i);
/*  874:     */      }
/*  875:     */    }
/*  876:     */    
/*  880:     */    zi localzi;
/*  881:     */    
/*  884: 876 */    if (j == 0)
/*  885:     */    {
/*  887: 879 */      (localObject1 = new ya()).c(str);
/*  888: 880 */      ((xM)localObject1).g(i);
/*  889:     */    }
/*  890:     */    
/*  891: 883 */    for (localIterator = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); localIterator.hasNext();) {
/*  892: 884 */      if ((localzi = (zi)localIterator.next()).jdField_a_of_type_JavaLangString.equals("node")) {
/*  893: 885 */        a(localzi, (xM)localObject1);
/*  894:     */      }
/*  895:     */    }
/*  896:     */    
/*  897: 889 */    paramxM.a((xM)localObject1);
/*  898: 890 */    if (localVector != null) {
/*  899: 891 */      ((xM)localObject1).a(localVector);
/*  900:     */      try {
/*  901: 893 */        ((xM)localObject1).b(((xM)localObject1).b());
/*  902:     */      } catch (AnimationNotFoundException localAnimationNotFoundException) {
/*  903: 895 */        
/*  904:     */        
/*  905: 897 */          localAnimationNotFoundException;
/*  906:     */      }
/*  907:     */      
/*  908: 898 */      xM.p();
/*  909:     */    }
/*  910: 900 */    ((xM)localObject1).b((Vector3f)localObject2);
/*  911: 901 */    ((xM)localObject1).b(localVector4f);
/*  912: 902 */    ((xM)localObject1).a(localVector4f);
/*  913: 903 */    ((xM)localObject1).e().set(localVector3f);
/*  914: 904 */    ((xM)localObject1).a(new Vector3f(localVector3f));
/*  915:     */  }
/*  916:     */  
/*  925:     */  private void b(zi paramzi, xM paramxM)
/*  926:     */  {
/*  927: 917 */    for (paramzi = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext();) {
/*  928:     */      zi localzi;
/*  929: 919 */      if ((localzi = (zi)paramzi.next()).jdField_a_of_type_JavaLangString.equals("node"))
/*  930:     */      {
/*  931: 921 */        a(localzi, paramxM);
/*  932:     */      }
/*  933:     */    }
/*  934:     */  }
/*  935:     */  
/*  946:     */  public final xW a(String paramString1, String paramString2)
/*  947:     */  {
/*  948: 938 */    this.jdField_a_of_type_JavaLangString = paramString1;
/*  949: 939 */    this.jdField_b_of_type_JavaLangString = paramString2;
/*  950: 940 */    xW localxW = new xW();
/*  951: 941 */    xM localxM = null; if (this.jdField_a_of_type_Zi == null) {
/*  952: 942 */      paramString1 = paramString1 + paramString2 + ".scene";
/*  953: 943 */      while (paramString1.contains("//")) {
/*  954: 944 */        paramString1 = paramString1.replaceAll("//", "/");
/*  955:     */      }
/*  956:     */      
/*  957: 947 */      a(paramString1);
/*  958:     */    }
/*  959:     */    
/*  960: 950 */    for (paramString1 = this.jdField_a_of_type_Zi.jdField_a_of_type_JavaUtilLinkedList.iterator(); paramString1.hasNext();) {
/*  961: 951 */      if ((paramString2 = (zi)paramString1.next()).jdField_a_of_type_JavaLangString.equals("environment")) {
/*  962: 952 */        a(paramString2);
/*  963:     */      }
/*  964: 954 */      if (paramString2.jdField_a_of_type_JavaLangString.equals("nodes")) {
/*  965: 955 */        b(paramString2, localxW);
/*  966:     */      }
/*  967:     */    }
/*  968:     */    
/*  971: 961 */    paramString1 = 0;
/*  972: 962 */    for (paramString2 = localxW.a().iterator(); paramString2.hasNext();) {
/*  973: 963 */      if (((localxM = (xM)paramString2.next()) instanceof xS)) {
/*  974: 964 */        paramString1 = (int)(paramString1 + ((xS)localxM).a());
/*  975:     */      }
/*  976:     */    }
/*  977: 967 */    localxW.a(paramString1);
/*  978:     */    
/*  980: 970 */    localxW.a(localxW.a(new Vector3f(), new Vector3f()));
/*  981:     */    
/*  984: 974 */    localxW.o();
/*  985:     */    
/*  986: 976 */    localxW.q();
/*  987:     */    
/*  988: 978 */    return localxW;
/*  989:     */  }
/*  990:     */  
/*  992:     */  private static void a(Mesh paramMesh, String paramString1, String paramString2)
/*  993:     */  {
/*  994: 984 */    Object localObject1 = paramString2;paramString2 = paramString1;System.err.println("[PARSER] parsing skeleton " + paramString2 + (String)localObject1);(localObject2 = new XMLOgreParser()).a(paramString2 + (String)localObject1 + ".xml");paramString2 = new ye();localObject1 = null;Object localObject3 = null;Object localObject4 = null; for (Object localObject2 = ((XMLOgreParser)localObject2).jdField_a_of_type_Zi.jdField_a_of_type_JavaUtilLinkedList.iterator(); ((Iterator)localObject2).hasNext();) { if ((localObject5 = (zi)((Iterator)localObject2).next()).jdField_a_of_type_JavaLangString.equals("bones")) localObject1 = localObject5; if (((zi)localObject5).jdField_a_of_type_JavaLangString.equals("bonehierarchy")) localObject3 = localObject5; if (((zi)localObject5).jdField_a_of_type_JavaLangString.equals("animations")) localObject4 = localObject5; } Object localObject5; b(paramString2, (zi)localObject1);a(paramString2, localObject3); if (localObject4 != null) { System.out.println("... parsing skeleton animation");paramString2.a(a(localObject4)); } paramString1 = paramString2;
/*  995: 985 */    paramString2 = new HashMap();
/*  996:     */    
/*  997: 987 */    for (localObject4 : paramMesh.a())
/*  998:     */    {
/* 1001: 990 */      if ((localObject5 = (Vector)paramString2.get(Integer.valueOf(localObject4.jdField_a_of_type_Int))) == null) {
/* 1002: 991 */        localObject5 = new Vector();
/* 1003: 992 */        paramString2.put(Integer.valueOf(localObject4.jdField_a_of_type_Int), localObject5);
/* 1004:     */      }
/* 1005:     */      
/* 1007: 996 */      ((Vector)localObject5).add(localObject4);
/* 1008:     */    }
/* 1009:     */    
/* 1012:1001 */    (localObject1 = new yj(paramMesh.a())).a(paramString2, paramString1);
/* 1013:1002 */    paramMesh.a(null);
/* 1014:     */    
/* 1015:1004 */    paramMesh.a(new yf(paramString1, (yj)localObject1));
/* 1016:     */  }
/* 1017:     */  
/* 1063:     */  private static String a(zi paramzi)
/* 1064:     */  {
/* 1065:1054 */    for (paramzi = paramzi.jdField_a_of_type_JavaUtilVector.iterator(); paramzi.hasNext();) { AA localAA;
/* 1066:1055 */      if ((localAA = (AA)paramzi.next()).jdField_a_of_type_JavaLangString.equals("name")) {
/* 1067:1056 */        return localAA.jdField_b_of_type_JavaLangString;
/* 1068:     */      }
/* 1069:     */    }
/* 1070:1059 */    return null;
/* 1071:     */  }
/* 1072:     */  
/* 1080:     */  private static xS b(zi paramzi)
/* 1081:     */  {
/* 1082:1071 */    Object localObject3 = null;
/* 1083:     */    
/* 1088:1077 */    for (Iterator localIterator1 = paramzi.jdField_a_of_type_JavaUtilVector.iterator(); localIterator1.hasNext();) {
/* 1089:1078 */      localObject5 = (localObject4 = (AA)localIterator1.next()).jdField_b_of_type_JavaLangString;
/* 1090:1079 */      ((AA)localObject4).jdField_a_of_type_JavaLangString.equals("material");
/* 1091:1080 */      if (((AA)localObject4).jdField_a_of_type_JavaLangString.equals("usesharedvertices"))
/* 1092:     */      {
/* 1094:1083 */        Boolean.parseBoolean((String)localObject5);
/* 1095:     */      }
/* 1096:1085 */      if (((AA)localObject4).jdField_a_of_type_JavaLangString.equals("use32bitindexes")) {
/* 1097:1086 */        Boolean.parseBoolean((String)localObject5);
/* 1098:     */      }
/* 1099:1088 */      if (((AA)localObject4).jdField_a_of_type_JavaLangString.equals("operationtype"))
/* 1100:1089 */        localObject3 = localObject5; }
/* 1101:     */    Object localObject4;
/* 1102:     */    Object localObject5;
/* 1103:     */    Object localObject2;
/* 1104:1093 */    if (((String)localObject3).equals("line_list")) {
/* 1105:1094 */      localObject2 = new xU();
/* 1106:     */    } else {
/* 1107:1096 */      localObject2 = new Mesh();
/* 1108:     */    }
/* 1109:     */    
/* 1110:1099 */    for (localIterator1 = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); localIterator1.hasNext();)
/* 1111:     */    {
/* 1112:     */      int i;
/* 1113:     */      
/* 1115:1104 */      if (((localObject4 = (zi)localIterator1.next()).jdField_a_of_type_JavaLangString.equals("faces")) && 
/* 1116:1105 */        ((localObject2 instanceof Mesh)))
/* 1117:     */      {
/* 1119:1108 */        localObject5 = (Mesh)localObject2;
/* 1120:1109 */        i = Integer.parseInt(((AA)((zi)localObject4).jdField_a_of_type_JavaUtilVector.get(0)).jdField_b_of_type_JavaLangString);
/* 1121:     */        
/* 1123:1112 */        ((Mesh)localObject5).b(i);
/* 1124:     */        
/* 1126:1115 */        ((Mesh)localObject5).a(GlUtil.a(i * 3 << 2, 0).asIntBuffer());
/* 1127:1116 */        ((Mesh)localObject5).b = GlUtil.a(i * 3 << 2, 1).asIntBuffer();
/* 1128:1117 */        ((Mesh)localObject5).jdField_c_of_type_JavaNioIntBuffer = GlUtil.a(i * 3 << 2, 2).asIntBuffer();
/* 1129:     */        
/* 1131:1120 */        for (localObject3 = ((zi)localObject4).jdField_a_of_type_JavaUtilLinkedList.iterator(); ((Iterator)localObject3).hasNext();) { paramzi = (zi)((Iterator)localObject3).next();
/* 1132:     */          
/* 1134:1123 */          ((Mesh)localObject5).j();
/* 1135:     */          
/* 1136:1125 */          for (i = 0; i < 3; i++)
/* 1137:     */          {
/* 1138:1127 */            ((Mesh)localObject5).a().put(Integer.parseInt(((AA)paramzi.jdField_a_of_type_JavaUtilVector.get(i)).jdField_b_of_type_JavaLangString));
/* 1139:     */            
/* 1141:1130 */            ((Mesh)localObject5).jdField_c_of_type_JavaNioIntBuffer.put(Integer.parseInt(((AA)paramzi.jdField_a_of_type_JavaUtilVector.get(i)).jdField_b_of_type_JavaLangString));
/* 1142:     */            
/* 1144:1133 */            ((Mesh)localObject5).b.put(Integer.parseInt(((AA)paramzi.jdField_a_of_type_JavaUtilVector.get(i)).jdField_b_of_type_JavaLangString));
/* 1145:     */          }
/* 1146:     */        }
/* 1147:     */      }
/* 1148:     */      
/* 1150:     */      int j;
/* 1151:     */      
/* 1152:     */      Object localObject1;
/* 1153:     */      
/* 1154:1143 */      if (((zi)localObject4).jdField_a_of_type_JavaLangString.equals("geometry")) {
/* 1155:1144 */        j = 0;
/* 1156:     */        
/* 1157:1146 */        for (paramzi = ((zi)localObject4).jdField_a_of_type_JavaUtilVector.iterator(); paramzi.hasNext();) {
/* 1158:1147 */          localObject3 = (localObject1 = (AA)paramzi.next()).jdField_b_of_type_JavaLangString;
/* 1159:1148 */          if (((AA)localObject1).jdField_a_of_type_JavaLangString.equals("vertexcount")) {
/* 1160:1149 */            j = Integer.parseInt((String)localObject3);
/* 1161:1150 */            ((xS)localObject2).a(j);
/* 1162:1151 */            if ((localObject2 instanceof Mesh)) {
/* 1163:1152 */              ((Mesh)localObject2).c(j);
/* 1164:     */            }
/* 1165:     */          }
/* 1166:     */        }
/* 1167:1156 */        for (paramzi = ((zi)localObject4).jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext();)
/* 1168:1157 */          if ((localObject1 = (zi)paramzi.next()).jdField_a_of_type_JavaLangString.equals("vertexbuffer")) {
/* 1169:1158 */            int k = j;Object localObject6 = localObject1;localObject3 = localObject2;boolean bool1 = false;boolean bool2 = false;int m = 0; for (Iterator localIterator2 = ((zi)localObject6).jdField_a_of_type_JavaUtilVector.iterator(); localIterator2.hasNext();) { AA localAA; String str = (localAA = (AA)localIterator2.next()).jdField_b_of_type_JavaLangString; if (localAA.jdField_a_of_type_JavaLangString.equals("positions")) bool1 = Boolean.parseBoolean(str); if (localAA.jdField_a_of_type_JavaLangString.equals("normals")) bool2 = Boolean.parseBoolean(str); if (localAA.jdField_a_of_type_JavaLangString.equals("texture_coords")) m = Integer.parseInt(str); if (localAA.jdField_a_of_type_JavaLangString.equals("texture_coord_dimension_0")) Integer.parseInt(str); } if (bool1) ((xS)localObject3).b = GlUtil.a(k * 3 << 2, 3).asFloatBuffer(); if (bool2) ((Mesh)localObject3).d = GlUtil.a(k * 3 << 2, 4).asFloatBuffer(); if (m > 0) ((Mesh)localObject3).jdField_c_of_type_JavaNioFloatBuffer = GlUtil.a(k * 3 << 2, 5).asFloatBuffer(); int n = 0;float f2 = -2.147484E+009F;float f1 = 2.147484E+009F;float f3 = -2.147484E+009F;float f4 = 2.147484E+009F;float f5 = -2.147484E+009F;float f6 = 2.147484E+009F; for (localObject6 = ((zi)localObject6).jdField_a_of_type_JavaUtilLinkedList.iterator(); ((Iterator)localObject6).hasNext();) { Vector3f localVector3f; for (localObject7 = ((zi)((Iterator)localObject6).next()).jdField_a_of_type_JavaUtilLinkedList.iterator(); ((Iterator)localObject7).hasNext(); ((Mesh)localObject3).jdField_c_of_type_JavaNioFloatBuffer.put(localVector3f.y)) { zi localzi = (zi)((Iterator)localObject7).next(); if ((bool1) && (localzi.jdField_a_of_type_JavaLangString.equals("position"))) { localVector3f = a(localzi);((xS)localObject3).b.put(localVector3f.x);((xS)localObject3).b.put(localVector3f.y);((xS)localObject3).b.put(localVector3f.z);f2 = localVector3f.x > f2 ? localVector3f.x : f2;f1 = localVector3f.x < f1 ? localVector3f.x : f1;f3 = localVector3f.y > f3 ? localVector3f.y : f3;f4 = localVector3f.y < f4 ? localVector3f.y : f4;f5 = localVector3f.z > f5 ? localVector3f.z : f5;f6 = localVector3f.z < f6 ? localVector3f.z : f6;n++; } if ((bool2) && (localzi.jdField_a_of_type_JavaLangString.equals("normal"))) { localVector3f = a(localzi);((Mesh)localObject3).d.put(localVector3f.x);((Mesh)localObject3).d.put(localVector3f.y);((Mesh)localObject3).d.put(localVector3f.z); } if ((m > 0) && (localzi.jdField_a_of_type_JavaLangString.equals("texcoord"))) { localVector3f = a(localzi);((Mesh)localObject3).jdField_c_of_type_JavaNioFloatBuffer.put(localVector3f.x); } } } Object localObject7; if (bool1) { localObject6 = new Vector3f(f1, f4, f6);localObject7 = new Vector3f(f2, f3, f5); if ((!jdField_a_of_type_Boolean) && (n != k)) throw new AssertionError(); ((xS)localObject3).a(new xO((Vector3f)localObject6, (Vector3f)localObject7));((xS)localObject3).o();
/* 1170:     */            }
/* 1171:     */          }
/* 1172:     */      }
/* 1173:1162 */      if (((zi)localObject4).jdField_a_of_type_JavaLangString.equals("boneassignments")) {
/* 1174:1163 */        a((Mesh)localObject2, (zi)localObject4);
/* 1175:     */      }
/* 1176:     */    }
/* 1177:1166 */    ((xS)localObject2).b.rewind();
/* 1178:1167 */    ((xS)localObject2).a().rewind();
/* 1179:1168 */    if ((localObject2 instanceof Mesh)) {
/* 1180:1169 */      ((Mesh)localObject2).jdField_c_of_type_JavaNioFloatBuffer.rewind();
/* 1181:1170 */      ((Mesh)localObject2).d.rewind();
/* 1182:1171 */      ((Mesh)localObject2).b.rewind();
/* 1183:1172 */      ((Mesh)localObject2).jdField_c_of_type_JavaNioIntBuffer.rewind();
/* 1184:     */    }
/* 1185:1174 */    return localObject2;
/* 1186:     */  }
/* 1187:     */  
/* 1194:     */  private static Vector b(zi paramzi)
/* 1195:     */  {
/* 1196:1185 */    Vector localVector = new Vector();
/* 1197:1186 */    for (paramzi = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext();)
/* 1198:     */    {
/* 1199:1188 */      xS localxS = b((zi)paramzi.next());
/* 1200:1189 */      localVector.add(localxS);
/* 1201:     */    }
/* 1202:1191 */    return localVector;
/* 1203:     */  }
/* 1204:     */  
/* 1210:     */  private static void a(zi paramzi, Vector paramVector)
/* 1211:     */  {
/* 1212:1201 */    for (Object localObject : paramzi.jdField_a_of_type_JavaUtilLinkedList) {
/* 1213:1202 */      int i = 0;
/* 1214:1203 */      String str = "";
/* 1215:1204 */      for (localObject = ((zi)localObject).jdField_a_of_type_JavaUtilVector.iterator(); ((Iterator)localObject).hasNext();) {
/* 1216:     */        AA localAA;
/* 1217:1206 */        if ((localAA = (AA)((Iterator)localObject).next()).jdField_a_of_type_JavaLangString.equals("name")) {
/* 1218:1207 */          str = localAA.jdField_b_of_type_JavaLangString;
/* 1219:     */        }
/* 1220:     */        
/* 1221:1210 */        if (localAA.jdField_a_of_type_JavaLangString.equals("index")) {
/* 1222:1211 */          i = Integer.parseInt(localAA.jdField_b_of_type_JavaLangString);
/* 1223:     */        }
/* 1224:     */      }
/* 1225:     */      
/* 1226:1215 */      ((xS)paramVector.get(i)).c(str);
/* 1227:     */    }
/* 1228:     */  }
/* 1229:     */  
/* 1452:     */  private static void a(zi paramzi, wP paramwP)
/* 1453:     */  {
/* 1454:1443 */    for (paramzi = paramzi.jdField_a_of_type_JavaUtilLinkedList.iterator(); paramzi.hasNext();)
/* 1455:     */    {
/* 1456:     */      Object localObject1;
/* 1457:     */      
/* 1458:1447 */      if ((localObject1 = (zi)paramzi.next()).jdField_a_of_type_JavaLangString.equals("keyframe")) {
/* 1459:1448 */        float f2 = -1.0F;
/* 1460:1449 */        for (Object localObject3 = ((zi)localObject1).jdField_a_of_type_JavaUtilVector.iterator(); ((Iterator)localObject3).hasNext();) {
/* 1461:1450 */          if ((localObject4 = (AA)((Iterator)localObject3).next()).jdField_a_of_type_JavaLangString.equals("time")) {
/* 1462:1451 */            f2 = Float.parseFloat(((AA)localObject4).jdField_b_of_type_JavaLangString);
/* 1463:     */          }
/* 1464:     */        }
/* 1465:     */        
/* 1467:1456 */        localObject3 = null;
/* 1468:1457 */        Object localObject4 = null;
/* 1469:1458 */        Vector3f localVector3f = null;
/* 1470:1459 */        for (localObject1 = ((zi)localObject1).jdField_a_of_type_JavaUtilLinkedList.iterator(); ((Iterator)localObject1).hasNext();)
/* 1471:     */        {
/* 1473:1462 */          if ((localObject5 = (zi)((Iterator)localObject1).next()).jdField_a_of_type_JavaLangString.equals("translate"))
/* 1474:     */          {
/* 1475:1464 */            localObject3 = a((zi)localObject5); }
/* 1476:     */          Iterator localIterator;
/* 1477:     */          Object localObject6;
/* 1478:1467 */          Object localObject7; if (((zi)localObject5).jdField_a_of_type_JavaLangString.equals("rotate"))
/* 1479:     */          {
/* 1480:1469 */            localObject4 = new AxisAngle4f();
/* 1481:1470 */            for (localIterator = ((zi)localObject5).jdField_a_of_type_JavaUtilVector.iterator(); localIterator.hasNext();) {
/* 1482:1471 */              localObject7 = (localObject6 = (AA)localIterator.next()).jdField_b_of_type_JavaLangString;
/* 1483:1472 */              if (((AA)localObject6).jdField_a_of_type_JavaLangString.equals("angle")) {
/* 1484:1473 */                ((AxisAngle4f)localObject4).angle = Float.parseFloat((String)localObject7);
/* 1485:     */              }
/* 1486:     */            }
/* 1487:1476 */            for (localIterator = ((zi)localObject5).jdField_a_of_type_JavaUtilLinkedList.iterator(); localIterator.hasNext();) {
/* 1488:1477 */              if ((localObject6 = (zi)localIterator.next()).jdField_a_of_type_JavaLangString.equals("axis"))
/* 1489:     */              {
/* 1490:1479 */                (localObject7 = a((zi)localObject6)).normalize();
/* 1491:1480 */                ((AxisAngle4f)localObject4).x = ((Vector3f)localObject7).x;
/* 1492:1481 */                ((AxisAngle4f)localObject4).y = ((Vector3f)localObject7).y;
/* 1493:1482 */                ((AxisAngle4f)localObject4).z = ((Vector3f)localObject7).z;
/* 1494:     */              }
/* 1495:     */            }
/* 1496:     */          }
/* 1497:1486 */          if (((zi)localObject5).jdField_a_of_type_JavaLangString.equals("scale"))
/* 1498:     */          {
/* 1499:1488 */            localVector3f = a((zi)localObject5);
/* 1500:     */          }
/* 1501:     */        }
/* 1502:1491 */        if ((!jdField_a_of_type_Boolean) && (f2 < 0.0F)) { throw new AssertionError();
/* 1503:     */        }
/* 1504:1493 */        if ((!jdField_a_of_type_Boolean) && (localObject3 == null)) throw new AssertionError();
/* 1505:1494 */        if ((!jdField_a_of_type_Boolean) && (localObject4 == null)) { throw new AssertionError();
/* 1506:     */        }
/* 1507:1496 */        Object localObject5 = new Quat4f();localObject4 = new Vector3f(((AxisAngle4f)localObject4).x, ((AxisAngle4f)localObject4).y, ((AxisAngle4f)localObject4).z);float f1 = ((AxisAngle4f)localObject4).angle;(localObject4 = new Vector3f((Vector3f)localObject4)).normalize();d.a(f1, (Vector3f)localObject4, (Quat4f)localObject5);Object localObject2 = localObject5;
/* 1508:1497 */        paramwP.a(f2, (Vector3f)localObject3, localObject2, localVector3f);
/* 1509:     */      }
/* 1510:     */    }
/* 1511:     */  }
/* 1512:     */  
/* 1518:     */  private static Vector3f a(zi paramzi)
/* 1519:     */  {
/* 1520:1509 */    Vector3f localVector3f = new Vector3f();
/* 1521:1510 */    AA localAA = (AA)paramzi.jdField_a_of_type_JavaUtilVector.get(0);
/* 1522:1511 */    localVector3f.x = Float.parseFloat(localAA.jdField_b_of_type_JavaLangString);
/* 1523:1512 */    localAA = (AA)paramzi.jdField_a_of_type_JavaUtilVector.get(1);
/* 1524:1513 */    localVector3f.y = Float.parseFloat(localAA.jdField_b_of_type_JavaLangString);
/* 1525:1514 */    if (paramzi.jdField_a_of_type_JavaUtilVector.size() > 2) {
/* 1526:1515 */      localAA = (AA)paramzi.jdField_a_of_type_JavaUtilVector.get(2);
/* 1527:1516 */      localVector3f.z = Float.parseFloat(localAA.jdField_b_of_type_JavaLangString);
/* 1528:     */    } else {
/* 1529:1518 */      localVector3f.z = 1.0F;
/* 1530:     */    }
/* 1531:1520 */    return localVector3f;
/* 1532:     */  }
/* 1533:     */  
/* 1539:     */  private static Vector4f a(zi paramzi)
/* 1540:     */  {
/* 1541:1530 */    Vector4f localVector4f = new Vector4f();
/* 1542:1531 */    AA localAA = (AA)paramzi.jdField_a_of_type_JavaUtilVector.get(0);
/* 1543:1532 */    localVector4f.x = Float.parseFloat(localAA.jdField_b_of_type_JavaLangString);
/* 1544:1533 */    localAA = (AA)paramzi.jdField_a_of_type_JavaUtilVector.get(1);
/* 1545:1534 */    localVector4f.y = Float.parseFloat(localAA.jdField_b_of_type_JavaLangString);
/* 1546:1535 */    localAA = (AA)paramzi.jdField_a_of_type_JavaUtilVector.get(2);
/* 1547:1536 */    localVector4f.z = Float.parseFloat(localAA.jdField_b_of_type_JavaLangString);
/* 1548:1537 */    localAA = (AA)paramzi.jdField_a_of_type_JavaUtilVector.get(3);
/* 1549:1538 */    localVector4f.w = Float.parseFloat(localAA.jdField_b_of_type_JavaLangString);
/* 1550:1539 */    return localVector4f;
/* 1551:     */  }
/* 1552:     */  
/* 1661:     */  private void a(String paramString)
/* 1662:     */  {
/* 1663:     */    try
/* 1664:     */    {
/* 1665:1654 */      SAXParserFactory.newInstance().newSAXParser().parse(Ad.a.a(paramString), this); return;
/* 1666:     */    }
/* 1667:     */    catch (ParserConfigurationException localParserConfigurationException) {
/* 1668:1657 */      
/* 1669:     */      
/* 1674:1663 */        localParserConfigurationException.printStackTrace(); return;
/* 1675:     */    } catch (SAXException localSAXException) {
/* 1676:1659 */      
/* 1677:     */      
/* 1680:1663 */        localSAXException.printStackTrace(); return;
/* 1681:     */    } catch (IOException localIOException) {
/* 1682:1661 */      
/* 1683:     */      
/* 1684:1663 */        localIOException;
/* 1685:     */    }
/* 1686:     */  }
/* 1687:     */  
/* 1743:     */  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
/* 1744:     */  {
/* 1745:1722 */    (paramString1 = "".equals(paramString2) ? paramString3 : paramString2).contains("userData");
/* 1746:     */    
/* 1749:1726 */    if (this.jdField_a_of_type_Zi == null) {
/* 1750:1727 */      this.jdField_a_of_type_Zi = new zi();
/* 1751:1728 */      this.jdField_a_of_type_Zi.jdField_a_of_type_JavaLangString = paramString1;
/* 1752:1729 */      this.jdField_b_of_type_Zi = this.jdField_a_of_type_Zi;return;
/* 1753:     */    }
/* 1754:     */    
/* 1756:1733 */    (paramString2 = new zi()).jdField_a_of_type_JavaLangString = paramString1;
/* 1757:     */    
/* 1758:1735 */    if (paramAttributes != null) {
/* 1759:1736 */      for (paramString1 = 0; paramString1 < paramAttributes.getLength(); paramString1++) {
/* 1760:1737 */        paramString3 = paramAttributes.getLocalName(paramString1);
/* 1761:1738 */        if ("".equals(paramString3)) {
/* 1762:1739 */          paramString3 = paramAttributes.getQName(paramString1);
/* 1763:     */        }
/* 1764:     */        
/* 1765:1742 */        paramString2.jdField_a_of_type_JavaUtilVector.add(new AA(paramString3, paramAttributes.getValue(paramString1)));
/* 1766:     */      }
/* 1767:     */    }
/* 1768:     */    
/* 1770:1747 */    this.jdField_b_of_type_Zi.jdField_a_of_type_JavaUtilLinkedList.add(paramString2);
/* 1771:     */    
/* 1772:1749 */    paramString2.jdField_a_of_type_Zi = this.jdField_b_of_type_Zi;
/* 1773:1750 */    if (!this.jdField_b_of_type_Zi.jdField_a_of_type_Boolean)
/* 1774:     */    {
/* 1777:1754 */      this.jdField_b_of_type_Zi = paramString2;
/* 1778:     */    }
/* 1779:     */  }
/* 1780:     */  
/* 1781:     */  public void endDocument() {}
/* 1782:     */  
/* 1783:     */  public void startDocument() {}
/* 1784:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.graphicsengine.meshimporter.XMLOgreParser
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */