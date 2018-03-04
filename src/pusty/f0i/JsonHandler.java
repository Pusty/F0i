package pusty.f0i;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonReader;

/**
 * A short file to make json file reading easier (and to be able to replace it easily with other json reading libraries)
 */
public class JsonHandler {
	/**
	 * A static JsonReader instance used to read json files
	 */
	private static JsonReader jsonReader = new JsonReader();
	
	/**Returns JsonObject from File**/
	public static JsonValue getObjectFromFile(InputStream is) {
		  JsonValue jsonobject = null;
	        jsonobject = jsonReader.parse(is);
	        return jsonobject;
	}
	
	/**Returns JsonArray from File**/
	public static JsonValue getArrayFromFile(InputStream is) {
		JsonValue jsonarray = null;
	        BufferedReader bufferedreader = null;
	        try {
	            bufferedreader = new BufferedReader(new InputStreamReader(is));
	            jsonarray = jsonReader.parse(is);
	        }
	        finally {
	           try {if(bufferedreader!=null)bufferedreader.close();} catch (Exception e) {e.printStackTrace();}
	        }
	        return jsonarray;
	}

	
}
