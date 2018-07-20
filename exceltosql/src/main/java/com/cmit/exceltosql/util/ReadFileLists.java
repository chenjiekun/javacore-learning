package com.cmit.exceltosql.util;
/** 
* @author chenjk
* @date 2018/7/19
*
*/

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;

@Service
public class ReadFileLists {

	public ReadFileLists() {

	}

	/**
	 * 读取path文件夹下的所有文件，并返回各个文件的绝对路径
	 * 
	 * @param path
	 *            包含文件的文件夹路径
	 * @return 所有文件的绝对路径
	 */
	public ArrayList<String> readFileList(String path) {
		ArrayList<String> fileList = new ArrayList<String>();
		File file = new File(path);
		if (!file.exists()) {
			System.err.println(path + " not exists");

		}
		File fa[] = file.listFiles();
		if (!(fa == null)) {
			for (int i = 0; i < fa.length; i++) {
				File fs = fa[i];
				if (fs.exists()) {
					File file1[] = fs.listFiles();
					if (!(file1 == null)) {
						for (int j = 0; j < file1.length; j++) {
							File fss = file1[j];
							if (fss.exists()) {
								File file2[] = fss.listFiles();
								if (!(file2 == null)) {
									for (int k = 0; k < file2.length; k++) {
										File fsss = file2[k];
										fileList.add(fsss.getAbsolutePath());
									}
								}
							} else {
								fileList.add(fss.getAbsolutePath());
							}
						}
					} else {
						fileList.add(fs.getAbsolutePath());
					}

				}
			}

		} else {
			fileList.add(file.getAbsolutePath());
		}
		return fileList;

	}
}
