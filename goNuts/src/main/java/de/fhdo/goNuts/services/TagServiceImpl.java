package de.fhdo.goNuts.services;

import de.fhdo.goNuts.domain.Tag;
import de.fhdo.goNuts.dto.TagDTO;
import de.fhdo.goNuts.interfaces.TagService;
import de.fhdo.goNuts.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<TagDTO> getTags() {
        return tagRepository.findAll().stream().map(tag -> new TagDTO(tag.getId(), tag.getName())).collect(Collectors.toList());
    }
}
