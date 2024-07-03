package de.tu_berlin.proscience.mca.team_yellow.team_yellow.model;

public class Module {
    //id, title,....siehe API
    // ID,Title,CourseType,CourseID,CreditHours,Annotation,Content,CourseURL,DetailedDescription,Requirements,Audience ,Comment,CourseAssessment ,Literature,TeachingContents,Cycle

    private Long id;
    private String Title;
    private String Content;


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return Title;
    }

    public String getContent() {
        return Content;
    }
}
