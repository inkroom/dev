package cn.inkroom.web.money.gate.controllers.about;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about/")
public class AboutController {

    @RequestMapping("/aboutUs")
    public String aboutUs() {
        return "about/aboutUs";
    }


    @RequestMapping("/introDepart")
    public String depart() {
        return "about/introDepart";
    }

    @RequestMapping("/introInstitute")
    public String institute() {
        return "about/introInstitute";
    }

    @RequestMapping("/introStructure")
    public String structure() {
        return "about/introStructure";
    }



    @RequestMapping("/prodAndTech")
    public String prodAndTeach() {
        return "prodAndTech/prodAndTech";
    }

    @RequestMapping("/technology")
    public String technology() {
        return "prodAndTech/technology";
    }
}
