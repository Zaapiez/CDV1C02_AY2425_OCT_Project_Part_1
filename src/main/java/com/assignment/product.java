package com.assignment;

public class product {
	protected String prodname;
	protected String prodquantity;
	
	
	public product(String prodname, String prodquantity) {
		super();
		this.prodname = prodname;
		this.prodquantity = prodquantity;
	}
	public String getProdname() {
		return prodname;
	}
	public void setProdname(String prodname) {
		this.prodname = prodname;
	}
	public String getProdquantity() {
		return prodquantity;
	}
	public void setProdquantity(String prodquantity) {
		this.prodquantity = prodquantity;
	}
}
