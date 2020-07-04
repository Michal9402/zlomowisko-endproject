package endproject.controller;

import endproject.model.Zlom;
import endproject.service.ZlomService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class ZlomController {

    private final ZlomService serv;

    public ZlomController(ZlomService serv) {
        this.serv = serv;
    }

    @RequestMapping("/")
    public String homePage(Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        model.addAttribute("message", "You are logged as: " + context.getAuthentication().getName());
        List<Zlom> zlomList = serv.getAll();
        model.addAttribute("zlomList", zlomList);
        return "index";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/pricelist")
    public String pricelistPage(Model model) {
        List<Zlom> zlomList = serv.getAll();
        model.addAttribute("zlomList", zlomList);
        return "pricelist";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/new")
    public String showNewZlomProductForm(Model model) {
        Zlom zlom = new Zlom();
        model.addAttribute("zlom", zlom);
        return "new_product";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("zlom") Zlom zlom) {
        serv.save(zlom);
        return "redirect:/pricelist";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditZlomProductForm(@PathVariable(name = "id") Long id) {
        ModelAndView mvn = new ModelAndView("edit_product");

        Zlom zlom = serv.getById(id);
        mvn.addObject("zlom", zlom);

        return mvn;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        serv.deleteById(id);

        return "redirect:/";
    }
    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
}