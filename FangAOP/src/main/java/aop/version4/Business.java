package aop.version4;

public class Business {
 
	public static void main(String[] args) {
		 FontProvider fontProvider = ProviderFactory.getFontProvider();
	        Font font = fontProvider.getFont("微软雅黑");
	        System.out.println(font);

		FontProvider fontProvider2 = ProviderFactory.getFontProvider();
		Font font2 = fontProvider2.getFont("微软雅黑");
		System.out.println(font2);
		fontProvider.printName("代理模式实现AOP");
	}
}