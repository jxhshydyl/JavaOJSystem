package cn.superman.system.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author pjf 去除c语言中重复代码 ，根据关键字判断
 * @author cs
 *
 */
public class CPlusPlusCompare extends Compare {

	/* C++关键字 */
	private String keyWords = "and|asm|auto|bad_cast|bad_typeid|bool|break|case|catch|char|class|const|const_cast"
			+ "|continue|default|delete|do|double|dynamic_cast|else|enum|except|explicit|extern|false|finally|float|for"
			+ "|friend|goto|if|inline|int|long|mutable|namespace|new|operator|or|private|protected|public|register|reinterpret_cast"
			+ "|return|short|signed|sizeof|static|static_cast|struct|switch|template|this|throw|true|try|type_info|typedef"
			+ "|typeid|typename|union|unsigned|using|virtual|void|volatile|wchar_t|while";
	private HashSet<String> keyWordSet = new HashSet<String>();
	private LD ld = new LD();

	public CPlusPlusCompare() {

		String list[] = keyWords.split("\\|");
		for (String keyword : list) {

			keyWordSet.add(keyword);

		}

	}

	/**
	 * @author pjf `读取文件
	 * @param code
	 * @return
	 */
	private String delVariables(String code) {

		code = "   " + code + "  ";
		// System.out.println("!"+code);
		int pos1 = 0, pos2 = 0;
		int len = code.length();
		boolean isVariables = false;
		StringBuffer ret = new StringBuffer();
		while (pos1 < len) {

			pos2++;
			if (isVariables) {

				if (code.substring(pos2, pos2 + 2).replaceAll("[0-9a-zA-Z_][^a-zA-Z_]", "").equals("")) {

					isVariables = false;
					String vv = code.substring(pos1, pos2 + 1);
					if (this.keyWordSet.contains(vv)) {

						ret.append(vv);
						// System.out.println("vv="+vv);

					}
					pos1 = pos2 + 1;

				}

			} else {

				if (code.substring(pos2, pos2 + 2).replaceAll("[^\\._a-zA-Z][_a-zA-Z]", "").equals("")) {

					isVariables = true;
					ret.append(code.substring(pos1, pos2 + 1));
					// System.out.println(code.substring(pos1,pos2+1));
					pos1 = pos2 + 1;

				}

			}
			if (pos2 == len - 2)
				break;

		}

		return ret.toString().trim();
		// return
		// code.replaceAll("(?<=([^\\._a-zA-Z]))[a-zA-Z_]+[0-9_a-zA-Z]*(?=([^a-zA-Z_]))",
		// "");

	}

	@Override
	public String getPreProcessedCode(String filePath) {

		// TODO Auto-generated method stub
		String code = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
			StringBuffer buf = new StringBuffer();
			String line=null;
			while ((line = br.readLine()) != null) {
				buf.append(line + "\n");
			}
			// 删除所有注释
			buf.append("import java.util.Scanner;\n" +
					"\n" +
					"/**\n" +
					" *java测试\n" +
					" */\n" +
					"public class Main {\n" +
					"    /**\n" +
					"     * main函数\n" +
					"     * @param args\n" +
					"     */\n" +
					"    public static void main(String[] args) {\n" +
					"        /*\n" +
					"         测试1\n" +
					"         */\n" +
					"        Scanner sc = new Scanner(System.in);//测试2\n" +
					"        while (sc.hasNext()) {\n" +
					"            //测试3\n" +
					"            int n = sc.nextInt();\n" +
					"            int[] arr = new int[n];\n" +
					"            for (int i = 0; i < arr.length; i++) {\n" +
					"                arr[i] = sc.nextInt();\n" +
					"            }\n" +
					"            int[] dp = new int[n + 1];\n" +
					"            for (int i = 0; i < n; i++) {\n" +
					"                int endPosition = Math.min(i + arr[i], n);\n" +
					"                for (int j = i + 1; j <= endPosition; j++) {\n" +
					"                    if (dp[j] == 0) dp[j] = dp[i] + 1;\n" +
					"                }\n" +
					"                if (dp[n] != 0 || (arr[i] == 0 && dp[i] == 0)) break;\n" +
					"            }\n" +
					"            if (dp[n] != 0) System.out.println(dp[n]);\n" +
					"            else System.out.println(-1);\n" +
					"        }\n" +
					"    }\n" +
					"}");
			code = DelComments.delComments(buf.toString());
			System.out.println(code+"==");
			/*
			int pos1 = 0, pos2 = 0;
			int len = code.length();
			boolean isString = false;
			StringBuffer ret = new StringBuffer();
			while (pos1 < len) {

				pos2++;
				if (isString) {

					if (pos2 < len - 1) {

						if (code.substring(pos2, pos2 + 1).equals("\"")
								&& !code.subSequence(pos2 - 1, pos2).equals("\\")) {

							isString = false;
							ret.append(delVariables(code.substring(pos1, pos2 + 1)));
							pos1 = pos2 + 1;

						}

					} else {

						break;

					}

				} else {

					if (pos2 < len - 1) {

						if (code.substring(pos2, pos2 + 1).equals("\"")) {

							isString = true;
							ret.append(delVariables(code.substring(pos1, pos2)));
							pos1 = pos2;

						}

					} else {

						ret.append(delVariables(code.substring(pos1, code.length())));
						break;

					}

				}

			}
			code = ret.toString();*/

			// 删除所有空格和换行
			code = code.replaceAll("\\s", "");
			System.err.println(code);
			br.close();

		} catch (Exception e) {

			e.printStackTrace();

		}
		return code;

	}

	@Override
	public double getSimilarity(String code1, String code2) {

		// TODO Auto-generated method stub
		return 1 - ld.ld(code1, code2) * 1.0 / Math.max(code1.length(), code2.length());

	}

	public static void main(String[] args) throws IOException {

		CPlusPlusCompare cmp = new CPlusPlusCompare();
		File dic = new File("F:\\solution_directory");
		String names[] = { "3695.cpp"};
		System.out.println(dic.listFiles().length);
		for (String name : names) {

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("F:\\solution_directory\\" + name)));
			// bw.write("题目："+name);
			System.out.println("题目：" + name);
			bw.newLine();
			ArrayList<String> idList = new ArrayList<String>();
			ArrayList<String> codeList = new ArrayList<String>();
			for (File f1 : dic.listFiles()) {
				File f2 = new File(f1.getAbsoluteFile()+"");
				if (f2.exists()) {
					idList.add(f1.getName());
					codeList.add(cmp.getPreProcessedCode(f2.getAbsolutePath()));
				}

			}
			for(String list:codeList) {
				System.out.println(list);
			}
			//System.out.println(codeList);
			for (int i = 0; i < codeList.size(); i++) {

				for (int j = i + 1; j < codeList.size(); j++) {

					double s = cmp.getSimilarity(codeList.get(i), codeList.get(j));
					System.out.println(s);

					if (s >= 0.7) {

						bw.write(idList.get(i) + "\t" + idList.get(j) + "\t" + s);
						bw.newLine();

					}

				}

			}
			bw.close();

		}

	}

}