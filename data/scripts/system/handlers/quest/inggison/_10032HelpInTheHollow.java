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

import static com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE.STR_MSG_FULL_INVENTORY;

import com.aionemu.gameserver.model.DialogAction;
import com.aionemu.gameserver.model.TeleportAnimation;
import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.HandlerResult;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.services.instance.InstanceService;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.WorldMapInstance;

/**
 * @author pralinka
 */
 
public class _10032HelpInTheHollow extends QuestHandler {

	private final static int questId = 10032;

	public _10032HelpInTheHollow() {
		super(questId);
	}
	
	@Override
	public void register() {
		int[] npc_ids = {798952, 798954, 799022, 799503};
		for (int npc_id : npc_ids) {
			qe.registerQuestNpc(npc_id).addOnTalkEvent(questId);
		}
		qe.registerQuestItem(182215618, questId);
		qe.registerQuestItem(182215619, questId);
		qe.registerOnEnterZoneMissionEnd(questId);
		qe.registerOnLevelUp(questId);
		qe.registerOnEnterWorld(questId);
		qe.registerOnDie(questId);
		qe.registerOnLogOut(questId);
		
		
	}

	@Override
	public boolean onZoneMissionEndEvent(QuestEnv env) {
		return defaultOnZoneMissionEndEvent(env);
	}

	@Override
	public boolean onLvlUpEvent(QuestEnv env) {
		return defaultOnLvlUpEvent(env, 10031, true);
	}

	@Override
	public boolean onDialogEvent(final QuestEnv env) {
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

		if (qs.getStatus() == QuestStatus.REWARD) {
			if (targetId == 798952) {
				if (env.getDialog() == DialogAction.QUEST_SELECT) {
					return sendQuestDialog(env, 10002);
				} else {
					return sendQuestEndDialog(env);
				}
			}
			return false;
		} else if (qs.getStatus() != QuestStatus.START) {
			return false;
		}
		if (targetId == 798952) { 
			switch (env.getDialog()) {
				case QUEST_SELECT:{
					if (var == 0) {
						return sendQuestDialog(env, 1011);			
					}
				}
				case SETPRO1:
					return defaultCloseDialog(env, 0, 1);
				default:
					break;
			}
		} else if (targetId == 798954) { 
			switch (env.getDialog()) {
				case QUEST_SELECT:
					if (var == 1) {
						return sendQuestDialog(env, 1352);					
					}
					case SETPRO2: 
						return defaultCloseDialog(env, 1, 2);
					default:
						break;
			}
		} else if (targetId == 799503) { 
			switch (env.getDialog()) {
				case QUEST_SELECT:
					if (var == 6) {
					removeQuestItem(env, 182215618, 1);
					removeQuestItem(env, 182215619, 1);
					TeleportService2.teleportTo(env.getPlayer(), 210050000, 2649, 340, 511, (byte) 92, TeleportAnimation.BEAM_ANIMATION);
                    return checkQuestItems(env, 6, 7, true, 10000, 10001);
                        }
                case FINISH_DIALOG: {
                    return sendQuestSelectionDialog(env);
                }	
			}
		} else if (targetId == 799022) { 
			switch (env.getDialog()) {
				case QUEST_SELECT: {
                    if (var == 2)
                        return sendQuestDialog(env, 1693);
                    }
					case SETPRO3:
					if (player.isInGroup2()) {
						return sendQuestDialog(env, 2034);
					} else {
						if (giveQuestItem(env, 182215618, 1) && giveQuestItem(env, 182215619, 1)) {
							WorldMapInstance newInstance = InstanceService.getNextAvailableInstance(300190000);
							InstanceService.registerPlayerWithInstance(newInstance, player);
							TeleportService2.teleportTo(player, 300190000, newInstance.getInstanceId(), 202.26694f, 226.0532f, 1098.236f, (byte) 30);
							changeQuestStep(env, 2, 3, false);
							return closeDialogWindow(env);
						} else {
							PacketSendUtility.sendPacket(player, STR_MSG_FULL_INVENTORY);
							return sendQuestSelectionDialog(env);
						}
					}
			}
		}
		return false;
	}

	@Override
	public HandlerResult onItemUseEvent(final QuestEnv env, Item item) {
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if (qs != null && qs.getStatus() == QuestStatus.START) {
			if (player.getWorldId() == 300190000) {
				int itemId = item.getItemId();
				int var = qs.getQuestVarById(0);
				int var1 = qs.getQuestVarById(1);
				if (itemId == 182215618) { // Taloc Fruit
					changeQuestStep(env, 4, 5, false); // 5
					return HandlerResult.SUCCESS; 
				} else if (itemId == 182215619) { // Taloc's Tears
					if (var == 5) {
						if (var1 >= 0 && var1 < 19) {
							changeQuestStep(env, var1, var1 + 1, false, 1);
							return HandlerResult.SUCCESS;
						} else if (var1 == 19) {
							qs.setQuestVar(6);
							updateQuestStatus(env);
							return HandlerResult.SUCCESS;
						}
					}
				}
			}
		}
		return HandlerResult.UNKNOWN;
	}

	@Override
	public boolean onEnterWorldEvent(QuestEnv env) {
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if (qs != null && qs.getStatus() == QuestStatus.START) {
			if (player.getWorldId() != 300190000) {
				int var = qs.getQuestVarById(0);
				if (var >= 4 && var < 6) {
					removeQuestItem(env, 182215618, 1);
					removeQuestItem(env, 182215619, 1);
					qs.setQuestVar(2);
					updateQuestStatus(env);
					return true;
				}
			}
			else if (player.getWorldId() == 300190000) {
				int var = qs.getQuestVarById(0);
				if (var == 3) {
					qs.setQuestVar(4);
					updateQuestStatus(env);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean onDieEvent(QuestEnv env) {
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if (qs != null && qs.getStatus() == QuestStatus.START) {
			int var = qs.getQuestVarById(0);
			if (var >= 4 && var < 6) {
				removeQuestItem(env, 182215618, 1);
				removeQuestItem(env, 182215619, 1);
				qs.setQuestVar(2);
				updateQuestStatus(env);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean onLogOutEvent(QuestEnv env) {
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if (qs != null && qs.getStatus() == QuestStatus.START) {
			int var = qs.getQuestVarById(0);
			if (var >= 4 && var < 6) {
				removeQuestItem(env, 182215618, 1);
				removeQuestItem(env, 182215619, 1);
				qs.setQuestVar(2);
				updateQuestStatus(env);
				return true;
			}
		}
		return false;
	}
}
