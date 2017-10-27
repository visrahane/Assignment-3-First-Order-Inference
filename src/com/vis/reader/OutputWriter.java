/**
 *
 */
package com.vis.reader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author vis
 *
 */
public class OutputWriter {

	private String fileName;

	public OutputWriter(String fileName) {
		this.fileName=fileName;
	}

	public void writeFile(List<String> outputList) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(fileName));
			for (String str : outputList) {
				bw.append(str);
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
