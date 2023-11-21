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
package admincommands;

import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.AdminService;
import com.aionemu.gameserver.services.BeritraService;
import com.aionemu.gameserver.services.item.ItemService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.Util;
import com.aionemu.gameserver.utils.chathandlers.AdminCommand;
import com.aionemu.gameserver.world.World;
import org.apache.commons.lang.math.NumberUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lyras
 */
public class Beritra extends AdminCommand {

    public Beritra() {
        super("beritra");
    }

    @Override
    public void execute(Player player, String... params) {
        if ((params.length < 0) || (params.length < 1)) {
            onFail(player, null);
            return;
        }

        if(params[0].equalsIgnoreCase("start"))
        {
            int loc = NumberUtils.toInt(params[1]);
            if(!isValidLoc(player, loc))
                return;
            if(!BeritraService.getInstance().isInvasionInProgress(loc))
                BeritraService.getInstance().startBeritraInvasion(loc);
            else
                PacketSendUtility.sendMessage(player, "Already under Invasion");
        }

        if(params[0].equalsIgnoreCase("stop"))
        {
            int loc = NumberUtils.toInt(params[1]);
            if(!isValidLoc(player, loc))
                return;
            if(BeritraService.getInstance().isInvasionInProgress(loc))
                BeritraService.getInstance().stopBeritraInvasion(loc);
            else
                PacketSendUtility.sendMessage(player, "Invasion already stopped");
        }

    }

    private boolean isValidLoc(Player player, int locId)
    {
        if(!BeritraService.getInstance().getBeritraLocations().keySet().contains(locId)) {
            PacketSendUtility.sendMessage(player, "Id " + locId + " is invalid");
            return false;
        }
        return true;
    }

    @Override
    public void onFail(Player player, String message) {
        PacketSendUtility.sendMessage(player, "syntax //beritra start|stop <locationId>");
    }
}
