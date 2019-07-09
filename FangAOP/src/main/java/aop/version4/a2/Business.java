package aop.version4.a2;

import aop.version4.Font;
import aop.version4.FontProvider;
import aop.version4.FontProviderFromDisk;

public class Business {
 
	public static void main(String[] args) {
		FontProvider fontProvider = ProviderFactory.getFontProvider();
		Font font = fontProvider.getFont("微软雅黑");
		System.out.println(font);
		fontProvider.printName("sdfdf");


		CGLibProxy cgLibProxy = new CGLibProxy();
		FontProviderFromDisk proxy = cgLibProxy.getProxy(FontProviderFromDisk.class);
		proxy.printName("微软雅黑");
	}
 
}