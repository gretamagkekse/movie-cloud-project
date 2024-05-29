package de.tu_berlin.proscience.mca.team_yellow.team_yellow.service;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.dto.CommentInput;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Comment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private List<Comment> comments = new ArrayList<>();
    private Long nextId = 1L;

    public List<Comment> getAllCommentsByModule(Long id) {
        return comments.stream().filter(comment -> comment.getModule_id() == id).toList();
    }
    public List<Comment> getAllCommentsByUserId(@PathVariable Long id) {
        return comments.stream().filter(comment -> comment.getUser_id() == id).toList();
    }

    //moduleId und userId nach implementierung von modules und users hinzufügen!!
    public void addComment(CommentInput commentInput) {
        Comment comment = new Comment(getNextEntryId(),commentInput.getContent(),commentInput.getAuthor(),8829L, 2L);
        this.comments.add(comment);
    }

    private Long getNextEntryId() {
        return nextId++;
    }
}
