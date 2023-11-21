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
package com.aionemu.gameserver.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aionemu.gameserver.configs.main.MembershipConfig;
import com.aionemu.gameserver.model.templates.item.ItemTemplate;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.DescriptionId;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.player.Equipment;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.items.ItemSlot;
import com.aionemu.gameserver.model.templates.item.RequireSkill;
import com.aionemu.gameserver.model.templates.item.Stigma;
import com.aionemu.gameserver.model.templates.item.Stigma.StigmaSkill;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.audit.AuditLogger;

/**
 * @author ATracer
 * @modified cura
 * @updated ever & Kill3r 4.8
 */
public class StigmaService {

    private static final Logger log = LoggerFactory.getLogger(StigmaService.class);

    /*public static boolean extendAdvancedStigmaSlots(Player player) {
     int newAdvancedSlotSize = player.getCommonData().getAdvancedStigmaSlotSize() + 1;
     if (newAdvancedSlotSize <= 6) { // maximum
     player.getCommonData().setAdvancedStigmaSlotSize(newAdvancedSlotSize);
     PacketSendUtility.sendPacket(player, SM_CUBE_UPDATE.stigmaSlots(player.getCommonData().getAdvancedStigmaSlotSize()));
     return true;
     }
     return false;
     }*/
    /**
     * @param player
     * @param resultItem
     * @param slot
     * @return
     */
    public static boolean notifyEquipAction(Player player, Item resultItem, long slot) {
        if (resultItem.getItemTemplate().isStigma()) {
            if (ItemSlot.isRegularStigma(slot)) {
                // check the number of stigma wearing
                if (getPossibleStigmaCount(player) <= player.getEquipment().getEquippedItemsRegularStigma().size()) {
                    AuditLogger.info(player, "Possible client hack stigma count big :O");
                    return false;
                }
            } else if (ItemSlot.isAdvancedStigma(slot)) {
                // check the number of advanced stigma wearing
                if (getPossibleAdvencedStigmaCount(player) <= player.getEquipment().getEquippedItemsAdvencedStigma().size()) {
                    AuditLogger.info(player, "Possible client hack advanced stigma count big :O");
                    return false;
                }
            } else if (ItemSlot.isMajorStigma(slot)) {
                // check the number of major stigma wearing
                if (getPossibleMajorStigmaCount(player) <= player.getEquipment().getEquippedItemsMajorStigma().size()) {
                    AuditLogger.info(player, "Possible client hack advanced stigma count big :O");
                    return false;
                }
            }

            if (resultItem.getItemTemplate().isClassSpecific(player.getCommonData().getPlayerClass()) == false) {
                AuditLogger.info(player, "Possible client hack not valid for class.");
                return false;
            }

            // You cannot equip 2 stigma skills at 1 slot , was possible before.. o.o
            if (!StigmaService.isPossibleEquippedStigma(player, resultItem)) {
                AuditLogger.info(player, "Player tried to get Multiple Stigma's from One Stigma Stone!");
                return false;
            }

            Stigma stigmaInfo = resultItem.getItemTemplate().getStigma();

            if (stigmaInfo == null) {
                log.warn("Stigma info missing for item: " + resultItem.getItemTemplate().getTemplateId());
                return false;
            }

            int kinahCount = stigmaInfo.getKinah();
            if (player.getInventory().getKinah() < kinahCount) {
                AuditLogger.info(player, "Possible client hack kinah count low.");
                return false;
            }

            if (!player.getInventory().tryDecreaseKinah(kinahCount)){
                return false;
            }

            player.getSkillList().addStigmaSkill(player, stigmaInfo.getSkills(), true);

            List<Integer> sStigma = player.getEquipment().getEquippedItemsAllStigmaIds();
            sStigma.add(resultItem.getItemId()); // The last item ur about to add is not in getEquippedItemsAllStigma , so adding manual

            checkForLinkStigmaAvailable(player, sStigma);

            /*int neededSkillsCount = stigmaInfo.getRequireSkill().size();
             for (RequireSkill rs : stigmaInfo.getRequireSkill()) {
             for (int id : rs.getSkillIds()) {
             if (player.getSkillList().isSkillPresent(id)) {
             neededSkillsCount--;
             break;
             }
             }
             }
             if (neededSkillsCount != 0) {
             AuditLogger.info(player, "Possible client hack advenced stigma skill.");
             return false;
             }

             if (!player.getInventory().decreaseByItemId(182400001, kinahCount)) {
             return false;
             }
             player.getSkillList().addStigmaSkill(player, stigmaInfo.getSkills(), true);*/
        }
        return true;
    }

