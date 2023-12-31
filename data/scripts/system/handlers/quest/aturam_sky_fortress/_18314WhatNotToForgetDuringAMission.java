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
package quest.aturam_sky_fortress;

import java.util.Collections;

import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.quest.QuestItems;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.model.DialogAction;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.services.QuestService;
import com.aionemu.gameserver.services.item.ItemService;

/**
 * @author pralinka
 */
public class _18314WhatNotToForgetDuringAMission extends QuestHandler {

    private final static int questId = 18314;

    public _18314WhatNotToForgetDuringAMission() {
        super(questId);
    }

    @Override
    public void register() {
        qe.registerQuestNpc(799530).addOnQuestStart(questId);
        qe.registerQuestNpc(799530).addOnTalkEvent(questId);
        qe.registerQuestNpc(730373).addOnTalkEvent(questId);
    }

    @Override
    public boolean onDialogEvent(QuestEnv env) {
        Player player = env.getPlayer();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        int targetId = env.getTargetId();

        if (qs == null || qs.getStatus() == QuestStatus.NONE) {
            if (targetId == 799530) {
                if (env.getDialog() == DialogAction.QUEST_SELECT) {
                    return sendQuestDialog(env, 4762);
                } else if (env.getDialog() == DialogAction.QUEST_ACCEPT_1) {
                    playQuestMovie(env, 469);
                    return sendQuestStartDialog(env);
                } else {
                    return sendQuestStartDialog(env);
                }
            }
        }
        if (qs == null) {
            return false;
        }
        int var = qs.getQuestVarById(0);
		int var1 = qs.getQuestVarById(1);
        if (qs.getStatus() == QuestStatus.START) {
            if (targetId == 730373) {
                switch (env.getDialog()) {
                    case USE_OBJECT:
                    if (var == 0) {
						if (var1 >= 0 && var1 < 6) {
							if (env.getVisibleObject() instanceof Npc) {
                            targetId = ((Npc) env.getVisibleObject()).getNpcId();
                            Npc npc = (Npc) env.getVisibleObject();
                            npc.getController().onDelete();
                            changeQuestStep(env, var1, var1 + 1, false, 1);
                            updateQuestStatus(env);
                            return closeDialogWindow(env);
							} 
						} else if (var1 == 6) {
							qs.setQuestVar(0);
                            Npc npc = (Npc) env.getVisibleObject();
                            npc.getController().onDelete();
							qs.setStatus(QuestStatus.REWARD);
							updateQuestStatus(env);
							return closeDialogWindow(env);
							}
						}
					}
                }
            }  else if (qs.getStatus() == QuestStatus.REWARD) {
				if (targetId == 799530) {
                switch (env.getDialog()) {
                    case USE_OBJECT:
                        return sendQuestDialog(env, 10002);
                    case SELECT_QUEST_REWARD:
                        return sendQuestDialog(env, 5);
                    default:
                        return sendQuestEndDialog(env);
                }
            }
        }
        return false;
    }
}
