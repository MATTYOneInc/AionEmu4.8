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
package com.aionemu.gameserver.model.instance.instancereward;

import com.aionemu.gameserver.model.instance.playerreward.EternalBastionPlayerReward;

/**
 * @author Romanz
 */
public class EternalBastionReward extends InstanceReward<EternalBastionPlayerReward> {

    private int points;
    private int npcKills;
    private int rank = 7;
    private int basicAP;
    private int powerfulBundlewater;
    private int ceraniumMedal;
    private int powerfulBundleessence;
    private int largeBundlewater;
    private int largeBundleessence;
    private int smallBundlewater;
    private boolean isRewarded = false;

    public EternalBastionReward(Integer mapId, int instanceId) {
        super(mapId, instanceId);
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public int getPoints() {
        return points;
    }

    public void addNpcKill() {
        npcKills++;
    }

    public int getNpcKills() {
        return npcKills;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public boolean isRewarded() {
        return isRewarded;
    }

    public void setRewarded() {
        isRewarded = true;
    }

    public int getBasicAP() {
        return basicAP;
    }

    public void setBasicAP(int ap) {
        this.basicAP = ap;
    }

    public int getCeramiumMedal() {
        return ceraniumMedal;
    }

    public int getPowerfulBundleWater() {
        return powerfulBundlewater;
    }

    public int getPowerfulBundleEssence() {
        return powerfulBundleessence;
    }

    public int getLargeBundleWater() {
        return largeBundlewater;
    }

    public int getLargeBundleEssence() {
        return largeBundleessence;
    }

    public int getSmallBundleWater() {
        return smallBundlewater;
    }

    public void setCeramiumMedal(int ceraniumMedal) {
        this.ceraniumMedal = ceraniumMedal;
    }

    public void setPowerfulBundleWater(int powerfulBundlewater) {
        this.powerfulBundlewater = powerfulBundlewater;
    }

    public void setPowerfulBundleEssence(int powerfulBundleessence) {
        this.powerfulBundleessence = powerfulBundleessence;
    }

    public void setLargeBundleWater(int largeBundlewater) {
        this.largeBundlewater = largeBundlewater;
    }

    public void setLargeBundleEssence(int largeBundleessence) {
        this.largeBundleessence = largeBundleessence;
    }

    public void setSmallBundleWater(int smallBundlewater) {
        this.smallBundlewater = smallBundlewater;
    }
}
