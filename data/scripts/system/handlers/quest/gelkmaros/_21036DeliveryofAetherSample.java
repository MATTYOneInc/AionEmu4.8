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
package quest.gelkmaros;

import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;

/**
 * @author VladimirZ
 * @modified Lilith
 */
public class _21036DeliveryofAetherSample extends QuestHandler {

    private final static int questId = 21036;

    public _21036DeliveryofAetherSample() {
        super(questId);
    }

    @Override
    public void register() {
        int[] npcs = {799258, 799238, 798713, 799239};
        for (int npc : npcs) {
            qe.registerQuestNpc(npc).addOnTalkEvent(questId);
        }
        qe.registerQuestNpc(799258).addOnQuestStart(questId);
    }

    @Override
    public boolean onDialogEvent(QuestEnv env) {
        if (sendQuestNoneDialog(env, 799258, 182207832, 1)) {
            return true;
        }

        QuestState qs = env.getPlayer().getQuestStateList().getQuestState(questId);
        if (qs == null) {
            return false;
        }

        int var = qs.getQuestVarById(0);
        if (qs.getStatus() == QuestStatus.START) {
            if (env.getTargetId() == 799238) {
                switch (env.getDialog()) {
                    case QUEST_SELECT:
                        if (var == 0) {
                            return sendQuestDialog(env, 1352);
                        }
                    case SETPRO1:
                        return defaultCloseDialog(env, 0, 1);
                }
            } else if (env.getTargetId() == 798713) {
                switch (env.getDialog()) {
                    case QUEST_SELECT:
                        if (var == 1) {
                            return sendQuestDialog(env, 1693);
                        }
                    case SETPRO2:
                        qs.setQuestVarById(0, qs.getQuestVarById(0) + 1);
                        qs.setStatus(QuestStatus.REWARD);
                        updateQuestStatus(env);
                        return defaultCloseDialog(env, 2, 3, true, false);
                }
            }
        }
        return sendQuestRewardDialog(env, 799239, 2375);
    }
}
