
import java.awt.Color;
import java.awt.Frame;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * Copyright (C) 2018 Matan Davidi
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * @author Matan Davidi
 * @version 3-dic-2018
 */
public class Game {

    private final List<Card> deck;

    private final List<Card> discarded;

    private int playersCount;

    public final List<Hand> players;

    private int turns;

    private final int STARTING_CARDS_NUMBER = 7;

    private int currentPlayer;

    private Hand winningPlayer;

    private Color currentColor;

    private boolean incrementalOrder = true;

    private final Frame parentContainer;

    //private UNOFrame frame;
    public int getPlayersCount() {

        return playersCount;

    }

    private void setPlayersCount(int playersCount) {

        if (playersCount > 1) {

            System.out.println("Setting players count to " + playersCount);
            this.playersCount = playersCount;

        } else {

            this.playersCount = 2;

        }

    }

    public Hand getWinningPlayer() {

        return winningPlayer;

    }

    /**
     * Creates a new game
     *
     * @param playersCount the number of players that will be playing
     * @param parentContainer the container that contains the game
     */
    public Game(int playersCount, Frame parentContainer) {

        setPlayersCount(playersCount);
        this.deck = new ArrayList<>();
        this.discarded = new ArrayList<>();
        this.players = new ArrayList<>(playersCount);
        turns = 0;
        currentPlayer = (int) (Math.random() * 3);
        winningPlayer = null;
        this.parentContainer = parentContainer;

        startGame();

    }

    public int getPlayersCount() {

        return playersCount;

    }

