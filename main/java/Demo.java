import com.egtinteractive.tic_tac_toe.machine.ArcadeGamesMachine;

public class Demo {

    public static void main(String[] args) {
	ArcadeGamesMachine machine = ArcadeGamesMachine.getInstance();

	machine.loadHomePage();
	machine.putCoins(15);
	machine.loadHomePage();
	machine.selectGame("TicTacToe");
	machine.playGame();
	
    }

}
