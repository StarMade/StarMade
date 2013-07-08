/*   1:    */package org.schema.game.common.controller.elements.dockingBeam;
/*   2:    */
/*   3:    */import cL;
/*   4:    */import dj;
/*   5:    */import jA;
/*   6:    */import java.io.PrintStream;
/*   7:    */import java.util.Collection;
/*   8:    */import java.util.Iterator;
/*   9:    */import java.util.List;
/*  10:    */import javax.vecmath.Vector3f;
/*  11:    */import jq;
/*  12:    */import jv;
/*  13:    */import kd;
/*  14:    */import ki;
/*  15:    */import ld;
/*  16:    */import org.schema.game.common.controller.CollectionNotLoadedException;
/*  17:    */import org.schema.game.common.controller.elements.ManagerModuleCollection;
/*  18:    */import org.schema.game.common.controller.elements.dockingBlock.DockingBlockCollectionManager;
/*  19:    */import org.schema.game.common.controller.elements.dockingBlock.DockingBlockManagerInterface;
/*  20:    */import org.schema.game.common.data.element.BeamHandler;
/*  21:    */import org.schema.game.common.data.element.BeamHandler.BeamState;
/*  22:    */import org.schema.game.common.data.element.BeamHandler.ElementNotFoundException;
/*  23:    */import org.schema.game.common.data.element.ElementInformation;
/*  24:    */import org.schema.game.common.data.element.ElementKeyMap;
/*  25:    */import org.schema.game.common.data.physics.CubeRayCastResult;
/*  26:    */import org.schema.game.common.data.world.Segment;
/*  27:    */import org.schema.game.network.objects.NetworkSegmentController;
/*  28:    */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  29:    */import org.schema.schine.network.objects.remote.RemoteVector4i;
/*  30:    */import s;
/*  31:    */import xq;
/*  32:    */
/*  33:    */public class DockingBeamHandler extends BeamHandler implements jq
/*  34:    */{
/*  35:    */  private static final float BEAM_TIMEOUT_IN_SECS = 0.5F;
/*  36:    */  private static final float TIME_BEAM_TO_HIT_ONE_PIECE_IN_SECS = 0.2F;
/*  37:    */  private final DockingBeamElementManager dockingBeamElementManager;
/*  38:    */  private long lastActivate;
/*  39:    */  
/*  40:    */  public DockingBeamHandler(org.schema.game.common.controller.SegmentController paramSegmentController, DockingBeamElementManager paramDockingBeamElementManager)
/*  41:    */  {
/*  42: 42 */    super(paramSegmentController, null);
/*  43: 43 */    this.dockingBeamElementManager = paramDockingBeamElementManager;
/*  44:    */  }
/*  45:    */  
/*  49:    */  public boolean canhit(org.schema.game.common.controller.SegmentController paramSegmentController, String[] paramArrayOfString, q paramq)
/*  50:    */  {
/*  51: 51 */    if ((paramSegmentController = (!paramSegmentController.equals(getSegmentController())) && (((paramSegmentController instanceof kd)) || ((paramSegmentController instanceof ki)) || ((paramSegmentController instanceof jA))) ? 1 : 0) == 0) {
/*  52: 52 */      paramArrayOfString[0] = "Must aim at docking module";
/*  53:    */    }
/*  54: 54 */    return paramSegmentController;
/*  55:    */  }
/*  56:    */  
/*  57:    */  public float getBeamTimeoutInSecs()
/*  58:    */  {
/*  59: 59 */    return 0.5F;
/*  60:    */  }
/*  61:    */  
/*  62:    */  public float getBeamToHitInSecs(BeamHandler.BeamState paramBeamState)
/*  63:    */  {
/*  64: 64 */    return 0.2F;
/*  65:    */  }
/*  66:    */  
/*  68:    */  public DockingBeamHandler getHandler()
/*  69:    */  {
/*  70: 70 */    return this;
/*  71:    */  }
/*  72:    */  
/*  75:    */  public void onBeamHit(BeamHandler.BeamState paramBeamState, jq paramjq, q paramq, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, CubeRayCastResult paramCubeRayCastResult, xq paramxq, Collection paramCollection)
/*  76:    */  {
/*  77:    */    try
/*  78:    */    {
/*  79: 79 */      (paramBeamState = new le(paramCubeRayCastResult.getSegment(), paramCubeRayCastResult.cubePos)).a();
/*  80: 80 */      if (paramBeamState.a() == 0) {
/*  81: 81 */        System.err.println("[DOCINGBEAM] hitting air");
/*  82: 82 */        return;
/*  83:    */      }
/*  84: 84 */      paramjq = ElementKeyMap.getInfo(paramBeamState.a());
/*  85:    */      
/*  87: 87 */      if (beamHit(getBeam(paramq), paramBeamState) > 0) {
/*  88: 88 */        paramq = paramCubeRayCastResult.getSegment().a();
/*  89:    */        
/*  90: 90 */        if (paramjq.isDockable())
/*  91:    */        {
/*  92: 92 */          if (((paramq instanceof ld)) && ((((ld)paramq).a() instanceof DockingBlockManagerInterface)))
/*  93:    */          {
/*  97: 97 */            paramjq = (DockingBlockManagerInterface)((ld)paramq).a();
/*  98: 98 */            paramSegment = paramBeamState.a(new q());
/*  99: 99 */            paramVector3f1 = 0;
/* 100:100 */            paramVector3f2 = 0;
/* 101:101 */            paramCubeRayCastResult = 0;
/* 102:102 */            paramxq = paramq.getDockingController().a() != null ? 1 : 0;
/* 103:103 */            if (paramBeamState.a() == 112) {
/* 104:104 */              getSegmentController().getDockingController().a(paramq.getUniqueIdentifier(), paramSegment);return;
/* 105:    */            }
/* 106:106 */            if (paramq.getDockingController().a() == null)
/* 107:    */            {
/* 108:108 */              paramjq = paramjq.getDockingBlock().iterator(); do { if (!paramjq.hasNext()) break;
/* 109:109 */                for (paramCollection = ((ManagerModuleCollection)paramjq.next()).getCollectionManagers().iterator(); paramCollection.hasNext();) { DockingBlockCollectionManager localDockingBlockCollectionManager;
/* 110:110 */                  if ((localDockingBlockCollectionManager = (DockingBlockCollectionManager)paramCollection.next()).getControllerPos().equals(paramSegment)) {
/* 111:111 */                    paramVector3f2 = 1;
/* 112:112 */                    localDockingBlockCollectionManager.refreshActive();
/* 113:113 */                    if (!localDockingBlockCollectionManager.hasCollision()) {
/* 114:    */                      try {
/* 115:115 */                        if (localDockingBlockCollectionManager.isObjectDockable(getSegmentController())) {
/* 116:116 */                          paramVector3f1 = 1;
/* 117:117 */                          break;
/* 118:    */                        }
/* 119:    */                      } catch (CollectionNotLoadedException localCollectionNotLoadedException) {
/* 120:120 */                        System.err.println("[DockingBeamHandler] CANNOT DOCK: COLLECTION NOT YET LAODED");
/* 121:    */                      }
/* 122:    */                      
/* 123:    */                    } else {
/* 124:124 */                      paramCubeRayCastResult = 1;
/* 125:    */                    }
/* 126:    */                  }
/* 127:    */                }
/* 128:128 */              } while (paramVector3f1 == 0); }
/* 129:129 */            if (paramVector3f1 != 0)
/* 130:    */            {
/* 134:134 */              if (getSegmentController().isOnServer()) {
/* 135:135 */                System.err.println("[SERVER] NOW REQUESTING DOCK FROM " + getSegmentController() + " ON " + paramq + " AT " + paramSegment);
/* 136:136 */                getSegmentController().getDockingController().a(paramq.getUniqueIdentifier(), paramSegment);
/* 137:    */              }
/* 138:    */              
/* 139:    */            }
/* 140:140 */            else if (getSegmentController().isClientOwnObject()) {
/* 141:141 */              if (paramxq != 0) {
/* 142:142 */                ((ct)getSegmentController().getState()).a().b("Cannot dock here!\nCannot dock on already\ndocked target (yet)");return;
/* 143:    */              }
/* 144:144 */              if (paramVector3f2 == 0) {
/* 145:145 */                ((ct)getSegmentController().getState()).a().b("Cannot dock here!\nno target found");return;
/* 146:    */              }
/* 147:147 */              if (paramCubeRayCastResult != 0) {
/* 148:148 */                ((ct)getSegmentController().getState()).a().b("Cannot dock here!\ntarget docking area\nis blocked.");
/* 149:    */              }
/* 150:    */              else
/* 151:    */              {
/* 152:152 */                if (paramVector3f1 != 0) break label576;
/* 153:153 */                if (paramBeamState.a() == 7) {
/* 154:154 */                  ((ct)getSegmentController().getState()).a().d("General Note: \nturrets may not have blocks\nbelow the core.");
/* 155:    */                  
/* 158:158 */                  ((ct)getSegmentController().getState()).a().b("The docking area is too small\nfor this turret to dock.\nPlease expand the docking area,\nor use a smaller turret.");
/* 161:    */                }
/* 162:    */                else
/* 163:    */                {
/* 165:165 */                  ((ct)getSegmentController().getState()).a().b("The docking area is too small\nfor this ship to dock.\nPlease expand the docking area,\nor use a smaller ship.");
/* 166:    */                }
/* 167:    */              }
/* 168:    */              
/* 172:172 */              ((ct)getSegmentController().getState()).a().a().a(paramBeamState);
/* 173:    */            }
/* 174:    */            
/* 175:    */            label576:
/* 176:176 */            return; }
/* 177:177 */          if (getSegmentController().isClientOwnObject()) {
/* 178:178 */            ((ct)getSegmentController().getState()).a().b("Cannot dock here!\ntarget not compatible");
/* 179:    */          }
/* 180:    */          
/* 182:    */        }
/* 183:183 */        else if (paramjq.canActivate()) {
/* 184:184 */          if ((!getSegmentController().isOnServer()) && (((ct)getSegmentController().getState()).a().b(paramBeamState.a().a())))
/* 185:    */          {
/* 187:187 */            if (System.currentTimeMillis() - this.lastActivate > 2000L)
/* 188:    */            {
/* 190:190 */              if ((paramBeamState.a() == 122) || (paramBeamState.a() == 55) || (paramBeamState.a() == 283) || (paramBeamState.a() == 284) || (paramBeamState.a() == 282) || (paramBeamState.a() == 285) || (paramBeamState.a() == 62))
/* 191:    */              {
/* 198:198 */                paramjq = paramBeamState.a(new q());
/* 199:199 */                paramSegment = new s(paramjq.a, paramjq.b, paramjq.c, paramBeamState.a() ? 0 : 1);
/* 200:    */                
/* 202:202 */                ((NetworkSegmentController)paramBeamState.a().a().getNetworkObject()).blockActivationBuffer.add(new RemoteVector4i(paramSegment, getSegmentController().getNetworkObject()));
/* 203:    */                
/* 204:204 */                this.lastActivate = System.currentTimeMillis();
/* 205:    */              }
/* 206:    */            }
/* 207:    */          }
/* 208:    */        }
/* 209:209 */        else if (getSegmentController().isClientOwnObject()) {
/* 210:210 */          ((ct)getSegmentController().getState()).a().b("Cannot dock here!\ntarget not compatible");
/* 211:    */        }
/* 212:    */      }
/* 213:    */      
/* 221:221 */      return;
/* 222:    */    }
/* 223:    */    catch (BeamHandler.ElementNotFoundException localElementNotFoundException) {}
/* 224:    */  }
/* 225:    */  
/* 228:    */  public DockingBeamElementManager getDockingBeamElementManager()
/* 229:    */  {
/* 230:230 */    return this.dockingBeamElementManager;
/* 231:    */  }
/* 232:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBeam.DockingBeamHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */