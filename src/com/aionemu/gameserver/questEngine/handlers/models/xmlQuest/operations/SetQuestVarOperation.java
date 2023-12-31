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
package com.aionemu.gameserver.questEngine.handlers.models.xmlQuest.operations;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_QUEST_ACTION;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.utils.PacketSendUtility;

/**
 * @author Mr. Poke
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SetQuestVarOperation")
public class SetQuestVarOperation extends QuestOperation {

    @XmlAttribute(name = "var_id", required = true)
    protected int varId;
    @XmlAttribute(required = true)
    protected int value;

    /*
     * (non-Javadoc)
     * @see
     * com.aionemu.gameserver.questEngine.handlers.models.xmlQuest.operations.QuestOperation#doOperate(com.aionemu.gameserver
     * .questEngine.model.QuestEnv)
     */
    @Override
    public void doOperate(QuestEnv env) {
        Player player = env.getPlayer();
        int questId = env.getQuestId();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        if (qs != null) {
            qs.getQuestVars().setVarById(varId, value);
            PacketSendUtility.sendPacket(player, new SM_QUEST_ACTION(questId, qs.getStatus(), qs.getQuestVars()
                    .getQuestVars()));
        }
    }
}
