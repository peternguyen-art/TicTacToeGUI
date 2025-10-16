import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.EventListener;

import static java.lang.System.exit;

public class TicTacToeFrame extends JFrame {
    JPanel mainPanel;

    JPanel titlePanel;
    JPanel contentPanel;
    JPanel buttonPanel;

    private TicTacToe gameEngine;

    JLabel titleLabel;

    TicTacToeTile[][] board = new TicTacToeTile[3][3];

    JButton newGameBtn;
    JButton quitBtn;

    int resetConfirm;

    public TicTacToeFrame()
    {
        gameEngine = new TicTacToe();

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        createTitlePanel();
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        createContentPanel();
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setSize(400,400);
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private void  createTitlePanel()
    {
        titlePanel = new JPanel();
        titleLabel = new JLabel("TicTacToe Game!", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(titleLabel);
    }

    public void createContentPanel(){
        contentPanel = new JPanel();

        int gap = 5;
        contentPanel.setLayout(new GridLayout(3,3, gap, gap));

        contentPanel.setBackground(Color.black);

        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                TicTacToeTile tile = new TicTacToeTile(row, col);
                board[row][col] = tile;
                tile.setFont(new Font("Arial", Font.BOLD, 50));

                tile.addActionListener(e -> {
                    handleTileClick(tile.getRow(), tile.getCol());
                });

                contentPanel.add(tile);
            }
        }

        mainPanel.add(contentPanel, BorderLayout.CENTER);
    }

    private void handleTileClick(int row, int col)
    {
        TicTacToeTile clickedTile = board[row][col];

        boolean moveSuccessful = gameEngine.makeMove(row, col);

        if(moveSuccessful){
            String player = gameEngine.getCurrentPlayer();
            clickedTile.setState(player.charAt(0));

            if (gameEngine.isWin(player)){
                titleLabel.setText("Congratulations! Player " +player+ " win!");
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        TicTacToeTile doneTile = board[i][j];
                        doneTile.setEnabled(false);
                    }
                }
            } else if (gameEngine.isTie()) {
                titleLabel.setText("It's a tie!");
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        TicTacToeTile doneTile = board[i][j];
                        doneTile.setEnabled(false);
                    }
                }
            } else {
                gameEngine.switchPlayer(player);
                titleLabel.setText("Player " +player+ "'s turn!");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Please choose another tile");
        }
    }

    private void resetGame(){
        resetConfirm = JOptionPane.showConfirmDialog(
                null,
                "Do you want to reset the game",
                "Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (resetConfirm == JOptionPane.YES_OPTION){
            gameEngine.clearBoard();
            titleLabel.setText("TicTacToe Game!");

            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    TicTacToeTile tile = board[i][j];

                    tile.clearTile();

                    tile.setEnabled(true);
                }
            }
        }else{}
    }

    private void createButtonPanel(){
        buttonPanel = new JPanel();

        newGameBtn = new JButton("New Game");
        newGameBtn.setFont(new Font("Arial", Font.BOLD, 20));
        newGameBtn.setBackground(Color.black);
        newGameBtn.setForeground(Color.white);
        newGameBtn.addActionListener((ActionEvent e) -> {
            resetGame();
        });

        quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("Arial", Font.BOLD, 20));
        quitBtn.setBackground(Color.black);
        quitBtn.setForeground(Color.white);
        quitBtn.addActionListener((ActionEvent ae) -> exit(0));

        buttonPanel.add(newGameBtn);
        buttonPanel.add(quitBtn);
    }
}
