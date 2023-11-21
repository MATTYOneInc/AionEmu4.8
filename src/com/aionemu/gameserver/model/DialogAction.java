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
package com.aionemu.gameserver.model;

/**
 * @author Rolandas
 */
public enum DialogAction {

    AIRLINE_SERVICE(44),
    AP_SELL(101),
    ASK_QUEST_ACCEPT(1007),
    BUY(2),
    BUY_AGAIN(70),
    BUY_BY_AP(55),
    CHANGE_ITEM_SKIN(43),
    CHARGE_ITEM_MULTI(76),
    CHARGE_ITEM_MULTI2(95),
    CHARGE_ITEM_SINGLE(75),
    CHARGE_ITEM_SINGLE2(94),
    CHECK_USER_HAS_QUEST_ITEM(39),
    CHECK_USER_HAS_QUEST_ITEM_SIMPLE(20002),
    CLOSE_LEGION_WAREHOUSE(56),
    COMBINE_SKILL_LEVELUP(46),
    COMPOUND_WEAPON(66),
    CRAFT(58),
    CREATE_LEGION(5),
    DECOMPOUND_WEAPON(67),
    DEPOSIT_ACCOUNT_WAREHOUSE(28),
    DEPOSIT_CHAR_WAREHOUSE(26),
    DIC(40),
    DISPERSE_LEGION(6),
    EDIT_CHARACTER(61),
    EDIT_GENDER(62),
    EDIT_GENDER_CONFIRM(57),
    ENTER_PVP(36),
    EXCHANGE_COIN(59),
    EXTEND_ACCOUNT_WAREHOUSE(49),
    EXTEND_CHAR_WAREHOUSE(48),
    EXTEND_INVENTORY(47),
    FACTION_JOIN(68),
    FACTION_SEPARATE(69),
    FINISH_DIALOG(1008),
    FUNC_PET_H_ABANDON(93),
    FUNC_PET_H_ADOPT(92),
    GATHER_SKILL_LEVELUP(45),
    GIVE_ITEM_PROC(41),
    GIVEUP_CRAFT_EXPERT(79),
    GIVEUP_CRAFT_MASTER(80),
    HOUSING_BUILD(73),
    HOUSING_CANCEL_GIVEUP(90),
    HOUSING_CHANGE_BUILDING(87),
    HOUSING_CONFIG(88),
    HOUSING_CREATE_PERSONAL_INS(91),
    HOUSING_DESTRUCT(74),
    HOUSING_FRIENDLIST(81),
    HOUSING_GIVEUP(89),
    HOUSING_GUESTBOOK(99),
    HOUSING_KICK(86),
    HOUSING_LIKE(97),
    HOUSING_PAY_RENT(85),
    HOUSING_PERSONAL_AUCTION(84),
    HOUSING_PERSONAL_INS_TELEPORT(83),
    HOUSING_RANDOM_TELEPORT(82),
    HOUSING_RECREATE_PERSONAL_INS(96),
    HOUSING_SCRIPT(98),
    INSTANCE_ENTRY(65),
    INSTANCE_PARTY_MATCH(77),
    LEAVE_PVP(37),
    LEGION_CHANGE_EMBLEM(52),
    LEGION_CREATE_EMBLEM(51),
    LEGION_LEVELUP(50),
    MAKE_MERCENARY(64),
    MATCH_MAKER(63),
    NULL(0),
    OPEN_INSTANCE_RECRUIT(105),
    OPEN_LEGION_WAREHOUSE(53),
    OPEN_PERSONAL_WAREHOUSE(54),
    OPEN_POSTBOX(38),
    OPEN_QUEST_WINDOW(32),
    OPEN_STIGMA_WINDOW(4),
    OPEN_VENDOR(33),
    PASS_DOORMAN(57),
    PET_ABANDON(72),
    PET_ADOPT(71),
    QUEST_ACCEPT(29),
    QUEST_ACCEPT_1(1002),
    QUEST_ACCEPT_SIMPLE(20000),
    QUEST_REFUSE(30),
    QUEST_REFUSE_1(1003),
    QUEST_REFUSE_2(1004),
    QUEST_REFUSE_SIMPLE(20001),
    QUEST_SELECT(31),
    RECOVERY(35),
    RECREATE_LEGION(7),
    REMOVE_MANASTONE(42),
    RESURRECT_BIND(34),
    RESURRECT_PET(24),
    RETRIEVE_ACCOUNT_WAREHOUSE(27),
    RETRIEVE_CHAR_WAREHOUSE(25),
    SELECT_ACTION_1011(1011),
    SELECT_ACTION_1012(1012),
    SELECT_ACTION_1013(1013),
    SELECT_ACTION_1014(1014),
    SELECT_ACTION_1015(1015),
    SELECT_ACTION_1016(1016),
    SELECT_ACTION_1017(1017),
    SELECT_ACTION_1018(1018),
    SELECT_ACTION_1019(1019),
    SELECT_ACTION_1097(1097),
    SELECT_ACTION_1182(1182),
    SELECT_ACTION_1267(1267),
    SELECT_ACTION_1352(1352),
    SELECT_ACTION_1353(1353),
    SELECT_ACTION_1354(1354),
    SELECT_ACTION_1438(1438),
    SELECT_ACTION_1609(1609),
    SELECT_ACTION_1693(1693),
    SELECT_ACTION_1694(1694),
    SELECT_ACTION_1695(1695),
    SELECT_ACTION_1779(1779),
    SELECT_ACTION_2034(2034),
    SELECT_ACTION_2035(2035),
    SELECT_ACTION_2036(2036),
    SELECT_ACTION_2037(2037),
    SELECT_ACTION_2376(2376),
    SELECT_ACTION_2377(2377),
    SELECT_ACTION_2546(2546),
    SELECT_ACTION_2717(2717),
    SELECT_ACTION_2718(2718),
    SELECT_ACTION_2720(2720),
    SELECT_ACTION_3058(3058),
    SELECT_ACTION_3143(3143),
    SELECT_ACTION_3399(3399),
    SELECT_ACTION_3400(3400),
    SELECT_ACTION_3570(3570),
    SELECT_ACTION_3740(3740),
    SELECT_ACTION_3911(3911),
    SELECT_ACTION_4081(4081),
    SELECT_ACTION_4763(4763),
    SELECTED_QUEST_REWARD1(8),
    SELECTED_QUEST_REWARD2(9),
    SELECTED_QUEST_REWARD3(10),
    SELECTED_QUEST_REWARD4(11),
    SELECTED_QUEST_REWARD5(12),
    SELECTED_QUEST_REWARD6(13),
    SELECTED_QUEST_REWARD7(14),
    SELECTED_QUEST_REWARD8(15),
    SELECTED_QUEST_REWARD9(16),    
    SELECTED_QUEST_REWARD10(17),
    SELECTED_QUEST_REWARD11(18),
    SELECTED_QUEST_REWARD12(19),
    SELECTED_QUEST_REWARD13(20),
    SELECTED_QUEST_REWARD14(21),
    SELECTED_QUEST_REWARD15(22),
    SELECTED_QUEST_NOREWARD(23),
    SELECT_QUEST_REWARD(1009),
    SELL(3),
    SET_SUCCEED(10255),
    SETPRO1(10000),
    SETPRO2(10001),
    SETPRO3(10002),
    SETPRO4(10003),
    SETPRO5(10004),
    SETPRO6(10005),
    SETPRO7(10006),
    SETPRO8(10007),
    SETPRO9(10008),
    SETPRO10(10009),
    SETPRO11(10010),
    SETPRO12(10011),
    SETPRO13(10012),
    SETPRO14(10013),
    SETPRO15(10014),
    SETPRO16(10015),
    SETPRO17(10016),
    SETPRO18(10017),
    SETPRO19(10018),
    SETPRO20(10019),
    SETPRO21(10020),
    SETPRO22(10021),
    SETPRO23(10022),
    SETPRO24(10023),
    SETPRO25(10024),
    SETPRO26(10025),
    SETPRO27(10026),
    SETPRO28(10027),
    SETPRO29(10028),
    SETPRO30(10029),
    SETPRO31(10030),
    SETPRO32(10031),
    SETPRO33(10032),
    SETPRO34(10033),
    SETPRO35(10034),
    SETPRO36(10035),
    SETPRO37(10036),
    SETPRO38(10037),
    SETPRO39(10038),
    SETPRO40(10039),
    SETPRO41(10040),
    SHOW_MOVIE(60),
    TELEPORT_SIMPLE(104),
    TOWN_CHALLENGE(100),
    TRADE_IN(78),
    TRADE_SELL_LIST(103),
    MOVE_ITEM_SKIN(106), // new 4.7 ?
    TRADE_IN_UPGRADE(107), // new 4.7 ?
    QUEST_AUTO_REWARD(108), // new 4.7 ?
    ITEM_UPGRADE(109), // new 4.7 ?
    CHARGE_STIGMA(125), // 4.8
    USE_OBJECT(-1);
    private int id;

    private DialogAction(int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }

    public static DialogAction getActionByDialogId(int id) {
        for (DialogAction da : values()) {
            if (da.id() == id) {
                return da;
            }
        }
        return null;
    }
}
