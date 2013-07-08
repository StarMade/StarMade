import java.io.PrintStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.lwjgl.opengl.GL20;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.core.ResourceException;

public final class class_1376
{
  public static int field_1542;
  private static Set field_1542;
  private static class_1377 field_1543;
  private static class_1377 field_1544;
  private static class_1377 field_1545;
  public static class_1377 field_1542;
  public static class_1377 field_1546;
  public static class_1377 field_1547;
  public static class_1377 field_1548;
  public static class_1377 field_1549;
  public static class_1377 field_1550;
  public static class_1377 field_1551;
  public static class_1377 field_1552;
  public static class_1377 field_1553;
  public static class_1377 field_1554;
  public static class_1377 field_1555;
  private static class_1377 field_1556;
  private static class_1377 field_1557;
  private static class_1377 field_1558;
  public static class_1377 field_1559;
  public static class_1377 field_1560;
  private static class_1377 field_1561;
  public static class_1377 field_1562;
  public static class_1377 field_1563;
  public static class_1377 field_1564;
  public static class_1377 field_1565;
  public static class_1377 field_1566;
  public static class_1377 field_1567;
  public static class_1377 field_1568;
  public static class_1377 field_1569;
  public static class_1377 field_1570;
  public static class_1377 field_1571;
  public static class_1377 field_1572;
  public static class_1377 field_1573;
  public static class_1377 field_1574;
  public static class_1377 field_1575;
  public static class_1377 field_1576;
  public static class_1377 field_1577;
  
  public static void a()
  {
    Iterator localIterator = jdField_field_1542_of_type_JavaUtilSet.iterator();
    while (localIterator.hasNext())
    {
      class_1377 localclass_1377;
      if ((localclass_1377 = (class_1377)localIterator.next()) != null) {
        try
        {
          GL20.glDeleteProgram(localclass_1377.jdField_field_1578_of_type_Int);
        }
        catch (Exception localException)
        {
          localException;
        }
      }
    }
  }
  
