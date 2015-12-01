package com.sina.ea.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sina.ea.core.bo.Adpos;
import com.sina.ea.core.bo.InfoResource;

public class TransformClass {
	
	/**
	 * 将资源包装换为资源位
	 * 
	 * @param infoResources
	 * @return
	 * @author biaofei
	 */
	public static List<Adpos> infoResourceToAdpos(List<InfoResource> infoResources){
		if(infoResources == null || infoResources.isEmpty()){
			return Collections.emptyList();
		}
		List<Adpos> adposes = new ArrayList<Adpos>(infoResources.size());
		for (InfoResource infoResource : infoResources) {
			Adpos adpos = new Adpos();
			adpos.setAdposId(infoResource.getPdpsId());
			adpos.setAdposName(infoResource.getPdpsName());
			adpos.setPlatform(infoResource.getPlatform());
			adposes.add(adpos);
		}
		return adposes;
	}
}
