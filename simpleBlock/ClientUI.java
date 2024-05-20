package simpleBlock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ClientUI extends JFrame implements KeyListener {
    private Client client;
    private List<Player> prev_players;
    private JPanel[][] cellPanel;

    public ClientUI(Client client) {
        this.client = client;
        prev_players = client.getPlayers();
        initFrame();
    }

    private void initFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(this);
        setSize(400, 400);
        setVisible(true);
    }

    public void initBoard() {
        int row_size = client.getBoard().ROW_SIZE;
        int col_size = client.getBoard().COL_SIZE;
        setLayout(new GridLayout(row_size, col_size));

        cellPanel = new JPanel[row_size][col_size];
        for (int i = 0; i < row_size; i++) {
            for (int j = 0; j < col_size; j++) {
                JPanel panel = new JPanel();
                panel.setBackground(Color.WHITE);
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                add(panel);
                cellPanel[i][j] = panel;
            }
        }
        setVisible(true);
    }

    private Player getPlayerFromPlayers(Player player, List<Player> players) {
        for (Player playerInPlayers : players) {
            if (playerInPlayers.equals(player)) {
                return playerInPlayers;
            }
        }
        return null;
    }

    public void render() {
        List<Player> new_players = client.getPlayers();
        try {
            for (Player newplayer : new_players) {
                Cell new_pos_cell = newplayer.getPositionCell();
                Player prevPlayer = getPlayerFromPlayers(newplayer, prev_players);
                Cell old_pos_cell = prevPlayer.getPositionCell();

                Color color;
                String playerColor = newplayer.getColor();
                if (playerColor.equalsIgnoreCase("Black")) {
                    color = Color.BLACK;
                } else if (playerColor.equalsIgnoreCase("Red")) {
                    color = Color.RED;
                } else if (playerColor.equalsIgnoreCase("Green")) {
                    color = Color.GREEN;
                } else {
                    color = Color.PINK;
                }

                if (!old_pos_cell.equals(new_pos_cell)) {
                    cellPanel[old_pos_cell.getRow()][old_pos_cell.getCol()].setBackground(Color.WHITE);
                    cellPanel[new_pos_cell.getRow()][new_pos_cell.getCol()].setBackground(color);
                } else {
                    cellPanel[new_pos_cell.getRow()][new_pos_cell.getCol()].setBackground(color);
                }
            }

            this.prev_players = new_players;
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("No new_players yet");
            this.prev_players = new_players;
        }
    }

    public void clearBoard() {
        for (int i = 0; i < cellPanel.length; i++) {
            for (int j = 0; j < cellPanel[0].length; j++) {
                JPanel panel = cellPanel[i][j];
                panel.setBackground(Color.WHITE);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                client.playerMove("up");
                break;
            case KeyEvent.VK_DOWN:
                client.playerMove("down");
                break;
            case KeyEvent.VK_LEFT:
                client.playerMove("left");
                break;
            case KeyEvent.VK_RIGHT:
                client.playerMove("right");
                break;
            default:
                System.out.println("Press!");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JTextField hostField = new JTextField(10);
            JTextField portField = new JTextField(5);
            JTextField usernameField = new JTextField(10);
            JTextField colorField = new JTextField(10);

            JPanel panel = new JPanel(new GridLayout(5, 2));
            panel.add(new JLabel("Enter server host:"));
            panel.add(hostField);
            panel.add(new JLabel("Enter server port:"));
            panel.add(portField);
            panel.add(new JLabel("Enter username:"));
            panel.add(usernameField);
            panel.add(new JLabel("Enter Color:"));
            panel.add(colorField);

            int result = JOptionPane.showConfirmDialog(null, panel, "User Input", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String host = hostField.getText();
                int port = Integer.parseInt(portField.getText());
                String username = usernameField.getText();
                String color = colorField.getText();

                try {
                    Player player = new Player(username, color, new Cell(0, 0));
                    Socket socket = new Socket(host, port);
                    Client client = new Client(player, socket);
                    client.sentPlayerObjToServer();
                    client.getBoardFromServer();

                    Thread UIThread = new Thread(() -> {
                        ClientUI clientUI = new ClientUI(client);
                        clientUI.initBoard();

                        while (true) {
                            clientUI.render();
                        }
                    });
                    UIThread.start();

                    Thread dataExchangeThread = new Thread(() -> {
                        try {
                            while (true) {
                                client.sentPlayerObjToServer();
                                TimeUnit.MILLISECONDS.sleep(50);
                                client.getPlayersFromServer();
                            }
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                    dataExchangeThread.start();

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
