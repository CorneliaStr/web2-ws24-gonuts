package de.fhdo.goNuts.interfaces;

import de.fhdo.goNuts.domain.Tag;
import de.fhdo.goNuts.dto.TagDTO;

import java.util.List;

public interface TagService {
    public List<TagDTO> getTags();
    public TagDTO createTag(TagDTO tagDTO);
}
