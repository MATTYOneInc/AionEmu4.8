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
package com.aionemu.gameserver.services;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aionemu.gameserver.configs.main.FastTrackConfig;
import com.aionemu.gameserver.management.users.UserManager;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SERVER_IDS;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.services.transfers.FastTrack;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.WorldType;


/**
 * @author Eloann - Enomine, Alcapwnd
 */
public class FastTrackService {


    private Logger log = LoggerFactory.getLogger(FastTrackService.class);
    
    private static final FastTrackService instance = new FastTrackService();
    private Map<Integer, Player> accountsOnFast = new HashMap<Integer, Player>(1);

    /**
	 * @param player  
     * @param off 
	 */
    public void fastTrackBonus(Player player, boolean off) {
    }
    
    public boolean isPvPZone(WorldType wt) {
    	return wt == WorldType.BALAUREA || wt == WorldType.ABYSS;
    }
    
    public boolean isNormalZone(WorldType wt) {
    	return wt == WorldType.ASMODAE || wt == WorldType.ELYSEA || wt == WorldType.NONE;
    }    
    
    /**
     * @param player
     */
    public void checkAuthorizationRequest(Player player) {
    	if (ServiceLogger.getFastTrackServer(player)) {
        int upto = FastTrackConfig.FASTTRACK_MAX_LEVEL;
        if (player.getLevel() > upto) {
            return;
        }
        PacketSendUtility.sendPacket(player, new SM_SERVER_IDS(new FastTrack(FastTrackConfig.FASTTRACK_SERVER_ID, true, 1, upto)));
    	}
    }

    public void handleMoveThere(Player player) {
    	UserManager.getInstance().isPlayerActive(player.getAcountName());
        TeleportService2.moveFastTrack(player, FastTrackConfig.FASTTRACK_SERVER_ID, false);
    }

    public void handleMoveBack(Player player) {
    	UserManager.getInstance().checkAllPlayers(World.getInstance().getAllPlayers().size());
        TeleportService2.moveFastTrack(player, FastTrackConfig.FASTTRACK_SERVER_ID, true);
    }

    public void checkFastTrackMove(Player player, int accId, boolean back) {
    	if (ServiceLogger.getFastTrackServer(player)) {
        if (back) {
            accountsOnFast.remove(accId);
            player.setOnFastTrack(false);
            UserManager.getInstance().Load();
            PacketSendUtility.sendYellowMessage(player, "You joined the standard server!");
            fastTrackBonus(player, true);
        } else {
            if (accountsOnFast.containsKey(accId)) {
                log.warn("Fast Track Service: Player " + player.getName() + " tried to move twice to ft server!");
                accountsOnFast.remove(accId);
                handleMoveBack(player);
                PacketSendUtility.sendYellowMessage(player, "You got teleported back to the normal server because you tried to enter the fast track server twice!");
            }

            if (accountsOnFast.containsKey(accId) && !accountsOnFast.containsValue(player)) {
                log.warn("Fast Track Service: Player " + player.getName() + " got wrong accid???");
                handleMoveBack(player);
                PacketSendUtility.sendYellowMessage(player, "You got teleported back to the normal server because something went wrong!");
            }

            accountsOnFast.put(accId, player);
            player.setOnFastTrack(true);
            PacketSendUtility.sendYellowMessage(player, "You joined the fast track server!");
            UserManager.getInstance().checkPlayer(World.getInstance().getAllPlayers().size());
            fastTrackBonus(player, false);
        }
    	}
    }
    
    public static FastTrackService getInstance() {
        return instance;
    }    
}