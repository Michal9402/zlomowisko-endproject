package endproject.controller;

import endproject.model.Zlom;
import endproject.service.ZlomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
public class ZlomController  {

    private final ZlomService serv;

    public ZlomController(ZlomService serv) {
        this.serv = serv;
    }

    @RequestMapping("/home")
    public String vievHomePage(Model model) {
        List<Zlom> zlomList = serv.getAll();
        model.addAttribute("zlomList", zlomList);
        return "index";
    }
}
