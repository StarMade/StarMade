package org.schema.game.common.controller.elements.dockingBeam;

import class_227;
import class_331;
import class_371;
import class_46;
import class_48;
import class_52;
import class_707;
import class_721;
import class_737;
import class_747;
import class_796;
import class_798;
import class_864;
import class_941;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.CollectionNotLoadedException;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ManagerModuleCollection;
import org.schema.game.common.controller.elements.dockingBlock.DockingBlockCollectionManager;
import org.schema.game.common.controller.elements.dockingBlock.DockingBlockManagerInterface;
import org.schema.game.common.data.element.BeamHandler;
import org.schema.game.common.data.element.BeamHandler.BeamState;
import org.schema.game.common.data.element.BeamHandler.ElementNotFoundException;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.physics.CubeRayCastResult;
import org.schema.game.common.data.world.Segment;
import org.schema.game.network.objects.NetworkSegmentController;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteVector4i;

public class DockingBeamHandler
  extends BeamHandler
  implements class_721
{
  private static final float BEAM_TIMEOUT_IN_SECS = 0.5F;
  private static final float TIME_BEAM_TO_HIT_ONE_PIECE_IN_SECS = 0.2F;
  private final DockingBeamElementManager dockingBeamElementManager;
  private long lastActivate;
  
  public DockingBeamHandler(SegmentController paramSegmentController, DockingBeamElementManager paramDockingBeamElementManager)
  {
    super(paramSegmentController, null);
    this.dockingBeamElementManager = paramDockingBeamElementManager;
  }
  
  public boolean canhit(SegmentController paramSegmentController, String[] paramArrayOfString, class_48 paramclass_48)
  {
    if ((paramSegmentController = (!paramSegmentController.equals(getSegmentController())) && (((paramSegmentController instanceof class_747)) || ((paramSegmentController instanceof class_737)) || ((paramSegmentController instanceof class_864))) ? 1 : 0) == 0) {
      paramArrayOfString[0] = "Must aim at docking module";
    }
    return paramSegmentController;
  }
  
  public float getBeamTimeoutInSecs()
  {
    return 0.5F;
  }
  
  public float getBeamToHitInSecs(BeamHandler.BeamState paramBeamState)
  {
    return 0.2F;
  }
  
  public DockingBeamHandler getHandler()
  {
    return this;
  }
  
  public void onBeamHit(BeamHandler.BeamState paramBeamState, class_721 paramclass_721, class_48 paramclass_48, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, CubeRayCastResult paramCubeRayCastResult, class_941 paramclass_941, Collection paramCollection)
  {
    try
    {
      (paramBeamState = new class_796(paramCubeRayCastResult.getSegment(), paramCubeRayCastResult.cubePos)).a12();
      if (paramBeamState.a9() == 0)
      {
        System.err.println("[DOCINGBEAM] hitting air");
        return;
      }
      paramclass_721 = ElementKeyMap.getInfo(paramBeamState.a9());
      if (beamHit(getBeam(paramclass_48), paramBeamState) > 0)
      {
        paramclass_48 = paramCubeRayCastResult.getSegment().a15();
        if (paramclass_721.isDockable())
        {
          if (((paramclass_48 instanceof class_798)) && ((((class_798)paramclass_48).a() instanceof DockingBlockManagerInterface)))
          {
            paramclass_721 = (DockingBlockManagerInterface)((class_798)paramclass_48).a();
            paramSegment = paramBeamState.a2(new class_48());
            paramVector3f1 = 0;
            paramVector3f2 = 0;
            paramCubeRayCastResult = 0;
            paramclass_941 = paramclass_48.getDockingController().a4() != null ? 1 : 0;
            if (paramBeamState.a9() == 112)
            {
              getSegmentController().getDockingController().a7(paramclass_48.getUniqueIdentifier(), paramSegment);
              return;
            }
            if (paramclass_48.getDockingController().a4() == null)
            {
              paramclass_721 = paramclass_721.getDockingBlock().iterator();
              do
              {
                if (!paramclass_721.hasNext()) {
                  break;
                }
                paramCollection = ((ManagerModuleCollection)paramclass_721.next()).getCollectionManagers().iterator();
                while (paramCollection.hasNext())
                {
                  DockingBlockCollectionManager localDockingBlockCollectionManager;
                  if ((localDockingBlockCollectionManager = (DockingBlockCollectionManager)paramCollection.next()).getControllerPos().equals(paramSegment))
                  {
                    paramVector3f2 = 1;
                    localDockingBlockCollectionManager.refreshActive();
                    if (!localDockingBlockCollectionManager.hasCollision()) {
                      try
                      {
                        if (localDockingBlockCollectionManager.isObjectDockable(getSegmentController()))
                        {
                          paramVector3f1 = 1;
                          break;
                        }
                      }
                      catch (CollectionNotLoadedException localCollectionNotLoadedException)
                      {
                        System.err.println("[DockingBeamHandler] CANNOT DOCK: COLLECTION NOT YET LAODED");
                      }
                    } else {
                      paramCubeRayCastResult = 1;
                    }
                  }
                }
              } while (paramVector3f1 == 0);
            }
            if (paramVector3f1 != 0)
            {
              if (getSegmentController().isOnServer())
              {
                System.err.println("[SERVER] NOW REQUESTING DOCK FROM " + getSegmentController() + " ON " + paramclass_48 + " AT " + paramSegment);
                getSegmentController().getDockingController().a7(paramclass_48.getUniqueIdentifier(), paramSegment);
              }
            }
            else if (getSegmentController().isClientOwnObject())
            {
              if (paramclass_941 != 0)
              {
                ((class_371)getSegmentController().getState()).a4().b1("Cannot dock here!\nCannot dock on already\ndocked target (yet)");
                return;
              }
              if (paramVector3f2 == 0)
              {
                ((class_371)getSegmentController().getState()).a4().b1("Cannot dock here!\nno target found");
                return;
              }
              if (paramCubeRayCastResult != 0)
              {
                ((class_371)getSegmentController().getState()).a4().b1("Cannot dock here!\ntarget docking area\nis blocked.");
              }
              else
              {
                if (paramVector3f1 != 0) {
                  break label578;
                }
                if (paramBeamState.a9() == 7)
                {
                  ((class_371)getSegmentController().getState()).a4().d1("General Note: \nturrets may not have blocks\nbelow the core.");
                  ((class_371)getSegmentController().getState()).a4().b1("The docking area is too small\nfor this turret to dock.\nPlease expand the docking area,\nor use a smaller turret.");
                }
                else
                {
                  ((class_371)getSegmentController().getState()).a4().b1("The docking area is too small\nfor this ship to dock.\nPlease expand the docking area,\nor use a smaller ship.");
                }
              }
              ((class_371)getSegmentController().getState()).a27().a90().a27(paramBeamState);
            }
            label578:
            return;
          }
          if (getSegmentController().isClientOwnObject()) {
            ((class_371)getSegmentController().getState()).a4().b1("Cannot dock here!\ntarget not compatible");
          }
        }
        else if (paramclass_721.canActivate())
        {
          if ((!getSegmentController().isOnServer()) && (((class_371)getSegmentController().getState()).a4().b5(paramBeamState.a7().a15())) && (System.currentTimeMillis() - this.lastActivate > 2000L) && ((paramBeamState.a9() == 122) || (paramBeamState.a9() == 55) || (paramBeamState.a9() == 283) || (paramBeamState.a9() == 284) || (paramBeamState.a9() == 282) || (paramBeamState.a9() == 285) || (paramBeamState.a9() == 62)))
          {
            paramclass_721 = paramBeamState.a2(new class_48());
            paramSegment = new class_46(paramclass_721.field_475, paramclass_721.field_476, paramclass_721.field_477, paramBeamState.a10() ? 0 : 1);
            ((NetworkSegmentController)paramBeamState.a7().a15().getNetworkObject()).blockActivationBuffer.add(new RemoteVector4i(paramSegment, getSegmentController().getNetworkObject()));
            this.lastActivate = System.currentTimeMillis();
          }
        }
        else if (getSegmentController().isClientOwnObject())
        {
          ((class_371)getSegmentController().getState()).a4().b1("Cannot dock here!\ntarget not compatible");
        }
      }
      return;
    }
    catch (BeamHandler.ElementNotFoundException localElementNotFoundException) {}
  }
  
  public DockingBeamElementManager getDockingBeamElementManager()
  {
    return this.dockingBeamElementManager;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBeam.DockingBeamHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */