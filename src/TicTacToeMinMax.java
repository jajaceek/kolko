import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class TicTacToeMinMax implements ActionListener, MouseListener {

    JFrame frame = new JFrame("Kółko i krzyżyk");
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JPanel choicePanel = new JPanel();
    JButton easyButton = new JButton("łatwy");
    JButton mediumButton = new JButton("średni");
    JButton hardButton = new JButton("trudny");
    JButton resetButton = new JButton("reset");
    JLabel textField = new JLabel();
    JLabel textButtonField1 = new JLabel();
    JLabel textButtonField2 = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn = true;
    ButtonGroup group = new ButtonGroup();
    boolean isEasy = false;
    boolean isMedium = false;
    boolean isHard = false;
    boolean isWinX = false;
    boolean isWinO = false;
    boolean isDraw = false;
    boolean possibleWinCol0 = false;
    boolean possibleWinCol1 = false;
    boolean possibleWinCol2 = false;
    boolean possibleWinRow0 = false;
    boolean possibleWinRow1 = false;
    boolean possibleWinRow2 = false;
    boolean possibleWinDiag0 = false;
    boolean possibleWinDiag1 = false;


    TicTacToeMinMax() {


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(89, 53, 9));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setBackground(new Color(89, 53, 9));
        frame.setForeground(new Color(89, 53, 9));


        textField.setBackground(new Color(89, 53, 9));
        textField.setForeground(new Color(242, 212, 186));
        textField.setFont(new Font("Arial", Font.BOLD, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Początek gry");
        textField.setOpaque(true);

        textButtonField1.setBackground(new Color(89, 53, 9));
        textButtonField1.setForeground(new Color(242, 212, 186));
        textButtonField1.setFont(new Font("Arial", Font.BOLD, 20));
        textButtonField1.setText("Wybierz poziom:");
        textButtonField1.setHorizontalAlignment(JLabel.CENTER);
        textButtonField1.setOpaque(true);

        textButtonField2.setBackground(new Color(89, 53, 9));
        textButtonField2.setForeground(new Color(242, 212, 186));
        textButtonField2.setFont(new Font("Arial", Font.BOLD, 20));
        textButtonField2.setHorizontalAlignment(JLabel.CENTER);
        textButtonField2.setOpaque(true);


        choicePanel.setLayout(new GridLayout(0, 3));
        choicePanel.setBounds(0, 0, 600, 75);
        choicePanel.setBorder(BorderFactory.createLineBorder(new Color(89, 53, 9)));
        choicePanel.setBackground(new Color(89, 53, 9));

        easyButton.setBackground(new Color(216, 113, 54));
        easyButton.setBorder(BorderFactory.createLineBorder(new Color(89, 53, 9)));
        easyButton.setBorder(BorderFactory.createRaisedBevelBorder());
        mediumButton.setBackground(new Color(216, 113, 54));
        mediumButton.setBorder(BorderFactory.createLineBorder(new Color(89, 53, 9)));
        mediumButton.setBorder(BorderFactory.createRaisedBevelBorder());
        hardButton.setBackground(new Color(216, 113, 54));
        hardButton.setBorder(BorderFactory.createLineBorder(new Color(89, 53, 9)));
        hardButton.setBorder(BorderFactory.createRaisedBevelBorder());
        resetButton.setBackground(new Color(216, 113, 54));
        resetButton.setBorder(BorderFactory.createLineBorder(new Color(89, 53, 9)));
        resetButton.setBorder(BorderFactory.createRaisedBevelBorder());

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(89, 53, 9));

//        textButtonField1.setBackground(new Color(25, 25, 25));
//        textButtonField1.setOpaque(true);


        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 600, 100);

        button_panel.setLayout(new GridLayout(3, 3));
//        button_panel.setBackground(new Color(150, 150, 150));
        button_panel.setBackground(new Color(165, 93, 4));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 120));
            buttons[i].setBackground(new Color(165, 93, 4));
            buttons[i].setBorder(BorderFactory.createLineBorder(new Color(89, 53, 9)));
            buttons[i].setBorder(BorderFactory.createRaisedBevelBorder());
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            buttons[i].addMouseListener(this);
        }


        choicePanel.setLayout(new GridLayout(0, 3));
        choicePanel.setBounds(0, 0, 600, 75);
        choicePanel.setBorder(BorderFactory.createLineBorder(new Color(89, 53, 9)));

        easyButton.setBackground(new Color(216, 113, 54));
        easyButton.setBorder(BorderFactory.createLineBorder(new Color(89, 53, 9)));
        easyButton.setBorder(BorderFactory.createRaisedBevelBorder());
        mediumButton.setBackground(new Color(216, 113, 54));
        mediumButton.setBorder(BorderFactory.createLineBorder(new Color(89, 53, 9)));
        mediumButton.setBorder(BorderFactory.createRaisedBevelBorder());
        hardButton.setBackground(new Color(216, 113, 54));
        hardButton.setBorder(BorderFactory.createLineBorder(new Color(89, 53, 9)));
        hardButton.setBorder(BorderFactory.createRaisedBevelBorder());
        resetButton.setBackground(new Color(216, 113, 54));
        resetButton.setBorder(BorderFactory.createLineBorder(new Color(89, 53, 9)));
        resetButton.setBorder(BorderFactory.createRaisedBevelBorder());

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(89, 53, 9));

        easyButton.addActionListener(this);
        mediumButton.addActionListener(this);
        hardButton.addActionListener(this);
        resetButton.addActionListener(this);


        title_panel.add(textField);
        choicePanel.add(textButtonField1);
        choicePanel.add(textButtonField2);
        choicePanel.add(resetButton);
        choicePanel.add(easyButton);
        choicePanel.add(mediumButton);
        choicePanel.add(hardButton);

        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(choicePanel, BorderLayout.SOUTH);
        frame.add(button_panel);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == easyButton) {
            textButtonField2.setText("łatwy");
            isEasy = true;
            isMedium = false;
            isHard = false;
            System.out.println("Easy");
        }
        if (e.getSource() == mediumButton) {
            textButtonField2.setText("średni");
            isEasy = false;
            isMedium = true;
            isHard = false;
            System.out.println("Medium");
        }
        if (e.getSource() == hardButton) {
            textButtonField2.setText("nie wygrasz");
            isEasy = false;
            isMedium = false;
            isHard = true;
            System.out.println("Hard");
        }
        if (e.getSource() == resetButton) {
            textButtonField2.setText("");
            reset();
        }


        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (isEasy == true) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setBackground(new Color(165, 145, 158));
                        buttons[i].setText("X");
//                        player1_turn = false;
//                        textField.setText("Teraz O");
                        check();
                        Draw();
                        System.out.println("easy mode - player1_turn " + player1_turn);
                        easyMode();
                    }
                }
                if (isMedium == true) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setBackground(new Color(165, 145, 158));
                        buttons[i].setText("X");
                        player1_turn = false;
                        textField.setText("Teraz X");
                        check();
                        Draw();
                        System.out.println("medium mode - player1_turn " + player1_turn);
                        mediumMode();
                    }
                }
                if (isHard == true) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setBackground(new Color(165, 145, 158));
                        buttons[i].setText("X");
                        player1_turn = false;
                        textField.setText("Teraz X");
                        check();
                        Draw();
                        System.out.println("hard mode - player1_turn " + player1_turn);
                        hardMode();
                    }
                }
            }
        }
    }


    public void firstTurn() {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        player1_turn = true;
        textField.setText("Teraz X");
//

//        if (random.nextInt(2) == 0) {
//            player1_turn = true;
//            textField.setText("Teraz X");
//        } else {
//            player1_turn = false;
//            textField.setText("Teraz O");
//        }
        isEasy = true;
        textButtonField2.setText("łatwy");

    }

    public void easyMode() {
//        reset();
//        isEasy = true;
        isMedium = false;
        isHard = false;
        int i;
        for (i = 0; i < 8; i++) {
            if (buttons[i].getText() == "X" || buttons[i].getText() == "O") {
            } else break;
        }
        check();
        Draw();
        if (isDraw || isWinO || isWinX) {
        } else {
            System.out.println(i);
            buttons[i].setForeground(new Color(0, 0, 255));
            buttons[i].setBackground(new Color(165, 145, 158));
            buttons[i].setText("O");

            textField.setText("Teraz X");
            check();
            Draw();
            System.out.println("easy move ");
        }
    }

    public void mediumMode() {
//        reset();
//        isMedium = true;
        isEasy = false;
        isHard = false;

        check();
        Draw();
        checkPossibleWin();
        if (isDraw || isWinO || isWinX) {
            System.out.println("pierwszy if - win or draw");
        } else if (possibleWinCol0 || possibleWinCol1 || possibleWinCol2 ||
                possibleWinRow0 || possibleWinRow1 || possibleWinRow2 ||
                possibleWinDiag0 || possibleWinDiag1) {
            System.out.println("drugi if - possible win");
            revangeMove();
            check();
            Draw();
            possibleWinCol0 = false;
            possibleWinCol1 = false;
            possibleWinCol2 = false;
            possibleWinRow0 = false;
            possibleWinRow1 = false;
            possibleWinRow2 = false;
            possibleWinDiag0 = false;
            possibleWinDiag1 = false;
//        } else if (buttons[4].getText() != "X" && buttons[4].getText() != "O") {
//            System.out.println("trzeci if - button[4]");
//            buttons[4].setForeground(new Color(0, 0, 255));
//            buttons[4].setBackground(new Color(165, 145, 158));
//            buttons[4].setText("O");
//
//            textField.setText("Teraz X");
//            check();
//            Draw();
        } else {
            System.out.println("ostatni else");
            int i;
            for (i = 0; i < 8; i++) {
                if (buttons[i].getText() == "X" || buttons[i].getText() == "O") {
                } else break;
            }
            System.out.println(i);
            buttons[i].setForeground(new Color(0, 0, 255));
            buttons[i].setBackground(new Color(165, 145, 158));
            buttons[i].setText("O");

            textField.setText("Teraz X");
            check();
            Draw();
            System.out.println("medium move ");
        }
    }

    public void hardMode() {
        int deepLevel = 0;
        int deepScore = 0;
//        reset();
//        isHard = true;
        isEasy = false;
        isMedium = false;
        check();
        Draw();
        checkPossibleWin();
        if (isDraw || isWinO || isWinX) {
            System.out.println("pierwszy if - win or draw");
        } else if (possibleWinCol0 || possibleWinCol1 || possibleWinCol2 ||
                possibleWinRow0 || possibleWinRow1 || possibleWinRow2 ||
                possibleWinDiag0 || possibleWinDiag1) {
            System.out.println("drugi if - possible win");
            revangeMove();
            check();
            Draw();
            possibleWinCol0 = false;
            possibleWinCol1 = false;
            possibleWinCol2 = false;
            possibleWinRow0 = false;
            possibleWinRow1 = false;
            possibleWinRow2 = false;
            possibleWinDiag0 = false;
            possibleWinDiag1 = false;
        } else if (buttons[4].getText() != "X" && buttons[4].getText() != "O") {
            System.out.println("trzeci if - button[4]");
            buttons[4].setForeground(new Color(0, 0, 255));
            buttons[4].setBackground(new Color(165, 145, 158));
            buttons[4].setText("O");

            textField.setText("Teraz X");
            check();
            Draw();
        } else {
            System.out.println("ostatni else");
            int i;
            for (i = 0; i < 8; i++) {
                if (buttons[i].getText() == "X" || buttons[i].getText() == "O") {
                } else break;
            }
            System.out.println(i);
            buttons[i].setForeground(new Color(0, 0, 255));
            buttons[i].setBackground(new Color(165, 145, 158));
            buttons[i].setText("O");

            textField.setText("Teraz X");
            check();
            Draw();
            System.out.println("hard move ");
        }
    }


    public void reset() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setFont(new Font("Arial", Font.BOLD, 120));
            buttons[i].setBackground(new Color(165, 93, 4));
            buttons[i].setBorder(BorderFactory.createLineBorder(new Color(89, 53, 9)));
            buttons[i].setBorder(BorderFactory.createRaisedBevelBorder());
            buttons[i].setFocusable(false);
            buttons[i].setText("");
            buttons[i].addActionListener(this);
        }
        textField.setFont(new Font("Arial", Font.BOLD, 75));

        isEasy = false;
        isMedium = false;
        isHard = false;
        isWinX = false;
        isWinO = false;
        isDraw = false;
        possibleWinCol0 = false;
        possibleWinCol1 = false;
        possibleWinCol2 = false;
        possibleWinRow0 = false;
        possibleWinRow1 = false;
        possibleWinRow2 = false;
        possibleWinDiag0 = false;
        possibleWinDiag1 = false;

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(true);
        }
        firstTurn();

    }

    public void check() {

        if ((buttons[0].getText() == "X") && (buttons[1].getText() == "X") && (buttons[2].getText() == "X")) {
            xWins(0, 1, 2);
        }
        if ((buttons[3].getText() == "X") && (buttons[4].getText() == "X") && (buttons[5].getText() == "X")) {
            xWins(3, 4, 5);
        }
        if ((buttons[6].getText() == "X") && (buttons[7].getText() == "X") && (buttons[8].getText() == "X")) {
            xWins(6, 7, 8);
        }
        if ((buttons[0].getText() == "X") && (buttons[3].getText() == "X") && (buttons[6].getText() == "X")) {
            xWins(0, 3, 6);
        }
        if ((buttons[1].getText() == "X") && (buttons[4].getText() == "X") && (buttons[7].getText() == "X")) {
            xWins(1, 4, 7);
        }
        if ((buttons[2].getText() == "X") && (buttons[5].getText() == "X") && (buttons[8].getText() == "X")) {
            xWins(2, 5, 8);
        }
        if ((buttons[0].getText() == "X") && (buttons[4].getText() == "X") && (buttons[8].getText() == "X")) {
            xWins(0, 4, 8);
        }
        if ((buttons[2].getText() == "X") && (buttons[4].getText() == "X") && (buttons[6].getText() == "X")) {
            xWins(2, 4, 6);
        }
        if ((buttons[0].getText() == "X") && (buttons[1].getText() == "X") && (buttons[2].getText() == "X")) {
            xWins(0, 1, 2);
        }
        if ((buttons[3].getText() == "X") && (buttons[4].getText() == "X") && (buttons[5].getText() == "X")) {
            xWins(3, 4, 5);
        }
        if ((buttons[6].getText() == "X") && (buttons[7].getText() == "X") && (buttons[8].getText() == "X")) {
            xWins(6, 7, 8);
        }
        if ((buttons[0].getText() == "X") && (buttons[3].getText() == "X") && (buttons[6].getText() == "X")) {
            xWins(0, 3, 6);
        }
        if ((buttons[1].getText() == "X") && (buttons[4].getText() == "X") && (buttons[7].getText() == "X")) {
            xWins(1, 4, 7);
        }
        if ((buttons[2].getText() == "X") && (buttons[5].getText() == "X") && (buttons[8].getText() == "X")) {
            xWins(2, 5, 8);
        }
        if ((buttons[0].getText() == "X") && (buttons[4].getText() == "X") && (buttons[8].getText() == "X")) {
            xWins(0, 4, 8);
        }
        if ((buttons[2].getText() == "X") && (buttons[4].getText() == "X") && (buttons[6].getText() == "X")) {
            xWins(2, 4, 6);
        }


        if ((buttons[0].getText() == "O") && (buttons[1].getText() == "O") && (buttons[2].getText() == "O")) {
            oWins(0, 1, 2);
        }
        if ((buttons[3].getText() == "O") && (buttons[4].getText() == "O") && (buttons[5].getText() == "O")) {
            oWins(3, 4, 5);
        }
        if ((buttons[6].getText() == "O") && (buttons[7].getText() == "O") && (buttons[8].getText() == "O")) {
            oWins(6, 7, 8);
        }
        if ((buttons[0].getText() == "O") && (buttons[3].getText() == "O") && (buttons[6].getText() == "O")) {
            oWins(0, 3, 6);
        }
        if ((buttons[1].getText() == "O") && (buttons[4].getText() == "O") && (buttons[7].getText() == "O")) {
            oWins(1, 4, 7);
        }
        if ((buttons[2].getText() == "O") && (buttons[5].getText() == "O") && (buttons[8].getText() == "O")) {
            oWins(2, 5, 8);
        }
        if ((buttons[0].getText() == "O") && (buttons[4].getText() == "O") && (buttons[8].getText() == "O")) {
            oWins(0, 4, 8);
        }
        if ((buttons[2].getText() == "O") && (buttons[4].getText() == "O") && (buttons[6].getText() == "O")) {
            oWins(2, 4, 6);
        }
    }

    public void xWins(int a, int b, int c) {

        if (!isDraw) {
            for (int i = 0; i < 9; i++) {
                buttons[i].setEnabled(false);
            }
            buttons[a].setBackground(new Color(242, 212, 186));
            buttons[b].setBackground(new Color(242, 212, 186));
            buttons[c].setBackground(new Color(242, 212, 186));
            textField.setText("Wygrał X");
            isWinX = true;

            UIManager.put("Button.disabledText", new Color(165, 93, 4));
        }
    }

    public void oWins(int a, int b, int c) {

        if (!isDraw) {
            buttons[a].setBackground(new Color(242, 212, 186));
            buttons[b].setBackground(new Color(242, 212, 186));
            buttons[c].setBackground(new Color(242, 212, 186));

            for (int i = 0; i < 9; i++) {
                buttons[i].setEnabled(false);
            }
            textField.setFont(new Font("Arial", Font.BOLD, 25));
            textField.setText("Ja wiedziałem, że nie wygrasz");
            textField.setFont(new Font("Arial", Font.BOLD, 25));

            isWinO = true;
            UIManager.put("Button.disabledText", new Color(165, 93, 4));

        }
    }

    public void Draw() {
        int counter = 0;
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText() == "X" || buttons[i].getText() == "O") {
                counter++;
            }
        }

        if (counter == 9 && !isWinX && !isWinO) {
            textField.setText("Remis");
            for (int i = 0; i < 9; i++) {
                buttons[i].setEnabled(false);
            }
            isDraw = true;
            UIManager.put("Button.disabledText", new Color(165, 93, 4));
        }
    }

    public void checkPossibleWin() {
        int button0 = 0;
        int button1 = 0;
        int button2 = 0;
        int button3 = 0;
        int button4 = 0;
        int button5 = 0;
        int button6 = 0;
        int button7 = 0;
        int button8 = 0;
        if (buttons[0].getText() == "X") {
            button0 = 1;
        }
        if (buttons[1].getText() == "X") {
            button1 = 1;
        }
        if (buttons[2].getText() == "X") {
            button2 = 1;
        }
        if (buttons[3].getText() == "X") {
            button3 = 1;
        }
        if (buttons[4].getText() == "X") {
            button4 = 1;
        }
        if (buttons[5].getText() == "X") {
            button5 = 1;
        }
        if (buttons[6].getText() == "X") {
            button6 = 1;
        }
        if (buttons[7].getText() == "X") {
            button7 = 1;
        }
        if (buttons[8].getText() == "X") {
            button8 = 1;
        }

        if (button0 + button1 + button2 == 2 && buttons[0].getText() != "O" && buttons[1].getText() != "O" && buttons[2].getText() != "O"
                && (buttons[0].getText() == "" || buttons[1].getText() == "" || buttons[2].getText() == "")) {
            possibleWinRow0 = true;
        } else if (button3 + button4 + button5 == 2 && buttons[3].getText() != "O" && buttons[4].getText() != "O" && buttons[5].getText() != "O"
                && (buttons[3].getText() == "" || buttons[4].getText() == "" || buttons[5].getText() == "")) {
            possibleWinRow1 = true;
        } else if (button6 + button7 + button8 == 2 && buttons[6].getText() != "O" && buttons[7].getText() != "O" && buttons[8].getText() != "O"
                && (buttons[6].getText() == "" || buttons[7].getText() == "" || buttons[8].getText() == "")) {
            possibleWinRow2 = true;
        } else if (button0 + button3 + button6 == 2 && buttons[0].getText() != "O" && buttons[3].getText() != "O" && buttons[6].getText() != "O"
                && (buttons[0].getText() == "" || buttons[3].getText() == "" || buttons[6].getText() == "")) {
            possibleWinCol0 = true;
        } else if (button1 + button4 + button7 == 2 && buttons[1].getText() != "O" && buttons[4].getText() != "O" && buttons[7].getText() != "O"
                && (buttons[1].getText() == "" || buttons[4].getText() == "" || buttons[7].getText() == "")) {
            possibleWinCol1 = true;
        } else if (button2 + button5 + button8 == 2 && buttons[2].getText() != "O" && buttons[5].getText() != "O" && buttons[8].getText() != "O"
                && (buttons[2].getText() == "" || buttons[5].getText() == "" || buttons[8].getText() == "")) {
            possibleWinCol2 = true;
        } else if (button0 + button4 + button8 == 2 && buttons[0].getText() != "O" && buttons[4].getText() != "O" && buttons[8].getText() != "O"
                && (buttons[0].getText() == "" || buttons[4].getText() == "" || buttons[8].getText() == "")) {
            possibleWinDiag0 = true;
        } else if (button2 + button4 + button6 == 2 && buttons[2].getText() != "O" && buttons[4].getText() != "O" && buttons[6].getText() != "O"
                && (buttons[2].getText() == "" || buttons[4].getText() == "" || buttons[6].getText() == "")) {
            possibleWinDiag1 = true;
        }


    }


    public void revangeMove() {

        System.out.println(possibleWinCol0 + " col 0");
        System.out.println(possibleWinCol1 + " col 1");
        System.out.println(possibleWinCol2 + " col 2");
        System.out.println(possibleWinRow0 + " row 0");
        System.out.println(possibleWinRow1 + " row 1");
        System.out.println(possibleWinRow2 + " row 2");
        System.out.println(possibleWinDiag0 + " diag 0");
        System.out.println(possibleWinDiag1 + " diag 1");


        if (possibleWinCol0) {
            if (buttons[0].getText() == "") {
                buttons[0].setText("O");
                buttons[0].setForeground(new Color(0, 0, 255));
                buttons[0].setBackground(new Color(165, 145, 158));
            }
            if (buttons[3].getText() == "") {
                buttons[3].setText("O");
                buttons[3].setForeground(new Color(0, 0, 255));
                buttons[3].setBackground(new Color(165, 145, 158));
            }
            if (buttons[6].getText() == "") {
                buttons[6].setText("O");
                buttons[6].setForeground(new Color(0, 0, 255));
                buttons[6].setBackground(new Color(165, 145, 158));
            }
        }
        if (possibleWinCol1) {
            if (buttons[1].getText() == "") {
                buttons[1].setText("O");
                buttons[1].setForeground(new Color(0, 0, 255));
                buttons[1].setBackground(new Color(165, 145, 158));
            }
            if (buttons[4].getText() == "") {
                buttons[4].setText("O");
                buttons[4].setForeground(new Color(0, 0, 255));
                buttons[4].setBackground(new Color(165, 145, 158));
            }
            if (buttons[7].getText() == "") {
                buttons[7].setText("O");
                buttons[7].setForeground(new Color(0, 0, 255));
                buttons[7].setBackground(new Color(165, 145, 158));
            }
        }
        if (possibleWinCol2) {
            if (buttons[2].getText() == "") {
                buttons[2].setText("O");
                buttons[2].setForeground(new Color(0, 0, 255));
                buttons[2].setBackground(new Color(165, 145, 158));
            }
            if (buttons[5].getText() == "") {
                buttons[5].setText("O");
                buttons[5].setForeground(new Color(0, 0, 255));
                buttons[5].setBackground(new Color(165, 145, 158));
            }
            if (buttons[8].getText() == "") {
                buttons[8].setText("O");
                buttons[8].setForeground(new Color(0, 0, 255));
                buttons[8].setBackground(new Color(165, 145, 158));
            }
        }
        if (possibleWinRow0) {
            if (buttons[0].getText() == "") {
                buttons[0].setText("O");
                buttons[0].setForeground(new Color(0, 0, 255));
                buttons[0].setBackground(new Color(165, 145, 158));
            }
            if (buttons[1].getText() == "") {
                buttons[1].setText("O");
                buttons[1].setForeground(new Color(0, 0, 255));
                buttons[1].setBackground(new Color(165, 145, 158));
            }
            if (buttons[2].getText() == "") {
                buttons[2].setText("O");
                buttons[2].setForeground(new Color(0, 0, 255));
                buttons[2].setBackground(new Color(165, 145, 158));
            }
        }
        if (possibleWinRow1) {
            if (buttons[3].getText() == "") {
                buttons[3].setText("O");
                buttons[3].setForeground(new Color(0, 0, 255));
                buttons[3].setBackground(new Color(165, 145, 158));
            }
            if (buttons[4].getText() == "") {
                buttons[4].setText("O");
                buttons[4].setForeground(new Color(0, 0, 255));
                buttons[4].setBackground(new Color(165, 145, 158));
            }
            if (buttons[5].getText() == "") {
                buttons[5].setText("O");
                buttons[5].setForeground(new Color(0, 0, 255));
                buttons[5].setBackground(new Color(165, 145, 158));
            }
        }
        if (possibleWinRow2) {
            if (buttons[6].getText() == "") {
                buttons[6].setText("O");
                buttons[6].setForeground(new Color(0, 0, 255));
                buttons[6].setBackground(new Color(165, 145, 158));
            }
            if (buttons[7].getText() == "") {
                buttons[7].setText("O");
                buttons[7].setForeground(new Color(0, 0, 255));
                buttons[7].setBackground(new Color(165, 145, 158));
            }
            if (buttons[8].getText() == "") {
                buttons[8].setText("O");
                buttons[8].setForeground(new Color(0, 0, 255));
                buttons[8].setBackground(new Color(165, 145, 158));
            }
        }
        if (possibleWinDiag0) {
            if (buttons[0].getText() == "") {
                buttons[0].setText("O");
                buttons[0].setForeground(new Color(0, 0, 255));
                buttons[0].setBackground(new Color(165, 145, 158));
            }
            if (buttons[4].getText() == "") {
                buttons[4].setText("O");
                buttons[4].setForeground(new Color(0, 0, 255));
                buttons[4].setBackground(new Color(165, 145, 158));
            }
            if (buttons[8].getText() == "") {
                buttons[8].setText("O");
                buttons[8].setForeground(new Color(0, 0, 255));
                buttons[8].setBackground(new Color(165, 145, 158));
            }
        }
        if (possibleWinDiag1) {
            if (buttons[2].getText() == "") {
                buttons[2].setText("O");
                buttons[2].setForeground(new Color(0, 0, 255));
                buttons[2].setBackground(new Color(165, 145, 158));
            }
            if (buttons[4].getText() == "") {
                buttons[4].setText("O");
                buttons[4].setForeground(new Color(0, 0, 255));
                buttons[4].setBackground(new Color(165, 145, 158));
            }
            if (buttons[6].getText() == "") {
                buttons[6].setText("O");
                buttons[6].setForeground(new Color(0, 0, 255));
                buttons[6].setBackground(new Color(165, 145, 158));
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                Color color;
                color = buttons[i].getBackground();
                buttons[i].setBackground(color);
                System.out.println(color.toString());
            }
        }

        System.out.println("pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}