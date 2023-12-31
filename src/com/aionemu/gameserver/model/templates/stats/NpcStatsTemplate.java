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
package com.aionemu.gameserver.model.templates.stats;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Luno
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "npc_stats_template")
public class NpcStatsTemplate extends StatsTemplate {

    @XmlAttribute(name = "pdef")
    private int pdef;
    @XmlAttribute(name = "mdef")
    private int mdef;
    @XmlAttribute(name = "mresist")
    private int mresist;
    @XmlAttribute(name = "crit")
    private int crit;
    @XmlAttribute(name = "accuracy")
    private int accuracy;
    @XmlAttribute(name = "power")
    private int power;
    @XmlAttribute(name = "maxXp")
    private int maxXp;
    @XmlAttribute(name = "rewardGP")
    private int rewardGP;

    @Override
    public float getGroupWalkSpeed() {
        return speeds == null ? 0 : speeds.getGroupWalkSpeed();
    }

    @Override
    public float getRunSpeedFight() {
        return speeds == null ? 0 : speeds.getRunSpeedFight();
    }

    @Override
    public float getGroupRunSpeedFight() {
        return speeds == null ? 0 : speeds.getGroupRunSpeedFight();
    }

    /**
     * @return the pdef
     */
    public int getPdef() {
        return pdef;
    }

    /**
     * @return the mdef
     */
    public float getMdef() {
        return mdef;
    }

    /**
     * @return the mresist
     */
    public int getMresist() {
        return mresist;
    }

    /**
     * @return the crit
     */
    public float getCrit() {
        return crit;
    }

    /**
     * @return the accuracy
     */
    public float getAccuracy() {
        return accuracy;
    }

    /**
     * @return the power
     */
    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    /**
     * @return the maxXp
     */
    public int getMaxXp() {
        return maxXp;
    }
	
    /**
     * @return the RewardGP
     */
    public int getRewardGP() {
        return rewardGP;
    }	
	
}
