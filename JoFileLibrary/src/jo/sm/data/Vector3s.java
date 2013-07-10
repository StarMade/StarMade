package jo.sm.data;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile


public class Vector3s
    implements Comparable<Object>
{

    public Vector3s()
    {
    }

    public Vector3s(float f1, float f2, float f3)
    {
        a = (short)f1;
        b = (short)f2;
        c = (short)f3;
    }

    public Vector3s(int i, int j, int k)
    {
        a = (short)i;
        b = (short)j;
        c = (short)k;
    }

    public Vector3s(Vector3f vector3f)
    {
        a = (short)vector3f.x;
        b = (short)vector3f.y;
        c = (short)vector3f.z;
    }

    public Vector3s(Vector3s q1)
    {
        a = q1.a;
        b = q1.b;
        c = q1.c;
    }

    public final void a(int i, int j, int k)
    {
        a += i;
        b += j;
        c += k;
    }

    public final void incr(Vector3s q1)
    {
        a += q1.a;
        b += q1.b;
        c += q1.c;
    }

    public final void div()
    {
        a /= 2;
        b /= 2;
        c /= 2;
    }

    public final boolean equals(int i, int j, int k)
    {
        return a == i && b == j && c == k;
    }

    public boolean equals(Object obj)
    {
        try
        {
            obj = (Vector3s)obj;
            return a == ((Vector3s) (obj)).a && b == ((Vector3s) (obj)).b && c == ((Vector3s) (obj)).c;
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
        return ((a ^ a >>> 16) * 15 + (b ^ b >>> 16)) * 15 + (c ^ c >>> 16);
    }

    public final float mag()
    {
        return (float)Math.sqrt(a * a + b * b + c * c);
    }

    public final void mult(int i)
    {
        a *= i;
        b *= i;
        c *= i;
    }

    public final void set(int i, int j, int k)
    {
        a = (short)i;
        b = (short)j;
        c = (short)k;
    }

    public final void set(Vector3s q1)
    {
        set(q1.a, q1.b, q1.c);
    }

    public final void decr(int i, int j, int k)
    {
        a -= i;
        b -= j;
        c -= k;
    }

    public final void decr(Vector3s q1)
    {
        a -= q1.a;
        b -= q1.b;
        c -= q1.c;
    }

    public final void sub(Vector3s q1, Vector3s q2)
    {
        a = (short)(q1.a - q2.a);
        b = (short)(q1.b - q2.b);
        c = (short)(q1.c - q2.c);
    }

    public final void add(Vector3s q1, Vector3s q2)
    {
        a = (short)(q1.a + q2.a);
        b = (short)(q1.b + q2.b);
        c = (short)(q1.c + q2.c);
    }

    public String toString()
    {
        return (new StringBuilder("(")).append(a).append(", ").append(b).append(", ").append(c).append(")").toString();
    }

    public final int getVal(int i)
    {
        switch(i)
        {
        case 0:
            return a;
        case 1:
            return b;
        case 2:
            return c;
        }
        throw new NullPointerException((new StringBuilder()).append(i).append(" coord").toString());
    }

    public static Vector3s fromString(String s)
    {
    	String[] s2 = s.split(",");
        if(s2.length != 3)
            throw new NumberFormatException("Wrong number of arguments");
        else
            return new Vector3s(Integer.parseInt(s2[0].trim()), Integer.parseInt(s2[1].trim()), Integer.parseInt(s2[2].trim()));
    }

    public final void neg()
    {
        a = (short)-a;
        b = (short)-b;
        c = (short)-c;
    }

    public final void abs()
    {
        a = (short)Math.abs(a);
        b = (short)Math.abs(b);
        c = (short)Math.abs(c);
    }

    public int compareTo(Object obj)
    {
        Vector3s q1 = (Vector3s)obj;
        obj = this;
        return (Math.abs(a) + Math.abs(((Vector3s) (obj)).b) + Math.abs(((Vector3s) (obj)).c)) - (Math.abs(q1.a) + Math.abs(q1.b) + Math.abs(q1.c));
    }

    public short a;
    public short b;
    public short c;
}
