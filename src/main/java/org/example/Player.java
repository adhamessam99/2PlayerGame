package org.example;

public class Player implements Runnable {
    private int messageCounter;
    private Player receiver;
    private boolean isInitiator;
    private final String name;
    private final Object lock = new Object();
    private boolean messageReady = false;

    public Player(final String name) {
        this.name = name;
        this.messageCounter = 0;
    }

    public void setReceiver(Player receiver) {
        this.receiver = receiver;
    }

    public void setInitiator(boolean isInitiator) {
        this.isInitiator = isInitiator;
    }

    // Sending message to the receiver player and incrementing the message counter for every message sent
    public void sendMessage(String message) {
        synchronized (lock) {
            if (messageCounter < 10) {
                messageCounter++;
                System.out.println(name + " sends: " + message + " (Message " + messageCounter + ")");
                receiver.receiveMessage(message);
                messageReady = true;
                lock.notify(); // Notify the receiver that a message has been sent
            }
        }
    }

    // Receiving the message and sending a response if the message counter is less than 10
    public void receiveMessage(String message) {
        synchronized (lock) {
            if (messageCounter < 10) {
                System.out.println(name + " received: " + message);
                String messageReply = "Reply " + messageCounter;
                sendMessage(messageReply);
                messageReady = false;
                try {
                    while (!messageReady) {
                        lock.wait(); // Wait for the other player to process the response
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                System.out.println(name + " Communication Finished.");
            }
        }
    }

    @Override
    public void run() {
        if (isInitiator) {
            sendMessage("Hello");
        } else {
            synchronized (lock) {
                try {
                    while (messageCounter < 10) {
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
