package de.fhdo.goNuts.graphqlcontroller;

import de.fhdo.goNuts.domain.Tag;
import de.fhdo.goNuts.interfaces.TagService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphTagController {
    private final TagService tagService;

    public GraphTagController(TagService tagService) {
        this.tagService = tagService;
    }

    @QueryMapping("tags")
    public List<Tag> getTags() {
        return tagService.getTags();
    }
}
