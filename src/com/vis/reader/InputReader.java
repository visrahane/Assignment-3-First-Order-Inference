/**
 *
 */
package com.vis.reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.vis.constants.IOConstants;

/**
 * @author Vis
 *
 */
public class InputReader {

	private String fileName;

	public InputReader(String fileName) {
		this.fileName=fileName;
	}

	public List<String> readIntoList() {
		FileReader fr = null;
		try {
			fr = new FileReader(fileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Scanner sc = new Scanner(fr);
		List<String> lines = new ArrayList<>();
		while (sc.hasNext()) {
			lines.add(sc.nextLine());
		}
		return lines;
	}

	public Map<Integer, Float> readIntoMap() {
		FileReader fr = null;
		try {
			fr = new FileReader(fileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Scanner sc = new Scanner(fr);
		Map<Integer, Float> calibrationMap = new HashMap<>();
		while (sc.hasNext()) {
			String words[]=(sc.nextLine().split(IOConstants.WORD_SPLITTER));
			calibrationMap.put(Integer.parseInt(words[0]), Float.parseFloat(words[1]));
		}
		return calibrationMap;
	}

}
