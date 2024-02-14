package com.blog.service;

//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.repository.PostJpaRepository;
import com.blog.repository.PostRepository;
import com.blog.vo.Post;

import io.micrometer.common.util.StringUtils;

@Service
public class PostService {
//	private static List<Post> posts;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	PostJpaRepository postJpaRepository;
	
	public Post getPost(Long id) {
		Post post = postJpaRepository.findOneById(id);
		
		return post;
	}
	

	
	public List<Post> getPosts(){
		List<Post> posts = postJpaRepository.findAllByOrderByUpdtDateDesc();
		
		return posts;
	}
		
	public List<Post> getPostsOrderByUpdtAsc(){
		List<Post> postList = postJpaRepository.findAllByOrderByUpdtDateAsc();
		
		return postList;
	}
	
	public List<Post> getPostsOrderByRegDesc(){
		List<Post> postList = postRepository.findPostOrderByRegDateDesc();
		
		return postList;
	}
	
	public List<Post> searchPostByTitle(String query){
		List<Post> posts = postJpaRepository.findByTitleContainingOrderByUpdtDateDesc(query);
		
		return posts;
	}
	
	public List<Post> searchPostByContent(String query){
		List<Post> posts = postJpaRepository.findByContentContainingOrderByUpdtDateDesc(query);
		
		return posts;
	}
	
	public boolean savePost(Post post) {
		Post result = postJpaRepository.save(post);
		boolean isSuccess = true;
		
		if(result == null) {
			isSuccess = false;
		}
		
		return isSuccess;
	}
	
	public boolean deletePost(Long id) {
		Post result = postJpaRepository.findOneById(id);
		
		if(result == null)
			return false;
		
		postJpaRepository.deleteById(id);
		return true;
	}
	
	public boolean updatePost(Post post) {
		Post result = postJpaRepository.findOneById(post.getId());
		
		if(result == null)
			return false;
		
		if(!StringUtils.isEmpty(post.getTitle())) {
			result.setTitle(post.getTitle());
		}
		
		if(!StringUtils.isEmpty(post.getContent())) {
			result.setContent(post.getContent());
		}
		
		postJpaRepository.save(result);
		
		return true;
	}
		
//	public boolean savePost(Post post) {
//		int result = postRepository.savePost(post);
//		boolean isSuccess = true;
//		
//		if(result == 0) {
//			isSuccess = false;
//		}
//		
//		return isSuccess;
//	}
	
//	public Post getPost(int id) {
//		Post post = posts.get(id-1);
//		
//		return post;
//	}
	
//	public Post getPost() {
//		Post post = new Post(1L, "Mike", "Mike's Post", "Welcome to My blog");
//		
//		return post;
//	}
	
//	public List<Post> getPosts(){
//		posts = new ArrayList<>();
//		
//		posts.add(new Post(1L, "Mike", "Mike's Post", "Welcome to My blog"));
//		posts.add(new Post(2L, "Jason", "It's Jason", "Hi, My name is Jason"));
//
//		return posts;
//	}
	
//	public Post getPost(Long id) {
//	Post post = postRepository.findOne(id);
//	
//	return post;
//}
//	public List<Post> getPosts(){
//	List<Post> postList = postRepository.findPost();
//	
//	return postList;
//}
}
