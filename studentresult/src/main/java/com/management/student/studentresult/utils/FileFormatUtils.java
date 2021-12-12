/**
 * 
 */
package com.management.student.studentresult.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

/**
 * @author PRATAP
 *
 */
public class FileFormatUtils {

	private static Map<String, Map<String, Integer>> map = new HashedMap<String, Map<String, Integer>>();

	public static Map<String, Map<String, Integer>> readFile() throws IOException {
		File file = new File(Constants.FEILD_CONFIGURATION_FILE);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;

		while ((line = br.readLine()) != null) {
			String[] input = line.split(Constants.FEILD_CONFIGURATION_KEY_VAL_SEP);
			String fileName = input[0];
			String[] buffer = input[1].split(Constants.FEILD_CONFIGURATION_FIELD_SEP);
			Map<String, Integer> fieldsMap = new HashedMap<String, Integer>();
			for (int i = 0; i < buffer.length; i++) {
				fieldsMap.put(buffer[i], i);
			}
			map.put(fileName, fieldsMap);
		}
		br.close();
		System.out.println(
				"Reading File Format Done Successfully.Proceeding with further processes......................");
		return map;
	}

	public static Map<String, Integer> getFields(String format) {
		return map.getOrDefault(format.toUpperCase(), null);
	}
}