    private void setPlayersCount(int playersCount) {

        if (playersCount > 1) {

            System.out.println("Setting players count to " + playersCount);
            this.playersCount = playersCount;

        } else {

            this.playersCount = 2;

        }


    private void fillDeck() {

        System.out.println("Filling the deck");

        deck.clear();

        //Add all colored cards (4 colors)
        for (int i = 0; i < 4; ++i) {

            Color color;

            switch (i) {

                case 0:
                    color = Color.YELLOW;
                    break;

                case 1:
                    color = Color.RED;
                    break;

                case 2:
                    color = Color.GREEN;
                    break;

                default:
                    color = Color.BLUE;
                    break;

            }

            //Add numerical cards (0-9) and special cards (10-12)
            for (int j = 0; j < 13; ++j) {

                int num;

                CardEffect ce;

                switch (j) {

                    case 10:
                        num = Integer.MIN_VALUE;
                        ce = CardEffect.Draw2;
                        break;

                    case 11:
                        num = Integer.MIN_VALUE;
                        ce = CardEffect.InvertOrder;
                        break;

                    case 12:
                        num = Integer.MIN_VALUE;
                        ce = CardEffect.Stop;
                        break;

                    default:
                        num = j;
                        ce = null;
                        break;

                }

                //If the card is not a 0, insert it twice
                if (num == 0) {

                    deck.add(new Card(num, color, ce));

                } else {

                    for (int k = 0; k < 2; ++k) {

                        deck.add(new Card(num, color, ce));

                    }

                }

            }

        }

        //Add 4 change color cards
        for (int i = 0; i < 4; ++i) {

            deck.add(new Card(Integer.MIN_VALUE, Color.BLACK, CardEffect.ChangeColor));

        }

        //Add 4 draw 4 change color cards
        for (int i = 0; i < 4; ++i) {

            deck.add(new Card(Integer.MIN_VALUE, Color.BLACK, CardEffect.Draw4ChangeColor));

        }

    }

    private void startGame() {

        System.out.println("Starting game");

        fillDeck();

        System.out.println("Deck size: " + deck.size());

        shuffleDeck();

        while (discard(1).getEffect() != null) {

            System.out.println("Discarding a card");

        }

        currentColor = discarded.get(0).getColor();

    }

    private void fillHands() {

        System.out.println("Filling the " + players.size() + " players' hands");

        for (int i = 0; i < STARTING_CARDS_NUMBER * playersCount; ++i) {

            draw(players.get(currentPlayer), 1);

            nextPlayer();

        }

    }

    private void shuffleDeck() {

        System.out.println("Shuffling the deck");
        Random rand = new Random(System.nanoTime());

        Collections.shuffle(deck, rand);

    }

    public boolean addPlayer(Hand player) {

        boolean re = false;

        if (players.size() + 1 <= playersCount) {

            re = players.add(player);

        }

        if (players.size() == playersCount) {

            fillHands();

        }

        return re;

    }

    private Card discard(int number) {

        Card drew = new Card();

        for (int i = 0; i < number; ++i) {

            drew = deck.remove(deck.size() - 1);

            discarded.add(0, drew);

            currentColor = drew.getColor();

        }

        return drew;

    }

    private void draw(Hand player, int number) {

        for (int i = 0; i < number; ++i) {

            if (deck.size() > 0) {

                Card drew = deck.remove(deck.size() - 1);

                player.addCard(drew);

            } else {

                discardedToDeck();

            }

        }

    }

    private void discardedToDeck() {

        if (deck.isEmpty() && !discarded.isEmpty()) {

            deck.clear();

            for (int i = 0; i < discarded.size(); ++i) {

                deck.add(discarded.get(discarded.size() - (i + 1)));

            }

            shuffleDeck();

            discarded.clear();

            discard(1);

        }

    }

    private Hand checkWin() {

        for (Hand player : players) {

            if (player.getCards().isEmpty()) {

                return player;

            }

        }

        return null;

    }

    public void movePlayer(Hand player, Card card) {

        if (card != null && canPlay(player)) {

            if (player.getCards().contains(card) && isCardPlayable(card)) {

                System.out.println("Turn " + turns + ": Player " + player.getName() + " is playing a " + card);

                player.removeCard(card);
                discarded.add(0, card);
                currentColor = card.getColor();

                if (card.getEffect() != null) {

                    switch (card.getEffect()) {

                        case ChangeColor:
                            changeColor();
                            break;

                        case Draw2:
                            draw(players.get(getNextPlayerIndex()), 2);
                            break;

                        case Draw4ChangeColor:
                            draw(players.get(getNextPlayerIndex()), 4);
                            changeColor();
                            break;

                        case InvertOrder:
                            invertOrder();
                            break;

                        case Stop:
                            nextPlayer();
                            break;

                    }

                }

            }

        } else {

            System.out.println("Player " + player.getName() + " has run out of playable cards and has to draw a card from the deck");
            draw(player, 1);

        }

        ++turns;
        Hand winPlayer = checkWin();
        nextPlayer();

        if (deck.isEmpty()) {

            discardedToDeck();

        }

        if (winPlayer != null) {

            winningPlayer = winPlayer;

        }

    }

    public void movePlayer(Card card) {

        movePlayer(players.get(currentPlayer), card);

    }

    private Card getPlayableCard(Hand player) {

        List<Card> playables = new ArrayList<>();

        for (Card card : player.getCards()) {

            if (isCardPlayable(card)) {

                playables.add(card);

            }

        }

        if (playables.isEmpty()) {

            return null;

        }

        Card playable = playables.get((int) (Math.random() * playables.size() - 1));
        return playable;

    }

    public Card getPlayableCard() {

        return getPlayableCard(players.get(currentPlayer));

    }

    public boolean isCardPlayable(Card card) {

        Card lastDiscarded = discarded.get(0);

        boolean re = card.getColor().equals(currentColor)
                || (card.getNumber() == lastDiscarded.getNumber() && card.getNumber() != Integer.MIN_VALUE)
                || card.getColor().equals(Color.BLACK)
                || (lastDiscarded.getEffect() != null && card.getEffect() != null && card.getEffect().equals(lastDiscarded.getEffect()));

        return re;

    }

    private void nextPlayer() {

        currentPlayer = getNextPlayerIndex();

    }

    private int getNextPlayerIndex() {

        int newPlayer;

        if (incrementalOrder) {

            if (currentPlayer < playersCount - 1) {

                newPlayer = currentPlayer + 1;

            } else {

                newPlayer = 0;

            }

        } else {

            if (currentPlayer > 0) {

                newPlayer = currentPlayer - 1;

            } else {

                newPlayer = playersCount - 1;

            }

        }

        return newPlayer;

    }

    private void changeColor() {

        Object[] options = {"RED", "GREEN", "BLUE", "YELLOW"};

        int userInput = JOptionPane.CLOSED_OPTION;

        while (userInput == JOptionPane.CLOSED_OPTION) {
            
            userInput = JOptionPane.showOptionDialog(
                    parentContainer, "Please select a color", "Color change",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);

        }

        Color newColor = stringToColor((String) options[userInput]);

        setCurrentColor(newColor);

    }

    private void invertOrder() {

        incrementalOrder = !incrementalOrder;

    }

    public boolean canPlay(Hand player) {

        boolean canPlay = false;

        for (Card card : player.getCards()) {

            if (isCardPlayable(card)) {

                canPlay = true;
                break;

            }

        }

        return canPlay;

    }

    /**
     * Converts a String representation of a color into an object of class
     * Color. Taken directly from
     * https://stackoverflow.com/questions/2854043/converting-a-string-to-color-in-java
     * Thanks ZZ Coder and Erick Robertson
     *
     * @param value the String to convert to an instance of class Color
     * @return an instance of class Color representing the Color whose name is
     * contained in the String parameter 'value'
     */
    private Color stringToColor(String value) {

        Color re;

        try {

            Field field = Class.forName("java.awt.Color").getField(value);
            re = (Color) field.get(null);

        } catch (Exception e) {

            re = null; // Not defined

        }

        return re;

    }

    private void setCurrentColor(Color currentColor) {

        this.currentColor = currentColor;

        System.out.println("The color was changed");

    }

}
