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
package com.aionemu.gameserver.dataholders;

import com.aionemu.gameserver.model.templates.housing.Building;
import com.aionemu.gameserver.model.templates.housing.HouseAddress;
import com.aionemu.gameserver.model.templates.housing.HouseType;
import com.aionemu.gameserver.model.templates.housing.HousingLand;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.*;

/**
 * @author Rolandas
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"lands"})
@XmlRootElement(name = "house_lands")
public class HouseData {

    @XmlElement(name = "land")
    protected List<HousingLand> lands;
    @XmlTransient
    Map<Integer, HousingLand> landsById = new HashMap<Integer, HousingLand>();
    @XmlTransient
    Map<Integer, Set<HousingLand>> landsByEntryWorldId = new HashMap<Integer, Set<HousingLand>>();

    void afterUnmarshal(Unmarshaller u, Object parent) {
        if (lands == null) {
            return;
        }

        for (HousingLand land : lands) {
            landsById.put(land.getId(), land);
            for (HouseAddress address : land.getAddresses()) {
                Integer exitMapId = address.getExitMapId();
                if (exitMapId == null) {
                    exitMapId = address.getMapId();
                }
                Set<HousingLand> landList = landsByEntryWorldId.get(exitMapId);
                if (landList == null) {
                    landList = new HashSet<HousingLand>();
                    landsByEntryWorldId.put(exitMapId, landList);
                }
                landList.add(land);
            }
        }

        lands.clear();
        lands = null;
    }

    public Set<HousingLand> getLandsForWorldId(int worldId) {
        return landsByEntryWorldId.get(worldId);
    }

    public HousingLand getLandForHouse(int worldId, HouseType houseSize) {
        Set<HousingLand> worldHouseAreas = landsByEntryWorldId.get(worldId);
        if (worldHouseAreas == null) {
            return null;
        }
        for (HousingLand land : worldHouseAreas) {
            for (Building building : land.getBuildings()) {
                if (houseSize.value().equals(building.getSize())) {
                    return land;
                }
            }
        }
        return null;
    }

    public HousingLand getLand(int landId) {
        return landsById.get(landId);
    }

    public Collection<HousingLand> getLands() {
        return landsById.values();
    }

    public int size() {
        return landsById.size();
    }
}
