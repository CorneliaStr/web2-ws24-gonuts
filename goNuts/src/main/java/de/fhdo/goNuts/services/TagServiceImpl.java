package de.fhdo.goNuts.services;

import de.fhdo.goNuts.domain.Tag;
import de.fhdo.goNuts.interfaces.TagService;
import de.fhdo.goNuts.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> getTags() {
        return tagRepository.findAll();
    }
}
