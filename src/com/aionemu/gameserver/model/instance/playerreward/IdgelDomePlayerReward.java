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
package com.aionemu.gameserver.model.instance.playerreward;

import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.instance.InstanceBuff;


/**
 * @author GiGatR00n (Aion-Core)
 */
public class IdgelDomePlayerReward extends InstancePlayerReward {

    /**
     * BUFF_SHIELD applies when your score in Idgel Dome is lower than the enemy's.
     * Increases PvP Physical Attack, PvP Magic Attack, PvP Physical Defense, and PvP Magical Defense by 30% 
     */
    private InstanceBuff ResurrectionBuff;
    private int InstanceBuffTime = 30;//30-Seconds
    
    private int timeBonus;
    private long logoutTime;
    private float timeBonusModifier;
    private Race race;
    private int rewardAp;
    private int rewardGp;
    private int bonusAp;
    private int bonusGp;
    private int reward1;
    private int reward2;
    private int bonusReward;
    private int bonusReward2;
    private float reward1Count;
    private float reward2Count;
    private float rewardCount;
    private int AdditionalReward;
    private float AdditionalRewardCount;

    
    
    
    public IdgelDomePlayerReward (Integer object, int timeBonus, Race race) {
        super(object);
        this.timeBonus = timeBonus;
        timeBonusModifier = ((float) this.timeBonus / (float) 660000);
        this.race = race;
        this.ResurrectionBuff = new InstanceBuff(15);
    }

    public boolean hasResurrectionBuff() {
        return ResurrectionBuff.hasInstanceBuff();
    }

    public void applyResurrectionBuff(Player player) {
    	ResurrectionBuff.applyEffect(player, InstanceBuffTime * 1000);
    }

    public void endResurrectionBuff(Player player) {
    	ResurrectionBuff.endEffect(player);
    }

    public int getRemaningResurrectionBuffTime() {
        int time = ResurrectionBuff.getRemaningTime();
        if (time >= 0 && time < InstanceBuffTime) {
            return (InstanceBuffTime - time);
        }
        return 0;
    }    
    
    public float getParticipation() {
        return (float) getTimeBonus() / timeBonus;
    }

    public int getScorePoints() {
        return timeBonus + getPoints();
    }

    public int getTimeBonus() {
        return timeBonus > 0 ? timeBonus : 0;
    }

    public void updateLogOutTime() {
        logoutTime = System.currentTimeMillis();
    }

    public void updateBonusTime() {
        int offlineTime = (int) (System.currentTimeMillis() - logoutTime);
        timeBonus -= offlineTime * timeBonusModifier;
    }

    public Race getRace() {
        return race;
    }

    public int getRewardAp() {
        return rewardAp;
    }
    
    public void setRewardAp(int ap) {
        this.rewardAp = ap;
    }    

    public int getBonusAp() {
        return bonusAp;
    }

    public void setBonusAp(int ap) {
        this.bonusAp = ap;
    }

    public int getReward1() {
        return reward1;
    }
    
    public void setReward1(int reward) {
        this.reward1 = reward;
    }
    
    public int getReward1Count() {
        return (int) reward1Count;
    }
    
    public void setReward1Count(float rewardCount) {
        this.reward1Count = rewardCount;
    }

    public int getReward2() {
        return reward2;
    }
    
    public void setReward2(int reward) {
        this.reward2 = reward;
    }
    
    public int getReward2Count() {
        return (int) reward2Count;
    }
    
    public void setReward2Count(float rewardCount) {
        this.reward2Count = rewardCount;
    }

    public int getBonusReward() {
        return bonusReward;
    }

    public void setBonusReward(int reward) {
        this.bonusReward = reward;
    }
    
	public int getBonusReward2() {
		return bonusReward2;
	}

	public void setBonusReward2(int bonusReward2) {
		this.bonusReward2 = bonusReward2;
	}
	
	public int getAdditionalReward() {
		return AdditionalReward;
	}

	public void setAdditionalReward(int additionalReward) {
		this.AdditionalReward = additionalReward;
	}
	
    public int getAdditionalRewardCount() {
        return (int) AdditionalRewardCount;
    }
    
    public void setAdditionalRewardCount(float rewardCount) {
        this.AdditionalRewardCount = rewardCount;
    }

    public int getRewardCount() {
        return (int) rewardCount;
    }

    public void setRewardCount(float rewardCount) {
        this.rewardCount = rewardCount;
    }

    public int getRewardGp() {
        return rewardGp;
    }

    public void setRewardGp(int rewardGp) {
        this.rewardGp = rewardGp;
    }

	public int getBonusGp() {
		return bonusGp;
	}

	public void setBonusGp(int bonusGp) {
		this.bonusGp = bonusGp;
	}
}
