/*    1:     */package org.schema.game.client.view;
/*    2:     */
/*    3:     */import cY;
/*    4:     */import com.bulletphysics.linearmath.Transform;
/*    5:     */import ct;
/*    6:     */import dG;
/*    7:     */import dH;
/*    8:     */import dc;
/*    9:     */import de;
/*   10:     */import df;
/*   11:     */import dj;
/*   12:     */import dm;
/*   13:     */import do;
/*   14:     */import eI;
/*   15:     */import eJ;
/*   16:     */import eu;
/*   17:     */import iO;
/*   18:     */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   19:     */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*   20:     */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   21:     */import it.unimi.dsi.fastutil.objects.ObjectHeapPriorityQueue;
/*   22:     */import jL;
/*   23:     */import java.io.PrintStream;
/*   24:     */import java.util.ArrayList;
/*   25:     */import java.util.HashSet;
/*   26:     */import java.util.Iterator;
/*   27:     */import javax.vecmath.Vector3f;
/*   28:     */import mF;
/*   29:     */import mr;
/*   30:     */import org.lwjgl.BufferUtils;
/*   31:     */import org.lwjgl.input.Keyboard;
/*   32:     */import org.lwjgl.opengl.GL11;
/*   33:     */import org.lwjgl.opengl.GL13;
/*   34:     */import org.lwjgl.opengl.GL15;
/*   35:     */import org.lwjgl.util.vector.Matrix4f;
/*   36:     */import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*   37:     */import org.schema.game.client.view.cubes.CubeOptOptMesh;
/*   38:     */import org.schema.game.common.controller.SegmentBufferManager;
/*   39:     */import org.schema.game.common.controller.SegmentController;
/*   40:     */import org.schema.game.common.data.element.BeamHandler.BeamState;
/*   41:     */import org.schema.schine.graphicsengine.camera.Camera;
/*   42:     */import org.schema.schine.graphicsengine.core.GlUtil;
/*   43:     */import org.schema.schine.network.objects.NetworkEntity;
/*   44:     */import org.schema.schine.network.objects.remote.RemoteBoolean;
/*   45:     */import q;
/*   46:     */import xd;
/*   47:     */import xe;
/*   48:     */import xg;
/*   49:     */import xq;
/*   50:     */import xu;
/*   51:     */import zJ;
/*   52:     */import zj;
/*   53:     */import zk;
/*   54:     */
/*  699:     */public class SegmentDrawer
/*  700:     */  implements xg
/*  701:     */{
/*  702:     */  private HashSet jdField_a_of_type_JavaUtilHashSet;
/*  703:     */  private HashSet jdField_b_of_type_JavaUtilHashSet;
/*  704:     */  
/*  705:     */  public static void main(String... paramVarArgs)
/*  706:     */  {
/*  707: 707 */    for (paramVarArgs = 0; paramVarArgs < 99; 
/*  708:     */        
/*  710: 710 */        paramVarArgs++) {
/*  711: 711 */      long l1 = System.nanoTime();
/*  712:     */      
/*  713: 713 */      long l2 = System.currentTimeMillis();
/*  714: 714 */      Thread.sleep(12L);
/*  715: 715 */      l2 = System.currentTimeMillis() - l2;
/*  716: 716 */      System.out.println("millis: " + l2);
/*  717:     */      
/*  718: 718 */      long l3 = (l1 = System.nanoTime() - l1) / 1000000L;
/*  719: 719 */      System.out.println("millis from nanos: " + l3 + ", nanos: " + l1 + " 1000000");
/*  720:     */    }
/*  721:     */  }
/*  722:     */  
/*  732: 732 */  private HashSet jdField_c_of_type_JavaUtilHashSet = new HashSet();
/*  733:     */  
/*  734:     */  public static int a;
/*  735:     */  
/*  736:     */  public int b;
/*  737:     */  
/*  738:     */  public int c;
/*  739:     */  
/*  740:     */  public static boolean a;
/*  741:     */  
/*  742:     */  private final ArrayList jdField_a_of_type_JavaUtilArrayList;
/*  743:     */  
/*  744:     */  public mr[] a;
/*  745:     */  private mr[] jdField_e_of_type_ArrayOfMr;
/*  746:     */  public mr[] b;
/*  747:     */  public mr[] c;
/*  748:     */  public mr[] d;
/*  749:     */  private int jdField_d_of_type_Int;
/*  750:     */  public ObjectArrayList a;
/*  751: 751 */  private boolean jdField_c_of_type_Boolean = true;
/*  752:     */  
/*  757:     */  public dc a;
/*  758:     */  
/*  763:     */  private ct jdField_a_of_type_Ct;
/*  764:     */  
/*  768:     */  private final de jdField_a_of_type_De;
/*  769:     */  
/*  773:     */  private final df jdField_a_of_type_Df;
/*  774:     */  
/*  778:     */  public static dH a;
/*  779:     */  
/*  783: 783 */  private HashSet jdField_d_of_type_JavaUtilHashSet = new HashSet();
/*  784:     */  private SegmentController jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/*  785:     */  private long jdField_a_of_type_Long;
/*  786:     */  private boolean jdField_d_of_type_Boolean;
/*  787:     */  private Vector3f jdField_a_of_type_JavaxVecmathVector3f;
/*  788:     */  private Vector3f jdField_b_of_type_JavaxVecmathVector3f;
/*  789:     */  private final ArrayList jdField_b_of_type_JavaUtilArrayList;
/*  790:     */  private ArrayList jdField_c_of_type_JavaUtilArrayList;
/*  791:     */  private int jdField_e_of_type_Int;
/*  792:     */  private Matrix4f jdField_a_of_type_OrgLwjglUtilVectorMatrix4f;
/*  793:     */  private q jdField_a_of_type_Q;
/*  794:     */  private zJ jdField_a_of_type_ZJ;
/*  795:     */  private boolean jdField_e_of_type_Boolean;
/*  796:     */  private long jdField_b_of_type_Long;
/*  797:     */  private static boolean f;
/*  798:     */  private final q jdField_b_of_type_Q;
/*  799:     */  private final q jdField_c_of_type_Q;
/*  800:     */  private static Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform;
/*  801:     */  public static boolean b;
/*  802:     */  
/*  803:     */  public SegmentDrawer(ct paramct)
/*  804:     */  {
/*  805: 734 */    this.jdField_b_of_type_Int = 1;
/*  806:     */    
/*  820: 749 */    this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList = new ObjectArrayList(512);
/*  821:     */    
/*  823: 752 */    this.jdField_a_of_type_Dc = new dc((byte)0);
/*  824:     */    
/*  858: 787 */    new Vector3f();
/*  859:     */    
/*  864: 793 */    this.jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*  865: 794 */    this.jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*  866:     */    
/*  868: 797 */    new Transform();
/*  869:     */    
/*  871: 800 */    new Transform();
/*  872:     */    
/*  873: 802 */    new Vector3f();
/*  874: 803 */    new Vector3f();
/*  875: 804 */    this.jdField_b_of_type_JavaUtilArrayList = new ArrayList();
/*  876: 805 */    this.jdField_c_of_type_JavaUtilArrayList = new ArrayList();
/*  877:     */    
/*  878: 807 */    this.jdField_e_of_type_Int = 0;
/*  879:     */    
/*  880: 809 */    this.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f = new Matrix4f();
/*  881: 810 */    BufferUtils.createFloatBuffer(16);
/*  882:     */    
/*  883: 812 */    this.jdField_a_of_type_Q = new q(-1, 0, 0);
/*  884: 813 */    this.jdField_a_of_type_ZJ = new zJ(8.0F);
/*  885:     */    
/*  963: 892 */    this.jdField_b_of_type_Q = new q();
/*  964:     */    
/*  965: 894 */    this.jdField_c_of_type_Q = new q();
/*  966:     */    
/* 1495:1424 */    new Matrix4f();
/* 1496:1425 */    new Matrix4f();this.jdField_a_of_type_Ct = paramct;jdField_a_of_type_DH = new dH();this.jdField_a_of_type_ArrayOfMr = new mr[jdField_a_of_type_DH.jdField_a_of_type_Int];this.jdField_e_of_type_ArrayOfMr = new mr[jdField_a_of_type_DH.jdField_a_of_type_Int];this.jdField_b_of_type_ArrayOfMr = new mr[jdField_a_of_type_DH.jdField_a_of_type_Int];this.jdField_c_of_type_ArrayOfMr = new mr[jdField_a_of_type_DH.jdField_a_of_type_Int];this.jdField_d_of_type_ArrayOfMr = new mr[jdField_a_of_type_DH.jdField_a_of_type_Int];this.jdField_a_of_type_De = new de(this);this.jdField_a_of_type_Df = new df(this);this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/* 1497:     */  }
/* 1498:     */  
/* 1500:     */  private void g()
/* 1501:     */  {
/* 1502: 850 */    synchronized (this.jdField_d_of_type_JavaUtilHashSet) {
/* 1503: 851 */      for (int i = 0; i < this.jdField_d_of_type_Int; i++) {
/* 1504: 852 */        if ((this.jdField_c_of_type_ArrayOfMr[i] != null) && (this.jdField_c_of_type_ArrayOfMr[i].a() < this.jdField_b_of_type_Int)) {
/* 1505: 853 */          this.jdField_c_of_type_ArrayOfMr[i].b(false);
/* 1506: 854 */          this.jdField_d_of_type_JavaUtilHashSet.add(this.jdField_c_of_type_ArrayOfMr[i]);
/* 1507:     */        }
/* 1508:     */      }
/* 1509:     */      
/* 1512: 860 */      Object localObject3 = null; synchronized (this.jdField_b_of_type_JavaUtilArrayList) {
/* 1513: 861 */        this.jdField_d_of_type_JavaUtilHashSet.addAll(this.jdField_b_of_type_JavaUtilArrayList);
/* 1514:     */        
/* 1515: 863 */        this.jdField_b_of_type_JavaUtilArrayList.clear();
/* 1516:     */      }
/* 1517: 865 */      for (??? = this.jdField_d_of_type_JavaUtilHashSet.iterator(); ((Iterator)???).hasNext();) { mr localmr;
/* 1518: 866 */        if (!(localmr = (mr)((Iterator)???).next()).c()) {
/* 1519: 867 */          localmr.c();
/* 1520: 868 */          localmr.b();
/* 1521: 869 */          localmr.b(false);
/* 1522: 870 */          this.jdField_c_of_type_JavaUtilArrayList.add(localmr);
/* 1523:     */        }
/* 1524:     */      }
/* 1525:     */      
/* 1528: 876 */      this.jdField_c_of_type_JavaUtilArrayList.isEmpty();
/* 1529:     */      
/* 1531: 879 */      if ((this.jdField_c_of_type_JavaUtilArrayList.isEmpty()) && (!this.jdField_d_of_type_JavaUtilHashSet.isEmpty())) {
/* 1532: 880 */        System.err.println("not Disposed LEFT: " + this.jdField_c_of_type_JavaUtilArrayList.size() + "/" + this.jdField_d_of_type_JavaUtilHashSet.size());
/* 1533:     */      }
/* 1534: 882 */      this.jdField_d_of_type_JavaUtilHashSet.removeAll(this.jdField_c_of_type_JavaUtilArrayList);
/* 1535: 883 */      this.jdField_c_of_type_JavaUtilArrayList.clear();
/* 1536:     */    }
/* 1537: 885 */    synchronized (this.jdField_a_of_type_Df.jdField_a_of_type_JavaLangObject) {
/* 1538: 886 */      this.jdField_a_of_type_Df.jdField_a_of_type_JavaLangObject.notify();
/* 1539:     */    }
/* 1540: 888 */    jdField_a_of_type_DH.a(this.jdField_b_of_type_Int);
/* 1541:     */  }
/* 1542:     */  
/* 1584:     */  public final void a()
/* 1585:     */  {
/* 1586: 934 */    jdField_a_of_type_DH.b();
/* 1587:     */  }
/* 1588:     */  
/* 1592:     */  public final void d()
/* 1593:     */  {
/* 1594: 942 */    synchronized (this.jdField_a_of_type_JavaUtilArrayList) {
/* 1595: 943 */      this.jdField_a_of_type_JavaUtilArrayList.clear();
/* 1596: 944 */      return;
/* 1597:     */    }
/* 1598:     */  }
/* 1599:     */  
/* 1634:     */  private void h()
/* 1635:     */  {
/* 1636: 984 */    zk.r.a = CubeOptOptMesh.jdField_a_of_type_IO;
/* 1637: 985 */    zk.r.b();
/* 1638: 986 */    GL11.glDisable(2896);
/* 1639: 987 */    GL13.glActiveTexture(33984);
/* 1640: 988 */    GL11.glEnable(2929);
/* 1641: 989 */    GL11.glEnable(2884);
/* 1642: 990 */    if (!f) {
/* 1643: 991 */      GL11.glEnable(3042);
/* 1644: 992 */      GL11.glBlendFunc(770, 771);
/* 1645:     */    } else {
/* 1646: 994 */      GL11.glDisable(3042);
/* 1647:     */    }
/* 1648: 996 */    this.jdField_a_of_type_Dc.k = 0L;
/* 1649: 997 */    this.jdField_a_of_type_Dc.l = 0L;
/* 1650: 998 */    GL11.glEnableClientState(32884);
/* 1651:     */  }
/* 1652:     */  
/* 1653:     */  static
/* 1654:     */  {
/* 1655: 733 */    jdField_a_of_type_Int = 0;
/* 1656:     */    
/* 1660: 738 */    f = false;
/* 1661:     */    
/* 1740: 818 */    (
/* 1741:     */    
/* 1925:1003 */      SegmentDrawer.jdField_a_of_type_ComBulletphysicsLinearmathTransform = new Transform()).setIdentity();
/* 1926:     */  }
/* 1927:     */  
/* 1930:     */  public final void b()
/* 1931:     */  {
/* 1932:1010 */    f = Keyboard.isKeyDown(88);
/* 1933:1011 */    Keyboard.isKeyDown(66);
/* 1934:     */    
/* 1936:1014 */    long l1 = System.currentTimeMillis();
/* 1937:1015 */    this.jdField_b_of_type_Long = l1;
/* 1938:     */    
/* 1939:1017 */    if (this.jdField_c_of_type_Boolean) {
/* 1940:1018 */      c();
/* 1941:     */    }
/* 1942:1020 */    if (jdField_a_of_type_Boolean) {
/* 1943:1021 */      System.err.println("Executing FULL vis update");
/* 1944:1022 */      synchronized (this.jdField_a_of_type_JavaUtilArrayList) { Object localObject1;
/* 1945:1023 */        for (Iterator localIterator = this.jdField_a_of_type_JavaUtilArrayList.iterator(); localIterator.hasNext(); 
/* 1946:1024 */            ((SegmentController)localObject4).getSegmentBuffer().a(new cY((SegmentDrawer)localObject1), false))
/* 1947:     */        {
/* 1948:1023 */          localObject1 = (SegmentController)localIterator.next();
/* 1949:1024 */          localObject4 = localObject1;localObject1 = this;
/* 1950:     */        }
/* 1951:     */      }
/* 1952:     */    }
/* 1953:     */    
/* 1962:1037 */    long l2 = System.currentTimeMillis();
/* 1963:     */    
/* 1966:1041 */    if (System.currentTimeMillis() - this.jdField_a_of_type_De.jdField_a_of_type_Long > 1000L) {
/* 1967:1042 */      this.jdField_a_of_type_De.jdField_a_of_type_Long = System.currentTimeMillis();
/* 1968:1043 */      this.jdField_a_of_type_De.jdField_a_of_type_JavaLangObject = Integer.valueOf(this.jdField_a_of_type_De.jdField_a_of_type_Int);
/* 1969:1044 */      this.jdField_a_of_type_De.jdField_a_of_type_Int = 0;
/* 1970:     */    }
/* 1971:1046 */    xd.jdField_a_of_type_JavaUtilArrayList.add("CONTEXT UPDATES: " + this.jdField_a_of_type_De.jdField_a_of_type_JavaLangObject + "; enqueued: " + this.jdField_a_of_type_JavaUtilHashSet.size());
/* 1972:1047 */    this.jdField_a_of_type_Long = System.currentTimeMillis();
/* 1973:1048 */    System.currentTimeMillis();
/* 1974:     */    
/* 1976:1051 */    System.currentTimeMillis();
/* 1977:     */    
/* 1979:1054 */    h();
/* 1980:     */    
/* 1981:1056 */    this.jdField_a_of_type_Dc.jdField_a_of_type_Long = 0L;
/* 1982:1057 */    this.jdField_a_of_type_Dc.jdField_b_of_type_Long = 0L;
/* 1983:1058 */    this.jdField_a_of_type_Dc.m = 0L;
/* 1984:1059 */    this.jdField_a_of_type_Dc.n = 0L;
/* 1985:1060 */    this.jdField_a_of_type_Dc.c = 0L;
/* 1986:1061 */    this.jdField_a_of_type_Dc.d = 0L;
/* 1987:1062 */    this.jdField_a_of_type_Dc.f = 0L;
/* 1988:1063 */    this.jdField_a_of_type_Dc.h = 0L;
/* 1989:1064 */    this.jdField_a_of_type_Dc.e = 0L;
/* 1990:1065 */    this.jdField_a_of_type_Dc.g = 0L;
/* 1991:1066 */    this.jdField_a_of_type_Dc.o = 0L;
/* 1992:1067 */    this.jdField_a_of_type_Dc.p = 0L;
/* 1993:     */    
/* 1995:1070 */    this.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.load(xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f);
/* 1996:     */    
/* 1997:1072 */    GlUtil.d();
/* 1998:     */    
/* 1999:1074 */    this.jdField_a_of_type_Dc.j = 0L;
/* 2000:     */    
/* 2005:1080 */    this.jdField_e_of_type_Int += 1;
/* 2006:     */    
/* 2008:     */    long l3;
/* 2009:     */    
/* 2011:1086 */    if ((l3 = System.currentTimeMillis() - l2) > 10L) {
/* 2012:1087 */      System.err.println("PREPARE TIME OF 0 elements: " + l3);
/* 2013:     */    }
/* 2014:1089 */    Object localObject4 = this.jdField_a_of_type_Ct.a().a().a();
/* 2015:1090 */    BeamHandler.BeamState localBeamState = null;
/* 2016:     */    
/* 2017:     */    boolean bool;
/* 2018:     */    
/* 2019:1094 */    if (((bool = xu.l.b())) && (!((ObjectHeapPriorityQueue)localObject4).isEmpty())) {
/* 2020:1095 */      localBeamState = (BeamHandler.BeamState)((ObjectHeapPriorityQueue)localObject4).dequeue();
/* 2021:     */    }
/* 2022:     */    
/* 2024:1099 */    synchronized (this.jdField_a_of_type_ArrayOfMr)
/* 2025:     */    {
/* 2026:1101 */      long l4 = System.currentTimeMillis();
/* 2027:1102 */      synchronized (this.jdField_a_of_type_Df.jdField_a_of_type_JavaLangObject) {
/* 2028:1103 */        if (this.jdField_d_of_type_Boolean) {
/* 2029:1104 */          g();
/* 2030:1105 */          this.jdField_d_of_type_Boolean = false;
/* 2031:     */        }
/* 2032:     */      }
/* 2033:     */      long l5;
/* 2034:1109 */      if ((l5 = System.currentTimeMillis() - l4) > 10L) {
/* 2035:1110 */        System.err.println("RESORTING TIME OF 0 elements: " + l5);
/* 2036:     */      }
/* 2037:     */      
/* 2039:1114 */      int i = Math.min(((Integer)xu.ad.a()).intValue(), this.jdField_d_of_type_Int);
/* 2040:     */      
/* 2041:1116 */      long l6 = System.nanoTime();
/* 2042:1117 */      long l7 = System.currentTimeMillis();
/* 2043:1118 */      this.jdField_a_of_type_Q.b(-1, 0, 0);
/* 2044:     */      
/* 2045:1120 */      dG.b();
/* 2046:     */      
/* 2047:1122 */      if (f) {
/* 2048:1123 */        GL11.glDisable(3042);
/* 2049:     */        
/* 2051:1126 */        j = 0;
/* 2052:     */        mr localmr2;
/* 2053:1128 */        for (int k = 0; k < i; k++)
/* 2054:     */        {
/* 2055:1130 */          localmr2 = this.jdField_a_of_type_ArrayOfMr[k];
/* 2056:     */          
/* 2057:1132 */          if (a(localmr2)) {
/* 2058:1133 */            this.jdField_e_of_type_ArrayOfMr[j] = localmr2;
/* 2059:1134 */            j++;
/* 2060:     */          }
/* 2061:     */        }
/* 2062:1137 */        GL11.glEnable(3042);
/* 2063:1138 */        GL11.glBlendFunc(770, 771);
/* 2064:1139 */        for (k = j - 1; k >= 0; 
/* 2065:     */            
/* 2067:1142 */            k--)
/* 2068:     */        {
/* 2069:1144 */          localmr2 = this.jdField_e_of_type_ArrayOfMr[k];
/* 2070:1145 */          a(localmr2);
/* 2071:     */        }
/* 2072:     */      }
/* 2073:     */      else {
/* 2074:1149 */        for (j = i - 1; j >= 0; j--)
/* 2075:     */        {
/* 2076:1151 */          mr localmr1 = this.jdField_a_of_type_ArrayOfMr[j];
/* 2077:     */          
/* 2078:1153 */          if (bool)
/* 2079:     */          {
/* 2080:1155 */            int m = 0;
/* 2081:1156 */            while ((localBeamState != null) && (localmr1.jdField_a_of_type_Float < localBeamState.camDistStart))
/* 2082:     */            {
/* 2083:1158 */              if (m == 0) {
/* 2084:1159 */                GlUtil.c();
/* 2085:1160 */                i();
/* 2086:     */              }
/* 2087:1162 */              GlUtil.d();
/* 2088:1163 */              m = 1;
/* 2089:1164 */              this.jdField_a_of_type_Ct.a().a().g();
/* 2090:1165 */              dm.a(localBeamState, jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 2091:1166 */              GlUtil.c();
/* 2092:1167 */              if (!((ObjectHeapPriorityQueue)localObject4).isEmpty()) {
/* 2093:1168 */                localBeamState = (BeamHandler.BeamState)((ObjectHeapPriorityQueue)localObject4).dequeue();
/* 2094:     */              } else
/* 2095:1170 */                localBeamState = null; }
/* 2096:1171 */            if (m != 0)
/* 2097:     */            {
/* 2100:1175 */              this.jdField_a_of_type_Ct.a().a();do.h();
/* 2101:1176 */              h();
/* 2102:1177 */              GlUtil.d();
/* 2103:     */            }
/* 2104:     */          }
/* 2105:1180 */          a(localmr1);
/* 2106:     */        }
/* 2107:     */      }
/* 2108:     */      
/* 2112:1187 */      int j = (int)(System.currentTimeMillis() - l7);
/* 2113:     */      long l8;
/* 2114:1189 */      if ((float)(l8 = System.nanoTime() - l6) / 1000000.0F > 25.0F) {
/* 2115:1190 */        System.err.println("DRAWING TIME OF " + i + " elements: " + j + "(" + (float)l8 / 1000000.0F + "); unifroms: " + (float)this.jdField_a_of_type_Dc.f / 1000000.0F + "; pointer " + (float)this.jdField_a_of_type_Dc.c / 1000000.0F + "; upChk " + (float)this.jdField_a_of_type_Dc.n / 1000000.0F + "; frust " + (float)this.jdField_a_of_type_Dc.m / 1000000.0F + "; update " + (float)this.jdField_a_of_type_Dc.l / 1000000.0F + "; draw: " + (float)this.jdField_a_of_type_Dc.g / 1000000.0F + "; totD: " + (float)this.jdField_a_of_type_Dc.h / 1000000.0F);
/* 2116:     */      }
/* 2117:     */    }
/* 2118:     */    
/* 2128:1203 */    i();
/* 2129:1204 */    System.currentTimeMillis();
/* 2130:     */    
/* 2131:1206 */    GlUtil.c();
/* 2132:     */    
/* 2133:1208 */    if ((bool) && ((!((ObjectHeapPriorityQueue)localObject4).isEmpty()) || (localBeamState != null))) {
/* 2134:1209 */      this.jdField_a_of_type_Ct.a().a().g();
/* 2135:     */      
/* 2137:1212 */      if (localBeamState != null) {
/* 2138:1213 */        GlUtil.d();
/* 2139:1214 */        dm.a(localBeamState, jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 2140:1215 */        GlUtil.c();
/* 2141:     */      }
/* 2142:1217 */      while (!((ObjectHeapPriorityQueue)localObject4).isEmpty()) {
/* 2143:1218 */        GlUtil.d();
/* 2144:1219 */        dm.a((BeamHandler.BeamState)((ObjectHeapPriorityQueue)localObject4).dequeue(), jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 2145:1220 */        GlUtil.c();
/* 2146:     */      }
/* 2147:1222 */      this.jdField_a_of_type_Ct.a().a();do.h();
/* 2148:     */    }
/* 2149:     */    
/* 2150:1225 */    System.currentTimeMillis();
/* 2151:1226 */    if (System.currentTimeMillis() - this.jdField_a_of_type_Dc.q > 1000L) {
/* 2152:1227 */      this.jdField_a_of_type_Dc.jdField_b_of_type_Int = this.jdField_a_of_type_Dc.jdField_a_of_type_Int;
/* 2153:1228 */      this.jdField_a_of_type_Dc.jdField_a_of_type_Int = 0;
/* 2154:1229 */      this.jdField_a_of_type_Dc.q = System.currentTimeMillis();
/* 2155:     */    }
/* 2156:1231 */    xd.jdField_a_of_type_JavaUtilArrayList.add("LUR: " + this.jdField_a_of_type_Dc.jdField_b_of_type_Int);
/* 2157:1232 */    xd.jdField_a_of_type_JavaUtilArrayList.add("RQU/RSEG/RR: " + ct.jdField_d_of_type_Int + " / " + ct.jdField_e_of_type_Int + " / " + ct.f);
/* 2158:     */    
/* 2159:1234 */    if (Keyboard.isKeyDown(64)) {
/* 2160:1235 */      GlUtil.f();
/* 2161:     */    }
/* 2162:     */  }
/* 2163:     */  
/* 2164:     */  private void i() {
/* 2165:1240 */    GL11.glDisableClientState(32884);
/* 2166:1241 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = null;
/* 2167:     */    
/* 2168:1243 */    GL11.glDisable(3042);
/* 2169:1244 */    GL11.glShadeModel(7425);
/* 2170:1245 */    GL15.glBindBuffer(34962, 0);
/* 2171:     */    
/* 2173:1248 */    zk.r.d();
/* 2174:     */  }
/* 2175:     */  
/* 2177:     */  private boolean a(mr parammr)
/* 2178:     */  {
/* 2179:1254 */    long l1 = System.nanoTime();
/* 2180:     */    
/* 2186:1261 */    mr localmr = parammr;Object localObject = this; if ((!localmr.d()) && (localmr.jdField_a_of_type_Boolean) && (!localmr.c())) { SegmentBufferManager localSegmentBufferManager = (SegmentBufferManager)localmr.a().getSegmentBuffer();((SegmentDrawer)localObject).jdField_b_of_type_Q.b(localmr.jdField_a_of_type_Q);((SegmentDrawer)localObject).jdField_c_of_type_Q.b(localmr.jdField_a_of_type_Q);((SegmentDrawer)localObject).jdField_b_of_type_Q.c(48, 48, 48);((SegmentDrawer)localObject).jdField_c_of_type_Q.a(48, 48, 48); long l3; if ((((l3 = System.currentTimeMillis() - localmr.jdField_b_of_type_Long) > 100L) && (localSegmentBufferManager.a(((SegmentDrawer)localObject).jdField_b_of_type_Q, ((SegmentDrawer)localObject).jdField_c_of_type_Q) > localmr.c)) || (l3 > 10000L)) { localmr.jdField_a_of_type_Boolean = false;localmr.e(true);((SegmentDrawer)localObject).jdField_a_of_type_Dc.jdField_a_of_type_Int += 1; } } if (((localmr.d()) || (localmr.a() == null)) && (!localmr.c())) ((SegmentDrawer)localObject).jdField_a_of_type_De.a(localmr); if ((((localmr.a() == null ? 0 : 1) == 0 ? 1 : 0) | (!parammr.b() ? 1 : 0)) != 0)
/* 2187:     */    {
/* 2188:1263 */      dc.a(this.jdField_a_of_type_Dc, System.nanoTime() - l1);
/* 2189:1264 */      dc.b(this.jdField_a_of_type_Dc, System.nanoTime() - l1);
/* 2190:1265 */      return false;
/* 2191:     */    }
/* 2192:     */    
/* 2193:1268 */    dc.a(this.jdField_a_of_type_Dc, System.nanoTime() - l1);
/* 2194:1269 */    parammr.jdField_a_of_type_Long = this.jdField_a_of_type_Long;
/* 2195:     */    
/* 2197:1272 */    localmr = parammr;localObject = this;localmr.a(((SegmentDrawer)localObject).jdField_a_of_type_JavaxVecmathVector3f, ((SegmentDrawer)localObject).jdField_b_of_type_JavaxVecmathVector3f); if (!xe.a().a(((SegmentDrawer)localObject).jdField_a_of_type_JavaxVecmathVector3f, ((SegmentDrawer)localObject).jdField_b_of_type_JavaxVecmathVector3f))
/* 2198:     */    {
/* 2201:1276 */      dc.b(this.jdField_a_of_type_Dc, System.nanoTime() - l1);
/* 2202:1277 */      return false;
/* 2203:     */    }
/* 2204:1279 */    long l2 = System.nanoTime() - l1;this.jdField_a_of_type_Dc.m += l2;
/* 2205:     */    
/* 2206:1281 */    if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController != parammr.a()) {
/* 2207:1282 */      GlUtil.c();
/* 2208:     */      
/* 2209:1284 */      GlUtil.d();
/* 2210:1285 */      GlUtil.b(parammr.a().getWorldTransformClient());
/* 2211:     */      
/* 2215:1290 */      this.jdField_a_of_type_Q.b(-1, 0, 0);
/* 2216:1291 */      this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = parammr.a();
/* 2217:     */      
/* 2218:1293 */      if (this.jdField_e_of_type_Boolean) {
/* 2219:1294 */        GlUtil.a(zk.r, "selectTime", 0.0F);
/* 2220:1295 */        this.jdField_e_of_type_Boolean = false;
/* 2221:     */      }
/* 2222:     */      
/* 2223:1298 */      if ((dj.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController == this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController) && (System.currentTimeMillis() - dj.jdField_a_of_type_Long < 8000L)) {
/* 2224:1299 */        this.jdField_e_of_type_Boolean = true;
/* 2225:     */      }
/* 2226:     */    }
/* 2227:     */    
/* 2229:1304 */    if (this.jdField_e_of_type_Boolean) {
/* 2230:1305 */      GlUtil.a(zk.r, "selectTime", this.jdField_a_of_type_ZJ.a() * 0.34F);
/* 2231:     */    }
/* 2232:     */    
/* 2233:1308 */    l2 = System.nanoTime() - l1;this.jdField_a_of_type_Dc.l += l2;
/* 2234:     */    
/* 2238:1313 */    this.jdField_a_of_type_Dc.jdField_a_of_type_Long += 1L;
/* 2239:     */    
/* 2240:1315 */    l2 = System.nanoTime() - l1;this.jdField_a_of_type_Dc.k += l2;
/* 2241:     */    
/* 2243:1318 */    if (parammr.a() != null)
/* 2244:     */    {
/* 2245:1320 */      mr.d();
/* 2246:     */      
/* 2249:1324 */      if (this.jdField_a_of_type_Q.jdField_a_of_type_Int == -1)
/* 2250:     */      {
/* 2252:1327 */        if (CubeMeshBufferContainer.jdField_a_of_type_Int < 3) {
/* 2253:1328 */          GL11.glTranslatef(parammr.jdField_a_of_type_Q.jdField_a_of_type_Int, parammr.jdField_a_of_type_Q.jdField_b_of_type_Int, parammr.jdField_a_of_type_Q.c);
/* 2258:     */        }
/* 2259:     */        
/* 2265:     */      }
/* 2266:1341 */      else if (CubeMeshBufferContainer.jdField_a_of_type_Int < 3) {
/* 2267:1342 */        GL11.glTranslatef(-this.jdField_a_of_type_Q.jdField_a_of_type_Int + parammr.jdField_a_of_type_Q.jdField_a_of_type_Int, -this.jdField_a_of_type_Q.jdField_b_of_type_Int + parammr.jdField_a_of_type_Q.jdField_b_of_type_Int, -this.jdField_a_of_type_Q.c + parammr.jdField_a_of_type_Q.c);
/* 2268:     */      }
/* 2269:     */      
/* 2271:1346 */      parammr.a().a();
/* 2272:     */      
/* 2280:1355 */      ct.i += 1;
/* 2281:     */      
/* 2282:1357 */      if (xu.s.b())
/* 2283:     */      {
/* 2285:1360 */        if (((localObject = this.jdField_a_of_type_Ct.a().a().a(parammr.a())) != null) && (((eI)localObject).a())) {
/* 2286:1361 */          zk.r.d();
/* 2287:     */          
/* 2288:1363 */          zk.f.a = ((eI)localObject).a();
/* 2289:     */          
/* 2290:1365 */          zk.f.b();
/* 2291:     */          
/* 2292:1367 */          GlUtil.a(zk.f, "segPos", parammr.jdField_a_of_type_Q.jdField_a_of_type_Int, parammr.jdField_a_of_type_Q.jdField_b_of_type_Int, parammr.jdField_a_of_type_Q.c);
/* 2293:     */          
/* 2295:1370 */          GlUtil.a(zk.t, "m_ShieldPercentage", ((eI)localObject).a());
/* 2296:     */          
/* 2297:1372 */          parammr.a().a();
/* 2298:     */          
/* 2299:1374 */          zk.f.d();
/* 2300:     */          
/* 2301:1376 */          zk.r.b();
/* 2302:     */        }
/* 2303:     */      }
/* 2304:     */      
/* 2307:1382 */      this.jdField_a_of_type_Q.b(parammr.jdField_a_of_type_Q); dc 
/* 2308:     */      
/* 2309:1384 */        tmp839_828 = this.jdField_a_of_type_Dc;tmp839_828.jdField_b_of_type_Long = tmp839_828.jdField_b_of_type_Long;
/* 2310:1385 */      l2 = parammr.a().a.jdField_d_of_type_Int;this.jdField_a_of_type_Dc.c += l2; dc 
/* 2311:1386 */        tmp885_874 = this.jdField_a_of_type_Dc;tmp885_874.d = tmp885_874.d; dc 
/* 2312:     */      
/* 2313:1388 */        tmp904_893 = this.jdField_a_of_type_Dc;tmp904_893.e = tmp904_893.e; dc 
/* 2314:1389 */        tmp923_912 = this.jdField_a_of_type_Dc;tmp923_912.f = tmp923_912.f; dc 
/* 2315:1390 */        tmp942_931 = this.jdField_a_of_type_Dc;tmp942_931.g = tmp942_931.g;
/* 2316:1391 */      l2 = parammr.a().a.jdField_b_of_type_Long;this.jdField_a_of_type_Dc.o += l2;
/* 2317:     */    }
/* 2318:     */    
/* 2325:1400 */    l2 = parammr.g() ? 0L : parammr.b();this.jdField_a_of_type_Dc.j += l2;
/* 2326:     */    
/* 2342:1417 */    l2 = CubeOptOptMesh.jdField_a_of_type_Long;this.jdField_a_of_type_Dc.p += l2;
/* 2343:     */    
/* 2344:1419 */    dc.b(this.jdField_a_of_type_Dc, System.nanoTime() - l1);
/* 2345:     */    
/* 2346:1421 */    return parammr.a().a() > 0;
/* 2347:     */  }
/* 2348:     */  
/* 2393:     */  public final ArrayList a()
/* 2394:     */  {
/* 2395:1470 */    return this.jdField_b_of_type_JavaUtilArrayList;
/* 2396:     */  }
/* 2397:     */  
/* 2398:     */  public final void e() {
/* 2399:1474 */    synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList)
/* 2400:     */    {
/* 2403:1478 */      while (!this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.isEmpty())
/* 2404:     */      {
/* 2405:1480 */        mr localmr1 = (mr)this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.remove(0);
/* 2406:1481 */        if ((!g) && (!localmr1.c())) throw new AssertionError(localmr1.jdField_a_of_type_Q);
/* 2407:1482 */        if ((!g) && (localmr1.b() == null)) throw new AssertionError(localmr1.jdField_a_of_type_Q);
/* 2408:1483 */        synchronized (localmr1.jdField_a_of_type_JavaLangObject)
/* 2409:     */        {
/* 2411:1486 */          mr localmr2 = localmr1;SegmentDrawer localSegmentDrawer = this; if ((!g) && (localmr2.b() == null)) throw new AssertionError(); System.currentTimeMillis();localmr2.b().a(localmr2.b());localSegmentDrawer.jdField_a_of_type_Ct.a().a().a(localmr2);System.currentTimeMillis();
/* 2412:     */          
/* 2413:1488 */          localmr1.c();
/* 2414:     */          
/* 2415:1490 */          localmr1.a();
/* 2416:     */          
/* 2418:1493 */          localmr1.b(true);
/* 2419:1494 */          localmr1.d(false);
/* 2420:     */        } }
/* 2421:1496 */      GL15.glBindBuffer(34962, 0);
/* 2422:     */      
/* 2424:1499 */      return;
/* 2425:     */    }
/* 2426:     */  }
/* 2427:     */  
/* 2499:     */  public final void c()
/* 2500:     */  {
/* 2501:1576 */    f();
/* 2502:     */    
/* 2504:1579 */    this.jdField_a_of_type_De.start();
/* 2505:1580 */    this.jdField_a_of_type_Df.start();
/* 2506:     */    
/* 2508:1583 */    this.jdField_c_of_type_Boolean = false;
/* 2509:     */  }
/* 2510:     */  
/* 2517:     */  public final void a(xq paramxq)
/* 2518:     */  {
/* 2519:1594 */    dH.a();
/* 2520:1595 */    xq localxq = paramxq; iO localiO; (localiO = CubeOptOptMesh.jdField_a_of_type_IO).jdField_a_of_type_Float += localxq.a(); if (localiO.jdField_a_of_type_Float > 0.5F) { localiO.jdField_a_of_type_Float -= 0.5F;localiO.jdField_a_of_type_Int = ((localiO.jdField_a_of_type_Int + 1) % 4); }
/* 2521:1596 */    this.jdField_a_of_type_De.a();
/* 2522:1597 */    this.jdField_a_of_type_ZJ.a(paramxq);
/* 2523:     */  }
/* 2524:     */  
/* 2525:     */  public final void f()
/* 2526:     */  {
/* 2527:1602 */    synchronized (this.jdField_a_of_type_JavaUtilArrayList) { Object localObject2;
/* 2528:1603 */      for (int i = 0; i < this.jdField_a_of_type_JavaUtilArrayList.size(); i++)
/* 2529:     */      {
/* 2530:1605 */        if ((((Boolean)(localObject2 = (SegmentController)this.jdField_a_of_type_JavaUtilArrayList.get(i)).getNetworkObject().markedDeleted.get()).booleanValue()) || (!this.jdField_a_of_type_Ct.a().containsKey(((SegmentController)localObject2).getId())))
/* 2531:     */        {
/* 2532:1607 */          this.jdField_a_of_type_JavaUtilArrayList.remove(i);
/* 2533:1608 */          i--;
/* 2534:     */        }
/* 2535:     */      }
/* 2536:     */      
/* 2539:1614 */      for (Iterator localIterator = this.jdField_a_of_type_Ct.a().values().iterator(); localIterator.hasNext();)
/* 2540:     */      {
/* 2541:1616 */        if (((localObject2 = (mF)localIterator.next()) instanceof SegmentController))
/* 2542:     */        {
/* 2543:1618 */          localObject2 = (SegmentController)localObject2;
/* 2544:     */          
/* 2545:1620 */          if ((!this.jdField_a_of_type_JavaUtilArrayList.contains(localObject2)) && (localObject2 != null)) {
/* 2546:1621 */            this.jdField_a_of_type_JavaUtilArrayList.add(localObject2);
/* 2547:     */          }
/* 2548:     */        }
/* 2549:     */      }
/* 2550:1625 */      return;
/* 2551:     */    }
/* 2552:     */  }
/* 2553:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.client.view.SegmentDrawer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */