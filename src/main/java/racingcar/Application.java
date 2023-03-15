package racingcar;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {
    public static void main(String[] args) {
        Game game = new Game();
        game.initiateGame();
    }
}

class Game{
    private int currentMove = 0;
    private int maxMove;
    private Car[] cars;
    Game(){

    }
    public void initiateGame(){

        makeCars(inputCarNames());
        maxMove = inputMaxMoves();
        playGame();
    }
    public void playGame(){
        System.out.println("실행 결과");
        while(currentMove < maxMove){
            printProgress();
            currentMove++;
        }

    }
    public void printProgress(){
        for(Car car : cars){
            car.updatePosition();
            car.printCar();
        }
        System.out.println();
    }

    public void makeCars(String[] carNames){

        cars = new Car[carNames.length];
        for(int index = 0; index < carNames.length; index++){
            cars[index] = new Car(carNames[index]);
        }
    }

    public String[] inputCarNames(){

        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String userInput = readLine();
        String[] cars = userInput.split(",");
        try{
            checkValidNames(cars);
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return inputCarNames();
        }
        return cars;
    }
    public void checkValidNames(String[] cars) {

        for (String car : cars) {
            if (car.length() > 5) {
                throw new IllegalArgumentException("[ERROR] 이름은 5자 이하여야 합니다.");
            }
        }
    }

    public int inputMaxMoves(){

        System.out.println("시도할 회수는 몇회인가요?");
        String userInput = readLine();
        int inputMoves;
        try{
            inputMoves = checkValidMoves(userInput);
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return inputMaxMoves();
        }
        return inputMoves;
    }

    public int checkValidMoves(String userInput) {

        int inputMoves;
        try{
            inputMoves = Integer.parseInt(userInput);
        }
        catch(NumberFormatException e){
            throw new IllegalArgumentException("[ERROR] 시도 횟수는 숫자여야 한다.");
        }
        if(inputMoves < 0){
            throw new IllegalArgumentException("[ERROR] 시도 횟수는 양의 정수여야 한다.");
        }
        return inputMoves;
    }
}