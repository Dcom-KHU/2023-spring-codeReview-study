package racingcar;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;


public class Car {
    private final String name;
    private int position = 0;

    public Car(String name) {
        this.name = name;
    }

    public int getPosition() { return this.position; }

    // 추가 기능 구현
    public void move(){
        if (Randoms.pickNumberInRange(0, 9) >= 4){
            this.position += 1;
        }
    }
    public static void game(ArrayList<Car> cars){
        int num = InputCheck.inputRound();
        for (int i = 0; i < num; i++){
            System.out.println("실행 결과");
            for (Car car : cars){
                car.move();
                car.printProgress();
            }
            System.out.println();
        }
    }
    public static ArrayList<Car> make(){
        ArrayList<String> names = InputCheck.inputCarNames();
        ArrayList<Car> Cars = new ArrayList<>();
        for (String name : names){
            Cars.add(new Car(name));
        }

        return Cars;
    }

    public void printProgress(){
        String distance = new String(new char[this.position]).replace("\0", "-");
        System.out.printf("%s : %s%n", this.name, distance);
    }

    public static void printResult(ArrayList<Car> cars){
        String winner = "";
        int winnerscore = -1;

        cars.sort((x, y) -> y.getPosition() - x.getPosition());
        for (Car car : cars){
            if (car.getPosition() >= winnerscore) {
                winner += car.name + ", ";
                winnerscore = car.position;
            }
        }

        System.out.printf("최종 우승자 : %s", winner.substring(0, winner.length() - 2));
    }
}