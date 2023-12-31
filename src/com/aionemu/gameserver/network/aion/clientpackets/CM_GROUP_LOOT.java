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
package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.PacketLoggerService;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.services.drop.DropDistributionService;

/**
 * @author Rhys2002
 */
public class CM_GROUP_LOOT extends AionClientPacket {

    @SuppressWarnings("unused")
    private int groupId;
    private int index;
    @SuppressWarnings("unused")
    private int unk1;
    private int itemId;
    @SuppressWarnings("unused")
    private int unk2;
    @SuppressWarnings("unused")
    private int unk3;
    private int npcId;
    private int distributionId;
    private int roll;
    private long bid;
    @SuppressWarnings("unused")
    private int unk4;

    public CM_GROUP_LOOT(int opcode, State state, State... restStates) {
        super(opcode, state, restStates);
    }

    @Override
    protected void readImpl() {
        PacketLoggerService.getInstance().logPacketCM(this.getPacketName());
        groupId = readD();
        index = readD();
        unk1 = readD();
        itemId = readD();
        unk2 = readC();
        unk3 = readC(); // 3.0
        unk4 = readC(); // 3.5
        npcId = readD();
        distributionId = readC();// 2: Roll 3: Bid
        roll = readD();// 0: Never Rolled 1: Rolled
        bid = readQ();// 0: No Bid else bid amount
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void runImpl() {
        Player player = getConnection().getActivePlayer();
        if (player == null) {
            return;
        }
        switch (distributionId) {
            case 2:
                DropDistributionService.getInstance().handleRoll(player, roll, itemId, npcId, index);
                break;
            case 3:
                DropDistributionService.getInstance().handleBid(player, bid, itemId, npcId, index);
                break;
        }
    }
}
