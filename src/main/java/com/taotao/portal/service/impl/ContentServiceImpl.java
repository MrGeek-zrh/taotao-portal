package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtil;
import com.taotao.pojo.Content;
import com.taotao.portal.pojo.AdNode;
import com.taotao.portal.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService{

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_CONTENT_URL}")
	private String REST_CONTENT_URL;
	@Value("${REST_CONTENT_AD_CID}")
	private String REST_CONTENT_AD_CID;

	/**
	 * 获得大广告位的内容
	 */
	@Override
	public String getAdList() {
		
		try {
			//调用服务获得数据
			String json = HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_URL+REST_CONTENT_AD_CID);
			//将json转换文java对象
			TaotaoResult taotaoResult = TaotaoResult.formatToList(json, Content.class);
			//取data属性
			List<Content> list= (List<Content>) taotaoResult.getData();
			//将内容列表转换为AdNode列表
			List<AdNode>resultList= new ArrayList<>();
			for (Content content : list) {
				AdNode adNode = new AdNode();
				adNode.setHeight(240);
				adNode.setWidth(670);
				adNode.setSrc(content.getPic());
				
				adNode.setHeightB(240);
				adNode.setWidthB(550);
				adNode.setSrcB(content.getPic2());
				
				adNode.setAlt(content.getSubTitle());
				adNode.setHref(content.getUrl());
				resultList.add(adNode);
			}
			return JsonUtil.ObjectToJSON(resultList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
