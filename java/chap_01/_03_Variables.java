package chap_01;

public class _03_Variables {
    public static void main(String[] args) {
        String name;
        name = "choi";
        String name2 = "kim";
        int old = 27;

        System.out.println("im " + name + " and " + old);
        System.out.println("you're " + name2);

        name = "park";
        System.out.println("new name is " + name);


        double gravity = 9.18;
        char g = 'g';

        System.out.println("중력가속도는 " + gravity + ", 단위는 " + g);

        float pi = 3.14F;
        System.out.println(pi);

        double a = 3.14123456789;
        float b = 3.14123456789F;
        System.out.println(a);
        System.out.println(b);

        int i = 1000000000;
        System.out.println(i);
        long l = 1000000000000L;
        System.out.println(l);
        l = 1_000_000_000_000L;
        System.out.println(l);


        boolean pass = true;
        System.out.println(pass);
    }
}
