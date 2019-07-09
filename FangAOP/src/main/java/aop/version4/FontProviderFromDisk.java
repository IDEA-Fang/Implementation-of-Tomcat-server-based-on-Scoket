package aop.version4;

public class FontProviderFromDisk implements FontProvider {
 
	
	@Override
	public Font getFont(String name) {
		Font font = new Font();
		font.setName(name);
		return font;
	}
 
	@Override
	public void printName(String name) {
		System.out.println(name);
		
	}
 
}
