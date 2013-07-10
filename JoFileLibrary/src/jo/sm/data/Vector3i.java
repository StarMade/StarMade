package jo.sm.data;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile


public class Vector3i
    implements Comparable<Object>
{

    public Vector3i()
    {
    }

    public Vector3i(float f, float f1, float f2)
    {
        a = (int)f;
        b = (int)f1;
        c = (int)f2;
    }

    public Vector3i(int i, int j, int k)
    {
        a = i;
        b = j;
        c = k;
    }

    public Vector3i(Vector3f vector3f)
    {
        a = (int)vector3f.x;
        b = (int)vector3f.y;
        c = (int)vector3f.z;
    }

    public Vector3i(Vector3i q1)
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

    public final void a(Vector3i q1)
    {
        a += q1.a;
        b += q1.b;
        c += q1.c;
    }

    public final void a()
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
            obj = (Vector3i)obj;
            return a == ((Vector3i) (obj)).a && b == ((Vector3i) (obj)).b && c == ((Vector3i) (obj)).c;
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

    public final void b(int i, int j, int k)
    {
        a = i;
        b = j;
        c = k;
    }

    public final void b(Vector3i q1)
    {
        b(q1.a, q1.b, q1.c);
    }

    public final void c(int i, int j, int k)
    {
        a -= i;
        b -= j;
        c -= k;
    }

    public final void c(Vector3i q1)
    {
        a -= q1.a;
        b -= q1.b;
        c -= q1.c;
    }

    public final void a(Vector3i q1, Vector3i q2)
    {
        a = q1.a - q2.a;
        b = q1.b - q2.b;
        c = q1.c - q2.c;
    }

    public final void b(Vector3i q1, Vector3i q2)
    {
        a = q1.a + q2.a;
        b = q1.b + q2.b;
        c = q1.c + q2.c;
    }

    public String toString()
    {
        return (new StringBuilder("(")).append(a).append(", ").append(b).append(", ").append(c).append(")").toString();
    }

    public final int a(int i)
    {
        switch(i)
        {
        case 0: // '\0'
            return a;

        case 1: // '\001'
            return b;

        case 2: // '\002'
            return c;
        }
        throw new NullPointerException((new StringBuilder()).append(i).append(" coord").toString());
    }

    public static Vector3i fromString(String s)
    {
    	String[] s2 = s.split(",");
        if(s2.length != 3)
            throw new NumberFormatException("Wrong number of arguments");
        else
            return new Vector3i(Integer.parseInt(s2[0].trim()), Integer.parseInt(s2[1].trim()), Integer.parseInt(s2[2].trim()));
    }

    public final void b()
    {
        a = -a;
        b = -b;
        c = -c;
    }

    public final void c()
    {
        a = Math.abs(a);
        b = Math.abs(b);
        c = Math.abs(c);
    }

    public int compareTo(Object obj)
    {
        Vector3i q1 = (Vector3i)obj;
        obj = this;
        return (Math.abs(a) + Math.abs(((Vector3i) (obj)).b) + Math.abs(((Vector3i) (obj)).c)) - (Math.abs(q1.a) + Math.abs(q1.b) + Math.abs(q1.c));
    }

    public int a;
    public int b;
    public int c;
}
