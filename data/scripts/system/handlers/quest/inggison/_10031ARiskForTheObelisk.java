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
package quest.inggison;

import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.questEngine.QuestEngine;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.model.DialogAction;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.services.QuestService;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.utils.PacketSendUtility;

/**
 * @author pralinka
 */
public class _10031ARiskForTheObelisk extends QuestHandler {

    private final static int questId = 10031;
		private final static int[] mobs = {215504, 215505, 216463, 216464, 215516, 215517, 215508, 215509};
    public _10031ARiskForTheObelisk() {
        super(questId);
    }

    @Override
    public void register() {
        qe.registerOnEnterWorld(questId);
		int[] npcs = {203701, 798600, 798408, 798926, 799052, 798927, 730224, 702662}; 	
		qe.registerOnLevelUp(questId);
        qe.registerOnEnterWorld(questId);
		qe.registerQuestItem(182215590, questId);
        for (int npc : npcs) {
            qe.registerQuestNpc(npc).addOnTalkEvent(questId);
        }
		for (int mob : mobs) {
            qe.registerQuestNpc(mob).addOnKillEvent(questId);
        }
    }

	@Override
    public boolean onLvlUpEvent(QuestEnv env) {
        return defaultOnLvlUpEvent(env);
    } 
	
    @Override
    public boolean onEnterWorldEvent(QuestEnv env) {
        Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
        if (player.getWorldId() == 110010000) {            
            if (qs == null) {
                env.setQuestId(questId);
                if (QuestService.startQuest(env)) {
                    return true;
                }
            }			
        }
		else if (player.getWorldId() == 210050000) {
			if (qs != null && qs.getStatus() == QuestStatus.START) {
            int var = qs.getQuestVars().getQuestVars();
            if (var == 3) {
					qs.setQuestVar(++var);
					updateQuestStatus(env);
                    return playQuestMovie(env, 501);					
				}
			}
		}	
        return false;
    }

    @Override
    public boolean onDialogEvent(QuestEnv env) {
        final Player player = env.getPlayer();
        final QuestState qs = player.getQuestStateList().getQuestState(questId);
        if (qs == null) {
            return false;
        }
        int var = qs.getQuestVarById(0);
        int targetId = 0;
        if (env.getVisibleObject() instanceof Npc) {
            targetId = ((Npc) env.getVisibleObject()).getNpcId();
        }
        if (qs.getStatus() == QuestStatus.START) {
            if (targetId == 203701) {
                switch (env.getDialog()) {
                    case QUEST_SELECT:
                        if (var == 0) {
                            return sendQuestDialog(env, 1011);
                        }
                    case SETPRO1:
                        qs.setQuestVarById(0, var + 1);
						updateQuestStatus(env);
						PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
						return true;
                }
            }
            else if (targetId == 798600) {
                switch (env.getDialog()) {
                    case QUEST_SELECT:
                        if (var == 1) {
                            return sendQuestDialog(env, 1352);
                        }
                    case SETPRO2:
						giveQuestItem(env, 182215615, 1);
                        qs.setQuestVarById(0, var + 1);
						updateQuestStatus(env);
						PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
						return true;
                }				
            }
			else if (targetId == 798408) {
                switch (env.getDialog()) {
                    case QUEST_SELECT:
                        if (var == 2) {
                            return sendQuestDialog(env, 1693);
                        }
                    case SETPRO3:
                        qs.setQuestVarById(0, var + 1);
						updateQuestStatus(env);
						TeleportService2.teleportTo(player, 210050000, 1, 1440, 408, 553, (byte) 77);
						return true;
                }				
            }
			else if (targetId == 798926) {
                switch (env.getDialog()) {
                    case QUEST_SELECT:
                        if (var == 4) {
                            return sendQuestDialog(env, 2375);
                        }
                    case SETPRO5:
						removeQuestItem(env, 182215615, 1);
                        qs.setQuestVarById(0, var + 1);
						updateQuestStatus(env);
						PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
						return true;
                }				
            }
			else if (targetId == 799052) {
                switch (env.getDialog()) {
                    case QUEST_SELECT:
                        if (var == 5) {
                            return sendQuestDialog(env, 2716);
                        }
                    case SETPRO6:
                        qs.setQuestVarById(0, var + 1);
						updateQuestStatus(env);
						PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
						return true;
                }				
            }
			else if (targetId == 798927) {
                switch (env.getDialog()) {
                    case QUEST_SELECT:
                        if (var == 6) {
                            return sendQuestDialog(env, 3057);
                        }
                    case SETPRO7:
						giveQuestItem(env, 182215616, 1);
						giveQuestItem(env, 182215617, 1);
						playQuestMovie(env, 516);
                        qs.setQuestVarById(0, var + 1);
						updateQuestStatus(env);
						PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
						return true;
                }				
            }
			else if (targetId == 730224) {
                switch (env.getDialog()) {
				case QUEST_SELECT: {
                        if (var == 7) {
                            return sendQuestDialog(env, 3398);
                        }
					}
                    case SETPRO8:
						if (var == 7) {
						removeQuestItem(env, 182215616, 1);
                        qs.setQuestVarById(0, var + 1);
						updateQuestStatus(env);
						PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
						return true;
						}
                }				
            }
			else if (targetId == 702662) {
                switch (env.getDialog()) {
				case QUEST_SELECT: {
                         if (var == 10) {
                            return sendQuestDialog(env, 3399);
                        }
					}
					case SETPRO11:
						if (var == 10) {
						removeQuestItem(env, 182215617, 1);
						
                        qs.setQuestVarById(0, var + 1);
						updateQuestStatus(env);
						PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
						return true;
						}
                }				
            }
        }
		else if (qs.getStatus() == QuestStatus.REWARD) {
            if (targetId == 799225) {
                if (env.getDialog() == DialogAction.USE_OBJECT) {
                    return sendQuestDialog(env, 10002);
                } else if (env.getDialogId() == DialogAction.SELECT_QUEST_REWARD.id()) {
                    return sendQuestDialog(env, 5);
                } else {
                    return sendQuestEndDialog(env);
                }
            }
        }
        return false;
    }
}
