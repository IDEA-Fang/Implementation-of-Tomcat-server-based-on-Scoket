package aop.version4;

import java.util.HashMap;
import java.util.Map;

 
/**
 * 给FontProvider的getFont添加缓存功能，用静态代理来实现
 * @author 
 *
 */
public class CachedFontProvider implements FontProvider {
 
	private FontProvider fontProvider;
	
	private Map<String, Font> cached = new HashMap<String, Font>();
	
	
	public CachedFontProvider() {
		fontProvider = new FontProviderFromDisk();
	}
 
 
 
	@Override
	public Font getFont(String name) {
		System.out.println("静态代理getFont()");
		Font font = cached.get(name);
		if(font == null) {
			font = fontProvider.getFont(name);
			cached.put(name, font);
		}
		return font;
	}
 
 
 
	@Override
	public void printName(String name) {
		System.out.println("静态代理printName()");
		fontProvider.printName(name);;
		
	}
 
	
}