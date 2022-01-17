import java.util.Random;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class main {

    public static int getRandomNumber(int min, int max) {

        return (int) ((Math.random() * (max - min)) + min);
    }

    public static boolean checkPrime(int num) {

        if (num <= 0 || num == 1) {
            return false;
        }
        boolean flag = true;
        for (int i = 2; i <= num / 2; ++i) {

            if (num % i == 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static int generate_random_prime() {
        boolean flag = true;
        //int p = ThreadLocalRandom.current().nextInt();
        Random rand = new Random();
        int p = rand.nextInt(10000);

        while (flag == true) {
            if (checkPrime(p) == true) {
                flag = false;
            } else
                //p = ThreadLocalRandom.current().nextInt();
                 p = rand.nextInt(10000);
        }
        return p;
    }

    public static int derived_num_e(BigInteger p, BigInteger q) {

        BigInteger euler =euler(p,q);
        int eu= euler.intValue();
        int e = getRandomNumber(1, eu);
        boolean flag = false;
        while (flag == false)
        {
            if (gcd(eu, e) == 1) {
                flag = true;
            }
            else {
                e = getRandomNumber(1, eu);
            }
        }
        return e;
    }

    public static BigInteger euler (BigInteger p,BigInteger q)
    {
        p = p.subtract(BigInteger.valueOf(1));
        q = q.subtract(BigInteger.valueOf(1));
        return p.multiply(q);
    }

    public static BigInteger encrypt(BigInteger m , BigInteger e,BigInteger n)
    {
        BigInteger  c = (m.pow(e.intValue())).mod(n);
        return c;
    }

    public static BigInteger decrypt(BigInteger c , BigInteger d,BigInteger n)
    {
        BigInteger m = (c.pow(d.intValue())).mod(n);
        return m;
    }


    public static void main(String[] args) {
        BigInteger p = BigInteger.valueOf(generate_random_prime());
        BigInteger q = BigInteger.valueOf(generate_random_prime());
        System.out.println(p);
        System.out.println(q);
        BigInteger n = p.multiply(q);
        BigInteger euler = euler (p,q);
        BigInteger e = BigInteger.valueOf(derived_num_e(p, q));
        BigInteger d = (e.modInverse(euler));

        Scanner sc= new Scanner(System.in);
        System.out.println("Please Enter M value: ");
        BigInteger m = sc.nextBigInteger();

        BigInteger c =encrypt (m,e,n);
        BigInteger z = decrypt(c,d,n);

        System.out.println("Encrypted M: "+c);
        System.out.println("Decrypted M: "+z);
    }
}
