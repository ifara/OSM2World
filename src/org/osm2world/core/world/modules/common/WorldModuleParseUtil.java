package org.osm2world.core.world.modules.common;

import org.openstreetmap.josm.plugins.graphview.core.data.TagGroup;
import org.openstreetmap.josm.plugins.graphview.core.util.ValueStringParser;
import org.osm2world.core.world.creation.WorldModule;

/**
 * utility class that can be used by {@link WorldModule}s
 * to interpret frequently used value formats.
 */
public class WorldModuleParseUtil {

	private WorldModuleParseUtil() { }
	
	/**
	 * retrieves width using (in this priority order) 
	 * width tag, est_width tag, defaultValue parameter
	 */
	public static final float parseWidth(TagGroup tags, float defaultValue) {		
		return parseMeasure(tags, defaultValue, "width", "est_width");		
	}

	/**
	 * retrieves height using (in this priority order) 
	 * height tag, building:height tag, est_height tag, defaultValue parameter
	 */
	public static final float parseHeight(TagGroup tags, float defaultValue) {		
		return parseMeasure(tags, defaultValue, "height", "building:height", "est_height");		
	}

	/**
	 * retrieves clearing using (in this priority order) 
	 * practical:maxheight tag, maxheight tag, defaultValue parameter
	 */
	public static final float parseClearing(TagGroup tags, float defaultValue) {		
		return parseMeasure(tags, defaultValue, "maxheight:physical", "maxheight");		
	}
	
	private static final float parseMeasure(TagGroup tags, float defaultValue,
			String... keys) {

		for (String key : keys) {
			if (tags.containsKey(key)) {
				Float value = ValueStringParser.parseMeasure(tags.getValue(key));
				if (value != null) {
					return value;
				}
			}
		}
		
		return defaultValue;
		
	}
	
	
}