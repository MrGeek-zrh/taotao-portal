package com.taotao.portal.pojo;

/**
 * 购物车对应的实体类
* <p>Title: ItemCart.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-22_09:08:50
* @version 1.0
 */
public class CartItem {

	private Long id;
	private String title;
	private Long price;
	private Integer num;
	private String image;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "ItemCart [id=" + id + ", title=" + title + ", price=" + price + ", num=" + num + ", image=" + image
				+ "]";
	}
}
