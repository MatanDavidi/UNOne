
import java.util.ArrayList;
import java.util.List;

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
 * @version 03 December 2018
 */
public class Hand {

    private String name;

    private List<Card> cards;

    private int cardsNumber;

    public Hand(String name) {

        this(new ArrayList<Card>(), name);

    }

    public Hand(List<Card> cards, String name) {

        this.cards = cards;
        cardsNumber = cards.size();
        this.name = name;

    }

    public List<Card> getCards() {

        return cards;

    }

    public int getCardsNumber() {

        return cardsNumber;

    }

    public String getName() {
        return name;
    }

    public boolean removeCard(Card c) {

        boolean re = cards.remove(c);
        cardsNumber = cards.size();
        return re;

    }

    public boolean addCard(Card c) {

        boolean re = cards.add(c);
        cardsNumber = cards.size();

        return re;

    }

}
