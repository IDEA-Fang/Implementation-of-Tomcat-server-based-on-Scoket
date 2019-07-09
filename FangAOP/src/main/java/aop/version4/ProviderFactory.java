package aop.version4;

public abstract class ProviderFactory {


    public static FontProvider getFontProvider() {
        return new CachedFontProvider();
    }

}