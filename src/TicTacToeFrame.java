import javax.swing.*;

public class TicTacToeFrame extends JFrame {
    JFrame frame;
    JPanel mainPanel;

    JPanel titlePanel;
    JPanel contentPanel;
    JPanel buttonPanel;

    JButton newGameBtn;
    JButton quitBtn;

    public TicTacToeFrame()
    {
        frame = new JFrame("TicTacToe");
        mainPanel = new JPanel();

        createContentPanel();


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createContentPanel(){}

    private void createButtonPanel(){}
}
