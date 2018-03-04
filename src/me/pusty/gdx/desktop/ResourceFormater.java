package me.pusty.gdx.desktop;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import pusty.f0i.FileChecker;

import com.badlogic.gdx.utils.JsonWriter;


/**
 * Formats resources into a json file to load them internally on all platforms (Desktop, Browser, Android, IOS)
 */
public class ResourceFormater {
	/**
	 * Formats resources into a json file to load them internally on all platforms (Desktop, Browser, Android, IOS)
	 * @param args arguments, not used
	 */
	public static void main(String[] args) {
		createStreams();
		File file = new File(getURL("resources/files.json").getFile());
		try {
		FileWriter fileWriter = new FileWriter(file);
		JsonWriter writer = new JsonWriter(fileWriter);

		writer.array();
		HashMap<String,File> hashmap = ResourceFormater.getStreams();
		for(Entry<String,File> entry:hashmap.entrySet())
			writer.json('"'+entry.getKey()+'"');
		writer.pop();
		writer.close();
		
		}catch(Exception io) {
			io.printStackTrace();
		}
		
	}
		/**
		 * Search for all files with in the directory which have a specific suffix
		 * @param dir the directory to start searching from
		 * @param suffix the suffix to search (null or empty to ignore)
		 * @return a list with all searched files within
		 */
		public static List<File> checkFolderSuffixFile(File dir,String suffix) {
			return checkFolderFile(dir,null,suffix,null);
		}
		/**
		 * Search for all files with in the directory which have a specific prefix
		 * @param dir the directory to start searching from
		 * @param prefix the prefix to search (null or empty to ignore)
		 * @return a list with all searched files within
		 */
		public static List<File> checkFolderPrefixFile(File dir,String prefix) {
			return checkFolderFile(dir,prefix,null,null);
		}
		/**
		 * Returns a List which contains all files within a given directory and all files within directories within the directory
		 * @param dir the directory to start searching from
		 * @param prefix the file prefixes to search for (null or empty to ignore)
		 * @param suffix the file suffixes to search for (null or empty to ignore)
		 * @param list the list to add to (null if searching just began)
		 * @return a list with all files
		 */
		public static List<File> checkFolderFile(File dir,String prefix,String suffix,List<File> list) {
			if(list==null)
				list = new ArrayList<File>();
			try {
			for(File file:dir.listFiles()) {
				if(file==null)continue;
				if(file.isDirectory()) {
					list = checkFolderFile(file,prefix,suffix,list);
					continue;
				}
				String name = file.getName().split(Pattern.quote("."))[0];
				String type = file.getName().split(Pattern.quote("."))[1];
				if(suffix != null && !type.equalsIgnoreCase(suffix))continue;
				if(prefix != null && !name.startsWith(prefix))continue;
				list.add(file);
			}
			} catch(Exception e) { e.printStackTrace(); }
			return list;
		}
		
		/**
		 * Puts all files within getSteams() in a list
		 * @param prefix prefixes to search for (empty or null to ignore)
		 * @param suffix suffixes to search for (empty or null to ignore)
		 * @return a list which contains all the Files with given prefix and suffix
		 */
		public static List<File> checkFolder(String prefix,String suffix) {
			List<File> list = new ArrayList<File>();
			for(Entry<String,File> entry:getStreams().entrySet()) {
				String name = entry.getKey().split(Pattern.quote("."))[0];
				String type = entry.getKey().split(Pattern.quote("."))[1];
				if(suffix != null && !type.equalsIgnoreCase(suffix))continue;
				if(prefix != null && !name.startsWith(prefix))continue;
				list.add(entry.getValue());
			}
			return list;
		}
		/**
		 * Puts all files within getSteams() in a HashMap
		 * @param prefix prefixes to search for (empty or null to ignore)
		 * @param suffix suffixes to search for (empty or null to ignore)
		 * @return a HashMap which contains all the Files with given prefix and suffix and their names
		 */
		public static HashMap<String,File> checkFolderToHashMap(String prefix,String suffix) {
			HashMap<String,File> list = new HashMap<String,File>();
			for(Entry<String,File> entry:getStreams().entrySet()) {
				String name = entry.getKey().split(Pattern.quote("."))[0];
				String type = entry.getKey().split(Pattern.quote("."))[1];
				if(suffix != null && !type.equalsIgnoreCase(suffix))continue;
				if(prefix != null && !name.startsWith(prefix))continue;
				list.put(entry.getKey(),entry.getValue());
			}
			return list;
		}
		/**
		 * A internal HashMap with all Files and Filenames in it
		 */
		private static HashMap<String,File> hashMaps = null;
		
		/**
		 * Returns the internal static HashMap
		 * @return the HashMap which contains all Files and their names
		 */
		public static HashMap<String,File> getStreams() {
			if(hashMaps==null)
				hashMaps = createStreams();
			return hashMaps;
		}
		
		/**
		 * Closes all open streams within the internal static HashMap
		 */
		public static void close() {
			if(hashMaps!=null) {
			for(Entry<String,File> entry:hashMaps.entrySet()) {
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
		 * Creates a HashMap which contains all Files and their names within the resources directory
		 * @return a HashMap with all the files and their names within
		 */
		@SuppressWarnings("deprecation")
		public static HashMap<String,File> createStreams() {
			HashMap<String,File> streams = new HashMap<String,File>();
			try {
					File resources = new File(getURL("resources").getFile());
					List<File> filesResources = checkFolderFile(resources,null,null,null);
					for(File f:filesResources) {
						StringBuilder builder = new StringBuilder(f.toURL().toString());
						String path = resources.toURL().toString();
						builder.replace(0, path.length(), "");		
						streams.put("resources/"+builder.toString(), f);
					}
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			return streams;
		}
		/**
		 * Returns a URL for a specific string
		 * @param name a file reference in String format
		 * @return the file reference int URL format
		 */
	    @SuppressWarnings("deprecation")
		public static URL getURL(String name){
			try {
				char c = '/';
				name = FileChecker.replaceAll(name, "/", System.getProperty("file.separator"));
				return  new File(System.getProperty("user.dir")+c+""+name).toURL();
			} catch (Exception e) {
					e.printStackTrace();
			}
	    	return null;
	    }
}
