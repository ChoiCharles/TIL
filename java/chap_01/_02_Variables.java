package chap_01;

public class _02_Variables {
    public static void main(String[] args) {
        String name = "choi";
        System.out.println(name + "asdhgawet");
        System.out.println(name + name);

        int hour = 24;
        System.out.println(hour + hour);

        double score = 90.5;
        char grade = 'A';
        name = "kim";
        System.out.println(name + "의 점수는 " + score + "점입니다.");
        System.out.println("학점은 " + grade + "입니다.");

        boolean pass = true;
        System.out.println("합격? " + pass);

        double d = 3.14123456789;
        float f = 3.14123456789F;
        System.out.println(d);
        System.out.println(f);

        long l = 1000000000000L;
        l = 1_000_000_000_000L;
        System.out.println(l);
    }
}
