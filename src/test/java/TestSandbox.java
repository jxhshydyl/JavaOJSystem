import java.math.BigInteger;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

import cn.superman.system.sandbox.constant.CommunicationSignal;
import cn.superman.system.sandbox.dto.Problem;
import cn.superman.system.sandbox.dto.Request;

import cn.superman.web.dto.ProblemAnswerDTO;
import cn.superman.web.service.front.AnswerSubmitService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:configs/spring/applicationContext.xml","classpath*:configs/spring/springmvc.xml"})
public class TestSandbox{

	@Autowired
	AnswerSubmitService answerSubmitService;

	public static void main(String[] args) throws Exception {

		System.out.println("测试客户端");
		Socket socket = new Socket("127.0.0.1", 50011);
		Scanner scanner = new Scanner(System.in);
		final Scanner scanner2 = new Scanner(socket.getInputStream());
		Gson gson = new Gson();
		int i = 0;

		new Thread() {
			public void run() {
				while (true) {
					if (scanner2.hasNextLine()) {
						System.out.println(scanner2.nextLine());
					}
				}
			};
		}.start();

		while (scanner.hasNext()) {
			i = scanner.nextInt();
			Request request = new Request();
			if (i == 0) {
				request.setCommand(CommunicationSignal.RequestSignal.SANDBOX_STATUS);
			} else if (i == 1) {
				request.setCommand(CommunicationSignal.RequestSignal.CLOSE_SANDBOX);
			} else if (i == 2) {
				request.setCommand(CommunicationSignal.RequestSignal.REQUSET_JUDGED_PROBLEM);
				Problem problem = new Problem();
				problem.setRunId("1111111111111");
				problem.setClassFileName("Main");
				problem.setTimeLimit(1000);
				problem.setMemoryLimit(1024 * 1024 * 60);
				problem.setInputDataFilePathList(Arrays
						.asList("C:\\Users\\john\\Desktop\\problem\\兰顿蚂蚁\\in\\input1.txt",
								"C:\\Users\\john\\Desktop\\problem\\兰顿蚂蚁\\in\\input2.txt",
								"C:\\Users\\john\\Desktop\\problem\\兰顿蚂蚁\\in\\input3.txt",
								"C:\\Users\\john\\Desktop\\problem\\兰顿蚂蚁\\in\\input4.txt"));
				request.setData(gson.toJson(problem));
			} else if (i == 3) {
				request.setCommand(CommunicationSignal.RequestSignal.IS_BUSY);
			}

			socket.getOutputStream().write(
					(gson.toJson(request) + "\n").getBytes());

		}

		scanner.close();
	}

	@Test
	public void test(){
		ProblemAnswerDTO dto=new ProblemAnswerDTO();
		dto.setCodeLanguage("java");
		dto.setCode("import java.util.*;\n" +
				"public class Main {\n" +
				"\tpublic static void main(String[] args){\n" +
				"\t\tScanner sc=new Scanner(System.in);\n" +
				"\t\twhile(sc.hasNext()){\n" +
				"\t\t\tint n=sc.nextInt();//空汽水瓶数\n" +
				"\t\t\tint count=0;//能喝汽水瓶数\n" +
				"\t\t\tif(n>0){\n" +
				"\t\t\t\twhile(n>1){\n" +
				"\t\t\t\t\tcount+=n/3;\n" +
				"\t\t\t\t\tn=n%3+n/3;\n" +
				"\t\t\t\t\tif(n==2){\n" +
				"\t\t\t\t\t\tn++;\n" +
				"\t\t\t\t\t}\n" +
				"\t\t\t\t}\n" +
				"\t\t\t\tSystem.out.println(count);\n" +
				"\t\t\t}\n" +
				"\t\t}\n" +
				"\t\tsc.close();\n" +
				"\t}\n" +
				"}");
		dto.setSubmitProblemId(BigInteger.valueOf(129));
		answerSubmitService.dealCode(dto,null);

	}

}
