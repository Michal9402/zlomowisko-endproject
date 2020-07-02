package endproject.controller;

import endproject.model.Zlom;
import endproject.service.ZlomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ZlomController  {

    private final ZlomService serv;

    public ZlomController(ZlomService serv) {
        this.serv = serv;
    }

    @RequestMapping("/")
    public String homePage(Model model) {
        List<Zlom> zlomList = serv.getAll();
        model.addAttribute("zlomList", zlomList);
        return "index";
    }
    @RequestMapping("/new")
    public String newZlomProduct(Model model) {
        Zlom zlom = new Zlom();
        model.addAttribute("zlom", zlom);
        return "new_product";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("zlom") Zlom zlom){
        serv.save(zlom);
        return "redirect:/";
    }
}
