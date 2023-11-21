/**
 * This file is part of Aion-Lightning <aion-lightning.org>.
 * <p/>
 * Aion-Lightning is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * Aion-Lightning is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details. *
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with Aion-Lightning.
 * If not, see <http://www.gnu.org/licenses/>.
 * <p/>
 * <p/>
 * Credits goes to all Open Source Core Developer Groups listed below
 * Please do not change here something, ragarding the developer credits, except the "developed by XXXX".
 * Even if you edit a lot of files in this source, you still have no rights to call it as "your Core".
 * Everybody knows that this Emulator Core was developed by Aion Lightning
 *
 * @-Aion-Unique-
 * @-Aion-Lightning
 * @Aion-Engine
 * @Aion-Extreme
 * @Aion-NextGen
 * @Aion-Core Dev.
 */
package com.aionemu.gameserver.model.templates.item.actions;

import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.PacketSendUtility;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Kill3r
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExpReturnAction")
public class ExpReturnAction extends AbstractItemAction {

    @XmlAttribute
    protected int percent;

    @Override
    public boolean canAct(Player player, Item parentItem, Item targetItem) {
        int nameId = parentItem.getItemTemplate().getNameId();
        //int restrictLevelMin = parentItem.getItemTemplate().getMinLevelRestrict(player);
        byte restrictLevelMax = parentItem.getItemTemplate().getMaxLevelRestrict(player);

        if (restrictLevelMax != 0){
            PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_CANNOT_USE_ITEM_TOO_LOW_LEVEL_MUST_BE_THIS_LEVEL(restrictLevelMax, nameId));
            return false;
        }
        /*if (restrictLevelMin != 0){
            PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_CANNOT_USE_ITEM_TOO_HIGH_LEVEL(restrictLevelMin, nameId));
            return false;
        }*/
        return false;
    }

    @Override
    public void act(Player player, Item parentItem, Item targetItem) {
        long totalXp = player.getCommonData().getExpNeed();
        long currentXp = player.getCommonData().getExp();
        long calcuatedXp = 0;

        calcuatedXp = (int)(totalXp * 0.2);
        player.getCommonData().setExp(currentXp + calcuatedXp);
    }
}
