package org.hsqldb;

import org.hsqldb.index.Index;

class ConstraintCore
{
  HsqlNameManager.HsqlName refName;
  HsqlNameManager.HsqlName mainName;
  HsqlNameManager.HsqlName uniqueName;
  HsqlNameManager.HsqlName refTableName;
  HsqlNameManager.HsqlName mainTableName;
  Table mainTable;
  int[] mainCols;
  Index mainIndex;
  Table refTable;
  int[] refCols;
  Index refIndex;
  int deleteAction;
  int updateAction;
  boolean hasUpdateAction;
  boolean hasDeleteAction;
  int matchType;

  ConstraintCore duplicate()
  {
    ConstraintCore localConstraintCore = new ConstraintCore();
    localConstraintCore.refName = this.refName;
    localConstraintCore.mainName = this.mainName;
    localConstraintCore.uniqueName = this.uniqueName;
    localConstraintCore.mainTable = this.mainTable;
    localConstraintCore.mainCols = this.mainCols;
    localConstraintCore.mainIndex = this.mainIndex;
    localConstraintCore.refTable = this.refTable;
    localConstraintCore.refCols = this.refCols;
    localConstraintCore.refIndex = this.refIndex;
    localConstraintCore.deleteAction = this.deleteAction;
    localConstraintCore.updateAction = this.updateAction;
    localConstraintCore.matchType = this.matchType;
    return localConstraintCore;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.ConstraintCore
 * JD-Core Version:    0.6.2
 */