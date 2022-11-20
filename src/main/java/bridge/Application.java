package bridge;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        BridgeGame bridgeGame = new BridgeGame();
        OutputView outputView = new OutputView();

        Boolean endGame = false;
        String gameResult = "실패";
        int gameTryCount = 0;

        System.out.println("다리 건너기 게임을 시작합니다.");
        System.out.println("\n다리의 길이를 입력해주세요.");

        int bridgeSize = inputView.readBridgeSize();

        List<String> bridge = bridgeMaker.makeBridge(bridgeSize);
        System.out.println(bridge);

        while (!endGame) {
            bridgeGame.retry();
            String movingResult = " O ";
            int bridgeIndex = 0;
            gameTryCount += 1;
            while (movingResult.equals(" O ") && bridgeIndex < bridgeSize) {
                System.out.println("\n이동할 칸을 선택해주세요. (위: U, 아래: D)");
                String moving = inputView.readMoving();
                movingResult = bridgeGame.move(bridge, moving, bridgeIndex);
                outputView.printMap(bridgeGame);
                bridgeIndex += 1;
            }
            if (bridgeIndex == bridgeSize) {
                gameResult = "성공";
                break;
            }

            System.out.println("\n게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
            String command = inputView.readGameCommand();
            if (command.equals("Q")) {
                break;
            }
        }

        outputView.printResult(bridgeGame, gameResult, gameTryCount);
    }
}