  public static void b()
  {
    try
    {
      jdField_field_1542_of_type_JavaUtilSet = new HashSet();
      if (class_949.field_1194.a4().equals("normal"))
      {
        field_1576 = new class_1377("data//shader/atmosphere/SkyFromSpace.vert", "data//shader/atmosphere/SkyFromSpace.frag");
        field_1575 = new class_1377("data//shader/atmosphere/SkyFromAtmosphere.vert", "data//shader/atmosphere/SkyFromAtmosphere.frag");
      }
      else if (class_949.field_1194.a4().equals("fallback"))
      {
        System.err.println("LOADING FALLBACK ATHMOSPHERE SHADER");
        field_1576 = new class_1377("data//shader/atmosphere/SkyFromSpace-le.vert", "data//shader/atmosphere/SkyFromSpace.frag");
        field_1575 = new class_1377("data//shader/atmosphere/SkyFromAtmosphere-le.vert", "data//shader/atmosphere/SkyFromAtmosphere.frag");
      }
      field_1574 = new class_1377("data//shader/perpixel/perpixel.vert.glsl", "data//shader/perpixel/perpixel.frag.glsl");
      field_1559 = new class_1377("data//shader/blackhole/blackhole.vert.glsl", "data//shader/blackhole/blackhole.frag.glsl");
      field_1569 = new class_1377("data//shader/cube/selectionSingle.vert.glsl", "data//shader/cube/selectionSingle.frag.glsl");
      field_1549 = new class_1377("data//shader/plasma/plasma.vert.glsl", "data//shader/plasma/plasma.frag.glsl");
      field_1572 = new class_1377("data//shader/bloom/godrays.vert.glsl", "data//shader/bloom/godrays.frag.glsl");
      field_1577 = new class_1377("data//shader/tubes/tube.vert.glsl", "data//shader/tubes/tube.frag.glsl");
      field_1571 = new class_1377("data//shader/simplebeam/simplebeam.vert.glsl", "data//shader/simplebeam/simplebeam.frag.glsl");
      if (class_949.field_1195.b1()) {
        field_1568 = new class_1377("data//shader/shieldhit/shieldhit.vert.glsl", "data//shader/shieldhit/shieldhit.frag.glsl");
      }
      field_1543 = new class_1377("data//shader/smoke.vert", "data//shader/smoke.frag");
      field_1553 = new class_1377("data//shader/bump.vert", "data//shader/bump.frag");
      field_1544 = new class_1377("data//shader/volumesmoke.vsh", "data//shader/volumesmoke.fsh");
      field_1545 = new class_1377("data//shader/projectiles/standard/projectile.vsh", "data//shader/projectiles/standard/projectile.fsh");
      field_1573 = new class_1377("data//shader/projectiles/trail/projectileTrail.vsh", "data//shader/projectiles/trail/projectileTrail.fsh");
      jdField_field_1542_of_type_Class_1377 = new class_1377("data//shader/projectiles/standard/projectileQuad.vsh", "data//shader/projectiles/standard/projectileQuad.fsh");
      field_1546 = new class_1377("data//shader/projectiles/beam/projectileQuad.vsh", "data//shader/projectiles/beam/projectileQuad.fsh");
      field_1551 = new class_1377("data//shader/explosion/explosion.vsh", "data//shader/explosion/explosion.fsh");
      field_1552 = new class_1377("data//shader/spacedustExt/spacedust.vsh", "data//shader/spacedustExt/spacedust.fsh");
      field_1554 = new class_1377("data//shader/planet.vert.glsl", "data//shader/planet.frag.glsl");
      field_1555 = new class_1377("data//shader/atmosphere.vert.glsl", "data//shader/atmosphere.frag.glsl");
      field_1556 = new class_1377("data//shader/bloom.vert.glsl", "data//shader/bloom2D.frag.glsl");
      field_1557 = new class_1377("data//shader/sun.vert.glsl", "data//shader/sun.frag.glsl");
      (class_1376.field_1558 = new class_1377("data//shader/silhouette.vert.glsl", "data//shader/silhouette.frag.glsl")).jdField_field_1578_of_type_Class_1369 = new class_1368();
      field_1565 = new class_1377("data//shader/silhouette.vert.glsl", "data//shader/silhouette2DAlpha.frag.glsl");
      field_1567 = new class_1377("data//shader/thruster/thruster.vert.glsl", "data//shader/thruster/thruster.frag.glsl");
      field_1547 = new class_1377("data//shader/starsExt/stars.vsh", "data//shader/starsExt/stars.fsh");
      field_1548 = new class_1377("data//shader/starsExt/starsflare.vsh", "data//shader/starsExt/starsflare.fsh");
      if (jdField_field_1542_of_type_Int == 3) {
        try
        {
          field_1566 = new class_1379("data//shader/cube/quads13/cube-3rd.vsh", "data//shader/cube/quads13/cube.fsh");
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          System.err.println("Exception: CANNOT COMPILE SHADER. Trying fallback without arrays");
          field_1566 = new class_1378("data//shader/cube/quads13/cube-3rd-bk.vsh", "data//shader/cube/quads13/cube.fsh");
        }
      } else {
        field_1566 = new class_1373("data//shader/cube/quads13/cube.vsh", "data//shader/cube/quads13/cube.fsh");
      }
      if (class_949.field_1195.b1()) {
        if (jdField_field_1542_of_type_Int == 3) {
          field_1550 = new class_1372("data//shader/cube/shieldCube/shieldcube-3rd.vsh", "data//shader/cube/shieldCube/shieldcube.fsh");
        } else {
          field_1550 = new class_1375("data//shader/cube/shieldCube/shieldcube.vsh", "data//shader/cube/shieldCube/shieldcube.fsh");
        }
      }
      field_1560 = new class_1377("data//shader/cubemap.vsh", "data//shader/cubemap.fsh");
      field_1570 = new class_1374("data//shader/skin/skin-tex.vert.glsl", "data//shader/skin/skin-tex.frag.glsl");
      field_1561 = new class_1377("data//shader/bloom/bloom.vert.glsl", "data//shader/bloom/bloom1.frag.glsl");
      field_1562 = new class_1377("data//shader/bloom/bloom.vert.glsl", "data//shader/bloom/bloom2.frag.glsl");
      field_1563 = new class_1377("data//shader/bloom/bloom.vert.glsl", "data//shader/bloom/bloom3.frag.glsl");
      field_1564 = new class_1377("data//shader/bloom/bloom.vert.glsl", "data//shader/bloom/bloom4.frag.glsl");
      GlUtil.f1();
      jdField_field_1542_of_type_JavaUtilSet.add(field_1559);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1577);
      if (!class_949.field_1194.a4().equals("none"))
      {
        jdField_field_1542_of_type_JavaUtilSet.add(field_1576);
        jdField_field_1542_of_type_JavaUtilSet.add(field_1575);
      }
      jdField_field_1542_of_type_JavaUtilSet.add(field_1574);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1573);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1572);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1549);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1570);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1571);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1569);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1568);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1567);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1548);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1550);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1558);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1543);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1553);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1544);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1554);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1555);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1556);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1557);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1552);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1545);
      jdField_field_1542_of_type_JavaUtilSet.add(jdField_field_1542_of_type_Class_1377);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1546);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1551);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1547);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1560);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1561);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1562);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1563);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1564);
      jdField_field_1542_of_type_JavaUtilSet.add(null);
      jdField_field_1542_of_type_JavaUtilSet.add(field_1566);
      return;
    }
    catch (ResourceException localResourceException)
    {
      localResourceException;
    }
  }
  
  public static void c()
  {
    Iterator localIterator = jdField_field_1542_of_type_JavaUtilSet.iterator();
    while (localIterator.hasNext())
    {
      class_1377 localclass_1377 = (class_1377)localIterator.next();
      try
      {
        if (localclass_1377 != null)
        {
          localclass_1377.jdField_field_1578_of_type_JavaUtilMap.clear();
          localclass_1377.a1();
        }
      }
      catch (ResourceException localResourceException)
      {
        localResourceException;
      }
    }
  }
  
  static
  {
    jdField_field_1542_of_type_Int = class_949.field_1256.b1() ? 2 : 3;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1376
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */