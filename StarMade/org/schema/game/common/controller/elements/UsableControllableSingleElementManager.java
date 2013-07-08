/*  1:   */package org.schema.game.common.controller.elements;
/*  2:   */
/*  3:   */import cz;
/*  4:   */import jL;
/*  5:   */import java.io.PrintStream;
/*  6:   */import lA;
/*  7:   */import lE;
/*  8:   */import le;
/*  9:   */import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
/* 10:   */import org.schema.game.common.controller.SegmentController;
/* 11:   */import org.schema.game.common.data.player.ShipConfigurationNotFoundException;
/* 12:   */import q;
/* 13:   */
/* 15:   */public abstract class UsableControllableSingleElementManager
/* 16:   */  extends UsableElementManager
/* 17:   */{
/* 18:   */  private final ElementCollectionManager collection;
/* 19:   */  
/* 20:   */  public UsableControllableSingleElementManager(ElementCollectionManager paramElementCollectionManager, SegmentController paramSegmentController)
/* 21:   */  {
/* 22:22 */    super(paramSegmentController);
/* 23:23 */    this.collection = paramElementCollectionManager;
/* 24:   */  }
/* 25:   */  
/* 26:   */  protected boolean convertDeligateControls(lA paramlA, q paramq1, q paramq2) {
/* 27:27 */    paramq1.b((q)paramlA.jdField_a_of_type_JavaLangObject);
/* 28:28 */    paramq2.b((q)paramlA.jdField_a_of_type_JavaLangObject);
/* 29:   */    
/* 31:31 */    paramq1 = null;
/* 32:   */    
/* 33:   */    try
/* 34:   */    {
/* 35:35 */      if (((paramq1 = getSegmentBuffer().a(paramq2, true, new le())) != null) && (paramq1.a() == 1)) {
/* 36:   */        try {
/* 37:37 */          paramq1 = checkShipConfig(paramlA);
/* 38:38 */          paramlA = paramlA.jdField_a_of_type_LE.d();
/* 39:39 */          if (!paramq1.a(paramlA)) {
/* 40:40 */            return false;
/* 41:   */          }
/* 42:42 */          paramq2.b(paramq1.a(paramlA));
/* 43:   */        }
/* 44:   */        catch (ShipConfigurationNotFoundException localShipConfigurationNotFoundException) {
/* 45:45 */          return false;
/* 46:   */        }
/* 47:   */      }
/* 48:   */    } catch (CannotImmediateRequestOnClientException paramq1) {
/* 49:49 */      System.err.println("[CLIENT][WARNING] deferring request for deligated control. segment has been requested: " + paramq1.a());
/* 50:50 */      return false;
/* 51:   */    }
/* 52:52 */    return true;
/* 53:   */  }
/* 54:   */  
/* 57:   */  public final ElementCollectionManager getCollection()
/* 58:   */  {
/* 59:59 */    return this.collection;
/* 60:   */  }
/* 61:   */  
/* 62:   */  public abstract void onControllerChange();
/* 63:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.UsableControllableSingleElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */