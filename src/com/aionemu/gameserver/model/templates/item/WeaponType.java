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
package com.aionemu.gameserver.model.templates.item;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * @author ATracer
 * @Reworked Kill3r (Aion-Core) 4.8
 */
@XmlType(name = "weapon_type")
@XmlEnum
public enum WeaponType {

    DAGGER_1H(new int[]{66, 45}, 1),
    MACE_1H(new int[]{39, 46}, 1),
    SWORD_1H(new int[]{37, 44}, 1),
    TOOLHOE_1H(new int[]{}, 1),
    GUN_1H(new int[]{112, 117}, 1),
    BOOK_2H(new int[]{100, 107}, 2),
    ORB_2H(new int[]{111}, 2),
    POLEARM_2H(new int[]{52}, 2),
    STAFF_2H(new int[]{89}, 2),
    SWORD_2H(new int[]{51}, 2),
    TOOLPICK_2H(new int[]{}, 2),
    TOOLROD_2H(new int[]{}, 2),
    BOW(new int[]{53}, 2),
    CANNON_2H(new int[]{113}, 2),
    HARP_2H(new int[]{124, 114}, 2),
    GUN_2H(new int[]{}, 2),
    KEYBLADE_2H(new int[]{115}, 2);
    private int[] requiredSkill;
    private int slots;

    private WeaponType(int[] requiredSkills, int slots) {
        this.requiredSkill = requiredSkills;
        this.slots = slots;
    }

    public int[] getRequiredSkills() {
        return requiredSkill;
    }

    public int getRequiredSlots() {
        return slots;
    }

    /**
     * @return int
     */
    public int getMask() {
        return 1 << this.ordinal();
    }
}
