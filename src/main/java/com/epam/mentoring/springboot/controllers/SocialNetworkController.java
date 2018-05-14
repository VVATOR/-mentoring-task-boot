package com.epam.mentoring.springboot.controllers;

import com.epam.mentoring.springboot.beans.User;
import com.epam.mentoring.springboot.repository.FriendshipRepository;
import com.epam.mentoring.springboot.repository.UserRepository;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class SocialNetworkController {
    private static final Logger LOG = LoggerFactory.getLogger(SocialNetworkController.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FriendshipRepository friendshipRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "The best social network");
        LOG.info("GOTO: index");
        return "index";
    }

    @RequestMapping(value = "/users-generate/{count}",method = RequestMethod.GET)
    public String printUsersGenerator(@PathVariable("count") final int count,ModelMap model) {
        long startTime = System.nanoTime();
        userRepository.generateUsers(count);
        model.addAttribute("users", userRepository.findAll());
        long endTime = System.nanoTime();
        LOG.info("GENERATE: " + count + " USERS (" + (endTime - startTime) + " ns)");
        LOG.info("GOTO: index");
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView allUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", userRepository.findAll());
        modelAndView.setViewName("users");
        LOG.info("GOTO: users");
        return modelAndView;
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public String getUserFriendships(@PathVariable("id") final Integer id, Model model) {
        model.addAttribute("friendships", friendshipRepository.getFriendshipsByUserId1(id));
        LOG.info("GOTO: friendships of user with id(\'"+id+"\')");
        return "friendships";
    }

    @RequestMapping(value = "/friendships", method = RequestMethod.GET)
    public ModelAndView getFriendships() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("friendships", friendshipRepository.findAll());
        modelAndView.setViewName("friendships");

        LOG.info("GOTO: friendships");
        return modelAndView;
    }

    @RequestMapping(value = "/users/remove/{id}", method = RequestMethod.GET)
    public ModelAndView removeUser(@PathVariable("id") final Integer id, ModelMap model) {
        userRepository.deleteById(id);
        LOG.info("DELETE: user with id(\'"+id+"\')");
        return new ModelAndView("forward:/users", model);
    }

    @RequestMapping(value = "/users/edit_user", method = RequestMethod.GET)
    public String viewAddUser(ModelMap model) {
        LOG.info("AAAAAAAAAA /users/edit_user/{id}");
        User userForm = new User();
        model.put("userForm", userForm);
        model.put("addUser", true);
        LOG.info("GOTO: edit_user");
        return "userPage";
    }

    @RequestMapping(value = "/users/edit_user/{id}", method = RequestMethod.GET)
    public String editGetUser(@PathVariable("id") final Integer id, Map<String, Object> model) {
        LOG.info("AAAAAAAAAA /users/edit_user/{id}");
        Optional<User> userForm = userRepository.findById(id);
        model.put("userForm", userForm.get());
        model.put("addUser", false);
        LOG.info("GOTO: edit_user with id(\'"+id+"\')");
        return "userPage";
    }

    @RequestMapping(value = "/users/add_user", method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("userForm") User user, BindingResult result) {
        LOG.info("AAAAAAAAAA /users/add_user");
        System.out.println(result.hasErrors());
        if (result.hasErrors()) {
            return "userPage";
        }
        userRepository.save(user);
        LOG.info("AAAAAAAAAA /users/add_user");
        LOG.info(user.toString());
        return "redirect:/users";
    }

    @RequestMapping(value = "/users/edit_user/edit_current_user", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("userForm") User user) {
        LOG.info("AAAAAAAAAA/users/edit_user/edit_current_user");
        LOG.info(user.toString());
        Optional<User> existing = userRepository.findById(user.getId());
        if(existing != null) user.setId(existing.get().getId());
        userRepository.save(user);

        //User userForm = userRepository.save(user);
        LOG.info(userRepository.findById(existing.get().getId()).toString());
        return "redirect:/users";
    }


}
