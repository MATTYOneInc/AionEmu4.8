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
package quest.enshar;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.model.DialogAction;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.services.QuestService;

/**
 * @author pralinka
 */
public class _25030CluesFromTheUndead extends QuestHandler {

    private final static int questId = 25030;

    public _25030CluesFromTheUndead() {
        super(questId);
    }

    @Override
    public void register() {
        qe.registerQuestNpc(804729).addOnQuestStart(questId);
        qe.registerQuestNpc(804729).addOnTalkEvent(questId);
        qe.registerQuestNpc(804913).addOnTalkEvent(questId);
        qe.registerQuestNpc(804728).addOnTalkEvent(questId);
		qe.registerQuestNpc(804911).addOnTalkEvent(questId);
    }

    @Override
    public boolean onDialogEvent(QuestEnv env) {
        Player player = env.getPlayer();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        int targetId = env.getTargetId();
        DialogAction dialog = env.getDialog();

        if (qs == null || qs.getStatus() == QuestStatus.NONE) {
            if (targetId == 804729) {
                if (dialog == DialogAction.QUEST_SELECT) {
                    return sendQuestDialog(env, 4762);
                } else {
                    return sendQuestStartDialog(env);
                }
            }
        } else if (qs.getStatus() == QuestStatus.START) {
            int var = qs.getQuestVarById(0);
            if (targetId == 804729) {
                switch (dialog) {
                case QUEST_SELECT: {
                    if (var == 0) 
                        return sendQuestDialog(env, 1011);
                    if (var == 1) 
                        return sendQuestDialog(env, 1352);
                }    
                case CHECK_USER_HAS_QUEST_ITEM:
                    if (QuestService.collectItemCheck(env, true)) {
                        qs.setQuestVarById(0, var + 1);
                        updateQuestStatus(env);
                        return sendQuestDialog(env, 10000);
                    } else {
                        return sendQuestDialog(env, 10001);
                    }
                case SETPRO2:
                    if (var == 1) {
                        qs.setQuestVarById(0, var + 1);
                        updateQuestStatus(env);
						giveQuestItem(env, 182215713, 1);
                        return true;
                    }
                    return false;
				}
            } 
			if (targetId == 804913) { 
                if (dialog == DialogAction.QUEST_SELECT) {
                    if (var == 2) {
                        return sendQuestDialog(env, 1693);
                    }
                } else if (dialog == DialogAction.SETPRO3) {
					changeQuestStep(env, 2, 3, false); 
                    return closeDialogWindow(env);
                }
            }
			if (targetId == 804728) { 
                if (dialog == DialogAction.QUEST_SELECT) {
                    if (var == 3) {
                        return sendQuestDialog(env, 2034);
                    }
                } else if (dialog == DialogAction.SET_SUCCEED) {
					removeQuestItem(env, 182215713, 1);
                    changeQuestStep(env, 3, 4, true); 
                    return closeDialogWindow(env);
                }
            }
        } else if (qs.getStatus() == QuestStatus.REWARD) {
            if (targetId == 804911) {
                if (dialog == DialogAction.QUEST_SELECT) {
                    return sendQuestDialog(env, 2375);
                } else {
                    return sendQuestEndDialog(env);
                }
            }
        }
        return false;
    }
}
