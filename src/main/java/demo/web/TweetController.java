package demo.web;

import demo.data.UserAccountRepository;
import demo.data.TweetRepository;
import demo.entity.Tweet;
import demo.entity.TweetForm;
import demo.validation.DuplicateTweetException;
import demo.validation.TweetNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tweets")
public class TweetController {

    private TweetRepository tweetRepository;
    private UserAccountRepository accountRepository;

    @Autowired
    //public TweetController(TweetRepository tweetRepository) {
    public TweetController(TweetRepository tweetRepository, UserAccountRepository accountRepository) {
        this.tweetRepository = tweetRepository;
        this.accountRepository = accountRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Tweet> tweetList(@RequestParam(value = "count", defaultValue = "20") int count) {
        return tweetRepository.findRecentTweets(count);
    }

    @RequestMapping(value = "/{tweetId}", method = RequestMethod.GET)
    public String tweet(@PathVariable("tweetId") long tweetId, Model model) {
        Tweet tweet = tweetRepository.findById(tweetId);
        if (tweet == null) {
            throw new TweetNotFoundException();
        }
        model.addAttribute(tweet);
        return "tweet";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveTweet(@Valid TweetForm form, Model model) {
        try {
            tweetRepository.save(
                    //TODO get Username from principal
                    //new Tweet( null, form.getText(), new Date(), accountRepository.getUserIdByUsername("getUsernameFromLogin")));
                    new Tweet( null, form.getText(), new Date(), null));
            return "redirect:/tweets";
        } catch (DuplicateTweetException e) {
            return "error/duplicate";
        }
    }

}
