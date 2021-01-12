package cn.antigenmhc.otaku.service.manager.pojo.vo;

import cn.antigenmhc.otaku.service.manager.pojo.Subject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/11 20:18
 * @Version: 1.0
 **/
@Data
public class AnimeSubjectVo implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;
    private String title;
    private Integer sort;
    private List<AnimeSubjectVo> childrenSubject = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        AnimeSubjectVo that = (AnimeSubjectVo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
