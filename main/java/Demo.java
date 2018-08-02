import com.egtinteractive.tic_tac_toe.machine.ArcadeGamesMachine;

public class Demo {

    public static void main(String[] args) {
	ArcadeGamesMachine machine = ArcadeGamesMachine.getInstance();
	machine.putCoins(15);
	machine.selectGame("TicTacToe");
	machine.playGame();
    }

}
