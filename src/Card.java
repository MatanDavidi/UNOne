
import java.awt.Color;
import java.util.Set;

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
 * @version 30-nov-2018
 */
public class Card {

    private int number;

    private Color color;

    private CardEffect effect;

    public Color getColor() {

        return color;

    }

    public CardEffect getEffect() {

        return effect;

    }

    public int getNumber() {

        return number;

    }

    public void setColor(Color color) {

        if (color.equals(Color.YELLOW)
                || color.equals(Color.RED)
                || color.equals(Color.GREEN)
                || color.equals(Color.BLUE)
                || color.equals(Color.BLACK)) {

            this.color = color;

        }

    }

    public void setEffect(CardEffect effect) {

        this.effect = effect;

    }

    public void setNumber(int number) {

        if (number >= 0 && number <= 10 + CardEffect.values().length) {

            this.number = number;

        }

    }

    public Card(int number, Color color, CardEffect effect) {

        this.number = number;
        this.color = color;
        this.effect = effect;

    }

    public Card() {

    }

    @Override
    public String toString() {

        String re = "";
        String colorString;
        
        if (color.equals(Color.YELLOW)) {
            
            colorString = "yellow";
            
        } else if (color.equals(Color.RED)) {
            
            colorString = "red";
            
        } else if (color.equals(Color.GREEN)) {
            
            colorString = "green";
            
        } else if (color.equals(Color.BLUE)) {
            
            colorString = "blue";
            
        } else {
            
            colorString = "black";
            
        }
        
        re += colorString;
        
        if (number >= 0 && number <= 9) {
            
           re += number;
            
        }
        
        if (effect != null) {
            
            re += effect;
            
        }
        
        return re;

    }

}
