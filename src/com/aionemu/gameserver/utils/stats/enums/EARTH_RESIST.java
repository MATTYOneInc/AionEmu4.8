/**
 * This file is part of Aion-Lightning <aion-lightning.org>.
 *
 *  Aion-Lightning is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Aion-Lightning is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details. *
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Aion-Lightning.
 *  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 * Credits goes to all Open Source Core Developer Groups listed below
 * Please do not change here something, ragarding the developer credits, except the "developed by XXXX".
 * Even if you edit a lot of files in this source, you still have no rights to call it as "your Core".
 * Everybody knows that this Emulator Core was developed by Aion Lightning 
 * @-Aion-Unique-
 * @-Aion-Lightning
 * @Aion-Engine
 * @Aion-Extreme
 * @Aion-NextGen
 * @Aion-Core Dev.
 */
package com.aionemu.gameserver.utils.stats.enums;

/**
 * @author ATracer
 */
public enum EARTH_RESIST {

    WARRIOR(0),
    GLADIATOR(0),
    TEMPLAR(0),
    SCOUT(0),
    ASSASSIN(0),
    RANGER(0),
    MAGE(0),
    SORCERER(0),
    SPIRIT_MASTER(0),
    PRIEST(0),
    CLERIC(0),
    CHANTER(0),
    ENGINEER(0),
    RIDER(0),
    GUNNER(0),
    ARTIST(0),
    BARD(0);
    private int value;

    private EARTH_RESIST(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
