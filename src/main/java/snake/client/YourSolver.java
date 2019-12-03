package snake.client;

import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.Direction;

public class YourSolver implements Solver<Board> {

  @Override
  public String get(Board board) {
    SmartSnake ss = new SmartSnake(board);
    Direction solved = ss.solve();
    return solved.toString();
  }

  public static void main(String[] args) {
    WebSocketRunner.runClient(
            "http://104.248.35.51/codenjoy-contest/board/player/tcbahlgxkkp4sd3tpa3l?code=3554994023848420864",
        new YourSolver(),
        new Board()
    );
  }
}
