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
 *
 * @author Matan Davidi
 * @version 30-nov-2018
 */
public enum CardEffect {
    
   Draw2,
   Draw4ChangeColor,
   ChangeColor,
   InvertOrder,
   Stop;

    @Override
    public String toString() {
        
       switch (this) {
           
           case ChangeColor:
               return "ChangeColor";
               
           case Draw2:
               return "Draw2";
               
           case Draw4ChangeColor:
               return "Draw4ChangeColor";
               
           case InvertOrder:
               return "InvertOrder";
               
           default:
               return "Stop";
               
       }
        
    }
   
}
