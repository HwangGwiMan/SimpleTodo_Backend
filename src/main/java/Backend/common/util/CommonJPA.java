package Backend.common.util;

import Backend.common.AbstractDto;
import Backend.common.AbstractEntity;
import Backend.common.DirtyFlag;
import Backend.common.mapper.GenericMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class CommonJPA {

    public static <D extends AbstractDto, E extends AbstractEntity, Repo extends JpaRepository<E, Long>, Conv extends GenericMapper<D, E>> void saveEntity(D dto, Repo repo, Conv conv) {
        DirtyFlag dirtyFlag = dto.getDirtyFlag();
        E entity = conv.toEntity(dto);

        switch (dirtyFlag.getFlag()) {
            case INSERT, UPDATE -> repo.save(entity);
            case DELETE -> repo.delete(entity);
        }
    }

    public static <D extends AbstractDto, E extends AbstractEntity, Repo extends JpaRepository<E, Long>, Conv extends GenericMapper<D, E>> void saveEntity(List<D> dtoList, Repo repo, Conv conv) {
        dtoList.forEach(dto -> saveEntity(dto, repo, conv));
    }
}
