package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // static 형식
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello Spring!!");
        return "hello";
    }

    // mvc 엔진
    //required = true 가 default (기본값이지만 보이기위해 씀)
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name", required = true) String name, Model model) {
        // ex)"spring" -> attributeName : spring
        model.addAttribute("name", name);
        return "hello-template";
    }
    //template 엔진
    @GetMapping("hello-string")
    @ResponseBody           //http통신 프로토콜에서 hello name 을 바로 받음 (html형식 안 가져감)
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }


    // api 방식 (보통은 json 형식으로 사용)
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;

    }
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
