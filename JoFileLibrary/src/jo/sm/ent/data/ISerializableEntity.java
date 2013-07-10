package jo.sm.ent.data;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

import java.io.DataOutputStream;

public interface ISerializableEntity
{

    public abstract void writeToTag(DataOutputStream dataoutputstream);

    public abstract byte getFactoryId();
}
