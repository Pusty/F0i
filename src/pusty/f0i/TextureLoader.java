package pusty.f0i;


import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;



/**
 * Loads textures into a HashMap for easier access
 */
public class TextureLoader {
	/**
	 * the HashMap which contains all TextureRegions
	 */
	private HashMap<String, TextureRegion> list = new HashMap<String, TextureRegion>();
	
	/**
	 * Creates a new TextureLoader
	 */
	public TextureLoader() {
		list.clear();
	}
	/**
	 * Adds an image to the TextureLoader
	 * @param name Name of the image
	 * @param img The image as a TextureRegion
	 */
	public void addImage(String name, TextureRegion img) {
		list.put(name, img);
	}
	/**
	 * Returns the TextureRegion with the given <code>name</code>
	 * @param name the name of the wanted image
	 * @return the image as a TextureRegion
	 */
	public TextureRegion getImage(String name) {
		if (list.containsKey(name))
			return list.get(name);
		else
			return null;
	}
	/**
	 * Removes a image from the internal HashMap
	 * @param name the image to remove
	 * @return the removed image as a TextureRegion
	 */
	public TextureRegion removeImage(String name) {
		if (list.containsKey(name))
			return list.remove(name);
		else
			return null;
	}
	/**
	 * Returns the HashMap with the images within
	 * @return the HashMap with the images as values and names as keys
	 */
	public HashMap<String, TextureRegion> getList() {
		return list;
	}
	
	/**
	 * Sets a specific HashMap to the internal HashMap of this TextureLoader (it does not clone it)
	 * @param list the HashMap that replaces the old one
	 */
	public void setList(HashMap<String, TextureRegion> list) {
		this.list = list;
	}

	
}
