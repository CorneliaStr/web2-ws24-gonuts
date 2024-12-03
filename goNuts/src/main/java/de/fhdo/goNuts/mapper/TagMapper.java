package de.fhdo.goNuts.mapper;

import de.fhdo.goNuts.domain.Tag;
import de.fhdo.goNuts.dto.TagDTO;
import org.springframework.stereotype.Component;

@Component
public class TagMapper implements Mapper<Tag, TagDTO> {

    @Override
    public TagDTO mapEntityToDto(Tag entity) {
        if (entity == null) return null;

        return new TagDTO(entity.getId(), entity.getName());
    }

    @Override
    public Tag mapDtoToEntity(TagDTO dto) {
        if (dto == null) return null;

        Tag tag = new Tag(dto.getName());
        tag.setId(dto.getId());
        return tag;
    }
}
