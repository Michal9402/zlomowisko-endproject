package endproject.controller;

import endproject.model.ZlomModel;
import endproject.service.ZlomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ZlomController  {
    private ZlomService serv;

    public ZlomController(ZlomService serv) {
        this.serv = serv;
    }

    @RequestMapping("/")
    public String homePage(Model model) {
        List<ZlomModel> productList = serv.getAll();
        model.addAttribute("productList", productList);
        return "index";
    }
}
