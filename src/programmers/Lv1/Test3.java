package programmers.Lv1;

/*
    <유연근무제>
    https://school.programmers.co.kr/learn/courses/30/lessons/388351?language=java

     P회사에서는 오늘부터 오늘부터 일주일동안 각자 설정한 출근 희망시각에 늦지않고 출근한 직원들에게 상품을 주는 이벤트를 진행
     직원들은 일주일동안 자신이 설정한 출근희망시각+10분까지 어플로 출근해야함
     예를들어 출근희망시각이 9시 58분인 직원은 10시 8분까지 출근해야함

     단 토,일의 출근시각은 이벤트에 영향을 끼치지 않는다.

     직원들은 매일 한번씩만 어플로 출근하고, 모든 시각은 시에 100을 곱하고 분을 더한 정수로 표현됨
     예를들어 10시 13분은 1013이 되고 9시 58분은 958이 됨.

     직원 n명이 설정한 출근 희망 시각을 담은 1차원 정수 배열 schedules,
     직원들이 일주일동안 출근한 시각을 담은 2차원 정수 배열 timelogs,
     이벤트를 시작한 요일을 의미하는 정수 startday
     위 3개가 매개변수로 주어짐. 이 떄 상품을 받을 직원의 수를 return하도록 solution함수를 완성해라.

 */
public class Test3 {
    public static void main(String[] args) {
        int[] schedules = {700,800,1100}; // 직원n명이 설정한 출근시각 배열 (7:00 ~ 11:00 사이)
        int[][] timelogs = { // 직원들이 일주일동안 출근한 시각을 담은 배열
                {710, 2359, 1050, 700, 650, 631, 659},
                {800, 801, 805, 800, 759, 810, 809},
                {1105, 1001, 1002, 600, 1059, 1001, 1100}
        };
        int startday = 5; // 시작한 요일

        /*
        int[] schedules = {730, 855, 700, 720};
        int[][] timelogs = {
            {710, 700, 650, 735, 700, 931, 912},
            {908, 901, 805, 815, 800, 831, 835},
            {705, 701, 702, 705, 710, 710, 711},
            {707, 731, 859, 913, 934, 931, 905}
        };
        int startday = 1;
         */

        int result = solution(schedules, timelogs, startday);
        System.out.println("=============================");
        System.out.println(result); // 3
    }


    /**
     * 상품을 받을 직원의 수를 구하는 메소드
     *
     * @param schedules 각 직원이 설정한 출근 시각 (HHMM 형식)
     * @param timelogs 각 직원의 7일간 출근 시각 로그 (HHMM 형식)
     * @param startday 시작요일 (1:월요일, ... 7:일요일)
     * @return 상품을 받을 직원 수 (7일 모두 출근 성공한 직원 수)
     */
    public static int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;

        for(var i=0; i<timelogs.length; i++) { // i번째 직원
            int success = 0;
            int day = startday;

            for(var j=0; j<7; j++) { // 일주일이니 7일치
                int time = timelogs[i][j]; // 출근시각
                int schedule = schedules[i]; // 설정한 출근시각

                // 설정 시각 + 10분 계산
                int passTime;
                int hour = schedule / 100;
                int minute = schedule % 100 + 10;

                if(minute >= 60) {
                    hour += 1;
                    minute -= 60;
                }
                passTime = hour * 100 + minute;

                // 요일계산 (1~7 : 월~일)
                int currentDay = ((startday + j - 1) % 7) + 1;

                // 주말은 늦게와도 상관없으니까 패스. 평일에는 출근시각 비교
                if(day == 6 || day == 7 || time <= passTime) {
                    success++;
                }
            }
            if(success == 7) { // 일주일 모두 출근 성공했을 경우
                answer++;
            }
        }

        return answer;
    }
}

