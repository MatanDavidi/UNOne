
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

 /* 
 *
 * @author Matan Davidi
 * @version 3-dic-2018
 */
public class Game {

    private final List<Card> deck;

    private final List<Card> discarded;

    private int playersCount;

    private final List<Hand> players;

    private int turns;

    private final int STARTING_CARDS_NUMBER = 7;

    private int currentPlayer;

    private Hand winningPlayer;

    private Color currentColor;

    private boolean incrementalOrder = true;

    private ChangeColorFrame changeColorFrame;

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
     */
    public Game(int playersCount) {

        setPlayersCount(playersCount);
        this.deck = new ArrayList<>();
        this.discarded = new ArrayList<>();
        this.players = new ArrayList<>(playersCount);
        turns = 0;
        currentPlayer = (int) (Math.random() * 3);
        winningPlayer = null;
        changeColorFrame = new ChangeColorFrame(this);
        changeColorFrame.setVisible(false);

        startGame();

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

            //Add numerical cards (0-9) and special cards (10-15)
            for (int j = 0; j < 16; ++j) {

                int num = j;

                CardEffect ce;

                switch (j) {

                    case 10:
                    case 11:
                        num = Integer.MIN_VALUE;
                        ce = CardEffect.Draw2;
                        break;

                    case 12:
                    case 13:
                        num = Integer.MIN_VALUE;
                        ce = CardEffect.InvertOrder;
                        break;

                    case 14:
                    case 15:
                        num = Integer.MIN_VALUE;
                        ce = CardEffect.Stop;
                        break;

                    default:
                        ce = null;
                        break;

                }

                deck.add(new Card(num, color, ce));

            }

        }

        //Add 2 * 2 black special cards
        for (int i = 0; i < 2; ++i) {

            CardEffect ce;

            if (i == 0) {

                ce = CardEffect.ChangeColor;

                for (int j = 0; j < 4; ++j) {

                    deck.add(new Card(Integer.MIN_VALUE, Color.BLACK, ce));

                }

            } else {

                ce = CardEffect.Draw4ChangeColor;

                for (int j = 0; j < 2; ++j) {

                    deck.add(new Card(Integer.MIN_VALUE, Color.BLACK, ce));

                }

            }

        }

    }

    private void startGame() {

        System.out.println("Starting game");

        fillDeck();

        shuffleDeck();

        fillHands();

        while (discard(1).getEffect() != null) {

            System.out.println("Discarding a card");

        }

        currentColor = discarded.get(0).getColor();

    }

    private void fillHands() {

        System.out.println("Filling the players' hands");

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

        if (players.size() <= playersCount) {
         
            return players.add(player);
            
        }
        
        return false;

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

    private void movePlayer(Hand player, Card card) {

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
            nextPlayer();

        }

        ++turns;
        nextPlayer();

        Hand winPlayer = checkWin();

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

//        int newColorIndex = (int) (Math.random() * 3);
//
//        switch (newColorIndex) {
//
//            case 0:
//                currentColor = Color.RED;
//                break;
//
//            case 1:
//                currentColor = Color.BLUE;
//                break;
//
//            case 2:
//                currentColor = Color.GREEN;
//                break;
//
//            case 3:
//                currentColor = Color.YELLOW;
//
//        }
//
        boolean userInput = false;

        changeColorFrame.setVisible(true);

        while (userInput) {

            changeColorFrame.mainThread.start();

            try {

                changeColorFrame.mainThread.join();
                userInput = true;

            } catch (InterruptedException ie) {

            }

        }

        changeColorFrame.setVisible(false);
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

    void setCurrentColor(Color currentColor) {

        this.currentColor = currentColor;

    }

}