    /**
     * @param player
     * @param resultItem
     * @return
     */
    public static boolean notifyUnequipAction(Player player, Item resultItem) {
        if (resultItem.getItemTemplate().isStigma()) {
            Stigma stigmaInfo = resultItem.getItemTemplate().getStigma();
            int itemId = resultItem.getItemId();
            Equipment equipment = player.getEquipment();
            if (itemId == 140000007 || itemId == 140000005) {
                if (equipment.hasDualWeaponEquipped(ItemSlot.LEFT_HAND)) {
                    PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_STIGMA_CANNT_UNEQUIP_STONE_FIRST_UNEQUIP_CURRENT_EQUIPPED_ITEM);
                    return false;
                }
            }
            for (Item item : player.getEquipment().getEquippedItemsAllStigma()) {
                Stigma si = item.getItemTemplate().getStigma();
                if (resultItem == item || si == null) {
                    continue;
                }

                for (StigmaSkill sSkill : stigmaInfo.getSkills()) {
                    for (RequireSkill rs : si.getRequireSkill()) {
                        if (rs.getSkillIds().contains(sSkill.getSkillId())) {
                            PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1300410, new DescriptionId(resultItem
                                    .getItemTemplate().getNameId()), new DescriptionId(item.getItemTemplate().getNameId())));
                            return false;
                        }
                    }
                }
            }

            for (StigmaSkill sSkill : stigmaInfo.getSkills()) {
                int nameId = DataManager.SKILL_DATA.getSkillTemplate(sSkill.getSkillId()).getNameId();
                PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1300403, new DescriptionId(nameId)));

                // remove skill
                if ((player.getEquipment().getEquippedItemsRegularStigma().size() < 6) && (player.getLinkedSkill() != 0)) {
                    SkillLearnService.removeLinkedSkill(player, player.getLinkedSkill());
                    SkillLearnService.removeSkill(player, player.getLinkedSkill());
                } else {
                    SkillLearnService.removeSkill(player, sSkill.getSkillId());
                }
                // remove effect
                player.getEffectController().removeEffect(sSkill.getSkillId());
            }

            /*for (StigmaSkill sSkill : stigmaInfo.getSkills()) {
             int nameId = DataManager.SKILL_DATA.getSkillTemplate(sSkill.getSkillId()).getNameId();
             PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1300403, new DescriptionId(nameId)));

             SkillLearnService.removeSkill(player, sSkill.getSkillId());
             //remove effect
             player.getEffectController().removeEffect(sSkill.getSkillId());
             }*/
        }
        return true;
    }

    /**
     * @param player
     */
    public static void onPlayerLogin(Player player) {
        List<Item> equippedItems = player.getEquipment().getEquippedItemsAllStigma();
        List<Integer> equippedStigmaId =  player.getEquipment().getEquippedItemsAllStigmaIds();
        for (Item item : equippedItems) { // All Equipped Items are Stigmas
            if (item.getItemTemplate().isStigma()) {
                Stigma stigmaInfo = item.getItemTemplate().getStigma();

                if (stigmaInfo == null) {
                    log.warn("Stigma info missing for item: " + item.getItemTemplate().getTemplateId());
                    return;
                }
                player.getSkillList().addStigmaSkill(player, stigmaInfo.getSkills(), false);
            }
        }

        checkForLinkStigmaAvailable(player, equippedStigmaId);

        for (Item item : equippedItems) {
            if (item.getItemTemplate().isStigma()) {
                if (!isPossibleEquippedStigma(player, item)) {
                    AuditLogger.info(player, "Possible client hack stigma count big :O");
                    player.getEquipment().unEquipItem(item.getObjectId(), 0);
                    continue;
                }

                Stigma stigmaInfo = item.getItemTemplate().getStigma();

                if (stigmaInfo == null) {
                    log.warn("Stigma info missing for item: " + item.getItemTemplate().getTemplateId());
                    player.getEquipment().unEquipItem(item.getObjectId(), 0);
                    continue;
                }

                /*int needSkill = stigmaInfo.getRequireSkill().size();
                 for (RequireSkill rs : stigmaInfo.getRequireSkill()) {
                 for (int id : rs.getSkillIds()) {
                 if (player.getSkillList().isSkillPresent(id)) {
                 needSkill--;
                 break;
                 }
                 }
                 }
                 if (needSkill != 0) {
                 AuditLogger.info(player, "Possible client hack advenced stigma skill.");
                 player.getEquipment().unEquipItem(item.getObjectId(), 0);
                 continue;
                 }*/
                if (item.getItemTemplate().isClassSpecific(player.getCommonData().getPlayerClass()) == false) {
                    AuditLogger.info(player, "Possible client hack not valid for class.");
                    player.getEquipment().unEquipItem(item.getObjectId(), 0);
                    continue;
                }
            }
        }
    }

    /**
     * Get the number of available Stigma
     *
     * @param player
     * @return
     */
    private static int getPossibleStigmaCount(Player player) {
        if (player == null || player.getLevel() < 20) {
            return 0;
        }

        if (player.havePermission(MembershipConfig.STIGMA_SLOT_QUEST)) {
            return 3;
        }

        /*
         * Stigma Quest Elyos: 1929, Asmodians: 2900
         */
        boolean isCompleteQuest = false;

        if (player.getRace() == Race.ELYOS) {
            isCompleteQuest = player.isCompleteQuest(1929)
                    || (player.getQuestStateList().getQuestState(1929).getStatus() == QuestStatus.START && player.getQuestStateList().getQuestState(1929).getQuestVars().getQuestVars() == 98);
        } else {
            isCompleteQuest = player.isCompleteQuest(2900)
                    || (player.getQuestStateList().getQuestState(2900).getStatus() == QuestStatus.START && player.getQuestStateList().getQuestState(2900).getQuestVars().getQuestVars() == 99);
        }

        int playerLevel = player.getLevel();

        if (isCompleteQuest) {
            if (playerLevel <= 20) {
                return 1;
            } else if (playerLevel <= 30) {
                return 2;
            } else if (playerLevel <= 40) {
                return 3;
            } else {
                return 3;
            }
        }
        return 0;
    }

    /**
     * Get the number of available Advenced Stigma
     *
     * @param player
     * @return
     */
    private static int getPossibleAdvencedStigmaCount(Player player) {
        if (player == null || player.getLevel() < 45) {
            return 0;
        }

        if (player.havePermission(MembershipConfig.STIGMA_SLOT_QUEST)) {
            return 2;
        }

        /*
         * Stigma Quest Elyos: 1929, Asmodians: 2900
         */
        boolean isCompleteQuest = false;

        if (player.getRace() == Race.ELYOS) {
            isCompleteQuest = player.isCompleteQuest(1929)
                    || (player.getQuestStateList().getQuestState(1929).getStatus() == QuestStatus.START && player.getQuestStateList().getQuestState(1929).getQuestVars().getQuestVars() == 98);
        } else {
            isCompleteQuest = player.isCompleteQuest(2900)
                    || (player.getQuestStateList().getQuestState(2900).getStatus() == QuestStatus.START && player.getQuestStateList().getQuestState(2900).getQuestVars().getQuestVars() == 99);
        }

        int playerLevel = player.getLevel();

        if (isCompleteQuest) {
            if (playerLevel <= 45) {
                return 1;
            } else if (playerLevel <= 50) {
                return 2;
            } else {
                return 2;
            }
        }
        return 0;
    }

    /**
     * Get the number of available Major Stigma
     *
     * @param player
     * @return
     */
    private static int getPossibleMajorStigmaCount(Player player) {
        if (player == null || player.getLevel() < 55) {
            return 0;
        }

        if (player.havePermission(MembershipConfig.STIGMA_SLOT_QUEST)) {
            return 1;
        }

        /*
         * Stigma Quest Elyos: 1929, Asmodians: 2900
         */
        boolean isCompleteQuest = false;

        if (player.getRace() == Race.ELYOS) {
            isCompleteQuest = player.isCompleteQuest(1929)
                    || (player.getQuestStateList().getQuestState(1929).getStatus() == QuestStatus.START && player.getQuestStateList().getQuestState(1929).getQuestVars().getQuestVars() == 98);
        } else {
            isCompleteQuest = player.isCompleteQuest(2900)
                    || (player.getQuestStateList().getQuestState(2900).getStatus() == QuestStatus.START && player.getQuestStateList().getQuestState(2900).getQuestVars().getQuestVars() == 99);
        }

        int playerLevel = player.getLevel();

        if (isCompleteQuest) {
            if (playerLevel >= 55) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * Stigma is a worn check available slots
     *
     * @param player
     * @param item
     * @return
     */
    private static boolean isPossibleEquippedStigma(Player player, Item item) {
        if (player == null || (item == null || !item.getItemTemplate().isStigma())) {
            return false;
        }

        long itemSlotToEquip = item.getEquipmentSlot();

        // Stigma
        if (ItemSlot.isRegularStigma(itemSlotToEquip)) {
            int stigmaCount = getPossibleStigmaCount(player);

            if (stigmaCount > 0) {
                if (stigmaCount == 1) {
                    if (itemSlotToEquip == ItemSlot.STIGMA1.getSlotIdMask()) {
                        return true;
                    }
                } else if (stigmaCount == 2) {
                    if (itemSlotToEquip == ItemSlot.STIGMA1.getSlotIdMask() ||
                            itemSlotToEquip == ItemSlot.STIGMA2.getSlotIdMask()) {
                        return true;
                    }
                } else if (stigmaCount == 3) {
                    return true;
                }
            }
        } // Advenced Stigma
        else if (ItemSlot.isAdvancedStigma(itemSlotToEquip)) {
            int advStigmaCount = getPossibleAdvencedStigmaCount(player);

            if (advStigmaCount > 0) {
                if (advStigmaCount == 1) {
                    if (itemSlotToEquip == ItemSlot.ADV_STIGMA1.getSlotIdMask()) {
                        return true;
                    }
                } else if (advStigmaCount == 2) {
                    return true;
                }
            }
        } // Major Stigma
        else if (ItemSlot.isMajorStigma(itemSlotToEquip)) {
            int majStigmaCount = getPossibleMajorStigmaCount(player);
            if (majStigmaCount > 0) {
                if (majStigmaCount == 1) {
                    if (itemSlotToEquip == ItemSlot.MAJ_STIGMA.getSlotIdMask()) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    public static void checkForLinkStigmaAvailable(Player player, List<Integer> sStigma) {
        boolean hasInert = false;

        for (Integer in : sStigma){ // if Inert Stigma socketed, Cannot get Link
            ItemTemplate it = DataManager.ITEM_DATA.getItemTemplate(in);
            if (it.getName().contains("(Inert)")){
                hasInert = true;
            }
        }

        switch (player.getPlayerClass()) {
            case GLADIATOR:
                if ((sStigma.size() == 6) && !hasInert) {
                    if (((sStigma.contains(140001119)) && (sStigma.contains(140001106)) && ((sStigma.contains(140001108))
                            || (sStigma.contains(140001107)))) || ((sStigma.contains(140001108)) && (sStigma.contains(140001107)))) {
                        player.getSkillList().addLinkedSkill(player, 641, 1);
                    } else if (((sStigma.contains(140001118)) && (sStigma.contains(140001104)) && ((sStigma.contains(140001103))
                            || (sStigma.contains(140001105)))) || ((sStigma.contains(140001103)) && (sStigma.contains(140001105)))) {
                        player.getSkillList().addLinkedSkill(player, 727, 1);
                    } else {
                        player.getSkillList().addLinkedSkill(player, 657, 1);
                    }
                }
                return;
            case TEMPLAR:
                if ((sStigma.size() == 6) && !hasInert) {
                    if (((sStigma.contains(140001134)) && (sStigma.contains(140001122)) && ((sStigma.contains(140001120))
                            || (sStigma.contains(140001125)))) || ((sStigma.contains(140001120)) && (sStigma.contains(140001125)))) {
                        player.getSkillList().addLinkedSkill(player, 2919, 1);
                    } else if (((sStigma.contains(140001135)) && (sStigma.contains(140001123)) && ((sStigma.contains(140001124))
                            || (sStigma.contains(140001121)))) || ((sStigma.contains(140001124)) && (sStigma.contains(140001121)))) {
                        player.getSkillList().addLinkedSkill(player, 2918, 1);
                    } else {
                        player.getSkillList().addLinkedSkill(player, 2915, 1);
                    }
                }
                return;
            case ASSASSIN:
                if ((sStigma.size() == 6) && !hasInert) {
                    if (((sStigma.contains(140001152)) && (sStigma.contains(140001138)) && ((sStigma.contains(140001139))
                            || (sStigma.contains(140001141)))) || ((sStigma.contains(140001139)) && (sStigma.contains(140001141)))) {
                        player.getSkillList().addLinkedSkill(player, 3326, 1);
                    } else if (((sStigma.contains(140001151)) && (sStigma.contains(140001136)) && ((sStigma.contains(140001140))
                            || (sStigma.contains(140001137)))) || ((sStigma.contains(140001140)) && (sStigma.contains(140001137)))) {
                        player.getSkillList().addLinkedSkill(player, 3239, 1);
                    } else {
                        player.getSkillList().addLinkedSkill(player, 3242, 1);
                    }
                }
                return;
            case RANGER:
                if ((sStigma.size() == 6) && !hasInert) {
                    if (((sStigma.contains(140001172)) && (sStigma.contains(140001155)) && ((sStigma.contains(140001157))
                            || (sStigma.contains(140001153)))) || ((sStigma.contains(140001157)) && (sStigma.contains(140001153)))) {
                        player.getSkillList().addLinkedSkill(player, 1006, 1);
                    } else if (((sStigma.contains(140001173)) && (sStigma.contains(140001154)) && ((sStigma.contains(140001158))
                            || (sStigma.contains(140001156)))) || ((sStigma.contains(140001158)) && (sStigma.contains(140001156)))) {
                        player.getSkillList().addLinkedSkill(player, 936, 1);
                    } else {
                        player.getSkillList().addLinkedSkill(player, 1061, 1);
                    }
                }
                return;
            case SORCERER:
                if ((sStigma.size() == 6) && !hasInert) {
                    if (((sStigma.contains(140001191)) && (sStigma.contains(140001174)) && ((sStigma.contains(140001181))
                            || (sStigma.contains(140001178)))) || ((sStigma.contains(140001181)) && (sStigma.contains(140001178)))) {
                        player.getSkillList().addLinkedSkill(player, 1340, 1);
                    } else if (((sStigma.contains(140001192)) && (sStigma.contains(140001176)) && ((sStigma.contains(140001177))
                            || (sStigma.contains(140001184)))) || ((sStigma.contains(140001177)) && (sStigma.contains(140001184)))) {
                        player.getSkillList().addLinkedSkill(player, 1540, 1);
                    } else {
                        player.getSkillList().addLinkedSkill(player, 1418, 1);
                    }
                }
                return;
            case SPIRIT_MASTER:
                if ((sStigma.size() == 6) && !hasInert) {
                    if (((sStigma.contains(140001209)) && (sStigma.contains(140001195)) && ((sStigma.contains(140001193))
                            || (sStigma.contains(140001194)))) || ((sStigma.contains(140001193)) && (sStigma.contains(140001194)))) {
                        player.getSkillList().addLinkedSkill(player, 3541, 1);
                    } else if (((sStigma.contains(140001210)) && (sStigma.contains(140001199)) && ((sStigma.contains(140001197))
                            || (sStigma.contains(140001196)))) || ((sStigma.contains(140001197)) && (sStigma.contains(140001196)))) {
                        player.getSkillList().addLinkedSkill(player, 3549, 1);
                    } else {
                        player.getSkillList().addLinkedSkill(player, 3849, 1);
                    }
                }
                return;
            case CLERIC:
                if ((sStigma.size() == 6) && !hasInert) {
                    if (((sStigma.contains(140001246)) && (sStigma.contains(140001234)) && ((sStigma.contains(140001232))
                            || (sStigma.contains(140001233)))) || ((sStigma.contains(140001232)) && (sStigma.contains(140001233)))) {
                        player.getSkillList().addLinkedSkill(player, 3932, 1);
                    } else if (((sStigma.contains(140001245)) && (sStigma.contains(140001229)) && ((sStigma.contains(140001228))
                            || (sStigma.contains(140001230)))) || ((sStigma.contains(140001228)) && (sStigma.contains(140001230)))) {
                        player.getSkillList().addLinkedSkill(player, 4167, 1);
                    } else {
                        player.getSkillList().addLinkedSkill(player, 3906, 1);
                    }
                }
                return;
            case CHANTER:
                if ((sStigma.size() == 6) && !hasInert) {
                    if (((sStigma.contains(140001226)) && (sStigma.contains(140001212)) && ((sStigma.contains(140001213))
                            || (sStigma.contains(140001211)))) || ((sStigma.contains(140001213)) && (sStigma.contains(140001211)))) {
                        player.getSkillList().addLinkedSkill(player, 1907, 1);
                    } else if (((sStigma.contains(140001227)) && (sStigma.contains(140001214)) && ((sStigma.contains(140001216))
                            || (sStigma.contains(140001215)))) || ((sStigma.contains(140001216)) && (sStigma.contains(140001215)))) {
                        player.getSkillList().addLinkedSkill(player, 1901, 1);
                    } else {
                        player.getSkillList().addLinkedSkill(player, 1904, 1);
                    }
                }
                return;
            case RIDER:
                if ((sStigma.size() == 6) && !hasInert) {
                    if (((sStigma.contains(140001279)) && (sStigma.contains(140001264)) && ((sStigma.contains(140001269))
                            || (sStigma.contains(140001265)))) || ((sStigma.contains(140001269)) && (sStigma.contains(140001265)))) {
                        player.getSkillList().addLinkedSkill(player, 2852, 1);
                    } else if (((sStigma.contains(140001280)) && (sStigma.contains(140001266)) && ((sStigma.contains(140001268))
                            || (sStigma.contains(140001267)))) || ((sStigma.contains(140001268)) && (sStigma.contains(140001267)))) {
                        player.getSkillList().addLinkedSkill(player, 2861, 1);
                    } else {
                        player.getSkillList().addLinkedSkill(player, 2849, 1);
                    }
                }
                return;
            case GUNNER:
                if ((sStigma.size() == 6) && !hasInert) {
                    if (((sStigma.contains(140001262)) && (sStigma.contains(140001249)) && ((sStigma.contains(140001247))
                            || (sStigma.contains(140001248)))) || ((sStigma.contains(140001247)) && (sStigma.contains(140001248)))) {
                        player.getSkillList().addLinkedSkill(player, 2368, 1);
                    } else if (((sStigma.contains(140001263)) && (sStigma.contains(140001251)) && ((sStigma.contains(140001252))
                            || (sStigma.contains(140001250)))) || ((sStigma.contains(140001252)) && (sStigma.contains(140001250)))) {
                        player.getSkillList().addLinkedSkill(player, 2371, 1);
                    } else {
                        player.getSkillList().addLinkedSkill(player, 2380, 1);
                    }
                }
                return;
            case BARD:
                if ((sStigma.size() == 6) && !hasInert) {
                    if (((sStigma.contains(140001297)) && (sStigma.contains(140001285)) && ((sStigma.contains(140001283))
                            || (sStigma.contains(140001286)))) || ((sStigma.contains(140001283)) && (sStigma.contains(140001286)))) {
                        player.getSkillList().addLinkedSkill(player, 4483, 1);
                    } else if (((sStigma.contains(140001296)) && (sStigma.contains(140001281)) && ((sStigma.contains(140001284))
                            || (sStigma.contains(140001282)))) || ((sStigma.contains(140001284)) && (sStigma.contains(140001282)))) {
                        player.getSkillList().addLinkedSkill(player, 4474, 1);
                    } else {
                        player.getSkillList().addLinkedSkill(player, 4564, 1);
                    }
                }
                return;
        }
        hasInert = false;
    }
}
