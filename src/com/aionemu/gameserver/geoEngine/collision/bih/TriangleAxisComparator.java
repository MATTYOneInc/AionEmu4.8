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
package com.aionemu.gameserver.geoEngine.collision.bih;

import com.aionemu.gameserver.geoEngine.math.Vector3f;

import java.util.Comparator;

public class TriangleAxisComparator implements Comparator<BIHTriangle> {

    private final int axis;

    public TriangleAxisComparator(int axis) {
        this.axis = axis;
    }

    public int compare(BIHTriangle o1, BIHTriangle o2) {
        float v1, v2;
        Vector3f c1 = o1.getCenter();
        Vector3f c2 = o2.getCenter();
        switch (axis) {
            case 0:
                v1 = c1.x;
                v2 = c2.x;
                break;
            case 1:
                v1 = c1.y;
                v2 = c2.y;
                break;
            case 2:
                v1 = c1.z;
                v2 = c2.z;
                break;
            default:
                assert false;
                return 0;
        }
        if (v1 > v2) {
            return 1;
        } else if (v1 < v2) {
            return -1;
        } else {
            return 0;
        }
    }
}
