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
package com.aionemu.gameserver.model.templates.staticdoor;

import com.aionemu.gameserver.geoEngine.bounding.BoundingBox;
import com.aionemu.gameserver.model.templates.VisibleObjectTemplate;

import javax.xml.bind.annotation.*;
import java.util.EnumSet;

/**
 * @author Wakizashi
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StaticDoor")
public class StaticDoorTemplate extends VisibleObjectTemplate {

    @XmlAttribute
    protected DoorType type = DoorType.DOOR;
    @XmlAttribute
    protected Float x;
    @XmlAttribute
    protected Float y;
    @XmlAttribute
    protected Float z;
    @XmlAttribute(name = "doorid")
    protected int doorId;
    @XmlAttribute(name = "keyid")
    protected int keyId;
    @XmlAttribute(name = "state")
    protected String statesHex;
    @XmlAttribute(name = "mesh")
    private String meshFile;
    @XmlElement(name = "box")
    private StaticDoorBounds box;
    @XmlTransient
    EnumSet<StaticDoorState> states = EnumSet.noneOf(StaticDoorState.class);

    public Float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public Float getZ() {
        return z;
    }

    /**
     * @return the doorId
     */
    public int getDoorId() {
        return doorId;
    }

    /**
     * @return the keyItem
     */
    public int getKeyId() {
        return keyId;
    }

    @Override
    public int getTemplateId() {
        return 300001;
    }

    @Override
    public String getName() {
        return "door";
    }

    @Override
    public int getNameId() {
        return 0;
    }

    public EnumSet<StaticDoorState> getInitialStates() {
        if (statesHex != null) {
            int radix = 16;
            if (statesHex.startsWith("0x")) {
                statesHex = statesHex.replace("0x", "");
            } else {
                radix = 10;
            }
            try {
                StaticDoorState.setStates(Integer.parseInt(statesHex, radix), states);
            } catch (NumberFormatException ex) {
            } finally {
                statesHex = null;
            }
        }
        return states;
    }

    public String getMeshFile() {
        return meshFile;
    }

    public BoundingBox getBoundingBox() {
        if (box == null) {
            return null;
        }
        return box.getBoundingBox();
    }

    public DoorType getDoorType() {
        return type;
    }
}
