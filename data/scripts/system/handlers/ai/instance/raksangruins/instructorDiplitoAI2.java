/**
 * This file is part of Aion-Lightning <aion-lightning.org>.
 * <p/>
 * Aion-Lightning is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * Aion-Lightning is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details. *
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with Aion-Lightning.
 * If not, see <http://www.gnu.org/licenses/>.
 * <p/>
 * <p/>
 * Credits goes to all Open Source Core Developer Groups listed below
 * Please do not change here something, ragarding the developer credits, except the "developed by XXXX".
 * Even if you edit a lot of files in this source, you still have no rights to call it as "your Core".
 * Everybody knows that this Emulator Core was developed by Aion Lightning
 *
 * @-Aion-Unique-
 * @-Aion-Lightning
 * @Aion-Engine
 * @Aion-Extreme
 * @Aion-NextGen
 * @Aion-Core Dev.
 */
package ai.instance.raksangruins;

import ai.AggressiveNpcAI2;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Kill3r
 */
@AIName("diplito")
public class instructorDiplitoAI2 extends AggressiveNpcAI2 {

    protected List<Integer> percents = new ArrayList<Integer>();
    private boolean used = false;

    private void addPercents(){
        percents.clear();
        Collections.addAll(percents, new Integer[]{40});
    }

    private synchronized void checkhpPercentage(int hpPercentage){
        for (Integer percent : percents){
            if (hpPercentage <= percent){
                switch (percent){
                    case 40:
                        summonAdd();
                        used = true;
                        break;
                }
            }
        }
    }

    private void summonAdd(){
        if (!used) {
            spawn(855908, getPosition().getX(), getPosition().getY(), getPosition().getZ(), getPosition().getHeading());
        }
    }

    @Override
    protected void handleSpawned() {
        addPercents();
        super.handleSpawned();
    }

    @Override
    protected void handleDied() {
        super.handleDied();
        used = false;
        despawnNpc(getPosition().getWorldMapInstance().getNpc(855908));
    }

    @Override
    protected void handleAttack(Creature creature) {
        super.handleAttack(creature);
        checkhpPercentage(getLifeStats().getHpPercentage());
    }

    protected void despawnNpc(Npc npc) {
        if (npc != null) {
            npc.getController().onDelete();
        }
    }

}
