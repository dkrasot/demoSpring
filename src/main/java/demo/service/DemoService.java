package demo.service;

import demo.entity.Tweet;
import demo.entity.UserAccount;

import java.util.List;

public interface DemoService {

    void saveUserAccount(UserAccount profile);
    void saveTweet(Tweet tweet);
    UserAccount getUserAccount(long id);
    UserAccount getUserAccount(String username);
    Tweet getTweetById(long id);
    List<UserAccount> getAllUserAccounts();
    List<Tweet> getTweetsForProfile(UserAccount profile);
    List<Tweet> getTweetsForProfile(String username);
    List<Tweet> getRecentTweets(int count);
    void deleteTweet(long id);
    void startFollowing(UserAccount follower, UserAccount followee);
}
