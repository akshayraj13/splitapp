package com.howtographql.hackernews;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.howtographql.hackernews.models.AuthData;
import com.howtographql.hackernews.models.Card;
import com.howtographql.hackernews.models.CardInput;
import com.howtographql.hackernews.models.SigninPayload;
import com.howtographql.hackernews.models.User;

import graphql.GraphQLException;

public class Mutation implements GraphQLRootResolver{
	
	private final LinkRepository linkRepository;

    public Mutation(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }
    
    public Link createLink(String url, String description) {
        Link newLink = new Link(url, description);
        linkRepository.saveLink(newLink);
        return newLink;
    }
    public SigninPayload signinUser(AuthData auth) {
        /*User user = userRepository.findByEmail(auth.getEmail());
        if (user.getPassword().equals(auth.getPassword())) {
            return new SigninPayload(user.getId(), user);
        }*/
    	// to do
    	
//        throw new GraphQLException("Invalid credentials");
    	System.out.println("authdata" + auth.getEmail());
    	User user = new User("Akshay", auth.getEmail(), auth.getPassword());
    	SigninPayload signinPayload = new SigninPayload("akshay123", user);
    	return signinPayload;
    }
    
    public SigninPayload addCard(CardInput card){
    	Card c = new Card("a","b" , "c", "d", "e", "f", "g");
    	System.out.println("card" + c.toString());
    	System.out.println("authdata" + card.getEmail());
    	User user = new User("Akshay", card.getEmail(), card.getEmail());
    	SigninPayload signinPayload = new SigninPayload("akshay123", user);
    	return signinPayload;
    }

}
