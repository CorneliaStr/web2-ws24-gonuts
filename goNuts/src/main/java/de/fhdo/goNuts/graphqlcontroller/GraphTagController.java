package de.fhdo.goNuts.graphqlcontroller;

import de.fhdo.goNuts.dto.TagDTO;
import de.fhdo.goNuts.interfaces.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphTagController {
    private final TagService tagService;

    @Autowired
    public GraphTagController(TagService tagService) {
        this.tagService = tagService;
    }

    @QueryMapping("tags")
    public List<TagDTO> getTags() {
        return tagService.getTags();
    }

    @MutationMapping
    public TagDTO createTag(@Argument("tag") TagDTO tagDTO) {
        return this.tagService.createTag(tagDTO);
    }

}
