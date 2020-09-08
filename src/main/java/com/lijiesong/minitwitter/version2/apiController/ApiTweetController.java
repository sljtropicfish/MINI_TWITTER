package com.lijiesong.minitwitter.version2.apiController;

import com.lijiesong.minitwitter.version2.Exception.ResourceNotFoundException;
import com.lijiesong.minitwitter.version2.domain.Tweet;
import com.lijiesong.minitwitter.version2.domain.TweetDetail;
import com.lijiesong.minitwitter.version2.repository.TweetRepository;
import com.lijiesong.minitwitter.version2.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiTweetController {
    private TweetService tweetService;
    private TweetRepository tweetRepository;

    @Autowired
    public ApiTweetController(TweetService tweetService, TweetRepository tweetRepository) {
        this.tweetService = tweetService;
        this.tweetRepository = tweetRepository;
    }

    @GetMapping("/tweets")
    public List<Tweet> getAllTweets(){
        return this.tweetService.getAllTweets();
    }

    @GetMapping("/tweets/users")
    public List<Object> getAllTweetsWithName(){
        return this.tweetService.getAllTweetsWithName();
    }

    @GetMapping("/tweets/id")
    @ResponseBody
    public Optional<Tweet> getTweetById(@RequestParam int id){
        return this.tweetService.gettweetById(id);
    }

    @GetMapping("/tweets/user")
    @ResponseBody
    public List<Tweet> getTweetsByUserId(@RequestParam int id){
        return this.tweetService.getAllTweetsByUserId(id);
    }

    @PostMapping("/tweets")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTweet(@RequestBody Tweet tweet){
        this.tweetService.createTweet(tweet.getUserId(),tweet.getContent());
    }

    @PutMapping("/tweets/{id}")
    public Tweet updateTweet(@PathVariable int id, @Valid @RequestBody Tweet tweetDetails){
        Tweet tweet = this.tweetService.gettweetById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Tweet", "id", id));
        if(tweetDetails.getUserId() != tweet.getUserId()){
            return null;
        }
        tweet.setContent(tweetDetails.getContent());
        Tweet updateTweet = this.tweetRepository.save(tweet);
        return updateTweet;
    }

    @DeleteMapping("/tweets/delete/{id}/{userId}")
    public void deleteTweet(@PathVariable int id, @PathVariable int userId){
        this.tweetService.deleteById(id, userId);
    }


}
