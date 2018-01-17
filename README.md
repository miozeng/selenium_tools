# selenium_tools

selenium的自动化测试工具目前只完成了后台部分。    
1.提供测试项目的录入，包括项目，项目下面的模块，模块下面有测试用例，测试下面有测试步骤。报错项目和模块的测试运行     
2.用例用swagger提供rest api。     
3.测试完成生产测试报告到指定路径。测试路径在dirver.properties中的path指定          
4.前端代码在[selenium_ui](https://github.com/miozeng/selenium_ui)   

测试
1。访问页面：http://localhost:8080/selenium_tools/dist/listproject
2.由于angular2之后angular的router不是使用 #而是直接用html5风格的/   导致在页面刷新时报404的错误
可以通过修改web.xml
``` xml
<error-page>
	    <error-code>404</error-code>
	    <location>/dist/index.html</location>
	</error-page>
```

如果是springboot项目
``` java
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RestErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";
    private static final String TARGET_PATH = "forward:/";

    @RequestMapping(value = "/error")
    public String error() {
        return TARGET_PATH;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
```


新建一个project，project下面新建一个module，进入case页面选择src/test/resources下的testmodule_case1 .csv导入之后则可以进行自动化测


![image](https://github.com/miozeng/selenium_tools/blob/master/img/projectList.png)
project list显示project列表，包括project的信息和操作，可以查看project测试执行结果报告，也可以进入module查看具体测试，左侧菜单栏为属性show为true的project，点击后进入此project的module列表        

![image](https://github.com/miozeng/selenium_tools/blob/master/img/modulelist.png)
显示某项目下的module的信息，和对module可执行的操作，包括runtest，excuteDate是上一次执行测试的时间。

![image](https://github.com/miozeng/selenium_tools/blob/master/img/caselist.png)
test case list是某模块下testcase列表，可以单独跑testcase，success表示此测试用例是否成功，也可以在此处导入csv文档，可运行csv文档的测试用例，看是否能通过测试，也可以下载此测试用例进行调整后重新上传为新的测试用例

![image](https://github.com/miozeng/selenium_tools/blob/master/img/stepList.png)
测试步骤表，测试用例中每一个步骤都做了些什么，如果测试过则会显示测试结果，如果测试失败或者设置了需要take photo则会截取此步骤测屏幕
