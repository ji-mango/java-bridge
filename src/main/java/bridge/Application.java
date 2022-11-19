package bridge;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        BridgeGame bridgeGame = new BridgeGame();
        OutputView outputView = new OutputView();

        Boolean isMoving = true;
        int bridgeIndex = 0;

        System.out.println("다리 건너기 게임을 시작합니다.");
        System.out.println("\n다리의 길이를 입력해주세요.");

        int bridgeSize = inputView.readBridgeSize();

        List<String> bridge = bridgeMaker.makeBridge(bridgeSize);
        System.out.println(bridge);

        while (isMoving && bridgeIndex < bridgeSize) {
            System.out.println("\n이동할 칸을 선택해주세요. (위: U, 아래: D)");
            String moving = inputView.readMoving();
            isMoving = bridgeGame.move(bridge, moving, bridgeIndex);
            outputView.printMap(isMoving, moving);
            bridgeIndex += 1;
        }
    }
}
