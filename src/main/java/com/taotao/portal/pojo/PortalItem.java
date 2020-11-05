package com.taotao.portal.pojo;

import com.taotao.pojo.Item;

/**
 * 继承Item，添加一个getImages方法，方便页面进行调用
* <p>Title: PortalItem.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-19_14:39:50
* @version 1.0
 */
public class PortalItem extends Item {

	/**
	 * 这里额外添加一个getImages方法，是为了为前端页面提供iamges
	* <p>Title: getImages<／p>
	* <p>Description: <／p>
	* @return
	 */
	public String[] getImages(){
		String images = this.getImage();
		if (images!=null && !images.equals("")) {
			String[] imgs = images.split(",");
			return imgs;
		}
		return null;
	}
}
