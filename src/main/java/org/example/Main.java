package org.example;

public class Main {
    public static void main(String[] args) {
        // Create two players
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        // Link the players
        player1.setReceiver(player2);
        player2.setReceiver(player1);

        // Set Player1 as the initiator
        player1.setInitiator(true);
        player2.setInitiator(false);


        Thread thread1 = new Thread(player1);
        Thread thread2 = new Thread(player2);

        thread1.start();
        thread2.start();

    }

}
