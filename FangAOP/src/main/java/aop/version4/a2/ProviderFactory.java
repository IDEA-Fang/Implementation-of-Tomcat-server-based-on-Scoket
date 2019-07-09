package aop.version4.a2;

import java.lang.reflect.Proxy;

import aop.version4.CachedProviderHandler;
import aop.version4.FontProvider;
import aop.version4.FontProviderFromDisk;

public class ProviderFactory {
 
	public static FontProvider getFontProvider() {
		Class<FontProvider> targetClass = FontProvider.class;
		return (FontProvider)Proxy.newProxyInstance(targetClass.getClassLoader(), new Class[]{targetClass},
				new CachedProviderHandler(new FontProviderFromDisk()));
		
	}
}