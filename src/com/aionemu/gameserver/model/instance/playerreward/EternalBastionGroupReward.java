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
package com.aionemu.gameserver.model.instance.playerreward;

import com.aionemu.gameserver.model.autogroup.AGPlayer;

import java.util.List;

/**
 * @author Eloann
 */
public class EternalBastionGroupReward extends EternalBastionWarPlayerReward {

    private List<AGPlayer> players;

    public EternalBastionGroupReward(Integer object, int timeBonus, byte buffId, List<AGPlayer> players) {
        super(object, timeBonus, buffId);
        this.players = players;
    }

    public List<AGPlayer> getAGPlayers() {
        return players;
    }

    public boolean containPlayer(Integer object) {
        for (AGPlayer agp : players) {
            if (agp.getObjectId().equals(object)) {
                return true;
            }
        }
        return false;
    }

    public AGPlayer getAGPlayer(Integer object) {
        for (AGPlayer agp : players) {
            if (agp.getObjectId().equals(object)) {
                return agp;
            }
        }
        return null;
    }
}
