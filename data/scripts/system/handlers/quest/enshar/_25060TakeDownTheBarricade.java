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

/**
 * @author pralinka
 */
public class _25060TakeDownTheBarricade extends QuestHandler {

    private final static int questId = 25060;

    public _25060TakeDownTheBarricade() {
        super(questId);
    }

    @Override
    public void register() {
        qe.registerQuestNpc(804730).addOnQuestStart(questId);
        qe.registerQuestNpc(804730).addOnTalkEvent(questId);
		qe.registerQuestNpc(220033).addOnKillEvent(questId);
		qe.registerQuestNpc(220034).addOnKillEvent(questId);
        qe.registerQuestNpc(220035).addOnKillEvent(questId);

    }

    @Override
    public boolean onKillEvent(QuestEnv env) {
        Player player = env.getPlayer();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        if (qs != null && qs.getStatus() == QuestStatus.START) {
            int var = qs.getQuestVarById(0);
            if (var == 0) {
                int targetId = env.getTargetId();
                int var1 = qs.getQuestVarById(1);
                int var2 = qs.getQuestVarById(2);
				int var3 = qs.getQuestVarById(3);
                switch (targetId) {
                    case 220033: {
                        if (var1 < 0) {
                            return defaultOnKillEvent(env, 220033, 0, 0, 1);
                        } else if (var1 == 0) {
                            if (var2 == 1 && var3 == 1) {
                                qs.setQuestVar(1);
								qs.setStatus(QuestStatus.REWARD);
								updateQuestStatus(env);
                                return true;
                            } else {
                                return defaultOnKillEvent(env, 220033, 0, 1, 1);
                            }
                        }
                        break;
                    }
                    case 220034: {
                        if (var2 < 0) {
                            return defaultOnKillEvent(env, 220034, 0, 0, 2);
                        } else if (var2 == 0) {
                            if (var1 == 1 && var3 == 1) {
                                qs.setQuestVar(1);
								qs.setStatus(QuestStatus.REWARD);
								updateQuestStatus(env);
                                return true;
                            } else {
                                return defaultOnKillEvent(env, 220034, 0, 1, 2);
                            }
                        }
                        break;
                    }
					case 220035: {
                        if (var3 < 0) {
                            return defaultOnKillEvent(env, 220035, 0, 0, 3);
                        } else if (var3 == 0) {
                            if (var1 == 1 && var2 == 1) {
                                qs.setQuestVar(1);
								qs.setStatus(QuestStatus.REWARD);
								updateQuestStatus(env);
                                return true;
                            } else {
                                return defaultOnKillEvent(env, 220035, 0, 1, 3);
                            }
                        }
                        break;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean onDialogEvent(QuestEnv env) {
        Player player = env.getPlayer();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        DialogAction dialog = env.getDialog();
        int targetId = env.getTargetId();

        if (qs == null || qs.getStatus() == QuestStatus.NONE || qs.canRepeat()) {
            if (targetId == 804730) {
                if (dialog == DialogAction.QUEST_SELECT) {
                    return sendQuestDialog(env, 4762);
                } else {
                    return sendQuestStartDialog(env);
                }
            }
        } else if (qs.getStatus() == QuestStatus.START) {
            if (targetId == 804730) {
                if (env.getDialog() == DialogAction.QUEST_SELECT) { 
                    qs.setStatus(QuestStatus.REWARD);
                    updateQuestStatus(env);
                    return sendQuestDialog(env, 1352);
                } else {
                    return sendQuestStartDialog(env);
                }
            }
        } else if (qs.getStatus() == QuestStatus.REWARD) {
            if (targetId == 804730) {
                if (dialog == DialogAction.USE_OBJECT) {
                    return sendQuestDialog(env, 10002);
                }
                return sendQuestEndDialog(env);
            }
        }
        return false;
    }
}
