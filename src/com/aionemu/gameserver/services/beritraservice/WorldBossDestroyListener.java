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
package com.aionemu.gameserver.services.beritraservice;

import com.aionemu.gameserver.ai2.AbstractAI;
import com.aionemu.gameserver.ai2.eventcallback.OnDieEventCallback;
import com.aionemu.gameserver.services.BeritraService;

/**
 * @author Cx3
 */

@SuppressWarnings("rawtypes")
public class WorldBossDestroyListener extends OnDieEventCallback
{
	private final BeritraInvasion<?> beritra;
	
	public WorldBossDestroyListener(BeritraInvasion beritra) {
		this.beritra = beritra;
	}
	
	@Override
	public void onBeforeDie(AbstractAI obj) {
	}
	
	@Override
	public void onAfterDie(AbstractAI obj) {
	    beritra.setWorldBossDestroyed(true);
		BeritraService.getInstance().stopBeritraInvasion(beritra.getBeritraLocationId());
	}
}