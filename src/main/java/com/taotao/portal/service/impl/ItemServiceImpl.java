package com.taotao.portal.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtil;
import com.taotao.pojo.Item;
import com.taotao.pojo.ItemDesc;
import com.taotao.pojo.ItemParamItem;
import com.taotao.portal.pojo.PortalItem;
import com.taotao.portal.service.ItemService;

import net.sf.json.JSONArray;

/**
 * Item信息获取实现类
* <p>Title: ItemServiceImpl.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-19_14:16:39
* @version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

	//rest服务的基础URL
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	//获取item信息的URL
	@Value("${REST_ITEM_BASE_URL}")
	private String REST_ITEM_BASE_URL;
	//获取商品描述信息的URL
	@Value("${REST_ITEM_DESC_URL}")
	private String REST_ITEM_DESC_URL;
	@Value("${REST_ITEM_PARAM_URL}")
	private String REST_ITEM_PARAM_URL;
	
	/**
	 * 调用服务，获取item的相关信息
	 */
	@Override
	public Item getItemByItemId(Long itemId) throws Exception {
		// 根据商品id查询商品基本信息
		String json = HttpClientUtil.doGet(REST_BASE_URL + REST_ITEM_BASE_URL + itemId);
		//转换成java对象
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, PortalItem.class);
		//取商品对象
		Item item = (Item) taotaoResult.getData();
		return item;
	}
	
	/**
	 * 调用服务，获取item的描述信息
	 */
	@Override
	public String getItemDescById(Long itemId) throws Exception{
		//根据商品id调用taotao-rest的服务获得数据
		String json = HttpClientUtil.doGet(REST_BASE_URL + REST_ITEM_DESC_URL + itemId);
		//转换成java对象
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, ItemDesc.class);
		//取商品描述
		ItemDesc itemDesc = (ItemDesc) taotaoResult.getData();
		String desc = itemDesc.getItemDesc();
		return desc;
	}
	
	/**
	 * 展示商品参数信息
	 */
	@Override
	public String getItemParamById(Long itemId) throws Exception {
		// 根据商品id获得对应的规格参数
		String json = HttpClientUtil.doGet(REST_BASE_URL + REST_ITEM_PARAM_URL + itemId);
		if (json!=null) {
			// 转换成java对象
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, ItemParamItem.class);
			// 取规格参数
			ItemParamItem itemParamItem = (ItemParamItem) taotaoResult.getData();
			String paramJson = itemParamItem.getParamData();
			// 把规格参数的json数据转换成java对象
			// 转换成java对象
			//指定paramds 的数据类型
			Map<Object, Object>typeM = new HashMap<Object, Object>();
			typeM.put("params", HashMap.class);
			//将获取到的JSON字符串转换成Java对象：List<HashMap<String,Object>>
			//Object 是String类型或ArrayList<HashMap<String,String>>类型
			List<Map<Object, Object>>mapList = JSONArray.toList(JSONArray.fromObject(paramJson), Map.class,typeM);
			
//			List<Map> mapList = (List<Map>) JsonUtil.jsonToObject(paramJson, Map.class);
			// 遍历list生成html
			StringBuffer sb = new StringBuffer();

			sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
			sb.append("	<tbody>\n");
			for (Map map : mapList) {
				sb.append("		<tr>\n");
				sb.append("			<th class=\"tdTitle\" colspan=\"2\">" + map.get("group") + "</th>\n");
				sb.append("		</tr>\n");
				// 取规格项
				List<Map> mapList2 = (List<Map>) map.get("params");
				for (Map map2 : mapList2) {
					sb.append("		<tr>\n");
					sb.append("			<td class=\"tdTitle\">" + map2.get("k") + "</td>\n");
					sb.append("			<td>" + map2.get("v") + "</td>\n");
					sb.append("		</tr>\n");
				}
			}
			sb.append("	</tbody>\n");
			sb.append("</table>");

			return sb.toString();
		}else {
			return null;
		}

	}

}
