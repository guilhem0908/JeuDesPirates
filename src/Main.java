import view.IView;
import view.ConsoleView;
// import view.JavaFXView;
import controller.GameController;
import model.GameModel;

public class Main {
	public static void main(String[] args) {
		IView view = new ConsoleView();
		// view = new JavaFXView();
		GameModel model = new GameModel();
		GameController controller = new GameController(view, model);
		controller.start();
	}
}