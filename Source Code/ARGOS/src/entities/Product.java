package entities;

public class Product {
	String _name;
	double _temperature;
	double _price;
	String _description;
	String imageURL;
	String dealURL;
	String _URL;
	@Override
	public String toString(){
		return "Name : "+this._name+" Temperature "+this._temperature+" Price "+this._price+" Description "+this._description+" Image URL "+this.imageURL+" Deal URL "+this.dealURL+" URL "+this._URL;

	}
	public String get_name() {
		return _name;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public double get_temperature() {
		return _temperature;
	}
	public void set_temperature(double _temperature) {
		this._temperature = _temperature;
	}
	public double get_price() {
		return _price;
	}
	public void set_price(double _price) {
		this._price = _price;
	}
	public String get_description() {
		return _description;
	}
	public void set_description(String _description) {
		this._description = _description;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getDealURL() {
		return dealURL;
	}
	public void setDealURL(String dealURL) {
		this.dealURL = dealURL;
	}
	public String get_URL() {
		return _URL;
	}
	public void set_URL(String _URL) {
		this._URL = _URL;
	}
}
