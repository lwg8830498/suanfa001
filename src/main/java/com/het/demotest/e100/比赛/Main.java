package com.het.demotest.e100.比赛;

import java.util.*;

class Player {
    int id;  // 选手编号
    int totalScore;  // 选手总得分
    int[] scoreCount;  // 各分数的计数（1到10分）

    Player(int id) {
        this.id = id;
        this.totalScore = 0;
        this.scoreCount = new int[11];
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstLine = scanner.nextLine();
        String[] firstLineParts = firstLine.split(",");

        if (firstLineParts.length != 2) {
            System.out.println(-1);
            return;
        }

        int M, N;
        try {
            M = Integer.parseInt(firstLineParts[0].trim());
            N = Integer.parseInt(firstLineParts[1].trim());
        } catch (NumberFormatException e) {
            System.out.println(-1);
            return;
        }

        // 判断 M 和 N 是否在有效范围内
        if (M < 3 || M > 10 || N < 3 || N > 100) {
            System.out.println(-1);
            return;
        }

        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            players.add(new Player(i));
        }

        // 读取每个评委对选手的打分
        for (int i = 0; i < M; i++) {
            String line = scanner.nextLine();
            String[] scores = line.split(",");

            if (scores.length != N) {
                System.out.println(-1);
                return;
            }

            for (int j = 0; j < N; j++) {
                int score;
                try {
                    score = Integer.parseInt(scores[j].trim());
                } catch (NumberFormatException e) {
                    System.out.println(-1);
                    return;
                }

                // 判断打分是否在有效范围内（1到10）
                if (score < 1 || score > 10) {
                    System.out.println(-1);
                    return;
                }

                players.get(j).totalScore += score;
                players.get(j).scoreCount[score]++;
            }
        }

        // 对选手按照得分进行排序
        players.sort((a, b) -> {
            if (b.totalScore != a.totalScore) {
                return b.totalScore - a.totalScore;
            }
            for (int i = 10; i >= 1; i--) {
                if (b.scoreCount[i] != a.scoreCount[i]) {
                    return b.scoreCount[i] - a.scoreCount[i];
                }
            }
            return 0;
        });

        // 输出得分最多的前三名选手的编号
        for (int i = 0; i < 3; i++) {
            System.out.print(players.get(i).id);
            if (i < 2) {
                System.out.print(",");
            }
        }
        System.out.println();
    }
}