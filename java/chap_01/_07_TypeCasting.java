package chap_01;

public class _07_TypeCasting {
    public static void main(String[] args) {
        // 정수형 => 실수형
        // 실수형 => 정수형

        int score = 93;
        System.out.println(score);
        System.out.println((float) score);
        System.out.println((double) score);

        float score_f = 93.3F;
        double score_d = 98.8;
        System.out.println((int) score_f);
        System.out.println((int) score_d);

        double a = (double) score + score_d;
        System.out.println(a);
        int b = score + (int) score_d;
        System.out.println(b);

        double convertedScoreDouble = score;
        // int -> long -> float -> double (자동 형변환)

        int convertedScoreInt = (int) score_d;
        // double -> float -> long -> int (수동 형변환)
    }
}
