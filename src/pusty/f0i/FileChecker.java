package pusty.f0i;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonValue;



public class FileChecker {
	
	/**
	 * Loads FileHandles from a folder with given prefix and suffix
	 * @param prefix prefixes to search for (null or empty to ignore)
	 * @param suffix suffixes to search for (null or empty to ignore)
	 * @return returns a list with the file handles within the folder
	 */
	public static List<FileHandle> checkFolder(String prefix,String suffix) {
		List<FileHandle> list = new ArrayList<FileHandle>();
		for(Entry<String,FileHandle> entry:getStreams().entrySet()) {
			String name = splitNonRegex(entry.getKey(),".")[0];
			String type = splitNonRegex(entry.getKey(),".")[1];
			if(suffix != null && !type.equalsIgnoreCase(suffix))continue;
			if(prefix != null && !name.startsWith(prefix))continue;
			list.add(entry.getValue());
		}
		return list;
	}
	/**
	 * Loads FileHandles from a folder with given prefix and suffix and puts them in a hashmap
	 * @param prefix prefixes to search for (null or empty to ignore)
	 * @param suffix suffixes to search for (null or empty to ignore)
	 * @return returns a hashmap with the files as value and the names as key
	 */
	public static HashMap<String,FileHandle> checkFolderToHashMap(String prefix,String suffix) {
		HashMap<String,FileHandle> list = new HashMap<String,FileHandle>();
		for(Entry<String,FileHandle> entry:getStreams().entrySet()) {
			if(splitNonRegex(entry.getKey(),".").length < 2)continue;
			String name = splitNonRegex(entry.getKey(),".")[0];
			String type = splitNonRegex(entry.getKey(),".")[1];
			if(type==null)continue;
			if(suffix != null && !type.equalsIgnoreCase(suffix))continue;
			if(prefix != null && !name.startsWith(prefix))continue;
			list.put(entry.getKey(),entry.getValue());
		}
		return list;
	}
	
	/**
	 * A custom split function that ignores regex
	 * @param input string to split
	 * @param delim strings to split by
	 * @return a split string array
	 */
	public static String[] splitMultiNonRegex(String input, String... delim)
	{
		String[] last = splitNonRegex(input, delim[0]);
	    for(int a=1;a<delim.length;a++) {
	    	List<String> l = new ArrayList<String>();
	    	for(int b=0;b<last.length;b++) {
	    		String[] t = splitNonRegex(last[b], delim[a]);
	    		for(int c=0;c<t.length;c++)
	    			l.add(t[c]);
	    	}
	    	last = l.toArray(new String[l.size()]);
	    }
	    return last;
	}
	/**
	 * A custom split function that ignores regex
	 * @param input string to split
	 * @param delim string to split by
	 * @return a split string array
	 */
	public static String[] splitNonRegex(String input, String delim)
	{
	    List<String> l = new ArrayList<String>();
	    int offset = 0;

	    while (true)
	    {
	        int index = input.indexOf(delim, offset);
	        if (index == -1)
	        {
	            l.add(input.substring(offset));
	            return l.toArray(new String[l.size()]);
	        } else
	        {
	            l.add(input.substring(offset, index));
	            offset = (index + delim.length());
	        }
	    }
	}
	/**
	 * Replaces parts of a string with another string
	 * @param in string in which to replace parts
	 * @param ths string part to replace
	 * @param that string part which replaces the old one
	 * @return a string with replaced parts
	 */
	public static String replaceAll(String in, String ths, String that) {
	    StringBuilder sb = new StringBuilder(in);
	    int idx = sb.indexOf(ths); 
	    
	    while (idx > -1) {
	        sb.replace(idx, idx + ths.length(), that);
	        idx = sb.indexOf(ths);

	    }
	    
	    return sb.toString();

	}
	/**
	 * The hashmap with all the file handles within
	 */
	static HashMap<String,FileHandle> hashMaps = null;
	
	/**
	 * Returns a hashmap with filehandles and filenames
	 * @return a hashmap with filehandles and filenames
	 */
	public static HashMap<String,FileHandle> getStreams() {
		if(hashMaps==null)
			hashMaps = createStreams();
		return hashMaps;
	}
	/**
	 * Closes all filestreams to files within the static hashmap
	 */
	public static void close() {
		if(hashMaps!=null) {
		for(Entry<String,FileHandle> entry:hashMaps.entrySet()) {
			try {
				entry.setValue(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		hashMaps.clear();
		}
	}
	/**
	 * Creates Streams based on the content of the "resources/files.json"
	 * @return a hashmap with filenames and filehandles within
	 */
	public static HashMap<String,FileHandle> createStreams() {
		HashMap<String,FileHandle> streams = new HashMap<String,FileHandle>();
		try {
			FileHandle fileHandle = Gdx.files.internal("resources/files.json");
			JsonValue jsonArray = JsonHandler.getArrayFromFile(fileHandle.read());
			
			for(JsonValue jsonEntry:jsonArray) {
				FileHandle file = Gdx.files.internal(jsonEntry.asString());
				streams.put(file.name(),file);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return streams;
	}
}
