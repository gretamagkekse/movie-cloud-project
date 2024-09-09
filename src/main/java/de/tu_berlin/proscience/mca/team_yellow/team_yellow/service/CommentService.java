package de.tu_berlin.proscience.mca.team_yellow.team_yellow.service;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.dto.CommentInput;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Comment;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.repository.ModuleCommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private ModuleCommentRepository moduleCommentRepository;

    private UserService userService;


    public CommentService(ModuleCommentRepository moduleCommentRepository, UserService userService) {
        this.moduleCommentRepository = moduleCommentRepository;
        this.userService = userService;
    }

    public List<Comment> getAllComments() {
        return moduleCommentRepository.findAll();
    }

    public Optional<Comment> getCommentById(long id) {
        return moduleCommentRepository.findById(id);
    }

//
//    public List<Comment> getAllCommentsByModule(Long id) {
//        return comments.stream().filter(comment -> comment.getModule_id() == id).toList();
//    }
//    public List<Comment> getAllCommentsByUserId(@PathVariable Long id) {
//        return comments.stream().filter(comment -> comment.getUser_id() == id).toList();
//    }

    //TODO:moduleId und userId nach implementierung von modules und users hinzufügen!!
    public Comment addComment(CommentInput commentInput, String username) {
        Comment comment = new Comment(
                commentInput.getContent(),
                userService.getCurrentUser(username).get()
                );
        return moduleCommentRepository.save(comment);
    }



}
