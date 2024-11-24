package sg.edu.nus.iss.vttp5_ssf_day12workshop.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping(path ="")
public class HomeController {
    

    @GetMapping(path = "")
    public String showHome(){
        return "index";
    }

    @GetMapping(path = "/form")
    public String showForm(){
        return "form";
    }

    @PostMapping(path = "/form")
    public String formHandling(@RequestBody MultiValueMap<String, String> formMap, Model model){
        Integer noOfRandomNo = Integer.parseInt(formMap.getFirst("noOfRandomNo"));
        Random randomGenerator = new Random();
        Set<Integer> randomNoSet = new HashSet<>(); //means only unique numbers will go in

        while(randomNoSet.size() < noOfRandomNo){
            Integer randomNo = randomGenerator.nextInt(31);
            randomNoSet.add(randomNo);
        }

        //List<Integer> randomNoList = new ArrayList<>(randomNoSet);
        List<String> randomeNo_HREF_List = new ArrayList<>();
        for (Integer randomNo : randomNoSet){
            String HREF = "images/number" + String.valueOf(randomNo) + "_75.jpg";
            randomeNo_HREF_List.add(HREF);
        }

        model.addAttribute("noOfRandomNo", noOfRandomNo);
        //model.addAttribute("randomNoList", randomNoList);
        model.addAttribute("randomNo_HREF_List", randomeNo_HREF_List);
        

        return "result";
    }


}
