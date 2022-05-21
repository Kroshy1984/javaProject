package com.example.demo_conv;

import com.example.demo_conv.models.convertings;
import com.example.demo_conv.models.products;
import com.example.demo_conv.repo.convertings_repo;
import com.example.demo_conv.repo.products_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ConvertController {
    @Autowired
    private products_repo Products_repo;

    @Autowired
    private convertings_repo Convertings_repo;

    @GetMapping("/convert")
    public String convert(Model model,@RequestParam String product,@RequestParam int count)
    {
        Long id = 0L;
        Iterable<products> products = Products_repo.findAll();
        for (products p:products) {
            if (p.getName().equals(product)){
                id = p.getId();
            }
        }
        if (id == 0){
            String Str =  "Продукта нет в базе!!!";
            model.addAttribute("Str",Str);
            return "convert";
        }
        Optional<convertings> convs = Convertings_repo.findById(id);
        convertings c = convs.get();
        String Str = product + " в метрической системе = "+" "+count+" "+c.getMetric_name()+"\n в бытовой системе это соответсвует "+count / c.getCoefficient()+" "+c.getHousehold_name();
        model.addAttribute("Str",Str);
        return "convert";
    }
}
