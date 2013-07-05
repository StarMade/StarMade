/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import com.bulletphysics.collision.shapes.BoxShape;
/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import java.util.ArrayList;
/*    */ import javax.vecmath.Matrix3f;
/*    */ import javax.vecmath.Vector3f;
/*    */ import o;
/*    */ import org.schema.game.common.data.physics.octree.IntersectionCallback;
/*    */ import org.schema.game.common.data.physics.octree.OctreeVariableSet;
/*    */ import q;
/*    */ import xO;
/*    */ 
/*    */ public class CubeConvexVariableSet
/*    */ {
/* 21 */   Vector3f nA = new Vector3f();
/* 22 */   Transform cubeMeshTransform = new Transform();
/* 23 */   Transform convexShapeTransform = new Transform();
/* 24 */   Transform boxTransformation = new Transform();
/* 25 */   Vector3f tmp = new Vector3f();
/* 26 */   Vector3f pos0 = new Vector3f();
/* 27 */   Vector3f pos1 = new Vector3f();
/* 28 */   Vector3f diff = new Vector3f();
/* 29 */   Vector3f normalOnSurfaceB = new Vector3f();
/*    */ 
/* 32 */   xO outer = new xO();
/* 33 */   xO inner = new xO();
/* 34 */   xO outBB = new xO();
/* 35 */   q minIntA = new q();
/* 36 */   q maxIntA = new q();
/* 37 */   q minIntB = new q();
/* 38 */   q maxIntB = new q();
/*    */ 
/* 40 */   Vector3f min = new Vector3f();
/* 41 */   Vector3f max = new Vector3f();
/* 42 */   Vector3f othermin = new Vector3f();
/* 43 */   Vector3f othermax = new Vector3f();
/* 44 */   Vector3f minOut = new Vector3f();
/* 45 */   Vector3f maxOut = new Vector3f();
/* 46 */   Vector3f localMinOut = new Vector3f();
/* 47 */   Vector3f localMaxOut = new Vector3f();
/* 48 */   Vector3f otherminOut = new Vector3f();
/* 49 */   Vector3f othermaxOut = new Vector3f();
/*    */ 
/* 51 */   Vector3f elemPosA = new Vector3f();
/* 52 */   Vector3f elemPosB = new Vector3f();
/* 53 */   Vector3f hitMin = new Vector3f();
/* 54 */   Vector3f hitMax = new Vector3f();
/* 55 */   o startA = new o();
/* 56 */   o endA = new o();
/*    */ 
/* 61 */   BoxShape box0 = new BoxShape(new Vector3f(0.56F, 0.56F, 0.56F));
/*    */ 
/* 66 */   ArrayList positionCache = new ArrayList();
/* 67 */   ArrayList blockInfoCache = new ArrayList();
/*    */ 
/* 69 */   IntersectionCallback intersectionCallBackAwithB = new IntersectionCallback();
/*    */ 
/* 71 */   Vector3f shapeMin = new Vector3f();
/* 72 */   Vector3f shapeMax = new Vector3f();
/* 73 */   Vector3f outMin = new Vector3f();
/* 74 */   Vector3f outMax = new Vector3f();
/* 75 */   public Matrix3f absolute = new Matrix3f();
/*    */   public OctreeVariableSet oSet;
/* 77 */   public GjkPairDetectorVariables gjkVars = new GjkPairDetectorVariables();
/* 78 */   public Transform inv = new Transform();
/* 79 */   public AABBVarSet aabbVarSet = new AABBVarSet();
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeConvexVariableSet
 * JD-Core Version:    0.6.2
 */