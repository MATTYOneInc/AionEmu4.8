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
package com.aionemu.gameserver.model.templates.itemgroups;

import com.aionemu.gameserver.model.templates.rewards.BonusType;

import javax.xml.bind.annotation.*;

/**
 * @author Rolandas
 *
 */

/**
 * <p/>
 * Java class for ItemGroup complex type.
 * <p/>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * <p/>
 * <pre>
 * &lt;complexType name="BonusItemGroup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="bonusType" use="required" type="{}BonusType" />
 *       &lt;attribute name="chance" type="{http://www.w3.org/2001/XMLSchema}float" default="0" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BonusItemGroup")
@XmlSeeAlso({CraftItemGroup.class, CraftRecipeGroup.class, ManastoneGroup.class, FoodGroup.class, MedicineGroup.class,
        OreGroup.class, GatherGroup.class, EnchantGroup.class, BossGroup.class})
public abstract class BonusItemGroup {

    @XmlAttribute(name = "bonusType", required = true)
    protected BonusType bonusType;
    @XmlAttribute(name = "chance")
    protected Float chance;

    /**
     * Gets the value of the bonusType property.
     *
     * @return possible object is {@link BonusType }
     */
    public BonusType getBonusType() {
        return bonusType;
    }

    /**
     * Gets the value of the chance property.
     *
     * @return possible object is {@link Float }
     */
    public float getChance() {
        if (chance == null) {
            return 0.0F;
        } else {
            return chance;
        }
    }

    public abstract ItemRaceEntry[] getRewards();
}
