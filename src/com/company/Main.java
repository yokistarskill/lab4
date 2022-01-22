package com.company;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<BigInteger> array = new ArrayList<BigInteger>();
    static List<BigInteger> prime = new ArrayList<BigInteger>();


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input three numbers with enter: ");
        BigInteger numone = in.nextBigInteger();
        BigInteger numtwo = in.nextBigInteger();
        BigInteger numthree = in.nextBigInteger();
        in.close();
        mod(numone, numtwo, numthree);
        powTwo(numone);
        generateKey();
    }


    private static BigInteger multiplication(BigInteger i, BigInteger j) {
        BigInteger Ten = new BigInteger("10");
        if (i.compareTo(Ten) == -1 || j.compareTo(Ten) == -1)
            return i.multiply(j);
        String length = Integer.toString(i.max(j).toString().length());
        BigInteger n = new BigInteger(length);
        if (n.mod(new BigInteger("2")) == new BigInteger("1"))
            n.add(new BigInteger ("1"));

        BigInteger val = Ten.pow((n.divide(new BigInteger("2"))).intValue());

        BigInteger a = i.divide(val);
        BigInteger b = i.mod(val);
        BigInteger c = j.divide(val);
        BigInteger d = j.mod(val);

        BigInteger first = multiplication(a,c);
        BigInteger second = multiplication(b,d);
        BigInteger third = multiplication(a.add(b),c.add(d));

        BigInteger result = plus(first.multiply(Ten.pow(n.intValue())), (((third.subtract(first).subtract(second)))));
        BigInteger res = result.multiply(val).add(second);
        System.out.print(res);

        return res;
    }

    private static BigInteger mod(BigInteger a, BigInteger b, BigInteger c) {
        int i = 0;
        BigInteger k;
        k = BigInteger.valueOf(1);
        List<BigInteger> arr = new ArrayList<>();
        while (b.compareTo(BigInteger.valueOf(0)) > 0){
            while (BigInteger.valueOf((long) Math.pow(2, i)).compareTo(b) > 0){
                i++;
            }
            i--;
            b = b.subtract( BigInteger.valueOf((long) Math.pow(2, i)));
            while (i != 0){
                k = a.pow(2).mod(c);
                i--;
            }
            arr.add(k);
        }
        k = BigInteger.valueOf(1);
        while (arr.size() > 0){
            k = k.multiply(arr.get(arr.size()));
            arr.remove(arr.size());
        }
        k = k.mod(c);
        System.out.print(k);
        return k;
    }

    public static BigInteger plus(BigInteger i, BigInteger j){
        BigInteger k = i.add(j);
        return k;
    }

    private static void powTwo(BigInteger i) {
        multiplication(i, BigInteger.valueOf(2));
    }

    private static void generateKey(){
        BigInteger x;
        array.add(BigInteger.valueOf(114589 * 5236));
        for (int i = 0; i < 100; i++) {
            x = (array.get(i).multiply(BigInteger.valueOf(2)).add(BigInteger.valueOf(3)));
            mod(x, BigInteger.valueOf(1), BigInteger.valueOf(63));
            array.add(x);
        }
        ferma();
    }

    private static void ferma(){
        int a;
        for (int i = 0; array.size() > i; i++)
        {
            a = (int) (Math.random() * 13) + 114;
            if ((BigInteger.valueOf(a).pow(array.get(i).intValue()).subtract(array.get(i)).mod(BigInteger.valueOf(a)).intValue()) == 0) {
                prime.add(array.get(i));
            }
        }
    }

    private void toBinar(int k) {
            prime.get(k).toString(2);
    }
}