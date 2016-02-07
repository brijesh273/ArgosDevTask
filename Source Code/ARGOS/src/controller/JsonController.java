package controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import entities.Product;

public class JsonController {
	JSONObject jsonObj;
	public JsonController(String docString) {
		jsonObj = XML.toJSONObject(docString);
	}
	public String getTopTenDeals(){
		JSONArray prodList = new JSONArray();
		JSONArray apiItems =  ((JSONObject)((JSONObject) jsonObj.getJSONObject("api_response")).getJSONObject("deals")).getJSONArray("api_item");
		int _size = apiItems.length();
		for(int i=0 ; i < _size;i++)
		{
			Product _product = new Product();
			JSONObject tempProduct = (JSONObject) apiItems.get(i);
			Double _temperature = tempProduct.getDouble("temperature");
			_product.set_temperature(_temperature);
			Double _price = tempProduct.getDouble("price");
			_product.set_price(_price);
			String _description= (String) (null==tempProduct.get("description")?"":tempProduct.get("description"));
			_product.set_description(_description);
			String dealURL= (String) (null==tempProduct.get("deal_link")?"":tempProduct.get("deal_link"));
			_product.setDealURL(dealURL);
			String _name= (String) (null==tempProduct.get("title")?"":tempProduct.get("title"));
			_product.set_name(_name);
			String imageURL= (String) (null==tempProduct.get("deal_image_highres")?"":tempProduct.get("deal_image_highres"));
			_product.setImageURL(imageURL);
			String argoURL = getArgoURL(imageURL);
			_product.set_URL(argoURL);
			JSONObject tempJsonObj= getJSONObjectFromPojo(_product);
			prodList.put(tempJsonObj);
		}
		JSONObject topProduct =  new JSONObject();
		topProduct.put("TopTenProducts", prodList);
		return topProduct.toString();
	}
	private String getArgoURL(String imageURL) {
		String tempStr = imageURL;
		tempStr =tempStr.substring(tempStr.lastIndexOf("/")+1, tempStr.lastIndexOf("."));
		return "http://www.hotukdeals.com/visit?m=5&q="+tempStr;
	}
	private JSONObject getJSONObjectFromPojo(Product _product) {
		JSONObject prodObj = new JSONObject();
		prodObj.put("NAME", _product.get_name());
		prodObj.put("TEMPERATURE", _product.get_temperature());
		prodObj.put("PRICE",_product.get_price());
		prodObj.put("DEALURL", _product.getDealURL());
		prodObj.put("DESCRIPTION", _product.get_description());
		prodObj.put("IMAGEURL", _product.getImageURL());
		prodObj.put("ARGOURL", _product.get_URL());
		return prodObj;
	}
}