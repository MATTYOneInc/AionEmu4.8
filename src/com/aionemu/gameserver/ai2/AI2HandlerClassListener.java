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
package com.aionemu.gameserver.ai2;

import com.aionemu.commons.scripting.classlistener.ClassListener;
import com.aionemu.commons.utils.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Modifier;

/**
 * @author ATracer
 */
public class AI2HandlerClassListener implements ClassListener {

    private static final Logger log = LoggerFactory.getLogger(AI2HandlerClassListener.class);

    @SuppressWarnings("unchecked")
    @Override
    public void postLoad(Class<?>[] classes) {
        for (Class<?> c : classes) {
            if (log.isDebugEnabled()) {
                log.debug("Load class " + c.getName());
            }

            if (!isValidClass(c)) {
                continue;
            }

            if (ClassUtils.isSubclass(c, AbstractAI.class)) {
                Class<? extends AbstractAI> tmp = (Class<? extends AbstractAI>) c;
                if (tmp != null) {
                    AI2Engine.getInstance().registerAI(tmp);
                }
            }
        }
    }

    @Override
    public void preUnload(Class<?>[] classes) {
        if (log.isDebugEnabled()) {
            for (Class<?> c : classes) {
                log.debug("Unload class " + c.getName());
            }
        }
    }

    public boolean isValidClass(Class<?> clazz) {
        final int modifiers = clazz.getModifiers();

        if (Modifier.isAbstract(modifiers) || Modifier.isInterface(modifiers)) {
            return false;
        }

        if (!Modifier.isPublic(modifiers)) {
            return false;
        }

        return true;
    }
}
