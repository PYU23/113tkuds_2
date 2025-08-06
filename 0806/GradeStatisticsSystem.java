public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};
        int sum = 0;
        int max = scores[0];
        int min = scores[0];
        // 計算總分、最高分與最低分
        for (int score : scores) {
            sum += score;
            if (score > max) max = score;
            if (score < min) min = score;
        }
        double average = (double) sum / scores.length;
        // 計算各等第人數
        int gradeA = 0, gradeB = 0, gradeC = 0, gradeD = 0, gradeF = 0;
        for (int score : scores) {
            if (score >= 90) {
                gradeA++;
            } else if (score >= 80) {
                gradeB++;
            } else if (score >= 70) {
                gradeC++;
            } else if (score >= 60) {
                gradeD++;
            } else {
                gradeF++;
            }
        }
        // 計算高於平均分的人數
        int aboveAverageCount = 0;
        for (int score : scores) {
            if (score > average) {
                aboveAverageCount++;
            }
        }
        // 輸出成績報表
        System.out.println("===== 成績統計報表 =====");
        System.out.println("成績列表：");
        for (int score : scores) {
            System.out.print(score + " ");
        }
        System.out.println("\n------------------------");
        System.out.printf("平均分數：%.2f\n", average);
        System.out.println("最高分數：" + max);
        System.out.println("最低分數：" + min);
        System.out.println("------------------------");
        System.out.println("A 等 (90-100)： " + gradeA + " 人");
        System.out.println("B 等 (80-89)：  " + gradeB + " 人");
        System.out.println("C 等 (70-79)：  " + gradeC + " 人");
        System.out.println("D 等 (60-69)：  " + gradeD + " 人");
        System.out.println("F 等 (59 以下)：" + gradeF + " 人");
        System.out.println("------------------------");
        System.out.println("高於平均分數的人數：" + aboveAverageCount + " 人");
    }
}
