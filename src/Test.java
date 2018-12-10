
import java.util.ArrayList;



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
 * @version 03 December 2018
 */
public class Test {

    private static Game g;
    
    public static void main(String[] args) {

        g = new Game(4);
        
        createPlayers();

        do {

            g.movePlayer(g.getPlayableCard());

        } while (g.getWinningPlayer() == null);

        System.out.println("The winner is " + g.getWinningPlayer().getName() + "!");
    }
    
    private static void createPlayers() {

        System.out.println("Adding the players to the game");

        for (int i = 0; i < g.getPlayersCount(); ++i) {

            g.addPlayer(new Hand(new ArrayList<>(), "Player " + (i + 1)));

        }

    }

}
