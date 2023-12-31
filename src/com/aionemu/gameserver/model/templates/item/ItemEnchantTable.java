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
package com.aionemu.gameserver.model.templates.item;

import gnu.trove.map.hash.TIntObjectHashMap;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.aionemu.gameserver.model.stats.calc.functions.StatFunction;


/**
 * @author Alcapwnd
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ItemEnchantTable")
public class ItemEnchantTable {

	@XmlAttribute(name = "id")
    private int id;
	@XmlAttribute(name = "type")
    private String type;
	@XmlAttribute(name = "part")
    private String part;
    @XmlElement(name = "item_enchant", required = false)
    private List<ItemEnchantBonus> item_enchant;
    @SuppressWarnings({"rawtypes", "unchecked"})
    @XmlTransient
    private TIntObjectHashMap<List<StatFunction>> enchants = new TIntObjectHashMap();

    public List<StatFunction> getStats(int level) {
        for (ItemEnchantBonus ib : getItemEnchant()) {
        	if (ib.getLevel() != level)
        		continue;
        	
        	return (List<StatFunction>) ib.getModifiers();
        }
        return null;
    }

    public List<ItemEnchantBonus> getItemEnchant() {
        return this.item_enchant;
    }

    public int getId() {
        return this.id;
    }
    
    public String getType() {
    	return this.type;
    }
    
    public String getPart() {
    	return this.part;
    }
    
}
