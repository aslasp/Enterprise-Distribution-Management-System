package Presentation.stockui;

public class StockMessage {

	public static String getStringResult(int result) {
		String stringResult = " ";
		switch (result) {
		case 1:
			stringResult = "该上级分类下有商品，无法添加";
			break;
		case 2:
			stringResult = "该上级分类不存在，无法添加";
			break;
		case 3:
			stringResult = "该商品分类下有商品，无法删除";
			break;
		case 4:
			stringResult = "商品已存在，无法添加";
			break;
		case 5:
			stringResult = "商品分类已存在，无法添加";
			break;
		case 6:
			stringResult = "商品分类已存在，无法修改";
			break;
		case 91:
			stringResult = "商品已被进货，无法删除";
			break;
		case 92:
			stringResult = "商品已被销售过，无法删除";
			break;
		case 93:
			stringResult = "商品已进行过库存赠送，无法删除";
			break;
		case 10:
			stringResult = "商品不存在，无法进行操作";
			break;
		default:
			break;
		}
		return stringResult;
	}
}
