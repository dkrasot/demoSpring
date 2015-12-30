package demo.web;

import demo.data.UserAccountRepository;
import demo.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class UserAccountController {

    private UserAccountRepository repository;

    @Autowired
    public UserAccountController(UserAccountRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute(new UserAccount());
        return "signUp";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String processRegistrationWithoutFile(@Valid UserAccount userAccount,
                                                 BindingResult bindingResult,
                                                 RedirectAttributes redirectAttributes)
            throws IllegalStateException, IOException {
        if (bindingResult.hasErrors()) {
            return "signUp";
        }
        userAccount = repository.save(userAccount);
        redirectAttributes.addAttribute("username", userAccount.getUsername());
        redirectAttributes.addFlashAttribute("userAccount", userAccount);
        return "redirect:/profile/{username}";
    }

    @RequestMapping(value = "/profile/{username}", method = RequestMethod.GET)
    public String showUserAccountInfo(@PathVariable String username, Model model) {
        if (!model.containsAttribute("userAccount")) {
            UserAccount userAccount = repository.findByUsername(username);
            model.addAttribute(userAccount);
        }
        return "profile";
    }
}
