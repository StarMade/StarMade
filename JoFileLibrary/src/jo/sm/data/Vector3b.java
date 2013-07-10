package jo.sm.data;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile


public class Vector3b
{

    public Vector3b()
    {
    }

    public Vector3b(byte byte0, byte byte1, byte byte2)
    {
        a = byte0;
        b = byte1;
        c = byte2;
    }

    public Vector3b(float f, float f1, float f2)
    {
        a = (byte)(int)f;
        b = (byte)(int)f1;
        c = (byte)(int)f2;
    }

    public Vector3b(Vector3b o1)
    {
        a = o1.a;
        b = o1.b;
        c = o1.c;
    }

    public final void a(byte byte0, byte byte1, byte byte2)
    {
        a += byte0;
        b += byte1;
        c += byte2;
    }

    public final void a(Vector3b o1)
    {
        a += o1.a;
        b += o1.b;
        c += o1.c;
    }

    public final void a()
    {
        a /= 2;
        b /= 2;
        c /= 2;
    }

    public boolean equals(Object obj)
    {
        try
        {
            obj = (Vector3b)obj;
            return a == ((Vector3b) (obj)).a && b == ((Vector3b) (obj)).b && c == ((Vector3b) (obj)).c;
        }
        catch(NullPointerException _ex)
        {
            return false;
        }
        catch(ClassCastException _ex)
        {
            return false;
        }
    }

    public int hashCode()
    {
        long l = 7L + (long)a;
        l = 7L * l + (long)b;
        long l1 = 7L * l + (long)c;
        return (byte)(int)(l1 ^ l1 >> 8);
    }

    public final void b(byte byte0, byte byte1, byte byte2)
    {
        a = byte0;
        b = byte1;
        c = byte2;
    }

    public final void b(Vector3b o1)
    {
        b(o1.a, o1.b, o1.c);
    }

    public final void c(Vector3b o1)
    {
        a -= o1.a;
        b -= o1.b;
        c -= o1.c;
    }

    public String toString()
    {
        return (new StringBuilder("(")).append(a).append(", ").append(b).append(", ").append(c).append(")").toString();
    }

    public byte a;
    public byte b;
    public byte c;
}
