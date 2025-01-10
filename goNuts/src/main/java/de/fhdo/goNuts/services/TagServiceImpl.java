package de.fhdo.goNuts.services;

import de.fhdo.goNuts.domain.Tag;
import de.fhdo.goNuts.dto.TagDTO;
import de.fhdo.goNuts.interfaces.TagService;
import de.fhdo.goNuts.mapper.TagMapper;
import de.fhdo.goNuts.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    @Override
    public List<TagDTO> getTags() {
        return tagRepository.findAll().stream().map(tag -> tagMapper.mapEntityToDto(tag)).collect(Collectors.toList());
    }

    @Override
    public TagDTO createTag(TagDTO tagDTO) {
        Tag tag = tagMapper.mapDtoToEntity(tagDTO);
        return tagMapper.mapEntityToDto(tagRepository.save(tag));
    }

    @Override
    public TagDTO updateTag(TagDTO tagDTO) {
        Tag tag = tagMapper.mapDtoToEntity(tagDTO);
        return tagMapper.mapEntityToDto(tagRepository.save(tag));
    }

    @Override
    public boolean deleteTag(Long tagID) {
        try {
            tagRepository.deleteById(tagID);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
