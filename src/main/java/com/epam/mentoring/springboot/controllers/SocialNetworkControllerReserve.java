package com.epam.mentoring.springboot.controllers;

import com.epam.mentoring.springboot.beans.User;
import com.epam.mentoring.springboot.services.SocialNetworkService;
import java.util.Map;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

///@Controller
//RequestMapping("/")
public class SocialNetworkControllerReserve {

   // @Resource
    private SocialNetworkService socialNetworkService;

    //@RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "The best social network");
        return "index";
    }

   // @RequestMapping(value = "/users-generate/{count}",method = RequestMethod.GET)
    public String printUsersGenerator(@PathVariable("count") final int count,ModelMap model) {
        socialNetworkService.generateUsers(count);
        model.addAttribute("users", socialNetworkService.getUsers());
        return "index";
    }

    //@RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView allUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", socialNetworkService.getUsers());
        modelAndView.setViewName("users");
        return modelAndView;
    }

    //@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public String getUserFriendships(@PathVariable("id") final int id, Model model) {
        model.addAttribute("friendships", socialNetworkService.getUserFriendShips(id));
        return "friendships";
    }

    //@RequestMapping(value = "/friendships", method = RequestMethod.GET)
    public ModelAndView getFriendships() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("friendships", socialNetworkService.getAllFriendShips());
        modelAndView.setViewName("friendships");
        return modelAndView;
    }

   //@RequestMapping(value = "/users/remove/{id}", method = RequestMethod.GET)
    public ModelAndView removeUser(@PathVariable("id") final int id, ModelMap model) {
        socialNetworkService.removeUser(id);
        return new ModelAndView("forward:/users", model);
    }

    //@RequestMapping(value = "/users/edit_user", method = RequestMethod.GET)
    public String viewAddUser(ModelMap model) {
        User userForm = new User();
        model.put("userForm", userForm);
        model.put("addUser", true);
        return "userPage";
    }

    //@RequestMapping(value = "/users/edit_user/{id}", method = RequestMethod.GET)
    public String editGetUser(@PathVariable("id") final int id, Map<String, Object> model) {
        User userForm = socialNetworkService.getUser(id);
        model.put("userForm", userForm);
        model.put("addUser", false);
        return "userPage";
    }

    //@RequestMapping(value = "/users/add_user", method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("userForm") User user, BindingResult result) {
        System.out.println(result.hasErrors());
        if (result.hasErrors()) {
            return "userPage";
        }
        socialNetworkService.addUser(user);
        return "redirect:/users";
    }

   // @RequestMapping(value = "/users/edit_user/edit_current_user", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("userForm") User user) {
        System.out.println(user);
        socialNetworkService.editUser(user);
        return "redirect:/users";
    }


}
